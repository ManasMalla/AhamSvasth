package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class FoodActivity extends AppCompatActivity {

    List<Recipe> recipeList;
    NonScrollListView listView;
    RecipeListAdapter recipeListAdapter;
    TabLayout tabLayout;
    int layoutHeight, layoutHeightL,layoutHeightS,layoutHeightD;
    int timeday = 0;
    ImageView editB, editS, editL, editD;
    RecipesList recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_food);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_food);
        bottomNavigationView.setSelectedItemId(R.id.foodNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeNavigationButton:
                        startActivity(new Intent(FoodActivity.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.yogaNavigationButton:
                        startActivity(new Intent(FoodActivity.this, YogaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.foodNavigationButton:
                        return true;
                    case R.id.journalNavigationButton:
                        startActivity(new Intent(FoodActivity.this, JournalActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.coronaNavigationButton:
                        startActivity(new Intent(FoodActivity.this, CoronaCautionActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return true;
            }
        });

        recipesList = new RecipesList();
        recipesList.setupRecipes();
        recipeList = recipesList.getRecipes(0);

        listView = findViewById(R.id.recipeListView);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        recipeListAdapter = new RecipeListAdapter(this, recipeList);
        listView.setAdapter(recipeListAdapter);
        recipeListAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), RecipieActivity.class).putExtra("recipe", i).putExtra("timeDay", timeday);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(FoodActivity.this, Pair.create(view.findViewById(R.id.recipeImageView_adapter),"recipeImageTransition"));
                    startActivity(intent, activityOptions.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });

        /* Get recipes time of the day value
        0 - Breakfast
        1 - Lunch
        2 - Snack
        3 - Dinner
         */
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String title = tab.getText().toString();
                switch (title) {
                    case "Breakfast":
                        timeday = 0;
                        recipeList = recipesList.getRecipes(0);
                        recipeListAdapter = new RecipeListAdapter(FoodActivity.this, recipeList);
                        listView.setAdapter(recipeListAdapter);
                        recipeListAdapter.notifyDataSetChanged();
                        break;
                    case "Lunch":
                        timeday = 1;
                        recipeList = recipesList.getRecipes(1);
                        recipeListAdapter = new RecipeListAdapter(FoodActivity.this, recipeList);
                        for (Recipe recipe: recipeList){
                            Log.d("Recipe Lunch", recipe.recipeName);
                        };
                        listView.setAdapter(recipeListAdapter);
                        recipeListAdapter.notifyDataSetChanged();
                        break;
                    case "Snack":
                        timeday = 2;
                        recipeList = recipesList.getRecipes(2);
                        recipeListAdapter = new RecipeListAdapter(FoodActivity.this, recipeList);
                        listView.setAdapter(recipeListAdapter);
                        recipeListAdapter.notifyDataSetChanged();
                        break;
                    case "Dinner":
                        timeday = 3;
                        recipeList = recipesList.getRecipes(3);
                        recipeListAdapter = new RecipeListAdapter(FoodActivity.this, recipeList);
                        listView.setAdapter(recipeListAdapter);
                        recipeListAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        editB = findViewById(R.id.imageView14);
        editL = findViewById(R.id.imageView15);
        editS = findViewById(R.id.imageView16);
        editD = findViewById(R.id.imageView17);

        Intent foodListIntent = new Intent(getApplicationContext(), ListOfFoodActivity.class);
        View.OnClickListener edit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == editB.getId()){
                    Log.d("Hello", "Breakfast");
                    foodListIntent.putExtra("w",'b');
                }else if (view.getId() == editL.getId()){
                    foodListIntent.putExtra("w",'l');
                    Log.d("Hello", "Lunch");
                }else if (view.getId() == editS.getId()){
                    foodListIntent.putExtra("w",'s');
                    Log.d("Hello", "Snack");
                }else if (view.getId() == editD.getId()){
                    foodListIntent.putExtra("w",'d');
                    Log.d("Hello", "Dinner");
                }

                startActivity(foodListIntent);
            }
        };

        editB.setOnClickListener(edit);
        editL.setOnClickListener(edit);
        editS.setOnClickListener(edit);
        editD.setOnClickListener(edit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        List<String> breakfast = UserFoodListDatabase.getItemsListFood(this, 'b');
        List<String> lunch = UserFoodListDatabase.getItemsListFood(this, 'l');
        List<String> snack = UserFoodListDatabase.getItemsListFood(this, 's');
        List<String> dinner = UserFoodListDatabase.getItemsListFood(this, 'd');
        List<Recipe> rBreakfast = new ArrayList<>();
        List<Recipe> rLunch = new ArrayList<>();
        List<Recipe> rSnack = new ArrayList<>();
        List<Recipe> rDinner = new ArrayList<>();
        List<Recipe> recipeList1 = new ArrayList<>();
        recipeList1.addAll(recipesList.getRecipes(0));
        recipeList1.addAll(recipesList.getRecipes(1));
        recipeList1.addAll(recipesList.getRecipes(2));
        recipeList1.addAll(recipesList.getRecipes(3));
        NutritionAPIJSONCreator apijsonCreator = new NutritionAPIJSONCreator();
        for (NutritionAPIJSONCreator.Food food : apijsonCreator.createAPIJSON()) {
            recipeList1.add(RecipesList.createRecipe(food.name, food.image, new Ingredient[]{}, new String[]{}, new String[]{}, new String[]{String.valueOf(food.proteins) + "g", String.valueOf(food.carbohydrates) + "g", String.valueOf(food.fat) + "g", String.valueOf(food.energy) + " cal", String.valueOf(food.fibre) + "g", String.valueOf(food.cholestrol) + "mg"}));
        }
        Log.d("breakfast", breakfast.toString());

        LinearLayout linearLayout = findViewById(R.id.brekfastcircleviewlayout);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                layoutHeight = linearLayout.getHeight();
                Log.i("LL", String.valueOf(layoutHeight));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(layoutHeight, layoutHeight);
                layoutParams.setMargins(8, 0, 8, 0);
                for (String br : breakfast){
                  for (Recipe recipe : recipeList1){
                      if (recipe.recipeName.matches(br)){
                          rBreakfast.add(recipe);
                      }
                  }
                }
                for (Recipe recipeB: rBreakfast){
                    CircleImageView circleImageView = new CircleImageView(FoodActivity.this);
                    circleImageView.setLayoutParams(layoutParams);
                    //circleImageView.setLayoutParams(new LinearLayout.LayoutParams(circleImageView.getHeight(), ViewGroup.LayoutParams.MATCH_PARENT));
                    circleImageView.setImageResource(recipeB.recipeImage);
                    linearLayout.addView(circleImageView);
                }
            }
        });


        LinearLayout linearLayoutL = findViewById(R.id.lunchcircleviewlayout);
        linearLayoutL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayoutL.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                layoutHeightL = linearLayoutL.getHeight();
                Log.i("LLL", String.valueOf(layoutHeightL));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(layoutHeightL, layoutHeightL);
                layoutParams.setMargins(8, 0, 8, 0);
                for (String lu : lunch){
                    for (Recipe recipe : recipeList1){
                        if (recipe.recipeName.matches(lu)){
                            rLunch.add(recipe);
                        }
                    }
                }
                for (Recipe recipeL: rLunch){
                    CircleImageView circleImageView = new CircleImageView(FoodActivity.this);
                    circleImageView.setLayoutParams(layoutParams);
                    //circleImageView.setLayoutParams(new LinearLayout.LayoutParams(circleImageView.getHeight(), ViewGroup.LayoutParams.MATCH_PARENT));
                    circleImageView.setImageResource(recipeL.recipeImage);
                    linearLayoutL.addView(circleImageView);
                }
            }
        });

        LinearLayout linearLayoutS = findViewById(R.id.snackcircleviewlayout);
        linearLayoutS.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayoutS.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                layoutHeightS = linearLayoutS.getHeight();
                Log.i("LLL", String.valueOf(layoutHeightS));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(layoutHeightS, layoutHeightS);
                layoutParams.setMargins(8, 0, 8, 0);
                for (String sn : snack){
                    for (Recipe recipe : recipeList1){
                        if (recipe.recipeName.matches(sn)){
                            rSnack.add(recipe);
                        }
                    }
                }
                for (Recipe recipeS: rSnack){
                    CircleImageView circleImageView = new CircleImageView(FoodActivity.this);
                    circleImageView.setLayoutParams(layoutParams);
                    //circleImageView.setLayoutParams(new LinearLayout.LayoutParams(circleImageView.getHeight(), ViewGroup.LayoutParams.MATCH_PARENT));
                    circleImageView.setImageResource(recipeS.recipeImage);
                    linearLayoutS.addView(circleImageView);
                }
            }
        });

        LinearLayout linearLayoutD = findViewById(R.id.dinnercircleviewlayout);
        linearLayoutD.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayoutD.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                layoutHeightD = linearLayoutD.getHeight();
                Log.i("LLL", String.valueOf(layoutHeightD));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(layoutHeightD, layoutHeightD);
                layoutParams.setMargins(8, 0, 8, 0);
                for (String di : dinner){
                    for (Recipe recipe : recipeList1){
                        if (recipe.recipeName.matches(di)){
                            rDinner.add(recipe);
                        }
                    }
                }
                for (Recipe recipeD: rDinner){
                    CircleImageView circleImageView = new CircleImageView(FoodActivity.this);
                    circleImageView.setLayoutParams(layoutParams);
                    //circleImageView.setLayoutParams(new LinearLayout.LayoutParams(circleImageView.getHeight(), ViewGroup.LayoutParams.MATCH_PARENT));
                    circleImageView.setImageResource(recipeD.recipeImage);
                    linearLayoutD.addView(circleImageView);
                }
            }
        });

        //END OF THIS METHOD
    }

}