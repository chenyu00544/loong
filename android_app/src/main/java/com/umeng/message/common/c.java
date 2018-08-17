package com.umeng.message.common;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.PushAgent;

/* compiled from: Res */
public class c {
    private static final String a = c.class.getName();
    private static c b;
    private static Class e = null;
    private static Class f = null;
    private static Class g = null;
    private static Class h = null;
    private static Class i = null;
    private static Class j = null;
    private static Class k = null;
    private static Class l = null;
    private Context c;
    private String d;

    private c(Context context) {
        this.c = context.getApplicationContext();
        UmLog.d(a, "packageName=" + this.c.getPackageName());
        try {
            f = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$drawable");
        } catch (ClassNotFoundException e) {
            UmLog.e(a, e.getMessage());
        }
        try {
            g = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$layout");
        } catch (ClassNotFoundException e2) {
            UmLog.e(a, e2.getMessage());
        }
        try {
            e = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$id");
        } catch (ClassNotFoundException e22) {
            UmLog.e(a, e22.getMessage());
        }
        try {
            h = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$anim");
        } catch (ClassNotFoundException e222) {
            UmLog.e(a, e222.getMessage());
        }
        try {
            i = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$style");
        } catch (ClassNotFoundException e2222) {
            UmLog.e(a, e2222.getMessage());
        }
        try {
            j = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$string");
        } catch (ClassNotFoundException e22222) {
            UmLog.e(a, e22222.getMessage());
        }
        try {
            k = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$array");
        } catch (ClassNotFoundException e222222) {
            UmLog.e(a, e222222.getMessage());
        }
        try {
            l = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName()) + ".R$raw");
        } catch (ClassNotFoundException e2222222) {
            UmLog.e(a, e2222222.getMessage());
        }
    }

    public static c a(Context context) {
        if (b == null) {
            b = new c(context);
        }
        return b;
    }

    public int a(String str) {
        return a(h, str);
    }

    public int b(String str) {
        return a(e, str);
    }

    public int c(String str) throws Exception {
        return b(e, str);
    }

    public int d(String str) {
        return a(f, str);
    }

    public int e(String str) {
        return a(g, str);
    }

    public int f(String str) throws Exception {
        return b(g, str);
    }

    public int g(String str) {
        return a(i, str);
    }

    public int h(String str) {
        return a(j, str);
    }

    public int i(String str) {
        return a(k, str);
    }

    public int j(String str) {
        return a(l, str);
    }

    private int a(Class<?> cls, String str) {
        if (cls == null) {
            UmLog.e(a, "getRes(null," + str + ")");
            throw new IllegalArgumentException("ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have " + this.c.getPackageName() + ".R$* configured in obfuscation. field=" + str);
        }
        try {
            return cls.getField(str).getInt(str);
        } catch (Exception e) {
            UmLog.e(a, "getRes(" + cls.getName() + ", " + str + ")");
            UmLog.e(a, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
            UmLog.e(a, e.getMessage());
            return -1;
        }
    }

    private int b(Class<?> cls, String str) throws Exception {
        if (cls == null) {
            UmLog.e(a, "getRes(null," + str + ")");
            throw new IllegalArgumentException("ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have " + this.c.getPackageName() + ".R$* configured in obfuscation. field=" + str);
        }
        int i = cls.getField(str).getInt(str);
        UmLog.e(a, "getRes(" + cls.getName() + ", " + str + ")");
        UmLog.e(a, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
        return i;
    }

    public void k(String str) {
        this.d = str;
    }

    public String a() {
        if (TextUtils.isEmpty(this.d)) {
            return this.c.getPackageName();
        }
        return this.d;
    }
}
