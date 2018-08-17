package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import com.ecjia.a.a.b;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ac;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ECJia_BONUS;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.umeng.message.PushAgent;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class ECJiaMobileRegisterActivity extends a implements TextWatcher, OnClickListener, a {
    private Button a;
    private EditText b;
    private EditText c;
    private EditText d;
    private CheckBox e;
    private String k;
    private String l;
    private String m;
    private String n;
    private ac o;
    private c p;
    private boolean q = true;
    private JSONArray r = new JSONArray();
    private ImageView s;
    private String t;

    class ECJiaMobileRegisterActivity_1 implements ImageLoadingListener {
        final /* synthetic */ ECJiaMobileRegisterActivity a;

        ECJiaMobileRegisterActivity_1(ECJiaMobileRegisterActivity eCJiaMobileRegisterActivity) {
            this.a = eCJiaMobileRegisterActivity;
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

    class ECJiaMobileRegisterActivity_2 implements OnCheckedChangeListener {
        final /* synthetic */ ECJiaMobileRegisterActivity a;

        ECJiaMobileRegisterActivity_2(ECJiaMobileRegisterActivity eCJiaMobileRegisterActivity) {
            this.a = eCJiaMobileRegisterActivity;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                this.a.c.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                this.a.c.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    class ECJiaMobileRegisterActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaMobileRegisterActivity a;

        class ECJiaMobileRegisterActivity_3_1 implements OnClickListener {
            final /* synthetic */ ECJiaMobileRegisterActivity_3 a;

            ECJiaMobileRegisterActivity_3_1(ECJiaMobileRegisterActivity_3 eCJiaMobileRegisterActivity_3) {
                this.a = eCJiaMobileRegisterActivity_3;
            }

            public void onClick(View view) {
                this.a.a.p.b();
            }
        }

        class ECJiaMobileRegisterActivity_3_2 implements OnClickListener {
            final /* synthetic */ ECJiaMobileRegisterActivity_3 a;

            ECJiaMobileRegisterActivity_3_2(ECJiaMobileRegisterActivity_3 eCJiaMobileRegisterActivity_3) {
                this.a = eCJiaMobileRegisterActivity_3;
            }

            public void onClick(View view) {
                this.a.a.p.b();
                Intent intent = new Intent(this.a.a, ECJiaLoginActivity.class);
                intent.setFlags(67108864);
                this.a.a.startActivity(intent);
                this.a.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                this.a.a.c();
            }
        }

        ECJiaMobileRegisterActivity_3(ECJiaMobileRegisterActivity eCJiaMobileRegisterActivity) {
            this.a = eCJiaMobileRegisterActivity;
        }

        public void onClick(View view) {
            this.a.p = new c(this.a, this.a.g.getString(R.string.register_tips), this.a.g.getString(R.string.register_back));
            this.a.p.a(2);
            this.a.p.a();
            this.a.p.c(new ECJiaMobileRegisterActivity_3_1(this));
            this.a.p.b(new ECJiaMobileRegisterActivity_3_2(this));
        }
    }

    class ECJiaMobileRegisterActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaMobileRegisterActivity a;

        ECJiaMobileRegisterActivity_4(ECJiaMobileRegisterActivity eCJiaMobileRegisterActivity) {
            this.a = eCJiaMobileRegisterActivity;
        }

        public void onClick(View view) {
            this.a.p.b();
        }
    }

    class ECJiaMobileRegisterActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaMobileRegisterActivity a;

        ECJiaMobileRegisterActivity_5(ECJiaMobileRegisterActivity eCJiaMobileRegisterActivity) {
            this.a = eCJiaMobileRegisterActivity;
        }

        public void onClick(View view) {
            this.a.p.b();
            Intent intent = new Intent(this.a, ECJiaLoginActivity.class);
            intent.setFlags(67108864);
            this.a.startActivity(intent);
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            this.a.c();
        }
    }

    class ECJiaMobileRegisterActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaMobileRegisterActivity a;

        ECJiaMobileRegisterActivity_6(ECJiaMobileRegisterActivity eCJiaMobileRegisterActivity) {
            this.a = eCJiaMobileRegisterActivity;
        }

        public void onClick(View view) {
            this.a.p.b();
            Intent intent = new Intent();
            intent.putExtra("login", true);
            intent.setFlags(67108864);
            this.a.setResult(-1, intent);
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setpassword);
        this.t = getIntent().getStringExtra("invite");
        a();
        this.s = (ImageView) findViewById(R.id.root_view);
        ImageLoader.getInstance().displayImage("file:///sdcard/android/data/com.ecmoban.android.missmall/login_bg", this.s, new ECJiaMobileRegisterActivity_1(this));
        PushAgent.getInstance(this).onAppStart();
        de.greenrobot.event.c.a().a((Object) this);
        this.n = getIntent().getStringExtra("userName");
        this.a = (Button) findViewById(R.id.setpassword_next);
        this.c = (EditText) findViewById(R.id.setpassword_password);
        this.d = (EditText) findViewById(R.id.et_username);
        this.b = (EditText) findViewById(R.id.setpassword_mail);
        this.e = (CheckBox) findViewById(R.id.setpassword_showpassword);
        this.a.setOnClickListener(this);
        this.c.addTextChangedListener(this);
        this.b.addTextChangedListener(this);
        this.e.setOnCheckedChangeListener(new ECJiaMobileRegisterActivity_2(this));
        getPreferences(32768);
        this.o = new ac(this);
        this.o.a((a) this);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.mobile_register_topview);
        this.i.setBackgroundColor(Color.parseColor("#00000000"));
        this.i.setTitleText((int) R.string.mobile_register);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaMobileRegisterActivity_3(this));
    }

    public void onClick(View view) {
        Resources resources = getBaseContext().getResources();
        resources.getString(R.string.register_email_cannot_be_empty);
        String string = resources.getString(R.string.register_password_cannot_be_empty);
        resources.getString(R.string.register_email_format_false);
        resources.getString(R.string.hold_on);
        switch (view.getId()) {
            case R.id.setpassword_next:
                this.m = this.b.getText().toString();
                this.l = this.c.getText().toString();
                this.k = this.d.getText().toString();
                k kVar;
                if (TextUtils.isEmpty(this.k)) {
                    kVar = new k((Context) this, resources.getString(R.string.input_username_tips3));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else if ("".equals(this.l)) {
                    k kVar2 = new k((Context) this, string);
                    kVar2.a(17, 0, 0);
                    kVar2.a();
                    return;
                } else if (this.l.length() < 6) {
                    kVar = new k((Context) this, resources.getString(R.string.register_pwd_tooshort));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else if (a(this.l)) {
                    b();
                    return;
                } else {
                    kVar = new k((Context) this, resources.getString(R.string.register_pwd_format_false));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
            default:
                return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            this.p = new c(this, this.g.getString(R.string.register_tips), this.g.getString(R.string.register_back));
            this.p.a(2);
            this.p.a();
            this.p.c(new ECJiaMobileRegisterActivity_4(this));
            this.p.b(new ECJiaMobileRegisterActivity_5(this));
        }
        return true;
    }

    public void b() {
        if (this.q) {
            c();
            this.o.a(this.k, this.l, this.m, this.r, this.n, this.t);
        }
    }

    public void c() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
    }

    public static boolean a(String str) {
        return Pattern.compile("^[A-Za-z0-9*#@.&_]+$").matcher(str).matches();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() >= 6) {
            this.a.setEnabled(true);
            this.a.setTextColor(Color.parseColor("#ffffffff"));
            this.a.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.a.setEnabled(this.q);
        this.a.setTextColor(Color.parseColor("#ff999999"));
        this.a.setBackgroundResource(R.drawable.shape_unable);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() >= 6) {
            this.a.setEnabled(true);
            this.a.setTextColor(Color.parseColor("#ffffffff"));
            this.a.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.a.setEnabled(this.q);
        this.a.setTextColor(Color.parseColor("#ff999999"));
        this.a.setBackgroundResource(R.drawable.shape_unable);
    }

    public void afterTextChanged(Editable editable) {
        if (this.c.getText().toString().length() >= 6) {
            this.a.setEnabled(true);
            this.a.setTextColor(Color.parseColor("#ffffffff"));
            this.a.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.a.setEnabled(this.q);
        this.a.setTextColor(Color.parseColor("#ff999999"));
        this.a.setBackgroundResource(R.drawable.shape_unable);
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
    }

    public void a(String str, String str2, ax axVar) {
        Resources resources = getResources();
        if (!str.equals("user/signup")) {
            return;
        }
        if (axVar.b() == 1) {
            String string = resources.getString(R.string.register_success);
            String string2 = resources.getString(R.string.logonId);
            de.greenrobot.event.c.a().c(new b("frommobile"));
            if (this.h.e().f().size() > 0) {
                this.p = new c(this, string, string2.replace("#replace#", ((ECJia_BONUS) this.h.e().f().get(0)).getBonus_amount()));
                this.p.a(1);
                this.p.a(new ECJiaMobileRegisterActivity_6(this));
                this.p.a();
                return;
            }
            k kVar = new k((Context) this, resources.getString(R.string.login_welcome));
            kVar.a(17, 0, 0);
            kVar.a();
            Intent intent = new Intent();
            intent.putExtra("login", true);
            intent.setFlags(67108864);
            setResult(-1, intent);
            finish();
            return;
        }
        k kVar2 = new k((Context) this, axVar.d());
        kVar2.a(17, 0, 0);
        kVar2.a();
    }
}
