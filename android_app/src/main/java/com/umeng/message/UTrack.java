package com.umeng.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.taobao.agoo.TaobaoRegister;
import com.umeng.message.common.UmLog;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.impl.json.JUtrack;
import com.umeng.message.common.inter.IUtrack;
import com.umeng.message.entity.UMessage;
import com.umeng.message.entity.Ucode;
import com.umeng.message.proguard.c;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.l;
import com.umeng.message.proguard.l.a;
import com.umeng.message.proguard.l.b;
import com.umeng.message.util.HttpRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UTrack {
    private static final String a = UTrack.class.getName();
    private static UTrack d;
    private static IUtrack e;
    private static boolean j = false;
    private static boolean k = false;
    private static boolean l = false;
    private static boolean m = false;
    private JSONObject b;
    private JSONObject c;
    private ScheduledThreadPoolExecutor f;
    private Context g;
    private boolean h;
    private final String i = "appkey";

    class UTrack_11 implements Runnable {
        final /* synthetic */ UTrack a;

        UTrack_11(UTrack uTrack) {
            this.a = uTrack;
        }

        public void run() {
            try {
                UTrack.e.sendAliasFailLog(this.a.g());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class UTrack_6 implements Runnable {
        final /* synthetic */ UTrack a;

        UTrack_6(UTrack uTrack) {
            this.a = uTrack;
        }

        public void run() {
            try {
                ArrayList a = l.a(this.a.g).a();
                for (int i = 0; i < a.size(); i++) {
                    a aVar = (a) a.get(i);
                    this.a.b(aVar.a, aVar.c, aVar.b);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                UmLog.d(UTrack.a, th.toString());
            } finally {
                UmLog.d(UTrack.a, "sendCachedMsgLog finished, clear cacheLogSending flag");
                UTrack.j = false;
            }
        }
    }

    class UTrack_7 implements Runnable {
        final /* synthetic */ UTrack a;

        UTrack_7(UTrack uTrack) {
            this.a = uTrack;
        }

        public void run() {
            try {
                ArrayList c = l.a(this.a.g).c();
                for (int i = 0; i < c.size(); i++) {
                    b bVar = (b) c.get(i);
                    this.a.sendMsgLogForAgoo(bVar.a, bVar.b, bVar.c, bVar.d);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                UmLog.d(UTrack.a, th.toString());
            } finally {
                UTrack.k = false;
            }
        }
    }

    class UTrack_8 implements Runnable {
        final /* synthetic */ UTrack a;

        UTrack_8(UTrack uTrack) {
            this.a = uTrack;
        }

        public void run() {
            try {
                JSONObject b = this.a.g();
                JSONArray c = this.a.c();
                if (c != null) {
                    b.put(MsgConstant.KEY_UCODE, c.a(c.toString()));
                }
                UTrack.e.trackAppLaunch(b, true);
            } catch (Exception e) {
                e.printStackTrace();
                if (!(e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPS_ERROR))) {
                    try {
                        UTrack.e.trackAppLaunch(null, false);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                UmLog.d(UTrack.a, e.toString());
            } finally {
                UTrack.l = false;
            }
        }
    }

    class UTrack_9 implements Runnable {
        final /* synthetic */ UTrack a;

        UTrack_9(UTrack uTrack) {
            this.a = uTrack;
        }

        public void run() {
            try {
                JSONObject b = this.a.g();
                UmLog.i(UTrack.a, "trackRegister-->request:" + b.toString());
                String d = this.a.f();
                if (!h.d(d)) {
                    UmLog.d(UTrack.a, "TestDevice sign =" + d);
                    b.put("TD", d);
                }
                UTrack.e.trackRegister(b, true);
                UTrack.m = false;
            } catch (Exception e) {
                e.printStackTrace();
                if (!(e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPS_ERROR))) {
                    try {
                        UTrack.e.trackRegister(null, false);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                UTrack.m = false;
            } catch (Throwable th) {
                UTrack.m = false;
            }
        }
    }

    public interface ICallBack {
        void onMessage(boolean z, String str);
    }

    enum SuccessState {
        SUCCESS_CACHE,
        SUCCESS,
        FAIL_REQUEST,
        FAIL_PARAM
    }

    public void setClearPrevMessage(boolean z) {
        this.h = z;
    }

    private UTrack(Context context) {
        this.g = context.getApplicationContext();
        this.f = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
        d();
    }

    public static synchronized UTrack getInstance(Context context) {
        UTrack uTrack;
        synchronized (UTrack.class) {
            if (d == null) {
                d = new UTrack(context);
                e = new JUtrack(context);
            }
            uTrack = d;
        }
        return uTrack;
    }

    public void trackMsgArrival(UMessage uMessage) {
        if (uMessage != null && uMessage.msg_id != null) {
            a(uMessage.msg_id, 0, uMessage.random_min * 60000);
        }
    }

    public void trackMsgClick(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            a(uMessage.msg_id, 1, uMessage.random_min * 60000);
        }
        if (!(uMessage == null || uMessage.message_id == null)) {
            a(uMessage.message_id, uMessage.task_id, "8");
        }
        if (this.h) {
            ((UmengMessageHandler) PushAgent.getInstance(this.g).getMessageHandler()).setPrevMessage(null);
        }
    }

    public void trackMiPushMsgClick(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            a(uMessage.msg_id, 21, uMessage.random_min * 60000);
        }
        if (this.h) {
            ((UmengMessageHandler) PushAgent.getInstance(this.g).getMessageHandler()).setPrevMessage(null);
        }
    }

    public void trackMsgDismissed(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            a(uMessage.msg_id, 2, uMessage.random_min * 60000);
        }
        if (!(uMessage == null || uMessage.message_id == null)) {
            a(uMessage.message_id, uMessage.task_id, "9");
        }
        if (this.h) {
            ((UmengMessageHandler) PushAgent.getInstance(this.g).getMessageHandler()).setPrevMessage(null);
        }
    }

    public void trackMsgPulled(UMessage uMessage, int i) {
        if (uMessage != null && uMessage.msg_id != null) {
            a(uMessage.msg_id, i, uMessage.random_min * 60000);
        }
    }

    private void a(String str, int i, long j) {
        if (!e()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            UmLog.e(a, "trackMsgLog: empty msgId");
            return;
        }
        long j2;
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            l.a(this.g).a(str, i, currentTimeMillis);
        } catch (Exception e) {
            e.printStackTrace();
            UmLog.d(a, "trackMsgLog: " + e.toString());
        }
        final String str2 = str;
        final int i2 = i;
        Runnable uTrack_1 = new Runnable(this) {
            final /* synthetic */ UTrack d;

            public void run() {
                this.d.b(str2, i2, currentTimeMillis);
            }
        };
        if (j <= 0 || i == 1 || i == 21) {
            j2 = 0;
        } else {
            j2 = Math.abs(new Random().nextLong() % j);
        }
        UmLog.d(a, String.format("trackMsgLog(msgId=%s, actionType=%d, random=%d, delay=%d)", new Object[]{str, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2)}));
        this.f.schedule(uTrack_1, j2, TimeUnit.MILLISECONDS);
    }

    private void a(String str, String str2, String str3) {
        if (!e()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            UmLog.e(a, "trackMsgLogForAgoo: empty msgId");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            l.a(this.g).a(str, str2, str3, currentTimeMillis);
        } catch (Exception e) {
            e.printStackTrace();
            UmLog.d(a, "trackMsgLog: " + e.toString());
        }
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final long j = currentTimeMillis;
        this.f.submit(new Runnable(this) {
            final /* synthetic */ UTrack e;

            public void run() {
                this.e.sendMsgLogForAgoo(str4, str5, str6, j);
            }
        });
    }

    private synchronized void b(String str, int i, long j) {
        JSONObject jSONObject = null;
        try {
            jSONObject = h();
            jSONObject.put("msg_id", str);
            jSONObject.put(MsgConstant.KEY_ACTION_TYPE, i);
            jSONObject.put("ts", j);
            e.sendMsgLog(jSONObject, str, i, j, true);
        } catch (Exception e) {
            e.printStackTrace();
            if (!(e == null || e.getMessage() == null)) {
                UmLog.d(a, e.toString());
                if (e.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                    try {
                        e.sendMsgLog(jSONObject, str, i, j, false);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized void sendMsgLogForAgoo(String str, String str2, String str3, long j) {
        UmLog.d(a, "sendMsgLogForAgoo-->msgId:" + str + ",taskId:" + str2);
        if (str3.equalsIgnoreCase("8")) {
            TaobaoRegister.clickMessage(this.g, str, str2);
        } else {
            TaobaoRegister.dismissMessage(this.g, str, str2);
        }
        l.a(this.g).b(str, str3);
        if (!str3.equals("7")) {
            l.a(this.g).d(str);
        }
    }

    public void sendCachedMsgLog(long j) {
        if (!e()) {
            return;
        }
        if (j || k) {
            UmLog.d(a, "sendCachedMsgLog already in queue, abort this request.");
            return;
        }
        UmLog.d(a, "sendCachedMsgLog start, set cacheLogSending flag");
        j = true;
        k = true;
        Runnable uTrack_6 = new UTrack_6(this);
        UmLog.d(a, String.format("sendCachedMsgLog(delay=%d)", new Object[]{Long.valueOf(j)}));
        this.f.schedule(uTrack_6, j, TimeUnit.MILLISECONDS);
        this.f.submit(new UTrack_7(this));
    }

    public void trackAppLaunch(long j) {
        if (!e()) {
            return;
        }
        if (MessageSharedPrefs.getInstance(this.g).getAppLaunchLogSendPolicy() == 1) {
            UmLog.d(a, "launch_policy=1, skip sending app launch info.");
        } else if (!MessageSharedPrefs.getInstance(this.g).hasAppLaunchLogSentToday()) {
            a(j);
        }
    }

    private void a(long j) {
        if (l) {
            UmLog.d(a, "trackAppLaunch already in queue, abort this request.");
            return;
        }
        UmLog.d(a, "trackAppLaunch start, set appLaunchSending flag");
        l = true;
        Runnable uTrack_8 = new UTrack_8(this);
        UmLog.d(a, String.format("trackAppLaunch(delay=%d)", new Object[]{Long.valueOf(j)}));
        this.f.schedule(uTrack_8, j, TimeUnit.MILLISECONDS);
    }

    private JSONArray c() {
        List g;
        int i;
        JSONArray jSONArray;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        String ucode = MessageSharedPrefs.getInstance(this.g).getUcode();
        if (!(ucode == null || ucode.equals(""))) {
            try {
                g = h.g(ucode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (g == null) {
                return null;
            }
            for (i = 0; i < g.size(); i++) {
                stringBuilder.append("{");
                stringBuilder.append("\"p\":");
                stringBuilder.append("\"");
                stringBuilder.append(((Ucode) g.get(i)).p);
                stringBuilder.append("\"");
                stringBuilder.append(",");
                stringBuilder.append("\"t\":");
                stringBuilder.append(((Ucode) g.get(i)).b);
                stringBuilder.append("}");
                if (i != g.size() - 1) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("]");
            try {
                jSONArray = new JSONArray(stringBuilder.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
                jSONArray = null;
            }
            return jSONArray;
        }
        g = null;
        if (g == null) {
            return null;
        }
        for (i = 0; i < g.size(); i++) {
            stringBuilder.append("{");
            stringBuilder.append("\"p\":");
            stringBuilder.append("\"");
            stringBuilder.append(((Ucode) g.get(i)).p);
            stringBuilder.append("\"");
            stringBuilder.append(",");
            stringBuilder.append("\"t\":");
            stringBuilder.append(((Ucode) g.get(i)).b);
            stringBuilder.append("}");
            if (i != g.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        jSONArray = new JSONArray(stringBuilder.toString());
        return jSONArray;
    }

    public void trackRegister() {
        if (!e() || MessageSharedPrefs.getInstance(this.g).getHasRegister()) {
            return;
        }
        if (m) {
            UmLog.d(a, "sendRegisterLog already in queue, abort this request.");
            return;
        }
        UmLog.d(a, "trackRegisterLog start, set registerSending flag");
        m = true;
        Runnable uTrack_9 = new UTrack_9(this);
        UmLog.d(a, String.format("trackRegister(delay=%d)", new Object[]{Integer.valueOf(0)}));
        this.f.schedule(uTrack_9, 0, TimeUnit.MILLISECONDS);
    }

    public void trackLocation(final byte[] bArr) {
        if (e()) {
            this.f.schedule(new Runnable(this) {
                final /* synthetic */ UTrack b;

                public void run() {
                    JSONObject jSONObject = null;
                    try {
                        jSONObject = this.b.i();
                        jSONObject.put("location", HttpRequest.a.a(bArr));
                        jSONObject.put("interval", PushAgent.getInstance(this.b.g).getLocationInterval());
                        UmLog.d(UTrack.a, jSONObject.toString());
                        UTrack.e.trackLocation(jSONObject, true);
                    } catch (Exception e) {
                        if (e != null && e.getMessage() != null && e.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                            try {
                                UTrack.e.trackLocation(jSONObject, false);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            }, 0, TimeUnit.MILLISECONDS);
        }
    }

    private void d() {
        com.umeng.message.common.b bVar;
        if (this.b == null) {
            bVar = new com.umeng.message.common.b();
            bVar.b(this.g, new String[0]);
            bVar.a(this.g, PushAgent.getInstance(this.g).getMessageAppkey(), PushAgent.getInstance(this.g).getMessageChannel());
            this.b = new JSONObject();
            try {
                bVar.b(this.b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.c == null) {
            bVar = new com.umeng.message.common.b();
            bVar.c(this.g, new String[0]);
            bVar.a(this.g, PushAgent.getInstance(this.g).getMessageAppkey(), PushAgent.getInstance(this.g).getMessageChannel());
            this.c = new JSONObject();
            try {
                bVar.c(this.c);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public JSONObject getHeader() {
        return this.b;
    }

    public void sendAliasFailLog() {
        new Thread(new UTrack_11(this)).start();
    }

    public void sendRegisterLog(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ UTrack b;

            public void run() {
                if (MessageSharedPrefs.getInstance(this.b.g).getDaRegisterSendPolicy() == 1) {
                    UmLog.d(UTrack.a, "da_register_policy=1, skip sending da_register info.");
                    return;
                }
                try {
                    JSONObject b = this.b.g();
                    b.put("registerLog", str);
                    UTrack.e.sendRegisterLog(b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean addAlias(final String str, final String str2, final ICallBack iCallBack) {
        new Thread(new Runnable(this) {
            final /* synthetic */ UTrack d;

            public void run() {
                SuccessState successState;
                Exception exception;
                String str = "";
                String str2 = "" + "utdid:" + UmengMessageDeviceConfig.getUtdid(this.d.g) + ",deviceToken:" + MessageSharedPrefs.getInstance(this.d.g).getDeviceToken() + ";";
                if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                    UmLog.e(UTrack.a, "addAlias: empty type or alias");
                    Object obj = str2 + "addAlias: empty type or alias;";
                    successState = SuccessState.FAIL_PARAM;
                } else {
                    String str3 = str2;
                    successState = null;
                }
                if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.d.g))) {
                    UmLog.e(UTrack.a, "UTDID is empty");
                    obj = obj + "UTDID is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.d.g).getDeviceToken())) {
                    UmLog.e(UTrack.a, "RegistrationId is empty");
                    obj = obj + "RegistrationId is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (MessageSharedPrefs.getInstance(this.d.g).isAliasSet(0, str, str2)) {
                    UmLog.d(UTrack.a, String.format("addAlias: <%s, %s> has been synced to the server before. Ingore this request.", new Object[]{str, str2}));
                    str = str + String.format("addAlias: <%s, %s> has been synced to the server before. Ingore this request;", new Object[]{str, str2});
                    successState = SuccessState.SUCCESS_CACHE;
                    Object obj2 = str;
                } else {
                    String str4 = str;
                }
                JSONObject b;
                try {
                    b = this.d.g();
                    if (successState == null) {
                        try {
                            b.put("alias", str);
                            b.put("type", str2);
                            b.put(MsgConstant.KEY_LAST_ALIAS, MessageSharedPrefs.getInstance(this.d.g).getLastAlias(0, str2));
                            b.put("ts", System.currentTimeMillis());
                        } catch (Exception e) {
                            exception = e;
                            if (exception != null || exception.getMessage() == null) {
                                iCallBack.onMessage(false, "alias:" + str + "添加失败");
                                MessageSharedPrefs.getInstance(this.d.g).addAlias(str, str2, 0, 1, "添加失败");
                            }
                            if (exception.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                                try {
                                    UTrack.e.addAlias(str, str2, b, iCallBack, false);
                                } catch (Exception e2) {
                                    if (e2 == null) {
                                        iCallBack.onMessage(false, "alias:" + str + "添加失败");
                                    } else {
                                        iCallBack.onMessage(false, "alias:" + str + "添加失败:" + exception.getMessage());
                                    }
                                }
                            } else {
                                iCallBack.onMessage(false, "alias:" + str + "添加失败:" + exception.getMessage());
                            }
                            MessageSharedPrefs.getInstance(this.d.g).addAlias(str, str2, 0, 1, exception.getMessage());
                            return;
                        }
                    } else if (successState == SuccessState.FAIL_PARAM) {
                        b.put("fail", obj);
                    } else if (successState == SuccessState.SUCCESS_CACHE) {
                        b.put("success", obj2);
                    }
                    UTrack.e.addAlias(str, str2, b, iCallBack, true);
                } catch (Exception e22) {
                    exception = e22;
                    b = null;
                    if (exception != null) {
                    }
                    iCallBack.onMessage(false, "alias:" + str + "添加失败");
                    MessageSharedPrefs.getInstance(this.d.g).addAlias(str, str2, 0, 1, "添加失败");
                }
            }
        }).start();
        return false;
    }

    public void addExclusiveAlias(final String str, final String str2, final ICallBack iCallBack) {
        new Thread(new Runnable(this) {
            final /* synthetic */ UTrack d;

            public void run() {
                SuccessState successState;
                Exception exception;
                String str = "";
                String str2 = "";
                if (TextUtils.isEmpty(str2)) {
                    UmLog.e(UTrack.a, "addExclusiveAlias: empty type");
                    Object obj = str + "addExclusiveAlias: empty type";
                    successState = SuccessState.FAIL_PARAM;
                } else {
                    String str3 = str;
                    successState = null;
                }
                if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.d.g))) {
                    UmLog.e(UTrack.a, "UTDID is empty");
                    obj = obj + "UTDID is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.d.g).getDeviceToken())) {
                    UmLog.e(UTrack.a, "RegistrationId is empty");
                    obj = obj + "RegistrationId is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (MessageSharedPrefs.getInstance(this.d.g).isAliasSet(1, str, str2)) {
                    UmLog.d(UTrack.a, String.format("addExclusiveAlias: <%s, %s> has been synced to the server before. Ingore this request.", new Object[]{str, str2}));
                    str2 = str2 + String.format("addExclusiveAlias: <%s, %s> has been synced to the server before. Ingore this request.", new Object[]{str, str2});
                    successState = SuccessState.SUCCESS_CACHE;
                    Object obj2 = str2;
                } else {
                    String str4 = str2;
                }
                JSONObject b;
                try {
                    b = this.d.g();
                    if (successState == null) {
                        try {
                            b.put("alias", str);
                            b.put("type", str2);
                            b.put(MsgConstant.KEY_LAST_ALIAS, MessageSharedPrefs.getInstance(this.d.g).getLastAlias(1, str2));
                            b.put("ts", System.currentTimeMillis());
                        } catch (Exception e) {
                            exception = e;
                            if (exception != null) {
                            }
                            iCallBack.onMessage(false, "alias:" + str + "添加失败");
                            MessageSharedPrefs.getInstance(this.d.g).addAlias(str, str2, 1, 1, "添加失败");
                            return;
                        }
                    } else if (successState == SuccessState.FAIL_PARAM) {
                        b.put("fail", obj);
                    } else if (successState == SuccessState.SUCCESS_CACHE) {
                        b.put("success", obj2);
                    }
                    UTrack.e.addExclusiveAlias(str, str2, b, iCallBack, true);
                } catch (Exception e2) {
                    exception = e2;
                    b = null;
                    if (exception != null || exception.getMessage() == null) {
                        iCallBack.onMessage(false, "alias:" + str + "添加失败");
                        MessageSharedPrefs.getInstance(this.d.g).addAlias(str, str2, 1, 1, "添加失败");
                        return;
                    }
                    if (exception.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                        try {
                            UTrack.e.addExclusiveAlias(str, str2, b, iCallBack, false);
                        } catch (Exception e22) {
                            if (e22 == null) {
                                iCallBack.onMessage(false, "alias:" + str + "添加失败");
                            } else {
                                iCallBack.onMessage(false, "alias:" + str + "添加失败:" + exception.getMessage());
                            }
                        }
                    } else {
                        iCallBack.onMessage(false, "alias:" + str + "添加失败:" + exception.getMessage());
                    }
                    MessageSharedPrefs.getInstance(this.d.g).addAlias(str, str2, 1, 1, exception.getMessage());
                }
            }
        }).start();
    }

    public void removeAlias(final String str, final String str2, final ICallBack iCallBack) {
        new Thread(new Runnable(this) {
            final /* synthetic */ UTrack d;

            public void run() {
                SuccessState successState;
                Exception exception;
                String str = "";
                String str2 = "";
                if (TextUtils.isEmpty(str2)) {
                    UmLog.e(UTrack.a, "removeAlias: empty type");
                    Object obj = str + "removeAlias: empty type";
                    successState = SuccessState.FAIL_PARAM;
                } else {
                    String str3 = str;
                    successState = null;
                }
                if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.d.g))) {
                    UmLog.e(UTrack.a, "UTDID is empty");
                    obj = obj + "UTDID is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.d.g).getDeviceToken())) {
                    UmLog.e(UTrack.a, "RegistrationId is empty");
                    obj = obj + "RegistrationId is empty;";
                    successState = SuccessState.FAIL_PARAM;
                }
                JSONObject b;
                try {
                    b = this.d.g();
                    if (successState == null) {
                        try {
                            b.put("alias", str);
                            b.put("type", str2);
                            b.put("ts", System.currentTimeMillis());
                        } catch (Exception e) {
                            exception = e;
                            if (exception != null) {
                            }
                            iCallBack.onMessage(false, "alias:" + str + "移除失败");
                        }
                    } else if (successState == SuccessState.FAIL_PARAM) {
                        b.put("fail", obj);
                    } else if (successState == SuccessState.SUCCESS_CACHE) {
                        b.put("success", str2);
                    }
                    UTrack.e.removeAlias(str, str2, b, iCallBack, true);
                } catch (Exception e2) {
                    exception = e2;
                    b = null;
                    if (exception != null || exception.getMessage() == null) {
                        iCallBack.onMessage(false, "alias:" + str + "移除失败");
                    } else if (exception.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                        try {
                            UTrack.e.removeAlias(str, str2, b, iCallBack, false);
                        } catch (Exception e22) {
                            if (e22 == null) {
                                iCallBack.onMessage(false, "alias:" + str + "移除失败");
                            } else {
                                iCallBack.onMessage(false, "alias:" + str + "移除失败:" + exception.getMessage());
                            }
                        }
                    } else {
                        iCallBack.onMessage(false, "alias:" + str + "移除失败:" + exception.getMessage());
                    }
                }
            }
        }).start();
    }

    private boolean e() {
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.g))) {
            UmLog.e(a, "UTDID is empty");
            return false;
        } else if (!TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.g).getDeviceToken())) {
            return true;
        } else {
            UmLog.e(a, "RegistrationId is empty");
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    private String f() {
        BufferedReader bufferedReader;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        try {
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return null;
            }
            String str = Environment.getExternalStorageDirectory().getPath() + "/data/" + this.g.getPackageName() + "/";
            UmLog.d(a, "path=" + str);
            File file = new File(str, "umeng-message.config");
            if (!file.exists()) {
                return null;
            }
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                do {
                    try {
                        str = bufferedReader.readLine();
                        if (str == null) {
                            if (bufferedReader == null) {
                                return null;
                            }
                            try {
                                bufferedReader.close();
                                return null;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                return null;
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        e2 = e4;
                    } catch (IOException e5) {
                        e3 = e5;
                    }
                } while (!str.startsWith("sign="));
                str = str.substring("sign=".length());
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return str;
            } catch (FileNotFoundException e7) {
                e2 = e7;
                bufferedReader = null;
                try {
                    e2.printStackTrace();
                    if (bufferedReader == null) {
                        return null;
                    }
                    try {
                        bufferedReader.close();
                        return null;
                    } catch (IOException e32) {
                        e32.printStackTrace();
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e62) {
                            e62.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e8) {
                e32 = e8;
                bufferedReader = null;
                e32.printStackTrace();
                if (bufferedReader == null) {
                    return null;
                }
                try {
                    bufferedReader.close();
                    return null;
                } catch (IOException e322) {
                    e322.printStackTrace();
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    private JSONObject g() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.g).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.g);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", this.b);
        jSONObject.put("utdid", utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    private JSONObject h() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.g).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.g);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", this.c);
        jSONObject.put("utdid", utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    private JSONObject i() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.g).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.g);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appkey", PushAgent.getInstance(this.g).getMessageAppkey());
        jSONObject.put("utdid", utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    public void updateHeader() {
        com.umeng.message.common.b bVar = new com.umeng.message.common.b();
        bVar.b(this.g, new String[0]);
        bVar.a(this.g, PushAgent.getInstance(this.g).getMessageAppkey(), PushAgent.getInstance(this.g).getMessageChannel());
        this.b = new JSONObject();
        try {
            bVar.b(this.b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bVar = new com.umeng.message.common.b();
        bVar.c(this.g, new String[0]);
        bVar.a(this.g, PushAgent.getInstance(this.g).getMessageAppkey(), PushAgent.getInstance(this.g).getMessageChannel());
        this.c = new JSONObject();
        try {
            bVar.c(this.c);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
