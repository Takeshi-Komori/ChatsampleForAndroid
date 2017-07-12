package com.example.komoritakeshi.myapp1;

import android.graphics.Bitmap;
import android.provider.Settings;
import android.util.Log;

import com.github.bassaer.chatmessageview.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by komoritakeshi on 2017/07/12.
 */

@IgnoreExtraProperties
public class User2 extends User{
    private String name;
    private String gender;
    private String age;
    private String region;
    private String biography;
    private String userID;
    private UserDatabaseCallBack userDatabaseCallBack;

    public User2(String name, String userID){
        super(0, null, null);
        this.userID = userID;
        this.name = name;
    }

//    public User(String name, String gender, String age, String region, String biography) {
//        this.name = name;
//        this.gender = gender;
//        this.age = age;
//        this.region = region;
//        this.biography = biography;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

//    private DatabaseReference mDatabase;
//    // ...
//    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users");

    static void readUsersFromDatabase() {

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users");
        databaseRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map twoKeysMap = new HashMap();
                        twoKeysMap = (Map) dataSnapshot.getValue();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }
}
