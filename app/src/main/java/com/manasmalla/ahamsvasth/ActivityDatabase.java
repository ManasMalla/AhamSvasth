package com.manasmalla.ahamsvasth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActivityDatabase {

    public static class Activity {
        private String name;
        private Date startTime, endTime;
        private int distance;

        public void setName(String name) {
            this.name = name;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public Date getStartTime() {
            return startTime;
        }

        public int getDistance() {
            return distance;
        }

        public String getName() {
            return name;
        }
    }

    public ActivityDatabase(Context context, Activity activity) {
        setDatabase(context, activity);
    }

    public static List<Activity> getDatabase(Context context) {
        String currentuser = AhamSvasthaUser.getCurrentUsername(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);

        Set<String> ids = sharedPreferences.getStringSet("idsActivity", null);
        List<Activity> activityList = new ArrayList<>();
        if (ids != null) {
            for (String id : ids) {
                Activity activity = new Activity();
                activity.setName(sharedPreferences.getString("activity" + id + currentuser, null));
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy hh mm ss a EEEE");
                    activity.setStartTime(simpleDateFormat.parse(sharedPreferences.getString("starttime" + id + currentuser, null)));
                    activity.setEndTime(simpleDateFormat.parse(sharedPreferences.getString("endtime" + id + currentuser, null)));
                    Log.d("SP", simpleDateFormat.format(simpleDateFormat.parse(simpleDateFormat.format(Calendar.getInstance().getTime()))));
                    if (sharedPreferences.getInt("distance" + id + currentuser, -1) != -1) {
                        activity.setDistance(sharedPreferences.getInt("distance" + id + currentuser, -1));
                    }else{
                        activity.setDistance(0);
                    }
                    activityList.add(activity);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.d("DistanceSPE", String.valueOf(sharedPreferences.getInt("distance" + id + currentuser, -1)));

            }
            return activityList;
        }
        return null;
    }

    public void setDatabase(Context context, Activity activity) {
        String currentuser = AhamSvasthaUser.getCurrentUsername(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy hh mm ss a EEEE");
        Set<String> ids = sharedPreferences.getStringSet("idsActivity", null);
        if (ids == null) {
            ids = new HashSet<>();
        }
        long id = Calendar.getInstance().getTime().getTime();
        ids.add(String.valueOf(id));
        sharedPreferences.edit().putString("activity" + id + currentuser, activity.getName()).apply();
        sharedPreferences.edit().putString("starttime" + id + currentuser, simpleDateFormat.format(activity.getEndTime())).apply();
        sharedPreferences.edit().putString("endtime" + id + currentuser, simpleDateFormat.format(activity.getStartTime())).apply();
        Log.d("DistanceSP", String.valueOf(activity.getDistance()));
        if (activity.getDistance() != 0) {
            sharedPreferences.edit().putInt("distance" + id + currentuser, activity.getDistance()).apply();
        } else {
            sharedPreferences.edit().putInt("distance" + id + currentuser, -1).apply();
        }
        sharedPreferences.edit().putStringSet("idsActivity", ids).apply();
    }
}
