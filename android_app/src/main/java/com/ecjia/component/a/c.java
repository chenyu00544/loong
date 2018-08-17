package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.a.m;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecjia.hamster.model.ECJia_DEVICE;
import com.ecjia.hamster.model.ak;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.i;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaAddressModel */
public class c extends e {
    public ArrayList<ECJia_ADDRESS> a = new ArrayList();
    public ArrayList<ak> b = new ArrayList();
    public ArrayList<i> c = new ArrayList();
    public ArrayList<i> d = new ArrayList();
    m e = m.a();
    String f;
    public String g;
    public ECJia_ADDRESS h;
    public String i;
    Comparator j = new c_9(this);
    private ArrayList<ak> t = new ArrayList();
    private boolean u = false;
    private int v;
    private boolean w;
    private PrintStream x = null;

    /* compiled from: ECJiaAddressModel */
    class c_1 implements OnCancelListener {
        final /* synthetic */ c a;

        c_1(c cVar) {
            this.a = cVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAddressModel */
    class c_2 implements OnCancelListener {
        final /* synthetic */ c a;

        c_2(c cVar) {
            this.a = cVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAddressModel */
    class c_3 implements OnCancelListener {
        final /* synthetic */ c a;

        c_3(c cVar) {
            this.a = cVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAddressModel */
    class c_4 implements OnCancelListener {
        final /* synthetic */ c a;

        c_4(c cVar) {
            this.a = cVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAddressModel */
    class c_5 implements OnCancelListener {
        final /* synthetic */ c a;

        c_5(c cVar) {
            this.a = cVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAddressModel */
    class c_6 implements OnCancelListener {
        final /* synthetic */ c a;

        c_6(c cVar) {
            this.a = cVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAddressModel */
    class c_7 implements OnCancelListener {
        final /* synthetic */ c a;

        c_7(c cVar) {
            this.a = cVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAddressModel */
    class c_8 implements OnCancelListener {
        final /* synthetic */ c a;

        c_8(c cVar) {
            this.a = cVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAddressModel */
    class c_9 implements Comparator<i> {
        final /* synthetic */ c a;

        c_9(c cVar) {
            this.a = cVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((i) obj, (i) obj2);
        }

        public int a(i iVar, i iVar2) {
            String substring = iVar.c().substring(0, 1);
            String substring2 = iVar2.c().substring(0, 1);
            int compareTo = substring.compareTo(substring2);
            if (compareTo == 0) {
                return substring.compareTo(substring2);
            }
            return compareTo;
        }
    }

    public c(Context context) {
        super(context);
        this.s.a((b) this);
        this.f = this.n.getPackageName();
        this.g = context.getCacheDir() + "/ECJia/cache";
        if (j.a() != null) {
            this.t = j.a().c;
        }
    }

    public void a() {
        ap c = ap.c();
        this.l.show();
        this.q = "address/list";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new c_1(this));
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.q = "address/add";
        ap c = ap.c();
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        ECJia_ADDRESS eCJia_ADDRESS = new ECJia_ADDRESS();
        eCJia_ADDRESS.setConsignee(str);
        eCJia_ADDRESS.setTel(str2);
        eCJia_ADDRESS.setEmail(str3);
        eCJia_ADDRESS.setMobile(str4);
        eCJia_ADDRESS.setZipcode(str5);
        eCJia_ADDRESS.setAddress(str6);
        eCJia_ADDRESS.setCountry(str7);
        eCJia_ADDRESS.setProvince(str8);
        eCJia_ADDRESS.setCity(str9);
        eCJia_ADDRESS.setDistrict(str10);
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("address", eCJia_ADDRESS.toJson());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new c_2(this));
    }

    public void a(String str, int i) {
        this.w = false;
        ap c = ap.c();
        this.l.show();
        this.q = "shop/region";
        this.v = i;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("parent_id", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new c_3(this));
    }

    public void a(String str) {
        this.w = true;
        this.d.clear();
        if (this.t.size() > 0) {
            for (int i = 0; i < this.t.size(); i++) {
                i iVar = new i();
                iVar.a(((ak) this.t.get(i)).a());
                m mVar = this.e;
                iVar.e(m.b(((ak) this.t.get(i)).b()));
                iVar.b("1");
                iVar.d(((ak) this.t.get(i)).b());
                iVar.c(((ak) this.t.get(i)).c());
                if (((ak) this.t.get(i)).b().contains("重庆")) {
                    iVar.e("chongqing");
                }
                this.d.add(iVar);
                Collections.sort(this.d, this.j);
            }
        }
        if (this.c.size() > 0) {
            ax axVar = new ax();
            axVar.b(1);
            a("shop/region", "", axVar);
            return;
        }
        ap c = ap.c();
        this.l.show();
        this.q = "shop/region";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("type", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new c_4(this));
    }

    public void b(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "address/info";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("address_id", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new c_5(this));
    }

    public void c(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "address/setDefault";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("address_id", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new c_6(this));
    }

    public void d(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "address/delete";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("address_id", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new c_7(this));
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        ap c = ap.c();
        this.l.show();
        this.q = "address/update";
        JSONObject jSONObject = new JSONObject();
        ECJia_ADDRESS eCJia_ADDRESS = new ECJia_ADDRESS();
        eCJia_ADDRESS.setConsignee(str2);
        eCJia_ADDRESS.setTel(str3);
        eCJia_ADDRESS.setEmail(str4);
        eCJia_ADDRESS.setMobile(str5);
        eCJia_ADDRESS.setZipcode(str6);
        eCJia_ADDRESS.setAddress(str7);
        eCJia_ADDRESS.setCountry(str8);
        eCJia_ADDRESS.setProvince(str9);
        eCJia_ADDRESS.setCity(str10);
        eCJia_ADDRESS.setDistrict(str11);
        try {
            jSONObject.put("session", c.d());
            jSONObject.put("address_id", str);
            jSONObject.put("address", eCJia_ADDRESS.toJson());
            jSONObject.put("device", ECJia_DEVICE.getInstance().toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new c_8(this));
    }

    public void b(String str, String str2) {
        File file = new File(this.g + "/" + this.f);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(file + "/" + str2 + ".dat"));
            this.x = new PrintStream(fileOutputStream);
            this.x.print(str);
            this.x.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r9, java.lang.String r10) {
        /*
        r8 = this;
        r0 = 0;
        r2 = 1;
        super.a(r9, r10);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00e1 }
        r3.<init>(r10);	 Catch:{ JSONException -> 0x00e1 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00e1 }
        r1.<init>();	 Catch:{ JSONException -> 0x00e1 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00e1 }
        r1 = r1.append(r9);	 Catch:{ JSONException -> 0x00e1 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00e1 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00e1 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00e1 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00e1 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00e1 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00e1 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00e1 }
        r8.r = r1;	 Catch:{ JSONException -> 0x00e1 }
        r1 = -1;
        r4 = r9.hashCode();	 Catch:{ JSONException -> 0x00e1 }
        switch(r4) {
            case -1378396154: goto L_0x005b;
            case 219640297: goto L_0x006f;
            case 219725273: goto L_0x0051;
            case 469643302: goto L_0x0083;
            case 936006778: goto L_0x008d;
            case 966255428: goto L_0x0079;
            case 1531907117: goto L_0x0065;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00e1 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0097;
            case 1: goto L_0x0045;
            case 2: goto L_0x0107;
            case 3: goto L_0x01f8;
            case 4: goto L_0x0045;
            case 5: goto L_0x0045;
            case 6: goto L_0x020e;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00e1 }
    L_0x0045:
        r8.g();	 Catch:{ JSONException -> 0x00e1 }
        r0 = r8.r;	 Catch:{ JSONException -> 0x00e1 }
        r8.a(r9, r10, r0);	 Catch:{ JSONException -> 0x00e1 }
    L_0x004d:
        r8.e(r10);
        return;
    L_0x0051:
        r4 = "address/list";
        r4 = r9.equals(r4);	 Catch:{ JSONException -> 0x00e1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "address/add";
        r4 = r9.equals(r4);	 Catch:{ JSONException -> 0x00e1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "shop/region";
        r4 = r9.equals(r4);	 Catch:{ JSONException -> 0x00e1 }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r4 = "address/info";
        r4 = r9.equals(r4);	 Catch:{ JSONException -> 0x00e1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0077:
        r1 = 3;
        goto L_0x0042;
    L_0x0079:
        r4 = "address/update";
        r4 = r9.equals(r4);	 Catch:{ JSONException -> 0x00e1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0081:
        r1 = 4;
        goto L_0x0042;
    L_0x0083:
        r4 = "address/delete";
        r4 = r9.equals(r4);	 Catch:{ JSONException -> 0x00e1 }
        if (r4 == 0) goto L_0x0042;
    L_0x008b:
        r1 = 5;
        goto L_0x0042;
    L_0x008d:
        r4 = "address/setDefault";
        r4 = r9.equals(r4);	 Catch:{ JSONException -> 0x00e1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0095:
        r1 = 6;
        goto L_0x0042;
    L_0x0097:
        r1 = r8.r;	 Catch:{ JSONException -> 0x00e1 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00e1 }
        if (r1 != r2) goto L_0x0045;
    L_0x009f:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00e1 }
        if (r1 == 0) goto L_0x0045;
    L_0x00a7:
        r3 = r1.length();	 Catch:{ JSONException -> 0x00e1 }
        if (r3 <= 0) goto L_0x0045;
    L_0x00ad:
        r3 = r8.a;	 Catch:{ JSONException -> 0x00e1 }
        r3.clear();	 Catch:{ JSONException -> 0x00e1 }
    L_0x00b2:
        r3 = r1.length();	 Catch:{ JSONException -> 0x00e1 }
        if (r0 >= r3) goto L_0x0045;
    L_0x00b8:
        r3 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00e1 }
        r3 = com.ecjia.hamster.model.ECJia_ADDRESS.fromJson(r3);	 Catch:{ JSONException -> 0x00e1 }
        r4 = r3.getDefault_address();	 Catch:{ JSONException -> 0x00e1 }
        if (r4 != r2) goto L_0x00cf;
    L_0x00c6:
        r4 = r8.a;	 Catch:{ JSONException -> 0x00e1 }
        r5 = 0;
        r4.add(r5, r3);	 Catch:{ JSONException -> 0x00e1 }
    L_0x00cc:
        r0 = r0 + 1;
        goto L_0x00b2;
    L_0x00cf:
        r4 = r3.getProvince_name();	 Catch:{ JSONException -> 0x00e1 }
        r5 = "null";
        r4 = r4.equals(r5);	 Catch:{ JSONException -> 0x00e1 }
        if (r4 != 0) goto L_0x00cc;
    L_0x00db:
        r4 = r8.a;	 Catch:{ JSONException -> 0x00e1 }
        r4.add(r3);	 Catch:{ JSONException -> 0x00e1 }
        goto L_0x00cc;
    L_0x00e1:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r9);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r10);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004d;
    L_0x0107:
        r1 = r8.w;	 Catch:{ JSONException -> 0x00e1 }
        if (r1 == 0) goto L_0x01b3;
    L_0x010b:
        r1 = r8.r;	 Catch:{ JSONException -> 0x00e1 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00e1 }
        if (r1 != r2) goto L_0x0045;
    L_0x0113:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00e1 }
        r2 = "regions";
        r2 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00e1 }
        r1 = r8.b;	 Catch:{ JSONException -> 0x00e1 }
        r1.clear();	 Catch:{ JSONException -> 0x00e1 }
        if (r2 == 0) goto L_0x0045;
    L_0x0126:
        r1 = r2.length();	 Catch:{ JSONException -> 0x00e1 }
        if (r1 <= 0) goto L_0x0045;
    L_0x012c:
        r3 = r2.length();	 Catch:{ JSONException -> 0x00e1 }
        r1 = r0;
    L_0x0131:
        if (r1 >= r3) goto L_0x0187;
    L_0x0133:
        r4 = r2.getJSONObject(r1);	 Catch:{ JSONException -> 0x00e1 }
        r4 = com.ecjia.hamster.model.ak.a(r4);	 Catch:{ JSONException -> 0x00e1 }
        r5 = new com.ecjia.hamster.model.i;	 Catch:{ JSONException -> 0x00e1 }
        r5.<init>();	 Catch:{ JSONException -> 0x00e1 }
        r6 = r4.a();	 Catch:{ JSONException -> 0x00e1 }
        r5.a(r6);	 Catch:{ JSONException -> 0x00e1 }
        r6 = r8.e;	 Catch:{ JSONException -> 0x00e1 }
        r6 = r4.b();	 Catch:{ JSONException -> 0x00e1 }
        r6 = com.ecjia.a.m.b(r6);	 Catch:{ JSONException -> 0x00e1 }
        r5.e(r6);	 Catch:{ JSONException -> 0x00e1 }
        r6 = r4.b();	 Catch:{ JSONException -> 0x00e1 }
        r7 = "重庆";
        r6 = r6.contains(r7);	 Catch:{ JSONException -> 0x00e1 }
        if (r6 == 0) goto L_0x0165;
    L_0x0160:
        r6 = "chongqing";
        r5.e(r6);	 Catch:{ JSONException -> 0x00e1 }
    L_0x0165:
        r6 = r4.c();	 Catch:{ JSONException -> 0x00e1 }
        r5.c(r6);	 Catch:{ JSONException -> 0x00e1 }
        r4 = r4.b();	 Catch:{ JSONException -> 0x00e1 }
        r5.d(r4);	 Catch:{ JSONException -> 0x00e1 }
        r4 = "0";
        r5.b(r4);	 Catch:{ JSONException -> 0x00e1 }
        r4 = r8.c;	 Catch:{ JSONException -> 0x00e1 }
        r4.add(r5);	 Catch:{ JSONException -> 0x00e1 }
        r4 = r8.c;	 Catch:{ JSONException -> 0x00e1 }
        r5 = r8.j;	 Catch:{ JSONException -> 0x00e1 }
        java.util.Collections.sort(r4, r5);	 Catch:{ JSONException -> 0x00e1 }
        r1 = r1 + 1;
        goto L_0x0131;
    L_0x0187:
        r2 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x00e1 }
        r2.<init>();	 Catch:{ JSONException -> 0x00e1 }
        r1 = r8.c;	 Catch:{ JSONException -> 0x00e1 }
        r3 = r1.size();	 Catch:{ JSONException -> 0x00e1 }
        r1 = r0;
    L_0x0193:
        if (r1 >= r3) goto L_0x01a8;
    L_0x0195:
        r0 = r8.c;	 Catch:{ JSONException -> 0x00e1 }
        r0 = r0.get(r1);	 Catch:{ JSONException -> 0x00e1 }
        r0 = (com.ecjia.hamster.model.i) r0;	 Catch:{ JSONException -> 0x00e1 }
        r0 = r0.d();	 Catch:{ JSONException -> 0x00e1 }
        r2.put(r0);	 Catch:{ JSONException -> 0x00e1 }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0193;
    L_0x01a8:
        r0 = r2.toString();	 Catch:{ JSONException -> 0x00e1 }
        r1 = "citylistData";
        r8.b(r0, r1);	 Catch:{ JSONException -> 0x00e1 }
        goto L_0x0045;
    L_0x01b3:
        r1 = r8.r;	 Catch:{ JSONException -> 0x00e1 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00e1 }
        if (r1 != r2) goto L_0x01ef;
    L_0x01bb:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00e1 }
        r2 = "regions";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00e1 }
        r2 = r8.b;	 Catch:{ JSONException -> 0x00e1 }
        r2.clear();	 Catch:{ JSONException -> 0x00e1 }
        if (r1 == 0) goto L_0x01ef;
    L_0x01ce:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00e1 }
        if (r2 <= 0) goto L_0x01ef;
    L_0x01d4:
        r2 = r8.b;	 Catch:{ JSONException -> 0x00e1 }
        r2.clear();	 Catch:{ JSONException -> 0x00e1 }
    L_0x01d9:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00e1 }
        if (r0 >= r2) goto L_0x01ef;
    L_0x01df:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00e1 }
        r2 = com.ecjia.hamster.model.ak.a(r2);	 Catch:{ JSONException -> 0x00e1 }
        r3 = r8.b;	 Catch:{ JSONException -> 0x00e1 }
        r3.add(r2);	 Catch:{ JSONException -> 0x00e1 }
        r0 = r0 + 1;
        goto L_0x01d9;
    L_0x01ef:
        r0 = r8.r;	 Catch:{ JSONException -> 0x00e1 }
        r1 = r8.v;	 Catch:{ JSONException -> 0x00e1 }
        r0.a(r1);	 Catch:{ JSONException -> 0x00e1 }
        goto L_0x0045;
    L_0x01f8:
        r0 = r8.r;	 Catch:{ JSONException -> 0x00e1 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00e1 }
        if (r0 != r2) goto L_0x0045;
    L_0x0200:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00e1 }
        r0 = com.ecjia.hamster.model.ECJia_ADDRESS.fromJson(r0);	 Catch:{ JSONException -> 0x00e1 }
        r8.h = r0;	 Catch:{ JSONException -> 0x00e1 }
        goto L_0x0045;
    L_0x020e:
        r0 = r8.r;	 Catch:{ JSONException -> 0x00e1 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00e1 }
        if (r0 != r2) goto L_0x0045;
    L_0x0216:
        r0 = "address_id";
        r0 = r3.optString(r0);	 Catch:{ JSONException -> 0x00e1 }
        r8.i = r0;	 Catch:{ JSONException -> 0x00e1 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.c.a(java.lang.String, java.lang.String):void");
    }
}
