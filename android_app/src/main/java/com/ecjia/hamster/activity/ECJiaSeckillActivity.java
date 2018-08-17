package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.ab;
import com.ecjia.a.q;
import com.ecjia.component.a.o;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.bf;
import com.ecjia.hamster.adapter.bf.b;
import com.ecjia.hamster.adapter.bg;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ac;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ECJiaSeckillActivity extends a implements com.ecjia.component.a.a.a, com.ecjia.component.view.ECJiaXListView.a {
    private ECJiaErrorView A;
    private RecyclerView a;
    private bf b;
    private ECJiaXListView c;
    private bg d;
    private o e;
    private ECJia_FILTER k = new ECJia_FILTER();
    private String l;
    private ArrayList<ac> m = new ArrayList();
    private String n;
    private boolean o;
    private String p;
    private Handler q = new ECJiaSeckillActivity_1(this);
    private a r;
    private SimpleDateFormat s;
    private TextView t;
    private TextView u;
    private TextView v;
    private String w = "立即秒杀";
    private TextView x;
    private int y;
    private LinearLayout z;

    class ECJiaSeckillActivity_1 extends Handler {
        final /* synthetic */ ECJiaSeckillActivity a;

        ECJiaSeckillActivity_1(ECJiaSeckillActivity eCJiaSeckillActivity) {
            this.a = eCJiaSeckillActivity;
        }

        public void handleMessage(Message message) {
            if (message.what == 2) {
                boolean z = false;
                q.a("countdownView_ll4====" + this.a.p);
                CharSequence b = ab.b(this.a.n, this.a.p, 1);
                CharSequence b2 = ab.b(this.a.n, this.a.p, 2);
                CharSequence b3 = ab.b(this.a.n, this.a.p, 3);
                if (b.equals("00") && b2.equals("00") && b3.equals("00")) {
                    z = true;
                }
                this.a.t.setText(b);
                this.a.u.setText(b2);
                this.a.v.setText(b3);
                if (z == this.a.e.c.size()) {
                    this.a.o = true;
                }
                this.a.n = ab.e(this.a.n);
            }
        }
    }

    class ECJiaSeckillActivity_2 implements b {
        final /* synthetic */ ECJiaSeckillActivity a;

        ECJiaSeckillActivity_2(ECJiaSeckillActivity eCJiaSeckillActivity) {
            this.a = eCJiaSeckillActivity;
        }

        public void a(View view, int i) {
            q.a("spikegoodslist===" + ((ac) this.a.e.b.get(i)).j());
            this.a.y = i;
            this.a.e.a(((ac) this.a.e.b.get(i)).j());
            for (int i2 = 0; i2 < this.a.e.b.size(); i2++) {
                if (i2 == i) {
                    ((ac) this.a.e.b.get(i2)).a(true);
                } else {
                    ((ac) this.a.e.b.get(i2)).a(false);
                }
            }
            this.a.b.notifyDataSetChanged();
            this.a.w = ((ac) this.a.e.b.get(i)).l();
            this.a.o = true;
            if (this.a.r != null) {
                this.a.r.interrupt();
                this.a.r = null;
            }
        }
    }

    class ECJiaSeckillActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaSeckillActivity a;

        ECJiaSeckillActivity_3(ECJiaSeckillActivity eCJiaSeckillActivity) {
            this.a = eCJiaSeckillActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    private class a extends Thread {
        final /* synthetic */ ECJiaSeckillActivity a;

        private a(ECJiaSeckillActivity eCJiaSeckillActivity) {
            this.a = eCJiaSeckillActivity;
        }

        public void run() {
            while (!this.a.o) {
                this.a.q.sendEmptyMessage(2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.seckill_times);
        this.z = (LinearLayout) findViewById(R.id.seckill_itme_activity);
        this.A = (ECJiaErrorView) findViewById(R.id.null_pager);
        this.z.setVisibility(0);
        this.A.setVisibility(8);
        a();
        b();
    }

    private void b() {
        this.a = (RecyclerView) findViewById(R.id.sechill_recyclerView);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.a.setLayoutManager(linearLayoutManager);
        this.e = new o(this);
        this.e.a((com.ecjia.component.a.a.a) this);
        this.e.a();
        this.x = (TextView) findViewById(R.id.seckill__name);
        this.b = new bf(this, this.e.b);
        this.a.setAdapter(this.b);
        this.b.a(new ECJiaSeckillActivity_2(this));
        this.c = (ECJiaXListView) findViewById(R.id.sechill_listview);
        this.c.setPullLoadEnable(true);
        this.c.setRefreshTime();
        this.c.setXListViewListener(this, 1);
    }

    private String c() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        Calendar instance = Calendar.getInstance();
        instance.roll(6, 1);
        q.a("countdownView_ll4====" + simpleDateFormat.format(instance.getTime()));
        return simpleDateFormat.format(instance.getTime());
    }

    private String e() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        Calendar instance = Calendar.getInstance();
        simpleDateFormat.format(instance.getTime());
        q.a("countdownView_ll4====" + simpleDateFormat.format(instance.getTime()));
        return simpleDateFormat.format(instance.getTime());
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.seckill_topview);
        this.i.setLeftType(1);
        this.i.setLeftOnClickListener(new ECJiaSeckillActivity_3(this));
        if (getResources().getConfiguration().locale.equals(Locale.CHINA)) {
            this.i.setTitleImage((int) R.drawable.icon_title_seckill);
        } else {
            this.i.setTitleImage((int) R.drawable.icon_title_seckill_en);
        }
        this.t = (TextView) findViewById(R.id.tv_home_groupbuy_time_hour);
        this.u = (TextView) findViewById(R.id.tv_home_groupbuy_time_min);
        this.v = (TextView) findViewById(R.id.tv_home_groupbuy_time_sec);
    }

    private void f() {
        if (this.e.b.size() > 0) {
            try {
                g();
            } catch (InterruptedException e) {
            }
            this.s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.n = this.s.format(new Date());
            return;
        }
        this.o = true;
        if (this.r != null) {
            this.r.interrupt();
            this.r = null;
        }
    }

    private void g() throws InterruptedException {
        this.o = false;
        if (this.r != null) {
            this.r.interrupt();
        } else {
            this.r = new a();
        }
        if (!this.r.isInterrupted()) {
            this.r.start();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("goods/spike/period")) {
            if (axVar.b() == 1) {
                for (int i = 0; i < this.e.b.size(); i++) {
                    if (i == 0) {
                        ((ac) this.e.b.get(i)).a(true);
                    } else {
                        ((ac) this.e.b.get(i)).a(false);
                    }
                }
                if (this.e.b == null || this.e.b.size() <= 0) {
                    this.z.setVisibility(8);
                    this.A.setVisibility(0);
                    return;
                }
                this.l = ((ac) this.e.b.get(0)).j();
                this.e.a(this.l);
                q.a("but_sechill1===" + ((ac) this.e.b.get(0)).l());
                this.b.notifyDataSetChanged();
                this.w = ((ac) this.e.b.get(0)).l();
            }
        } else if (str.equals("goods/spike/goodslist") && axVar.b() == 1) {
            this.c.stopRefresh();
            this.c.stopLoadMore();
            this.c.setRefreshTime();
            if (this.e.a(this.e.h)) {
                this.c.setPullLoadEnable(true);
            } else {
                this.c.setPullLoadEnable(false);
            }
            q.a("bunnte===" + this.w);
            this.d = new bg(this, this.e.c, this.w);
            this.c.setAdapter(this.d);
            this.x.setText(this.g.getString(R.string.home_spike_sold_out));
            this.p = e() + this.e.t;
            if (((ac) this.e.b.get(this.y)).m().equals("going")) {
                this.x.setText(this.g.getString(R.string.home_spike_sold_out));
                this.p = e() + this.e.t;
                q.a("countdownView_ll4====1" + this.e.t);
            } else if (((ac) this.e.b.get(this.y)).m().equals("coming")) {
                this.x.setText(this.g.getString(R.string.home_spike_sold_start));
                this.p = e() + this.e.j;
                q.a("countdownView_ll4====2" + this.e.j);
            } else if (((ac) this.e.b.get(this.y)).m().equals("finished")) {
                q.a("countdownView_ll4====3" + this.e.j);
                this.x.setText(this.g.getString(R.string.home_spike_sold_start));
                this.p = c() + this.e.j;
            }
            f();
        }
    }

    public void a(int i) {
        this.o = true;
        if (this.r != null) {
            this.r.interrupt();
            this.r = null;
        }
        this.e.a(this.l);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.o = true;
        if (this.r != null) {
            this.r.interrupt();
            this.r = null;
        }
    }

    public void b(int i) {
        this.e.b(this.l);
    }
}
