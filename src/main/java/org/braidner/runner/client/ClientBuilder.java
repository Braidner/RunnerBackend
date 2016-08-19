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
    public GoogleClient build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GoogleClient.BASE_URL)
                .build();

        return retrofit.create(GoogleClient.class);
    }


}
