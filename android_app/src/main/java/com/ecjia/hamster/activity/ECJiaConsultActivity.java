package com.ecjia.hamster.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.a.u;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.k;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.g;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.l;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import java.util.ArrayList;

public class ECJiaConsultActivity extends a implements TextWatcher, OnClickListener, a, ECJiaXListView.a {
    private k A;
    private g B;
    private String C;
    private ArrayList<l> D = new ArrayList();
    private SharedPreferences E;
    private Editor F;
    private String G;
    private Bitmap H;
    private TextView I;
    private int J = 1;
    private String K;
    int a = 1;
    int b = 0;
    private ImageView c;
    private TextView d;
    private final String e = "order_consult";
    private final String k = "goods_consult";
    private final String l = "all_consult";
    private LinearLayout m;
    private LinearLayout n;
    private TextView o;
    private TextView p;
    private TextView q;
    private ImageView r;
    private Intent s;
    private TextView t;
    private TextView u;
    private TextView v;
    private ImageView w;
    private ECJiaXListView x;
    private EditText y;
    private TextView z;

    class ECJiaConsultActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaConsultActivity a;

        ECJiaConsultActivity_1(ECJiaConsultActivity eCJiaConsultActivity) {
            this.a = eCJiaConsultActivity;
        }

        public void onClick(View view) {
            this.a.e();
        }
    }

    class ECJiaConsultActivity_2 implements OnFocusChangeListener {
        final /* synthetic */ ECJiaConsultActivity a;

        ECJiaConsultActivity_2(ECJiaConsultActivity eCJiaConsultActivity) {
            this.a = eCJiaConsultActivity;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                this.a.I.setVisibility(0);
                this.a.I.setEnabled(true);
                return;
            }
            this.a.I.setVisibility(8);
            this.a.I.setEnabled(false);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_consult);
        this.E = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.F = this.E.edit();
        this.G = this.E.getString("uid", "");
        if (!TextUtils.isEmpty(this.G)) {
            this.H = u.a().b(this.G);
        }
        this.s = getIntent();
        this.C = this.s.getStringExtra("type");
        if (TextUtils.isEmpty(this.C)) {
            this.C = "all_consult";
        }
        a(this.C);
        c(this.C);
    }

    private void a(String str) {
        this.c = (ImageView) findViewById(R.id.top_view_back);
        this.c.setOnClickListener(this);
        this.d = (TextView) findViewById(R.id.top_view_text);
        this.I = (TextView) findViewById(R.id.consult_close_keyboard);
        this.I.setOnClickListener(this);
        this.x = (ECJiaXListView) findViewById(R.id.consult_list);
        this.x.setXListViewListener(this, 0);
        this.x.setPullRefreshEnable(false);
        this.x.setPullLoadEnable(false);
        this.x.setRefreshTime();
        View inflate = LayoutInflater.from(this).inflate(R.layout.head_consult_order, null);
        this.n = (LinearLayout) inflate.findViewById(R.id.consult_order);
        this.o = (TextView) inflate.findViewById(R.id.consult_order_number);
        this.p = (TextView) inflate.findViewById(R.id.consult_order_price);
        this.q = (TextView) inflate.findViewById(R.id.consult_order_time);
        this.r = (ImageView) inflate.findViewById(R.id.consult_order_img);
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.head_consult_goods, null);
        this.m = (LinearLayout) inflate2.findViewById(R.id.consult_goods);
        this.t = (TextView) inflate2.findViewById(R.id.consult_goods_title);
        this.u = (TextView) inflate2.findViewById(R.id.consult_goods_price);
        this.v = (TextView) inflate2.findViewById(R.id.consult_goods_sendurl);
        this.w = (ImageView) inflate2.findViewById(R.id.consult_goods_img);
        if (str.equals("order_consult")) {
            this.o.setText(this.s.getStringExtra("order_sn"));
            if (0.0f == com.ecjia.a.k.a(this.s.getStringExtra("order_price"))) {
                this.p.setText("免费");
            } else {
                this.p.setText(this.s.getStringExtra("order_price"));
            }
            this.q.setText(this.s.getStringExtra("order_time"));
            p.a((Context) this).a(this.r, this.s.getStringExtra("order_goodsImg"));
            this.x.addHeaderView(inflate);
            this.d.setText(R.string.consult_order);
        } else if (str.equals("goods_consult")) {
            this.t.setText(this.s.getStringExtra("goods_title"));
            if (0.0f == com.ecjia.a.k.a(this.s.getStringExtra("goods_price"))) {
                this.u.setText("免费");
            } else {
                this.u.setText(this.s.getStringExtra("goods_price"));
            }
            p.a((Context) this).a(this.w, this.s.getStringExtra("goods_img"));
            this.v.setOnClickListener(new ECJiaConsultActivity_1(this));
            this.x.addHeaderView(inflate2);
            this.d.setText(R.string.consult_goods);
        } else if (str.equals("all_consult")) {
            this.d.setText(R.string.consult);
        }
        this.y = (EditText) findViewById(R.id.consult_edit);
        this.y.addTextChangedListener(this);
        this.y.setOnFocusChangeListener(new ECJiaConsultActivity_2(this));
        this.z = (TextView) findViewById(R.id.consult_send);
        this.z.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.consult_close_keyboard:
                c();
                return;
            case R.id.consult_send:
                b(this.C);
                return;
            case R.id.top_view_back:
                c();
                finish();
                return;
            default:
                return;
        }
    }

    private void e() {
        int intExtra = this.s.getIntExtra("goods_id", 0);
        this.K = "http://www.missmall.com/goods.php?id=" + intExtra;
        if (TextUtils.isEmpty(this.G)) {
            this.A.a(intExtra + "", "goods", null, this.K);
        } else {
            this.A.a(intExtra + "", "goods", this.h.e().p(), this.K);
        }
        this.J = 0;
    }

    @TargetApi(9)
    private void b(String str) {
        String obj = this.y.getText().toString();
        if (obj.trim().isEmpty()) {
            com.ecjia.component.view.k kVar = new com.ecjia.component.view.k((Context) this, "内容不能为空");
            kVar.a(17, 0, 0);
            kVar.a();
            this.y.setText("");
            return;
        }
        if (this.A == null) {
            this.A = new k(this);
            this.A.a((a) this);
        }
        int i = -1;
        switch (str.hashCode()) {
            case -1466137597:
                if (str.equals("goods_consult")) {
                    i = 1;
                    break;
                }
                break;
            case 771423227:
                if (str.equals("order_consult")) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                if (!TextUtils.isEmpty(this.G)) {
                    this.A.a(this.s.getStringExtra("order_id"), "orders", this.h.e().p(), obj);
                    break;
                } else {
                    this.A.a(this.s.getStringExtra("order_id"), "orders", null, obj);
                    break;
                }
            case 1:
                if (!TextUtils.isEmpty(this.G)) {
                    this.A.a(this.s.getIntExtra("goods_id", 0) + "", "goods", this.h.e().p(), obj);
                    break;
                } else {
                    this.A.a(this.s.getIntExtra("goods_id", 0) + "", "goods", null, obj);
                    break;
                }
            default:
                if (!TextUtils.isEmpty(this.G)) {
                    this.A.a(null, "common", this.h.e().p(), obj);
                    break;
                } else {
                    this.A.a(null, "common", null, obj);
                    break;
                }
        }
        this.J = 1;
    }

    private void c(String str) {
        this.A = new k(this);
        if (str.equals("order_consult")) {
            this.A.b(this.s.getStringExtra("order_id"), "orders");
        } else if (str.equals("goods_consult")) {
            if (this.G == null || this.G.equals("")) {
                this.A.b(this.s.getIntExtra("goods_id", 0) + "", "goods");
            } else {
                this.A.b(this.s.getIntExtra("goods_id", 0) + "", "goods");
            }
        } else if (this.G == null || this.G.equals("")) {
            this.A.b(null, "common");
        } else {
            this.A.b(null, "common");
        }
    }

    private void d(String str) {
        if (this.A == null) {
            this.A = new k(this);
        }
        if (str.equals("order_consult")) {
            this.A.c(this.s.getStringExtra("order_id"), "orders");
        } else if (str.equals("goods_consult")) {
            if (this.h.e() == null || TextUtils.isEmpty(this.h.e().m())) {
                this.A.c(this.s.getIntExtra("goods_id", 0) + "", "goods");
            } else {
                this.A.c(this.s.getIntExtra("goods_id", 0) + "", "goods");
            }
        } else if (this.h.e() == null || TextUtils.isEmpty(this.h.e().m())) {
            this.A.c(null, "common");
        } else {
            this.A.c(null, "common");
        }
    }

    public void b() {
        l lVar = new l();
        switch (this.J) {
            case 0:
                lVar.b(this.K);
                lVar.a("1");
                this.D.add(0, lVar);
                this.B.notifyDataSetChanged();
                this.x.setSelection(this.x.getCount() - 1);
                return;
            case 1:
                lVar.b(this.y.getText().toString());
                lVar.a("1");
                this.y.setText("");
                this.D.add(0, lVar);
                this.B.notifyDataSetChanged();
                this.x.setSelection(this.x.getCount() - 1);
                return;
            default:
                return;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.y.getText().toString() == null || "".equals(this.y.getText().toString())) {
            this.z.setBackgroundResource(0);
            this.z.setTextColor(getResources().getColor(R.color.my_dark));
            this.z.setEnabled(false);
            return;
        }
        this.z.setBackgroundResource(R.drawable.shape_public_bg);
        this.z.setTextColor(Color.parseColor("#ffffffff"));
        this.z.setEnabled(true);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.y.getText().toString() == null || "".equals(this.y.getText().toString())) {
            this.z.setBackgroundResource(0);
            this.z.setTextColor(getResources().getColor(R.color.my_dark));
            this.z.setEnabled(false);
            return;
        }
        this.z.setBackgroundResource(R.drawable.shape_public_bg);
        this.z.setTextColor(Color.parseColor("#ffffffff"));
        this.z.setEnabled(true);
    }

    public void afterTextChanged(Editable editable) {
        if (this.y.getText().toString() == null || "".equals(this.y.getText().toString())) {
            this.z.setBackgroundResource(0);
            this.z.setTextColor(getResources().getColor(R.color.my_dark));
            this.z.setEnabled(false);
            return;
        }
        this.z.setBackgroundResource(R.drawable.shape_public_bg);
        this.z.setTextColor(Color.parseColor("#ffffffff"));
        this.z.setEnabled(true);
    }

    public void a(int i) {
        d(this.C);
    }

    public void b(int i) {
    }

    public void c() {
        this.y.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.y.getWindowToken(), 0);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("feedback/list")) {
            if (axVar.b() == 1) {
                this.x.stopRefresh();
                if (this.A.a.a() == 0) {
                    this.x.setPullRefreshEnable(false);
                } else {
                    this.x.setPullRefreshEnable(true);
                }
                this.D = this.A.b;
                this.b = this.D.size();
                if (this.B == null) {
                    this.B = new g(this, this.D, this.H);
                    this.x.setAdapter(this.B);
                    this.x.setSelection(this.x.getCount() - 1);
                } else {
                    this.B.notifyDataSetChanged();
                    this.x.setSelection(0);
                }
            }
            if (axVar.b() == 2) {
                this.x.stopRefresh();
                if (this.A.a.a() == 0) {
                    this.x.setPullRefreshEnable(false);
                } else {
                    this.x.setPullRefreshEnable(true);
                }
                this.D = this.A.b;
                this.B.notifyDataSetChanged();
                this.x.setSelection(this.D.size() - this.b);
                this.b = this.D.size();
            }
            if (axVar.b() == 0) {
                this.x.stopRefresh();
                if (this.B == null) {
                    this.B = new g(this, this.D, this.H);
                    this.x.setAdapter(this.B);
                    this.x.setSelection(this.x.getCount() - 1);
                } else {
                    this.B.notifyDataSetChanged();
                    this.x.setSelection(0);
                }
            }
        }
        if (str.equals("feedback/create") && axVar.b() == 1) {
            b();
        }
    }
}
