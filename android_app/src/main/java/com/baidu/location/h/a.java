package com.baidu.location.h;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.Jni;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.e.h;
import com.baidu.location.e.o;
import com.tencent.open.yyb.TitleBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

public class a extends g implements f {
    private static String jC = null;
    private static a jD = null;
    private static int jL = 0;
    private static final int jz = 3000;
    private GpsStatus jA;
    private a jB = null;
    private int jE;
    private final int jF = 1;
    private long jG = 0;
    private c jH = null;
    private Handler jI = null;
    private Context jJ;
    private boolean jK = false;
    private b jM = null;
    private Location jN;
    private final long jO = 1000;
    private LocationManager jP = null;
    private HashMap jp;
    private boolean jq = false;
    private final long jr = 9000;
    private final int js = 2;
    private int jt;
    private long ju = 0;
    private String jv = null;
    private final int jw = 4;
    private boolean jx = false;
    private final int jy = 3;

    class a_1 extends Handler {
        final /* synthetic */ a a;

        a_1(a aVar) {
            this.a = aVar;
        }

        public void handleMessage(Message message) {
            if (com.baidu.location.f.isServing) {
                switch (message.what) {
                    case 1:
                        this.a.case((Location) message.obj);
                        return;
                    case 2:
                        if (this.a.jB != null) {
                            this.a.jB.a((String) message.obj);
                            return;
                        }
                        return;
                    case 3:
                        this.a.if("&og=1", (Location) message.obj);
                        return;
                    case 4:
                        this.a.if("&og=2", (Location) message.obj);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private class a implements Listener, NmeaListener {
        private long a;
        private List byte;
        private final int case;
        private String do;
        private String for;
        long if;
        final /* synthetic */ a int;
        private String new;
        private boolean try;

        private a(a aVar) {
            this.int = aVar;
            this.if = 0;
            this.a = 0;
            this.case = 400;
            this.try = false;
            this.byte = new ArrayList();
            this.for = null;
            this.new = null;
            this.do = null;
        }

        public void a(String str) {
            if (System.currentTimeMillis() - this.a > 400 && this.try && this.byte.size() > 0) {
                try {
                    i iVar = new i(this.byte, this.for, this.new, this.do);
                    if (iVar.do()) {
                        k.b1 = this.int.if(iVar, this.int.jt);
                        if (k.b1 > 0) {
                            a.jC = String.format(Locale.CHINA, "&nmea=%.1f|%.1f&g_tp=%d", new Object[]{Double.valueOf(iVar.if()), Double.valueOf(iVar.for()), Integer.valueOf(k.b1)});
                        }
                    } else {
                        k.b1 = 0;
                    }
                } catch (Exception e) {
                    k.b1 = 0;
                }
                this.byte.clear();
                this.do = null;
                this.new = null;
                this.for = null;
                this.try = false;
            }
            if (str.startsWith("$GPGGA")) {
                this.try = true;
                this.for = str.trim();
            } else if (str.startsWith("$GPGSV")) {
                this.byte.add(str.trim());
            } else if (str.startsWith("$GPGSA")) {
                this.do = str.trim();
            }
            this.a = System.currentTimeMillis();
        }

        public void onGpsStatusChanged(int i) {
            if (this.int.jP != null) {
                switch (i) {
                    case 2:
                        this.int.char(null);
                        this.int.goto(false);
                        a.jL = 0;
                        return;
                    case 4:
                        if (this.int.jx) {
                            try {
                                if (this.int.jA == null) {
                                    this.int.jA = this.int.jP.getGpsStatus(null);
                                } else {
                                    this.int.jP.getGpsStatus(this.int.jA);
                                }
                                this.int.jE = 0;
                                this.int.jt = 0;
                                this.int.jp = new HashMap();
                                int i2 = 0;
                                for (GpsSatellite gpsSatellite : this.int.jA.getSatellites()) {
                                    if (gpsSatellite.usedInFix()) {
                                        i2++;
                                        if (gpsSatellite.getSnr() >= ((float) k.bH)) {
                                            this.int.jt = this.int.jt + 1;
                                        }
                                        this.int.if(gpsSatellite, this.int.jp);
                                    }
                                }
                                a.jL = i2;
                                return;
                            } catch (Exception e) {
                                return;
                            }
                        }
                        return;
                    default:
                        return;
                }
            }
        }

        public void onNmeaReceived(long j, String str) {
            if (!this.int.jx) {
                return;
            }
            if (!com.baidu.location.e.f.bB().gA) {
                k.b1 = 0;
            } else if (str != null && !str.equals("") && str.length() >= 9 && str.length() <= 150 && this.int.cI()) {
                this.int.jI.sendMessage(this.int.jI.obtainMessage(2, str));
            }
        }
    }

    private class b implements LocationListener {
        final /* synthetic */ a a;

        private b(a aVar) {
            this.a = aVar;
        }

        public void onLocationChanged(Location location) {
            this.a.jG = System.currentTimeMillis();
            this.a.goto(true);
            this.a.char(location);
            this.a.jq = false;
        }

        public void onProviderDisabled(String str) {
            this.a.char(null);
            this.a.goto(false);
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            switch (i) {
                case 0:
                    this.a.char(null);
                    this.a.goto(false);
                    return;
                case 1:
                    this.a.ju = System.currentTimeMillis();
                    this.a.jq = true;
                    this.a.goto(false);
                    return;
                case 2:
                    this.a.jq = false;
                    return;
                default:
                    return;
            }
        }
    }

    private class c implements LocationListener {
        final /* synthetic */ a a;
        private long if;

        private c(a aVar) {
            this.a = aVar;
            this.if = 0;
        }

        public void onLocationChanged(Location location) {
            if (!this.a.jx && location != null && location.getProvider() == "gps" && System.currentTimeMillis() - this.if >= 10000 && o.if(location, false)) {
                this.if = System.currentTimeMillis();
                this.a.jI.sendMessage(this.a.jI.obtainMessage(4, location));
            }
        }

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    private a() {
    }

    public static a cM() {
        if (jD == null) {
            jD = new a();
        }
        return jD;
    }

    private void case(Location location) {
        if (location != null) {
            int i = jL;
            if (i == 0) {
                try {
                    i = location.getExtras().getInt("satellites");
                } catch (Exception e) {
                }
            }
            if (i != 0 || k.cj) {
                this.jN = location;
                if (this.jN == null) {
                    this.jv = null;
                } else {
                    this.jN.setTime(System.currentTimeMillis());
                    float speed = (float) (((double) this.jN.getSpeed()) * 3.6d);
                    if (!this.jN.hasSpeed()) {
                        speed = -1.0f;
                    }
                    i = jL;
                    if (i == 0) {
                        try {
                            i = this.jN.getExtras().getInt("satellites");
                        } catch (Exception e2) {
                        }
                    }
                    this.jv = String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", new Object[]{Double.valueOf(this.jN.getLongitude()), Double.valueOf(this.jN.getLatitude()), Float.valueOf(speed), Float.valueOf(this.jN.getBearing()), Integer.valueOf(i), Long.valueOf(r2)});
                    if(this.jN.getLongitude(), this.jN.getLatitude(), speed);
                }
                try {
                    com.baidu.location.e.k.b7().try(this.jN);
                } catch (Exception e3) {
                }
                if (this.jN != null) {
                    h.bJ().for(this.jN);
                }
                if (cI() && this.jN != null) {
                    com.baidu.location.e.c.bq().l(cE());
                    if (jL > 2 && o.if(this.jN, true)) {
                        boolean cZ = e.df().cZ();
                        com.baidu.location.e.a.a(new h(b.cV().cN()));
                        com.baidu.location.e.a.a(System.currentTimeMillis());
                        com.baidu.location.e.a.a(new Location(this.jN));
                        com.baidu.location.e.a.a(com.baidu.location.e.c.bq().bt());
                        if (!cZ) {
                            o.do(com.baidu.location.e.a.a(), null, com.baidu.location.e.a.if(), com.baidu.location.e.c.bq().bt());
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        this.jN = null;
    }

    private void char(Location location) {
        this.jI.sendMessage(this.jI.obtainMessage(1, location));
    }

    public static String else(Location location) {
        String str = long(location);
        return str != null ? str + jC : str;
    }

    public static String goto(Location location) {
        String str = long(location);
        return str != null ? str + "&g_tp=0" : str;
    }

    private void goto(boolean z) {
        this.jK = z;
        if (!z || !cI()) {
        }
    }

    private int if(i iVar, int i) {
        if (jL >= k.cq) {
            return 1;
        }
        if (jL <= k.b7) {
            return 4;
        }
        double d = iVar.if();
        if (d <= ((double) k.cQ)) {
            return 1;
        }
        if (d >= ((double) k.bW)) {
            return 4;
        }
        d = iVar.for();
        return d > ((double) k.ck) ? d >= ((double) k.cA) ? 4 : i < k.bJ ? i <= k.cC ? 4 : this.jp != null ? if(this.jp) : 3 : 1 : 1;
    }

    private int if(HashMap hashMap) {
        if (this.jE > 4) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            int i = 0;
            for (Entry value : hashMap.entrySet()) {
                int i2;
                List list = (List) value.getValue();
                if (list != null) {
                    Object obj = if(list);
                    if (obj != null) {
                        arrayList.add(obj);
                        i2 = i + 1;
                        arrayList2.add(Integer.valueOf(i));
                        i = i2;
                    }
                }
                i2 = i;
                i = i2;
            }
            if (!arrayList.isEmpty()) {
                double[] dArr;
                double[] dArr2 = new double[2];
                int size = arrayList.size();
                for (int i3 = 0; i3 < size; i3++) {
                    dArr = (double[]) arrayList.get(i3);
                    i = ((Integer) arrayList2.get(i3)).intValue();
                    dArr[0] = dArr[0] * ((double) i);
                    dArr[1] = dArr[1] * ((double) i);
                    dArr2[0] = dArr2[0] + dArr[0];
                    dArr2[1] = dArr[1] + dArr2[1];
                }
                dArr2[0] = dArr2[0] / ((double) size);
                dArr2[1] = dArr2[1] / ((double) size);
                dArr = new(dArr2[0], dArr2[1]);
                if (dArr[0] <= ((double) k.cI)) {
                    return 1;
                }
                if (dArr[0] >= ((double) k.bQ)) {
                    return 4;
                }
            }
        }
        return 3;
    }

    private String if(GpsSatellite gpsSatellite, HashMap hashMap) {
        int floor = (int) Math.floor((double) (gpsSatellite.getAzimuth() / 6.0f));
        float elevation = gpsSatellite.getElevation();
        int floor2 = (int) Math.floor(((double) elevation) / 1.5d);
        float snr = gpsSatellite.getSnr();
        int round = Math.round(snr / 5.0f);
        int prn = gpsSatellite.getPrn();
        int i = prn >= 65 ? prn - 32 : prn;
        if (snr >= TitleBar.SHAREBTN_RIGHT_MARGIN && elevation >= 1.0f) {
            List list = (List) hashMap.get(Integer.valueOf(round));
            if (list == null) {
                list = new ArrayList();
            }
            list.add(gpsSatellite);
            hashMap.put(Integer.valueOf(round), list);
            this.jE++;
        }
        if (floor >= 64) {
            if (floor2 < 64) {
                if (i >= 65) {
                }
            }
            return null;
        }
        if (floor2 < 64) {
            if (i >= 65) {
            }
        }
        return null;
        if (i >= 65) {
        }
        return null;
    }

    private void if(double d, double d2, float f) {
        int i = 0;
        if (com.baidu.location.e.f.bB().gw) {
            if (d >= 73.146973d && d <= 135.252686d && d2 <= 54.258807d && d2 >= 14.604847d && f <= 18.0f) {
                int i2 = (int) ((d - k.bG) * 1000.0d);
                int i3 = (int) ((k.b4 - d2) * 1000.0d);
                if (i2 <= 0 || i2 >= 50 || i3 <= 0 || i3 >= 50) {
                    String str = String.format(Locale.CHINA, "&ll=%.5f|%.5f", new Object[]{Double.valueOf(d), Double.valueOf(d2)}) + "&im=" + com.baidu.location.b.c.N().L();
                    k.cp = d;
                    k.cE = d2;
                    com.baidu.location.e.f.bB().m(str);
                } else {
                    i2 += i3 * 50;
                    i3 = i2 >> 2;
                    i2 &= 3;
                    if (k.cM) {
                        i = (k.b2[i3] >> (i2 * 2)) & 3;
                    }
                }
            }
            if (k.co != i) {
                k.co = i;
            }
        }
    }

    private void if(String str, Location location) {
        if (location != null) {
            String str2 = str + com.baidu.location.e.c.bq().bt();
            boolean cZ = e.df().cZ();
            com.baidu.location.e.a.a(new h(b.cV().cN()));
            com.baidu.location.e.a.a(System.currentTimeMillis());
            com.baidu.location.e.a.a(new Location(location));
            com.baidu.location.e.a.a(str2);
            if (!cZ) {
                o.do(com.baidu.location.e.a.a(), null, com.baidu.location.e.a.if(), str2);
            }
        }
    }

    public static boolean if(Location location, Location location2, boolean z) {
        if (location == location2) {
            return false;
        }
        if (location == null || location2 == null) {
            return true;
        }
        float speed = location2.getSpeed();
        if (z && ((k.co == 3 || !com.baidu.location.b.h.W().for(location2.getLongitude(), location2.getLatitude())) && speed < 5.0f)) {
            return true;
        }
        float distanceTo = location2.distanceTo(location);
        return speed > k.cg ? distanceTo > k.cx : speed > k.ch ? distanceTo > k.cO : distanceTo > 5.0f;
    }

    private double[] if(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        double[] dArr = new double[2];
        for (GpsSatellite gpsSatellite : list) {
            if (gpsSatellite != null) {
                double[] dArr2 = int((double) (90.0f - gpsSatellite.getElevation()), (double) gpsSatellite.getAzimuth());
                dArr[0] = dArr[0] + dArr2[0];
                dArr[1] = dArr[1] + dArr2[1];
            }
        }
        int size = list.size();
        dArr[0] = dArr[0] / ((double) size);
        dArr[1] = dArr[1] / ((double) size);
        return dArr;
    }

    private double[] int(double d, double d2) {
        return new double[]{Math.sin(Math.toRadians(d2)) * d, Math.cos(Math.toRadians(d2)) * d};
    }

    public static String long(Location location) {
        float f = -1.0f;
        if (location == null) {
            return null;
        }
        float speed = (float) (((double) location.getSpeed()) * 3.6d);
        if (!location.hasSpeed()) {
            speed = -1.0f;
        }
        int accuracy = (int) (location.hasAccuracy() ? location.getAccuracy() : -1.0f);
        double altitude = location.hasAltitude() ? location.getAltitude() : 555.0d;
        if (location.hasBearing()) {
            f = location.getBearing();
        }
        return String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d", new Object[]{Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(f), Integer.valueOf(accuracy), Integer.valueOf(jL), Double.valueOf(altitude), Long.valueOf(location.getTime() / 1000)});
    }

    private double[] new(double d, double d2) {
        double d3 = 0.0d;
        if (d2 != 0.0d) {
            d3 = Math.toDegrees(Math.atan(d / d2));
        } else if (d > 0.0d) {
            d3 = 90.0d;
        } else if (d < 0.0d) {
            d3 = 270.0d;
        }
        return new double[]{Math.sqrt((d * d) + (d2 * d2)), d3};
    }

    public synchronized void cB() {
        if (com.baidu.location.f.isServing) {
            this.jJ = com.baidu.location.f.getServiceContext();
            try {
                this.jP = (LocationManager) this.jJ.getSystemService("location");
                this.jB = new a();
                this.jP.addGpsStatusListener(this.jB);
                this.jH = new c();
                this.jP.requestLocationUpdates("passive", 9000, 0.0f, this.jH);
            } catch (Exception e) {
            }
            this.jI = new a_1(this);
        }
    }

    public Location cC() {
        return this.jN;
    }

    public boolean cD() {
        return (this.jN == null || this.jN.getLatitude() == 0.0d || this.jN.getLongitude() == 0.0d) ? false : true;
    }

    public String cE() {
        if (this.jN == null) {
            return null;
        }
        double[] dArr;
        int i;
        String str = "{\"result\":{\"time\":\"" + k.ad() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\"," + "\"s\":\"%f\",\"n\":\"%d\"";
        int accuracy = (int) (this.jN.hasAccuracy() ? this.jN.getAccuracy() : TitleBar.SHAREBTN_RIGHT_MARGIN);
        float speed = (float) (((double) this.jN.getSpeed()) * 3.6d);
        if (!this.jN.hasSpeed()) {
            speed = -1.0f;
        }
        double[] dArr2 = new double[2];
        if (com.baidu.location.b.h.W().for(this.jN.getLongitude(), this.jN.getLatitude())) {
            dArr2 = Jni.if(this.jN.getLongitude(), this.jN.getLatitude(), "gps2gcj");
            if (dArr2[0] > 0.0d || dArr2[1] > 0.0d) {
                dArr = dArr2;
                i = 1;
            } else {
                dArr2[0] = this.jN.getLongitude();
                dArr2[1] = this.jN.getLatitude();
                dArr = dArr2;
                i = 1;
            }
        } else {
            dArr2[0] = this.jN.getLongitude();
            dArr2[1] = this.jN.getLatitude();
            dArr = dArr2;
            i = 0;
        }
        String format = String.format(Locale.CHINA, str, new Object[]{Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(accuracy), Float.valueOf(this.jN.getBearing()), Float.valueOf(speed), Integer.valueOf(jL)});
        if (i == 0) {
            format = format + ",\"in_cn\":\"0\"";
        }
        if (!this.jN.hasAltitude()) {
            return format + "}}";
        }
        return format + String.format(Locale.CHINA, ",\"h\":%.2f}}", new Object[]{Double.valueOf(this.jN.getAltitude())});
    }

    public synchronized void cF() {
        cJ();
        if (this.jP != null) {
            try {
                if (this.jB != null) {
                    this.jP.removeGpsStatusListener(this.jB);
                }
                this.jP.removeUpdates(this.jH);
            } catch (Exception e) {
            }
            this.jB = null;
            this.jP = null;
        }
    }

    public void cG() {
        cB();
        if (!this.jx) {
            try {
                this.jM = new b();
                this.jP.requestLocationUpdates("gps", 1000, 0.0f, this.jM);
                this.jP.addNmeaListener(this.jB);
                this.jx = true;
            } catch (Exception e) {
            }
        }
    }

    public String cH() {
        return (!cI() || this.jN == null) ? null : long(this.jN);
    }

    public boolean cI() {
        if (!cD() || System.currentTimeMillis() - this.jG > 10000) {
            return false;
        }
        return (!this.jq || System.currentTimeMillis() - this.ju >= 3000) ? this.jK : true;
    }

    public void cJ() {
        if (this.jx) {
            if (this.jP != null) {
                try {
                    if (this.jM != null) {
                        this.jP.removeUpdates(this.jM);
                    }
                    if (this.jB != null) {
                        this.jP.removeNmeaListener(this.jB);
                    }
                } catch (Exception e) {
                }
            }
            k.b1 = 0;
            k.co = 0;
            this.jM = null;
            this.jx = false;
            goto(false);
        }
    }

    public String cK() {
        return this.jv;
    }

    public boolean cL() {
        return this.jx;
    }

    public void else(boolean z) {
        if (z) {
            cG();
        } else {
            cJ();
        }
    }
}
