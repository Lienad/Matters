package com.daniel.matters.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by dabraham on 1/8/16.
 */
public interface MattersApi {

    //https://app.goclio.com/api/v2/matters`.
    @Headers({
            "Authorization: Bearer Xzd7LAtiZZ6HBBjx0DVRqalqN8yjvXgzY5qaD15a",
            "Content-Type: application/json",
            "Accept: application/json"
    })
    @GET("v2/matters")
    public Call<MattersResponse> getMatters();
}
