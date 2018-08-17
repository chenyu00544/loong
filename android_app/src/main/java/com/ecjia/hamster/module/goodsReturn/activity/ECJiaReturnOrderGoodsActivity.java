package com.ecjia.hamster.module.goodsReturn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.model.ECJia_ORDERDETAIL;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecjia.hamster.module.goodsReturn.a.b;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.io.Serializable;
import java.util.ArrayList;

public class ECJiaReturnOrderGoodsActivity extends a implements com.ecjia.hamster.b.a {
    ECJia_ORDERDETAIL a;
    private ArrayList<ECJia_ORDER_GOODS_LIST> b;
    private ListView c;
    private b d;
    private String e;
    private String k;

    class ECJiaReturnOrderGoodsActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnOrderGoodsActivity a;

        ECJiaReturnOrderGoodsActivity_1(ECJiaReturnOrderGoodsActivity eCJiaReturnOrderGoodsActivity) {
            this.a = eCJiaReturnOrderGoodsActivity;
        }

        public void onClick(View view) {
            this.a.findViewById(R.id.show_all).setVisibility(8);
            this.a.d = new b(this.a, this.a.b);
            this.a.d.a(this.a);
            this.a.c.setAdapter(this.a.d);
        }
    }

    class ECJiaReturnOrderGoodsActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaReturnOrderGoodsActivity a;

        ECJiaReturnOrderGoodsActivity_2(ECJiaReturnOrderGoodsActivity eCJiaReturnOrderGoodsActivity) {
            this.a = eCJiaReturnOrderGoodsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_return_order_goodslist);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        a();
        b();
        this.c = (ListView) findViewById(R.id.trade_list);
        if (this.b != null) {
            if (this.b.size() <= 3) {
                findViewById(R.id.show_all).setVisibility(8);
                this.d = new b(this, this.b);
                this.d.a((com.ecjia.hamster.b.a) this);
                this.c.setAdapter(this.d);
                findViewById(R.id.show_all).setVisibility(8);
            } else {
                findViewById(R.id.show_all).setVisibility(0);
                findViewById(R.id.show_all).setOnClickListener(new ECJiaReturnOrderGoodsActivity_1(this));
                this.d = new b(this, this.b.subList(0, 3));
                this.d.a((com.ecjia.hamster.b.a) this);
                this.c.setAdapter(this.d);
            }
            findViewById(R.id.null_pager).setVisibility(8);
            this.c.setVisibility(0);
            return;
        }
        findViewById(R.id.null_pager).setVisibility(0);
        findViewById(R.id.show_all).setVisibility(8);
        this.c.setVisibility(8);
    }

    private void b() {
        this.a = (ECJia_ORDERDETAIL) getIntent().getSerializableExtra("order_detail");
        if (this.a != null) {
            ((TextView) findViewById(R.id.order_time)).setText(this.a.getOrder_time());
            ((TextView) findViewById(R.id.order_sn)).setText(this.a.getOrder_sn());
            this.e = this.a.getOrder_id();
            this.k = this.a.getService_phone();
        }
        this.b = (ArrayList) getIntent().getSerializableExtra("goods_list");
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.return_ordergoods_topview);
        this.i.setTitleType(TitleType.TEXT);
        this.i.setTitleText((int) R.string.return_apply);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaReturnOrderGoodsActivity_2(this));
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        if ("RETURN_APPLY_SUCCESS".equals(bVar.c())) {
            finish();
        }
    }

    public void a(int i, int i2, Object obj) {
        Intent intent = new Intent(this, ECJiaReturnApplyFirstActivity.class);
        intent.putExtra("order_goods_list", (Serializable) this.b.get(i2));
        intent.putExtra("order_detail", this.a);
        startActivity(intent);
    }
}
