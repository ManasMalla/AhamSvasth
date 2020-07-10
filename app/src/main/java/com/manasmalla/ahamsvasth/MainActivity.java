package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.Manifest;
import android.app.ActivityOptions;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.manasmalla.ahamsvasth.BackgroundDetectedActivitiesService.isServiceRunning;

public class MainActivity extends AppCompatActivity {

    TextView greetTextView;
    CircleImageView profileImage;
    private static final int activityRecognitionPermissionCode = 003;
    ActivityOptions activityOptions;
    ImageView[] waterIndicator;
    

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
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.homeNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNavigationButton:
                        return true;
                    case R.id.yogaNavigationButton:
                        startActivity(new Intent(MainActivity.this, YogaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.foodNavigationButton:
                        startActivity(new Intent(MainActivity.this, FoodActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.journalNavigationButton:
                        startActivity(new Intent(MainActivity.this, JournalActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.infoNavigationButton:
                        startActivity(new Intent(MainActivity.this, AboutMeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });

        greetTextView = findViewById(R.id.usernameGreet_MainActivity);
        profileImage = findViewById(R.id.userProfile_mainActivity);
        String displayName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
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
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, activityRecognitionPermissionCode);
        } else {
            if (!isServiceRunning){
                Log.i("Service", "Not Running");
                startTracking();
            }else{
                Log.i("Service", "Running");
            }
        }

        waterIndicator = new ImageView[]{findViewById(R.id.waterIndicator_1), findViewById(R.id.waterIndicator_2), findViewById(R.id.waterIndicator_3), findViewById(R.id.waterIndicator_4), findViewById(R.id.waterIndicator_5), findViewById(R.id.waterIndicator_6), findViewById(R.id.waterIndicator_7),findViewById(R.id.waterIndicator_8)};
        for (ImageView glass : waterIndicator){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                glass.setImageTintMode(PorterDuff.Mode.SRC_ATOP);
                glass.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.waterTint)));
            }else{
                Drawable wrapDrawable = DrawableCompat.wrap(glass.getDrawable());
                DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(this, R.color.waterTint));
                DrawableCompat.setTintMode(wrapDrawable, PorterDuff.Mode.SRC_ATOP);
                glass.setImageDrawable(wrapDrawable);
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        Log.i("Date", simpleDateFormat.format(Calendar.getInstance().getTime()));
        for (int i = 0; i < getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE).getInt("Water"+ simpleDateFormat.format(Calendar.getInstance().getTime()), 0); i++){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                waterIndicator[i].setImageTintList(null);
            }else{
                waterIndicator[i].setImageResource(R.drawable.water);
            }
        }
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

}