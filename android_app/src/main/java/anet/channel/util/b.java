package anet.channel.util;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* compiled from: Taobao */
final class b implements ComponentCallbacks2 {
    public static final String TAG = "awcn.ComponentCallbacks2";

    b() {
    }

    public void onTrimMemory(int i) {
        ALog.i(TAG, "onTrimMemory", null, "level", Integer.valueOf(i));
        if (i == 20) {
            AppLifecycle.c();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }
}
