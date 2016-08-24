package org.braidner.runner.service.auth.runner;

import org.braidner.runner.dto.TokenInfo;
import org.braidner.runner.service.TokenValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KuznetsovNE on 24.08.2016.
 */

@Component
public class RunnerTokenValidator implements TokenValidator {

    private final Map<String, UserDetails> tokenStore = new HashMap<>();

    @Override
    public boolean validate(String token) {
        return tokenStore.containsKey(token);
    }

    @Override
    public TokenInfo getTokenInfo(String token) {
        UserDetails userDetails = tokenStore.get(token);
        //TODO
        return new TokenInfo();
    }

    @Override
    public void addToken(String token, UserDetails userDetails) {
        tokenStore.put(token, userDetails);
    }

}
