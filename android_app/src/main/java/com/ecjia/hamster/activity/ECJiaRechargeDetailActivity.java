package com.ecjia.hamster.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.q;
import com.ecjia.a.u;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.aa;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.c;
import com.ecjia.hamster.model.aj;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaRechargeDetailActivity extends a implements a {
    SharedPreferences a;
    aa b;
    c c;
    Resources d;
    private aj e;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private Button r;
    private Button s;
    private Button t;
    private LinearLayout u;
    private LinearLayout v;
    private LinearLayout w;
    private TextView x;
    private ImageView y;
    private Bitmap z;

    class ECJiaRechargeDetailActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaRechargeDetailActivity a;

        class ECJiaRechargeDetailActivity_1_1 implements OnClickListener {
            final /* synthetic */ ECJiaRechargeDetailActivity_1 a;

            ECJiaRechargeDetailActivity_1_1(ECJiaRechargeDetailActivity_1 eCJiaRechargeDetailActivity_1) {
                this.a = eCJiaRechargeDetailActivity_1;
            }

            public void onClick(View view) {
                this.a.a.c.b();
                this.a.a.b.c(this.a.a.e.a());
            }
        }

        class ECJiaRechargeDetailActivity_1_2 implements OnClickListener {
            final /* synthetic */ ECJiaRechargeDetailActivity_1 a;

            ECJiaRechargeDetailActivity_1_2(ECJiaRechargeDetailActivity_1 eCJiaRechargeDetailActivity_1) {
                this.a = eCJiaRechargeDetailActivity_1;
            }

            public void onClick(View view) {
                this.a.a.c.b();
            }
        }

        ECJiaRechargeDetailActivity_1(ECJiaRechargeDetailActivity eCJiaRechargeDetailActivity) {
            this.a = eCJiaRechargeDetailActivity;
        }

        public void onClick(View view) {
            this.a.c = new c(this.a, this.a.d.getString(R.string.point), this.a.d.getString(R.string.sure));
            this.a.c.a(2);
            this.a.c.b(new ECJiaRechargeDetailActivity_1_1(this));
            this.a.c.c(new ECJiaRechargeDetailActivity_1_2(this));
            this.a.c.a();
        }
    }

    class ECJiaRechargeDetailActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaRechargeDetailActivity a;

        ECJiaRechargeDetailActivity_2(ECJiaRechargeDetailActivity eCJiaRechargeDetailActivity) {
            this.a = eCJiaRechargeDetailActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a, ECJiaChoosePayActivity.class);
            intent.putExtra("pay_type", "account_id");
            intent.putExtra("account_id", this.a.e.a());
            intent.putExtra("pay_is_create", false);
            intent.putExtra("pay_body", "余额充值");
            intent.putExtra("pay_amount", this.a.e.b());
            intent.putExtra("pay_id", this.a.e.g());
            this.a.startActivity(intent);
        }
    }

    class ECJiaRechargeDetailActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaRechargeDetailActivity a;

        class ECJiaRechargeDetailActivity_3_1 implements OnClickListener {
            final /* synthetic */ ECJiaRechargeDetailActivity_3 a;

            ECJiaRechargeDetailActivity_3_1(ECJiaRechargeDetailActivity_3 eCJiaRechargeDetailActivity_3) {
                this.a = eCJiaRechargeDetailActivity_3;
            }

            public void onClick(View view) {
                this.a.a.c.b();
                this.a.a.b.c(this.a.a.e.a());
            }
        }

        class ECJiaRechargeDetailActivity_3_2 implements OnClickListener {
            final /* synthetic */ ECJiaRechargeDetailActivity_3 a;

            ECJiaRechargeDetailActivity_3_2(ECJiaRechargeDetailActivity_3 eCJiaRechargeDetailActivity_3) {
                this.a = eCJiaRechargeDetailActivity_3;
            }

            public void onClick(View view) {
                this.a.a.c.b();
            }
        }

        ECJiaRechargeDetailActivity_3(ECJiaRechargeDetailActivity eCJiaRechargeDetailActivity) {
            this.a = eCJiaRechargeDetailActivity;
        }

        public void onClick(View view) {
            this.a.c = new c(this.a, this.a.d.getString(R.string.point), this.a.d.getString(R.string.sure));
            this.a.c.a(2);
            this.a.c.b(new ECJiaRechargeDetailActivity_3_1(this));
            this.a.c.c(new ECJiaRechargeDetailActivity_3_2(this));
            this.a.c.a();
        }
    }

    class ECJiaRechargeDetailActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaRechargeDetailActivity a;

        ECJiaRechargeDetailActivity_4(ECJiaRechargeDetailActivity eCJiaRechargeDetailActivity) {
            this.a = eCJiaRechargeDetailActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_recharge_detail);
        de.greenrobot.event.c.a().a((Object) this);
        PushAgent.getInstance(this).onAppStart();
        b();
    }

    private void b() {
        this.d = getResources();
        try {
            this.e = aj.a(new JSONObject(getIntent().getStringExtra("data")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a();
        this.y = (ImageView) findViewById(R.id.recharge_profilephoto);
        this.k = (TextView) findViewById(R.id.tv_name);
        this.l = (TextView) findViewById(R.id.tv_type);
        this.m = (TextView) findViewById(R.id.tv_amount);
        this.n = (TextView) findViewById(R.id.payment_name);
        this.o = (TextView) findViewById(R.id.payment_type);
        this.p = (TextView) findViewById(R.id.add_time);
        this.q = (TextView) findViewById(R.id.tv_number);
        this.x = (TextView) findViewById(R.id.success_text);
        this.r = (Button) findViewById(R.id.btn_cancle);
        this.s = (Button) findViewById(R.id.btn_ok);
        this.t = (Button) findViewById(R.id.raply_cancel);
        this.u = (LinearLayout) findViewById(R.id.success_item);
        this.v = (LinearLayout) findViewById(R.id.needpay_item);
        this.w = (LinearLayout) findViewById(R.id.needcancel_item);
        this.a = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.z = u.a().b(this.a.getString("uid", ""));
        if (this.z != null) {
            this.y.setImageBitmap(this.z);
        } else {
            this.y.setImageResource(R.drawable.profile_no_avarta_icon_light);
        }
        this.k.setText(this.a.getString(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME, ""));
        this.l.setText(this.e.e());
        this.b = new aa(this);
        this.b.a((a) this);
        if ("deposit".equals(this.e.d())) {
            this.m.setText(SocializeConstants.OP_DIVIDER_PLUS + this.e.b());
            q.a("_____" + this.e.f());
            this.n.setText(this.e.f());
            this.w.setVisibility(8);
            if ("0".equals(this.e.h())) {
                this.u.setVisibility(8);
                this.v.setVisibility(0);
                this.r.setOnClickListener(new ECJiaRechargeDetailActivity_1(this));
                this.s.setOnClickListener(new ECJiaRechargeDetailActivity_2(this));
            } else {
                this.u.setVisibility(0);
                this.v.setVisibility(8);
                this.x.setText(this.e.i());
            }
        } else if ("raply".equals(this.e.d())) {
            this.m.setText(this.e.b());
            this.v.setVisibility(8);
            this.n.setText(this.d.getString(R.string.user_account));
            if ("0".equals(this.e.h())) {
                this.w.setVisibility(0);
                this.u.setVisibility(8);
                this.v.setVisibility(8);
                this.t.setOnClickListener(new ECJiaRechargeDetailActivity_3(this));
            } else {
                q.a("_____" + this.e.f());
                this.u.setVisibility(0);
                this.x.setText(this.e.i());
            }
        }
        this.o.setText(this.e.e());
        this.p.setText(this.e.c());
        this.q.setText(this.e.a());
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.recharge_detail_topview);
        this.i.setTitleText((int) R.string.accoubt_record_detail);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaRechargeDetailActivity_4(this));
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/account/cancel") && axVar.b() == 1) {
            de.greenrobot.event.c.a().c(new b("recharge_cancel"));
            finish();
        }
    }
}
