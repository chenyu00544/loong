package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.e;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ae;
import com.ecjia.component.a.af;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bn;
import com.ecjia.hamster.adapter.s;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

@SuppressLint({"CommitPrefEdits"})
public class ECJiaSearchAllActivity extends a implements OnClickListener, a, ECJiaXListView.a {
    private TextView a;
    private TextView b;
    private LinearLayout c;
    private View d;
    private FrameLayout e;
    private ECJiaXListView k;
    private af l;
    private s m;
    private ae n;
    private bn o;
    private String p;
    private Handler q;
    private int r = -1;

    class ECJiaSearchAllActivity_1 extends Handler {
        final /* synthetic */ ECJiaSearchAllActivity a;

        ECJiaSearchAllActivity_1(ECJiaSearchAllActivity eCJiaSearchAllActivity) {
            this.a = eCJiaSearchAllActivity;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.a.r = message.arg1;
                this.a.l.c(((ao) this.a.o.a().get(message.arg1)).e());
            }
            if (message.what == 2) {
                this.a.r = message.arg1;
                this.a.l.d(((ao) this.a.o.a().get(message.arg1)).e());
            }
        }
    }

    class ECJiaSearchAllActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaSearchAllActivity a;

        ECJiaSearchAllActivity_2(ECJiaSearchAllActivity eCJiaSearchAllActivity) {
            this.a = eCJiaSearchAllActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaSearchAllActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaSearchAllActivity a;

        ECJiaSearchAllActivity_3(ECJiaSearchAllActivity eCJiaSearchAllActivity) {
            this.a = eCJiaSearchAllActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_all);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        this.q = new ECJiaSearchAllActivity_1(this);
        this.p = getIntent().getStringExtra("keyword");
        if (this.n == null) {
            this.n = new ae(this);
        }
        this.n.a((a) this);
        if (this.l == null) {
            this.l = new af(this);
        }
        this.l.a((a) this);
        e();
        if (TextUtils.isEmpty(this.p)) {
            b();
            return;
        }
        this.a.setText(this.p);
        this.n.a(this.p);
    }

    private void e() {
        this.k = (ECJiaXListView) findViewById(R.id.good_list);
        this.k.setPullLoadEnable(false);
        this.k.setRefreshTime();
        this.k.setXListViewListener(this, 1);
        this.d = findViewById(R.id.null_pager);
        this.e = (FrameLayout) findViewById(R.id.fl_search_top);
        this.c = (LinearLayout) findViewById(R.id.ll_search);
        this.a = (TextView) findViewById(R.id.et_search_input);
        this.b = (TextView) findViewById(R.id.tv_search_cancel);
        this.b.setOnClickListener(this);
        this.a.setOnClickListener(new ECJiaSearchAllActivity_2(this));
        findViewById(R.id.search_back).setOnClickListener(new ECJiaSearchAllActivity_3(this));
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 66) {
            return super.dispatchKeyEvent(keyEvent);
        }
        String string = getBaseContext().getResources().getString(R.string.search_please_input);
        c();
        String charSequence = this.a.getText().toString();
        e.a().a(this, charSequence);
        if (charSequence == null || "".equals(charSequence)) {
            k kVar = new k((Context) this, string);
            kVar.a(17, 0, 0);
            kVar.a();
        } else {
            this.p = charSequence;
            this.n.a(this.p);
        }
        return true;
    }

    public void b() {
        k kVar;
        if (this.n.e == 1) {
            if (!TextUtils.isEmpty(this.p)) {
                e.a().b(this, this.p);
            }
            if (this.n.b.size() == 0) {
                kVar = new k((Context) this, this.g.getString(R.string.search_nothing_seller));
                kVar.a(17, 0, 0);
                kVar.a();
                this.d.setVisibility(0);
                this.k.setVisibility(8);
            } else {
                this.k.setVisibility(0);
                this.d.setVisibility(8);
            }
            if (this.o == null) {
                this.o = new bn(this, this.n.b);
                this.o.a = this.q;
                this.k.setAdapter(this.o);
                return;
            }
            this.o.notifyDataSetChanged();
        } else if (this.n.e == 2) {
            if (!TextUtils.isEmpty(this.p)) {
                e.a().c(this, this.p);
            }
            if (this.n.a.size() == 0) {
                kVar = new k((Context) this, this.g.getString(R.string.search_nothing_good));
                kVar.a(17, 0, 0);
                kVar.a();
                this.d.setVisibility(0);
                this.k.setVisibility(8);
            } else {
                this.k.setVisibility(0);
                this.d.setVisibility(8);
            }
            if (this.m == null) {
                this.m = new s(this, this.n.a);
                this.k.setAdapter(this.m);
                return;
            }
            this.m.notifyDataSetChanged();
        } else {
            this.d.setVisibility(0);
            this.k.setVisibility(8);
        }
    }

    @TargetApi(11)
    public void onClick(View view) {
        getBaseContext().getResources();
        switch (view.getId()) {
            case R.id.tv_search_cancel:
                c();
                finish();
                return;
            default:
                return;
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void c() {
        this.a.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return true;
        }
        c();
        finish();
        return false;
    }

    public void a(int i) {
        this.n.a(this.p);
    }

    public void b(int i) {
        this.n.b(this.p);
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
        if ("collectrefresh".equals(aVar.c()) && this.n != null && this.n.e == 1) {
            this.n.a(this.p);
        }
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case -2073837039:
                if (str.equals("seller/collect/create")) {
                    z = false;
                    break;
                }
                break;
            case -2057001280:
                if (str.equals("seller/collect/delete")) {
                    z = true;
                    break;
                }
                break;
            case -1466113407:
                if (str.equals("goods/search")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    ((ao) this.o.a().get(this.r)).a("1");
                    ((ao) this.o.a().get(this.r)).a(Integer.valueOf(((ao) this.o.a().get(this.r)).g().intValue() + 1));
                    this.o.notifyDataSetChanged();
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    ((ao) this.o.a().get(this.r)).a("0");
                    ((ao) this.o.a().get(this.r)).a(Integer.valueOf(((ao) this.o.a().get(this.r)).g().intValue() - 1));
                    this.o.notifyDataSetChanged();
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    this.k.stopRefresh();
                    this.k.stopLoadMore();
                    this.k.setRefreshTime();
                    b();
                    if (this.n.c.a() == 0) {
                        this.k.setPullLoadEnable(false);
                        return;
                    } else {
                        this.k.setPullLoadEnable(true);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }
}
