package com.ecjia.hamster.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import com.ecjia.a.ab;
import com.ecjia.component.a.p;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.t;
import com.ecjia.hamster.model.af;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.s;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ECJiaGroupbuyGoodsActivity extends a implements com.ecjia.component.a.a.a, com.ecjia.component.view.ECJiaXListView.a {
    private ECJiaXListView a;
    private t b;
    private p c;
    private ECJiaErrorView d;
    private String e;
    private boolean k;
    private Handler l = new ECJiaGroupbuyGoodsActivity_1(this);
    private a m;
    private SimpleDateFormat n;

    class ECJiaGroupbuyGoodsActivity_1 extends Handler {
        final /* synthetic */ ECJiaGroupbuyGoodsActivity a;

        ECJiaGroupbuyGoodsActivity_1(ECJiaGroupbuyGoodsActivity eCJiaGroupbuyGoodsActivity) {
            this.a = eCJiaGroupbuyGoodsActivity;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                int i = 0;
                for (int i2 = 0; i2 < this.a.c.b.size(); i2++) {
                    int b = ab.b(this.a.e, ((s) this.a.c.b.get(i2)).c());
                    int b2 = ab.b(this.a.e, ((s) this.a.c.b.get(i2)).d());
                    String str;
                    switch (b) {
                        case -1:
                            ((s) this.a.c.b.get(i2)).a(new af("距离活动开始还有" + ab.a(((s) this.a.c.b.get(i2)).c(), this.a.e, 0), ab.a(((s) this.a.c.b.get(i2)).c(), this.a.e, 1), ab.a(((s) this.a.c.b.get(i2)).c(), this.a.e, 2), ab.a(((s) this.a.c.b.get(i2)).c(), this.a.e, 3)));
                            break;
                        case 0:
                            str = "距离活动结束还有";
                            if (b2 == 0 || b2 == 1) {
                                i++;
                                str = "活动已结束";
                            }
                            ((s) this.a.c.b.get(i2)).a(new af(str + ab.a(this.a.e, ((s) this.a.c.b.get(i2)).d(), 0) + "天", ab.a(this.a.e, ((s) this.a.c.b.get(i2)).d(), 1), ab.a(this.a.e, ((s) this.a.c.b.get(i2)).d(), 2), ab.a(this.a.e, ((s) this.a.c.b.get(i2)).d(), 3)));
                            break;
                        case 1:
                            str = "距离活动结束还有";
                            if (b2 == 0 || b2 == 1) {
                                i++;
                                str = "活动已结束";
                            }
                            ((s) this.a.c.b.get(i2)).a(new af(str + ab.a(this.a.e, ((s) this.a.c.b.get(i2)).d(), 0) + "天", ab.a(this.a.e, ((s) this.a.c.b.get(i2)).d(), 1), ab.a(this.a.e, ((s) this.a.c.b.get(i2)).d(), 2), ab.a(this.a.e, ((s) this.a.c.b.get(i2)).d(), 3)));
                            break;
                        default:
                            break;
                    }
                }
                if (i == this.a.c.b.size()) {
                    this.a.k = true;
                }
                this.a.e = ab.d(this.a.e);
                this.a.b.notifyDataSetChanged();
            }
        }
    }

    class ECJiaGroupbuyGoodsActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaGroupbuyGoodsActivity a;

        ECJiaGroupbuyGoodsActivity_2(ECJiaGroupbuyGoodsActivity eCJiaGroupbuyGoodsActivity) {
            this.a = eCJiaGroupbuyGoodsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    private class a extends Thread {
        final /* synthetic */ ECJiaGroupbuyGoodsActivity a;

        private a(ECJiaGroupbuyGoodsActivity eCJiaGroupbuyGoodsActivity) {
            this.a = eCJiaGroupbuyGoodsActivity;
        }

        public void run() {
            while (!this.a.k) {
                this.a.l.sendEmptyMessage(1);
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
        setContentView(R.layout.activity_groupgoods);
        a();
        b();
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.group_topview);
        this.i.setLeftType(1);
        this.i.setLeftOnClickListener(new ECJiaGroupbuyGoodsActivity_2(this));
        if (getResources().getConfiguration().locale.equals(Locale.CHINA)) {
            this.i.setTitleImage((int) R.drawable.icon_title_groupbuy);
        } else {
            this.i.setTitleImage((int) R.drawable.icon_title_groupbuy_en);
        }
    }

    private void b() {
        PushAgent.getInstance(this).onAppStart();
        this.c = new p(this);
        this.a = (ECJiaXListView) findViewById(R.id.group_listview);
        this.d = (ECJiaErrorView) findViewById(R.id.group_null_pager);
        this.a.setPullLoadEnable(false);
        this.a.setPullRefreshEnable(true);
        this.a.setXListViewListener(this, 1);
        this.c.a((com.ecjia.component.a.a.a) this);
        this.c.a();
        this.b = new t(this, this.c.b);
        this.a.setAdapter(this.b);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    private void c() {
        if (this.c.b.size() > 0) {
            this.a.setVisibility(0);
            this.d.setVisibility(8);
            this.b.a(this.c.b);
            this.b.notifyDataSetChanged();
            try {
                e();
            } catch (InterruptedException e) {
            }
            this.n = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.e = this.n.format(new Date());
            return;
        }
        this.a.setVisibility(8);
        this.d.setVisibility(0);
        this.k = true;
        if (this.m != null) {
            this.m.interrupt();
            this.m = null;
        }
    }

    private void e() throws InterruptedException {
        this.k = false;
        if (this.m != null) {
            this.m.interrupt();
        } else {
            this.m = new a();
        }
        if (!this.m.isInterrupted()) {
            this.m.start();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("goods/groupbuygoods")) {
            return;
        }
        if (axVar.b() == 1) {
            this.a.setRefreshTime();
            this.a.stopRefresh();
            this.a.stopLoadMore();
            if (this.c.a.a() == 1) {
                this.a.setPullLoadEnable(true);
            } else {
                this.a.setPullLoadEnable(false);
            }
            c();
            return;
        }
        this.a.setVisibility(8);
        this.d.setVisibility(0);
        new k((Context) this, getResources().getString(R.string.payment_network_problem)).a();
    }

    public void a(int i) {
        this.k = true;
        if (this.m != null) {
            this.m.interrupt();
            this.m = null;
        }
        this.c.a();
    }

    public void b(int i) {
        this.c.b();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.k = true;
        if (this.m != null) {
            this.m.interrupt();
            this.m = null;
        }
    }
}
