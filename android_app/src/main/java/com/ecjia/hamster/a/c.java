package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.a.ab;
import com.ecjia.hamster.activity.ECJiaGroupbuyGoodsActivity;
import com.ecjia.hamster.adapter.w;
import com.ecjia.hamster.model.s;
import com.ecmoban.android.missmall.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* compiled from: ECJiaGroupBuyView */
public class c extends d<s> {
    private LinearLayout d;
    private LinearLayout e;
    private RecyclerView f;
    private LinearLayout g;
    private w h;
    private SimpleDateFormat i;
    private a j;
    private String k;
    private Handler l = new c_2(this);
    private boolean m = false;

    /* compiled from: ECJiaGroupBuyView */
    class c_1 implements OnClickListener {
        final /* synthetic */ c a;

        c_1(c cVar) {
            this.a = cVar;
        }

        public void onClick(View view) {
            this.a.a.startActivity(new Intent(this.a.a, ECJiaGroupbuyGoodsActivity.class));
            this.a.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    /* compiled from: ECJiaGroupBuyView */
    class c_2 extends Handler {
        final /* synthetic */ c a;

        c_2(c cVar) {
            this.a = cVar;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                int i = 0;
                for (int i2 = 0; i2 < this.a.c.size(); i2++) {
                    String str;
                    String a = ab.a(this.a.k, ((s) this.a.c.get(i2)).d());
                    if (a.equals("0")) {
                        i++;
                        str = "活动已结束";
                    } else {
                        str = a;
                    }
                    ((s) this.a.c.get(i2)).a(str);
                }
                if (i == this.a.c.size()) {
                    this.a.m = true;
                }
                this.a.k = ab.d(this.a.k);
                this.a.h.notifyDataSetChanged();
            }
        }
    }

    /* compiled from: ECJiaGroupBuyView */
    private class a extends Thread {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public void run() {
            while (!this.a.m) {
                Message obtain = Message.obtain();
                obtain.what = 1;
                this.a.l.sendMessage(obtain);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("结束");
        }
    }

    public c(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.d = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.home_group_goods, null);
        this.e = (LinearLayout) this.d.findViewById(R.id.home_groupgoodlist_item);
        this.f = (RecyclerView) this.d.findViewById(R.id.home_groupgoodlist);
        this.g = (LinearLayout) this.d.findViewById(R.id.group_getmore);
        this.g.setOnClickListener(new c_1(this));
        LayoutManager linearLayoutManager = new LinearLayoutManager(this.a);
        linearLayoutManager.setOrientation(0);
        this.f.setLayoutManager(linearLayoutManager);
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.d);
    }

    public void a(ArrayList<s> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.c = arrayList;
        if (this.h == null) {
            try {
                c();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.h = new w(this.a, arrayList);
        } else {
            this.h.notifyDataSetChanged();
        }
        this.f.setAdapter(this.h);
        this.i = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.k = this.i.format(new Date());
    }

    private void c() throws InterruptedException {
        for (int i = 0; i < this.c.size(); i++) {
            if (TextUtils.isEmpty(((s) this.c.get(i)).d())) {
                ((s) this.c.get(i)).b("2000-01-01 00:00:00");
            }
        }
        this.m = false;
        if (this.j != null) {
            this.j.interrupt();
        } else {
            this.j = new a();
        }
        if (!this.j.isAlive()) {
            this.j.start();
        }
    }
}
