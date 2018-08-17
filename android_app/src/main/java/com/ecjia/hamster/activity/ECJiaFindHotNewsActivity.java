package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.l;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaXListViewNews;
import com.ecjia.hamster.adapter.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;

public class ECJiaFindHotNewsActivity extends a implements a, ECJiaXListViewNews.a {
    private ImageView a;
    private TextView b;
    private ECJiaXListViewNews c;
    private k d;
    private l e;
    private int k = 0;
    private int l = 0;
    private ECJiaErrorView m;

    class ECJiaFindHotNewsActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaFindHotNewsActivity a;

        ECJiaFindHotNewsActivity_1(ECJiaFindHotNewsActivity eCJiaFindHotNewsActivity) {
            this.a = eCJiaFindHotNewsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_find_hot_news);
        b();
    }

    private void b() {
        this.a = (ImageView) findViewById(R.id.top_view_back);
        this.b = (TextView) findViewById(R.id.top_view_text);
        this.b.setText(getResources().getString(R.string.find_today_hot));
        this.a.setOnClickListener(new ECJiaFindHotNewsActivity_1(this));
        this.m = (ECJiaErrorView) findViewById(R.id.null_pager);
        this.c = (ECJiaXListViewNews) findViewById(R.id.hot_new_listview);
        this.c.setXListViewListener(this, 1);
        this.c.setPullLoadEnable(true);
        this.c.setPullRefreshEnable(false);
        this.e = new l(this);
        this.e.a((a) this);
        this.d = new k(this, this.e.b);
        this.c.setAdapter(this.d);
        this.e.b();
    }

    private void c() {
        this.d.notifyDataSetChanged();
    }

    public void a(int i) {
    }

    public void b(int i) {
        this.e.c();
    }

    public void a(String str, String str2, ax axVar) {
        if ("home/news".equals(str) && axVar.b() == 1) {
            this.c.stopRefresh();
            this.c.setRefreshTime();
            if (this.e.e.a() == 0) {
                this.c.setPullLoadEnable(false);
            } else {
                this.c.setPullLoadEnable(true);
            }
            if (this.e.b.size() > 0) {
                this.m.setVisibility(8);
                this.c.setVisibility(0);
                c();
                this.k = this.e.b.size();
                this.k -= this.l;
                this.c.setSelection(this.k);
                this.l = this.e.b.size();
                return;
            }
            this.m.setVisibility(0);
            this.c.setVisibility(8);
        }
    }
}
