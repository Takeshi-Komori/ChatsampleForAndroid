package com.example.komoritakeshi.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OtherProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
    }

    void talkBtnTapped(View view) {
        switch (view.getId()) {
            case R.id.talkBtn: {
                Intent intent = new Intent(this, MessageActivity.class);
                startActivity(intent);
            }
        }
    }
}
