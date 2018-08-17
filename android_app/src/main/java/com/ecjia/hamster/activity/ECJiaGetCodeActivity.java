package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.g;
import com.ecjia.component.a.t;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.c;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.umeng.message.PushAgent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ECJiaGetCodeActivity extends a implements TextWatcher, OnClickListener, com.ecjia.component.a.a.a {
    public k a;
    public g b;
    private LinearLayout c;
    private LinearLayout d;
    private EditText e;
    private TextView k;
    private EditText l;
    private Button m;
    private c n;
    private t o;
    private d p;
    private ImageView q;
    private TextView r;
    private EditText s;
    private a t;
    private TextView u;
    private String v;
    private String w;
    private boolean x;
    private String y;

    class ECJiaGetCodeActivity_1 implements ImageLoadingListener {
        final /* synthetic */ ECJiaGetCodeActivity a;

        ECJiaGetCodeActivity_1(ECJiaGetCodeActivity eCJiaGetCodeActivity) {
            this.a = eCJiaGetCodeActivity;
        }

        public void onLoadingStarted(String str, View view) {
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
            this.a.q.setImageResource(R.drawable.login_defaultbg);
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        }

        public void onLoadingCancelled(String str, View view) {
        }
    }

    class ECJiaGetCodeActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaGetCodeActivity a;

        ECJiaGetCodeActivity_2(ECJiaGetCodeActivity eCJiaGetCodeActivity) {
            this.a = eCJiaGetCodeActivity;
        }

        public void onClick(View view) {
            this.a.n.b();
        }
    }

    class ECJiaGetCodeActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaGetCodeActivity a;

        ECJiaGetCodeActivity_3(ECJiaGetCodeActivity eCJiaGetCodeActivity) {
            this.a = eCJiaGetCodeActivity;
        }

        public void onClick(View view) {
            this.a.n.b();
            Intent intent = new Intent(this.a, ECJiaLoginActivity.class);
            intent.setFlags(67108864);
            this.a.startActivity(intent);
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            this.a.b();
        }
    }

    class ECJiaGetCodeActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaGetCodeActivity a;

        class ECJiaGetCodeActivity_4_1 implements OnClickListener {
            final /* synthetic */ ECJiaGetCodeActivity_4 a;

            ECJiaGetCodeActivity_4_1(ECJiaGetCodeActivity_4 eCJiaGetCodeActivity_4) {
                this.a = eCJiaGetCodeActivity_4;
            }

            public void onClick(View view) {
                this.a.a.n.b();
            }
        }

        class ECJiaGetCodeActivity_4_2 implements OnClickListener {
            final /* synthetic */ ECJiaGetCodeActivity_4 a;

            ECJiaGetCodeActivity_4_2(ECJiaGetCodeActivity_4 eCJiaGetCodeActivity_4) {
                this.a = eCJiaGetCodeActivity_4;
            }

            public void onClick(View view) {
                this.a.a.n.b();
                Intent intent = new Intent(this.a.a, ECJiaLoginActivity.class);
                intent.setFlags(67108864);
                this.a.a.startActivity(intent);
                this.a.a.finish();
                this.a.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                this.a.a.b();
            }
        }

        ECJiaGetCodeActivity_4(ECJiaGetCodeActivity eCJiaGetCodeActivity) {
            this.a = eCJiaGetCodeActivity;
        }

        public void onClick(View view) {
            if (this.a.l.getText().toString().length() > 0) {
                this.a.n = new c(this.a, this.a.g.getString(R.string.register_tips), this.a.g.getString(R.string.register_back));
                this.a.n.a(2);
                this.a.n.c(new ECJiaGetCodeActivity_4_1(this));
                this.a.n.b(new ECJiaGetCodeActivity_4_2(this));
                this.a.n.a();
                return;
            }
            Intent intent = new Intent(this.a, ECJiaLoginActivity.class);
            intent.setFlags(67108864);
            this.a.startActivity(intent);
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            this.a.b();
        }
    }

    class ECJiaGetCodeActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaGetCodeActivity a;

        ECJiaGetCodeActivity_5(ECJiaGetCodeActivity eCJiaGetCodeActivity) {
            this.a = eCJiaGetCodeActivity;
        }

        public void onClick(View view) {
            this.a.n.b();
            Intent intent = new Intent(this.a, ECJiaLoginActivity.class);
            intent.setFlags(67108864);
            this.a.startActivity(intent);
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    class ECJiaGetCodeActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaGetCodeActivity a;

        ECJiaGetCodeActivity_6(ECJiaGetCodeActivity eCJiaGetCodeActivity) {
            this.a = eCJiaGetCodeActivity;
        }

        public void onClick(View view) {
            this.a.n.b();
        }
    }

    class ECJiaGetCodeActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaGetCodeActivity a;

        ECJiaGetCodeActivity_7(ECJiaGetCodeActivity eCJiaGetCodeActivity) {
            this.a = eCJiaGetCodeActivity;
        }

        public void onClick(View view) {
            this.a.n.b();
        }
    }

    class a extends CountDownTimer {
        final /* synthetic */ ECJiaGetCodeActivity a;

        public a(ECJiaGetCodeActivity eCJiaGetCodeActivity, long j, long j2) {
            this.a = eCJiaGetCodeActivity;
            super(j, j2);
        }

        public void onTick(long j) {
            this.a.u.setBackgroundResource(R.drawable.shape_unable);
            this.a.u.setTextColor(Color.parseColor("#ff999999"));
            this.a.u.setClickable(false);
            this.a.u.setText(this.a.g.getString(R.string.register_resend) + "(" + (j / 1000) + ")");
        }

        public void onFinish() {
            this.a.u.setText(this.a.g.getString(R.string.register_resend));
            this.a.u.setClickable(true);
            this.a.u.setTextColor(Color.parseColor("#ffffff"));
            this.a.u.setBackgroundResource(R.drawable.selector_get_code);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mobileregister);
        a();
        this.q = (ImageView) findViewById(R.id.root_view);
        this.p = d.a((Context) this);
        ImageLoader.getInstance().displayImage("file:///sdcard/android/data/com.ecmoban.android.missmall/login_bg", this.q, new ECJiaGetCodeActivity_1(this));
        PushAgent.getInstance(this).onAppStart();
        this.b = new g(this);
        this.b.a((com.ecjia.component.a.a.a) this);
        this.o = new t(this);
        this.o.a((com.ecjia.component.a.a.a) this);
        this.r = (TextView) findViewById(R.id.messagecodecheck_attention);
        this.c = (LinearLayout) findViewById(R.id.ll_invitation);
        this.d = (LinearLayout) findViewById(R.id.ll_invitation2);
        this.e = (EditText) findViewById(R.id.et_invitation);
        this.k = (TextView) findViewById(R.id.tv_invitation);
        this.l = (EditText) findViewById(R.id.mobileregister_edit);
        this.m = (Button) findViewById(R.id.mobileregister_next);
        this.m.setOnClickListener(this);
        this.l.addTextChangedListener(this);
        this.s = (EditText) findViewById(R.id.messagecodecheck_edit);
        this.s.addTextChangedListener(this);
        this.t = new a(this, 119900, 1000);
        this.u = (TextView) findViewById(R.id.messagecodecheck_time);
        this.u.setOnClickListener(this);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.l.getText().toString().length() > 0) {
                this.n = new c(this, this.g.getString(R.string.register_tips), this.g.getString(R.string.register_back));
                this.n.a(2);
                this.n.c(new ECJiaGetCodeActivity_2(this));
                this.n.b(new ECJiaGetCodeActivity_3(this));
                this.n.a();
            } else {
                Intent intent = new Intent(this, ECJiaLoginActivity.class);
                intent.setFlags(67108864);
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                b();
                finish();
            }
        }
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.messagecodecheck_time:
                this.v = this.l.getText().toString();
                if (a(this.v)) {
                    this.b.a(this.v);
                    this.t.start();
                    this.p.show();
                    return;
                } else if (this.v == null || this.v == "") {
                    this.a = new k((Context) this, this.g.getString(R.string.register_num_null));
                    this.a.a(17, 0, 0);
                    this.a.a();
                    return;
                } else {
                    this.a = new k((Context) this, this.g.getString(R.string.register_num_format));
                    this.a.a(17, 0, 0);
                    this.a.a();
                    return;
                }
            case R.id.mobileregister_next:
                this.v = this.l.getText().toString();
                this.w = this.s.getText().toString();
                this.y = this.e.getText().toString();
                if (a(this.v)) {
                    if (this.w.length() != 6) {
                        this.a = new k((Context) this, this.g.getString(R.string.register_wrong_code));
                        this.a.a(17, 0, 0);
                        this.a.a();
                        return;
                    } else if (this.y.length() <= 0 || this.y.length() == 6) {
                        this.b.b(this.v, this.w);
                        this.p.show();
                        return;
                    } else {
                        this.a = new k((Context) this, this.g.getString(R.string.register_wrong_invite_code));
                        this.a.a(17, 0, 0);
                        this.a.a();
                        return;
                    }
                } else if (this.v == null || this.v == "") {
                    this.a = new k((Context) this, this.g.getString(R.string.register_num_null));
                    this.a.a(17, 0, 0);
                    this.a.a();
                    return;
                } else {
                    this.a = new k((Context) this, this.g.getString(R.string.register_num_format));
                    this.a.a(17, 0, 0);
                    this.a.a();
                    return;
                }
            default:
                return;
        }
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.getcode_topview);
        this.i.setBackgroundColor(Color.parseColor("#00000000"));
        this.i.setTitleText((int) R.string.mobile_register);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaGetCodeActivity_4(this));
    }

    public void b() {
        this.l.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.l.getWindowToken(), 0);
    }

    public static boolean a(String str) {
        Matcher matcher = Pattern.compile("(1)\\d{10}$").matcher(str);
        System.out.println(matcher.matches() + "---");
        return matcher.matches();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.l.getText().toString().length() == 11) {
            this.m.setEnabled(true);
            this.m.setTextColor(Color.parseColor("#ffffffff"));
            this.m.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.m.setEnabled(false);
        this.m.setTextColor(Color.parseColor("#ff999999"));
        this.m.setBackgroundResource(R.drawable.shape_unable);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.l.getText().toString().length() == 11) {
            this.m.setEnabled(true);
            this.m.setTextColor(Color.parseColor("#ffffffff"));
            this.m.setBackgroundResource(R.drawable.selector_login_button);
            if (!this.x) {
                this.l.setEnabled(false);
                this.o.b(this.l.getText().toString());
                return;
            }
            return;
        }
        this.m.setEnabled(false);
        this.m.setTextColor(Color.parseColor("#ff999999"));
        this.m.setBackgroundResource(R.drawable.shape_unable);
    }

    public void afterTextChanged(Editable editable) {
        if (this.l.getText().toString().length() == 11) {
            this.m.setEnabled(true);
            this.m.setTextColor(Color.parseColor("#ffffffff"));
            this.m.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.m.setEnabled(false);
        this.m.setTextColor(Color.parseColor("#ff999999"));
        this.m.setBackgroundResource(R.drawable.shape_unable);
    }

    public void a(String str, String str2, ax axVar) {
        this.p.dismiss();
        if (str.equals("user/userbind")) {
            if (axVar.a() == 1) {
                this.n = new c(this, this.g.getString(R.string.register_tips), this.g.getString(R.string.register_num_extinct));
                this.n.a(2);
                this.n.c(new ECJiaGetCodeActivity_5(this));
                this.n.b(new ECJiaGetCodeActivity_6(this));
                this.n.a();
            } else if (axVar.a() == 0) {
                this.n = new c(this, this.g.getString(R.string.register_tips), this.g.getString(R.string.register_code_send) + "\n" + this.v);
                this.n.a();
                this.n.c();
                this.n.a(1);
                this.n.a(new ECJiaGetCodeActivity_7(this));
            } else if (axVar.a() == 2) {
                this.a = new k((Context) this, this.g.getString(R.string.getcode_attention_sendfail));
                this.a.a(17, 0, 0);
                this.a.a();
            }
        } else if (str.equals("invite/validate")) {
            this.x = true;
            this.l.setEnabled(true);
            if (axVar.b() != 1) {
                this.c.setVisibility(0);
                this.d.setVisibility(8);
            } else if (TextUtils.isEmpty(this.o.f)) {
                this.c.setVisibility(0);
                this.d.setVisibility(8);
            } else {
                this.e.setText(this.o.f);
                this.k.setText(this.o.f);
                this.c.setVisibility(8);
                this.d.setVisibility(0);
            }
        } else if (str != "validate/bind") {
        } else {
            if (axVar.b() == 1) {
                Intent intent = new Intent(this, ECJiaMobileRegisterActivity.class);
                intent.putExtra("userName", this.v);
                intent.putExtra("invite", this.e.getText().toString());
                intent.setFlags(67108864);
                startActivityForResult(intent, 1);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            }
            this.a = new k((Context) this, axVar.d());
            this.a.a(17, 0, 0);
            this.a.a();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Intent intent2 = new Intent();
        intent2.setFlags(67108864);
        setResult(-1, intent2);
        finish();
    }
}
