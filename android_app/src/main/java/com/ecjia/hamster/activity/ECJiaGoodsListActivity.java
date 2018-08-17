package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.a.d;
import com.ecjia.component.a.o;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.an;
import com.ecjia.hamster.adapter.s;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ECJia_FILTER_ATTR;
import com.ecjia.hamster.model.ECJia_FILTER_BRAND;
import com.ecjia.hamster.model.ECJia_FILTER_CATEGORY;
import com.ecjia.hamster.model.ECJia_FILTER_PRICE;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import org.apache.commons.lang3.c;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaGoodsListActivity extends a implements OnClickListener, com.ecjia.component.a.a.a, com.ecjia.component.view.ECJiaXListView.a {
    public static String a = "price_desc";
    public static String b = "price_asc";
    public static String c = "is_hot";
    public static String d = "is_new";
    private View A;
    private TextView B;
    private String C;
    private a D;
    private a E;
    private a F;
    private a G;
    private ECJia_FILTER H = new ECJia_FILTER();
    private ImageView I;
    private EditText J;
    private ImageView K;
    private View L;
    private View M;
    private View N;
    private View O;
    private d P;
    private boolean Q = false;
    private boolean R = false;
    private boolean S = false;
    private float T = 0.0f;
    private FrameLayout U;
    private String V;
    private FrameLayout W;
    public String e;
    public int k;
    public boolean l = false;
    public ArrayList<ECJia_FILTER_CATEGORY> m = new ArrayList();
    public ArrayList<ECJia_FILTER_BRAND> n = new ArrayList();
    public ArrayList<ECJia_FILTER_PRICE> o = new ArrayList();
    public ArrayList<ECJia_FILTER_ATTR> p = new ArrayList();
    private ImageView q;
    private ImageView r;
    private ECJiaXListView s;
    private o t;
    private an u;
    private s v;
    private int w = -1;
    private boolean x = true;
    private SharedPreferences y;
    private Editor z;

    class ECJiaGoodsListActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsListActivity a;

        ECJiaGoodsListActivity_1(ECJiaGoodsListActivity eCJiaGoodsListActivity) {
            this.a = eCJiaGoodsListActivity;
        }

        public void onClick(View view) {
            if (TextUtils.isEmpty(this.a.V)) {
                Intent intent = new Intent();
                intent.setClass(this.a, ECJiaSearchNewActivity.class);
                intent.putExtra("filter", this.a.H);
                this.a.startActivityForResult(intent, 100);
                return;
            }
            this.a.finish();
        }
    }

    class ECJiaGoodsListActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsListActivity a;

        ECJiaGoodsListActivity_2(ECJiaGoodsListActivity eCJiaGoodsListActivity) {
            this.a = eCJiaGoodsListActivity;
        }

        public void onClick(View view) {
            this.a.f();
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    class ECJiaGoodsListActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsListActivity a;

        ECJiaGoodsListActivity_3(ECJiaGoodsListActivity eCJiaGoodsListActivity) {
            this.a = eCJiaGoodsListActivity;
        }

        public void onClick(View view) {
            String string = this.a.y.getString("uid", "");
            this.a.f();
            if (string.equals("")) {
                this.a.startActivity(new Intent(this.a, ECJiaLoginActivity.class));
                this.a.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                k kVar = new k(this.a, this.a.g.getString(R.string.no_login));
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            }
            this.a.startActivity(new Intent(this.a, ECJiaShoppingCartActivity.class));
        }
    }

    class ECJiaGoodsListActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsListActivity a;

        ECJiaGoodsListActivity_4(ECJiaGoodsListActivity eCJiaGoodsListActivity) {
            this.a = eCJiaGoodsListActivity;
        }

        public void onClick(View view) {
            this.a.c(-1);
        }
    }

    class ECJiaGoodsListActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsListActivity a;

        ECJiaGoodsListActivity_5(ECJiaGoodsListActivity eCJiaGoodsListActivity) {
            this.a = eCJiaGoodsListActivity;
        }

        public void onClick(View view) {
            this.a.c(0);
        }
    }

    class ECJiaGoodsListActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsListActivity a;

        ECJiaGoodsListActivity_6(ECJiaGoodsListActivity eCJiaGoodsListActivity) {
            this.a = eCJiaGoodsListActivity;
        }

        public void onClick(View view) {
            this.a.w = 1;
            this.a.c(1);
        }
    }

    protected class a {
        public TextView a;
        public ImageView b;
        public RelativeLayout c;
        final /* synthetic */ ECJiaGoodsListActivity d;

        protected a(ECJiaGoodsListActivity eCJiaGoodsListActivity) {
            this.d = eCJiaGoodsListActivity;
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.goodslist_activity);
        PushAgent.getInstance(this).onAppStart();
        h();
        this.J = (EditText) findViewById(R.id.search_input);
        this.K = (ImageView) findViewById(R.id.search_filter);
        this.L = findViewById(R.id.filter_one);
        this.M = findViewById(R.id.filter_two);
        this.N = findViewById(R.id.filter_three);
        this.O = findViewById(R.id.filter_four);
        this.s = (ECJiaXListView) findViewById(R.id.goods_listview);
        this.U = (FrameLayout) findViewById(R.id.ll_goodlist_top);
        this.I = (ImageView) findViewById(R.id.search_search);
        this.I.setOnClickListener(this);
        this.J.setImeOptions(3);
        this.J.setInputType(1);
        this.J.setFocusable(false);
        this.J.setOnClickListener(new ECJiaGoodsListActivity_1(this));
        this.V = getIntent().getStringExtra("keyword");
        if (!TextUtils.isEmpty(this.V)) {
            this.J.setText(this.V);
            this.H.setKeywords(this.V);
        }
        this.C = getIntent().getStringExtra("category_id");
        if (!TextUtils.isEmpty(this.C)) {
            this.H.setCategory_id(this.C);
            this.e = this.C;
        }
        this.K.setOnClickListener(this);
        this.y = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.z = this.y.edit();
        this.k = this.y.getInt("goodlist_type", 3);
        this.q = (ImageView) findViewById(R.id.nav_back_button);
        this.q.setOnClickListener(new ECJiaGoodsListActivity_2(this));
        this.r = (ImageView) findViewById(R.id.good_list_shopping_cart);
        this.r.setOnClickListener(new ECJiaGoodsListActivity_3(this));
        this.B = (TextView) findViewById(R.id.shopping_cart_num);
        this.A = findViewById(R.id.null_pager);
        this.s.setPullLoadEnable(true);
        this.s.setRefreshTime();
        this.s.setXListViewListener(this, 1);
        this.t = new o(this);
        this.t.a((com.ecjia.component.a.a.a) this);
        if (this.u == null) {
            this.u = new an(this, this.t.a);
        }
        if (this.v == null) {
            this.v = new s(this, this.t.a);
        }
        this.l = true;
        if (this.l) {
            if (this.k == 1) {
                this.K.setBackgroundResource(R.drawable.goodlist_choose1);
                this.s.setAdapter(this.u);
            } else if (this.k == 3) {
                this.K.setBackgroundResource(R.drawable.goodlist_choose3);
                this.s.setAdapter(this.v);
            }
            this.l = false;
        }
        this.D = new a(this);
        this.E = new a(this);
        this.F = new a(this);
        this.G = new a(this);
        this.D.a = (TextView) findViewById(R.id.filter_title_tabone);
        this.D.b = (ImageView) findViewById(R.id.filter_order_tabone);
        this.D.c = (RelativeLayout) findViewById(R.id.tabOne);
        this.D.c.setOnClickListener(new ECJiaGoodsListActivity_4(this));
        this.E.a = (TextView) findViewById(R.id.filter_title_tabtwo);
        this.E.b = (ImageView) findViewById(R.id.filter_order_tabtwo);
        this.E.c = (RelativeLayout) findViewById(R.id.tabTwo);
        this.E.c.setOnClickListener(new ECJiaGoodsListActivity_5(this));
        this.F.a = (TextView) findViewById(R.id.filter_title_tabthree);
        this.F.b = (ImageView) findViewById(R.id.filter_order_tabthree);
        this.F.c = (RelativeLayout) findViewById(R.id.tabThree);
        this.F.c.setOnClickListener(new ECJiaGoodsListActivity_6(this));
        this.G.a = (TextView) findViewById(R.id.filter_title_tabfour);
        this.G.b = (ImageView) findViewById(R.id.filter_order_tabfour);
        this.G.c = (RelativeLayout) findViewById(R.id.tabfour);
        this.G.c.setOnClickListener(this);
        this.P = new d(this);
        this.P.a((com.ecjia.component.a.a.a) this);
        if (!TextUtils.isEmpty(this.C)) {
            this.P.c(this.C);
        }
        c(-1);
    }

    private void h() {
        this.W = (FrameLayout) findViewById(R.id.fl_midLayout);
    }

    void c(int i) {
        q.a("运行==");
        this.x = true;
        ColorStateList colorStateList = this.g.getColorStateList(R.color.filter_text_color);
        if (i == -1) {
            this.D.b.setImageResource(R.drawable.item_grid_filter_down_active_arrow);
            this.D.b.setWillNotCacheDrawing(true);
            this.D.a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.E.a.setTextColor(colorStateList);
            this.G.a.setTextColor(colorStateList);
            this.F.a.setTextColor(colorStateList);
            this.H.setSort_by(d);
            this.t.a(this.H, true);
            this.L.setVisibility(0);
            this.M.setVisibility(8);
            this.N.setVisibility(8);
            this.O.setVisibility(8);
        } else if (i == 0) {
            this.E.b.setImageResource(R.drawable.item_grid_filter_down_active_arrow);
            this.E.a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.D.a.setTextColor(colorStateList);
            this.F.a.setTextColor(colorStateList);
            this.G.a.setTextColor(colorStateList);
            this.H.setSort_by(c);
            this.t.a(this.H, true);
            this.L.setVisibility(8);
            this.M.setVisibility(0);
            this.N.setVisibility(8);
            this.O.setVisibility(8);
        } else if (i == 1) {
            this.F.a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.D.a.setTextColor(colorStateList);
            this.G.a.setTextColor(colorStateList);
            this.E.a.setTextColor(colorStateList);
            if (this.H.getSort_by().equals("price_asc")) {
                this.H.setSort_by(a);
                this.F.b.setImageResource(R.drawable.goodlist_buttom);
            } else if (this.H.getSort_by().equals("price_desc")) {
                this.H.setSort_by(b);
                this.F.b.setImageResource(R.drawable.goodlist_top);
            } else {
                this.H.setSort_by(b);
                this.F.b.setImageResource(R.drawable.goodlist_top);
            }
            this.t.a(this.H, true);
            this.L.setVisibility(8);
            this.M.setVisibility(8);
            this.N.setVisibility(0);
            this.O.setVisibility(8);
        } else if (i != 3) {
        }
    }

    public void b() {
        if (this.t.a.size() == 0) {
            this.A.setVisibility(0);
            this.s.setVisibility(8);
        } else {
            this.s.setVisibility(0);
            this.A.setVisibility(8);
            if (this.k == 3) {
                this.v.notifyDataSetChanged();
                this.v.a(this.t.a);
            } else if (this.k == 1) {
                this.u.notifyDataSetChanged();
                this.u.a(this.t.a);
            }
        }
        if ("".equals(this.y.getString("uid", "")) || this.h.g() == 0) {
            this.B.setVisibility(8);
        } else {
            e();
        }
    }

    public void onClick(View view) {
        this.g = getBaseContext().getResources();
        this.g.getString(R.string.goodlist_network_problem);
        switch (view.getId()) {
            case R.id.search_filter:
                f();
                g();
                return;
            case R.id.tabfour:
                c();
                return;
            default:
                return;
        }
    }

    public void c() {
        c(3);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(10);
        this.W.setAnimation(alphaAnimation);
        this.W.setVisibility(0);
        alphaAnimation.start();
        Intent intent = new Intent(this, ECJiaFilterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("origin_category", this.C);
        bundle.putSerializable(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY, this.m);
        bundle.putSerializable(Constants.KEY_BRAND, this.n);
        bundle.putSerializable("price", this.o);
        bundle.putSerializable("filter_attr", this.p);
        intent.putExtra("data", bundle);
        startActivityForResult(intent, 1);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_buttom_out);
    }

    public void a(int i) {
        this.x = true;
        this.t.a(this.H, false);
        this.P.a();
        this.P.a(this.e);
        if (this.e == null || c.a(this.e)) {
            this.P.b("0");
        } else {
            this.P.b(this.e);
        }
    }

    public void b(int i) {
        this.x = false;
        this.t.a(this.H);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Animation alphaAnimation;
        switch (i) {
            case 1:
                if (intent != null) {
                    String stringExtra = intent.getStringExtra("filter");
                    Bundle bundleExtra = intent.getBundleExtra("data");
                    this.m.clear();
                    this.n.clear();
                    this.o.clear();
                    this.p.clear();
                    this.m = (ArrayList) bundleExtra.getSerializable(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY);
                    this.n = (ArrayList) bundleExtra.getSerializable(Constants.KEY_BRAND);
                    this.o = (ArrayList) bundleExtra.getSerializable("price");
                    this.p = (ArrayList) bundleExtra.getSerializable("filter_attr");
                    if (stringExtra != null) {
                        try {
                            ECJia_FILTER fromJson = ECJia_FILTER.fromJson(new JSONObject(stringExtra));
                            this.H.setCategory_id(fromJson.getCategory_id());
                            this.H.setPrice_range(fromJson.getPrice_range());
                            this.H.setBrand_id(fromJson.getBrand_id());
                            this.H.setFilter_attr(fromJson.getFilter_attr());
                            this.t.a(this.H, true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(10);
                this.W.setAnimation(alphaAnimation);
                alphaAnimation.start();
                this.W.setVisibility(8);
                break;
            case 100:
                if (i2 == -1 && intent != null) {
                    this.V = intent.getStringExtra("keyword");
                    this.J.setText(this.V);
                    this.H.setKeywords(this.V);
                    this.t.a(this.H, true);
                    break;
                }
        }
        alphaAnimation = new TranslateAnimation(0.0f, 0.0f, -this.T, 0.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.85f, 1.0f, 1.0f, 1.0f);
        TranslateAnimation translateAnimation = new TranslateAnimation(-150.0f, 0.0f, 0.0f, 0.0f);
        alphaAnimation.setDuration(200);
        scaleAnimation.setDuration(150);
        translateAnimation.setDuration(150);
        alphaAnimation.setFillAfter(true);
        scaleAnimation.setFillAfter(true);
        translateAnimation.setFillAfter(true);
        this.U.startAnimation(alphaAnimation);
    }

    protected void onResume() {
        super.onResume();
        e();
    }

    public void e() {
        if (this.h.g() == 0) {
            this.B.setVisibility(8);
            return;
        }
        this.B.setVisibility(0);
        if (this.h.g() > 0 && this.h.g() <= 99) {
            this.B.setText(this.h.g() + "");
        } else if (this.h.g() > 99) {
            this.B.setText("99+");
        }
    }

    public void f() {
        if (c.a(getIntent().getStringExtra("keyword"))) {
            this.J.clearFocus();
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.J.getWindowToken(), 0);
        }
    }

    public void g() {
        if (this.k == 1) {
            this.k = 3;
            this.s.setAdapter(this.v);
            this.K.setBackgroundResource(R.drawable.goodlist_choose3);
        } else if (this.k == 3) {
            this.k = 1;
            this.s.setAdapter(this.u);
            this.K.setBackgroundResource(R.drawable.goodlist_choose1);
        }
    }

    protected void onPause() {
        super.onPause();
        this.z.putInt("goodlist_type", this.k);
        this.z.commit();
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1834268591:
                if (str.equals("goods/filter")) {
                    z = true;
                    break;
                }
                break;
            case 168458797:
                if (str.equals("cart/list")) {
                    z = true;
                    break;
                }
                break;
            case 248549303:
                if (str.equals("goods/list")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.s.stopRefresh();
                    this.s.stopLoadMore();
                    this.s.setRefreshTime();
                    b();
                    if (this.t.a(this.t.h)) {
                        this.s.setPullLoadEnable(true);
                        return;
                    } else {
                        this.s.setPullLoadEnable(false);
                        return;
                    }
                }
                return;
            case true:
                this.m.clear();
                this.n.clear();
                this.o.clear();
                this.p.clear();
                this.m.addAll(this.P.c);
                this.n.addAll(this.P.a);
                this.o.addAll(this.P.b);
                this.p.addAll(this.P.d);
                return;
            case true:
                e();
                return;
            default:
                return;
        }
    }
}
