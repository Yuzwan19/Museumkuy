package com.kw.museumkuy.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Izcax on 12/8/17.
 */

public class DevClient {

    public static final String JSC_URL = "http://api.jakarta.go.id/ruang-publik/";
    private static Retrofit retrofit = null;

    public static Retrofit getClientDev() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(JSC_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
