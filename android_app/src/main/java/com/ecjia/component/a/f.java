package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.baidu.mapapi.map.WeightedLatLng;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.h;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaCheckInModel */
public class f extends e {
    public int a = 1;
    public int b = 0;
    public int c = 1;
    public int d = 0;
    public String e = "";
    public ArrayList<h> f = new ArrayList();
    public ArrayList<h> g = new ArrayList();
    public aa h;
    private String i;
    private boolean j;

    /* compiled from: ECJiaCheckInModel */
    class f_1 implements OnCancelListener {
        final /* synthetic */ f a;

        f_1(f fVar) {
            this.a = fVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCheckInModel */
    class f_2 implements OnCancelListener {
        final /* synthetic */ f a;

        f_2(f fVar) {
            this.a = fVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCheckInModel */
    class f_3 implements OnCancelListener {
        final /* synthetic */ f a;

        f_3(f fVar) {
            this.a = fVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public f(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "mobile/checkin";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("filite_user", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new f_1(this));
    }

    public void b(String str) {
        this.j = true;
        this.l.show();
        this.q = "mobile/checkin/record";
        this.i = str;
        ap c = ap.c();
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("filite_user", str);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new f_2(this));
    }

    public void a() {
        this.j = true;
        this.q = "mobile/checkin/record";
        this.i = "all";
        ap c = ap.c();
        ab abVar = new ab();
        abVar.b(((int) Math.ceil((((double) this.g.size()) * WeightedLatLng.DEFAULT_INTENSITY) / 8.0d)) + 1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("filite_user", "all");
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new f_3(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r2 = 1;
        r0 = 0;
        super.a(r6, r7);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00d6 }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x00d6 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00d6 }
        r1.<init>();	 Catch:{ JSONException -> 0x00d6 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00d6 }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x00d6 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00d6 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00d6 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00d6 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00d6 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00d6 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00d6 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00d6 }
        r5.r = r1;	 Catch:{ JSONException -> 0x00d6 }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x00d6 }
        switch(r4) {
            case -1804486272: goto L_0x005b;
            case 1721686976: goto L_0x0051;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00d6 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0045;
            case 1: goto L_0x0065;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00d6 }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x00d6 }
        r0 = r5.r;	 Catch:{ JSONException -> 0x00d6 }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x00d6 }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "mobile/checkin";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d6 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "mobile/checkin/record";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d6 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00d6 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00d6 }
        if (r1 != r2) goto L_0x0045;
    L_0x006d:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00d6 }
        r2 = r5.i;	 Catch:{ JSONException -> 0x00d6 }
        r4 = "current";
        r2 = r2.equals(r4);	 Catch:{ JSONException -> 0x00d6 }
        if (r2 == 0) goto L_0x00fc;
    L_0x007d:
        r2 = "checkin_award_open";
        r2 = r1.optInt(r2);	 Catch:{ JSONException -> 0x00d6 }
        r5.a = r2;	 Catch:{ JSONException -> 0x00d6 }
        r2 = "checkin_day";
        r2 = r1.optInt(r2);	 Catch:{ JSONException -> 0x00d6 }
        r5.c = r2;	 Catch:{ JSONException -> 0x00d6 }
        r2 = "checkin_award";
        r2 = r1.optInt(r2);	 Catch:{ JSONException -> 0x00d6 }
        r5.b = r2;	 Catch:{ JSONException -> 0x00d6 }
        r2 = "checkin_extra_award";
        r2 = r1.optInt(r2);	 Catch:{ JSONException -> 0x00d6 }
        r5.d = r2;	 Catch:{ JSONException -> 0x00d6 }
        r2 = "lable_checkin_extra_award";
        r2 = r1.optString(r2);	 Catch:{ JSONException -> 0x00d6 }
        r5.e = r2;	 Catch:{ JSONException -> 0x00d6 }
        r2 = "checkin_record";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00d6 }
        if (r1 == 0) goto L_0x00ce;
    L_0x00ad:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d6 }
        if (r2 <= 0) goto L_0x00ce;
    L_0x00b3:
        r2 = r5.f;	 Catch:{ JSONException -> 0x00d6 }
        r2.clear();	 Catch:{ JSONException -> 0x00d6 }
    L_0x00b8:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d6 }
        if (r0 >= r2) goto L_0x00ce;
    L_0x00be:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00d6 }
        r2 = com.ecjia.hamster.model.h.a(r2);	 Catch:{ JSONException -> 0x00d6 }
        r3 = r5.f;	 Catch:{ JSONException -> 0x00d6 }
        r3.add(r2);	 Catch:{ JSONException -> 0x00d6 }
        r0 = r0 + 1;
        goto L_0x00b8;
    L_0x00ce:
        r0 = r5.r;	 Catch:{ JSONException -> 0x00d6 }
        r1 = 0;
        r0.a(r1);	 Catch:{ JSONException -> 0x00d6 }
        goto L_0x0045;
    L_0x00d6:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r6);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004d;
    L_0x00fc:
        r2 = r5.i;	 Catch:{ JSONException -> 0x00d6 }
        r4 = "all";
        r2 = r2.equals(r4);	 Catch:{ JSONException -> 0x00d6 }
        if (r2 == 0) goto L_0x0045;
    L_0x0106:
        r2 = "checkin_record";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00d6 }
        if (r1 == 0) goto L_0x0133;
    L_0x010e:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d6 }
        if (r2 <= 0) goto L_0x0133;
    L_0x0114:
        r2 = r5.j;	 Catch:{ JSONException -> 0x00d6 }
        if (r2 == 0) goto L_0x011d;
    L_0x0118:
        r2 = r5.g;	 Catch:{ JSONException -> 0x00d6 }
        r2.clear();	 Catch:{ JSONException -> 0x00d6 }
    L_0x011d:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d6 }
        if (r0 >= r2) goto L_0x0133;
    L_0x0123:
        r2 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00d6 }
        r2 = com.ecjia.hamster.model.h.a(r2);	 Catch:{ JSONException -> 0x00d6 }
        r4 = r5.g;	 Catch:{ JSONException -> 0x00d6 }
        r4.add(r2);	 Catch:{ JSONException -> 0x00d6 }
        r0 = r0 + 1;
        goto L_0x011d;
    L_0x0133:
        r0 = r5.r;	 Catch:{ JSONException -> 0x00d6 }
        r1 = 1;
        r0.a(r1);	 Catch:{ JSONException -> 0x00d6 }
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00d6 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00d6 }
        r5.h = r0;	 Catch:{ JSONException -> 0x00d6 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.f.a(java.lang.String, java.lang.String):void");
    }
}
