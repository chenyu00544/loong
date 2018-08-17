package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.d;
import com.ecjia.a.g;
import com.ecjia.a.i;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.j;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bu;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.pro.x;
import com.umeng.message.PushAgent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.text.SimpleDateFormat;

public class ECJiaSettingActivity extends a implements OnClickListener, a {
    private TextView a;
    private ImageView b;
    private LinearLayout c;
    private TextView d;
    private TextView e;
    private TextView k;
    private TextView l;
    private LinearLayout m;
    private LinearLayout n;
    private LinearLayout o;
    private LinearLayout p;
    private LinearLayout q;
    private SharedPreferences r;
    private SharedPreferences s;
    private Editor t;
    private Resources u;
    private c v;
    private View w;
    private LinearLayout x;
    private TextView y;
    private Handler z = new ECJiaSettingActivity_1(this);

    class ECJiaSettingActivity_1 extends Handler {
        final /* synthetic */ ECJiaSettingActivity a;

        ECJiaSettingActivity_1(ECJiaSettingActivity eCJiaSettingActivity) {
            this.a = eCJiaSettingActivity;
        }

        public void handleMessage(Message message) {
            this.a.l.setText(message.obj.toString());
        }
    }

    class ECJiaSettingActivity_2 extends Thread {
        final /* synthetic */ ECJiaSettingActivity a;

        ECJiaSettingActivity_2(ECJiaSettingActivity eCJiaSettingActivity) {
            this.a = eCJiaSettingActivity;
        }

        public void run() {
            Message message = new Message();
            try {
                message.obj = i.a("sdcard/Android/data/com.ecmoban.android.missmall/cache");
            } catch (Exception e) {
            }
            this.a.z.sendMessage(message);
        }
    }

    class ECJiaSettingActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaSettingActivity a;

        ECJiaSettingActivity_5(ECJiaSettingActivity eCJiaSettingActivity) {
            this.a = eCJiaSettingActivity;
        }

        public void onClick(View view) {
            this.a.v.b();
            this.a.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + j.a().a.d())));
        }
    }

    class ECJiaSettingActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaSettingActivity a;

        ECJiaSettingActivity_6(ECJiaSettingActivity eCJiaSettingActivity) {
            this.a = eCJiaSettingActivity;
        }

        public void onClick(View view) {
            this.a.v.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        PackageInfo packageInfo;
        super.onCreate(bundle);
        setContentView(R.layout.setting);
        PushAgent.getInstance(this).onAppStart();
        de.greenrobot.event.c.a().a((Object) this);
        this.r = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.t = this.r.edit();
        this.s = PreferenceManager.getDefaultSharedPreferences(this);
        if (j.a() == null || j.a().a == null) {
            j jVar = new j(this);
            jVar.a((a) this);
            jVar.b();
        }
        this.a = (TextView) findViewById(R.id.top_view_text);
        this.u = getBaseContext().getResources();
        this.a.setText(this.u.getString(R.string.setting));
        this.b = (ImageView) findViewById(R.id.top_view_back);
        this.b.setOnClickListener(this);
        this.c = (LinearLayout) findViewById(R.id.setting_type1);
        this.l = (TextView) findViewById(R.id.setting_cachesize);
        new ECJiaSettingActivity_2(this).start();
        this.x = (LinearLayout) findViewById(R.id.setting_language);
        this.y = (TextView) findViewById(R.id.used_language);
        this.u.getConfiguration();
        if ("zh".equalsIgnoreCase(this.s.getString(x.F, null))) {
            this.y.setText(getResources().getString(R.string.Chinese));
        } else if (SocializeProtocolConstants.PROTOCOL_KEY_EN.equalsIgnoreCase(this.s.getString(x.F, null))) {
            this.y.setText(getResources().getString(R.string.English));
        } else {
            this.y.setText(getResources().getString(R.string.local));
        }
        this.n = (LinearLayout) findViewById(R.id.setting_mobile_layout);
        this.o = (LinearLayout) findViewById(R.id.setting_version_layout);
        this.p = (LinearLayout) findViewById(R.id.setting_version_update);
        this.q = (LinearLayout) findViewById(R.id.setting_new_function);
        this.w = findViewById(R.id.new_function_line);
        this.q.setVisibility(8);
        this.w.setVisibility(8);
        this.n.setOnClickListener(this);
        this.d = (TextView) findViewById(R.id.setting_mobile);
        this.e = (TextView) findViewById(R.id.setting_version);
        this.k = (TextView) findViewById(R.id.setting_version_date);
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        this.e.setText(packageInfo.versionName);
        try {
            this.k.setText(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(packageInfo.versionCode + "")));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!(j.a().a == null || j.a().a.d() == null)) {
            this.d.setText(j.a().a.d());
        }
        this.m = (LinearLayout) findViewById(R.id.setting_official_web);
        this.c.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.m.setOnClickListener(this);
    }

    public void onClick(View view) {
        String string = this.u.getString(R.string.setting_website);
        this.u.getString(R.string.setting_tech);
        String string2 = this.u.getString(R.string.setting_call_or_not);
        String string3 = this.u.getString(R.string.setting_call_cannot_empty);
        String string4 = this.u.getString(R.string.goodlist_network_problem);
        this.u.getString(R.string.setting_zoo_introduce);
        k kVar;
        switch (view.getId()) {
            case R.id.setting_language:
                startActivity(new Intent(this, ECJiaLanguageActivity.class));
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.setting_type1:
                final c cVar = new c(this, this.u.getString(R.string.point), this.u.getString(R.string.setting_clear_notify));
                cVar.a(2);
                cVar.c(new OnClickListener(this) {
                    final /* synthetic */ ECJiaSettingActivity b;

                    public void onClick(View view) {
                        cVar.b();
                    }
                });
                cVar.b(new OnClickListener(this) {
                    final /* synthetic */ ECJiaSettingActivity b;

                    class ECJiaSettingActivity_4_1 implements Runnable {
                        final /* synthetic */ ECJiaSettingActivity_4 a;

                        ECJiaSettingActivity_4_1(ECJiaSettingActivity_4 eCJiaSettingActivity_4) {
                            this.a = eCJiaSettingActivity_4;
                        }

                        public void run() {
                            Message message = new Message();
                            try {
                                g.a("sdcard/Android/data/com.ecmoban.android.missmall/cache", false);
                                new bu(this.a.b).b();
                                message.obj = i.a("sdcard/Android/data/com.ecmoban.android.missmall/cache");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            this.a.b.z.sendMessage(message);
                        }
                    }

                    public void onClick(View view) {
                        cVar.b();
                        new Thread(new ECJiaSettingActivity_4_1(this)).start();
                    }
                });
                cVar.a();
                return;
            case R.id.setting_mobile_layout:
                if (org.apache.commons.lang3.c.b(this.d.getText().toString())) {
                    this.v = new c(this, string2, j.a().a.d());
                    this.v.a();
                    this.v.b.setOnClickListener(new ECJiaSettingActivity_5(this));
                    this.v.d.setOnClickListener(new ECJiaSettingActivity_6(this));
                    return;
                }
                kVar = new k((Context) this, string3);
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case R.id.setting_official_web:
                if (!d.a(this) || j.a().a == null) {
                    kVar = new k((Context) this, string4);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                Intent intent = new Intent(this, ECJiaWebViewActivity.class);
                intent.putExtra("url", j.a().a.e());
                intent.putExtra("title", string);
                startActivity(intent);
                return;
            case R.id.setting_new_function:
                this.r = getSharedPreferences(Constants.KEY_USER_ID, 0);
                this.t = this.r.edit();
                this.t.putBoolean("isSettingGo", true);
                this.t.commit();
                startActivity(new Intent(this, ECJiaGalleryImageActivity.class));
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.setting_version_update:
                startActivity(new Intent(this, ECJiaUpdateActivity.class));
                return;
            case R.id.top_view_back:
                finish();
                return;
            default:
                return;
        }
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(b bVar) {
        if ("changelanguage".equals(bVar.c())) {
            q.a("运行==");
            this.v = null;
            finish();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("shop/config") && axVar.b() == 1 && j.a().a != null && j.a().a.d() != null) {
            this.d.setText(j.a().a.d());
        }
    }
}
