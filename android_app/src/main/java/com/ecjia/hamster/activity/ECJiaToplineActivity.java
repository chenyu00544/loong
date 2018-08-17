package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ak;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.ca;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;

public class ECJiaToplineActivity extends a implements OnClickListener, a, ECJiaXListView.a {
    private ECJiaXListView a;
    private ak b;
    private ca c;
    private ECJiaErrorView d;

    class ECJiaToplineActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaToplineActivity a;

        ECJiaToplineActivity_1(ECJiaToplineActivity eCJiaToplineActivity) {
            this.a = eCJiaToplineActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_topline);
        b();
    }

    private void b() {
        a();
        this.d = (ECJiaErrorView) findViewById(R.id.topline_null);
        this.a = (ECJiaXListView) findViewById(R.id.topline_list);
        this.a.setXListViewListener(this, 1);
        this.a.setPullLoadEnable(false);
        this.b = new ak(this);
        this.b.a((a) this);
        this.b.a(false);
        this.c = new ca(this, this.b.b);
        this.a.setAdapter(this.c);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.topline_topview);
        this.i.setTitleText((int) R.string.topline_tilte);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaToplineActivity_1(this));
    }

    public void onClick(View view) {
    }

    public void a(int i) {
        this.b.a(false);
    }

    public void b(int i) {
        this.b.a();
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("mobile/toutiao") && axVar.b() == 1) {
            if (this.b.b.size() > 0) {
                this.d.setVisibility(8);
            } else {
                this.d.setVisibility(0);
            }
            this.a.setRefreshTime();
            this.a.stopRefresh();
            this.a.stopLoadMore();
            if (this.b.a.a() == 1) {
                this.a.setPullLoadEnable(true);
            } else {
                this.a.setPullLoadEnable(false);
            }
            this.c.notifyDataSetChanged();
        }
    }
}
