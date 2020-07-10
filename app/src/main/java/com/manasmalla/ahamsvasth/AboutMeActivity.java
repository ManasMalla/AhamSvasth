package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        //Navigation Bar Related
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_aboutme);
        bottomNavigationView.setSelectedItemId(R.id.infoNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNavigationButton:
                        startActivity(new Intent(AboutMeActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.yogaNavigationButton:
                        startActivity(new Intent(AboutMeActivity.this, YogaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.foodNavigationButton:
                        startActivity(new Intent(AboutMeActivity.this, FoodActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.journalNavigationButton:
                        startActivity(new Intent(AboutMeActivity.this, JournalActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.infoNavigationButton:
                        return true;
                }
                return true;
            }
        });
    }
}