package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.ap;
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

/* compiled from: ECJiaSearchModel */
public class ad extends e {
    public String a;
    public ArrayList<String> b = new ArrayList();
    public ArrayList<ECJia_CATEGORY> c = new ArrayList();
    private String d;
    private PrintStream e = null;

    /* compiled from: ECJiaSearchModel */
    class ad_1 implements OnCancelListener {
        final /* synthetic */ ad a;

        ad_1(ad adVar) {
            this.a = adVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaSearchModel */
    class ad_2 implements OnCancelListener {
        final /* synthetic */ ad a;

        ad_2(ad adVar) {
            this.a = adVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public ad(Context context) {
        super(context);
        this.s.a((b) this);
        this.d = this.n.getPackageName();
        this.a = context.getCacheDir() + "/ECJia/cache";
        this.d = this.n.getPackageName();
        a();
    }

    public void a() {
        try {
            InputStream fileInputStream = new FileInputStream(new File(this.a + "/" + this.d + "/" + "searchData.dat"));
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
                ax a = ax.a(jSONObject.optJSONObject("status"));
                this.b.clear();
                if (a.b() == 1) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("data");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            this.b.add(optJSONArray.getString(i));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void b() {
        this.q = "goods/category";
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
        this.l.setOnCancelListener(new ad_1(this));
    }

    public void c() {
        this.q = "mobile/hot_keywords";
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
        this.l.setOnCancelListener(new ad_2(this));
    }

    public void b(String str, String str2) {
        File file = new File(this.a + "/" + this.d);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(file + "/" + str2));
            this.e = new PrintStream(fileOutputStream);
            this.e.print(str);
            this.e.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void a(String str, String str2) {
        int i = 0;
        super.a(str, str2);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            q.a("===" + str + "返回===" + jSONObject.toString());
            this.r = ax.a(jSONObject.optJSONObject("status"));
            int i2 = -1;
            switch (str.hashCode()) {
                case -2030634071:
                    if (str.equals("mobile/hot_keywords")) {
                        i2 = 1;
                        break;
                    }
                    break;
                case -1023842409:
                    if (str.equals("goods/category")) {
                        i2 = 0;
                        break;
                    }
                    break;
            }
            JSONArray optJSONArray;
            switch (i2) {
                case 0:
                    if (this.r.b() == 1) {
                        b(jSONObject.toString(), "searchData.dat");
                        optJSONArray = jSONObject.optJSONArray("data");
                        this.c.clear();
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.c.add(ECJia_CATEGORY.fromJson(optJSONArray.optJSONObject(i)));
                                i++;
                            }
                            break;
                        }
                    }
                    break;
                case 1:
                    if (this.r.b() == 1) {
                        b(jSONObject.toString(), "search_keywords.dat");
                        optJSONArray = jSONObject.optJSONArray("data");
                        this.b.clear();
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.b.add(optJSONArray.getString(i));
                                i++;
                            }
                            break;
                        }
                    }
                    break;
            }
            g();
            a(str, str2, this.r);
        } catch (JSONException e) {
            e.printStackTrace();
            q.a("===" + str + "返回===" + str2);
        }
        e(str2);
    }
}
