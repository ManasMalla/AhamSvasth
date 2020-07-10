package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

import java.util.HashMap;
import java.util.List;

import static com.manasmalla.ahamsvasth.RecipesList.ragiDosa;

public class FoodActivity extends AppCompatActivity {

    List<Recipe> recipeList;
    NonScrollListView listView;
    RecipeListAdapter recipeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_food);
        bottomNavigationView.setSelectedItemId(R.id.foodNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNavigationButton:
                        startActivity(new Intent(FoodActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.yogaNavigationButton:
                        startActivity(new Intent(FoodActivity.this, YogaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.foodNavigationButton:
                        return true;
                    case R.id.journalNavigationButton:
                        startActivity(new Intent(FoodActivity.this, JournalActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.infoNavigationButton:
                        startActivity(new Intent(FoodActivity.this, AboutMeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });
        RecipesList recipesList = new RecipesList();
        recipesList.setupRecipes();
        recipeList = recipesList.getRecipes();

        listView = findViewById(R.id.recipeListView);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        recipeListAdapter = new RecipeListAdapter(this, recipeList);
        listView.setAdapter(recipeListAdapter);
        recipeListAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(), RecipieActivity.class).putExtra("recipe", i));
            }
        });

    }

}