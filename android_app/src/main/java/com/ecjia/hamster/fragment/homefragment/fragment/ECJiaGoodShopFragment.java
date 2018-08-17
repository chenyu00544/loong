package com.ecjia.hamster.fragment.homefragment.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.a.ab;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.component.a.af;
import com.ecjia.component.a.r;
import com.ecjia.component.view.ECJiaAutoReturnView;
import com.ecjia.component.view.ECJiaMyXListView;
import com.ecjia.hamster.a.b;
import com.ecjia.hamster.a.c;
import com.ecjia.hamster.a.e;
import com.ecjia.hamster.a.f;
import com.ecjia.hamster.a.g;
import com.ecjia.hamster.a.h;
import com.ecjia.hamster.a.i;
import com.ecjia.hamster.a.j;
import com.ecjia.hamster.a.k;
import com.ecjia.hamster.a.l;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaSeckillActivity;
import com.ecjia.hamster.adapter.ECJiaCirculatoryPagerAdapter;
import com.ecjia.hamster.adapter.y;
import com.ecjia.hamster.fragment.ECJiaBaseFragment;
import com.ecjia.hamster.fragment.ECJiaHomeFragment;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ac;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"ValidFragment"})
public class ECJiaGoodShopFragment extends ECJiaBaseFragment implements com.ecjia.component.a.a.a, com.ecjia.component.view.ECJiaMyXListView.a, com.ecjia.consts.b.a {
    private c A;
    private com.ecjia.hamster.a.a B;
    private j C;
    private ECJia_FILTER D = new ECJia_FILTER();
    private LinearLayout E;
    private RelativeLayout F;
    private ViewPager G;
    private ArrayList<View> H;
    private ECJiaCirculatoryPagerAdapter I;
    private LinearLayout J;
    private LinearLayout K;
    private int L = 0;
    private boolean M = true;
    private View N;
    private LinearLayout O;
    private ImageView P;
    private ECJiaAutoReturnView Q;
    private TextView R;
    private TextView S;
    private TextView T;
    private TextView U;
    private TextView V;
    private String W;
    private SimpleDateFormat X;
    private a Y;
    private boolean Z = false;
    private String aa;
    private ac ab = new ac();
    private Handler ac = new ECJiaGoodShopFragment_1(this);
    private ImageView ad;
    private TextView ae;
    private TextView af;
    boolean d = false;
    ECJiaHomeFragment e;
    l f;
    b g;
    public boolean h = false;
    private ECJiaMyXListView i;
    private r j;
    private SharedPreferences k;
    private String l;
    private View m;
    private ImageView n;
    private boolean o;
    private boolean p;
    private int q;
    private af r;
    private y s;
    private Activity t;
    private k u;
    private f v;
    private h w;
    private e x;
    private i y;
    private g z;

    class ECJiaGoodShopFragment_1 extends Handler {
        final /* synthetic */ ECJiaGoodShopFragment a;

        ECJiaGoodShopFragment_1(ECJiaGoodShopFragment eCJiaGoodShopFragment) {
            this.a = eCJiaGoodShopFragment;
        }

        public void handleMessage(Message message) {
            if (message.what == 101) {
                boolean z = false;
                CharSequence b = ab.b(this.a.W, this.a.aa, 1);
                CharSequence b2 = ab.b(this.a.W, this.a.aa, 2);
                CharSequence b3 = ab.b(this.a.W, this.a.aa, 3);
                if (b.equals("00") && b2.equals("00") && b3.equals("00")) {
                    this.a.j.a();
                    z = true;
                }
                this.a.T.setText(b);
                this.a.U.setText(b2);
                this.a.V.setText(b3);
                if (z == this.a.j.w.size()) {
                    this.a.Z = true;
                }
                this.a.W = ab.e(this.a.W);
            }
        }
    }

    class ECJiaGoodShopFragment_2 implements OnScrollListener {
        final /* synthetic */ ECJiaGoodShopFragment a;

        ECJiaGoodShopFragment_2(ECJiaGoodShopFragment eCJiaGoodShopFragment) {
            this.a = eCJiaGoodShopFragment;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            switch (i) {
                case 0:
                    this.a.o = false;
                    if (this.a.i.getLastVisiblePosition() == this.a.i.getCount() - 1) {
                        this.a.n.setVisibility(0);
                        this.a.p = true;
                    }
                    if (this.a.i.getFirstVisiblePosition() == 0) {
                        this.a.n.setVisibility(8);
                        this.a.p = false;
                        return;
                    }
                    return;
                case 1:
                    this.a.o = true;
                    return;
                case 2:
                    this.a.o = false;
                    return;
                default:
                    return;
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.a.o) {
                if (i > this.a.q) {
                    this.a.n.setVisibility(0);
                    this.a.p = true;
                } else if (i >= this.a.q) {
                    return;
                }
                this.a.q = i;
            }
        }
    }

    class ECJiaGoodShopFragment_3 implements OnClickListener {
        final /* synthetic */ ECJiaGoodShopFragment a;

        ECJiaGoodShopFragment_3(ECJiaGoodShopFragment eCJiaGoodShopFragment) {
            this.a = eCJiaGoodShopFragment;
        }

        public void onClick(View view) {
            this.a.i.setSelection(0);
            this.a.n.setVisibility(8);
            this.a.p = false;
            this.a.o = false;
            this.a.q = 0;
        }
    }

    class ECJiaGoodShopFragment_4 implements OnClickListener {
        final /* synthetic */ ECJiaGoodShopFragment a;

        ECJiaGoodShopFragment_4(ECJiaGoodShopFragment eCJiaGoodShopFragment) {
            this.a = eCJiaGoodShopFragment;
        }

        public void onClick(View view) {
            this.a.t.startActivity(new Intent(this.a.t, ECJiaSeckillActivity.class));
            this.a.t.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    class ECJiaGoodShopFragment_5 implements OnPageChangeListener {
        final /* synthetic */ ECJiaGoodShopFragment a;

        ECJiaGoodShopFragment_5(ECJiaGoodShopFragment eCJiaGoodShopFragment) {
            this.a = eCJiaGoodShopFragment;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            int size = i % this.a.j.w.size();
            this.a.J.getChildAt(this.a.L).setEnabled(false);
            this.a.J.getChildAt(size).setEnabled(true);
            this.a.L = size;
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    class ECJiaGoodShopFragment_6 implements OnClickListener {
        final /* synthetic */ ECJiaGoodShopFragment a;

        ECJiaGoodShopFragment_6(ECJiaGoodShopFragment eCJiaGoodShopFragment) {
            this.a = eCJiaGoodShopFragment;
        }

        public void onClick(View view) {
            ac acVar = (ac) view.getTag();
            if (!acVar.f().equals("0")) {
                Intent intent = new Intent(this.a.t, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", acVar.c() + "");
                intent.putExtra("object_id", acVar.h());
                intent.putExtra("seckill", "立即秒杀");
                intent.putExtra("rec_type", "SPIKE_GOODS");
                this.a.t.startActivity(intent);
                this.a.t.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        }
    }

    private class a extends Thread {
        final /* synthetic */ ECJiaGoodShopFragment a;

        private a(ECJiaGoodShopFragment eCJiaGoodShopFragment) {
            this.a = eCJiaGoodShopFragment;
        }

        public void run() {
            while (!this.a.Z) {
                this.a.ac.sendEmptyMessage(101);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressLint({"ValidFragment"})
    public ECJiaGoodShopFragment(ECJiaHomeFragment eCJiaHomeFragment) {
        this.e = eCJiaHomeFragment;
    }

    public void onAttach(Activity activity) {
        this.t = activity;
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.k = this.t.getSharedPreferences(Constants.KEY_USER_ID, 0);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.m != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.m.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.m);
            }
        } else {
            this.m = layoutInflater.inflate(R.layout.fragment_good_shop, null);
            a();
            g();
            f();
            h();
            v();
            if (this.r.e != null) {
                if (this.r.e.a() == 0) {
                    this.i.setPullLoadEnable(false);
                } else {
                    this.i.setPullLoadEnable(true);
                }
            }
            this.Y = new a();
        }
        return this.m;
    }

    private void f() {
        if (this.j == null) {
            this.j = new r(this.t);
            this.j.a((com.ecjia.component.a.a.a) this);
        }
        this.j.a();
        if (this.r == null) {
            this.r = new af(this.t);
            this.r.a((com.ecjia.component.a.a.a) this);
        }
        this.r.a("");
        this.s = new y(this.t, this.r.a, e());
    }

    private void g() {
        this.i = (ECJiaMyXListView) this.m.findViewById(R.id.home_listview);
        this.i.setPullLoadEnable(true, true);
        this.i.setPullRefreshEnable(true);
        this.i.setXListViewListener(this, 0);
        this.i.setRefreshTime();
        this.i.setOnScrollListener(new ECJiaGoodShopFragment_2(this));
    }

    void a() {
        this.n = (ImageView) this.m.findViewById(R.id.back_top);
        this.o = false;
        this.q = 0;
        if (this.p) {
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
        this.n.setOnClickListener(new ECJiaGoodShopFragment_3(this));
    }

    private void h() {
        k();
        n();
        l();
        r();
        s();
        u();
        i();
        t();
        q();
        p();
        o();
        m();
    }

    private void i() {
        this.E = (LinearLayout) LayoutInflater.from(this.t).inflate(R.layout.home_groupbuy, null);
        this.O = (LinearLayout) this.E.findViewById(R.id.ll_home_groupbuy);
        this.F = (RelativeLayout) this.E.findViewById(R.id.banner_groupbuy_layout_in);
        this.G = (ViewPager) this.E.findViewById(R.id.banner_groupbuy_viewpager);
        this.H = new ArrayList();
        q.a("groupbuyListView=" + this.H.size());
        this.I = new ECJiaCirculatoryPagerAdapter(this.H);
        this.J = (LinearLayout) this.E.findViewById(R.id.groupbuy_viewGroup);
        this.K = (LinearLayout) this.E.findViewById(R.id.ll_home_groupbuy_enter);
        this.K.setOnClickListener(new ECJiaGoodShopFragment_4(this));
        this.T = (TextView) this.E.findViewById(R.id.tv_home_groupbuy_time_hour);
        this.U = (TextView) this.E.findViewById(R.id.tv_home_groupbuy_time_min);
        this.V = (TextView) this.E.findViewById(R.id.tv_home_groupbuy_time_sec);
        this.G.addOnPageChangeListener(new ECJiaGoodShopFragment_5(this));
        this.i.addHeaderView(this.E);
    }

    public void b() {
        this.H.clear();
        if (this.j.w.size() > 0) {
            for (int i = 0; i < this.j.w.size(); i++) {
                if (TextUtils.isEmpty(this.j.A)) {
                    ((ac) this.j.w.get(i)).a("2000-01-01 00:00:00");
                }
                ac acVar = (ac) this.j.w.get(i);
                this.N = LayoutInflater.from(this.t).inflate(R.layout.seckill_groupbuy_item, null);
                this.P = (ImageView) this.N.findViewById(R.id.iv_home_groupbuy);
                this.Q = (ECJiaAutoReturnView) this.N.findViewById(R.id.home_goodname);
                this.R = (TextView) this.N.findViewById(R.id.tv_home_groupbuy_price);
                this.S = (TextView) this.N.findViewById(R.id.tv_home_groupbuy_market_price);
                this.S.getPaint().setFlags(17);
                this.ad = (ImageView) this.N.findViewById(R.id.sechill_circular);
                this.ae = (TextView) this.N.findViewById(R.id.sechill_soldout);
                this.af = (TextView) this.N.findViewById(R.id.home_immediately);
                if (acVar.f().equals("0")) {
                    this.af.setText("已售完");
                    this.af.setBackgroundResource(R.drawable.selector_login_button_two);
                    this.ad.setVisibility(0);
                    this.ae.setVisibility(0);
                } else {
                    this.af.setText("立即秒杀");
                    this.af.setBackgroundResource(R.drawable.selector_login_button);
                    this.ad.setVisibility(8);
                    this.ae.setVisibility(8);
                }
                this.Q.setContent(acVar.g());
                this.R.setText(acVar.d());
                this.S.setText(acVar.e());
                p.a(this.t).a(this.P, acVar.b().getUrl());
                this.N.setTag(acVar);
                this.N.setOnClickListener(new ECJiaGoodShopFragment_6(this));
                this.H.add(this.N);
            }
            if (this.M) {
                c();
            }
            this.I.a = this.H;
            this.G.setAdapter(this.I);
            this.G.setCurrentItem(this.H.size() * 1000);
            this.I.notifyDataSetChanged();
        }
    }

    public void c() {
        this.J.removeAllViews();
        if (this.H.size() != 0) {
            if (this.H.size() == 1) {
                this.J.setVisibility(4);
            }
            for (int i = 0; i < this.j.w.size(); i++) {
                View view = new View(this.t);
                LayoutParams layoutParams = new LinearLayout.LayoutParams((int) this.c.getDimension(R.dimen.default_pointwidth), (int) this.c.getDimension(R.dimen.default_pointwidth));
                layoutParams.setMargins((int) this.c.getDimension(R.dimen.default_pointdistance), 0, (int) this.c.getDimension(R.dimen.default_pointdistance), 0);
                view.setLayoutParams(layoutParams);
                view.setBackgroundResource(R.drawable.selector_red_point_bg);
                view.setEnabled(false);
                this.J.addView(view);
            }
        }
    }

    private void j() throws InterruptedException {
        this.Z = false;
        if (this.Y != null) {
            this.Y.interrupt();
        } else {
            this.Y = new a();
        }
        if (!this.Y.isAlive()) {
            this.Y.start();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("seller/list")) {
            if (axVar.b() == 1) {
                this.i.stopLoadMore();
                this.i.stopRefresh();
                this.i.setRefreshTime();
                if (this.r.e.a() == 0) {
                    this.i.setPullLoadEnable(false);
                } else {
                    this.i.setPullLoadEnable(true);
                }
                if (this.r.a.size() == 0) {
                    this.g.a(null);
                } else {
                    this.g.a(this.r.a);
                }
                this.s.notifyDataSetChanged();
            }
        } else if (str.equals("home/data") && axVar.b() == 1) {
            this.i.stopRefresh();
            this.i.setRefreshTime();
            this.v.a(this.j.d);
            this.y.a(this.j.h);
            this.f.a(this.j.x);
            this.u.a(this.j.u);
            this.B.a(this.j.v);
            this.A.a(this.j.f);
            this.w.a(this.j.a);
            this.x.a(this.j.g);
            this.z.a(this.j.b);
            this.r.a("");
            this.v.d();
            this.f.e();
            q.a("spike_goodslist===" + this.j.w.size());
            if (this.j.w.size() > 0) {
                this.O.setVisibility(0);
                b();
            } else {
                this.O.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.j.A) && !TextUtils.isEmpty(this.j.z)) {
                switch (ab.a(this.j.z, this.j.A, "HH:mm:ss")) {
                    case -1:
                        this.aa = ab.k("yyyy/MM/dd ") + this.j.A;
                        q.a("===end_time-1===" + this.aa);
                        try {
                            j();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.X = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        this.W = this.X.format(new Date());
                        return;
                    case 0:
                        if (this.Y != null) {
                            this.Y.interrupt();
                            this.Y = null;
                            return;
                        }
                        return;
                    case 1:
                        this.aa = ab.l("yyyy/MM/dd ") + this.j.A;
                        q.a("===end_time1===" + this.aa);
                        try {
                            j();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        this.X = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        this.W = this.X.format(new Date());
                        return;
                    default:
                        return;
                }
            } else if (this.Y != null) {
                this.Y.interrupt();
                this.Y = null;
            }
        }
    }

    private void k() {
        this.v = new f(this.t);
        this.v.a(this.i);
    }

    private void l() {
        this.f = new l(this.t);
        this.f.a(this.i);
    }

    private void m() {
        this.g = new b(this.t);
        this.g.a(this.i);
    }

    private void n() {
        this.y = new i(this.t);
        this.y.a(this.i);
    }

    private void o() {
        this.z = new g(this.t);
        this.z.a(this.i);
    }

    private void p() {
        this.x = new e(this.t);
        this.x.a(this.i);
    }

    private void q() {
        this.w = new h(this.t);
        this.w.a(this.i);
    }

    private void r() {
        this.u = new k(this.t);
        this.u.a(this.i);
    }

    private void s() {
        this.B = new com.ecjia.hamster.a.a(this.t);
        this.B.a(this.i);
    }

    private void t() {
        this.A = new c(this.t);
        this.A.a(this.i);
    }

    private void u() {
        this.C = new j(this.t);
        this.C.a(this.i);
    }

    public void onResume() {
        super.onResume();
        if (!this.h) {
            this.h = true;
            com.ecjia.consts.b.a((com.ecjia.consts.b.a) this);
        }
        this.l = this.k.getString("uid", "");
        MobclickAgent.onPageStart("Home");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    private void v() {
        this.v.a(this.j.d);
        this.y.a(this.j.h);
        this.f.a(this.j.x);
        this.u.a(this.j.u);
        this.B.a(this.j.v);
        this.A.a(this.j.f);
        this.w.a(this.j.a);
        this.x.a(this.j.g);
        this.z.a(this.j.b);
        this.i.setAdapter(this.s);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onDestroy() {
        this.f.d();
        super.onDestroy();
    }

    public void a(int i) {
        this.d = true;
        this.i.setPullLoadEnable(true, true);
        this.j.a();
    }

    public void b(int i) {
        this.r.b("");
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Home");
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        Iterator it;
        ao aoVar;
        if ("add_collect_seller".equals(bVar.c())) {
            it = this.j.t.iterator();
            while (it.hasNext()) {
                aoVar = (ao) it.next();
                if (aoVar.e().equals(bVar.d())) {
                    aoVar.a(Integer.valueOf(aoVar.g().intValue() + 1));
                    this.s.notifyDataSetChanged();
                    return;
                }
            }
        } else if ("minus_collect_seller".equals(bVar.c())) {
            it = this.j.t.iterator();
            while (it.hasNext()) {
                aoVar = (ao) it.next();
                if (aoVar.e().equals(bVar.d())) {
                    aoVar.a(Integer.valueOf(aoVar.g().intValue() - 1));
                    this.s.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        if (!d()) {
            this.h = false;
        }
    }

    public void onDetach() {
        this.f.c();
        super.onDetach();
    }

    public boolean d() {
        ActivityManager activityManager = (ActivityManager) this.t.getApplicationContext().getSystemService("activity");
        String packageName = this.t.getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public void onDestroyView() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroyView();
    }

    public int e() {
        return Math.min(this.t.getWindowManager().getDefaultDisplay().getWidth(), this.t.getWindowManager().getDefaultDisplay().getHeight()) - (((int) getResources().getDimension(R.dimen.eight_margin)) * 2);
    }
}
