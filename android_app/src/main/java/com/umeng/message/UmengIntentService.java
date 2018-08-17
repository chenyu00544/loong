package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import com.baidu.location.h.e;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.entity.UMessage;
import java.util.Map.Entry;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

public class UmengIntentService extends UmengBaseIntentService {
    private static final String a = UmengIntentService.class.getName();

    protected void onMessage(final Context context, Intent intent) {
        super.onMessage(context, intent);
        String str = "";
        str = "";
        str = "";
        try {
            str = intent.getStringExtra("body");
            String stringExtra = intent.getStringExtra("id");
            String stringExtra2 = intent.getStringExtra(AgooConstants.MESSAGE_TASK_ID);
            final UMessage uMessage = new UMessage(new JSONObject(str));
            if (!UMessage.DISPLAY_TYPE_PULLAPP.equals(uMessage.display_type)) {
                String pushIntentServiceClass = MessageSharedPrefs.getInstance(context).getPushIntentServiceClass();
                if (pushIntentServiceClass.equalsIgnoreCase("")) {
                    Intent intent2 = new Intent();
                    intent2.setPackage(context.getPackageName());
                    intent2.setAction(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION);
                    intent2.putExtra("body", str);
                    intent2.putExtra("id", stringExtra);
                    intent2.putExtra(AgooConstants.MESSAGE_TASK_ID, stringExtra2);
                    context.startService(intent2);
                    return;
                }
                Intent intent3 = new Intent();
                intent3.setClassName(context, pushIntentServiceClass);
                intent3.setPackage(context.getPackageName());
                intent3.putExtra("body", str);
                intent3.putExtra("id", stringExtra);
                intent3.putExtra(AgooConstants.MESSAGE_TASK_ID, stringExtra2);
                context.startService(intent3);
            } else if (UmengMessageDeviceConfig.isServiceWork(context, uMessage.pulled_service, uMessage.pulled_package)) {
                UTrack.getInstance(context).trackMsgPulled(uMessage, 52);
            } else if (UmengMessageDeviceConfig.getDataData(uMessage.pulled_package)) {
                Intent intent4 = new Intent();
                intent4.setClassName(uMessage.pulled_package, uMessage.pulled_service);
                a(intent4, uMessage);
                startService(intent4);
                new Thread(new Runnable(this) {
                    final /* synthetic */ UmengIntentService c;

                    public void run() {
                        try {
                            Thread.sleep(e.kg);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (UmengMessageDeviceConfig.isServiceWork(context, uMessage.pulled_service, uMessage.pulled_package)) {
                            UTrack.getInstance(context).trackMsgPulled(uMessage, 51);
                        } else {
                            UTrack.getInstance(context).trackMsgPulled(uMessage, 50);
                        }
                    }
                }).start();
            } else {
                UTrack.getInstance(context).trackMsgPulled(uMessage, 53);
            }
        } catch (Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
        }
    }

    private Intent a(Intent intent, UMessage uMessage) {
        if (!(intent == null || uMessage == null || uMessage.extra == null)) {
            for (Entry entry : uMessage.extra.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (str != null) {
                    intent.putExtra(str, str2);
                }
            }
        }
        return intent;
    }
}
