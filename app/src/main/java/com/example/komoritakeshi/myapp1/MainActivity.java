package com.example.komoritakeshi.myapp1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> imgList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new HueAdapter(this));
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.MyProfBtn: {
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.chatlistBtn: {
                Intent intent = new Intent(this, ChatListActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void imageBtnTap(View view) {
        switch (view.getId()) {
            case R.id.hue_imagebutton: {
                Intent intent = new Intent(this, OtherProfileActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

}
