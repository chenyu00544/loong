package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.c;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bt;
import com.ecjia.hamster.model.ak;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaAddressChooseActivity extends a implements a {
    private d A;
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView k;
    private TextView l;
    private TextView m;
    private ListView n;
    private bt o;
    private c p;
    private int q = 0;
    private String r = "";
    private String s = "";
    private String t = "";
    private String u = "";
    private String v = "";
    private String w = "";
    private String x = "";
    private String y = "";
    private String z;

    class ECJiaAddressChooseActivity_1 implements OnKeyListener {
        final /* synthetic */ ECJiaAddressChooseActivity a;

        ECJiaAddressChooseActivity_1(ECJiaAddressChooseActivity eCJiaAddressChooseActivity) {
            this.a = eCJiaAddressChooseActivity;
        }

        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 1) {
                this.a.A.dismiss();
                this.a.finish();
            }
            return false;
        }
    }

    class ECJiaAddressChooseActivity_2 implements OnItemClickListener {
        final /* synthetic */ ECJiaAddressChooseActivity a;

        ECJiaAddressChooseActivity_2(ECJiaAddressChooseActivity eCJiaAddressChooseActivity) {
            this.a = eCJiaAddressChooseActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.A.show();
            this.a.n.setEnabled(false);
            if (this.a.q == 1) {
                this.a.r = ((ak) this.a.p.b.get(i)).a();
                this.a.v = ((ak) this.a.p.b.get(i)).b();
                this.a.e.setText(((ak) this.a.p.b.get(i)).b());
            } else if (this.a.q == 2) {
                this.a.s = ((ak) this.a.p.b.get(i)).a();
                this.a.w = ((ak) this.a.p.b.get(i)).b();
                this.a.k.setText(((ak) this.a.p.b.get(i)).b());
            } else if (this.a.q == 3) {
                this.a.t = ((ak) this.a.p.b.get(i)).a();
                this.a.x = ((ak) this.a.p.b.get(i)).b();
                this.a.l.setText(((ak) this.a.p.b.get(i)).b());
            } else if (this.a.q == 4) {
                this.a.u = ((ak) this.a.p.b.get(i)).a();
                this.a.y = ((ak) this.a.p.b.get(i)).b();
                this.a.m.setText(((ak) this.a.p.b.get(i)).b());
            }
            this.a.p.a(((ak) this.a.p.b.get(i)).a(), this.a.q);
        }
    }

    class ECJiaAddressChooseActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaAddressChooseActivity a;

        ECJiaAddressChooseActivity_3(ECJiaAddressChooseActivity eCJiaAddressChooseActivity) {
            this.a = eCJiaAddressChooseActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaAddressChooseActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaAddressChooseActivity a;

        ECJiaAddressChooseActivity_4(ECJiaAddressChooseActivity eCJiaAddressChooseActivity) {
            this.a = eCJiaAddressChooseActivity;
        }

        public void onClick(View view) {
            if (this.a.q != 0) {
                if (this.a.q == 1) {
                    this.a.finish();
                }
                this.a.b.setText(this.a.z);
                this.a.e.setText(R.string.address2_no_choosed);
                this.a.k.setText(null);
                this.a.l.setText(null);
                this.a.m.setText(null);
                this.a.q = 0;
                this.a.A.show();
                this.a.p.a("0", this.a.q);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.address_b);
        PushAgent.getInstance(this).onAppStart();
        this.A = d.a((Context) this);
        this.A.setCanceledOnTouchOutside(false);
        this.A.setOnKeyListener(new ECJiaAddressChooseActivity_1(this));
        this.a = (ImageView) findViewById(R.id.address_title_back);
        this.b = (TextView) findViewById(R.id.address_title);
        this.c = (TextView) findViewById(R.id.address_title_cancle);
        this.d = (TextView) findViewById(R.id.address_choosed_title);
        this.e = (TextView) findViewById(R.id.address_choosed_area);
        this.k = (TextView) findViewById(R.id.address_choosed_area1);
        this.l = (TextView) findViewById(R.id.address_choosed_area2);
        this.m = (TextView) findViewById(R.id.address_choosed_area3);
        this.n = (ListView) findViewById(R.id.address_list);
        this.z = getBaseContext().getResources().getString(R.string.addressb_country);
        this.b.setText(this.z);
        this.d.setText(R.string.address2_choosed_area);
        this.e.setText(R.string.address2_no_choosed);
        this.p = new c(this);
        this.p.a((a) this);
        this.p.a("0", this.q);
        this.n.setOnItemClickListener(new ECJiaAddressChooseActivity_2(this));
        this.a.setOnClickListener(new ECJiaAddressChooseActivity_3(this));
        this.c.setOnClickListener(new ECJiaAddressChooseActivity_4(this));
    }

    public void b() {
        Resources resources = getBaseContext().getResources();
        CharSequence string = resources.getString(R.string.select_province);
        CharSequence string2 = resources.getString(R.string.select_city);
        CharSequence string3 = resources.getString(R.string.select_area);
        if (this.p.b.size() == 0) {
            Intent intent = new Intent();
            intent.putExtra("country_id", this.r);
            intent.putExtra("province_id", this.s);
            intent.putExtra("city_id", this.t);
            intent.putExtra("county_id", this.u);
            intent.putExtra("country_name", this.v);
            intent.putExtra("province_name", this.w);
            intent.putExtra("city_name", this.x);
            intent.putExtra("county_name", this.y);
            setResult(-1, intent);
            finish();
            return;
        }
        this.q++;
        if (this.q == 2) {
            this.b.setText(string);
        } else if (this.q == 3) {
            this.b.setText(string2);
        } else if (this.q == 4) {
            this.b.setText(string3);
        }
        if (this.o == null) {
            this.o = new bt(this, this.p.b);
            this.n.setAdapter(this.o);
            return;
        }
        this.o.notifyDataSetChanged();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }

    public void a(String str, String str2, ax axVar) {
        if (!"shop/region".equals(str)) {
            return;
        }
        if (axVar.b() == 1) {
            if (this.q == axVar.a()) {
                b();
                this.A.dismiss();
                this.n.setEnabled(true);
            }
        } else if (axVar.b() == 0 && this.q == axVar.a()) {
            this.A.dismiss();
            this.n.setEnabled(true);
            new k((Context) this, this.g.getString(R.string.error_network)).a();
        }
    }
}
