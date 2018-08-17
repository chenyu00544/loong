package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.pro.ca.b;
import com.umeng.analytics.pro.cw.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.json.JSONObject;

/* compiled from: Sender */
public class ba {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static Context g;
    private ad d;
    private af e;
    private final int f = 1;
    private bc h;
    private av i;
    private JSONObject j;
    private boolean k = false;
    private boolean l;

    /* compiled from: Sender */
    class ba_1 implements b {
        final /* synthetic */ ba a;

        ba_1(ba baVar) {
            this.a = baVar;
        }

        public void a(File file) {
        }

        public boolean b(File file) {
            Throwable th;
            InputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] b = bu.b(fileInputStream);
                    try {
                        bu.c(fileInputStream);
                        byte[] a = this.a.i.a(b);
                        int i;
                        if (a == null) {
                            i = 1;
                        } else {
                            i = this.a.a(a);
                        }
                        if (!this.a.l && r2 == 1) {
                            return false;
                        }
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bu.c(fileInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                bu.c(fileInputStream);
                throw th;
            }
        }

        public void c(File file) {
            this.a.h.k();
        }
    }

    public ba(Context context, bc bcVar) {
        this.d = ad.a(context);
        this.e = af.a(context);
        g = context;
        this.h = bcVar;
        this.i = new av(context);
        this.i.a(this.h);
    }

    public void a(JSONObject jSONObject) {
        this.j = jSONObject;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public void a(ay ayVar) {
        this.e.a(ayVar);
    }

    public void a() {
        try {
            if (this.j != null) {
                c();
            } else {
                b();
            }
        } catch (Throwable th) {
        }
    }

    private void b() {
        ca.a(g).i().a(new ba_1(this));
    }

    private void c() {
        try {
            this.d.a();
            try {
                CharSequence encodeToString = Base64.encodeToString(new cn().a(this.d.b()), 0);
                if (!TextUtils.isEmpty(encodeToString)) {
                    JSONObject jSONObject = this.j.getJSONObject("header");
                    jSONObject.put(x.O, encodeToString);
                    this.j.put("header", jSONObject);
                }
            } catch (Exception e) {
            }
            byte[] bytes = String.valueOf(this.j).getBytes();
            if (bytes != null && !br.a(g, bytes)) {
                aa b;
                int i;
                if (this.k) {
                    b = aa.b(g, AnalyticsConfig.getAppkey(g), bytes);
                } else {
                    b = aa.a(g, AnalyticsConfig.getAppkey(g), bytes);
                }
                byte[] c = b.c();
                ca.a(g).g();
                bytes = this.i.a(c);
                if (bytes == null) {
                    i = 1;
                } else {
                    i = a(bytes);
                }
                switch (i) {
                    case 1:
                        if (!this.l) {
                            ca.a(g).a(c);
                            return;
                        }
                        return;
                    case 2:
                        this.d.d();
                        this.h.k();
                        return;
                    case 3:
                        this.h.k();
                        return;
                    default:
                        return;
                }
            }
        } catch (Throwable th) {
        }
    }

    private int a(byte[] bArr) {
        ce bnVar = new bn();
        try {
            new ch(new a()).a(bnVar, bArr);
            if (bnVar.a == 1) {
                this.e.b(bnVar.i());
                this.e.d();
            }
            bw.c("send log:" + bnVar.f());
        } catch (Throwable th) {
        }
        if (bnVar.a == 1) {
            return 2;
        }
        return 3;
    }
}
