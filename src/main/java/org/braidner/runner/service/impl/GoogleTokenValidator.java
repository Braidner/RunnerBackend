package org.braidner.runner.service.impl;

import org.braidner.runner.service.TokenValidator;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 16/08/2016
 * Time: 14:39
 */
@Service
public class GoogleTokenValidator implements TokenValidator {

    /**
     * https://developers.google.com/identity/sign-in/web/backend-auth
     * */
    private static final String GOOGLE_TOKEN_INFO = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";

    @Override
    public boolean validate(String token) {
        final String infoUrl = GOOGLE_TOKEN_INFO + token;
        //TODO validate token
        return false;
    }
}
