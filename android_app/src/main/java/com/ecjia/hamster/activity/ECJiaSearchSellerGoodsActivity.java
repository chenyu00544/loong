package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ecjia.a.e;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ae;
import com.ecjia.component.a.af;
import com.ecjia.component.a.ag;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bn;
import com.ecjia.hamster.adapter.s;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"CommitPrefEdits"})
public class ECJiaSearchSellerGoodsActivity extends a implements OnClickListener, a, ECJiaXListView.a {
    private View A;
    private View B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private int G;
    private boolean H;
    private Handler I;
    private int J = -1;
    private EditText a;
    private TextView b;
    private LinearLayout c;
    private ECJiaErrorView d;
    private FrameLayout e;
    private ECJiaXListView k;
    private ag l;
    private af m;
    private s n;
    private ae o;
    private bn p;
    private ECJia_FILTER q;
    private String r;
    private String s;
    private LinearLayout t;
    private RelativeLayout u;
    private RelativeLayout v;
    private RelativeLayout w;
    private RelativeLayout x;
    private View y;
    private View z;

    class ECJiaSearchSellerGoodsActivity_1 extends Handler {
        final /* synthetic */ ECJiaSearchSellerGoodsActivity a;

        ECJiaSearchSellerGoodsActivity_1(ECJiaSearchSellerGoodsActivity eCJiaSearchSellerGoodsActivity) {
            this.a = eCJiaSearchSellerGoodsActivity;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.a.J = message.arg1;
                this.a.m.c(((ao) this.a.p.a().get(message.arg1)).e());
            }
            if (message.what == 2) {
                this.a.J = message.arg1;
                this.a.m.d(((ao) this.a.p.a().get(message.arg1)).e());
            }
        }
    }

    class ECJiaSearchSellerGoodsActivity_2 extends TimerTask {
        final /* synthetic */ ECJiaSearchSellerGoodsActivity a;

        ECJiaSearchSellerGoodsActivity_2(ECJiaSearchSellerGoodsActivity eCJiaSearchSellerGoodsActivity) {
            this.a = eCJiaSearchSellerGoodsActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.a.getContext().getSystemService("input_method")).showSoftInput(this.a.a, 0);
        }
    }

    class ECJiaSearchSellerGoodsActivity_3 implements OnEditorActionListener {
        final /* synthetic */ ECJiaSearchSellerGoodsActivity a;

        ECJiaSearchSellerGoodsActivity_3(ECJiaSearchSellerGoodsActivity eCJiaSearchSellerGoodsActivity) {
            this.a = eCJiaSearchSellerGoodsActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            String string = this.a.getBaseContext().getResources().getString(R.string.search_please_input);
            this.a.c();
            if (i == 3) {
                String obj = this.a.a.getText().toString();
                e.a().a(this.a, obj);
                if (obj == null || "".equals(obj)) {
                    k kVar = new k(this.a, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else {
                    this.a.s = obj;
                    this.a.q.setKeywords(this.a.s);
                    if (this.a.H) {
                        this.a.o.c(this.a.s);
                    } else {
                        this.a.l.a(this.a.q, this.a.r);
                    }
                }
            }
            return true;
        }
    }

    class ECJiaSearchSellerGoodsActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaSearchSellerGoodsActivity a;

        ECJiaSearchSellerGoodsActivity_4(ECJiaSearchSellerGoodsActivity eCJiaSearchSellerGoodsActivity) {
            this.a = eCJiaSearchSellerGoodsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_sellergoods);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        this.I = new ECJiaSearchSellerGoodsActivity_1(this);
        this.q = new ECJia_FILTER();
        this.q.setSort_by(c(this.G));
        Intent intent = getIntent();
        this.r = intent.getStringExtra("sellerid");
        this.s = intent.getStringExtra("keyword");
        this.H = intent.getBooleanExtra("searchseller", false);
        if (this.H) {
            if (this.o == null) {
                this.o = new ae(this);
                this.o.a((a) this);
            }
            if (this.m == null) {
                this.m = new af(this);
                this.m.a((a) this);
            }
        } else if (this.l == null) {
            this.l = new ag(this);
            this.l.a((a) this);
        }
        f();
        e();
        if (this.H) {
            this.a.setHint(this.g.getString(R.string.search_input_shop));
        } else {
            this.a.setHint(this.g.getString(R.string.search_input_shop_goods));
        }
        if (TextUtils.isEmpty(this.s)) {
            b();
            return;
        }
        this.q.setKeywords(this.s);
        this.a.setText(this.s);
        if (this.H) {
            this.o.c(this.s);
        } else {
            this.l.a(this.q, this.r);
        }
    }

    private void e() {
        this.t = (LinearLayout) findViewById(R.id.filter_toolbar);
        if (this.H) {
            this.t.setVisibility(8);
        } else {
            this.t.setVisibility(0);
        }
        this.u = (RelativeLayout) findViewById(R.id.rl_filter_one);
        this.u.setOnClickListener(this);
        this.u.setEnabled(false);
        this.v = (RelativeLayout) findViewById(R.id.rl_filter_two);
        this.v.setOnClickListener(this);
        this.w = (RelativeLayout) findViewById(R.id.rl_filter_three);
        this.w.setOnClickListener(this);
        this.x = (RelativeLayout) findViewById(R.id.rl_filter_four);
        this.x.setOnClickListener(this);
        this.y = findViewById(R.id.filter_one);
        this.z = findViewById(R.id.filter_two);
        this.A = findViewById(R.id.filter_three);
        this.B = findViewById(R.id.filter_four);
        this.C = (TextView) findViewById(R.id.tv_filter_one);
        this.D = (TextView) findViewById(R.id.tv_filter_two);
        this.E = (TextView) findViewById(R.id.tv_filter_three);
        this.F = (TextView) findViewById(R.id.tv_filter_four);
    }

    private void f() {
        this.k = (ECJiaXListView) findViewById(R.id.good_list);
        if (this.H) {
            this.p = new bn(this, this.o.b);
            this.p.a = this.I;
            this.k.setAdapter(this.p);
        } else {
            this.n = new s(this, this.l.h);
            this.k.setAdapter(this.n);
        }
        this.k.setPullLoadEnable(false);
        this.k.setRefreshTime();
        this.k.setXListViewListener(this, 1);
        this.d = (ECJiaErrorView) findViewById(R.id.null_pager);
        this.e = (FrameLayout) findViewById(R.id.fl_search_top);
        this.c = (LinearLayout) findViewById(R.id.ll_search);
        this.a = (EditText) findViewById(R.id.et_search_input);
        this.b = (TextView) findViewById(R.id.tv_search_cancel);
        this.b.setOnClickListener(this);
        this.a.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        this.a.requestFocus();
        ((InputMethodManager) this.a.getContext().getSystemService("input_method")).showSoftInput(this.a, 0);
        new Timer().schedule(new ECJiaSearchSellerGoodsActivity_2(this), 998);
        this.a.setOnEditorActionListener(new ECJiaSearchSellerGoodsActivity_3(this));
        findViewById(R.id.search_back).setOnClickListener(new ECJiaSearchSellerGoodsActivity_4(this));
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 66) {
            return super.dispatchKeyEvent(keyEvent);
        }
        String string = getBaseContext().getResources().getString(R.string.search_please_input);
        c();
        String obj = this.a.getText().toString();
        e.a().a(this, obj);
        if (obj == null || "".equals(obj)) {
            k kVar = new k((Context) this, string);
            kVar.a(17, 0, 0);
            kVar.a();
        } else {
            this.s = obj;
            this.q.setKeywords(this.s);
            if (this.H) {
                this.o.c(this.s);
            } else {
                this.l.a(this.q, this.r);
            }
        }
        return true;
    }

    public void b() {
        if (this.H) {
            if (this.o.b.size() == 0) {
                this.d.setErrorImageResource(R.drawable.null_normal);
                this.d.setErrorText((int) R.string.null_shop);
                this.d.setVisibility(0);
                this.k.setVisibility(8);
            } else {
                this.k.setVisibility(0);
                this.d.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.s)) {
                e.a().b(this, this.s);
            }
            this.p.notifyDataSetChanged();
            return;
        }
        if (this.l.h.size() == 0) {
            this.d.setVisibility(0);
            this.k.setVisibility(8);
        } else {
            this.k.setVisibility(0);
            this.d.setVisibility(8);
        }
        this.n.notifyDataSetChanged();
    }

    private String c(int i) {
        String str = "new";
        switch (i) {
            case 0:
                return "new";
            case 1:
                return "hot";
            case 2:
                return "price_desc";
            case 3:
                return "price_asc";
            default:
                return str;
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
            case R.id.rl_filter_one:
                this.u.setEnabled(false);
                this.v.setEnabled(true);
                this.w.setEnabled(true);
                this.x.setEnabled(true);
                this.y.setVisibility(0);
                this.z.setVisibility(4);
                this.A.setVisibility(4);
                this.B.setVisibility(4);
                this.C.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                this.D.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.E.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.F.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.G = 0;
                this.q.setSort_by(c(this.G));
                if (TextUtils.isEmpty(this.s)) {
                    this.d.setVisibility(0);
                    this.k.setVisibility(8);
                    return;
                }
                this.l.a(this.q, this.r);
                return;
            case R.id.rl_filter_two:
                this.u.setEnabled(true);
                this.v.setEnabled(false);
                this.w.setEnabled(true);
                this.x.setEnabled(true);
                this.y.setVisibility(4);
                this.z.setVisibility(0);
                this.A.setVisibility(4);
                this.B.setVisibility(4);
                this.C.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.D.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                this.E.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.F.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.G = 1;
                this.q.setSort_by(c(this.G));
                if (TextUtils.isEmpty(this.s)) {
                    this.d.setVisibility(0);
                    this.k.setVisibility(8);
                    return;
                }
                this.l.a(this.q, this.r);
                return;
            case R.id.rl_filter_three:
                this.u.setEnabled(true);
                this.v.setEnabled(true);
                this.w.setEnabled(false);
                this.x.setEnabled(true);
                this.y.setVisibility(4);
                this.z.setVisibility(4);
                this.A.setVisibility(0);
                this.B.setVisibility(4);
                this.C.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.D.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.E.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                this.F.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.G = 2;
                this.q.setSort_by(c(this.G));
                if (TextUtils.isEmpty(this.s)) {
                    this.d.setVisibility(0);
                    this.k.setVisibility(8);
                    return;
                }
                this.l.a(this.q, this.r);
                return;
            case R.id.rl_filter_four:
                this.u.setEnabled(true);
                this.v.setEnabled(true);
                this.w.setEnabled(true);
                this.x.setEnabled(false);
                this.y.setVisibility(4);
                this.z.setVisibility(4);
                this.A.setVisibility(4);
                this.B.setVisibility(0);
                this.C.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.D.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.E.setTextColor(this.g.getColor(R.color.filter_text_color));
                this.F.setTextColor(this.g.getColor(R.color.public_theme_color_normal));
                this.G = 3;
                this.q.setSort_by(c(this.G));
                if (TextUtils.isEmpty(this.s)) {
                    this.d.setVisibility(0);
                    this.k.setVisibility(8);
                    return;
                }
                this.l.a(this.q, this.r);
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
        if (this.H) {
            this.o.c(this.s);
        } else {
            this.l.a(this.q, this.r);
        }
    }

    public void b(int i) {
        if (this.H) {
            this.o.d(this.s);
        } else {
            this.l.b(this.q, this.r);
        }
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
        if ("collectrefresh".equals(aVar.c()) && this.o != null && this.H) {
            this.o.c(this.s);
        }
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case -2073837039:
                if (str.equals("seller/collect/create")) {
                    z = true;
                    break;
                }
                break;
            case -2057001280:
                if (str.equals("seller/collect/delete")) {
                    z = true;
                    break;
                }
                break;
            case -1175639458:
                if (str.equals("merchant/goods/list")) {
                    z = false;
                    break;
                }
                break;
            case 1324243512:
                if (str.equals("seller/search")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.k.stopRefresh();
                    this.k.stopLoadMore();
                    this.k.setRefreshTime();
                    b();
                    if (this.l.i.a() == 0) {
                        this.k.setPullLoadEnable(false);
                        return;
                    } else {
                        this.k.setPullLoadEnable(true);
                        return;
                    }
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    ((ao) this.p.a().get(this.J)).a("1");
                    ((ao) this.p.a().get(this.J)).a(Integer.valueOf(((ao) this.p.a().get(this.J)).g().intValue() + 1));
                    this.p.notifyDataSetChanged();
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    ((ao) this.p.a().get(this.J)).a("0");
                    ((ao) this.p.a().get(this.J)).a(Integer.valueOf(((ao) this.p.a().get(this.J)).g().intValue() - 1));
                    this.p.notifyDataSetChanged();
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    this.k.stopRefresh();
                    this.k.stopLoadMore();
                    this.k.setRefreshTime();
                    b();
                    if (this.o.d.a() == 0) {
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
