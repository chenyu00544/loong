package com.ecjia.hamster.module.goodsReturn;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;
import com.ecjia.component.a.a.b;
import com.ecjia.component.a.e;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_DETAIL;
import com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_REASON;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaReturnModel */
public class a extends e {
    public String a;
    public String b;
    public ECJia_RETURN_DETAIL c;
    public aa d;
    public ArrayList<com.ecjia.hamster.module.goodsReturn.model.a> e = new ArrayList();
    public ArrayList<ECJia_RETURN_REASON> f = new ArrayList();
    private final int g = 8;
    private boolean h;

    /* compiled from: ECJiaReturnModel */
    class a_1 implements OnCancelListener {
        final /* synthetic */ a a;

        a_1(a aVar) {
            this.a = aVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaReturnModel */
    class a_2 implements OnCancelListener {
        final /* synthetic */ a a;

        a_2(a aVar) {
            this.a = aVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaReturnModel */
    class a_3 implements OnCancelListener {
        final /* synthetic */ a a;

        a_3(a aVar) {
            this.a = aVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaReturnModel */
    class a_4 implements OnCancelListener {
        final /* synthetic */ a a;

        a_4(a aVar) {
            this.a = aVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaReturnModel */
    class a_5 implements OnCancelListener {
        final /* synthetic */ a a;

        a_5(a aVar) {
            this.a = aVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public a(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str, String str2, String str3, String str4, String str5, ArrayList<String> arrayList, ECJia_ADDRESS eCJia_ADDRESS) {
        this.q = "order/return/apply";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            if (eCJia_ADDRESS != null) {
                jSONObject.put("consignee", eCJia_ADDRESS.getConsignee());
                jSONObject.put("mobile", eCJia_ADDRESS.getMobile());
                jSONObject.put(x.G, eCJia_ADDRESS.getCountry());
                jSONObject.put("province", eCJia_ADDRESS.getProvince());
                jSONObject.put("city", eCJia_ADDRESS.getCity());
                jSONObject.put("district", eCJia_ADDRESS.getDistrict());
                jSONObject.put("address", eCJia_ADDRESS.getAddress());
            }
            jSONObject.put("rec_id", str2);
            jSONObject.put("reason_id", str3);
            jSONObject.put("return_description", str4);
            jSONObject.put("number", str5);
            jSONObject.put("return_type", str);
            jSONObject.put("return_images ", "upload_imgs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.a(this.q, jSONObject.toString(), (ArrayList) arrayList);
        this.l.setOnCancelListener(new a_1(this));
    }

    public void a(String str) {
        this.q = "order/return/cancel";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("return_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new a_2(this));
    }

    public void b(String str) {
        this.q = "order/return/detail";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("return_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new a_3(this));
    }

    public void c(String str) {
        this.h = true;
        this.q = "order/return/list";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("keywords", str);
            }
            ab abVar = new ab();
            abVar.b(1);
            abVar.a(8);
            jSONObject.put("pagination", abVar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new a_4(this));
    }

    public void d(String str) {
        this.h = false;
        this.q = "order/return/list";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("keywords", str);
            }
            ab abVar = new ab();
            abVar.b((this.e.size() / 8) + 1);
            abVar.a(8);
            jSONObject.put("pagination", abVar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a() {
        this.q = "order/return/reason";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new a_5(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r0 = 0;
        r2 = 1;
        super.a(r7, r8);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00ac }
        r3.<init>(r8);	 Catch:{ JSONException -> 0x00ac }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00ac }
        r1.<init>();	 Catch:{ JSONException -> 0x00ac }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ac }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x00ac }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ac }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00ac }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ac }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00ac }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00ac }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00ac }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00ac }
        r6.r = r1;	 Catch:{ JSONException -> 0x00ac }
        r1 = -1;
        r4 = r7.hashCode();	 Catch:{ JSONException -> 0x00ac }
        switch(r4) {
            case -410394404: goto L_0x006f;
            case 152722160: goto L_0x0051;
            case 482756408: goto L_0x005b;
            case 515256591: goto L_0x0065;
            case 915516162: goto L_0x0079;
            case 1050675924: goto L_0x0083;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00ac }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x008d;
            case 1: goto L_0x0045;
            case 2: goto L_0x00d2;
            case 3: goto L_0x00e8;
            case 4: goto L_0x012b;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00ac }
    L_0x0045:
        r6.g();	 Catch:{ JSONException -> 0x00ac }
        r0 = r6.r;	 Catch:{ JSONException -> 0x00ac }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x00ac }
    L_0x004d:
        r6.e(r8);
        return;
    L_0x0051:
        r4 = "order/return/apply";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00ac }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "order/return/cancel";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00ac }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "order/return/detail";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00ac }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r4 = "order/return/list";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00ac }
        if (r4 == 0) goto L_0x0042;
    L_0x0077:
        r1 = 3;
        goto L_0x0042;
    L_0x0079:
        r4 = "order/return/reason";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00ac }
        if (r4 == 0) goto L_0x0042;
    L_0x0081:
        r1 = 4;
        goto L_0x0042;
    L_0x0083:
        r4 = "order/return/activation";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00ac }
        if (r4 == 0) goto L_0x0042;
    L_0x008b:
        r1 = 5;
        goto L_0x0042;
    L_0x008d:
        r0 = r6.r;	 Catch:{ JSONException -> 0x00ac }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00ac }
        if (r0 != r2) goto L_0x0045;
    L_0x0095:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ac }
        r1 = "return_id";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00ac }
        r6.b = r1;	 Catch:{ JSONException -> 0x00ac }
        r1 = "apply_time";
        r0 = r0.optString(r1);	 Catch:{ JSONException -> 0x00ac }
        r6.a = r0;	 Catch:{ JSONException -> 0x00ac }
        goto L_0x0045;
    L_0x00ac:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004d;
    L_0x00d2:
        r0 = r6.r;	 Catch:{ JSONException -> 0x00ac }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00ac }
        if (r0 != r2) goto L_0x0045;
    L_0x00da:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ac }
        r0 = com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_DETAIL.fromJson(r0);	 Catch:{ JSONException -> 0x00ac }
        r6.c = r0;	 Catch:{ JSONException -> 0x00ac }
        goto L_0x0045;
    L_0x00e8:
        r1 = r6.r;	 Catch:{ JSONException -> 0x00ac }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00ac }
        if (r1 != r2) goto L_0x011d;
    L_0x00f0:
        r1 = r6.h;	 Catch:{ JSONException -> 0x00ac }
        if (r1 == 0) goto L_0x00f9;
    L_0x00f4:
        r1 = r6.e;	 Catch:{ JSONException -> 0x00ac }
        r1.clear();	 Catch:{ JSONException -> 0x00ac }
    L_0x00f9:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00ac }
        if (r1 == 0) goto L_0x011d;
    L_0x0101:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ac }
        if (r2 <= 0) goto L_0x011d;
    L_0x0107:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ac }
    L_0x010b:
        if (r0 >= r2) goto L_0x011d;
    L_0x010d:
        r4 = r6.e;	 Catch:{ JSONException -> 0x00ac }
        r5 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ac }
        r5 = com.ecjia.hamster.module.goodsReturn.model.a.a(r5);	 Catch:{ JSONException -> 0x00ac }
        r4.add(r5);	 Catch:{ JSONException -> 0x00ac }
        r0 = r0 + 1;
        goto L_0x010b;
    L_0x011d:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ac }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00ac }
        r6.d = r0;	 Catch:{ JSONException -> 0x00ac }
        goto L_0x0045;
    L_0x012b:
        r1 = r6.r;	 Catch:{ JSONException -> 0x00ac }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00ac }
        if (r1 != r2) goto L_0x0045;
    L_0x0133:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00ac }
        if (r1 == 0) goto L_0x0045;
    L_0x013b:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ac }
        if (r2 <= 0) goto L_0x0045;
    L_0x0141:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ac }
    L_0x0145:
        if (r0 >= r2) goto L_0x0045;
    L_0x0147:
        r3 = r6.f;	 Catch:{ JSONException -> 0x00ac }
        r4 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ac }
        r4 = com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_REASON.fromJson(r4);	 Catch:{ JSONException -> 0x00ac }
        r3.add(r4);	 Catch:{ JSONException -> 0x00ac }
        r0 = r0 + 1;
        goto L_0x0145;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.hamster.module.goodsReturn.a.a(java.lang.String, java.lang.String):void");
    }
}
