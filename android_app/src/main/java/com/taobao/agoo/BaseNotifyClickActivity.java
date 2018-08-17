package com.taobao.agoo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.common.a;
import com.taobao.accs.utl.ALog;
import java.util.HashSet;
import java.util.Set;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.MsgDO;
import org.android.agoo.control.AgooFactory;
import org.android.agoo.control.NotifManager;

/* compiled from: Taobao */
public class BaseNotifyClickActivity extends Activity {
    private static final String TAG = "accs.BaseNotifyClickActivity";
    private static final String TAOBAO_PACKAGE_NAME = "com.taobao.taobao";
    private static Set<INotifyListener> notifyListeners;
    private AgooFactory agooFactory;
    private String msgSource;
    private NotifManager notifyManager;

    /* compiled from: Taobao */
    public interface INotifyListener {
        String getMsgSource();

        String parseMsgFromIntent(Intent intent);
    }

    public static void addNotifyListener(INotifyListener iNotifyListener) {
        if (notifyListeners == null) {
            notifyListeners = new HashSet();
        }
        notifyListeners.add(iNotifyListener);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ALog.i(TAG, "onCreate", new Object[0]);
        buildMessage(getIntent());
    }

    protected void onStart() {
        super.onStart();
    }

    public void onMessage(Intent intent) {
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ALog.i(TAG, "onNewIntent", new Object[0]);
        buildMessage(intent);
    }

    private void buildMessage(Intent intent) {
        a.a(new a(this, intent));
    }

    private String parseMsgByThirdPush(Intent intent) {
        String str;
        String str2 = null;
        if (notifyListeners == null || notifyListeners.size() <= 0) {
            ALog.e(TAG, "no impl to parse intent!", new Object[0]);
            str = null;
        } else {
            for (INotifyListener iNotifyListener : notifyListeners) {
                str = iNotifyListener.parseMsgFromIntent(intent);
                this.msgSource = iNotifyListener.getMsgSource();
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(this.msgSource)) {
                    break;
                }
                str2 = str;
            }
            str = str2;
        }
        ALog.i(TAG, "parseMsgByThirdPush", "result", str, "msgSource", this.msgSource);
        return str;
    }

    private void reportClickNotifyMsg(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("id");
            String stringExtra2 = intent.getStringExtra(AgooConstants.MESSAGE_SOURCE);
            String stringExtra3 = intent.getStringExtra(AgooConstants.MESSAGE_REPORT);
            String stringExtra4 = intent.getStringExtra(AgooConstants.MESSAGE_EXT);
            MsgDO msgDO = new MsgDO();
            msgDO.msgIds = stringExtra;
            msgDO.extData = stringExtra4;
            msgDO.messageSource = stringExtra2;
            msgDO.reportStr = stringExtra3;
            msgDO.msgStatus = "8";
            ALog.i(TAG, "reportClickNotifyMsg messageId:" + stringExtra + " source:" + stringExtra2 + " reportStr:" + stringExtra3 + " status:" + msgDO.msgStatus, new Object[0]);
            this.notifyManager.report(msgDO, null);
        } catch (Exception e) {
            ALog.e(TAG, "reportClickNotifyMsg exception: " + e, new Object[0]);
        }
    }
}
