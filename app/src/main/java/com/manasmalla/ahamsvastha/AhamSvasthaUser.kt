package com.manasmalla.ahamsvastha

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.util.*


class AhamSvasthaUser {
    lateinit var name: String
    lateinit var username: String
    lateinit var password: String
    var age: Int = 0
    lateinit var gender: String
    var height: Double = 0.0
    var weight: Double = 0.0
    lateinit var diseases: HashMap<String, Boolean>
    lateinit var hUnit: String
    lateinit var wUnit: String
    lateinit var activityUser: String

    @JvmName("setName1")
    fun setName(name: String) {
        this.name = name
    }

    @JvmName("setUsername1")
    fun setUsername(username: String) {
        this.username = username
    }

    @JvmName("setPassword1")
    fun setPassword(password: String) {
        this.password = password
    }

    @JvmName("setAge1")
    fun setAge(age: Int) {
        this.age = age
    }

    @JvmName("setGender1")
    fun setGender(gender: String) {
        this.gender = gender
    }

    @JvmName("setHeight1")
    fun setHeight(height: Double) {
        this.height = height
    }

    @JvmName("setWeight1")
    fun setWeight(height: Double) {
        this.height = height
    }

    @JvmName("setDiseases1")
    fun setDiseases(diseasesList: HashMap<String, Boolean>) {
        this.diseases = diseasesList
    }

    @JvmName("setHUnit1")
    fun setHUnit(hUnit: String) {
        this.hUnit = hUnit
    }

    @JvmName("setWUnit1")
    fun setWUnit(wUnit: String) {
        this.wUnit = wUnit
    }

    @JvmName("getHeight1")
    fun getHeight(): String {
        return "$height" + hUnit
    }

    @JvmName("getWeight1")
    fun getWeight(): String {
        return "$weight" + wUnit
    }

    fun getDiseases(): String {
        return this.diseases.toString();
    }

    fun getDiseasesHashMap(): HashMap<String, Boolean> {
        return diseases
    }

    fun setUserActivity(userActivity: String) {
        this.activityUser = userActivity
    }

    fun getUserData(): String {
        return "Gender: $gender ; Age: $age years; Height: ${getHeight()}; Weight: ${getWeight()}; Diseases:${getDiseases()}; User is $activityUser!"
    }

    @SuppressLint("ApplySharedPref")
    fun saveAhamSvasthaUser(user: AhamSvasthaUser, context: Context, username: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("com.manasmalla.ahamsvastha", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("Gender$username", user.gender).commit()
        sharedPreferences.edit().putInt("Age$username", user.age).commit()
        sharedPreferences.edit().putFloat("Height$username", user.height.toFloat()).commit()
        sharedPreferences.edit().putString("hUnit$username", user.hUnit).commit()
        sharedPreferences.edit().putFloat("Weight$username", user.weight.toFloat()).commit()
        sharedPreferences.edit().putString("wUnit$username", user.wUnit).commit()
        sharedPreferences.edit()
            .putBoolean("isHavingDiabetes$username", user.diseases["Diabetes"]!!).commit()
        sharedPreferences.edit().putBoolean("isHavingThyroid$username", user.diseases["Thyroid"]!!)
            .commit()
        sharedPreferences.edit()
            .putBoolean("isHavingCholestrol$username", user.diseases["Cholestrol"]!!).commit()
        sharedPreferences.edit().putBoolean("isHavingBP$username", user.diseases["BP"]!!).commit()
        sharedPreferences.edit().putBoolean("isHavingObesity$username", user.diseases["Obesity"]!!)
            .commit()
        sharedPreferences.edit().putString("userActivity$username", user.activityUser).commit()
        sharedPreferences.edit().putString("currentUserUsername", username).commit()
        sharedPreferences.edit().putInt("numberOfUsers", sharedPreferences.getInt("numberOfUsers", 0) + 1).commit()
        if (sharedPreferences.getStringSet("listOfUsernames", null) != null){
            val listStringSet = sharedPreferences.getStringSet("listOfUsernames", null)
            listStringSet!!.add(username)
            sharedPreferences.edit().putStringSet("listOfUsernames", listStringSet).commit()
        }else{
            sharedPreferences.edit().putStringSet("listOfUsernames", setOf("$username")).commit()
        }
    }

    fun getAhamSvasthaUser(context: Context, username: String): AhamSvasthaUser? {
        val restoredUser = AhamSvasthaUser()
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("com.manasmalla.ahamsvastha", Context.MODE_PRIVATE)
        restoredUser.setGender(sharedPreferences.getString("Gender$username", null)!!)
        restoredUser.setAge(sharedPreferences.getInt("Age$username", 0))
        restoredUser.setHeight(sharedPreferences.getFloat("Height$username", 0f).toDouble())
        restoredUser.setHUnit(sharedPreferences.getString("hUnit$username", "")!!)
        restoredUser.setWeight(sharedPreferences.getFloat("Weight$username", 0f).toDouble())
        restoredUser.setWUnit(sharedPreferences.getString("wUnit$username", "")!!)
        val restoredDiseases = HashMap<String, Boolean>()
        restoredDiseases["Diabetes"] =
            sharedPreferences.getBoolean("isHavingDiabetes$username", false)
        restoredDiseases["Thyroid"] =
            sharedPreferences.getBoolean("isHavingThyroid$username", false)
        restoredDiseases["Cholestrol"] =
            sharedPreferences.getBoolean("isHavingCholestrol$username", false)
        restoredDiseases["BP"] = sharedPreferences.getBoolean("isHavingBP$username", false)
        restoredDiseases["Obesity"] =
            sharedPreferences.getBoolean("isHavingObesity$username", false)
        restoredUser.setDiseases(restoredDiseases)
        restoredUser.setUserActivity(sharedPreferences.getString("userActivity$username", null)!!)
        return restoredUser
    }

    fun getAhamSvasthaUserNumber(ctx: Context):Int{
        var userList = 0
        val sharedPreferences: SharedPreferences = ctx.getSharedPreferences("com.manasmalla.ahamsvastha", Context.MODE_PRIVATE)
        userList = sharedPreferences.getInt("numberOfUsers", 0)
        return userList
    }

    fun getCurrentUser(ctx: Context):String{
        lateinit var userName:String
        val sharedPreferences: SharedPreferences = ctx.getSharedPreferences("com.manasmalla.ahamsvastha", Context.MODE_PRIVATE)
        userName = sharedPreferences.getString("currentUserUsername", "")!!
        return userName
    }

    fun getAhamSvasthaUserList(ctx: Context):Set<String>{
        val sharedPreferences: SharedPreferences = ctx.getSharedPreferences("com.manasmalla.ahamsvastha", Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet("listOfUsernames", null)!!
    }

}