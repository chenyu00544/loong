package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.analytics.pro.af.a;
import org.json.JSONObject;

/* compiled from: Defcon */
public class bf implements ay {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final long e = 14400000;
    private static final long f = 28800000;
    private static final long g = 86400000;
    private static bf j = null;
    private int h = 0;
    private final long i = 60000;

    public static synchronized bf a(Context context) {
        bf bfVar;
        synchronized (bf.class) {
            if (j == null) {
                j = new bf();
                j.a(af.a(context).b().a(0));
            }
            bfVar = j;
        }
        return bfVar;
    }

    private bf() {
    }

    public void a(JSONObject jSONObject, Context context) {
        if (this.h == 1) {
            jSONObject.remove("error");
            jSONObject.remove(x.aJ);
            jSONObject.remove(x.aK);
            jSONObject.remove(x.au);
            w.a(context).a(false, true);
            m.a(context).b(new f());
        } else if (this.h == 2) {
            jSONObject.remove(x.U);
            try {
                jSONObject.put(x.U, a());
            } catch (Exception e) {
            }
            jSONObject.remove("error");
            jSONObject.remove(x.aJ);
            jSONObject.remove(x.aK);
            jSONObject.remove(x.au);
            w.a(context).a(false, true);
            m.a(context).b(new f());
        } else if (this.h == 3) {
            jSONObject.remove(x.U);
            jSONObject.remove("error");
            jSONObject.remove(x.aJ);
            jSONObject.remove(x.aK);
            jSONObject.remove(x.au);
            w.a(context).a(false, true);
            m.a(context).b(new f());
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("id", bb.a());
            jSONObject.put(x.W, currentTimeMillis);
            jSONObject.put(x.X, currentTimeMillis + 60000);
            jSONObject.put("duration", 60000);
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    public long b() {
        switch (this.h) {
            case 1:
                return 14400000;
            case 2:
                return f;
            case 3:
                return 86400000;
            default:
                return 0;
        }
    }

    public long c() {
        return this.h == 0 ? 0 : 300000;
    }

    public void a(int i) {
        if (i >= 0 && i <= 3) {
            this.h = i;
        }
    }

    public boolean d() {
        return this.h != 0;
    }

    public void a(a aVar) {
        a(aVar.a(0));
    }
}
