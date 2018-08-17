package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.component.a.af;
import com.ecjia.component.a.ag;
import com.ecjia.component.view.ECJiaMyListView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.g;
import com.ecjia.component.view.k;
import com.ecjia.consts.b;
import com.ecjia.hamster.adapter.an;
import com.ecjia.hamster.adapter.bk;
import com.ecjia.hamster.adapter.bm;
import com.ecjia.hamster.adapter.s;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.ECJia_FAVOUR;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ECJia_PRICE_RANGE;
import com.ecjia.hamster.model.ar;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.f;
import com.ecjia.hamster.model.w;
import com.ecmoban.android.missmall.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.util.ArrayList;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaShopListActivity extends a implements OnClickListener, com.ecjia.component.a.a.a, com.ecjia.component.view.ECJiaXListView.a {
    private FrameLayout A;
    private FrameLayout B;
    private ECJiaXListView C;
    private ag D;
    private an E;
    private s F;
    private ImageView G;
    private boolean H;
    private SharedPreferences I;
    private Editor J;
    private View K;
    private af L;
    private ExpandableListView M;
    private LinearLayout N;
    private LinearLayout O;
    private RelativeLayout P;
    private LinearLayout Q;
    private TextView R;
    private LinearLayout S;
    private LinearLayout T;
    private int U;
    private String V;
    private String W;
    private String X;
    private TextView Y;
    private TextView Z;
    public String a;
    private TextView aA;
    private TextView aB;
    private TextView aC;
    private TextView aD;
    private LinearLayout aE;
    private ImageView aF;
    private String aG;
    private float aH = 0.0f;
    private boolean aI;
    private int aJ;
    private LinearLayout aK;
    private g aL;
    private LinearLayout aM;
    private TextView aN;
    private TextView aO;
    private ImageView aP;
    private ECJiaMyListView aQ;
    private bk aR;
    private boolean aS;
    private TextView aT;
    private boolean aU;
    private String aa;
    private boolean ab;
    private boolean ac;
    private ar ad;
    private TextView ae;
    private LinearLayout af;
    private ImageView ag;
    private ImageView ah;
    private k ai;
    private View aj;
    private View ak;
    private View al;
    private View am;
    private View an;
    private View ao;
    private bm ap;
    private ArrayList<ECJia_CATEGORY> aq;
    private LinearLayout ar;
    private RelativeLayout as;
    private LinearLayout at;
    private ImageView au;
    private TextView av;
    private String aw;
    private View ax;
    private LinearLayout ay;
    private TextView az;
    public int b;
    boolean c = true;
    public boolean d = false;
    ECJia_FILTER e;
    SlidingMenu k;
    int l;
    TextView m;
    boolean n = false;
    boolean o = false;
    boolean p = false;
    a q;
    a r;
    a s;
    a t;
    a u;
    a v;
    private ImageView w;
    private ImageView x;
    private ImageView y;
    private TextView z;

    class ECJiaShopListActivity_10 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_10(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            if (this.a.aU) {
                Intent intent = new Intent();
                intent.putExtra("collect_num", this.a.U + "");
                this.a.setResult(-1, intent);
            }
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    class ECJiaShopListActivity_11 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_11(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            if (this.a.I.getString("uid", "").equals("")) {
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

    class ECJiaShopListActivity_12 implements OnScrollListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_12(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            switch (i) {
                case 0:
                    this.a.aI = false;
                    return;
                case 1:
                    this.a.aI = true;
                    if (this.a.C.mHeaderView.getVisiableHeight() > 0) {
                        this.a.ay.setVisibility(8);
                        return;
                    }
                    return;
                case 2:
                    this.a.aI = false;
                    return;
                default:
                    return;
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.a.aI) {
                this.a.aJ = i;
            }
            int measuredHeight = (this.a.ar.getMeasuredHeight() + this.a.ay.getMeasuredHeight()) - this.a.A.getMeasuredHeight();
            int b = this.a.b();
            if (b == 0) {
                this.a.B.setAlpha(0.0f);
                this.a.z.setAlpha(0.0f);
                this.a.ay.setVisibility(8);
            } else if (b - measuredHeight < measuredHeight) {
                this.a.aH = new Float((float) (b - measuredHeight)).floatValue() / new Float((float) measuredHeight).floatValue();
                this.a.B.setAlpha(this.a.aH);
                this.a.z.setAlpha(this.a.aH);
                this.a.ay.setVisibility(8);
            } else {
                this.a.B.setAlpha(1.0f);
                this.a.z.setAlpha(1.0f);
                this.a.ay.setVisibility(0);
            }
        }
    }

    class ECJiaShopListActivity_16 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_16(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
        }
    }

    class ECJiaShopListActivity_17 implements OnChildClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_17(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
            int i3;
            if (i == 0) {
                for (i3 = 0; i3 < b.d.size(); i3++) {
                    if (i2 == i3) {
                        ((HashMap) b.d.get(i3)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(true));
                        this.a.e.setBrand_id(((f) this.a.D.a.get(i2)).a() + "");
                    } else {
                        ((HashMap) b.d.get(i3)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(false));
                    }
                }
            } else if (i == 1) {
                for (i3 = 0; i3 < b.e.size(); i3++) {
                    if (i3 == i2) {
                        ((HashMap) b.e.get(i3)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(true));
                        this.a.e.setCategory_id(((ECJia_CATEGORY) this.a.aq.get(i2)).getId() + "");
                    } else {
                        ((HashMap) b.e.get(i3)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(false));
                    }
                }
            } else if (i == 2) {
                for (i3 = 0; i3 < b.f.size(); i3++) {
                    if (i2 == i3) {
                        ((HashMap) b.f.get(i3)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(true));
                        this.a.e.setPrice_range((ECJia_PRICE_RANGE) this.a.D.b.get(i2));
                    } else {
                        ((HashMap) b.f.get(i3)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(false));
                    }
                }
            }
            this.a.ap.notifyDataSetChanged();
            this.a.D.a(this.a.e, this.a.aw);
            this.a.k.toggle();
            return true;
        }
    }

    class ECJiaShopListActivity_1 implements OnScrollListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_1(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i > 1) {
                this.a.ay.setVisibility(0);
            } else {
                this.a.ay.setVisibility(8);
            }
        }
    }

    class ECJiaShopListActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_2(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            int i;
            if (b.d != null && b.d.size() > 0) {
                for (i = 0; i < b.d.size(); i++) {
                    if (((Boolean) ((HashMap) b.d.get(i)).get(AgooConstants.MESSAGE_FLAG)).booleanValue()) {
                        ((HashMap) b.d.get(i)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(false));
                    }
                }
            }
            if (b.f != null && b.f.size() > 0) {
                for (i = 0; i < b.f.size(); i++) {
                    if (((Boolean) ((HashMap) b.f.get(i)).get(AgooConstants.MESSAGE_FLAG)).booleanValue()) {
                        ((HashMap) b.f.get(i)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(false));
                    }
                }
            }
            if (b.e != null && b.e.size() > 0) {
                for (i = 0; i < b.e.size(); i++) {
                    if (((Boolean) ((HashMap) b.e.get(i)).get(AgooConstants.MESSAGE_FLAG)).booleanValue()) {
                        ((HashMap) b.e.get(i)).put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(false));
                    }
                }
            }
            this.a.a = "";
            this.a.e.setBrand_id("");
            this.a.e.setCategory_id("");
            this.a.e.setPrice_range(null);
            this.a.ap.notifyDataSetChanged();
            this.a.D.a(this.a.e, this.a.aw);
            this.a.k.toggle();
        }
    }

    class ECJiaShopListActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_3(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            this.a.e();
        }
    }

    class ECJiaShopListActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_4(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            if (!this.a.ab) {
                this.a.c(-1);
            }
        }
    }

    class ECJiaShopListActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_5(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            if (!this.a.ab) {
                this.a.c(-1);
            }
        }
    }

    class ECJiaShopListActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_6(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            if (!this.a.ac) {
                this.a.c(0);
            }
        }
    }

    class ECJiaShopListActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_7(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            if (!this.a.ac) {
                this.a.c(0);
            }
        }
    }

    class ECJiaShopListActivity_8 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_8(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            this.a.c(1);
        }
    }

    class ECJiaShopListActivity_9 implements OnClickListener {
        final /* synthetic */ ECJiaShopListActivity a;

        ECJiaShopListActivity_9(ECJiaShopListActivity eCJiaShopListActivity) {
            this.a = eCJiaShopListActivity;
        }

        public void onClick(View view) {
            this.a.c(1);
        }
    }

    protected class a {
        public TextView a;
        public ImageView b;
        public RelativeLayout c;
        final /* synthetic */ ECJiaShopListActivity d;

        protected a(ECJiaShopListActivity eCJiaShopListActivity) {
            this.d = eCJiaShopListActivity;
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_shop_detail_new);
        c.a().a((Object) this);
        PushAgent.getInstance(this).onAppStart();
        this.ax = View.inflate(this, R.layout.layout_shop_info, null);
        this.aK = (LinearLayout) findViewById(R.id.layout_custommenu);
        i();
        this.ay = (LinearLayout) findViewById(R.id.stick_toolbar);
        this.ay.setVisibility(8);
        Intent intent = getIntent();
        this.aw = intent.getStringExtra("merchant_id");
        this.C = (ECJiaXListView) findViewById(R.id.goods_listview);
        this.C.addHeaderView(this.ax);
        this.C.setOnScrollListener(new ECJiaShopListActivity_1(this));
        this.ah = (ImageView) findViewById(R.id.search_filter2);
        this.ah.setOnClickListener(this);
        this.P = (RelativeLayout) findViewById(R.id.tabfour2);
        this.P.setOnClickListener(this);
        if (this.L == null) {
            this.L = new af(this);
        }
        this.L.a((com.ecjia.component.a.a.a) this);
        this.L.f(this.aw);
        this.at = (LinearLayout) findViewById(R.id.ll_top_category);
        this.at.setOnClickListener(this);
        this.aF = (ImageView) this.ax.findViewById(R.id.headview_bg);
        a(this.aF);
        this.T = (LinearLayout) findViewById(R.id.ll_top_guide);
        this.T.setOnClickListener(this);
        this.af = (LinearLayout) findViewById(R.id.search_search);
        this.af.setOnClickListener(this);
        this.e = new ECJia_FILTER();
        Object stringExtra = intent.getStringExtra("category_id");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.e.setCategory_id(stringExtra);
        }
        ECJia_FILTER eCJia_FILTER = this.e;
        ag agVar = this.D;
        eCJia_FILTER.setSort_by(ag.d);
        if (!TextUtils.isEmpty(this.e.getCategory_id())) {
            this.a = this.e.getCategory_id();
            q.a("predefine_category_id====" + this.a);
        }
        this.I = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.J = this.I.edit();
        this.b = this.I.getInt("goodlist_type", 1);
        this.A = (FrameLayout) findViewById(R.id.top_view);
        this.B = (FrameLayout) findViewById(R.id.top_view_bg);
        this.z = (TextView) findViewById(R.id.top_view_text);
        this.w = (ImageView) findViewById(R.id.nav_back_button);
        this.w.setOnClickListener(new ECJiaShopListActivity_10(this));
        this.x = (ImageView) findViewById(R.id.good_list_shopping_cart);
        this.x.setOnClickListener(new ECJiaShopListActivity_11(this));
        this.R = (TextView) findViewById(R.id.shopping_cart_num);
        this.Q = (LinearLayout) findViewById(R.id.shopping_cart_num_bg_one);
        this.G = (ImageView) findViewById(R.id.goodslist_bg);
        this.K = findViewById(R.id.null_pager);
        this.C.setPullLoadEnable(false);
        this.C.setPullRefreshEnable(false);
        this.C.setRefreshTime();
        this.C.setXListViewListener(this, 1);
        f();
        this.D = new ag(this);
        this.D.a((com.ecjia.component.a.a.a) this);
        this.D.a(this.aw);
        if (this.E == null) {
            this.E = new an(this, this.D.h);
        }
        this.F = new s(this, this.D.h);
        this.d = true;
        if (this.d) {
            if (this.b == 1) {
                this.C.setAdapter(this.F);
            } else if (this.b == 3) {
                this.C.setAdapter(this.F);
            }
            this.d = false;
        }
        c(-1);
        g();
    }

    private void a(ImageView imageView) {
        int d = d();
        imageView.setLayoutParams(new LayoutParams(d, (d * 1) / 2));
    }

    public int d() {
        return Math.min(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
    }

    private void f() {
        this.C.setOnScrollListener(new ECJiaShopListActivity_12(this));
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case -2073837039:
                if (str.equals("seller/collect/create")) {
                    z = true;
                    break;
                }
                break;
            case -2057001280:
                if (str.equals("seller/collect/delete")) {
                    z = true;
                    break;
                }
                break;
            case -1175639458:
                if (str.equals("merchant/goods/list")) {
                    z = false;
                    break;
                }
                break;
            case -189303981:
                if (str.equals("merchant/home/data")) {
                    z = true;
                    break;
                }
                break;
            case 57300670:
                if (str.equals("merchant/goods/category")) {
                    z = true;
                    break;
                }
                break;
        }
        int i;
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    if (this.c) {
                        this.c = false;
                    }
                    this.C.stopRefresh();
                    this.C.stopLoadMore();
                    this.C.setRefreshTime();
                    c();
                    if (this.D.i.a() == 0) {
                        this.C.setPullLoadEnable(false);
                        return;
                    } else {
                        this.C.setPullLoadEnable(true);
                        return;
                    }
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    new k((Context) this, getResources().getString(R.string.collection_success)).a();
                    this.as.setBackgroundResource(R.drawable.shape_shopcollect);
                    this.Z.setTextColor(this.g.getColor(R.color.white));
                    this.y.setImageResource(R.drawable.shop_collected);
                    this.Z.setText(this.g.getString(R.string.shop_collected));
                    this.V = "1";
                    this.U++;
                    this.ad.a(Integer.valueOf(this.U));
                    this.ad.a("1");
                    this.Y.setText(this.U + this.g.getString(R.string.follower_num));
                    c.a().c(new com.ecjia.a.a.b("collectrefresh"));
                    this.aU = true;
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    new k((Context) this, getResources().getString(R.string.del_collection_success)).a();
                    this.as.setBackgroundResource(R.drawable.shape_shopuncollect);
                    this.Z.setTextColor(this.g.getColor(R.color.normal_dark_gray));
                    this.y.setImageResource(R.drawable.shop_uncollect);
                    this.Z.setText(this.g.getString(R.string.shop_uncollected));
                    this.V = "0";
                    this.U--;
                    this.ad.a(Integer.valueOf(this.U));
                    this.ad.a("0");
                    this.Y.setText(this.U + this.g.getString(R.string.follower_num));
                    c.a().c(new com.ecjia.a.a.b("collectrefresh"));
                    this.aU = true;
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    this.p = true;
                    if (this.aq == null) {
                        this.aq = new ArrayList();
                    }
                    if (this.D.c.size() > 0) {
                        this.aq = this.D.c;
                        if (b.e == null || b.e.size() == 0) {
                            b.e = new ArrayList();
                        }
                        b.e.clear();
                        for (i = 0; i < this.aq.size(); i++) {
                            HashMap hashMap = new HashMap();
                            if (TextUtils.isEmpty(this.a)) {
                                hashMap.put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(false));
                            } else if (this.a.equals(((ECJia_CATEGORY) this.aq.get(i)).getId() + "")) {
                                hashMap.put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(true));
                            } else {
                                hashMap.put(AgooConstants.MESSAGE_FLAG, Boolean.valueOf(false));
                            }
                            b.e.add(hashMap);
                        }
                    }
                    this.n = true;
                    this.o = true;
                    if (this.ap != null) {
                        this.ap.notifyDataSetChanged();
                        return;
                    }
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    CharSequence string = this.g.getString(R.string.data_null);
                    this.V = this.L.f.i();
                    if ("0".equals(this.V)) {
                        this.as.setBackgroundResource(R.drawable.shape_shopuncollect);
                        this.Z.setTextColor(this.g.getColor(R.color.normal_dark_gray));
                        this.y.setImageResource(R.drawable.shop_uncollect);
                        this.Z.setText(this.g.getString(R.string.shop_uncollected));
                    } else {
                        this.as.setBackgroundResource(R.drawable.shape_shopcollect);
                        this.Z.setTextColor(this.g.getColor(R.color.white));
                        this.y.setImageResource(R.drawable.shop_collected);
                        this.Z.setText(this.g.getString(R.string.shop_collected));
                    }
                    this.ad = this.L.f;
                    this.W = this.L.f.c();
                    this.X = this.L.f.b();
                    this.U = this.L.f.n().intValue();
                    this.aG = this.L.f.g();
                    p.a((Context) this).a(this.au, this.W);
                    p.a((Context) this).a(this.aF, this.L.f.e());
                    this.av.setText(this.X);
                    this.z.setText(this.X);
                    this.Y.setVisibility(0);
                    this.Y.setText(this.U + this.g.getString(R.string.follower_num));
                    float a = com.ecjia.a.k.a(this.L.f.m());
                    if (0.0f == a) {
                        this.aT.setText("");
                        this.aT.setVisibility(8);
                    } else {
                        this.aT.setVisibility(0);
                        if (a < 1000.0f) {
                            this.aT.setText(((int) a) + "m");
                        } else {
                            this.aT.setText(com.ecjia.a.k.a(a / 1000.0f) + "km");
                        }
                    }
                    if (!TextUtils.isEmpty(this.L.f.j().a())) {
                        this.az.setText(this.L.f.j().a());
                    }
                    if (!TextUtils.isEmpty(this.L.f.j().c())) {
                        this.aA.setText(this.L.f.j().c());
                    }
                    if (!TextUtils.isEmpty(this.L.f.j().b())) {
                        this.aB.setText(this.L.f.j().b());
                    }
                    if (TextUtils.isEmpty(this.L.f.f())) {
                        this.aC.setText(string);
                    } else {
                        this.aC.setText(this.L.f.f());
                    }
                    if (TextUtils.isEmpty(this.L.f.h())) {
                        this.aD.setText(string);
                    } else {
                        this.aD.setText(this.L.f.h());
                    }
                    a(this.L.f.o());
                    i = this.L.f.p().size();
                    if (i > 0) {
                        this.aM.setVisibility(0);
                        this.aN.setText(((ECJia_FAVOUR) this.L.f.p().get(0)).getType_label());
                        this.aO.setText(((ECJia_FAVOUR) this.L.f.p().get(0)).getName());
                        if (i > 1) {
                            this.aQ.setVisibility(0);
                            this.aP.setVisibility(0);
                            ArrayList arrayList = new ArrayList();
                            for (int i2 = 1; i2 < this.L.f.p().size(); i2++) {
                                arrayList.add(this.L.f.p().get(i2));
                            }
                            this.aR = new bk(this, arrayList);
                            this.aQ.setAdapter(this.aR);
                            return;
                        }
                        this.aQ.setVisibility(8);
                        this.aP.setVisibility(4);
                        return;
                    }
                    this.aM.setVisibility(8);
                    return;
                }
                new k((Context) this, axVar.d()).a();
                return;
            default:
                return;
        }
    }

    private void a(ArrayList<w> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            this.aK.setVisibility(8);
            return;
        }
        int i;
        this.aK.setVisibility(0);
        this.aK.removeAllViews();
        int size = arrayList.size();
        if (size > 3) {
            i = 3;
        } else {
            i = size;
        }
        for (int i2 = 0; i2 < i; i2++) {
            final w wVar = (w) arrayList.get(i2);
            LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.item_custommenu, null);
            linearLayout.setLayoutParams(new LayoutParams(-1, -1, 1.0f));
            ImageView imageView = (ImageView) linearLayout.findViewById(R.id.iv_custommenu);
            ((TextView) linearLayout.findViewById(R.id.tv_custommenu_name)).setText(wVar.a());
            if (wVar.c().size() > 0) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaShopListActivity b;

                public void onClick(View view) {
                    if (wVar.c().size() == 0) {
                        com.ecjia.a.b.a.a().a(this.b, wVar.b());
                        return;
                    }
                    this.b.aL = new g(this.b, wVar.c(), view.getWidth() - 20, 0);
                    this.b.aL.a(view);
                }
            });
            this.aK.addView(linearLayout);
        }
    }

    public int b() {
        View childAt = this.C.getChildAt(0);
        if (childAt == null) {
            return 0;
        }
        int firstVisiblePosition = this.C.getFirstVisiblePosition();
        int top = childAt.getTop();
        if (firstVisiblePosition > 1) {
            return 5000;
        }
        return (firstVisiblePosition * childAt.getHeight()) + (-top);
    }

    void c(int i) {
        ColorStateList colorStateList = this.g.getColorStateList(R.color.filter_text_color);
        ECJia_FILTER eCJia_FILTER;
        ag agVar;
        if (i == -1) {
            this.ab = true;
            this.ac = false;
            this.q.b.setImageResource(R.drawable.item_grid_filter_down_active_arrow);
            this.q.b.setWillNotCacheDrawing(true);
            this.q.a.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            this.r.a.setTextColor(colorStateList);
            this.s.a.setTextColor(colorStateList);
            this.t.a.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            this.u.a.setTextColor(colorStateList);
            this.v.a.setTextColor(colorStateList);
            eCJia_FILTER = this.e;
            agVar = this.D;
            eCJia_FILTER.setSort_by(ag.g);
            this.D.a(this.e, this.aw);
            this.aj.setVisibility(0);
            this.ak.setVisibility(8);
            this.al.setVisibility(8);
            this.am.setVisibility(0);
            this.an.setVisibility(8);
            this.ao.setVisibility(8);
        } else if (i == 0) {
            this.ab = false;
            this.ac = true;
            this.r.b.setImageResource(R.drawable.item_grid_filter_down_active_arrow);
            this.r.a.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            this.q.a.setTextColor(colorStateList);
            this.s.a.setTextColor(colorStateList);
            this.u.a.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            this.t.a.setTextColor(colorStateList);
            this.v.a.setTextColor(colorStateList);
            eCJia_FILTER = this.e;
            agVar = this.D;
            eCJia_FILTER.setSort_by(ag.f);
            this.D.a(this.e, this.aw);
            this.aj.setVisibility(8);
            this.ak.setVisibility(0);
            this.al.setVisibility(8);
            this.am.setVisibility(8);
            this.an.setVisibility(0);
            this.ao.setVisibility(8);
        } else if (i == 1) {
            this.ab = false;
            this.ac = false;
            this.s.a.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            this.q.a.setTextColor(colorStateList);
            this.r.a.setTextColor(colorStateList);
            this.v.a.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            this.t.a.setTextColor(colorStateList);
            this.u.a.setTextColor(colorStateList);
            if (this.e.getSort_by().equals("price_asc")) {
                eCJia_FILTER = this.e;
                agVar = this.D;
                eCJia_FILTER.setSort_by(ag.d);
                this.s.b.setImageResource(R.drawable.goodlist_buttom);
                this.v.b.setImageResource(R.drawable.goodlist_buttom);
            } else if (this.e.getSort_by().equals("price_desc")) {
                eCJia_FILTER = this.e;
                agVar = this.D;
                eCJia_FILTER.setSort_by(ag.e);
                this.s.b.setImageResource(R.drawable.goodlist_top);
                this.v.b.setImageResource(R.drawable.goodlist_top);
            } else {
                eCJia_FILTER = this.e;
                agVar = this.D;
                eCJia_FILTER.setSort_by(ag.d);
                this.s.b.setImageResource(R.drawable.goodlist_buttom);
                this.v.b.setImageResource(R.drawable.goodlist_buttom);
            }
            this.D.a(this.e, this.aw);
            this.aj.setVisibility(8);
            this.ak.setVisibility(8);
            this.al.setVisibility(0);
            this.am.setVisibility(8);
            this.an.setVisibility(8);
            this.ao.setVisibility(0);
        } else if (i != 3) {
        }
    }

    public void c() {
        if (this.D.h.size() == 0) {
            this.ai = new k((Context) this, "暂无商品");
            this.ai.a(17, 0, 0);
            this.ai.a();
            this.C.setVisibility(0);
            this.G.setVisibility(8);
            this.K.setVisibility(0);
            this.E.notifyDataSetChanged();
            this.D.h.clear();
            if (this.E != null) {
                this.E.notifyDataSetChanged();
            }
            this.F.notifyDataSetChanged();
        } else {
            this.C.setVisibility(0);
            this.G.setVisibility(8);
            this.K.setVisibility(8);
            this.F.notifyDataSetChanged();
            this.F.a(this.D.h);
            if (this.b != 3 && this.b == 1) {
                this.E.notifyDataSetChanged();
                this.E.a(this.D.h);
            }
        }
        if ("".equals(this.I.getString("uid", "")) || this.h.g() == 0) {
            this.Q.setVisibility(8);
            this.R.setVisibility(8);
            return;
        }
        this.Q.setVisibility(0);
        this.R.setVisibility(0);
        if (this.h.g() < 10) {
            this.R.setText(this.h.g() + "");
        } else if (this.h.g() < 100 && this.h.g() > 9) {
            this.R.setText(this.h.g() + "");
        } else if (this.h.g() > 99) {
            this.R.setText("99+");
        }
    }

    public void e() {
        String f = this.L.f.f();
        String distance = this.L.f.l().getDistance();
        String latitude = this.L.f.l().getLatitude();
        String longitude = this.L.f.l().getLongitude();
        Intent intent = new Intent(this, ECJiaMapActivity.class);
        intent.putExtra("isGuide", true);
        intent.putExtra("shop_name", this.L.f.b());
        intent.putExtra("name", f);
        intent.putExtra("distance", distance);
        intent.putExtra("lat", latitude);
        intent.putExtra("lng", longitude);
        startActivity(intent);
    }

    public void onClick(View view) {
        int i = 0;
        final String str;
        String string;
        String string2;
        k kVar;
        switch (view.getId()) {
            case R.id.search_search:
                Intent intent = new Intent(this, ECJiaSearchSellerGoodsActivity.class);
                intent.putExtra("sellergood", true);
                intent.putExtra("sellerid", this.aw);
                startActivity(intent);
                return;
            case R.id.ll_company_phone:
                str = this.aG;
                string = this.g.getString(R.string.setting_call_or_not);
                if (!TextUtils.isEmpty(str)) {
                    final com.ecjia.component.view.c cVar = new com.ecjia.component.view.c(this, string, string + "\n" + str);
                    cVar.a();
                    cVar.b.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaShopListActivity c;

                        public void onClick(View view) {
                            cVar.b();
                            this.c.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + str)));
                        }
                    });
                    cVar.d.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaShopListActivity b;

                        public void onClick(View view) {
                            cVar.b();
                        }
                    });
                    return;
                }
                return;
            case R.id.search_filter:
                if (((this.n & this.o) & this.p) != 0) {
                    if (this.aq.size() <= 0) {
                        this.M.setVisibility(8);
                        this.m.setVisibility(8);
                        this.N.setVisibility(0);
                    } else if (this.ap == null) {
                        str = this.g.getString(R.string.goodlist_brand);
                        string = this.g.getString(R.string.goodlist_classify);
                        string2 = this.g.getString(R.string.goodlist_price);
                        this.ap = new bm(this, new String[]{str, string, string2}, this.a, this.D, this.aq);
                        this.M.setAdapter(this.ap);
                        this.k.setTouchModeAbove(1);
                        while (i < this.ap.getGroupCount()) {
                            this.M.expandGroup(i);
                            i++;
                        }
                    } else {
                        this.M.setAdapter(this.ap);
                        while (i < this.ap.getGroupCount()) {
                            this.M.expandGroup(i);
                            i++;
                        }
                    }
                    this.k.toggle();
                    return;
                }
                kVar = new k((Context) this, "数据加载中，请稍等");
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case R.id.rl_shop_collect:
                this.aa = this.I.getString("uid", "");
                if ("".equals(this.aa)) {
                    startActivity(new Intent(this, ECJiaLoginActivity.class));
                    overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                } else if ("0".equals(this.V)) {
                    this.L.c(this.aw);
                    c.a().c(new com.ecjia.a.a.b("add_collect_seller", this.aw));
                    return;
                } else {
                    this.L.d(this.aw);
                    c.a().c(new com.ecjia.a.a.b("minus_collect_seller", this.aw));
                    return;
                }
            case R.id.iv_favour_more:
                if (this.aS) {
                    this.aS = false;
                    this.aP.setImageResource(R.drawable.arrow_collect_down);
                    this.aQ.setVisibility(0);
                    return;
                }
                this.aS = true;
                this.aP.setImageResource(R.drawable.arrow_collect_up);
                this.aQ.setVisibility(8);
                return;
            case R.id.search_filter2:
                if (((this.n & this.o) & this.p) != 0) {
                    if (this.aq.size() <= 0) {
                        this.M.setVisibility(8);
                        this.m.setVisibility(8);
                        this.N.setVisibility(0);
                    } else if (this.ap == null) {
                        str = this.g.getString(R.string.goodlist_brand);
                        string = this.g.getString(R.string.goodlist_classify);
                        string2 = this.g.getString(R.string.goodlist_price);
                        this.ap = new bm(this, new String[]{str, string, string2}, this.a, this.D, this.aq);
                        this.M.setAdapter(this.ap);
                        this.k.setTouchModeAbove(1);
                        while (i < this.ap.getGroupCount()) {
                            this.M.expandGroup(i);
                            i++;
                        }
                    } else {
                        this.M.setAdapter(this.ap);
                        while (i < this.ap.getGroupCount()) {
                            this.M.expandGroup(i);
                            i++;
                        }
                    }
                    this.k.toggle();
                    return;
                }
                kVar = new k((Context) this, "数据加载中，请稍等");
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case R.id.ll_top_guide:
                e();
                return;
            case R.id.ll_top_category:
                if (((this.n & this.o) & this.p) != 0) {
                    if (this.aq.size() <= 0) {
                        this.M.setVisibility(8);
                        this.m.setVisibility(8);
                        this.N.setVisibility(0);
                    } else if (this.ap == null) {
                        str = this.g.getString(R.string.goodlist_brand);
                        string = this.g.getString(R.string.goodlist_classify);
                        string2 = this.g.getString(R.string.goodlist_price);
                        this.ap = new bm(this, new String[]{str, string, string2}, this.a, this.D, this.aq);
                        this.M.setAdapter(this.ap);
                        this.k.setTouchModeAbove(1);
                        for (int i2 = 0; i2 < this.ap.getGroupCount(); i2++) {
                            this.M.expandGroup(i2);
                        }
                    } else {
                        this.M.setAdapter(this.ap);
                        while (i < this.ap.getGroupCount()) {
                            this.M.expandGroup(i);
                            i++;
                        }
                    }
                    this.k.toggle();
                    return;
                }
                kVar = new k((Context) this, "数据加载中，请稍等");
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            default:
                return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.k.isMenuShowing()) {
            this.k.toggle();
            return true;
        }
        if (this.aU) {
            Intent intent = new Intent();
            intent.putExtra("collect_num", this.U + "");
            setResult(-1, intent);
        }
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        return false;
    }

    public void a(int i) {
        this.D.a(this.e, this.aw);
    }

    public void b(int i) {
        this.D.b(this.e, this.aw);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 1:
                if (intent != null) {
                    String stringExtra = intent.getStringExtra("filter");
                    if (stringExtra != null) {
                        try {
                            ECJia_FILTER fromJson = ECJia_FILTER.fromJson(new JSONObject(stringExtra));
                            this.e.setCategory_id(fromJson.getCategory_id());
                            this.e.setPrice_range(fromJson.getPrice_range());
                            this.e.setBrand_id(fromJson.getBrand_id());
                            this.D.a(fromJson, this.aw);
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                }
                return;
            case 2:
                this.L.f(this.aw);
                return;
            default:
                return;
        }
    }

    protected void onResume() {
        super.onResume();
        this.H = false;
        if (this.h.g() == 0) {
            this.Q.setVisibility(8);
            this.R.setVisibility(8);
            return;
        }
        this.Q.setVisibility(0);
        this.R.setVisibility(0);
        if (this.h.g() < 10) {
            this.R.setText(this.h.g() + "");
        } else if (this.h.g() < 100 && this.h.g() > 9) {
            this.R.setText(this.h.g() + "");
        } else if (this.h.g() > 99) {
            this.R.setText("99+");
        }
    }

    private void g() {
        this.l = getWindowManager().getDefaultDisplay().getWidth();
        this.k = new SlidingMenu(this);
        this.k.setMode(1);
        this.k.setTouchModeAbove(2);
        this.k.setShadowDrawable((int) R.drawable.new_good_distance);
        this.k.setShadowWidthRes(R.dimen.slidingmenu_offset);
        this.k.setBehindWidth((int) (((double) this.l) * 0.8333333333333334d));
        this.k.setFadeDegree(0.35f);
        this.k.setMenu((int) R.layout.activity_goodlist_menu);
        this.k.attachToActivity(this, 1);
        h();
    }

    private void h() {
        this.M = (ExpandableListView) this.k.findViewById(R.id.goodlist_filterlist);
        this.O = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.goodlist_menu_foot, null);
        this.ae = (TextView) this.O.findViewById(R.id.menu_reset);
        this.ae.setOnClickListener(new ECJiaShopListActivity_16(this));
        this.M.addFooterView(this.O);
        this.N = (LinearLayout) this.k.findViewById(R.id.goodlist_null);
        this.m = (TextView) this.k.findViewById(R.id.filter_finish);
        this.M.setOnChildClickListener(new ECJiaShopListActivity_17(this));
        this.m.setOnClickListener(new ECJiaShopListActivity_2(this));
    }

    protected void onPause() {
        super.onPause();
        this.J.putInt("goodlist_type", this.b);
        this.J.commit();
    }

    private void i() {
        this.au = (ImageView) this.ax.findViewById(R.id.shopdetail_shop_img);
        this.y = (ImageView) this.ax.findViewById(R.id.iv_collect);
        this.Z = (TextView) this.ax.findViewById(R.id.tv_collect);
        this.aT = (TextView) this.ax.findViewById(R.id.shopdetail_shop_distance);
        this.av = (TextView) this.ax.findViewById(R.id.shopdetail_shop_name);
        this.Y = (TextView) this.ax.findViewById(R.id.shopdetail_shop_colloct);
        this.ar = (LinearLayout) this.ax.findViewById(R.id.ll_head_all);
        this.S = (LinearLayout) this.ax.findViewById(R.id.ll_head_top);
        this.as = (RelativeLayout) this.ax.findViewById(R.id.rl_shop_collect);
        this.as.setOnClickListener(this);
        this.az = (TextView) this.ax.findViewById(R.id.tv_goodsscore);
        this.aA = (TextView) this.ax.findViewById(R.id.tv_logisticsscore);
        this.aB = (TextView) this.ax.findViewById(R.id.tv_servicescore);
        this.aC = (TextView) this.ax.findViewById(R.id.tv_company_area);
        this.aD = (TextView) this.ax.findViewById(R.id.tv_company_notice);
        this.aE = (LinearLayout) this.ax.findViewById(R.id.ll_company_phone);
        this.aE.setOnClickListener(this);
        this.aM = (LinearLayout) this.ax.findViewById(R.id.ll_favour_item);
        this.aN = (TextView) this.ax.findViewById(R.id.tv_first_favour_name);
        this.aO = (TextView) this.ax.findViewById(R.id.tv_first_favour_content);
        this.aP = (ImageView) this.ax.findViewById(R.id.iv_favour_more);
        this.aP.setOnClickListener(this);
        this.aQ = (ECJiaMyListView) this.ax.findViewById(R.id.mlv_favour);
        this.ag = (ImageView) this.ax.findViewById(R.id.search_filter);
        this.ag.setOnClickListener(this);
        this.ax.findViewById(R.id.guide).setOnClickListener(new ECJiaShopListActivity_3(this));
        this.aj = this.ax.findViewById(R.id.filter_one);
        this.ak = this.ax.findViewById(R.id.filter_two);
        this.al = this.ax.findViewById(R.id.filter_three);
        this.am = findViewById(R.id.filter_one2);
        this.an = findViewById(R.id.filter_two2);
        this.ao = findViewById(R.id.filter_three2);
        this.q = new a(this);
        this.r = new a(this);
        this.s = new a(this);
        this.t = new a(this);
        this.u = new a(this);
        this.v = new a(this);
        this.q.a = (TextView) this.ax.findViewById(R.id.filter_title_tabone);
        this.q.b = (ImageView) this.ax.findViewById(R.id.filter_order_tabone);
        this.q.c = (RelativeLayout) this.ax.findViewById(R.id.tabOne);
        this.t.a = (TextView) findViewById(R.id.filter_title_tabone2);
        this.t.c = (RelativeLayout) findViewById(R.id.tabOne2);
        this.q.c.setOnClickListener(new ECJiaShopListActivity_4(this));
        this.t.c.setOnClickListener(new ECJiaShopListActivity_5(this));
        this.r.a = (TextView) this.ax.findViewById(R.id.filter_title_tabtwo);
        this.r.b = (ImageView) this.ax.findViewById(R.id.filter_order_tabtwo);
        this.r.c = (RelativeLayout) this.ax.findViewById(R.id.tabTwo);
        this.u.a = (TextView) findViewById(R.id.filter_title_tabtwo2);
        this.u.c = (RelativeLayout) findViewById(R.id.tabTwo2);
        this.r.c.setOnClickListener(new ECJiaShopListActivity_6(this));
        this.u.c.setOnClickListener(new ECJiaShopListActivity_7(this));
        this.s.a = (TextView) this.ax.findViewById(R.id.filter_title_tabthree);
        this.s.b = (ImageView) this.ax.findViewById(R.id.filter_order_tabthree);
        this.s.c = (RelativeLayout) this.ax.findViewById(R.id.tabThree);
        this.v.a = (TextView) findViewById(R.id.filter_title_tabthree2);
        this.v.b = (ImageView) findViewById(R.id.filter_order_tabthree2);
        this.v.c = (RelativeLayout) findViewById(R.id.tabThree2);
        this.s.c.setOnClickListener(new ECJiaShopListActivity_8(this));
        this.v.c.setOnClickListener(new ECJiaShopListActivity_9(this));
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
    }
}
