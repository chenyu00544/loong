package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ac;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class ECJiaRegisterActivity extends a implements OnClickListener, a {
    Resources a;
    private Button b;
    private EditText c;
    private EditText d;
    private EditText e;
    private String k;
    private String l;
    private String m;
    private ac n;
    private JSONArray o = new JSONArray();
    private boolean p = true;
    private String q;

    class ECJiaRegisterActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaRegisterActivity a;

        ECJiaRegisterActivity_1(ECJiaRegisterActivity eCJiaRegisterActivity) {
            this.a = eCJiaRegisterActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.register);
        PushAgent.getInstance(this).onAppStart();
        a();
        this.q = getIntent().getStringExtra("invite_code");
        this.a = getBaseContext().getResources();
        this.b = (Button) findViewById(R.id.register_register);
        this.c = (EditText) findViewById(R.id.register_name);
        this.d = (EditText) findViewById(R.id.register_email);
        this.e = (EditText) findViewById(R.id.register_password1);
        this.b.setOnClickListener(this);
        this.n = new ac(this);
        this.n.a((a) this);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.register_topview);
        this.i.setTitleText((int) R.string.login_register);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaRegisterActivity_1(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_register:
                this.k = this.c.getText().toString();
                this.l = this.d.getText().toString();
                this.m = this.e.getText().toString();
                String string = this.a.getString(R.string.register_user_name_cannot_be_empty);
                String string2 = this.a.getString(R.string.register_email_cannot_be_empty);
                String string3 = this.a.getString(R.string.register_password_cannot_be_empty);
                String string4 = this.a.getString(R.string.register_email_format_false);
                if ("".equals(this.k)) {
                    k kVar = new k((Context) this, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else if ("".equals(this.l)) {
                    r0 = new k((Context) this, string2);
                    r0.a(17, 0, 0);
                    r0.a();
                    return;
                } else if ("".equals(this.m)) {
                    r0 = new k((Context) this, string3);
                    r0.a(17, 0, 0);
                    r0.a();
                    return;
                } else if (!a(this.l)) {
                    r0 = new k((Context) this, string4);
                    r0.a(17, 0, 0);
                    r0.a();
                    return;
                } else if (this.k.length() > 15) {
                    r0 = new k((Context) this, "用户名过长");
                    r0.a(17, 0, 0);
                    r0.a();
                    return;
                } else if (this.m.length() < 6) {
                    r0 = new k((Context) this, "密码不能少于6位");
                    r0.a(17, 0, 0);
                    r0.a();
                    return;
                } else if (b(this.m)) {
                    b();
                    return;
                } else {
                    r0 = new k((Context) this, "密码格式不正确");
                    r0.a(17, 0, 0);
                    r0.a();
                    return;
                }
            default:
                return;
        }
    }

    public void b() {
        if (this.p) {
            c();
            this.n.a(this.k, this.m, this.l, this.o, "", this.q);
        }
    }

    public void c() {
        this.c.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.c.getWindowToken(), 0);
    }

    public static boolean a(String str) {
        return Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(str).matches();
    }

    public static boolean b(String str) {
        return Pattern.compile("^[A-Za-z0-9*#@.&_]+$").matcher(str).matches();
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("user/signupFields") && str.equals("user/signup") && axVar.b() == 1) {
            Intent intent = new Intent();
            intent.putExtra("login", true);
            setResult(-1, intent);
            finish();
            k kVar = new k((Context) this, this.a.getString(R.string.login_welcome));
            kVar.a(17, 0, 0);
            kVar.a();
        }
    }
}
