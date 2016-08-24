package org.braidner.runner.service.auth.google;

import org.braidner.runner.client.GoogleAccountsClient;
import org.braidner.runner.client.GoogleClient;
import org.braidner.runner.dto.Token;
import org.braidner.runner.dto.TokenInfo;
import org.braidner.runner.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import retrofit2.Call;

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
    private final GoogleAccountsClient googleAccounts;

    @Value("${google.client_id}")
    private String clientId;

    @Value("${google.client_secret}")
    private String clientSecret;

    /**
     * https://developers.google.com/identity/sign-in/web/backend-auth
     * */

    @Autowired
    public GoogleTokenValidator(GoogleClient googleClient, GoogleAccountsClient googleAccountsClient) {
        this.googleClient = googleClient;
        this.googleAccounts = googleAccountsClient;
    }

    @Override
    public boolean validate(String token) {
        Call<Token> tokenCall = googleAccounts.getToken(clientId, clientSecret, "", token);
        try {
            Token tokenResponse = tokenCall.execute().body();
            String accessToken = tokenResponse.getAccessToken();

            TokenInfo tokenInfo = googleClient.getTokenInfo(accessToken).execute().body();

            tokenStore.put(token, tokenInfo);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public TokenInfo getTokenInfo(String token) {
        return tokenStore.get(token);
    }

    @Override
    public void addToken(String token, UserDetails userDetails) {
        throw new RuntimeException("Not supported");
    }
}
