package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.aa;
import com.ecjia.component.a.al;
import com.ecjia.component.a.x;
import com.ecjia.component.a.y;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.as;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.paycenter.b.b;
import com.ecjia.hamster.paycenter.base.ECJiaOnPaySucceedListener;
import com.ecjia.hamster.paycenter.base.ECJiaOnPaySucceedListener.PaymentType;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.util.ArrayList;

public class ECJiaChoosePayActivity extends a implements a, ECJiaXListView.a, ECJiaOnPaySucceedListener {
    private as A;
    private LinearLayout B;
    private LinearLayout C;
    private TextView D;
    private TextView E;
    private TextView F;
    private View G;
    private String H = "";
    private Boolean I;
    private aa J;
    private String K;
    private String L;
    private int M = 0;
    private String N;
    private String O;
    private String P;
    private String Q;
    private y R;
    private b S;
    private com.ecjia.hamster.paycenter.a.b T;
    private com.ecjia.hamster.paycenter.c.b U;
    private String V;
    Intent a;
    String b;
    ArrayList<ECJia_PAYMENT> c;
    boolean d = true;
    private String e;
    private Boolean k;
    private String l;
    private Button m;
    private x n;
    private TextView o;
    private TextView p;
    private LinearLayout q;
    private LinearLayout r;
    private Resources s;
    private LinearLayout t;
    private LinearLayout u;
    private TextView v;
    private LinearLayout w;
    private LinearLayout x;
    private ListView y;
    private ImageView z;

    class ECJiaChoosePayActivity_1 implements as.b {
        final /* synthetic */ ECJiaChoosePayActivity a;

        ECJiaChoosePayActivity_1(ECJiaChoosePayActivity eCJiaChoosePayActivity) {
            this.a = eCJiaChoosePayActivity;
        }

        public void a(View view, int i) {
            this.a.O = ((ECJia_PAYMENT) this.a.A.a.get(i)).getPay_code();
            this.a.Q = ((ECJia_PAYMENT) this.a.A.a.get(i)).getPay_name();
            this.a.K = ((ECJia_PAYMENT) this.a.A.a.get(i)).getPay_id();
            this.a.P = this.a.Q;
            this.a.N = this.a.O;
            if (this.a.I.booleanValue()) {
                this.a.J.a(this.a.b, "", this.a.K, this.a.L);
            } else {
                this.a.n.c(this.a.e, this.a.K);
            }
            this.a.p.setText(this.a.P);
            this.a.y.setVisibility(8);
            this.a.z.setBackgroundResource(R.drawable.search_showchild);
        }
    }

    class ECJiaChoosePayActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaChoosePayActivity a;

        ECJiaChoosePayActivity_2(ECJiaChoosePayActivity eCJiaChoosePayActivity) {
            this.a = eCJiaChoosePayActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(this.a, ECJiaMainActivity.class);
            intent.setFlags(67141632);
            this.a.startActivity(intent);
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    class ECJiaChoosePayActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaChoosePayActivity a;

        ECJiaChoosePayActivity_3(ECJiaChoosePayActivity eCJiaChoosePayActivity) {
            this.a = eCJiaChoosePayActivity;
        }

        public void onClick(View view) {
            if (this.a.k.booleanValue()) {
                Intent intent = new Intent();
                intent.setClass(this.a, ECJiaOrderListActivity.class);
                intent.putExtra("order_type", "await_ship");
                this.a.startActivity(intent);
                this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                this.a.finish();
                return;
            }
            intent = new Intent();
            intent.setClass(this.a, ECJiaOrderListActivity.class);
            intent.putExtra("order_type", "await_ship");
            intent.setFlags(67108864);
            this.a.startActivity(intent);
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            this.a.finish();
        }
    }

    class ECJiaChoosePayActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaChoosePayActivity a;

        ECJiaChoosePayActivity_4(ECJiaChoosePayActivity eCJiaChoosePayActivity) {
            this.a = eCJiaChoosePayActivity;
        }

        public void onClick(View view) {
            if (this.a.I.booleanValue()) {
                this.a.R.b(this.a.J.e, this.a.J.d);
            } else {
                this.a.R.a(this.a.e);
            }
        }
    }

    class ECJiaChoosePayActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaChoosePayActivity a;

        ECJiaChoosePayActivity_5(ECJiaChoosePayActivity eCJiaChoosePayActivity) {
            this.a = eCJiaChoosePayActivity;
        }

        public void onClick(View view) {
            if (this.a.y.getVisibility() == 0) {
                this.a.y.setVisibility(8);
                this.a.z.setBackgroundResource(R.drawable.search_showchild);
            } else if (this.a.y.getVisibility() == 8) {
                this.a.y.setVisibility(0);
                this.a.z.setBackgroundResource(R.drawable.search_hidden);
            }
            this.a.A.notifyDataSetChanged();
        }
    }

    class ECJiaChoosePayActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaChoosePayActivity a;

        ECJiaChoosePayActivity_6(ECJiaChoosePayActivity eCJiaChoosePayActivity) {
            this.a = eCJiaChoosePayActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    @SuppressLint({"JavascriptInterface"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.choose_pay);
        c.a().a((Object) this);
        a();
        this.R = new y(this);
        this.R.a((a) this);
        this.n = new x(this);
        this.n.a((a) this);
        this.J = new aa(this);
        this.J.a((a) this);
        e();
        f();
    }

    private void c() {
        b();
        this.x.setVisibility(0);
        if (this.A == null) {
            this.A = new as(this, this.c);
            this.A.a(new ECJiaChoosePayActivity_1(this));
            this.y.setAdapter(this.A);
            a(this.y);
            return;
        }
        this.A.a = this.c;
        a(this.y);
        this.A.notifyDataSetChanged();
    }

    private void e() {
        this.t = (LinearLayout) findViewById(R.id.choose_btnitem);
        this.v = (TextView) findViewById(R.id.error_desc);
        this.u = (LinearLayout) findViewById(R.id.error_item);
        this.w = (LinearLayout) findViewById(R.id.choose_create);
        this.r = (LinearLayout) findViewById(R.id.change_pay_type);
        this.m = (Button) findViewById(R.id.payweb_submit);
        this.o = (TextView) findViewById(R.id.choose_total_fee);
        this.p = (TextView) findViewById(R.id.choose_paytype);
        this.q = (LinearLayout) findViewById(R.id.choose_paytype_list);
        this.x = (LinearLayout) findViewById(R.id.type_rechange_item);
        this.y = (ListView) findViewById(R.id.choose_listview);
        this.z = (ImageView) findViewById(R.id.choose_showlist);
        this.B = (LinearLayout) findViewById(R.id.yue_item);
        this.C = (LinearLayout) findViewById(R.id.yue_more);
        this.D = (TextView) findViewById(R.id.yue_title);
        this.E = (TextView) findViewById(R.id.yue_buy);
        this.F = (TextView) findViewById(R.id.yue_order);
        this.G = findViewById(R.id.dis_view);
        this.E.setOnClickListener(new ECJiaChoosePayActivity_2(this));
        this.F.setOnClickListener(new ECJiaChoosePayActivity_3(this));
        this.m.setOnClickListener(new ECJiaChoosePayActivity_4(this));
        this.q.setOnClickListener(new ECJiaChoosePayActivity_5(this));
    }

    private void f() {
        this.s = getResources();
        String string = this.s.getString(R.string.yuan);
        String string2 = this.s.getString(R.string.yuan_unit);
        this.a = getIntent();
        int i;
        if (!TextUtils.isEmpty(this.a.getStringExtra("pay_code"))) {
            this.N = this.a.getStringExtra("pay_code");
            for (i = 0; i < this.h.d.size(); i++) {
                if (((ECJia_PAYMENT) this.h.d.get(i)).getPay_code().equals(this.N)) {
                    this.P = ((ECJia_PAYMENT) this.h.d.get(i)).getPay_name();
                    this.K = ((ECJia_PAYMENT) this.h.d.get(i)).getPay_id();
                    break;
                }
            }
        } else if (!TextUtils.isEmpty(this.a.getStringExtra("pay_id"))) {
            this.K = this.a.getStringExtra("pay_id");
            for (i = 0; i < this.h.d.size(); i++) {
                if (((ECJia_PAYMENT) this.h.d.get(i)).getPay_id().equals(this.K)) {
                    this.N = ((ECJia_PAYMENT) this.h.d.get(i)).getPay_code();
                    this.P = ((ECJia_PAYMENT) this.h.d.get(i)).getPay_name();
                    break;
                }
            }
        }
        this.k = Boolean.valueOf(this.a.getBooleanExtra("pay_is_create", false));
        String stringExtra = this.a.getStringExtra("pay_type");
        if (stringExtra.equals("account_id")) {
            this.I = Boolean.valueOf(true);
            this.L = this.a.getStringExtra(stringExtra);
        } else {
            this.I = Boolean.valueOf(false);
            this.e = this.a.getStringExtra(stringExtra);
        }
        this.l = this.a.getStringExtra("pay_body");
        this.b = this.a.getStringExtra("pay_amount");
        if (this.I.booleanValue()) {
            this.J.a(this.b, "", this.K, this.L);
            this.B.setVisibility(8);
            this.t.setVisibility(0);
            this.G.setVisibility(0);
            c();
        } else {
            String[] strArr = new String[]{"pay_bank", "pay_cod"};
            int i2 = 0;
            while (i2 < strArr.length) {
                if (!TextUtils.isEmpty(this.N) && this.N.equals(strArr[i2])) {
                    this.d = false;
                    break;
                }
                i2++;
            }
            if (!this.d || this.N.equals("pay_balance")) {
                this.R.a(this.e);
            } else {
                this.B.setVisibility(8);
                this.t.setVisibility(0);
                this.G.setVisibility(0);
                c();
            }
        }
        this.p.setText(this.P);
        if (this.k.booleanValue()) {
            this.w.setVisibility(0);
        } else {
            this.w.setVisibility(8);
        }
        this.o.setText(string2 + k.a(this.b) + string);
    }

    private void g() {
        String string = this.s.getString(R.string.choosepay_can_use);
        String string2 = this.s.getString(R.string.choosepay_zero_nopay);
        this.N = this.R.f;
        this.p.setText(this.R.b);
        if (0.0f == k.a(this.b)) {
            a(PaymentType.PAYMENT_ZERO, string2);
        } else if ("pay_balance".equals(this.R.f)) {
            if ("error".equals(this.R.h)) {
                c();
                this.B.setVisibility(0);
                this.C.setVisibility(8);
                this.D.setText(this.R.i);
                this.G.setVisibility(8);
                this.t.setVisibility(8);
                return;
            }
            a(PaymentType.PAYMENT_CODE_BALANCE, string + this.R.j + "。");
        } else if ("pay_bank".equals(this.R.f)) {
            r0 = this.s.getString(R.string.choosepay_need_line_pay);
            if (this.k.booleanValue()) {
                this.B.setVisibility(0);
            } else {
                this.B.setVisibility(8);
            }
            this.u.setVisibility(0);
            this.v.setText(this.R.g);
            this.D.setText(r0);
            this.C.setVisibility(0);
            this.t.setVisibility(8);
            this.x.setVisibility(8);
        } else if ("pay_cod".equals(this.R.f)) {
            r0 = this.s.getString(R.string.choosepay_cod);
            if (this.k.booleanValue()) {
                this.B.setVisibility(0);
            } else {
                this.B.setVisibility(8);
            }
            this.u.setVisibility(0);
            this.v.setText(r0);
            this.C.setVisibility(0);
            this.t.setVisibility(8);
            this.x.setVisibility(8);
            this.D.setText("");
        } else {
            this.B.setVisibility(8);
            this.t.setVisibility(0);
            this.G.setVisibility(0);
            c();
            Resources resources = getResources();
            string2 = resources.getString(R.string.choosepay_no_unionpay);
            String string3 = resources.getString(R.string.choosepay_no_wxpay);
            resources.getString(R.string.choosepay_unknown_pay);
            string = resources.getString(R.string.not_install_wxpay);
            Intent intent;
            if (this.R.f.equals("pay_alipay")) {
                q.a(this.R.f + "这是paycod");
                this.R.d.i(this.l);
                if (TextUtils.isEmpty(this.R.d.a())) {
                    intent = new Intent(this, ECJiaPayWebActivity.class);
                    intent.putExtra("code", this.R.f);
                    intent.putExtra("html", this.R.g);
                    startActivity(intent);
                    return;
                }
                if (this.T == null) {
                    this.T = new com.ecjia.hamster.paycenter.a.b(this);
                    this.T.a((ECJiaOnPaySucceedListener) this);
                }
                this.T.a(this.R.d);
            } else if (this.R.f.equals("pay_upmp")) {
                new com.ecjia.component.view.k((Context) this, string2).a();
            } else if (!this.R.f.equals("pay_wxpay")) {
                intent = new Intent(this, ECJiaPayWebActivity.class);
                intent.putExtra("code", this.R.f);
                intent.putExtra("html", this.R.g);
                startActivity(intent);
            } else if (TextUtils.isEmpty(this.R.e.a())) {
                new com.ecjia.component.view.k((Context) this, string3).a();
            } else {
                if (this.U == null) {
                    this.U = new com.ecjia.hamster.paycenter.c.b(this);
                }
                if (this.U.a()) {
                    this.U.a(this.R.e);
                } else {
                    new com.ecjia.component.view.k((Context) this, string).a();
                }
            }
        }
    }

    public void a() {
        this.i = (ECJiaTopView) findViewById(R.id.choosepay_topview);
        this.i.setTitleText((int) R.string.payment_center);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaChoosePayActivity_6(this));
    }

    public void a(int i) {
    }

    public void b(int i) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        return true;
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    void b() {
        int i = 0;
        if (this.c == null) {
            this.c = new ArrayList();
        } else {
            this.c.clear();
        }
        int i2;
        if (this.I.booleanValue()) {
            for (i2 = 0; i2 < this.h.g.size(); i2++) {
                if (!((ECJia_PAYMENT) this.h.g.get(i2)).getPay_code().equals(this.N)) {
                    this.c.add(this.h.g.get(i2));
                }
            }
        } else if (this.M == 1) {
            for (i2 = 0; i2 < this.h.e.size(); i2++) {
                if (!((ECJia_PAYMENT) this.h.e.get(i2)).getPay_code().equals(this.N)) {
                    this.c.add(this.h.e.get(i2));
                }
            }
        } else {
            while (i < this.h.e.size()) {
                this.c.add(this.h.e.get(i));
                i++;
            }
        }
    }

    private void a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
        }
    }

    protected void onResume() {
        super.onResume();
        if ("wappay".equals(this.H)) {
            this.s.getString(R.string.payment_paysuccess);
            a(PaymentType.PAYMENT_WAPPAY, this.s.getString(R.string.payment_payfail));
            this.H = "";
        }
        if (!"wxpay".equals(this.V)) {
            return;
        }
        if (this.I.booleanValue()) {
            new al(this).a();
            return;
        }
        a(PaymentType.PAYMENT_WXPAY, getResources().getString(R.string.payment_paysuccess));
        this.V = "";
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
        if ("wappay".equals(aVar.c())) {
            this.H = aVar.c();
        }
        if ("wxpay".equals(aVar.c())) {
            this.V = aVar.c();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (!(intent == null || i2 != -1 || i == 100)) {
            this.S.a(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void a(PaymentType paymentType, String str) {
        this.u.setVisibility(8);
        this.t.setVisibility(8);
        this.x.setVisibility(8);
        this.B.setVisibility(0);
        this.C.setVisibility(0);
        this.D.setText(str);
        getResources();
        if (str.equals(str)) {
            new al(this).a();
        }
        setResult(-1);
        if (!this.k.booleanValue()) {
            c.a().c(new com.ecjia.a.a.b(true, 2));
        }
        if (paymentType != PaymentType.PAYMENT_UPPAY) {
        }
    }

    public void a(String str, String str2, ax axVar) {
        int i = -1;
        switch (str.hashCode()) {
            case -1121250696:
                if (str.equals("user/account/deposit")) {
                    i = 1;
                    break;
                }
                break;
            case -392675737:
                if (str.equals("order/pay")) {
                    i = 2;
                    break;
                }
                break;
            case 294875250:
                if (str.equals("user/info")) {
                    i = 4;
                    break;
                }
                break;
            case 1444503210:
                if (str.equals("order/update")) {
                    i = 0;
                    break;
                }
                break;
            case 1698802050:
                if (str.equals("user/account/pay")) {
                    i = 3;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                if (axVar.b() == 1) {
                    this.N = this.O;
                    this.P = this.Q;
                    this.p.setText(this.P);
                    q.a("支付方式更新");
                    c.a().c(new com.ecjia.a.a.b("UPDATE_ORDER"));
                    this.B.setVisibility(8);
                    this.G.setVisibility(0);
                    this.x.setVisibility(0);
                    this.t.setVisibility(0);
                    c();
                    if (this.N.equals("pay_balance")) {
                        this.R.a(this.e);
                        return;
                    }
                    return;
                }
                return;
            case 1:
                this.M = axVar.b();
                if (axVar.b() == 1) {
                    this.K = this.J.e;
                    this.L = this.J.d;
                    this.p.setText(this.P);
                    q.a("支付方式更新");
                    this.B.setVisibility(8);
                    this.G.setVisibility(0);
                    this.x.setVisibility(0);
                    this.t.setVisibility(0);
                    c();
                    return;
                }
                return;
            case 2:
                this.M = axVar.b();
                if (axVar.b() == 1) {
                    g();
                    return;
                }
                return;
            case 3:
                this.M = axVar.b();
                if (axVar.b() == 1) {
                    g();
                    return;
                }
                return;
            case 4:
                if (axVar.b() == 1 && this.I.booleanValue()) {
                    c.a().c(new com.ecjia.a.a.b("changed"));
                    Intent intent = new Intent(this, ECJiaAccountActivity.class);
                    intent.setFlags(67108864);
                    startActivity(intent);
                    return;
                }
                return;
            default:
                this.m.setVisibility(8);
                com.ecjia.component.view.k kVar = new com.ecjia.component.view.k((Context) this, getResources().getString(R.string.choosepay_network_problem));
                kVar.a(17, 0, 0);
                kVar.a();
                return;
        }
    }
}
