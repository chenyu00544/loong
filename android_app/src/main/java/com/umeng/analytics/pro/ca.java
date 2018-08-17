package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: StoreHelper */
public final class ca {
    private static ca a = null;
    private static Context b = null;
    private static String c = null;
    private static final String e = "mobclick_agent_user_";
    private static final String f = "mobclick_agent_header_";
    private static final String g = "mobclick_agent_cached_";
    private a d;

    /* compiled from: StoreHelper */
    public interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    /* compiled from: StoreHelper */
    class ca_1 extends f {
        final /* synthetic */ ca a;

        ca_1(ca caVar) {
            this.a = caVar;
        }

        public void a(Object obj, boolean z) {
            if (!obj.equals("success")) {
            }
        }
    }

    /* compiled from: StoreHelper */
    public static class a {
        private final int a;
        private File b;
        private FilenameFilter c;

        /* compiled from: StoreHelper */
        class ca_a_2 implements FilenameFilter {
            final /* synthetic */ a a;

            ca_a_2(a aVar) {
                this.a = aVar;
            }

            public boolean accept(File file, String str) {
                return str.startsWith("um");
            }
        }

        public a(Context context) {
            this(context, ".um");
        }

        public a(Context context, String str) {
            this.a = 10;
            this.c = new ca_a_2(this);
            this.b = new File(context.getFilesDir(), str);
            if (!this.b.exists() || !this.b.isDirectory()) {
                this.b.mkdir();
            }
        }

        public boolean a() {
            File[] listFiles = this.b.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return false;
            }
            return true;
        }

        public void a(b bVar) {
            int i;
            int i2 = 0;
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                final int length = listFiles.length - 10;
                bx.b(new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        if (length > 0) {
                            m.a(ca.b).a((long) length, System.currentTimeMillis(), com.umeng.analytics.a.w);
                        }
                    }
                });
                for (i = 0; i < length; i++) {
                    listFiles[i].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                bVar.a(this.b);
                i = listFiles.length;
                while (i2 < i) {
                    try {
                        if (bVar.b(listFiles[i2])) {
                            listFiles[i2].delete();
                        }
                    } catch (Throwable th) {
                        listFiles[i2].delete();
                    }
                    i2++;
                }
                bVar.c(this.b);
            }
        }

        public void a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    bu.a(new File(this.b, String.format(Locale.US, "um_cache_%d.env", new Object[]{Long.valueOf(System.currentTimeMillis())})), bArr);
                } catch (Exception e) {
                }
            }
        }

        public void b() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length > 0) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        public int c() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles == null || listFiles.length <= 0) {
                return 0;
            }
            return listFiles.length;
        }
    }

    public ca(Context context) {
        this.d = new a(context);
    }

    public static synchronized ca a(Context context) {
        ca caVar;
        synchronized (ca.class) {
            b = context.getApplicationContext();
            c = context.getPackageName();
            if (a == null) {
                a = new ca(context);
            }
            caVar = a;
        }
        return caVar;
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Editor edit = k().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    public String[] a() {
        SharedPreferences k = k();
        String string = k.getString("au_p", null);
        String string2 = k.getString("au_u", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new String[]{string, string2};
    }

    public void b() {
        k().edit().remove("au_p").remove("au_u").commit();
    }

    public String c() {
        SharedPreferences a = az.a(b);
        if (a != null) {
            return a.getString("appkey", null);
        }
        return null;
    }

    public void a(String str) {
        SharedPreferences a = az.a(b);
        if (a != null) {
            a.edit().putString("appkey", str).commit();
        }
    }

    public String d() {
        SharedPreferences a = az.a(b);
        if (a != null) {
            return a.getString("channel", null);
        }
        return null;
    }

    public void b(String str) {
        SharedPreferences a = az.a(b);
        if (a != null) {
            a.edit().putString("channel", str).commit();
        }
    }

    public String e() {
        SharedPreferences a = az.a(b);
        if (a != null) {
            return a.getString(SocializeProtocolConstants.PROTOCOL_KEY_ST, null);
        }
        return null;
    }

    public void c(String str) {
        SharedPreferences a = az.a(b);
        if (a != null) {
            a.edit().putString(SocializeProtocolConstants.PROTOCOL_KEY_ST, str).commit();
        }
    }

    public void a(int i) {
        SharedPreferences a = az.a(b);
        if (a != null) {
            a.edit().putInt("vt", i).commit();
        }
    }

    public int f() {
        SharedPreferences a = az.a(b);
        if (a != null) {
            return a.getInt("vt", 0);
        }
        return 0;
    }

    public void g() {
        b.deleteFile(l());
        b.deleteFile(m());
        w.a(b).a(true, false);
        m.a(b).b(new ca_1(this));
    }

    public void a(byte[] bArr) {
        this.d.a(bArr);
    }

    public boolean h() {
        return this.d.a();
    }

    public a i() {
        return this.d;
    }

    private SharedPreferences k() {
        return b.getSharedPreferences(e + c, 0);
    }

    private String l() {
        return f + c;
    }

    private String m() {
        SharedPreferences a = az.a(b);
        if (a == null) {
            return g + c + bt.a(b);
        }
        int i = a.getInt(com.umeng.analytics.a.B, 0);
        int parseInt = Integer.parseInt(bt.a(b));
        if (i == 0 || parseInt == i) {
            return g + c + bt.a(b);
        }
        return g + c + i;
    }
}
