package org.braidner.runner.service.auth.google;

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
public class GoogleAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(GoogleAuthenticationFilter.class);
    private static final String HEADER_SECURITY_TOKEN = "X-GoogleToken";
    private static final String HEADER_SECURITY_USERNAME = "X-Username";
    private static final String HEADER_SECURITY_USER_ID = "X-User-id";

    @Autowired
    public GoogleAuthenticationFilter(@Qualifier("googleAuthenticationManager") AuthenticationManager authenticationManager) {
        super("/auth/**");
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler((request, response, authentication) ->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getRequestDispatcher(request.getServletPath() + "/auth").forward(request, response);
        });
        setAuthenticationFailureHandler((request, response, authenticationException)
                -> response.getOutputStream().print(authenticationException.getMessage()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(HEADER_SECURITY_TOKEN);
        String username = request.getHeader(HEADER_SECURITY_USERNAME);
        String userId = request.getHeader(HEADER_SECURITY_USER_ID);
        if (token == null || username == null || userId == null) {
            throw new UsernameNotFoundException("Token not found");
        }
        GoogleAuthentication tokenAuthentication = new GoogleAuthentication(token, username, userId);
        return getAuthenticationManager().authenticate(tokenAuthentication);
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}
