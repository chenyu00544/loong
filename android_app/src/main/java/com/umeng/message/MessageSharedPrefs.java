package com.umeng.message;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.util.Log;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.umeng.message.common.UmLog;
import com.umeng.message.proguard.k;
import com.umeng.message.proguard.l;
import com.umeng.message.provider.a;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessageSharedPrefs {
    private static final String a = MessageSharedPrefs.class.getName();
    private static MessageSharedPrefs c = null;
    private static final String d = "tempkey";
    private static final String e = "tempvalue";
    private Context b;
    private SharedPreferences f;
    private int g = 0;

    private MessageSharedPrefs(Context context) {
        this.b = context;
        if (VERSION.SDK_INT > 11) {
            this.g |= 4;
        }
        this.f = context.getSharedPreferences(MsgConstant.PUSH_SHARED_PREFERENCES_FILE_NAME, this.g);
    }

    public static synchronized MessageSharedPrefs getInstance(Context context) {
        MessageSharedPrefs messageSharedPrefs;
        synchronized (MessageSharedPrefs.class) {
            if (c == null) {
                c = new MessageSharedPrefs(context);
            }
            messageSharedPrefs = c;
        }
        return messageSharedPrefs;
    }

    public boolean hasAppLaunchLogSentToday() {
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTimeInMillis(l.a(this.b).f());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(a, e.toString());
        }
        Calendar instance2 = Calendar.getInstance();
        if (instance.get(6) == instance2.get(6) && instance.get(1) == instance2.get(1)) {
            return true;
        }
        return false;
    }

    public void setDisplayNotificationNumber(int i) {
        c(MsgConstant.KEY_NOTIFICATION_NUMBER, i + "");
    }

    public int getDisplayNotificationNumber() {
        return Integer.valueOf(b(MsgConstant.KEY_NOTIFICATION_NUMBER, "1")).intValue();
    }

    public void setMessageAppKey(String str) {
        this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY, str).commit();
    }

    public void removeMessageAppKey() {
        this.f.edit().remove(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY).commit();
    }

    public String getMessageAppKey() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY, "");
    }

    public void setMessageAppSecret(String str) {
        this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET, str).commit();
    }

    public void removeMessageAppSecret() {
        this.f.edit().remove(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET).commit();
    }

    public String getMessageAppSecret() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET, "");
    }

    public void setMessageChannel(String str) {
        this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_CHANNEL, str).commit();
    }

    public String getMessageChannel() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_CHANNEL, "");
    }

    public void setAppLaunchLogSendPolicy(int i) {
        c(MsgConstant.KEY_APP_LAUNCH_LOG_SEND_POLICY, i + "");
    }

    public void setDaRegisterSendPolicy(int i) {
        c(MsgConstant.KEY_APP_DAREGISTER_LOG_SEND_POLICY, i + "");
    }

    public int getDaRegisterSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_APP_DAREGISTER_LOG_SEND_POLICY, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE)).intValue();
    }

    public void setTagSendPolicy(int i) {
        c(MsgConstant.KEY_TAG_SEND_POLICY, i + "");
    }

    public int getAppLaunchLogSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_APP_LAUNCH_LOG_SEND_POLICY, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE)).intValue();
    }

    public int getTagSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_TAG_SEND_POLICY, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE)).intValue();
    }

    public void addAlias(String str, String str2, int i, int i2, String str3) {
        try {
            a(str2, str3);
            String str4 = "alias=? and type=? and exclusive=?";
            String[] strArr = new String[]{str, str2, i + ""};
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            Cursor query = contentResolver.query(a.d, new String[]{"error"}, str4, strArr, "time desc");
            ContentValues contentValues;
            ContentResolver contentResolver2;
            if (query == null) {
                contentValues = new ContentValues();
                contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
                contentValues.put("type", str2);
                contentValues.put("alias", str);
                contentValues.put(k.A, Integer.valueOf(i));
                contentValues.put("error", Integer.valueOf(i2));
                contentValues.put("message", str3);
                contentResolver2 = this.b.getContentResolver();
                a.a(this.b);
                contentResolver2.insert(a.d, contentValues);
            } else if (query.getCount() > 0) {
                query.moveToFirst();
                contentValues = new ContentValues();
                contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
                contentValues.put("type", str2);
                contentValues.put("alias", str);
                contentValues.put(k.A, Integer.valueOf(i));
                contentValues.put("error", Integer.valueOf(i2));
                contentValues.put("message", str3);
                contentResolver2 = this.b.getContentResolver();
                a.a(this.b);
                contentResolver2.update(a.d, contentValues, str4, strArr);
            } else {
                contentValues = new ContentValues();
                contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
                contentValues.put("type", str2);
                contentValues.put("alias", str);
                contentValues.put(k.A, Integer.valueOf(i));
                contentValues.put("error", Integer.valueOf(i2));
                contentValues.put("message", str3);
                contentResolver2 = this.b.getContentResolver();
                a.a(this.b);
                contentResolver2.insert(a.d, contentValues);
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(String str, String str2) {
        String[] strArr = new String[]{str, str2};
        ContentResolver contentResolver = this.b.getContentResolver();
        a.a(this.b);
        contentResolver.delete(a.d, "type=? and message=? ", strArr);
    }

    public void removeAlias(int i, String str, String str2) {
        try {
            String[] strArr = new String[]{str2, str, i + ""};
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            contentResolver.delete(a.d, "type=? and alias=? and exclusive=? ", strArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLastAlias(int i, String str) {
        String str2;
        Exception e;
        String str3 = "";
        try {
            String[] strArr = new String[]{str, i + "", "0", "2"};
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            Cursor query = contentResolver.query(a.d, new String[]{"alias"}, "type=? and exclusive=? and (error=? or error = ?)", strArr, "time desc");
            if (query == null || query.getCount() <= 0) {
                str2 = str3;
            } else {
                query.moveToFirst();
                str2 = query.getString(query.getColumnIndex("alias"));
            }
            if (query != null) {
                try {
                    query.close();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return str2;
                }
            }
        } catch (Exception e3) {
            e = e3;
            str2 = str3;
            e.printStackTrace();
            return str2;
        }
        return str2;
    }

    public boolean isAliasSet(int i, String str, String str2) {
        try {
            boolean z;
            String[] strArr = new String[]{str2, str, i + "", "0", "2"};
            UmLog.d(a, "type:" + str2 + ",alias:" + str + ",exclusive:" + i);
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            Cursor query = contentResolver.query(a.d, new String[]{"type", "alias"}, "type=? and alias=? and exclusive=? and (error=? or error = ?)", strArr, null);
            if (query != null) {
                int count = query.getCount();
                UmLog.d(a, "count:" + count);
                if (count > 0) {
                    query.moveToFirst();
                    String string = query.getString(query.getColumnIndex("type"));
                    String string2 = query.getString(query.getColumnIndex("alias"));
                    UmLog.d(a, "typeTmp:" + string + ",aliasTmp:" + string2 + ",type:" + str2 + ",alis:" + str);
                    if (string.equalsIgnoreCase(str2) && string2.equalsIgnoreCase(str)) {
                        z = true;
                        if (query != null) {
                            return z;
                        }
                        try {
                            query.close();
                            return z;
                        } catch (Exception e) {
                            return z;
                        }
                    }
                }
            }
            z = false;
            if (query != null) {
                return z;
            }
            query.close();
            return z;
        } catch (Exception e2) {
            return false;
        }
    }

    public void addTags(String... strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String format = String.format("UMENG_TAG_%s", new Object[]{strArr[i]});
            if (!Boolean.valueOf(b(format, "false")).booleanValue()) {
                c(format, "true");
                c("UMENG_TAG_COUNT", (getTagCount() + 1) + "");
            }
        }
    }

    public void removeTags(String... strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String format = String.format("UMENG_TAG_%s", new Object[]{strArr[i]});
            if (Boolean.valueOf(b(format, "false")).booleanValue()) {
                removeKeyAndValue(format);
                c("UMENG_TAG_COUNT", (getTagCount() - 1) + "");
            }
        }
    }

    public boolean isTagSet(String str) {
        return Boolean.valueOf(b(String.format("UMENG_TAG_%s", new Object[]{str}), "false")).booleanValue();
    }

    public int getTagCount() {
        return Integer.valueOf(b("UMENG_TAG_COUNT", "0")).intValue();
    }

    public void setTagRemain(int i) {
        c("UMENG_TAG_REMAIN", i + "");
    }

    public int getTagRemain() {
        return Integer.valueOf(b("UMENG_TAG_REMAIN", "64")).intValue();
    }

    public void resetTags() {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            List arrayList = new ArrayList();
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            query = contentResolver.query(a.c, null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        while (!query.isAfterLast()) {
                            String string = query.getString(query.getColumnIndex(d));
                            if (string.contains("UMENG_TAG")) {
                                arrayList.add(string);
                            }
                            query.moveToNext();
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            for (int i = 0; i < arrayList.size(); i++) {
                String[] strArr = new String[]{(String) arrayList.get(i)};
                contentResolver = this.b.getContentResolver();
                a.a(this.b);
                contentResolver.delete(a.c, "tempkey=?", strArr);
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            if (e != null) {
                try {
                    e.printStackTrace();
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    void a(int i, int i2, int i3, int i4) {
        c(MsgConstant.KEY_NO_DISTURB_START_HOUR, i + "");
        c(MsgConstant.KEY_NO_DISTURB_START_HOUR, i2 + "");
        c(MsgConstant.KEY_NO_DISTURB_END_HOUR, i3 + "");
        c(MsgConstant.KEY_NO_DISTURB_END_MINUTE, i4 + "");
    }

    int a() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_START_HOUR, "23")).intValue();
    }

    int b() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_START_MINUTE, "0")).intValue();
    }

    int c() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_END_HOUR, "7")).intValue();
    }

    int d() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_END_MINUTE, "0")).intValue();
    }

    void e() {
        c(MsgConstant.KEY_ENEABLED, "true");
    }

    void f() {
        c(MsgConstant.KEY_ENEABLED, "false");
    }

    boolean g() {
        if (b(MsgConstant.KEY_ENEABLED, "false").equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }

    boolean a(String str) {
        if (b(MsgConstant.KEY_DEVICE_TOKEN, "").equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    public void setIsEnabled(boolean z) {
        c(MsgConstant.KEY_ISENABLED, String.valueOf(z));
    }

    public boolean isEnabled() {
        String b = b(MsgConstant.KEY_ISENABLED, "");
        if (b.equalsIgnoreCase("true") || b.equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public boolean hasTransferedCacheFileDataToSQL() {
        if (b(MsgConstant.KEY_CACHE_FILE_TRANSFER_TO_SQL, "false").equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }

    public boolean finishTransferedCacheFileDataToSQL() {
        if (b(MsgConstant.KEY_CACHE_FILE_TRANSFER_TO_SQL, "true").equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }

    public <U extends UmengMessageService> void setPushIntentServiceClass(Class<U> cls) {
        if (cls == null) {
            removeKeyAndValue(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME);
        } else {
            c(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME, cls.getName());
        }
    }

    public String getPushIntentServiceClass() {
        String b = b(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME, "");
        if (b.equalsIgnoreCase("")) {
            return "";
        }
        try {
            Class.forName(b);
            return b;
        } catch (ClassNotFoundException e) {
            return "";
        }
    }

    public boolean hasMessageResourceDownloaded(String str) {
        if (b(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str, "false").equals("true")) {
            return true;
        }
        return false;
    }

    public void setMessageResourceDownloaded(String str) {
        c(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str, "true");
    }

    public void removeMessageResouceRecord(String str) {
        removeKeyAndValue(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str);
    }

    public void setLastMessageMsgID(String str) {
        c(MsgConstant.KEY_LAST_MSG_ID, str);
    }

    public String getLastMessageMsgID() {
        return b(MsgConstant.KEY_LAST_MSG_ID, "");
    }

    public void setMuteDuration(int i) {
        c(MsgConstant.KEY_MUTE_DURATION, i + "");
    }

    public int getMuteDuration() {
        return Integer.valueOf(b(MsgConstant.KEY_MUTE_DURATION, "60")).intValue();
    }

    public void setSerialNo(int i) {
        c(MsgConstant.KEY_SERIA_NO, i + "");
    }

    public int getSerialNo() {
        return Integer.valueOf(b(MsgConstant.KEY_SERIA_NO, "1")).intValue();
    }

    public boolean getNotificaitonOnForeground() {
        if (b(MsgConstant.KEY_SET_NOTIFICATION_ON_FOREGROUND, "true").equals("true")) {
            return true;
        }
        return false;
    }

    public void setNotificaitonOnForeground(boolean z) {
        c(MsgConstant.KEY_SET_NOTIFICATION_ON_FOREGROUND, String.valueOf(z));
    }

    public String getResourcePackageName() {
        return b(MsgConstant.KEY_SET_RESOURCE_PACKAGENAME, "");
    }

    public void setResourcePackageName(String str) {
        c(MsgConstant.KEY_SET_RESOURCE_PACKAGENAME, str);
    }

    public int getNotificationPlayVibrate() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_VIBRATE, "0")).intValue();
    }

    public void setNotificationPlayVibrate(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_VIBRATE, i + "");
    }

    public int getNotificationPlayLights() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_LIGHTS, "0")).intValue();
    }

    public void setNotificationPlayLights(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_LIGHTS, i + "");
    }

    public int getNotificationPlaySound() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_SOUND, "0")).intValue();
    }

    public void setNotificationPlaySound(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_SOUND, i + "");
    }

    public void setAppVersion(String str) {
        if (str == null) {
            removeKeyAndValue("app_version");
        } else {
            c("app_version", str);
        }
    }

    public String getAppVersion() {
        return b("app_version", "");
    }

    public void setDeviceToken(String str) {
        if (str == null) {
            removeKeyAndValue(MsgConstant.KEY_DEVICE_TOKEN);
        } else {
            c(MsgConstant.KEY_DEVICE_TOKEN, str);
        }
    }

    public String getDeviceToken() {
        return b(MsgConstant.KEY_DEVICE_TOKEN, "");
    }

    public void setUmid(String str) {
        this.f.edit().putString("KEY_SET_UMID", str).apply();
    }

    public String getUmid() {
        return this.f.getString("KEY_SET_UMID", "");
    }

    public void setLocationInterval(int i) {
        if (i < 2 || i > 1800) {
            UmLog.d("LBS", "The interval of LBS should not be smaller than 2 seconds");
        } else {
            c("interval", i + "");
        }
    }

    public int getLocationInterval() {
        return Integer.valueOf(b("interval", "600")).intValue();
    }

    public void setHasResgister(boolean z) {
        c(MsgConstant.KEY_HASREGISTER, String.valueOf(z));
    }

    public boolean getHasRegister() {
        if (b(MsgConstant.KEY_HASREGISTER, "false").equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }

    public int getRegisterTimes() {
        return Integer.valueOf(getInstance(this.b).b(MsgConstant.KEY_REGISTER_TIMES, "0")).intValue();
    }

    public void setRegisterTimes(int i) {
        c(MsgConstant.KEY_REGISTER_TIMES, i + "");
    }

    public String getUcode() {
        return getInstance(this.b).b(MsgConstant.KEY_UCODE, "");
    }

    public void setUcode(String str) {
        c(MsgConstant.KEY_UCODE, str);
    }

    private String b(String str, String str2) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        Cursor query;
        try {
            new ContentValues().put(d, str);
            String[] strArr = new String[]{str};
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            query = contentResolver.query(a.c, new String[]{e}, "tempkey=?", strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(query.getColumnIndex(e));
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                    if (e != null) {
                        try {
                            e.printStackTrace();
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = query;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                    return str2;
                }
            } else if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            if (e != null) {
                e.printStackTrace();
            }
            if (query != null) {
                query.close();
            }
            return str2;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return str2;
    }

    private void c(final String str, final String str2) {
        new Thread(new Runnable(this) {
            final /* synthetic */ MessageSharedPrefs c;

            public void run() {
                Cursor query;
                Exception e;
                Throwable th;
                try {
                    String str = "tempkey=?";
                    String[] strArr = new String[]{str};
                    ContentResolver contentResolver = this.c.b.getContentResolver();
                    a.a(this.c.b);
                    query = contentResolver.query(a.c, new String[]{MessageSharedPrefs.e}, str, strArr, null);
                    ContentValues contentValues;
                    ContentResolver contentResolver2;
                    if (query == null) {
                        try {
                            contentValues = new ContentValues();
                            contentValues.put(MessageSharedPrefs.d, str);
                            contentValues.put(MessageSharedPrefs.e, str2);
                            contentResolver2 = this.c.b.getContentResolver();
                            a.a(this.c.b);
                            contentResolver2.insert(a.c, contentValues);
                        } catch (Exception e2) {
                            e = e2;
                            if (e != null) {
                                try {
                                    e.printStackTrace();
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (query != null) {
                                        query.close();
                                    }
                                    throw th;
                                }
                            }
                            if (query != null) {
                                query.close();
                            }
                        }
                    } else if (query.moveToFirst()) {
                        contentValues = new ContentValues();
                        contentValues.put(MessageSharedPrefs.e, str2);
                        contentResolver2 = this.c.b.getContentResolver();
                        a.a(this.c.b);
                        contentResolver2.update(a.c, contentValues, str, strArr);
                    } else {
                        contentValues = new ContentValues();
                        contentValues.put(MessageSharedPrefs.d, str);
                        contentValues.put(MessageSharedPrefs.e, str2);
                        contentResolver2 = this.c.b.getContentResolver();
                        a.a(this.c.b);
                        contentResolver2.insert(a.c, contentValues);
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    query = null;
                    if (e != null) {
                        e.printStackTrace();
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query = null;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        }).start();
    }

    public void removeKeyAndValue(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ MessageSharedPrefs b;

            public void run() {
                Exception e;
                Throwable th;
                Cursor cursor = null;
                Cursor query;
                try {
                    new ContentValues().put(MessageSharedPrefs.d, str);
                    ContentResolver contentResolver = this.b.b.getContentResolver();
                    a.a(this.b.b);
                    query = contentResolver.query(a.c, new String[]{MessageSharedPrefs.e}, null, null, null);
                    if (query != null) {
                        try {
                            String[] strArr = new String[]{str};
                            ContentResolver contentResolver2 = this.b.b.getContentResolver();
                            a.a(this.b.b);
                            contentResolver2.delete(a.c, "tempkey=?", strArr);
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                e.printStackTrace();
                                if (query != null) {
                                    query.close();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                cursor = query;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    query = null;
                    e.printStackTrace();
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }).start();
    }
}
