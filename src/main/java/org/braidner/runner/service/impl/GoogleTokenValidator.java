package org.braidner.runner.service.impl;

import org.braidner.runner.client.GoogleClient;
import org.braidner.runner.dto.TokenInfo;
import org.braidner.runner.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 16/08/2016
 * Time: 14:39
 */
@Service
public class GoogleTokenValidator implements TokenValidator {

    private Map<String, TokenInfo> tokenStore = new HashMap<>();

    private final GoogleClient googleClient;

    /**
     * https://developers.google.com/identity/sign-in/web/backend-auth
     * */

    @Autowired
    public GoogleTokenValidator(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    @Override
    public boolean validate(String token) {
        Call<TokenInfo> tokenInfo = googleClient.getTokenInfo(token);
        try {
            Response<TokenInfo> infoResponse = tokenInfo.execute();
            TokenInfo body = infoResponse.body();
            if (body != null) {
                tokenStore.put(token, body);
                return body.getEmailVerified();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public TokenInfo getTokenInfo(String token) {
        return tokenStore.get(token);
    }
}
