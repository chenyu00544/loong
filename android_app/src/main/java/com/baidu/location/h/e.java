package com.baidu.location.h;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.baidu.location.b.f;
import com.baidu.location.e.m;
import com.baidu.location.e.o;
import com.baidu.mapapi.map.WeightedLatLng;
import com.taobao.accs.utl.UtilityImpl;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class e extends k implements f {
    public static final long j8 = 3000;
    public static final int j9 = 15;
    public static long kb = 0;
    public static final long ke = 3000;
    public static final long kg = 5000;
    private static e ki = null;
    private boolean ka = true;
    private f kc = null;
    private Object kd = null;
    private long kf = 0;
    private WifiManager kh = null;
    private Method kj = null;
    private boolean kk = false;
    private long kl = 0;
    private a km = null;

    private class a extends BroadcastReceiver {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null && intent.getAction().equals("android.net.wifi.SCAN_RESULTS")) {
                e.kb = System.currentTimeMillis() / 1000;
                this.a.de();
                m.ba().a9();
                if (System.currentTimeMillis() - com.baidu.location.e.a.for() <= e.kg) {
                    o.do(com.baidu.location.e.a.a(), this.a.c7(), com.baidu.location.e.a.if(), com.baidu.location.e.a.do());
                }
            }
        }
    }

    private e() {
    }

    private void de() {
        if (this.kh != null) {
            try {
                f fVar = new f(this.kh.getScanResults(), this.kf);
                if (this.kc == null || !fVar.new(this.kc)) {
                    this.kc = fVar;
                }
            } catch (Exception e) {
            }
        }
    }

    public static e df() {
        if (ki == null) {
            ki = new e();
        }
        return ki;
    }

    public static double if(f fVar, f fVar2) {
        if (fVar == null || fVar2 == null) {
            return 0.0d;
        }
        List list = fVar.kq;
        List list2 = fVar2.kq;
        if (list == list2) {
            return WeightedLatLng.DEFAULT_INTENSITY;
        }
        if (list == null || list2 == null) {
            return 0.0d;
        }
        int size = list.size();
        int size2 = list2.size();
        float f = (float) (size + size2);
        if (size == 0 && size2 == 0) {
            return WeightedLatLng.DEFAULT_INTENSITY;
        }
        if (size == 0 || size2 == 0) {
            return 0.0d;
        }
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            String str = ((ScanResult) list.get(i)).BSSID;
            if (str == null) {
                i3 = i2;
            } else {
                for (int i4 = 0; i4 < size2; i4++) {
                    if (str.equals(((ScanResult) list2.get(i4)).BSSID)) {
                        i3 = i2 + 1;
                        break;
                    }
                }
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return f <= 0.0f ? 0.0d : ((double) i2) / ((double) f);
    }

    public static boolean if(f fVar, f fVar2, float f) {
        if (fVar == null || fVar2 == null) {
            return false;
        }
        List list = fVar.kq;
        List list2 = fVar2.kq;
        if (list == list2) {
            return true;
        }
        if (list == null || list2 == null) {
            return false;
        }
        int size = list.size();
        int size2 = list2.size();
        float f2 = (float) (size + size2);
        if (size == 0 && size2 == 0) {
            return true;
        }
        if (size == 0 || size2 == 0) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            String str = ((ScanResult) list.get(i)).BSSID;
            if (str == null) {
                i3 = i2;
            } else {
                for (int i4 = 0; i4 < size2; i4++) {
                    if (str.equals(((ScanResult) list2.get(i4)).BSSID)) {
                        i3 = i2 + 1;
                        break;
                    }
                }
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return ((float) (i2 * 2)) >= f2 * f;
    }

    public static boolean if(List list, List list2, float f) {
        if (list == null || list2 == null) {
            return false;
        }
        if (list == list2) {
            return true;
        }
        if (list == null || list2 == null) {
            return false;
        }
        int size = list.size();
        int size2 = list2.size();
        float f2 = (float) (size + size2);
        if (size == 0 && size2 == 0) {
            return true;
        }
        if (size == 0 || size2 == 0) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            String str = ((ScanResult) list.get(i)).BSSID;
            if (str == null) {
                i3 = i2;
            } else {
                for (int i4 = 0; i4 < size2; i4++) {
                    if (str.equals(((ScanResult) list2.get(i4)).BSSID)) {
                        i3 = i2 + 1;
                        break;
                    }
                }
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return ((float) (i2 * 2)) >= f2 * f;
    }

    public boolean D(String str) {
        return super.D(str);
    }

    public boolean c0() {
        return this.kh.isWifiEnabled() && 3 == this.kh.getWifiState();
    }

    public boolean c1() {
        boolean z = false;
        if (this.kh != null) {
            try {
                z = this.kh.isScanAlwaysAvailable();
            } catch (NoSuchMethodError e) {
            }
        }
        return z;
    }

    public void c2() {
        super.c2();
    }

    public f c3() {
        if (this.kh != null) {
            try {
                return new f(this.kh.getScanResults(), this.kf);
            } catch (Exception e) {
            }
        }
        return new f(null, 0);
    }

    public boolean c4() {
        return (this.kh != null && System.currentTimeMillis() - this.kf > 3000) ? c9() : false;
    }

    public synchronized void c5() {
        if (!this.kk) {
            if (com.baidu.location.f.isServing) {
                this.kh = (WifiManager) com.baidu.location.f.getServiceContext().getSystemService(UtilityImpl.NET_TYPE_WIFI);
                this.km = new a();
                try {
                    com.baidu.location.f.getServiceContext().registerReceiver(this.km, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                } catch (Exception e) {
                }
                this.kk = true;
                try {
                    Field declaredField = Class.forName("android.net.wifi.WifiManager").getDeclaredField("mService");
                    if (declaredField != null) {
                        declaredField.setAccessible(true);
                        this.kd = declaredField.get(this.kh);
                        this.kd.getClass();
                    }
                } catch (Exception e2) {
                }
            }
        }
    }

    public String c6() {
        String str = null;
        try {
            WifiInfo connectionInfo = this.kh.getConnectionInfo();
            if (connectionInfo != null) {
                str = connectionInfo.getMacAddress();
            }
        } catch (Exception e) {
        }
        return str;
    }

    public f c7() {
        return (this.kc == null || !this.kc.dr()) ? c3() : this.kc;
    }

    public String c8() {
        if (this.kh == null) {
            return null;
        }
        WifiInfo connectionInfo = this.kh.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        try {
            String bssid = connectionInfo.getBSSID();
            if (bssid != null) {
                bssid = bssid.replace(":", "");
                if ("000000000000".equals(bssid) || "".equals(bssid)) {
                    return null;
                }
            }
            bssid = null;
            return bssid;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean c9() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.baidu.location.h.e.c9():boolean. bs: [B:12:0x0020, B:21:0x0050]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r7 = this;
        r1 = 1;
        r0 = 0;
        r2 = r7.kh;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        r2 = r2.isWifiEnabled();	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        if (r2 != 0) goto L_0x0018;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
    L_0x000a:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        r3 = 17;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        if (r2 <= r3) goto L_0x003a;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
    L_0x0010:
        r2 = r7.kh;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        r2 = r2.isScanAlwaysAvailable();	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        if (r2 == 0) goto L_0x003a;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
    L_0x0018:
        r2 = r7.kj;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        if (r2 == 0) goto L_0x0050;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
    L_0x001c:
        r2 = r7.kd;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        if (r2 == 0) goto L_0x0050;
    L_0x0020:
        r2 = r7.kj;	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
        r3 = r7.kd;	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
        r4 = 1;	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
        r5 = 0;	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
        r6 = r7.ka;	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
        r6 = java.lang.Boolean.valueOf(r6);	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
        r2.invoke(r3, r4);	 Catch:{ Exception -> 0x003b, NoSuchMethodError -> 0x0042 }
    L_0x0033:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        r7.kf = r2;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        r0 = r1;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
    L_0x003a:
        return r0;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
    L_0x003b:
        r2 = move-exception;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        r2 = r7.kh;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        r2.startScan();	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        goto L_0x0033;
    L_0x0042:
        r0 = move-exception;
        r0 = r7.kh;
        r0.startScan();
        r2 = java.lang.System.currentTimeMillis();
        r7.kf = r2;
        r0 = r1;
        goto L_0x003a;
    L_0x0050:
        r2 = r7.kh;	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        r2.startScan();	 Catch:{ NoSuchMethodError -> 0x0042, Exception -> 0x0056 }
        goto L_0x0033;
    L_0x0056:
        r1 = move-exception;
        goto L_0x003a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.h.e.c9():boolean");
    }

    public boolean cZ() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.kl <= kg) {
            return false;
        }
        this.kl = currentTimeMillis;
        return c4();
    }

    public boolean da() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) com.baidu.location.f.getServiceContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public int db() {
        if (this.kh == null) {
            return -1;
        }
        WifiInfo connectionInfo = this.kh.getConnectionInfo();
        if (connectionInfo == null) {
            return -1;
        }
        try {
            String bssid = connectionInfo.getBSSID();
            int rssi = connectionInfo.getRssi();
            if (rssi < 0) {
                rssi = -rssi;
            }
            if (bssid != null) {
                bssid = bssid.replace(":", "");
                if ("000000000000".equals(bssid) || "".equals(bssid)) {
                    return -1;
                }
            }
            return rssi;
        } catch (Exception e) {
            return -1;
        }
    }

    public synchronized void dc() {
        if (this.kk) {
            try {
                com.baidu.location.f.getServiceContext().unregisterReceiver(this.km);
                kb = 0;
            } catch (Exception e) {
            }
            this.km = null;
            this.kh = null;
            this.kk = false;
        }
    }

    public f dd() {
        return (this.kc == null || !this.kc.dn()) ? c3() : this.kc;
    }

    public f do(List list) {
        return super.do(list);
    }
}
