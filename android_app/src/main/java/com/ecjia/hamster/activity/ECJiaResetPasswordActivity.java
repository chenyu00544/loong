package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.j;
import com.ecjia.component.a.m;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;

public class ECJiaResetPasswordActivity extends a implements TextWatcher, a {
    private String a;
    private ImageView b;
    private EditText c;
    private EditText d;
    private Button e;
    private m k;
    private d l;
    private LinearLayout m;
    private CheckBox n;
    private CheckBox o;

    class ECJiaResetPasswordActivity_1 implements OnCheckedChangeListener {
        final /* synthetic */ ECJiaResetPasswordActivity a;

        ECJiaResetPasswordActivity_1(ECJiaResetPasswordActivity eCJiaResetPasswordActivity) {
            this.a = eCJiaResetPasswordActivity;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                this.a.c.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                this.a.c.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    class ECJiaResetPasswordActivity_2 implements OnCheckedChangeListener {
        final /* synthetic */ ECJiaResetPasswordActivity a;

        ECJiaResetPasswordActivity_2(ECJiaResetPasswordActivity eCJiaResetPasswordActivity) {
            this.a = eCJiaResetPasswordActivity;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                this.a.d.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                this.a.d.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    class ECJiaResetPasswordActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaResetPasswordActivity a;

        ECJiaResetPasswordActivity_3(ECJiaResetPasswordActivity eCJiaResetPasswordActivity) {
            this.a = eCJiaResetPasswordActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaResetPasswordActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaResetPasswordActivity a;

        ECJiaResetPasswordActivity_4(ECJiaResetPasswordActivity eCJiaResetPasswordActivity) {
            this.a = eCJiaResetPasswordActivity;
        }

        public void onClick(View view) {
            if (this.a.c.getText().toString().length() < 6) {
                new k(this.a, "密码长度不能少于6位").a();
            } else if (this.a.d.getText().toString().equals(this.a.c.getText().toString())) {
                this.a.k.b("mobile", this.a.a, this.a.c.getText().toString());
                this.a.l.show();
            } else {
                new k(this.a, "两次新密码不一致").a();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_reset_password);
        b();
    }

    private void b() {
        this.n = (CheckBox) findViewById(R.id.reset_show_pwd);
        this.o = (CheckBox) findViewById(R.id.reset_show_pwd2);
        this.n.setOnCheckedChangeListener(new ECJiaResetPasswordActivity_1(this));
        this.o.setOnCheckedChangeListener(new ECJiaResetPasswordActivity_2(this));
        this.m = (LinearLayout) findViewById(R.id.root_view);
        if (j.a().b != null) {
            this.m.setBackgroundDrawable(j.a().b);
        }
        this.l = d.a((Context) this);
        this.a = getIntent().getStringExtra("mobile");
        this.k = m.a();
        this.k.a((a) this);
        this.b = (ImageView) findViewById(R.id.reset_password_back);
        this.b.setOnClickListener(new ECJiaResetPasswordActivity_3(this));
        this.c = (EditText) findViewById(R.id.edit_password);
        this.d = (EditText) findViewById(R.id.edit_password2);
        this.c.addTextChangedListener(this);
        this.d.addTextChangedListener(this);
        this.e = (Button) findViewById(R.id.reset_password_sure);
        this.e.setOnClickListener(new ECJiaResetPasswordActivity_4(this));
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() < 6 || this.d.getText().toString().length() < 6) {
            this.e.setEnabled(false);
            this.e.setTextColor(Color.parseColor("#ff999999"));
            this.e.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.e.setEnabled(true);
        this.e.setTextColor(Color.parseColor("#ffffffff"));
        this.e.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() < 6 || this.d.getText().toString().length() < 6) {
            this.e.setEnabled(false);
            this.e.setTextColor(Color.parseColor("#ff999999"));
            this.e.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.e.setEnabled(true);
        this.e.setTextColor(Color.parseColor("#ffffffff"));
        this.e.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void afterTextChanged(Editable editable) {
        if (this.c.getText().toString().length() < 6 || this.d.getText().toString().length() < 6) {
            this.e.setEnabled(false);
            this.e.setTextColor(Color.parseColor("#ff999999"));
            this.e.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.e.setEnabled(true);
        this.e.setTextColor(Color.parseColor("#ffffffff"));
        this.e.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/reset_password")) {
            this.l.dismiss();
            Intent intent = new Intent(this, ECJiaLoginActivity.class);
            intent.setFlags(67108864);
            startActivity(intent);
            overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
            finish();
        }
    }
}
