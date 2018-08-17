package com.baidu.location.e;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.b.f;
import com.baidu.location.b.g;
import com.baidu.location.b.j;
import com.baidu.location.b.k;
import com.baidu.location.b.m;
import com.baidu.platform.comapi.location.CoordinateType;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class p implements f {
    private static Method i0 = null;
    private static Class i2 = null;
    private static Method iT = null;
    private static Method iW = null;
    private LocationClientOption i1;
    private a iP;
    private String iQ = null;
    private b iR = new b();
    private Context iS = null;
    private String iU = null;
    private TelephonyManager iV = null;
    private c iX = null;
    private WifiManager iY = null;
    d iZ = new d(this);

    public interface a {
        void onReceiveLocation(BDLocation bDLocation);
    }

    private class b {
        final /* synthetic */ p a;
        public int do;
        public int for;
        public int if;
        public char int;
        public int new;

        private b(p pVar) {
            this.a = pVar;
            this.for = -1;
            this.new = -1;
            this.do = -1;
            this.if = -1;
            this.int = '\u0000';
        }

        private boolean do() {
            return this.for > -1 && this.new > 0;
        }

        public String a() {
            if (!do()) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append(this.new + 23);
            stringBuffer.append("H");
            stringBuffer.append(this.for + 45);
            stringBuffer.append("K");
            stringBuffer.append(this.if + 54);
            stringBuffer.append("Q");
            stringBuffer.append(this.do + g.a);
            return stringBuffer.toString();
        }

        public int if() {
            return (this.do <= 0 || !do()) ? 2 : (this.do == 460 || this.do == 454 || this.do == 455 || this.do == 466) ? 1 : 0;
        }

        public String toString() {
            if (!do()) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append("&nw=");
            stringBuffer.append(this.int);
            stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d", new Object[]{Integer.valueOf(this.do), Integer.valueOf(this.if), Integer.valueOf(this.for), Integer.valueOf(this.new)}));
            return stringBuffer.toString();
        }
    }

    protected class c {
        final /* synthetic */ p a;
        private long do = 0;
        public List if = null;

        public c(p pVar, List list) {
            this.a = pVar;
            this.if = list;
            this.do = System.currentTimeMillis();
            a();
        }

        private void a() {
            if (if() >= 1) {
                Object obj = 1;
                for (int size = this.if.size() - 1; size >= 1 && r2 != null; size--) {
                    int i = 0;
                    obj = null;
                    while (i < size) {
                        Object obj2;
                        if (((ScanResult) this.if.get(i)).level < ((ScanResult) this.if.get(i + 1)).level) {
                            ScanResult scanResult = (ScanResult) this.if.get(i + 1);
                            this.if.set(i + 1, this.if.get(i));
                            this.if.set(i, scanResult);
                            obj2 = 1;
                        } else {
                            obj2 = obj;
                        }
                        i++;
                        obj = obj2;
                    }
                }
            }
        }

        public String a(int i) {
            if (if() < 2) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            int size = this.if.size();
            int i2 = 0;
            int i3 = 0;
            int i4 = 1;
            while (i2 < size) {
                int i5;
                if (((ScanResult) this.if.get(i2)).level == 0) {
                    i5 = i3;
                } else {
                    if (i4 != 0) {
                        stringBuffer.append("&wf=");
                        i4 = 0;
                    } else {
                        stringBuffer.append("|");
                    }
                    stringBuffer.append(((ScanResult) this.if.get(i2)).BSSID.replace(":", ""));
                    i5 = ((ScanResult) this.if.get(i2)).level;
                    if (i5 < 0) {
                        i5 = -i5;
                    }
                    stringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[]{Integer.valueOf(i5)}));
                    i5 = i3 + 1;
                    if (i5 > i) {
                        break;
                    }
                }
                i2++;
                i3 = i5;
            }
            return i4 != 0 ? null : stringBuffer.toString();
        }

        public int if() {
            return this.if == null ? 0 : this.if.size();
        }
    }

    class d extends m {
        final /* synthetic */ p eU;
        String eV;

        d(p pVar) {
            this.eU = pVar;
            this.eV = null;
            this.c7 = new ArrayList();
        }

        public void au() {
            this.c5 = k.Z();
            String H = Jni.H(this.eV);
            this.eV = null;
            this.c7.add(new BasicNameValuePair("bloc", H));
            StringBuffer stringBuffer = new StringBuffer(512);
            stringBuffer.append(String.format(Locale.CHINA, "&ki=%s&sn=%s", new Object[]{j.a(this.eU.iS), j.if(this.eU.iS)}));
            if (stringBuffer.length() > 0) {
                this.c7.add(new BasicNameValuePair(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND, Jni.H(stringBuffer.toString())));
            }
            this.c7.add(new BasicNameValuePair("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())})));
        }

        public void e(String str) {
            this.eV = str;
            ao();
        }

        public void int(boolean z) {
            if (z && this.c6 != null) {
                try {
                    BDLocation bDLocation;
                    try {
                        bDLocation = new BDLocation(EntityUtils.toString(this.c6, "utf-8"));
                    } catch (Exception e) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(63);
                    }
                    if (bDLocation != null) {
                        if (bDLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                            bDLocation.setCoorType(this.eU.i1.coorType);
                            this.eU.iP.onReceiveLocation(bDLocation);
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (this.c7 != null) {
                this.c7.clear();
            }
        }
    }

    public p(Context context, LocationClientOption locationClientOption, a aVar) {
        String deviceId;
        String str;
        this.iS = context.getApplicationContext();
        this.i1 = locationClientOption;
        this.iP = aVar;
        String packageName = this.iS.getPackageName();
        try {
            this.iV = (TelephonyManager) this.iS.getSystemService("phone");
            deviceId = this.iV.getDeviceId();
        } catch (Exception e) {
            deviceId = null;
        }
        try {
            str = com.baidu.location.f.a.a.if(this.iS);
        } catch (Exception e2) {
            str = null;
        }
        if (str != null) {
            this.iQ = "&prod=" + this.i1.prodName + ":" + packageName + "|&cu=" + str + "&coor=" + locationClientOption.getCoorType();
        } else {
            this.iQ = "&prod=" + this.i1.prodName + ":" + packageName + "|&im=" + deviceId + "&coor=" + locationClientOption.getCoorType();
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("&fw=");
        stringBuffer.append("6.13");
        stringBuffer.append("&lt=1");
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        if (locationClientOption.getAddrType() != null) {
        }
        if (locationClientOption.getAddrType() != null && locationClientOption.getAddrType().equals("all")) {
            this.iQ += "&addr=all";
        }
        if (locationClientOption.isNeedAptag || locationClientOption.isNeedAptagd) {
            this.iQ += "&sema=";
            if (locationClientOption.isNeedAptag) {
                this.iQ += "aptag|";
            }
            if (locationClientOption.isNeedAptagd) {
                this.iQ += "aptagd|";
            }
        }
        stringBuffer.append("&first=1");
        stringBuffer.append(VERSION.SDK);
        this.iQ += stringBuffer.toString();
        this.iY = (WifiManager) this.iS.getSystemService(UtilityImpl.NET_TYPE_WIFI);
        Object cr = cr();
        if (!TextUtils.isEmpty(cr)) {
            cr = cr.replace(":", "");
        }
        if (!TextUtils.isEmpty(cr)) {
            this.iQ += "&mac=" + cr;
        }
        cs();
    }

    private String case(int i) {
        String bVar;
        String a;
        if (i < 3) {
            i = 3;
        }
        try {
            if(this.iV.getCellLocation());
            bVar = this.iR.toString();
        } catch (Exception e) {
            bVar = null;
        }
        try {
            this.iX = null;
            this.iX = new c(this, this.iY.getScanResults());
            a = this.iX.a(i);
        } catch (Exception e2) {
            a = null;
        }
        if (bVar == null && a == null) {
            this.iU = null;
            return null;
        }
        if (a != null) {
            bVar = bVar + a;
        }
        if (bVar == null) {
            return null;
        }
        this.iU = bVar + this.iQ;
        return bVar + this.iQ;
    }

    private void if(CellLocation cellLocation) {
        int i = 0;
        if (cellLocation != null && this.iV != null) {
            b bVar = new b();
            String networkOperator = this.iV.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                try {
                    if (networkOperator.length() >= 3) {
                        int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (intValue < 0) {
                            intValue = this.iR.do;
                        }
                        bVar.do = intValue;
                    }
                    String substring = networkOperator.substring(3);
                    if (substring != null) {
                        char[] toCharArray = substring.toCharArray();
                        while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                            i++;
                        }
                    }
                    i = Integer.valueOf(substring.substring(0, i)).intValue();
                    if (i < 0) {
                        i = this.iR.if;
                    }
                    bVar.if = i;
                } catch (Exception e) {
                }
            }
            if (cellLocation instanceof GsmCellLocation) {
                bVar.for = ((GsmCellLocation) cellLocation).getLac();
                bVar.new = ((GsmCellLocation) cellLocation).getCid();
                bVar.int = 'g';
            } else if (cellLocation instanceof CdmaCellLocation) {
                bVar.int = 'c';
                if (i2 == null) {
                    try {
                        i2 = Class.forName("android.telephony.cdma.CdmaCellLocation");
                        i0 = i2.getMethod("getBaseStationId", new Class[0]);
                        iW = i2.getMethod("getNetworkId", new Class[0]);
                        iT = i2.getMethod("getSystemId", new Class[0]);
                    } catch (Exception e2) {
                        i2 = null;
                        return;
                    }
                }
                if (i2 != null && i2.isInstance(cellLocation)) {
                    try {
                        i = ((Integer) iT.invoke(cellLocation, new Object[0])).intValue();
                        if (i < 0) {
                            i = this.iR.if;
                        }
                        bVar.if = i;
                        bVar.new = ((Integer) i0.invoke(cellLocation, new Object[0])).intValue();
                        bVar.for = ((Integer) iW.invoke(cellLocation, new Object[0])).intValue();
                    } catch (Exception e3) {
                        return;
                    }
                }
            }
            if (bVar.do()) {
                this.iR = bVar;
            }
        }
    }

    public String char(int i) {
        try {
            return case(i);
        } catch (Exception e) {
            return null;
        }
    }

    public void cq() {
        if (this.iU != null) {
            if (this.iR == null || this.iR.if() == 1) {
                BDLocation bDLocation = null;
                if (!(this.i1.scanSpan < 1000 || this.i1.getAddrType().equals("all") || this.i1.isNeedAptag || this.i1.isNeedAptagd)) {
                    bDLocation = com.baidu.location.c.b.aZ().if(this.iR.a(), this.iY.getScanResults(), false);
                    if (!this.i1.coorType.equals(CoordinateType.GCJ02)) {
                        double longitude = bDLocation.getLongitude();
                        double latitude = bDLocation.getLatitude();
                        if (!(longitude == Double.MIN_VALUE || latitude == Double.MIN_VALUE)) {
                            double[] dArr = Jni.if(longitude, latitude, this.i1.coorType);
                            bDLocation.setLongitude(dArr[0]);
                            bDLocation.setLatitude(dArr[1]);
                            bDLocation.setCoorType(this.i1.coorType);
                        }
                    }
                    if (bDLocation.getLocType() == 66) {
                        this.iP.onReceiveLocation(bDLocation);
                    }
                }
                if (bDLocation == null) {
                    this.iZ.e(this.iU);
                }
            }
        }
    }

    public String cr() {
        String str = null;
        try {
            WifiInfo connectionInfo = this.iY.getConnectionInfo();
            if (connectionInfo != null) {
                str = connectionInfo.getMacAddress();
            }
        } catch (Exception e) {
        }
        return str;
    }

    public String cs() {
        try {
            return case(15);
        } catch (Exception e) {
            return null;
        }
    }
}
