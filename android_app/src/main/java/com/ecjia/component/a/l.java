package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ah;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.t;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaFindModel */
public class l extends e {
    public ArrayList<ah> a = new ArrayList();
    public ArrayList<ArrayList<t>> b = new ArrayList();
    String c = this.n.getPackageName();
    public String d;
    public aa e;
    private ArrayList<ArrayList<t>> f = new ArrayList();
    private String g;
    private ax h;
    private boolean i;
    private PrintStream j = null;

    /* compiled from: ECJiaFindModel */
    class l_1 implements OnCancelListener {
        final /* synthetic */ l a;

        l_1(l lVar) {
            this.a = lVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaFindModel */
    class l_2 implements OnCancelListener {
        final /* synthetic */ l a;

        l_2(l lVar) {
            this.a = lVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public l(Context context) {
        super(context);
        this.d = context.getCacheDir() + "/ECJia/cache";
        this.s.a((b) this);
    }

    public void a() {
        this.q = "home/discover";
        ap c = ap.c();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new l_1(this));
    }

    public void b() {
        this.i = true;
        this.q = "home/news";
        this.l.show();
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(8);
        ap c = ap.c();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new l_2(this));
    }

    public void c() {
        this.i = false;
        this.q = "home/news";
        ab abVar = new ab();
        abVar.b((this.b.size() / 8) + 1);
        abVar.a(8);
        ap c = ap.c();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void j() {
        if (this.a.size() > 0) {
            a("home/discover", this.g, this.h);
        } else {
            k();
        }
    }

    private void k() {
        File file = new File(this.d + "/" + this.c + "/findData.dat");
        if (file.exists()) {
            try {
                InputStream fileInputStream = new FileInputStream(file);
                Reader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                a(bufferedReader.readLine());
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    private void a(String str) {
        String str2 = "home/discover";
        if (str != null) {
            try {
                this.g = str;
                JSONObject jSONObject = new JSONObject(str);
                this.h = ax.a(jSONObject.optJSONObject("status"));
                if (this.h.b() == 1) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("data");
                    this.a.clear();
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        int length = optJSONArray.length();
                        for (int i = 0; i < length; i++) {
                            this.a.add(ah.a(optJSONArray.optJSONObject(i)));
                        }
                    }
                }
                a("home/discover", str, this.h);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        q.a("运行");
    }

    public void b(String str, String str2) {
        File file = new File(this.d + "/" + this.c);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(file + "/" + str2 + ".dat"));
            this.j = new PrintStream(fileOutputStream);
            this.j.print(str);
            this.j.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r11, java.lang.String r12) {
        /*
        r10 = this;
        r2 = 1;
        r1 = 0;
        super.a(r11, r12);
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0081 }
        r4.<init>(r12);	 Catch:{ JSONException -> 0x0081 }
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0081 }
        r0.<init>();	 Catch:{ JSONException -> 0x0081 }
        r3 = "===";
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x0081 }
        r0 = r0.append(r11);	 Catch:{ JSONException -> 0x0081 }
        r3 = "返回===";
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x0081 }
        r3 = r4.toString();	 Catch:{ JSONException -> 0x0081 }
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x0081 }
        r0 = r0.toString();	 Catch:{ JSONException -> 0x0081 }
        com.ecjia.a.q.a(r0);	 Catch:{ JSONException -> 0x0081 }
        r0 = "status";
        r0 = r4.optJSONObject(r0);	 Catch:{ JSONException -> 0x0081 }
        r0 = com.ecjia.hamster.model.ax.a(r0);	 Catch:{ JSONException -> 0x0081 }
        r10.r = r0;	 Catch:{ JSONException -> 0x0081 }
        r0 = -1;
        r3 = r11.hashCode();	 Catch:{ JSONException -> 0x0081 }
        switch(r3) {
            case 966255428: goto L_0x0065;
            case 1249111833: goto L_0x0051;
            case 2073696771: goto L_0x005b;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x0081 }
    L_0x0042:
        switch(r0) {
            case 0: goto L_0x006f;
            case 1: goto L_0x00a6;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x0081 }
    L_0x0045:
        r10.g();	 Catch:{ JSONException -> 0x0081 }
        r0 = r10.r;	 Catch:{ JSONException -> 0x0081 }
        r10.a(r11, r12, r0);	 Catch:{ JSONException -> 0x0081 }
    L_0x004d:
        r10.e(r12);
        return;
    L_0x0051:
        r3 = "home/discover";
        r3 = r11.equals(r3);	 Catch:{ JSONException -> 0x0081 }
        if (r3 == 0) goto L_0x0042;
    L_0x0059:
        r0 = r1;
        goto L_0x0042;
    L_0x005b:
        r3 = "home/news";
        r3 = r11.equals(r3);	 Catch:{ JSONException -> 0x0081 }
        if (r3 == 0) goto L_0x0042;
    L_0x0063:
        r0 = r2;
        goto L_0x0042;
    L_0x0065:
        r3 = "address/update";
        r3 = r11.equals(r3);	 Catch:{ JSONException -> 0x0081 }
        if (r3 == 0) goto L_0x0042;
    L_0x006d:
        r0 = 2;
        goto L_0x0042;
    L_0x006f:
        r0 = r10.r;	 Catch:{ JSONException -> 0x0081 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0081 }
        if (r0 != r2) goto L_0x0045;
    L_0x0077:
        r0 = r4.toString();	 Catch:{ JSONException -> 0x0081 }
        r1 = "findData";
        r10.b(r0, r1);	 Catch:{ JSONException -> 0x0081 }
        goto L_0x0045;
    L_0x0081:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r12);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004d;
    L_0x00a6:
        r0 = r10.r;	 Catch:{ JSONException -> 0x0081 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0081 }
        if (r0 != r2) goto L_0x0045;
    L_0x00ae:
        r0 = "data";
        r5 = r4.optJSONArray(r0);	 Catch:{ JSONException -> 0x0081 }
        r0 = r10.i;	 Catch:{ JSONException -> 0x0081 }
        if (r0 == 0) goto L_0x00c2;
    L_0x00b8:
        r0 = r10.b;	 Catch:{ JSONException -> 0x0081 }
        r0.clear();	 Catch:{ JSONException -> 0x0081 }
        r0 = r10.f;	 Catch:{ JSONException -> 0x0081 }
        r0.clear();	 Catch:{ JSONException -> 0x0081 }
    L_0x00c2:
        if (r5 == 0) goto L_0x0101;
    L_0x00c4:
        r0 = r5.length();	 Catch:{ JSONException -> 0x0081 }
        if (r0 <= 0) goto L_0x0101;
    L_0x00ca:
        r6 = r5.length();	 Catch:{ JSONException -> 0x0081 }
        r0 = 0;
        r2 = r0;
        r0 = r1;
    L_0x00d1:
        if (r0 >= r6) goto L_0x0101;
    L_0x00d3:
        r7 = r5.optJSONArray(r0);	 Catch:{ JSONException -> 0x0081 }
        if (r7 == 0) goto L_0x00f9;
    L_0x00d9:
        r3 = r7.length();	 Catch:{ JSONException -> 0x0081 }
        if (r3 <= 0) goto L_0x00f9;
    L_0x00df:
        r8 = r7.length();	 Catch:{ JSONException -> 0x0081 }
        r2 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x0081 }
        r2.<init>();	 Catch:{ JSONException -> 0x0081 }
        r3 = r1;
    L_0x00e9:
        if (r3 >= r8) goto L_0x00f9;
    L_0x00eb:
        r9 = r7.optJSONObject(r3);	 Catch:{ JSONException -> 0x0081 }
        r9 = com.ecjia.hamster.model.t.a(r9);	 Catch:{ JSONException -> 0x0081 }
        r2.add(r9);	 Catch:{ JSONException -> 0x0081 }
        r3 = r3 + 1;
        goto L_0x00e9;
    L_0x00f9:
        r3 = r10.f;	 Catch:{ JSONException -> 0x0081 }
        r3.add(r2);	 Catch:{ JSONException -> 0x0081 }
        r0 = r0 + 1;
        goto L_0x00d1;
    L_0x0101:
        r0 = r10.f;	 Catch:{ JSONException -> 0x0081 }
        if (r0 == 0) goto L_0x0125;
    L_0x0105:
        r0 = r10.f;	 Catch:{ JSONException -> 0x0081 }
        r0 = r0.size();	 Catch:{ JSONException -> 0x0081 }
        if (r0 <= 0) goto L_0x0125;
    L_0x010d:
        r0 = r10.f;	 Catch:{ JSONException -> 0x0081 }
        r0 = r0.size();	 Catch:{ JSONException -> 0x0081 }
        r0 = r0 + -1;
    L_0x0115:
        if (r0 < 0) goto L_0x0125;
    L_0x0117:
        r1 = r10.b;	 Catch:{ JSONException -> 0x0081 }
        r2 = r10.f;	 Catch:{ JSONException -> 0x0081 }
        r2 = r2.get(r0);	 Catch:{ JSONException -> 0x0081 }
        r1.add(r2);	 Catch:{ JSONException -> 0x0081 }
        r0 = r0 + -1;
        goto L_0x0115;
    L_0x0125:
        r0 = "paginated";
        r0 = r4.optJSONObject(r0);	 Catch:{ JSONException -> 0x0081 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x0081 }
        r10.e = r0;	 Catch:{ JSONException -> 0x0081 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.l.a(java.lang.String, java.lang.String):void");
    }
}
