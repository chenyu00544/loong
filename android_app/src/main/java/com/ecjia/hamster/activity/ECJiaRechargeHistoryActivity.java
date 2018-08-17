package com.ecjia.hamster.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.aa;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.ay;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaRechargeHistoryActivity extends a implements a, ECJiaXListView.a {
    Resources a;
    aa b;
    ay c;
    String d;
    Resources e;
    private TextView k;
    private TextView l;
    private TextView m;
    private LinearLayout n;
    private ECJiaXListView o;
    private View p;

    class ECJiaRechargeHistoryActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaRechargeHistoryActivity a;

        ECJiaRechargeHistoryActivity_1(ECJiaRechargeHistoryActivity eCJiaRechargeHistoryActivity) {
            this.a = eCJiaRechargeHistoryActivity;
        }

        public void onClick(View view) {
            this.a.a("");
            this.a.b.a(this.a.d);
        }
    }

    class ECJiaRechargeHistoryActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaRechargeHistoryActivity a;

        ECJiaRechargeHistoryActivity_2(ECJiaRechargeHistoryActivity eCJiaRechargeHistoryActivity) {
            this.a = eCJiaRechargeHistoryActivity;
        }

        public void onClick(View view) {
            this.a.a("raply");
            this.a.b.a(this.a.d);
        }
    }

    class ECJiaRechargeHistoryActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaRechargeHistoryActivity a;

        ECJiaRechargeHistoryActivity_3(ECJiaRechargeHistoryActivity eCJiaRechargeHistoryActivity) {
            this.a = eCJiaRechargeHistoryActivity;
        }

        public void onClick(View view) {
            this.a.a("deposit");
            this.a.b.a(this.a.d);
        }
    }

    class ECJiaRechargeHistoryActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaRechargeHistoryActivity a;

        ECJiaRechargeHistoryActivity_4(ECJiaRechargeHistoryActivity eCJiaRechargeHistoryActivity) {
            this.a = eCJiaRechargeHistoryActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_recharge_history);
        c.a().a((Object) this);
        PushAgent.getInstance(this).onAppStart();
        b();
    }

    private void b() {
        a();
        this.e = getResources();
        this.b = new aa(this);
        this.b.a((a) this);
        this.a = getBaseContext().getResources();
        this.n = (LinearLayout) findViewById(R.id.history_head_bg);
        this.k = (TextView) findViewById(R.id.head_all);
        this.m = (TextView) findViewById(R.id.head_withdrawal);
        this.l = (TextView) findViewById(R.id.head_recharge);
        this.o = (ECJiaXListView) findViewById(R.id.recharge_list);
        this.p = findViewById(R.id.null_pager);
        this.o.setPullLoadEnable(true);
        this.o.setRefreshTime();
        this.o.setXListViewListener(this, 1);
        this.k.setOnClickListener(new ECJiaRechargeHistoryActivity_1(this));
        this.m.setOnClickListener(new ECJiaRechargeHistoryActivity_2(this));
        this.l.setOnClickListener(new ECJiaRechargeHistoryActivity_3(this));
        a("");
        this.b.a(this.d);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.recharge_history_topview);
        this.i.setTitleText((int) R.string.accoubt_record);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaRechargeHistoryActivity_4(this));
    }

    private void a(String str) {
        if ("".equals(str)) {
            this.n.setBackgroundResource(R.drawable.history_all);
            this.k.setTextColor(-1);
            this.m.setTextColor(this.a.getColor(R.color.trade_head_selectbg));
            this.l.setTextColor(this.a.getColor(R.color.trade_head_selectbg));
            this.k.setEnabled(false);
            this.m.setEnabled(true);
            this.l.setEnabled(true);
            this.d = "";
        } else if ("raply".equals(str)) {
            this.n.setBackgroundResource(R.drawable.history_withdrawal);
            this.m.setTextColor(-1);
            this.k.setTextColor(this.a.getColor(R.color.trade_head_selectbg));
            this.l.setTextColor(this.a.getColor(R.color.trade_head_selectbg));
            this.k.setEnabled(true);
            this.m.setEnabled(false);
            this.l.setEnabled(true);
            this.d = "raply";
        } else if ("deposit".equals(str)) {
            this.n.setBackgroundResource(R.drawable.history_recharge);
            this.l.setTextColor(-1);
            this.k.setTextColor(this.a.getColor(R.color.trade_head_selectbg));
            this.m.setTextColor(this.a.getColor(R.color.trade_head_selectbg));
            this.k.setEnabled(true);
            this.m.setEnabled(true);
            this.l.setEnabled(false);
            this.d = "deposit";
        }
    }

    private void c() {
        if (this.c != null) {
            if (this.b.b.size() == 0) {
                this.p.setVisibility(0);
                this.o.setVisibility(8);
            } else {
                this.p.setVisibility(8);
                this.o.setVisibility(0);
                this.c.a = this.b.b;
            }
            this.c.notifyDataSetChanged();
        } else if (this.b.b.size() == 0) {
            this.p.setVisibility(0);
            this.o.setVisibility(8);
        } else {
            this.p.setVisibility(8);
            this.o.setVisibility(0);
            this.c = new ay(this, this.b.b);
            this.o.setAdapter(this.c);
        }
    }

    public void a(int i) {
        this.b.a(this.d);
    }

    public void b(int i) {
        this.b.b(this.d);
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
        if ("recharge_cancel".equals(aVar.c())) {
            this.b.a(this.d);
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/account/record") && axVar.b() == 1) {
            this.o.stopRefresh();
            this.o.stopLoadMore();
            this.o.setRefreshTime();
            c();
            if (this.b.c.a() == 0) {
                this.o.setPullLoadEnable(false);
            } else {
                this.o.setPullLoadEnable(true);
            }
        }
    }
}
