package com.vcvb.chenyu.shop.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("Action.Alarm".equals(intent.getAction())) {
            System.out.println("闹钟事件发生了！");
        }
    }
}
