package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.f;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Taobao */
public class Message implements Serializable {
    public static int CONTROL_MAX_RETRY_TIMES = 5;
    public static final int EXT_HEADER_VALUE_MAX_LEN = 1023;
    public static final int FLAG_ACK_TYPE = 32;
    public static final int FLAG_BIZ_RET = 64;
    public static final int FLAG_DATA_TYPE = 32768;
    public static final int FLAG_ERR = 4096;
    public static final int FLAG_REQ_BIT1 = 16384;
    public static final int FLAG_REQ_BIT2 = 8192;
    public static final int FLAG_RET = 2048;
    public static final String KEY_BINDAPP = "ctrl_bindapp";
    public static final String KEY_BINDSERVICE = "ctrl_bindservice";
    public static final String KEY_BINDUSER = "ctrl_binduser";
    public static final String KEY_UNBINDAPP = "ctrl_unbindapp";
    public static final String KEY_UNBINDSERVICE = "ctrl_unbindservice";
    public static final String KEY_UNBINDUSER = "ctrl_unbinduser";
    public static final int MAX_RETRY_TIMES = 3;
    static long a = 1;
    byte[] A;
    int B;
    long C;
    transient NetPerformanceMonitor D;
    String E = null;
    public String appSign = null;
    byte b = (byte) 0;
    public String bizId = null;
    byte c = (byte) 0;
    public Integer command = null;
    public String cunstomDataId;
    short d;
    public String dataId;
    public long delyTime = 0;
    short e;
    short f;
    public boolean force = false;
    byte g;
    byte h;
    public URL host;
    String i;
    public boolean isAck = false;
    public boolean isCancel = false;
    String j;
    int k = -1;
    Map<ExtHeaderType, String> l;
    String m = null;
    Integer n = Integer.valueOf(0);
    String o = null;
    Integer p = null;
    String q = null;
    String r = null;
    public int retryTimes = 0;
    String s = null;
    public String serviceId = null;
    public long startSendTime;
    Integer t = null;
    public int timeout = 40000;
    String u = null;
    public String userinfo = null;
    String v = null;
    String w = null;
    String x = null;
    String y = null;
    String z = null;

    /* compiled from: Taobao */
    public enum ReqType {
        DATA,
        ACK,
        REQ,
        RES;

        public static ReqType valueOf(int i) {
            switch (i) {
                case 0:
                    return DATA;
                case 1:
                    return ACK;
                case 2:
                    return REQ;
                case 3:
                    return RES;
                default:
                    return DATA;
            }
        }
    }

    /* compiled from: Taobao */
    public static class a {
        public static final int INVALID = -1;
        public static final int NEED_ACK = 1;
        public static final int NO_ACK = 0;

        public static int a(int i) {
            switch (i) {
                case 0:
                    return 0;
                default:
                    return 1;
            }
        }

        public static String b(int i) {
            switch (i) {
                case 0:
                    return "NO_ACK";
                case 1:
                    return "NEED_ACK";
                default:
                    return "INVALID";
            }
        }
    }

    /* compiled from: Taobao */
    public static class b {
        public static final int CONTROL = 0;
        public static final int DATA = 1;
        public static final int HANDSHAKE = 3;
        public static final int INVALID = -1;
        public static final int PING = 2;

        public static int a(int i) {
            switch (i) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                default:
                    return 0;
            }
        }

        public static String b(int i) {
            switch (i) {
                case 0:
                    return "CONTROL";
                case 1:
                    return "DATA";
                case 2:
                    return "PING";
                case 3:
                    return "HANDSHAKE";
                default:
                    return "INVALID";
            }
        }
    }

    private Message() {
        synchronized (Message.class) {
            long j = a;
            a = 1 + j;
            this.dataId = String.valueOf(j);
        }
        this.startSendTime = System.currentTimeMillis();
    }

    public int getNode() {
        return this.B;
    }

    public int getType() {
        return this.k;
    }

    public String getDataId() {
        return this.dataId;
    }

    public boolean isControlFrame() {
        return Constants.TARGET_CONTROL.equals(this.i);
    }

    public int getIntDataId() {
        try {
            if (this.isAck) {
                return -((int) a);
            }
            return Integer.valueOf(this.dataId).intValue();
        } catch (Exception e) {
            ALog.w("Msg", "parse int dataId error " + this.dataId, new Object[0]);
            return -1;
        }
    }

    public void setSendTime(long j) {
        this.C = j;
    }

    public NetPerformanceMonitor getNetPermanceMonitor() {
        return this.D;
    }

    public long getDelyTime() {
        return this.delyTime;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }

    private String c() {
        return "Msg" + this.E;
    }

    public String getPackageName() {
        return this.m == null ? "" : this.m;
    }

    public boolean isTimeOut() {
        boolean z = (System.currentTimeMillis() - this.startSendTime) + this.delyTime >= ((long) this.timeout);
        if (z) {
            ALog.e(c(), "delay time:" + this.delyTime + " beforeSendTime:" + (System.currentTimeMillis() - this.startSendTime) + " timeout" + this.timeout, new Object[0]);
        }
        return z;
    }

    public byte[] build(Context context, int i) {
        String str;
        byte[] bytes;
        int i2;
        try {
            b();
        } catch (Throwable e) {
            ALog.e(c(), "build1", e, new Object[0]);
        } catch (Throwable e2) {
            ALog.e(c(), "build2", e2, new Object[0]);
        }
        if (this.A != null) {
            str = new String(this.A);
        } else {
            str = "";
        }
        a();
        if (!this.isAck) {
            String str2;
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder append = stringBuilder.append(UtilityImpl.getDeviceId(context)).append("|").append(this.m).append("|").append(this.serviceId == null ? "" : this.serviceId).append("|");
            if (this.userinfo == null) {
                str2 = "";
            } else {
                str2 = this.userinfo;
            }
            append.append(str2);
            this.j = stringBuilder.toString();
        }
        try {
            bytes = (this.dataId + "").getBytes("utf-8");
            this.h = (byte) this.j.getBytes("utf-8").length;
            this.g = (byte) this.i.getBytes("utf-8").length;
        } catch (Throwable e22) {
            e22.printStackTrace();
            ALog.e(c(), "build3", e22, new Object[0]);
            bytes = (this.dataId + "").getBytes();
            this.h = (byte) this.j.getBytes().length;
            this.g = (byte) this.i.getBytes().length;
        }
        short a = a(this.l);
        int length = bytes.length + ((((this.g + 3) + 1) + this.h) + 1);
        if (this.A == null) {
            i2 = 0;
        } else {
            i2 = this.A.length;
        }
        this.e = (short) (((i2 + length) + a) + 2);
        this.d = (short) (this.e + 2);
        f fVar = new f((this.d + 2) + 4);
        ALog.d(c(), "Build Message", new Object[0]);
        try {
            fVar.a((byte) (this.b | 32));
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\tversion:2 compress:" + this.b, new Object[0]);
            }
            if (i == 0) {
                fVar.a(Byte.MIN_VALUE);
                ALog.d(c(), "\tflag: 0x80", new Object[0]);
            } else {
                fVar.a((byte) 64);
                ALog.d(c(), "\tflag: 0x40", new Object[0]);
            }
            fVar.a(this.d);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\ttotalLength:" + this.d, new Object[0]);
            }
            fVar.a(this.e);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\tdataLength:" + this.e, new Object[0]);
            }
            fVar.a(this.f);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\tflags:" + Integer.toHexString(this.f), new Object[0]);
            }
            fVar.a(this.g);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\ttargetLength:" + this.g, new Object[0]);
            }
            fVar.write(this.i.getBytes("utf-8"));
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\ttarget:" + new String(this.i), new Object[0]);
            }
            fVar.a(this.h);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\tsourceLength:" + this.h, new Object[0]);
            }
            fVar.write(this.j.getBytes("utf-8"));
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\tsource:" + new String(this.j), new Object[0]);
            }
            fVar.a((byte) bytes.length);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\tdataIdLength:" + bytes.length, new Object[0]);
            }
            fVar.write(bytes);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\tdataId:" + new String(bytes), new Object[0]);
            }
            fVar.a(a);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\textHeader len:" + a, new Object[0]);
            }
            if (this.l != null) {
                for (ExtHeaderType extHeaderType : this.l.keySet()) {
                    String str3 = (String) this.l.get(extHeaderType);
                    if (!TextUtils.isEmpty(str3)) {
                        fVar.a((short) ((((short) extHeaderType.ordinal()) << 10) | ((short) (str3.getBytes("utf-8").length & EXT_HEADER_VALUE_MAX_LEN))));
                        fVar.write(str3.getBytes("utf-8"));
                        if (ALog.isPrintLog(Level.D)) {
                            ALog.d(c(), "\textHeader key:" + extHeaderType + " value:" + str3, new Object[0]);
                        }
                    }
                }
            }
            if (this.A != null) {
                fVar.write(this.A);
            }
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(c(), "\toriData:" + str, new Object[0]);
            }
            fVar.flush();
        } catch (Throwable e222) {
            ALog.e(c(), "build4", e222, new Object[0]);
        }
        bytes = fVar.toByteArray();
        a(bytes);
        try {
            fVar.close();
        } catch (Throwable e3) {
            ALog.e(c(), "build5", e3, new Object[0]);
        }
        return bytes;
    }

    short a(Map<ExtHeaderType, String> map) {
        short s = (short) 0;
        if (map != null) {
            try {
                for (ExtHeaderType extHeaderType : map.keySet()) {
                    short s2;
                    String str = (String) map.get(extHeaderType);
                    if (TextUtils.isEmpty(str)) {
                        s2 = s;
                    } else {
                        s2 = (short) ((((short) (str.getBytes("utf-8").length & EXT_HEADER_VALUE_MAX_LEN)) + 2) + s);
                    }
                    s = s2;
                }
            } catch (Exception e) {
                e.toString();
            }
        }
        return s;
    }

    void a() {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        GZIPOutputStream gZIPOutputStream = null;
        try {
            if (this.A == null) {
                if (null != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception e) {
                        return;
                    }
                }
                if (null != null) {
                    byteArrayOutputStream3.close();
                    return;
                }
                return;
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(this.A);
                    gZIPOutputStream.finish();
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    if (toByteArray != null && toByteArray.length < this.A.length) {
                        this.A = toByteArray;
                        this.b = (byte) 1;
                    }
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e2) {
                            return;
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream = null;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            gZIPOutputStream = null;
            byteArrayOutputStream = null;
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    void b() throws JSONException, UnsupportedEncodingException {
        if (this.command != null && this.command.intValue() != 100 && this.command.intValue() != 102 && this.command.intValue() != 105) {
            this.A = new com.taobao.accs.utl.d.a().a("command", this.command.intValue() == 100 ? null : this.command).a("appKey", this.o).a(Constants.KEY_OS_TYPE, this.p).a("sign", this.appSign).a(Constants.KEY_SDK_VERSION, this.t).a("appVersion", this.s).a(Constants.KEY_TTID, this.u).a(Constants.KEY_MODEL, this.w).a(Constants.KEY_BRAND, this.x).a("imei", this.z).a(Constants.KEY_IMSI, this.z).a("os", this.q).a().toString().getBytes("utf-8");
        }
    }

    void a(byte[] bArr) {
        String str = "";
        if (ALog.isPrintLog(Level.D)) {
            ALog.d(c(), "len:" + bArr.length, new Object[0]);
            if (bArr.length < 512) {
                String str2 = str;
                for (byte b : bArr) {
                    str2 = str2 + Integer.toHexString(b & 255) + " ";
                }
                ALog.d(c(), str2, new Object[0]);
            }
        }
    }

    public static Message BuildPing(boolean z, int i) {
        Message message = new Message();
        message.k = 2;
        message.command = Integer.valueOf(201);
        message.force = z;
        message.delyTime = (long) i;
        return message;
    }

    public static Message buildHandshake(String str) {
        Message message = new Message();
        message.a(3, ReqType.DATA, 1);
        message.m = str;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(200);
        return message;
    }

    public static Message buildBindApp(com.taobao.accs.net.a aVar, Context context, Intent intent) {
        Message buildBindApp;
        Throwable e;
        try {
            String stringExtra = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_USER_ID);
            String stringExtra3 = intent.getStringExtra("appKey");
            String stringExtra4 = intent.getStringExtra(Constants.KEY_TTID);
            String stringExtra5 = intent.getStringExtra("sid");
            String stringExtra6 = intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE);
            String stringExtra7 = intent.getStringExtra("appVersion");
            buildBindApp = buildBindApp(context, stringExtra3, intent.getStringExtra("app_sercet"), stringExtra, stringExtra4, stringExtra7, stringExtra5, stringExtra2, stringExtra6);
            try {
                a(aVar, buildBindApp);
            } catch (Exception e2) {
                e = e2;
                ALog.e("Msg", "buildBindApp", e, new Object[0]);
                e.printStackTrace();
                return buildBindApp;
            }
        } catch (Throwable e3) {
            e = e3;
            buildBindApp = null;
            ALog.e("Msg", "buildBindApp", e, new Object[0]);
            e.printStackTrace();
            return buildBindApp;
        }
        return buildBindApp;
    }

    public static Message buildBindApp(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        if (TextUtils.isEmpty(str3)) {
            return null;
        }
        Message message = new Message();
        try {
            message.B = 1;
            message.a(1, ReqType.DATA, 1);
            message.p = Integer.valueOf(1);
            message.q = VERSION.SDK_INT + "";
            message.m = str3;
            message.i = Constants.TARGET_CONTROL;
            message.command = Integer.valueOf(1);
            message.o = str;
            message.appSign = UtilityImpl.getAppsign(context, str, str2, UtilityImpl.getDeviceId(context), null, null);
            message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
            message.s = str5;
            message.m = str3;
            message.u = str4;
            message.w = Build.MODEL;
            message.x = Build.BRAND;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            message.y = telephonyManager != null ? telephonyManager.getDeviceId() : null;
            message.z = telephonyManager != null ? telephonyManager.getSubscriberId() : null;
            message.cunstomDataId = KEY_BINDAPP;
            message.E = str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public static Message buildUnbindApp(com.taobao.accs.net.a aVar, Context context, Intent intent) {
        Message buildUnbindApp;
        Throwable e;
        ALog.e("Msg", "buildUnbindApp1" + UtilityImpl.getStackMsg(new Exception()), new Object[0]);
        try {
            String stringExtra = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildUnbindApp = buildUnbindApp(aVar, context, stringExtra, intent.getStringExtra("sid"), stringExtra2, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(aVar, buildUnbindApp);
            } catch (Exception e2) {
                e = e2;
                ALog.e("Msg", "buildUnbindApp1", e, new Object[0]);
                e.printStackTrace();
                return buildUnbindApp;
            }
        } catch (Throwable e3) {
            e = e3;
            buildUnbindApp = null;
            ALog.e("Msg", "buildUnbindApp1", e, new Object[0]);
            e.printStackTrace();
            return buildUnbindApp;
        }
        return buildUnbindApp;
    }

    public static Message buildUnbindApp(com.taobao.accs.net.a aVar, Context context, String str, String str2, String str3, String str4) {
        Message message;
        Throwable e;
        try {
            ALog.e("Msg", "buildUnbindApp" + UtilityImpl.getStackMsg(new Exception()), new Object[0]);
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            message = new Message();
            try {
                message.B = 1;
                message.a(1, ReqType.DATA, 1);
                message.m = str;
                message.i = Constants.TARGET_CONTROL;
                message.command = Integer.valueOf(2);
                message.m = str;
                message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
                message.cunstomDataId = KEY_UNBINDAPP;
                a(aVar, message);
                return message;
            } catch (Exception e2) {
                e = e2;
                ALog.e("Msg", "buildUnbindApp", e, new Object[0]);
                e.printStackTrace();
                return message;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            message = null;
            e = th;
            ALog.e("Msg", "buildUnbindApp", e, new Object[0]);
            e.printStackTrace();
            return message;
        }
    }

    public static Message buildBindService(com.taobao.accs.net.a aVar, Context context, Intent intent) {
        Message buildBindService;
        Throwable e;
        try {
            String stringExtra = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_SERVICE_ID);
            String stringExtra3 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildBindService = buildBindService(context, stringExtra, intent.getStringExtra("appKey"), stringExtra2, intent.getStringExtra("sid"), stringExtra3, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(aVar, buildBindService);
            } catch (Exception e2) {
                e = e2;
                ALog.e("Msg", "buildBindService", e, new Object[0]);
                e.printStackTrace();
                return buildBindService;
            }
        } catch (Throwable e3) {
            e = e3;
            buildBindService = null;
            ALog.e("Msg", "buildBindService", e, new Object[0]);
            e.printStackTrace();
            return buildBindService;
        }
        return buildBindService;
    }

    public static Message buildBindService(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.serviceId = str3;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(5);
        message.m = str;
        message.serviceId = str3;
        message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_BINDSERVICE;
        message.E = str2;
        return message;
    }

    public static Message buildUnbindService(com.taobao.accs.net.a aVar, Context context, Intent intent) {
        Message buildUnbindService;
        Throwable e;
        try {
            String stringExtra = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_SERVICE_ID);
            String stringExtra3 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildUnbindService = buildUnbindService(context, stringExtra, intent.getStringExtra("appKey"), stringExtra2, intent.getStringExtra("sid"), stringExtra3, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(aVar, buildUnbindService);
            } catch (Exception e2) {
                e = e2;
                ALog.e("Msg", "buildUnbindService", e, new Object[0]);
                e.printStackTrace();
                return buildUnbindService;
            }
        } catch (Throwable e3) {
            e = e3;
            buildUnbindService = null;
            ALog.e("Msg", "buildUnbindService", e, new Object[0]);
            e.printStackTrace();
            return buildUnbindService;
        }
        return buildUnbindService;
    }

    public static Message buildUnbindService(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.serviceId = str3;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(6);
        message.m = str;
        message.serviceId = str3;
        message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_UNBINDSERVICE;
        message.E = str2;
        return message;
    }

    public static Message buildBindUser(com.taobao.accs.net.a aVar, Context context, Intent intent) {
        Message buildBindUser;
        Throwable e;
        try {
            String stringExtra = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildBindUser = buildBindUser(context, stringExtra, intent.getStringExtra("appKey"), intent.getStringExtra("sid"), stringExtra2, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(aVar, buildBindUser);
            } catch (Exception e2) {
                e = e2;
                ALog.e("Msg", "buildBindUser", e, new Object[0]);
                e.printStackTrace();
                return buildBindUser;
            }
        } catch (Throwable e3) {
            e = e3;
            buildBindUser = null;
            ALog.e("Msg", "buildBindUser", e, new Object[0]);
            e.printStackTrace();
            return buildBindUser;
        }
        return buildBindUser;
    }

    public static Message buildBindUser(Context context, String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str4)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.userinfo = str4;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(3);
        message.m = str;
        message.userinfo = str4;
        message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_BINDUSER;
        message.E = str2;
        return message;
    }

    public static Message buildUnbindUser(com.taobao.accs.net.a aVar, Context context, Intent intent) {
        Message buildUnbindUser;
        Throwable e;
        try {
            String stringExtra = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildUnbindUser = buildUnbindUser(context, stringExtra, intent.getStringExtra("appKey"), intent.getStringExtra("sid"), stringExtra2, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(aVar, buildUnbindUser);
            } catch (Exception e2) {
                e = e2;
                ALog.e("Msg", "buildUnbindUser", e, new Object[0]);
                e.printStackTrace();
                return buildUnbindUser;
            }
        } catch (Throwable e3) {
            e = e3;
            buildUnbindUser = null;
            ALog.e("Msg", "buildUnbindUser", e, new Object[0]);
            e.printStackTrace();
            return buildUnbindUser;
        }
        return buildUnbindUser;
    }

    public static Message buildUnbindUser(Context context, String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(4);
        message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_UNBINDUSER;
        message.E = str2;
        return message;
    }

    public static Message buildElection(String str, Map<String, Integer> map) {
        if (map == null) {
            return null;
        }
        Message message = new Message();
        try {
            message.a(1, ReqType.DATA, 1);
            message.B = 1;
            message.m = str;
            message.i = Constants.TARGET_ELECTION;
            message.command = Integer.valueOf(105);
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : map.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Constants.KEY_ELECTION_PKG, entry.getKey());
                jSONObject.put("sdkv", entry.getValue());
                jSONArray.put(jSONObject);
            }
            message.A = new com.taobao.accs.utl.d.a().a("sdkv", Integer.valueOf(Constants.SDK_VERSION_CODE)).a(Constants.KEY_ELECTION_PACKS, jSONArray).a().toString().getBytes("utf-8");
        } catch (Throwable th) {
            ALog.e("Msg", "buildElection", th, new Object[0]);
        }
        return message;
    }

    public static Message buildStatist(String str, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Message message = new Message();
        message.a(1, ReqType.DATA, 1);
        message.B = 1;
        message.A = bArr;
        message.m = str;
        message.i = Constants.TARGET_STATIST;
        message.command = Integer.valueOf(102);
        return message;
    }

    public static Message buildSendData(com.taobao.accs.net.a aVar, Context context, String str, String str2, AccsRequest accsRequest) {
        return buildSendData(aVar, context, str, str2, accsRequest, true);
    }

    public static Message buildSendData(com.taobao.accs.net.a aVar, Context context, String str, String str2, AccsRequest accsRequest, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.command = Integer.valueOf(100);
        message.m = str;
        message.serviceId = accsRequest.serviceId;
        message.userinfo = accsRequest.userId;
        message.A = accsRequest.data;
        message.i = Constants.TARGET_SERVICE_PRE + (TextUtils.isEmpty(accsRequest.targetServiceName) ? accsRequest.serviceId : accsRequest.targetServiceName) + "|" + (accsRequest.target == null ? "" : accsRequest.target);
        message.cunstomDataId = accsRequest.dataId;
        message.bizId = accsRequest.businessId;
        if (accsRequest.timeout > 0) {
            message.timeout = accsRequest.timeout;
        }
        if (z) {
            a(aVar, message, accsRequest);
        } else {
            message.host = accsRequest.host;
        }
        a(message, GlobalClientInfo.getInstance(context).getSid(str2), GlobalClientInfo.getInstance(context).getUserId(str2), GlobalClientInfo.c, accsRequest.businessId, accsRequest.tag);
        message.D = new NetPerformanceMonitor();
        message.D.setDataId(accsRequest.dataId);
        message.D.setServiceId(accsRequest.serviceId);
        message.D.setHost(message.host.toString());
        message.E = str2;
        return message;
    }

    public static Message buildRequest(com.taobao.accs.net.a aVar, Context context, String str, String str2, AccsRequest accsRequest, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.REQ, 1);
        message.command = Integer.valueOf(100);
        message.m = str;
        message.serviceId = accsRequest.serviceId;
        message.userinfo = accsRequest.userId;
        message.A = accsRequest.data;
        message.i = Constants.TARGET_SERVICE_PRE + (TextUtils.isEmpty(accsRequest.targetServiceName) ? accsRequest.serviceId : accsRequest.targetServiceName) + "|" + (accsRequest.target == null ? "" : accsRequest.target);
        message.cunstomDataId = accsRequest.dataId;
        message.bizId = accsRequest.businessId;
        message.E = str2;
        if (accsRequest.timeout > 0) {
            message.timeout = accsRequest.timeout;
        }
        if (z) {
            a(aVar, message, accsRequest);
        } else {
            message.host = accsRequest.host;
        }
        a(message, GlobalClientInfo.getInstance(context).getSid(str2), GlobalClientInfo.getInstance(context).getUserId(str2), GlobalClientInfo.c, accsRequest.businessId, accsRequest.tag);
        message.D = new NetPerformanceMonitor();
        message.D.setDataId(accsRequest.dataId);
        message.D.setServiceId(accsRequest.serviceId);
        message.D.setHost(message.host.toString());
        message.E = str2;
        return message;
    }

    public static Message buildRequest(com.taobao.accs.net.a aVar, Context context, String str, String str2, AccsRequest accsRequest) {
        return buildRequest(aVar, context, str, str2, accsRequest, true);
    }

    private static void a(com.taobao.accs.net.a aVar, Message message, AccsRequest accsRequest) {
        if (accsRequest.host == null) {
            try {
                message.host = new URL(aVar.b(null));
                return;
            } catch (Throwable e) {
                ALog.e("Msg", "setUnit", e, new Object[0]);
                e.printStackTrace();
                return;
            }
        }
        message.host = accsRequest.host;
    }

    private static void a(com.taobao.accs.net.a aVar, Message message) {
        try {
            message.host = new URL(aVar.b(null));
        } catch (Throwable e) {
            ALog.e("Msg", "setControlHost", e, new Object[0]);
        }
    }

    public static Message buildPushAck(com.taobao.accs.net.a aVar, String str, String str2, String str3, boolean z, short s, String str4, Map<ExtHeaderType, String> map) {
        Message message = new Message();
        message.B = 1;
        message.a(s, z);
        message.j = str;
        message.i = str2;
        message.dataId = str3;
        message.isAck = true;
        message.l = map;
        try {
            if (TextUtils.isEmpty(str4)) {
                GlobalClientInfo.getContext();
                message.host = new URL(aVar.b(null));
            } else {
                message.host = new URL(str4);
            }
            message.E = aVar.i();
            if (message.host == null) {
                try {
                    GlobalClientInfo.getContext();
                    message.host = new URL(aVar.b(null));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (message.host == null) {
                try {
                    GlobalClientInfo.getContext();
                    message.host = new URL(aVar.b(null));
                } catch (MalformedURLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return message;
    }

    public static Message buildParameterError(String str, int i) {
        Message message = new Message();
        message.a(1, ReqType.ACK, 0);
        message.command = Integer.valueOf(i);
        message.m = str;
        return message;
    }

    private static void a(Message message, String str, String str2, String str3, String str4, String str5) {
        if (!TextUtils.isEmpty(str4) || !TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str5) || str3 != null) {
            message.l = new HashMap();
            if (str4 != null && UtilityImpl.getByteLen(str4) <= EXT_HEADER_VALUE_MAX_LEN) {
                message.l.put(ExtHeaderType.TYPE_BUSINESS, str4);
            }
            if (str != null && UtilityImpl.getByteLen(str) <= EXT_HEADER_VALUE_MAX_LEN) {
                message.l.put(ExtHeaderType.TYPE_SID, str);
            }
            if (str2 != null && UtilityImpl.getByteLen(str2) <= EXT_HEADER_VALUE_MAX_LEN) {
                message.l.put(ExtHeaderType.TYPE_USERID, str2);
            }
            if (str5 != null && UtilityImpl.getByteLen(str5) <= EXT_HEADER_VALUE_MAX_LEN) {
                message.l.put(ExtHeaderType.TYPE_TAG, str5);
            }
            if (str3 != null && UtilityImpl.getByteLen(str3) <= EXT_HEADER_VALUE_MAX_LEN) {
                message.l.put(ExtHeaderType.TYPE_COOKIE, str3);
            }
        }
    }

    private void a(int i, ReqType reqType, int i2) {
        this.k = i;
        if (i != 2) {
            this.f = (short) (((((i & 1) << 4) | (reqType.ordinal() << 2)) | i2) << 11);
        }
    }

    private void a(short s, boolean z) {
        this.k = 1;
        this.f = s;
        this.f = (short) (this.f & -16385);
        this.f = (short) (this.f | 8192);
        this.f = (short) (this.f & -2049);
        this.f = (short) (this.f & -65);
        if (z) {
            this.f = (short) (this.f | 32);
        }
    }
}
