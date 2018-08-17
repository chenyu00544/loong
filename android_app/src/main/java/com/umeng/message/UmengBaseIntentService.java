package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.taobao.agoo.TaobaoBaseIntentService;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.l;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

public abstract class UmengBaseIntentService extends TaobaoBaseIntentService {
    private static final String a = UmengBaseIntentService.class.getName();

    protected void onMessage(Context context, Intent intent) {
        if (Process.getElapsedCpuTime() < 3000) {
            UmLog.i(a, "App is launched by push message");
            PushAgent.setAppLaunchByMessage();
        }
        String stringExtra = intent.getStringExtra("body");
        UmLog.d(a, "onMessage():[" + stringExtra + "]");
        try {
            UMessage uMessage = new UMessage(new JSONObject(stringExtra));
            uMessage.message_id = intent.getStringExtra("id");
            uMessage.task_id = intent.getStringExtra(AgooConstants.MESSAGE_TASK_ID);
            UTrack.getInstance(getApplicationContext()).trackMsgArrival(uMessage);
            l.a(context).a(uMessage.msg_id, uMessage.display_type);
            l.a(context).a(uMessage.message_id, uMessage.task_id, uMessage.display_type);
            if (TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type)) {
                String stringExtra2 = intent.getStringExtra("id");
                String stringExtra3 = intent.getStringExtra(AgooConstants.MESSAGE_TASK_ID);
                Intent intent2 = new Intent();
                intent2.setPackage(context.getPackageName());
                intent2.setAction(MsgConstant.MESSAGE_AUTOUPDATE_HANDLER_ACTION);
                intent2.putExtra("body", stringExtra);
                intent2.putExtra("id", stringExtra2);
                intent2.putExtra(AgooConstants.MESSAGE_TASK_ID, stringExtra3);
                context.startService(intent2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            UmLog.d(a, e.toString());
        }
    }

    protected void onError(Context context, String str) {
        UmLog.d(a, "onError()[" + str + "]");
    }

    protected void onRegistered(Context context, String str) {
    }

    protected void onUnregistered(Context context, String str) {
    }
}
