package com.manasmalla.ahamsvasth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class RecipieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie);
        RecipesList recipesList = new RecipesList();
        recipesList.setupRecipes();
        Recipe deliverRecipe = recipesList.getRecipes().get(getIntent().getIntExtra("recipe", 0));
        Log.i("Recipe pressed", deliverRecipe.recipeName);
    }
}