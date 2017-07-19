package com.example.komoritakeshi.myapp1;

import android.graphics.Bitmap;

/**
 * Created by komoritakeshi on 2017/07/14.
 */

public class User2 {
    private int mId;
    private String mName;
    private String mToken;

    public User2(int id, String name, String token) {
        mId = id;
        mName = name;
        mToken = token;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }
}
