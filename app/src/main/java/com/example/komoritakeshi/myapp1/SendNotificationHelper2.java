package com.example.komoritakeshi.myapp1;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by komoritakeshi on 2017/07/14.
 */

public class SendNotificationHelper2 {
    static final String API_URL = "/fcm.googleapis.com/fcm/send";
    static final String FB_DEVELOP_SERVER_KEY
            = "AAAA5MD6prc:APA91bHsTFGm7-Bl4vFu3XJauQ4z_sAvf_B-JYG-x4pcXzMirRmATDvwF7lpB-VxOsRimZXwu5cT10UtM76oiZ7CLg8SdofN5AeHyBFLWXbBYwds1u8XigvY_bnII3j2k6aYLGMu5wAg";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static SendNotificationHelper2 getInstance() {
        return new SendNotificationHelper2();
    }

    public static void sendFCM(String token, String contents,String userName,Integer deviceType) {
        new FCMTask(token, contents,userName,deviceType).execute();

    }
    private static String makeGCMBody(String token, String contents,String userName, Integer DeviceType) {
        String mainData = makeDataJSON(contents, userName);

        if (DeviceType == 1) {
            return new StringBuilder()
                    .append("{\"to\":\"").append(token).append("\",")
                    .append("\"notification\":").append(mainData)
                    .append(",\"data\":").append(mainData)
                    .append(",\"priority\":\"high\"").append("}")
                    .toString();
        }
        if (DeviceType == 2) {
            return new StringBuilder()
                    .append("{\"to\":\"").append(token).append("\",")
                    .append(",\"data\":").append(mainData)
                    .append(",\"priority\":\"high\"").append("}")
                    .toString();
        }
        return new String();
    }

    private static String makeDataJSON(String contents, String userName) {

        return new StringBuilder()
                .append("{\"body\":\"")
                .append(contents)
                .append("\",\"title\":\"")
                .append("Chaty" + " : " + userName)
                .append("\",\"badge\":\"")
                .append("999999999")
                .append("\"}")
                .toString();
    }

    private static class FCMTask extends AsyncTask<Void, Void, String> {
        private String token;
        private String userName;
        private String contents;
        private Integer mDeviceType;

        FCMTask(String token, String contents,String userName, Integer deviceType) {
            this.token = token;
            this.contents = contents;
            this.mDeviceType = deviceType;
            this.userName = userName;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                RequestBody body = RequestBody.create(JSON, makeGCMBody(token, contents, userName ,mDeviceType));
                Request request = new Request.Builder()
                        .header("Authorization", "key=" + FB_DEVELOP_SERVER_KEY)
                        .url("https://fcm.googleapis.com/fcm/send")
                        .post(body)
                        .build();
                Response response = new OkHttpClient().newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}