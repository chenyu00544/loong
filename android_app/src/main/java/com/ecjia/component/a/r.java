package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.a.ab;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_ADSENSE_GROUP;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ac;
import com.ecjia.hamster.model.ad;
import com.ecjia.hamster.model.ah;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.au;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.az;
import com.ecjia.hamster.model.bd;
import com.ecjia.hamster.model.c;
import com.ecjia.hamster.model.g;
import com.ecjia.hamster.model.s;
import com.ecjia.hamster.model.y;
import com.umeng.analytics.pro.x;
import java.io.PrintStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaHomeModel */
public class r extends e {
    public String A;
    public String B;
    private boolean C;
    private PrintStream D = null;
    public ArrayList<au> a = new ArrayList();
    public ArrayList<au> b = new ArrayList();
    public ArrayList<g> c = new ArrayList();
    public ArrayList<ad> d = new ArrayList();
    public ArrayList<c> e = new ArrayList();
    public ArrayList<s> f = new ArrayList();
    public ArrayList<y> g = new ArrayList();
    public ArrayList<ah> h = new ArrayList();
    public ArrayList<az> i = new ArrayList();
    public aa j;
    public ArrayList<ao> t = new ArrayList();
    public ArrayList<ah> u = new ArrayList();
    public ArrayList<ECJia_ADSENSE_GROUP> v = new ArrayList();
    public ArrayList<ac> w = new ArrayList();
    public ArrayList<bd> x = new ArrayList();
    String y;
    public String z;

    /* compiled from: ECJiaHomeModel */
    class r_1 implements OnCancelListener {
        final /* synthetic */ r a;

        r_1(r rVar) {
            this.a = rVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public r(Context context) {
        super(context);
        this.s.a((b) this);
        this.y = this.n.getPackageName();
        this.B = context.getCacheDir() + "/ECJia/cache";
    }

    public void a() {
        this.q = "home/data";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("area_id", e());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new r_1(this));
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
                case -1494392599:
                    if (str.equals("goods/suggestlist")) {
                        i2 = 1;
                        break;
                    }
                    break;
                case 2073394906:
                    if (str.equals("home/data")) {
                        i2 = 0;
                        break;
                    }
                    break;
            }
            JSONArray optJSONArray;
            switch (i2) {
                case 0:
                    if (this.r.b() == 1) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            int length;
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("player");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                this.d.clear();
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    this.d.add(ad.a(optJSONArray2.getJSONObject(i2)));
                                }
                            }
                            optJSONArray2 = optJSONObject.optJSONArray("mobile_menu");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                this.h.clear();
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    this.h.add(ah.a(optJSONArray2.getJSONObject(i2)));
                                }
                            }
                            this.x.clear();
                            optJSONArray2 = optJSONObject.optJSONArray("toutiao");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    this.x.add(bd.a(optJSONArray2.optJSONObject(i2)));
                                }
                            }
                            optJSONArray2 = optJSONObject.optJSONArray("adsense_group");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                this.v.clear();
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    this.v.add(ECJia_ADSENSE_GROUP.fromJson(optJSONArray2.getJSONObject(i2)));
                                }
                            }
                            optJSONArray2 = optJSONObject.optJSONArray("promote_goods");
                            if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                                this.a.clear();
                            } else {
                                this.a.clear();
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    this.a.add(au.a(optJSONArray2.getJSONObject(i2)));
                                }
                            }
                            optJSONArray2 = optJSONObject.optJSONArray("new_goods");
                            if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                                this.b.clear();
                            } else {
                                this.b.clear();
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    this.b.add(au.a(optJSONArray2.getJSONObject(i2)));
                                }
                            }
                            this.e.clear();
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject("mobile_home_adsense1");
                            if (optJSONObject2 != null) {
                                this.e.add(0, c.a(optJSONObject2));
                            }
                            optJSONArray2 = optJSONObject.optJSONArray("mobile_home_adsense2");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    this.e.add(c.a(optJSONArray2.optJSONObject(i2)));
                                }
                            }
                            this.f.clear();
                            optJSONArray2 = optJSONObject.optJSONArray("group_goods");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                length = optJSONArray2.length();
                                for (i2 = 0; i2 < length; i2++) {
                                    this.f.add(s.a(optJSONArray2.optJSONObject(i2)));
                                }
                            }
                            this.g.clear();
                            optJSONArray2 = optJSONObject.optJSONArray("mobile_buy_goods");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                length = optJSONArray2.length();
                                for (i2 = 0; i2 < length; i2++) {
                                    this.g.add(y.a(optJSONArray2.optJSONObject(i2)));
                                }
                            }
                            this.t.clear();
                            optJSONArray2 = optJSONObject.optJSONArray("seller_recommend");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    this.t.add(ao.a(optJSONArray2.optJSONObject(i2)));
                                }
                            }
                            optJSONArray2 = optJSONObject.optJSONArray("mobile_topic_adsense");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                this.u.clear();
                                length = optJSONArray2.length();
                                for (i2 = 0; i2 < length; i2++) {
                                    this.u.add(ah.a(optJSONArray2.optJSONObject(i2)));
                                }
                            }
                            optJSONObject2 = optJSONObject.optJSONObject("spike_goods");
                            this.w.clear();
                            if (optJSONObject2 != null) {
                                this.z = optJSONObject2.optString("begin_time");
                                this.A = ab.j(optJSONObject2.optString(x.X));
                                optJSONArray = optJSONObject2.optJSONArray("goods_list");
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    while (i < optJSONArray.length()) {
                                        this.w.add(ac.a(optJSONArray.optJSONObject(i)));
                                        i++;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case 1:
                    if (this.r.b() == 1) {
                        if (this.C) {
                            this.i.clear();
                        }
                        optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.i.add(az.a(optJSONArray.optJSONObject(i)));
                                i++;
                            }
                        }
                    }
                    this.j = aa.a(jSONObject.optJSONObject("paginated"));
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
