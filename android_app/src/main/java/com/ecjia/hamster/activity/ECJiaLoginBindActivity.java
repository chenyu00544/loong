package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ecjia.a.a.b;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.u;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.k;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaLoginBindActivity extends a implements TextWatcher, OnClickListener, a {
    SharedPreferences a;
    Editor b;
    private Button c;
    private EditText d;
    private EditText e;
    private CheckBox k;
    private String l;
    private String m;
    private String n;
    private String o;
    private u p;
    private LinearLayout q;
    private View r;
    private ImageView s;
    private k t;
    private int u;
    private int v = 0;

    class ECJiaLoginBindActivity_1 implements ImageLoadingListener {
        final /* synthetic */ ECJiaLoginBindActivity a;

        ECJiaLoginBindActivity_1(ECJiaLoginBindActivity eCJiaLoginBindActivity) {
            this.a = eCJiaLoginBindActivity;
        }

        public void onLoadingStarted(String str, View view) {
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
            this.a.s.setVisibility(0);
            this.a.s.setImageResource(R.drawable.login_defaultbg);
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            this.a.s.setVisibility(0);
        }

        public void onLoadingCancelled(String str, View view) {
        }
    }

    class ECJiaLoginBindActivity_2 implements OnCheckedChangeListener {
        final /* synthetic */ ECJiaLoginBindActivity a;

        ECJiaLoginBindActivity_2(ECJiaLoginBindActivity eCJiaLoginBindActivity) {
            this.a = eCJiaLoginBindActivity;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                this.a.e.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                this.a.e.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (this.a.e.length() > 0) {
                this.a.e.setSelection(this.a.e.length());
            }
        }
    }

    class ECJiaLoginBindActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaLoginBindActivity a;

        ECJiaLoginBindActivity_3(ECJiaLoginBindActivity eCJiaLoginBindActivity) {
            this.a = eCJiaLoginBindActivity;
        }

        public void onClick(View view) {
            this.a.finish();
            this.a.b();
            this.a.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_login_bind);
        a();
        this.a = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.b = this.a.edit();
        this.t = this.h.d();
        if (this.t == null || TextUtils.isEmpty(this.t.b())) {
            this.u = Color.parseColor("#ff999999");
        } else {
            this.u = Color.parseColor(this.t.b());
        }
        this.s = (ImageView) findViewById(R.id.login_view);
        this.s.setVisibility(4);
        ImageLoader.getInstance().displayImage("file:///sdcard/android/data/com.ecmoban.android.missmall/login_bg", this.s, new ECJiaLoginBindActivity_1(this));
        c.a().a((Object) this);
        this.p = new u(this);
        this.p.a((a) this);
        this.q = (LinearLayout) findViewById(R.id.root_view);
        this.r = findViewById(R.id.buttom_view);
        this.c = (Button) findViewById(R.id.login_login);
        this.d = (EditText) findViewById(R.id.login_name);
        this.e = (EditText) findViewById(R.id.login_password);
        this.d.addTextChangedListener(this);
        this.e.addTextChangedListener(this);
        this.c.setOnClickListener(this);
        this.k = (CheckBox) findViewById(R.id.login_show_pwd);
        this.k.setOnCheckedChangeListener(new ECJiaLoginBindActivity_2(this));
        a(this.q, this.r);
        if ("sns_qq".equals(this.a.getString("thirdWay", ""))) {
            this.n = this.a.getString("qq_id", "");
            this.o = "sns_qq";
        } else if ("sns_wechat".equals(this.a.getString("thirdWay", ""))) {
            this.n = this.a.getString("wx_id", "");
            this.o = "sns_wechat";
        }
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.login_topview);
        this.i.setBackgroundColor(Color.parseColor("#00000000"));
        this.i.setTitleText((int) R.string.login_bind);
        this.i.setLeftBackImage((int) R.drawable.login_back, new ECJiaLoginBindActivity_3(this));
    }

    public void onClick(View view) {
        String string = this.g.getString(R.string.register_user_name_cannot_be_empty);
        String string2 = this.g.getString(R.string.register_password_cannot_be_empty);
        this.g.getString(R.string.check_the_network);
        switch (view.getId()) {
            case R.id.login_login:
                this.l = this.d.getText().toString();
                this.m = this.e.getText().toString();
                if ("".equals(this.l)) {
                    com.ecjia.component.view.k kVar = new com.ecjia.component.view.k((Context) this, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else if ("".equals(this.m)) {
                    com.ecjia.component.view.k kVar2 = new com.ecjia.component.view.k((Context) this, string2);
                    kVar2.a(17, 0, 0);
                    kVar2.a();
                    return;
                } else {
                    this.p.a(this.l, this.m, this.n, this.o);
                    b();
                    return;
                }
            default:
                return;
        }
    }

    protected void onPause() {
        super.onPause();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
        return true;
    }

    public void b() {
        this.d.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.d.getWindowToken(), 0);
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(b bVar) {
    }

    public void a(final View view, final View view2) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ ECJiaLoginBindActivity c;

            public void onGlobalLayout() {
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                if (view.getRootView().getHeight() - rect.bottom > 100) {
                    int[] iArr = new int[2];
                    view2.getLocationInWindow(iArr);
                    int height = (iArr[1] + view2.getHeight()) - rect.bottom;
                    if (height > this.c.v) {
                        this.c.v = height;
                        view.scrollTo(0, height);
                        return;
                    }
                    return;
                }
                view.scrollTo(0, 0);
                this.c.v = 0;
            }
        });
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.d.getText().toString().length() <= 0 || this.e.getText().toString().length() < 1) {
            this.c.setEnabled(false);
            this.c.setTextColor(this.u);
            this.c.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.c.setEnabled(true);
        this.c.setTextColor(Color.parseColor("#ffffffff"));
        this.c.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.d.getText().toString().length() <= 0 || this.e.getText().toString().length() < 1) {
            this.c.setEnabled(false);
            this.c.setTextColor(this.u);
            this.c.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.c.setEnabled(true);
        this.c.setTextColor(Color.parseColor("#ffffffff"));
        this.c.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void afterTextChanged(Editable editable) {
        if (this.d.getText().toString().length() <= 0 || this.e.getText().toString().length() < 1) {
            this.c.setEnabled(false);
            this.c.setTextColor(this.u);
            this.c.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.c.setEnabled(true);
        this.c.setTextColor(Color.parseColor("#ffffffff"));
        this.c.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void a(String str, String str2, ax axVar) {
        this.g.getString(R.string.login_invalid_password);
        this.g.getString(R.string.login_welcome);
        if (!str.equals("connect/bind")) {
            return;
        }
        if (axVar.b() == 1) {
            setResult(-1);
            finish();
            return;
        }
        com.ecjia.component.view.k kVar = new com.ecjia.component.view.k((Context) this, axVar.d());
        kVar.a(17, 0, 0);
        kVar.a();
    }
}
