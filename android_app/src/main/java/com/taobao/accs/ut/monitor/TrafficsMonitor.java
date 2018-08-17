package com.taobao.accs.ut.monitor;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.Dimension;
import anet.channel.statist.Measure;
import anet.channel.statist.Monitor;
import anet.channel.statist.StatObject;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.android.agoo.common.AgooConstants;

/* compiled from: Taobao */
public class TrafficsMonitor {
    private Map<String, List<a>> a = new HashMap();
    private Map<String, String> b = new TrafficsMonitor_1(this);
    private int c = 0;
    private Context d;
    private String e = "";

    /* compiled from: Taobao */
    class TrafficsMonitor_1 extends HashMap<String, String> {
        final /* synthetic */ TrafficsMonitor a;

        TrafficsMonitor_1(TrafficsMonitor trafficsMonitor) {
            this.a = trafficsMonitor;
            put("im", "512");
            put("motu", "513");
            put("acds", "514");
            put(GlobalClientInfo.AGOO_SERVICE_ID, "515");
            put(AgooConstants.AGOO_SERVICE_AGOOACK, "515");
            put("agooTokenReport", "515");
            put("accsSelf", Constants.DEFAULT_UIN);
        }
    }

    @Monitor(module = "NetworkSDK", monitorPoint = "TrafficStats")
    /* compiled from: Taobao */
    public static class StatTrafficMonitor extends BaseMonitor {
        @Dimension
        public String bizId;
        @Dimension
        public String date;
        @Dimension
        public String host;
        @Dimension
        public boolean isBackground;
        @Dimension
        public String serviceId;
        @Measure
        public long size;
    }

    /* compiled from: Taobao */
    public static class a {
        String a;
        String b;
        String c;
        boolean d;
        String e;
        long f;

        public a(String str, boolean z, String str2, long j) {
            this.c = str;
            this.d = z;
            this.e = str2;
            this.f = j;
        }

        public a(String str, String str2, String str3, boolean z, String str4, long j) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = z;
            this.e = str4;
            this.f = j;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("date:" + this.a);
            stringBuilder.append(" ");
            stringBuilder.append("bizId:" + this.b);
            stringBuilder.append(" ");
            stringBuilder.append("serviceId:" + this.c);
            stringBuilder.append(" ");
            stringBuilder.append("host:" + this.e);
            stringBuilder.append(" ");
            stringBuilder.append("isBackground:" + this.d);
            stringBuilder.append(" ");
            stringBuilder.append("size:" + this.f);
            return stringBuilder.toString();
        }
    }

    public TrafficsMonitor(Context context) {
        this.d = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.taobao.accs.ut.monitor.TrafficsMonitor.a r11) {
        /*
        r10 = this;
        r3 = 0;
        if (r11 == 0) goto L_0x002f;
    L_0x0003:
        r0 = r11.e;
        if (r0 == 0) goto L_0x002f;
    L_0x0007:
        r0 = r11.f;
        r4 = 0;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x002f;
    L_0x000f:
        r0 = r11.c;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0030;
    L_0x0017:
        r0 = "accsSelf";
    L_0x0019:
        r11.c = r0;
        r5 = r10.a;
        monitor-enter(r5);
        r0 = r10.b;	 Catch:{ all -> 0x00c4 }
        r1 = r11.c;	 Catch:{ all -> 0x00c4 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x00c4 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x00c4 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x00c4 }
        if (r1 == 0) goto L_0x0033;
    L_0x002e:
        monitor-exit(r5);	 Catch:{ all -> 0x00c4 }
    L_0x002f:
        return;
    L_0x0030:
        r0 = r11.c;
        goto L_0x0019;
    L_0x0033:
        r11.b = r0;	 Catch:{ all -> 0x00c4 }
        r1 = com.taobao.accs.utl.ALog.Level.D;	 Catch:{ all -> 0x00c4 }
        r1 = com.taobao.accs.utl.ALog.isPrintLog(r1);	 Catch:{ all -> 0x00c4 }
        if (r1 == 0) goto L_0x0068;
    L_0x003d:
        r1 = "TrafficsMonitor";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c4 }
        r2.<init>();	 Catch:{ all -> 0x00c4 }
        r4 = "addTrafficInfo count:";
        r2 = r2.append(r4);	 Catch:{ all -> 0x00c4 }
        r4 = r10.c;	 Catch:{ all -> 0x00c4 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x00c4 }
        r4 = " ";
        r2 = r2.append(r4);	 Catch:{ all -> 0x00c4 }
        r4 = r11.toString();	 Catch:{ all -> 0x00c4 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x00c4 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c4 }
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x00c4 }
        com.taobao.accs.utl.ALog.d(r1, r2, r4);	 Catch:{ all -> 0x00c4 }
    L_0x0068:
        r1 = r10.a;	 Catch:{ all -> 0x00c4 }
        r1 = r1.get(r0);	 Catch:{ all -> 0x00c4 }
        r1 = (java.util.List) r1;	 Catch:{ all -> 0x00c4 }
        if (r1 == 0) goto L_0x00bb;
    L_0x0072:
        r4 = 1;
        r6 = r1.iterator();	 Catch:{ all -> 0x00c4 }
    L_0x0077:
        r2 = r6.hasNext();	 Catch:{ all -> 0x00c4 }
        if (r2 == 0) goto L_0x00c7;
    L_0x007d:
        r2 = r6.next();	 Catch:{ all -> 0x00c4 }
        r2 = (com.taobao.accs.ut.monitor.TrafficsMonitor.a) r2;	 Catch:{ all -> 0x00c4 }
        r7 = r2.d;	 Catch:{ all -> 0x00c4 }
        r8 = r11.d;	 Catch:{ all -> 0x00c4 }
        if (r7 != r8) goto L_0x0077;
    L_0x0089:
        r7 = r2.e;	 Catch:{ all -> 0x00c4 }
        if (r7 == 0) goto L_0x0077;
    L_0x008d:
        r7 = r2.e;	 Catch:{ all -> 0x00c4 }
        r8 = r11.e;	 Catch:{ all -> 0x00c4 }
        r7 = r7.equals(r8);	 Catch:{ all -> 0x00c4 }
        if (r7 == 0) goto L_0x0077;
    L_0x0097:
        r6 = r2.f;	 Catch:{ all -> 0x00c4 }
        r8 = r11.f;	 Catch:{ all -> 0x00c4 }
        r6 = r6 + r8;
        r2.f = r6;	 Catch:{ all -> 0x00c4 }
        r2 = r3;
    L_0x009f:
        if (r2 == 0) goto L_0x00a4;
    L_0x00a1:
        r1.add(r11);	 Catch:{ all -> 0x00c4 }
    L_0x00a4:
        r2 = r10.a;	 Catch:{ all -> 0x00c4 }
        r2.put(r0, r1);	 Catch:{ all -> 0x00c4 }
        r0 = r10.c;	 Catch:{ all -> 0x00c4 }
        r0 = r0 + 1;
        r10.c = r0;	 Catch:{ all -> 0x00c4 }
        monitor-exit(r5);	 Catch:{ all -> 0x00c4 }
        r0 = r10.c;
        r1 = 10;
        if (r0 < r1) goto L_0x002f;
    L_0x00b6:
        r10.b();
        goto L_0x002f;
    L_0x00bb:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x00c4 }
        r1.<init>();	 Catch:{ all -> 0x00c4 }
        r1.add(r11);	 Catch:{ all -> 0x00c4 }
        goto L_0x00a4;
    L_0x00c4:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x00c7:
        r2 = r4;
        goto L_0x009f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.ut.monitor.TrafficsMonitor.a(com.taobao.accs.ut.monitor.TrafficsMonitor$a):void");
    }

    private void b() {
        synchronized (this.a) {
            Object obj;
            String str;
            String formatDay = UtilityImpl.formatDay(System.currentTimeMillis());
            if (TextUtils.isEmpty(this.e) || this.e.equals(formatDay)) {
                obj = null;
                str = formatDay;
            } else {
                str = this.e;
                obj = 1;
            }
            for (String str2 : this.a.keySet()) {
                for (a aVar : (List) this.a.get(str2)) {
                    if (aVar != null) {
                        com.taobao.accs.c.a.a(this.d).a(aVar.e, aVar.c, (String) this.b.get(aVar.c), aVar.d, aVar.f, str);
                    }
                }
            }
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("TrafficsMonitor", "savetoDay:" + str + " saveTraffics" + this.a.toString(), new Object[0]);
            }
            if (obj != null) {
                this.a.clear();
                c();
            } else if (ALog.isPrintLog(Level.D)) {
                ALog.d("TrafficsMonitor", "no need commit lastsaveDay:" + this.e + " currday:" + formatDay, new Object[0]);
            }
            this.e = formatDay;
            this.c = 0;
        }
    }

    public void a() {
        try {
            synchronized (this.a) {
                this.a.clear();
            }
            List<a> a = com.taobao.accs.c.a.a(this.d).a(true);
            if (a != null) {
                for (a a2 : a) {
                    a(a2);
                }
            }
        } catch (Exception e) {
            ALog.w("TrafficsMonitor", e.toString(), new Object[0]);
        }
    }

    private void c() {
        List<a> a = com.taobao.accs.c.a.a(this.d).a(false);
        if (a != null) {
            try {
                for (a aVar : a) {
                    if (aVar != null) {
                        StatObject statTrafficMonitor = new StatTrafficMonitor();
                        statTrafficMonitor.bizId = aVar.b;
                        statTrafficMonitor.date = aVar.a;
                        statTrafficMonitor.host = aVar.e;
                        statTrafficMonitor.isBackground = aVar.d;
                        statTrafficMonitor.size = aVar.f;
                        AppMonitor.getInstance().commitStat(statTrafficMonitor);
                    }
                }
                com.taobao.accs.c.a.a(this.d).a();
            } catch (Throwable th) {
                ALog.e("", th.toString(), new Object[0]);
                th.printStackTrace();
            }
        }
    }
}
