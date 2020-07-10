package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class YogaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_yoga);
        bottomNavigationView.setSelectedItemId(R.id.yogaNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNavigationButton:
                        startActivity(new Intent(YogaActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.yogaNavigationButton:
                        return true;
                    case R.id.foodNavigationButton:
                        startActivity(new Intent(YogaActivity.this, FoodActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.journalNavigationButton:
                        startActivity(new Intent(YogaActivity.this, JournalActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.infoNavigationButton:
                        startActivity(new Intent(YogaActivity.this, AboutMeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });
    }
}