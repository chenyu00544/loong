package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/* compiled from: ImprintHandler */
public class af {
    private static final String a = ".imprint";
    private static final byte[] b = "pbl0".getBytes();
    private static af f;
    private ay c;
    private a d = new a();
    private bl e = null;
    private Context g;

    /* compiled from: ImprintHandler */
    public static class a {
        private int a = -1;
        private int b = -1;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private String f = null;
        private int g = -1;
        private String h = null;
        private int i = -1;
        private int j = -1;
        private String k = null;
        private String l = null;
        private String m = null;
        private String n = null;
        private String o = null;

        a() {
        }

        a(bl blVar) {
            a(blVar);
        }

        public void a(bl blVar) {
            if (blVar != null) {
                this.a = a(blVar, "defcon");
                this.b = a(blVar, x.an);
                this.c = a(blVar, "codex");
                this.d = a(blVar, "report_policy");
                this.e = a(blVar, "report_interval");
                this.f = b(blVar, "client_test");
                this.g = a(blVar, "test_report_interval");
                this.h = b(blVar, "umid");
                this.i = a(blVar, "integrated_test");
                this.j = a(blVar, "latent_hours");
                this.k = b(blVar, x.G);
                this.l = b(blVar, "domain_p");
                this.m = b(blVar, "domain_s");
                this.n = b(blVar, x.Q);
                this.o = b(blVar, "track_list");
            }
        }

        public String a(String str) {
            if (this.n != null) {
                return this.n;
            }
            return str;
        }

        public String b(String str) {
            if (this.o != null) {
                return this.o;
            }
            return str;
        }

        public String c(String str) {
            if (this.m != null) {
                return this.m;
            }
            return str;
        }

        public String d(String str) {
            if (this.l != null) {
                return this.l;
            }
            return str;
        }

        public String e(String str) {
            if (this.k != null) {
                return this.k;
            }
            return str;
        }

        public int a(int i) {
            return (this.a != -1 && this.a <= 3 && this.a >= 0) ? this.a : i;
        }

        public int b(int i) {
            return (this.b != -1 && this.b >= 0 && this.b <= 1800) ? this.b * 1000 : i;
        }

        public int c(int i) {
            if (this.c == 0 || this.c == 1 || this.c == -1) {
                return this.c;
            }
            return i;
        }

        public int[] a(int i, int i2) {
            if (this.d == -1 || !by.a(this.d)) {
                return new int[]{i, i2};
            }
            if (this.e == -1 || this.e < 90 || this.e > 86400) {
                this.e = 90;
            }
            return new int[]{this.d, this.e * 1000};
        }

        public String f(String str) {
            return (this.f == null || !be.a(this.f)) ? str : this.f;
        }

        public int d(int i) {
            return (this.g == -1 || this.g < 90 || this.g > 86400) ? i : this.g * 1000;
        }

        public boolean a() {
            return this.g != -1;
        }

        public String g(String str) {
            return this.h;
        }

        public boolean b() {
            return this.i == 1;
        }

        public long a(long j) {
            return (this.j != -1 && this.j >= 48) ? com.umeng.analytics.a.j * ((long) this.j) : j;
        }

        private int a(bl blVar, String str) {
            if (blVar != null) {
                try {
                    if (blVar.f()) {
                        bm bmVar = (bm) blVar.d().get(str);
                        if (bmVar == null || TextUtils.isEmpty(bmVar.c())) {
                            return -1;
                        }
                        try {
                            return Integer.parseInt(bmVar.c().trim());
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return -1;
                }
            }
            return -1;
        }

        private String b(bl blVar, String str) {
            if (blVar == null) {
                return null;
            }
            String c;
            try {
                if (!blVar.f()) {
                    return null;
                }
                bm bmVar = (bm) blVar.d().get(str);
                if (bmVar == null || TextUtils.isEmpty(bmVar.c())) {
                    return null;
                }
                c = bmVar.c();
                return c;
            } catch (Exception e) {
                e.printStackTrace();
                c = null;
            }
        }
    }

    af(Context context) {
        this.g = context;
    }

    public static synchronized af a(Context context) {
        af afVar;
        synchronized (af.class) {
            if (f == null) {
                f = new af(context);
                f.c();
            }
            afVar = f;
        }
        return afVar;
    }

    public void a(ay ayVar) {
        this.c = ayVar;
    }

    public String a(bl blVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : new TreeMap(blVar.d()).entrySet()) {
            stringBuilder.append((String) entry.getKey());
            if (((bm) entry.getValue()).e()) {
                stringBuilder.append(((bm) entry.getValue()).c());
            }
            stringBuilder.append(((bm) entry.getValue()).f());
            stringBuilder.append(((bm) entry.getValue()).i());
        }
        stringBuilder.append(blVar.b);
        return bu.a(stringBuilder.toString()).toLowerCase(Locale.US);
    }

    private boolean c(bl blVar) {
        if (!blVar.j().equals(a(blVar))) {
            return false;
        }
        for (bm bmVar : blVar.d().values()) {
            byte[] a = br.a(bmVar.i());
            byte[] a2 = a(bmVar);
            for (int i = 0; i < 4; i++) {
                if (a[i] != a2[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[] a(bm bmVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(bmVar.f());
        byte[] array = allocate.array();
        byte[] bArr = b;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public void b(bl blVar) {
        String str = null;
        if (blVar != null && c(blVar)) {
            Object obj = null;
            synchronized (this) {
                bl d;
                bl blVar2 = this.e;
                String j = blVar2 == null ? null : blVar2.j();
                if (blVar2 == null) {
                    d = d(blVar);
                } else {
                    d = a(blVar2, blVar);
                }
                this.e = d;
                if (d != null) {
                    str = d.j();
                }
                if (!a(j, str)) {
                    obj = 1;
                }
            }
            if (this.e != null && r0 != null) {
                this.d.a(this.e);
                if (this.c != null) {
                    this.c.a(this.d);
                }
            }
        }
    }

    private boolean a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return false;
        }
        return true;
    }

    private bl a(bl blVar, bl blVar2) {
        if (blVar2 != null) {
            Map d = blVar.d();
            for (Entry entry : blVar2.d().entrySet()) {
                if (((bm) entry.getValue()).e()) {
                    d.put(entry.getKey(), entry.getValue());
                } else {
                    d.remove(entry.getKey());
                }
            }
            blVar.a(blVar2.g());
            blVar.a(a(blVar));
        }
        return blVar;
    }

    private bl d(bl blVar) {
        Map d = blVar.d();
        List<String> arrayList = new ArrayList(d.size() / 2);
        for (Entry entry : d.entrySet()) {
            if (!((bm) entry.getValue()).e()) {
                arrayList.add(entry.getKey());
            }
        }
        for (String remove : arrayList) {
            d.remove(remove);
        }
        return blVar;
    }

    public synchronized bl a() {
        return this.e;
    }

    public a b() {
        return this.d;
    }

    public void c() {
        InputStream openFileInput;
        byte[] b;
        Exception e;
        bl blVar;
        Throwable th;
        InputStream inputStream = null;
        if (new File(this.g.getFilesDir(), a).exists()) {
            try {
                openFileInput = this.g.openFileInput(a);
                try {
                    b = bu.b(openFileInput);
                    bu.c(openFileInput);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        bu.c(openFileInput);
                        if (b == null) {
                            try {
                                blVar = new bl();
                                new ch().a((ce) blVar, b);
                                this.e = blVar;
                                this.d.a(blVar);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = openFileInput;
                        bu.c(inputStream);
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e3 = e4;
                openFileInput = inputStream;
                e3.printStackTrace();
                bu.c(openFileInput);
                if (b == null) {
                    blVar = new bl();
                    new ch().a((ce) blVar, b);
                    this.e = blVar;
                    this.d.a(blVar);
                }
            } catch (Throwable th3) {
                th = th3;
                bu.c(inputStream);
                throw th;
            }
            if (b == null) {
                blVar = new bl();
                new ch().a((ce) blVar, b);
                this.e = blVar;
                this.d.a(blVar);
            }
        }
    }

    public void d() {
        if (this.e != null) {
            try {
                bu.a(new File(this.g.getFilesDir(), a), new cn().a(this.e));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean e() {
        return new File(this.g.getFilesDir(), a).delete();
    }
}
