package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.proguard.h;

public class NetworkReceiver extends BroadcastReceiver {
    private static final String a = "com.umeng.message.UmengLocationService";

    public void onReceive(Context context, Intent intent) {
        boolean isNetworkConnected = UtilityImpl.isNetworkConnected(context);
        boolean c = h.c(context, a);
        if (!isNetworkConnected && c) {
            Intent intent2 = new Intent();
            intent2.setAction(MsgConstant.MESSAGE_LBS_ACTION);
            intent2.setPackage(context.getPackageName());
            intent2.putExtra("stopTimer", true);
            context.startService(intent2);
        }
        if (isNetworkConnected && c) {
            Intent intent3 = new Intent();
            intent3.setAction(MsgConstant.MESSAGE_LBS_ACTION);
            intent3.setPackage(context.getPackageName());
            context.startService(intent3);
        }
    }
}
