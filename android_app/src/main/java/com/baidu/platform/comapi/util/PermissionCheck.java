package com.baidu.platform.comapi.util;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.baidu.lbsapi.auth.h;
import com.baidu.lbsapi.auth.m;
import com.baidu.mtjstatsdk.BasicStoreTools;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.stat.DeviceInfo;
import com.umeng.analytics.pro.x;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

public class PermissionCheck {
    private static final String a = PermissionCheck.class.getSimpleName();
    private static Context b;
    private static Hashtable<String, String> c;
    private static h d = null;
    private static m e = null;
    private static c f = null;

    public interface c {
        void a(b bVar);
    }

    private static class a implements m {
        private a() {
        }

        public void a(int i, String str) {
            if (str != null) {
                b bVar = new b();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("status")) {
                        bVar.a = jSONObject.optInt("status");
                    }
                    if (jSONObject.has("appid")) {
                        bVar.c = jSONObject.optString("appid");
                    }
                    if (jSONObject.has("uid")) {
                        bVar.b = jSONObject.optString("uid");
                    }
                    if (jSONObject.has("message")) {
                        bVar.d = jSONObject.optString("message");
                    }
                    if (jSONObject.has("token")) {
                        bVar.e = jSONObject.optString("token");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (PermissionCheck.f != null) {
                    PermissionCheck.f.a(bVar);
                }
            }
        }
    }

    public static class b {
        public int a = 0;
        public String b = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        public String c = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        public String d = "";
        public String e;

        public String toString() {
            return String.format("errorcode: %d uid: %s appid %s msg: %s", new Object[]{Integer.valueOf(this.a), this.b, this.c, this.d});
        }
    }

    public static void destory() {
        f = null;
        b = null;
        e = null;
    }

    public static void init(Context context) {
        b = context;
        if (c == null) {
            c = new Hashtable();
        }
        if (d == null) {
            d = h.a(b);
        }
        if (e == null) {
            e = new a();
        }
        Object obj = "";
        try {
            obj = context.getPackageManager().getPackageInfo(b.getPackageName(), 0).applicationInfo.loadLabel(b.getPackageManager()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("auth info", "mcode: " + a.a(b));
        Bundle a = f.a();
        c.put("mb", a.getString("mb"));
        c.put("os", a.getString("os"));
        c.put("sv", a.getString("sv"));
        c.put("imt", "1");
        c.put("net", a.getString("net"));
        c.put(x.o, a.getString(x.o));
        c.put("glr", a.getString("glr"));
        c.put("glv", a.getString("glv"));
        c.put("resid", a.getString("resid"));
        c.put("appid", WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
        c.put(DeviceInfo.TAG_VERSION, "1");
        c.put("screen", String.format("(%d,%d)", new Object[]{Integer.valueOf(a.getInt("screen_x")), Integer.valueOf(a.getInt("screen_y"))}));
        c.put("dpi", String.format("(%d,%d)", new Object[]{Integer.valueOf(a.getInt("dpi_x")), Integer.valueOf(a.getInt("dpi_y"))}));
        c.put("pcn", a.getString("pcn"));
        c.put(BasicStoreTools.DEVICE_CUID, a.getString(BasicStoreTools.DEVICE_CUID));
        c.put("name", obj);
    }

    public static synchronized int permissionCheck() {
        int i = 0;
        synchronized (PermissionCheck.class) {
            if (!(d == null || e == null || b == null)) {
                i = d.a(false, "lbs_androidsdk", c, e);
            }
        }
        return i;
    }

    public static void setPermissionCheckResultListener(c cVar) {
        f = cVar;
    }
}
