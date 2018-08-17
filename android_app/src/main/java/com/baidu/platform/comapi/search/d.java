package com.baidu.platform.comapi.search;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.mapapi.MessageCenter;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.model.inner.MapBound;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.platform.comjni.map.search.a;
import java.util.Map;

public class d {
    private static final String a = d.class.getSimpleName();
    private a b;
    private long c;
    private c d;
    private Handler e;
    private int f;
    private Bundle g;

    public d() {
        this.b = null;
        this.d = null;
        this.e = null;
        this.f = 10;
        this.g = null;
        this.b = new a();
        this.c = this.b.a();
        this.d = new c();
        this.e = new e(this);
        MessageCenter.registMessage(m_AppUI.MSG_APP_DATA_OK, this.e);
        this.d.a(this);
    }

    private Bundle a(a aVar) {
        if (aVar == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("type", aVar.a);
        bundle.putString("uid", aVar.e);
        if (aVar.b != null) {
            bundle.putInt("x", aVar.b.getmPtx());
            bundle.putInt("y", aVar.b.getmPty());
        }
        bundle.putString("keyword", aVar.d);
        return bundle;
    }

    private Bundle c() {
        if (this.g == null) {
            this.g = new Bundle();
        } else {
            this.g.clear();
        }
        return this.g;
    }

    public void a() {
        MessageCenter.unregistMessage(m_AppUI.MSG_APP_DATA_OK, this.e);
        this.b.b();
        this.d.a();
        this.e = null;
        this.b = null;
        this.g = null;
        this.d = null;
        this.c = 0;
    }

    public void a(int i) {
        if (i > 0 && i <= 50) {
            this.f = i;
        }
    }

    public void a(b bVar) {
        this.d.a(bVar);
    }

    public boolean a(Point point) {
        if (point == null) {
            return false;
        }
        int i = point.getmPty();
        return this.b.a(point.getmPtx(), i);
    }

    public boolean a(Point point, Point point2, String str, String str2, int i, int i2, int i3, int i4, int i5) {
        if (this.b == null) {
            return false;
        }
        Bundle c = c();
        if (i3 == 3) {
            c.putInt("cityCode", i4);
            c.putInt("pn", i5);
        } else {
            if (i >= 0) {
                c.putInt("startCode", i);
            }
            if (i >= 0) {
                c.putInt("endCode", i2);
            }
        }
        c.putInt("tn", i3);
        if (point != null) {
            c.putInt("startX", point.getmPtx());
            c.putInt("startY", point.getmPty());
        }
        if (point2 != null) {
            c.putInt("endX", point2.getmPtx());
            c.putInt("endY", point2.getmPty());
        }
        c.putString("strName", str);
        c.putString("endName", str2);
        return this.b.k(c);
    }

    public boolean a(Point point, String str, String str2) {
        return (point == null || str == null || str2 == null) ? false : this.b.a(point.getmPtx(), point.getmPty(), str, str2);
    }

    public boolean a(PlanNode planNode, PlanNode planNode2, String str, int i, int i2, int i3, int i4) {
        Bundle c = c();
        if (planNode.getLocation() != null) {
            c.putString("origin", planNode.getLocation().latitude + "," + planNode.getLocation().longitude);
            if (!(planNode.getCity() == null || planNode.getCity() == "")) {
                c.putString("origin_region", planNode.getCity());
            }
        } else if (planNode.getName() == null || planNode.getName() == "" || planNode.getCity() == null || planNode.getCity() == "") {
            return false;
        } else {
            c.putString("origin", planNode.getName());
            c.putString("origin_region", planNode.getCity());
        }
        if (planNode2.getLocation() != null) {
            c.putString("destination", planNode2.getLocation().latitude + "," + planNode2.getLocation().longitude);
            if (!(planNode2.getCity() == null || planNode2.getCity() == "")) {
                c.putString("destination_region", planNode2.getCity());
            }
        } else if (planNode2.getName() == null || planNode2.getName() == "" || planNode2.getCity() == null || planNode2.getCity() == "") {
            return false;
        } else {
            c.putString("destination", planNode2.getName());
            c.putString("destination_region", planNode2.getCity());
        }
        if (!(str == null || str == "")) {
            c.putString("coord_type", str);
        }
        if (i < 0 || i > 5) {
            return false;
        }
        c.putInt("tactics_incity", i);
        if (i2 < 0 || i2 > 2) {
            return false;
        }
        c.putInt("tactics_intercity", i2);
        if (i3 < 0 || i3 > 2) {
            return false;
        }
        c.putInt("trans_type_intercity", i3);
        c.putInt("page_size", this.f);
        c.putInt("page_index", i4);
        return this.b.e(c);
    }

    public boolean a(a aVar, a aVar2, String str, MapBound mapBound, int i, int i2, Map<String, Object> map) {
        if (str == null || str.equals("")) {
            return false;
        }
        Bundle c = c();
        Bundle a = a(aVar);
        Bundle a2 = a(aVar2);
        if (a == null || a2 == null) {
            return false;
        }
        c.putBundle("start", a);
        c.putBundle("end", a2);
        if (i2 < 3 || i2 > 6) {
            return false;
        }
        c.putInt("strategy", i2);
        c.putString("cityid", str);
        if (!(mapBound == null || mapBound.ptLB == null || mapBound.ptRT == null)) {
            Bundle bundle = new Bundle();
            bundle.putInt("level", i);
            bundle.putInt("ll_x", mapBound.ptLB.getmPtx());
            bundle.putInt("ll_y", mapBound.ptLB.getmPty());
            bundle.putInt("ru_x", mapBound.ptRT.getmPtx());
            bundle.putInt("ru_y", mapBound.ptRT.getmPty());
            c.putBundle("mapbound", bundle);
        }
        if (map != null) {
            bundle = new Bundle();
            for (Object next : map.keySet()) {
                bundle.putString(next.toString(), map.get(next).toString());
            }
            c.putBundle("extparams", bundle);
        }
        return this.b.d(c);
    }

    public boolean a(a aVar, a aVar2, String str, String str2) {
        if (aVar == null || aVar2 == null) {
            return false;
        }
        if (aVar.c == null && (aVar.d == null || aVar.d.equals(""))) {
            return false;
        }
        if (aVar2.c == null && (aVar2.d == null || aVar2.d.equals(""))) {
            return false;
        }
        Bundle c = c();
        c.putInt("starttype", aVar.a);
        if (aVar.c != null) {
            c.putDouble("startptx", aVar.c.longitude);
            c.putDouble("startpty", aVar.c.latitude);
        }
        c.putString("startkeyword", aVar.d);
        c.putString("startcity", str);
        c.putInt("endtype", aVar2.a);
        if (aVar2.c != null) {
            c.putDouble("endptx", aVar2.c.longitude);
            c.putDouble("endpty", aVar2.c.latitude);
        }
        c.putString("endkeyword", aVar2.d);
        c.putString("endcity", str2);
        return this.b.h(c);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.baidu.platform.comapi.search.a r13, com.baidu.platform.comapi.search.a r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, com.baidu.mapapi.model.inner.MapBound r18, int r19, int r20, int r21, java.util.ArrayList<com.baidu.platform.comapi.search.f> r22, java.util.Map<java.lang.String, java.lang.Object> r23) {
        /*
        r12 = this;
        if (r13 == 0) goto L_0x0004;
    L_0x0002:
        if (r14 != 0) goto L_0x0006;
    L_0x0004:
        r1 = 0;
    L_0x0005:
        return r1;
    L_0x0006:
        r1 = r13.b;
        if (r1 != 0) goto L_0x0018;
    L_0x000a:
        if (r16 == 0) goto L_0x0016;
    L_0x000c:
        r1 = "";
        r0 = r16;
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0018;
    L_0x0016:
        r1 = 0;
        goto L_0x0005;
    L_0x0018:
        r1 = r14.b;
        if (r1 != 0) goto L_0x002a;
    L_0x001c:
        if (r17 == 0) goto L_0x0028;
    L_0x001e:
        r1 = "";
        r0 = r17;
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x002a;
    L_0x0028:
        r1 = 0;
        goto L_0x0005;
    L_0x002a:
        r6 = r12.c();
        r1 = "starttype";
        r2 = r13.a;
        r6.putInt(r1, r2);
        r1 = r13.b;
        if (r1 == 0) goto L_0x004f;
    L_0x0039:
        r1 = "startptx";
        r2 = r13.b;
        r2 = r2.getmPtx();
        r6.putInt(r1, r2);
        r1 = "startpty";
        r2 = r13.b;
        r2 = r2.getmPty();
        r6.putInt(r1, r2);
    L_0x004f:
        r1 = "startkeyword";
        r2 = r13.d;
        r6.putString(r1, r2);
        r1 = "startuid";
        r2 = r13.e;
        r6.putString(r1, r2);
        r1 = "endtype";
        r2 = r14.a;
        r6.putInt(r1, r2);
        r1 = r14.b;
        if (r1 == 0) goto L_0x007e;
    L_0x0068:
        r1 = "endptx";
        r2 = r14.b;
        r2 = r2.getmPtx();
        r6.putInt(r1, r2);
        r1 = "endpty";
        r2 = r14.b;
        r2 = r2.getmPty();
        r6.putInt(r1, r2);
    L_0x007e:
        r1 = "endkeyword";
        r2 = r14.d;
        r6.putString(r1, r2);
        r1 = "enduid";
        r2 = r14.e;
        r6.putString(r1, r2);
        r1 = "level";
        r0 = r19;
        r6.putInt(r1, r0);
        if (r18 == 0) goto L_0x00d5;
    L_0x0095:
        r0 = r18;
        r1 = r0.ptLB;
        if (r1 == 0) goto L_0x00d5;
    L_0x009b:
        r0 = r18;
        r1 = r0.ptRT;
        if (r1 == 0) goto L_0x00d5;
    L_0x00a1:
        r1 = "ll_x";
        r0 = r18;
        r2 = r0.ptLB;
        r2 = r2.getmPtx();
        r6.putInt(r1, r2);
        r1 = "ll_y";
        r0 = r18;
        r2 = r0.ptLB;
        r2 = r2.getmPty();
        r6.putInt(r1, r2);
        r1 = "ru_x";
        r0 = r18;
        r2 = r0.ptRT;
        r2 = r2.getmPtx();
        r6.putInt(r1, r2);
        r1 = "ru_y";
        r0 = r18;
        r2 = r0.ptRT;
        r2 = r2.getmPty();
        r6.putInt(r1, r2);
    L_0x00d5:
        r1 = "strategy";
        r0 = r20;
        r6.putInt(r1, r0);
        r1 = "cityid";
        r6.putString(r1, r15);
        r1 = "st_cityid";
        r0 = r16;
        r6.putString(r1, r0);
        r1 = "en_cityid";
        r0 = r17;
        r6.putString(r1, r0);
        r1 = "traffic";
        r0 = r21;
        r6.putInt(r1, r0);
        if (r22 == 0) goto L_0x01f1;
    L_0x00f8:
        r7 = r22.size();
        r3 = 0;
        r4 = "";
        r2 = "";
        r1 = 0;
        r5 = r1;
    L_0x0103:
        if (r5 >= r7) goto L_0x01e7;
    L_0x0105:
        r8 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x01de }
        r8.<init>();	 Catch:{ JSONException -> 0x01de }
        r0 = r22;
        r1 = r0.get(r5);	 Catch:{ JSONException -> 0x01de }
        r1 = (com.baidu.platform.comapi.search.f) r1;	 Catch:{ JSONException -> 0x01de }
        r1 = r1.a;	 Catch:{ JSONException -> 0x01de }
        if (r1 == 0) goto L_0x01d6;
    L_0x0116:
        r1 = "type";
        r9 = 1;
        r8.put(r1, r9);	 Catch:{ JSONException -> 0x01de }
    L_0x011c:
        r9 = "keyword";
        r0 = r22;
        r1 = r0.get(r5);	 Catch:{ JSONException -> 0x01de }
        r1 = (com.baidu.platform.comapi.search.f) r1;	 Catch:{ JSONException -> 0x01de }
        r1 = r1.b;	 Catch:{ JSONException -> 0x01de }
        r8.put(r9, r1);	 Catch:{ JSONException -> 0x01de }
        r0 = r22;
        r1 = r0.get(r5);	 Catch:{ JSONException -> 0x01de }
        r1 = (com.baidu.platform.comapi.search.f) r1;	 Catch:{ JSONException -> 0x01de }
        r1 = r1.a;	 Catch:{ JSONException -> 0x01de }
        if (r1 == 0) goto L_0x0173;
    L_0x0137:
        r9 = "xy";
        r10 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01de }
        r10.<init>();	 Catch:{ JSONException -> 0x01de }
        r0 = r22;
        r1 = r0.get(r5);	 Catch:{ JSONException -> 0x01de }
        r1 = (com.baidu.platform.comapi.search.f) r1;	 Catch:{ JSONException -> 0x01de }
        r1 = r1.a;	 Catch:{ JSONException -> 0x01de }
        r1 = r1.x;	 Catch:{ JSONException -> 0x01de }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ JSONException -> 0x01de }
        r1 = r10.append(r1);	 Catch:{ JSONException -> 0x01de }
        r10 = ",";
        r10 = r1.append(r10);	 Catch:{ JSONException -> 0x01de }
        r0 = r22;
        r1 = r0.get(r5);	 Catch:{ JSONException -> 0x01de }
        r1 = (com.baidu.platform.comapi.search.f) r1;	 Catch:{ JSONException -> 0x01de }
        r1 = r1.a;	 Catch:{ JSONException -> 0x01de }
        r1 = r1.y;	 Catch:{ JSONException -> 0x01de }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ JSONException -> 0x01de }
        r1 = r10.append(r1);	 Catch:{ JSONException -> 0x01de }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x01de }
        r8.put(r9, r1);	 Catch:{ JSONException -> 0x01de }
    L_0x0173:
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01de }
        r1.<init>();	 Catch:{ JSONException -> 0x01de }
        r9 = r1.append(r2);	 Catch:{ JSONException -> 0x01de }
        r0 = r22;
        r1 = r0.get(r5);	 Catch:{ JSONException -> 0x01de }
        r1 = (com.baidu.platform.comapi.search.f) r1;	 Catch:{ JSONException -> 0x01de }
        r1 = r1.c;	 Catch:{ JSONException -> 0x01de }
        r1 = r9.append(r1);	 Catch:{ JSONException -> 0x01de }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x01de }
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x022b }
        r2.<init>();	 Catch:{ JSONException -> 0x022b }
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x022b }
        r8 = r8.toString();	 Catch:{ JSONException -> 0x022b }
        r2 = r2.append(r8);	 Catch:{ JSONException -> 0x022b }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x022b }
        r4 = r7 + -1;
        if (r3 == r4) goto L_0x01cd;
    L_0x01a7:
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0229 }
        r4.<init>();	 Catch:{ JSONException -> 0x0229 }
        r4 = r4.append(r2);	 Catch:{ JSONException -> 0x0229 }
        r8 = "|";
        r4 = r4.append(r8);	 Catch:{ JSONException -> 0x0229 }
        r2 = r4.toString();	 Catch:{ JSONException -> 0x0229 }
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0229 }
        r4.<init>();	 Catch:{ JSONException -> 0x0229 }
        r4 = r4.append(r1);	 Catch:{ JSONException -> 0x0229 }
        r8 = "|";
        r4 = r4.append(r8);	 Catch:{ JSONException -> 0x0229 }
        r1 = r4.toString();	 Catch:{ JSONException -> 0x0229 }
    L_0x01cd:
        r3 = r3 + 1;
    L_0x01cf:
        r4 = r5 + 1;
        r5 = r4;
        r4 = r2;
        r2 = r1;
        goto L_0x0103;
    L_0x01d6:
        r1 = "type";
        r9 = 2;
        r8.put(r1, r9);	 Catch:{ JSONException -> 0x01de }
        goto L_0x011c;
    L_0x01de:
        r1 = move-exception;
        r11 = r1;
        r1 = r2;
        r2 = r4;
        r4 = r11;
    L_0x01e3:
        r4.printStackTrace();
        goto L_0x01cf;
    L_0x01e7:
        r1 = "wp";
        r6.putString(r1, r4);
        r1 = "wpc";
        r6.putString(r1, r2);
    L_0x01f1:
        if (r23 == 0) goto L_0x0221;
    L_0x01f3:
        r1 = new android.os.Bundle;
        r1.<init>();
        r2 = r23.keySet();
        r2 = r2.iterator();
    L_0x0200:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x021c;
    L_0x0206:
        r3 = r2.next();
        r0 = r23;
        r4 = r0.get(r3);
        r3 = r3.toString();
        r4 = r4.toString();
        r1.putString(r3, r4);
        goto L_0x0200;
    L_0x021c:
        r2 = "extparams";
        r6.putBundle(r2, r1);
    L_0x0221:
        r1 = r12.b;
        r1 = r1.f(r6);
        goto L_0x0005;
    L_0x0229:
        r4 = move-exception;
        goto L_0x01e3;
    L_0x022b:
        r2 = move-exception;
        r11 = r2;
        r2 = r4;
        r4 = r11;
        goto L_0x01e3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.search.d.a(com.baidu.platform.comapi.search.a, com.baidu.platform.comapi.search.a, java.lang.String, java.lang.String, java.lang.String, com.baidu.mapapi.model.inner.MapBound, int, int, int, java.util.ArrayList, java.util.Map):boolean");
    }

    public boolean a(a aVar, a aVar2, String str, String str2, String str3, MapBound mapBound, int i, Map<String, Object> map) {
        if (aVar == null || aVar2 == null) {
            return false;
        }
        if (aVar.b == null && (str2 == null || str2.equals(""))) {
            return false;
        }
        if (aVar2.b == null && (str3 == null || str3.equals(""))) {
            return false;
        }
        Bundle c = c();
        c.putInt("starttype", aVar.a);
        if (aVar.b != null) {
            c.putInt("startptx", aVar.b.getmPtx());
            c.putInt("startpty", aVar.b.getmPty());
        }
        c.putString("startkeyword", aVar.d);
        c.putString("startuid", aVar.e);
        c.putInt("endtype", aVar2.a);
        if (aVar2.b != null) {
            c.putInt("endptx", aVar2.b.getmPtx());
            c.putInt("endpty", aVar2.b.getmPty());
        }
        c.putString("endkeyword", aVar2.d);
        c.putString("enduid", aVar2.e);
        c.putInt("level", i);
        if (!(mapBound == null || mapBound.ptLB == null || mapBound.ptRT == null)) {
            c.putInt("ll_x", mapBound.ptLB.getmPtx());
            c.putInt("ll_y", mapBound.ptLB.getmPty());
            c.putInt("ru_x", mapBound.ptRT.getmPtx());
            c.putInt("ru_y", mapBound.ptRT.getmPty());
        }
        c.putString("cityid", str);
        c.putString("st_cityid", str2);
        c.putString("en_cityid", str3);
        if (map != null) {
            Bundle bundle = new Bundle();
            for (Object next : map.keySet()) {
                bundle.putString(next.toString(), map.get(next).toString());
            }
            c.putBundle("extparams", bundle);
        }
        return this.b.g(c);
    }

    public boolean a(String str) {
        if (str == null) {
            return false;
        }
        String trim = str.trim();
        return (trim.length() == 0 || trim.length() > 99) ? false : this.b.a(trim);
    }

    public boolean a(String str, int i, int i2, int i3, MapBound mapBound, MapBound mapBound2, Map<String, Object> map, int i4) {
        if (str == null) {
            return false;
        }
        String trim = str.trim();
        if (trim.length() == 0 || trim.length() > 99) {
            return false;
        }
        Bundle c = c();
        c.putString("keyword", trim);
        c.putInt("pagenum", i2);
        c.putInt("count", this.f);
        c.putInt("cityid", i);
        c.putInt("level", i3);
        c.putInt("sortType", i4);
        if (mapBound2 != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("ll_x", mapBound2.ptLB.getmPtx());
            bundle.putInt("ll_y", mapBound2.ptLB.getmPty());
            bundle.putInt("ru_x", mapBound2.ptRT.getmPtx());
            bundle.putInt("ru_y", mapBound2.ptRT.getmPty());
            c.putBundle("mapbound", bundle);
        }
        if (mapBound != null) {
            c.putInt("ll_x", mapBound.ptLB.getmPtx());
            c.putInt("ll_y", mapBound.ptLB.getmPty());
            c.putInt("ru_x", mapBound.ptRT.getmPtx());
            c.putInt("ru_y", mapBound.ptRT.getmPty());
            c.putInt("loc_x", (mapBound.ptLB.getmPtx() + mapBound.ptRT.getmPtx()) / 2);
            c.putInt("loc_y", (mapBound.ptLB.getmPty() + mapBound.ptRT.getmPty()) / 2);
        }
        if (map != null) {
            bundle = new Bundle();
            for (Object next : map.keySet()) {
                bundle.putString(next.toString(), map.get(next).toString());
            }
            c.putBundle("extparams", bundle);
        }
        return this.b.b(c);
    }

    public boolean a(String str, int i, int i2, MapBound mapBound, int i3, Point point, Map<String, Object> map) {
        if (mapBound == null || str == null) {
            return false;
        }
        String trim = str.trim();
        if (trim.length() == 0 || trim.length() > 99) {
            return false;
        }
        Bundle c = c();
        c.putString("keyword", trim);
        c.putInt("pagenum", i2);
        c.putInt("count", this.f);
        c.putString("cityid", String.valueOf(i));
        c.putInt("level", i3);
        if (mapBound != null) {
            c.putInt("ll_x", mapBound.ptLB.getmPtx());
            c.putInt("ll_y", mapBound.ptLB.getmPty());
            c.putInt("ru_x", mapBound.ptRT.getmPtx());
            c.putInt("ru_y", mapBound.ptRT.getmPty());
        }
        if (point != null) {
            c.putInt("loc_x", point.x);
            c.putInt("loc_y", point.y);
        }
        if (map != null) {
            Bundle bundle = new Bundle();
            for (Object next : map.keySet()) {
                bundle.putString(next.toString(), map.get(next).toString());
            }
            c.putBundle("extparams", bundle);
        }
        return this.b.l(c);
    }

    public boolean a(String str, int i, String str2, MapBound mapBound, int i2, Point point) {
        if (str == null) {
            return false;
        }
        if (i != 0 && i != 2) {
            return false;
        }
        String trim = str.trim();
        if (trim.length() == 0 || trim.length() > 99) {
            return false;
        }
        Bundle c = c();
        c.putString("keyword", str);
        c.putInt("type", i);
        c.putString("cityid", str2);
        Bundle bundle = new Bundle();
        bundle.putInt("level", i2);
        c.putBundle("mapbound", bundle);
        if (point != null) {
            c.putInt("loc_x", point.x);
            c.putInt("loc_y", point.y);
        }
        return this.b.j(c);
    }

    public boolean a(String str, String str2) {
        if (str2 == null || str == null || str.equals("")) {
            return false;
        }
        String trim = str2.trim();
        return (trim.length() == 0 || trim.length() > 99) ? false : this.b.a(str, trim);
    }

    public boolean a(String str, String str2, int i, int i2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("bid", str);
        bundle.putString("wd", str2);
        bundle.putInt("currentPage", i);
        bundle.putInt("pageSize", i2);
        if (str3 != null && str3.length() > 0) {
            bundle.putString("floor", str3);
        }
        return this.b.c(bundle);
    }

    public boolean a(String str, String str2, int i, MapBound mapBound, int i2, Map<String, Object> map) {
        if (str == null) {
            return false;
        }
        String trim = str.trim();
        if (trim.length() == 0 || trim.length() > 99) {
            return false;
        }
        Bundle c = c();
        c.putString("keyword", trim);
        c.putInt("pagenum", i);
        c.putInt("count", this.f);
        c.putString("cityid", str2);
        c.putInt("level", i2);
        if (mapBound != null) {
            c.putInt("ll_x", mapBound.ptLB.getmPtx());
            c.putInt("ll_y", mapBound.ptLB.getmPty());
            c.putInt("ru_x", mapBound.ptRT.getmPtx());
            c.putInt("ru_y", mapBound.ptRT.getmPty());
        }
        if (map != null) {
            Bundle bundle = new Bundle();
            for (Object next : map.keySet()) {
                bundle.putString(next.toString(), map.get(next).toString());
            }
            c.putBundle("extparams", bundle);
        }
        return this.b.a(c);
    }

    public boolean a(String str, String str2, Bundle bundle) {
        if (str == null || str2 == null) {
            return false;
        }
        Bundle c = c();
        c.putString("start", str);
        c.putString("end", str2);
        if (bundle != null) {
            c.putBundle("extparams", bundle);
        }
        return this.b.i(c);
    }

    public int b() {
        return this.f;
    }

    String b(int i) {
        String a = this.b.a(i);
        return (a == null || a.trim().length() > 0) ? a : null;
    }

    public boolean b(String str) {
        return str == null ? false : this.b.b(str);
    }

    public boolean b(String str, String str2) {
        return this.b.b(str, str2);
    }

    public boolean c(String str, String str2) {
        return (this.b == null || TextUtils.isEmpty(str)) ? false : this.b.c(str, str2);
    }
}
