package com.umeng.socialize.handler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class WeixinPreferences {
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_ACCESS_TOKEN_TTL = "expires_in";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String KEY_OPENID = "openid";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_REFRESH_TOKEN_TTL = "rt_expires_in";
    private String mAccessToken;
    private long mAccessTokenTTL;
    private String mRefreshToken;
    private long mRefreshTokenTTL;
    private String mUID;
    private long mexpirein;
    private SharedPreferences sharedPreferences = null;

    public WeixinPreferences(Context context, String str) {
        this.sharedPreferences = context.getSharedPreferences(str, 0);
        this.mUID = this.sharedPreferences.getString("openid", null);
        this.mAccessToken = this.sharedPreferences.getString("access_token", null);
        this.mAccessTokenTTL = this.sharedPreferences.getLong("expires_in", 0);
        this.mRefreshToken = this.sharedPreferences.getString("refresh_token", null);
        this.mRefreshTokenTTL = this.sharedPreferences.getLong(KEY_REFRESH_TOKEN_TTL, 0);
        this.mexpirein = this.sharedPreferences.getLong("expires_in", 0);
    }

    public WeixinPreferences setAuthData(Map<String, String> map) {
        this.mUID = (String) map.get("openid");
        this.mAccessToken = (String) map.get("access_token");
        this.mRefreshToken = (String) map.get("refresh_token");
        String str = (String) map.get("expires_in");
        if (!TextUtils.isEmpty(str)) {
            this.mexpirein = (Long.valueOf(str).longValue() * 1000) + System.currentTimeMillis();
        }
        str = (String) map.get("expires_in");
        if (!TextUtils.isEmpty(str)) {
            this.mAccessTokenTTL = (Long.valueOf(str).longValue() * 1000) + System.currentTimeMillis();
        }
        str = (String) map.get(KEY_REFRESH_TOKEN_TTL);
        if (!TextUtils.isEmpty(str)) {
            this.mRefreshTokenTTL = (Long.valueOf(str).longValue() * 1000) + System.currentTimeMillis();
        }
        return this;
    }

    public WeixinPreferences setBundle(Bundle bundle) {
        this.mUID = bundle.getString("openid");
        this.mAccessToken = bundle.getString("access_token");
        this.mRefreshToken = bundle.getString("refresh_token");
        Object string = bundle.getString("expires_in");
        if (!TextUtils.isEmpty(string)) {
            this.mexpirein = (Long.valueOf(string).longValue() * 1000) + System.currentTimeMillis();
        }
        string = bundle.getString("expires_in");
        if (!TextUtils.isEmpty(string)) {
            this.mAccessTokenTTL = (Long.valueOf(string).longValue() * 1000) + System.currentTimeMillis();
        }
        string = bundle.getString(KEY_REFRESH_TOKEN_TTL);
        if (!TextUtils.isEmpty(string)) {
            this.mRefreshTokenTTL = (Long.valueOf(string).longValue() * 1000) + System.currentTimeMillis();
        }
        commit();
        return this;
    }

    public String getUID() {
        return this.mUID;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public Map<String, String> getmap() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("access_token", this.mAccessToken);
        hashMap.put("openid", this.mUID);
        hashMap.put("refresh_token", this.mRefreshToken);
        return hashMap;
    }

    public boolean isAccessTokenAvailable() {
        boolean isEmpty = TextUtils.isEmpty(this.mAccessToken);
        boolean z;
        if (this.mexpirein - System.currentTimeMillis() <= 0) {
            z = true;
        } else {
            z = false;
        }
        if (isEmpty || r2) {
            return false;
        }
        return true;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public boolean isAuthValid() {
        boolean isEmpty = TextUtils.isEmpty(this.mRefreshToken);
        boolean z;
        if (this.mRefreshTokenTTL - System.currentTimeMillis() <= 0) {
            z = true;
        } else {
            z = false;
        }
        if (isEmpty || r2) {
            return false;
        }
        return true;
    }

    public boolean isAuth() {
        return !TextUtils.isEmpty(getAccessToken());
    }

    public void delete() {
        this.sharedPreferences.edit().clear().commit();
    }

    public void commit() {
        this.sharedPreferences.edit().putString("openid", this.mUID).putString("access_token", this.mAccessToken).putLong("expires_in", this.mAccessTokenTTL).putString("refresh_token", this.mRefreshToken).putLong(KEY_REFRESH_TOKEN_TTL, this.mRefreshTokenTTL).putLong("expires_in", this.mexpirein).commit();
    }
}
