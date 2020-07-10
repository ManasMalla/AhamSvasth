package com.manasmalla.ahamsvasth;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;

public class AhamSvasthaApp extends Application {

    public static final String CHANNEL_ID = "AhamSvasthaNotificationChannelId";
    public static final String CHANNEL_ID_WATER = "AhamSvasthaWaterNotificationChannelId";
    NotificationManager mNotifyManager;

    public static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        createNotificationChannel();
        createNotificationChannelWater();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Aham Svastha", NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
            serviceChannel.enableVibration(true);
            serviceChannel.setDescription("Activity Check Foreground Service!");
            manager.createNotificationChannel(serviceChannel);
        }

    }
    public void createNotificationChannelWater() {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Water Remainder", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification to drink water!");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}