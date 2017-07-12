package com.example.komoritakeshi.myapp1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by komoritakeshi on 2017/07/12.
 */

public class Me {
    Boolean imageHaving;
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users");

    private Me(){}

    private static class MeHolder {
        private static final Me instance = new Me();
    }

    public static Me getInstance() {
        return MeHolder.instance;
    }

    public void createMe4Database(String name, String gender, int age, String region, String biography, Activity activity) {
        imageHaving = false;
        String uuid = UUID.randomUUID().toString();
        createMe4Local(name, gender, age, region, biography, activity);

        Integer population = new Integer(234567); // 値

        // データの格納 (一旦これで！)
        Map twoKeysMap = new HashMap();
        twoKeysMap.put("info", new HashMap());
        ((Map) twoKeysMap.get("info")).put("name", name);
        ((Map) twoKeysMap.get("info")).put("gender", gender);
        ((Map) twoKeysMap.get("info")).put("age", age);
        ((Map) twoKeysMap.get("info")).put("place", region);
        ((Map) twoKeysMap.get("info")).put("biography", biography);

        databaseRef.child(uuid).setValue(twoKeysMap);
    }

    //localに保存
    static public void createMe4Local(String name, String gender, int age, String region, String biography, Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putString("gender", gender);
        editor.putInt("age", age);
        editor.putString("region", region);
        editor.putString("biography", biography);
        editor.putBoolean("register", true);
        editor.apply();
    }

    //localのデータ読み込み ------------------------------------------------------------------------
    public String getNameFromLocal(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String localName = prefs.getString("name","未設定");
        return localName;
    }

    public String getGenderFromLocal(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String localGender = prefs.getString("gender","未設定");
        return localGender;
    }

    public int getAgeFromLocal(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        int localAge = prefs.getInt("age", 0);
        return localAge;
    }

    public String getRegionFromLocal(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String localRegion = prefs.getString("region","未設定");
        return localRegion;
    }

    public String getBiographyFromLocal(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String localBiography = prefs.getString("biography","未設定");
        return localBiography;
    }

    public Boolean isRegisterd(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        Boolean registerBoolValue = prefs.getBoolean("register",false);
        return registerBoolValue;
    }

    // ------------------------------------------------------------------------//

}
