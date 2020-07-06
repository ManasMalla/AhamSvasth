package com.manasmalla.ahamsvasth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }
     public  void ahamSvasth(View view){
         ImageView ahamSvasth = (ImageView) findViewById(R.id.imageView) ;
         ahamSvasth.animate().setDuration(2000);
         Handler handler = new Handler();
         Runnable runnable = new Runnable() {
             @Override
             public void run() {
                 Intent intent = new Intent(getApplicationContext(),  LoginActivity.class);
                 startActivity(intent);
             }
         };

         handler.postDelayed(runnable, 2000);

     }



    }
