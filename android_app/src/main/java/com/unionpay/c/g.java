package com.unionpay.c;

import android.app.Activity;
import android.content.Context;
import java.lang.reflect.Method;

final class g implements ak {
    final /* synthetic */ Context a;

    g(Context context) {
        this.a = context;
    }

    public final void a(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        if (!(this.a instanceof Activity)) {
            return;
        }
        if (name.equalsIgnoreCase("activityPaused")) {
            ax.a((Activity) this.a);
        } else if (name.equalsIgnoreCase("activityIdle")) {
            ax.b((Activity) this.a);
        }
    }

    public final void a(Object obj, Method method, Object[] objArr, Object obj2) {
    }
}
