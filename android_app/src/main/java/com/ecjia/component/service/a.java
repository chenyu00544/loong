package com.ecjia.component.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.IBinder;
import com.ecjia.a.ab;
import com.ecjia.a.q;
import com.taobao.accs.common.Constants;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Calendar;

/* compiled from: ECJiaCheckInNotificationService */
public class a extends Service {
    String a = "ECJiaCheckInNotificationService";
    public com.ecjia.component.b.a b = new com.ecjia.component.b.a();

    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sendNotification");
        registerReceiver(this.b, intentFilter);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        q.a(this.a + "启动签到提醒服务");
        long j = getSharedPreferences(Constants.KEY_USER_ID, 0).getLong("signin_time", 0);
        if (j == 0) {
            a(0);
            q.a(this.a + "从未签到过，启动闹钟提醒签到，立即提醒");
        } else if (ab.a(j, System.currentTimeMillis())) {
            q.a(this.a + "签到时间是同一天");
            a((Context) this);
        } else {
            q.a(this.a + "签到时间不是同一天");
            if (ab.a(getSharedPreferences(Constants.KEY_USER_ID, 0).getLong("issignin_notify", 0), System.currentTimeMillis())) {
                q.a(this.a + "已经提醒过了");
            } else {
                q.a(this.a + "没有提醒，需要注册提醒");
                a(0);
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        onCreate();
        super.onDestroy();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onLowMemory() {
        super.onLowMemory();
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
    }

    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    void a(long j) {
        AlarmManager alarmManager = (AlarmManager) getSystemService("alarm");
        Intent intent = new Intent(this, com.ecjia.component.b.a.class);
        intent.setAction("sendNotification");
        alarmManager.set(2, j, PendingIntent.getBroadcast(this, 0, intent, 0));
    }

    private void a(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent(context, com.ecjia.component.b.a.class);
        intent.setAction("sendNotification");
        alarmManager.cancel(PendingIntent.getBroadcast(context, 0, intent, 0));
        intent = new Intent(context, com.ecjia.component.b.a.class);
        intent.setAction("sendNotification");
        alarmManager.set(0, a(), PendingIntent.getBroadcast(context, 0, intent, 0));
    }

    public long a() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 8);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        if (System.currentTimeMillis() < instance.getTimeInMillis()) {
            return instance.getTimeInMillis();
        }
        instance.set(6, 7);
        instance.set(11, 8);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        q.a(this.a + "明天8点：" + instance.getTimeInMillis());
        return instance.getTimeInMillis();
    }
}
