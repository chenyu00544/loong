package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.p;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.af;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ar;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaShopDetailActivity extends a implements OnClickListener, a {
    private String A;
    private String B;
    private c C;
    private ar D;
    private String E;
    private SharedPreferences F;
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private ImageView u;
    private ImageView v;
    private ImageView w;
    private LinearLayout x;
    private LinearLayout y;
    private af z;

    class ECJiaShopDetailActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaShopDetailActivity a;

        ECJiaShopDetailActivity_2(ECJiaShopDetailActivity eCJiaShopDetailActivity) {
            this.a = eCJiaShopDetailActivity;
        }

        public void onClick(View view) {
            this.a.C.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_shop_detail2);
        de.greenrobot.event.c.a().a((Object) this);
        Intent intent = getIntent();
        this.D = new ar();
        try {
            this.D = ar.a(new JSONObject(intent.getStringExtra("shopHomeData")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.B = this.D.a();
        this.F = getSharedPreferences(Constants.KEY_USER_ID, 0);
        b();
        c();
        if (this.z == null) {
            this.z = new af(this);
        }
        this.z.a((a) this);
    }

    private void b() {
        this.a = (TextView) findViewById(R.id.top_view_text);
        this.a.setText(this.g.getString(R.string.shop_detail));
        this.b = (TextView) findViewById(R.id.shopdetail_shop_name);
        this.c = (TextView) findViewById(R.id.shopdetail_shop_colloct);
        this.d = (TextView) findViewById(R.id.tv_allgoods);
        this.e = (TextView) findViewById(R.id.tv_newgoods);
        this.k = (TextView) findViewById(R.id.tv_promote);
        this.l = (TextView) findViewById(R.id.tv_news);
        this.n = (TextView) findViewById(R.id.tv_goodsscore);
        this.o = (TextView) findViewById(R.id.tv_logisticsscore);
        this.p = (TextView) findViewById(R.id.tv_servicescore);
        this.q = (TextView) findViewById(R.id.tv_company_phone);
        this.r = (TextView) findViewById(R.id.tv_company_name);
        this.s = (TextView) findViewById(R.id.tv_company_area);
        this.t = (TextView) findViewById(R.id.tv_company_notice);
        this.m = (TextView) findViewById(R.id.tv_collect);
        this.w = (ImageView) findViewById(R.id.iv_collect);
        this.u = (ImageView) findViewById(R.id.top_view_back);
        this.u.setOnClickListener(this);
        this.v = (ImageView) findViewById(R.id.shopdetail_shop_img);
        this.x = (LinearLayout) findViewById(R.id.ll_collect);
        this.x.setOnClickListener(this);
        this.y = (LinearLayout) findViewById(R.id.ll_company_phone);
        this.y.setOnClickListener(this);
    }

    private void c() {
        this.A = this.D.i();
        if ("0".equals(this.A)) {
            this.x.setBackgroundResource(R.drawable.shape_shopuncollect);
            this.m.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            this.m.setGravity(19);
            this.w.setVisibility(0);
            this.m.setText(this.g.getString(R.string.shop_uncollected));
        } else {
            this.x.setBackgroundResource(R.drawable.shape_shopcollect);
            this.m.setTextColor(this.g.getColor(R.color.white));
            this.m.setGravity(17);
            this.w.setVisibility(8);
            this.m.setText(this.g.getString(R.string.shop_collected));
        }
        p.a((Context) this).a(this.v, this.D.c());
        this.b.setText(this.D.b());
        this.c.setText(this.D.n() + this.g.getString(R.string.follower_num));
        if (TextUtils.isEmpty(this.D.k().a())) {
            this.d.setText("0");
        } else {
            this.d.setText(this.D.k().a());
        }
        if (TextUtils.isEmpty(this.D.k().b())) {
            this.e.setText("0");
        } else {
            this.e.setText(this.D.k().b());
        }
        if (TextUtils.isEmpty(this.D.k().c())) {
            this.k.setText("0");
        } else {
            this.k.setText(this.D.k().c());
        }
        if (TextUtils.isEmpty(this.D.k().d())) {
            this.l.setText("0");
        } else {
            this.l.setText(this.D.k().d());
        }
        if (TextUtils.isEmpty(this.D.j().a())) {
            this.n.setText("暂无评分");
        } else {
            this.n.setText(this.D.j().a());
        }
        if (TextUtils.isEmpty(this.D.j().c())) {
            this.o.setText("暂无评分");
        } else {
            this.o.setText(this.D.j().c());
        }
        if (TextUtils.isEmpty(this.D.j().b())) {
            this.p.setText("暂无评分");
        } else {
            this.p.setText(this.D.j().b());
        }
        if (TextUtils.isEmpty(this.D.g())) {
            this.q.setText("暂无");
        } else {
            this.q.setText(this.D.g());
        }
        if (TextUtils.isEmpty(this.D.d())) {
            this.r.setText("暂无");
        } else {
            this.r.setText(this.D.d());
        }
        if (TextUtils.isEmpty(this.D.f())) {
            this.s.setText("暂无");
        } else {
            this.s.setText(this.D.f());
        }
        if (TextUtils.isEmpty(this.D.h())) {
            this.t.setText("暂无");
        } else {
            this.t.setText(this.D.h());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_collect:
                this.E = this.F.getString("uid", "");
                if ("".equals(this.E)) {
                    startActivity(new Intent(this, ECJiaLoginActivity.class));
                    overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                } else if ("0".equals(this.A)) {
                    this.z.c(this.B);
                    de.greenrobot.event.c.a().c(new b("add_collect_seller", this.B));
                    return;
                } else {
                    this.z.d(this.B);
                    de.greenrobot.event.c.a().c(new b("minus_collect_seller", this.B));
                    return;
                }
            case R.id.ll_company_phone:
                final String charSequence = this.q.getText().toString();
                String string = this.g.getString(R.string.setting_call_or_not);
                if (!TextUtils.isEmpty(charSequence)) {
                    this.C = new c(this, string, charSequence);
                    this.C.a();
                    this.C.b.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaShopDetailActivity b;

                        public void onClick(View view) {
                            this.b.C.b();
                            this.b.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + charSequence)));
                        }
                    });
                    this.C.d.setOnClickListener(new ECJiaShopDetailActivity_2(this));
                    return;
                }
                return;
            case R.id.top_view_back:
                finish();
                return;
            default:
                return;
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("seller/collect/create")) {
            if (axVar.b() == 1) {
                this.x.setBackgroundResource(R.drawable.shape_shopcollect);
                this.m.setTextColor(this.g.getColor(R.color.white));
                this.m.setGravity(17);
                this.w.setVisibility(8);
                this.m.setText(this.g.getString(R.string.shop_collected));
                this.A = "1";
                this.D.a(Integer.valueOf(this.D.n().intValue() + 1));
                this.c.setText(this.D.n() + this.g.getString(R.string.follower_num));
                de.greenrobot.event.c.a().c(new b("collectrefresh"));
                new k((Context) this, getResources().getString(R.string.collection_success)).a();
            }
        } else if (str.equals("seller/collect/delete") && axVar.b() == 1) {
            new k((Context) this, getResources().getString(R.string.del_collection_success)).a();
            this.x.setBackgroundResource(R.drawable.shape_shopuncollect);
            this.m.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            this.m.setGravity(19);
            this.w.setVisibility(0);
            this.m.setText(this.g.getString(R.string.shop_uncollected));
            this.A = "0";
            this.D.a(Integer.valueOf(this.D.n().intValue() - 1));
            this.c.setText(this.D.n() + this.g.getString(R.string.follower_num));
            de.greenrobot.event.c.a().c(new b("collectrefresh"));
        }
    }
}
