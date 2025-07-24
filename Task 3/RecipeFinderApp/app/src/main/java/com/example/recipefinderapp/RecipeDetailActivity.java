package com.example.recipefinderapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ImageView imageView = findViewById(R.id.detailImage);
        TextView titleText = findViewById(R.id.detailTitle);
        TextView timeText = findViewById(R.id.detailTime);
        TextView servingsText = findViewById(R.id.detailServings);

        String title = getIntent().getStringExtra("title");
        String image = getIntent().getStringExtra("image");
        int time = getIntent().getIntExtra("readyInMinutes", 0);
        int servings = getIntent().getIntExtra("servings", 0);

        titleText.setText(title);
        timeText.setText("Ready in: " + time + " minutes");
        servingsText.setText("Servings: " + servings);
        Glide.with(this).load(image).into(imageView);
    }
}
