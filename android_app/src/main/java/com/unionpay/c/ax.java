package com.unionpay.c;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

final class ax {
    static volatile boolean a = false;
    static volatile boolean b = false;
    static boolean c = false;
    static t d;
    private static boolean e = false;
    private static final HandlerThread f = new HandlerThread("PauseEventThread");
    private static Handler g;

    static class a {
        HashMap a = new HashMap();

        a() {
        }
    }

    static {
        g = null;
        f.start();
        g = new ay(f.getLooper());
    }

    private static String a(Bundle bundle, String str) {
        if (bundle != null) {
            for (String equalsIgnoreCase : bundle.keySet()) {
                if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                    return String.valueOf(bundle.get(str));
                }
            }
        }
        return null;
    }

    static /* synthetic */ void a() {
        try {
            if (z.c().equals("1")) {
                w.a().removeMessages(103);
                a aVar = new a();
                aVar.a.put("apiType", Integer.valueOf(3));
                aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
                aVar.a.put("isPageOrSession", Boolean.valueOf(false));
                aVar.a.put("sessionEnd", Integer.valueOf(1));
                Message.obtain(w.a(), 102, aVar).sendToTarget();
                z.a("2");
            }
        } catch (Exception e) {
        }
    }

    static void a(Activity activity) {
        a(activity, activity.getLocalClassName(), true);
    }

    static void a(Activity activity, String str, boolean z) {
        if (!a) {
            a((Context) activity, null, null);
        }
        if (z.c() != null && z.c().equals("2")) {
            e();
        }
        z.a("0");
        g.removeMessages(0);
        if (activity == null || (activity.getChangingConfigurations() & 128) != 128) {
            am.a(new c(str, z, activity));
            return;
        }
        y.a("Ignore page changing during screen switch");
        e = true;
    }

    static void a(Context context, String str) {
        if (e) {
            e = false;
            return;
        }
        y.a("onPageStart being called! pageName: " + str);
        if (am.b(str) && (context instanceof Activity)) {
            str = ((Activity) context).getLocalClassName();
        }
        a(context, str, 6);
    }

    private static void a(Context context, String str, int i) {
        if (!(context == null || a)) {
            a(context, null, null);
        }
        am.a(new f(i, str));
    }

    static void a(Context context, String str, String str2) {
        if (context == null) {
            y.a("Init failed Context is null");
            return;
        }
        System.currentTimeMillis();
        d.c = context.getApplicationContext();
        am.b = "UPLog";
        if (a) {
            av.a();
            at.a();
            e();
            return;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            String a = a(bundle, "UP_APP_ID");
            String a2 = a(bundle, "UP_CHANNEL_ID");
            if (!am.b(a)) {
                str = a;
            }
            if (am.b(a2)) {
                a2 = str2;
            }
            a = am.a(context, "ChannelConfig.json");
            if (am.b(a)) {
                a = a2;
            }
            if (am.b(a)) {
                a = "Default";
            }
            if (am.b(str)) {
                y.b("[SDKInit] TD AppId is null");
            } else if (am.b(context, MsgConstant.PERMISSION_INTERNET)) {
                d.a(str, a);
                try {
                    if (am.a(14)) {
                        Object obj = null;
                        try {
                            if (d.c instanceof Activity) {
                                obj = ((Activity) d.c).getApplication();
                            } else if (d.c instanceof Application) {
                                Application application = (Application) d.c;
                            }
                            if (!(obj == null || c)) {
                                Class cls = Class.forName("android.app.Application$ActivityLifecycleCallbacks");
                                Method method = obj.getClass().getMethod("registerActivityLifecycleCallbacks", new Class[]{cls});
                                d = new t();
                                method.invoke(obj, new Object[]{d});
                                c = true;
                            }
                        } catch (Throwable th) {
                        }
                    } else {
                        am.a(Class.forName("android.app.ActivityManagerNative"), new g(context), "gDefault", "android.app.IActivityManager");
                        c = true;
                    }
                } catch (Throwable th2) {
                    y.a("[SDKInit] Failed to initialize!", th2);
                    return;
                }
                am.a(new az());
                a = true;
                y.a("Analytics SDK Initializing...\n\tSDK_VERSION is: Android+UP+V2.2.33 gp\n\tApp ID is: " + d.j + "\n\tApp Channel is: " + d.k + "\n\tSDK_OVC is: TDOVC+002f025db9206d4e7824a3846d0b93cc+UnionPayChaJian");
            } else {
                y.b("[SDKInit] Permission \"android.permission.INTERNET\" is needed.");
            }
        } catch (Throwable th22) {
            y.a("[SDKInit] Failed to initialize!", th22);
        }
    }

    static void a(Context context, String str, String str2, Map map) {
        am.a(new ba(str, str2, map, context));
    }

    static void a(String str, long j) {
        if (d.c != null) {
            aj.a(d.c, "UPpref_longtime", "UPadditionalVersionName", str);
        }
        if (d.c != null) {
            aj.a(d.c, "UPpref_longtime", "UPadditionalVersionCode", j);
        }
    }

    static void a(String str, boolean z) {
        z.a("1");
        g.removeMessages(0);
        g.sendEmptyMessageDelayed(0, d.l);
        am.a(new e(str, z));
    }

    static void b(Activity activity) {
        a(activity.getLocalClassName(), true);
    }

    static void b(Context context, String str) {
        y.a("onPageEnd being called! pageName: " + str);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (am.b(str)) {
                str = activity.getLocalClassName();
            }
            if ((activity.getChangingConfigurations() & 128) == 128) {
                e = true;
                return;
            }
        }
        a(context, str, 7);
    }

    static /* synthetic */ void c() {
        if (z.b() == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (d.c != null) {
                aj.a(d.c, "UPpref_longtime", "UPpref.init.key", currentTimeMillis);
            }
        } else if (System.currentTimeMillis() - z.b() > 86400000) {
            am.d = true;
        }
    }

    private static void e() {
        a aVar = new a();
        aVar.a.put("apiType", Integer.valueOf(1));
        aVar.a.put("controller", av.a());
        Message obtain = Message.obtain();
        obtain.what = 102;
        obtain.obj = aVar;
        w.a().sendMessageDelayed(obtain, 100);
    }

    private static FileChannel f() {
        Throwable th;
        FileChannel fileChannel = null;
        RandomAccessFile randomAccessFile;
        try {
            File file = new File(d.c.getFilesDir(), "td.lock");
            if (file.exists() || file.createNewFile()) {
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    fileChannel = randomAccessFile.getChannel();
                } catch (Throwable th2) {
                    th = th2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception e) {
                            if (a.a) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (a.a) {
                        th.printStackTrace();
                    }
                    return fileChannel;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = fileChannel;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            if (a.a) {
                th.printStackTrace();
            }
            return fileChannel;
        }
        return fileChannel;
    }
}
