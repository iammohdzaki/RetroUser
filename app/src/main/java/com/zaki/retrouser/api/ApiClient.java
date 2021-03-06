package com.zaki.retrouser.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private final static String BASE_URL = "http://jsonplaceholder.typicode.com";
    private static Retrofit retrofit = null;

    /**
     * Used to access the Retrofit Object
     *
     * @return a Retrofit Object
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}


