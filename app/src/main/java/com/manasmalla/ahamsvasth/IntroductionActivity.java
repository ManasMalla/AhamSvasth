package com.manasmalla.ahamsvasth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntroductionActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private int[] layouts;
    NonScrollListView listView;
    RecommendationAdapter recommendationAdapter;
    List<YogaPose> yogaPosesList;
    List<String> yogaPoseNames;
    List<Integer> yogaPoseDrawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_introduction);
        viewPager = (ViewPager) findViewById(R.id.intro_viewPager);
        layouts = new int[]{
                R.layout.intro_slide_one,
                R.layout.activity_about_me, R.layout.intro_slide_three, R.layout.intro_slide_four};
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        //Get yoga poses
        List<YogaPose> aasans = YogaPosesList.createAasans();

        yogaPosesList = new ArrayList<>();
        AhamSvasthaUser currentUser = AhamSvasthaUser.getAhamSvasthaUser(this, AhamSvasthaUser.getCurrentUsername(this));
        Log.i("Dies", currentUser.getDiseasesHashMap().toString());
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
        yogaPoseNames = new ArrayList<>();
        yogaPoseDrawables = new ArrayList<>();
        for (YogaPose y : yogaPosesList) {
            yogaPoseNames.add(y.getName());
            yogaPoseDrawables.add(y.getPicture());
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
            } else {
                // still pages are left
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            if (view.findViewById(R.id.exercise_recommendation_listView) != null) {
                recommendationAdapter = new RecommendationAdapter(IntroductionActivity.this, yogaPoseNames, yogaPoseDrawables, 24);
                listView = view.findViewById(R.id.exercise_recommendation_listView);
                listView.setAdapter(recommendationAdapter);
                listView.setDivider(null);
                recommendationAdapter.notifyDataSetChanged();
            }

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    public void closeWebView(View view) {

    }

    public void startApp(View view) {
        int current = getItem(+1);
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void downloadBeCoronaReady(View view) {
        String downloadURL = "https://github.com/ManasMalla/BeCoronaReady/releases/download/v1.0/BeCoronaReady.apk";
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.downloadFile(IntroductionActivity.this, downloadURL, "BeCoronaReady.apk");
    }
}