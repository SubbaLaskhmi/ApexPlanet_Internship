package com.example.recipefinderapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.recipefinderapp.api.ApiClient;
import com.example.recipefinderapp.api.RecipeApiService;
import com.example.recipefinderapp.model.Recipe;
import com.example.recipefinderapp.model.RecipeResponse;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private RecipeApiService apiService;

    public RecipeRepository() {
        apiService = ApiClient.getRetrofit().create(RecipeApiService.class);
    }

    public MutableLiveData<List<Recipe>> getRecipes(String query, String apiKey) {
        MutableLiveData<List<Recipe>> data = new MutableLiveData<>();

        apiService.getRecipes(query, 10, apiKey).enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d("API_URL", call.request().url().toString());

                if (response.isSuccessful() && response.body() != null) {
                    List<Recipe> recipeList = response.body().getResults();
                    Log.d("API_RESPONSE", "Recipes returned: " + recipeList.size());
                    data.setValue(recipeList);
                } else {
                    Log.e("API_ERROR", "Error: " + response.message());
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Request failed", t);
                data.setValue(null);
            }
        });

        return data;
    }
}
