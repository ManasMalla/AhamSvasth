package com.manasmalla.ahamsvasth;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.os.Handler;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.manasmalla.ahamsvasth.AhamSvasthaApp.PRIMARY_CHANNEL_ID;
import static com.manasmalla.ahamsvasth.BackgroundDetectedActivitiesService.NOTIFICATION_ID_WATER;

public class SnoozeService extends BroadcastReceiver {
    Context context;
    NotificationManager mNotifyManager;
    private NotificationCompat.Builder getNotificationBuilder(){
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
        this.context = context;
        int notificationId = intent.getIntExtra("id", 0);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(notificationId);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent updateIntent = new Intent(context, AddWaterService.class).putExtra("id", 100);
                PendingIntent updatePendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID_WATER, updateIntent, PendingIntent.FLAG_ONE_SHOT);
                Intent updateNoIntent = new Intent(context, SnoozeService.class).putExtra("id", 100);
                PendingIntent updateNoPendingIntent = PendingIntent.getBroadcast(context, 1, updateNoIntent, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
                notifyBuilder.addAction(R.drawable.water, "Drank", updatePendingIntent);
                notifyBuilder.addAction(R.drawable.water, "Snooze", updateNoPendingIntent);
                mNotifyManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                mNotifyManager.notify(100, notifyBuilder.build());
            }
        }, 15 * 60 *1000);
        Toast.makeText(context, "Snoozed for 15 min :(", Toast.LENGTH_SHORT).show();

    }
}
