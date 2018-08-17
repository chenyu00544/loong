package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.antibrush.AntiBrush;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message.ReqType;
import com.taobao.accs.flowcontrol.FlowControl;
import com.taobao.accs.net.a;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.ut.monitor.TrafficsMonitor;
import com.taobao.accs.ut.statistics.d;
import com.taobao.accs.ut.statistics.e;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.g;
import com.umeng.socialize.common.SocializeConstants;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Taobao */
public class b {
    public ConcurrentMap<String, ScheduledFuture<?>> a = new ConcurrentHashMap();
    public int b;
    protected TrafficsMonitor c;
    public FlowControl d;
    public AntiBrush e;
    public String f = "";
    private ConcurrentMap<String, Message> g = new ConcurrentHashMap();
    private boolean h = false;
    private Context i;
    private d j;
    private Message k;
    private a l;
    private String m = "MsgRecv";
    private LinkedHashMap<String, String> n = new MessageHandler$1(this);
    private Runnable o = new d(this);

    public b(Context context, a aVar) {
        this.i = context;
        this.l = aVar;
        this.c = new TrafficsMonitor(this.i);
        this.d = new FlowControl(this.i);
        this.e = new AntiBrush(this.i);
        this.m = aVar == null ? this.m : this.m + aVar.i();
        h();
        g();
    }

    public void a(byte[] bArr) throws IOException {
        a(bArr, null);
    }

    public void a(byte[] bArr, String str) throws IOException {
        int i = 0;
        g gVar = new g(bArr);
        try {
            int a = gVar.a();
            int i2 = (a & SocializeConstants.MASK_USER_CENTER_HIDE_AREA) >> 4;
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(this.m, "version:" + i2, new Object[0]);
            }
            a &= 15;
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(this.m, "compress:" + a, new Object[0]);
            }
            gVar.a();
            int b = gVar.b();
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(this.m, "totalLen:" + b, new Object[0]);
            }
            while (i < b) {
                int b2 = gVar.b();
                i += 2;
                if (b2 > 0) {
                    byte[] bArr2 = new byte[b2];
                    gVar.read(bArr2);
                    if (ALog.isPrintLog(Level.D)) {
                        ALog.d(this.m, "buf len:" + bArr2.length, new Object[0]);
                    }
                    i += bArr2.length;
                    a(a, bArr2, str, i2);
                } else {
                    throw new IOException("data format error");
                }
            }
        } catch (Throwable th) {
            com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", this.b + th.toString());
            ALog.e(this.m, "", th, new Object[0]);
        } finally {
            gVar.close();
        }
    }

    private void a(int i, byte[] bArr, String str, int i2) throws IOException {
        g gVar = new g(bArr);
        long b = (long) gVar.b();
        if (ALog.isPrintLog(Level.D)) {
            ALog.d(this.m, "flag:" + Integer.toHexString((int) b), new Object[0]);
        }
        String a = gVar.a(gVar.a());
        if (ALog.isPrintLog(Level.D)) {
            ALog.d(this.m, "target:" + a, new Object[0]);
        }
        String a2 = gVar.a(gVar.a());
        if (ALog.isPrintLog(Level.D)) {
            ALog.d(this.m, "source:" + a2, new Object[0]);
        }
        try {
            int read;
            boolean z;
            Message message;
            String a3 = gVar.a(gVar.a());
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(this.m, "dataId:" + a3, new Object[0]);
            }
            String str2 = a2 + a3;
            byte[] bArr2 = null;
            Map map = null;
            if (gVar.available() > 0) {
                if (i2 == 2) {
                    map = a(gVar);
                }
                if (i == 0) {
                    bArr2 = gVar.c();
                } else if (i == 1) {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(gVar);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr3 = new byte[8192];
                        while (true) {
                            read = gZIPInputStream.read(bArr3);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr3, 0, read);
                        }
                        bArr2 = byteArrayOutputStream.toByteArray();
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (Exception e) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    } catch (Exception e2) {
                        ALog.e(this.m, "uncompress data error " + e2.toString(), new Object[0]);
                        com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", this.b + " uncompress data error " + e2.toString());
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (Exception e3) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    } catch (Throwable th) {
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    }
                }
            }
            gVar.close();
            if (bArr2 == null) {
                try {
                    ALog.d(this.m, "oriData is null", new Object[0]);
                } catch (Exception e22) {
                    ALog.e(this.m, e22.toString(), new Object[0]);
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", this.b + e22.toString());
                    e22.printStackTrace();
                    return;
                }
            } else if (ALog.isPrintLog(Level.D)) {
                ALog.d(this.m, "oriData:" + String.valueOf(bArr2), new Object[0]);
            }
            int a4 = com.taobao.accs.data.Message.b.a((int) ((b >> 15) & 1));
            ReqType valueOf = ReqType.valueOf((int) ((b >> 13) & 3));
            read = (int) ((b >> 12) & 1);
            int a5 = Message.a.a((int) ((b >> 11) & 1));
            if (((int) ((b >> 6) & 1)) == 1) {
                z = true;
            } else {
                z = false;
            }
            ALog.i(this.m, this.b + " dataId", a3, "type", com.taobao.accs.data.Message.b.b(a4), "reqType", valueOf.name(), "resType", Message.a.b(a5), Constants.KEY_TARGET, a);
            if (a4 == 1 && (valueOf == ReqType.ACK || valueOf == ReqType.RES)) {
                Message message2 = (Message) this.g.remove(a3);
                if (message2 != null) {
                    ALog.d(this.m, "reqMessage not null", new Object[0]);
                    int i3 = 200;
                    if (read == 1) {
                        try {
                            i3 = new JSONObject(new String(bArr2)).getInt("code");
                        } catch (Exception e5) {
                            i3 = -3;
                        }
                    }
                    if (message2.getNetPermanceMonitor() != null) {
                        message2.getNetPermanceMonitor().onRecAck();
                    }
                    if (valueOf == ReqType.RES) {
                        a(message2, i3, valueOf, bArr2, map);
                    } else {
                        a(message2, i3, map);
                    }
                    a(new TrafficsMonitor.a(message2.serviceId, GlobalAppRuntimeInfo.isAppBackground(), str, (long) bArr.length));
                } else {
                    ALog.e(this.m, this.b + " data ack/res reqMessage is null," + a3, new Object[0]);
                }
            }
            if (a4 == 0 && valueOf == ReqType.RES) {
                message = (Message) this.g.remove(a3);
                if (message != null) {
                    a(message, bArr2, bArr, str);
                    return;
                }
                ALog.e(this.m, this.b + " contorl ACK reqMessage is null" + a3, new Object[0]);
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d(this.m, "Message not handled, body:" + new String(bArr2), new Object[0]);
                }
            }
            if (a4 == 1 && valueOf == ReqType.DATA && a != null) {
                String[] split = a.split("\\|");
                if (split != null && split.length >= 2) {
                    ALog.d(this.m, "onPush", new Object[0]);
                    if (this.j != null) {
                        this.j.commitUT();
                    }
                    this.j = new d();
                    this.j.c = String.valueOf(System.currentTimeMillis());
                    if (UtilityImpl.packageExist(this.i, split[1])) {
                        String str3 = split.length >= 3 ? split[2] : null;
                        this.j.e = str3;
                        if (c(str2)) {
                            ALog.e(this.m, this.b + " msg duplicate" + a3, new Object[0]);
                            this.j.h = true;
                        } else {
                            d(str2);
                            ALog.i(this.m, this.b + " try deliver msg to " + split[1] + "/" + str3, new Object[0]);
                            Intent intent = new Intent(Constants.ACTION_RECEIVE);
                            intent.setPackage(split[1]);
                            intent.putExtra("command", 101);
                            if (split.length >= 3) {
                                intent.putExtra(Constants.KEY_SERVICE_ID, split[2]);
                            }
                            String str4 = "";
                            if (split.length >= 4) {
                                str4 = split[3];
                                intent.putExtra(Constants.KEY_USER_ID, str4);
                            }
                            intent.putExtra("data", bArr2);
                            intent.putExtra(Constants.KEY_DATA_ID, a3);
                            intent.putExtra(Constants.KEY_PACKAGE_NAME, this.i.getPackageName());
                            intent.putExtra("host", str);
                            intent.putExtra(Constants.KEY_CONN_TYPE, this.b);
                            intent.putExtra(Constants.KEY_NEED_BUSINESS_ACK, z);
                            intent.putExtra("appKey", this.l.i());
                            a(map, intent);
                            if (z) {
                                a(intent, a2, a, (short) ((int) b));
                            }
                            e.a(this.i, intent);
                            UTMini.getInstance().commitEvent(66001, "MsgToBussPush", "commandId=101", "serviceId=" + str3 + " dataId=" + a3, Integer.valueOf(Constants.SDK_VERSION_CODE));
                            com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_TO_BUSS, "1commandId=101serviceId=" + str3, 0.0d);
                            this.j.b = a3;
                            this.j.i = str4;
                            this.j.f = (bArr2 == null ? 0 : bArr2.length) + "";
                            this.j.a = UtilityImpl.getDeviceId(this.i);
                            this.j.d = String.valueOf(System.currentTimeMillis());
                            a(new TrafficsMonitor.a(str3, GlobalAppRuntimeInfo.isAppBackground(), str, (long) bArr.length));
                        }
                        if (a5 == 1) {
                            ALog.i(this.m, this.b + " try to send ack dataId " + a3, new Object[0]);
                            message = Message.buildPushAck(this.l, a, a2, a3, false, (short) ((int) b), str, map);
                            this.l.b(message, true);
                            a(message.dataId, str3);
                            if (z) {
                                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_ACK, "", 0.0d);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    ALog.e(this.m, "package " + split[1] + " not exist, unbind it", new Object[0]);
                    this.l.b(Message.buildUnbindApp(this.l, this.i, split[1], null, null, null), true);
                }
            }
        } catch (Exception e222) {
            ALog.e(this.m, "dataId read error " + e222.toString(), new Object[0]);
            gVar.close();
            com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", this.b + "data id read error" + e222.toString());
        }
    }

    private void a(Message message, byte[] bArr, byte[] bArr2, String str) {
        int i;
        int i2 = -8;
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(this.m, "parse Json:" + jSONObject.toString(), new Object[0]);
            }
            i2 = jSONObject.getInt("code");
            if (i2 == 200) {
                String string;
                switch (message.command.intValue()) {
                    case 1:
                        jSONObject = jSONObject.getJSONObject("data");
                        this.f = com.taobao.accs.utl.d.a(jSONObject, Constants.KEY_DEVICE_TOKEN, null);
                        UtilityImpl.saveUtdid(this.i);
                        if (jSONObject != null) {
                            JSONArray jSONArray = jSONObject.getJSONArray(Constants.KEY_PACKAGE_NAMES);
                            if (jSONArray != null) {
                                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                                    string = jSONArray.getString(i3);
                                    if (UtilityImpl.packageExist(this.i, string)) {
                                        this.l.j().a(message.m);
                                    } else {
                                        ALog.e(this.m, "unbind app", Constants.KEY_ELECTION_PKG, string);
                                        this.l.b(Message.buildUnbindApp(this.l, this.i, string, null, null, null), true);
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    case 2:
                        this.l.j().b(message.m);
                        break;
                    case 3:
                        this.l.j().a(message.m, message.userinfo);
                        break;
                    case 4:
                        com.taobao.accs.client.b j = this.l.j();
                        String str2 = message.m;
                        string = message.userinfo;
                        j.e(str2);
                        break;
                }
            } else if (message.command.intValue() == 3 && i2 == ErrorCode.APP_NOT_BIND) {
                this.l.j().b(message.m);
            }
            i = i2;
        } catch (Throwable th) {
            i = i2;
            ALog.e(this.m, "handleControlMessage", th, new Object[0]);
            com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "handleControlMessage", "", this.b + th.toString());
        }
        a(message, i, null, bArr, null);
        a(new TrafficsMonitor.a(message.serviceId, GlobalAppRuntimeInfo.isAppBackground(), str, (long) bArr2.length));
    }

    private Map<ExtHeaderType, String> a(g gVar) {
        Map<ExtHeaderType, String> hashMap;
        Throwable th;
        Map<ExtHeaderType, String> map = null;
        if (gVar != null) {
            try {
                int b = gVar.b();
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d(this.m, "extHeaderLen:" + b, new Object[0]);
                }
                int i = 0;
                while (i < b) {
                    int b2 = gVar.b();
                    i += 2;
                    b2 &= Message.EXT_HEADER_VALUE_MAX_LEN;
                    ExtHeaderType valueOf = ExtHeaderType.valueOf((64512 & b2) >> 10);
                    String a = gVar.a(b2);
                    b2 += i;
                    if (valueOf != null) {
                        if (map == null) {
                            hashMap = new HashMap();
                        } else {
                            hashMap = map;
                        }
                        try {
                            hashMap.put(valueOf, a);
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            map = hashMap;
                            th = th2;
                        }
                    } else {
                        hashMap = map;
                    }
                    if (ALog.isPrintLog(Level.D)) {
                        ALog.d(this.m, "", "extHeaderType", valueOf, "value", a);
                    }
                    map = hashMap;
                    i = b2;
                }
            } catch (Exception e2) {
                th = e2;
                ALog.e(this.m, "parseExtHeader", th, new Object[0]);
                return map;
            }
        }
        return map;
    }

    public void a(Message message, int i) {
        a(message, i, null, null, null);
    }

    public void a(Message message, int i, Map<ExtHeaderType, String> map) {
        a(message, i, null, null, map);
    }

    public void a(Message message, int i, ReqType reqType, byte[] bArr, Map<ExtHeaderType, String> map) {
        if (message.command == null || message.getType() < 0 || message.getType() == 2) {
            ALog.d(this.m, "onError, skip ping/ack", new Object[0]);
            return;
        }
        Map map2;
        byte[] bArr2;
        int i2;
        if (message.cunstomDataId != null) {
            this.a.remove(message.cunstomDataId);
        }
        if (this.e.checkAntiBrush(message.host, map)) {
            i = ErrorCode.SERVIER_ANTI_BRUSH;
            bArr = null;
            map2 = null;
            reqType = null;
        }
        int a = this.d.a(map2, message.serviceId);
        if (a != 0) {
            if (a == 2) {
                a = ErrorCode.SERVIER_HIGH_LIMIT;
            } else if (a == 3) {
                a = ErrorCode.SERVIER_HIGH_LIMIT_BRUSH;
            } else {
                a = ErrorCode.SERVIER_LOW_LIMIT;
            }
            bArr2 = null;
            map2 = null;
            reqType = null;
            i2 = a;
        } else {
            bArr2 = bArr;
            i2 = i;
        }
        if (ALog.isPrintLog(Level.D)) {
            ALog.d(this.m, "onResult command:" + message.command + " erorcode:" + i2, new Object[0]);
        }
        if (message.command.intValue() == 102) {
            return;
        }
        if (message.command.intValue() == 105) {
            AccsAbstractDataListener listener = GlobalClientInfo.getInstance(this.i).getListener(com.taobao.accs.internal.a.ELECTION_SERVICE_ID);
            if (listener != null) {
                listener.onResponse(com.taobao.accs.internal.a.ELECTION_SERVICE_ID, message.cunstomDataId, i2, bArr2, null);
                return;
            } else {
                ALog.e(this.m, "onResult election listener null", new Object[0]);
                return;
            }
        }
        if (message.isCancel) {
            ALog.e(this.m, this.b + " message is cancel! command:" + message.command, new Object[0]);
        } else if (!b(i2) || message.command.intValue() == 100 || message.retryTimes > Message.CONTROL_MAX_RETRY_TIMES) {
            ALog.d(this.m, "prepare send broadcast", new Object[0]);
            Intent c = c(message);
            c.putExtra(Constants.KEY_ERROR_CODE, i2);
            ReqType valueOf = ReqType.valueOf((message.f >> 13) & 3);
            if (reqType == ReqType.RES || valueOf == ReqType.REQ) {
                c.putExtra(Constants.KEY_SEND_TYPE, Constants.SEND_TYPE_RES);
            }
            if (i2 == 200) {
                c.putExtra("data", bArr2);
            }
            c.putExtra("appKey", this.l.b);
            a(map2, c);
            e.a(this.i, c);
            if (!TextUtils.isEmpty(message.serviceId)) {
                UTMini.getInstance().commitEvent(66001, "MsgToBuss0", "commandId=" + message.command, "serviceId=" + message.serviceId + " errorCode=" + i2 + " dataId=" + message.dataId, Integer.valueOf(Constants.SDK_VERSION_CODE));
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_TO_BUSS, "1commandId=" + message.command + "serviceId=" + message.serviceId, 0.0d);
            }
        } else {
            message.startSendTime = System.currentTimeMillis();
            message.retryTimes++;
            this.l.b(message, true);
        }
        NetPerformanceMonitor netPermanceMonitor = message.getNetPermanceMonitor();
        if (netPermanceMonitor != null) {
            netPermanceMonitor.onToBizDate();
            String url = message.host == null ? null : message.host.toString();
            if (i2 == 200) {
                netPermanceMonitor.setRet(true);
                if (message.retryTimes > 0) {
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_RESEND, "succ", 0.0d);
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_RESEND, "succ_" + message.retryTimes, 0.0d);
                } else {
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_REQUEST, url);
                }
            } else {
                if (message.retryTimes > 0) {
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_RESEND, "fail＿" + i2, 0.0d);
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_RESEND, "fail", 0.0d);
                } else if (i2 != -13) {
                    com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_REQUEST, url, UtilityImpl.int2String(i2), this.b + message.serviceId + message.timeout);
                }
                netPermanceMonitor.setRet(false);
                netPermanceMonitor.setFailReason(i2);
            }
            AppMonitor.getInstance().commitStat(message.getNetPermanceMonitor());
        }
        b(message, i2);
    }

    private boolean b(int i) {
        if (i == -1 || i == -9 || i == -10 || i == -11) {
            return true;
        }
        return false;
    }

    public void a() {
        ALog.d(this.m, "onSendPing", new Object[0]);
        synchronized (b.class) {
            this.h = true;
        }
    }

    public void b() {
        ALog.d(this.m, "onRcvPing", new Object[0]);
        synchronized (b.class) {
            this.h = false;
        }
    }

    public boolean c() {
        return this.h;
    }

    public void a(Message message) {
        if (!(this.k == null || message.cunstomDataId == null || message.serviceId == null || this.k.cunstomDataId != message.cunstomDataId || this.k.serviceId != message.serviceId)) {
            UTMini.getInstance().commitEvent(66001, "SEND_REPEAT", message.serviceId, message.cunstomDataId, Long.valueOf(Thread.currentThread().getId()));
        }
        if (message.getType() != -1 && message.getType() != 2 && !message.isAck) {
            this.g.put(message.getDataId(), message);
        }
    }

    public void a(int i) {
        this.h = false;
        String[] strArr = (String[]) this.g.keySet().toArray(new String[0]);
        if (strArr != null && strArr.length > 0) {
            ALog.d(this.m, "onNetworkFail", new Object[0]);
            for (Object remove : strArr) {
                Message message = (Message) this.g.remove(remove);
                if (message != null) {
                    a(message, i);
                }
            }
        }
    }

    public void b(Message message) {
        if (this.g.keySet() != null && this.g.keySet().size() > 0) {
            for (String str : this.g.keySet()) {
                Message message2 = (Message) this.g.get(str);
                if (!(message2 == null || message2.command == null || !message2.getPackageName().equals(message.getPackageName()))) {
                    switch (message.command.intValue()) {
                        case 1:
                        case 2:
                            if (message2.command.intValue() == 1 || message2.command.intValue() == 2) {
                                message2.isCancel = true;
                                break;
                            }
                        case 3:
                        case 4:
                            if (message2.command.intValue() == 3 || message2.command.intValue() == 4) {
                                message2.isCancel = true;
                                break;
                            }
                        case 5:
                        case 6:
                            if (message2.command.intValue() == 5 || message2.command.intValue() == 6) {
                                message2.isCancel = true;
                                break;
                            }
                    }
                }
                if (message2 != null && message2.isCancel) {
                    ALog.e(this.m, "cancelControlMessage", "command", message2.command);
                }
            }
        }
    }

    public int d() {
        return this.g.size();
    }

    public Collection<Message> e() {
        return this.g.values();
    }

    public Message a(String str) {
        return (Message) this.g.get(str);
    }

    public Message b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (Message) this.g.remove(str);
    }

    private boolean c(String str) {
        if (!TextUtils.isEmpty(str) && this.n.containsKey(str)) {
            return true;
        }
        return false;
    }

    private void d(String str) {
        if (!TextUtils.isEmpty(str) && !this.n.containsKey(str)) {
            this.n.put(str, str);
            i();
        }
    }

    private void h() {
        try {
            File file = new File(this.i.getDir("accs", 0), "message" + this.l.i());
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        this.n.put(readLine, readLine);
                    } else {
                        bufferedReader.close();
                        return;
                    }
                }
            }
            ALog.d(this.m, "message file not exist", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void i() {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.i.getDir("accs", 0), "message" + this.l.i()));
            fileWriter.write("");
            for (String str : this.n.keySet()) {
                fileWriter.append(str + "\r\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Intent c(Message message) {
        Intent intent = new Intent(Constants.ACTION_RECEIVE);
        intent.setPackage(message.m);
        intent.putExtra("command", message.command);
        intent.putExtra(Constants.KEY_SERVICE_ID, message.serviceId);
        intent.putExtra(Constants.KEY_USER_ID, message.userinfo);
        if (message.command != null && message.command.intValue() == 100) {
            intent.putExtra(Constants.KEY_DATA_ID, message.cunstomDataId);
        }
        return intent;
    }

    private void a(Map<ExtHeaderType, String> map, Intent intent) {
        if (map != null && intent != null) {
            for (ExtHeaderType extHeaderType : ExtHeaderType.values()) {
                String str = (String) map.get(extHeaderType);
                if (!TextUtils.isEmpty(str)) {
                    intent.putExtra(extHeaderType.toString(), str);
                }
            }
        }
    }

    private void a(Intent intent, String str, String str2, short s) {
        if (intent != null) {
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("source", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                intent.putExtra(Constants.KEY_TARGET, str2);
            }
            intent.putExtra(Constants.KEY_FLAGS, s);
        }
    }

    public d f() {
        return this.j;
    }

    private void b(Message message, int i) {
        if (message != null) {
            String deviceId = UtilityImpl.getDeviceId(this.i);
            String str = System.currentTimeMillis() + "";
            boolean z = true;
            if (i != 200) {
                z = false;
            }
            switch (message.command.intValue()) {
                case 1:
                    com.taobao.accs.ut.statistics.a aVar = new com.taobao.accs.ut.statistics.a();
                    aVar.a = deviceId;
                    aVar.b = str;
                    aVar.c = z;
                    aVar.a(i);
                    aVar.commitUT();
                    return;
                case 3:
                    com.taobao.accs.ut.statistics.b bVar = new com.taobao.accs.ut.statistics.b();
                    bVar.a = deviceId;
                    bVar.b = str;
                    bVar.c = z;
                    bVar.e = message.userinfo;
                    bVar.a(i);
                    bVar.commitUT();
                    return;
                default:
                    return;
            }
        }
    }

    private void a(String str, String str2) {
        e eVar = new e();
        eVar.a = UtilityImpl.getDeviceId(this.i);
        eVar.c = str;
        eVar.d = "" + System.currentTimeMillis();
        eVar.f = "";
        eVar.e = str2;
        eVar.b = "";
        eVar.commitUT();
    }

    public void g() {
        try {
            com.taobao.accs.common.a.a().execute(this.o);
        } catch (Throwable th) {
            ALog.e(this.m, "restoreTraffics", th, new Object[0]);
        }
    }

    public void a(TrafficsMonitor.a aVar) {
        try {
            com.taobao.accs.common.a.a().execute(new c(this, aVar));
        } catch (Throwable th) {
            ALog.e(this.m, "addTrafficsInfo", th, new Object[0]);
        }
    }
}
