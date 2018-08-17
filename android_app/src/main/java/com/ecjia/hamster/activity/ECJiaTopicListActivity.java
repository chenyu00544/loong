package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.aj;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.bz;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import java.util.Locale;

public class ECJiaTopicListActivity extends a implements a, ECJiaXListView.a {
    private ECJiaXListView a;
    private FrameLayout b;
    private aj c;
    private bz d;

    class ECJiaTopicListActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaTopicListActivity a;

        ECJiaTopicListActivity_1(ECJiaTopicListActivity eCJiaTopicListActivity) {
            this.a = eCJiaTopicListActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_topic_list);
        b();
    }

    private void b() {
        a();
        this.c = new aj(this);
        this.c.a();
        this.c.a((a) this);
        this.d = new bz(this, this.c.b);
        this.a = (ECJiaXListView) findViewById(R.id.topic_list);
        this.a.setPullLoadEnable(false);
        this.a.setXListViewListener(this, 0);
        this.a.setAdapter(this.d);
        this.b = (FrameLayout) findViewById(R.id.null_pager);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.topiclist_topview);
        if (getResources().getConfiguration().locale.equals(Locale.CHINA)) {
            this.i.setTitleImage((int) R.drawable.theme_icon_chinese_white);
        } else {
            this.i.setTitleImage((int) R.drawable.theme_icon_english_white);
        }
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaTopicListActivity_1(this));
    }

    public void a(int i) {
        this.c.a();
    }

    public void b(int i) {
        this.c.b();
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("topic/list")) {
            return;
        }
        if (axVar.b() == 1) {
            this.d.notifyDataSetChanged();
            this.a.setRefreshTime();
            this.a.stopLoadMore();
            this.a.stopRefresh();
            if (this.c.a.a() == 0) {
                this.a.setPullLoadEnable(false);
            } else {
                this.a.setPullLoadEnable(true);
            }
            if (this.c.b.size() > 0) {
                this.a.setVisibility(0);
                this.b.setVisibility(8);
                return;
            }
            this.a.setVisibility(8);
            this.b.setVisibility(0);
            return;
        }
        this.a.setVisibility(8);
        this.b.setVisibility(0);
    }
}
