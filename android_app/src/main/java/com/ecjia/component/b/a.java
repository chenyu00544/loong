package com.ecjia.component.b;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import com.ecjia.a.ab;
import com.ecjia.a.q;
import com.ecjia.hamster.adapter.aj;
import com.ecjia.hamster.model.z;
import com.ecmoban.android.missmall.ECJiaPushActivity;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.message.entity.UMessage;
import java.util.Calendar;

/* compiled from: ECJiaAlarmReceiver */
public class a extends BroadcastReceiver {
    String a = "ECJiaCheckInNotificationService";
    NotificationManager b;
    Notification c;

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("sendNotification")) {
            q.a(this.a + "接收到了消息");
            long j = context.getSharedPreferences(Constants.KEY_USER_ID, 0).getLong("signin_time", 0);
            if (j == 0) {
                b(context);
                a(context);
                q.a(this.a + "从未签到,显示通知栏");
                return;
            }
            q.a(this.a + "已签到过");
            if (ab.a(j, System.currentTimeMillis())) {
                q.a(this.a + "上次签到是同一天");
                return;
            }
            q.a(this.a + "上次签到不是同一天");
            if (ab.a(context.getSharedPreferences(Constants.KEY_USER_ID, 0).getLong("issignin_notify", 0), System.currentTimeMillis())) {
                c(context);
                q.a(this.a + "已经在状态栏提醒过了，明天再提醒");
                return;
            }
            q.a(this.a + "状态栏没有，显示通知栏");
            b(context);
            a(context);
        }
    }

    private void b(Context context) {
        context.getSharedPreferences(Constants.KEY_USER_ID, 0).edit().putLong("issignin_notify", System.currentTimeMillis()).commit();
        z zVar = new z();
        zVar.m("签到送积分,快来啊！！！");
        zVar.n("人生路漫漫，签到NO1,今天你签了吗？");
        zVar.l("");
        zVar.h("");
        zVar.i("");
        zVar.j("");
        zVar.k("user_check_in");
        zVar.f("user_check_in");
        aj.a(context).a(zVar);
    }

    private void c(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent(context, a.class);
        intent.setAction("sendNotification");
        alarmManager.cancel(PendingIntent.getBroadcast(context, 0, intent, 0));
        q.a(this.a + "明天8点：" + a() + "\n当前：" + SystemClock.elapsedRealtime() + "\n相差：" + (a() - SystemClock.elapsedRealtime()));
        intent = new Intent(context, a.class);
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

    void a(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager;
        PackageManager packageManager2;
        String str;
        q.a(this.a + "接收到了消息，弹出提示框");
        this.b = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        PendingIntent activity = PendingIntent.getActivity(context, 0, new Intent(context, ECJiaPushActivity.class), 0);
        Builder builder = new Builder(context);
        try {
            PackageManager packageManager3 = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager3.getApplicationInfo(context.getPackageName(), 0);
                packageManager = packageManager3;
            } catch (NameNotFoundException e) {
                packageManager2 = packageManager3;
                packageManager = packageManager2;
                applicationInfo = null;
                str = (String) packageManager.getApplicationLabel(applicationInfo);
                builder.setPriority(0);
                builder.setOngoing(true);
                builder.setTicker(str + ",签到送积分！！！");
                builder.setContentTitle("签到送积分,快来啊！！！");
                builder.setContentInfo("人生路漫漫，签到NO1,今天你签了吗？");
                builder.setWhen(System.currentTimeMillis());
                builder.setDefaults(-1);
                builder.setContentIntent(activity);
                builder.setSmallIcon(R.drawable.ecmoban_logo);
                builder.setAutoCancel(true);
                this.c = builder.build();
                this.b.notify("nihao", 0, this.c);
            }
        } catch (NameNotFoundException e2) {
            packageManager2 = null;
            packageManager = packageManager2;
            applicationInfo = null;
            str = (String) packageManager.getApplicationLabel(applicationInfo);
            builder.setPriority(0);
            builder.setOngoing(true);
            builder.setTicker(str + ",签到送积分！！！");
            builder.setContentTitle("签到送积分,快来啊！！！");
            builder.setContentInfo("人生路漫漫，签到NO1,今天你签了吗？");
            builder.setWhen(System.currentTimeMillis());
            builder.setDefaults(-1);
            builder.setContentIntent(activity);
            builder.setSmallIcon(R.drawable.ecmoban_logo);
            builder.setAutoCancel(true);
            this.c = builder.build();
            this.b.notify("nihao", 0, this.c);
        }
        str = (String) packageManager.getApplicationLabel(applicationInfo);
        builder.setPriority(0);
        builder.setOngoing(true);
        builder.setTicker(str + ",签到送积分！！！");
        builder.setContentTitle("签到送积分,快来啊！！！");
        builder.setContentInfo("人生路漫漫，签到NO1,今天你签了吗？");
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(-1);
        builder.setContentIntent(activity);
        builder.setSmallIcon(R.drawable.ecmoban_logo);
        builder.setAutoCancel(true);
        this.c = builder.build();
        this.b.notify("nihao", 0, this.c);
    }
}
