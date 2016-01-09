package com.daniel.matters.apis;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by dabraham on 1/8/16.
 */
public class ApiProvider {

    public static MattersApi mattersApi;

    public static synchronized MattersApi mattersApi() {
        if (mattersApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://app.goclio.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mattersApi = retrofit.create(MattersApi.class);
        }

        return mattersApi;
    }
}
