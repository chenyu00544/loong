package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecjia.hamster.model.ECJia_BONUS;
import com.ecjia.hamster.model.ECJia_GOODS_LIST;
import com.ecjia.hamster.model.ECJia_NEWGOODITEM;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecjia.hamster.model.ECJia_STOREGOODSLIST;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.be;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaShoppingCartModel */
public class ah extends e {
    public ArrayList<ECJia_PAYMENT> A = new ArrayList();
    public ArrayList<ECJia_PAYMENT> B = new ArrayList();
    String C;
    public String D;
    public JSONObject E;
    public JSONObject F;
    public JSONObject G;
    public JSONObject H;
    ArrayList<String> I = new ArrayList();
    boolean J;
    public String K;
    private int L;
    private int M;
    private PrintStream N = null;
    public be a;
    public ECJia_ADDRESS b;
    public ArrayList<ECJia_GOODS_LIST> c = new ArrayList();
    public ArrayList<ECJia_STOREGOODSLIST> d = new ArrayList();
    public String e;
    public String f;
    public String g;
    public String h;
    public ArrayList<ECJia_BONUS> i = new ArrayList();
    public int j;
    public long t;
    public long u;
    public ArrayList<ECJia_GOODS_LIST> v = new ArrayList();
    public String w;
    public boolean x;
    public ArrayList<ECJia_NEWGOODITEM> y = new ArrayList();
    public ArrayList<ECJia_PAYMENT> z = new ArrayList();

    /* compiled from: ECJiaShoppingCartModel */
    class ah_10 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_10(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_1 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_1(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_2 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_2(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_3 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_3(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_4 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_4(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_5 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_5(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_6 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_6(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_7 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_7(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_8 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_8(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaShoppingCartModel */
    class ah_9 implements OnCancelListener {
        final /* synthetic */ ah a;

        ah_9(ah ahVar) {
            this.a = ahVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public ah(Context context) {
        super(context);
        this.s.a((b) this);
        this.C = this.n.getPackageName();
        this.D = context.getCacheDir() + "/ECJia/cache";
    }

    public void a(boolean z) {
        this.q = "cart/list";
        if (z) {
            this.l.show();
            this.l.onWindowFocusChanged(false);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("area_id", e());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_1(this));
    }

    public void a(List<String> list, int i) {
        this.q = "cart/list";
        this.l.show();
        this.l.onWindowFocusChanged(false);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("area_id", e());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i2 = 0; i2 < list.size(); i2++) {
                stringBuilder.append((String) list.get(i2));
                if (i2 < list.size() - 1) {
                    stringBuilder.append(",");
                }
            }
            jSONObject.put("rec_id", stringBuilder.toString());
            jSONObject.put("is_checked", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_3(this));
    }

    public void a(String str, int i) {
        this.q = "cart/list";
        this.l.show();
        this.l.onWindowFocusChanged(false);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("area_id", e());
            jSONObject.put("rec_id", str);
            jSONObject.put("is_checked", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_4(this));
    }

    public void a(ArrayList<String> arrayList) {
        this.q = "cart/delete";
        this.l.show();
        this.l.onWindowFocusChanged(false);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                stringBuilder.append((String) arrayList.get(i));
                if (i < arrayList.size() - 1) {
                    stringBuilder.append(",");
                }
            }
            jSONObject.put("rec_id", stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_5(this));
    }

    public void a(String str) {
        this.q = "cart/delete";
        this.l.show();
        this.l.onWindowFocusChanged(false);
        JSONObject jSONObject = new JSONObject();
        this.J = true;
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("rec_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_6(this));
    }

    public void b(String str, int i) {
        this.q = "cart/update";
        this.l.show();
        this.l.onWindowFocusChanged(false);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("rec_id", str);
            jSONObject.put("new_number", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_7(this));
    }

    public void b(String str, String str2) {
        this.q = "flow/checkOrder";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("rec_id", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("address_id", str2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_8(this));
    }

    public void a(String str, String str2, String str3, JSONArray jSONArray, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.q = "flow/done";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("rec_id", str);
            }
            jSONObject.put("pay_id", str2);
            jSONObject.put("address_id", str3);
            jSONObject.put("shipping_id", jSONArray);
            if (str4 != null) {
                jSONObject.put("bonus", str4);
            }
            if (str5 != null) {
                jSONObject.put("integral", str5);
            }
            if (str6 != null) {
                jSONObject.put("inv_type", str6);
            }
            if (str7 != null) {
                jSONObject.put("inv_payee", str7);
            }
            if (str8 != null) {
                jSONObject.put("inv_content", str8);
            }
            if (!(str6 == null || str8 == null || str7 == null)) {
                jSONObject.put("need_inv", "1");
            }
            if (str9 != null) {
                jSONObject.put("postscript", str9);
            }
            if (TextUtils.isEmpty(str10)) {
                jSONObject.put("inv_title_type", "personal");
            } else {
                jSONObject.put("inv_tax_no", str10);
                jSONObject.put("inv_title_type", "enterprise");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_9(this));
    }

    public void b(String str) {
        this.q = "validate/integral";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("integral", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_10(this));
    }

    public void c(String str, String str2) {
        File file = new File(this.D + "/" + this.C);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(file + "/" + str2 + ".dat"));
            this.N = new PrintStream(fileOutputStream);
            this.N.print(str);
            this.N.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void a(String str, ArrayList<Integer> arrayList, int i, String str2, String str3) {
        this.q = "cart/create";
        this.l.show();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("goods_id", str);
            jSONObject.put("number", i);
            jSONObject.put("object_id", str2);
            jSONObject.put("rec_type", str3);
            jSONObject.put("area_id", e());
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                jSONArray.put(((Integer) arrayList.get(i2)).intValue());
            }
            jSONObject.put("spec", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ah_2(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r8, java.lang.String r9) {
        /*
        r7 = this;
        r2 = 1;
        r1 = 0;
        super.a(r8, r9);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0128 }
        r3.<init>(r9);	 Catch:{ JSONException -> 0x0128 }
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0128 }
        r0.<init>();	 Catch:{ JSONException -> 0x0128 }
        r4 = "===";
        r0 = r0.append(r4);	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.append(r8);	 Catch:{ JSONException -> 0x0128 }
        r4 = "返回===";
        r0 = r0.append(r4);	 Catch:{ JSONException -> 0x0128 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.append(r4);	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.toString();	 Catch:{ JSONException -> 0x0128 }
        com.ecjia.a.q.a(r0);	 Catch:{ JSONException -> 0x0128 }
        r0 = "status";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r0 = com.ecjia.hamster.model.ax.b(r0);	 Catch:{ JSONException -> 0x0128 }
        r7.r = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = -1;
        r4 = r8.hashCode();	 Catch:{ JSONException -> 0x0128 }
        switch(r4) {
            case -1569635637: goto L_0x0097;
            case -1552799878: goto L_0x005b;
            case -1544749117: goto L_0x0079;
            case -1377282586: goto L_0x008d;
            case -1056187752: goto L_0x0065;
            case 168458797: goto L_0x0051;
            case 1268154023: goto L_0x006f;
            case 2076046981: goto L_0x0083;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x0128 }
    L_0x0042:
        switch(r0) {
            case 0: goto L_0x00a1;
            case 1: goto L_0x014e;
            case 2: goto L_0x0045;
            case 3: goto L_0x0169;
            case 4: goto L_0x02e6;
            case 5: goto L_0x0300;
            case 6: goto L_0x0304;
            case 7: goto L_0x0308;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x0128 }
    L_0x0045:
        r7.g();	 Catch:{ JSONException -> 0x0128 }
        r0 = r7.r;	 Catch:{ JSONException -> 0x0128 }
        r7.a(r8, r9, r0);	 Catch:{ JSONException -> 0x0128 }
    L_0x004d:
        r7.e(r9);
        return;
    L_0x0051:
        r4 = "cart/list";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r0 = r1;
        goto L_0x0042;
    L_0x005b:
        r4 = "cart/delete";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r0 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "cart/update";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r0 = 2;
        goto L_0x0042;
    L_0x006f:
        r4 = "flow/checkOrder";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r4 == 0) goto L_0x0042;
    L_0x0077:
        r0 = 3;
        goto L_0x0042;
    L_0x0079:
        r4 = "flow/done";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r4 == 0) goto L_0x0042;
    L_0x0081:
        r0 = 4;
        goto L_0x0042;
    L_0x0083:
        r4 = "validate/integral";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r4 == 0) goto L_0x0042;
    L_0x008b:
        r0 = 5;
        goto L_0x0042;
    L_0x008d:
        r4 = "validate/bonus";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r4 == 0) goto L_0x0042;
    L_0x0095:
        r0 = 6;
        goto L_0x0042;
    L_0x0097:
        r4 = "cart/create";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r4 == 0) goto L_0x0042;
    L_0x009f:
        r0 = 7;
        goto L_0x0042;
    L_0x00a1:
        r0 = r7.r;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0128 }
        if (r0 != r2) goto L_0x0045;
    L_0x00a9:
        r7.H = r3;	 Catch:{ JSONException -> 0x0128 }
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r2 = "total";
        r2 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x0128 }
        r2 = com.ecjia.hamster.model.be.a(r2);	 Catch:{ JSONException -> 0x0128 }
        r7.a = r2;	 Catch:{ JSONException -> 0x0128 }
        r2 = "cart_list";
        r2 = r0.optJSONArray(r2);	 Catch:{ JSONException -> 0x0128 }
        r0 = r7.y;	 Catch:{ JSONException -> 0x0128 }
        r0.clear();	 Catch:{ JSONException -> 0x0128 }
        r0 = 0;
        r7.M = r0;	 Catch:{ JSONException -> 0x0128 }
        if (r2 == 0) goto L_0x011f;
    L_0x00cd:
        r0 = r2.length();	 Catch:{ JSONException -> 0x0128 }
        if (r0 <= 0) goto L_0x011f;
    L_0x00d3:
        r0 = r1;
    L_0x00d4:
        r3 = r2.length();	 Catch:{ JSONException -> 0x0128 }
        if (r0 >= r3) goto L_0x00ea;
    L_0x00da:
        r3 = r2.getJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r3 = com.ecjia.hamster.model.ECJia_NEWGOODITEM.fromJson(r3);	 Catch:{ JSONException -> 0x0128 }
        r4 = r7.y;	 Catch:{ JSONException -> 0x0128 }
        r4.add(r3);	 Catch:{ JSONException -> 0x0128 }
        r0 = r0 + 1;
        goto L_0x00d4;
    L_0x00ea:
        r0 = r7.y;	 Catch:{ JSONException -> 0x0128 }
        r4 = r0.size();	 Catch:{ JSONException -> 0x0128 }
        if (r4 <= 0) goto L_0x011f;
    L_0x00f2:
        r3 = r1;
    L_0x00f3:
        if (r3 >= r4) goto L_0x011f;
    L_0x00f5:
        r0 = r7.y;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.get(r3);	 Catch:{ JSONException -> 0x0128 }
        r0 = (com.ecjia.hamster.model.ECJia_NEWGOODITEM) r0;	 Catch:{ JSONException -> 0x0128 }
        r5 = r0.getGoodslist();	 Catch:{ JSONException -> 0x0128 }
        r2 = r1;
    L_0x0102:
        r0 = r5.size();	 Catch:{ JSONException -> 0x0128 }
        if (r2 >= r0) goto L_0x011b;
    L_0x0108:
        r0 = r5.get(r2);	 Catch:{ JSONException -> 0x0128 }
        r0 = (com.ecjia.hamster.model.ECJia_GOODS_LIST) r0;	 Catch:{ JSONException -> 0x0128 }
        r6 = r7.M;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.getGoods_number();	 Catch:{ JSONException -> 0x0128 }
        r0 = r0 + r6;
        r7.M = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0102;
    L_0x011b:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x00f3;
    L_0x011f:
        r0 = r7.m;	 Catch:{ JSONException -> 0x0128 }
        r1 = r7.M;	 Catch:{ JSONException -> 0x0128 }
        r0.a(r1);	 Catch:{ JSONException -> 0x0128 }
        goto L_0x0045;
    L_0x0128:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r9);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004d;
    L_0x014e:
        r0 = r7.r;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0128 }
        if (r0 != r2) goto L_0x0045;
    L_0x0156:
        r0 = r7.J;	 Catch:{ JSONException -> 0x0128 }
        if (r0 != 0) goto L_0x0045;
    L_0x015a:
        r0 = r7.m;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.g();	 Catch:{ JSONException -> 0x0128 }
        r0 = r0 + -1;
        r1 = r7.m;	 Catch:{ JSONException -> 0x0128 }
        r1.a(r0);	 Catch:{ JSONException -> 0x0128 }
        goto L_0x0045;
    L_0x0169:
        r0 = r7.r;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0128 }
        if (r0 != r2) goto L_0x0045;
    L_0x0171:
        r0 = "data";
        r2 = r3.getJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r0 = "consignee";
        r0 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r0 = com.ecjia.hamster.model.ECJia_ADDRESS.fromJson(r0);	 Catch:{ JSONException -> 0x0128 }
        r7.b = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = "store_goods_list";
        r3 = r2.optJSONArray(r0);	 Catch:{ JSONException -> 0x0128 }
        r0 = r7.c;	 Catch:{ JSONException -> 0x0128 }
        r0.clear();	 Catch:{ JSONException -> 0x0128 }
        r0 = r7.d;	 Catch:{ JSONException -> 0x0128 }
        r0.clear();	 Catch:{ JSONException -> 0x0128 }
        if (r3 == 0) goto L_0x01bb;
    L_0x0195:
        r0 = r3.length();	 Catch:{ JSONException -> 0x0128 }
        if (r0 <= 0) goto L_0x01bb;
    L_0x019b:
        r0 = r1;
    L_0x019c:
        r4 = r3.length();	 Catch:{ JSONException -> 0x0128 }
        if (r0 >= r4) goto L_0x01bb;
    L_0x01a2:
        r4 = r3.getJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r4 = com.ecjia.hamster.model.ECJia_STOREGOODSLIST.fromJson(r4);	 Catch:{ JSONException -> 0x0128 }
        r5 = r7.c;	 Catch:{ JSONException -> 0x0128 }
        r6 = r4.getGoods_list();	 Catch:{ JSONException -> 0x0128 }
        r5.addAll(r6);	 Catch:{ JSONException -> 0x0128 }
        r5 = r7.d;	 Catch:{ JSONException -> 0x0128 }
        r5.add(r4);	 Catch:{ JSONException -> 0x0128 }
        r0 = r0 + 1;
        goto L_0x019c;
    L_0x01bb:
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0128 }
        r0.<init>();	 Catch:{ JSONException -> 0x0128 }
        r3 = "是否允许红包:";
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x0128 }
        r3 = "allow_use_bonus";
        r3 = r2.optString(r3);	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.toString();	 Catch:{ JSONException -> 0x0128 }
        com.ecjia.a.q.a(r0);	 Catch:{ JSONException -> 0x0128 }
        r0 = "allow_use_bonus";
        r0 = r2.optString(r0);	 Catch:{ JSONException -> 0x0128 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ JSONException -> 0x0128 }
        if (r0 != 0) goto L_0x0223;
    L_0x01e3:
        r0 = "allow_use_bonus";
        r0 = r2.optString(r0);	 Catch:{ JSONException -> 0x0128 }
        r3 = "1";
        r0 = r0.equals(r3);	 Catch:{ JSONException -> 0x0128 }
        if (r0 == 0) goto L_0x0223;
    L_0x01f1:
        r0 = 1;
        r7.x = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = "是否允许红包:true";
        com.ecjia.a.q.a(r0);	 Catch:{ JSONException -> 0x0128 }
    L_0x01f9:
        r0 = "bonus";
        r3 = r2.optJSONArray(r0);	 Catch:{ JSONException -> 0x0128 }
        if (r3 == 0) goto L_0x0227;
    L_0x0201:
        r0 = r3.length();	 Catch:{ JSONException -> 0x0128 }
        if (r0 <= 0) goto L_0x0227;
    L_0x0207:
        r0 = r7.i;	 Catch:{ JSONException -> 0x0128 }
        r0.clear();	 Catch:{ JSONException -> 0x0128 }
        r0 = r1;
    L_0x020d:
        r4 = r3.length();	 Catch:{ JSONException -> 0x0128 }
        if (r0 >= r4) goto L_0x0227;
    L_0x0213:
        r4 = r7.i;	 Catch:{ JSONException -> 0x0128 }
        r5 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r5 = com.ecjia.hamster.model.ECJia_BONUS.fromJson(r5);	 Catch:{ JSONException -> 0x0128 }
        r4.add(r5);	 Catch:{ JSONException -> 0x0128 }
        r0 = r0 + 1;
        goto L_0x020d;
    L_0x0223:
        r0 = 0;
        r7.x = r0;	 Catch:{ JSONException -> 0x0128 }
        goto L_0x01f9;
    L_0x0227:
        r0 = "discount_formated";
        r0 = r2.optString(r0);	 Catch:{ JSONException -> 0x0128 }
        r7.f = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = "discount";
        r0 = r2.optString(r0);	 Catch:{ JSONException -> 0x0128 }
        r7.g = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = "allow_can_invoice";
        r0 = r2.optString(r0);	 Catch:{ JSONException -> 0x0128 }
        r7.h = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = "your_integral";
        r4 = r2.optLong(r0);	 Catch:{ JSONException -> 0x0128 }
        r7.t = r4;	 Catch:{ JSONException -> 0x0128 }
        r0 = "order_max_integral";
        r4 = r2.optLong(r0);	 Catch:{ JSONException -> 0x0128 }
        r7.u = r4;	 Catch:{ JSONException -> 0x0128 }
        r0 = "allow_use_integral";
        r0 = r2.optInt(r0);	 Catch:{ JSONException -> 0x0128 }
        r7.j = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = r2.toString();	 Catch:{ JSONException -> 0x0128 }
        r7.e = r0;	 Catch:{ JSONException -> 0x0128 }
        r0 = "payment_list";
        r2 = r2.optJSONArray(r0);	 Catch:{ JSONException -> 0x0128 }
        r0 = r7.A;	 Catch:{ JSONException -> 0x0128 }
        r0.clear();	 Catch:{ JSONException -> 0x0128 }
        r0 = r7.B;	 Catch:{ JSONException -> 0x0128 }
        r0.clear();	 Catch:{ JSONException -> 0x0128 }
        r0 = r7.z;	 Catch:{ JSONException -> 0x0128 }
        r0.clear();	 Catch:{ JSONException -> 0x0128 }
        if (r2 == 0) goto L_0x02a8;
    L_0x0274:
        r0 = r2.length();	 Catch:{ JSONException -> 0x0128 }
        if (r0 <= 0) goto L_0x02a8;
    L_0x027a:
        r0 = r1;
    L_0x027b:
        r1 = r2.length();	 Catch:{ JSONException -> 0x0128 }
        if (r0 >= r1) goto L_0x02a8;
    L_0x0281:
        r1 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r1 = com.ecjia.hamster.model.ECJia_PAYMENT.fromJson(r1);	 Catch:{ JSONException -> 0x0128 }
        r3 = r7.z;	 Catch:{ JSONException -> 0x0128 }
        r3.add(r1);	 Catch:{ JSONException -> 0x0128 }
        r3 = "1";
        r4 = r1.getIs_online();	 Catch:{ JSONException -> 0x0128 }
        r3 = r3.equals(r4);	 Catch:{ JSONException -> 0x0128 }
        if (r3 == 0) goto L_0x02a2;
    L_0x029a:
        r3 = r7.A;	 Catch:{ JSONException -> 0x0128 }
        r3.add(r1);	 Catch:{ JSONException -> 0x0128 }
    L_0x029f:
        r0 = r0 + 1;
        goto L_0x027b;
    L_0x02a2:
        r3 = r7.B;	 Catch:{ JSONException -> 0x0128 }
        r3.add(r1);	 Catch:{ JSONException -> 0x0128 }
        goto L_0x029f;
    L_0x02a8:
        r0 = r7.A;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.size();	 Catch:{ JSONException -> 0x0128 }
        if (r0 <= 0) goto L_0x02c7;
    L_0x02b0:
        r0 = r7.A;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.size();	 Catch:{ JSONException -> 0x0128 }
        if (r0 <= 0) goto L_0x0045;
    L_0x02b8:
        r0 = r7.A;	 Catch:{ JSONException -> 0x0128 }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ JSONException -> 0x0128 }
        r0 = (com.ecjia.hamster.model.ECJia_PAYMENT) r0;	 Catch:{ JSONException -> 0x0128 }
        r1 = 1;
        r0.setSelected(r1);	 Catch:{ JSONException -> 0x0128 }
        goto L_0x0045;
    L_0x02c7:
        r0 = r7.B;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.size();	 Catch:{ JSONException -> 0x0128 }
        if (r0 <= 0) goto L_0x0045;
    L_0x02cf:
        r0 = r7.B;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.size();	 Catch:{ JSONException -> 0x0128 }
        if (r0 <= 0) goto L_0x0045;
    L_0x02d7:
        r0 = r7.B;	 Catch:{ JSONException -> 0x0128 }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ JSONException -> 0x0128 }
        r0 = (com.ecjia.hamster.model.ECJia_PAYMENT) r0;	 Catch:{ JSONException -> 0x0128 }
        r1 = 1;
        r0.setSelected(r1);	 Catch:{ JSONException -> 0x0128 }
        goto L_0x0045;
    L_0x02e6:
        r7.E = r3;	 Catch:{ JSONException -> 0x0128 }
        r0 = r7.r;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0128 }
        if (r0 != r2) goto L_0x0045;
    L_0x02f0:
        r0 = "data";
        r0 = r3.getJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        r1 = "order_id";
        r0 = r0.getString(r1);	 Catch:{ JSONException -> 0x0128 }
        r7.K = r0;	 Catch:{ JSONException -> 0x0128 }
        goto L_0x0045;
    L_0x0300:
        r7.F = r3;	 Catch:{ JSONException -> 0x0128 }
        goto L_0x0045;
    L_0x0304:
        r7.G = r3;	 Catch:{ JSONException -> 0x0128 }
        goto L_0x0045;
    L_0x0308:
        r0 = r7.r;	 Catch:{ JSONException -> 0x0128 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0128 }
        if (r0 != r2) goto L_0x0045;
    L_0x0310:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x0128 }
        if (r0 == 0) goto L_0x0045;
    L_0x0318:
        r1 = "rec_id";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x0128 }
        r7.w = r1;	 Catch:{ JSONException -> 0x0128 }
        r1 = "goods_number";
        r0 = r0.optInt(r1);	 Catch:{ JSONException -> 0x0128 }
        r7.L = r0;	 Catch:{ JSONException -> 0x0128 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.ah.a(java.lang.String, java.lang.String):void");
    }
}
