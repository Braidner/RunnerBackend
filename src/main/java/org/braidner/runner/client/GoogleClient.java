package org.braidner.runner.client;

import org.braidner.runner.dto.TokenInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by KuznetsovNE on 19.08.2016.
 */
public interface GoogleClient {
    String BASE_URL = "https://www.googleapis.com/oauth2/v3/";

    @GET("tokeninfo?id_token={token}")
    Call<TokenInfo> getTokenInfo(@Path("token") String token);
}