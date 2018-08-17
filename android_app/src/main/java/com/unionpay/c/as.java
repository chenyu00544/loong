package com.unionpay.c;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

final class as {
    private static d a = null;
    private static e b = null;

    static f a(s sVar) {
        Object obj;
        Context context = d.c;
        f fVar = new f();
        fVar.a = af.b(context);
        fVar.b = d.j;
        fVar.c = b();
        fVar.d = c();
        int c = (aq.c(5) + aq.b(fVar.a)) + aq.b(fVar.b);
        d dVar = fVar.c;
        int c2 = (((((aq.c(9) + aq.b(dVar.a)) + aq.b(dVar.b)) + aq.b(dVar.c)) + aq.b(dVar.d)) + aq.b(dVar.e)) + aq.b(dVar.f);
        boolean z = dVar.g;
        c += aq.b(dVar.i) + ((c2 + 1) + aq.b(dVar.h));
        e eVar = fVar.d;
        c2 = (aq.c(24) + aq.b(eVar.a)) + aq.b(eVar.b);
        h hVar = eVar.c;
        int c3 = aq.c(2);
        double d = hVar.a;
        c3 += 9;
        d = hVar.b;
        c2 = (((((((((c2 + (c3 + 9)) + aq.b(eVar.d)) + aq.b(eVar.e)) + aq.b(eVar.f)) + aq.b(eVar.g)) + aq.b(eVar.h)) + aq.c(eVar.i)) + aq.b(eVar.j)) + aq.c(eVar.k)) + aq.b(eVar.l);
        z = eVar.m;
        int b = ((c + (aq.b(eVar.x) + (((((((((((c2 + 1) + aq.b(eVar.n)) + aq.b(eVar.o)) + aq.b(eVar.p)) + aq.b(eVar.q)) + aq.b(eVar.r)) + aq.b(eVar.s)) + aq.b(eVar.t)) + aq.b(eVar.u)) + aq.c(eVar.v)) + aq.c(eVar.w)))) + 3) + 0;
        if (e()) {
            i iVar = new i();
            iVar.a = 1;
            iVar.c = a();
            fVar.e.add(iVar);
            int c4 = aq.c(iVar.a);
            g gVar = iVar.c;
            c3 = (aq.c(29) + aq.b(gVar.a)) + aq.c(gVar.b);
            float f = gVar.c;
            c3 = ((((((((((c3 + 5) + aq.b(gVar.d)) + aq.b(gVar.e)) + aq.b(gVar.f)) + aq.c(gVar.g)) + aq.c(gVar.h)) + aq.c(gVar.i)) + aq.c(gVar.j)) + aq.c(gVar.k)) + aq.c(gVar.l)) + aq.c(gVar.m);
            f = gVar.n;
            c3 += 5;
            f = gVar.o;
            c3 = ((((((((c3 + 5) + aq.c(gVar.p)) + aq.b(gVar.q)) + aq.b(gVar.r)) + aq.b(gVar.s)) + aq.b(gVar.t)) + aq.b(gVar.u)) + aq.b(gVar.v)) + aq.b(gVar.w);
            boolean z2 = gVar.x;
            b += (aq.b(gVar.C) + (((((c3 + 1) + aq.b(gVar.y)) + aq.b(gVar.z)) + aq.b(gVar.A)) + aq.b(gVar.B))) + c4;
            obj = 1;
            c = b;
        } else {
            obj = null;
            c = b;
        }
        sVar.a();
        fVar.h = sVar.c("error_report");
        List<j> c5 = sVar.c();
        List arrayList = new ArrayList();
        b = 0;
        c3 = c;
        for (j jVar : c5) {
            c4 = b + 1;
            String str = jVar.a;
            long j = fVar.f;
            jVar.e = sVar.a(str);
            str = jVar.a;
            j = fVar.g;
            jVar.f = sVar.b(str);
            Object obj2 = null;
            if (jVar.f != null) {
                for (b bVar : jVar.f) {
                    if (!bVar.a.startsWith("__")) {
                        obj2 = 1;
                        break;
                    }
                }
            }
            i iVar2 = new i();
            iVar2.a = 2;
            iVar2.b = jVar;
            b = jVar.a();
            if (b + c3 > 20480 && c4 != 1) {
                break;
            }
            b += c3;
            arrayList.add(jVar);
            if (SystemClock.elapsedRealtime() - d.f >= 7200000 || jVar.c != 2 || jVar.e.size() != 0 || (jVar.f.size() != 0 && (r5 != null || ax.b))) {
                fVar.e.add(iVar2);
                c3 = b;
                b = c4;
            } else {
                c3 = b;
                b = c4;
            }
        }
        fVar.f = sVar.a(arrayList);
        fVar.g = sVar.b(arrayList);
        if (fVar.h > 0) {
            for (i add : sVar.a(fVar.h)) {
                fVar.e.add(add);
            }
        }
        sVar.b();
        return (obj == null && fVar.e.size() == 0) ? null : fVar;
    }

    private static g a() {
        Context context = d.c;
        g gVar = new g();
        String[] e = ag.e();
        try {
            gVar.a = e[0];
            try {
                gVar.b = Integer.valueOf(e[1]).intValue();
            } catch (Throwable th) {
            }
            gVar.d = e[2];
            try {
                gVar.c = Float.valueOf(e[3]).floatValue();
            } catch (Throwable th2) {
            }
            JSONObject a = ag.a(d.c);
            if (a != null) {
                gVar.C = a.toString();
            }
        } catch (Exception e2) {
        }
        int[] f = ag.f();
        gVar.g = f[0];
        gVar.h = f[1];
        try {
            f = ag.g();
            gVar.i = f[0];
            gVar.j = f[1];
            gVar.k = f[2];
            gVar.l = f[3];
        } catch (Throwable th3) {
        }
        gVar.m = ag.h();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        gVar.n = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
        gVar.o = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
        gVar.p = displayMetrics.densityDpi;
        gVar.q = Build.DISPLAY;
        gVar.r = "unknown";
        try {
            gVar.r = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class}).invoke(null, new Object[]{"gsm.version.baseband"});
        } catch (Exception e3) {
        }
        String d = af.d(context);
        if (d != null) {
            gVar.s = d;
        }
        d = af.g(context);
        if (d != null) {
            gVar.t = d;
        }
        try {
            gVar.y = af.f(context);
            gVar.A = af.e(context);
            gVar.B = af.c(context);
        } catch (Exception e4) {
        }
        return gVar;
    }

    private static synchronized d b() {
        d dVar;
        synchronized (as.class) {
            if (a != null) {
                dVar = a;
            } else if (d.c == null) {
                dVar = null;
            } else {
                dVar = new d();
                a = dVar;
                dVar.a = d.c.getPackageName();
                a.b = z.e();
                a.c = String.valueOf(z.d());
                a.d = z.b();
                a.e = "Android+UP+V2.2.33 gp";
                a.f = d.k;
                a.h = b.a().c(d.c);
                a.i = b.a().d(d.c);
                dVar = a;
            }
        }
        return dVar;
    }

    private static synchronized e c() {
        e eVar;
        synchronized (as.class) {
            if (b == null) {
                if (d.c == null) {
                    eVar = null;
                } else {
                    eVar = new e();
                    b = eVar;
                    eVar.s = af.i(d.c);
                    b.a = ag.a();
                    b.b = String.valueOf(ag.b());
                    b.d = Build.CPU_ABI;
                    b.e = ag.c(d.c);
                    b.f = ag.d();
                    b.g = ah.k(d.c);
                    b.h = ag.c();
                    b.i = ((TimeZone.getDefault().getRawOffset() / 1000) / 60) / 60;
                    b.j = "Android+" + VERSION.RELEASE;
                    b.r = System.currentTimeMillis() - SystemClock.elapsedRealtime();
                    JSONObject a = ag.a(d.c);
                    if (a != null) {
                        b.x = a.toString();
                    }
                }
            }
            d();
            eVar = b;
        }
        return eVar;
    }

    private static void d() {
        Location location = null;
        for (Location location2 : al.a(d.c)) {
            Location location3;
            if (location != null && location2.getTime() <= location.getTime()) {
                location3 = location;
            }
            location = location3;
        }
        h hVar = new h();
        if (location != null) {
            hVar.b = location.getLatitude();
            hVar.a = location.getLongitude();
        }
        b.c = hVar;
        b.k = ah.d(d.c) ? 0 : 1;
        b.l = ah.f(d.c);
        b.o = ah.h(d.c);
        b.n = ah.i(d.c);
        b.p = al.b(d.c);
        b.t = ah.l(d.c).toString();
        JSONArray c = al.c(d.c);
        if (e() || new Random().nextInt(100) > 90) {
            b.u = c == null ? "" : c.toString();
        }
    }

    private static synchronized boolean e() {
        boolean z;
        synchronized (as.class) {
            z = aj.b(d.c, "UPpref_longtime", "UPpref.profile.key", 1) != 0;
        }
        return z;
    }
}
