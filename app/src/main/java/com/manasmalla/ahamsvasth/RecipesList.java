package com.manasmalla.ahamsvasth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipesList {
    public static Recipe ragiDosa, ragiRavaDosa, oatsIdli, chanaDhokla, multiGrainParatha;
    HashMap<String, String> ragiDosaNutrients, ragiRavaDosaNutrients, oatsIdliNutrients, chanaDhoklaNutrients, mutliGrainParthaNutrients;
    HashMap<Ingredient, String> ingredientsRagiDosa, ingredientsRagiRavaDosa, ingredientsOatsIdli, chanaDhoklaIngredients, multiGrainIngredients;
    List<String> ragiDosaMethod, ragiRavaDosaMethod, oatsIdliMethod, chanaDhoklaMethod, multigrainMethod;

    //Recipes List
    List<Recipe> recipeList;

    public void setupRecipes(){
        Ingredient ingredient = new Ingredient();
        ingredient.getIngredients();
        //Ragi Dosa
        ragiDosa = new Recipe();
        ragiDosaNutrients = new HashMap<>();
        ragiDosaNutrients.put("Proteins", "3.2g");
        ragiDosaNutrients.put("Carbohydrates", "25g");
        ragiDosaNutrients.put("Energy", "150 cal");
        ragiDosaNutrients.put("Fats", "6g");
        ragiDosaNutrients.put("Fibre", "3g");
        ingredientsRagiDosa = new HashMap<>();
        ingredientsRagiDosa.put(Ingredient.uradDal, "0.5 cup");
        ingredientsRagiDosa.put(Ingredient.ragiFlour, "1 cup");
        ingredientsRagiDosa.put(Ingredient.riceFlour, "0.25 cup");
        ingredientsRagiDosa.put(Ingredient.curd, "0.5 cup");
        ingredientsRagiDosa.put(Ingredient.salt, "To Taste");
        ingredientsRagiDosa.put(Ingredient.oil, "as Required");
        ragiDosaMethod = new ArrayList<>();
        ragiDosaMethod.add("Take the ragi flour in a bowl, in this add rice flour, urad dal paste, curd, salt and add required water make it like a dosa batter");
        ragiDosaMethod.add("And whisk it nicely, the batter should be lump free and smooth");
        ragiDosaMethod.add("Kepp the batter aside for 1 hour!");
        ragiDosaMethod.add("Heat a dosa tawa and pour the batter with the help of ladle and spread it slowly");
        ragiDosaMethod.add("Pour some oil and cook it for 2 minutes with the lid on");
        ragiDosaMethod.add("After 2 minutes add the mixture on the top of the dosa and fold it nicely");
        ragiDosa.setMethod(ragiDosaMethod);
        ragiDosa.setIngredients(ingredientsRagiDosa);
        ragiDosa.setNutrients(ragiDosaNutrients);
        ragiDosa.setRecipeName("Ragi Dosa");
        ragiDosa.setRecipeImage(R.drawable.ragi_dosa);

        //Ragi Rava Dosa
        ragiRavaDosa = new Recipe();
        ingredientsRagiRavaDosa = new HashMap<>();
        ingredientsRagiRavaDosa.put(Ingredient.ragiFlour, "1 cup");
        ingredientsRagiRavaDosa.put(Ingredient.suji, "1 cup");
        ingredientsRagiRavaDosa.put(Ingredient.riceFlour, "1/2 cup");
        ingredientsRagiRavaDosa.put(Ingredient.curd, "1/2 cup");
        ingredientsRagiRavaDosa.put(Ingredient.ginger, "1 inch, finely chopped");
        ingredientsRagiRavaDosa.put(Ingredient.greenChilli, "1, finely chopped");
        ingredientsRagiRavaDosa.put(Ingredient.curryLeaf, "Few, finely chopped");
        ingredientsRagiRavaDosa.put(Ingredient.coriender, "2tsp, finely chopped");
        ingredientsRagiRavaDosa.put(Ingredient.onion, "1 medium, finely chopped");
        ingredientsRagiRavaDosa.put(Ingredient.jeera, "1tsp");
        ingredientsRagiRavaDosa.put(Ingredient.pepper, "1/2 tsp");
        ingredientsRagiRavaDosa.put(Ingredient.salt, "1 tsp");
        ingredientsRagiRavaDosa.put(Ingredient.water, "3/4 cup");
        ragiRavaDosaMethod = new ArrayList<>();
        ragiRavaDosaMethod.add("Firstly, in a large mixing bowl take 1 cup ragi flour, 1 cup rava, ½ cup rice flour");
        ragiRavaDosaMethod.add("Add ½ cup curd, 1 inch ginger, 1 green chilli, few curry leaves, 2 tbsp coriander, 1 onion, 1 tsp cumin, ½ tsp pepper and 1 tsp salt");
        ragiRavaDosaMethod.add("add 1-2 cup water and mix well to form a lump free batter");
        ragiRavaDosaMethod.add("Rest the batter for 15-20 minutes");
        ragiRavaDosaMethod.add("Add 1½ cups of water or as required and prepare a flowing consistency batter");
        ragiRavaDosaMethod.add("Now carefully pour the dosa batter over hot tawa");
        ragiRavaDosaMethod.add("Sprinkle ½ or 1 tsp of oil from the top");
        ragiRavaDosaMethod.add("Once the dosa roasts to golden brown, flip over and cook");
        ragiRavaDosaMethod.add("Finally, fold the dosa and serve instant ragi dosa immediately with mint chutney");
        ragiRavaDosaNutrients = new HashMap<>();
        ragiRavaDosaNutrients.put("Proteins", "4g");
        ragiRavaDosaNutrients.put("Carbohydrates", "50g");
        ragiRavaDosaNutrients.put("Energy", "436 cal");
        ragiRavaDosaNutrients.put("Fats", "6g");
        ragiRavaDosaNutrients.put("Fibre", "4g");
        ragiRavaDosa.setRecipeName("Ragi Rava Dosa");
        ragiRavaDosa.setRecipeImage(R.drawable.ragi_dosa);
        ragiRavaDosa.setIngredients(ingredientsRagiRavaDosa);
        ragiRavaDosa.setNutrients(ragiRavaDosaNutrients);
        ragiRavaDosa.setMethod(ragiRavaDosaMethod);

        //Oats Idli
        oatsIdli = new Recipe();
        ingredientsOatsIdli = new HashMap<>();
        oatsIdliNutrients = new HashMap<>();
        oatsIdliMethod = new ArrayList<>();
        ingredientsOatsIdli.put(Ingredient.oats, "1/2 cup");
        ingredientsOatsIdli.put(Ingredient.suji, "1/2 cup");
        ingredientsOatsIdli.put(Ingredient.curd, "1/2 cup");
        ingredientsOatsIdli.put(Ingredient.ghee, "2tsp");
        ingredientsOatsIdli.put(Ingredient.mustardSeeds, "1/2 tsp");
        ingredientsOatsIdli.put(Ingredient.jeera, "1 tsp");
        ingredientsOatsIdli.put(Ingredient.hing, "1/2 tsp");
        ingredientsOatsIdli.put(Ingredient.ginger, "grated, 1/2 tsp");
        ingredientsOatsIdli.put(Ingredient.cashew, "chopped, 2tsp");
        ingredientsOatsIdli.put(Ingredient.greenChilli, "chopped, 2tsp");
        ingredientsOatsIdli.put(Ingredient.coriender, "chopped, 1tsp");
        ingredientsOatsIdli.put(Ingredient.salt, "To Taste");
        oatsIdliMethod.add("Combine the oats flour, semolina, curds and ¾ cup of water in a deep bowl and mix well. Cover with a lid and keep aside for 30 minutes");
        oatsIdliMethod.add("Heat the ghee in a small non-stick pan and add the mustard seeds");
        oatsIdliMethod.add("When the seeds crackle, add the cumin seeds and asafoetida and sauté on a medium flame for a few seconds");
        oatsIdliMethod.add("Add this tempering and the remaining ingredients to the prepared oats-semolina batter and mix well");
        oatsIdliMethod.add("Just before steaming and pour 2 tsp of water over it.\n" +
                "When the bubbles form, mix gently");
        oatsIdliMethod.add("Put a little batter into each of the greased idli mould and steam in a steamer for 7 to 8 minutes or till the idlis are cooked");
        oatsIdliMethod.add("Cool slightly, demould and serve immediately with sambhar and coconut chutney");
        oatsIdliNutrients.put("Proteins", "1.5g");
        oatsIdliNutrients.put("Carbohydrates", "5.5g");
        oatsIdliNutrients.put("Energy", "48 cal");
        oatsIdliNutrients.put("Fats", "2.1g");
        oatsIdliNutrients.put("Fibre", "0.3g");
        oatsIdli.setRecipeName("Oats Idli");
        oatsIdli.setRecipeImage(R.drawable.active_boy);
        oatsIdli.setMethod(oatsIdliMethod);
        oatsIdli.setNutrients(oatsIdliNutrients);
        oatsIdli.setIngredients(ingredientsOatsIdli);

        //Dokhla
        chanaDhokla = new Recipe();
        chanaDhokla.setRecipeName("Chana Dhokla");
        chanaDhokla.setRecipeImage(R.drawable.active_boy);

        chanaDhoklaMethod = new ArrayList<>();
        chanaDhoklaIngredients = new HashMap<>();
        chanaDhoklaNutrients = new HashMap<>();

        chanaDhoklaIngredients.put(Ingredient.chanaDal, "1 cup");
        chanaDhoklaIngredients.put(Ingredient.rice, "1/4 cup");
        chanaDhoklaIngredients.put(Ingredient.water, "For Soaking");
        chanaDhoklaIngredients.put(Ingredient.ginger, "1 inch");
        chanaDhoklaIngredients.put(Ingredient.greenChilli, "4");
        chanaDhoklaIngredients.put(Ingredient.curd, "1/4 cup");
        chanaDhoklaIngredients.put(Ingredient.turmeric, "1/4 tsp");
        chanaDhoklaIngredients.put(Ingredient.salt, "5/4 tsp");
        chanaDhoklaIngredients.put(Ingredient.water, "3/4 cup");
        chanaDhoklaIngredients.put(Ingredient.oil, "2tbsp");
        chanaDhoklaIngredients.put(Ingredient.mustardSeeds, "1tsp");
        chanaDhoklaIngredients.put(Ingredient.jeera, "1tsp");
        chanaDhoklaIngredients.put(Ingredient.hing, "1 pinch");
        chanaDhoklaIngredients.put(Ingredient.seasme, "1 tsp");
        chanaDhoklaIngredients.put(Ingredient.curryLeaf, "Few");
        chanaDhoklaIngredients.put(Ingredient.lemonJuice, "1tsp");
        chanaDhoklaIngredients.put(Ingredient.coconut, "Grated, 1tsp");
        chanaDhoklaIngredients.put(Ingredient.coriender, "Chopped, 2tsp");
        chanaDhoklaMethod.add("Firstly, in a large bowl take 1 cup chana dal and ¼ cup rice");
        chanaDhoklaMethod.add("Soak in enough water for 4 hours");
        chanaDhoklaMethod.add("Drain off the water and transfer to the mixi");
        chanaDhoklaMethod.add("Add 1 inch ginger, 2 chilli and blend to a coarse paste");
        chanaDhoklaMethod.add("Transfer the chana dal - rice batter to a large bowl");
        chanaDhoklaMethod.add("Further add ¼ cup curd, ¼ tsp turmeric, ½ tsp salt and ½ cup water");
        chanaDhoklaMethod.add("Mix well forming a smooth consistency batter");
        chanaDhoklaMethod.add("Cover and ferment for 8 hours or until the batter is well fermented");
        chanaDhoklaMethod.add("Transfer the batter to a greased pan and steam for 25 minutes on medium flame");
        chanaDhoklaMethod.add("Cool completely and cut dhokla into pieces");
        chanaDhoklaMethod.add("In a pan heat 2 tbsp oil and splutter 1 tsp mustard, 1 tsp cumin, 1 tsp sesame, pinch hing, 2 chilli and few curry leaves.");
        chanaDhoklaMethod.add("Add ¼ cup water, 1 tsp sugar, ¼ tsp salt and 1 tsp lemon juice");
        chanaDhoklaMethod.add("Mix and get the water to a boil");
        chanaDhoklaMethod.add("Pour the tempering over dhokla");
        chanaDhoklaMethod.add("Garnish with 2 tbsp coconut and 2 tbsp coriander");
        chanaDhoklaMethod.add("Finally, enjoy dal dhokla with green chutney");

        //TODO Update nutrition
        chanaDhoklaNutrients.put("Proteins", "1.5g");
        chanaDhoklaNutrients.put("Carbohydrates", "5.5g");
        chanaDhoklaNutrients.put("Energy", "48 cal");
        chanaDhoklaNutrients.put("Fats", "2.1g");
        chanaDhoklaNutrients.put("Fibre", "0.3g");
        chanaDhokla.setMethod(chanaDhoklaMethod);
        chanaDhokla.setIngredients(chanaDhoklaIngredients);
        chanaDhokla.setNutrients(chanaDhoklaNutrients);

        //Multigrain Paratha
        //TODO Finish

    }

    public List<Recipe> getRecipes(){
        recipeList = new ArrayList<>();
        recipeList.add(ragiDosa);
        recipeList.add(ragiRavaDosa);
        recipeList.add(chanaDhokla);
        return this.recipeList;
    }

}
