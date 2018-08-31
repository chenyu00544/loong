package com.vcvb.chenyu.shop.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class UserInfoUtils {
    private static final byte[] LOCKER = new byte[0];
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Context context;
    private static UserInfoUtils mInstance;

    @SuppressLint("WrongConstant")
    public static UserInfoUtils getInstance(Context ct) {
        if (mInstance == null) {
            synchronized (LOCKER) {
                if (mInstance == null) {
                    mInstance = new UserInfoUtils();
                    context = ct;
                    sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                }
            }
        }
        return mInstance;
    }

    public void setUserInfo(Map<String, String> userInfo) {
        editor.putString("username", userInfo.get("username"));
        editor.putString("token", userInfo.get("token"));
        editor.putString("logo", userInfo.get("logo"));
        editor.putString("nickname", userInfo.get("nickname"));
        editor.commit();
    }

    public Map<String, ?> getUserInfo(){
        return sharedPreferences.getAll();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}
