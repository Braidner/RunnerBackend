package org.braidner.runner.service.auth.runner;

import org.braidner.runner.domain.RunnerAuthentication;
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
public class RunnerAuthenticationManager implements AuthenticationManager {

    private final UserDetailsService userDetailsService;

    private final TokenValidator tokenValidator;

    @Autowired
    public RunnerAuthenticationManager(UserDetailsService userDetailsService, @Qualifier("runnerTokenValidator") TokenValidator tokenValidator) {
        this.userDetailsService = userDetailsService;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof RunnerAuthentication) {
            RunnerAuthentication auth = (RunnerAuthentication) authentication;
            String token = auth.getToken();
            if (!tokenValidator.validate(token)) throw new RuntimeException("Runner token is not valid: " + token);
            TokenInfo tokenInfo = tokenValidator.getTokenInfo(token);

            return new RunnerAuthentication(token, true, userDetailsService.loadUserByUsername(tokenInfo.getUserId()));
        }
        throw new AuthenticationServiceException("Token expired date error");
    }
}
