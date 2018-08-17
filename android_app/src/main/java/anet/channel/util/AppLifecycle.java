package anet.channel.util;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.os.Build.VERSION;
import anet.channel.GlobalAppRuntimeInfo;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: Taobao */
public class AppLifecycle {
    public static long a = 0;
    private static CopyOnWriteArraySet<AppLifecycleListener> b = new CopyOnWriteArraySet();
    private static ActivityLifecycleCallbacks c = new a();
    private static ComponentCallbacks2 d = new b();

    /* compiled from: Taobao */
    public interface AppLifecycleListener {
        void background();

        void forground();
    }

    private AppLifecycle() {
    }

    public static void a() {
        if (VERSION.SDK_INT >= 14) {
            ((Application) GlobalAppRuntimeInfo.getContext().getApplicationContext()).registerActivityLifecycleCallbacks(c);
            GlobalAppRuntimeInfo.getContext().registerComponentCallbacks(d);
        }
    }

    public static void a(AppLifecycleListener appLifecycleListener) {
        if (appLifecycleListener != null) {
            b.add(appLifecycleListener);
        }
    }

    public static void b(AppLifecycleListener appLifecycleListener) {
        b.remove(appLifecycleListener);
    }

    public static void b() {
        if (GlobalAppRuntimeInfo.isBackground) {
            GlobalAppRuntimeInfo.isBackground = false;
            Iterator it = b.iterator();
            while (it.hasNext()) {
                ((AppLifecycleListener) it.next()).forground();
            }
        }
    }

    public static void c() {
        if (!GlobalAppRuntimeInfo.isBackground) {
            GlobalAppRuntimeInfo.isBackground = true;
            a = System.currentTimeMillis();
            Iterator it = b.iterator();
            while (it.hasNext()) {
                ((AppLifecycleListener) it.next()).background();
            }
        }
    }
}
