package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.g;
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

public class ECJiaCheckCodeActivity extends a implements TextWatcher, OnClickListener, com.ecjia.component.a.a.a {
    private TextView a;
    private TextView b;
    private EditText c;
    private Button d;
    private a e;
    private String k;
    private c l;
    private k m;
    private String n;
    private String o;
    private g p;
    private d q;
    private boolean r = false;
    private ImageView s;

    class ECJiaCheckCodeActivity_1 implements ImageLoadingListener {
        final /* synthetic */ ECJiaCheckCodeActivity a;

        ECJiaCheckCodeActivity_1(ECJiaCheckCodeActivity eCJiaCheckCodeActivity) {
            this.a = eCJiaCheckCodeActivity;
        }

        public void onLoadingStarted(String str, View view) {
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
            this.a.s.setImageResource(R.drawable.login_defaultbg);
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        }

        public void onLoadingCancelled(String str, View view) {
        }
    }

    class ECJiaCheckCodeActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaCheckCodeActivity a;

        class ECJiaCheckCodeActivity_2_1 implements OnClickListener {
            final /* synthetic */ ECJiaCheckCodeActivity_2 a;

            ECJiaCheckCodeActivity_2_1(ECJiaCheckCodeActivity_2 eCJiaCheckCodeActivity_2) {
                this.a = eCJiaCheckCodeActivity_2;
            }

            public void onClick(View view) {
                this.a.a.l.b();
            }
        }

        class ECJiaCheckCodeActivity_2_2 implements OnClickListener {
            final /* synthetic */ ECJiaCheckCodeActivity_2 a;

            ECJiaCheckCodeActivity_2_2(ECJiaCheckCodeActivity_2 eCJiaCheckCodeActivity_2) {
                this.a = eCJiaCheckCodeActivity_2;
            }

            public void onClick(View view) {
                this.a.a.l.b();
                Intent intent = new Intent(this.a.a, ECJiaLoginActivity.class);
                intent.setFlags(67108864);
                this.a.a.startActivity(intent);
                this.a.a.finish();
                this.a.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                this.a.a.b();
            }
        }

        ECJiaCheckCodeActivity_2(ECJiaCheckCodeActivity eCJiaCheckCodeActivity) {
            this.a = eCJiaCheckCodeActivity;
        }

        public void onClick(View view) {
            this.a.l = new c(this.a, this.a.g.getString(R.string.register_tips), this.a.g.getString(R.string.register_back));
            this.a.l.a(2);
            this.a.l.a();
            this.a.l.c(new ECJiaCheckCodeActivity_2_1(this));
            this.a.l.b(new ECJiaCheckCodeActivity_2_2(this));
        }
    }

    class ECJiaCheckCodeActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaCheckCodeActivity a;

        ECJiaCheckCodeActivity_3(ECJiaCheckCodeActivity eCJiaCheckCodeActivity) {
            this.a = eCJiaCheckCodeActivity;
        }

        public void onClick(View view) {
            this.a.r = false;
            this.a.l.b();
        }
    }

    class ECJiaCheckCodeActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaCheckCodeActivity a;

        ECJiaCheckCodeActivity_4(ECJiaCheckCodeActivity eCJiaCheckCodeActivity) {
            this.a = eCJiaCheckCodeActivity;
        }

        public void onClick(View view) {
            this.a.l.b();
        }
    }

    class ECJiaCheckCodeActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaCheckCodeActivity a;

        ECJiaCheckCodeActivity_5(ECJiaCheckCodeActivity eCJiaCheckCodeActivity) {
            this.a = eCJiaCheckCodeActivity;
        }

        public void onClick(View view) {
            this.a.l.b();
        }
    }

    class ECJiaCheckCodeActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaCheckCodeActivity a;

        ECJiaCheckCodeActivity_6(ECJiaCheckCodeActivity eCJiaCheckCodeActivity) {
            this.a = eCJiaCheckCodeActivity;
        }

        public void onClick(View view) {
            this.a.l.b();
            Intent intent = new Intent(this.a, ECJiaLoginActivity.class);
            intent.setFlags(67108864);
            this.a.startActivity(intent);
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            this.a.b();
        }
    }

    class a extends CountDownTimer {
        final /* synthetic */ ECJiaCheckCodeActivity a;

        public a(ECJiaCheckCodeActivity eCJiaCheckCodeActivity, long j, long j2) {
            this.a = eCJiaCheckCodeActivity;
            super(j, j2);
        }

        public void onTick(long j) {
            this.a.b.setBackgroundResource(R.drawable.shape_unable);
            this.a.b.setTextColor(Color.parseColor("#ff999999"));
            this.a.b.setClickable(false);
            this.a.b.setText(this.a.g.getString(R.string.register_resend) + "(" + (j / 1000) + ")");
        }

        public void onFinish() {
            this.a.b.setText(this.a.g.getString(R.string.register_resend));
            this.a.b.setClickable(true);
            this.a.b.setTextColor(Color.parseColor("#ffffff"));
            this.a.b.setBackgroundResource(R.drawable.selector_get_code);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_message_code_check);
        a();
        this.o = getIntent().getStringExtra("invite");
        this.s = (ImageView) findViewById(R.id.root_view);
        ImageLoader.getInstance().displayImage("file:///sdcard/android/data/com.ecmoban.android.missmall/login_bg", this.s, new ECJiaCheckCodeActivity_1(this));
        this.q = d.a((Context) this);
        this.p = g.a();
        this.p.a((com.ecjia.component.a.a.a) this);
        this.k = getIntent().getStringExtra("mobile");
        this.a = (TextView) findViewById(R.id.messagecodecheck_attention);
        String string = this.g.getString(R.string.register_enter_recode);
        String substring = string.substring(0, 3);
        this.a.setText(substring + this.k + string.substring(3, string.length()));
        this.c = (EditText) findViewById(R.id.messagecodecheck_edit);
        this.c.addTextChangedListener(this);
        this.e = new a(this, 119900, 1000);
        this.b = (TextView) findViewById(R.id.messagecodecheck_time);
        this.e.start();
        this.d = (Button) findViewById(R.id.messagecodecheck_next);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.messagecodecheck_time:
                this.e.start();
                this.p.a(this.k);
                this.q.show();
                return;
            case R.id.messagecodecheck_next:
                this.n = this.c.getText().toString();
                if (this.n.length() == 6) {
                    this.p.b(this.k, this.n);
                    this.q.show();
                    return;
                }
                this.m = new k((Context) this, this.g.getString(R.string.register_wrong_code));
                this.m.a(17, 0, 0);
                this.m.a();
                return;
            default:
                return;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() == 6) {
            this.d.setEnabled(true);
            this.d.setTextColor(Color.parseColor("#ffffffff"));
            this.d.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.d.setEnabled(false);
        this.d.setTextColor(Color.parseColor("#ff999999"));
        this.d.setBackgroundResource(R.drawable.shape_unable);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() == 6) {
            this.d.setEnabled(true);
            this.d.setTextColor(Color.parseColor("#ffffffff"));
            this.d.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.d.setEnabled(false);
        this.d.setTextColor(Color.parseColor("#ff999999"));
        this.d.setBackgroundResource(R.drawable.shape_unable);
    }

    public void afterTextChanged(Editable editable) {
        if (this.c.getText().toString().length() == 6) {
            this.d.setEnabled(true);
            this.d.setTextColor(Color.parseColor("#ffffffff"));
            this.d.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.d.setEnabled(false);
        this.d.setTextColor(Color.parseColor("#ff999999"));
        this.d.setBackgroundResource(R.drawable.shape_unable);
    }

    public void a() {
        this.i = (ECJiaTopView) findViewById(R.id.checkcode_topview);
        this.i.setTitleText((int) R.string.mobile_register);
        this.i.setBackgroundColor(Color.parseColor("#00000000"));
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaCheckCodeActivity_2(this));
    }

    public void a(String str, String str2, ax axVar) {
        this.q.dismiss();
        if (str == "user/userbind") {
            if (axVar.a() == 0) {
                this.l = new c(this, this.g.getString(R.string.register_tips), this.g.getString(R.string.register_code_send) + "\n" + this.k);
                this.l.a();
                this.l.c();
                this.r = true;
                this.l.a(1);
                this.l.a(new ECJiaCheckCodeActivity_3(this));
            }
        } else if (str != "validate/bind") {
        } else {
            if (axVar.b() == 1) {
                Intent intent = new Intent(this, ECJiaMobileRegisterActivity.class);
                intent.putExtra("userName", this.k);
                intent.putExtra("invite", this.o);
                intent.setFlags(67108864);
                startActivityForResult(intent, 1);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            }
            this.l = new c(this, this.g.getString(R.string.register_tips), axVar.d());
            this.l.a();
            this.l.c();
            this.l.a(1);
            this.l.a(new ECJiaCheckCodeActivity_4(this));
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            this.l = new c(this, this.g.getString(R.string.register_tips), this.g.getString(R.string.register_back));
            this.l.a();
            this.l.a(2);
            this.l.c(new ECJiaCheckCodeActivity_5(this));
            this.l.b(new ECJiaCheckCodeActivity_6(this));
        }
        return true;
    }

    public void b() {
        this.c.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.c.getWindowToken(), 0);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Intent intent2 = new Intent();
        intent2.setFlags(67108864);
        setResult(-1, intent2);
        finish();
    }
}
