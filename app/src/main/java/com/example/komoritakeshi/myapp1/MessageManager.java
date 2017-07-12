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

import java.util.ArrayList;
import java.util.HashMap;
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
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chatrooms").child("5A22178B-7F4E-4D1C-8F25-EB52C5393AD2").child("messages");
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("createDate" , "0000000");
        message.put("from" , "e7eb329e-9a0c-4775-b465-641699f0b319");
        message.put("text" , text);
        databaseRef.push().setValue(message);

    }

    void readMessageFromDatabase(final ResponseCallback callback) {
        initUsers();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chatrooms").child("5A22178B-7F4E-4D1C-8F25-EB52C5393AD2").child("messages");
        databaseRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        messages = new ArrayList<Message>();
                        Map<String, HashMap> messageDataSource = (HashMap<String, HashMap>) dataSnapshot.getValue();
                        for (Map.Entry<String, HashMap> entry : messageDataSource.entrySet()) {
                            Message message = new Message();
                            String key = entry.getKey();
                            HashMap val = entry.getValue();
                            String userID = (String) val.get("from");
                            if (userID.equals("e7eb329e-9a0c-4775-b465-641699f0b319")) {
                                message.setUser(mUsers.get(0));
                            } else {
                                message.setUser(mUsers.get(1));
                            }
                            message.setMessageText((String) val.get("text"));
                            messages.add(message);
                        }
                        count = 0;
                        callback.callbackMethod(messages);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }

    void setUpMessageFromDatabase(final ResponseCallback2 callback2) {
        initUsers();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chatrooms").child("5A22178B-7F4E-4D1C-8F25-EB52C5393AD2").child("messages");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messages = new ArrayList<Message>();
                Map<String, String> messageDataSource = (HashMap<String, String>) dataSnapshot.getValue();
                Message message = new Message();
                for (Map.Entry<String, String> entry : messageDataSource.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    if (key.equals("from")) {
                        if (val.equals("e7eb329e-9a0c-4775-b465-641699f0b319")) {
                            message.setUser(mUsers.get(0));
                        } else {
                            message.setUser(mUsers.get(1));
                        }
                    }
                    if (key.equals("text")) {
                        message.setMessageText(val);
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
