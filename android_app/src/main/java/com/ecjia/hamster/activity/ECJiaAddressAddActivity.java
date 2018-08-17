package com.ecjia.hamster.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.c;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaAddressAddActivity extends a implements a {
    ImageView a;
    private Button b;
    private EditText c;
    private EditText d;
    private EditText e;
    private EditText k;
    private EditText l;
    private LinearLayout m;
    private TextView n;
    private EditText o;
    private String p;
    private String q;
    private String r;
    private String s;
    private c t;
    private boolean u;

    class ECJiaAddressAddActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaAddressAddActivity a;

        ECJiaAddressAddActivity_1(ECJiaAddressAddActivity eCJiaAddressAddActivity) {
            this.a = eCJiaAddressAddActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaLocationPOIActivity.class), 2);
        }
    }

    class ECJiaAddressAddActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaAddressAddActivity a;

        ECJiaAddressAddActivity_2(ECJiaAddressAddActivity eCJiaAddressAddActivity) {
            this.a = eCJiaAddressAddActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaAddressChooseActivity.class), 1);
            this.a.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        }
    }

    class ECJiaAddressAddActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaAddressAddActivity a;

        ECJiaAddressAddActivity_3(ECJiaAddressAddActivity eCJiaAddressAddActivity) {
            this.a = eCJiaAddressAddActivity;
        }

        public void onClick(View view) {
            String obj = this.a.c.getText().toString();
            this.a.d.getText().toString();
            String obj2 = this.a.e.getText().toString();
            String obj3 = this.a.k.getText().toString();
            String obj4 = this.a.l.getText().toString();
            String obj5 = this.a.o.getText().toString();
            Resources resources = this.a.getBaseContext().getResources();
            String string = resources.getString(R.string.add_name);
            String string2 = resources.getString(R.string.add_tel);
            resources.getString(R.string.add_email);
            resources.getString(R.string.add_correct_email);
            String string3 = resources.getString(R.string.add_address);
            String string4 = resources.getString(R.string.confirm_address);
            resources.getString(R.string.address_zipcode_notnull);
            String string5 = resources.getString(R.string.address_name_toolong);
            String string6 = resources.getString(R.string.address_mobile_false);
            resources.getString(R.string.address_zipcode_false);
            resources.getString(R.string.address_email_false);
            k kVar;
            if ("".equals(obj)) {
                kVar = new k(this.a, string);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (obj.length() > 15) {
                kVar = new k(this.a, string5);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if ("".equals(obj3)) {
                kVar = new k(this.a, string2);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (obj3.length() != 11) {
                kVar = new k(this.a, string6);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (this.a.p == null || this.a.q == null || this.a.r == null || this.a.s == null) {
                kVar = new k(this.a, string4);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if ("".equals(obj5)) {
                kVar = new k(this.a, string3);
                kVar.a(17, 0, 0);
                kVar.a();
            } else {
                this.a.t = new c(this.a);
                this.a.t.a(this.a);
                this.a.t.a(obj, obj3, obj2, obj3, obj4, obj5, this.a.p, this.a.q, this.a.r, this.a.s);
            }
        }
    }

    class ECJiaAddressAddActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaAddressAddActivity a;

        ECJiaAddressAddActivity_4(ECJiaAddressAddActivity eCJiaAddressAddActivity) {
            this.a = eCJiaAddressAddActivity;
        }

        public void onClick(View view) {
            this.a.setResult(0, new Intent());
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_address_add);
        b();
        this.u = getIntent().getBooleanExtra("isfirst", false);
    }

    void b() {
        a();
        this.b = (Button) findViewById(R.id.address_add_submit);
        this.c = (EditText) findViewById(R.id.add_address_name);
        this.k = (EditText) findViewById(R.id.add_address_phoneNum);
        this.m = (LinearLayout) findViewById(R.id.add_address_area);
        this.n = (TextView) findViewById(R.id.add_address_address);
        this.o = (EditText) findViewById(R.id.add_address_detail);
        this.a = (ImageView) findViewById(R.id.localtion);
        this.a.setOnClickListener(new ECJiaAddressAddActivity_1(this));
        this.m.setOnClickListener(new ECJiaAddressAddActivity_2(this));
        this.d = (EditText) findViewById(R.id.add_address_telNum);
        this.e = (EditText) findViewById(R.id.add_address_email);
        this.l = (EditText) findViewById(R.id.add_address_zipCode);
        this.b.setOnClickListener(new ECJiaAddressAddActivity_3(this));
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
                stringBuffer.append(intent.getStringExtra("country_name") + " ");
                stringBuffer.append(intent.getStringExtra("province_name") + " ");
                stringBuffer.append(intent.getStringExtra("city_name") + " ");
                stringBuffer.append(intent.getStringExtra("county_name"));
                this.n.setText(stringBuffer.toString());
            }
        } else if (i == 2 && i2 == -1) {
            this.o.setText(intent.getStringExtra("address_address") + intent.getStringExtra("address_name"));
        }
    }

    public void a() {
        this.i = (ECJiaTopView) findViewById(R.id.add_address_topview);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaAddressAddActivity_4(this));
        this.i.setTitleText((int) R.string.manage_add_address);
        this.i.setRightType(13);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7, com.ecjia.hamster.model.ax r8) {
        /*
        r5 = this;
        r4 = 2131099705; // 0x7f060039 float:1.781177E38 double:1.052903152E-314;
        r1 = 0;
        r2 = -1;
        r3 = 1;
        r0 = r6.hashCode();
        switch(r0) {
            case -1378396154: goto L_0x0012;
            case 219725273: goto L_0x001c;
            case 936006778: goto L_0x0026;
            default: goto L_0x000d;
        };
    L_0x000d:
        r0 = r2;
    L_0x000e:
        switch(r0) {
            case 0: goto L_0x0030;
            case 1: goto L_0x0061;
            case 2: goto L_0x0099;
            default: goto L_0x0011;
        };
    L_0x0011:
        return;
    L_0x0012:
        r0 = "address/add";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000d;
    L_0x001a:
        r0 = r1;
        goto L_0x000e;
    L_0x001c:
        r0 = "address/list";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000d;
    L_0x0024:
        r0 = r3;
        goto L_0x000e;
    L_0x0026:
        r0 = "address/setDefault";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000d;
    L_0x002e:
        r0 = 2;
        goto L_0x000e;
    L_0x0030:
        r0 = r8.b();
        if (r0 != r3) goto L_0x0054;
    L_0x0036:
        r0 = r5.u;
        if (r0 == 0) goto L_0x0040;
    L_0x003a:
        r0 = r5.t;
        r0.a();
        goto L_0x0011;
    L_0x0040:
        r0 = new com.ecjia.component.view.k;
        r0.<init>(r5, r4);
        r0.a();
        r0 = new android.content.Intent;
        r0.<init>();
        r5.setResult(r2, r0);
        r5.finish();
        goto L_0x0011;
    L_0x0054:
        r0 = new com.ecjia.component.view.k;
        r1 = r8.d();
        r0.<init>(r5, r1);
        r0.a();
        goto L_0x0011;
    L_0x0061:
        r0 = r8.b();
        if (r0 != r3) goto L_0x0011;
    L_0x0067:
        r0 = r5.t;
        r0 = r0.a;
        r0 = r0.size();
        if (r0 <= 0) goto L_0x0011;
    L_0x0071:
        r2 = r5.t;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = "";
        r3 = r0.append(r3);
        r0 = r5.t;
        r0 = r0.a;
        r0 = r0.get(r1);
        r0 = (com.ecjia.hamster.model.ECJia_ADDRESS) r0;
        r0 = r0.getId();
        r0 = r3.append(r0);
        r0 = r0.toString();
        r2.c(r0);
        goto L_0x0011;
    L_0x0099:
        r0 = r8.b();
        if (r0 != r3) goto L_0x0011;
    L_0x009f:
        r0 = new com.ecjia.component.view.k;
        r0.<init>(r5, r4);
        r0.a();
        r0 = new android.content.Intent;
        r0.<init>();
        r5.setResult(r2, r0);
        r5.finish();
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.hamster.activity.ECJiaAddressAddActivity.a(java.lang.String, java.lang.String, com.ecjia.hamster.model.ax):void");
    }
}
