package com.unionpay.c;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class t implements ActivityLifecycleCallbacks {
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
        ax.a(activity.getLocalClassName(), false);
    }

    public final void onActivityResumed(Activity activity) {
        ax.a(activity, activity.getLocalClassName(), false);
        d.g = true;
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
