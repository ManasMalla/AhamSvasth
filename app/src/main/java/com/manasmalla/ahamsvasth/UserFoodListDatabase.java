package com.manasmalla.ahamsvasth;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserFoodListDatabase {

    public static void saveItem(Context context, Recipe recipe, char w){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        String id = new SimpleDateFormat("dd MM yyyy hh mm ss a EEEE").format(Calendar.getInstance().getTime());

        Set<String> ids = sharedPreferences.getStringSet("idsFood", null);

        if (ids == null) {
            ids = new HashSet<>();
        }
        ids.add(id);

        sharedPreferences.edit().putString("recipeEaten"+id,recipe.recipeName).commit();
        sharedPreferences.edit().putString("recipeEatenType"+id,String.valueOf(w)).commit();
        sharedPreferences.edit().putString("recipeEatenDate" +id, id).commit();
        sharedPreferences.edit().putStringSet("idsFood", ids).commit();
    }
    public static List<String> getItemsListFood(Context context, char w){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);

        Set<String> ids = sharedPreferences.getStringSet("idsFood", null);
        List<String> recipes = new ArrayList<>();
        if (ids != null) {
            for (String id : ids) {
                try {
                    Date idgot = new SimpleDateFormat("dd MM yyyy hh mm ss a EEEE").parse(id);
                    Date current = Calendar.getInstance().getTime();
                    if (idgot.getDate() == current.getDate() && idgot.getMonth() == current.getMonth() && idgot.getYear() == current.getYear()){
                        String charm = sharedPreferences.getString("recipeEatenType"+id, null);
                        if (w == 'b' && charm.matches("b")){
                            recipes.add(sharedPreferences.getString("recipeEaten" + id, null));
                        }else if (w == 'l'&& charm.matches("l")){
                            recipes.add(sharedPreferences.getString("recipeEaten" + id, null));
                        }else if (w == 's'&& charm.matches("s")){
                            recipes.add(sharedPreferences.getString("recipeEaten" + id, null));
                        }else if (w == 'd'&& charm.matches("d")){
                            recipes.add(sharedPreferences.getString("recipeEaten" + id, null));
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        }

        return recipes;
    }
}
