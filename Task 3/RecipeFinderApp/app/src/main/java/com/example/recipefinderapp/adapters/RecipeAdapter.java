package com.example.recipefinderapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipefinderapp.R;
import com.example.recipefinderapp.RecipeDetailActivity;
import com.example.recipefinderapp.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private final List<Recipe> recipeList;
    private final Context context;

    public RecipeAdapter(List<Recipe> recipes, Context ctx) {
        this.recipeList = recipes;
        this.context = ctx;
    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        Log.d("BINDING", "Binding recipe: " + recipe.getTitle());
        holder.title.setText(recipe.getTitle());
        Glide.with(context).load(recipe.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("title", recipe.getTitle());
            intent.putExtra("image", recipe.getImage());
            intent.putExtra("readyInMinutes", recipe.getReadyInMinutes());
            intent.putExtra("servings", recipe.getServings());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.recipeTitle);
            imageView = view.findViewById(R.id.recipeImage);
        }
    }
}
