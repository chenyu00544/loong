package com.vcvb.chenyu.shop.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.vcvb.chenyu.shop.R;

public class Receiver extends BroadcastReceiver {
    private NotificationManager notificationManager;
    private Notification.Builder builder;

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println(intent.getAction());
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(context);
        builder.setContentTitle("adfafadfafdadsf");
        builder.setContentText("dfgsgsfg");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationManager.notify(1001, builder.build());
        }
    }
}
