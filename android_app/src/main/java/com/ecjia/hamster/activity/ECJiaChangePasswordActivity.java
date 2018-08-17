package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.ecjia.a.a.b;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.al;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaChangePasswordActivity extends a implements a {
    private ImageView a;
    private EditText b;
    private EditText c;
    private EditText d;
    private Button e;
    private al k;

    class ECJiaChangePasswordActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaChangePasswordActivity a;

        ECJiaChangePasswordActivity_1(ECJiaChangePasswordActivity eCJiaChangePasswordActivity) {
            this.a = eCJiaChangePasswordActivity;
        }

        public void onClick(View view) {
            this.a.b();
            this.a.finish();
        }
    }

    class ECJiaChangePasswordActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaChangePasswordActivity a;

        ECJiaChangePasswordActivity_2(ECJiaChangePasswordActivity eCJiaChangePasswordActivity) {
            this.a = eCJiaChangePasswordActivity;
        }

        public void onClick(View view) {
            if (TextUtils.isEmpty(this.a.b.getText().toString())) {
                new k(this.a, (int) R.string.origin_pwd_cannot_be_empty).a();
            } else if (this.a.c.getText().toString().length() < 6) {
                new k(this.a, (int) R.string.register_pwd_tooshort).a();
            } else if (this.a.b.getText().toString().equals(this.a.c.getText().toString())) {
                new k(this.a, (int) R.string.new_old_pwd_cannot_be_same).a();
            } else if (this.a.d.getText().toString().equals(this.a.c.getText().toString())) {
                this.a.b();
                this.a.k.b(this.a.b.getText().toString(), this.a.c.getText().toString());
            } else {
                new k(this.a, (int) R.string.password_not_match).a();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.change_password);
        PushAgent.getInstance(this).onAppStart();
        this.k = new al(this);
        this.k.a((a) this);
        this.a = (ImageView) findViewById(R.id.change_password_back);
        this.b = (EditText) findViewById(R.id.change_password_old);
        this.c = (EditText) findViewById(R.id.change_password_new);
        this.d = (EditText) findViewById(R.id.change_password_new2);
        this.e = (Button) findViewById(R.id.change_password_sure);
        this.a.setOnClickListener(new ECJiaChangePasswordActivity_1(this));
        this.e.setOnClickListener(new ECJiaChangePasswordActivity_2(this));
    }

    public void b() {
        this.b.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.b.getWindowToken(), 0);
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("user/password")) {
            return;
        }
        if (axVar.b() == 1) {
            k kVar = new k((Context) this, (int) R.string.change_password_succeed);
            kVar.a(17, 0, 0);
            kVar.a();
            finish();
            c.a().c(new b("exsit"));
            startActivity(new Intent(this, ECJiaLoginActivity.class));
            overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
            return;
        }
        kVar = new k((Context) this, axVar.d());
        kVar.a(17, 0, 0);
        kVar.a();
    }
}
