package org.braidner.runner.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

/**
 * Created by KuznetsovNE on 19.08.2016.
 */
@Configuration
public class ClientBuilder {

    @Bean
    public GoogleClient buildGoogleClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GoogleClient.BASE_URL)
                .build();

        return retrofit.create(GoogleClient.class);
    }

    @Bean
    public GoogleAccountsClient buildGoogleAccountsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GoogleAccountsClient.BASE_URL)
                .build();

        return retrofit.create(GoogleAccountsClient.class);
    }


}
