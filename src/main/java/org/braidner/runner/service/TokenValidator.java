package org.braidner.runner.service;

import org.braidner.runner.dto.TokenInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 16/08/2016
 * Time: 14:40
 */
public interface TokenValidator {
    boolean validate(String token);

    TokenInfo getTokenInfo(String token);
}
