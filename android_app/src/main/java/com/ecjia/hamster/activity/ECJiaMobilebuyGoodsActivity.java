package com.ecjia.hamster.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.v;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.ai;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaMobilebuyGoodsActivity extends a implements a, ECJiaXListView.a {
    private ImageView a;
    private TextView b;
    private ECJiaXListView c;
    private ai d;
    private v e;
    private ECJiaErrorView k;

    class ECJiaMobilebuyGoodsActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaMobilebuyGoodsActivity a;

        ECJiaMobilebuyGoodsActivity_1(ECJiaMobilebuyGoodsActivity eCJiaMobilebuyGoodsActivity) {
            this.a = eCJiaMobilebuyGoodsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mobilegoods);
        b();
    }

    private void b() {
        PushAgent.getInstance(this).onAppStart();
        this.e = new v(this);
        this.a = (ImageView) findViewById(R.id.top_view_back);
        this.b = (TextView) findViewById(R.id.top_view_text);
        this.c = (ECJiaXListView) findViewById(R.id.mobile_listview);
        this.k = (ECJiaErrorView) findViewById(R.id.mobile_null_pager);
        this.b.setText(this.g.getString(R.string.mobile_buy));
        this.a.setOnClickListener(new ECJiaMobilebuyGoodsActivity_1(this));
        this.c.setPullLoadEnable(false);
        this.c.setPullRefreshEnable(true);
        this.c.setXListViewListener(this, 1);
        this.e.a((a) this);
        this.e.a(true);
        this.d = new ai(this, this.e.b);
        this.c.setAdapter(this.d);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    private void c() {
        if (this.e.b.size() > 0) {
            this.c.setVisibility(0);
            this.k.setVisibility(8);
            this.d.a(this.e.b);
            this.d.notifyDataSetChanged();
            return;
        }
        this.c.setVisibility(8);
        this.k.setVisibility(0);
    }

    public void a(int i) {
        this.e.a(false);
    }

    public void b(int i) {
        this.e.a();
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("goods/mobilebuygoods")) {
            return;
        }
        if (axVar.b() == 1) {
            this.c.setRefreshTime();
            this.c.stopRefresh();
            this.c.stopLoadMore();
            if (this.e.a.a() == 1) {
                this.c.setPullLoadEnable(true);
            } else {
                this.c.setPullLoadEnable(false);
            }
            c();
            return;
        }
        this.c.setVisibility(8);
        this.k.setVisibility(0);
        new k((Context) this, getResources().getString(R.string.payment_network_problem)).a();
    }
}
