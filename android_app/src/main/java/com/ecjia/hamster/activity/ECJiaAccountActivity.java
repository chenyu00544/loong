package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.al;
import com.ecjia.component.view.k;
import com.ecjia.consts.b;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaAccountActivity extends a implements a {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private Button e;
    private Button k;
    private al l;

    class ECJiaAccountActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaAccountActivity a;

        ECJiaAccountActivity_1(ECJiaAccountActivity eCJiaAccountActivity) {
            this.a = eCJiaAccountActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaAccountActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaAccountActivity a;

        ECJiaAccountActivity_2(ECJiaAccountActivity eCJiaAccountActivity) {
            this.a = eCJiaAccountActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaYueRechangeActivity.class));
        }
    }

    class ECJiaAccountActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaAccountActivity a;

        ECJiaAccountActivity_3(ECJiaAccountActivity eCJiaAccountActivity) {
            this.a = eCJiaAccountActivity;
        }

        public void onClick(View view) {
            Intent intent;
            if (this.a.h.e().t() == 0) {
                new k(this.a, (int) R.string.real_name_verify_bind).a();
                intent = new Intent(this.a, ECJiaBindingPhoneActivity.class);
                intent.putExtra("type", "user_modify_mobile");
                this.a.startActivityForResult(intent, 110);
            } else if (this.a.h.e().s() == 3) {
                this.a.startActivity(new Intent(this.a, ECJiaWithdrawalActivity.class));
            } else if (this.a.h.e().s() == 2) {
                new k(this.a, (int) R.string.withdraw_real_name_verify).a();
                intent = new Intent(this.a, ECJiaVerifyScheduleActivity.class);
                intent.putExtra("real_id", this.a.h.e().u().a());
                this.a.startActivity(intent);
            } else {
                new k(this.a, (int) R.string.withdraw_real_name_verify).a();
                this.a.startActivity(new Intent(this.a, ECJiaRealNameVerifyActivity.class));
            }
        }
    }

    class ECJiaAccountActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaAccountActivity a;

        ECJiaAccountActivity_4(ECJiaAccountActivity eCJiaAccountActivity) {
            this.a = eCJiaAccountActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaRechargeHistoryActivity.class));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_account);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        this.l = new al(this);
        this.l.a((a) this);
        this.l.a();
        c_();
    }

    void c_() {
        Resources a = b.a((Context) this);
        this.b = (TextView) findViewById(R.id.top_view_text);
        this.a = (ImageView) findViewById(R.id.top_view_back);
        this.c = (TextView) findViewById(R.id.account_money);
        this.d = (TextView) findViewById(R.id.top_right_save);
        this.d.setText(a.getString(R.string.accoubt_record));
        this.d.setVisibility(0);
        this.e = (Button) findViewById(R.id.account_rechange);
        this.k = (Button) findViewById(R.id.account_withdraw);
        this.b.setText(a.getString(R.string.account_user));
        this.a.setOnClickListener(new ECJiaAccountActivity_1(this));
        this.e.setOnClickListener(new ECJiaAccountActivity_2(this));
        this.k.setOnClickListener(new ECJiaAccountActivity_3(this));
        this.d.setOnClickListener(new ECJiaAccountActivity_4(this));
        b();
    }

    private void b() {
        if (this.h.e() != null && !TextUtils.isEmpty(this.h.e().m())) {
            this.c.setText(this.h.e().i());
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a().b(this);
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        if ("changed".equals(bVar.c())) {
            this.c.setText(this.h.e().i());
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 110:
                this.l.a();
                return;
            default:
                return;
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/info") && axVar.b() == 1) {
            b();
        }
    }
}
