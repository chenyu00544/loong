package com.ecjia.hamster.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ecjia.component.view.ECJiaMyGridView;
import com.ecjia.component.view.ECJiaMyListView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.adapter.ECJiaNewPaymentAdapter;
import com.ecjia.hamster.adapter.ECJiaStoreGoodsListAdapter;
import com.ecjia.hamster.adapter.ECJiaStoreGoodsListAdapter.a;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecjia.hamster.model.ECJia_SHIPPING;
import com.ecjia.hamster.model.ECJia_STOREGOODSLIST;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;

public class ECJiaPaymentAndShippingActivity extends a {
    private ArrayList<ECJia_PAYMENT> a = new ArrayList();
    private ArrayList<ECJia_PAYMENT> b = new ArrayList();
    private ArrayList<ECJia_STOREGOODSLIST> c = new ArrayList();
    private ECJiaStoreGoodsListAdapter d;
    private ECJiaNewPaymentAdapter e;
    private ECJiaNewPaymentAdapter k;
    private ECJia_PAYMENT l;
    @BindView(2131558543)
    LinearLayout llPaymentOffline;
    @BindView(2131558541)
    LinearLayout llPaymentOnline;
    @BindView(2131558544)
    ECJiaMyGridView mgvPaymentOffline;
    @BindView(2131558542)
    ECJiaMyGridView mgvPaymentOnline;
    @BindView(2131558545)
    ECJiaMyListView mlvShipping;
    @BindView(2131558540)
    ECJiaTopView topviewPaymentAndShipping;

    class ECJiaPaymentAndShippingActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaPaymentAndShippingActivity a;

        ECJiaPaymentAndShippingActivity_1(ECJiaPaymentAndShippingActivity eCJiaPaymentAndShippingActivity) {
            this.a = eCJiaPaymentAndShippingActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaPaymentAndShippingActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaPaymentAndShippingActivity a;

        ECJiaPaymentAndShippingActivity_2(ECJiaPaymentAndShippingActivity eCJiaPaymentAndShippingActivity) {
            this.a = eCJiaPaymentAndShippingActivity;
        }

        public void onClick(View view) {
            this.a.e();
        }
    }

    class ECJiaPaymentAndShippingActivity_3 implements a {
        final /* synthetic */ ECJiaPaymentAndShippingActivity a;

        ECJiaPaymentAndShippingActivity_3(ECJiaPaymentAndShippingActivity eCJiaPaymentAndShippingActivity) {
            this.a = eCJiaPaymentAndShippingActivity;
        }

        public void a(View view, int i, int i2) {
            if (!((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.a.c.get(i)).getShipping_list().get(i2)).isSelected()) {
                this.a.a(i);
                ((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.a.c.get(i)).getShipping_list().get(i2)).setSelected(true);
                this.a.d.notifyDataSetChanged();
            }
        }
    }

    class ECJiaPaymentAndShippingActivity_4 implements OnItemClickListener {
        final /* synthetic */ ECJiaPaymentAndShippingActivity a;

        ECJiaPaymentAndShippingActivity_4(ECJiaPaymentAndShippingActivity eCJiaPaymentAndShippingActivity) {
            this.a = eCJiaPaymentAndShippingActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.c();
            ((ECJia_PAYMENT) this.a.a.get(i)).setSelected(true);
            this.a.l = (ECJia_PAYMENT) this.a.a.get(i);
            this.a.e.notifyDataSetChanged();
            if (this.a.k != null) {
                this.a.k.notifyDataSetChanged();
            }
        }
    }

    class ECJiaPaymentAndShippingActivity_5 implements OnItemClickListener {
        final /* synthetic */ ECJiaPaymentAndShippingActivity a;

        ECJiaPaymentAndShippingActivity_5(ECJiaPaymentAndShippingActivity eCJiaPaymentAndShippingActivity) {
            this.a = eCJiaPaymentAndShippingActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.c();
            ((ECJia_PAYMENT) this.a.b.get(i)).setSelected(true);
            this.a.l = (ECJia_PAYMENT) this.a.b.get(i);
            this.a.k.notifyDataSetChanged();
            if (this.a.e != null) {
                this.a.e.notifyDataSetChanged();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_payment_and_shipping);
        ButterKnife.bind((Activity) this);
        PushAgent.getInstance(this).onAppStart();
        Intent intent = getIntent();
        this.a = (ArrayList) intent.getSerializableExtra("payment_list_online");
        this.b = (ArrayList) intent.getSerializableExtra("payment_list_offline");
        this.c = (ArrayList) intent.getSerializableExtra("store_goods_list");
        b();
    }

    private void b() {
        this.topviewPaymentAndShipping.setTitleText((int) R.string.choose_payment_and_shipping);
        this.topviewPaymentAndShipping.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaPaymentAndShippingActivity_1(this));
        this.topviewPaymentAndShipping.setRightType(11);
        this.topviewPaymentAndShipping.setRightText((int) R.string.save, new ECJiaPaymentAndShippingActivity_2(this));
        this.d = new ECJiaStoreGoodsListAdapter(this, this.c);
        this.mlvShipping.setAdapter(this.d);
        this.d.a(new ECJiaPaymentAndShippingActivity_3(this));
        if (this.a.size() > 0) {
            this.llPaymentOnline.setVisibility(0);
            this.e = new ECJiaNewPaymentAdapter(this, this.a);
            this.mgvPaymentOnline.setAdapter(this.e);
            this.mgvPaymentOnline.setOnItemClickListener(new ECJiaPaymentAndShippingActivity_4(this));
        } else {
            this.llPaymentOnline.setVisibility(8);
        }
        if (this.b.size() > 0) {
            this.llPaymentOffline.setVisibility(0);
            this.k = new ECJiaNewPaymentAdapter(this, this.b);
            this.mgvPaymentOffline.setAdapter(this.k);
            this.mgvPaymentOffline.setOnItemClickListener(new ECJiaPaymentAndShippingActivity_5(this));
            return;
        }
        this.llPaymentOffline.setVisibility(8);
    }

    private void c() {
        int i;
        for (i = 0; i < this.a.size(); i++) {
            ((ECJia_PAYMENT) this.a.get(i)).setSelected(false);
        }
        for (i = 0; i < this.b.size(); i++) {
            ((ECJia_PAYMENT) this.b.get(i)).setSelected(false);
        }
    }

    private void a(int i) {
        for (int i2 = 0; i2 < ((ECJia_STOREGOODSLIST) this.c.get(i)).getShipping_list().size(); i2++) {
            ((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.c.get(i)).getShipping_list().get(i2)).setSelected(false);
        }
    }

    private void e() {
        Intent intent = new Intent();
        intent.putExtra("payment", this.l);
        intent.putExtra("payment_online_list", this.a);
        intent.putExtra("payment_list_offline", this.b);
        intent.putExtra("store_goods_list", this.c);
        setResult(-1, intent);
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
