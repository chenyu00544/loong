package com.ecjia.hamster.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.x;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.adapter.j;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaLogisticsActivity extends a implements a {
    private TextView a;
    private TextView b;
    private TextView c;
    private x d;
    private ListView e;
    private LinearLayout k;
    private LinearLayout l;
    private j m;
    private View n;

    class ECJiaLogisticsActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaLogisticsActivity a;

        ECJiaLogisticsActivity_1(ECJiaLogisticsActivity eCJiaLogisticsActivity) {
            this.a = eCJiaLogisticsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_logistics);
        PushAgent.getInstance(this).onAppStart();
        c();
        this.d = new x(this);
        this.d.a((a) this);
        this.d.d(getIntent().getStringExtra("order_id"));
    }

    @TargetApi(11)
    private void b() {
        if (this.d.d.size() == 0 || this.d.d == null) {
            this.k.setVisibility(8);
        }
        this.e.setVisibility(0);
        this.m = new j(this, this.d.c);
        this.e.setAdapter(this.m);
    }

    private void c() {
        getResources();
        a();
        this.l = (LinearLayout) findViewById(R.id.ll_logistic_top);
        this.k = (LinearLayout) findViewById(R.id.head_below);
        this.b = (TextView) findViewById(R.id.logistics_status);
        this.a = (TextView) findViewById(R.id.company_name);
        this.c = (TextView) findViewById(R.id.log_no);
        this.n = findViewById(R.id.null_pager);
        this.e = (ListView) findViewById(R.id.log_list);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("order_status"))) {
            this.b.setText(getIntent().getStringExtra("order_status"));
        }
        if (!TextUtils.isEmpty(getIntent().getStringExtra("shippingname"))) {
            this.a.setText(getIntent().getStringExtra("shippingname"));
        }
        if (!TextUtils.isEmpty(getIntent().getStringExtra("shipping_number"))) {
            this.c.setText(getIntent().getStringExtra("shipping_number"));
        }
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.logistics_topview);
        this.i.setTitleText((int) R.string.logistics_info);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaLogisticsActivity_1(this));
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("order/express")) {
            return;
        }
        if (axVar.b() == 1) {
            this.l.setVisibility(0);
            b();
            if (!TextUtils.isEmpty(this.d.j)) {
                this.b.setText(this.d.j);
            }
            if (!TextUtils.isEmpty(this.d.h)) {
                this.a.setText(this.d.h);
            }
            if (!TextUtils.isEmpty(this.d.i)) {
                this.c.setText(this.d.i);
            }
            if (this.d.d.size() > 0) {
                this.n.setVisibility(0);
                return;
            } else {
                this.n.setVisibility(8);
                return;
            }
        }
        this.n.setVisibility(0);
        this.l.setVisibility(8);
    }
}
