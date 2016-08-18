package org.braidner.runner.service.impl;

import org.braidner.runner.domain.GoogleAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by KuznetsovNE on 18.08.2016.
 */
@Component
public class TokenFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    private static final String HEADER_SECURITY_TOKEN = "X-GoogleToken";
    private static final String HEADER_SECURITY_USERNAME = "X-Username";

    @Autowired
    public TokenFilter(@Qualifier("tokenManager") AuthenticationManager authenticationManager) {
        super("/api/**");
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler((request, response, authentication) ->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getRequestDispatcher(request.getServletPath() + request.getPathInfo()).forward(request, response);
        });
        setAuthenticationFailureHandler((request, response, authenticationException)
                -> response.getOutputStream().print(authenticationException.getMessage()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(HEADER_SECURITY_TOKEN);
        String username = request.getHeader(HEADER_SECURITY_USERNAME);
        if (token == null || username == null) {
            throw new UsernameNotFoundException("Token not found");
        }
        GoogleAuthentication tokenAuthentication = new GoogleAuthentication(token, username);
        return getAuthenticationManager().authenticate(tokenAuthentication);
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}
