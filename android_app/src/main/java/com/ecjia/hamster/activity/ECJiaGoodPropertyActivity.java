package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.hamster.adapter.n;
import com.ecjia.hamster.adapter.o;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaGoodPropertyActivity extends a {
    private ListView a;
    private o b;
    private TextView c;
    private ImageView d;
    private int e;
    private FrameLayout k;

    class ECJiaGoodPropertyActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaGoodPropertyActivity a;

        ECJiaGoodPropertyActivity_1(ECJiaGoodPropertyActivity eCJiaGoodPropertyActivity) {
            this.a = eCJiaGoodPropertyActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.good_property_activity);
        PushAgent.getInstance(this).onAppStart();
        this.e = getIntent().getIntExtra("info", 0);
        q.a("获取到的值=====" + this.e);
        this.c = (TextView) findViewById(R.id.top_view_text);
        this.c.setText(n.a().a.p());
        this.d = (ImageView) findViewById(R.id.top_view_back);
        this.d.setOnClickListener(new ECJiaGoodPropertyActivity_1(this));
        this.a = (ListView) findViewById(R.id.property_list);
        this.k = (FrameLayout) findViewById(R.id.no_info);
        if (this.e == 0) {
            this.k.setVisibility(0);
            this.a.setVisibility(8);
        } else {
            this.k.setVisibility(8);
            this.a.setVisibility(0);
        }
        if (n.a().a.q().size() > 0) {
            this.a.setVisibility(0);
            this.b = new o(this, n.a().a.q());
            this.a.setAdapter(this.b);
            return;
        }
        this.a.setVisibility(8);
    }
}
