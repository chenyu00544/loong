package anet.channel.util;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

/* compiled from: Taobao */
final class a implements ActivityLifecycleCallbacks {
    a() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        AppLifecycle.b();
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
