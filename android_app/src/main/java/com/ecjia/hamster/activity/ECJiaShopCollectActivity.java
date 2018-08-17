package com.ecjia.hamster.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.af;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bi;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaShopCollectActivity extends a implements a, ECJiaXListView.a {
    private ImageView a;
    private TextView b;
    private ECJiaXListView c;
    private af d;
    private boolean e = false;
    private bi k;
    private ProgressDialog l;
    private View m;
    private Handler n;

    class ECJiaShopCollectActivity_1 extends Handler {
        final /* synthetic */ ECJiaShopCollectActivity a;

        ECJiaShopCollectActivity_1(ECJiaShopCollectActivity eCJiaShopCollectActivity) {
            this.a = eCJiaShopCollectActivity;
        }

        public void handleMessage(Message message) {
            CharSequence string = this.a.g.getString(R.string.collect_done);
            CharSequence string2 = this.a.g.getString(R.string.collect_delete);
            if (message.arg1 == 0) {
                this.a.b.setText(string);
            } else if (message.arg1 == 1) {
                this.a.b.setText(string2);
            } else if (message.arg1 == 100) {
                this.a.k.notifyDataSetChanged();
            }
        }
    }

    class ECJiaShopCollectActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaShopCollectActivity a;

        ECJiaShopCollectActivity_2(ECJiaShopCollectActivity eCJiaShopCollectActivity) {
            this.a = eCJiaShopCollectActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaShopCollectActivity_3 implements OnItemClickListener {
        final /* synthetic */ ECJiaShopCollectActivity a;

        ECJiaShopCollectActivity_3(ECJiaShopCollectActivity eCJiaShopCollectActivity) {
            this.a = eCJiaShopCollectActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!this.a.e) {
                Intent intent = new Intent(this.a, ECJiaShopListActivity.class);
                intent.putExtra("merchant_id", ((ao) this.a.k.b.get(i - 1)).e());
                this.a.startActivity(intent);
            }
        }
    }

    class ECJiaShopCollectActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaShopCollectActivity a;

        ECJiaShopCollectActivity_4(ECJiaShopCollectActivity eCJiaShopCollectActivity) {
            this.a = eCJiaShopCollectActivity;
        }

        public void onClick(View view) {
            CharSequence string = this.a.g.getString(R.string.collect_done);
            String string2 = this.a.g.getString(R.string.collect_cancel_collect);
            String string3 = this.a.g.getString(R.string.collect_cancel_sure);
            final CharSequence string4 = this.a.g.getString(R.string.collect_compile);
            if (this.a.e) {
                this.a.c.setPullRefreshEnable(true);
                this.a.e = false;
                if (this.a.f()) {
                    for (int i = 0; i < this.a.d.b.size(); i++) {
                        q.a(i + "需要删除====" + ((ao) this.a.d.b.get(i)).d());
                    }
                    final c cVar = new c(this.a, string2, string3);
                    cVar.a();
                    cVar.b.setOnClickListener(new OnClickListener(this) {
                        Resources a = this.e.a.getBaseContext().getResources();
                        String b = this.a.getString(R.string.collect_delete_success);
                        final /* synthetic */ ECJiaShopCollectActivity_4 e;

                        public void onClick(View view) {
                            cVar.b();
                            this.e.a.b();
                            k kVar = new k(this.e.a, this.b);
                            kVar.a(17, 0, 0);
                            kVar.a();
                            this.e.a.k.a = 1;
                            this.e.a.k.notifyDataSetChanged();
                            this.e.a.b.setText(string4);
                        }
                    });
                    cVar.d.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaShopCollectActivity_4 c;

                        public void onClick(View view) {
                            this.c.a.g();
                            this.c.a.k.a = 1;
                            this.c.a.k.notifyDataSetChanged();
                            this.c.a.b.setText(string4);
                            cVar.b();
                        }
                    });
                    return;
                }
                this.a.k.a = 1;
                this.a.k.notifyDataSetChanged();
                this.a.b.setText(string4);
                return;
            }
            this.a.k.a = 2;
            this.a.k.notifyDataSetChanged();
            this.a.c.setPullRefreshEnable(false);
            this.a.e = true;
            this.a.b.setText(string);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_shop_collect);
        PushAgent.getInstance(this).onAppStart();
        if (this.d == null) {
            this.d = new af(this);
        }
        this.d.a((a) this);
        e();
        this.d.b();
        this.n = new ECJiaShopCollectActivity_1(this);
    }

    private void e() {
        this.a = (ImageView) findViewById(R.id.collect_back);
        this.b = (TextView) findViewById(R.id.collect_edit);
        this.b.setEnabled(false);
        this.m = findViewById(R.id.null_pager);
        this.a.setOnClickListener(new ECJiaShopCollectActivity_2(this));
        this.c = (ECJiaXListView) findViewById(R.id.collect_list);
        if (this.k == null) {
            this.k = new bi(this, this.d.b, 1);
        }
        this.c.setXListViewListener(this, 1);
        this.c.setAdapter(this.k);
        this.c.setOnItemClickListener(new ECJiaShopCollectActivity_3(this));
        this.b.setOnClickListener(new ECJiaShopCollectActivity_4(this));
        this.c.setPullRefreshEnable(true);
        this.c.setPullLoadEnable(false);
    }

    private boolean f() {
        for (int i = 0; i < this.d.b.size(); i++) {
            if (((ao) this.d.b.get(i)).d()) {
                return true;
            }
        }
        return false;
    }

    private void g() {
        for (int i = 0; i < this.d.b.size(); i++) {
            if (((ao) this.d.b.get(i)).d()) {
                ((ao) this.d.b.get(i)).b(false);
            }
        }
    }

    void b() {
        if (this.l == null) {
            this.l = new ProgressDialog(this);
        }
        this.l.show();
        int i = 0;
        while (i < this.d.b.size()) {
            if (((ao) this.d.b.get(i)).d()) {
                this.d.d(((ao) this.d.b.get(i)).e());
                this.d.b.remove(i);
                i--;
            }
            i++;
        }
        this.l.dismiss();
    }

    public void c() {
        if (this.d.b.size() == 0) {
            this.b.setEnabled(false);
            this.b.setVisibility(8);
            if (this.k != null) {
                this.k.notifyDataSetChanged();
            }
            this.m.setVisibility(0);
            this.c.setVisibility(8);
            return;
        }
        this.c.setVisibility(0);
        this.m.setVisibility(8);
        this.b.setEnabled(true);
        this.b.setVisibility(0);
        if (this.k != null) {
            this.k.notifyDataSetChanged();
        }
        this.k.c = this.n;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        return true;
    }

    public void a(int i) {
        this.d.b();
    }

    public void b(int i) {
        this.d.c();
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("seller/collect/list")) {
            if (axVar.b() == 1) {
                this.c.setRefreshTime();
                this.c.stopRefresh();
                this.c.stopLoadMore();
                if (this.d.e.a() == 0) {
                    this.c.setPullLoadEnable(false);
                } else {
                    this.c.setPullLoadEnable(true);
                }
                c();
            }
        } else if (str.equals("seller/collect/delete") && axVar.b() == 1 && this.d.b.size() == 0) {
            this.b.setEnabled(false);
            this.m.setVisibility(0);
            this.c.setVisibility(8);
        }
    }
}
