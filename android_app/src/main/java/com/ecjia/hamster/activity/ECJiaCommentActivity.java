package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.i;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.f;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;

public class ECJiaCommentActivity extends a implements a, ECJiaXListView.a {
    ArrayList<com.ecjia.hamster.activity.goodsdetail.a.a> a = new ArrayList();
    private ECJiaXListView b;
    private f c;
    private String d;
    private i e;
    private FrameLayout k;

    class ECJiaCommentActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaCommentActivity a;

        ECJiaCommentActivity_1(ECJiaCommentActivity eCJiaCommentActivity) {
            this.a = eCJiaCommentActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.comment);
        a();
        PushAgent.getInstance(this).onAppStart();
        this.d = getIntent().getStringExtra("goods_id");
        this.k = (FrameLayout) findViewById(R.id.no_comment);
        this.b = (ECJiaXListView) findViewById(R.id.comment_list);
        this.b.setPullLoadEnable(true);
        this.b.setRefreshTime();
        this.b.setXListViewListener(this, 1);
        this.e = new i(this);
        this.e.a((a) this);
        this.e.a(this.d, "positive", true);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.comment_topview);
        this.i.setTitleText((int) R.string.gooddetail_commit);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaCommentActivity_1(this));
    }

    public void a(int i) {
        this.a.clear();
        this.e.a(this.d, "positive", true);
    }

    public void b(int i) {
        this.e.b(this.d, "positive");
    }

    public void b() {
        int i = 0;
        if (this.e.j.size() > 0) {
            this.b.setVisibility(0);
            if (this.c == null) {
                while (i < this.e.j.size()) {
                    this.a.add(this.e.j.get(i));
                    i++;
                }
                this.c = new f(this, this.a);
                this.b.setAdapter(this.c);
                return;
            }
            while (i < this.e.j.size()) {
                this.a.add(this.e.j.get(i));
                i++;
            }
            this.c.notifyDataSetChanged();
            return;
        }
        this.b.setVisibility(8);
        this.k.setVisibility(0);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("comments") && axVar.b() == 1) {
            this.b.setRefreshTime();
            this.b.stopRefresh();
            this.b.stopLoadMore();
            if (this.e.a.a() == 0) {
                this.b.setPullLoadEnable(false);
            } else {
                this.b.setPullLoadEnable(true);
            }
            b();
        }
    }
}
