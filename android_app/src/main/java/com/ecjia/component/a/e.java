package com.ecjia.component.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import com.ecjia.a.aa;
import com.ecjia.a.h;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.a.b;
import com.ecjia.component.a.a.c;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.model.ECJia_DEVICE;
import com.ecjia.hamster.model.ECJia_LOCATION;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.tencent.tauth.Tencent;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaBaseModel */
public class e implements a, b {
    private ArrayList<a> a = new ArrayList();
    public String k;
    public d l;
    public ECJiaApplication m;
    public Context n;
    public ECJia_DEVICE o;
    public String p;
    public String q;
    public ax r;
    public c s;

    public e(Context context) {
        this.n = context;
        this.m = (ECJiaApplication) this.n.getApplicationContext();
        this.s = new c();
        this.l = d.a(context);
        this.l.a(this.m.getString(R.string.loading));
        this.k = aa.a(this.n, "location", "area_id");
        this.o = (ECJia_DEVICE) aa.b(this.n, "deviceInfo", "device");
        if (this.o == null) {
            this.o = new ECJia_DEVICE();
        } else if (TextUtils.isEmpty(this.o.getUdid()) || TextUtils.isEmpty(this.o.getClient()) || TextUtils.isEmpty(this.o.getCode())) {
            this.o.setUdid(h.a(this.n));
            this.o.setClient(anet.channel.strategy.dispatch.c.ANDROID);
            this.o.setCode("4001");
        }
    }

    public String d() {
        this.p = aa.a(this.n, Constants.KEY_USER_ID, "sid");
        if (TextUtils.isEmpty(this.p)) {
            this.p = aa.a(this.n, Constants.KEY_USER_ID, "shop_token");
        }
        return this.p;
    }

    public String e() {
        this.k = aa.a(this.n, "location", "area_id");
        return this.k;
    }

    public ECJia_LOCATION f() {
        ECJia_LOCATION eCJia_LOCATION = new ECJia_LOCATION();
        eCJia_LOCATION.setLatitude(com.ecjia.consts.b.g[1] + "");
        eCJia_LOCATION.setLongitude(com.ecjia.consts.b.g[0] + "");
        return eCJia_LOCATION;
    }

    public void e(String str) {
        k kVar;
        try {
            ax a = ax.a(new JSONObject(str).optJSONObject("status"));
            if (a.b() != 1) {
                Resources resources = this.n.getResources();
                String string = resources.getString(R.string.delivery);
                String string2 = resources.getString(R.string.collected);
                String string3 = resources.getString(R.string.understock);
                String string4 = resources.getString(R.string.been_used);
                String string5 = resources.getString(R.string.submit_the_parameter_error);
                String string6 = resources.getString(R.string.failed);
                String string7 = resources.getString(R.string.purchase_failed);
                String string8 = resources.getString(R.string.no_shipping_information);
                String d = a.d();
                if (a.c() == 100) {
                    this.m.c();
                    this.m.c = true;
                    ap.c().a = "";
                    ap.c().b = "";
                    aa.a(this.n, Constants.KEY_USER_ID, "uid", "");
                    aa.a(this.n, Constants.KEY_USER_ID, "sid", "");
                    ((Activity) this.n).startActivityForResult(new Intent(this.n, ECJiaLoginActivity.class), 4096);
                    ((Activity) this.n).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                } else if (a.c() == Tencent.REQUEST_LOGIN) {
                    kVar = new k(this.n, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else if (a.c() == 10007) {
                    kVar = new k(this.n, string2);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else if (a.c() == 10008) {
                    kVar = new k(this.n, string3);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else if (a.c() == 11) {
                    kVar = new k(this.n, string4);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else if (a.c() == 101) {
                    kVar = new k(this.n, string5);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else if (a.c() == 8) {
                    kVar = new k(this.n, string6);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else if (a.c() == 14) {
                    kVar = new k(this.n, string7);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else if (a.c() == 10009) {
                    kVar = new k(this.n, string8);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else {
                    kVar = new k(this.n, d);
                    kVar.a(17, 0, 0);
                    kVar.a();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            kVar = new k(this.n, "数据好像走丢了呢");
            kVar.a(17, 0, 0);
            kVar.a();
        }
    }

    public void g() {
        q.b("===closeDialog===");
        try {
            if (this.l != null && this.l.isShowing()) {
                this.l.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(a aVar) {
        if (!this.a.contains(aVar)) {
            this.a.add(aVar);
        }
    }

    public void b(a aVar) {
        this.a.remove(aVar);
    }

    public void h() {
        this.a.clear();
    }

    public void a(String str, String str2, ax axVar) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(str, str2, axVar);
        }
    }

    public void i() {
        g();
    }

    public void a(String str, String str2) {
        i();
    }

    public boolean a(com.ecjia.hamster.model.aa aaVar) {
        return aaVar != null && aaVar.a() == 1;
    }
}
