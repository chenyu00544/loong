package com.baidu.location.g;

import android.os.Message;
import android.os.SystemClock;
import com.baidu.location.Address;
import com.baidu.location.Address.Builder;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.b.l;
import com.baidu.location.b.m;
import com.baidu.location.b.o;
import com.baidu.location.e.c;
import com.baidu.location.h.h;
import com.baidu.platform.comapi.location.CoordinateType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.util.EntityUtils;

public class b implements f {
    private static SimpleDateFormat jm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long ji = 0;
    h jj = null;
    private l jk = new l();
    com.baidu.location.h.f jl = null;
    boolean jn = false;
    a jo = null;

    class a extends m {
        final /* synthetic */ b eW;
        boolean eX = false;
        BDLocation eY = null;

        public a(b bVar, BDLocation bDLocation) {
            this.eW = bVar;
            this.eY = bDLocation;
        }

        public void au() {
            if (this.eX) {
                this.c5 = "https://sapi.skyhookwireless.com/wps2/reverse-geo";
                StringBuffer stringBuffer = new StringBuffer(1024);
                stringBuffer.append("<ReverseGeoRQ xmlns=\"http://skyhookwireless.com/wps/2005\"\nversion=\"2.24\"\nstreet-address-lookup=\"full\">\n<authentication version=\"2.2\">\n<key key=\"" + Jni.dH() + "\"\n" + "username=\"BAIDULOC\"/></authentication>\n");
                stringBuffer.append("<point>");
                stringBuffer.append("<latitude>");
                stringBuffer.append("" + this.eY.getLatitude());
                stringBuffer.append("</latitude>");
                stringBuffer.append("<longitude>");
                stringBuffer.append("" + this.eY.getLongitude());
                stringBuffer.append("</longitude>");
                stringBuffer.append("</point>");
                stringBuffer.append("</ReverseGeoRQ>");
                this.da = stringBuffer.toString();
                return;
            }
            try {
                this.c5 = k.aa() + "?&x=" + this.eY.getLongitude() + "&y=" + this.eY.getLatitude() + "&r=100&prodname=searchbox" + "&addr=" + URLEncoder.encode("country|country_code|province|city|city_code|street|street_number|district", "UTF-8");
            } catch (UnsupportedEncodingException e) {
                this.c5 = k.aa() + "?&x=" + this.eY.getLongitude() + "&y=" + this.eY.getLatitude() + "&r=100";
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void int(boolean r11) {
            /*
            r10 = this;
            r7 = 21;
            r6 = 1;
            r5 = 0;
            r1 = 0;
            if (r11 == 0) goto L_0x01c0;
        L_0x0007:
            r0 = r10.c6;
            if (r0 == 0) goto L_0x01c0;
        L_0x000b:
            r0 = r10.c6;	 Catch:{ Exception -> 0x01bd }
            r2 = "utf-8";
            r0 = org.apache.http.util.EntityUtils.toString(r0, r2);	 Catch:{ Exception -> 0x01bd }
            r2 = r10.eX;	 Catch:{ Exception -> 0x01bd }
            if (r2 == 0) goto L_0x00d3;
        L_0x0017:
            r3 = new com.baidu.location.g.a;	 Catch:{ Exception -> 0x01bd }
            r3.<init>(r0);	 Catch:{ Exception -> 0x01bd }
            r4 = r3.i7;	 Catch:{ Exception -> 0x01bd }
            r1 = r3.i6;	 Catch:{ Exception -> 0x01bd }
            r0 = r3.jb;	 Catch:{ Exception -> 0x01bd }
            r2 = "";
            r2 = r1.equals(r2);	 Catch:{ Exception -> 0x01bd }
            if (r2 == 0) goto L_0x0222;
        L_0x002a:
            r1 = r3.i3;	 Catch:{ Exception -> 0x01bd }
            r2 = r1;
        L_0x002d:
            r1 = "";
            r1 = r0.equals(r1);	 Catch:{ Exception -> 0x01bd }
            if (r1 == 0) goto L_0x021f;
        L_0x0035:
            r0 = r3.jc;	 Catch:{ Exception -> 0x01bd }
            r1 = r0;
        L_0x0038:
            r0 = r3.jh;	 Catch:{ Exception -> 0x01bd }
            r5 = "";
            r5 = r0.equals(r5);	 Catch:{ Exception -> 0x01bd }
            if (r5 == 0) goto L_0x0044;
        L_0x0042:
            r0 = r3.ja;	 Catch:{ Exception -> 0x01bd }
        L_0x0044:
            r5 = "skyhook rgc metro2 = ";
            r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01bd }
            r6.<init>();	 Catch:{ Exception -> 0x01bd }
            r7 = "";
            r6 = r6.append(r7);	 Catch:{ Exception -> 0x01bd }
            r7 = r3.ja;	 Catch:{ Exception -> 0x01bd }
            r6 = r6.append(r7);	 Catch:{ Exception -> 0x01bd }
            r6 = r6.toString();	 Catch:{ Exception -> 0x01bd }
            android.util.Log.i(r5, r6);	 Catch:{ Exception -> 0x01bd }
            r5 = r3.je;	 Catch:{ Exception -> 0x01bd }
            r3 = r3.i4;	 Catch:{ Exception -> 0x01bd }
            r6 = new com.baidu.location.Address$Builder;	 Catch:{ Exception -> 0x01bd }
            r6.<init>();	 Catch:{ Exception -> 0x01bd }
            r4 = r6.country(r4);	 Catch:{ Exception -> 0x01bd }
            r2 = r4.province(r2);	 Catch:{ Exception -> 0x01bd }
            r1 = r2.city(r1);	 Catch:{ Exception -> 0x01bd }
            r0 = r1.district(r0);	 Catch:{ Exception -> 0x01bd }
            r0 = r0.street(r5);	 Catch:{ Exception -> 0x01bd }
            r0 = r0.streetNumber(r3);	 Catch:{ Exception -> 0x01bd }
            r0 = r0.build();	 Catch:{ Exception -> 0x01bd }
            r1 = r10.eY;	 Catch:{ Exception -> 0x01bd }
            r1.setAddr(r0);	 Catch:{ Exception -> 0x01bd }
        L_0x0088:
            r0 = r10.eW;	 Catch:{ Exception -> 0x01bd }
            r0 = r0.jo;	 Catch:{ Exception -> 0x01bd }
            r0 = r0.cy();	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x00bf;
        L_0x0092:
            r0 = r10.eW;	 Catch:{ Exception -> 0x01bd }
            r0 = r0.jo;	 Catch:{ Exception -> 0x01bd }
            r0 = r0.cv();	 Catch:{ Exception -> 0x01bd }
            r2 = r10.eW;	 Catch:{ Exception -> 0x01bd }
            r2 = r2.jo;	 Catch:{ Exception -> 0x01bd }
            r2 = r2.cu();	 Catch:{ Exception -> 0x01bd }
            r4 = "gps2gcj";
            r0 = com.baidu.location.Jni.if(r0, r2, r4);	 Catch:{ Exception -> 0x01bd }
            r1 = r10.eY;	 Catch:{ Exception -> 0x01bd }
            r2 = "gcj02";
            r1.setCoorType(r2);	 Catch:{ Exception -> 0x01bd }
            r1 = r10.eY;	 Catch:{ Exception -> 0x01bd }
            r2 = 0;
            r2 = r0[r2];	 Catch:{ Exception -> 0x01bd }
            r1.setLongitude(r2);	 Catch:{ Exception -> 0x01bd }
            r1 = r10.eY;	 Catch:{ Exception -> 0x01bd }
            r2 = 1;
            r2 = r0[r2];	 Catch:{ Exception -> 0x01bd }
            r1.setLatitude(r2);	 Catch:{ Exception -> 0x01bd }
        L_0x00bf:
            r0 = com.baidu.location.e.m.ba();	 Catch:{ Exception -> 0x01bd }
            r0 = r0.fP;	 Catch:{ Exception -> 0x01bd }
            r1 = 21;
            r0 = r0.obtainMessage(r1);	 Catch:{ Exception -> 0x01bd }
            r1 = r10.eY;	 Catch:{ Exception -> 0x01bd }
            r0.obj = r1;	 Catch:{ Exception -> 0x01bd }
            r0.sendToTarget();	 Catch:{ Exception -> 0x01bd }
        L_0x00d2:
            return;
        L_0x00d3:
            r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x01bd }
            r2.<init>(r0);	 Catch:{ Exception -> 0x01bd }
            r0 = "addr";
            r0 = r2.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x0088;
        L_0x00e0:
            r0 = "addr";
            r9 = r2.getJSONObject(r0);	 Catch:{ Exception -> 0x01bd }
            r0 = "country";
            r0 = r9.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x021c;
        L_0x00ee:
            r0 = "country";
            r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01bd }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01bd }
            if (r2 != 0) goto L_0x021c;
        L_0x00fa:
            r8 = r0;
        L_0x00fb:
            r0 = "country_code";
            r0 = r9.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x0219;
        L_0x0103:
            r0 = "country_code";
            r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01bd }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01bd }
            if (r2 != 0) goto L_0x0219;
        L_0x010f:
            r7 = r0;
        L_0x0110:
            r0 = "province";
            r0 = r9.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x0216;
        L_0x0118:
            r0 = "province";
            r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01bd }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01bd }
            if (r2 != 0) goto L_0x0216;
        L_0x0124:
            r6 = r0;
        L_0x0125:
            r0 = "city";
            r0 = r9.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x0213;
        L_0x012d:
            r0 = "city";
            r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01bd }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01bd }
            if (r2 != 0) goto L_0x0213;
        L_0x0139:
            r5 = r0;
        L_0x013a:
            r0 = "city_code";
            r0 = r9.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x0210;
        L_0x0142:
            r0 = "city_code";
            r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01bd }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01bd }
            if (r2 != 0) goto L_0x0210;
        L_0x014e:
            r4 = r0;
        L_0x014f:
            r0 = "district";
            r0 = r9.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x020d;
        L_0x0157:
            r0 = "district";
            r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01bd }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01bd }
            if (r2 != 0) goto L_0x020d;
        L_0x0163:
            r3 = r0;
        L_0x0164:
            r0 = "street";
            r0 = r9.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x020a;
        L_0x016c:
            r0 = "street";
            r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01bd }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01bd }
            if (r2 != 0) goto L_0x020a;
        L_0x0178:
            r2 = r0;
        L_0x0179:
            r0 = "streetNumber";
            r0 = r9.has(r0);	 Catch:{ Exception -> 0x01bd }
            if (r0 == 0) goto L_0x0208;
        L_0x0181:
            r0 = "street_number";
            r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01bd }
            r9 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01bd }
            if (r9 != 0) goto L_0x0208;
        L_0x018d:
            r1 = new com.baidu.location.Address$Builder;	 Catch:{ Exception -> 0x01bd }
            r1.<init>();	 Catch:{ Exception -> 0x01bd }
            r1 = r1.country(r8);	 Catch:{ Exception -> 0x01bd }
            r1 = r1.countryCode(r7);	 Catch:{ Exception -> 0x01bd }
            r1 = r1.province(r6);	 Catch:{ Exception -> 0x01bd }
            r1 = r1.city(r5);	 Catch:{ Exception -> 0x01bd }
            r1 = r1.cityCode(r4);	 Catch:{ Exception -> 0x01bd }
            r1 = r1.district(r3);	 Catch:{ Exception -> 0x01bd }
            r1 = r1.street(r2);	 Catch:{ Exception -> 0x01bd }
            r0 = r1.streetNumber(r0);	 Catch:{ Exception -> 0x01bd }
            r0 = r0.build();	 Catch:{ Exception -> 0x01bd }
            r1 = r10.eY;	 Catch:{ Exception -> 0x01bd }
            r1.setAddr(r0);	 Catch:{ Exception -> 0x01bd }
            goto L_0x0088;
        L_0x01bd:
            r0 = move-exception;
            goto L_0x00d2;
        L_0x01c0:
            r0 = r10.eW;
            r0 = r0.jo;
            r0 = r0.cy();
            if (r0 == 0) goto L_0x01f5;
        L_0x01ca:
            r0 = r10.eW;
            r0 = r0.jo;
            r0 = r0.cv();
            r2 = r10.eW;
            r2 = r2.jo;
            r2 = r2.cu();
            r4 = "gps2gcj";
            r0 = com.baidu.location.Jni.if(r0, r2, r4);
            r1 = r10.eY;
            r2 = "gcj02";
            r1.setCoorType(r2);
            r1 = r10.eY;
            r2 = r0[r5];
            r1.setLongitude(r2);
            r1 = r10.eY;
            r2 = r0[r6];
            r1.setLatitude(r2);
        L_0x01f5:
            r0 = com.baidu.location.e.m.ba();
            r0 = r0.fP;
            r0 = r0.obtainMessage(r7);
            r1 = r10.eY;
            r0.obj = r1;
            r0.sendToTarget();
            goto L_0x00d2;
        L_0x0208:
            r0 = r1;
            goto L_0x018d;
        L_0x020a:
            r2 = r1;
            goto L_0x0179;
        L_0x020d:
            r3 = r1;
            goto L_0x0164;
        L_0x0210:
            r4 = r1;
            goto L_0x014f;
        L_0x0213:
            r5 = r1;
            goto L_0x013a;
        L_0x0216:
            r6 = r1;
            goto L_0x0125;
        L_0x0219:
            r7 = r1;
            goto L_0x0110;
        L_0x021c:
            r8 = r1;
            goto L_0x00fb;
        L_0x021f:
            r1 = r0;
            goto L_0x0038;
        L_0x0222:
            r2 = r1;
            goto L_0x002d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.b.a.int(boolean):void");
        }

        void new(boolean z) {
            this.eX = z;
            if (z) {
                av();
            } else {
                as();
            }
        }
    }

    class b extends m {
        final /* synthetic */ b eZ;

        public b(b bVar) {
            this.eZ = bVar;
        }

        public void au() {
        }

        void h(String str) {
            this.da = str;
            av();
        }

        public void int(boolean z) {
            this.eZ.jk.for(SystemClock.uptimeMillis());
            if (!z || this.c6 == null) {
                BDLocation bDLocation = new BDLocation();
                bDLocation.setLocType(63);
                c.bq().try(bDLocation);
                return;
            }
            try {
                String entityUtils = EntityUtils.toString(this.c6, "utf-8");
                this.eZ.jo = new a(entityUtils);
                if (this.eZ.jn && this.eZ.jo.ct()) {
                    BDLocation bDLocation2 = new BDLocation();
                    bDLocation2.setLongitude(this.eZ.jo.cv());
                    bDLocation2.setLatitude(this.eZ.jo.cu());
                    bDLocation2.setRadius(this.eZ.jo.cw());
                    bDLocation2.setLocType(BDLocation.TypeNetWorkLocation);
                    bDLocation2.setLocationWhere(0);
                    bDLocation2.setCoorType(CoordinateType.WGS84);
                    bDLocation2.setNetworkLocationType("sky");
                    String str = this.eZ.jo.i7;
                    String str2 = this.eZ.jo.i6;
                    entityUtils = this.eZ.jo.jb;
                    if (str2.equals("")) {
                        str2 = this.eZ.jo.i3;
                    }
                    if (entityUtils.equals("")) {
                        entityUtils = this.eZ.jo.ja;
                    }
                    if (entityUtils.equals("")) {
                        entityUtils = this.eZ.jo.jc;
                    }
                    String str3 = this.eZ.jo.jh;
                    String str4 = this.eZ.jo.je;
                    Address build = new Builder().country(str).province(str2).city(entityUtils).district(str3).street(str4).streetNumber(this.eZ.jo.i4).build();
                    bDLocation2.setTime(b.jm.format(new Date()));
                    bDLocation2.setOperators(com.baidu.location.h.c.a().cR());
                    if (com.baidu.location.e.l.cf().cc()) {
                        bDLocation2.setDirection(com.baidu.location.e.l.cf().ch());
                    }
                    if (k.cf.equals("all")) {
                        bDLocation2.setAddr(build);
                    }
                    if (this.eZ.jo.cy()) {
                        double[] dArr = Jni.if(this.eZ.jo.cv(), this.eZ.jo.cu(), "gps2gcj");
                        bDLocation2.setCoorType(CoordinateType.GCJ02);
                        bDLocation2.setLongitude(dArr[0]);
                        bDLocation2.setLatitude(dArr[1]);
                    }
                    Message obtainMessage = com.baidu.location.e.m.ba().fP.obtainMessage(21);
                    obtainMessage.obj = bDLocation2;
                    obtainMessage.sendToTarget();
                    this.eZ.jk.int(SystemClock.uptimeMillis());
                    this.eZ.jk.char(l.c0);
                    if (this.eZ.jj != null) {
                        this.eZ.jk.else(this.eZ.jj.dy());
                    }
                    o.aX().if(this.eZ.jk);
                } else if (this.eZ.jn && !this.eZ.jo.ct()) {
                    bDLocation = new BDLocation();
                    bDLocation.setLocType(BDLocation.TypeServerError);
                    c.bq().try(bDLocation);
                    this.eZ.jk.int(SystemClock.uptimeMillis());
                    this.eZ.jk.char(l.cX);
                    if (this.eZ.jj != null) {
                        this.eZ.jk.else(this.eZ.jj.dy());
                    }
                    o.aX().if(this.eZ.jk);
                }
            } catch (Exception e) {
            }
        }
    }

    public b(h hVar, com.baidu.location.h.f fVar, boolean z) {
        this.jj = hVar;
        this.jl = fVar;
        this.jn = z;
        this.jk.aj();
        long uptimeMillis = SystemClock.uptimeMillis();
        this.jk.do(uptimeMillis);
        this.jk.if(uptimeMillis);
    }

    public void cA() {
        String str = null;
        StringBuffer stringBuffer = new StringBuffer(1024);
        String i = (this.jl == null || this.jl.dm() <= 1) ? null : this.jl.i(15);
        if (this.jj != null && this.jj.dt()) {
            str = this.jj.dx();
        }
        if (i != null || str != null) {
            stringBuffer.append("<LocationRQ xmlns=\"http://skyhookwireless.com/wps/2005\"\nversion=\"2.24\"\nstreet-address-lookup=\"full\">\n<authentication version=\"2.2\">\n<key key=\"" + Jni.dH() + "\"\n" + "username=\"BAIDULOC\"/></authentication>\n");
            if (i != null) {
                stringBuffer.append(i);
            }
            if (str != null) {
                stringBuffer.append(str);
            }
            stringBuffer.append("</LocationRQ>");
            new b(this).h(stringBuffer.toString());
            this.ji = System.currentTimeMillis();
        }
    }
}
