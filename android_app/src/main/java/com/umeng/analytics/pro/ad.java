package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: IdTracker */
public class ad {
    public static ad a;
    private final String b = "umeng_it.cache";
    private File c;
    private bk d = null;
    private long e;
    private long f;
    private Set<y> g = new HashSet();
    private a h = null;

    /* compiled from: IdTracker */
    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context context) {
            this.a = context;
        }

        public boolean a(String str) {
            return !this.b.contains(str);
        }

        public void b(String str) {
            this.b.add(str);
        }

        public void c(String str) {
            this.b.remove(str);
        }

        public void a() {
            if (!this.b.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : this.b) {
                    stringBuilder.append(append);
                    stringBuilder.append(',');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                az.a(this.a).edit().putString("invld_id", stringBuilder.toString()).commit();
            }
        }

        public void b() {
            Object string = az.a(this.a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(",");
                if (split != null) {
                    for (CharSequence charSequence : split) {
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.b.add(charSequence);
                        }
                    }
                }
            }
        }
    }

    ad(Context context) {
        this.c = new File(context.getFilesDir(), "umeng_it.cache");
        this.f = 86400000;
        this.h = new a(context);
        this.h.b();
    }

    public static synchronized ad a(Context context) {
        ad adVar;
        synchronized (ad.class) {
            if (a == null) {
                a = new ad(context);
                a.a(new ae(context));
                a.a(new z(context));
                a.a(new am(context));
                a.a(new ac(context));
                a.a(new ab(context));
                a.a(new ag(context));
                a.a(new aj());
                a.a(new an(context));
                y aiVar = new ai(context);
                if (aiVar.g()) {
                    a.a(aiVar);
                    a.a(new ah(context));
                    aiVar.i();
                }
                a.e();
            }
            adVar = a;
        }
        return adVar;
    }

    public boolean a(y yVar) {
        if (this.h.a(yVar.b())) {
            return this.g.add(yVar);
        }
        return false;
    }

    public void a(long j) {
        this.f = j;
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.e >= this.f) {
            Object obj = null;
            for (y yVar : this.g) {
                if (yVar.c()) {
                    if (yVar.a()) {
                        obj = 1;
                        if (!yVar.c()) {
                            this.h.b(yVar.b());
                        }
                    }
                    obj = obj;
                }
            }
            if (obj != null) {
                g();
                this.h.a();
                f();
            }
            this.e = currentTimeMillis;
        }
    }

    public bk b() {
        return this.d;
    }

    private void g() {
        bk bkVar = new bk();
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (y yVar : this.g) {
            if (yVar.c()) {
                if (yVar.d() != null) {
                    hashMap.put(yVar.b(), yVar.d());
                }
                if (!(yVar.e() == null || yVar.e().isEmpty())) {
                    arrayList.addAll(yVar.e());
                }
            }
        }
        bkVar.a(arrayList);
        bkVar.a(hashMap);
        synchronized (this) {
            this.d = bkVar;
        }
    }

    public String c() {
        return null;
    }

    public void d() {
        boolean z = false;
        for (y yVar : this.g) {
            if (yVar.c()) {
                boolean z2;
                if (yVar.e() == null || yVar.e().isEmpty()) {
                    z2 = z;
                } else {
                    yVar.a(null);
                    z2 = true;
                }
                z = z2;
            }
        }
        if (z) {
            this.d.b(false);
            f();
        }
    }

    public void e() {
        bk h = h();
        if (h != null) {
            List<y> arrayList = new ArrayList(this.g.size());
            synchronized (this) {
                this.d = h;
                for (y yVar : this.g) {
                    yVar.a(this.d);
                    if (!yVar.c()) {
                        arrayList.add(yVar);
                    }
                }
                for (y yVar2 : arrayList) {
                    this.g.remove(yVar2);
                }
            }
            g();
        }
    }

    public void f() {
        if (this.d != null) {
            a(this.d);
        }
    }

    private bk h() {
        InputStream fileInputStream;
        Exception e;
        Throwable th;
        if (!this.c.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(this.c);
            try {
                byte[] b = bu.b(fileInputStream);
                ce bkVar = new bk();
                new ch().a(bkVar, b);
                bu.c(fileInputStream);
                return bkVar;
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    bu.c(fileInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    bu.c(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            e.printStackTrace();
            bu.c(fileInputStream);
            return null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            bu.c(fileInputStream);
            throw th;
        }
    }

    private void a(bk bkVar) {
        if (bkVar != null) {
            try {
                byte[] a;
                synchronized (this) {
                    a = new cn().a(bkVar);
                }
                if (a != null) {
                    bu.a(this.c, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
