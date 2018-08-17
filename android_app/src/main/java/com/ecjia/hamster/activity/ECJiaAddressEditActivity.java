package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.c;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaAddressEditActivity extends a implements a {
    private c a;
    private EditText b;
    private EditText c;
    private EditText d;
    private EditText e;
    private EditText k;
    private LinearLayout l;
    private TextView m;
    private EditText n;
    private CheckBox o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private LinearLayout u;
    private Button v;

    class ECJiaAddressEditActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaAddressEditActivity a;

        ECJiaAddressEditActivity_1(ECJiaAddressEditActivity eCJiaAddressEditActivity) {
            this.a = eCJiaAddressEditActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaLocationPOIActivity.class), 2);
        }
    }

    class ECJiaAddressEditActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaAddressEditActivity a;

        ECJiaAddressEditActivity_2(ECJiaAddressEditActivity eCJiaAddressEditActivity) {
            this.a = eCJiaAddressEditActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaAddressChooseActivity.class), 1);
            this.a.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        }
    }

    class ECJiaAddressEditActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaAddressEditActivity a;

        ECJiaAddressEditActivity_3(ECJiaAddressEditActivity eCJiaAddressEditActivity) {
            this.a = eCJiaAddressEditActivity;
        }

        public void onClick(View view) {
            String obj = this.a.b.getText().toString();
            this.a.c.getText().toString();
            String obj2 = this.a.d.getText().toString();
            String obj3 = this.a.e.getText().toString();
            String obj4 = this.a.k.getText().toString();
            String obj5 = this.a.n.getText().toString();
            Resources resources = this.a.getBaseContext().getResources();
            String string = resources.getString(R.string.add_name);
            resources.getString(R.string.add_tel);
            resources.getString(R.string.add_email);
            resources.getString(R.string.add_correct_email);
            String string2 = resources.getString(R.string.add_address);
            String string3 = resources.getString(R.string.confirm_address);
            resources.getString(R.string.address_zipcode_notnull);
            String string4 = resources.getString(R.string.address_name_toolong);
            String string5 = resources.getString(R.string.address_mobile_false);
            resources.getString(R.string.address_zipcode_false);
            k kVar;
            if ("".equals(obj)) {
                kVar = new k(this.a, string);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (obj.length() > 15) {
                kVar = new k(this.a, string4);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (obj3.length() != 11) {
                kVar = new k(this.a, string5);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (this.a.p == null || this.a.q == null || this.a.r == null || this.a.s == null) {
                kVar = new k(this.a, string3);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if ("".equals(obj5)) {
                kVar = new k(this.a, string2);
                kVar.a(17, 0, 0);
                kVar.a();
            } else {
                this.a.a.a(this.a.t, obj, obj3, obj2, obj3, obj4, obj5, this.a.p, this.a.q, this.a.r, this.a.s);
            }
        }
    }

    class ECJiaAddressEditActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaAddressEditActivity a;

        ECJiaAddressEditActivity_4(ECJiaAddressEditActivity eCJiaAddressEditActivity) {
            this.a = eCJiaAddressEditActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_address_edit);
        c();
    }

    public void b() {
        this.b.setText(this.a.h.getConsignee());
        this.c.setText(this.a.h.getTel());
        this.d.setText(this.a.h.getEmail());
        this.e.setText(this.a.h.getMobile());
        this.k.setText(this.a.h.getZipcode());
        this.n.setText(this.a.h.getAddress());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.a.h.getProvince_name() + " ");
        stringBuffer.append(this.a.h.getCity_name() + " ");
        stringBuffer.append(this.a.h.getDistrict_name());
        this.m.setText(stringBuffer.toString());
        findViewById(R.id.localtion).setOnClickListener(new ECJiaAddressEditActivity_1(this));
        if (this.a.h.getDefault_address() == 1) {
            this.u.setVisibility(8);
        } else {
            this.u.setVisibility(0);
        }
        this.p = this.a.h.getCountry();
        this.q = this.a.h.getProvince();
        this.r = this.a.h.getCity();
        this.s = this.a.h.getDistrict();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (intent != null) {
                this.p = intent.getStringExtra("country_id");
                this.q = intent.getStringExtra("province_id");
                this.r = intent.getStringExtra("city_id");
                this.s = intent.getStringExtra("county_id");
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(intent.getStringExtra("province_name") + " ");
                stringBuffer.append(intent.getStringExtra("city_name") + " ");
                stringBuffer.append(intent.getStringExtra("county_name"));
                this.m.setText(stringBuffer.toString());
            }
        } else if (i == 2 && i2 == -1) {
            this.n.setText(intent.getStringExtra("address_address") + intent.getStringExtra("address_name"));
        }
    }

    void c() {
        a();
        this.b = (EditText) findViewById(R.id.address_manage2_name);
        this.c = (EditText) findViewById(R.id.address_manage2_telNum);
        this.d = (EditText) findViewById(R.id.address_manage2_email);
        this.e = (EditText) findViewById(R.id.address_manage2_phoneNum);
        this.k = (EditText) findViewById(R.id.address_manage2_zipCode);
        this.l = (LinearLayout) findViewById(R.id.address_manage2_area);
        this.m = (TextView) findViewById(R.id.address_manage2_address);
        this.n = (EditText) findViewById(R.id.address_manage2_detail);
        this.o = (CheckBox) findViewById(R.id.address_manage2_default);
        this.v = (Button) findViewById(R.id.address_submit);
        this.u = (LinearLayout) findViewById(R.id.setdefault_item);
        this.l.setOnClickListener(new ECJiaAddressEditActivity_2(this));
        this.t = getIntent().getStringExtra("address_id");
        this.a = new c(this);
        this.a.a((a) this);
        this.a.b(this.t);
        this.v.setOnClickListener(new ECJiaAddressEditActivity_3(this));
    }

    public void a() {
        this.i = (ECJiaTopView) findViewById(R.id.address_edit_topview);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaAddressEditActivity_4(this));
        this.i.setTitleText((int) R.string.manage_edit_address);
    }

    public void a(String str, String str2, ax axVar) {
        if (str == "address/info" && axVar.b() == 1) {
            b();
        }
        if (str == "address/setDefault" && axVar.b() == 1) {
            k kVar = new k((Context) this, getBaseContext().getResources().getString(R.string.successful_operation));
            kVar.a(17, 0, 0);
            kVar.a();
            finish();
        }
        if (str != "address/update" || axVar.b() != 1) {
            return;
        }
        if (this.o.isChecked()) {
            this.a.c(this.t);
            return;
        }
        kVar = new k((Context) this, getBaseContext().getResources().getString(R.string.successful_operation));
        kVar.a(17, 0, 0);
        kVar.a();
        finish();
    }
}
