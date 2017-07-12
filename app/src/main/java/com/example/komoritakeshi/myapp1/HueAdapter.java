package com.example.komoritakeshi.myapp1;

/**
 * Created by komoritakeshi on 2017/07/11.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by rakuishi on 6/22/14.
 */
public class HueAdapter extends BaseAdapter {

    private UserDatabaseCallBack userDatabaseCallBack;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mHueArray = {
            "FF4040", "FFCF40", "9FFF40", "40FF6F",
            "40FFFF", "406FFF", "9F40FF", "FF40CF"
    };
    private Integer[] mHueIdArray = {
            1,2,3,4,5,6,7,8
    };
    private static class ViewHolder {
        public ImageButton hueImageButton;
    }

    public HueAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
//        this.userDatabaseCallBack = userDatabaseCallBack;
//        Map datasource = this.userDatabaseCallBack.getUserDataBase();
    }

    public int getCount() {
        return mHueArray.length;
    }

    public Object getItem(int position) {
        return mHueArray[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.grid_item_hue, null);
            holder = new ViewHolder();
            holder.hueImageButton = (ImageButton)convertView.findViewById(R.id.hue_imagebutton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

}