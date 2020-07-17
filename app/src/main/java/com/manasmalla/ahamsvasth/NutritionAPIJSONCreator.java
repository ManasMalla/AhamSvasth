package com.manasmalla.ahamsvasth;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class NutritionAPIJSONCreator {

    public class Food {
        String name;
        double energy, proteins, carbohydrates, fat, fibre, cholestrol;
        String time;
        int image;

        public void put(String index, double d) {
            if (index.matches("energy")) {
                this.energy = d;
            } else if (index.matches("proteins")) {
                this.proteins = d;
            } else if (index.matches("carbohydrates")) {
                this.carbohydrates = d;
            } else if (index.matches("fat")) {
                this.fat = d;
            } else if (index.matches("fibre")) {
                this.fibre = d;
            } else if (index.matches("cholesterol")) {
                this.cholestrol = d;
            }
        }

        public void putI(int draw) {
            this.image = draw;
        }

        public void putS(String index, String s) {
            this.name = s;
        }

        public void putC(String index, String w) {
            this.time = w;
        }
    }

    /*JSONObject nutrition = new JSONObject();
    JSONArray bArray = new JSONArray(), rArray = new JSONArray();*/
    public List<Food> createAPIJSON() {
        Food[] array = new Food[]{
                createFood("b", R.drawable.oatsidli, "Idli", 33, 1, 7.2, 0.3, 0.1, 0),
                createFood("b", R.drawable.oatsidli, "Dosa", 133, 2.7, 18.8, 1.1, 5.2, 0),
                createFood("b", R.drawable.oatsidli, "Chapati", 150, 5, 29, 4, 2.5, 0),
                createFood("b", R.drawable.oatsidli, "Aloo Paratha", 222, 3.4, 22.7, 3.4, 4.1, 0),
                createFood("b", R.drawable.oatsidli, "Mixed Veg Paratha", 153, 4.1, 25.6, 3.4, 4.1, 0),
                createFood("b", R.drawable.oatsidli, "Dhokla", 81, 3.6, 12.1, 2.6, 2, 0),
                createFood("b", R.drawable.oatsidli, "Multigrain Dosa", 318, 11, 57, 7, 6, 0),
                createFood("b", R.drawable.oatsidli, "Upma", 192, 4, 30.7, 0.3, 5.8, 0),
                createFood("b", R.drawable.oatsidli, "Oats Upma", 207, 7.4, 30.6, 5.4, 6.4, 0),
                createFood("b", R.drawable.oatsidli, "Pesarattu", 145, 7.6, 20.5, 5.3, 3.6, 0),
                createFood("b", R.drawable.oatsidli, "Poori", 101, 1.3, 7.5, 0.2, 7.4, 0),
                createFood("b", R.drawable.oatsidli, "Uttapam", 92, 17.9, 14.2, 1.5, 2.8, 0),
                createFood("l", R.drawable.oatsidli, "Rice", 130, 2.6, 23, 1.8, 0.9, 0),
                createFood("l", R.drawable.oatsidli, "Veg. Fried Rice", 279, 8, 30, 2.1, 14, 0.088),
                createFood("l", R.drawable.oatsidli, "Chicken Biryani", 250, 23, 40, 3, 12, 0.002),
                createFood("l", R.drawable.oatsidli, "Veg. Biryani", 241, 4.8, 13.9, 3.3, 17.9, 0),
                createFood("l", R.drawable.oatsidli, "Mutton Biryani", 321, 19, 70, 1.5, 19, 0.1),
                createFood("l", R.drawable.oatsidli, "Pearl Millet", 268, 10, 60, 1.3, 8.5, 0),
                createFood("l", R.drawable.oatsidli, "Foxtail Millet", 270, 12.3, 55, 8.3, 0, 0),
                createFood("l", R.drawable.oatsidli, "Barnyard Millet", 300, 11.2, 66.5, 10.1, 0, 0),
                createFood("l", R.drawable.oatsidli, "Little Millet", 280, 7.1, 60, 7.6, 0, 0),
                createFood("l", R.drawable.oatsidli, "Brinjal Curry", 138, 2.3, 7.05, 10.9, 3.6, 0),
                createFood("l", R.drawable.oatsidli, "Dal Fry", 118, 6, 18, 1, 0, 0),
                createFood("l", R.drawable.oatsidli, "Chicken Curry", 123, 18.3, 5, 3.3, 2.1, 0),
                createFood("l", R.drawable.oatsidli, "Fish Curry", 163, 15.8, 3.5, 9.8, 1.2, 0),
                createFood("l", R.drawable.oatsidli, "Mutton Curry", 246, 12.3, 3.4, 20.2, 1.29, 0),
                createFood("l", R.drawable.oatsidli, "Chicken Friedrice", 280, 9.8, 4, 9, 1, 0),
                createFood("l", R.drawable.oatsidli, "Panner Friedrice", 488,11.5,57,23,4,0),
                createFood("l", R.drawable.oatsidli, "Egg Friedrice", 241, 6.8, 13.9, 3.3, 17.9, 0),
                createFood("l", R.drawable.oatsidli, "Fish Friedrice", 241, 6.5, 13.9, 3.3, 17.9, 0),
                createFood("l", R.drawable.oatsidli, "Fish Biryani", 241, 6, 13.9, 3.3, 17.9, 0),
                createFood("l", R.drawable.oatsidli, "Manchuria. Friedrice", 290, 9, 32, 3, 15, 0.09),
                createFood("s", R.drawable.oatsidli, "Icecream", 258, 6.22, 28, 13, 0.2, 0),
                createFood("s", R.drawable.oatsidli, "Raisins", 217, 2.2, 57, 0.3, 2.7, 0),
                createFood("s", R.drawable.oatsidli, "Apricots", 157, 2.2, 41, 0.3, 4.7, 0),
                createFood("s", R.drawable.oatsidli, "Fig", 186, 2.5, 48, 0.7, 7.3, 0),
                createFood("s", R.drawable.oatsidli, "Almonds", 529, 20, 20, 45, 11, 0),
                createFood("s", R.drawable.oatsidli, "Cashew", 553, 18, 30, 44, 3.3, 0),
                createFood("s", R.drawable.oatsidli, "Pista", 562, 20, 28, 45, 10, 0),
                createFood("s", R.drawable.oatsidli, "Peanuts", 567, 26, 16, 49, 9, 0),
                createFood("s", R.drawable.oatsidli, "Walnut", 654, 15, 14, 65, 7, 0),
                createFood("s", R.drawable.oatsidli, "Fruit", 52,0.3,14,0.2,2.4,0),
                createFood("s", R.drawable.oatsidli, "Samosa", 160, 3, 25, 1, 7, 0),
                createFood("s", R.drawable.oatsidli, "Bajji", 421, 5, 17.1, 14.8, 3.2, 0),
                createFood("s", R.drawable.oatsidli, "Pizza", 420, 52, 14, 14, 8, 80),
                createFood("s", R.drawable.oatsidli, "Burger", 240, 19, 9, 8, 3, 75),
                createFood("s", R.drawable.oatsidli, "Pav Bajji", 434, 14, 82, 10.6, 1.6, 0)
        };

        /*try {
            nutrition.put("Breakfast", bArray);
            nutrition.put("Rices", rArray);
            Log.i("API", nutrition.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

         */
        return Arrays.asList(array);
    }

    public Food createFood(String f, int drawable, String name, double energy, double proteins, double carbohydrates, double fat, double fibre, double cholestrol) {

        Food foodItem = new Food();
        foodItem.putS("name", name);
        foodItem.putI(drawable);
        foodItem.put("energy", energy);
        foodItem.put("proteins", proteins);
        foodItem.put("carbohydrates", carbohydrates);
        foodItem.put("fat", fat);
        foodItem.put("fibre", fibre);
        foodItem.put("cholesterol", cholestrol);
        if (f.matches("b")) {
            foodItem.putC("time", f);
        } else if (f.matches("r")) {
            foodItem.putC("time", f);
        }
        return foodItem;
    }
}
