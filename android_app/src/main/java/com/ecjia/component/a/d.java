package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_FILTER_ATTR;
import com.ecjia.hamster.model.ECJia_FILTER_BRAND;
import com.ecjia.hamster.model.ECJia_FILTER_CATEGORY;
import com.ecjia.hamster.model.ECJia_FILTER_PRICE;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ax;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaAdvanceSearchModel */
public class d extends e {
    public ArrayList<ECJia_FILTER_BRAND> a = new ArrayList();
    public ArrayList<ECJia_FILTER_PRICE> b = new ArrayList();
    public ArrayList<ECJia_FILTER_CATEGORY> c = new ArrayList();
    public ArrayList<ECJia_FILTER_ATTR> d = new ArrayList();
    private String e;

    /* compiled from: ECJiaAdvanceSearchModel */
    class d_1 implements OnCancelListener {
        final /* synthetic */ d a;

        d_1(d dVar) {
            this.a = dVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAdvanceSearchModel */
    class d_2 implements OnCancelListener {
        final /* synthetic */ d a;

        d_2(d dVar) {
            this.a = dVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAdvanceSearchModel */
    class d_3 implements OnCancelListener {
        final /* synthetic */ d a;

        d_3(d dVar) {
            this.a = dVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaAdvanceSearchModel */
    class d_4 implements OnCancelListener {
        final /* synthetic */ d a;

        d_4(d dVar) {
            this.a = dVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public d(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "goods/brand";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            if (str != null) {
                jSONObject.put("category_id", str);
            }
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new d_1(this));
    }

    public void b(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "goods/price_range";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            if (str != null) {
                jSONObject.put("category_id", str);
            }
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new d_2(this));
    }

    public void c(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "goods/filter";
        JSONObject jSONObject = new JSONObject();
        this.e = str;
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("category_id", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new d_3(this));
    }

    public void a() {
        ap c = ap.c();
        this.l.show();
        this.q = "goods/category";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new d_4(this));
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
                case -1834268591:
                    if (str.equals("goods/filter")) {
                        i2 = 2;
                        break;
                    }
                    break;
                case -1023842409:
                    if (str.equals("goods/category")) {
                        i2 = 3;
                        break;
                    }
                    break;
                case -893890674:
                    if (str.equals("goods/brand")) {
                        i2 = 0;
                        break;
                    }
                    break;
                case 1915032974:
                    if (str.equals("goods/price_range")) {
                        i2 = 1;
                        break;
                    }
                    break;
            }
            JSONArray optJSONArray;
            switch (i2) {
                case 0:
                    if (this.r.b() == 1) {
                        optJSONArray = jSONObject.optJSONArray("data");
                        this.a.clear();
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.a.add(ECJia_FILTER_BRAND.fromJson(optJSONArray.getJSONObject(i)));
                                i++;
                            }
                            break;
                        }
                    }
                    break;
                case 1:
                    if (this.r.b() == 1) {
                        optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            this.b.clear();
                            while (i < optJSONArray.length()) {
                                this.b.add(ECJia_FILTER_PRICE.fromJson(optJSONArray.getJSONObject(i)));
                                i++;
                            }
                            break;
                        }
                    }
                    break;
                case 2:
                    if (this.r.b() == 1) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("category_filter");
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            this.c.clear();
                            for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                ECJia_FILTER_CATEGORY fromJson = ECJia_FILTER_CATEGORY.fromJson(optJSONArray2.getJSONObject(i2));
                                if (this.e.equals(fromJson.getParent_id())) {
                                    this.c.add(fromJson);
                                }
                            }
                        }
                        optJSONArray2 = optJSONObject.optJSONArray("brand_filter");
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                this.a.add(ECJia_FILTER_BRAND.fromJson(optJSONArray2.getJSONObject(i2)));
                            }
                        }
                        optJSONArray2 = optJSONObject.optJSONArray("price_filter");
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                this.b.add(ECJia_FILTER_PRICE.fromJson(optJSONArray2.getJSONObject(i2)));
                            }
                        }
                        optJSONArray = optJSONObject.optJSONArray("attr_filter");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.d.add(ECJia_FILTER_ATTR.fromJson(optJSONArray.getJSONObject(i)));
                                i++;
                            }
                            break;
                        }
                    }
                    break;
                case 3:
                    if (this.r.b() == 1) {
                        this.c.clear();
                        optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.c.add(ECJia_FILTER_CATEGORY.fromJson(optJSONArray.getJSONObject(i)));
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
