package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.an;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.bb;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaTopicModel */
public class aj extends e {
    public aa a;
    public ArrayList<bb> b = new ArrayList();
    public ArrayList<an> c = new ArrayList();
    public bb d;
    private boolean e;

    /* compiled from: ECJiaTopicModel */
    class aj_1 implements OnCancelListener {
        final /* synthetic */ aj a;

        aj_1(aj ajVar) {
            this.a = ajVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaTopicModel */
    class aj_2 implements OnCancelListener {
        final /* synthetic */ aj a;

        aj_2(aj ajVar) {
            this.a = ajVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public aj(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a() {
        this.e = true;
        this.q = "topic/list";
        this.l.show();
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new aj_1(this));
    }

    public void b() {
        this.e = false;
        this.q = "topic/list";
        ab abVar = new ab();
        abVar.b((this.b.size() / 8) + 1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a(String str, String str2, String str3) {
        this.e = true;
        this.q = "topic/info";
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(8);
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("topic_id", str);
            jSONObject.put("type", str2);
            jSONObject.put("sort_by", str3);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new aj_2(this));
    }

    public void b(String str, String str2, String str3) {
        this.e = false;
        this.q = "topic/info";
        ab abVar = new ab();
        abVar.b((this.c.size() / 8) + 1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("topic_id", str);
            jSONObject.put("type", str2);
            jSONObject.put("sort_by", str3);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r2 = 1;
        r0 = 0;
        super.a(r6, r7);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00a7 }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x00a7 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00a7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00a7 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x00a7 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a7 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00a7 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00a7 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00a7 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00a7 }
        r5.r = r1;	 Catch:{ JSONException -> 0x00a7 }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x00a7 }
        switch(r4) {
            case -878916210: goto L_0x005b;
            case -878831234: goto L_0x0051;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00a7 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0065;
            case 1: goto L_0x00cc;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00a7 }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x00a7 }
        r0 = r5.r;	 Catch:{ JSONException -> 0x00a7 }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x00a7 }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "topic/list";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00a7 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "topic/info";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00a7 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00a7 }
        if (r1 != r2) goto L_0x009a;
    L_0x006d:
        r1 = r5.e;	 Catch:{ JSONException -> 0x00a7 }
        if (r1 == 0) goto L_0x0076;
    L_0x0071:
        r1 = r5.b;	 Catch:{ JSONException -> 0x00a7 }
        r1.clear();	 Catch:{ JSONException -> 0x00a7 }
    L_0x0076:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00a7 }
        if (r1 == 0) goto L_0x009a;
    L_0x007e:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00a7 }
        if (r2 <= 0) goto L_0x009a;
    L_0x0084:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00a7 }
        if (r0 >= r2) goto L_0x009a;
    L_0x008a:
        r2 = r5.b;	 Catch:{ JSONException -> 0x00a7 }
        r4 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a7 }
        r4 = com.ecjia.hamster.model.bb.a(r4);	 Catch:{ JSONException -> 0x00a7 }
        r2.add(r4);	 Catch:{ JSONException -> 0x00a7 }
        r0 = r0 + 1;
        goto L_0x0084;
    L_0x009a:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a7 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00a7 }
        r5.a = r0;	 Catch:{ JSONException -> 0x00a7 }
        goto L_0x0045;
    L_0x00a7:
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
    L_0x00cc:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00a7 }
        if (r1 != r2) goto L_0x0117;
    L_0x00d4:
        r1 = r5.e;	 Catch:{ JSONException -> 0x00a7 }
        if (r1 == 0) goto L_0x00dd;
    L_0x00d8:
        r1 = r5.c;	 Catch:{ JSONException -> 0x00a7 }
        r1.clear();	 Catch:{ JSONException -> 0x00a7 }
    L_0x00dd:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00a7 }
        r1 = com.ecjia.hamster.model.bb.a(r1);	 Catch:{ JSONException -> 0x00a7 }
        r5.d = r1;	 Catch:{ JSONException -> 0x00a7 }
        r1 = r5.d;	 Catch:{ JSONException -> 0x00a7 }
        if (r1 == 0) goto L_0x0117;
    L_0x00ed:
        r1 = r5.d;	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.e();	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.size();	 Catch:{ JSONException -> 0x00a7 }
        if (r1 <= 0) goto L_0x0117;
    L_0x00f9:
        r1 = r5.d;	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.e();	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.size();	 Catch:{ JSONException -> 0x00a7 }
    L_0x0103:
        if (r0 >= r1) goto L_0x0117;
    L_0x0105:
        r2 = r5.c;	 Catch:{ JSONException -> 0x00a7 }
        r4 = r5.d;	 Catch:{ JSONException -> 0x00a7 }
        r4 = r4.e();	 Catch:{ JSONException -> 0x00a7 }
        r4 = r4.get(r0);	 Catch:{ JSONException -> 0x00a7 }
        r2.add(r4);	 Catch:{ JSONException -> 0x00a7 }
        r0 = r0 + 1;
        goto L_0x0103;
    L_0x0117:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a7 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00a7 }
        r5.a = r0;	 Catch:{ JSONException -> 0x00a7 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.aj.a(java.lang.String, java.lang.String):void");
    }
}
