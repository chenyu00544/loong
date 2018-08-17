package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ab;
import com.ecjia.hamster.adapter.ba;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaRedpapperListActivity extends a implements a {
    ba a;
    FrameLayout b;
    private TextView c;
    private ImageView d;
    private ListView e;
    private ab k;

    class ECJiaRedpapperListActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaRedpapperListActivity a;

        ECJiaRedpapperListActivity_1(ECJiaRedpapperListActivity eCJiaRedpapperListActivity) {
            this.a = eCJiaRedpapperListActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_redpapper_list);
        PushAgent.getInstance(this).onAppStart();
        this.k = new ab(this);
        this.k.a((a) this);
        b();
        this.k.d("");
    }

    private void b() {
        this.d = (ImageView) findViewById(R.id.top_view_back);
        this.d.setOnClickListener(new ECJiaRedpapperListActivity_1(this));
        CharSequence string = getBaseContext().getResources().getString(R.string.redpaper_detail);
        this.c = (TextView) findViewById(R.id.top_view_text);
        this.c.setText(string);
        this.b = (FrameLayout) findViewById(R.id.null_pager);
        this.e = (ListView) findViewById(R.id.redpapper_list);
        this.a = new ba(this.k.b, this);
        this.e.setAdapter(this.a);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/bonus") && axVar.b() == 1) {
            if (this.k.b.size() > 0) {
                this.b.setVisibility(8);
                this.e.setVisibility(0);
            } else {
                this.b.setVisibility(0);
                this.e.setVisibility(8);
            }
            this.a.notifyDataSetChanged();
        }
    }
}
