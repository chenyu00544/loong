package org.android.agoo.control;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.IACCSManager;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.a;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.MsgDO;
import org.android.agoo.common.b;
import org.android.agoo.common.e;
import org.json.JSONObject;

/* compiled from: Taobao */
public class NotifManager {
    private static final String ACK_MESSAGE = "accs.ackMessage";
    private static final int EVENT_ID = 66001;
    private static final String TAG = "NotifManager";
    private static Context mContext = null;
    private ScheduledThreadPoolExecutor mThreadPool;

    public void init(Context context) {
        mContext = context;
        this.mThreadPool = e.a();
    }

    public void handlerACKMessage(MsgDO msgDO, ExtraInfo extraInfo) {
        if (msgDO != null) {
            if (TextUtils.isEmpty(msgDO.msgIds) && TextUtils.isEmpty(msgDO.removePacks) && TextUtils.isEmpty(msgDO.errorCode)) {
                UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, ACK_MESSAGE, a.b(mContext), "handlerACKMessageRetuen", "msgids=" + msgDO.msgIds + ",removePacks=" + msgDO.removePacks + ",errorCode=" + msgDO.errorCode);
                return;
            }
            try {
                Map hashMap = new HashMap();
                hashMap.put("api", AgooConstants.AGOO_SERVICE_AGOOACK);
                hashMap.put("id", msgDO.msgIds + "@" + msgDO.messageSource);
                if (!TextUtils.isEmpty(msgDO.removePacks)) {
                    hashMap.put("del_pack", msgDO.removePacks);
                }
                if (!TextUtils.isEmpty(msgDO.errorCode)) {
                    hashMap.put("ec", msgDO.errorCode);
                }
                if (!TextUtils.isEmpty(msgDO.type)) {
                    hashMap.put("type", msgDO.type);
                }
                if (!TextUtils.isEmpty(msgDO.extData)) {
                    hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND, msgDO.extData);
                }
                hashMap.put("appkey", b.a(mContext));
                hashMap.put("utdid", a.b(mContext));
                byte[] bytes = new JSONObject(hashMap).toString().getBytes("UTF-8");
                UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, ACK_MESSAGE, a.b(mContext), "handlerACKMessageSendData", msgDO.msgIds);
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ACK, "handlerACKMessage", 0.0d);
                AccsRequest accsRequest = new AccsRequest(null, AgooConstants.AGOO_SERVICE_AGOOACK, bytes, null, null, null, null);
                if (msgDO != null) {
                    accsRequest.setTag(msgDO.msgIds);
                }
                ALog.i(TAG, "handlerACKMessage,endRequest,dataId=" + ACCSManager.getAccsInstance(mContext, b.a(mContext)).sendPushResponse(mContext, accsRequest, extraInfo), new Object[0]);
            } catch (Throwable th) {
                Throwable th2 = th;
                if (ALog.isPrintLog(Level.E)) {
                    ALog.e(TAG, "handlerACKMessage Throwable,msgIds=" + msgDO.msgIds + ",type=" + msgDO.type + ",e=" + th2.toString(), new Object[0]);
                }
                UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, ACK_MESSAGE, a.b(mContext), "handlerACKMessageExceptionFailed", th2.toString());
            }
        }
    }

    public void report(MsgDO msgDO, ExtraInfo extraInfo) {
        if (!TextUtils.isEmpty(msgDO.reportStr)) {
            try {
                if (Integer.parseInt(msgDO.reportStr) >= -1) {
                    reportMethod(msgDO, extraInfo);
                    if (!msgDO.isFromCache) {
                        com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ACK, msgDO.msgStatus, 0.0d);
                    }
                }
            } catch (Throwable th) {
                ALog.e(TAG, "[report] is error", th, new Object[0]);
            }
        }
    }

    public void reportNotifyMessage(MsgDO msgDO) {
        if (msgDO != null) {
            try {
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_REPORT_ID, msgDO.msgIds, 0.0d);
                String sendRequest = ACCSManager.getAccsInstance(mContext, b.a(mContext)).sendRequest(mContext, new AccsRequest(null, AgooConstants.AGOO_SERVICE_AGOOACK, convertMsgToBytes(msgDO), null, null, null, null));
                if (ALog.isPrintLog(Level.E)) {
                    ALog.e(TAG, "reportNotifyMessage", Constants.KEY_DATA_ID, sendRequest, "status", msgDO.msgStatus);
                }
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_CLICK, msgDO.msgStatus, 0.0d);
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ACK, msgDO.msgStatus, 0.0d);
            } catch (Throwable th) {
                ALog.e(TAG, "[reportNotifyMessage] is error", th, new Object[0]);
                UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "reportMethod", a.b(mContext), th.toString());
            }
        }
    }

    private byte[] convertMsgToBytes(MsgDO msgDO) throws UnsupportedEncodingException {
        Map hashMap = new HashMap();
        hashMap.put("api", "agooReport");
        hashMap.put("id", msgDO.msgIds + "@" + msgDO.messageSource);
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND, msgDO.extData);
        hashMap.put("status", msgDO.msgStatus);
        if (!TextUtils.isEmpty(msgDO.errorCode)) {
            hashMap.put("ec", msgDO.errorCode);
        }
        if (!TextUtils.isEmpty(msgDO.type)) {
            hashMap.put("type", msgDO.type);
        }
        if (!TextUtils.isEmpty(msgDO.fromPkg)) {
            hashMap.put("fromPkg", msgDO.fromPkg);
        }
        if (!TextUtils.isEmpty(msgDO.fromAppkey)) {
            hashMap.put(AgooConstants.MESSAGE_FROM_APPKEY, msgDO.fromAppkey);
        }
        if (!TextUtils.isEmpty(msgDO.notifyEnable)) {
            hashMap.put("notifyEnable", msgDO.notifyEnable);
        }
        if (!TextUtils.isEmpty(msgDO.extData)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND, msgDO.extData);
        }
        hashMap.put("isStartProc", Boolean.toString(msgDO.isStartProc));
        hashMap.put("appkey", b.a(mContext));
        hashMap.put("utdid", a.b(mContext));
        return new JSONObject(hashMap).toString().getBytes("UTF-8");
    }

    private void reportMethod(MsgDO msgDO, ExtraInfo extraInfo) {
        try {
            AccsRequest accsRequest = new AccsRequest(null, AgooConstants.AGOO_SERVICE_AGOOACK, convertMsgToBytes(msgDO), null, null, null, null);
            if (msgDO != null) {
                accsRequest.setTag(msgDO.msgIds);
            }
            String sendPushResponse = ACCSManager.getAccsInstance(mContext, b.a(mContext)).sendPushResponse(mContext, accsRequest, extraInfo);
            if (ALog.isPrintLog(Level.E)) {
                ALog.e(TAG, AgooConstants.MESSAGE_REPORT, Constants.KEY_DATA_ID, sendPushResponse, "status", msgDO.msgStatus, "errorcode", msgDO.errorCode);
            }
        } catch (Throwable th) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "reportMethod", a.b(mContext), th.toString());
        }
    }

    public void reportThirdPushToken(String str, String str2, boolean z) {
        try {
            String sendData;
            Map hashMap = new HashMap();
            hashMap.put("thirdTokenType", str2);
            hashMap.put("token", str);
            hashMap.put("appkey", b.a(mContext));
            hashMap.put("utdid", a.b(mContext));
            ALog.d(TAG, "report,utdid=" + a.b(mContext) + ",regId=" + str + ",type=" + str2, new Object[0]);
            AccsRequest accsRequest = new AccsRequest(null, "agooTokenReport", new JSONObject(hashMap).toString().getBytes("UTF-8"), null, null, null, null);
            IACCSManager accsInstance = ACCSManager.getAccsInstance(mContext, b.a(mContext));
            if (z) {
                sendData = accsInstance.sendData(mContext, accsRequest);
            } else {
                sendData = accsInstance.sendPushResponse(mContext, accsRequest, new ExtraInfo());
            }
            if (ALog.isPrintLog(Level.D)) {
                ALog.i(TAG, "reportThirdPushToken,dataId=" + sendData + ",regId=" + str + ",type=" + str2, new Object[0]);
            }
        } catch (Throwable th) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "reportThirdPushToken", a.b(mContext), th.toString());
            if (ALog.isPrintLog(Level.E)) {
                ALog.e(TAG, "[report] is error", th, new Object[0]);
            }
        }
    }

    public void reportThirdPushToken(String str, String str2) {
        reportThirdPushToken(str, str2, true);
    }

    public void doUninstall(String str, boolean z) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("pack", str);
            hashMap.put("appkey", b.a(mContext));
            hashMap.put("utdid", a.b(mContext));
            ACCSManager.getAccsInstance(mContext, b.a(mContext)).sendPushResponse(mContext, new AccsRequest(null, "agooKick", new JSONObject(hashMap).toString().getBytes("UTF-8"), null, null, null, null), new ExtraInfo());
        } catch (Throwable th) {
            ALog.e(TAG, "[doUninstall] is error", th, new Object[0]);
        }
    }

    public void pingApp(String str, String str2, String str3, int i) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            if (ALog.isPrintLog(Level.I)) {
                ALog.i(TAG, "pingApp [print param],percent=" + i + ",pack=" + str2 + ",service=" + str3 + ",action=" + str, new Object[0]);
            }
            this.mThreadPool.execute(new g(this, i, str2, str, str3));
        }
    }

    private boolean isAppInstalled(String str) {
        PackageInfo packageInfo;
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            packageInfo = mContext.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return false;
            }
            ALog.i(TAG, "isAppInstalled true..", new Object[0]);
            return true;
        } catch (Throwable th) {
            packageInfo = null;
        }
    }

    private String getVersion(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "null";
            }
            String str2 = mContext.getPackageManager().getPackageInfo(str, 0).versionName;
            ALog.d(TAG, "getVersion###版本号为 : " + str2, new Object[0]);
            return str2;
        } catch (Throwable th) {
            return "null";
        }
    }
}
