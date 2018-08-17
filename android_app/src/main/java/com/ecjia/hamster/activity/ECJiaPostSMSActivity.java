package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.j;
import com.ecjia.component.a.m;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;

public class ECJiaPostSMSActivity extends a implements TextWatcher, com.ecjia.component.a.a.a {
    d a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private EditText e;
    private Button k;
    private a l;
    private String m;
    private m n;
    private String o;
    private LinearLayout p;

    class ECJiaPostSMSActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaPostSMSActivity a;

        ECJiaPostSMSActivity_1(ECJiaPostSMSActivity eCJiaPostSMSActivity) {
            this.a = eCJiaPostSMSActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaPostSMSActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaPostSMSActivity a;

        ECJiaPostSMSActivity_2(ECJiaPostSMSActivity eCJiaPostSMSActivity) {
            this.a = eCJiaPostSMSActivity;
        }

        public void onClick(View view) {
            this.a.l.start();
            this.a.n.b("mobile", this.a.m);
            this.a.a.show();
        }
    }

    class ECJiaPostSMSActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaPostSMSActivity a;

        ECJiaPostSMSActivity_3(ECJiaPostSMSActivity eCJiaPostSMSActivity) {
            this.a = eCJiaPostSMSActivity;
        }

        public void onClick(View view) {
            this.a.o = this.a.e.getText().toString();
            if (this.a.o.length() == 6) {
                this.a.n.a("mobile", this.a.m, this.a.o);
                return;
            }
            k kVar = new k(this.a, this.a.g.getString(R.string.register_wrong_code));
            kVar.a(17, 0, 0);
            kVar.a();
        }
    }

    class a extends CountDownTimer {
        final /* synthetic */ ECJiaPostSMSActivity a;

        public a(ECJiaPostSMSActivity eCJiaPostSMSActivity, long j, long j2) {
            this.a = eCJiaPostSMSActivity;
            super(j, j2);
        }

        public void onTick(long j) {
            this.a.d.setBackgroundResource(R.drawable.shape_unable);
            this.a.d.setTextColor(Color.parseColor("#ff999999"));
            this.a.d.setClickable(false);
            this.a.d.setText(this.a.g.getString(R.string.register_resend) + "(" + (j / 1000) + ")");
        }

        public void onFinish() {
            this.a.d.setText(this.a.g.getString(R.string.register_resend));
            this.a.d.setClickable(true);
            this.a.d.setTextColor(Color.parseColor("#ffffff"));
            this.a.d.setBackgroundResource(R.drawable.selector_get_code);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_post_sms);
        b();
    }

    private void b() {
        this.p = (LinearLayout) findViewById(R.id.root_view);
        if (j.a().b != null) {
            this.p.setBackgroundDrawable(j.a().b);
        }
        this.a = d.a((Context) this);
        this.m = getIntent().getStringExtra("mobile");
        this.n = m.a();
        this.n.a((com.ecjia.component.a.a.a) this);
        this.b = (ImageView) findViewById(R.id.back);
        this.b.setOnClickListener(new ECJiaPostSMSActivity_1(this));
        this.c = (TextView) findViewById(R.id.getpassword_codecheck_attention);
        String string = this.g.getString(R.string.register_enter_recode);
        String substring = string.substring(0, 3);
        this.c.setText(substring + this.m + string.substring(3, string.length()));
        this.e = (EditText) findViewById(R.id.getpassword_codecheck_edit);
        this.e.addTextChangedListener(this);
        this.l = new a(this, 119900, 1000);
        this.l.start();
        this.d = (TextView) findViewById(R.id.getpassword_codecheck_time);
        this.d.setOnClickListener(new ECJiaPostSMSActivity_2(this));
        this.k = (Button) findViewById(R.id.getpassword_codecheck_next);
        this.k.setOnClickListener(new ECJiaPostSMSActivity_3(this));
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("validate/forget_password")) {
            this.a.dismiss();
            if (axVar.b() == 1) {
                Intent intent = new Intent(this, ECJiaResetPasswordActivity.class);
                intent.putExtra("mobile", this.m);
                startActivity(intent);
            }
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.e.getText().toString().length() == 6) {
            this.k.setEnabled(true);
            this.k.setTextColor(Color.parseColor("#ffffffff"));
            this.k.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.k.setEnabled(false);
        this.k.setTextColor(Color.parseColor("#ff999999"));
        this.k.setBackgroundResource(R.drawable.shape_unable);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.e.getText().toString().length() == 6) {
            this.k.setEnabled(true);
            this.k.setTextColor(Color.parseColor("#ffffffff"));
            this.k.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.k.setEnabled(false);
        this.k.setTextColor(Color.parseColor("#ff999999"));
        this.k.setBackgroundResource(R.drawable.shape_unable);
    }

    public void afterTextChanged(Editable editable) {
        if (this.e.getText().toString().length() == 6) {
            this.k.setEnabled(true);
            this.k.setTextColor(Color.parseColor("#ffffffff"));
            this.k.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.k.setEnabled(false);
        this.k.setTextColor(Color.parseColor("#ff999999"));
        this.k.setBackgroundResource(R.drawable.shape_unable);
    }
}
