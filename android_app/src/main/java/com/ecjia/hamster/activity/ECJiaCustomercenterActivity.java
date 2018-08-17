package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.a.u;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.al;
import com.ecjia.component.view.b;
import com.ecjia.component.view.c;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ECJiaCustomercenterActivity extends a implements OnClickListener, a {
    private b A;
    private ImageView B;
    private Handler C;
    private al D;
    private String E;
    private Bitmap F;
    private String G;
    private String H;
    private LinearLayout I;
    private TextView J;
    private boolean K = true;
    private PrintStream L = null;
    String a;
    String b;
    String c;
    Resources d;
    d e;
    private LinearLayout k;
    private LinearLayout l;
    private LinearLayout m;
    private SharedPreferences n;
    private Editor o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private c u;
    private ImageView v;
    private LinearLayout w;
    private LinearLayout x;
    private Bitmap y = null;
    private LinearLayout z;

    class ECJiaCustomercenterActivity_1 extends Handler {
        final /* synthetic */ ECJiaCustomercenterActivity a;

        ECJiaCustomercenterActivity_1(ECJiaCustomercenterActivity eCJiaCustomercenterActivity) {
            this.a = eCJiaCustomercenterActivity;
        }

        public void handleMessage(Message message) {
            if (message.obj.equals("save_profile_succeed") && message.what == 0) {
                u.a().b();
                this.a.F = u.a().b(this.a.E);
                this.a.B.setImageBitmap(this.a.F);
                de.greenrobot.event.c.a().c(new com.ecjia.a.a.b("USER_CHANGE_PHOTO"));
                this.a.e.dismiss();
            }
        }
    }

    class ECJiaCustomercenterActivity_2 implements u.a {
        final /* synthetic */ ECJiaCustomercenterActivity a;

        ECJiaCustomercenterActivity_2(ECJiaCustomercenterActivity eCJiaCustomercenterActivity) {
            this.a = eCJiaCustomercenterActivity;
        }

        public void a() {
            this.a.c();
        }

        public void b() {
            q.a("下载失败");
        }
    }

    class ECJiaCustomercenterActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaCustomercenterActivity a;

        ECJiaCustomercenterActivity_3(ECJiaCustomercenterActivity eCJiaCustomercenterActivity) {
            this.a = eCJiaCustomercenterActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaChangePasswordActivity.class));
        }
    }

    class ECJiaCustomercenterActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaCustomercenterActivity a;

        ECJiaCustomercenterActivity_4(ECJiaCustomercenterActivity eCJiaCustomercenterActivity) {
            this.a = eCJiaCustomercenterActivity;
        }

        public void onClick(View view) {
            this.a.u.b();
            this.a.D.b();
        }
    }

    class ECJiaCustomercenterActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaCustomercenterActivity a;

        ECJiaCustomercenterActivity_5(ECJiaCustomercenterActivity eCJiaCustomercenterActivity) {
            this.a = eCJiaCustomercenterActivity;
        }

        public void onClick(View view) {
            this.a.u.b();
        }
    }

    class ECJiaCustomercenterActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaCustomercenterActivity a;

        ECJiaCustomercenterActivity_6(ECJiaCustomercenterActivity eCJiaCustomercenterActivity) {
            this.a = eCJiaCustomercenterActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory(), this.a.E + ".jpg")));
            this.a.startActivityForResult(intent, 2);
            this.a.A.b();
        }
    }

    class ECJiaCustomercenterActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaCustomercenterActivity a;

        ECJiaCustomercenterActivity_7(ECJiaCustomercenterActivity eCJiaCustomercenterActivity) {
            this.a = eCJiaCustomercenterActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), 1);
            this.a.A.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_customercenter);
        de.greenrobot.event.c.a().a((Object) this);
        this.D = new al(this);
        this.D.a((a) this);
        PushAgent.getInstance(this).onAppStart();
        getIntent();
        this.n = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.E = this.n.getString("uid", "");
        this.G = this.n.getString(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME, "");
        q.a(this.G + "=============================");
        this.H = this.n.getString("level", "");
        q.a(this.H + "=============================");
        this.o = this.n.edit();
        this.d = getBaseContext().getResources();
        this.b = this.d.getString(R.string.exit);
        this.a = this.d.getString(R.string.ensure_exit);
        this.c = this.d.getString(R.string.custormercenter);
        this.C = new ECJiaCustomercenterActivity_1(this);
        e();
        c();
        u.a().a(new ECJiaCustomercenterActivity_2(this));
    }

    private void c() {
        this.F = u.a().b(this.E);
        if (this.F != null) {
            this.B.setImageBitmap(this.F);
        } else {
            this.B.setImageResource(R.drawable.profile_no_avarta_icon_light);
        }
    }

    private void e() {
        this.I = (LinearLayout) findViewById(R.id.ll_username);
        this.p = (TextView) findViewById(R.id.top_view_text);
        this.p.setText(this.c);
        this.k = (LinearLayout) findViewById(R.id.setting_exitLogin);
        this.k.setOnClickListener(this);
        this.v = (ImageView) findViewById(R.id.top_view_back);
        this.v.setOnClickListener(this);
        this.w = (LinearLayout) findViewById(R.id.change_password);
        this.w.setOnClickListener(new ECJiaCustomercenterActivity_3(this));
        this.l = (LinearLayout) findViewById(R.id.ll_phone);
        this.m = (LinearLayout) findViewById(R.id.ll_email);
        this.x = (LinearLayout) findViewById(R.id.real_name_verify);
        this.m.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.s = (TextView) findViewById(R.id.customercenter_phone);
        this.t = (TextView) findViewById(R.id.customercenter_email);
        this.J = (TextView) findViewById(R.id.customercenter_usernames);
        this.q = (TextView) findViewById(R.id.customercenter_username);
        this.r = (TextView) findViewById(R.id.customercenter_level);
        if (org.apache.commons.lang3.c.b(this.H)) {
            q.a("执行了");
            this.r.setText(this.H);
        }
        this.z = (LinearLayout) findViewById(R.id.customercenter_layout_img);
        this.z.setOnClickListener(this);
        this.B = (ImageView) findViewById(R.id.customercenter_img);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.customercenter_layout_img:
                this.A = new b(this);
                this.A.a.setOnClickListener(new ECJiaCustomercenterActivity_6(this));
                this.A.b.setOnClickListener(new ECJiaCustomercenterActivity_7(this));
                this.A.a();
                return;
            case R.id.ll_username:
                startActivityForResult(new Intent(this, ECJiaChangeUsernameActivity.class), 101);
                return;
            case R.id.ll_phone:
                if (this.h.e() == null || TextUtils.isEmpty(this.h.e().m()) || this.h.e().t() != 0) {
                    intent = new Intent(this, ECJiaShowPhoneActivity.class);
                    intent.putExtra("type", "user_modify_mobile");
                    startActivityForResult(intent, 110);
                    return;
                }
                intent = new Intent(this, ECJiaBindingPhoneActivity.class);
                intent.putExtra("type", "user_modify_mobile");
                startActivityForResult(intent, 110);
                return;
            case R.id.ll_email:
                if (this.t.getText().toString().equals("未绑定")) {
                    intent = new Intent(this, ECJiaBindingPhoneActivity.class);
                    intent.putExtra("type", "user_modify_mail");
                    startActivityForResult(intent, 110);
                    return;
                }
                intent = new Intent(this, ECJiaShowPhoneActivity.class);
                intent.putExtra("type", "user_modify_mail");
                startActivityForResult(intent, 110);
                return;
            case R.id.real_name_verify:
                if (this.h.e() != null && !TextUtils.isEmpty(this.h.e().m()) && this.h.e().t() == 0) {
                    new k((Context) this, (int) R.string.real_name_verify_bind).a();
                    intent = new Intent(this, ECJiaBindingPhoneActivity.class);
                    intent.putExtra("type", "user_modify_mobile");
                    startActivityForResult(intent, 110);
                    return;
                } else if (this.h.e() != null && !TextUtils.isEmpty(this.h.e().m()) && this.h.e().s() == 0) {
                    startActivity(new Intent(this, ECJiaRealNameVerifyActivity.class));
                    return;
                } else if (this.h.e() != null && !TextUtils.isEmpty(this.h.e().m())) {
                    intent = new Intent(this, ECJiaVerifyScheduleActivity.class);
                    intent.putExtra("real_id", this.h.e().u().a());
                    startActivity(intent);
                    return;
                } else {
                    return;
                }
            case R.id.setting_exitLogin:
                this.u = new c(this, this.b, this.a);
                this.u.a(2);
                this.u.b(new ECJiaCustomercenterActivity_4(this));
                this.u.c(new ECJiaCustomercenterActivity_5(this));
                this.u.a();
                return;
            case R.id.top_view_back:
                finish();
                return;
            default:
                return;
        }
    }

    void b() {
        q.a("setinfo已启动");
        if (TextUtils.isEmpty(this.D.a.a())) {
            this.q.setText("暂无");
        } else {
            this.q.setText(this.D.a.a());
        }
        this.J.setText(this.D.a.p());
        if (TextUtils.isEmpty(this.D.a.h())) {
            this.t.setText("未绑定");
        } else {
            this.t.setText(this.D.a.h());
        }
        if (this.h.e() != null && !TextUtils.isEmpty(this.h.e().m()) && this.h.e().t() == 0) {
            this.s.setText("未绑定");
        } else if (!(this.h.e() == null || TextUtils.isEmpty(this.h.e().m()))) {
            this.s.setText(this.D.a.g());
        }
        this.s.setText(this.D.d);
        this.t.setText(this.D.e);
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 1:
                if (i2 == -1 && intent != null) {
                    a(intent.getData());
                    break;
                }
            case 2:
                if (i2 == -1) {
                    a(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/" + this.E + ".jpg")));
                    break;
                }
                break;
            case 3:
                q.a("我被执行1");
                if (i2 == -1) {
                    q.a("我被执行2");
                    if (intent != null) {
                        q.a("我被执行3");
                        this.e = d.a((Context) this);
                        this.e.setCancelable(false);
                        this.e.show();
                        a(intent);
                        break;
                    }
                }
                break;
            case 101:
                if (!(i2 != -1 || this.h.e() == null || TextUtils.isEmpty(this.h.e().m()))) {
                    this.q.setText(this.h.e().a());
                    break;
                }
            case 110:
                this.D.a();
                break;
        }
        super.onActivityResult(i, i2, intent);
    }

    public void a(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 3);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.y = (Bitmap) extras.getParcelable("data");
            this.D.a(com.ecjia.hamster.paycenter.a.a.a.a(a(this.y)));
        }
    }

    public byte[] a(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    protected void onResume() {
        super.onResume();
        this.D.a();
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        if ("exsit".equals(bVar.c())) {
            finish();
        }
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case 252601229:
                if (str.equals("user/update")) {
                    z = false;
                    break;
                }
                break;
            case 294875250:
                if (str.equals("user/info")) {
                    z = true;
                    break;
                }
                break;
            case 1563413037:
                if (str.equals("user/signout")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.B.setImageBitmap(this.y);
                    u.a().a(this.y, this.E, this.C);
                    return;
                } else if (axVar.b() == 2) {
                    this.e.dismiss();
                    return;
                } else {
                    return;
                }
            case true:
                this.o.putString("uid", "");
                this.o.putString("sid", "");
                this.o.putString("qq_id", "");
                this.o.putString("myscreen_name", "");
                this.o.putString("wx_id", "");
                this.o.putString("nick_name", "");
                this.o.commit();
                de.greenrobot.event.c.a().c(new com.ecjia.a.a.b("exsit"));
                finish();
                return;
            case true:
                if (this.h.e().q() != null && this.K) {
                    this.K = false;
                    u.a().a(this.h.e().q(), this.E);
                }
                b();
                return;
            default:
                return;
        }
    }
}
