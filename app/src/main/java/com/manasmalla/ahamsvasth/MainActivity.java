package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rjsv.circularview.CircleView;

import static com.manasmalla.ahamsvasth.BackgroundDetectedActivitiesService.isServiceRunning;
import static com.manasmalla.ahamsvasth.RecipesList.Nutrient.CARBS;
import static com.manasmalla.ahamsvasth.RecipesList.Nutrient.CHOLESTEROL;
import static com.manasmalla.ahamsvasth.RecipesList.Nutrient.ENERGY;
import static com.manasmalla.ahamsvasth.RecipesList.Nutrient.FATS;
import static com.manasmalla.ahamsvasth.RecipesList.Nutrient.FIBRE;
import static com.manasmalla.ahamsvasth.RecipesList.Nutrient.PROTEINS;

public class MainActivity extends AppCompatActivity {

    TextView greetTextView;
    CircleImageView profileImage;
    private static final int activityRecognitionPermissionCode = 003;
    ActivityOptions activityOptions;
    ImageView[] waterIndicator;
    protected static boolean isAppForeground = false;
    TextView messageSleep, sleepTitle;
    ImageView morningImageView;
    NonScrollListView scrollListViewExercise;
    boolean isSleep = true;

    List<String> activitys, sDate, eDate;
    List<Integer> distances;

    public void waterOnClick(View view) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        editor.putInt("Water" + AhamSvasthaUser.getCurrentUsername(getApplicationContext()) + simpleDateFormat.format(Calendar.getInstance().getTime()), sharedPreferences.getInt("Water" + AhamSvasthaUser.getCurrentUsername(getApplicationContext()) + simpleDateFormat.format(Calendar.getInstance().getTime()), 0) + 1).apply();
        Toast.makeText(getApplicationContext(), "Very Good! You drank " + sharedPreferences.getInt("Water" + AhamSvasthaUser.getCurrentUsername(getApplicationContext()) + simpleDateFormat.format(Calendar.getInstance().getTime()), 0) + " glasses :)", Toast.LENGTH_SHORT).show();
        if (MainActivity.isAppForeground) {
            getApplicationContext().startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == activityRecognitionPermissionCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startTracking();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        int hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hourOfDay >= 6 && hourOfDay < 20) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_sleep);
        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        messageSleep = findViewById(R.id.textView19);
        sleepTitle = findViewById(R.id.textView26);
        morningImageView = findViewById(R.id.imageView19);/*
        NutritionAPIJSONCreator nutritionAPIJSONCreator = new NutritionAPIJSONCreator();
        nutritionAPIJSONCreator.createAPIJSON();*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.homeNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeNavigationButton:
                        return true;
                    case R.id.yogaNavigationButton:
                        startActivity(new Intent(MainActivity.this, YogaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.foodNavigationButton:
                        startActivity(new Intent(MainActivity.this, FoodActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.journalNavigationButton:
                        startActivity(new Intent(MainActivity.this, JournalActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.coronaNavigationButton:
                        startActivity(new Intent(MainActivity.this, CoronaCautionActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return true;
            }
        });

        greetTextView = findViewById(R.id.usernameGreet_MainActivity);
        profileImage = findViewById(R.id.userProfile_mainActivity);
        String displayName = AhamSvasthaUser.getCurrentUsername(this);
        String firstName = displayName;
        if (displayName.contains(" ")) {
            firstName = displayName.substring(0, displayName.indexOf(" "));
        }
        greetTextView.setText("Hey, " + firstName + "!");
        File externalStorage = MainActivity.this.getExternalFilesDir(null);
        File filePath = new File(externalStorage.getAbsolutePath() + "profile");
        File imageSlide = new File(filePath, "profile_image.png");
        Bitmap bitmap = BitmapFactory.decodeFile(imageSlide.getAbsolutePath());
        profileImage.setImageBitmap(bitmap);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent socialIntent = new Intent(getApplicationContext(), UserDataQuizActivity.class);
                socialIntent.putExtra("Name", MainActivity.this.getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).getString("username", null));
                socialIntent.putExtra("Email", MainActivity.this.getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).getString("email", null));
                startActivity(socialIntent);
            }
        });

        scrollListViewExercise = findViewById(R.id.exerciseIndicatorMainActivity);
        activitys = new ArrayList<>();
        distances = new ArrayList<>();
        if (ActivityDatabase.getDatabase(this) != null) {

            List<ActivityDatabase.Activity> activityList = ActivityDatabase.getDatabase(this);
            for (ActivityDatabase.Activity activity : activityList) {
                if (activity.getStartTime().getDate() == Calendar.getInstance().getTime().getDate() && activity.getStartTime().getMonth() == Calendar.getInstance().getTime().getMonth() && activity.getStartTime().getYear() == Calendar.getInstance().getTime().getYear()) {
                    activitys.add(activity.getName() + ", " + activity.getDistance() + "m");
                }
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activitys);
        scrollListViewExercise.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        int hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        if (hourOfDay >= 6 && hourOfDay < 20) {
            //setContentView(R.layout.activity_main);
            AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

            Date futureDate = new Date(new Date().getTime() + 86400000);
            futureDate.setHours(6);
            futureDate.setMinutes(0);
            futureDate.setSeconds(0);
            Intent intent = new Intent(getApplicationContext(), MyAppReciever.class);

            PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            am.set(AlarmManager.RTC_WAKEUP, futureDate.getTime(), sender);

            if (hourOfDay >= 10) {
                getSleepTimeText();
            } else {
                didYouWakeUpTextChanger();
            }
        } else {
            //setContentView(R.layout.activity_main_sleep);
            didYouSleepTextChanger();
        }
        isAppForeground = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, activityRecognitionPermissionCode);
        } else {
            if (!isServiceRunning) {
                Log.i("Service", "Not Running");
                startTracking();
            } else {
                Log.i("Service", "Running");
            }
        }

        waterIndicator = new ImageView[]{findViewById(R.id.waterIndicator_1), findViewById(R.id.waterIndicator_2), findViewById(R.id.waterIndicator_3), findViewById(R.id.waterIndicator_4), findViewById(R.id.waterIndicator_5), findViewById(R.id.waterIndicator_6), findViewById(R.id.waterIndicator_7), findViewById(R.id.waterIndicator_8)};
        for (ImageView glass : waterIndicator) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                glass.setImageTintMode(PorterDuff.Mode.SRC_ATOP);
                glass.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.waterTint)));
            } else {
                Drawable wrapDrawable = DrawableCompat.wrap(glass.getDrawable());
                DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(this, R.color.waterTint));
                DrawableCompat.setTintMode(wrapDrawable, PorterDuff.Mode.SRC_ATOP);
                glass.setImageDrawable(wrapDrawable);
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        /*try {
//            Log.i("Sleep Date", String.valueOf(simpleDateFormat.parse(getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getString("Slept@"+ AhamSvasthaUser.getCurrentUsername(MainActivity.this)+ simpleDateFormat.format(calendar.getTime()), ""))));
        } catch (ParseException e) {
            e.printStackTrace();
        }

         */
        Log.i("Date", simpleDateFormat.format(Calendar.getInstance().getTime()));
        for (int i = 0; i < getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getInt("Water" + AhamSvasthaUser.getCurrentUsername(MainActivity.this) + simpleDateFormat.format(Calendar.getInstance().getTime()), 0); i++) {
            if (i < 8) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    waterIndicator[i].setImageTintList(null);
                } else {
                    waterIndicator[i].setImageResource(R.drawable.water);
                }
            }
        }
        int numberOfPoses = getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getInt("PosesNumber" + AhamSvasthaUser.getCurrentUsername(this) + simpleDateFormat.format(Calendar.getInstance().getTime()), 0);
        int poseNumber = getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getInt("Pose" + AhamSvasthaUser.getCurrentUsername(this) + simpleDateFormat.format(Calendar.getInstance().getTime()), 0);
        CircleView exerciseIndicator = findViewById(R.id.exerciseCircleViewindicator);
        TextView exerciseIndicatorText = findViewById(R.id.textView40);
        if (numberOfPoses != 0) {
            exerciseIndicator.setProgressValue((poseNumber * 100) / numberOfPoses);
            exerciseIndicatorText.setText(poseNumber + "\n-----\n" + numberOfPoses);
        }

        updateFood();
    }

    private void updateFood() {
        double proteins = 0, carbohydrates = 0, fats = 0, fibre = 0, cholestrol = 0, energy = 0;

        RecipesList recipesList = new RecipesList();
        recipesList.setupRecipes();
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

        for (String br : breakfast) {
            for (Recipe recipe : recipeList1) {
                if (recipe.recipeName.matches(br)) {
                    rBreakfast.add(recipe);
                }
            }
        }
        for (Recipe recipeB : rBreakfast) {
            proteins += Double.parseDouble(recipeB.nutrients.get(PROTEINS).replace("g", ""));
            carbohydrates += Double.parseDouble(recipeB.nutrients.get(CARBS).replace("g", ""));
            fats += Double.parseDouble(recipeB.nutrients.get(FATS).replace("g", ""));
            fibre += Double.parseDouble(recipeB.nutrients.get(FIBRE).replace("g", ""));
            energy += Double.parseDouble(recipeB.nutrients.get(ENERGY).replace(" cal", ""));
            cholestrol += Double.parseDouble(recipeB.nutrients.get(CHOLESTEROL).replace("mg", ""));
        }

        for (String lu : lunch) {
            for (Recipe recipe : recipeList1) {
                if (recipe.recipeName.matches(lu)) {
                    rLunch.add(recipe);
                }
            }
        }
        for (Recipe recipeL : rLunch) {
            proteins += Double.parseDouble(recipeL.nutrients.get(PROTEINS).replace("g", ""));
            carbohydrates += Double.parseDouble(recipeL.nutrients.get(CARBS).replace("g", ""));
            fats += Double.parseDouble(recipeL.nutrients.get(FATS).replace("g", ""));
            fibre += Double.parseDouble(recipeL.nutrients.get(FIBRE).replace("g", ""));
            energy += Double.parseDouble(recipeL.nutrients.get(ENERGY).replace(" cal", ""));
            cholestrol += Double.parseDouble(recipeL.nutrients.get(CHOLESTEROL).replace("mg", ""));
        }


        for (String sn : snack) {
            for (Recipe recipe : recipeList1) {
                if (recipe.recipeName.matches(sn)) {
                    rSnack.add(recipe);
                }
            }
        }
        for (Recipe recipeS : rSnack) {
            proteins += Double.parseDouble(recipeS.nutrients.get(PROTEINS).replace("g", ""));
            carbohydrates += Double.parseDouble(recipeS.nutrients.get(CARBS).replace("g", ""));
            fats += Double.parseDouble(recipeS.nutrients.get(FATS).replace("g", ""));
            fibre += Double.parseDouble(recipeS.nutrients.get(FIBRE).replace("g", ""));
            energy += Double.parseDouble(recipeS.nutrients.get(ENERGY).replace(" cal", ""));
            cholestrol += Double.parseDouble(recipeS.nutrients.get(CHOLESTEROL).replace("mg", ""));
        }


        for (String di : dinner) {
            for (Recipe recipe : recipeList1) {
                if (recipe.recipeName.matches(di)) {
                    rDinner.add(recipe);
                }
            }
        }
        for (Recipe recipeD : rDinner) {
            proteins += Double.parseDouble(recipeD.nutrients.get(PROTEINS).replace("g", ""));
            carbohydrates += Double.parseDouble(recipeD.nutrients.get(CARBS).replace("g", ""));
            fats += Double.parseDouble(recipeD.nutrients.get(FATS).replace("g", ""));
            fibre += Double.parseDouble(recipeD.nutrients.get(FIBRE).replace("g", ""));
            energy += Double.parseDouble(recipeD.nutrients.get(ENERGY).replace(" cal", ""));
            cholestrol += Double.parseDouble(recipeD.nutrients.get(CHOLESTEROL).replace("mg", ""));
        }

        TextView prI, caI, carI, faI, fiI, chI;
        prI = findViewById(R.id.nutritionTV2_m);
        caI = findViewById(R.id.nutritionTV1_m);
        carI = findViewById(R.id.nutritionTV3_m);
        faI = findViewById(R.id.nutritionTV4_m);
        fiI = findViewById(R.id.nutritionTV5_m);
        chI = findViewById(R.id.nutritionTV6_m);

        prI.setText(Math.round(proteins) + " g");
        caI.setText(Math.round(energy) + " cal");
        carI.setText(Math.round(carbohydrates) + " g");
        faI.setText(Math.round(fats) + " g");
        fiI.setText(Math.round(fibre) + " g");
        chI.setText(Math.round(cholestrol) + " mg");

    }

    public class MyAppReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            startService(new Intent(MainActivity.this, BackgroundDetectedActivitiesService.class));
        }
    }

    private void didYouSleepTextChanger() {
        isSleep = true;
        messageSleep.setText("Did you go to");
        morningImageView.setImageResource(R.drawable.sleep);
        findViewById(R.id.imageView21).setVisibility(View.VISIBLE);
        findViewById(R.id.imageView22).setVisibility(View.VISIBLE);
    }

    private void didYouWakeUpTextChanger() {
        isSleep = false;
        messageSleep.setText("Did you wake up from");
        morningImageView.setImageResource(R.drawable.awake);
        findViewById(R.id.imageView21).setVisibility(View.VISIBLE);
        findViewById(R.id.imageView22).setVisibility(View.VISIBLE);
    }

    private void getSleepTimeText() {
        messageSleep.setText("You slept for");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        Date sleptDate = null, wokeDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Log.d("Yesterday", simpleDateFormat.format(calendar.getTime()));
        try {
            sleptDate = simpleDateFormat.parse(getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getString("Slept@" + AhamSvasthaUser.getCurrentUsername(MainActivity.this) + simpleDateFormat.format(calendar.getTime()), ""));

            wokeDate = simpleDateFormat.parse(getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getString("Woke@" + AhamSvasthaUser.getCurrentUsername(MainActivity.this) + simpleDateFormat.format(calendar.getTime()), ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (sleptDate != null && wokeDate != null) {
            sleepTitle.setText(String.valueOf((int) ((wokeDate.getTime() - sleptDate.getTime()) / 3600000)) + " hours!");
        } else {
            sleepTitle.setText("?? hours!");
        }
        findViewById(R.id.imageView21).setVisibility(View.GONE);
        findViewById(R.id.imageView22).setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isAppForeground = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAppForeground = false;
    }

    public void showIntroduction(View view) {
        startActivity(new Intent(getApplicationContext(), IntroductionActivity.class));
    }


    private void startTracking() {
        Intent intent = new Intent(MainActivity.this, BackgroundDetectedActivitiesService.class);
        startService(intent);
    }

    private void stopTracking() {
        Intent intent = new Intent(MainActivity.this, BackgroundDetectedActivitiesService.class);
        stopService(intent);
    }

    public void sleptOnClick(View view) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        if (isSleep) {
            if (getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getString("Slept@" + AhamSvasthaUser.getCurrentUsername(MainActivity.this) + simpleDateFormat.format(Calendar.getInstance().getTime()), null) != null) {
                Log.d("Slept", "Already");
            } else {
                getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).edit().putString("Slept@" + AhamSvasthaUser.getCurrentUsername(MainActivity.this) + simpleDateFormat.format(Calendar.getInstance().getTime()), simpleDateFormat.format(Calendar.getInstance().getTime()).toString()).apply();
                stopTracking();
            }
            didYouWakeUpTextChanger();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            if (getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getString("Woke@" + AhamSvasthaUser.getCurrentUsername(MainActivity.this) + simpleDateFormat.format(calendar.getTime()), null) != null) {
                Log.d("Woke", "Already");
            } else {
                getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).edit().putString("Woke@" + AhamSvasthaUser.getCurrentUsername(MainActivity.this) + simpleDateFormat.format(calendar.getTime()), simpleDateFormat.format(Calendar.getInstance().getTime()).toString()).apply();
            }
            getSleepTimeText();
        }
    }

    public void didSleepOnClick(View view) {
        if (isSleep) {
            Toast.makeText(this, "It's time to go to bed! Remember, Early to Bed, Early to Rise! :)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "It's time to go to wake up! Remember, Early to Bed, Early to Rise! :)", Toast.LENGTH_SHORT).show();
        }
    }
}