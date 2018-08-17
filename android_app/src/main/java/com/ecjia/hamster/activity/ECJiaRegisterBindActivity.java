package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.ac;
import com.ecjia.component.a.t;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ECJiaRegisterBindActivity extends a implements TextWatcher, OnClickListener, com.ecjia.component.a.a.a {
    private EditText A;
    private EditText B;
    private CheckBox C;
    private String D;
    private SharedPreferences E;
    private Editor F;
    private TextView a;
    private TextView b;
    private EditText c;
    private Button d;
    private a e;
    private String k;
    private k l;
    private LinearLayout m;
    private LinearLayout n;
    private EditText o;
    private TextView p;
    private EditText q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private ac w;
    private t x;
    private ImageView y;
    private c z;

    class ECJiaRegisterBindActivity_1 implements ImageLoadingListener {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        ECJiaRegisterBindActivity_1(ECJiaRegisterBindActivity eCJiaRegisterBindActivity) {
            this.a = eCJiaRegisterBindActivity;
        }

        public void onLoadingStarted(String str, View view) {
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
            this.a.y.setImageResource(R.drawable.login_defaultbg);
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        }

        public void onLoadingCancelled(String str, View view) {
        }
    }

    class ECJiaRegisterBindActivity_2 implements TextWatcher {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        ECJiaRegisterBindActivity_2(ECJiaRegisterBindActivity eCJiaRegisterBindActivity) {
            this.a = eCJiaRegisterBindActivity;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.q.getText().toString().length() == 11) {
                this.a.d.setEnabled(true);
                this.a.d.setTextColor(Color.parseColor("#ffffffff"));
                this.a.d.setBackgroundResource(R.drawable.selector_login_button);
                return;
            }
            this.a.d.setEnabled(false);
            this.a.d.setTextColor(Color.parseColor("#ff999999"));
            this.a.d.setBackgroundResource(R.drawable.shape_unable);
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.q.getText().toString().length() == 11) {
                this.a.d.setEnabled(true);
                this.a.d.setTextColor(Color.parseColor("#ffffffff"));
                this.a.d.setBackgroundResource(R.drawable.selector_login_button);
                this.a.q.setEnabled(false);
                this.a.x.b(this.a.q.getText().toString());
                return;
            }
            this.a.d.setEnabled(false);
            this.a.d.setTextColor(Color.parseColor("#ff999999"));
            this.a.d.setBackgroundResource(R.drawable.shape_unable);
        }

        public void afterTextChanged(Editable editable) {
            if (this.a.q.getText().toString().length() == 11) {
                this.a.d.setEnabled(true);
                this.a.d.setTextColor(Color.parseColor("#ffffffff"));
                this.a.d.setBackgroundResource(R.drawable.selector_login_button);
                return;
            }
            this.a.d.setEnabled(false);
            this.a.d.setTextColor(Color.parseColor("#ff999999"));
            this.a.d.setBackgroundResource(R.drawable.shape_unable);
        }
    }

    class ECJiaRegisterBindActivity_3 implements OnCheckedChangeListener {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        ECJiaRegisterBindActivity_3(ECJiaRegisterBindActivity eCJiaRegisterBindActivity) {
            this.a = eCJiaRegisterBindActivity;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                this.a.A.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                this.a.A.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (this.a.A.length() > 0) {
                this.a.A.setSelection(this.a.A.length());
            }
        }
    }

    class ECJiaRegisterBindActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        class ECJiaRegisterBindActivity_4_1 implements OnClickListener {
            final /* synthetic */ ECJiaRegisterBindActivity_4 a;

            ECJiaRegisterBindActivity_4_1(ECJiaRegisterBindActivity_4 eCJiaRegisterBindActivity_4) {
                this.a = eCJiaRegisterBindActivity_4;
            }

            public void onClick(View view) {
                this.a.a.z.b();
            }
        }

        class ECJiaRegisterBindActivity_4_2 implements OnClickListener {
            final /* synthetic */ ECJiaRegisterBindActivity_4 a;

            ECJiaRegisterBindActivity_4_2(ECJiaRegisterBindActivity_4 eCJiaRegisterBindActivity_4) {
                this.a = eCJiaRegisterBindActivity_4;
            }

            public void onClick(View view) {
                this.a.a.z.b();
                this.a.a.finish();
                this.a.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                this.a.a.b();
            }
        }

        ECJiaRegisterBindActivity_4(ECJiaRegisterBindActivity eCJiaRegisterBindActivity) {
            this.a = eCJiaRegisterBindActivity;
        }

        public void onClick(View view) {
            this.a.z = new c(this.a, this.a.g.getString(R.string.register_tips), this.a.g.getString(R.string.register_back));
            this.a.z.a(2);
            this.a.z.a();
            this.a.z.c(new ECJiaRegisterBindActivity_4_1(this));
            this.a.z.b(new ECJiaRegisterBindActivity_4_2(this));
        }
    }

    class ECJiaRegisterBindActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        ECJiaRegisterBindActivity_5(ECJiaRegisterBindActivity eCJiaRegisterBindActivity) {
            this.a = eCJiaRegisterBindActivity;
        }

        public void onClick(View view) {
            this.a.z.b();
        }
    }

    class ECJiaRegisterBindActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        ECJiaRegisterBindActivity_6(ECJiaRegisterBindActivity eCJiaRegisterBindActivity) {
            this.a = eCJiaRegisterBindActivity;
        }

        public void onClick(View view) {
            this.a.z.b();
            this.a.setResult(-1);
            this.a.finish();
        }
    }

    class ECJiaRegisterBindActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        ECJiaRegisterBindActivity_7(ECJiaRegisterBindActivity eCJiaRegisterBindActivity) {
            this.a = eCJiaRegisterBindActivity;
        }

        public void onClick(View view) {
            this.a.z.b();
        }
    }

    class ECJiaRegisterBindActivity_8 implements OnClickListener {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        ECJiaRegisterBindActivity_8(ECJiaRegisterBindActivity eCJiaRegisterBindActivity) {
            this.a = eCJiaRegisterBindActivity;
        }

        public void onClick(View view) {
            this.a.z.b();
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            this.a.b();
        }
    }

    class a extends CountDownTimer {
        final /* synthetic */ ECJiaRegisterBindActivity a;

        public a(ECJiaRegisterBindActivity eCJiaRegisterBindActivity, long j, long j2) {
            this.a = eCJiaRegisterBindActivity;
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
        setContentView(R.layout.activity_register_bind);
        a();
        this.E = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.F = this.E.edit();
        if ("sns_qq".equals(this.E.getString("thirdWay", ""))) {
            this.t = this.E.getString("qq_id", "");
            this.v = this.E.getString("myscreen_name", "");
            this.s = "sns_qq";
        } else if ("sns_wechat".equals(this.E.getString("thirdWay", ""))) {
            this.t = this.E.getString("wx_id", "");
            this.v = this.E.getString("nick_name", "");
            this.s = "sns_wechat";
        }
        this.w = new ac(this);
        this.w.a((com.ecjia.component.a.a.a) this);
        this.x = new t(this);
        this.x.a((com.ecjia.component.a.a.a) this);
        c();
    }

    private void c() {
        this.y = (ImageView) findViewById(R.id.root_view);
        ImageLoader.getInstance().displayImage("file:///sdcard/android/data/com.ecmoban.android.missmall/login_bg", this.y, new ECJiaRegisterBindActivity_1(this));
        this.a = (TextView) findViewById(R.id.messagecodecheck_attention);
        this.m = (LinearLayout) findViewById(R.id.ll_invitation);
        this.n = (LinearLayout) findViewById(R.id.ll_invitation2);
        this.o = (EditText) findViewById(R.id.et_invitation);
        this.p = (TextView) findViewById(R.id.tv_invitation);
        this.q = (EditText) findViewById(R.id.mobileregister_edit);
        this.q.addTextChangedListener(new ECJiaRegisterBindActivity_2(this));
        this.c = (EditText) findViewById(R.id.messagecodecheck_edit);
        this.c.addTextChangedListener(this);
        this.A = (EditText) findViewById(R.id.login_password);
        this.A.addTextChangedListener(this);
        this.B = (EditText) findViewById(R.id.et_username);
        this.B.setText(this.v);
        this.e = new a(this, 119900, 1000);
        this.b = (TextView) findViewById(R.id.messagecodecheck_time);
        this.d = (Button) findViewById(R.id.messagecodecheck_next);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.C = (CheckBox) findViewById(R.id.login_show_pwd);
        this.C.setOnCheckedChangeListener(new ECJiaRegisterBindActivity_3(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.messagecodecheck_time:
                this.k = this.q.getText().toString();
                if (a(this.k)) {
                    this.w.a();
                    return;
                } else if (this.k == null || this.k == "") {
                    this.l = new k((Context) this, this.g.getString(R.string.register_num_null));
                    this.l.a(17, 0, 0);
                    this.l.a();
                    return;
                } else {
                    this.l = new k((Context) this, this.g.getString(R.string.register_num_format));
                    this.l.a(17, 0, 0);
                    this.l.a();
                    return;
                }
            case R.id.messagecodecheck_next:
                String string = this.g.getString(R.string.register_password_cannot_be_empty);
                this.r = this.c.getText().toString();
                this.D = this.A.getText().toString();
                this.k = this.q.getText().toString();
                this.v = this.B.getText().toString();
                this.u = this.o.getText().toString();
                if (TextUtils.isEmpty(this.v)) {
                    this.l = new k((Context) this, this.g.getString(R.string.input_username_tips3));
                    this.l.a(17, 0, 0);
                    this.l.a();
                    return;
                } else if (!c(this.v)) {
                    this.l = new k((Context) this, this.g.getString(R.string.input_username_tips1));
                    this.l.a(17, 0, 0);
                    this.l.a();
                    return;
                } else if (a(this.k)) {
                    if (this.r.length() != 6) {
                        this.l = new k((Context) this, this.g.getString(R.string.register_wrong_code));
                        this.l.a(17, 0, 0);
                        this.l.a();
                        return;
                    } else if (this.u.length() > 0 && this.u.length() != 6) {
                        this.l = new k((Context) this, this.g.getString(R.string.register_wrong_invite_code));
                        this.l.a(17, 0, 0);
                        this.l.a();
                        return;
                    } else if ("".equals(this.D)) {
                        k kVar = new k((Context) this, string);
                        kVar.a(17, 0, 0);
                        kVar.a();
                        return;
                    } else if (this.D.length() < 6) {
                        r0 = new k((Context) this, this.g.getString(R.string.register_pwd_tooshort));
                        r0.a(17, 0, 0);
                        r0.a();
                        return;
                    } else if (b(this.D)) {
                        this.w.a(this.v, this.D, this.k, this.u, this.t, this.s, this.r);
                        return;
                    } else {
                        r0 = new k((Context) this, this.g.getString(R.string.register_pwd_format_false));
                        r0.a(17, 0, 0);
                        r0.a();
                        return;
                    }
                } else if (this.k == null || this.k == "") {
                    this.l = new k((Context) this, this.g.getString(R.string.register_num_null));
                    this.l.a(17, 0, 0);
                    this.l.a();
                    return;
                } else {
                    this.l = new k((Context) this, this.g.getString(R.string.register_num_format));
                    this.l.a(17, 0, 0);
                    this.l.a();
                    return;
                }
            default:
                return;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() != 6 || this.A.getText().toString().length() < 6) {
            this.d.setEnabled(false);
            this.d.setTextColor(Color.parseColor("#ff999999"));
            this.d.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.d.setEnabled(true);
        this.d.setTextColor(Color.parseColor("#ffffffff"));
        this.d.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() != 6 || this.A.getText().toString().length() < 6) {
            this.d.setEnabled(false);
            this.d.setTextColor(Color.parseColor("#ff999999"));
            this.d.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.d.setEnabled(true);
        this.d.setTextColor(Color.parseColor("#ffffffff"));
        this.d.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void afterTextChanged(Editable editable) {
        if (this.c.getText().toString().length() != 6 || this.A.getText().toString().length() < 6) {
            this.d.setEnabled(false);
            this.d.setTextColor(Color.parseColor("#ff999999"));
            this.d.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.d.setEnabled(true);
        this.d.setTextColor(Color.parseColor("#ffffffff"));
        this.d.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void a() {
        this.i = (ECJiaTopView) findViewById(R.id.bind_topview);
        this.i.setTitleText((int) R.string.register_bind);
        this.i.setBackgroundColor(Color.parseColor("#00000000"));
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaRegisterBindActivity_4(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r9, java.lang.String r10, com.ecjia.hamster.model.ax r11) {
        /*
        r8 = this;
        r2 = -1;
        r5 = 17;
        r4 = 8;
        r3 = 1;
        r1 = 0;
        r0 = r9.hashCode();
        switch(r0) {
            case -1123998372: goto L_0x0031;
            case 642956364: goto L_0x0013;
            case 1445060061: goto L_0x0027;
            case 2129775008: goto L_0x001d;
            default: goto L_0x000e;
        };
    L_0x000e:
        r0 = r2;
    L_0x000f:
        switch(r0) {
            case 0: goto L_0x003b;
            case 1: goto L_0x00d2;
            case 2: goto L_0x00e6;
            case 3: goto L_0x016c;
            default: goto L_0x0012;
        };
    L_0x0012:
        return;
    L_0x0013:
        r0 = "user/userbind";
        r0 = r9.equals(r0);
        if (r0 == 0) goto L_0x000e;
    L_0x001b:
        r0 = r1;
        goto L_0x000f;
    L_0x001d:
        r0 = "shop/token";
        r0 = r9.equals(r0);
        if (r0 == 0) goto L_0x000e;
    L_0x0025:
        r0 = r3;
        goto L_0x000f;
    L_0x0027:
        r0 = "connect/signup";
        r0 = r9.equals(r0);
        if (r0 == 0) goto L_0x000e;
    L_0x002f:
        r0 = 2;
        goto L_0x000f;
    L_0x0031:
        r0 = "invite/validate";
        r0 = r9.equals(r0);
        if (r0 == 0) goto L_0x000e;
    L_0x0039:
        r0 = 3;
        goto L_0x000f;
    L_0x003b:
        r0 = r11.a();
        if (r0 != 0) goto L_0x008d;
    L_0x0041:
        r0 = new com.ecjia.component.view.c;
        r1 = r8.g;
        r2 = 2131100466; // 0x7f060332 float:1.7813314E38 double:1.052903528E-314;
        r1 = r1.getString(r2);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r8.g;
        r5 = 2131100445; // 0x7f06031d float:1.7813272E38 double:1.0529035177E-314;
        r4 = r4.getString(r5);
        r2 = r2.append(r4);
        r4 = "\n";
        r2 = r2.append(r4);
        r4 = r8.k;
        r2 = r2.append(r4);
        r2 = r2.toString();
        r0.<init>(r8, r1, r2);
        r8.z = r0;
        r0 = r8.z;
        r0.a();
        r0 = r8.z;
        r0.c();
        r0 = r8.z;
        r0.a(r3);
        r0 = r8.z;
        r1 = new com.ecjia.hamster.activity.ECJiaRegisterBindActivity$5;
        r1.<init>(r8);
        r0.a(r1);
        goto L_0x0012;
    L_0x008d:
        r0 = r11.a();
        if (r0 != r3) goto L_0x00af;
    L_0x0093:
        r0 = new com.ecjia.component.view.k;
        r2 = r8.g;
        r3 = 2131100456; // 0x7f060328 float:1.7813294E38 double:1.052903523E-314;
        r2 = r2.getString(r3);
        r0.<init>(r8, r2);
        r8.l = r0;
        r0 = r8.l;
        r0.a(r5, r1, r1);
        r0 = r8.l;
        r0.a();
        goto L_0x0012;
    L_0x00af:
        r0 = r11.a();
        r2 = 2;
        if (r0 != r2) goto L_0x0012;
    L_0x00b6:
        r0 = new com.ecjia.component.view.k;
        r2 = r8.g;
        r3 = 2131100058; // 0x7f06019a float:1.7812487E38 double:1.0529033265E-314;
        r2 = r2.getString(r3);
        r0.<init>(r8, r2);
        r8.l = r0;
        r0 = r8.l;
        r0.a(r5, r1, r1);
        r0 = r8.l;
        r0.a();
        goto L_0x0012;
    L_0x00d2:
        r0 = r11.b();
        if (r0 != r3) goto L_0x0012;
    L_0x00d8:
        r0 = r8.e;
        r0.start();
        r0 = r8.w;
        r1 = r8.k;
        r0.a(r1);
        goto L_0x0012;
    L_0x00e6:
        r0 = r11.b();
        if (r0 != r3) goto L_0x015b;
    L_0x00ec:
        r0 = r8.g;
        r4 = 2131100465; // 0x7f060331 float:1.7813312E38 double:1.0529035276E-314;
        r4 = r0.getString(r4);
        r0 = r8.g;
        r5 = 2131100196; // 0x7f060224 float:1.7812767E38 double:1.0529033947E-314;
        r5 = r0.getString(r5);
        r0 = de.greenrobot.event.c.a();
        r6 = new com.ecjia.a.a.b;
        r7 = "frommobile";
        r6.<init>(r7);
        r0.c(r6);
        r0 = r8.h;
        r0 = r0.e();
        r0 = r0.f();
        r0 = r0.size();
        if (r0 <= 0) goto L_0x0153;
    L_0x011c:
        r2 = "#replace#";
        r0 = r8.h;
        r0 = r0.e();
        r0 = r0.f();
        r0 = r0.get(r1);
        r0 = (com.ecjia.hamster.model.ECJia_BONUS) r0;
        r0 = r0.getBonus_amount();
        r0 = r5.replace(r2, r0);
        r1 = new com.ecjia.component.view.c;
        r1.<init>(r8, r4, r0);
        r8.z = r1;
        r0 = r8.z;
        r0.a(r3);
        r0 = r8.z;
        r1 = new com.ecjia.hamster.activity.ECJiaRegisterBindActivity$6;
        r1.<init>(r8);
        r0.a(r1);
        r0 = r8.z;
        r0.a();
        goto L_0x0012;
    L_0x0153:
        r8.setResult(r2);
        r8.finish();
        goto L_0x0012;
    L_0x015b:
        r0 = new com.ecjia.component.view.k;
        r2 = r11.d();
        r0.<init>(r8, r2);
        r0.a(r5, r1, r1);
        r0.a();
        goto L_0x0012;
    L_0x016c:
        r0 = r8.q;
        r0.setEnabled(r3);
        r0 = r11.b();
        if (r0 != r3) goto L_0x01b1;
    L_0x0177:
        r0 = r8.x;
        r0 = r0.f;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x01a5;
    L_0x0181:
        r0 = r8.x;
        r0 = r0.f;
        r8.u = r0;
        r0 = r8.o;
        r2 = r8.x;
        r2 = r2.f;
        r0.setText(r2);
        r0 = r8.p;
        r2 = r8.x;
        r2 = r2.f;
        r0.setText(r2);
        r0 = r8.m;
        r0.setVisibility(r4);
        r0 = r8.n;
        r0.setVisibility(r1);
        goto L_0x0012;
    L_0x01a5:
        r0 = r8.m;
        r0.setVisibility(r1);
        r0 = r8.n;
        r0.setVisibility(r4);
        goto L_0x0012;
    L_0x01b1:
        r0 = r8.m;
        r0.setVisibility(r1);
        r0 = r8.n;
        r0.setVisibility(r4);
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.hamster.activity.ECJiaRegisterBindActivity.a(java.lang.String, java.lang.String, com.ecjia.hamster.model.ax):void");
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            this.z = new c(this, this.g.getString(R.string.register_tips), this.g.getString(R.string.register_back));
            this.z.a();
            this.z.a(2);
            this.z.c(new ECJiaRegisterBindActivity_7(this));
            this.z.b(new ECJiaRegisterBindActivity_8(this));
        }
        return true;
    }

    public void b() {
        this.c.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.c.getWindowToken(), 0);
    }

    public static boolean a(String str) {
        Matcher matcher = Pattern.compile("(1)\\d{10}$").matcher(str);
        System.out.println(matcher.matches() + "---");
        return matcher.matches();
    }

    public static boolean b(String str) {
        return Pattern.compile("^[A-Za-z0-9*#@.&_]+$").matcher(str).matches();
    }

    public static boolean c(String str) {
        return Pattern.compile("^[A-Za-z0-9_\\P{Cn}]+$").matcher(str).matches();
    }
}
