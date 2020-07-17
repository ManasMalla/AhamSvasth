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
    public HashMap<String, Boolean> getDiseasesHashMap(){
        return this.diseases;
    }
    public void setUserActivity(String activity){
        this.activityUser = activity;
    }
    public String getUserData(){
        return "Gender: " + gender + "; Age: " + age + "years; Height: " + getHeight() + "; Weight: " + getWeight() + "; Diseases: " + getDiseases() + "; User is " + activityUser + "!";
    }
    public static void saveAhamSvasthaUser(AhamSvasthaUser user, Context context, String username){

        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("Gender"+username, user.gender).commit();
        sharedPreferences.edit().putInt("Age"+username, user.age).commit();
        sharedPreferences.edit().putFloat("Height"+username, user.height).commit();
        sharedPreferences.edit().putString("hUnit"+username, user.unit).commit();
        sharedPreferences.edit().putFloat("Weight"+username, user.weight).commit();
        sharedPreferences.edit().putString("wUnit"+username, user.wUnit).commit();
        sharedPreferences.edit().putBoolean("isHavingDiabetes"+username, user.diseases.get("Diabetes")).commit();
        sharedPreferences.edit().putBoolean("isHavingThyroid"+username, user.diseases.get("Thyroid")).commit();
        sharedPreferences.edit().putBoolean("isHavingCholestrol"+username, user.diseases.get("Cholestrol")).commit();
        sharedPreferences.edit().putBoolean("isHavingBP"+username, user.diseases.get("BP")).commit();
        sharedPreferences.edit().putBoolean("isHavingObesity"+username, user.diseases.get("Obesity")).commit();
        sharedPreferences.edit().putString("userActivity"+username, user.activityUser).commit();
    }
    public static AhamSvasthaUser getAhamSvasthaUser(Context context, String username){
        AhamSvasthaUser restoredUser = new AhamSvasthaUser();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        restoredUser.setGender(sharedPreferences.getString("Gender"+username, null));
        restoredUser.setAge(sharedPreferences.getInt("Age"+username, 0));
        restoredUser.setHeight(sharedPreferences.getFloat("Height"+username, 0));
        restoredUser.setUnit(sharedPreferences.getString("hUnit"+username, null));
        restoredUser.setWeight(sharedPreferences.getFloat("Weight"+username,0));
        restoredUser.setWUnit(sharedPreferences.getString("wUnit"+username, null));
        HashMap<String, Boolean> restoredDiseases = new HashMap<>();
        if (sharedPreferences.getBoolean("isHavingDiabetes"+username, false)){
            restoredDiseases.put("Diabetes", true);
        }else{
            restoredDiseases.put("Diabetes", false);
        }
        if (sharedPreferences.getBoolean("isHavingThyroid"+username, false)){
            restoredDiseases.put("Thyroid", true);
        }else{
            restoredDiseases.put("Thyroid", false);
        }
        if (sharedPreferences.getBoolean("isHavingCholestrol"+username, false)){
            restoredDiseases.put("Cholestrol", true);
        }else{
            restoredDiseases.put("Cholestrol", false);
        }
        if (sharedPreferences.getBoolean("isHavingBP"+username, false)){
            restoredDiseases.put("BP", true);
        }else{
            restoredDiseases.put("BP", false);
        }
        if (sharedPreferences.getBoolean("isHavingObesity"+username, false)){
            restoredDiseases.put("Obesity", true);
        }else{
            restoredDiseases.put("Obesity", false);
        }
        restoredUser.setDiseases(restoredDiseases);
        restoredUser.setUserActivity(sharedPreferences.getString("userActivity"+username, null));
        return restoredUser;
    }

    public static String getCurrentUsername(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.manasmalla.ahamsvasth", Context.MODE_PRIVATE);
        return sharedPreferences.getString("current_username", sharedPreferences.getString("name", null));
    }
}
