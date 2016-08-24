package org.braidner.runner.client;

import org.braidner.runner.dto.Token;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by KuznetsovNE on 19.08.2016.
 */
public interface GoogleAccountsClient {
    String BASE_URL = "https://accounts.google.com/o/oauth2/";

    @POST("token")
    @FormUrlEncoded
    Call<Token> getToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("code") String code
    );
}