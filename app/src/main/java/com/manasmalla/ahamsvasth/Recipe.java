package com.manasmalla.ahamsvasth;

import java.util.HashMap;
import java.util.List;

public class Recipe {
    String recipeName;
    int recipeImage;
    HashMap<String, String> nutrients;
    List<String> method;
    HashMap<Ingredient, String> ingredients;

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeImage(int recipeImage) {
        this.recipeImage = recipeImage;
    }

    public void setNutrients(HashMap<String, String> nutrients) {
        this.nutrients = nutrients;
    }

    public void setMethod(List<String> method) {
        this.method = method;
    }

    public void setIngredients(HashMap<Ingredient, String> ingredients) {
        this.ingredients = ingredients;
    }
}
