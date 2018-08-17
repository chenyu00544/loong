package com.ecjia.a.b;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ecjia.a.j;
import com.ecjia.a.q;
import com.ecjia.hamster.model.ap;
import com.taobao.accs.common.Constants;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaOpenType */
public final class a {
    public static c a;
    private static Application b;
    private static String c = "opentype.json";
    private static HashMap<String, String> d = new HashMap();
    private static HashMap<String, String> e = new HashMap();
    private static String f;

    /* compiled from: ECJiaOpenType */
    private static class a extends Application {
        public a(Context context) {
            attachBaseContext(context);
        }
    }

    public static c a() {
        if (a == null) {
            b();
            c();
        }
        return a;
    }

    public static void a(Application application) {
        if (b == null) {
            b = application;
            c();
            try {
                JSONObject jSONObject = new JSONObject(j.a((Context) application, c));
                d.clear();
                e.clear();
                JSONObject optJSONObject = jSONObject.optJSONObject("support");
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    d.put(obj, optJSONObject.optString(obj));
                }
                jSONObject = jSONObject.optJSONObject("need_login");
                Iterator keys2 = optJSONObject.keys();
                while (keys2.hasNext()) {
                    String obj2 = keys2.next().toString();
                    e.put(obj2, jSONObject.optString(obj2));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void b() {
        d.clear();
        e.clear();
        if (b == null) {
            try {
                a(new a((Context) Class.forName("com.android.layoutlib.bridge.impl.RenderAction").getDeclaredMethod("getCurrentContext", new Class[0]).invoke(null, new Object[0])));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean a(Context context, String str) {
        if (!a(str).equals(b(str)) || a(context)) {
            return false;
        }
        return true;
    }

    public static boolean a(Context context) {
        if (TextUtils.isEmpty(context.getSharedPreferences(Constants.KEY_USER_ID, 0).getString("sid", ""))) {
            return false;
        }
        return true;
    }

    public static String a(String str) {
        return (String) d.get(str);
    }

    private static String b(String str) {
        return (String) e.get(str);
    }

    public static void b(Context context, String str) {
        try {
            f = context.getClass().getName();
            q.a("hostActivityName  " + f);
            Intent intent = new Intent(context, Class.forName(a("sign_in")));
            intent.putExtra("from", str);
            ((Activity) context).startActivityForResult(intent, 999);
            ((Activity) context).overridePendingTransition(context.getResources().getIdentifier("push_buttom_in", "anim", context.getPackageName()), context.getResources().getIdentifier("push_buttom_out", "anim", context.getPackageName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void c(Context context, String str) {
        try {
            Intent intent = new Intent(context, Class.forName(a("webview")));
            intent.putExtra("url", str);
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(context.getResources().getIdentifier("push_right_in", "anim", context.getPackageName()), context.getResources().getIdentifier("push_right_out", "anim", context.getPackageName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void d(Context context, String str) {
        try {
            Intent intent = new Intent(context, Class.forName(a("webview")));
            intent.putExtra("url", c(str));
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(context.getResources().getIdentifier("push_right_in", "anim", context.getPackageName()), context.getResources().getIdentifier("push_right_out", "anim", context.getPackageName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String c(String str) {
        String str2 = "";
        if (TextUtils.isEmpty(ap.c().b())) {
            return str.replace("?token=token", "");
        }
        return str.replace("token=token", "token=" + ap.c().b());
    }

    private static void c() {
        d.b();
    }
}
