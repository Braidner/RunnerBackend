package org.braidner.runner.service.impl;

import org.braidner.runner.domain.GoogleAuthentication;
import org.braidner.runner.dto.TokenInfo;
import org.braidner.runner.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 16/08/2016
 * Time: 14:33
 */
@Component
public class TokenManager implements AuthenticationManager {

    private final UserDetailsService userDetailsService;

    private final TokenValidator tokenValidator;

    @Autowired
    public TokenManager(UserDetailsService userDetailsService, @Qualifier("googleTokenValidator") TokenValidator tokenValidator) {
        this.userDetailsService = userDetailsService;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof GoogleAuthentication) {
            GoogleAuthentication auth = (GoogleAuthentication) authentication;
            String token = auth.getToken();
            if (!tokenValidator.validate(token)) throw new RuntimeException("Google token is not valid: " + token);
            TokenInfo tokenInfo = tokenValidator.getTokenInfo(token);
            return new GoogleAuthentication(token, true, userDetailsService.loadUserByUsername(tokenInfo.getEmail()));
        }
        throw new AuthenticationServiceException("Token expired date error");
    }
}
