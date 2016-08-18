package org.braidner.runner.service.impl;

import org.braidner.runner.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 16/08/2016
 * Time: 14:33
 * TODO https://javattitude.com/2014/06/07/spring-security-custom-token-based-rest-authentication/
 *
 */
@Component
public class TokenManager implements AuthenticationManager {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("googleTokenValidator")
    private TokenValidator tokenValidator;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
        String token = (String) authentication.getCredentials();
        if (!tokenValidator.validate(token)) {
            throw new RuntimeException("Google token is not valid: " + token);
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    }
}
