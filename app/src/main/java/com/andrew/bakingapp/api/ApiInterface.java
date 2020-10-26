package com.andrew.bakingapp.api;

import com.andrew.bakingapp.model.Bake;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("baking.json")
    Call<List<Bake>> getReceiptList();

}
