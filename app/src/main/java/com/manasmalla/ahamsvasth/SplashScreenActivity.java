package com.manasmalla.ahamsvasth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.manasmalla.ahamsvasth.LoginActivity.downloadImage;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (SplashScreenActivity.this.getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).getString("Gender"+AhamSvasthaUser.getCurrentUsername(SplashScreenActivity.this), null) != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else if (SplashScreenActivity.this.getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).getString("username", null) != null){
                    Intent socialIntent = new Intent(getApplicationContext(), UserDataQuizActivity.class);
                    socialIntent.putExtra("Name", SplashScreenActivity.this.getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).getString("username", null) );
                    socialIntent.putExtra("Email", SplashScreenActivity.this.getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).getString("email", null) );
                    startActivity(socialIntent);
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        handler.postDelayed(runnable, 2000);

    }
}