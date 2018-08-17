package com.umeng.message;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.umeng.message.common.UmLog;

public abstract class UmengMessageService extends IntentService {
    private static final String a = UmengMessageService.class.getSimpleName();

    public abstract void onMessage(Context context, Intent intent);

    public UmengMessageService() {
        super("UmengMessageService");
    }

    public void onCreate() {
        super.onCreate();
    }

    protected void onHandleIntent(Intent intent) {
        onMessage(this, intent);
        UmLog.i(a, "message:" + intent.getStringExtra("body"));
    }
}
