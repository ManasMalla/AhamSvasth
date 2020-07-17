package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CoronaCautionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_corona_caution);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_corona);
        bottomNavigationView.setSelectedItemId(R.id.coronaNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNavigationButton:
                        startActivity(new Intent(CoronaCautionActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.yogaNavigationButton:
                        startActivity(new Intent(CoronaCautionActivity.this, YogaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.foodNavigationButton:
                        startActivity(new Intent(CoronaCautionActivity.this, FoodActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.journalNavigationButton:
                        startActivity(new Intent(CoronaCautionActivity.this, JournalActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.coronaNavigationButton:
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}