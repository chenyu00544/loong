package com.umeng.message.inapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UInAppMessage;

/* compiled from: UmengInAppClickHandler */
class c implements UInAppHandler {
    private static final String a = c.class.getName();
    private String b = null;
    private String c = null;
    private String d = null;

    c() {
    }

    public final void handleInAppMessage(Activity activity, UInAppMessage uInAppMessage, boolean z) {
        if (z) {
            this.b = uInAppMessage.bottom_action_type;
            this.c = uInAppMessage.bottom_action_activity;
            this.d = uInAppMessage.bottom_action_url;
        } else {
            this.b = uInAppMessage.action_type;
            this.c = uInAppMessage.action_activity;
            this.d = uInAppMessage.action_url;
        }
        if (!TextUtils.isEmpty(this.b)) {
            if (TextUtils.equals("go_activity", this.b)) {
                a(activity);
            } else if (TextUtils.equals("go_url", this.b)) {
                b(activity);
            } else if (!TextUtils.equals("go_app", this.b)) {
            }
        }
    }

    private void a(Activity activity) {
        try {
            if (this.c != null && !TextUtils.isEmpty(this.c.trim())) {
                Intent intent = new Intent();
                intent.setClassName(activity, this.c);
                intent.setFlags(536870912);
                activity.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(Activity activity) {
        try {
            if (this.d != null && !TextUtils.isEmpty(this.d.trim())) {
                UmLog.d(a, "handleInAppMessage: open url: " + this.d);
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.d)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
