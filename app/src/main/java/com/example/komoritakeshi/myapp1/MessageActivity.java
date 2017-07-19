package com.example.komoritakeshi.myapp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.bassaer.chatmessageview.models.Message;
import com.github.bassaer.chatmessageview.models.User;
import com.github.bassaer.chatmessageview.utils.ChatBot;
import com.github.bassaer.chatmessageview.views.ChatView;
import com.github.bassaer.chatmessageview.views.MessageView;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MessageActivity extends AppCompatActivity {
    private ChatView mChatView;
    private ArrayList<User2> mUsers;
    private ArrayList<Message> messages;
    private int setMessageCount;
    private String token;
    private String userName;

    private static final int READ_REQUEST_CODE = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        initUsers2();

        mChatView = (ChatView) findViewById(R.id.chat_view);

        //Set UI parameters if you need
        mChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mChatView.setLeftBubbleColor(ContextCompat.getColor(this, R.color.gray300));
        mChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.blueGray400));
        mChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.blueGray500));
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setOptionButtonColor(R.color.teal500);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.WHITE);
        mChatView.setSendTimeTextColor(Color.WHITE);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setMessageStatusTextColor(Color.WHITE);
        mChatView.setInputTextHint("new message...");
        mChatView.setMessageMarginTop(5);
        mChatView.setMessageMarginBottom(5);

        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initUsers2();
                MessageManager.getInstance().createMessage(mChatView.getInputText());
                SendNotificationHelper2.getInstance().sendFCM(token, mChatView.getInputText(), userName ,1);
                mChatView.setInputText("");
            }
        });
        //呼び出す
//        loadMessages();
        setUpMessage();
    }

    private void initUsers2() {
        mUsers = new ArrayList<>();
        //User id
        int myId = 0;
        //User icon
        //User name
        String myName = "いけてるメンズ";

        int yourId = 1;
        userName = "gyghhvh";

        String myToken = "eWbfpclvDx4:APA91bFslTKGM0xhoJygqXNIajO9aD2v3_56BtEDZIgSjCO-9f3DnmVjHNcNJE1xw9HcCbqm4UTMv7ov6dIViAYmP9yHR_TOUB-coXH0R0WgW2RZIJh1u1W4EuNL-SStlMbGRy4kKfiA";
        token = "c8VIHIpnZXI:APA91bH36yXuEziM5R6O_hG_I-u6yVcc17FxUSY44KgWOEbIWtLWK1uUNSssgHakHZLWGXoZ53dEcYGdO6cFKWAq4L1TUcXRBn9hzINEZgasKD56xFGI2mhbKwsuYiOiXdN0GT2R1mHq";


        final User2 me = new User2(myId, myName, myToken);
        final User2 you = new User2(yourId, userName, token);

        mUsers.add(me);
        mUsers.add(you);
    }
    /**
     * Load saved messages
     */
    private void loadMessages() {
        MessageManager.getInstance().readMessageFromDatabase(new ResponseCallback() {
            @Override
            public void callbackMethod(ArrayList<Message> arrayList) {
                messages = arrayList;
                for (Message message : messages) {
                    mChatView.send(message);
                }
            }
        });

        if (messages == null) {
            messages = new ArrayList<Message>();
        } else {

        }
    }

    void setUpMessage() {
        MessageManager.getInstance().setUpMessageFromDatabase(new ResponseCallback2() {
            @Override
            public void callbackMethod2(Message message) {
                mChatView.send(message);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initUsers2();
    }

    @Override
    public void onPause() {
        super.onPause();
        //Save message
    }
}