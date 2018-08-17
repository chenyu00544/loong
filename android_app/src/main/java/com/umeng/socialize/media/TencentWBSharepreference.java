package com.umeng.socialize.media;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.socialize.utils.Log;
import java.util.HashMap;
import java.util.Map;

public class TencentWBSharepreference {
    private static final String KEY_ACCESS_KEY = "access_key";
    private static final String KEY_ACCESS_SECRET = "access_secret";
    private static final String KEY_TTL = "expires_in";
    private static final String KEY_UID = "uid";
    private String mAccessKey = null;
    private String mAccessSecret = null;
    private long mTTL = 0;
    private String mUID = null;
    private SharedPreferences sharedPreferences = null;

    public TencentWBSharepreference(Context context, String str) {
        this.sharedPreferences = context.getSharedPreferences(str, 0);
        this.mAccessKey = this.sharedPreferences.getString(KEY_ACCESS_KEY, null);
        this.mAccessSecret = this.sharedPreferences.getString(KEY_ACCESS_SECRET, null);
        this.mUID = this.sharedPreferences.getString("uid", null);
        this.mTTL = this.sharedPreferences.getLong("expires_in", 0);
    }

    public TencentWBSharepreference setAuthData(Map<String, String> map) {
        this.mAccessKey = (String) map.get(KEY_ACCESS_KEY);
        this.mAccessSecret = (String) map.get(KEY_ACCESS_SECRET);
        this.mUID = (String) map.get("uid");
        if (!TextUtils.isEmpty((CharSequence) map.get("expires_in"))) {
            this.mTTL = (Long.valueOf((String) map.get("expires_in")).longValue() * 1000) + System.currentTimeMillis();
        }
        return this;
    }

    public Map<String, String> getAuthData() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(KEY_ACCESS_KEY, this.mAccessKey);
        hashMap.put(KEY_ACCESS_SECRET, this.mAccessSecret);
        hashMap.put("uid", this.mUID);
        hashMap.put("expires_in", String.valueOf(this.mTTL));
        return hashMap;
    }

    public String getUID() {
        return this.mUID;
    }

    public boolean isAuthorized() {
        return !TextUtils.isEmpty(this.mUID);
    }

    public boolean isAuthValid() {
        boolean isAuthorized = isAuthorized();
        boolean z;
        if (this.mTTL - System.currentTimeMillis() <= 0) {
            z = true;
        } else {
            z = false;
        }
        if (!isAuthorized || r2) {
            return false;
        }
        return true;
    }

    public void commit() {
        this.sharedPreferences.edit().putString(KEY_ACCESS_KEY, this.mAccessKey).putString(KEY_ACCESS_SECRET, this.mAccessSecret).putString("uid", this.mUID).putLong("expires_in", this.mTTL).commit();
        Log.i("save auth succeed");
    }

    public void delete() {
        this.sharedPreferences.edit().clear().commit();
    }
}
