package com.umeng.message.inapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.common.UmLog;
import com.umeng.message.common.impl.json.JUtrack;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.l;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppMessageManager {
    static boolean a = false;
    static int b = 1800000;
    static int c = 1000;
    private static final String d = InAppMessageManager.class.getName();
    @SuppressLint({"StaticFieldLeak"})
    private static InAppMessageManager e = null;
    private static boolean h = false;
    private static final String i = "tempkey";
    private static final String j = "tempvalue";
    private Context f;
    private String g;

    class InAppMessageManager_4 implements Runnable {
        final /* synthetic */ InAppMessageManager a;

        InAppMessageManager_4(InAppMessageManager inAppMessageManager) {
            this.a = inAppMessageManager;
        }

        public void run() {
            try {
                Iterator it = this.a.k().iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    JSONObject a = this.a.b(aVar.b, aVar.c, aVar.d, aVar.e, aVar.f, aVar.g, aVar.h, aVar.i);
                    if (a != null && TextUtils.equals(a.getString("success"), ITagManager.SUCCESS)) {
                        this.a.k(aVar.b);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                InAppMessageManager.h = false;
            }
        }
    }

    class a {
        public long a;
        public String b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        final /* synthetic */ InAppMessageManager j;

        public a(InAppMessageManager inAppMessageManager, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.j = inAppMessageManager;
            this.a = System.currentTimeMillis();
            this.b = str;
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
            this.h = i6;
            this.i = i7;
        }

        public a(InAppMessageManager inAppMessageManager, Cursor cursor) {
            this.j = inAppMessageManager;
            this.b = cursor.getString(cursor.getColumnIndex(l.i));
            this.c = cursor.getInt(cursor.getColumnIndex(l.j));
            this.d = cursor.getInt(cursor.getColumnIndex(l.r));
            this.e = cursor.getInt(cursor.getColumnIndex(l.s));
            this.f = cursor.getInt(cursor.getColumnIndex(l.t));
            this.g = cursor.getInt(cursor.getColumnIndex(l.u));
            this.h = cursor.getInt(cursor.getColumnIndex(l.v));
            this.i = cursor.getInt(cursor.getColumnIndex(l.w));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(l.l, Long.valueOf(this.a));
            contentValues.put(l.i, this.b);
            contentValues.put(l.j, Integer.valueOf(this.c));
            contentValues.put(l.r, Integer.valueOf(this.d));
            contentValues.put(l.s, Integer.valueOf(this.e));
            contentValues.put(l.t, Integer.valueOf(this.f));
            contentValues.put(l.u, Integer.valueOf(this.g));
            contentValues.put(l.v, Integer.valueOf(this.h));
            contentValues.put(l.w, Integer.valueOf(this.i));
            return contentValues;
        }
    }

    private InAppMessageManager(Context context) {
        this.f = context;
    }

    public static InAppMessageManager getInstance(Context context) {
        if (e == null) {
            synchronized (InAppMessageManager.class) {
                if (e == null) {
                    e = new InAppMessageManager(context.getApplicationContext());
                }
            }
        }
        return e;
    }

    public void showCardMessage(Activity activity, String str, IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback) {
        new a(activity, str, iUmengInAppMsgCloseCallback).a();
    }

    public void setInAppMsgDebugMode(boolean z) {
        a = z;
    }

    public void setMainActivityPath(String str) {
        this.g = str;
    }

    public String getMainActivityPath() {
        return this.g;
    }

    void a() {
        b(MsgConstant.KEY_SPLASH_TS, System.currentTimeMillis() + "");
    }

    long b() {
        return Long.valueOf(a(MsgConstant.KEY_SPLASH_TS, "0")).longValue();
    }

    void a(String str) {
        b("KEY_CARD_TS_" + str, System.currentTimeMillis() + "");
    }

    long b(String str) {
        return Long.valueOf(a("KEY_CARD_TS_" + str, "0")).longValue();
    }

    void a(UInAppMessage uInAppMessage) {
        if (uInAppMessage == null) {
            b(MsgConstant.KEY_LAST_SPLASH_ID, "");
        } else if (uInAppMessage.getRaw() != null) {
            b(MsgConstant.KEY_LAST_SPLASH_ID, uInAppMessage.getRaw().toString());
        }
    }

    String c() {
        return a(MsgConstant.KEY_LAST_SPLASH_ID, "");
    }

    void a(UInAppMessage uInAppMessage, String str) {
        if (uInAppMessage == null) {
            b("KEY_LAST_CARD_ID_" + str, "");
        } else if (uInAppMessage.getRaw() != null) {
            b("KEY_LAST_CARD_ID_" + str, uInAppMessage.getRaw().toString());
        }
    }

    String c(String str) {
        return a("KEY_LAST_CARD_ID_" + str, "");
    }

    void a(String str, int i) {
        if (i == 0) {
            b(str, "0");
        }
        if (i == 1) {
            b(str, (d(str) + 1) + "");
        }
    }

    int d(String str) {
        return Integer.valueOf(a(str, "0")).intValue();
    }

    void e(String str) {
        b(MsgConstant.KEY_CARD_LABEL_LIST, str);
    }

    String d() {
        return a(MsgConstant.KEY_CARD_LABEL_LIST, "");
    }

    void f(String str) {
        b(MsgConstant.KEY_LAST_VERSION_CODE, str);
    }

    String e() {
        return a(MsgConstant.KEY_LAST_VERSION_CODE, "");
    }

    void f() {
        b(MsgConstant.KEY_LAST_SHOW_SPLASH_TS, System.currentTimeMillis() + "");
    }

    long g() {
        return Long.parseLong(a(MsgConstant.KEY_LAST_SHOW_SPLASH_TS, "0"));
    }

    void g(String str) {
        b("KEY_LAST_SHOW_CARD_TS_" + str, System.currentTimeMillis() + "");
    }

    long h(String str) {
        return Long.parseLong(a("KEY_LAST_SHOW_CARD_TS_" + str, "0"));
    }

    boolean b(UInAppMessage uInAppMessage) {
        try {
            if (System.currentTimeMillis() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(uInAppMessage.expire_time).getTime()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean c(UInAppMessage uInAppMessage) {
        if (uInAppMessage.show_times != 0 && d(uInAppMessage.msg_id) >= uInAppMessage.show_times) {
            return false;
        }
        return true;
    }

    void a(final IUmengInAppMessageCallback iUmengInAppMessageCallback) {
        j();
        new Thread(new Runnable(this) {
            final /* synthetic */ InAppMessageManager b;

            public void run() {
                UmLog.i(InAppMessageManager.d, "get splash message begin");
                try {
                    JSONObject sendRequest = JUtrack.sendRequest(this.b.i(), MsgConstant.SPLASH_MSG_ENDPOINT);
                    if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
                        UmLog.d(InAppMessageManager.d, "get splash message success" + sendRequest);
                        sendRequest = sendRequest.getJSONObject("data");
                        InAppMessageManager.b = sendRequest.getInt("pduration") * 1000;
                        InAppMessageManager.c = sendRequest.getInt("sduration") * 1000;
                        iUmengInAppMessageCallback.onSplashMessage(new UInAppMessage(sendRequest.getJSONObject("launch")));
                        this.b.a();
                    } else if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), "fail") && TextUtils.equals(sendRequest.getString("error"), "no message")) {
                        Object c = this.b.c();
                        if (!TextUtils.isEmpty(c)) {
                            UInAppMessage uInAppMessage;
                            try {
                                uInAppMessage = new UInAppMessage(new JSONObject(c));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                uInAppMessage = null;
                            }
                            if (uInAppMessage != null) {
                                this.b.a(new File(h.d(this.b.f, uInAppMessage.msg_id)));
                                this.b.a(null);
                            }
                        }
                    } else {
                        iUmengInAppMessageCallback.onSplashMessage(null);
                    }
                } catch (Exception e2) {
                    iUmengInAppMessageCallback.onSplashMessage(null);
                    e2.printStackTrace();
                }
            }
        }).start();
    }

    void a(final String str, final IUmengInAppMessageCallback iUmengInAppMessageCallback) {
        j();
        new Thread(new Runnable(this) {
            final /* synthetic */ InAppMessageManager c;

            public void run() {
                UmLog.i(InAppMessageManager.d, "get card message begin");
                try {
                    JSONObject a = this.c.i();
                    a.put("label", str);
                    JSONObject sendRequest = JUtrack.sendRequest(a, MsgConstant.CARD_MSG_ENDPOINT);
                    if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
                        UmLog.d(InAppMessageManager.d, "get card message success" + sendRequest);
                        sendRequest = sendRequest.getJSONObject("data");
                        InAppMessageManager.b = sendRequest.getInt("pduration") * 1000;
                        InAppMessageManager.c = sendRequest.getInt("sduration") * 1000;
                        iUmengInAppMessageCallback.onCardMessage(new UInAppMessage(sendRequest.getJSONObject("card")));
                        this.c.a(a.optString("label", ""));
                    } else if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), "fail") && TextUtils.equals(sendRequest.getString("error"), "no message")) {
                        Object c = this.c.c(str);
                        if (!TextUtils.isEmpty(c)) {
                            UInAppMessage uInAppMessage;
                            try {
                                uInAppMessage = new UInAppMessage(new JSONObject(c));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                uInAppMessage = null;
                            }
                            if (uInAppMessage != null) {
                                this.c.a(new File(h.d(this.c.f, uInAppMessage.msg_id)));
                                this.c.a(null, str);
                            }
                        }
                    } else {
                        iUmengInAppMessageCallback.onCardMessage(null);
                    }
                } catch (Exception e2) {
                    iUmengInAppMessageCallback.onCardMessage(null);
                    e2.printStackTrace();
                }
            }
        }).start();
    }

    void a(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        final String str2 = str;
        final int i8 = i;
        final int i9 = i2;
        final int i10 = i3;
        final int i11 = i4;
        final int i12 = i5;
        final int i13 = i6;
        final int i14 = i7;
        new Thread(new Runnable(this) {
            final /* synthetic */ InAppMessageManager i;

            public void run() {
                try {
                    UmLog.i(InAppMessageManager.d, "track in app msg begin");
                    JSONObject a = this.i.b(str2, i8, i9, i10, i11, i12, i13, i14);
                    if (a != null && TextUtils.equals(a.getString("success"), ITagManager.SUCCESS)) {
                        UmLog.i(InAppMessageManager.d, "track in app msg success");
                    }
                } catch (Exception e) {
                    Exception exception = e;
                    this.i.c(str2, i8, i9, i10, i11, i12, i13, i14);
                    exception.printStackTrace();
                }
            }
        }).start();
    }

    private JSONObject i() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.f).getHeader());
        if (a) {
            jSONObject.put(MsgConstant.KEY_INAPP_PMODE, "0");
        } else {
            jSONObject.put(MsgConstant.KEY_INAPP_PMODE, "1");
        }
        return jSONObject;
    }

    private void j() {
        if (h) {
            UmLog.i(d, "sendInAppCacheLog already in queue, abort this request");
            return;
        }
        h = true;
        UmLog.i(d, "sendInAppCacheLog begin");
        new Thread(new InAppMessageManager_4(this)).start();
    }

    private JSONObject b(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.f).getHeader());
        jSONObject.put("msg_id", str);
        jSONObject.put(MsgConstant.INAPP_MSG_TYPE, i);
        jSONObject.put(MsgConstant.INAPP_NUM_DISPLAY, i2);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_FULL, i3);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_TOP, i4);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_BUTTOM, i5);
        jSONObject.put(MsgConstant.INAPP_NUM_CLOSE, i6);
        jSONObject.put(MsgConstant.INAPP_NUM_DURATION, i7);
        return JUtrack.sendRequest(jSONObject, MsgConstant.STATS_ENDPOINT);
    }

    private void c(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (!TextUtils.isEmpty(str)) {
            final String str2 = str;
            final int i8 = i;
            final int i9 = i2;
            final int i10 = i3;
            final int i11 = i4;
            final int i12 = i5;
            final int i13 = i6;
            final int i14 = i7;
            new Thread(new Runnable(this) {
                final /* synthetic */ InAppMessageManager i;

                public void run() {
                    try {
                        a b = this.i.j(str2);
                        a aVar;
                        if (b != null) {
                            aVar = new a(this.i, str2, i8, i9 + b.d, i10 + b.e, i11 + b.f, i12 + b.g, i13 + b.h, b.i + i14);
                            String[] strArr = new String[]{str2};
                            ContentResolver contentResolver = this.i.f.getContentResolver();
                            com.umeng.message.provider.a.a(this.i.f);
                            contentResolver.update(com.umeng.message.provider.a.k, aVar.a(), "MsgId=?", strArr);
                        } else {
                            aVar = new a(this.i, str2, i8, i9, i10, i11, i12, i13, i14);
                            ContentResolver contentResolver2 = this.i.f.getContentResolver();
                            com.umeng.message.provider.a.a(this.i.f);
                            contentResolver2.insert(com.umeng.message.provider.a.k, aVar.a());
                        }
                        UmLog.i(InAppMessageManager.d, "store in app cache log success");
                    } catch (Exception e) {
                        UmLog.i(InAppMessageManager.d, "store in app cache log fail");
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private a j(String str) {
        a aVar = null;
        String[] strArr = new String[]{str};
        ContentResolver contentResolver = this.f.getContentResolver();
        com.umeng.message.provider.a.a(this.f);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.k, null, "MsgId=?", strArr, null);
        if (query.moveToFirst()) {
            aVar = new a(this, query);
        }
        if (query != null) {
            query.close();
        }
        return aVar;
    }

    private ArrayList<a> k() {
        Cursor query;
        Exception e;
        Throwable th;
        ArrayList<a> arrayList = new ArrayList();
        try {
            ContentResolver contentResolver = this.f.getContentResolver();
            com.umeng.message.provider.a.a(this.f);
            query = contentResolver.query(com.umeng.message.provider.a.k, null, null, null, null);
            boolean z = false;
            if (query != null) {
                try {
                    z = query.moveToFirst();
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
                    return arrayList;
                }
            }
            while (z) {
                arrayList.add(new a(this, query));
                z = query.moveToNext();
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
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }

    private boolean k(String str) {
        String[] strArr = new String[]{str};
        ContentResolver contentResolver = this.f.getContentResolver();
        com.umeng.message.provider.a.a(this.f);
        if (contentResolver.delete(com.umeng.message.provider.a.k, "MsgId=?", strArr) == 1) {
            return true;
        }
        return false;
    }

    private String a(String str, String str2) {
        Cursor query;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            new ContentValues().put(i, str);
            String[] strArr = new String[]{str};
            ContentResolver contentResolver = this.f.getContentResolver();
            com.umeng.message.provider.a.a(this.f);
            query = contentResolver.query(com.umeng.message.provider.a.c, new String[]{j}, "tempkey=?", strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(query.getColumnIndex(j));
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

    private void b(final String str, final String str2) {
        new Thread(new Runnable(this) {
            final /* synthetic */ InAppMessageManager c;

            public void run() {
                Cursor query;
                Exception e;
                Throwable th;
                try {
                    String str = "tempkey=?";
                    String[] strArr = new String[]{str};
                    ContentResolver contentResolver = this.c.f.getContentResolver();
                    com.umeng.message.provider.a.a(this.c.f);
                    query = contentResolver.query(com.umeng.message.provider.a.c, new String[]{InAppMessageManager.j}, str, strArr, null);
                    ContentValues contentValues;
                    ContentResolver contentResolver2;
                    if (query == null) {
                        try {
                            contentValues = new ContentValues();
                            contentValues.put(InAppMessageManager.i, str);
                            contentValues.put(InAppMessageManager.j, str2);
                            contentResolver2 = this.c.f.getContentResolver();
                            com.umeng.message.provider.a.a(this.c.f);
                            contentResolver2.insert(com.umeng.message.provider.a.c, contentValues);
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
                        contentValues.put(InAppMessageManager.j, str2);
                        contentResolver2 = this.c.f.getContentResolver();
                        com.umeng.message.provider.a.a(this.c.f);
                        contentResolver2.update(com.umeng.message.provider.a.c, contentValues, str, strArr);
                    } else {
                        contentValues = new ContentValues();
                        contentValues.put(InAppMessageManager.i, str);
                        contentValues.put(InAppMessageManager.j, str2);
                        contentResolver2 = this.c.f.getContentResolver();
                        com.umeng.message.provider.a.a(this.c.f);
                        contentResolver2.insert(com.umeng.message.provider.a.c, contentValues);
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

    void i(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ InAppMessageManager b;

            public void run() {
                Cursor query;
                Exception e;
                Throwable th;
                Cursor cursor = null;
                try {
                    new ContentValues().put(InAppMessageManager.i, str);
                    ContentResolver contentResolver = this.b.f.getContentResolver();
                    com.umeng.message.provider.a.a(this.b.f);
                    query = contentResolver.query(com.umeng.message.provider.a.c, new String[]{InAppMessageManager.j}, null, null, null);
                    if (query != null) {
                        try {
                            String[] strArr = new String[]{str};
                            ContentResolver contentResolver2 = this.b.f.getContentResolver();
                            com.umeng.message.provider.a.a(this.b.f);
                            contentResolver2.delete(com.umeng.message.provider.a.c, "tempkey=?", strArr);
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

    void a(final File file) {
        new Thread(new Runnable(this) {
            final /* synthetic */ InAppMessageManager b;

            public void run() {
                if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
                    for (File file : file.listFiles()) {
                        if (!file.isDirectory()) {
                            file.delete();
                        }
                    }
                    file.delete();
                }
            }
        }).start();
    }
}
