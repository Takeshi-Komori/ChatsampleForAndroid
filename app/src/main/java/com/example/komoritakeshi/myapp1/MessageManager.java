package com.example.komoritakeshi.myapp1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.github.bassaer.chatmessageview.models.Message;
import com.github.bassaer.chatmessageview.models.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by komoritakeshi on 2017/07/12.
 */
//


interface ResponseCallback {
    void callbackMethod(ArrayList<Message> arrayList);
}

interface ResponseCallback2 {
    void callbackMethod2(Message message);
}

public class MessageManager {

    private ArrayList<User> mUsers;
    private ArrayList<Message> messages;
    private ResponseCallback callback;
    private ResponseCallback2 callback2;
    private int count;

    private static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";
    SimpleDateFormat dateformat = new SimpleDateFormat(DATE_PATTERN);
    java.util.Date date = null;

    public void setResponseCallback(ResponseCallback callback){
        this.callback = callback;
    }
    public void setResponseCallback2(ResponseCallback2 callback2){
        this.callback2 = callback2;
    }

    static public MessageManager getInstance() {
        return new MessageManager();
    }

    void createMessage(String text) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chatrooms").child("94A656B1-8797-4E36-8F4E-B12F3988660A").child("messages");
        HashMap<String, String> message = new HashMap<String, String>();

        Date now = new Date();
        String date = new SimpleDateFormat(DATE_PATTERN).format(now);
        message.put("createDate" ,date);
        message.put("from" , "25008f08-fd8c-4fa7-996d-68fa25ec5f3f");
        message.put("text" , text);
        databaseRef.push().setValue(message);
    }

    void readMessageFromDatabase(final ResponseCallback callback) {
        initUsers();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chatrooms").child("94A656B1-8797-4E36-8F4E-B12F3988660A").child("messages");
        databaseRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        messages = new ArrayList<Message>();
                        if (dataSnapshot.getValue() != null) {
                            HashMap<String, HashMap> messageDataSource = (HashMap<String, HashMap>) dataSnapshot.getValue();
                            for (HashMap.Entry<String, HashMap> entry : messageDataSource.entrySet()) {
                                Message message = new Message();
                                String key = entry.getKey();
                                HashMap val = entry.getValue();
                                String userID = (String) val.get("from");
                                String created_at = (String) val.get("createDate");
                                try {
                                    date = new java.util.Date(dateformat.parse(created_at).getTime());
                                    Calendar cal_created = Calendar.getInstance();
                                    cal_created.setTime(date);
                                    message.setCreatedAt(cal_created);
                                } catch (java.text.ParseException e) {
                                    System.out.print(e.getMessage());
                                }

                                if (!userID.equals("e7eb329e-9a0c-4775-b465-641699f0b319")) {
                                    message.setUser(mUsers.get(0));
                                } else {
                                    message.setUser(mUsers.get(1));
                                    message.setRightMessage(true);
                                }
                                message.setMessageText((String) val.get("text"));
                                messages.add(message);
                            }
                            callback.callbackMethod(messages);
                        } else {
                            count = 0;
                            callback.callbackMethod(messages);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }

    void setUpMessageFromDatabase(final ResponseCallback2 callback2) {
        initUsers();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chatrooms").child("94A656B1-8797-4E36-8F4E-B12F3988660A").child("messages");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messages = new ArrayList<Message>();
                HashMap<String, String> messageDataSource = (HashMap<String, String>) dataSnapshot.getValue();
                Message message = new Message();
                for (HashMap.Entry<String, String> entry : messageDataSource.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    if (key.equals("from")) {
                        if (!val.equals("25008f08-fd8c-4fa7-996d-68fa25ec5f3f")) {
                            message.setUser(mUsers.get(0));
                        } else {
                            message.setUser(mUsers.get(1));
                            message.setRightMessage(true);
                        }
                    }
                    if (key.equals("text")) {
                        message.setMessageText(val);
                    }
                    if (key.equals("createDate")) {
                        try {
                            date = new java.util.Date(dateformat.parse(val).getTime());
                            Calendar cal_created = Calendar.getInstance();
                            cal_created.setTime(date);
                            message.setCreatedAt(cal_created);
                        } catch(java.text.ParseException e) {
                            System.out.print(e.getMessage());
                        }
                    }
                }
                if (count > 0) {
                    callback2.callbackMethod2(message);
                }
                count++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseRef.addChildEventListener(childEventListener);
    }


    private void initUsers() {
        mUsers = new ArrayList<User>();
        //User id
        int myId = 0;
        //User icon
        //User name
        String myName = "いけてるメンズ";

        int yourId = 1;
        String yourName = "gyghhvh";

        final User me = new User(myId, myName, null);
        final User you = new User(yourId, yourName, null);

        mUsers.add(me);
        mUsers.add(you);
    }















}
