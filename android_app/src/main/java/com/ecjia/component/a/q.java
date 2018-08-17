package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.as;
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

/* compiled from: ECJiaHelpModel */
public class q extends e {
    public ArrayList<as> a = new ArrayList();
    String b;
    public String c;
    public String d;
    public String e;
    private PrintStream f = null;

    /* compiled from: ECJiaHelpModel */
    class q_1 implements OnCancelListener {
        final /* synthetic */ q a;

        q_1(q qVar) {
            this.a = qVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public q(Context context) {
        super(context);
        this.s.a((b) this);
        this.b = this.n.getPackageName();
        this.c = context.getCacheDir() + "/ECMoban/cache";
        a();
    }

    public void a() {
        try {
            InputStream fileInputStream = new FileInputStream(new File(this.c + "/" + this.b + "/helpData.dat"));
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
                    this.a.clear();
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            this.a.add(as.a(optJSONArray.getJSONObject(i)));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void b(String str, String str2) {
        File file = new File(this.c + "/" + this.b);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(file + "/" + str2 + ".dat"));
            this.f = new PrintStream(fileOutputStream);
            this.f.print(str);
            this.f.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void b() {
        this.q = "shop/help";
        this.l.show();
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
        this.l.setOnCancelListener(new q_1(this));
    }

    public void a(int i) {
        this.q = "shop/help/detail";
        ap c = ap.c();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("article_id", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a(String str, String str2) {
        int i = 0;
        super.a(str, str2);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            com.ecjia.a.q.a("===" + str + "返回===" + jSONObject.toString());
            this.r = ax.a(jSONObject.optJSONObject("status"));
            int i2 = -1;
            switch (str.hashCode()) {
                case -877124282:
                    if (str.equals("shop/help/detail")) {
                        i2 = 1;
                        break;
                    }
                    break;
                case 2146545338:
                    if (str.equals("shop/help")) {
                        i2 = 0;
                        break;
                    }
                    break;
            }
            switch (i2) {
                case 0:
                    if (this.r.b() == 1) {
                        b(jSONObject.toString(), "helpData");
                        JSONArray optJSONArray = jSONObject.optJSONArray("data");
                        this.d = jSONObject.toString();
                        this.a.clear();
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.a.add(as.a(optJSONArray.getJSONObject(i)));
                                i++;
                            }
                            break;
                        }
                    }
                    break;
                case 1:
                    if (this.r.b() == 1) {
                        this.e = jSONObject.optString("data");
                        break;
                    }
                    break;
            }
            g();
            a(str, str2, this.r);
        } catch (JSONException e) {
            e.printStackTrace();
            com.ecjia.a.q.a("===" + str + "返回===" + str2);
        }
        e(str2);
    }
}
