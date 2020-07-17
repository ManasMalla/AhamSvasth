package com.manasmalla.ahamsvasth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

public class ListOfFoodActivity extends AppCompatActivity {

    char whatFood;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_food);

        Intent intent = getIntent();
        whatFood = intent.getCharExtra("w", 'a');

        NutritionAPIJSONCreator apijsonCreator = new NutritionAPIJSONCreator();
        List<NutritionAPIJSONCreator.Food> foodArray = apijsonCreator.createAPIJSON();
        List<Recipe> recipes = new ArrayList<>();
        for (NutritionAPIJSONCreator.Food food : foodArray) {
            recipes.add(RecipesList.createRecipe(food.name, food.image, new Ingredient[]{}, new String[]{}, new String[]{}, new String[]{String.valueOf(food.proteins) + "g", String.valueOf(food.carbohydrates) + "g", String.valueOf(food.fat) + "g", String.valueOf(food.energy) + " cal", String.valueOf(food.fibre) + "g", String.valueOf(food.cholestrol) + "mg"}));
        }
        RecipesList recipesList = new RecipesList();
        recipesList.setupRecipes();
        for (Recipe recipe : recipesList.getRecipes(0)) {
            recipes.add(recipe);
        }
        for (Recipe recipe : recipesList.getRecipes(1)) {
            recipes.add(recipe);
        }
        for (Recipe recipe : recipesList.getRecipes(2)) {
            recipes.add(recipe);
        }
        for (Recipe recipe : recipesList.getRecipes(3)) {
            recipes.add(recipe);
        }
        listView = findViewById(R.id.foodListListView);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(this, recipes);
        listView.setAdapter(recipeListAdapter);
        recipeListAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(), FoodActivity.class);
                if (whatFood != 'a') {
                    UserFoodListDatabase.saveItem(getApplicationContext(), recipes.get(i), whatFood);
                }else{
                    Toast.makeText(ListOfFoodActivity.this, "Couldn't add the recipe!", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent1);
            }
        });

    }
}