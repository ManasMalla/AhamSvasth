package com.manasmalla.ahamsvasth;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.function.Function;

public class AhamSvasthaUser {
    String gender = "";
    int age = 0;
    float height, weight;
    String unit, wUnit;
    HashMap<String, Boolean> diseases;
    String activityUser;

    public void setGender(String gender){
        this.gender = gender;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setHeight(float height){
        this.height = height;
    }
    public void setUnit(String unit){
        this.unit = unit;
    }
    public String getHeight(){
        return String.valueOf(this.height) + unit;
    }
    public void setWeight(float weight){
        this.weight = weight;
    }
    public void setWUnit(String wunit){
        this.wUnit = wunit;
    }
    public String getWeight(){
        return String.valueOf(this.weight) + wUnit;
    }
    public void setDiseases(HashMap<String, Boolean> diseases){
        this.diseases = diseases;
    }
    public String getDiseases(){
        return this.diseases.toString();
    }
    public void setUserActivity(String activity){
        this.activityUser = activity;
    }
    public String getUserData(){
        return "Gender: " + gender + "; Age: " + age + "years; Height: " + getHeight() + "; Weight: " + getWeight() + "; Diseases: " + getDiseases() + "; User is " + activityUser + "!";
    }
    public static void saveAhamSvasthaUser(AhamSvasthaUser user, Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("Gender", user.gender).commit();
        sharedPreferences.edit().putInt("Age", user.age).commit();
        sharedPreferences.edit().putFloat("Height", user.height).commit();
        sharedPreferences.edit().putString("hUnit", user.unit).commit();
        sharedPreferences.edit().putFloat("Weight", user.weight).commit();
        sharedPreferences.edit().putString("wUnit", user.wUnit).commit();
        sharedPreferences.edit().putBoolean("isHavingDiabetes", user.diseases.get("Diabetes")).commit();
        sharedPreferences.edit().putBoolean("isHavingThyroid", user.diseases.get("Thyroid")).commit();
        sharedPreferences.edit().putBoolean("isHavingCholestrol", user.diseases.get("Cholestrol")).commit();
        sharedPreferences.edit().putBoolean("isHavingBP", user.diseases.get("BP")).commit();
        sharedPreferences.edit().putBoolean("isHavingObesity", user.diseases.get("Obesity")).commit();
        sharedPreferences.edit().putString("userActivity", user.activityUser).commit();
    }
    public static AhamSvasthaUser getAhamSvasthaUser(Context context){
        AhamSvasthaUser restoredUser = new AhamSvasthaUser();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        restoredUser.setGender(sharedPreferences.getString("Gender", null));
        restoredUser.setAge(sharedPreferences.getInt("Age", 0));
        restoredUser.setHeight(sharedPreferences.getFloat("Height", 0));
        restoredUser.setUnit(sharedPreferences.getString("hUnit", null));
        restoredUser.setWeight(sharedPreferences.getFloat("Weight",0));
        restoredUser.setWUnit(sharedPreferences.getString("wUnit", null));
        HashMap<String, Boolean> restoredDiseases = new HashMap<>();
        if (sharedPreferences.getBoolean("isHavingDiabetes", false)){
            restoredDiseases.put("Diabetes", true);
        }else{
            restoredDiseases.put("Diabetes", false);
        }
        if (sharedPreferences.getBoolean("isHavingThyroid", false)){
            restoredDiseases.put("Thyroid", true);
        }else{
            restoredDiseases.put("Thyroid", false);
        }
        if (sharedPreferences.getBoolean("isHavingCholestrol", false)){
            restoredDiseases.put("Cholestrol", true);
        }else{
            restoredDiseases.put("Cholestrol", false);
        }
        if (sharedPreferences.getBoolean("isHavingBP", false)){
            restoredDiseases.put("BP", true);
        }else{
            restoredDiseases.put("BP", false);
        }
        if (sharedPreferences.getBoolean("isHavingObesity", false)){
            restoredDiseases.put("Obesity", true);
        }else{
            restoredDiseases.put("Obesity", false);
        }
        restoredUser.setDiseases(restoredDiseases);
        restoredUser.setUserActivity(sharedPreferences.getString("userActivity", null));
        return restoredUser;
    }
}
