package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;
import rjsv.circularview.CircleView;
import rjsv.circularview.CircleViewAnimation;
import rjsv.circularview.enumerators.AnimationStyle;

public class YogaActivity extends AppCompatActivity {

    ListView aasanDescriptionListView, aasanCuresListView, aasanDontsListView;
    BulletListAdapter descriptionAdapter, curesAdapter, dontsAdapter;
    List<String> description;
    ConstraintLayout timerLayout;
    CircleView timerView;
    TextToSpeech textToSpeech;
    Runnable runnable;
    boolean isTimeOver;
    int time = 30;

    NestedScrollView scrollView;
    TextView aasanNameTextView, aasanAngloNameTextView;
    ImageView aasanImageView;
    List<YogaPose> yogaPosesList;
    MaterialButton doneButton;

    int pose = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_yoga);

        description = new ArrayList<>();
        doneButton = findViewById(R.id.yogaStartButton);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_yoga);
        bottomNavigationView.setSelectedItemId(R.id.yogaNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeNavigationButton:
                        startActivity(new Intent(YogaActivity.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.yogaNavigationButton:
                        return true;
                    case R.id.foodNavigationButton:
                        startActivity(new Intent(YogaActivity.this, FoodActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.journalNavigationButton:
                        startActivity(new Intent(YogaActivity.this, JournalActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.coronaNavigationButton:
                        startActivity(new Intent(YogaActivity.this, CoronaCautionActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return true;
            }
        });

        timerLayout = findViewById(R.id.timerConstraintLayout);
        timerView = findViewById(R.id.circleViewTimer);

        aasanNameTextView = findViewById(R.id.aasanNameTextView);
        aasanAngloNameTextView = findViewById(R.id.angloNamePoseTextView);
        scrollView = findViewById(R.id.nestedAasanScrollView);

        List<YogaPose> aasans = YogaPosesList.createAasans();

        yogaPosesList = new ArrayList<>();
        AhamSvasthaUser currentUser = AhamSvasthaUser.getAhamSvasthaUser(this, AhamSvasthaUser.getCurrentUsername(this));
        for (YogaPose yogaPose : aasans) {
            if (yogaPose.getCures().containsKey("Improves Immunity") || yogaPose.getCures().containsKey("Protects from Coronavirus")) {
                if (!yogaPosesList.contains(yogaPose)) {
                    yogaPosesList.add(yogaPose);
                }

                if (yogaPose.getName().matches("Kapalabhati")) {
                    List<YogaPose> sn = Arrays.asList(YogaPosesList.suryaNamaskaras);
                    for (YogaPose a : sn) {
                        if (!yogaPosesList.contains(a)) {
                            yogaPosesList.add(a);
                        }
                    }
                }
            }
            if (currentUser.getDiseasesHashMap().get("Diabetes") && yogaPose.getCures().containsKey("Diabetes")) {
                if (!yogaPosesList.contains(yogaPose)) {
                    yogaPosesList.add(yogaPose);
                }
            }
            if (currentUser.getDiseasesHashMap().get("Thyroid") && yogaPose.getCures().containsKey("Thyroid")) {
                if (!yogaPosesList.contains(yogaPose)) {
                    yogaPosesList.add(yogaPose);
                }
            }
            if (currentUser.getDiseasesHashMap().get("BP") && yogaPose.getCures().containsKey("High B.P.")) {
                if (!yogaPosesList.contains(yogaPose)) {
                    yogaPosesList.add(yogaPose);
                }
            }
            if (currentUser.getDiseasesHashMap().get("BP") && yogaPose.getCures().containsKey("Low B.P.")) {
                if (!yogaPosesList.contains(yogaPose)) {
                    yogaPosesList.add(yogaPose);
                }
            }
            if (currentUser.getDiseasesHashMap().get("Obesity") && yogaPose.getCures().containsKey("Weight Loss")) {
                if (!yogaPosesList.contains(yogaPose)) {
                    yogaPosesList.add(yogaPose);
                }
            }
        }
        yogaPosesList.add(YogaPosesList.donePose);

        SharedPreferences sharedPreferences = getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        sharedPreferences.edit().putInt("PosesNumber" + AhamSvasthaUser.getCurrentUsername(this) + simpleDateFormat.format(Calendar.getInstance().getTime()), yogaPosesList.size()-1).apply();
        pose = sharedPreferences.getInt("Pose" + AhamSvasthaUser.getCurrentUsername(this) + simpleDateFormat.format(Calendar.getInstance().getTime()), 0);

        aasanDescriptionListView = findViewById(R.id.aasanDescriptionListView);
        aasanCuresListView = findViewById(R.id.aasanCuresListView);
        aasanDontsListView = findViewById(R.id.aasanDontsListView);
        aasanDescriptionListView.setDivider(null);
        aasanDontsListView.setDivider(null);
        aasanCuresListView.setDivider(null);
        aasanCuresListView.setDividerHeight(0);
        aasanDontsListView.setDividerHeight(0);
        aasanDescriptionListView.setDividerHeight(0);
        aasanImageView = findViewById(R.id.aasanImageView);
        List<String> aasanNamesFound = new ArrayList<>();
        for (YogaPose y : yogaPosesList) {
            aasanNamesFound.add(y.getName());
        }
        Log.i("Aasans", aasanNamesFound.toString());
        changeAasana();
    }

    private void changeAasana() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        editor.putInt("Pose" + AhamSvasthaUser.getCurrentUsername(YogaActivity.this) + simpleDateFormat.format(Calendar.getInstance().getTime()), pose).apply();
        aasanNameTextView.setText(yogaPosesList.get(pose).getName());
        aasanAngloNameTextView.setText(yogaPosesList.get(pose).getAngloName());
        if (yogaPosesList.get(pose).getName().matches("Surya Namaskaras")) {
            GifImageView gifImageView = findViewById(R.id.suryanamaskarGIFView);
            gifImageView.setVisibility(View.VISIBLE);
        } else if (yogaPosesList.get(pose).getName().matches("Great Job!")) {
            GifImageView gifImageView = findViewById(R.id.suryanamaskarGIFView);
            gifImageView.setVisibility(View.GONE);
            aasanImageView.setImageResource(yogaPosesList.get(pose).getPicture());
            aasanImageView.setScaleType(ImageView.ScaleType.CENTER);
            doneButton.setText("Go to Home");
        } else {
            GifImageView gifImageView = findViewById(R.id.suryanamaskarGIFView);
            gifImageView.setVisibility(View.GONE);
            aasanImageView.setImageResource(yogaPosesList.get(pose).getPicture());
        }
        description = yogaPosesList.get(pose).getDescription();
        descriptionAdapter = new BulletListAdapter(this, description, R.drawable.google, 20);
        List<String> curesNames = new ArrayList<>();
        List<Integer> curesIcons = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : yogaPosesList.get(pose).getCures().entrySet()) {
            curesNames.add(entry.getKey());
            curesIcons.add(entry.getValue());
        }
        curesAdapter = new BulletListAdapter(this, curesNames, curesIcons, 18);

        List<String> dontsNames = new ArrayList<>();
        List<Integer> dontsIcons = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : yogaPosesList.get(pose).getDonotDo().entrySet()) {
            dontsNames.add(entry.getKey());
            dontsIcons.add(entry.getValue());
        }
        dontsAdapter = new BulletListAdapter(this, dontsNames, dontsIcons, 18);

        aasanDescriptionListView.setAdapter(descriptionAdapter);
        aasanCuresListView.setAdapter(curesAdapter);
        aasanDontsListView.setAdapter(dontsAdapter);
        descriptionAdapter.notifyDataSetChanged();
        curesAdapter.notifyDataSetChanged();
        dontsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        if (pose == 0) {
            doneButton.setText("Start");
            aasanImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                } else {
                    Log.i("error", "error");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.stop();
        textToSpeech.shutdown();
    }

    public void startYogaAasanaOnClick(View view) {

        if (aasanNameTextView.getText().toString().matches("Surya Namaskaras")) {
            pose++;
            changeAasana();
        } else if (aasanNameTextView.getText().toString().matches("Great Job!")) {
            startActivity(new Intent(YogaActivity.this, MainActivity.class));
            overridePendingTransition(0, 0);
        } else {

            scrollView.setAlpha(0.6f);
            scrollView.fullScroll(View.FOCUS_UP);
            scrollView.setEnabled(false);

            Handler handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    Log.i("timeValue", String.valueOf(isTimeOver));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && time >= 0) {
                        textToSpeech.speak(String.valueOf(time), TextToSpeech.QUEUE_ADD, null);
                        time--;
                        handler.postDelayed(runnable, 1000);
                    } else {
                        timerLayout.setVisibility(View.GONE);
                        scrollView.setAlpha(1f);
                        scrollView.setEnabled(true);
                        time = 30;
                        timerView.setProgressValue(30);

                        pose++;
                        Log.i("Pose", String.valueOf(pose));
                        changeAasana();

                    }
                }
            };

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(String.valueOf(time), TextToSpeech.QUEUE_ADD, null);
                time--;
            }
            timerLayout.setVisibility(View.VISIBLE);
            CircleViewAnimation circleViewAnimation = new CircleViewAnimation(timerView)
                    .setAnimationStyle(AnimationStyle.CONTINUOUS)
                    .setDuration(timerView.getProgressValue())
                    .setCustomAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                textToSpeech.speak(String.valueOf(time), TextToSpeech.QUEUE_ADD, null);
                                time--;
                            }
                            handler.postDelayed(runnable, 1000);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    }).setTimerOperationOnFinish(new Runnable() {
                        @Override
                        public void run() {
                            timerLayout.setVisibility(View.GONE);
                        }
                    })
                    .setCustomInterpolator(new LinearInterpolator());
            circleViewAnimation.start();
        }
    }
}