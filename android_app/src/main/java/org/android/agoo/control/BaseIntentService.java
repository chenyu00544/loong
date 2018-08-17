package org.android.agoo.control;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.client.a;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.MsgDO;
import org.android.agoo.common.b;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.message.MessageService;

/* compiled from: Taobao */
public abstract class BaseIntentService extends IntentService {
    private static final String TAG = "BaseIntentService";
    private static final String msgStatus = "4";
    private AgooFactory agooFactory;
    private Context mContext = null;
    private MessageService messageService;
    private NotifManager notifyManager;

    protected abstract void onError(Context context, String str);

    protected abstract void onMessage(Context context, Intent intent);

    protected abstract void onRegistered(Context context, String str);

    public BaseIntentService() {
        super("AgooIntentService");
    }

    protected void onUserCommand(Context context, Intent intent) {
    }

    public void onCreate() {
        a.g.incrementAndGet();
        this.notifyManager = new NotifManager();
        this.notifyManager.init(getApplicationContext());
        this.messageService = new MessageService();
        this.messageService.a(getApplicationContext());
        this.agooFactory = new AgooFactory();
        this.agooFactory.init(getApplicationContext(), this.notifyManager, this.messageService);
        super.onCreate();
    }

    protected void onHandleIntent(Intent intent) {
        this.mContext = getApplicationContext();
        if (intent != null) {
            CharSequence action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                CharSequence agooCommand = IntentUtil.getAgooCommand(this.mContext);
                CharSequence thirdPushCommand = IntentUtil.getThirdPushCommand(this.mContext);
                ALog.i(TAG, "onHandleIntent,action=" + action + ",agooCommand=" + agooCommand + ",mipushCommand=" + thirdPushCommand, new Object[0]);
                try {
                    if (TextUtils.equals(action, agooCommand)) {
                        action = intent.getStringExtra("command");
                        ALog.d(TAG, "actionCommand --->[" + action + "]", new Object[0]);
                        if (TextUtils.equals(action, AgooConstants.AGOO_COMMAND_MESSAGE_READED) || TextUtils.equals(action, AgooConstants.AGOO_COMMAND_MESSAGE_DELETED)) {
                            onUserCommand(this.mContext, intent);
                        }
                    } else if (TextUtils.equals(action, thirdPushCommand)) {
                        action = intent.getStringExtra("command");
                        String stringExtra = intent.getStringExtra(AgooConstants.THIRD_PUSH_ID);
                        if (TextUtils.equals(action, AgooConstants.AGOO_COMMAND_MIPUSHID_REPORT)) {
                            this.notifyManager.reportThirdPushToken(stringExtra, "MI_TOKEN", false);
                        } else if (TextUtils.equals(action, AgooConstants.AGOO_COMMAND_HUAWEIPUSHID_REPORT)) {
                            ALog.d(TAG, "HW_TOKEN report begin..regid=" + stringExtra, new Object[0]);
                            this.notifyManager.reportThirdPushToken(stringExtra, "HW_TOKEN", false);
                        } else if (TextUtils.equals(action, AgooConstants.AGOO_COMMAND_GCMIPUSHID_REPORT)) {
                            ALog.i(TAG, "GCM_TOKEN report begin..regid=" + stringExtra, new Object[0]);
                            this.notifyManager.reportThirdPushToken(stringExtra, "gcm", false);
                        }
                    } else if (action.equals(AgooConstants.INTENT_FROM_AGOO_MESSAGE)) {
                        handleRemoteMessage(this.mContext, intent);
                    } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                        handleRemovePackage(this.mContext, intent);
                    } else if (TextUtils.equals(action, AgooConstants.INTENT_FROM_AGOO_REPORT) || TextUtils.equals(action, "android.net.conn.CONNECTIVITY_CHANGE") || TextUtils.equals(action, "android.intent.action.BOOT_COMPLETED") || TextUtils.equals(action, "android.intent.action.PACKAGE_ADDED") || TextUtils.equals(action, "android.intent.action.PACKAGE_REPLACED") || TextUtils.equals(action, "android.intent.action.USER_PRESENT") || TextUtils.equals(action, "android.intent.action.ACTION_POWER_CONNECTED") || TextUtils.equals(action, "android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        try {
                            ALog.i(TAG, "is report cache msg,Config.isReportCacheMsg(mContext)=" + b.b(this.mContext), new Object[0]);
                            if (b.b(this.mContext) && com.taobao.accs.utl.a.c(this.mContext)) {
                                b.c(this.mContext);
                                this.agooFactory.reportCacheMsg();
                                this.messageService.a();
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            if (ALog.isPrintLog(Level.I)) {
                                ALog.i(TAG, "is clear all msg=" + b.b(this.mContext, currentTimeMillis), new Object[0]);
                            }
                            if (b.b(this.mContext, currentTimeMillis)) {
                                b.a(this.mContext, currentTimeMillis);
                                this.messageService.a();
                            }
                        } catch (Throwable th) {
                            ALog.e(TAG, "reportCacheMsg", th, new Object[0]);
                        }
                    }
                    a.g.incrementAndGet();
                } catch (Throwable th2) {
                    a.g.incrementAndGet();
                }
            }
        }
    }

    private final void handleRemovePackage(Context context, Intent intent) {
        if (intent != null && context != null) {
            String str = null;
            Uri data = intent.getData();
            if (data != null) {
                str = data.getSchemeSpecificPart();
            }
            if (!TextUtils.isEmpty(str)) {
                boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d(TAG, "handleRemovePackage---->[replacing:" + booleanExtra + "],uninstallPack=" + str, new Object[0]);
                }
                if (!booleanExtra) {
                    this.notifyManager.doUninstall(str, booleanExtra);
                }
            }
        }
    }

    private final void handleRemoteMessage(Context context, Intent intent) {
        String stringExtra;
        String stringExtra2;
        String stringExtra3;
        String stringExtra4;
        String stringExtra5;
        String str;
        String stringExtra6;
        int parseInt;
        CharSequence stringExtra7;
        try {
            stringExtra = intent.getStringExtra("id");
            stringExtra2 = intent.getStringExtra("body");
            String stringExtra8 = intent.getStringExtra("type");
            stringExtra3 = intent.getStringExtra(AgooConstants.MESSAGE_SOURCE);
            stringExtra4 = intent.getStringExtra(AgooConstants.MESSAGE_REPORT);
            String stringExtra9 = intent.getStringExtra(AgooConstants.MESSAGE_ENCRYPTED);
            stringExtra5 = intent.getStringExtra(AgooConstants.MESSAGE_EXT);
            String stringExtra10 = intent.getStringExtra(AgooConstants.MESSAGE_ORI);
            ExtraInfo extraInfo = null;
            str = null;
            Context context2 = context;
            getTrace(context2, Long.valueOf(intent.getLongExtra(AgooConstants.MESSAGE_TRACE, -1)).longValue());
            Bundle bundleExtra = intent.getBundleExtra(AgooConstants.MESSAGE_AGOO_BUNDLE);
            if (bundleExtra != null) {
                extraInfo = (ExtraInfo) bundleExtra.getSerializable(AgooConstants.MESSAGE_ACCS_EXTRA);
            }
            str = intent.getStringExtra("source");
            if (TextUtils.isEmpty(str)) {
                str = "oldsdk";
            }
            stringExtra6 = intent.getStringExtra(AgooConstants.MESSAGE_FROM_APPKEY);
        } catch (Throwable th) {
            com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_exception" + th.toString(), 0.0d);
            return;
        }
        if (ALog.isPrintLog(Level.I)) {
            ALog.i(TAG, "handleRemoteMessage", "message", stringExtra2, "source", stringExtra3, "msgId", stringExtra, "utdid", com.taobao.accs.utl.a.b(context), "fromPkg", str, AgooConstants.MESSAGE_FROM_APPKEY, stringExtra6);
        }
        MsgDO msgDO = new MsgDO();
        msgDO.msgIds = stringExtra;
        msgDO.extData = stringExtra5;
        msgDO.messageSource = stringExtra3;
        msgDO.msgStatus = "4";
        msgDO.reportStr = stringExtra4;
        msgDO.fromPkg = str;
        msgDO.fromAppkey = stringExtra6;
        msgDO.isStartProc = a.c();
        msgDO.notifyEnable = com.taobao.accs.utl.a.d(this.mContext);
        if (!TextUtils.isEmpty(stringExtra2)) {
            if (Integer.toString(0).equals(stringExtra9)) {
                ALog.i(TAG, "normal msg, onMessage() will be excuted", new Object[0]);
                stringExtra5 = stringExtra2;
            } else if (Integer.toString(4).equals(stringExtra9)) {
                if (!intent.getBooleanExtra(AgooConstants.MESSAGE_HAS_DECRYPTED, false)) {
                    ALog.i(TAG, "message is encrypted, attemp to decrypt msg", new Object[0]);
                    stringExtra5 = AgooFactory.parseEncryptedMsg(stringExtra2);
                }
            } else {
                ALog.e(TAG, "msg encrypted flag not exist~~", new Object[0]);
                try {
                    msgDO.errorCode = "22";
                    this.notifyManager.report(msgDO, extraInfo);
                    return;
                } catch (Throwable th2) {
                    return;
                }
            }
            if (TextUtils.isEmpty(stringExtra5)) {
                intent.putExtra("body", stringExtra5);
                try {
                    this.notifyManager.report(msgDO, extraInfo);
                    this.messageService.a(stringExtra, stringExtra10, "0");
                    UTMini.getInstance().commitEvent((int) UTMini.EVENTID_AGOO, UTMini.PAGE_AGOO, BaseMonitor.COUNT_AGOO_ARRIVE_ID, null, null, null, "messageId=" + msgDO.msgIds);
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive", 0.0d);
                } catch (Throwable th3) {
                    ALog.e(TAG, "report message Throwable--->t=" + th3.toString(), new Object[0]);
                }
                if (this.messageService.a(stringExtra)) {
                    if (ALog.isPrintLog(Level.I)) {
                        ALog.i(TAG, "handleMessage--->[" + stringExtra5 + "],[" + stringExtra3 + "]", new Object[0]);
                    }
                    try {
                        CharSequence stringExtra11 = intent.getStringExtra(AgooConstants.MESSAGE_DUPLICATE);
                        if (!TextUtils.isEmpty(stringExtra11) && TextUtils.equals(stringExtra11, "1")) {
                            if (this.messageService.a(stringExtra, stringExtra5.hashCode())) {
                                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_dupbody", 0.0d);
                                return;
                            }
                        }
                    } catch (Throwable th32) {
                        if (ALog.isPrintLog(Level.E)) {
                            ALog.e(TAG, "hasMessageDuplicate message,e=" + th32.toString(), new Object[0]);
                        }
                    }
                    int i = -1;
                    try {
                        parseInt = Integer.parseInt(intent.getStringExtra(AgooConstants.MESSAGE_NOTIFICATION));
                    } catch (Throwable th4) {
                        parseInt = i;
                    }
                    stringExtra6 = "";
                    try {
                        stringExtra7 = intent.getStringExtra(AgooConstants.MESSAGE_HAS_TEST);
                        if (TextUtils.isEmpty(stringExtra7) && TextUtils.equals(stringExtra7, "1")) {
                            this.messageService.a(stringExtra, stringExtra5, stringExtra8, parseInt);
                            com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_test", 0.0d);
                            return;
                        }
                        stringExtra3 = getClass().getName();
                        this.messageService.a(stringExtra, stringExtra5, stringExtra8, parseInt);
                        UTMini.getInstance().commitEvent((int) UTMini.EVENTID_AGOO, UTMini.PAGE_AGOO, BaseMonitor.COUNT_AGOO_ARRIVE_REAL_ID, null, null, null, "messageId=" + msgDO.msgIds);
                        com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_real_" + stringExtra3, 0.0d);
                        onMessage(context, intent);
                        return;
                    } catch (Throwable th5) {
                        stringExtra3 = stringExtra6;
                    }
                } else {
                    if (ALog.isPrintLog(Level.I)) {
                        ALog.i(TAG, "handleRemoteMessage hasMessageDuplicate,messageId=" + stringExtra + ",utdid=" + com.taobao.accs.utl.a.b(context), new Object[0]);
                    }
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_dup", 0.0d);
                    return;
                }
            }
            try {
                msgDO.errorCode = "21";
                this.notifyManager.report(msgDO, extraInfo);
            } catch (Throwable th6) {
            }
            ALog.e(TAG, "handleMessage--->[null]", new Object[0]);
        }
        stringExtra5 = stringExtra2;
        if (TextUtils.isEmpty(stringExtra5)) {
            intent.putExtra("body", stringExtra5);
            this.notifyManager.report(msgDO, extraInfo);
            this.messageService.a(stringExtra, stringExtra10, "0");
            UTMini.getInstance().commitEvent((int) UTMini.EVENTID_AGOO, UTMini.PAGE_AGOO, BaseMonitor.COUNT_AGOO_ARRIVE_ID, null, null, null, "messageId=" + msgDO.msgIds);
            com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive", 0.0d);
            if (this.messageService.a(stringExtra)) {
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i(TAG, "handleMessage--->[" + stringExtra5 + "],[" + stringExtra3 + "]", new Object[0]);
                }
                CharSequence stringExtra112 = intent.getStringExtra(AgooConstants.MESSAGE_DUPLICATE);
                if (this.messageService.a(stringExtra, stringExtra5.hashCode())) {
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_dupbody", 0.0d);
                    return;
                }
                int i2 = -1;
                parseInt = Integer.parseInt(intent.getStringExtra(AgooConstants.MESSAGE_NOTIFICATION));
                stringExtra6 = "";
                stringExtra7 = intent.getStringExtra(AgooConstants.MESSAGE_HAS_TEST);
                if (TextUtils.isEmpty(stringExtra7)) {
                }
                stringExtra3 = getClass().getName();
                this.messageService.a(stringExtra, stringExtra5, stringExtra8, parseInt);
                UTMini.getInstance().commitEvent((int) UTMini.EVENTID_AGOO, UTMini.PAGE_AGOO, BaseMonitor.COUNT_AGOO_ARRIVE_REAL_ID, null, null, null, "messageId=" + msgDO.msgIds);
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_real_" + stringExtra3, 0.0d);
                onMessage(context, intent);
                return;
            }
            if (ALog.isPrintLog(Level.I)) {
                ALog.i(TAG, "handleRemoteMessage hasMessageDuplicate,messageId=" + stringExtra + ",utdid=" + com.taobao.accs.utl.a.b(context), new Object[0]);
            }
            com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_dup", 0.0d);
            return;
        }
        msgDO.errorCode = "21";
        this.notifyManager.report(msgDO, extraInfo);
        ALog.e(TAG, "handleMessage--->[null]", new Object[0]);
    }

    private final String getTrace(Context context, long j) {
        String str;
        String str2 = null;
        if (TextUtils.isEmpty(null)) {
            str = "unknow";
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(null)) {
            str2 = "unknow";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("appkey");
        stringBuffer.append("|");
        stringBuffer.append(j);
        stringBuffer.append("|");
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append("|");
        stringBuffer.append(str);
        stringBuffer.append("|");
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static final void runIntentInService(Context context, Intent intent, String str) {
        try {
            intent.setClassName(context, str);
            context.startService(intent);
        } catch (Throwable th) {
            ALog.w(TAG, "runIntentInService", th, new Object[0]);
        }
    }
}
