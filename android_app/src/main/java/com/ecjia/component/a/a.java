package com.ecjia.component.a;

import android.content.Context;
import android.text.TextUtils;
import com.ecjia.a.g;
import com.ecjia.a.q;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.b;
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

/* compiled from: ECJiaADPicModel */
public class a extends e {
    public ArrayList<b> a = new ArrayList();
    public ArrayList<b> b = new ArrayList();
    private PrintStream c = null;

    public a(Context context) {
        super(context);
        this.s.a((com.ecjia.component.a.a.b) this);
    }

    public void a() {
        this.q = "home/adsense";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a(String str, String str2, String str3) {
        File file = new File(str);
        if (file.exists()) {
            q.a("文件存在");
            if (TextUtils.isEmpty(str2)) {
                q.a("广告图数据是空的");
                g.a(str, str3);
                return;
            }
        }
        file.mkdirs();
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(str + "/" + str3));
            this.c = new PrintStream(fileOutputStream);
            this.c.print(str2);
            this.c.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void b() {
        File file = new File("sdcard/Android/data/com.ecmoban.android.missmall/cache/image.dat");
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
        if (str != null) {
            try {
                JSONArray optJSONArray = new JSONObject(str).optJSONArray("data");
                this.b.clear();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    b a = b.a(optJSONArray.getJSONObject(i));
                    a.a("sdcard/android/data/com.ecmoban.android.missmall/cache/ad_image/" + i + ".png");
                    this.b.add(a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void c() {
        for (int i = 0; i < this.a.size(); i++) {
            this.q = "home/adsense";
            this.s.c(((b) this.a.get(i)).d(), "sdcard/android/data/com.ecmoban.android.missmall/cache/ad_image/" + i + ".png");
        }
    }

    public void a(String str, String str2) {
        super.a(str, str2);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            q.a("===" + str + "返回===" + jSONObject.toString());
            this.r = ax.a(jSONObject.optJSONObject("status"));
            if (str.equals("home/adsense") && this.r.b() == 1) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                this.a.clear();
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    a("sdcard/Android/data/com.ecmoban.android.missmall/cache", "", "image.dat");
                } else {
                    int i;
                    for (i = 0; i < optJSONArray.length(); i++) {
                        this.a.add(b.a(optJSONArray.getJSONObject(i)));
                    }
                    int i2 = 0;
                    i = 0;
                    while (i2 < this.a.size()) {
                        int i3;
                        if (TextUtils.isEmpty(((b) this.a.get(i2)).d())) {
                            i3 = i;
                        } else {
                            i3 = i + 1;
                        }
                        i2++;
                        i = i3;
                    }
                    if (i == this.a.size()) {
                        a("sdcard/Android/data/com.ecmoban.android.missmall/cache", str2, "image.dat");
                        c();
                    }
                }
            }
            g();
            a(str, str2, this.r);
        } catch (JSONException e) {
            e.printStackTrace();
            q.a("===" + str + "返回===" + str2);
            e(str2);
        }
    }
}
