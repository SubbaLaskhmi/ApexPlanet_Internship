package com.example.recipefinderapp.api;

import com.example.recipefinderapp.model.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApiService {
    @GET("recipes/complexSearch")
    Call<RecipeResponse> getRecipes(
            @Query("query") String query,
            @Query("number") int number,
            @Query("apiKey") String apiKey
    );

}
