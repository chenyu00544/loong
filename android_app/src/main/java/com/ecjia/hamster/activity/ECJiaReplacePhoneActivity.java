package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.a.g;
import com.ecjia.component.view.c;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ECJiaReplacePhoneActivity extends b implements com.ecjia.component.a.a.a {
    public k a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private EditText k;
    private EditText l;
    private String m;
    private String n;
    private g o;
    private a p;
    private d q;
    private c r;
    private String s;
    private String t;
    private ImageView u;

    class ECJiaReplacePhoneActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReplacePhoneActivity a;

        ECJiaReplacePhoneActivity_1(ECJiaReplacePhoneActivity eCJiaReplacePhoneActivity) {
            this.a = eCJiaReplacePhoneActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaReplacePhoneActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaReplacePhoneActivity a;

        ECJiaReplacePhoneActivity_2(ECJiaReplacePhoneActivity eCJiaReplacePhoneActivity) {
            this.a = eCJiaReplacePhoneActivity;
        }

        public void onClick(View view) {
            this.a.m = this.a.k.getText().toString();
            if (this.a.s.equals("user_modify_mobile")) {
                if (ECJiaReplacePhoneActivity.a(this.a.m)) {
                    if (this.a.m.equals(this.a.t)) {
                        this.a.a = new k(this.a, "该手机号与当前绑定的手机号相同");
                        this.a.a.a(17, 0, 0);
                        this.a.a.a();
                        return;
                    }
                    this.a.o.c(this.a.s, this.a.m);
                    this.a.p.start();
                    this.a.q.show();
                } else if (this.a.m == null || this.a.m == "") {
                    this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_null));
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                } else {
                    this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_format));
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                }
            } else if (!this.a.s.equals("user_modify_mail")) {
            } else {
                if (ECJiaReplacePhoneActivity.b(this.a.m)) {
                    if (this.a.m.equals(this.a.t)) {
                        this.a.a = new k(this.a, "该邮箱地址与当前绑定的邮箱地址相同");
                        this.a.a.a(17, 0, 0);
                        this.a.a.a();
                        return;
                    }
                    this.a.o.c(this.a.s, this.a.m);
                    this.a.p.start();
                    this.a.q.show();
                } else if (this.a.m == null || this.a.m == "") {
                    this.a.a = new k(this.a, "请输入正确的邮箱");
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                } else {
                    this.a.a = new k(this.a, "请输入正确的邮箱");
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                }
            }
        }
    }

    class ECJiaReplacePhoneActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaReplacePhoneActivity a;

        ECJiaReplacePhoneActivity_3(ECJiaReplacePhoneActivity eCJiaReplacePhoneActivity) {
            this.a = eCJiaReplacePhoneActivity;
        }

        public void onClick(View view) {
            this.a.m = this.a.k.getText().toString();
            this.a.n = this.a.l.getText().toString();
            if (this.a.s.equals("user_modify_mobile")) {
                if (ECJiaReplacePhoneActivity.a(this.a.m)) {
                    if (this.a.n.length() != 6) {
                        this.a.a = new k(this.a, this.a.g.getString(R.string.register_wrong_code));
                        this.a.a.a(17, 0, 0);
                        this.a.a.a();
                        return;
                    }
                    this.a.o.a(this.a.s, this.a.m, this.a.n);
                    this.a.q.show();
                } else if (this.a.m == null || this.a.m == "") {
                    this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_null));
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                } else {
                    this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_format));
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                }
            } else if (!this.a.s.equals("user_modify_mail")) {
            } else {
                if (ECJiaReplacePhoneActivity.b(this.a.m)) {
                    if (this.a.n.length() != 6) {
                        this.a.a = new k(this.a, this.a.g.getString(R.string.register_wrong_code));
                        this.a.a.a(17, 0, 0);
                        this.a.a.a();
                        return;
                    }
                    this.a.o.a(this.a.s, this.a.m, this.a.n);
                    this.a.q.show();
                } else if (this.a.m == null || this.a.m == "") {
                    this.a.a = new k(this.a, "请输入正确的邮箱");
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                } else {
                    this.a.a = new k(this.a, "请输入正确的邮箱");
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                }
            }
        }
    }

    class ECJiaReplacePhoneActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaReplacePhoneActivity a;

        ECJiaReplacePhoneActivity_4(ECJiaReplacePhoneActivity eCJiaReplacePhoneActivity) {
            this.a = eCJiaReplacePhoneActivity;
        }

        public void onClick(View view) {
            this.a.r.b();
        }
    }

    class ECJiaReplacePhoneActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaReplacePhoneActivity a;

        ECJiaReplacePhoneActivity_5(ECJiaReplacePhoneActivity eCJiaReplacePhoneActivity) {
            this.a = eCJiaReplacePhoneActivity;
        }

        public void onClick(View view) {
            this.a.r.b();
        }
    }

    class a extends CountDownTimer {
        final /* synthetic */ ECJiaReplacePhoneActivity a;

        public a(ECJiaReplacePhoneActivity eCJiaReplacePhoneActivity, long j, long j2) {
            this.a = eCJiaReplacePhoneActivity;
            super(j, j2);
        }

        public void onTick(long j) {
            this.a.c.setBackgroundResource(R.drawable.shape_rad3_ffffff);
            this.a.c.setTextColor(this.a.getBaseContext().getResources().getColorStateList(R.color.public_theme_color_normal));
            this.a.c.setClickable(false);
            this.a.c.setText(this.a.g.getString(R.string.register_resend) + "(" + (j / 1000) + ")");
        }

        public void onFinish() {
            this.a.c.setText(this.a.g.getString(R.string.register_resend));
            this.a.c.setClickable(true);
            this.a.c.setTextColor(this.a.getBaseContext().getResources().getColorStateList(R.color.public_theme_color_normal));
            this.a.c.setBackgroundResource(R.drawable.shape_rad3_ffffff);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.replace_phone_time);
        this.q = d.a((Context) this);
        this.o = new g(this);
        this.o.a((com.ecjia.component.a.a.a) this);
        this.p = new a(this, 119900, 1000);
        this.s = getIntent().getStringExtra("type");
        this.t = getIntent().getStringExtra("textType");
        b();
    }

    void b() {
        this.u = (ImageView) findViewById(R.id.top_view_back);
        this.u.setOnClickListener(new ECJiaReplacePhoneActivity_1(this));
        this.e = (TextView) findViewById(R.id.top_view_text);
        this.d = (TextView) findViewById(R.id.replace_text);
        this.b = (TextView) findViewById(R.id.replace_exitLogin);
        this.c = (TextView) findViewById(R.id.messagecodecheck_time_phone);
        this.k = (EditText) findViewById(R.id.change_phone_new);
        this.l = (EditText) findViewById(R.id.change_phone_new2);
        if (this.s.equals("user_modify_mobile")) {
            this.e.setText("更换手机号");
            this.d.setText("请设置新手机号");
            this.k.setMaxEms(11);
            this.k.setRawInputType(2);
            this.k.setHint("请输入手机号");
        } else if (this.s.equals("user_modify_mail")) {
            this.e.setText("更换邮箱");
            this.d.setText("请设置新邮箱账号");
            this.k.setHint("请输入邮箱账号");
        }
        this.c.setOnClickListener(new ECJiaReplacePhoneActivity_2(this));
        this.b.setOnClickListener(new ECJiaReplacePhoneActivity_3(this));
    }

    public static boolean a(String str) {
        Matcher matcher = Pattern.compile("(1)\\d{10}$").matcher(str);
        System.out.println(matcher.matches() + "---");
        return matcher.matches();
    }

    public static boolean b(String str) {
        return Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(str).matches();
    }

    public void a(String str, String str2, ax axVar) {
        this.q.dismiss();
        q.a("ADMIN_CAPTCHA_SMS===" + str);
        if (str.equals("shop/captcha/sms")) {
            if (axVar.b() == 1) {
                this.r = new c(this, this.g.getString(R.string.register_tips), "短信已发送到手机" + this.m + this.g.getString(R.string.register_code_send3));
                this.r.a();
                this.r.c();
                this.r.a(1);
                this.r.a(new ECJiaReplacePhoneActivity_4(this));
            } else if (axVar.d() != null) {
                this.a = new k((Context) this, axVar.d());
                this.a.a(17, 0, 0);
                this.a.a();
            } else {
                this.a = new k((Context) this, axVar.c());
                this.a.a(17, 0, 0);
                this.a.a();
            }
        }
        if (str.equals("shop/captcha/mail")) {
            if (axVar.b() == 1) {
                this.r = new c(this, this.g.getString(R.string.register_tips), "短信已发送到邮箱" + this.m + this.g.getString(R.string.register_code_send3));
                this.r.a();
                this.r.c();
                this.r.a(1);
                this.r.a(new ECJiaReplacePhoneActivity_5(this));
            } else if (axVar.d() != null) {
                this.a = new k((Context) this, axVar.d());
                this.a.a(17, 0, 0);
                this.a.a();
            } else {
                this.a = new k((Context) this, axVar.c());
                this.a.a(17, 0, 0);
                this.a.a();
            }
        } else if (!str.equals("user/bind")) {
        } else {
            if (axVar.b() == 1) {
                this.a = new k((Context) this, "更换成功！");
                this.a.a(17, 0, 0);
                this.a.a();
                setResult(-1, new Intent());
                finish();
                return;
            }
            this.a = new k((Context) this, axVar.c());
            this.a.a(17, 0, 0);
            this.a.a();
        }
    }
}
