package org.braidner.runner.service;

import org.braidner.runner.dto.TokenInfo;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 16/08/2016
 * Time: 14:40
 */
public interface TokenValidator {
    boolean validate(String token);

    TokenInfo getTokenInfo(String token);

    void addToken(String token, UserDetails userDetails);
}
