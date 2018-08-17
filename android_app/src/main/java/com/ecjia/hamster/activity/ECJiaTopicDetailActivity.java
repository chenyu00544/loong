package com.ecjia.hamster.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.aj;
import com.ecjia.component.view.ECJiaMyListView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.bx;
import com.ecjia.hamster.adapter.bx.b;
import com.ecjia.hamster.adapter.by;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.bc;
import com.ecmoban.android.missmall.R;

public class ECJiaTopicDetailActivity extends a implements a, ECJiaXListView.a {
    private TextView A;
    private TextView B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private View G;
    private View H;
    private View I;
    private View J;
    private View K;
    private View L;
    private View M;
    private View N;
    private int O;
    private int P;
    private aj Q;
    private by R;
    private PopupWindow S;
    private View T;
    private ECJiaMyListView U;
    private bx V;
    private LinearLayout W;
    private FrameLayout X;
    private ImageView Y;
    private ImageView a;
    private TextView b;
    private LinearLayout c;
    private String d = "";
    private String e = "hot";
    private String k;
    private ECJiaXListView l;
    private FrameLayout m;
    private View n;
    private View o;
    private LinearLayout p;
    private RelativeLayout q;
    private RelativeLayout r;
    private RelativeLayout s;
    private RelativeLayout t;
    private RelativeLayout u;
    private RelativeLayout v;
    private RelativeLayout w;
    private RelativeLayout x;
    private TextView y;
    private TextView z;

    class ECJiaTopicDetailActivity_10 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_10(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.a("two");
        }
    }

    class ECJiaTopicDetailActivity_11 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_11(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.a("three");
        }
    }

    class ECJiaTopicDetailActivity_12 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_12(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.a("four");
        }
    }

    class ECJiaTopicDetailActivity_13 implements b {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_13(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void a(View view, int i) {
            if (i == 0) {
                this.a.d = "";
            } else {
                this.a.d = ((bc) this.a.V.a.get(i)).a();
            }
            q.a("i=" + i);
            this.a.V.a(i);
            this.a.Q.a(this.a.k, this.a.d, this.a.e);
            this.a.S.dismiss();
        }
    }

    class ECJiaTopicDetailActivity_14 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_14(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.S.dismiss();
        }
    }

    class ECJiaTopicDetailActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_1(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaTopicDetailActivity_2 implements OnDismissListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_2(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onDismiss() {
            this.a.c.setBackgroundResource(R.drawable.top_category);
        }
    }

    class ECJiaTopicDetailActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_3(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.a("one");
        }
    }

    class ECJiaTopicDetailActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_4(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.a("two");
        }
    }

    class ECJiaTopicDetailActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_5(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.a("three");
        }
    }

    class ECJiaTopicDetailActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_6(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.a("four");
        }
    }

    class ECJiaTopicDetailActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_7(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            if (this.a.Q.d.d().size() > 0) {
                this.a.S.showAsDropDown(this.a.X);
                this.a.c.setBackgroundResource(R.drawable.circle_close_button);
            }
        }
    }

    class ECJiaTopicDetailActivity_8 implements OnScrollListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_8(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i > 1) {
                this.a.p.setVisibility(0);
            } else {
                this.a.p.setVisibility(8);
            }
        }
    }

    class ECJiaTopicDetailActivity_9 implements OnClickListener {
        final /* synthetic */ ECJiaTopicDetailActivity a;

        ECJiaTopicDetailActivity_9(ECJiaTopicDetailActivity eCJiaTopicDetailActivity) {
            this.a = eCJiaTopicDetailActivity;
        }

        public void onClick(View view) {
            this.a.a("one");
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_topic_detail);
        b();
    }

    private void b() {
        this.k = getIntent().getStringExtra("topic_id");
        this.Q = new aj(this);
        this.Q.a((a) this);
        this.O = this.g.getColor(R.color.public_theme_color_normal);
        this.P = this.g.getColor(R.color.my_black);
        e();
        f();
        this.a = (ImageView) findViewById(R.id.back);
        this.a.setOnClickListener(new ECJiaTopicDetailActivity_1(this));
        this.W = (LinearLayout) findViewById(R.id.topic_detail_content);
        this.X = (FrameLayout) findViewById(R.id.topic_top_item);
        this.b = (TextView) findViewById(R.id.title_text);
        this.c = (LinearLayout) findViewById(R.id.topic_top_category);
        this.c.setOnClickListener(new ECJiaTopicDetailActivity_7(this));
        this.c.setClickable(false);
        this.l = (ECJiaXListView) findViewById(R.id.topic_detail_list);
        this.l.addHeaderView(this.n);
        this.l.addHeaderView(this.o);
        this.l.setXListViewListener(this, 1);
        this.p = (LinearLayout) findViewById(R.id.tab_big_item);
        this.p.setVisibility(8);
        this.l.setPullLoadEnable(false);
        this.m = (FrameLayout) findViewById(R.id.null_pager);
        this.R = new by(this, this.Q.c);
        this.l.setAdapter(this.R);
        this.l.setOnScrollListener(new ECJiaTopicDetailActivity_8(this));
        this.q = (RelativeLayout) findViewById(R.id.tab_item_one);
        this.r = (RelativeLayout) findViewById(R.id.tab_item_two);
        this.s = (RelativeLayout) findViewById(R.id.tab_item_three);
        this.t = (RelativeLayout) findViewById(R.id.tab_item_four);
        this.y = (TextView) findViewById(R.id.tab_item_one_text);
        this.z = (TextView) findViewById(R.id.tab_item_two_text);
        this.A = (TextView) findViewById(R.id.tab_item_three_text);
        this.B = (TextView) findViewById(R.id.tab_item_four_text);
        this.G = findViewById(R.id.tab_item_one_line);
        this.H = findViewById(R.id.tab_item_two_line);
        this.I = findViewById(R.id.tab_item_three_line);
        this.J = findViewById(R.id.tab_item_four_line);
        this.q.setOnClickListener(new ECJiaTopicDetailActivity_9(this));
        this.r.setOnClickListener(new ECJiaTopicDetailActivity_10(this));
        this.s.setOnClickListener(new ECJiaTopicDetailActivity_11(this));
        this.t.setOnClickListener(new ECJiaTopicDetailActivity_12(this));
        a("one");
    }

    private void c() {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.topicdetail_popupwindow, null, true);
        this.T = inflate.findViewById(R.id.topic_buttom_view);
        this.U = (ECJiaMyListView) inflate.findViewById(R.id.topic_pop_list);
        if (this.Q.d.d() == null || this.Q.d.d().size() <= 0) {
            this.c.setVisibility(8);
        } else {
            this.c.setClickable(true);
            this.c.setVisibility(0);
            this.V = new bx(this, this.Q.d.d());
            this.V.a(new ECJiaTopicDetailActivity_13(this));
            this.U.setAdapter(this.V);
        }
        this.S = new PopupWindow(this.W, -1, -1, true);
        this.S.setContentView(inflate);
        this.S.setOutsideTouchable(true);
        this.S.setFocusable(true);
        this.S.setBackgroundDrawable(new ColorDrawable(10066329));
        this.S.setAnimationStyle(R.style.alpha_anim_style);
        this.T.setOnClickListener(new ECJiaTopicDetailActivity_14(this));
        this.S.setOnDismissListener(new ECJiaTopicDetailActivity_2(this));
    }

    private void a(String str) {
        if ("one".equals(str)) {
            this.y.setTextColor(this.O);
            this.z.setTextColor(this.P);
            this.A.setTextColor(this.P);
            this.B.setTextColor(this.P);
            this.G.setVisibility(0);
            this.H.setVisibility(8);
            this.I.setVisibility(8);
            this.J.setVisibility(8);
            this.C.setTextColor(this.O);
            this.D.setTextColor(this.P);
            this.E.setTextColor(this.P);
            this.F.setTextColor(this.P);
            this.K.setVisibility(0);
            this.L.setVisibility(8);
            this.M.setVisibility(8);
            this.N.setVisibility(8);
            this.e = "new";
        } else if ("two".equals(str)) {
            this.y.setTextColor(this.P);
            this.z.setTextColor(this.O);
            this.A.setTextColor(this.P);
            this.B.setTextColor(this.P);
            this.G.setVisibility(8);
            this.H.setVisibility(0);
            this.I.setVisibility(8);
            this.J.setVisibility(8);
            this.C.setTextColor(this.P);
            this.D.setTextColor(this.O);
            this.E.setTextColor(this.P);
            this.F.setTextColor(this.P);
            this.K.setVisibility(8);
            this.L.setVisibility(0);
            this.M.setVisibility(8);
            this.N.setVisibility(8);
            this.e = "hot";
        } else if ("three".equals(str)) {
            this.y.setTextColor(this.P);
            this.z.setTextColor(this.P);
            this.A.setTextColor(this.O);
            this.B.setTextColor(this.P);
            this.G.setVisibility(8);
            this.H.setVisibility(8);
            this.I.setVisibility(0);
            this.J.setVisibility(8);
            this.C.setTextColor(this.P);
            this.D.setTextColor(this.P);
            this.E.setTextColor(this.O);
            this.F.setTextColor(this.P);
            this.K.setVisibility(8);
            this.L.setVisibility(8);
            this.M.setVisibility(0);
            this.N.setVisibility(8);
            this.e = "price_desc";
        } else if ("four".equals(str)) {
            this.y.setTextColor(this.P);
            this.z.setTextColor(this.P);
            this.A.setTextColor(this.P);
            this.B.setTextColor(this.O);
            this.G.setVisibility(8);
            this.H.setVisibility(8);
            this.I.setVisibility(8);
            this.J.setVisibility(0);
            this.C.setTextColor(this.P);
            this.D.setTextColor(this.P);
            this.E.setTextColor(this.P);
            this.F.setTextColor(this.O);
            this.K.setVisibility(8);
            this.L.setVisibility(8);
            this.M.setVisibility(8);
            this.N.setVisibility(0);
            this.e = "price_asc";
        } else {
            this.y.setTextColor(this.O);
            this.z.setTextColor(this.P);
            this.A.setTextColor(this.P);
            this.B.setTextColor(this.P);
            this.G.setVisibility(0);
            this.H.setVisibility(8);
            this.I.setVisibility(8);
            this.J.setVisibility(8);
            this.C.setTextColor(this.O);
            this.D.setTextColor(this.P);
            this.E.setTextColor(this.P);
            this.F.setTextColor(this.P);
            this.K.setVisibility(0);
            this.L.setVisibility(8);
            this.M.setVisibility(8);
            this.N.setVisibility(8);
            this.e = "hot";
        }
        this.Q.a(this.k, this.d, this.e);
    }

    private void e() {
        this.n = LayoutInflater.from(this).inflate(R.layout.topic_head_view, null);
        this.Y = (ImageView) this.n.findViewById(R.id.top_img);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.width = d();
        layoutParams.height = (layoutParams.width * 2) / 5;
        this.Y.setLayoutParams(layoutParams);
    }

    private void f() {
        this.o = LayoutInflater.from(this).inflate(R.layout.topic_head_view2, null);
        this.u = (RelativeLayout) this.o.findViewById(R.id.head_tab_item_one);
        this.v = (RelativeLayout) this.o.findViewById(R.id.head_tab_item_two);
        this.w = (RelativeLayout) this.o.findViewById(R.id.head_tab_item_three);
        this.x = (RelativeLayout) this.o.findViewById(R.id.head_tab_item_four);
        this.C = (TextView) this.o.findViewById(R.id.head_tab_item_one_text);
        this.D = (TextView) this.o.findViewById(R.id.head_tab_item_two_text);
        this.E = (TextView) this.o.findViewById(R.id.head_tab_item_three_text);
        this.F = (TextView) this.o.findViewById(R.id.head_tab_item_four_text);
        this.K = this.o.findViewById(R.id.head_tab_item_one_line);
        this.L = this.o.findViewById(R.id.head_tab_item_two_line);
        this.M = this.o.findViewById(R.id.head_tab_item_three_line);
        this.N = this.o.findViewById(R.id.head_tab_item_four_line);
        this.u.setOnClickListener(new ECJiaTopicDetailActivity_3(this));
        this.v.setOnClickListener(new ECJiaTopicDetailActivity_4(this));
        this.w.setOnClickListener(new ECJiaTopicDetailActivity_5(this));
        this.x.setOnClickListener(new ECJiaTopicDetailActivity_6(this));
    }

    public int d() {
        return Math.min(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
    }

    public void a(int i) {
        this.Q.a(this.k, this.d, this.e);
    }

    public void b(int i) {
        this.Q.b(this.k, this.d, this.e);
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("topic/info")) {
            return;
        }
        if (axVar.b() == 1) {
            if ("".equals(this.b.getText().toString())) {
                this.b.setText(this.Q.d.b());
                p.a((Context) this).a(this.Y, this.Q.d.c());
                c();
            }
            this.R.notifyDataSetChanged();
            this.l.stopRefresh();
            this.l.stopLoadMore();
            this.l.stopRefresh();
            if (this.Q.a.a() == 0) {
                this.l.setPullLoadEnable(false);
            } else {
                this.l.setPullLoadEnable(true);
            }
            if (this.Q.c.size() > 0) {
                this.l.setVisibility(0);
                this.m.setVisibility(8);
                return;
            }
            this.l.setVisibility(8);
            this.m.setVisibility(0);
            return;
        }
        this.l.setVisibility(8);
        this.m.setVisibility(0);
    }
}
