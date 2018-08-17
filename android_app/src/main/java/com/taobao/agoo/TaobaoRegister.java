package com.taobao.agoo;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.ACCSClient;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.AccsClientConfig.Builder;
import com.taobao.accs.AccsClientConfig.ENV;
import com.taobao.accs.AccsException;
import com.taobao.accs.IACCSManager;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.a;
import com.taobao.agoo.a.a.c;
import com.taobao.agoo.a.b;
import com.umeng.analytics.pro.x;
import org.android.agoo.accs.AgooService;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.CallBack;
import org.android.agoo.common.MsgDO;
import org.android.agoo.control.AgooFactory;
import org.android.agoo.control.NotifManager;

/* compiled from: Taobao */
public final class TaobaoRegister {
    private static final int EVENT_ID = 66001;
    static final String PREFERENCES = "Agoo_AppStore";
    static final String PROPERTY_APP_NOTIFICATION_CUSTOM_SOUND = "app_notification_custom_sound";
    static final String PROPERTY_APP_NOTIFICATION_ICON = "app_notification_icon";
    static final String PROPERTY_APP_NOTIFICATION_SOUND = "app_notification_sound";
    static final String PROPERTY_APP_NOTIFICATION_VIBRATE = "app_notification_vibrate";
    private static final String SERVICEID = "agooSend";
    protected static final String TAG = "TaobaoRegister";
    private static b mRequestListener;

    private TaobaoRegister() {
        throw new UnsupportedOperationException();
    }

    public static void register(Context context, String str, String str2, String str3, IRegister iRegister) throws AccsException {
        if (context == null || TextUtils.isEmpty(str)) {
            ALog.e(TAG, "register context null", new Object[0]);
            return;
        }
        ALog.i(TAG, c.JSON_CMD_REGISTER, "appKey", str);
        Context applicationContext = context.getApplicationContext();
        org.android.agoo.common.b.a(context, str);
        a.b = str2;
        if (!TextUtils.isEmpty(str2)) {
            com.taobao.accs.client.a.a = 2;
        }
        if (AccsClientConfig.getConfig(str) == null) {
            new Builder().setAppKey(str).setAppSecret(str2).build();
        } else {
            ALog.i(TAG, "config exist", "config", AccsClientConfig.getConfig(str).toString());
        }
        IACCSManager accsInstance = ACCSManager.getAccsInstance(context, str);
        accsInstance.bindApp(applicationContext, str, str2, str3, new b(applicationContext, iRegister, accsInstance, str, str3));
    }

    public static void setAlias(Context context, String str, ICallback iCallback) {
        ALog.i(TAG, com.taobao.agoo.a.a.a.JSON_CMD_SETALIAS, "alias", str);
        Object f = org.android.agoo.common.b.f(context);
        String a = org.android.agoo.common.b.a(context);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(f) || context == null || TextUtils.isEmpty(str)) {
            if (iCallback != null) {
                iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "input params null!!");
            }
            ALog.e(TAG, "setAlias param null", "appkey", a, "deviceId", f, "alias", str, x.aI, context);
            return;
        }
        try {
            if (mRequestListener == null) {
                mRequestListener = new b(context.getApplicationContext());
            }
            b bVar = mRequestListener;
            if (b.b.d(str)) {
                ALog.i(TAG, "Alias already set", "alias", str);
                if (iCallback != null) {
                    iCallback.onSuccess();
                    return;
                }
                return;
            }
            IACCSManager accsInstance = ACCSManager.getAccsInstance(context, a);
            b bVar2 = mRequestListener;
            if (b.b.b(context.getPackageName())) {
                accsInstance.registerDataListener(context, TaobaoConstants.SERVICE_ID_DEVICECMD, mRequestListener);
                CharSequence sendRequest = accsInstance.sendRequest(context, new AccsRequest(null, TaobaoConstants.SERVICE_ID_DEVICECMD, com.taobao.agoo.a.a.a.a(a, f, str), null));
                if (TextUtils.isEmpty(sendRequest)) {
                    if (iCallback != null) {
                        iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "accs channel disabled!");
                    }
                } else if (iCallback != null) {
                    iCallback.extra = str;
                    mRequestListener.a.put(sendRequest, iCallback);
                }
            } else if (iCallback != null) {
                iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "bindApp first!!");
            }
        } catch (Throwable th) {
            ALog.e(TAG, com.taobao.agoo.a.a.a.JSON_CMD_SETALIAS, th, new Object[0]);
        }
    }

    public static void removeAlias(Context context, ICallback iCallback) {
        ALog.i(TAG, com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS, new Object[0]);
        try {
            Object f = org.android.agoo.common.b.f(context);
            Object g = org.android.agoo.common.b.g(context);
            String a = org.android.agoo.common.b.a(context);
            if (TextUtils.isEmpty(a) || TextUtils.isEmpty(f) || context == null || TextUtils.isEmpty(g)) {
                if (iCallback != null) {
                    iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "input params null!!");
                }
                ALog.e(TAG, "setAlias param null", "appkey", a, "deviceId", f, com.taobao.agoo.a.a.a.JSON_PUSH_USER_TOKEN, g, x.aI, context);
                return;
            }
            IACCSManager accsInstance = ACCSManager.getAccsInstance(context, a);
            if (mRequestListener == null) {
                mRequestListener = new b(context.getApplicationContext());
                accsInstance.registerDataListener(context, TaobaoConstants.SERVICE_ID_DEVICECMD, mRequestListener);
            }
            CharSequence sendRequest = accsInstance.sendRequest(context, new AccsRequest(null, TaobaoConstants.SERVICE_ID_DEVICECMD, com.taobao.agoo.a.a.a.b(a, f, g), null));
            if (TextUtils.isEmpty(sendRequest)) {
                if (iCallback != null) {
                    iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "accs channel disabled!");
                }
            } else if (iCallback != null) {
                mRequestListener.a.put(sendRequest, iCallback);
            }
        } catch (Throwable th) {
            ALog.e(TAG, com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS, th, new Object[0]);
        }
    }

    @Deprecated
    public static void bindAgoo(Context context, String str, String str2, CallBack callBack) {
        if (context == null) {
            try {
                throw new NullPointerException("Context==null");
            } catch (Throwable th) {
                ALog.e(TAG, "bindAgoo", th, new Object[0]);
                return;
            }
        }
        AgooService.a = callBack;
        ACCSManager.getAccsInstance(context, org.android.agoo.common.b.a(context)).bindService(context, "agooSend");
        UTMini.getInstance().commitEvent(66001, "bindAgoo", UtilityImpl.getDeviceId(context));
    }

    @Deprecated
    public static void unBindAgoo(Context context, String str, String str2, CallBack callBack) {
        if (context == null) {
            try {
                throw new NullPointerException("context==null");
            } catch (Throwable th) {
                ALog.e(TAG, "unBindAgoo", th, new Object[0]);
                return;
            }
        }
        String a = org.android.agoo.common.b.a(context);
        ALog.i(TAG, "unregister,appkey" + a, new Object[0]);
        AgooService.b = callBack;
        ACCSManager.getAccsInstance(context, a).unbindService(context, "agooSend");
        UTMini.getInstance().commitEvent(66001, "unregister", UtilityImpl.getDeviceId(context));
    }

    public static void bindAgoo(Context context, CallBack callBack) {
        bindAgoo(context, null, null, callBack);
    }

    public static void unbindAgoo(Context context, CallBack callBack) {
        unBindAgoo(context, null, null, callBack);
    }

    public static void clickMessage(Context context, String str, String str2) {
        Object th;
        Throwable th2;
        MsgDO msgDO = null;
        NotifManager notifManager = new NotifManager();
        try {
            if (ALog.isPrintLog(Level.I)) {
                ALog.i(TAG, "clickMessage", "msgid", str, AgooConstants.MESSAGE_EXT, str2);
            }
            String str3 = "accs";
            String str4 = "8";
            if (TextUtils.isEmpty(str)) {
                ALog.d(TAG, "messageId == null", new Object[0]);
                if (null != null) {
                    notifManager.reportNotifyMessage(null);
                    return;
                }
                return;
            }
            notifManager.init(context);
            MsgDO msgDO2 = new MsgDO();
            try {
                msgDO2.msgIds = str;
                msgDO2.extData = str2;
                msgDO2.messageSource = str3;
                msgDO2.msgStatus = str4;
                AgooFactory agooFactory = new AgooFactory();
                agooFactory.init(context, notifManager, null);
                agooFactory.updateMsgStatus(str, "8");
                if (msgDO2 != null) {
                    notifManager.reportNotifyMessage(msgDO2);
                }
            } catch (Throwable th3) {
                th2 = th3;
                msgDO = msgDO2;
                if (msgDO != null) {
                    notifManager.reportNotifyMessage(msgDO);
                }
                throw th2;
            }
        } catch (Throwable th4) {
            th = th4;
            ALog.e(TAG, "clickMessage,error=" + th, new Object[0]);
            if (msgDO != null) {
                notifManager.reportNotifyMessage(msgDO);
            }
        }
    }

    public static void dismissMessage(Context context, String str, String str2) {
        Object th;
        Throwable th2;
        MsgDO msgDO = null;
        NotifManager notifManager = new NotifManager();
        try {
            if (ALog.isPrintLog(Level.I)) {
                ALog.i(TAG, "dismissMessage", "msgid", str, AgooConstants.MESSAGE_EXT, str2);
            }
            String str3 = "accs";
            String str4 = "9";
            if (TextUtils.isEmpty(str)) {
                ALog.d(TAG, "messageId == null", new Object[0]);
                if (null != null) {
                    notifManager.reportNotifyMessage(null);
                    return;
                }
                return;
            }
            notifManager.init(context);
            MsgDO msgDO2 = new MsgDO();
            try {
                msgDO2.msgIds = str;
                msgDO2.extData = str2;
                msgDO2.messageSource = str3;
                msgDO2.msgStatus = str4;
                AgooFactory agooFactory = new AgooFactory();
                agooFactory.init(context, notifManager, null);
                agooFactory.updateMsgStatus(str, "9");
                if (msgDO2 != null) {
                    notifManager.reportNotifyMessage(msgDO2);
                }
            } catch (Throwable th3) {
                th2 = th3;
                msgDO = msgDO2;
                if (msgDO != null) {
                    notifManager.reportNotifyMessage(msgDO);
                }
                throw th2;
            }
        } catch (Throwable th4) {
            th = th4;
            ALog.e(TAG, "clickMessage,error=" + th, new Object[0]);
            if (msgDO != null) {
                notifManager.reportNotifyMessage(msgDO);
            }
        }
    }

    public static void pingApp(Context context, String str, String str2, String str3, int i) {
        NotifManager notifManager = new NotifManager();
        notifManager.init(context);
        notifManager.pingApp(str, str2, str3, i);
    }

    public static void isEnableDaemonServer(Context context, boolean z) {
        if (ALog.isPrintLog(Level.I)) {
            ALog.i(TAG, "isEnableDaemonServer begin,enable=" + z, new Object[0]);
        }
        org.android.agoo.common.b.a(context, z);
    }

    public static void setAgooMsgReceiveService(String str) {
        com.taobao.accs.client.a.b = str;
    }

    public static void setEnv(Context context, @ENV int i) {
        ACCSClient.setEnvironment(context, i);
    }

    @Deprecated
    public static void setNotificationIcon(Context context, int i) {
    }

    @Deprecated
    public static void setNotificationSound(Context context, boolean z) {
    }

    @Deprecated
    public static void setBuilderSound(Context context, String str) {
    }

    @Deprecated
    public static void setNotificationVibrate(Context context, boolean z) {
    }

    @Deprecated
    public static void unregister(Context context, CallBack callBack) {
        unBindAgoo(context, null, null, callBack);
    }
}
