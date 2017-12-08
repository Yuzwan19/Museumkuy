package com.kw.museumkuy.rest;

import com.kw.museumkuy.model.market.MarketModel;
import com.kw.museumkuy.model.place.TempatModel;
import com.kw.museumkuy.model.spbu.SpbuModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Izcax on 12/8/17.
 */

public interface DevInterface {
    @GET("minimarket")
    Call<MarketModel> getMarket();

    @GET("tempat-ibadah")
    Call<TempatModel> getTempat();

    @GET("spbu")
    Call<SpbuModel> getSpbu();

}
