package com.ecjia.hamster.activity;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.ecjia.a.a.b;
import com.ecjia.a.d;
import com.ecjia.a.l;
import com.ecjia.a.q;
import com.ecjia.a.u;
import com.ecjia.a.y;
import com.ecjia.component.a.ah;
import com.ecjia.component.a.al;
import com.ecjia.component.a.am;
import com.ecjia.component.a.j;
import com.ecjia.component.dragLayout.ECJiaDragLayout;
import com.ecjia.component.service.ECJiaNetworkStateService;
import com.ecjia.component.view.ECJiaScrollView_Main;
import com.ecjia.component.view.k;
import com.ecjia.consts.ECJiaClassName.ActivityName;
import com.ecjia.hamster.fragment.ECJiaTabsFragment;
import com.ecjia.hamster.model.ECJia_VERSION;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.o;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.x;
import com.umeng.message.PushAgent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;

import de.greenrobot.event.c;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ECJiaMainActivity extends AppCompatActivity implements OnClickListener, com.ecjia
        .component.a.a.a, com.ecjia.hamster.fragment.ECJiaTabsFragment.a {
    private com.ecjia.component.a.a A;
    private FrameLayout B;
    private LinearLayout C;
    private View D;
    private View E;
    private ECJiaScrollView_Main F;
    private LinearLayout G;
    private j H;
    private ECJiaTabsFragment I;
    private boolean J = false;
    public ECJiaDragLayout a;
    public Handler b;
    public a c = new a(this);
    public LocationClient d;
    public LocationClientOption e;
    public boolean f = true;
    public Bitmap g;
    Handler h = new ECJiaMainActivity_9(this);
    ArrayList<o> i = new ArrayList();
    ah j;
    private boolean k = false;
    private al l;
    private SharedPreferences m;
    private SharedPreferences n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private ImageView s;
    private ImageView t;
    private LinearLayout u;
    private Resources v;
    private Editor w;
    private boolean x = true;
    private ECJiaApplication y;
    private Location z = null;

    class ECJiaMainActivity_1 implements OnTouchListener {
        final /* synthetic */ ECJiaMainActivity a;

        ECJiaMainActivity_1(ECJiaMainActivity eCJiaMainActivity) {
            this.a = eCJiaMainActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    class ECJiaMainActivity_2 implements com.ecjia.a.u.a {
        final /* synthetic */ ECJiaMainActivity a;

        ECJiaMainActivity_2(ECJiaMainActivity eCJiaMainActivity) {
            this.a = eCJiaMainActivity;
        }

        public void a() {
            this.a.g = u.a().b(this.a.m.getString("uid", ""));
            try {
                this.a.s.setImageBitmap(this.a.g);
                c.a().c(new b("USER_PHOTO_DOWNLOAD_SUCCESS"));
            } catch (Exception e) {
                e.printStackTrace();
                q.a("错误");
            }
            this.a.a(1);
        }

        public void b() {
            q.a("下载失败");
        }
    }

    class ECJiaMainActivity_3 extends Handler {
        final /* synthetic */ ECJiaMainActivity a;

        ECJiaMainActivity_3(ECJiaMainActivity eCJiaMainActivity) {
            this.a = eCJiaMainActivity;
        }

        public void handleMessage(Message message) {
            if (this.a.k) {
                this.a.a.close();
            }
        }
    }

    class ECJiaMainActivity_4 implements am.b {
        final /* synthetic */ ECJiaMainActivity a;

        ECJiaMainActivity_4(ECJiaMainActivity eCJiaMainActivity) {
            this.a = eCJiaMainActivity;
        }

        public void a() {
        }

        public void a(int i, ECJia_VERSION eCJia_VERSION) {
            switch (i) {
                case 1:
                    am.a(this.a, eCJia_VERSION);
                    return;
                default:
                    return;
            }
        }
    }

    class ECJiaMainActivity_5 implements com.ecjia.component.dragLayout.ECJiaDragLayout.a {
        final /* synthetic */ ECJiaMainActivity a;

        ECJiaMainActivity_5(ECJiaMainActivity eCJiaMainActivity) {
            this.a = eCJiaMainActivity;
        }

        public void a() {
            if (TextUtils.isEmpty(this.a.m.getString("uid", ""))) {
                this.a.a(false);
            }
        }

        public void b() {
        }

        public void a(float f) {
            this.a.a(f);
        }
    }

    class ECJiaMainActivity_9 extends Handler {
        final /* synthetic */ ECJiaMainActivity a;

        ECJiaMainActivity_9(ECJiaMainActivity eCJiaMainActivity) {
            this.a = eCJiaMainActivity;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.a.J = false;
        }
    }

    public class a implements BDLocationListener {
        final /* synthetic */ ECJiaMainActivity a;

        public a(ECJiaMainActivity eCJiaMainActivity) {
            this.a = eCJiaMainActivity;
        }

        public void onReceiveLocation(BDLocation bDLocation) {
            if (bDLocation == null) {
                this.a.d();
            } else if (this.a.f) {
                this.a.f = false;
                com.ecjia.consts.b.g[0] = bDLocation.getLongitude();
                com.ecjia.consts.b.g[1] = bDLocation.getLatitude();
                com.ecjia.consts.b.h[0] = bDLocation.getProvince();
                com.ecjia.consts.b.h[1] = bDLocation.getCity();
                com.ecjia.consts.b.h[2] = bDLocation.getAddrStr();
                q.a("运行===");
                if (com.ecjia.consts.b.h[2] != null) {
                    c.a().c(new b("refreshsucceed"));
                } else {
                    c.a().c(new b("refreshfail"));
                }
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.y = (ECJiaApplication) getApplication();
        c.a().a((Object) this);
        this.v = getBaseContext().getResources();
        SDKInitializer.initialize(getApplicationContext());
        k();
        setContentView((int) R.layout.activity_main);
        getSupportActionBar().hide();
        findViewById(R.id.fragment_container).setOnTouchListener(new ECJiaMainActivity_1(this));
        e();
        this.I = (ECJiaTabsFragment) getSupportFragmentManager().findFragmentById(R.id
                .tabs_fragment);
        l();
        this.m = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.w = this.m.edit();
        String string = this.v.getString(R.string.main_no_network);
        if (!d.a(this)) {
            k kVar = new k((Context) this, string);
            kVar.a(17, 0, 0);
            kVar.a();
        }
        startService(new Intent(this, ECJiaNetworkStateService.class));
        PushAgent.getInstance(this).onAppStart();
        h();
        this.A = new com.ecjia.component.a.a(this);
        this.A.a();
        i();
        if (this.l == null) {
            this.l = new al(this);
        }
        u.a().a(new ECJiaMainActivity_2(this));
        this.b = new ECJiaMainActivity_3(this);
        g();
        f();
    }

    private void g() {
        this.H = new j(this);
        this.H.a((com.ecjia.component.a.a.a) this);
        if (this.y.d() == null) {
            this.H.b();
        }
        this.H.c();
    }

    private void h() {
        am.a((Context) this).a(new ECJiaMainActivity_4(this));
        am.a((Context) this).b(this);
    }

    void a() {
        l.b().c();
        this.i.addAll(l.b().d());
    }

    public int b() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", anet.channel
                .strategy.dispatch.c.ANDROID);
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void i() {
        int i = 5;
        this.a = (ECJiaDragLayout) findViewById(R.id.dl);
        this.a.setDragListener(new ECJiaMainActivity_5(this));
        a();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.left_first_group_item);
        for (int i2 = 0; i2 < 5; i2++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_main_left, null);
            inflate.setLayoutParams(new LayoutParams(-1, (int) getResources().getDimension(R
                    .dimen.dp_40)));
            ((ImageView) inflate.findViewById(R.id.main_left_item_image)).setImageResource(((o)
                    this.i.get(i2)).d());
            ((TextView) inflate.findViewById(R.id.main_left_item_text)).setText(((o) this.i.get
                    (i2)).f());
            inflate.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaMainActivity b;

                public void onClick(View view) {
                    this.b.b(i2);
                }
            });
            linearLayout.addView(inflate);
        }
        linearLayout = (LinearLayout) findViewById(R.id.left_second_group_item);
        while (i < this.i.size()) {
            View inflate2 = LayoutInflater.from(this).inflate(R.layout.item_main_left, null);
            inflate2.setLayoutParams(new LayoutParams(-1, (int) getResources().getDimension(R
                    .dimen.dp_40)));
            ((ImageView) inflate2.findViewById(R.id.main_left_item_image)).setImageResource(((o)
                    this.i.get(i)).d());
            ((TextView) inflate2.findViewById(R.id.main_left_item_text)).setText(((o) this.i.get
                    (i)).f());
            inflate2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaMainActivity b;

                public void onClick(View view) {
                    if (!((o) this.b.i.get(i)).a().equals("feedback") || !com.ecjia.consts.a.b) {
                        this.b.b(i);
                    } else if (this.b.y.e() == null || TextUtils.isEmpty(this.b.y.e().m())) {
                        this.b.startActivity(new Intent(this.b, ECJiaLoginActivity.class));
                        this.b.overridePendingTransition(R.anim.push_buttom_in, R.anim
                                .push_buttom_out);
                        k kVar = new k(this.b, this.b.getResources().getString(R.string.no_login));
                        kVar.a(17);
                        kVar.a();
                    } else {
                        String str = "http://www.missmall.com/mobile/index.php?m=chat" +
                                "&origin=app&openid=" + this.b.y.e().r() + "&token=" + this.b.y.e
                                ().b();
                        Intent intent = new Intent(this.b, ECJiaWebViewActivity.class);
                        intent.putExtra("url", str);
                        intent.putExtra("title", "");
                        this.b.startActivity(intent);
                        this.b.overridePendingTransition(R.anim.push_right_in, R.anim
                                .push_right_out);
                    }
                }
            });
            linearLayout.addView(inflate2);
            i++;
        }
        this.u = (LinearLayout) this.a.findViewById(R.id.profile_head);
        this.B = (FrameLayout) this.a.findViewById(R.id.profile_head_text);
        this.C = (LinearLayout) this.a.findViewById(R.id.profile_setting_ll);
        this.o = (TextView) this.a.findViewById(R.id.profile_newset);
        this.t = (ImageView) this.a.findViewById(R.id.profile_setting);
        this.s = (ImageView) this.a.findViewById(R.id.profile_newuser_img);
        this.p = (TextView) this.a.findViewById(R.id.user_name);
        this.q = (TextView) this.a.findViewById(R.id.user_level);
        this.r = (TextView) this.a.findViewById(R.id.no_login);
        this.r.setVisibility(0);
        this.u.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.C.setOnClickListener(this);
        findViewById(R.id.myfind_home_item).setOnClickListener(this);
        this.D = findViewById(R.id.main_below_background);
        this.E = findViewById(R.id.myfind_top_empty);
        this.E.getLayoutParams().height = (int) ((((float) getWindowManager().getDefaultDisplay()
                .getWidth()) * 9.5f) / 32.0f);
        final int height = getWindowManager().getDefaultDisplay().getHeight() - b();
        findViewById(R.id.main_below_top).setVisibility(8);
        int width = (getWindowManager().getDefaultDisplay().getWidth() * 8) / 32;
        q.a("height:" + height);
        final float width2 = (float) ((getWindowManager().getDefaultDisplay().getWidth() * 8) / 32);
        final float width3 = (float) ((getWindowManager().getDefaultDisplay().getWidth() * 7) / 32);
        com.nineoldandroids.b.a.a(this.u, 0.0f);
        com.nineoldandroids.b.a.b(this.u, 0.0f);
        com.nineoldandroids.b.a.a(this.B, 0.0f);
        com.nineoldandroids.b.a.b(this.B, 0.0f);
        this.F = (ECJiaScrollView_Main) findViewById(R.id.main_sv);
        this.F.setOnScrollListener(new com.ecjia.component.view.ECJiaScrollView_Main.a(this) {
            final /* synthetic */ ECJiaMainActivity d;

            @TargetApi(11)
            public void a(int i, int i2, int i3, int i4) {
                q.a("l:" + i + "  t:" + i2 + "  oldl:" + i3 + "  odlt:" + i4 + "  scaleY:" + this
                        .d.s.getScaleY() + "  sv.getScrollY()+" + this.d.F.getScrollY());
                if (((float) this.d.F.getScrollY()) <= width2) {
                    com.nineoldandroids.b.a.a(this.d.s, 0.0f);
                    com.nineoldandroids.b.a.b(this.d.s, width3 / 2.0f);
                    com.nineoldandroids.b.a.d(this.d.s, 1.0f - ((((float) this.d.F.getScrollY())
                            / width2) * 0.4f));
                    com.nineoldandroids.b.a.e(this.d.s, 1.0f - ((((float) this.d.F.getScrollY())
                            / width2) * 0.4f));
                    com.nineoldandroids.b.a.d(this.d.B, 1.0f - ((((float) this.d.F.getScrollY())
                            / width2) * 0.2f));
                    com.nineoldandroids.b.a.e(this.d.B, 1.0f - ((((float) this.d.F.getScrollY())
                            / width2) * 0.2f));
                    com.nineoldandroids.b.a.a(this.d.D, 0.0f);
                    com.nineoldandroids.b.a.b(this.d.D, (((float) height) * 0.308f) - (((float)
                            this.d.F.getScrollY()) * 0.8f));
                    com.nineoldandroids.b.a.d(this.d.D, 1.0f - ((((float) this.d.F.getScrollY())
                            / width2) * 0.2f));
                    com.nineoldandroids.b.a.e(this.d.D, 1.0f - ((((float) this.d.F.getScrollY())
                            / width2) * 0.2f));
                    com.nineoldandroids.b.a.f(this.d.B, ((-width3) * (((float) this.d.F
                            .getScrollY()) / width2)) * 0.4f);
                    com.nineoldandroids.b.a.g(this.d.B, ((float) (-this.d.F.getScrollY())) * 0.55f);
                    com.nineoldandroids.b.a.g(this.d.s, ((float) (-this.d.F.getScrollY())) * 0.63f);
                    com.nineoldandroids.b.a.g(this.d.D, ((float) (-this.d.F.getScrollY())) * 0.8f);
                } else if (((float) this.d.F.getScrollY()) > width2) {
                    com.nineoldandroids.b.a.a(this.d.s, 0.0f);
                    com.nineoldandroids.b.a.b(this.d.s, width3 / 2.0f);
                    com.nineoldandroids.b.a.d(this.d.s, 0.6f);
                    com.nineoldandroids.b.a.e(this.d.s, 0.6f);
                    com.nineoldandroids.b.a.g(this.d.s, (-width2) * 0.63f);
                    com.nineoldandroids.b.a.d(this.d.B, 0.8f);
                    com.nineoldandroids.b.a.e(this.d.B, 0.8f);
                    com.nineoldandroids.b.a.a(this.d.D, 0.0f);
                    com.nineoldandroids.b.a.b(this.d.D, (((float) height) * 0.308f) - (width2 *
                            0.8f));
                    com.nineoldandroids.b.a.d(this.d.D, 0.8f);
                    com.nineoldandroids.b.a.e(this.d.D, 0.8f);
                    com.nineoldandroids.b.a.f(this.d.B, (-width3) * 0.4f);
                    com.nineoldandroids.b.a.g(this.d.B, (-width2) * 0.55f);
                    com.nineoldandroids.b.a.g(this.d.D, (-width2) * 0.8f);
                }
            }
        });
        this.G = (LinearLayout) findViewById(R.id.scorllview_ll);
        ((FrameLayout.LayoutParams) this.F.getLayoutParams()).topMargin = width;
        ((FrameLayout.LayoutParams) this.F.getLayoutParams()).topMargin = width;
        ((FrameLayout.LayoutParams) this.F.getLayoutParams()).bottomMargin = 0;
        this.G.setMinimumHeight((int) (((float) ((height - width) + 0)) + width2));
    }

    private void b(int i) {
        if (((o) this.i.get(i)).h() && (this.y.e() == null || TextUtils.isEmpty(this.y.e().m()))) {
            startActivityForResult(new Intent(this, ECJiaLoginActivity.class), ((o) this.i.get(i)
            ).b());
            overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(this, Class.forName(((o) this.i.get(i)).g().getActivityName()));
            if (((o) this.i.get(i)).g().equals(ActivityName.QRSHARE)) {
                intent.putExtra("startType", 1);
            }
            if (((o) this.i.get(i)).a().equals("promotion")) {
                intent.putExtra("type", "promotion");
            }
            startActivity(intent);
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(11)
    private void a(float f) {
        View vg_left = this.a.getVg_left();
        View vg_main = this.a.getVg_main();
        float width = (float) vg_left.getWidth();
        com.nineoldandroids.b.a.f(vg_left, ((-width) / 2.7f) + ((width / 2.7f) * f));
        com.nineoldandroids.b.a.f(vg_main, ((-width) / 4.5f) * f);
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    protected void onStart() {
        super.onStart();
    }

    private void j() {
        if (this.J) {
            stopService(new Intent(this, ECJiaNetworkStateService.class));
            finish();
            System.exit(0);
            return;
        }
        this.J = true;
        k kVar = new k((Context) this, "再按一次退出程序");
        kVar.a(17, 0, y.a(this) / 4);
        kVar.a();
        this.h.sendEmptyMessageDelayed(0, 800);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.a.isOpen()) {
                this.a.close();
            } else {
                j();
            }
        }
        return true;
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onResume() {
        super.onResume();
        q.a("uid::" + this.m.getString("uid", ""));
        if (TextUtils.isEmpty(this.m.getString("uid", ""))) {
            this.k = false;
            a(false);
        } else {
            this.k = true;
            if (this.x) {
                this.l.a();
                this.x = false;
            } else {
                a(true);
            }
        }
        MobclickAgent.onResume(this);
        if (this.l != null) {
            this.l.a((com.ecjia.component.a.a.a) this);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.d != null) {
            this.d.unRegisterLocationListener(this.c);
            if (this.d != null) {
                this.d.stop();
                this.d = null;
            }
            this.c = null;
        }
        this.b.sendMessageDelayed(new Message(), 200);
        MobclickAgent.onPause(this);
        if (this.l != null) {
            this.l.b(this);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myfind_home_item:
                if (this.a.isOpen()) {
                    this.I.b();
                    this.a.close();
                    return;
                }
                return;
            case R.id.profile_head:
                if (TextUtils.isEmpty(this.m.getString("uid", ""))) {
                    startActivity(new Intent(this, ECJiaLoginActivity.class));
                    overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                Intent intent = new Intent(this, ECJiaCustomercenterActivity.class);
                if (this.y.e() != null && this.y.e().p() != null && this.y.e().o() != null) {
                    intent.putExtra(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME2, this.y.e
                            ().p());
                    intent.putExtra("level", this.y.e().o());
                    intent.putExtra("profile_photo", this.y.e().q());
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    return;
                }
                return;
            case R.id.profile_setting_ll:
                startActivity(new Intent(this, ECJiaSettingActivity.class));
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.profile_setting:
                startActivity(new Intent(this, ECJiaSettingActivity.class));
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.profile_newset:
                startActivity(new Intent(this, ECJiaSettingActivity.class));
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            default:
                return;
        }
    }

    private void a(boolean z) {
        this.v = getBaseContext().getResources();
        if (z) {
            if (this.p != null) {
                this.p.setText(this.y.e().p());
            }
            if (this.q != null) {
                this.q.setText(this.y.e().o());
            }
            this.r.setVisibility(4);
            a(1);
            return;
        }
        if (this.p != null) {
            this.p.setText("");
        }
        if (this.q != null) {
            this.q.setText("");
        }
        this.r.setVisibility(0);
        this.g = null;
        u.a().b();
        a(2);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 999 && i2 == -1) {
            q.a("home hostActivityName" + getClass().getName());
            com.ecjia.a.b.a.a().a(this, com.ecjia.a.b.a.a().a());
        }
    }

    private void k() {
        Configuration configuration = this.v.getConfiguration();
        this.n = PreferenceManager.getDefaultSharedPreferences(this);
        if ("zh".equalsIgnoreCase(this.n.getString(x.F, null))) {
            configuration.locale = Locale.CHINA;
        } else if (SocializeProtocolConstants.PROTOCOL_KEY_EN.equalsIgnoreCase(this.n.getString(x
                .F, null))) {
            configuration.locale = Locale.ENGLISH;
        } else {
            this.n.edit().putString(x.F, "auto").commit();
            configuration.locale = Locale.getDefault();
        }
        getBaseContext().getResources().updateConfiguration(configuration, null);
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    private void l() {
        this.d = new LocationClient(this);
        this.e = new LocationClientOption();
        this.e.setAddrType("all");
        this.e.setLocationNotify(true);
        this.e.setCoorType("bd09ll");
        this.e.setOpenGps(true);
        this.e.setScanSpan(1000);
        this.d.setLocOption(this.e);
        this.d.registerLocationListener(this.c);
        this.d.start();
    }

    public void addIgnoredView(View view) {
        this.a.addIgnoredView(view);
    }

    public void removeIgnoredView(View view) {
        this.a.removeIgnoredView(view);
    }

    public void c() {
        this.a.open();
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/info")) {
            if (axVar.b() == 1) {
                a(true);
            } else {
                a(false);
            }
        } else if (str.equals("cart/list")) {
            q.a("获取到了购物车数据" + this.y.g());
            this.I.c();
        }
    }

    public boolean d() {
        LocationManager locationManager = (LocationManager) getSystemService("location");
        List providers = locationManager.getProviders(true);
        for (int size = providers.size() - 1; size >= 0; size--) {
            this.z = locationManager.getLastKnownLocation((String) providers.get(size));
            if (this.z != null) {
                break;
            }
        }
        com.ecjia.consts.b.g = new double[2];
        if (this.z != null) {
            com.ecjia.consts.b.g[0] = this.z.getLongitude();
            com.ecjia.consts.b.g[1] = this.z.getLatitude();
        } else {
            q.a("定位失败");
        }
        if (com.ecjia.consts.b.g[0] != 0.0d) {
            return true;
        }
        return false;
    }

    public void onEvent(b bVar) {
        if ("changelanguage".equals(bVar.c())) {
            finish();
        }
        if ("userinfo_refresh".equals(bVar.c())) {
            this.l.a();
        }
        if ("avater_refresh".equals(bVar.c()) && this.y.e().q() != null) {
            u.a().a(this.y.e().q(), this.y.e().m());
        }
        if ("refreshlocal".equals(bVar.c())) {
            this.f = true;
        }
        if (bVar.c().equals("OPENTYPE_SELLER")) {
            q.a("首页收到消息==" + bVar.d());
            if (TextUtils.isEmpty(bVar.d())) {
                this.I.b();
            } else {
                this.I.a(bVar.d());
            }
        }
        if (bVar.c().equals("WINREWARD_ECJIAMAIN")) {
            this.I.b();
        }
        if (bVar.c().equals("ECJIAMAIN_MAIN")) {
            this.I.b();
        }
        if (bVar.c().equals("ECJIAMAIN_FIND")) {
            this.I.b("tab_five");
        }
        if (bVar.c().equals("USER_CHANGE_PHOTO")) {
            a(true);
            a(1);
        }
        if (bVar.c().equals("exsit")) {
            u.a().b();
            this.g = null;
            a(false);
            a(2);
        }
        if (bVar.c().equals("USER_LOGIN_SUCCESS")) {
            a(true);
            if (this.j == null) {
                this.j = new ah(this);
                this.j.a((com.ecjia.component.a.a.a) this);
            }
            this.j.a(false);
        }
    }

    void a(int i) {
        switch (i) {
            case 0:
                this.s.setImageResource(R.drawable.profile_no_avarta_icon_light);
                return;
            case 1:
                if (u.a().a(this.m.getString("uid", ""))) {
                    this.s.setImageBitmap(u.a().b(this.m.getString("uid", "")));
                    return;
                } else {
                    this.s.setImageResource(R.drawable.profile_no_avarta_icon_light);
                    return;
                }
            default:
                this.s.setImageResource(R.drawable.profile_no_avarta_icon);
                return;
        }
    }

    public String e() {
        List runningTasks = ((ActivityManager) getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null) {
            return null;
        }
        q.a("runningTaskInfos===" + ((RunningTaskInfo) runningTasks.get(0)).topActivity
                .getClassName());
        return ((RunningTaskInfo) runningTasks.get(0)).topActivity.getClassName();
    }

    public boolean f() {
        List runningAppProcesses = ((ActivityManager) getSystemService("activity"))
                .getRunningAppProcesses();
        if (runningAppProcesses.size() > 0) {
            q.a("top Activity = " + ((RunningAppProcessInfo) runningAppProcesses.get(0))
                    .processName);
            if ("com.ecmoban.android.street".equals(((RunningAppProcessInfo) runningAppProcesses
                    .get(0)).processName)) {
                q.a("在前台1");
                return true;
            }
        }
        q.a("在后台1");
        return false;
    }
}
