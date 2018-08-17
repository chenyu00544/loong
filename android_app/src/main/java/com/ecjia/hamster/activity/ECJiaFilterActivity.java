package com.ecjia.hamster.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.adapter.i;
import com.ecjia.hamster.adapter.i.a;
import com.ecjia.hamster.adapter.q;
import com.ecjia.hamster.model.ECJia_ATTR_LIST;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ECJia_FILTER_ATTR;
import com.ecjia.hamster.model.ECJia_FILTER_BRAND;
import com.ecjia.hamster.model.ECJia_FILTER_CATEGORY;
import com.ecjia.hamster.model.ECJia_FILTER_PRICE;
import com.ecjia.hamster.model.ECJia_PRICE_RANGE;
import com.ecmoban.android.missmall.R;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import org.json.JSONException;

public class ECJiaFilterActivity extends a implements OnClickListener, a {
    TextView A;
    TextView B;
    View C;
    GridView D;
    boolean E = false;
    private ImageView F;
    private TextView G;
    private TextView H;
    private LinearLayout I;
    private ExpandableListView J;
    private q K;
    private i L;
    private i M;
    private i N;
    private String[] O = new String[3];
    private Object[] P = new Object[3];
    private String Q;
    private String R;
    private LinearLayout S;
    private View T;
    private LinearLayout U;
    private LinearLayout V;
    private String W;
    public ArrayList<ECJia_FILTER_BRAND> a;
    public ArrayList<ECJia_FILTER_PRICE> b;
    public ArrayList<ECJia_FILTER_CATEGORY> c;
    public ArrayList<ECJia_FILTER_ATTR> d;
    Bundle e;
    View k;
    LinearLayout l;
    TextView m;
    TextView n;
    View o;
    GridView p;
    boolean q = false;
    View r;
    LinearLayout s;
    TextView t;
    TextView u;
    View v;
    GridView w;
    boolean x = false;
    View y;
    LinearLayout z;

    class ECJiaFilterActivity_1 implements OnTouchListener {
        final /* synthetic */ ECJiaFilterActivity a;

        ECJiaFilterActivity_1(ECJiaFilterActivity eCJiaFilterActivity) {
            this.a = eCJiaFilterActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.finish();
            return true;
        }
    }

    class ECJiaFilterActivity_2 implements OnGroupClickListener {
        final /* synthetic */ ECJiaFilterActivity a;

        ECJiaFilterActivity_2(ECJiaFilterActivity eCJiaFilterActivity) {
            this.a = eCJiaFilterActivity;
        }

        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
            if (this.a.J.isGroupExpanded(i)) {
                this.a.J.collapseGroup(i);
            } else {
                this.a.J.expandGroup(i);
            }
            return true;
        }
    }

    class ECJiaFilterActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaFilterActivity a;

        ECJiaFilterActivity_3(ECJiaFilterActivity eCJiaFilterActivity) {
            this.a = eCJiaFilterActivity;
        }

        public void onClick(View view) {
            if (this.a.q) {
                this.a.q = false;
                this.a.p.setVisibility(8);
                if (TextUtils.isEmpty(this.a.O[1])) {
                    this.a.n.setText(this.a.Q);
                    this.a.n.setTextColor(Color.parseColor("#999999"));
                    return;
                }
                this.a.n.setText(this.a.O[1]);
                this.a.n.setTextColor(this.a.g.getColor(R.color.public_theme_color_normal));
                return;
            }
            this.a.q = true;
            this.a.p.setVisibility(0);
            if (TextUtils.isEmpty(this.a.O[1])) {
                this.a.n.setText(this.a.R);
                this.a.n.setTextColor(Color.parseColor("#999999"));
                return;
            }
            this.a.n.setText(this.a.O[1]);
            this.a.n.setTextColor(this.a.g.getColor(R.color.public_theme_color_normal));
        }
    }

    class ECJiaFilterActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaFilterActivity a;

        ECJiaFilterActivity_4(ECJiaFilterActivity eCJiaFilterActivity) {
            this.a = eCJiaFilterActivity;
        }

        public void onClick(View view) {
            this.a.w.setVisibility(8);
            if (this.a.x) {
                this.a.x = false;
                this.a.w.setVisibility(8);
                if (TextUtils.isEmpty(this.a.O[2])) {
                    this.a.u.setText(this.a.Q);
                    this.a.u.setTextColor(Color.parseColor("#999999"));
                    return;
                }
                this.a.u.setText(this.a.O[2]);
                this.a.u.setTextColor(this.a.g.getColor(R.color.public_theme_color_normal));
                return;
            }
            this.a.x = true;
            this.a.w.setVisibility(0);
            if (TextUtils.isEmpty(this.a.O[2])) {
                this.a.u.setText(this.a.R);
                this.a.u.setTextColor(Color.parseColor("#999999"));
                return;
            }
            this.a.u.setText(this.a.O[2]);
            this.a.u.setTextColor(this.a.g.getColor(R.color.public_theme_color_normal));
        }
    }

    class ECJiaFilterActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaFilterActivity a;

        ECJiaFilterActivity_5(ECJiaFilterActivity eCJiaFilterActivity) {
            this.a = eCJiaFilterActivity;
        }

        public void onClick(View view) {
            if (this.a.E) {
                this.a.E = false;
                this.a.D.setVisibility(8);
                if (TextUtils.isEmpty(this.a.O[0])) {
                    this.a.B.setText(this.a.R);
                    this.a.B.setTextColor(Color.parseColor("#999999"));
                    return;
                }
                this.a.B.setText(this.a.O[0]);
                this.a.B.setTextColor(this.a.g.getColor(R.color.public_theme_color_normal));
                return;
            }
            this.a.E = true;
            this.a.D.setVisibility(0);
            if (TextUtils.isEmpty(this.a.O[0])) {
                this.a.B.setText(this.a.Q);
                this.a.B.setTextColor(Color.parseColor("#999999"));
                return;
            }
            this.a.B.setText(this.a.O[0]);
            this.a.B.setTextColor(this.a.g.getColor(R.color.public_theme_color_normal));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_filter);
        PushAgent.getInstance(this).onAppStart();
        b();
        g();
    }

    void b() {
        this.Q = " " + getResources().getString(R.string.all);
        this.R = getResources().getString(R.string.filter_close);
        this.O[0] = "";
        this.O[1] = "";
        this.O[2] = "";
        this.e = getIntent().getBundleExtra("data");
        this.c = (ArrayList) this.e.getSerializable(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY);
        this.a = (ArrayList) this.e.getSerializable(Constants.KEY_BRAND);
        this.b = (ArrayList) this.e.getSerializable("price");
        this.d = (ArrayList) this.e.getSerializable("filter_attr");
        this.W = this.e.getString("origin_category");
        com.ecjia.a.q.a("品牌：" + this.a.size());
        com.ecjia.a.q.a("品牌：" + this.b.size());
        com.ecjia.a.q.a("品牌：" + this.c.size());
        com.ecjia.a.q.a("品牌：" + this.d.size());
    }

    private void g() {
        this.F = (ImageView) findViewById(R.id.filter_back);
        this.G = (TextView) findViewById(R.id.filter_clear);
        this.H = (TextView) findViewById(R.id.filter_sure);
        this.F.setOnClickListener(this);
        this.G.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.T = findViewById(R.id.filter_left_empty);
        this.T.setOnTouchListener(new ECJiaFilterActivity_1(this));
        this.J = (ExpandableListView) findViewById(R.id.goodlist_filterlist);
        this.J.setOnGroupClickListener(new ECJiaFilterActivity_2(this));
        this.V = (LinearLayout) findViewById(R.id.ll_filter_bottom);
        this.I = (LinearLayout) findViewById(R.id.null_pager);
        if ((this.c == null || this.c.size() == 0) && ((this.a == null || this.a.size() == 0) && ((this.b == null || this.b.size() == 0) && (this.d == null || this.d.size() == 0)))) {
            this.J.setVisibility(8);
            this.V.setVisibility(8);
            this.I.setVisibility(0);
        } else {
            this.J.setVisibility(0);
            this.V.setVisibility(0);
            this.I.setVisibility(8);
        }
        f();
        c();
        e();
        this.K = new q(this, this.d);
        this.J.setAdapter(this.K);
    }

    void c() {
        int i = 0;
        this.k = LayoutInflater.from(this).inflate(R.layout.layout_filter_brand, null);
        this.S = (LinearLayout) this.k.findViewById(R.id.filter_brand_ll_in);
        this.l = (LinearLayout) this.k.findViewById(R.id.filter_brand_top);
        this.m = (TextView) this.k.findViewById(R.id.filter_brand_parent_name);
        this.m.setText(this.g.getString(R.string.brand));
        this.n = (TextView) this.k.findViewById(R.id.filter_brand_parent_selected_name);
        this.o = this.k.findViewById(R.id.filter_brand_parent_bottomline);
        this.p = (GridView) this.k.findViewById(R.id.filter_brand_list);
        this.p.setNumColumns(2);
        this.p.setGravity(17);
        this.p.setHorizontalSpacing(6);
        this.p.setVerticalSpacing(6);
        this.L = new i(this, 0, this.a);
        this.L.a((a) this);
        if (this.q) {
            this.p.setVisibility(0);
            if (TextUtils.isEmpty(this.O[1])) {
                this.n.setText(this.R);
                this.n.setTextColor(Color.parseColor("#999999"));
            } else {
                this.n.setText(this.O[1]);
                this.n.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            }
        } else {
            this.p.setVisibility(8);
            if (TextUtils.isEmpty(this.O[1])) {
                this.n.setText(this.Q);
                this.n.setTextColor(Color.parseColor("#999999"));
            } else {
                this.n.setText(this.O[1]);
                this.n.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            }
        }
        this.l.setOnClickListener(new ECJiaFilterActivity_3(this));
        this.p.setAdapter(this.L);
        if (this.a == null || this.a.size() <= 0) {
            this.S.setVisibility(8);
            this.l.setVisibility(8);
        } else {
            this.S.setVisibility(0);
            this.l.setVisibility(0);
        }
        this.J.addHeaderView(this.k);
        if (this.a != null && this.a.size() > 0) {
            while (i < this.a.size()) {
                if (((ECJia_FILTER_BRAND) this.a.get(i)).isSelected()) {
                    this.O[1] = ((ECJia_FILTER_BRAND) this.a.get(i)).getBrand_name();
                    this.P[1] = ((ECJia_FILTER_BRAND) this.a.get(i)).getBrand_id();
                    this.n.setText(this.O[1]);
                    this.n.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                    return;
                }
                i++;
            }
        }
    }

    void e() {
        int i = 0;
        this.r = LayoutInflater.from(this).inflate(R.layout.layout_filter_price, null);
        this.U = (LinearLayout) this.r.findViewById(R.id.filter_price_ll_in);
        this.s = (LinearLayout) this.r.findViewById(R.id.filter_price_top);
        this.t = (TextView) this.r.findViewById(R.id.filter_price_parent_name);
        this.t.setText(this.g.getString(R.string.price));
        this.u = (TextView) this.r.findViewById(R.id.filter_price_parent_selected_name);
        this.v = this.r.findViewById(R.id.filter_price_parent_bottomline);
        this.w = (GridView) this.r.findViewById(R.id.filter_price_list);
        this.w.setNumColumns(2);
        this.w.setGravity(17);
        this.w.setHorizontalSpacing(6);
        this.w.setVerticalSpacing(6);
        this.M = new i(this, 0, this.b);
        this.M.a((a) this);
        if (this.x) {
            this.w.setVisibility(0);
            if (TextUtils.isEmpty(this.O[2])) {
                this.u.setText(this.R);
                this.u.setTextColor(Color.parseColor("#999999"));
            } else {
                this.u.setText(this.O[2]);
                this.u.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            }
        } else {
            this.w.setVisibility(8);
            if (TextUtils.isEmpty(this.O[2])) {
                this.u.setText(this.Q);
                this.u.setTextColor(Color.parseColor("#999999"));
            } else {
                this.u.setText(this.O[2]);
                this.u.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            }
        }
        this.s.setOnClickListener(new ECJiaFilterActivity_4(this));
        this.w.setAdapter(this.M);
        if (this.b == null || this.b.size() <= 0) {
            this.U.setVisibility(8);
            this.s.setVisibility(8);
        } else {
            this.U.setVisibility(0);
            this.s.setVisibility(0);
        }
        this.J.addHeaderView(this.r);
        if (this.b != null && this.b.size() > 0) {
            while (i < this.b.size()) {
                if (((ECJia_FILTER_PRICE) this.b.get(i)).isSelected()) {
                    this.O[2] = ((ECJia_FILTER_PRICE) this.b.get(i)).getPrice_range();
                    ECJia_PRICE_RANGE eCJia_PRICE_RANGE = new ECJia_PRICE_RANGE();
                    eCJia_PRICE_RANGE.setPrice_min(((ECJia_FILTER_PRICE) this.b.get(i)).getPrice_min());
                    eCJia_PRICE_RANGE.setPrice_max(((ECJia_FILTER_PRICE) this.b.get(i)).getPrice_max());
                    this.P[2] = eCJia_PRICE_RANGE;
                    this.u.setText(this.O[2]);
                    this.u.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                    return;
                }
                i++;
            }
        }
    }

    void f() {
        this.y = LayoutInflater.from(this).inflate(R.layout.layout_filter_category, null);
        this.z = (LinearLayout) this.y.findViewById(R.id.filter_category_top);
        this.A = (TextView) this.y.findViewById(R.id.filter_category_parent_name);
        this.A.setText(this.g.getString(R.string.filter_category));
        this.B = (TextView) this.y.findViewById(R.id.filter_category_parent_selected_name);
        this.B.setText(this.O[0]);
        this.C = this.y.findViewById(R.id.filter_category_parent_bottomline);
        this.D = (GridView) this.y.findViewById(R.id.filter_category_list);
        this.D.setNumColumns(2);
        this.D.setGravity(17);
        this.D.setHorizontalSpacing(6);
        this.D.setVerticalSpacing(6);
        this.N = new i(this, 0, this.c);
        this.N.a((a) this);
        if (this.E) {
            this.D.setVisibility(0);
            if (TextUtils.isEmpty(this.O[0])) {
                this.B.setText(this.R);
                this.B.setTextColor(Color.parseColor("#999999"));
            } else {
                this.B.setText(this.O[0]);
                this.B.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            }
        } else {
            this.D.setVisibility(8);
            if (TextUtils.isEmpty(this.O[0])) {
                this.B.setText(this.Q);
                this.B.setTextColor(Color.parseColor("#999999"));
            } else {
                this.B.setText(this.O[0]);
                this.B.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
            }
        }
        this.z.setOnClickListener(new ECJiaFilterActivity_5(this));
        this.D.setAdapter(this.N);
        if (this.c == null || this.c.size() <= 0) {
            this.z.setVisibility(8);
        } else {
            this.z.setVisibility(0);
        }
        this.J.addHeaderView(this.y);
        if (this.c != null && this.c.size() > 0) {
            for (int i = 0; i < this.c.size(); i++) {
                if (((ECJia_FILTER_CATEGORY) this.c.get(i)).isSelected()) {
                    this.O[0] = ((ECJia_FILTER_CATEGORY) this.c.get(i)).getCat_name();
                    this.P[0] = ((ECJia_FILTER_CATEGORY) this.c.get(i)).getCat_id();
                    this.B.setText(this.O[0]);
                    this.B.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                    return;
                }
            }
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_up_in, R.anim.push_left_out);
    }

    public void onClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.filter_back:
                finish();
                return;
            case R.id.filter_clear:
                int i2;
                this.O[0] = "";
                this.O[1] = "";
                this.O[2] = "";
                if (this.E) {
                    this.B.setText(this.R);
                    this.B.setTextColor(Color.parseColor("#999999"));
                } else {
                    this.B.setText(this.Q);
                    this.B.setTextColor(Color.parseColor("#999999"));
                }
                if (this.q) {
                    this.n.setText(this.R);
                    this.n.setTextColor(Color.parseColor("#999999"));
                } else {
                    this.n.setText(this.Q);
                    this.n.setTextColor(Color.parseColor("#999999"));
                }
                if (this.x) {
                    this.u.setText(this.R);
                    this.u.setTextColor(Color.parseColor("#999999"));
                } else {
                    this.u.setText(this.Q);
                    this.u.setTextColor(Color.parseColor("#999999"));
                }
                this.P[0] = this.W;
                this.P[1] = "0";
                this.P[2] = new ECJia_PRICE_RANGE();
                if (this.c != null && this.c.size() > 0) {
                    for (i2 = 0; i2 < this.c.size(); i2++) {
                        ((ECJia_FILTER_CATEGORY) this.c.get(i2)).setSelected(false);
                    }
                }
                this.N.notifyDataSetChanged();
                if (this.a != null && this.a.size() > 0) {
                    for (i2 = 0; i2 < this.a.size(); i2++) {
                        ((ECJia_FILTER_BRAND) this.a.get(i2)).setSelected(false);
                    }
                }
                this.L.notifyDataSetChanged();
                if (this.b != null && this.b.size() > 0) {
                    for (i2 = 0; i2 < this.b.size(); i2++) {
                        ((ECJia_FILTER_PRICE) this.b.get(i2)).setSelected(false);
                    }
                }
                this.M.notifyDataSetChanged();
                if (this.d != null && this.d.size() > 0) {
                    for (i2 = 0; i2 < this.d.size(); i2++) {
                        for (int i3 = 0; i3 < ((ECJia_FILTER_ATTR) this.d.get(i2)).getAttrs().size(); i3++) {
                            ((ECJia_ATTR_LIST) ((ECJia_FILTER_ATTR) this.d.get(i2)).getAttrs().get(i3)).setSelected(false);
                        }
                    }
                }
                this.K.a();
                this.K.notifyDataSetChanged();
                return;
            case R.id.filter_sure:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY, this.c);
                bundle.putSerializable(Constants.KEY_BRAND, this.a);
                bundle.putSerializable("price", this.b);
                bundle.putSerializable("filter_attr", this.d);
                intent.putExtra("data", bundle);
                ECJia_FILTER eCJia_FILTER = new ECJia_FILTER();
                if (this.P[0] == null || TextUtils.isEmpty((String) this.P[0])) {
                    eCJia_FILTER.setCategory_id(this.W);
                } else {
                    eCJia_FILTER.setCategory_id((String) this.P[0]);
                }
                if (this.P[1] == null || TextUtils.isEmpty((String) this.P[1])) {
                    eCJia_FILTER.setBrand_id("0");
                } else {
                    eCJia_FILTER.setBrand_id((String) this.P[1]);
                }
                if (this.P[2] == null || ((ECJia_PRICE_RANGE) this.P[2]).getPrice_max() <= 0) {
                    eCJia_FILTER.setPrice_range(new ECJia_PRICE_RANGE());
                } else {
                    eCJia_FILTER.setPrice_range((ECJia_PRICE_RANGE) this.P[2]);
                }
                if (this.K.a != null && this.K.a.length > 0) {
                    String str = "";
                    while (i < this.K.a.length) {
                        if (i == this.K.a.length - 1) {
                            str = str + this.K.a[i];
                        } else {
                            str = str + this.K.a[i] + ".";
                        }
                        i++;
                    }
                    eCJia_FILTER.setFilter_attr(str);
                }
                try {
                    intent.putExtra("filter", eCJia_FILTER.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setResult(-1, intent);
                finish();
                return;
            default:
                return;
        }
    }

    public void a(int i, int i2, int i3) {
        int i4;
        switch (i) {
            case 0:
                for (i4 = 0; i4 < this.c.size(); i4++) {
                    ((ECJia_FILTER_CATEGORY) this.c.get(i4)).setSelected(false);
                }
                ((ECJia_FILTER_CATEGORY) this.c.get(i3)).setSelected(true);
                this.P[0] = ((ECJia_FILTER_CATEGORY) this.c.get(i3)).getCat_id();
                this.O[0] = ((ECJia_FILTER_CATEGORY) this.c.get(i3)).getCat_name();
                this.B.setText(this.O[0]);
                this.B.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                return;
            case 1:
                for (i4 = 0; i4 < this.a.size(); i4++) {
                    ((ECJia_FILTER_BRAND) this.a.get(i4)).setSelected(false);
                }
                ((ECJia_FILTER_BRAND) this.a.get(i3)).setSelected(true);
                this.P[1] = ((ECJia_FILTER_BRAND) this.a.get(i3)).getBrand_id();
                this.O[1] = ((ECJia_FILTER_BRAND) this.a.get(i3)).getBrand_name();
                this.n.setText(this.O[1]);
                this.n.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                return;
            case 2:
                for (i4 = 0; i4 < this.b.size(); i4++) {
                    ((ECJia_FILTER_PRICE) this.b.get(i4)).setSelected(false);
                }
                ((ECJia_FILTER_PRICE) this.b.get(i3)).setSelected(true);
                ECJia_PRICE_RANGE eCJia_PRICE_RANGE = new ECJia_PRICE_RANGE();
                eCJia_PRICE_RANGE.setPrice_min(((ECJia_FILTER_PRICE) this.b.get(i3)).getPrice_min());
                eCJia_PRICE_RANGE.setPrice_max(((ECJia_FILTER_PRICE) this.b.get(i3)).getPrice_max());
                this.P[2] = eCJia_PRICE_RANGE;
                this.O[2] = ((ECJia_FILTER_PRICE) this.b.get(i3)).getPrice_range();
                this.u.setText(this.O[2].toString());
                this.u.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                return;
            default:
                return;
        }
    }
}
