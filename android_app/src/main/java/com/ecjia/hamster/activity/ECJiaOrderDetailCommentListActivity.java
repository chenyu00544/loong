package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.i;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.ap;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;

public class ECJiaOrderDetailCommentListActivity extends a implements a, ECJiaXListView.a {
    private TextView a;
    private ImageView b;
    private ECJiaXListView c;
    private String d;
    private i e;
    private ap k;
    private View l;

    class ECJiaOrderDetailCommentListActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaOrderDetailCommentListActivity a;

        ECJiaOrderDetailCommentListActivity_1(ECJiaOrderDetailCommentListActivity eCJiaOrderDetailCommentListActivity) {
            this.a = eCJiaOrderDetailCommentListActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_orderdetail_comment);
        b();
        this.c = (ECJiaXListView) findViewById(R.id.orderdetail_comment_list);
        this.c.setPullRefreshEnable(false);
        this.c.setRefreshTime();
        this.c.setPullLoadEnable(false);
        this.c.setXListViewListener(this, 0);
        this.d = getIntent().getStringExtra("order_id");
        this.e = new i(this);
        this.e.a((a) this);
        this.k = new ap(this, this.e.f);
        this.c.setAdapter(this.k);
    }

    public void a(int i) {
    }

    public void b(int i) {
    }

    public void b() {
        this.l = findViewById(R.id.null_pager);
        this.l.setVisibility(8);
        this.b = (ImageView) findViewById(R.id.top_view_back);
        this.a = (TextView) findViewById(R.id.top_view_text);
        this.a.setText(R.string.create_comment);
        this.b.setOnClickListener(new ECJiaOrderDetailCommentListActivity_1(this));
    }

    protected void onResume() {
        super.onResume();
        this.e.a(this.d);
    }

    public void a(String str, String str2, ax axVar) {
        this.c.stopRefresh();
        this.c.stopLoadMore();
        if (str.equals("orders/comment") && axVar.b() == 1) {
            if (this.e.f.size() == 0) {
                this.l.setVisibility(0);
            } else {
                this.l.setVisibility(8);
            }
            this.k.notifyDataSetChanged();
        }
    }
}
