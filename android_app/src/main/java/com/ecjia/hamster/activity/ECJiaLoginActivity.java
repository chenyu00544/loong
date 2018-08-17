package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ecjia.a.a.b;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.u;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.d;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.k;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.taobao.accs.common.Constants;
import com.tencent.open.GameAppOperation;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import de.greenrobot.event.c;
import java.util.Map;
import java.util.Map.Entry;

public class ECJiaLoginActivity extends a implements TextWatcher, OnClickListener, a {
    private k A;
    private RelativeLayout B;
    private int C;
    private ImageView D;
    private String E;
    private String F;
    private boolean G = false;
    private int H = 0;
    private String I;
    SharedPreferences a;
    SharedPreferences b;
    Editor c;
    Editor d;
    UMShareAPI e;
    private Button k;
    private EditText l;
    private EditText m;
    private CheckBox n;
    private String o;
    private String p;
    private u q;
    private TextView r;
    private String s = "";
    private ImageView t;
    private ImageView u;
    private LinearLayout v;
    private d w;
    private LinearLayout x;
    private View y;
    private ImageView z;

    class ECJiaLoginActivity_1 implements ImageLoadingListener {
        final /* synthetic */ ECJiaLoginActivity a;

        ECJiaLoginActivity_1(ECJiaLoginActivity eCJiaLoginActivity) {
            this.a = eCJiaLoginActivity;
        }

        public void onLoadingStarted(String str, View view) {
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
            this.a.z.setVisibility(0);
            this.a.z.setImageResource(R.drawable.login_defaultbg);
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            this.a.z.setVisibility(0);
        }

        public void onLoadingCancelled(String str, View view) {
        }
    }

    class ECJiaLoginActivity_2 implements OnCheckedChangeListener {
        final /* synthetic */ ECJiaLoginActivity a;

        ECJiaLoginActivity_2(ECJiaLoginActivity eCJiaLoginActivity) {
            this.a = eCJiaLoginActivity;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                this.a.m.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                this.a.m.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (this.a.m.length() > 0) {
                this.a.m.setSelection(this.a.m.length());
            }
        }
    }

    class ECJiaLoginActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaLoginActivity a;

        ECJiaLoginActivity_3(ECJiaLoginActivity eCJiaLoginActivity) {
            this.a = eCJiaLoginActivity;
        }

        public void onClick(View view) {
            this.a.finish();
            this.a.b();
            this.a.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
    }

    class ECJiaLoginActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaLoginActivity a;

        ECJiaLoginActivity_4(ECJiaLoginActivity eCJiaLoginActivity) {
            this.a = eCJiaLoginActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaGetCodeActivity.class), 1);
        }
    }

    class ECJiaLoginActivity_5 implements UMAuthListener {
        final /* synthetic */ ECJiaLoginActivity a;

        ECJiaLoginActivity_5(ECJiaLoginActivity eCJiaLoginActivity) {
            this.a = eCJiaLoginActivity;
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            Toast.makeText(this.a, "授权失败", 0).show();
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            if (map != null) {
                if (com.ecjia.consts.a.c) {
                    for (Entry entry : map.entrySet()) {
                        Object key = entry.getKey();
                        q.b("key=" + key + " value=" + entry.getValue());
                    }
                }
                if (share_media == SHARE_MEDIA.QQ) {
                    this.a.q.a((String) map.get("access_token"));
                    this.a.I = (String) map.get("openid");
                    return;
                } else if (share_media != SHARE_MEDIA.WEIXIN) {
                    return;
                } else {
                    if (!TextUtils.isEmpty(this.a.b.getString("wx_id", "")) && (this.a.b.getString("wx_id", "").equals(map.get("openid")) || this.a.b.getString("wx_id", "").equals(map.get(GameAppOperation.GAME_UNION_ID)))) {
                        this.a.a(this.a.b.getString("nick_name", ""), this.a.b.getString("wx_id", ""), "sns_wechat");
                        return;
                    } else if (TextUtils.isEmpty((CharSequence) map.get(GameAppOperation.GAME_UNION_ID))) {
                        this.a.a(share_media, (String) map.get("openid"));
                        return;
                    } else {
                        this.a.a(share_media, (String) map.get(GameAppOperation.GAME_UNION_ID));
                        return;
                    }
                }
            }
            Toast.makeText(this.a, "授权失败", 0).show();
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.login);
        a();
        c.a().a((Object) this);
        this.A = this.h.d();
        this.e = UMShareAPI.get(this);
        this.z = (ImageView) findViewById(R.id.login_view);
        this.z.setVisibility(4);
        ImageLoader.getInstance().displayImage("file:///sdcard/android/data/com.ecmoban.android.missmall/login_bg", this.z, new ECJiaLoginActivity_1(this));
        this.B = (RelativeLayout) findViewById(R.id.user_img_item);
        this.v = (LinearLayout) findViewById(R.id.ll_outer_login);
        if (this.w == null) {
            this.w = d.a((Context) this);
            this.w.a(this.g.getString(R.string.loading));
        }
        this.v.setVisibility(0);
        this.D = (ImageView) findViewById(R.id.user_head_img);
        this.q = new u(this);
        this.q.a((a) this);
        this.x = (LinearLayout) findViewById(R.id.root_view);
        this.y = findViewById(R.id.buttom_view);
        this.t = (ImageView) findViewById(R.id.qq_login);
        this.u = (ImageView) findViewById(R.id.wx_login);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.s = getIntent().getStringExtra("from");
        this.k = (Button) findViewById(R.id.login_login);
        this.l = (EditText) findViewById(R.id.login_name);
        this.m = (EditText) findViewById(R.id.login_password);
        this.l.addTextChangedListener(this);
        this.m.addTextChangedListener(this);
        this.r = (TextView) findViewById(R.id.login_getpassword);
        this.k.setOnClickListener(this);
        this.r.setOnClickListener(this);
        if (this.A == null || TextUtils.isEmpty(this.A.b())) {
            this.C = Color.parseColor("#ff999999");
        } else {
            this.C = Color.parseColor(this.A.b());
        }
        this.l.setHint(this.g.getString(R.string.login_username2));
        this.n = (CheckBox) findViewById(R.id.login_show_pwd);
        this.n.setOnCheckedChangeListener(new ECJiaLoginActivity_2(this));
        this.b = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.E = this.b.getString(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME, "");
        this.F = this.b.getString("local_uid", "");
        this.d = this.b.edit();
        this.a = getPreferences(32768);
        this.c = this.a.edit();
        this.l.setText(this.a.getString("name", ""));
        a(this.x, this.y);
        a(this.G);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.login_topview);
        this.i.setBackgroundColor(Color.parseColor("#00000000"));
        this.i.setTitleText((int) R.string.login_login);
        this.i.setLeftBackImage((int) R.drawable.login_back, new ECJiaLoginActivity_3(this));
        this.i.setRightType(11);
        this.i.setRightText((int) R.string.login_register, new ECJiaLoginActivity_4(this));
    }

    public void onClick(View view) {
        String string = this.g.getString(R.string.register_user_name_cannot_be_empty);
        String string2 = this.g.getString(R.string.register_password_cannot_be_empty);
        this.g.getString(R.string.check_the_network);
        switch (view.getId()) {
            case R.id.login_login:
                this.o = this.l.getText().toString();
                this.p = this.m.getText().toString();
                if ("".equals(this.o)) {
                    com.ecjia.component.view.k kVar = new com.ecjia.component.view.k((Context) this, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else if ("".equals(this.p)) {
                    com.ecjia.component.view.k kVar2 = new com.ecjia.component.view.k((Context) this, string2);
                    kVar2.a(17, 0, 0);
                    kVar2.a();
                    return;
                } else {
                    this.c.putString("name", this.l.getText().toString());
                    this.c.commit();
                    this.q.b(this.o, this.p);
                    b();
                    return;
                }
            case R.id.login_getpassword:
                startActivity(new Intent(this, ECJiaGetPasswordActivity.class));
                return;
            case R.id.qq_login:
                a(SHARE_MEDIA.QQ);
                return;
            case R.id.wx_login:
                a(SHARE_MEDIA.WEIXIN);
                return;
            default:
                return;
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.w.isShowing()) {
            this.w.dismiss();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.e.onActivityResult(i, i2, intent);
        Intent intent2;
        if (i == 1) {
            if (intent != null) {
                intent2 = new Intent();
                intent2.putExtra("login", true);
                setResult(-1, intent2);
                finish();
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }
        } else if (i == 1001 && i2 == -1) {
            String string = this.g.getString(R.string.login_welcome);
            this.d.putBoolean("thirdLog", true);
            this.d.commit();
            com.ecjia.component.view.k kVar = new com.ecjia.component.view.k((Context) this, string);
            kVar.a(17, 0, 0);
            kVar.a();
            c.a().c(new b("refresh_price"));
            intent2 = new Intent();
            intent2.putExtra("login", true);
            setResult(-1, intent2);
            finish();
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
        return true;
    }

    public void b() {
        this.l.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.l.getWindowToken(), 0);
    }

    private void a(SHARE_MEDIA share_media) {
        this.e.doOauthVerify(this, share_media, new ECJiaLoginActivity_5(this));
    }

    private void a(String str, String str2, String str3) {
        this.q.a(str, str2, str3);
    }

    private void a(final SHARE_MEDIA share_media, final String str) {
        this.e.getPlatformInfo(this, share_media, new UMAuthListener(this) {
            final /* synthetic */ ECJiaLoginActivity c;

            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (i != 2 || map == null) {
                    q.a("获取用户信息失败");
                    return;
                }
                if (com.ecjia.consts.a.c) {
                    for (Entry entry : map.entrySet()) {
                        Object key = entry.getKey();
                        q.b("key2=" + key + " value2=" + entry.getValue());
                    }
                }
                if (share_media == SHARE_MEDIA.QQ) {
                    this.c.d.putString("myscreen_name", ((String) map.get("screen_name")).toString());
                    this.c.d.putString("qq_log_img", ((String) map.get(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_ICON)).toString());
                    this.c.d.putString("qq_id", str);
                    this.c.d.commit();
                    this.c.a(((String) map.get("screen_name")).toString(), str, "sns_qq");
                } else if (share_media == SHARE_MEDIA.WEIXIN) {
                    this.c.d.putString("nick_name", ((String) map.get("nickname")).toString());
                    this.c.d.putString("wx_log_img", ((String) map.get("headimgurl")).toString());
                    this.c.d.putString("wx_id", str);
                    this.c.d.commit();
                    this.c.a(((String) map.get("nickname")).toString(), str, "sns_wechat");
                }
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            }

            public void onCancel(SHARE_MEDIA share_media, int i) {
            }
        });
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(b bVar) {
        if ("frommobile".equals(bVar.c())) {
            Intent intent = new Intent();
            intent.putExtra("login", true);
            setResult(-1, intent);
            finish();
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
    }

    public void a(final View view, final View view2) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ ECJiaLoginActivity c;

            public void onGlobalLayout() {
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                if (view.getRootView().getHeight() - rect.bottom > 100) {
                    int[] iArr = new int[2];
                    view2.getLocationInWindow(iArr);
                    int height = (iArr[1] + view2.getHeight()) - rect.bottom;
                    if (height > this.c.H) {
                        this.c.H = height;
                        view.scrollTo(0, height);
                        this.c.B.setVisibility(4);
                    }
                } else {
                    view.scrollTo(0, 0);
                    this.c.H = 0;
                    this.c.B.setVisibility(0);
                }
                this.c.a(this.c.G);
            }
        });
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.l.getText().toString().length() <= 0 || this.m.getText().toString().length() < 1) {
            this.k.setEnabled(false);
            this.k.setTextColor(this.C);
            this.k.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.k.setEnabled(true);
        this.k.setTextColor(Color.parseColor("#ffffffff"));
        this.k.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.l.getText().toString().length() <= 0) {
            this.G = false;
        } else if (!this.E.equals(this.l.getText().toString())) {
            this.G = false;
        } else if (this.F != "") {
            this.G = true;
        } else {
            this.G = false;
        }
        if (this.l.getText().toString().length() <= 0 || this.m.getText().toString().length() < 1) {
            this.k.setEnabled(false);
            this.k.setTextColor(this.C);
            this.k.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.k.setEnabled(true);
        this.k.setTextColor(Color.parseColor("#ffffffff"));
        this.k.setBackgroundResource(R.drawable.selector_login_button);
    }

    public void afterTextChanged(Editable editable) {
        if (this.l.getText().toString().length() <= 0 || this.m.getText().toString().length() < 1) {
            this.k.setEnabled(false);
            this.k.setTextColor(this.C);
            this.k.setBackgroundResource(R.drawable.shape_unable);
            return;
        }
        this.k.setEnabled(true);
        this.k.setTextColor(Color.parseColor("#ffffffff"));
        this.k.setBackgroundResource(R.drawable.selector_login_button);
    }

    private void a(boolean z) {
        if (!z) {
            this.D.setImageBitmap(null);
        } else if (this.F != "") {
            this.D.setImageBitmap(com.ecjia.a.u.a().b(this.F));
            q.b("===runuser_head_img===");
        } else {
            this.D.setImageBitmap(null);
        }
    }

    public void a(String str, String str2, ax axVar) {
        this.g.getString(R.string.login_invalid_password);
        String string = this.g.getString(R.string.login_welcome);
        q.a("===connect/bind返回0===" + str);
        boolean z = true;
        switch (str.hashCode()) {
            case -665416579:
                if (str.equals("https://graph.qq.com/oauth2.0/me?access_token=")) {
                    z = true;
                    break;
                }
                break;
            case 188979814:
                if (str.equals("user/signin")) {
                    z = false;
                    break;
                }
                break;
            case 1445059687:
                if (str.equals("connect/signin")) {
                    z = true;
                    break;
                }
                break;
        }
        com.ecjia.component.view.k kVar;
        Intent intent;
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.d.putBoolean("thirdLog", false);
                    this.d.commit();
                    c.a().c(new b("refresh_price"));
                    c.a().c(new b("avater_refresh"));
                    kVar = new com.ecjia.component.view.k((Context) this, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    if ("cart".equals(this.s)) {
                        startActivity(new Intent(this, ECJiaShoppingCartActivity.class));
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else if ("orders_list".equals(this.s)) {
                        startActivity(new Intent(this, ECJiaOrderListActivity.class));
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else if ("orders_detail".equals(this.s)) {
                        intent = new Intent(this, ECJiaOrderdetailActivity.class);
                        intent.putExtra("orderid", getIntent().getStringExtra("orderid"));
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else if ("user_wallet".equals(this.s)) {
                        startActivity(new Intent(this, ECJiaMyPurseActivity.class));
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else if ("user_address".equals(this.s)) {
                        startActivity(new Intent(this, ECJiaAddressManageActivity.class));
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else if ("user_account".equals(this.s)) {
                        startActivity(new Intent(this, ECJiaAccountActivity.class));
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else if ("user_password".equals(this.s)) {
                        startActivity(new Intent(this, ECJiaChangePasswordActivity.class));
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else if ("user_center".equals(this.s)) {
                        startActivity(new Intent(this, ECJiaCustomercenterActivity.class));
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else if ("qrshare".equals(this.s)) {
                        startActivity(new Intent(this, ECJiaShareQRCodeActivity.class));
                        finish();
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        return;
                    } else {
                        intent = new Intent();
                        intent.putExtra("login", true);
                        setResult(-1, intent);
                        finish();
                        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                        return;
                    }
                }
                kVar = new com.ecjia.component.view.k((Context) this, axVar.d());
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case true:
                if (axVar.b() == 1) {
                    this.d.putBoolean("thirdLog", true);
                    this.d.commit();
                    c.a().c(new b("refresh_price"));
                    c.a().c(new b("avater_refresh"));
                    kVar = new com.ecjia.component.view.k((Context) this, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    intent = new Intent();
                    intent.putExtra("login", true);
                    setResult(-1, intent);
                    finish();
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    return;
                } else if (axVar.e().equals("connect_no_userbind")) {
                    startActivityForResult(new Intent(this, ECJiaOuterLoginBindActivity.class), 1001);
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    return;
                } else {
                    kVar = new com.ecjia.component.view.k((Context) this, axVar.d());
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
            case true:
                if (this.q.d == null || this.q.d == "") {
                    q.a("===connect/bind返回2===2");
                    if (TextUtils.isEmpty(this.b.getString("qq_id", "")) || !this.b.getString("qq_id", "").equals(this.I)) {
                        a(SHARE_MEDIA.QQ, this.I);
                        return;
                    } else {
                        a(this.b.getString("myscreen_name", ""), this.b.getString("qq_id", ""), "sns_qq");
                        return;
                    }
                }
                q.a("===connect/bind返回1===" + this.q.d);
                q.a("===connect/bind返回1===1");
                if (TextUtils.isEmpty(this.b.getString("qq_id", "")) || !this.b.getString("qq_id", "").equals(this.q.d)) {
                    a(SHARE_MEDIA.QQ, this.q.d);
                    return;
                } else {
                    a(this.b.getString("myscreen_name", ""), this.b.getString("qq_id", ""), "sns_qq");
                    return;
                }
            default:
                return;
        }
    }
}
