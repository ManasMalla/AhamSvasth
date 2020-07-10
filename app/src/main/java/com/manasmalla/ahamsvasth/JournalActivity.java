package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JournalActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;

    private static String toActivityString(int activity, Context context) {
        String label = context.getString(R.string.activity_unknown);
        switch (activity) {
            case DetectedActivity.IN_VEHICLE: {
                label = context.getString(R.string.activity_in_vehicle);
                break;
            }
            case DetectedActivity.ON_BICYCLE: {
                label = context.getString(R.string.activity_on_bicycle);
                break;
            }
            case DetectedActivity.ON_FOOT: {
                label = context.getString(R.string.activity_on_foot);
                break;
            }
            case DetectedActivity.RUNNING: {
                label = context.getString(R.string.activity_running);
                break;
            }
            case DetectedActivity.STILL: {
                label = context.getString(R.string.activity_still);
                break;
            }
            case DetectedActivity.TILTING: {
                label = context.getString(R.string.activity_tilting);
                break;
            }
            case DetectedActivity.WALKING: {
                label = context.getString(R.string.activity_walking);
                break;
            }
            case DetectedActivity.UNKNOWN: {
                label = context.getString(R.string.activity_unknown);
                break;
            }
        }
        return label;
    }

    private static String toTransitionType(int transitionType) {
        switch (transitionType) {
            case ActivityTransition.ACTIVITY_TRANSITION_ENTER:
                return "ENTER";
            case ActivityTransition.ACTIVITY_TRANSITION_EXIT:
                return "EXIT";
            default:
                return "UNKNOWN";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        //Navigation Bar Related
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_journal);
        bottomNavigationView.setSelectedItemId(R.id.journalNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNavigationButton:
                        startActivity(new Intent(JournalActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.yogaNavigationButton:
                        startActivity(new Intent(JournalActivity.this, YogaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.foodNavigationButton:
                        startActivity(new Intent(JournalActivity.this, FoodActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.journalNavigationButton:
                        return true;
                    case R.id.infoNavigationButton:
                        startActivity(new Intent(JournalActivity.this, AboutMeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.BROADCAST_DETECTED_ACTIVITY)) {
                    int type = intent.getIntExtra("type", -1);
                    int confidence = intent.getIntExtra("confidence", 0);
                    handleUserActivity(type, confidence);
                }
            }
        };

    }
    private void handleUserActivity(int type, int confidence) {

        Log.e(TAG, "User activity: " + toActivityString(type, JournalActivity.this) + ", Confidence: " + confidence);

        if (confidence > Constants.CONFIDENCE) {
            Log.i("userActivity", toActivityString(type, JournalActivity.this));
            Log.i("confidence", String.valueOf(confidence));
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(Constants.BROADCAST_DETECTED_ACTIVITY));
    }
}