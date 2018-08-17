package com.ecmoban.android.missmall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.consts.b;
import com.ecjia.hamster.activity.ECJiaMainActivity;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.adapter.af;
import com.ecjia.hamster.adapter.aj;
import com.ecjia.hamster.model.z;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ECJiaPushActivity extends a implements ECJiaXListView.a, af.a {
    public af a;
    public Handler b;
    private TextView c;
    private ImageView d;
    private Resources e;
    private int k = 0;
    private ArrayList<z> l;
    private ArrayList<z> m;
    private ECJiaXListView n;
    private Boolean o;
    private String[] p;
    private FrameLayout q;
    private int r = 0;

    class ECJiaPushActivity_1 extends Handler {
        final /* synthetic */ ECJiaPushActivity a;

        ECJiaPushActivity_1(ECJiaPushActivity eCJiaPushActivity) {
            this.a = eCJiaPushActivity;
        }

        public void handleMessage(Message message) {
            if (message.arg1 == 1) {
                this.a.n.stopRefresh();
                this.a.n.setRefreshTime();
                if (this.a.a == null) {
                    this.a.a = new af(this.a, this.a.l);
                    this.a.a.a(this.a);
                    this.a.n.setAdapter(this.a.a);
                    this.a.r = this.a.a.a.size();
                    this.a.n.setSelection(this.a.a.a.size() - 1);
                    return;
                }
                this.a.a.a = this.a.l;
                this.a.a.notifyDataSetChanged();
                this.a.n.setSelection(this.a.a.a.size() - this.a.r);
                this.a.r = this.a.a.a.size();
            }
        }
    }

    class ECJiaPushActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaPushActivity a;

        ECJiaPushActivity_2(ECJiaPushActivity eCJiaPushActivity) {
            this.a = eCJiaPushActivity;
        }

        public void onClick(View view) {
            if (this.a.o.booleanValue()) {
                this.a.startActivity(new Intent(this.a, ECJiaMainActivity.class));
                this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c.a().a((Object) this);
        setContentView(R.layout.activity_message);
        PushAgent.getInstance(this).onAppStart();
        if (this.m == null) {
            this.m = new ArrayList();
        }
        this.o = Boolean.valueOf(getIntent().getBooleanExtra("refresh", false));
        this.b = new ECJiaPushActivity_1(this);
        c();
        b();
        c(0);
    }

    @SuppressLint({"NewApi"})
    private void b() {
        if (this.m != null) {
            this.m.clear();
        }
        if (this.l == null) {
            this.l = new ArrayList();
        }
        Cursor a = aj.a((Context) this).a();
        while (a.moveToNext()) {
            z zVar = new z();
            String string = a.getString(1);
            String string2 = a.getString(2);
            String string3 = a.getString(3);
            String string4 = a.getString(4);
            String string5 = a.getString(5);
            a.getString(6);
            a.getString(7);
            String string6 = a.getString(8);
            String string7 = a.getString(9);
            String string8 = a.getString(10);
            String string9 = a.getString(11);
            String string10 = a.getString(12);
            String string11 = a.getString(13);
            String string12 = a.getString(14);
            String string13 = a.getString(15);
            zVar.a(a.getInt(16));
            zVar.p(string13);
            zVar.h(string6);
            zVar.m(string);
            zVar.n(string2);
            zVar.l(string3);
            zVar.i(string5);
            zVar.f(string7);
            zVar.b(string8);
            zVar.c(string9);
            zVar.d(string10);
            zVar.a(string11);
            zVar.e(string12);
            this.p = string4.split(" ");
            zVar.o(this.p[1]);
            zVar.g(this.p[0]);
            this.m.add(zVar);
        }
        aj.a((Context) this).a.close();
        if (this.m.size() > 0) {
            this.n.setVisibility(0);
            this.q.setVisibility(8);
            if (this.m.size() > 8) {
                this.n.setPullRefreshEnable(true);
                this.n.setRefreshTime();
            } else {
                this.n.setPullRefreshEnable(false);
            }
        } else {
            this.n.setVisibility(8);
            this.q.setVisibility(0);
        }
        this.n.setPullLoadEnable(false);
    }

    private void c() {
        this.e = b.a((Context) this);
        this.d = (ImageView) findViewById(R.id.top_view_back);
        this.d.setOnClickListener(new ECJiaPushActivity_2(this));
        this.q = (FrameLayout) findViewById(R.id.push_null_pager);
        this.c = (TextView) findViewById(R.id.top_view_text);
        this.c.setText(this.e.getString(R.string.push_title));
        this.n = (ECJiaXListView) findViewById(R.id.message_listview);
        this.n.setXListViewListener(this, 1);
        this.n.setRefreshTime();
    }

    private void c(int i) {
        int i2 = i * 8;
        int i3 = (i * 8) + 8;
        while (i2 < i3 && this.m.size() > i2) {
            this.l.add(this.m.get(i2));
            i2++;
        }
        Message message = new Message();
        message.arg1 = 1;
        this.b.sendMessageDelayed(message, 1000);
    }

    public void a(int i) {
        this.k++;
        c(this.k);
    }

    public void b(int i) {
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
        this.l.clear();
        this.m.clear();
    }

    protected void onResume() {
        super.onResume();
        if (this.a != null) {
            this.a.notifyDataSetChanged();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.o = Boolean.valueOf(intent.getBooleanExtra("refresh", false));
        if (this.o.booleanValue()) {
            this.l.clear();
            this.m.clear();
            b();
            c(0);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.a != null) {
                this.a.notifyDataSetChanged();
            }
            if (this.o.booleanValue()) {
                startActivity(new Intent(this, ECJiaMainActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        if (bVar.c().equals("UPDATE_MESSAGE")) {
            q.a("运行");
            try {
                z e = bVar.e();
                String[] split = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()).toString().split(" ");
                e.o(split[1]);
                e.g(split[0]);
                this.l.add(0, e);
                this.a.a = this.l;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if ("refresh_push_adpter".equals(bVar.c())) {
            this.a.notifyDataSetChanged();
            this.n.setSelection(this.a.a.size() - 1);
        }
    }

    public void a(String str) {
        a(str, 0);
    }

    void a(String str, int i) {
        aj.a((Context) this).a(str, i);
    }
}
