package com.manasmalla.ahamsvasth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RecipieActivity extends AppCompatActivity {

    TextView recipeTextView, proteinIndicator, energyIndicator, carbIndicator, fatIndicator, cholIndicator, fibreIndicator;
    ImageView recipeImageView;
    NonScrollListView ingredient1, ingredient2, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_recipie);
        RecipesList recipesList = new RecipesList();
        recipesList.setupRecipes();
        Recipe deliverRecipe = recipesList.getRecipes(getIntent().getIntExtra("timeDay", 0)).get(getIntent().getIntExtra("recipe", 0));
        Log.i("Recipe pressed", deliverRecipe.recipeName);
        recipeTextView = findViewById(R.id.textView41);
        recipeImageView = findViewById(R.id.imageView36);
        ingredient1 = findViewById(R.id.nonScrollListViewIn1);
        ingredient2 = findViewById(R.id.nonScrollListViewIn2);
        description = findViewById(R.id.nonScrollListViewDes);
        proteinIndicator = findViewById(R.id.proteinIndicatorRecipeTxt);
        carbIndicator = findViewById(R.id.carbsIndicatorRecipeTxt);
        energyIndicator = findViewById(R.id.energyIndicatorRecipeTxt);
        fatIndicator = findViewById(R.id.fatIndicatorRecipeTxt);
        cholIndicator = findViewById(R.id.cholestrolIndicatorRecipeTxt);
        fibreIndicator = findViewById(R.id.fibreIndicatorRecipeTxt);

        ingredient1.setDivider(null);
        ingredient2.setDivider(null);
        description.setDivider(null);
        description.setDividerHeight(0);
        ingredient2.setDividerHeight(0);
        ingredient1.setDividerHeight(0);

        recipeTextView.setText(deliverRecipe.recipeName);
        recipeImageView.setImageResource(deliverRecipe.recipeImage);
        Set<Ingredient> setIngredient = deliverRecipe.ingredients.keySet();
        List<Ingredient> ingredients = new ArrayList<>();
        List<String> ingredients1 = new ArrayList<>();
        List<String> ingredients2 = new ArrayList<>();
        if (setIngredient == null) {
                Log.d("Set null", "null set");
        } else {
            for (Ingredient value : setIngredient) {
                ingredients.add(value);
            }
        }
        Log.d("NumberOfIngredients", String.valueOf(ingredients.size()));
        if ((ingredients.size())%2 == 0){
            for (int in = 0; in < (ingredients.size() / 2); in++) {
                ingredients1.add(ingredients.get(in).getIngredientName());
            }
            for (int in2 = ((ingredients.size() / 2)); in2 < ingredients.size(); in2++) {
                ingredients2.add(ingredients.get(in2).getIngredientName());
            }
        }else {
            for (int in = 0; in <= (ingredients.size() / 2); in++) {
                ingredients1.add(ingredients.get(in).getIngredientName());
            }
            for (int in2 = ((ingredients.size() / 2) + 1); in2 < ingredients.size(); in2++) {
                ingredients2.add(ingredients.get(in2).getIngredientName());
            }
        }

        for (String ingredientSingle : ingredients1){
            Log.d("Ingredient1", ingredientSingle);
        }
        for (String ingredientSingle2 : ingredients2){
            Log.d("Ingredient2", ingredientSingle2);
        }
        BulletListAdapter bulletListAdapterIng1 = new BulletListAdapter(this, ingredients1, R.drawable.google, 16);
        BulletListAdapter bulletListAdapterIng2 = new BulletListAdapter(this, ingredients2, R.drawable.apple_logo, 16);
        ingredient1.setAdapter(bulletListAdapterIng1);
        ingredient2.setAdapter(bulletListAdapterIng2);
        bulletListAdapterIng1.notifyDataSetChanged();
        bulletListAdapterIng2.notifyDataSetChanged();

        BulletListAdapter bulletListAdapterDes = new BulletListAdapter(this, deliverRecipe.method, R.drawable.food_bowl, 18);
        description.setAdapter(bulletListAdapterDes);
        bulletListAdapterDes.notifyDataSetChanged();

        proteinIndicator.setText(deliverRecipe.nutrients.get(RecipesList.Nutrient.PROTEINS));
        carbIndicator.setText(deliverRecipe.nutrients.get(RecipesList.Nutrient.CARBS));
        fatIndicator.setText(deliverRecipe.nutrients.get(RecipesList.Nutrient.FATS));
        fibreIndicator.setText(deliverRecipe.nutrients.get(RecipesList.Nutrient.FIBRE));
        energyIndicator.setText(deliverRecipe.nutrients.get(RecipesList.Nutrient.ENERGY));
        cholIndicator.setText(deliverRecipe.nutrients.get(RecipesList.Nutrient.CHOLESTEROL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}