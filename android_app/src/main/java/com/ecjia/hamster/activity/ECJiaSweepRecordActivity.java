package com.ecjia.hamster.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.ECJiaXListView.a;
import com.ecjia.component.view.c;
import com.ecjia.hamster.adapter.bv;
import com.ecjia.hamster.adapter.bv.b;
import com.ecjia.hamster.adapter.bw;
import com.ecjia.hamster.model.ba;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

public class ECJiaSweepRecordActivity extends a implements a {
    public Handler a;
    c b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private ECJiaXListView k;
    private FrameLayout l;
    private bw m;
    private bv n;
    private ArrayList<ba> o = new ArrayList();
    private ArrayList<ba> p = new ArrayList();
    private int q = 0;
    private int r = 8;

    class ECJiaSweepRecordActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaSweepRecordActivity a;

        ECJiaSweepRecordActivity_1(ECJiaSweepRecordActivity eCJiaSweepRecordActivity) {
            this.a = eCJiaSweepRecordActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaSweepRecordActivity_2 extends Handler {
        final /* synthetic */ ECJiaSweepRecordActivity a;

        class ECJiaSweepRecordActivity_2_1 implements b {
            final /* synthetic */ ECJiaSweepRecordActivity_2 a;

            ECJiaSweepRecordActivity_2_1(ECJiaSweepRecordActivity_2 eCJiaSweepRecordActivity_2) {
                this.a = eCJiaSweepRecordActivity_2;
            }

            public void a() {
                if (this.a.a.o.size() == 0) {
                    this.a.a.k.setVisibility(8);
                    this.a.a.l.setVisibility(0);
                    this.a.a.e.setVisibility(8);
                }
            }
        }

        ECJiaSweepRecordActivity_2(ECJiaSweepRecordActivity eCJiaSweepRecordActivity) {
            this.a = eCJiaSweepRecordActivity;
        }

        public void handleMessage(Message message) {
            if (message.arg1 == 1) {
                this.a.k.stopRefresh();
                this.a.k.stopLoadMore();
                this.a.k.setRefreshTime();
                if (this.a.q == 0) {
                    this.a.o.clear();
                }
                int b = this.a.q * this.a.r;
                int b2 = (this.a.q * this.a.r) + this.a.r;
                while (b < b2 && this.a.p.size() > b) {
                    this.a.o.add(this.a.p.get(b));
                    b++;
                }
                if (this.a.p.size() > this.a.o.size()) {
                    this.a.k.setPullLoadEnable(true);
                } else {
                    this.a.k.setPullLoadEnable(false);
                }
                if (this.a.n == null) {
                    this.a.n = new bv(this.a, this.a.o, (int) this.a.g.getDimension(R.dimen.sweep_right_width));
                    this.a.n.a(new ECJiaSweepRecordActivity_2_1(this));
                    this.a.k.setAdapter(this.a.n);
                    return;
                }
                this.a.n.notifyDataSetChanged();
            }
        }
    }

    class ECJiaSweepRecordActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaSweepRecordActivity a;

        ECJiaSweepRecordActivity_3(ECJiaSweepRecordActivity eCJiaSweepRecordActivity) {
            this.a = eCJiaSweepRecordActivity;
        }

        public void onClick(View view) {
            this.a.f();
        }
    }

    class ECJiaSweepRecordActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaSweepRecordActivity a;

        ECJiaSweepRecordActivity_4(ECJiaSweepRecordActivity eCJiaSweepRecordActivity) {
            this.a = eCJiaSweepRecordActivity;
        }

        public void onClick(View view) {
            this.a.o.clear();
            this.a.p.clear();
            this.a.m.b();
            this.a.n.notifyDataSetChanged();
            this.a.k.setVisibility(8);
            this.a.l.setVisibility(0);
            this.a.e.setVisibility(8);
            this.a.b.b();
        }
    }

    class ECJiaSweepRecordActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaSweepRecordActivity a;

        ECJiaSweepRecordActivity_5(ECJiaSweepRecordActivity eCJiaSweepRecordActivity) {
            this.a = eCJiaSweepRecordActivity;
        }

        public void onClick(View view) {
            this.a.b.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sweep_record);
        b();
    }

    private void b() {
        this.c = (ImageView) findViewById(R.id.top_view_back);
        this.c.setOnClickListener(new ECJiaSweepRecordActivity_1(this));
        this.a = new ECJiaSweepRecordActivity_2(this);
        this.d = (TextView) findViewById(R.id.top_view_text);
        this.d.setText(this.g.getString(R.string.sweep_history));
        this.e = (TextView) findViewById(R.id.top_right_save);
        this.e.setText(this.g.getString(R.string.top_clean));
        this.e.setVisibility(0);
        this.e.setOnClickListener(new ECJiaSweepRecordActivity_3(this));
        this.k = (ECJiaXListView) findViewById(R.id.sweep_record_list);
        this.k.setXListViewListener(this, 1);
        this.k.setRefreshTime();
        this.k.setPullLoadEnable(false);
        this.k.setPullRefreshEnable(true);
        this.l = (FrameLayout) findViewById(R.id.null_pager);
        this.m = bw.a((Context) this);
        c();
        c(0);
    }

    private void c() {
        Cursor a = this.m.a();
        while (a.moveToNext()) {
            ba baVar = new ba();
            baVar.a(a.getString(1));
            baVar.b(a.getString(2));
            baVar.c(a.getString(3));
            this.p.add(baVar);
        }
        this.m.a.close();
        if (this.p.size() > 0) {
            this.k.setVisibility(0);
            this.l.setVisibility(8);
            if (this.p.size() > 0) {
                this.k.setPullLoadEnable(true);
                this.k.setRefreshTime();
                return;
            }
            this.k.setPullLoadEnable(false);
            return;
        }
        this.k.setVisibility(8);
        this.l.setVisibility(0);
        this.e.setVisibility(8);
    }

    private void c(int i) {
        int i2 = this.r * i;
        int i3 = (this.r * i) + this.r;
        while (i2 < i3 && this.p.size() > i2) {
            this.o.add(this.p.get(i2));
            i2++;
        }
        Message message = new Message();
        message.arg1 = 1;
        this.a.sendMessage(message);
    }

    private void e() {
        Message message = new Message();
        message.arg1 = 1;
        this.a.sendMessageDelayed(message, 1000);
    }

    private void f() {
        this.b = new c(this, this.g.getString(R.string.qr_clear), this.g.getString(R.string.sure_clear));
        this.b.a();
        this.b.b.setOnClickListener(new ECJiaSweepRecordActivity_4(this));
        this.b.d.setOnClickListener(new ECJiaSweepRecordActivity_5(this));
    }

    public void a(int i) {
        this.q = 0;
        e();
    }

    public void b(int i) {
        this.q++;
        e();
    }

    protected void onPause() {
        this.m.a.close();
        super.onPause();
    }
}
