package com.example.recipefinderapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefinderapp.adapters.RecipeAdapter;
import com.example.recipefinderapp.model.Recipe;
import com.example.recipefinderapp.viewmodel.RecipeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView recyclerView;
    private RecipeViewModel recipeViewModel;

    private String API_KEY = BuildConfig.API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString().trim();

                Log.d("API_KEY", API_KEY); // Logs the API key to Logcat

                if (query.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a recipe name", Toast.LENGTH_SHORT).show();
                    return;
                }

                recipeViewModel.getRecipes(query, API_KEY).observe(MainActivity.this, recipes -> {
                    Log.d("UI_OBSERVER", "Recipe list returned: " + (recipes != null ? recipes.size() : "null"));

                    if (recipes != null && !recipes.isEmpty()) {
                        Log.d("UI_DATA", "Recipes found: " + recipes.size());
                        RecipeAdapter adapter = new RecipeAdapter(recipes, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("UI_DATA", "No recipes or empty list returned.");
                        Toast.makeText(MainActivity.this, "No recipes found", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
