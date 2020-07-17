package com.manasmalla.ahamsvasth;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.manasmalla.ahamsvasth.AhamSvasthaApp.CHANNEL_ID;
import static com.manasmalla.ahamsvasth.AhamSvasthaApp.PRIMARY_CHANNEL_ID;

public class BackgroundDetectedActivitiesService extends Service {
    private static final String TAG = BackgroundDetectedActivitiesService.class.getSimpleName();
    private static final int NOTIFICATION_ID = 005;
    public static final int NOTIFICATION_ID_WATER = 0;
    private NotificationManager mNotifyManager;
    private Intent mIntentService, mIntentNotification;
    private PendingIntent mPendingIntent;
    private ActivityRecognitionClient mActivityRecognitionClient;
    private static final int numberOfHours = 1;
    Runnable runnable;
    public static boolean isServiceRunning = false;

    IBinder mBinder = new BackgroundDetectedActivitiesService.LocalBinder();

    public class LocalBinder extends Binder {
        public BackgroundDetectedActivitiesService getServerInstance() {
            return BackgroundDetectedActivitiesService.this;
        }
    }

    public BackgroundDetectedActivitiesService() {

    }

    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID_WATER, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Get Hydrated!")
                .setContentText(getString(R.string.waterNotificationText))
                .setSmallIcon(R.drawable.ic_water)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        return notifyBuilder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityRecognitionClient = new ActivityRecognitionClient(this);
        mIntentService = new Intent(this, DetectedActivitiesIntentService.class);
        mPendingIntent = PendingIntent.getService(this, 1, mIntentService, PendingIntent.FLAG_UPDATE_CURRENT);
        requestActivityUpdatesButtonHandler();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.active_boy);

        mIntentNotification = new Intent(this, JournalActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, mIntentNotification, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Get Moving!")
                .setContentText("Come on, Get up and move! Activity Time!")
                .setSmallIcon(R.drawable.ic_main_notification)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.activityNotificationText)))
                .setLargeIcon(bitmap)
                .setContentIntent(notificationPendingIntent)
                .build();

        startForeground(NOTIFICATION_ID, notification);
        isServiceRunning = true;
        setupWaterNotification();
        Handler handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                setupWaterNotification();
                handler.postDelayed(runnable, numberOfHours * 60 * 60* 1000);
            }
        };
        handler.postDelayed(runnable, numberOfHours * 60 * 60* 1000);
        return START_STICKY;
    }

    private void setupWaterNotification() {
        Intent updateIntent = new Intent(this, AddWaterService.class).putExtra("id", 100);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID_WATER, updateIntent, PendingIntent.FLAG_ONE_SHOT);
        Intent updateNoIntent = new Intent(this, SnoozeService.class).putExtra("id", 100);
        PendingIntent updateNoPendingIntent = PendingIntent.getBroadcast(this, 1, updateNoIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.addAction(R.drawable.water, "Drank", updatePendingIntent);
        notifyBuilder.addAction(R.drawable.water, "Snooze", updateNoPendingIntent);
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        mNotifyManager.notify(100, notifyBuilder.build());
    }

    public void requestActivityUpdatesButtonHandler() {
        Task<Void> task = mActivityRecognitionClient.requestActivityUpdates(
                Constants.DETECTION_INTERVAL_IN_MILLISECONDS,
                mPendingIntent);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void result) {
                Toast.makeText(getApplicationContext(),
                        "Successfully requested activity updates",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Requesting activity updates failed to start",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public void removeActivityUpdatesButtonHandler() {
        Task<Void> task = mActivityRecognitionClient.removeActivityUpdates(
                mPendingIntent);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void result) {
                Toast.makeText(getApplicationContext(),
                        "Removed activity updates successfully!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to remove activity updates!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceRunning = false;
        removeActivityUpdatesButtonHandler();
    }
}
