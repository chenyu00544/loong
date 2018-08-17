package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;
import java.util.Map;
import org.android.agoo.accs.AgooService;

/* compiled from: Taobao */
public class a extends e {
    private static AgooService a = new AgooService();
    private static final Map<String, String> b = new AliyunMsgDistribute$1();

    protected boolean a(int i, String str) {
        return false;
    }

    protected boolean a(Context context, String str, String str2, Intent intent, IAppReceiver iAppReceiver) {
        if (UtilityImpl.isMainProcess(context)) {
            return false;
        }
        ALog.i("AliyunMsgDistribute", "start MsgDistributeService", Constants.KEY_DATA_ID, str2);
        intent.setClassName(intent.getPackage(), b());
        context.startService(intent);
        return true;
    }

    protected void a(Context context, IAppReceiver iAppReceiver, Intent intent, String str, String str2, int i, int i2) {
        AccsDataListener listener = GlobalClientInfo.getInstance(context).getListener(str);
        if (b.containsKey(str)) {
            a.a();
            a.b();
        } else if (listener != null) {
            AccsAbstractDataListener.onReceiveData(context, intent, listener);
        } else {
            ALog.e("AliyunMsgDistribute", "callback is null dataId:" + str2 + " serviceId：" + str + " command:" + i, new Object[0]);
            b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, str, "1", "service is null");
        }
        UTMini.getInstance().commitEvent(66001, "MsgToBuss", "commandId=" + i, "serviceId=" + str + " errorCode=" + i2 + " dataId=" + str2, Integer.valueOf(Constants.SDK_VERSION_CODE));
        b.a("accs", BaseMonitor.COUNT_POINT_TO_BUSS, "2commandId=" + i + "serviceId=" + str, 0.0d);
    }

    protected void a(Context context, Map<String, IAppReceiver> map, Intent intent, int i, int i2) {
        super.a(context, (Map) map, intent, i, i2);
    }

    protected String a() {
        return "com.alibaba.sdk.android.push.ChannelService";
    }

    protected String b() {
        return "com.alibaba.sdk.android.push.MsgService";
    }
}
