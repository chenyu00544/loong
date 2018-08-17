package com.baidu.platform.comapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.platform.comapi.util.c;
import com.baidu.platform.comapi.util.f;

public class d extends BroadcastReceiver {
    public static final String a = d.class.getSimpleName();

    public void a(Context context) {
        String c = c.c(context);
        if (!f.d().equals(c)) {
            f.a(c);
        }
    }

    public void onReceive(Context context, Intent intent) {
        a(context);
        c.a(context);
    }
}
