package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static java.text.DateFormat.getTimeInstance;

public class JournalActivity extends AppCompatActivity {

    private static final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 010;
    private String TAG = MainActivity.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;
    FitnessOptions fitnessOptions;

    FloatingActionButton floatingActionButton;
    Location mLastLocation;
    FusedLocationProviderClient mFusedLocationClient;

    TimePickerDialog timePickerDialog;
    int CalendarHourStart, CalendarMinuteStart, CalendarHourEnd, CalendarMinuteEnd;
    ActivityDatabase.Activity activity;
    Date startDateCalendar, endDateCalendar;

    ListView listView;
    List<String> activitys, sDate, eDate;
    List<Integer> distances;

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    LocationCallback mLocationCallback;

    private static String toActivityString(int activity, Context context) {
        String label = context.getString(R.string.activity_unknown);
        switch (activity) {
            case DetectedActivity.IN_VEHICLE: {
                label = context.getString(R.string.activity_in_vehicle);
                break;
            }
            case DetectedActivity.ON_BICYCLE: {
                label = context.getString(R.string.activity_on_bicycle);
                break;
            }
            case DetectedActivity.ON_FOOT: {
                label = context.getString(R.string.activity_on_foot);
                break;
            }
            case DetectedActivity.RUNNING: {
                label = context.getString(R.string.activity_running);
                break;
            }
            case DetectedActivity.STILL: {
                label = context.getString(R.string.activity_still);
                break;
            }
            case DetectedActivity.TILTING: {
                label = context.getString(R.string.activity_tilting);
                break;
            }
            case DetectedActivity.WALKING: {
                label = context.getString(R.string.activity_walking);
                break;
            }
            case DetectedActivity.UNKNOWN: {
                label = context.getString(R.string.activity_unknown);
                break;
            }
        }
        return label;
    }

    private static String toTransitionType(int transitionType) {
        switch (transitionType) {
            case ActivityTransition.ACTIVITY_TRANSITION_ENTER:
                return "ENTER";
            case ActivityTransition.ACTIVITY_TRANSITION_EXIT:
                return "EXIT";
            default:
                return "UNKNOWN";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_journal);

        floatingActionButton = findViewById(R.id.addActivityJournalButton);

        //Navigation Bar Related
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_journal);
        bottomNavigationView.setSelectedItemId(R.id.journalNavigationButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeNavigationButton:
                        startActivity(new Intent(JournalActivity.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.yogaNavigationButton:
                        startActivity(new Intent(JournalActivity.this, YogaActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.foodNavigationButton:
                        startActivity(new Intent(JournalActivity.this, FoodActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.journalNavigationButton:
                        return true;
                    case R.id.coronaNavigationButton:
                        startActivity(new Intent(JournalActivity.this, CoronaCautionActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return true;
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.BROADCAST_DETECTED_ACTIVITY)) {
                    int type = intent.getIntExtra("type", -1);
                    int confidence = intent.getIntExtra("confidence", 0);
                    String username = intent.getStringExtra("user");
                    //todo handle the current user detail which is returned show the journal or add data to that particular user
                    handleUserActivity(type, confidence, username);
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(JournalActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 400);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && ActivityCompat.checkSelfPermission(JournalActivity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 500);
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.d("Location", locationResult.getLastLocation().toString());
            }
        };
        listView = findViewById(R.id.exercisesListActivity);
        activitys = new ArrayList<>();
        distances = new ArrayList<>();
        sDate = new ArrayList<>();
        eDate = new ArrayList<>();
        if (ActivityDatabase.getDatabase(this) != null) {

           List<ActivityDatabase.Activity> activityList = ActivityDatabase.getDatabase(this);
           for (ActivityDatabase.Activity activity:activityList){
               activitys.add(activity.getName());
               distances.add(activity.getDistance());
               sDate.add(new SimpleDateFormat("dd MMM,yy hh:mm a").format(activity.getStartTime()));
               eDate.add(new SimpleDateFormat("dd MMM,yy hh:mm a").format(activity.getEndTime()));
           }
        }

        ActivityListAdapter activityListAdapter = new ActivityListAdapter(this, activitys, sDate,eDate,distances);
        listView.setAdapter(activityListAdapter);
        activityListAdapter.notifyDataSetChanged();
        ;
        /*fitnessOptions = FitnessOptions.builder().addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_WORKOUT_EXERCISE, FitnessOptions.ACCESS_READ).addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ).build();
        GoogleSignInAccount account = GoogleSignIn.getAccountForExtension(this, fitnessOptions);
        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            Log.d("GoogleFit", "Requesting permission");
            GoogleSignIn.requestPermissions(
                    this, // your activity
                    GOOGLE_FIT_PERMISSIONS_REQUEST_CODE, // e.g. 1
                    account,
                    fitnessOptions);
        } else {
            accessGoogleFit();
        }*/

    }

    private void handleUserActivity(int type, int confidence, String username) {

        Log.e(TAG, "User activity: " + toActivityString(type, JournalActivity.this) + ", Confidence: " + confidence);

        if (confidence > Constants.CONFIDENCE) {
            Log.i(username + "Activity", toActivityString(type, JournalActivity.this));
            Log.i("confidence", String.valueOf(confidence));
            String activityRecieved = toActivityString(type, JournalActivity.this);
            if (activityRecieved.matches(JournalActivity.this.getString(R.string.activity_running)) || activityRecieved.matches(JournalActivity.this.getString(R.string.activity_walking)) || activityRecieved.matches(JournalActivity.this.getString(R.string.activity_on_bicycle))) {
                if (ActivityCompat.checkSelfPermission(JournalActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && ActivityCompat.checkSelfPermission(JournalActivity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 300);
                } else {
                    //Get location
                    startCheckingLocation();
                }
            }
        }
    }

    private void startCheckingLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates
                (getLocationRequest(), mLocationCallback,
                        null /* Looper */);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && ActivityCompat.checkSelfPermission(JournalActivity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 300);
                } else {
                    //Get location
                    startCheckingLocation();
                }
            } else {
                ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
            }
        } else if (requestCode == 300) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Get location
                startCheckingLocation();
            } else {
                ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 300);
            }
        }

        if (requestCode == 400) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && ActivityCompat.checkSelfPermission(JournalActivity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 500);
                }
            } else {
                ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 400);
            }
        } else if (requestCode == 500) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permissions granted
            } else {
                ActivityCompat.requestPermissions(JournalActivity.this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 500);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(Constants.BROADCAST_DETECTED_ACTIVITY));
        activitys = new ArrayList<>();
        distances = new ArrayList<>();
        sDate = new ArrayList<>();
        eDate = new ArrayList<>();
        if (ActivityDatabase.getDatabase(this) != null) {

            List<ActivityDatabase.Activity> activityList = ActivityDatabase.getDatabase(this);
            for (ActivityDatabase.Activity activity:activityList){
                activitys.add(activity.getName());
                distances.add(activity.getDistance());
                sDate.add(new SimpleDateFormat("dd MMM,yy hh:mm a").format(activity.getStartTime()));
                eDate.add(new SimpleDateFormat("dd MMM,yy hh:mm a").format(activity.getEndTime()));
                Log.d("NEW", activity.getStartTime().toString());
            }
        }

        ActivityListAdapter activityListAdapter = new ActivityListAdapter(this, activitys, sDate,eDate,distances);
        listView.setAdapter(activityListAdapter);
        activityListAdapter.notifyDataSetChanged();
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
                Log.d("GoogleFit", "Permission accepted");
                accessGoogleFit();
            }
        }
    }*/

    /*private void accessGoogleFit() {
        Log.d("GOOGLEFIT", "WE have permissions");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.YEAR, -1);
        long startTime = cal.getTimeInMillis();

        DataReadRequest readRequest = new DataReadRequest.Builder()
                .read(DataType.TYPE_STEP_COUNT_DELTA)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .bucketByTime(1, TimeUnit.DAYS)
                .build();

        GoogleSignInAccount account = GoogleSignIn
                .getAccountForExtension(this, fitnessOptions);

        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this)).readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA).addOnCompleteListener(new OnCompleteListener<DataSet>() {
            @Override
            public void onComplete(@NonNull Task<DataSet> task) {
                if (task.getResult().getDataPoints().size() > 0) {
                    Log.e("History", "Number of buckets: " + task.getResult().getDataPoints().size());
                    for (DataPoint dataPoint : task.getResult().getDataPoints()) {

                            dumpDataSet(dataPoint, TAG);

                    }
                }
            }
        });

    }

    private static void dumpDataSet(DataPoint dp, String TAG) {
        DateFormat dateFormat = getTimeInstance();

            Log.i(TAG, "Data point:");
            Log.i(TAG, "\tType: " + dp.getDataType().getName());
            Log.i(TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.DAYS)));
            Log.i(TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
            for (Field field : dp.getDataType().getFields()) {
                Log.i(TAG, "\tField: " + field.getName() + " Value: " + dp.getValue(field));
            }

    }*/

    public void addExercise(View view) {
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select the Start Date");
        builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        CalendarConstraints.Builder builder1 = new CalendarConstraints.Builder();
        builder1.setEnd(MaterialDatePicker.thisMonthInUtcMilliseconds());
        builder.setCalendarConstraints(builder1.build());
        MaterialDatePicker datePicker = builder.build();

        MaterialDatePicker.Builder builderEnd = MaterialDatePicker.Builder.datePicker();
        builderEnd.setTitleText("Select the End Date");
        builderEnd.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        CalendarConstraints.Builder builderEnd1 = new CalendarConstraints.Builder();
        builderEnd1.setEnd(MaterialDatePicker.thisMonthInUtcMilliseconds());
        builderEnd.setCalendarConstraints(builder1.build());
        MaterialDatePicker datePickerEnd = builderEnd.build();
        //TIme Picker


        MaterialCardView materialCardView = findViewById(R.id.addExerciseCardView);
        if (materialCardView.getVisibility() == View.VISIBLE) {
            materialCardView.setVisibility(View.GONE);
        } else {
            activity = new ActivityDatabase.Activity();
            materialCardView.setVisibility(View.VISIBLE);
            AutoCompleteTextView autoCompleteTextView = findViewById(R.id.exerciseAutoCompleteTextView);
            ImageView imageViewSD, imageViewED;
            imageViewED = findViewById(R.id.imageView38);
            imageViewED.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Button", "Pressed");
                    datePickerEnd.show(getSupportFragmentManager(), "DATE_PICKER");
                    datePickerEnd.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            TimeZone timeZoneUTC = TimeZone.getDefault();
                            // It will be negative, so that's the -1
                            int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                            // Create a date format, then a date object with our offset
                            SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                            endDateCalendar = new Date((long) selection + offsetFromUTC);
                            Calendar calendar = Calendar.getInstance();
                            CalendarHourEnd = calendar.get(Calendar.HOUR_OF_DAY);

                            CalendarMinuteEnd = calendar.get(Calendar.MINUTE);

                            timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                    int Hour = 0;
                                    boolean isAM = true;
                                    if (hourOfDay >= 12) {
                                        isAM = false;
                                        switch (hourOfDay) {
                                            case 12:
                                                if (minute > 00) {
                                                    isAM = false;
                                                } else {
                                                    isAM = true;
                                                }
                                            case 13:
                                                Hour = 1;
                                                break;

                                            case 14:
                                                Hour = 2;
                                                break;

                                            case 15:
                                                Hour = 3;
                                                break;

                                            case 16:
                                                Hour = 4;
                                                break;

                                            case 17:
                                                Hour = 5;
                                                break;

                                            case 18:
                                                Hour = 6;
                                                break;

                                            case 19:
                                                Hour = 7;
                                                break;

                                            case 20:
                                                Hour = 8;
                                                break;

                                            case 21:
                                                Hour = 9;
                                                break;

                                            case 22:
                                                Hour = 10;
                                                break;

                                            case 23:
                                                Hour = 11;
                                                break;

                                            case 24:
                                                Hour = 12;
                                                break;
                                        }

                                    } else {

                                        Hour = hourOfDay;
                                    }

                                    String SelectedTime = "Selected Time is " + Hour + " : " + minute;
                                    if (isAM) {
                                        SelectedTime += "AM";
                                    } else {
                                        SelectedTime += "PM";
                                    }
                                    if (endDateCalendar == null) {
                                        Log.d("HelloDate", "Null");
                                    }
                                    Calendar calendar = Calendar.getInstance();
        /*
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.DATE, startDateCalendar.getDate());
        calendar.set(Calendar.DAY_OF_WEEK, startDateCalendar.getDay());
        calendar.set(Calendar.MONTH, startDateCalendar.getMonth());
        calendar.set(Calendar.YEAR, startDateCalendar.getYear());*/
                                    endDateCalendar.setHours(hourOfDay);
                                    Log.d("DateS", endDateCalendar.toString());
                                    Log.d("DateS", String.valueOf(endDateCalendar.getDate()));
                                    endDateCalendar.setMinutes(minute);
                                    activity.setEndTime(endDateCalendar);

                                    TextView en = findViewById(R.id.textView38);
                                    en.setText(new SimpleDateFormat("dd MMM YYYY hh:mm:ss a").format(endDateCalendar));
                                }
                            }, CalendarHourEnd, CalendarMinuteEnd, false);
                            timePickerDialog.setAccentColor("#f86734");
                            timePickerDialog.setCancelColor("#FFFFFF");
                            timePickerDialog.setOkColor("#FFFFFF");

                            timePickerDialog.show(getSupportFragmentManager(), "Material Design TimePicker Dialog");

                        }
                    });
                }
            });
            imageViewSD = findViewById(R.id.imageView37);
            imageViewSD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Button", "Pressed");
                    datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
                    datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            TimeZone timeZoneUTC = TimeZone.getDefault();
                            // It will be negative, so that's the -1
                            int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                            // Create a date format, then a date object with our offset
                            SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                            startDateCalendar = new Date((long) selection + offsetFromUTC);
                            Calendar calendar = Calendar.getInstance();
                            CalendarHourStart = calendar.get(Calendar.HOUR_OF_DAY);

                            CalendarMinuteStart = calendar.get(Calendar.MINUTE);

                            timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                    int Hour = 0;
                                    boolean isAM = true;
                                    if (hourOfDay >= 12) {
                                        isAM = false;
                                        switch (hourOfDay) {
                                            case 12:
                                                if (minute > 00) {
                                                    isAM = false;
                                                } else {
                                                    isAM = true;
                                                }
                                            case 13:
                                                Hour = 1;
                                                break;

                                            case 14:
                                                Hour = 2;
                                                break;

                                            case 15:
                                                Hour = 3;
                                                break;

                                            case 16:
                                                Hour = 4;
                                                break;

                                            case 17:
                                                Hour = 5;
                                                break;

                                            case 18:
                                                Hour = 6;
                                                break;

                                            case 19:
                                                Hour = 7;
                                                break;

                                            case 20:
                                                Hour = 8;
                                                break;

                                            case 21:
                                                Hour = 9;
                                                break;

                                            case 22:
                                                Hour = 10;
                                                break;

                                            case 23:
                                                Hour = 11;
                                                break;

                                            case 24:
                                                Hour = 12;
                                                break;
                                        }

                                    } else {

                                        Hour = hourOfDay;
                                    }

                                    String SelectedTime = "Selected Time is " + Hour + " : " + minute;
                                    if (isAM) {
                                        SelectedTime += "AM";
                                    } else {
                                        SelectedTime += "PM";
                                    }
                                    if (startDateCalendar == null) {
                                        Log.d("HelloDate", "Null");
                                    }
                                    Calendar calendar = Calendar.getInstance();
                                    startDateCalendar.setHours(hourOfDay);
                                    startDateCalendar.setMinutes(minute);
                                    activity.setStartTime(startDateCalendar);

                                    Log.d("DateS", startDateCalendar.toString());
                                    Log.d("DateS", String.valueOf(startDateCalendar.getDate()));
                                    TextView st = findViewById(R.id.textView39);
                                    st.setText(new SimpleDateFormat("dd MMM YYYY hh:mm:ss a").format(startDateCalendar));
                                }
                            }, CalendarHourStart, CalendarMinuteStart, false);
                            timePickerDialog.setAccentColor("#f86734");
                            timePickerDialog.setCancelColor("#FFFFFF");
                            timePickerDialog.setOkColor("#FFFFFF");

                            timePickerDialog.show(getSupportFragmentManager(), "Material Design TimePicker Dialog");

                        }
                    });
                }
            });
            String[] exercises = new String[]{"Walking", "Running", "Cycling","Exercise"};
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, exercises);
            autoCompleteTextView.setAdapter(arrayAdapter);
            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    activity.setName(exercises[i]);
                }
            });
            /* TODO transfer them to on add button - TextInputEditText editText = findViewById(R.id.distanceEditTextJournal);
            if (editText.getText().toString()!= null & !(editText.getText().toString().matches(""))){
                activity.setDistance(Integer.parseInt(editText.getText().toString()));
            }
            */

        }

    }

    public void addActivityButtonOnClick(View view) {
        TextInputEditText editText = findViewById(R.id.distanceEditTextJournal);
        Log.d("DateErro", startDateCalendar.toString());
        if (editText.getText().toString() != null & !(editText.getText().toString().matches(""))) {
            activity.setDistance(Integer.parseInt(editText.getText().toString()));
            activity.setStartTime(startDateCalendar);
            activity.setEndTime(endDateCalendar);
            Log.d("distanceneter", String.valueOf(activity.getDistance()));
            ActivityDatabase database = new ActivityDatabase(JournalActivity.this, activity);
        }else{
            ActivityDatabase database = new ActivityDatabase(JournalActivity.this, activity);
        }

        MaterialCardView materialCardView = findViewById(R.id.addExerciseCardView);
        materialCardView.setVisibility(View.GONE);
    }

    public void hideView(View view) {
        MaterialCardView materialCardView = findViewById(R.id.addExerciseCardView);
        materialCardView.setVisibility(View.GONE);
    }

    //
    //
}