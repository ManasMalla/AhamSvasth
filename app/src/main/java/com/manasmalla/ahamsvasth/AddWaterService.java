package com.manasmalla.ahamsvasth;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.manasmalla.ahamsvasth.AhamSvasthaApp.PRIMARY_CHANNEL_ID;
import static com.manasmalla.ahamsvasth.BackgroundDetectedActivitiesService.NOTIFICATION_ID_WATER;

public class AddWaterService extends BroadcastReceiver {
    private NotificationCompat.Builder getNotificationBuilder(Context context) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(context,
                NOTIFICATION_ID_WATER, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setContentTitle("Get Hydrated!")
                .setContentText(context.getString(R.string.waterNotificationText))
                .setSmallIcon(R.drawable.ic_water)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        return notifyBuilder;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId = intent.getIntExtra("id", 0);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(notificationId);
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        editor.putInt("Water"+ AhamSvasthaUser.getCurrentUsername(context) + simpleDateFormat.format(Calendar.getInstance().getTime()), sharedPreferences.getInt("Water" + AhamSvasthaUser.getCurrentUsername(context) + simpleDateFormat.format(Calendar.getInstance().getTime()), 0) + 1).apply();
        Toast.makeText(context, "Very Good! You drank " + sharedPreferences.getInt("Water" + AhamSvasthaUser.getCurrentUsername(context) + simpleDateFormat.format(Calendar.getInstance().getTime()), 0) + " glasses :)", Toast.LENGTH_SHORT).show();
        if (MainActivity.isAppForeground){
            context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}
