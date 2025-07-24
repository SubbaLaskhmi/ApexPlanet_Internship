package com.example.recipefinderapp.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipefinderapp.model.Recipe;
import com.example.recipefinderapp.repository.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends ViewModel {
    private RecipeRepository repository;

    public RecipeViewModel() {
        repository = new RecipeRepository();
    }

    public LiveData<List<Recipe>> getRecipes(String query, String apiKey) {
        return repository.getRecipes(query, apiKey);
    }
}