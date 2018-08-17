package com.ecjia.hamster.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.g;
import com.ecjia.component.view.c;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ECJiaBindingPhoneActivity extends b implements com.ecjia.component.a.a.a {
    public k a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private EditText k;
    private EditText l;
    private String m;
    private String n;
    private String o;
    private g p;
    private a q;
    private d r;
    private c s;
    private ImageView t;

    class ECJiaBindingPhoneActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaBindingPhoneActivity a;

        ECJiaBindingPhoneActivity_1(ECJiaBindingPhoneActivity eCJiaBindingPhoneActivity) {
            this.a = eCJiaBindingPhoneActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaBindingPhoneActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaBindingPhoneActivity a;

        ECJiaBindingPhoneActivity_2(ECJiaBindingPhoneActivity eCJiaBindingPhoneActivity) {
            this.a = eCJiaBindingPhoneActivity;
        }

        public void onClick(View view) {
            this.a.m = this.a.k.getText().toString();
            if (this.a.o.equals("user_modify_mobile")) {
                if (ECJiaBindingPhoneActivity.a(this.a.m)) {
                    this.a.p.c(this.a.o, this.a.m);
                    this.a.q.start();
                    this.a.r.show();
                } else if (this.a.m == null || this.a.m == "") {
                    this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_null));
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                } else {
                    this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_format));
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                }
            } else if (!this.a.o.equals("user_modify_mail")) {
            } else {
                if (ECJiaBindingPhoneActivity.b(this.a.m)) {
                    this.a.p.c(this.a.o, this.a.m);
                    this.a.q.start();
                    this.a.r.show();
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

    class ECJiaBindingPhoneActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaBindingPhoneActivity a;

        ECJiaBindingPhoneActivity_3(ECJiaBindingPhoneActivity eCJiaBindingPhoneActivity) {
            this.a = eCJiaBindingPhoneActivity;
        }

        public void onClick(View view) {
            this.a.m = this.a.k.getText().toString();
            this.a.n = this.a.l.getText().toString();
            if (this.a.o.equals("user_modify_mobile")) {
                if (ECJiaBindingPhoneActivity.a(this.a.m)) {
                    if (this.a.n.length() != 6) {
                        this.a.a = new k(this.a, this.a.g.getString(R.string.register_wrong_code));
                        this.a.a.a(17, 0, 0);
                        this.a.a.a();
                        return;
                    }
                    this.a.p.a(this.a.o, this.a.m, this.a.n);
                    this.a.r.show();
                } else if (this.a.m == null || this.a.m == "") {
                    this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_null));
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                } else {
                    this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_format));
                    this.a.a.a(17, 0, 0);
                    this.a.a.a();
                }
            } else if (!this.a.o.equals("user_modify_mail")) {
            } else {
                if (ECJiaBindingPhoneActivity.b(this.a.m)) {
                    if (this.a.n.length() != 6) {
                        this.a.a = new k(this.a, this.a.g.getString(R.string.register_wrong_code));
                        this.a.a.a(17, 0, 0);
                        this.a.a.a();
                        return;
                    }
                    this.a.p.a(this.a.o, this.a.m, this.a.n);
                    this.a.r.show();
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

    class ECJiaBindingPhoneActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaBindingPhoneActivity a;

        ECJiaBindingPhoneActivity_4(ECJiaBindingPhoneActivity eCJiaBindingPhoneActivity) {
            this.a = eCJiaBindingPhoneActivity;
        }

        public void onClick(View view) {
            this.a.s.b();
        }
    }

    class ECJiaBindingPhoneActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaBindingPhoneActivity a;

        ECJiaBindingPhoneActivity_5(ECJiaBindingPhoneActivity eCJiaBindingPhoneActivity) {
            this.a = eCJiaBindingPhoneActivity;
        }

        public void onClick(View view) {
            this.a.s.b();
        }
    }

    class a extends CountDownTimer {
        final /* synthetic */ ECJiaBindingPhoneActivity a;

        public a(ECJiaBindingPhoneActivity eCJiaBindingPhoneActivity, long j, long j2) {
            this.a = eCJiaBindingPhoneActivity;
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
        setContentView(R.layout.binding_itmes_list);
        this.r = d.a((Context) this);
        this.p = new g(this);
        this.p.a((com.ecjia.component.a.a.a) this);
        this.q = new a(this, 119900, 1000);
        this.o = getIntent().getStringExtra("type");
        b();
    }

    void b() {
        this.t = (ImageView) findViewById(R.id.top_view_back);
        this.t.setOnClickListener(new ECJiaBindingPhoneActivity_1(this));
        this.e = (TextView) findViewById(R.id.top_view_text);
        this.d = (TextView) findViewById(R.id.bin_txt);
        this.b = (TextView) findViewById(R.id.bin_exitLogin);
        this.c = (TextView) findViewById(R.id.bin_time_phone);
        this.k = (EditText) findViewById(R.id.bin_phone_new);
        this.l = (EditText) findViewById(R.id.bin_phone_new2);
        if (this.o.equals("user_modify_mobile")) {
            this.e.setText("绑定手机号");
            this.d.setText("绑定手机后，你可以使用手机号登录，也可以通过手机号找回密码");
            this.k.setMaxEms(11);
            this.k.setRawInputType(2);
            this.k.setHint("请输入手机号");
        } else if (this.o.equals("user_modify_mail")) {
            this.e.setText("绑定邮箱");
            this.d.setText("请输入你的邮箱账号");
            this.k.setHint("请输入邮箱账号");
        }
        this.c.setOnClickListener(new ECJiaBindingPhoneActivity_2(this));
        this.b.setOnClickListener(new ECJiaBindingPhoneActivity_3(this));
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
        this.r.dismiss();
        if (str.equals("shop/captcha/sms")) {
            if (axVar.b() == 1) {
                this.s = new c(this, this.g.getString(R.string.register_tips), "短信已发送手机" + this.m + this.g.getString(R.string.register_code_send3));
                this.s.a();
                this.s.c();
                this.s.a(1);
                this.s.a(new ECJiaBindingPhoneActivity_4(this));
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
                this.s = new c(this, this.g.getString(R.string.register_tips), "短信已发送到邮箱" + this.m + this.g.getString(R.string.register_code_send3));
                this.s.a();
                this.s.c();
                this.s.a(1);
                this.s.a(new ECJiaBindingPhoneActivity_5(this));
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
                this.a = new k((Context) this, "绑定成功！");
                this.a.a(17, 0, 0);
                this.a.a();
                setResult(-1);
                finish();
                return;
            }
            this.a = new k((Context) this, axVar.c());
            this.a.a(17, 0, 0);
            this.a.a();
        }
    }
}
