package com.taobao.accs.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.IACCSManager;
import com.taobao.accs.base.TaoBaseService.ConnectInfo;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public abstract class AccsAbstractDataListener implements AccsDataListener {
    private static final String TAG = "AccsAbstractDataListener";

    public void onConnected(ConnectInfo connectInfo) {
    }

    public void onDisconnected(ConnectInfo connectInfo) {
    }

    public void onAntiBrush(boolean z, ExtraInfo extraInfo) {
    }

    public static int onReceiveData(Context context, Intent intent, AccsDataListener accsDataListener) {
        Throwable e;
        if (accsDataListener == null || context == null) {
            ALog.e(TAG, "onReceiveData listener or context null", new Object[0]);
        } else if (intent != null) {
            String str = "";
            try {
                int intExtra = intent.getIntExtra("command", -1);
                int intExtra2 = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                String stringExtra = intent.getStringExtra(Constants.KEY_USER_ID);
                String stringExtra2 = intent.getStringExtra(Constants.KEY_DATA_ID);
                String stringExtra3 = intent.getStringExtra(Constants.KEY_SERVICE_ID);
                try {
                    if (ALog.isPrintLog(Level.I)) {
                        ALog.i(TAG, "onReceiveData dataId:" + stringExtra2 + " serviceId:" + stringExtra3 + " command:" + intExtra, new Object[0]);
                    }
                    if (intExtra > 0) {
                        UTMini.getInstance().commitEvent(66001, "MsgToBuss5", "commandId=" + intExtra, "serviceId=" + stringExtra3 + " dataId=" + stringExtra2, Integer.valueOf(Constants.SDK_VERSION_CODE));
                        b.a("accs", BaseMonitor.COUNT_POINT_TO_BUSS, "3commandId=" + intExtra + "serviceId=" + stringExtra3, 0.0d);
                        boolean booleanExtra;
                        switch (intExtra) {
                            case 5:
                                accsDataListener.onBind(stringExtra3, intExtra2, getExtraInfo(intent));
                                break;
                            case 6:
                                accsDataListener.onUnbind(stringExtra3, intExtra2, getExtraInfo(intent));
                                break;
                            case 100:
                                String stringExtra4 = intent.getStringExtra(Constants.KEY_DATA_ID);
                                if (!TextUtils.equals(Constants.SEND_TYPE_RES, intent.getStringExtra(Constants.KEY_SEND_TYPE))) {
                                    accsDataListener.onSendData(stringExtra3, stringExtra4, intExtra2, getExtraInfo(intent));
                                    break;
                                }
                                accsDataListener.onResponse(stringExtra3, stringExtra4, intExtra2, intent.getByteArrayExtra("data"), getExtraInfo(intent));
                                break;
                            case 101:
                                byte[] byteArrayExtra = intent.getByteArrayExtra("data");
                                booleanExtra = intent.getBooleanExtra(Constants.KEY_NEED_BUSINESS_ACK, false);
                                if (byteArrayExtra == null) {
                                    ALog.e(TAG, "COMMAND_RECEIVE_DATA msg null", new Object[0]);
                                    b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, stringExtra3, "1", "COMMAND_RECEIVE_DATA msg null");
                                    break;
                                }
                                String stringExtra5 = intent.getStringExtra(Constants.KEY_DATA_ID);
                                if (ALog.isPrintLog(Level.D)) {
                                    ALog.d(TAG, "COMMAND_RECEIVE_DATA onData dataId:" + stringExtra5 + " serviceId:" + stringExtra3, new Object[0]);
                                }
                                ExtraInfo extraInfo = getExtraInfo(intent);
                                if (booleanExtra) {
                                    ALog.i(TAG, "try to send biz ack dataId " + stringExtra5, new Object[0]);
                                    sendBusinessAck(context, intent, stringExtra5, extraInfo.extHeader);
                                }
                                accsDataListener.onData(stringExtra3, stringExtra, stringExtra5, byteArrayExtra, extraInfo);
                                break;
                            case 103:
                                booleanExtra = intent.getBooleanExtra(Constants.KEY_CONNECT_AVAILABLE, false);
                                str = intent.getStringExtra("host");
                                String stringExtra6 = intent.getStringExtra(Constants.KEY_ERROR_DETAIL);
                                boolean booleanExtra2 = intent.getBooleanExtra(Constants.KEY_TYPE_INAPP, false);
                                boolean booleanExtra3 = intent.getBooleanExtra(Constants.KEY_CENTER_HOST, false);
                                if (!TextUtils.isEmpty(str)) {
                                    if (!booleanExtra) {
                                        accsDataListener.onDisconnected(new ConnectInfo(str, booleanExtra2, booleanExtra3, intExtra2, stringExtra6));
                                        break;
                                    }
                                    accsDataListener.onConnected(new ConnectInfo(str, booleanExtra2, booleanExtra3));
                                    break;
                                }
                                break;
                            case 104:
                                booleanExtra = intent.getBooleanExtra(Constants.KEY_ANTI_BRUSH_RET, false);
                                ALog.e(TAG, "anti brush result:" + booleanExtra, new Object[0]);
                                accsDataListener.onAntiBrush(booleanExtra, null);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    str = stringExtra3;
                    e.printStackTrace();
                    b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, str, "1", "callback error" + e.toString());
                    ALog.e(TAG, "onReceiveData", e, new Object[0]);
                    return 2;
                }
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, str, "1", "callback error" + e.toString());
                ALog.e(TAG, "onReceiveData", e, new Object[0]);
                return 2;
            }
        }
        return 2;
    }

    private static Map<ExtHeaderType, String> getExtHeader(Intent intent) {
        Map<ExtHeaderType, String> map = null;
        if (intent != null) {
            try {
                for (ExtHeaderType extHeaderType : ExtHeaderType.values()) {
                    CharSequence stringExtra = intent.getStringExtra(extHeaderType.toString());
                    if (!TextUtils.isEmpty(stringExtra)) {
                        if (map == null) {
                            map = new HashMap();
                        }
                        map.put(extHeaderType, stringExtra);
                    }
                }
            } catch (Exception e) {
                ALog.e(TAG, e.toString(), new Object[0]);
            }
        }
        return map;
    }

    private static ExtraInfo getExtraInfo(Intent intent) {
        Map extHeader = getExtHeader(intent);
        Object stringExtra = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
        Object stringExtra2 = intent.getStringExtra("host");
        int intExtra = intent.getIntExtra(Constants.KEY_CONN_TYPE, 0);
        ExtraInfo extraInfo = new ExtraInfo();
        extraInfo.connType = intExtra;
        if (!(extHeader == null && TextUtils.isEmpty(stringExtra) && TextUtils.isEmpty(stringExtra2))) {
            extraInfo.extHeader = extHeader;
            extraInfo.fromPackage = stringExtra;
            extraInfo.fromHost = stringExtra2;
        }
        return extraInfo;
    }

    private static void sendBusinessAck(Context context, Intent intent, String str, Map<ExtHeaderType, String> map) {
        try {
            ALog.i(TAG, "sendBusinessAck", Constants.KEY_DATA_ID, str);
            if (intent != null) {
                String stringExtra = intent.getStringExtra("host");
                String stringExtra2 = intent.getStringExtra("source");
                String stringExtra3 = intent.getStringExtra(Constants.KEY_TARGET);
                String stringExtra4 = intent.getStringExtra("appKey");
                short shortExtra = intent.getShortExtra(Constants.KEY_FLAGS, (short) 0);
                IACCSManager accsInstance = ACCSManager.getAccsInstance(context, stringExtra4);
                if (accsInstance != null) {
                    accsInstance.sendBusinessAck(stringExtra3, stringExtra2, str, shortExtra, stringExtra, map);
                    b.a("accs", BaseMonitor.COUNT_BUSINESS_ACK_SUCC, "", 0.0d);
                    return;
                }
                b.a("accs", BaseMonitor.COUNT_BUSINESS_ACK_FAIL, "no acsmgr", 0.0d);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "sendBusinessAck", th, new Object[0]);
            b.a("accs", BaseMonitor.COUNT_BUSINESS_ACK_FAIL, th.toString(), 0.0d);
        }
    }
}
