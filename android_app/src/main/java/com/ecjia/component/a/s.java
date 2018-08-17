package com.ecjia.component.a;

import android.content.Context;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.a;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.at;
import com.ecjia.hamster.model.ax;
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

/* compiled from: ECJiaInfoModel */
public class s extends e {
    String a;
    public String b;
    public ArrayList<at> c = new ArrayList();
    public String d;
    public String e;
    private a f;
    private PrintStream g = null;

    public s(Context context) {
        super(context);
        this.s.a((b) this);
        this.a = this.n.getPackageName();
        this.b = context.getCacheDir() + "/ECMoban/cache";
        a();
    }

    public void a() {
        try {
            InputStream fileInputStream = new FileInputStream(new File(this.b + "/" + this.a + "/infoData.dat"));
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

    public void a(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (ax.a(jSONObject.optJSONObject("status")).b() == 1) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("data");
                    this.d = jSONObject.toString();
                    this.c.clear();
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            this.c.add(at.a(optJSONArray.getJSONObject(i)));
                        }
                    }
                    this.m.a(this.c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void b(String str, String str2) {
        File file = new File(this.b + "/" + this.a);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(file + "/" + str2 + ".dat"));
            this.g = new PrintStream(fileOutputStream);
            this.g.print(str);
            this.g.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void b() {
        this.q = "shop/info";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void c() {
        this.q = "shop/token";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a(int i) {
        this.q = "shop/info/detail";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("article_id", i);
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
        r0 = 0;
        r2 = 1;
        super.a(r6, r7);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00b7 }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x00b7 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00b7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00b7 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00b7 }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x00b7 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00b7 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00b7 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00b7 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00b7 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00b7 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00b7 }
        r5.r = r1;	 Catch:{ JSONException -> 0x00b7 }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x00b7 }
        switch(r4) {
            case 364251961: goto L_0x0065;
            case 2129775008: goto L_0x005b;
            case 2146583591: goto L_0x0051;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00b7 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x006f;
            case 1: goto L_0x00dd;
            case 2: goto L_0x00fe;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00b7 }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x00b7 }
        r0 = r5.r;	 Catch:{ JSONException -> 0x00b7 }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x00b7 }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "shop/info";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00b7 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "shop/token";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00b7 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "shop/info/detail";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00b7 }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00b7 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00b7 }
        if (r1 != r2) goto L_0x0045;
    L_0x0077:
        r1 = r3.toString();	 Catch:{ JSONException -> 0x00b7 }
        r2 = "infoData";
        r5.b(r1, r2);	 Catch:{ JSONException -> 0x00b7 }
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00b7 }
        r2 = r3.toString();	 Catch:{ JSONException -> 0x00b7 }
        r5.d = r2;	 Catch:{ JSONException -> 0x00b7 }
        r2 = r5.c;	 Catch:{ JSONException -> 0x00b7 }
        r2.clear();	 Catch:{ JSONException -> 0x00b7 }
        if (r1 == 0) goto L_0x00af;
    L_0x0093:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00b7 }
        if (r2 <= 0) goto L_0x00af;
    L_0x0099:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00b7 }
        if (r0 >= r2) goto L_0x00af;
    L_0x009f:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00b7 }
        r2 = com.ecjia.hamster.model.at.a(r2);	 Catch:{ JSONException -> 0x00b7 }
        r3 = r5.c;	 Catch:{ JSONException -> 0x00b7 }
        r3.add(r2);	 Catch:{ JSONException -> 0x00b7 }
        r0 = r0 + 1;
        goto L_0x0099;
    L_0x00af:
        r0 = r5.m;	 Catch:{ JSONException -> 0x00b7 }
        r1 = r5.c;	 Catch:{ JSONException -> 0x00b7 }
        r0.a(r1);	 Catch:{ JSONException -> 0x00b7 }
        goto L_0x0045;
    L_0x00b7:
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
    L_0x00dd:
        r0 = r5.r;	 Catch:{ JSONException -> 0x00b7 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00b7 }
        if (r0 != r2) goto L_0x0045;
    L_0x00e5:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00b7 }
        r0 = com.ecjia.hamster.model.a.a(r0);	 Catch:{ JSONException -> 0x00b7 }
        r5.f = r0;	 Catch:{ JSONException -> 0x00b7 }
        r0 = r5.m;	 Catch:{ JSONException -> 0x00b7 }
        r1 = r5.f;	 Catch:{ JSONException -> 0x00b7 }
        r1 = r1.a();	 Catch:{ JSONException -> 0x00b7 }
        r0.a(r1);	 Catch:{ JSONException -> 0x00b7 }
        goto L_0x0045;
    L_0x00fe:
        r0 = r5.r;	 Catch:{ JSONException -> 0x00b7 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00b7 }
        if (r0 != r2) goto L_0x0045;
    L_0x0106:
        r0 = "data";
        r0 = r3.getString(r0);	 Catch:{ JSONException -> 0x00b7 }
        r5.e = r0;	 Catch:{ JSONException -> 0x00b7 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.s.a(java.lang.String, java.lang.String):void");
    }
}
