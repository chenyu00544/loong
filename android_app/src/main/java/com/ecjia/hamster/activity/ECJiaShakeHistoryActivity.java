package com.ecjia.hamster.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.ecjia.a.c.a;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.adapter.bh;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.List;

public class ECJiaShakeHistoryActivity extends a implements OnClickListener {

    class ECJiaShakeHistoryActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaShakeHistoryActivity a;

        ECJiaShakeHistoryActivity_1(ECJiaShakeHistoryActivity eCJiaShakeHistoryActivity) {
            this.a = eCJiaShakeHistoryActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_shake_history);
        b();
    }

    void b() {
        a();
        a a = a.a((Context) this);
        ListView listView = (ListView) findViewById(R.id.shake_history_list);
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_shakehistory_head, null);
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.layout_shakehistory_head, null);
        listView.addHeaderView(inflate);
        listView.addFooterView(inflate2);
        List a2 = a.a(Integer.parseInt(this.h.e().m()));
        inflate = findViewById(R.id.no_shake);
        if (a2.size() > 0) {
            listView.setAdapter(new bh(this, a2));
            inflate.setVisibility(8);
            return;
        }
        inflate.setVisibility(0);
        listView.setVisibility(8);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.shake_history_topview);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaShakeHistoryActivity_1(this));
        this.i.setTitleText((int) R.string.title_shake_history);
        this.i.setRightType(13);
    }

    public void finish() {
        super.finish();
    }

    public void onClick(View view) {
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
