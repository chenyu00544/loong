package org.android.agoo.control;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.c;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UTMini;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import javax.crypto.spec.SecretKeySpec;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.MsgDO;
import org.android.agoo.common.e;
import org.android.agoo.message.MessageService;
import org.android.agoo.service.SendMessage;
import org.android.agoo.service.SendMessage.Stub;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Taobao */
public class AgooFactory {
    private static final String DEAL_MESSAGE = "accs.msgRecevie";
    private static final String TAG = "AgooFactory";
    private static Context mContext = null;
    private ScheduledThreadPoolExecutor mThreadPool;
    private MessageService messageService = null;
    protected NotifManager notifyManager = null;

    /* compiled from: Taobao */
    class a implements ServiceConnection {
        final /* synthetic */ AgooFactory a;
        private Intent b;
        private String c;
        private SendMessage d;
        private ServiceConnection e = this;

        public a(AgooFactory agooFactory, String str, Intent intent) {
            this.a = agooFactory;
            this.c = str;
            this.b = intent;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            ALog.d(AgooFactory.TAG, "MessageConnection disConnected", new Object[0]);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ALog.d(AgooFactory.TAG, "MessageConnection conneted:" + componentName, new Object[0]);
            this.d = Stub.asInterface(iBinder);
            ALog.d(AgooFactory.TAG, "onConnected current tid:" + Thread.currentThread().getId(), new Object[0]);
            ALog.d(AgooFactory.TAG, "MessageConnection sent:" + this.b, new Object[0]);
            if (this.d != null) {
                this.a.mThreadPool.execute(new f(this));
            }
        }
    }

    /* compiled from: Taobao */
    class b implements Runnable {
        final /* synthetic */ AgooFactory a;
        private String b;
        private Intent c;

        public b(AgooFactory agooFactory, String str, Intent intent) {
            this.a = agooFactory;
            this.b = str;
            this.c = intent;
        }

        public void run() {
            try {
                ALog.d(AgooFactory.TAG, "running tid:" + Thread.currentThread().getId() + ",pack=" + this.b, new Object[0]);
                AgooFactory.mContext.sendBroadcast(this.c);
                ALog.d(AgooFactory.TAG, "SendMessageRunnable for accs,pack=" + this.b, new Object[0]);
                try {
                    this.c.setPackage(this.b);
                    this.c.setAction(AgooConstants.INTENT_FROM_AGOO_MESSAGE);
                    AgooFactory.mContext.startService(this.c);
                } catch (Throwable th) {
                }
                Intent intent = new Intent(AgooConstants.BINDER_MSGRECEIVER_ACTION);
                intent.setPackage(this.b);
                ALog.d(AgooFactory.TAG, "this message pack:" + this.b, new Object[0]);
                ALog.d(AgooFactory.TAG, "start to service...", new Object[0]);
                ServiceConnection aVar = new a(this.a, this.c.getStringExtra("id"), this.c);
                Context access$000 = AgooFactory.mContext;
                AgooFactory.mContext;
                AgooFactory.mContext;
                boolean bindService = access$000.bindService(intent, aVar, 17);
                ALog.d(AgooFactory.TAG, "start service ret:" + bindService, new Object[0]);
                if (!bindService) {
                    ALog.d(AgooFactory.TAG, "SendMessageRunnable is error", new Object[0]);
                }
            } catch (Throwable th2) {
                ALog.e(AgooFactory.TAG, "SendMessageRunnable is error,e=" + th2.toString(), new Object[0]);
            }
        }
    }

    public void init(Context context, NotifManager notifManager, MessageService messageService) {
        mContext = context;
        this.mThreadPool = e.a();
        this.notifyManager = notifManager;
        if (this.notifyManager == null) {
            this.notifyManager = new NotifManager();
        }
        this.notifyManager.init(mContext);
        this.messageService = messageService;
        if (this.messageService == null) {
            this.messageService = new MessageService();
        }
        this.messageService.a(mContext);
    }

    public void saveMsg(byte[] bArr) {
        saveMsg(bArr, null);
    }

    public void saveMsg(byte[] bArr, String str) {
        if (bArr != null && bArr.length > 0) {
            this.mThreadPool.execute(new a(this, bArr, str));
        }
    }

    public void msgRecevie(byte[] bArr, String str) {
        msgRecevie(bArr, str, null);
    }

    public void msgRecevie(byte[] bArr, String str, ExtraInfo extraInfo) {
        try {
            if (ALog.isPrintLog(Level.I)) {
                ALog.i(TAG, "into--[AgooFactory,msgRecevie]:messageSource=" + str, new Object[0]);
            }
            this.mThreadPool.execute(new b(this, bArr, str, extraInfo));
        } catch (Throwable th) {
            ALog.e(TAG, "serviceImpl init task fail:" + th.toString(), new Object[0]);
        }
    }

    public Bundle msgReceiverPreHandler(byte[] bArr, String str, ExtraInfo extraInfo, boolean z) {
        if (bArr != null) {
            if (bArr.length > 0) {
                String str2 = new String(bArr, "utf-8");
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i(TAG, "msgRecevie,message--->[" + str2 + "]" + ",utdid=" + com.taobao.accs.utl.a.b(mContext), new Object[0]);
                }
                if (TextUtils.isEmpty(str2)) {
                    UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, DEAL_MESSAGE, com.taobao.accs.utl.a.b(mContext), "message==null");
                    ALog.i(TAG, "handleMessage message==null,utdid=" + com.taobao.accs.utl.a.b(mContext), new Object[0]);
                    return null;
                }
                JSONArray jSONArray = new JSONArray(str2);
                int length = jSONArray.length();
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilder2 = new StringBuilder();
                StringBuilder stringBuilder3 = new StringBuilder();
                Bundle bundle = null;
                int i = 0;
                String str3 = null;
                while (i < length) {
                    Bundle bundle2 = new Bundle();
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        String str4;
                        MsgDO msgDO = new MsgDO();
                        String string = jSONObject.getString("p");
                        String string2 = jSONObject.getString("i");
                        String string3 = jSONObject.getString("b");
                        long j = jSONObject.getLong("f");
                        if (jSONObject.isNull(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND)) {
                            str4 = str3;
                        } else {
                            str4 = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                        }
                        stringBuilder.append(string2);
                        if (i < length - 1) {
                            stringBuilder.append(",");
                        }
                        msgDO.msgIds = string2;
                        msgDO.extData = str4;
                        msgDO.removePacks = string;
                        msgDO.messageSource = str;
                        if (TextUtils.isEmpty(string3)) {
                            msgDO.errorCode = "11";
                            this.notifyManager.handlerACKMessage(msgDO, extraInfo);
                            str3 = str4;
                        } else if (TextUtils.isEmpty(string)) {
                            msgDO.errorCode = "12";
                            this.notifyManager.handlerACKMessage(msgDO, extraInfo);
                            str3 = str4;
                        } else if (j == -1) {
                            msgDO.errorCode = "13";
                            this.notifyManager.handlerACKMessage(msgDO, extraInfo);
                            str3 = str4;
                        } else {
                            if (checkPackage(mContext, string)) {
                                Bundle flag = getFlag(j, msgDO);
                                CharSequence string4 = flag.getString(AgooConstants.MESSAGE_ENCRYPTED);
                                boolean z2 = false;
                                if (mContext.getPackageName().equals(string)) {
                                    if (TextUtils.equals(string4, Integer.toString(0))) {
                                        ALog.i(TAG, "normal msg~~", new Object[0]);
                                        str3 = string3;
                                    } else if (TextUtils.equals(string4, Integer.toString(4))) {
                                        ALog.i(TAG, "begin parse EncryptedMsg", new Object[0]);
                                        str3 = parseEncryptedMsg(string3);
                                        if (TextUtils.isEmpty(str3)) {
                                            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, DEAL_MESSAGE, com.taobao.accs.utl.a.b(mContext), "parseEncryptedMsg failure", "22");
                                            msgDO.errorCode = "22";
                                            this.notifyManager.handlerACKMessage(msgDO, extraInfo);
                                            str3 = str4;
                                        }
                                    } else {
                                        ALog.e(TAG, "msgRecevie msg encrypted flag not exist, cannot prase!!!", new Object[0]);
                                        UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, DEAL_MESSAGE, com.taobao.accs.utl.a.b(mContext), "encrypted!=4", "15");
                                        msgDO.errorCode = "15";
                                        this.notifyManager.handlerACKMessage(msgDO, extraInfo);
                                        str3 = str4;
                                    }
                                    bundle2.putBoolean(AgooConstants.MESSAGE_HAS_DECRYPTED, true);
                                } else {
                                    z2 = true;
                                    str3 = string3;
                                }
                                if (flag != null) {
                                    bundle2.putAll(flag);
                                }
                                try {
                                    Object string5 = jSONObject.getString(c.TIMESTAMP);
                                    if (!TextUtils.isEmpty(string5)) {
                                        bundle2.putString("time", string5);
                                    }
                                } catch (Throwable th) {
                                    if (ALog.isPrintLog(Level.E)) {
                                        ALog.e(TAG, "msgRecevie is error,e=" + th, new Object[0]);
                                    }
                                    return null;
                                }
                                bundle2.putLong(AgooConstants.MESSAGE_TRACE, System.currentTimeMillis());
                                bundle2.putString("id", string2);
                                bundle2.putString("body", str3);
                                bundle2.putString("source", string);
                                bundle2.putString(AgooConstants.MESSAGE_FROM_APPKEY, org.android.agoo.common.b.a(mContext));
                                bundle2.putString(AgooConstants.MESSAGE_EXT, str4);
                                bundle2.putString(AgooConstants.MESSAGE_ORI, str2);
                                if (z) {
                                    sendMsgToBussiness(mContext, string, bundle2, z2, str, extraInfo);
                                    str3 = str4;
                                } else {
                                    bundle2.putString("type", "common-push");
                                    bundle2.putString(AgooConstants.MESSAGE_SOURCE, str);
                                }
                            } else {
                                ALog.d(TAG, "msgRecevie checkpackage is del,pack=" + string, new Object[0]);
                                UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, DEAL_MESSAGE, com.taobao.accs.utl.a.b(mContext), "deletePack", string);
                                stringBuilder3.append(string);
                                stringBuilder2.append(string2);
                                if (i < length - 1) {
                                    stringBuilder3.append(",");
                                    stringBuilder2.append(",");
                                    str3 = str4;
                                }
                            }
                            str3 = str4;
                        }
                    }
                    i++;
                    bundle = bundle2;
                }
                if (stringBuilder3 != null && stringBuilder3.length() > 0) {
                    MsgDO msgDO2 = new MsgDO();
                    msgDO2.msgIds = stringBuilder2.toString();
                    msgDO2.removePacks = stringBuilder3.toString();
                    msgDO2.errorCode = "10";
                    msgDO2.messageSource = str;
                    this.notifyManager.handlerACKMessage(msgDO2, extraInfo);
                }
                return bundle;
            }
        }
        UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, DEAL_MESSAGE, com.taobao.accs.utl.a.b(mContext), "data==null");
        ALog.i(TAG, "handleMessage data==null,utdid=" + com.taobao.accs.utl.a.b(mContext), new Object[0]);
        return null;
    }

    public static String parseEncryptedMsg(String str) {
        try {
            byte[] a;
            String a2 = org.android.agoo.common.b.a(mContext);
            if (com.taobao.accs.client.a.a != 2) {
                SecurityGuardManager instance = SecurityGuardManager.getInstance(mContext);
                if (instance != null) {
                    ALog.d(TAG, "SecurityGuardManager not null!", new Object[0]);
                    ISecureSignatureComponent secureSignatureComp = instance.getSecureSignatureComp();
                    SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
                    securityGuardParamContext.appKey = a2;
                    securityGuardParamContext.paramMap.put("INPUT", a2 + com.taobao.accs.utl.a.b(mContext));
                    securityGuardParamContext.requestType = 3;
                    a = org.android.agoo.common.c.a(secureSignatureComp.signRequest(securityGuardParamContext, com.taobao.accs.client.a.c));
                } else {
                    ALog.e(TAG, "SecurityGuardManager is null", new Object[0]);
                    a = null;
                }
            } else if (TextUtils.isEmpty(com.taobao.accs.utl.a.b)) {
                ALog.e(TAG, "getAppsign secret null", new Object[0]);
                a = null;
            } else {
                a = org.android.agoo.common.c.a(com.taobao.accs.utl.a.b.getBytes("utf-8"), (a2 + com.taobao.accs.utl.a.b(mContext)).getBytes("utf-8"));
            }
            if (a != null && a.length > 0) {
                return new String(org.android.agoo.common.c.a(org.android.agoo.common.a.a(str, 16), new SecretKeySpec(org.android.agoo.common.c.a(a), "AES"), org.android.agoo.common.c.a(a2.getBytes("utf-8"))), "utf-8");
            }
            ALog.e(TAG, "aesDecrypt key is null!", new Object[0]);
            return null;
        } catch (Throwable th) {
            ALog.e(TAG, "parseEncryptedMsg failure: ", th, new Object[0]);
        }
    }

    public void reportCacheMsg() {
        try {
            this.mThreadPool.execute(new c(this));
        } catch (Throwable th) {
            ALog.e(TAG, "reportCacheMsg fail:" + th.toString(), new Object[0]);
        }
    }

    public void updateMsg(byte[] bArr, boolean z) {
        this.mThreadPool.execute(new d(this, bArr, z));
    }

    public void updateNotifyMsg(String str, String str2) {
        this.mThreadPool.execute(new e(this, str, str2));
    }

    public void updateMsgStatus(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i(TAG, "updateNotifyMsg begin,messageId=" + str + ",status=" + str2 + ",reportTimes=" + org.android.agoo.common.b.d(mContext), new Object[0]);
                }
                if (TextUtils.equals(str2, "8")) {
                    this.messageService.a(str, "2");
                } else if (TextUtils.equals(str2, "9")) {
                    this.messageService.a(str, "3");
                }
            }
        } catch (Throwable th) {
            ALog.e(TAG, "updateNotifyMsg e=" + th.toString(), new Object[0]);
        }
    }

    private static final boolean checkPackage(Context context, String str) {
        try {
            if (context.getPackageManager().getApplicationInfo(str, 0) != null) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    private static Bundle getFlag(long j, MsgDO msgDO) {
        Bundle bundle = new Bundle();
        try {
            char[] toCharArray = Long.toBinaryString(j).toCharArray();
            if (toCharArray != null && 8 <= toCharArray.length) {
                if (8 <= toCharArray.length) {
                    bundle.putString(AgooConstants.MESSAGE_ENCRYPTED, "" + Integer.parseInt("" + toCharArray[1] + toCharArray[2] + toCharArray[3] + toCharArray[4], 2));
                    if (toCharArray[6] == '1') {
                        bundle.putString(AgooConstants.MESSAGE_REPORT, "1");
                        msgDO.reportStr = "1";
                    }
                    if (toCharArray[7] == '1') {
                        bundle.putString(AgooConstants.MESSAGE_NOTIFICATION, "1");
                    }
                }
                if (9 <= toCharArray.length && toCharArray[8] == '1') {
                    bundle.putString(AgooConstants.MESSAGE_HAS_TEST, "1");
                }
                if (10 <= toCharArray.length && toCharArray[9] == '1') {
                    bundle.putString(AgooConstants.MESSAGE_DUPLICATE, "1");
                }
                if (11 <= toCharArray.length && toCharArray[10] == '1') {
                    bundle.putInt(AgooConstants.MESSAGE_POPUP, 1);
                }
            }
        } catch (Throwable th) {
        }
        return bundle;
    }

    private void sendMsgToBussiness(Context context, String str, Bundle bundle, boolean z, String str2, ExtraInfo extraInfo) {
        Intent intent = new Intent();
        intent.setAction(AgooConstants.INTENT_FROM_AGOO_MESSAGE);
        intent.setPackage(str);
        intent.putExtras(bundle);
        intent.putExtra("type", "common-push");
        intent.putExtra(AgooConstants.MESSAGE_SOURCE, str2);
        intent.addFlags(32);
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable(AgooConstants.MESSAGE_ACCS_EXTRA, extraInfo);
            intent.putExtra(AgooConstants.MESSAGE_AGOO_BUNDLE, bundle2);
        } catch (Throwable th) {
            ALog.e(TAG, "sendMsgToBussiness", th, new Object[0]);
        }
        if (ALog.isPrintLog(Level.I)) {
            ALog.i(TAG, "sendMsgToBussiness intent:" + bundle.toString() + ",utdid=" + com.taobao.accs.utl.a.b(context) + ",pack=" + str + ",agooFlag=" + z, new Object[0]);
        }
        if (z) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, DEAL_MESSAGE, com.taobao.accs.utl.a.b(context), "agooMsg", "15");
            sendMsgByBindService(str, intent);
            return;
        }
        intent.setClassName(str, com.taobao.accs.client.a.a(str));
        context.startService(intent);
    }

    private void sendMsgByBindService(String str, Intent intent) {
        try {
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(TAG, "onHandleMessage current tid:" + Thread.currentThread().getId(), new Object[0]);
            }
            this.mThreadPool.execute(new b(this, str, intent));
        } catch (Throwable th) {
            ALog.e(TAG, "sendMsgByBindService error >>", th, new Object[0]);
        }
    }
}
