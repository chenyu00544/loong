package com.ecjia.hamster.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.ecjia.a.ab;
import com.ecjia.component.a.ai;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.ao;
import com.ecjia.hamster.adapter.av;
import com.ecjia.hamster.model.af;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.az;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ECJiaPromotionalGoodsActivity extends a implements com.ecjia.component.a.a.a, com.ecjia.component.view.ECJiaXListView.a {
    final String a = "hot";
    final String b = "new";
    final String c = "best";
    final String d = "promotion";
    private ECJiaXListView e;
    private av k;
    private ao l;
    private ai m;
    private ECJiaErrorView n;
    private String o = "new";
    private String p;
    private boolean q;
    private Handler r = new ECJiaPromotionalGoodsActivity_1(this);
    private a s;
    private SimpleDateFormat t;

    class ECJiaPromotionalGoodsActivity_1 extends Handler {
        final /* synthetic */ ECJiaPromotionalGoodsActivity a;

        ECJiaPromotionalGoodsActivity_1(ECJiaPromotionalGoodsActivity eCJiaPromotionalGoodsActivity) {
            this.a = eCJiaPromotionalGoodsActivity;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                int i = 0;
                for (int i2 = 0; i2 < this.a.m.a.size(); i2++) {
                    int b = ab.b(this.a.p, ((az) this.a.m.a.get(i2)).f());
                    int b2 = ab.b(this.a.p, ((az) this.a.m.a.get(i2)).g());
                    String str;
                    switch (b) {
                        case -1:
                            ((az) this.a.m.a.get(i2)).a(new af("距离活动开始还有" + ab.a(((az) this.a.m.a.get(i2)).f(), this.a.p, 0), ab.a(((az) this.a.m.a.get(i2)).f(), this.a.p, 1), ab.a(((az) this.a.m.a.get(i2)).f(), this.a.p, 2), ab.a(((az) this.a.m.a.get(i2)).f(), this.a.p, 3)));
                            break;
                        case 0:
                            str = "距离活动结束还有";
                            if (b2 == 0 || b2 == 1) {
                                i++;
                                str = "活动已结束";
                            }
                            ((az) this.a.m.a.get(i2)).a(new af(str + ab.a(this.a.p, ((az) this.a.m.a.get(i2)).g(), 0) + "天", ab.a(this.a.p, ((az) this.a.m.a.get(i2)).g(), 1), ab.a(this.a.p, ((az) this.a.m.a.get(i2)).g(), 2), ab.a(this.a.p, ((az) this.a.m.a.get(i2)).g(), 3)));
                            break;
                        case 1:
                            str = "距离活动结束还有";
                            if (b2 == 0 || b2 == 1) {
                                i++;
                                str = "活动已结束";
                            }
                            ((az) this.a.m.a.get(i2)).a(new af(str + ab.a(this.a.p, ((az) this.a.m.a.get(i2)).g(), 0) + "天", ab.a(this.a.p, ((az) this.a.m.a.get(i2)).g(), 1), ab.a(this.a.p, ((az) this.a.m.a.get(i2)).g(), 2), ab.a(this.a.p, ((az) this.a.m.a.get(i2)).g(), 3)));
                            break;
                        default:
                            break;
                    }
                }
                if (i == this.a.m.a.size()) {
                    this.a.q = true;
                }
                this.a.p = ab.d(this.a.p);
                this.a.l.notifyDataSetChanged();
            }
        }
    }

    class ECJiaPromotionalGoodsActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaPromotionalGoodsActivity a;

        ECJiaPromotionalGoodsActivity_2(ECJiaPromotionalGoodsActivity eCJiaPromotionalGoodsActivity) {
            this.a = eCJiaPromotionalGoodsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    private class a extends Thread {
        final /* synthetic */ ECJiaPromotionalGoodsActivity a;

        private a(ECJiaPromotionalGoodsActivity eCJiaPromotionalGoodsActivity) {
            this.a = eCJiaPromotionalGoodsActivity;
        }

        public void run() {
            while (!this.a.q) {
                this.a.r.sendEmptyMessage(1);
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
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_promotionalgoods);
        b();
    }

    private void b() {
        this.o = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(this.o)) {
            this.o = "new";
        }
        this.n = (ECJiaErrorView) findViewById(R.id.mobile_null_pager);
        a();
        this.e = (ECJiaXListView) findViewById(R.id.mobile_listview);
        this.e.setPullLoadEnable(false);
        this.e.setPullRefreshEnable(true);
        this.e.setXListViewListener(this, 1);
        this.m = new ai(this);
        this.m.a((com.ecjia.component.a.a.a) this);
        this.m.a(this.o);
        this.k = new av(this, this.m.a);
        this.l = new ao(this, this.m.a);
        if (this.o.equals("promotion")) {
            this.e.setAdapter(this.l);
        } else {
            this.e.setAdapter(this.k);
        }
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.promotion_topview);
        this.i.setLeftType(1);
        this.i.setLeftOnClickListener(new ECJiaPromotionalGoodsActivity_2(this));
        String str = this.o;
        int i = -1;
        switch (str.hashCode()) {
            case -799212381:
                if (str.equals("promotion")) {
                    i = 3;
                    break;
                }
                break;
            case 103501:
                if (str.equals("hot")) {
                    i = 0;
                    break;
                }
                break;
            case 108960:
                if (str.equals("new")) {
                    i = 1;
                    break;
                }
                break;
            case 3020260:
                if (str.equals("best")) {
                    i = 2;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                this.i.setTitleText((int) R.string.suggest_hot);
                return;
            case 1:
                this.i.setTitleText((int) R.string.newgoods);
                this.n.setErrorImageResource(R.drawable.null_normal);
                return;
            case 2:
                this.i.setTitleText((int) R.string.suggest_best);
                return;
            case 3:
                if (getResources().getConfiguration().locale.equals(Locale.CHINA)) {
                    this.i.setTitleImage((int) R.drawable.icon_title_promotion);
                } else {
                    this.i.setTitleImage((int) R.drawable.icon_title_promotion_en);
                }
                this.n.setErrorImageResource(R.drawable.null_normal);
                return;
            default:
                this.i.setTitleText((int) R.string.newgoods);
                return;
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    private void c() {
        if (this.m.a.size() > 0) {
            this.e.setVisibility(0);
            this.n.setVisibility(8);
            if (this.o.equals("promotion")) {
                this.l.a(this.m.a);
                this.l.notifyDataSetChanged();
                try {
                    e();
                } catch (InterruptedException e) {
                }
                this.t = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                this.p = this.t.format(new Date());
                return;
            }
            this.k.a(this.m.a);
            this.k.notifyDataSetChanged();
            return;
        }
        this.e.setVisibility(8);
        this.n.setVisibility(0);
        if (this.o.equals("promotion")) {
            this.q = true;
            if (this.s != null) {
                this.s.interrupt();
                this.s = null;
            }
        }
    }

    private void e() throws InterruptedException {
        this.q = false;
        if (this.s != null) {
            this.s.interrupt();
        } else {
            this.s = new a();
        }
        if (!this.s.isInterrupted()) {
            this.s.start();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("goods/suggestlist")) {
            return;
        }
        if (axVar.b() == 1) {
            this.e.setRefreshTime();
            this.e.stopRefresh();
            this.e.stopLoadMore();
            if (this.m.b.a() == 1) {
                this.e.setPullLoadEnable(true);
            } else {
                this.e.setPullLoadEnable(false);
            }
            c();
            return;
        }
        this.e.setVisibility(8);
        this.n.setVisibility(0);
        new k((Context) this, getResources().getString(R.string.payment_network_problem)).a();
    }

    public void a(int i) {
        if (this.o.equals("promotion")) {
            this.q = true;
            if (this.s != null) {
                this.s.interrupt();
                this.s = null;
            }
        }
        this.m.a(this.o);
    }

    public void b(int i) {
        this.m.b(this.o);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.o.equals("promotion")) {
            this.q = true;
            if (this.s != null) {
                this.s.interrupt();
                this.s = null;
            }
        }
    }
}
