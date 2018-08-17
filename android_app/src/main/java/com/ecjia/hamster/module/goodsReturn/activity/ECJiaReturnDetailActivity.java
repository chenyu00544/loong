package com.ecjia.hamster.module.goodsReturn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.y;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.module.goodsReturn.ECJiaWidthChangeAbleRecyclerView;
import com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_DETAIL.RETURN_LOG;
import com.ecmoban.android.missmall.R;

public class ECJiaReturnDetailActivity extends a implements com.ecjia.component.a.a.a {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView k;
    ECJiaWidthChangeAbleRecyclerView l;
    LinearLayout m;
    TextView n;
    String o;
    private com.ecjia.hamster.module.goodsReturn.a p;
    private TextView q;

    class ECJiaReturnDetailActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnDetailActivity a;

        ECJiaReturnDetailActivity_1(ECJiaReturnDetailActivity eCJiaReturnDetailActivity) {
            this.a = eCJiaReturnDetailActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a, ECJiaReturnProcessingActivity.class);
            intent.putExtra("return_detail", this.a.p.c);
            this.a.startActivity(intent);
        }
    }

    class ECJiaReturnDetailActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaReturnDetailActivity a;

        ECJiaReturnDetailActivity_2(ECJiaReturnDetailActivity eCJiaReturnDetailActivity) {
            this.a = eCJiaReturnDetailActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a, ECJiaReturnFeeDetailActivity.class);
            intent.putExtra("return_detail", this.a.p.c);
            this.a.startActivity(intent);
        }
    }

    class ECJiaReturnDetailActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaReturnDetailActivity a;

        ECJiaReturnDetailActivity_3(ECJiaReturnDetailActivity eCJiaReturnDetailActivity) {
            this.a = eCJiaReturnDetailActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_return_detail);
        c();
        b();
        this.p = new com.ecjia.hamster.module.goodsReturn.a(this);
        this.p.a((com.ecjia.component.a.a.a) this);
        this.p.b(this.o);
    }

    private void b() {
        this.o = getIntent().getStringExtra("return_id");
    }

    private void c() {
        a();
        findViewById(R.id.process_ll).setOnClickListener(new ECJiaReturnDetailActivity_1(this));
        this.a = (TextView) findViewById(R.id.service_sn);
        this.b = (TextView) findViewById(R.id.order_sn);
        this.c = (TextView) findViewById(R.id.return_status);
        this.d = (TextView) findViewById(R.id.processing);
        this.q = (TextView) findViewById(R.id.reason);
        this.e = (TextView) findViewById(R.id.description);
        this.k = (TextView) findViewById(R.id.apply_time);
        this.n = (TextView) findViewById(R.id.tv_return_refused);
        this.m = (LinearLayout) findViewById(R.id.ll_return_refused);
        this.l = (ECJiaWidthChangeAbleRecyclerView) findViewById(R.id.image_list);
        this.l.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.l.setOritation(0);
        this.l.getLayoutParams().height = (y.b(this) - y.a(this, 20)) / 5;
        findViewById(R.id.return_fee_detail).setOnClickListener(new ECJiaReturnDetailActivity_2(this));
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.return_detail_topview);
        this.i.setTitleType(TitleType.TEXT);
        this.i.setTitleText((int) R.string.return_service_order_detail);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaReturnDetailActivity_3(this));
    }

    public void a(String str, String str2, ax axVar) {
        if ("order/return/detail".equals(str) && axVar.b() == 1) {
            this.a.setText(this.p.c.getReturn_sn());
            this.b.setText(this.p.c.getOrder_sn());
            this.c.setText(this.p.c.getLabel_return_status());
            this.q.setText(this.p.c.getLabel_return_reason());
            this.d.setText(((RETURN_LOG) this.p.c.getReturn_log().get(0)).getLog_description());
            if (TextUtils.isEmpty(this.p.c.getReturn_description())) {
                this.e.setText("暂无描述内容");
            } else {
                this.e.setText(this.p.c.getReturn_description());
            }
            this.l.setAdapter(new com.ecjia.hamster.module.goodsReturn.a.a(this, this.p.c.getReturn_images(), (((y.b(this) - y.a(this, 20)) / 5) - y.a(this, 10)) + y.a(this, 10), false));
            this.k.setText(this.p.c.getCreate_time());
            if ("refused".equals(this.p.c.getReturn_status()) && !TextUtils.isEmpty(this.p.c.getRefused_reason())) {
                this.m.setVisibility(0);
                this.n.setText(this.p.c.getRefused_reason());
            }
            if (this.p.c.getReturn_type().equals("return")) {
                findViewById(R.id.return_fee_detail_ll).setVisibility(0);
            } else {
                findViewById(R.id.return_fee_detail_ll).setVisibility(8);
            }
        }
    }
}
