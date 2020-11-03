package com.manasmalla.ahamsvasth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.twitter.sdk.android.core.models.User;

import java.util.HashMap;

public class UserDataQuestionsActivity extends AppCompatActivity implements View.OnKeyListener {

    private MyViewPagerAdapter myViewPagerAdapter;
    ViewPager viewPager;
    private int[] layouts;
    AhamSvasthaUser user;
    int buttons[];
    int bars[];
    View currentView;
    int currentViewPosition;
    HashMap<String, Boolean> disease;

    TextInputEditText ageEditText, heightEditText, weightEditText;
    AutoCompleteTextView unitsView;
    ArrayAdapter<String> adapter;
    AutoCompleteTextView wUnitsView;
    ArrayAdapter<String> wAdapter;
    String unit = " cm", wUnit = " kg";
    ImageView diabetesTick, thyroidTick, cholestrolTick, bpTick, obesityTick;
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_questions);

        user = new AhamSvasthaUser();
        user.gender = null;
        user.diseases = null;
        user.age = 0;
        user.height =0;
        user.weight = 0;
        user.activityUser = null;


        question = findViewById(R.id.textView);

        final String[] units = new String[]{"cm", "m", "in", "feet"};
        adapter = new ArrayAdapter<String>(this, R.layout.dropdown_menu_popup_item, units);
        adapter.notifyDataSetChanged();

        final String[] wUnits = new String[]{"kg", "lbs"};
        wAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_menu_popup_item, wUnits);
        wAdapter.notifyDataSetChanged();

        disease = new HashMap<>();
        disease.put("Diabetes", false);
        disease.put("Thyroid", false);
        disease.put("Cholestrol", false);
        disease.put("BP", false);
        disease.put("Obesity", false);

        viewPager = findViewById(R.id.viewPager);
        layouts = new int[]{
                R.layout.question_one_layout,
                R.layout.question_two_layout,
                R.layout.question_three_layout,
                R.layout.question_four_layout,
                R.layout.question_five_layout,
                R.layout.question_six_layout};
        buttons = new int[]{
                R.id.genderQuestionImageView,
                R.id.ageQuestionImageView,
                R.id.hieghtQuestionImageView,
                R.id.weightQuestionImageView,
                R.id.health_historyQuestionImageView,
                R.id.activityQuestionImageView
        };
        bars = new int[]{
                R.id.question1BarView,
                R.id.question2BarView,
                R.id.question3BarView,
                R.id.question4BarView,
                R.id.question5BarView
        };
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private static final float thresholdOffset = 0.5f;
            private static final int thresholdOffsetPixels = 1;
            private boolean scrollStarted, checkDirection;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("NUmber", String.valueOf(position));

               try {
                   if (position == 1 && user.gender == null){
                       //Log.d("Gender", user.gender);
                       viewPager.setCurrentItem(0);
                   }else if ((position == 2) && user.age == 0){
                       if (ageEditText.getText().toString() != null) {
                           user.age = Integer.parseInt(ageEditText.getText().toString());
                       }
                       viewPager.setCurrentItem(1);
                   }else if ((position == 3) && user.height == 0){
                       if (heightEditText.getText().toString() != null) {
                           user.height = Integer.parseInt(heightEditText.getText().toString());
                       }
                       viewPager.setCurrentItem(2);
                   }else if ((position == 4) && user.weight == 0){
                       if (weightEditText.getText().toString() != null) {
                           user.weight = Integer.parseInt(weightEditText.getText().toString());
                       }
                       viewPager.setCurrentItem(3);
                   }else if ((position == 5) && user.diseases == null){
                       user.diseases = null;
                       if ((position == 5) && user.diseases == null){
                           user.diseases = null;
                           Toast.makeText(UserDataQuestionsActivity.this, "Either select a disease you have or scroll down to click the next button!", Toast.LENGTH_SHORT).show();
                           viewPager.setCurrentItem(4);
                       }
                       viewPager.setCurrentItem(4);
                   }else if ((position == 6) && user.activityUser == null){
                       user.activityUser = null;
                       if ((position == 6) && user.activityUser == null){
                           user.activityUser = null;
                           viewPager.setCurrentItem(5);
                       }
                       viewPager.setCurrentItem(5);
                   }
               }catch (NumberFormatException e){
                   if ((position == 3) && user.height == 0){
                       viewPager.setCurrentItem(2);
                   }else if ((position == 4) && user.weight == 0){
                       viewPager.setCurrentItem(3);
                   }else if ((position == 2) && user.age == 0){
                       viewPager.setCurrentItem(1);
                   }
                   Toast.makeText(UserDataQuestionsActivity.this, "Invalid Input! Please check again!", Toast.LENGTH_SHORT).show();
               }catch (Exception e){
                   if ((position == 3) && user.height == 0){
                       viewPager.setCurrentItem(2);
                   }else if ((position == 4) && user.weight == 0){
                       viewPager.setCurrentItem(3);
                   }else if ((position == 2) && user.age == 0){
                       viewPager.setCurrentItem(1);
                   }
                   Toast.makeText(UserDataQuestionsActivity.this, "Invalid Input! Please check again!", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("Page", String.valueOf(position));
                if (position == 0 && findViewById(R.id.boyImageView)!=null) {
                    question.setText("Please select your gender!");
                } else if (position == 1 && findViewById(R.id.birthdayAgeImageView) != null) {
                    ImageView birthdayImageView = findViewById(R.id.birthdayAgeImageView);
                    question.setText("Please enter your age!");
                    if (user.gender == "Boy") {
                        birthdayImageView.setImageResource(R.drawable.birthage_boy);
                    } else {
                        birthdayImageView.setImageResource(R.drawable.birthage_girl);
                    }
                    ageEditText = findViewById(R.id.ageEditText);
                    ageEditText.setOnKeyListener(UserDataQuestionsActivity.this::onKey);
                } else if (position == 3 && findViewById(R.id.weightImageView) != null) {
                    ImageView weightImageView = findViewById(R.id.weightImageView);
                    question.setText("Please enter your weight!");
                    if (user.gender == "Boy") {
                        weightImageView.setImageResource(R.drawable.weight_boy);
                    } else {
                        weightImageView.setImageResource(R.drawable.weight_girl);
                    }
                } else if (position == 2 && findViewById(R.id.heightImageView) != null) {
                    question.setText("Please enter your height!");
                } else if (position == 4 && findViewById(R.id.diabetesImageView) != null) {
                    question.setText("Please select all the diseases you have!");
                } else if (position == 5 && findViewById(R.id.restCardView) != null) {
                    question.setText("Please select how your day goes on!");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    public void goToNextPage() {
        int current = getItem(+1);
        ImageView button = findViewById(buttons[current]);
        View bar = findViewById(bars[current - 1]);
        button.setAlpha(1f);
        bar.setAlpha(1f);
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);
            currentViewPosition = current;
        } else {
            Toast.makeText(this, "Pages Over", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.i("finished", "yes" + currentViewPosition);
            if (currentViewPosition == 1) {
                user.setAge(Integer.parseInt(ageEditText.getText().toString()));
                goToNextPage();
            } else if (v.getId() == R.id.heightEditText) {
                user.setHeight(Integer.parseInt(heightEditText.getText().toString()));
                user.setUnit(unit);
                goToNextPage();
            } else if (v.getId() == R.id.weightEditText) {
                user.setWeight(Integer.parseInt(weightEditText.getText().toString()));
                user.setWUnit(wUnit);
                goToNextPage();
            }
        }
        return false;
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            currentView = view;
            Log.i("psitio", String.valueOf(position));
            if (position == 0) {
                ImageView boyImageView = view.findViewById(R.id.boyImageView);
                ImageView girlImageView = view.findViewById(R.id.girlImageView);
                question.setText("Please select your gender!");
                boyImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("Gender", "Boy");
                        user.setGender("Boy");
                        goToNextPage();
                    }
                });
                girlImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("Gender", "Girl");
                        user.setGender("Girl");
                        goToNextPage();
                    }
                });
            }
            if (view.findViewById(R.id.heightUnitsTextView) != null) {

                ImageView heightImageView = currentView.findViewById(R.id.heightImageView);
                if (user.gender == "Boy") {
                    heightImageView.setImageResource(R.drawable.height_boy);
                } else {
                    heightImageView.setImageResource(R.drawable.height_girl);
                }

                heightEditText = currentView.findViewById(R.id.heightEditText);
                heightEditText.setOnKeyListener(UserDataQuestionsActivity.this::onKey);
                unitsView = view.findViewById(R.id.heightUnitsTextView);
                unitsView.setAdapter(adapter);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    unitsView.setText("cm", false);
                }
                unitsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            unit = " cm";
                        } else if (position == 1) {
                            unit = " m";
                        } else if (position == 2) {
                            unit = " inches";
                        } else if (position == 3) {
                            unit = " feet";
                        }
                    }
                });
                adapter.notifyDataSetChanged();
            } else if (view.findViewById(R.id.weightUnitsTextView) != null) {
                weightEditText = currentView.findViewById(R.id.weightEditText);
                weightEditText.setOnKeyListener(UserDataQuestionsActivity.this::onKey);
                wUnitsView = view.findViewById(R.id.weightUnitsTextView);
                wUnitsView.setAdapter(wAdapter);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    wUnitsView.setText("kg", false);
                }
                wUnitsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            wUnit = " kg";
                        } else if (position == 1) {
                            wUnit = " lbs";
                        }
                    }
                });
                wAdapter.notifyDataSetChanged();
            } else if (currentView.findViewById(R.id.diabetesImageView) != null) {

                ImageView diabetesIV = currentView.findViewById(R.id.diabetesImageView);
                TextView diabetesTV = currentView.findViewById(R.id.diabetesTextView);
                ImageView thyroidIV = currentView.findViewById(R.id.thyroidImageView);
                TextView thyroidTV = currentView.findViewById(R.id.thyroidTextView);
                ImageView cholestrolIV = currentView.findViewById(R.id.cholestrolImageView);
                TextView cholestrolTV = currentView.findViewById(R.id.cholestrolTextView);
                ImageView bpIV = currentView.findViewById(R.id.bloodPressureImageView);
                TextView bpTV = currentView.findViewById(R.id.bloodPressureTextView);
                ImageView obesityIV = currentView.findViewById(R.id.obesityImageView);
                TextView obesityTV = currentView.findViewById(R.id.obesityTextView);
                CircleView circleContinue = currentView.findViewById(R.id.circleContinueButton);
                diabetesTick = currentView.findViewById(R.id.diabetesTickImageView);
                thyroidTick = currentView.findViewById(R.id.thyroidTickImageView);
                cholestrolTick = currentView.findViewById(R.id.cholestrolTickImageView);
                bpTick = currentView.findViewById(R.id.bpTickImageView);
                obesityTick = currentView.findViewById(R.id.obesityTickImageView);
                diabetesTV.setOnClickListener(setDiabetesTick);
                diabetesIV.setOnClickListener(setDiabetesTick);
                thyroidIV.setOnClickListener(setThyroidTick);
                thyroidTV.setOnClickListener(setThyroidTick);
                cholestrolIV.setOnClickListener(setCholestrolTick);
                cholestrolTV.setOnClickListener(setCholestrolTick);
                bpIV.setOnClickListener(setBPTick);
                bpTV.setOnClickListener(setBPTick);
                obesityIV.setOnClickListener(setObesityTick);
                obesityTV.setOnClickListener(setObesityTick);
                circleContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.setDiseases(disease);
                        Log.i("Diseases", disease.toString());
                        goToNextPage();
                    }
                });
            } else if (currentView.findViewById(R.id.restCardView) != null) {

                MaterialCardView sedentary = currentView.findViewById(R.id.restCardView);
                MaterialCardView medium = currentView.findViewById(R.id.mediumActivityCardView);
                MaterialCardView hectic = currentView.findViewById(R.id.activeCardView);
                Intent intentMain = new Intent(getApplicationContext(), IntroductionActivity.class);
                sedentary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.setUserActivity("Sedentary");
                        Log.i("About User", user.getUserData());

                        Log.i("Diseases", user.getDiseases());
                        AhamSvasthaUser.saveAhamSvasthaUser(user, UserDataQuestionsActivity.this, AhamSvasthaUser.getCurrentUsername(UserDataQuestionsActivity.this));
                        startActivity(intentMain);
                    }
                });
                medium.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.setUserActivity("Active");
                        Log.i("About User", user.getUserData());
                        AhamSvasthaUser.saveAhamSvasthaUser(user, UserDataQuestionsActivity.this, AhamSvasthaUser.getCurrentUsername(UserDataQuestionsActivity.this));
                        startActivity(intentMain);
                    }
                });
                hectic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.setUserActivity("Hectic");
                        Log.i("About User", user.getUserData());
                        AhamSvasthaUser.saveAhamSvasthaUser(user, UserDataQuestionsActivity.this, AhamSvasthaUser.getCurrentUsername(UserDataQuestionsActivity.this));
                        startActivity(intentMain);
                    }
                });
            }
            container.addView(view);
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

    View.OnClickListener setDiabetesTick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (disease.get("Diabetes") != null && disease.get("Diabetes")) {
                disease.put("Diabetes", false);
                diabetesTick.setVisibility(View.GONE);
            } else {
                disease.put("Diabetes", true);
                diabetesTick.setVisibility(View.VISIBLE);
            }
        }
    };
    View.OnClickListener setThyroidTick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (disease.get("Thyroid") != null && disease.get("Thyroid")) {
                disease.put("Thyroid", false);
                thyroidTick.setVisibility(View.GONE);
            } else {
                disease.put("Thyroid", true);
                thyroidTick.setVisibility(View.VISIBLE);
            }
        }
    };
    View.OnClickListener setCholestrolTick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (disease.get("Cholestrol") != null && disease.get("Cholestrol")) {
                disease.put("Cholestrol", false);
                cholestrolTick.setVisibility(View.GONE);
            } else {
                disease.put("Cholestrol", true);
                cholestrolTick.setVisibility(View.VISIBLE);
            }
        }
    };
    View.OnClickListener setBPTick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (disease.get("BP") != null && disease.get("BP")) {
                disease.put("BP", false);
                bpTick.setVisibility(View.GONE);
            } else {
                disease.put("BP", true);
                bpTick.setVisibility(View.VISIBLE);
            }
        }
    };
    View.OnClickListener setObesityTick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (disease.get("Obesity") != null && disease.get("Obesity")) {
                disease.put("Obesity", false);
                obesityTick.setVisibility(View.GONE);
            } else {
                disease.put("Obesity", true);
                obesityTick.setVisibility(View.VISIBLE);
            }
        }
    };

}