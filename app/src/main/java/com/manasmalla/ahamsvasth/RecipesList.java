package com.manasmalla.ahamsvasth;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.manasmalla.ahamsvasth.Ingredient.*;
import static com.manasmalla.ahamsvasth.RecipesList.Nutrient.*;


public class RecipesList {
    private static Recipe ragiDosa, ragiRavaDosa, oatsIdli, chanaDhokla, multiGrainParatha;
    private static Recipe lentilsKichidi, amlaPulihora, palakKichidi, sambar, bismillaibath;
    private static Recipe griledVegetableSalad, powerPackedSalad, elachiGranolaBar, bananaOatBread, threeBeanChat, lentilChaat;
    private static Recipe rajmaCurry, palakpaneer, roti, mixedVegCurry, brownRice, ladyfingerCurry;
    HashMap<String, String> ragiDosaNutrients, ragiRavaDosaNutrients, oatsIdliNutrients, chanaDhoklaNutrients;
    HashMap<Ingredient, String> ingredientsRagiDosa, ingredientsRagiRavaDosa, ingredientsOatsIdli, chanaDhoklaIngredients;
    List<String> ragiDosaMethod, ragiRavaDosaMethod, oatsIdliMethod, chanaDhoklaMethod;

    //Recipes List
    List<Recipe> recipeList;

    public void setupRecipes() {
        Ingredient ingredient = new Ingredient();
        ingredient.getIngredients();
        //Ragi Dosa
        ragiDosa = new Recipe();
        ragiDosaNutrients = new HashMap<>();
        ragiDosaNutrients.put(PROTEINS, "3.2g");
        ragiDosaNutrients.put(CARBS, "25g");
        ragiDosaNutrients.put(ENERGY, "150 cal");
        ragiDosaNutrients.put(FATS, "6g");
        ragiDosaNutrients.put(FIBRE, "3g");
        ragiDosaNutrients.put(CHOLESTEROL, "0mg");
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
        ingredientsRagiRavaDosa.put(onion, "1 medium, finely chopped");
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
        ragiRavaDosaNutrients.put(PROTEINS, "4g");
        ragiRavaDosaNutrients.put(CARBS, "50g");
        ragiRavaDosaNutrients.put(ENERGY, "436 cal");
        ragiRavaDosaNutrients.put(FATS, "6g");
        ragiRavaDosaNutrients.put(FIBRE, "4g");
        ragiRavaDosaNutrients.put(CHOLESTEROL, "0mg");
        ragiRavaDosa.setRecipeName("Ragi Rava Dosa");
        ragiRavaDosa.setRecipeImage(R.drawable.ragirava);
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
        oatsIdliNutrients.put(PROTEINS, "1.5g");
        oatsIdliNutrients.put(CARBS, "5.5g");
        oatsIdliNutrients.put(ENERGY, "48 cal");
        oatsIdliNutrients.put(FATS, "2.1g");
        oatsIdliNutrients.put(FIBRE, "0.3g");
        oatsIdliNutrients.put(CHOLESTEROL, "0mg");
        oatsIdli.setRecipeName("Oats Idli");
        oatsIdli.setRecipeImage(R.drawable.oatsidli);
        oatsIdli.setMethod(oatsIdliMethod);
        oatsIdli.setNutrients(oatsIdliNutrients);
        oatsIdli.setIngredients(ingredientsOatsIdli);

        //Dokhla
        chanaDhokla = new Recipe();
        chanaDhokla.setRecipeName("Chana Dhokla");
        chanaDhokla.setRecipeImage(R.drawable.chanadokla);

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
        chanaDhoklaNutrients.put(PROTEINS, "1.8g");
        chanaDhoklaNutrients.put(CARBS, "4.7g");
        chanaDhoklaNutrients.put(ENERGY, "37 cal");
        chanaDhoklaNutrients.put(FATS, "1.2g");
        chanaDhoklaNutrients.put(FIBRE, "1.1g");
        chanaDhoklaNutrients.put(CHOLESTEROL, "0mg");
        chanaDhokla.setMethod(chanaDhoklaMethod);
        chanaDhokla.setIngredients(chanaDhoklaIngredients);
        chanaDhokla.setNutrients(chanaDhoklaNutrients);


        //Multigrain Paratha
        multiGrainParatha = createRecipe("Multigrain Paratha",
                R.drawable.multigrainaparatha,
                new Ingredient[]{multigrainFlour, salt, oil, water, vegetables, gingergarlicPaste, greenChilli, garam_masala, jeeraPowder, aamchur, ajwain, hing, paneer, coriender}, new String[]{"2½ cup", "2tsp", "5tsp", "to Knead", "as per choice", "1/2 tsp", "2, finely chopped", "1/2 tsp", "1/2 tsp", "1/2 tsp", "1/4 tsp", "pich", "1 cup", "2tsp"},
                new String[]{"Firstly, pinch a ball sized multigrain flour dough and dust with some multigrain flour.", "Further, roll it in a circle of about 5 to 5.5 inches in diameter.",
                        "Place a ball sized prepared mix veg stuffing in the centre.", "Take the edge and start pleating bringing to the centre.",
                        "Also join the pleats together and secure tight pinching off excess dough.", "Sprinkle some multigrain flour and roll slightly thick.", "On a hot tawa place the rolled paratha and roast both sides with oil.",
                        "Finally, serve hot Multigrain Paratha with sauce, raita or pickle!"},
                new String[]{"4.1g", "20.6g", "3.5g", "150 cal", "4.5g", "0mg"}
        );

        //Lunch
        lentilsKichidi = createRecipe("Mixed Lentils & Vegetables Khichdi",
                R.drawable.lentils_kichidi,
                new Ingredient[]{ghee, jeera, ginger, vegetables, tomato, turmeric, redChilliPowder, salt, rice, lentils, water, coriender,}, new String[]{"2 tsp", "1 tsp", "1 tsp", "as per choice", "1, diced", "1/2 tsp", "1.5 tsp", "2tsp", "1 cup", "1 cup", "6 cups", "1/4 cup"},
                new String[]{"Turn the Instant Pot to Saute Mode and heat ghee. Add cumin seeds and ginger.",
                        "Cook for a 30 seconds. Add all the vegetables (carrots, beans, peas, tomato, potato, cauliflower, cabbage and spinach). Mix well.",
                        "Add turmeric, red chili powder and salt. Mix well.",
                        "Add rice and mixed lentils.", "Add 6 cups of water. Give a quick stir and close the Instant Pot lid with pressure valve to sealing.",
                        " Press the rice button (12 mins) followed by natural pressure release.", "Open the Instant Pot and  garnish with cilantro.", "Serve hot with roasted papad and pickle."},
                new String[]{"4g", "35g", "5g", "205 cal", "2g", "12mg"});
        palakKichidi = createRecipe("Palak Khichidi",
                R.drawable.palakkichidi,
                new Ingredient[]{spinach, rice, moongdal, turmeric, salt, ghee, gingergarlicPaste, onion, tomato, redChilliPowder, garam_masala, cinamon, cloves, elachi, jeera, curryLeaf, redChilli, garlic, greenChilli},
                new String[]{"2cups", "1/2 cup", "1/4 cup", "1/2 tsp", "to taste", "3 tsp", "1tsp", "1/4 cup", "1/2 cup", "1/2 tsp", "1/2 tsp", "1 small stick", "2", "1", "1 tsp", "6", "1", "1 tsp, finely chopped", "1 tsp, finely chopped"},
                new String[]{"To make Palak Khichdi, combine the rice and dal in a deep bowl and soak it in enough water for 30 minutes. Drain well. Keep aside.",
                        "Drain well, combine the rice, dal, turmeric powder, salt and 1½ cups of water in a pressure cooker, mix well and pressure cook for 3 whistles.",
                        "Combine the spinach and 2 tbsp of water in a mixer and blend till smooth. Keep aside.",
                        "Allow the steam to escape before opening the lid. Keep aside.",
                        "Heat 2 tbsp of ghee in a deep non-stick pan, add the garlic and ginger paste and sauté on a medium flame for a few seconds.",
                        "Add the onions and tomatoes and sauté on a medium flame for 3 minutes, while stirring occasionally.",
                        "Add the spinach purée, chilli powder and garam masala, mix well and cook on a medium flame for 3 minutes, while stirring occasionally.",
                        "Add the cooked dal-rice mixture, 1½ cups of water and salt and, mix well and cook on a medium flame for 2 to 3 minutes, while stirring occasionally. Keep aside.",
                        "Heat the remaining 1 tbsp of ghee in a small non-stick pan, add the cinnamon, cloves, cardamom, cumin seeds , curry leaves, kashmiri dry red chillies and sauté on a medium flame for 1 minute.",
                        "Add the garlic and green chillies and sauté on a medium flame for 30 seconds.",
                        "Pour the tempering over the palak khichdi and mix well.",
                        "Serve the palak khichdi hot with fresh curds and papad.",},
                new String[]{"4.7g", "25.1g", "10.3g", "212 cal", "2.9g", "0mg"});
        bismillaibath = createRecipe("Bisi Bele Bhath",
                R.drawable.bismillvath,
                new Ingredient[]{cashew, elachi, chanaDal, cinamon, cloves, cloves, coriender, jeera, coconut, redChilli, curryLeaf, gingergarlicPaste, vegetables, mustardSeeds, oil, rice, salt, tamrindJuice, toordal, turmeric, uradDal},
                new String[]{"2tsp", "2", "1tsp", "1 inch", "2", "1 tsp", "1 tsp", "1 tsp", "4", "few", "optional", "2 cups", "1/2 tsp", "1 tsp", "1 cup", "1", "20g", "1/2 cup", "1/4 tsp", "1 tsp"},
                new String[]{"Wash the toor dal and add 1 1/2 cups of water to it. and keep aside.or cook along with rice as shown.", "Roast all spices, mix with coconut, turmaric powder, ginger and garlic (optional), grind it to a powder form. keep this masala aside.",
                        "Heat the oil in pan and add the cumin and mustard seeds and curry leaves, after spluttering add the onions and fry till soft.", "Add all the chopped vegetables and softly fry it.",
                        "Add the rice, toor dal to the fried vegetables and add the masala /tomatoes(optional)along with tamarind juice and salt, cool it till the ingredient are done.",
                        "Garnish the dish with coriander leaves and serve it hot."},
                new String[]{"10g", "57.6g", "12.3g", "380 cal", "5.8g", "0mg"});
        sambar = createRecipe("Sambhar",
                R.drawable.sambar,
                new Ingredient[]{redChilliPowder, fenugreekSeeds, hing, jeera, mustardSeeds, redChilli, turmeric, oil, salt, onion, curryLeaf, vegetables, garlic, tamrindJuice, tomato, jaggery, sambarPowder, toordal},
                new String[]{"1 tsp", "1/4 tsp", "1 pinch", "1 tsp", "1 tsp", "3", "1/4 tsp", "2 tsp", "to taste", "1", "1 spring", "of your choice", "2 cloves", "1 cup", "3", "1 tsp", "2 tsp", "1 cup"},
                new String[]{"Boil the toor dal in a pressure cooker until dal is soft.",
                        "Heat oil in a pan, add red chillies, mustard seeds, cumin seeds, fenugreek seeds, turmeric, red chilli powder, Hing, garlic pieces, and add drumsticks, eggplant, bottle gourd, carrot, curry leaves, onions, onion shallots, saute this.",
                        "Add tomatoes, tamarind juice, sambhar powder, jaggery, salt, add water put the lid on cook until the vegetables are tender.",
                        "Then add boiled and mashed dal and place the lid and cook for 15 minutes in a slow flame.",
                        "Add coriander leaves, switch off the flame, and serve the hot tasty sambar!"},
                new String[]{"5.2g", "18.4g", "5.4g", "143 cal", "2.8g", "0mg"});

        amlaPulihora = createRecipe("Amla Pulihora",
                R.drawable.amlapulihora,
                new Ingredient[]{amla, coriender, greenChilli, salt, mustardSeeds, jeera, redChilli, uradDal, chanaDal, groundnut, turmeric, oil, rice, curryLeaf},
                new String[]{"1 cup", "1/4 bunch", "5", "to taste", "1 tsp", "1 tsp", "2", "1 tsp", "1 tsp", "1 tsp", "1/2 tsp", "3 tsp", "2 cup", "2 springs"},
                new String[]{"Take a mixie jar and add amla pieces, coriander leaves, green chillies, little salt and blend into a smooth paste.",
                        "Heat oil in a pan and add mustard seeds, cumin seeds, dry red chilly, urad dal, chana dal, ground nuts, curry leaves and saute it.",
                        "Then add little salt, turmeric powder, amla paste and saute it.",
                        "In the same pan, add cooked rice, mix thoroughly and serve it."},
                new String[]{"7.2g", "32.1g", "18.1g", "317 cal", "1.8g", "0mg"});
        //Snack
        threeBeanChat = createRecipe("Three Bean Chaat",
                R.drawable.threebeanchat,
                new Ingredient[]{rajma, chanaDal, chowli, vegetables, onion, tomato, cucumber, pomegranate, oliveoil, lemonJuice, chatMasaala, salt, pepper, coriender},
                new String[]{"1 cup", "1 cup", "1 cup", "of your choice", "1/2 cup", "1/2 cup", "1/2 cup", "1/2 cup", "2tsp", "1tsp", "1 tsp", "to taste", "to taste", "1 tsp"},
                new String[]{"Whisk the olive oil, lemon juice, chaat masala, salt, pepper, chopped coriander to make a wonderful dressing.",
                        "Mix all the rajma, channa, chowli beans and green beans into the dressing.",
                        "Then add chopped potatoes, onion, tomatoes and cucumber.",
                        "Mix it well and let it soak the dressing in the fridge for approx an hour.", "Serve chilled."},
                new String[]{"7.6g", "17.8g", "4.4g", "142 cal", "1g", "0mg"});
        bananaOatBread = createRecipe("Banana Oat Bread",
                R.drawable.bananaoat,
                new Ingredient[]{wheat, oats, banana, brownSugar, oliveoil, vanillaEssence, eggs, bakingPowder, bakingsoda, salt, nuts},
                new String[]{"1.5 cup", "1/2 cup", "3", "3/4 cup", "1/3 cup", "1 tsp", "1", "1/2 tsp", "1/4 tsp", "a pinch", "to garnish"},
                new String[]{"Pre-heat oven to 180 degrees Celsius.",
                        "Combine all dry ingredients in a bowl (Whole wheat flour, oatmeal, baking powder, baking soda, salt,sugar).",
                        "Combine all wet ingredients in a bowl (Mashed ripe bananas, Egg, vanilla essence, olive oil).",
                        "Fold in dry ingredients into the wet ingredients.",
                        "Lightly oil a baking tin with olive oil.",
                        "Add the banana bread batter into the baking tin. Sprinkle nuts of choice.",
                        "Place the baking tin in the oven and let it bake for about 50-55 mins or until a pierced toothpick comes out clean.",
                        "Serve warm or cold."},
                new String[]{"2.7g", "26.5g", "2.8g", "139 cal", "0.9g", "36.3mg"});
        elachiGranolaBar = createRecipe("Elaichi Granola Bar",
                R.drawable.elachibar,
                new Ingredient[]{oats, raisins, nuts, elachi, butter, brownSugar, honey}, new String[]{"2 cups", "1/2 cup", "1/2 cup", "1 tsp", "4 tsp", "1/3 cup", "4 tsp"},
                new String[]{"Heat the butter, brown sugar and honey in a saucepan until the butter melts and begins to boil.",
                        "Pour this mixture over the dry ingredients and mix until well coated.",
                        "Take an aluminum foil, pour the mix on it and tightly wrap it making a bar. Set to chill.",
                        "Once the bar is set. Cut into small pieces."},
                new String[]{"1.9g", "16.7g", "6.6g", "128 cal", "1.3g", "10.2mg"});
        griledVegetableSalad = createRecipe("Grilled Veggies Salad",
                R.drawable.veggiesalad,
                new Ingredient[]{vegetables, oliveoil, salt, pepper}, new String[]{"of your choice", "2.5 tsp", "to taste", "to taste"},
                new String[]{"Cut the zucchini into halve and cut them into thick vertical slices to get 8 slices.",
                        "Heat the olive oil in a griller pan or a pan, place the zucchini sprinkle little sea salt and pepper powder cook on a medium flame for 2 minutes. While turning it twice in between.",
                        "Add the carrots, little sea salt and pepper powder and toss on a medium flame for 2 minutes.",
                        "Serve immediately."},
                new String[]{"3.7g", "25g", "0.7g", "112 cal", "7.8", "0mg"});
        powerPackedSalad = createRecipe("Power Packed Salad",
                R.drawable.powerpackedsalad,
                new Ingredient[]{quinoa, vegetables, brocoli, mushroom, paneer, seasme, flax, tulasi, oliveoil, salt, lemonJuice}, new String[]{"2 tsp", "of your choice", "15 florets", "5", "50g", "2 tsp", "2 tsp", "15 leaves", "2 tsp", "to taste", "1/2 lemon"},
                new String[]{"In large mixing bowl, put quinoa, grated carrot and cherry tomatoes.",
                        "Cut all the coloured capsicums into small pieces and add. Add broccoli florets. Slice and add mushrooms.",
                        "Break cottage cheese into small pieces and add. Add sesame seeds and flax seeds.",
                        "Tear basil leaves and add. Add extra virgin olive oil, rock salt and juice of ½ lemon. Toss the salad well.",
                        "Transfer into a serving bowl and serve immediately."},
                new String[]{"6.2g", "31.9g", "9.1g", "236 cal", "5.1g", "0mg"});
        lentilChaat = createRecipe("Lentil and Charred Broccoli Chaat",
                R.drawable.lentilchat,
                new Ingredient[]{masoordal, blackbeans, brocoli, tabasco, sweetLime, papaya, vegetables, methiSprouts, chatMasaala, aamchur, oliveoil, vinegar, honey, onion},
                new String[]{"1 cup", "30g", "8 florets", "Few drops", "1/2", "100g, diced", "of your choice", "1 cup", "1/2 tsp", "1/2 tsp", "1 tsp", "1 tsp", "20ml", "30g"},
                new String[]{"Cut and trim broccoli florets.",
                        "Season with salt, pepper and olive oil and put on a grill until charred.",
                        "Alternately you could even roast in the oven for 15 minutes or so.",
                        "Whisk all the dressing ingredients together to mix well.",
                        "Add all the ingredients for the chaat in a bowl.",
                        "Pour in the dressing and toss together.",
                        "Add some methi sprouts and the charred broccoli and serve."},
                new String[]{"6.1g", "16.4g", "5.8g", "130 cal", "6g", "0mg"});
        //Dinner
        rajmaCurry = createRecipe("Rajma Masala",
                R.drawable.rajma_curry,
                new Ingredient[]{rajma, bayleaf, elachi, salt, water, ghee, jeera, cinamon, cloves, onion, gingergarlicPaste, greenChilli, tomato, turmeric, redChilliPowder, corienderPowder, jeeraPowder, aamchur, garam_masala, kasuriMethi, coriender},
                new String[]{"1 cup", "1", "1 tsp", "1.5 tsp", "4 cups", "1 tsp", "1 tsp", "1 inch", "5", "1, chopped", "1 tsp", "1, slit", "2 cup", "1/4 tsp", "1 tsp", "1 tsp", "1/2 tsp", "1/2 tsp", "1/2 tsp", "1 tsp", "2 tsp"},
                new String[]{"In a large kadai heat 1 tbsp ghee and saute 1 tsp cumin, 1 inch cinnamon and 5 cloves.",
                        "Add in 1 onion, 1 tsp ginger garlic paste, 1 chilli and saute until the onions turn golden brown.",
                        "Now add 2 cup tomato pulp.",
                        "Cover and cook for 10 minutes, or until the oil is separated.",
                        "Further keeping the flame on low add ¼ tsp turmeric, 1 tsp chilli powder, 1 tsp coriander powder, ½ tsp cumin powder, ½ tsp aamchur, ½ tsp garam masala and ½ tsp salt.",
                        "Saute until the spices turn aromatic.",
                        "Add in cooked rajma and mix well.",
                        "Cover and simmer for 15 minutes or until the curry thickens.",
                        "Now add 1 tsp kasuri methi, 2 tbsp coriander and mix well.",
                        "Finally, enjoy rajma with hot jeera rice."},
                new String[]{"6.4g", "19g", "11.7g", "207 cal", "2.6g", "0mg"});
        palakpaneer = createRecipe("Palak Paneer",
                R.drawable.palak_panner,
                new Ingredient[]{water, spinach,ginger, garlic, greenChilli, oil, butter, paneer, jeera, cinamon, cloves, elachi, bayleaf, kasuriMethi, onion ,tomato, salt, garam_masala, cream},
                new String[]{"5.25 cup", "1 bunch", "1 inch", "1", "3","3 tsp", "1 tsp", "11 cube", "1 tsp", "1 inch", "4", "2 pods", "1", "2 tsp", "1/2, finely chopped", "1/2, finely chopped", "3/4 tsp", "1/4 tsp", "2 tsp" },
                new String[]{"Take blanched palak, 1 inch ginger, 1 clove garlic and 4 green chilli.",
                        "Blend to smooth paste without adding any water. keep aside.",
                        "Now in a large kadai heat 3 tsp oil, 1 tsp butter and roast the spices till it turns aromatic.",
                        "Further, add ½ onion and saute till it turns golden brown.",
                        "Additionally, add ½ tomato and saute till the tomatoes turn soft and mushy.",
                        "Add prepared palak paste, ¼ cup water and ¾ tsp salt.",
                        "Mix well adjusting consistency as required.",
                        "Further, add roasted paneer and mix well.",
                        "Simmer for 5 minutes or till paneer absorbs flavour.",
                        "Turn off the flame and add ¼ tsp garam masala, 1 tsp kasuri methi and 2 tbsp cream. mix well.",
                        "Finally, serve restaurant-style palak paneer with roti / naan."},
                new String[]{"15.9g", "21.2g", "36g", "470 cal", "6g", "3.8mg"});
        roti = createRecipe("Bajra Roti",
                R.drawable.roti,
                new Ingredient[]{pearlMilletFlour, salt, water, wheat}, new String[]{"2 cups", "1/2 tsp", "to knead", "for dusting"},
                new String[]{"Firstly, in a large mixing bowl take 2 cup bajra atta, ½ tsp salt and mix well.",
                        "Add ½ of hot water and start to knead.",
                        "Knead to the soft dough for at least 10 minutes.",
                        "Add water as required and knead well as there is no gluten in the dough.",
                        "Pinch a small ball sized dough and knead again.",
                        "Dust with wheat flour and pat gently. you can alternatively use a rolling pin to roll as done for paratha.",
                        "Pat with both the hands until the roti turns as thin as possible. if the roti breaks, it means it needs more kneading.",
                        "Dust off excess flour and put over hot tawa.",
                        "Now spread water over the roti with help of hand or wet cloth removing excess dough.",
                        "Wait until the water evaporates then flip it to the other side.",
                        "Press gently and cook all the sides.",
                        "Finally, serve bajra roti / sajje rotti with jaggery or curry."},
                new String[]{"12g", "67g", "5g", "360 cal", "1g", "0mg"});
        mixedVegCurry = createRecipe("Mixed Vegetable Curry",
                R.drawable.mixvegcurry,
                new Ingredient[]{oil, paneer, badam, vegetables, tomato, cinamon, cloves, elachi, bayleaf, jeera, kasuriMethi, greenChilli, onion, gingergarlicPaste, turmeric, redChilliPowder, corienderPowder, garam_masala, salt, curd, water, cream, coriender},
                new String[]{"7 tsp", "12 cubes", "2 tsp + 12 blanched", "of your choice", "2", "1 inch", "5", "2", "1", "1 tsp", "2 tsp", "1", "1, chopped", "1 tsp", "1/4 tsp", "1 tsp", "1 tsp", "1/2 tsp", "1 tsp", "1/2 tsp", "1/2 tsp", "2 tsp", "2 tsp"},
                new String[]{"Firstly heat 4 tsp oil and saute spices.",
                        "Saute 1 onion and 1 tsp ginger garlic paste.",
                        "Further add spices and 1 tsp salt. saute well.",
                        "Add tomato puree and cook well.",
                        "Add ½ cup whisked curd. stir continuously.",
                        "Now add roasted mix vegetables and mix well.",
                        "Add ½ cup water and cook for another 10 minutes.",
                        "Turn off the flame and add 2 tbsp cream, 1 tsp kasuri methi and 2 tbsp coriander. mix well.",
                        "Finally, serve mix vegetable curry / mix veg recipe with hot roti."},
                new String[]{"6g", "29g", "9g", "215 cal", "3g", "1mg"});
        this.brownRice = createRecipe("Brown Rice",
                R.drawable.brown_rice,
                new Ingredient[]{Ingredient.brownRice, water}, new String[]{"1 cup", "2 cup"},
                new String[]{"Wash the brown rice with water and soak it with 2 cups water for 2 hours",
                        "Pressure cook it for 4 whistles in high flame", "Then, simmer it for 15min on a low flame",
                        "Turn off the flame, allow the steam to escape before opening the lid.",
                        "Brown rice is now ready to serve"},
                new String[]{"2.6g", "23g", "0.9g", "111 cal", "1.8g", "0mg"});
        ladyfingerCurry = createRecipe("Kadai Bhendi",
                R.drawable.ladyfingercurry,
                new Ingredient[]{capsicum, coriender, corienderPowder, corienderSeeds, jeeraPowder, curryLeaf, garam_masala, gingergarlicPaste, greenChilli, oil, ladyfinger, onion, redChilliPowder, salt, tomato, turmeric},
                new String[]{"1 cup", "1 bunch", "2 tsp", "1 tsp", "2 tsp", "7", "3/4 tsp", "1 tsp", "4", "as required", "400g", "1 cup", "1 tsp", "to taste", "1 cup", "1 pinch"},
                new String[]{"Cut bhendi into small pieces and deep fry them little bit to avoid stickiness.",
                        "Cut tomatoes and  bell pepper into small cubes.",
                        "Crush coriander seeds in dingchick and keep it a side.",
                        "Take a pan add oil,cumin seeds,corinader seeds crushed,chopped onion,salt mix well and then add turmuric, ginger garlic paste,cook it for 2 mins and then add curry leaves,green chillies saute them.",
                        "Now add tomatoes cook it for 1 min, then add coriander powder, cumin powder,chilli powder mix well and cook for 4min till tomatoes are cooked now add bell pepper,and sprinkle chopped coriander leaves , and finely add the fried bhendi, mix little bit and close it a lid for 4 min in very slow flame.",
                        "Once bhendi is cooked add garama masala powder and mix well serve hot with roti or rice."},
                new String[]{"4g", "3g", "5g", "130 cal", "8.6g", "0mg"});

    }

    public List<Recipe> getRecipes(int timeOfDay) {
        recipeList = new ArrayList<>();
        if (timeOfDay == 0) {
            recipeList.add(ragiDosa);
            recipeList.add(ragiRavaDosa);
            recipeList.add(chanaDhokla);
            recipeList.add(oatsIdli);
            recipeList.add(multiGrainParatha);
        } else if (timeOfDay == 1) {
            recipeList.add(lentilsKichidi);
            recipeList.add(palakKichidi);
            recipeList.add(sambar);
            recipeList.add(bismillaibath);
            recipeList.add(amlaPulihora);
        } else if (timeOfDay == 2) {
            recipeList.add(threeBeanChat);
            recipeList.add(bananaOatBread);
            recipeList.add(elachiGranolaBar);
            recipeList.add(powerPackedSalad);
            recipeList.add(griledVegetableSalad);
            recipeList.add(lentilChaat);
        } else if (timeOfDay == 3) {
            recipeList.add(palakpaneer);
            recipeList.add(ladyfingerCurry);
            recipeList.add(this.brownRice);
            recipeList.add(mixedVegCurry);
            recipeList.add(rajmaCurry);
            recipeList.add(roti);
        }
        return this.recipeList;
    }

    public static Recipe createRecipe(String name, int image, Ingredient[] ingredients, String[] ingredientQuanity, String[] method, String[] nutrientQuantity) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(name);
        HashMap<Ingredient, String> ingredientList = new HashMap<>();
        List<Ingredient> getListIndexed = Arrays.asList(ingredients);
        List<String> getListIndexedQ = Arrays.asList(ingredientQuanity);
        for (int i = 0; i < getListIndexed.size() - 1; i++) {
            //TODO CHech getListIndexed.size()-1 above
            ingredientList.put(getListIndexed.get(i), getListIndexedQ.get(i));
        }
        Log.d("Ingredient Created", ingredientList.toString());
        recipe.setIngredients(ingredientList);
        recipe.setMethod(Arrays.asList(method));
        //Getting the ingredient list as a list to get the method index to add the same value indexed in the nutrient values String[]
        recipe.setNutrients(getNutrientHashMap(nutrientQuantity));
        recipe.setRecipeImage(image);
        return recipe;
    }

    public static class Nutrient {
        final static String PROTEINS = "Proteins";
        final static String CARBS = "Carbohydrates";
        final static String FATS = "Fats";
        final static String ENERGY = "Energy";
        final static String FIBRE = "Fibre";
        final static String CHOLESTEROL = "Cholesterol";
    }

    public static HashMap<String, String> getNutrientHashMap(String[] nutrient) {
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put(PROTEINS, nutrient[0]);
        hashmap.put(CARBS, nutrient[1]);
        hashmap.put(FATS, nutrient[2]);
        hashmap.put(ENERGY, nutrient[3]);
        hashmap.put(FIBRE, nutrient[4]);
        hashmap.put(CHOLESTEROL, nutrient[5]);
        return hashmap;
    }

}
