package com.manasmalla.ahamsvasth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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

    FirebaseAuth mAuth;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();

        if (this.getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).getString("Gender", null) != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (mAuth.getCurrentUser() != null) {
            mAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                @Override
                public void onSuccess(GetTokenResult getTokenResult) {
                    updateUI(mAuth.getCurrentUser(), getTokenResult.getSignInProvider());
                }
            });
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void updateUI(FirebaseUser user, String provider) {
        //
        if (user != null) {
            Intent socialIntent = new Intent(getApplicationContext(), UserDataQuizActivity.class);

            String userPhotoURL = String.valueOf(user.getPhotoUrl());
            if (provider.matches("facebook.com")) {
                userPhotoURL = userPhotoURL + "?height=512";
            } else if (provider.matches("twitter.com")) {
                userPhotoURL = userPhotoURL.replace("_normal", "");
            }
            userPhotoURL = userPhotoURL.replace("s96-c", "s512-c");
            Log.i(provider, userPhotoURL);
            socialIntent.putExtra("Name", user.getDisplayName());
            socialIntent.putExtra("Email", user.getEmail());
            Bitmap profileBitmap = downloadImage(userPhotoURL);

            File externalStorage = SplashScreenActivity.this.getExternalFilesDir(null);
            File filePath = new File(externalStorage.getAbsolutePath() + "profile");
            if (filePath.exists()) {
                Log.i("FilePath", "Exists");
            } else {
                if (filePath.mkdir()) {
                    Log.i("Created Folder at ", filePath.toString());
                } else {
                    Toast.makeText(this, "Couldn't save profile pic! ", Toast.LENGTH_SHORT).show();
                }
            }
            File imageSlide = new File(filePath, "profile_image.png");

            try {
                outputStream = new FileOutputStream(imageSlide);
                profileBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(socialIntent);
        }

    }
}