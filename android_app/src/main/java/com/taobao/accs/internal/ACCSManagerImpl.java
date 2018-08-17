package com.taobao.accs.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.strategy.StrategyCenter;
import com.taobao.accs.ACCSClient;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.IACCSManager;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.ILoginInfo;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.Message.ReqType;
import com.taobao.accs.data.e;
import com.taobao.accs.net.a;
import com.taobao.accs.net.j;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.h;
import com.umeng.analytics.pro.x;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public class ACCSManagerImpl implements IACCSManager {
    public a a;
    private int b = 0;
    private String c = "ACCSMgrImpl";

    public ACCSManagerImpl(Context context, String str) {
        GlobalClientInfo.a = context.getApplicationContext();
        this.a = new j(context, 1, str);
        this.a.b = str;
        this.c += this.a.i();
    }

    public void bindApp(Context context, String str, String str2, IAppReceiver iAppReceiver) {
        bindApp(context, str, "accs", str2, iAppReceiver);
    }

    public void bindApp(Context context, String str, String str2, String str3, IAppReceiver iAppReceiver) {
        boolean z = true;
        if (context != null) {
            ALog.d(this.c, "bindApp APPKEY:" + str, new Object[0]);
            Message buildParameterError = Message.buildParameterError(context.getPackageName(), 1);
            if (UtilityImpl.getFocusDisableStatus(context)) {
                this.a.b(buildParameterError, -17);
            } else if (this.a.k() && TextUtils.isEmpty(this.a.h.getAppSecret())) {
                this.a.b(buildParameterError, -15);
            } else if (iAppReceiver == null) {
                this.a.b(buildParameterError, -16);
            } else if (TextUtils.isEmpty(str)) {
                this.a.b(buildParameterError, -14);
            } else {
                this.a.a = str3;
                this.a.b = str;
                UtilityImpl.saveAppKey(context, str, this.a.h.getAppSecret());
                GlobalClientInfo.getInstance(context).setAppReceiver(str, iAppReceiver);
                UtilityImpl.enableService(context);
                Intent a = a(context, 1);
                if (a != null) {
                    try {
                        String str4 = GlobalClientInfo.getInstance(context).getPackageInfo().versionName;
                        if (!(UtilityImpl.appVersionChanged(context) || UtilityImpl.utdidChanged(context))) {
                            z = false;
                        }
                        if (z) {
                            a.putExtra(Constants.KEY_FOUCE_BIND, true);
                        }
                        a.putExtra("appKey", str);
                        a.putExtra(Constants.KEY_TTID, str3);
                        a.putExtra("appVersion", str4);
                        a.putExtra("app_sercet", this.a.h.getAppSecret());
                        if (UtilityImpl.isMainProcess(context)) {
                            a(context, Message.buildBindApp(this.a, context, a), 1, z);
                        }
                        this.a.b(context.getApplicationContext());
                        h.a(new String[]{"accs"}, new h.a());
                        h.f();
                        h.e();
                    } catch (Throwable th) {
                        ALog.e(this.c, "bindApp exception", th, new Object[0]);
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r9, com.taobao.accs.data.Message r10, int r11, boolean r12) {
        /*
        r8 = this;
        r7 = 2;
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r2 = 1;
        r1 = 0;
        r0 = r8.a;
        r0.a();
        if (r10 != 0) goto L_0x0024;
    L_0x000c:
        r0 = r8.c;
        r2 = "message is null";
        r1 = new java.lang.Object[r1];
        com.taobao.accs.utl.ALog.e(r0, r2, r1);
        r0 = r9.getPackageName();
        r0 = com.taobao.accs.data.Message.buildParameterError(r0, r11);
        r1 = r8.a;
        r2 = -2;
        r1.b(r0, r2);
    L_0x0023:
        return;
    L_0x0024:
        switch(r11) {
            case 1: goto L_0x0043;
            case 2: goto L_0x007a;
            case 3: goto L_0x00b0;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = r2;
    L_0x0028:
        if (r0 == 0) goto L_0x0023;
    L_0x002a:
        r0 = r8.c;
        r3 = "sendControlMessage";
        r4 = new java.lang.Object[r7];
        r5 = "command";
        r4[r1] = r5;
        r1 = java.lang.Integer.valueOf(r11);
        r4[r2] = r1;
        com.taobao.accs.utl.ALog.i(r0, r3, r4);
        r0 = r8.a;
        r0.b(r10, r2);
        goto L_0x0023;
    L_0x0043:
        r0 = r8.a;
        r0 = r0.j();
        r3 = r10.getPackageName();
        r0 = r0.c(r3);
        if (r0 == 0) goto L_0x0027;
    L_0x0053:
        if (r12 != 0) goto L_0x0027;
    L_0x0055:
        r0 = r8.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r10.getPackageName();
        r3 = r3.append(r4);
        r4 = " isAppBinded";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r4 = new java.lang.Object[r1];
        com.taobao.accs.utl.ALog.i(r0, r3, r4);
        r0 = r8.a;
        r0.b(r10, r6);
        r0 = r1;
        goto L_0x0028;
    L_0x007a:
        r0 = r8.a;
        r0 = r0.j();
        r3 = r10.getPackageName();
        r0 = r0.d(r3);
        if (r0 == 0) goto L_0x0027;
    L_0x008a:
        r0 = r8.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r10.getPackageName();
        r3 = r3.append(r4);
        r4 = " isAppUnbinded";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r4 = new java.lang.Object[r1];
        com.taobao.accs.utl.ALog.i(r0, r3, r4);
        r0 = r8.a;
        r0.b(r10, r6);
        r0 = r1;
        goto L_0x0028;
    L_0x00b0:
        r0 = r8.a;
        r0 = r0.j();
        r3 = r10.getPackageName();
        r4 = r10.userinfo;
        r0 = r0.b(r3, r4);
        if (r0 == 0) goto L_0x0027;
    L_0x00c2:
        if (r12 != 0) goto L_0x0027;
    L_0x00c4:
        r0 = r8.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r10.getPackageName();
        r3 = r3.append(r4);
        r4 = "/";
        r3 = r3.append(r4);
        r4 = r10.userinfo;
        r3 = r3.append(r4);
        r4 = " isUserBinded";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r4 = new java.lang.Object[r7];
        r5 = "isForceBind";
        r4[r1] = r5;
        r5 = java.lang.Boolean.valueOf(r12);
        r4[r2] = r5;
        com.taobao.accs.utl.ALog.i(r0, r3, r4);
        r0 = r8.a;
        r0.b(r10, r6);
        r0 = r1;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.internal.ACCSManagerImpl.a(android.content.Context, com.taobao.accs.data.Message, int, boolean):void");
    }

    public void unbindApp(Context context) {
        ALog.e(this.c, "unbindApp" + UtilityImpl.getStackMsg(new Exception()), new Object[0]);
        if (!UtilityImpl.getFocusDisableStatus(context)) {
            Intent a = a(context, 2);
            if (a == null) {
                a(context, 2, null, null);
            } else if (UtilityImpl.isMainProcess(context)) {
                a(context, Message.buildUnbindApp(this.a, context, a), 2, false);
            }
        }
    }

    public void bindUser(Context context, String str) {
        bindUser(context, str, false);
    }

    public void bindUser(Context context, String str, boolean z) {
        try {
            ALog.i(this.c, "bindUser", "userId", str);
            if (UtilityImpl.getFocusDisableStatus(context)) {
                ALog.e(this.c, "accs disabled", new Object[0]);
                return;
            }
            Intent a = a(context, 3);
            if (a == null) {
                ALog.e(this.c, "intent null", new Object[0]);
                a(context, 3, null, null);
                return;
            }
            Object i = this.a.i();
            if (TextUtils.isEmpty(i)) {
                ALog.e(this.c, "appKey null", new Object[0]);
                return;
            }
            if (UtilityImpl.appVersionChanged(context) || z) {
                ALog.i(this.c, "force bind User", new Object[0]);
                a.putExtra(Constants.KEY_FOUCE_BIND, true);
                z = true;
            }
            a.putExtra("appKey", i);
            a.putExtra(Constants.KEY_USER_ID, str);
            if (UtilityImpl.isMainProcess(context)) {
                a(context, Message.buildBindUser(this.a, context, a), 3, z);
            }
            this.a.b(context.getApplicationContext());
        } catch (Throwable th) {
            ALog.e(this.c, "bindUser", th, new Object[0]);
        }
    }

    public void unbindUser(Context context) {
        if (!UtilityImpl.getFocusDisableStatus(context) && !UtilityImpl.getFocusDisableStatus(context)) {
            Intent a = a(context, 4);
            if (a == null) {
                a(context, 4, null, null);
                return;
            }
            Object i = this.a.i();
            if (!TextUtils.isEmpty(i)) {
                a.putExtra("appKey", i);
                if (UtilityImpl.isMainProcess(context)) {
                    a(context, Message.buildUnbindUser(this.a, context, a), 4, false);
                }
            }
        }
    }

    public void bindService(Context context, String str) {
        if (!UtilityImpl.getFocusDisableStatus(context) && !UtilityImpl.getFocusDisableStatus(context)) {
            Intent a = a(context, 5);
            if (a == null) {
                a(context, 5, str, null);
                return;
            }
            Object i = this.a.i();
            if (!TextUtils.isEmpty(i)) {
                a.putExtra("appKey", i);
                a.putExtra(Constants.KEY_SERVICE_ID, str);
                if (UtilityImpl.isMainProcess(context)) {
                    a(context, Message.buildBindService(this.a, context, a), 5, false);
                }
                this.a.b(context.getApplicationContext());
            }
        }
    }

    public void unbindService(Context context, String str) {
        if (!UtilityImpl.getFocusDisableStatus(context)) {
            Intent a = a(context, 6);
            if (a == null) {
                a(context, 6, str, null);
                return;
            }
            Object i = this.a.i();
            if (!TextUtils.isEmpty(i)) {
                a.putExtra("appKey", i);
                a.putExtra(Constants.KEY_SERVICE_ID, str);
                if (UtilityImpl.isMainProcess(context)) {
                    a(context, Message.buildUnbindService(this.a, context, a), 6, false);
                }
            }
        }
    }

    public String sendData(Context context, String str, String str2, byte[] bArr, String str3) {
        return sendData(context, str, str2, bArr, str3, null);
    }

    public String sendData(Context context, String str, String str2, byte[] bArr, String str3, String str4) {
        return sendData(context, str, str2, bArr, str3, str4, null);
    }

    public String sendData(Context context, String str, String str2, byte[] bArr, String str3, String str4, URL url) {
        return sendData(context, new AccsRequest(str, str2, bArr, str3, str4, url, null));
    }

    public String sendData(Context context, AccsRequest accsRequest) {
        try {
            boolean focusDisableStatus = UtilityImpl.getFocusDisableStatus(context);
            if (focusDisableStatus || accsRequest == null) {
                if (focusDisableStatus) {
                    b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "accs disable");
                } else {
                    b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", "data null");
                }
                ALog.e(this.c, "send data dataInfo null or disable:" + focusDisableStatus, new Object[0]);
                return null;
            }
            if (TextUtils.isEmpty(accsRequest.dataId)) {
                synchronized (ACCSManagerImpl.class) {
                    this.b++;
                    accsRequest.dataId = this.b + "";
                }
            }
            Object i = this.a.i();
            if (TextUtils.isEmpty(i)) {
                b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "data appkey null");
                ALog.e(this.c, "send data appkey null dataid:" + accsRequest.dataId, new Object[0]);
                return null;
            }
            this.a.a();
            Message buildSendData = Message.buildSendData(this.a, context, context.getPackageName(), i, accsRequest);
            if (buildSendData.getNetPermanceMonitor() != null) {
                buildSendData.getNetPermanceMonitor().onSend();
            }
            this.a.b(buildSendData, true);
            return accsRequest.dataId;
        } catch (Throwable th) {
            b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "data " + th.toString());
            ALog.e(this.c, "send data dataid:" + accsRequest.dataId, th, new Object[0]);
        }
    }

    public String sendRequest(Context context, String str, String str2, byte[] bArr, String str3, String str4) {
        return sendRequest(context, str, str2, bArr, str3, str4, null);
    }

    public String sendRequest(Context context, String str, String str2, byte[] bArr, String str3, String str4, URL url) {
        return sendRequest(context, new AccsRequest(str, str2, bArr, str3, str4, url, null));
    }

    public String sendRequest(Context context, AccsRequest accsRequest, String str, boolean z) {
        if (accsRequest == null) {
            try {
                ALog.e(this.c, "sendRequest request null", new Object[0]);
                b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, null, "1", "request null");
                return null;
            } catch (Throwable th) {
                b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "request " + th.toString());
                ALog.e(this.c, "sendRequest dataid:" + accsRequest.dataId, th, new Object[0]);
            }
        } else if (UtilityImpl.getFocusDisableStatus(context)) {
            ALog.e(this.c, "sendRequest disable", new Object[0]);
            b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "accs disable");
            return null;
        } else {
            if (TextUtils.isEmpty(accsRequest.dataId)) {
                synchronized (ACCSManagerImpl.class) {
                    this.b++;
                    accsRequest.dataId = this.b + "";
                }
            }
            Object i = this.a.i();
            if (TextUtils.isEmpty(i)) {
                b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "request appkey null");
                ALog.e(this.c, "sendRequest appkey null dataid:" + accsRequest.dataId, new Object[0]);
                return null;
            }
            this.a.a();
            Message buildRequest = Message.buildRequest(this.a, context, str == null ? context.getPackageName() : str, i, accsRequest, z);
            if (buildRequest.getNetPermanceMonitor() != null) {
                buildRequest.getNetPermanceMonitor().onSend();
            }
            this.a.b(buildRequest, true);
            return accsRequest.dataId;
        }
    }

    public String sendRequest(Context context, AccsRequest accsRequest) {
        return sendRequest(context, accsRequest, null, true);
    }

    public String sendPushResponse(Context context, AccsRequest accsRequest, ExtraInfo extraInfo) {
        boolean z = true;
        if (context == null || accsRequest == null) {
            try {
                ALog.e(this.c, "sendPushResponse input null", x.aI, context, "response", accsRequest, "extraInfo", extraInfo);
                b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", "sendPushResponse null");
            } catch (Throwable th) {
                b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "push response " + th.toString());
                ALog.e(this.c, "sendPushResponse dataid:" + accsRequest.dataId, th, new Object[0]);
            }
        } else {
            b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "push response total");
            if (UtilityImpl.getFocusDisableStatus(context)) {
                b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "sendPushResponse accs disable");
            } else {
                String i = this.a.i();
                if (TextUtils.isEmpty(i)) {
                    b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "sendPushResponse appkey null");
                    ALog.e(this.c, "sendPushResponse appkey null dataid:" + accsRequest.dataId, new Object[0]);
                } else {
                    if (TextUtils.isEmpty(accsRequest.dataId)) {
                        synchronized (ACCSManagerImpl.class) {
                            this.b++;
                            accsRequest.dataId = this.b + "";
                        }
                    }
                    if (extraInfo == null) {
                        extraInfo = new ExtraInfo();
                    }
                    accsRequest.host = null;
                    if (extraInfo.fromPackage == null) {
                        String str = com.taobao.accs.a.a.a(context).a;
                        if (TextUtils.isEmpty(str)) {
                            str = context.getPackageName();
                        }
                        extraInfo.fromPackage = str;
                    }
                    if (extraInfo.connType == 0 || extraInfo.fromHost == null) {
                        extraInfo.connType = 0;
                        ALog.w(this.c, "pushresponse use channel", "host", extraInfo.fromHost);
                        z = false;
                    }
                    ALog.i(this.c, "sendPushResponse", "sendbyInapp", Boolean.valueOf(z), "host", extraInfo.fromHost, Constants.KEY_ELECTION_PKG, extraInfo.fromPackage, Constants.KEY_DATA_ID, accsRequest.dataId);
                    Intent intent;
                    if (z) {
                        ALog.i(this.c, "sendPushResponse inapp by", "app", extraInfo.fromPackage);
                        accsRequest.host = new URL(extraInfo.fromHost);
                        if (context.getPackageName().equals(extraInfo.fromPackage) && UtilityImpl.isMainProcess(context)) {
                            sendRequest(context, accsRequest, context.getPackageName(), false);
                        } else {
                            intent = new Intent(Constants.ACTION_SEND);
                            intent.setClassName(extraInfo.fromPackage, com.taobao.accs.utl.a.msgService);
                            intent.putExtra(Constants.KEY_PACKAGE_NAME, context.getPackageName());
                            intent.putExtra(Constants.KEY_SEND_REQDATA, accsRequest);
                            intent.putExtra("appKey", i);
                            context.startService(intent);
                        }
                    } else {
                        intent = a(context, 100);
                        if (intent == null) {
                            b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, "1", "push response intent null");
                            a(context, 100, accsRequest.serviceId, accsRequest.dataId);
                            ALog.e(this.c, "sendPushResponse input null", x.aI, context, "response", accsRequest, "extraInfo", extraInfo);
                        } else {
                            ALog.i(this.c, "sendPushResponse channel by", "app", extraInfo.fromPackage);
                            intent.setClassName(extraInfo.fromPackage, com.taobao.accs.utl.a.channelService);
                            intent.putExtra(Constants.KEY_SEND_TYPE, ReqType.REQ);
                            intent.putExtra("appKey", i);
                            intent.putExtra(Constants.KEY_USER_ID, accsRequest.userId);
                            intent.putExtra(Constants.KEY_SERVICE_ID, accsRequest.serviceId);
                            intent.putExtra("data", accsRequest.data);
                            intent.putExtra(Constants.KEY_DATA_ID, accsRequest.dataId);
                            if (!TextUtils.isEmpty(accsRequest.businessId)) {
                                intent.putExtra(Constants.KEY_BUSINESSID, accsRequest.businessId);
                            }
                            if (!TextUtils.isEmpty(accsRequest.tag)) {
                                intent.putExtra(Constants.KEY_EXT_TAG, accsRequest.tag);
                            }
                            if (accsRequest.target != null) {
                                intent.putExtra(Constants.KEY_TARGET, accsRequest.target);
                            }
                            context.startService(intent);
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean isNetworkReachable(Context context) {
        return UtilityImpl.isNetworkConnected(context);
    }

    private Intent a(Context context, int i) {
        if (i == 1 || UtilityImpl.getServiceEnabled(context)) {
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_COMMAND);
            intent.setClassName(context.getPackageName(), com.taobao.accs.utl.a.channelService);
            intent.putExtra(Constants.KEY_PACKAGE_NAME, context.getPackageName());
            intent.putExtra("command", i);
            intent.putExtra("appKey", this.a.b);
            return intent;
        }
        ALog.e(this.c, "getIntent null command:" + i + " serviceEnable:" + UtilityImpl.getServiceEnabled(context), new Object[0]);
        return null;
    }

    public void forceDisableService(Context context) {
        UtilityImpl.focusDisableService(context);
    }

    public void forceEnableService(Context context) {
        UtilityImpl.focusEnableService(context);
    }

    @Deprecated
    public void setMode(Context context, int i) {
        ACCSClient.setEnvironment(context, i);
    }

    private void a(Context context, int i, String str, String str2) {
        Intent intent = new Intent(Constants.ACTION_RECEIVE);
        intent.setPackage(context.getPackageName());
        intent.putExtra("command", i);
        intent.putExtra(Constants.KEY_SERVICE_ID, str);
        intent.putExtra(Constants.KEY_DATA_ID, str2);
        intent.putExtra("appKey", this.a.b);
        intent.putExtra(Constants.KEY_ERROR_CODE, i == 2 ? 200 : ErrorCode.APP_NOT_BIND);
        e.a(context, intent);
    }

    public void setProxy(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        if (!TextUtils.isEmpty(str)) {
            edit.putString(Constants.KEY_PROXY_HOST, str);
        }
        edit.putInt(Constants.KEY_PROXY_PORT, i);
        edit.apply();
    }

    public void startInAppConnection(Context context, String str, String str2, IAppReceiver iAppReceiver) {
        startInAppConnection(context, str, null, str2, iAppReceiver);
    }

    public void startInAppConnection(Context context, String str, String str2, String str3, IAppReceiver iAppReceiver) {
        GlobalClientInfo.getInstance(context).setAppReceiver(str, iAppReceiver);
        if (UtilityImpl.isMainProcess(context)) {
            ALog.d(this.c, "startInAppConnection APPKEY:" + str, new Object[0]);
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.equals(this.a.i(), str)) {
                    this.a.a = str3;
                    this.a.b = str;
                    UtilityImpl.saveAppKey(context, str, this.a.h.getAppSecret());
                }
                this.a.a();
                return;
            }
            return;
        }
        ALog.d(this.c, "inapp only init in main process!", new Object[0]);
    }

    public void setLoginInfo(Context context, ILoginInfo iLoginInfo) {
        GlobalClientInfo.getInstance(context).setLoginInfoImpl(this.a.i(), iLoginInfo);
    }

    public void clearLoginInfo(Context context) {
        GlobalClientInfo.getInstance(context).clearLoginInfoImpl();
    }

    public boolean cancel(Context context, String str) {
        return this.a.a(str);
    }

    public Map<String, Boolean> getChannelState() throws Exception {
        String userUnit = getUserUnit();
        a aVar = this.a;
        GlobalClientInfo.getContext();
        String b = aVar.b(null);
        Map<String, Boolean> hashMap = new HashMap();
        hashMap.put(userUnit, Boolean.valueOf(false));
        hashMap.put(b, Boolean.valueOf(false));
        Session throwsException = SessionCenter.getInstance(this.a.h.getTag()).getThrowsException(b, 60000);
        Session throwsException2 = SessionCenter.getInstance(this.a.h.getTag()).getThrowsException(userUnit, 60000);
        if (throwsException != null) {
            hashMap.put(b, Boolean.valueOf(true));
        }
        if (throwsException2 != null) {
            hashMap.put(userUnit, Boolean.valueOf(true));
        }
        ALog.d(this.c, "getChannelState " + hashMap.toString(), new Object[0]);
        return hashMap;
    }

    public Map<String, Boolean> forceReConnectChannel() throws Exception {
        SessionCenter.getInstance(this.a.h.getTag()).forceRecreateAccsSession();
        return getChannelState();
    }

    public String getUserUnit() {
        Context context = GlobalClientInfo.getContext();
        if (context == null) {
            ALog.e(this.c, "context is null", new Object[0]);
            return null;
        }
        String b = this.a.b(StrategyCenter.getInstance().getUnitPrefix(GlobalClientInfo.getInstance(context).getUserId(this.a.i()), UtilityImpl.getDeviceId(context)));
        if (!ALog.isPrintLog(Level.D)) {
            return b;
        }
        ALog.d(this.c, "getUserUnit " + b, new Object[0]);
        return b;
    }

    public boolean isChannelError(int i) {
        return ErrorCode.isChannelError(i);
    }

    public void registerSerivce(Context context, String str, String str2) {
        GlobalClientInfo.getInstance(context).registerService(str, str2);
    }

    public void unRegisterSerivce(Context context, String str) {
        GlobalClientInfo.getInstance(context).unRegisterService(str);
    }

    public void registerDataListener(Context context, String str, AccsAbstractDataListener accsAbstractDataListener) {
        GlobalClientInfo.getInstance(context).registerListener(str, accsAbstractDataListener);
    }

    public void unRegisterDataListener(Context context, String str) {
        GlobalClientInfo.getInstance(context).unregisterListener(str);
    }

    public void sendBusinessAck(String str, String str2, String str3, short s, String str4, Map<ExtHeaderType, String> map) {
        this.a.b(Message.buildPushAck(this.a, str, str2, str3, true, s, str4, map), true);
    }
}
