package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.aa;
import com.ecjia.component.view.k;
import com.ecjia.consts.b;
import com.ecjia.hamster.adapter.cb;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.Timer;
import java.util.TimerTask;

public class ECJiaYueRechangeActivity extends a implements a {
    cb a;
    aa b;
    public Handler c;
    int d = 0;
    private ImageView e;
    private ImageView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private LinearLayout o;
    private SharedPreferences p;
    private Button q;
    private EditText r;
    private ListView s;

    class ECJiaYueRechangeActivity_1 extends Handler {
        final /* synthetic */ ECJiaYueRechangeActivity a;

        ECJiaYueRechangeActivity_1(ECJiaYueRechangeActivity eCJiaYueRechangeActivity) {
            this.a = eCJiaYueRechangeActivity;
        }

        public void handleMessage(Message message) {
            this.a.d = message.arg1;
            if (this.a.h.g.size() != 0) {
                this.a.n.setText(((ECJia_PAYMENT) this.a.h.g.get(this.a.d)).getPay_name());
                this.a.s.setVisibility(8);
                this.a.k.setBackgroundResource(R.drawable.search_showchild);
            }
        }
    }

    class ECJiaYueRechangeActivity_2 extends TimerTask {
        final /* synthetic */ ECJiaYueRechangeActivity a;

        ECJiaYueRechangeActivity_2(ECJiaYueRechangeActivity eCJiaYueRechangeActivity) {
            this.a = eCJiaYueRechangeActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.r.getContext().getSystemService("input_method")).showSoftInput(this.a.r, 0);
        }
    }

    class ECJiaYueRechangeActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaYueRechangeActivity a;

        ECJiaYueRechangeActivity_3(ECJiaYueRechangeActivity eCJiaYueRechangeActivity) {
            this.a = eCJiaYueRechangeActivity;
        }

        public void onClick(View view) {
            if (this.a.s.getVisibility() == 0) {
                this.a.s.setVisibility(8);
                this.a.k.setBackgroundResource(R.drawable.search_showchild);
            } else if (this.a.s.getVisibility() == 8) {
                this.a.s.setVisibility(0);
                this.a.k.setBackgroundResource(R.drawable.search_hidden);
            }
        }
    }

    class ECJiaYueRechangeActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaYueRechangeActivity a;

        ECJiaYueRechangeActivity_4(ECJiaYueRechangeActivity eCJiaYueRechangeActivity) {
            this.a = eCJiaYueRechangeActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_yue_rechange);
        PushAgent.getInstance(this).onAppStart();
        this.p = getSharedPreferences(Constants.KEY_USER_ID, 0);
        b();
    }

    void b() {
        this.c = new ECJiaYueRechangeActivity_1(this);
        this.b = new aa(this);
        this.b.a((a) this);
        final Resources a = b.a((Context) this);
        this.l = (TextView) findViewById(R.id.top_view_text);
        this.e = (ImageView) findViewById(R.id.top_view_back);
        this.o = (LinearLayout) findViewById(R.id.yue_choosetype);
        this.n = (TextView) findViewById(R.id.yue_choosedtype);
        if (this.h.g.size() == 0 || this.h.g == null) {
            this.n.setText(R.string.yue_nopayment);
        } else {
            this.n.setText(((ECJia_PAYMENT) this.h.g.get(this.d)).getPay_name());
        }
        this.m = (TextView) findViewById(R.id.yue_username);
        this.k = (ImageView) findViewById(R.id.yue_showlist);
        this.q = (Button) findViewById(R.id.rechange_ok);
        this.r = (EditText) findViewById(R.id.yue_money);
        this.r.setFocusable(true);
        this.r.setFocusableInTouchMode(true);
        this.r.requestFocus();
        new Timer().schedule(new ECJiaYueRechangeActivity_2(this), 300);
        this.s = (ListView) findViewById(R.id.yue_listview);
        this.a = new cb(this, this.h.g, this.c);
        this.a.a = this.c;
        this.s.setAdapter(this.a);
        this.s.setVisibility(8);
        this.m.setText(this.p.getString(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME, ""));
        this.o.setOnClickListener(new ECJiaYueRechangeActivity_3(this));
        this.e.setOnClickListener(new ECJiaYueRechangeActivity_4(this));
        this.l.setText(a.getString(R.string.yue_rechange));
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ECJiaYueRechangeActivity b;

            public void onClick(View view) {
                k kVar;
                if ("".equals(this.b.r.getText().toString())) {
                    kVar = new k(this.b, a.getString(R.string.yue_inputnotnull));
                    kVar.a(17);
                    kVar.a();
                } else if (this.b.h.g.size() > 0) {
                    this.b.b.a(this.b.r.getText().toString(), "", ((ECJia_PAYMENT) this.b.h.g.get(this.b.d)).getPay_id(), "");
                } else {
                    kVar = new k(this.b, a.getString(R.string.yue_nopayment));
                    kVar.a(17);
                    kVar.a();
                }
            }
        });
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/account/deposit")) {
            Intent intent = new Intent(this, ECJiaChoosePayActivity.class);
            intent.putExtra("pay_type", "account_id");
            intent.putExtra("account_id", this.b.d);
            intent.putExtra("pay_is_create", true);
            intent.putExtra("pay_body", "余额充值");
            intent.putExtra("pay_amount", this.r.getText().toString());
            intent.putExtra("pay_id", this.b.e);
            startActivity(intent);
        }
    }
}
