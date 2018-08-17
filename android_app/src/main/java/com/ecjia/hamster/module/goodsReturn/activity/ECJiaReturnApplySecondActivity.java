package com.ecjia.hamster.module.goodsReturn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.ECJiaAddressChooseActivity;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import de.greenrobot.event.c;
import java.util.ArrayList;

public class ECJiaReturnApplySecondActivity extends a implements com.ecjia.component.a.a.a {
    TextView a;
    EditText b;
    EditText c;
    EditText d;
    String e;
    String k;
    String l;
    String m;
    ArrayList<String> n;
    ECJia_ADDRESS o;
    String p = "";
    String q = "";
    String r = "";
    String s = "";
    private com.ecjia.hamster.module.goodsReturn.a t;
    private String u;

    class ECJiaReturnApplySecondActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplySecondActivity a;

        ECJiaReturnApplySecondActivity_1(ECJiaReturnApplySecondActivity eCJiaReturnApplySecondActivity) {
            this.a = eCJiaReturnApplySecondActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaAddressChooseActivity.class), 0);
        }
    }

    class ECJiaReturnApplySecondActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplySecondActivity a;

        ECJiaReturnApplySecondActivity_2(ECJiaReturnApplySecondActivity eCJiaReturnApplySecondActivity) {
            this.a = eCJiaReturnApplySecondActivity;
        }

        public void onClick(View view) {
            if (this.a.e()) {
                this.a.f();
            }
        }
    }

    class ECJiaReturnApplySecondActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplySecondActivity a;

        ECJiaReturnApplySecondActivity_3(ECJiaReturnApplySecondActivity eCJiaReturnApplySecondActivity) {
            this.a = eCJiaReturnApplySecondActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_return_address);
        c();
        b();
    }

    private void b() {
        this.o = (ECJia_ADDRESS) getIntent().getSerializableExtra("address");
        if (this.o != null) {
            this.a.setText(this.o.getCity_name() + " " + this.o.getProvince_name() + " " + this.o.getCity_name() + " " + this.o.getDistrict_name());
            this.r = this.o.getCity();
            this.q = this.o.getProvince();
            this.r = this.o.getCity();
            this.s = this.o.getDistrict();
            this.b.setText(this.o.getAddress());
            this.c.setText(this.o.getConsignee());
            this.d.setText(this.o.getMobile());
        }
        this.e = getIntent().getStringExtra("return_type");
        this.k = getIntent().getStringExtra("rec_id");
        this.l = getIntent().getStringExtra("return_reason");
        this.m = getIntent().getStringExtra("number");
        this.u = getIntent().getStringExtra("return_description");
        this.n = (ArrayList) getIntent().getSerializableExtra("return_images");
    }

    private void c() {
        a();
        findViewById(R.id.address_area_ll).setOnClickListener(new ECJiaReturnApplySecondActivity_1(this));
        this.a = (TextView) findViewById(R.id.address_area);
        this.b = (EditText) findViewById(R.id.address_detail);
        this.c = (EditText) findViewById(R.id.receiver_name);
        this.d = (EditText) findViewById(R.id.receiver_phone);
        findViewById(R.id.address_submit).setOnClickListener(new ECJiaReturnApplySecondActivity_2(this));
    }

    private boolean e() {
        if (TextUtils.isEmpty(this.a.getText().toString())) {
            new k((Context) this, "请选择收货地址").a();
            return false;
        } else if (TextUtils.isEmpty(this.b.getText().toString())) {
            new k((Context) this, "请填写详细地址").a();
            return false;
        } else if (TextUtils.isEmpty(this.c.getText().toString())) {
            new k((Context) this, "请填写联系人").a();
            return false;
        } else if (TextUtils.isEmpty(this.d.getText().toString())) {
            new k((Context) this, "请填写联系电话").a();
            return false;
        } else if (this.d.getText().length() == 11) {
            return true;
        } else {
            new k((Context) this, "请填写正确的联系电话").a();
            return false;
        }
    }

    private void f() {
        this.o = new ECJia_ADDRESS();
        this.o.setConsignee(this.c.getText().toString());
        this.o.setMobile(this.d.getText().toString());
        this.o.setCountry(this.p);
        this.o.setProvince(this.q);
        this.o.setCity(this.r);
        this.o.setDistrict(this.s);
        this.o.setAddress(this.b.getText().toString());
        if (this.t == null) {
            this.t = new com.ecjia.hamster.module.goodsReturn.a(this);
            this.t.a((com.ecjia.component.a.a.a) this);
        }
        this.t.a(this.e, this.k, this.l, this.u, this.m, this.n, this.o);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.return_address_topview);
        this.i.setTitleType(TitleType.TEXT);
        this.i.setTitleText((int) R.string.return_confirm_address);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaReturnApplySecondActivity_3(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 0 && i2 == -1 && intent != null) {
            this.p = "";
            this.q = "";
            this.r = "";
            this.s = "";
            this.p = intent.getStringExtra("country_id");
            this.q = intent.getStringExtra("province_id");
            this.r = intent.getStringExtra("city_id");
            this.s = intent.getStringExtra("county_id");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(intent.getStringExtra("country_name") + " ");
            stringBuffer.append(intent.getStringExtra("province_name") + " ");
            stringBuffer.append(intent.getStringExtra("city_name") + " ");
            stringBuffer.append(intent.getStringExtra("county_name"));
            this.a.setText(stringBuffer.toString());
        }
    }

    public void a(String str, String str2, ax axVar) {
        if ("order/return/apply".equals(str) && axVar.b() == 1) {
            c.a().c(new b("RETURN_APPLY_SUCCESS"));
            finish();
            Intent intent = new Intent(this, ECJiaReturnSuccessActivity.class);
            intent.putExtra("return_type", this.e);
            intent.putExtra("return_id", this.t.b);
            intent.putExtra("apply_time", this.t.a);
            startActivity(intent);
        }
    }
}
