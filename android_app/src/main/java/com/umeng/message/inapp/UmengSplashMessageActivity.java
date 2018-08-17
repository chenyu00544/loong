package com.umeng.message.inapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.map.WeightedLatLng;
import com.tencent.open.yyb.TitleBar;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.j;
import java.io.File;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

public class UmengSplashMessageActivity extends Activity {
    private static final String a = UmengSplashMessageActivity.class.getName();
    private static int p = m_AppUI.MSG_APP_DATA_OK;
    private static int q = 1000;
    private Activity b;
    private UImageLoadTask c;
    private ImageView d;
    private ImageView e;
    private TextView f;
    private boolean g = true;
    private boolean h = true;
    private a i;
    private a j;
    private UInAppMessage k;
    private c l;
    private boolean m = false;
    private long n;
    private long o;
    private ImageLoaderCallback r = new UmengSplashMessageActivity_1(this);
    private IUmengInAppMessageCallback s = new UmengSplashMessageActivity_2(this);

    class UmengSplashMessageActivity_1 implements ImageLoaderCallback {
        final /* synthetic */ UmengSplashMessageActivity a;

        class UmengSplashMessageActivity_1_1 implements OnClickListener {
            final /* synthetic */ UmengSplashMessageActivity_1 a;

            UmengSplashMessageActivity_1_1(UmengSplashMessageActivity_1 umengSplashMessageActivity_1) {
                this.a = umengSplashMessageActivity_1;
            }

            public void onClick(View view) {
                if (!TextUtils.equals(UInAppMessage.NONE, this.a.a.k.action_type)) {
                    this.a.a.o = this.a.a.o + (SystemClock.elapsedRealtime() - this.a.a.n);
                    InAppMessageManager.getInstance(this.a.a.b).a(this.a.a.k.msg_id, this.a.a.k.msg_type, 1, 1, 0, 0, 0, (int) this.a.a.o);
                    this.a.a.f();
                    this.a.a.l.handleInAppMessage(this.a.a.b, this.a.a.k, false);
                    this.a.a.finish();
                }
            }
        }

        class UmengSplashMessageActivity_1_2 implements OnClickListener {
            final /* synthetic */ UmengSplashMessageActivity_1 a;

            UmengSplashMessageActivity_1_2(UmengSplashMessageActivity_1 umengSplashMessageActivity_1) {
                this.a = umengSplashMessageActivity_1;
            }

            public void onClick(View view) {
                if (!TextUtils.equals(UInAppMessage.NONE, this.a.a.k.action_type)) {
                    this.a.a.o = this.a.a.o + (SystemClock.elapsedRealtime() - this.a.a.n);
                    InAppMessageManager.getInstance(this.a.a.b).a(this.a.a.k.msg_id, this.a.a.k.msg_type, 1, 0, 1, 0, 0, (int) this.a.a.o);
                    this.a.a.f();
                    this.a.a.l.handleInAppMessage(this.a.a.b, this.a.a.k, false);
                    this.a.a.finish();
                }
            }
        }

        class UmengSplashMessageActivity_1_3 implements OnClickListener {
            final /* synthetic */ UmengSplashMessageActivity_1 a;

            UmengSplashMessageActivity_1_3(UmengSplashMessageActivity_1 umengSplashMessageActivity_1) {
                this.a = umengSplashMessageActivity_1;
            }

            public void onClick(View view) {
                if (!TextUtils.equals(UInAppMessage.NONE, this.a.a.k.bottom_action_type)) {
                    this.a.a.o = this.a.a.o + (SystemClock.elapsedRealtime() - this.a.a.n);
                    InAppMessageManager.getInstance(this.a.a.b).a(this.a.a.k.msg_id, this.a.a.k.msg_type, 1, 0, 0, 1, 0, (int) this.a.a.o);
                    this.a.a.f();
                    this.a.a.l.handleInAppMessage(this.a.a.b, this.a.a.k, true);
                    this.a.a.finish();
                }
            }
        }

        class UmengSplashMessageActivity_1_4 implements OnClickListener {
            final /* synthetic */ UmengSplashMessageActivity_1 a;

            UmengSplashMessageActivity_1_4(UmengSplashMessageActivity_1 umengSplashMessageActivity_1) {
                this.a = umengSplashMessageActivity_1;
            }

            public void onClick(View view) {
                this.a.a.o = this.a.a.o + (SystemClock.elapsedRealtime() - this.a.a.n);
                InAppMessageManager.getInstance(this.a.a.b).a(this.a.a.k.msg_id, this.a.a.k.msg_type, 1, 0, 0, 0, 1, (int) this.a.a.o);
                this.a.a.f();
                this.a.a.finish();
            }
        }

        UmengSplashMessageActivity_1(UmengSplashMessageActivity umengSplashMessageActivity) {
            this.a = umengSplashMessageActivity;
        }

        public void onLoadImage(Bitmap[] bitmapArr) {
            if (!this.a.e()) {
                if (this.a.i != null) {
                    this.a.i.a();
                    this.a.i = null;
                }
                try {
                    if (bitmapArr.length == 1) {
                        this.a.d.setOnClickListener(new UmengSplashMessageActivity_1_1(this));
                        this.a.e.setVisibility(8);
                        this.a.d.setImageBitmap(bitmapArr[0]);
                        this.a.a(this.a.d);
                    }
                    if (bitmapArr.length == 2) {
                        this.a.d.setOnClickListener(new UmengSplashMessageActivity_1_2(this));
                        this.a.e.setOnClickListener(new UmengSplashMessageActivity_1_3(this));
                        this.a.d.setImageBitmap(bitmapArr[0]);
                        this.a.e.setImageBitmap(bitmapArr[1]);
                        this.a.a(this.a.d);
                        this.a.a(this.a.e);
                    }
                    this.a.n = SystemClock.elapsedRealtime();
                    if (this.a.k.display_button) {
                        this.a.f.setVisibility(0);
                        this.a.f.setOnClickListener(new UmengSplashMessageActivity_1_4(this));
                    } else {
                        this.a.f.setVisibility(8);
                    }
                    InAppMessageManager.getInstance(this.a.b).a(this.a.k);
                    InAppMessageManager.getInstance(this.a.b).a(this.a.k.msg_id, 1);
                    InAppMessageManager.getInstance(this.a.b).f();
                    this.a.g = false;
                    this.a.j = new a(this.a, (long) (this.a.k.display_time * 1000), (long) UmengSplashMessageActivity.q);
                    this.a.j.b();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class UmengSplashMessageActivity_2 implements IUmengInAppMessageCallback {
        final /* synthetic */ UmengSplashMessageActivity a;

        UmengSplashMessageActivity_2(UmengSplashMessageActivity umengSplashMessageActivity) {
            this.a = umengSplashMessageActivity;
        }

        public void onSplashMessage(UInAppMessage uInAppMessage) {
            UInAppMessage uInAppMessage2;
            Object c = InAppMessageManager.getInstance(this.a.b).c();
            if (!TextUtils.isEmpty(c)) {
                try {
                    uInAppMessage2 = new UInAppMessage(new JSONObject(c));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (uInAppMessage != null) {
                    if (!(uInAppMessage2 == null || uInAppMessage.msg_id.equals(uInAppMessage2.msg_id))) {
                        InAppMessageManager.getInstance(this.a.b).a(new File(h.d(this.a.b, uInAppMessage2.msg_id)));
                    }
                    this.a.k = uInAppMessage;
                } else if (uInAppMessage2 != null) {
                    this.a.k = uInAppMessage2;
                } else {
                    return;
                }
                if (this.a.k.show_type == 1 && !this.a.g()) {
                    InAppMessageManager.getInstance(this.a.b).a(this.a.k.msg_id, 0);
                }
                if (InAppMessageManager.getInstance(this.a.b).b(this.a.k) && InAppMessageManager.getInstance(this.a.b).c(this.a.k)) {
                    if (this.a.k.msg_type == 0) {
                        UmLog.d(UmengSplashMessageActivity.a, "SPLASH_A");
                        this.a.c = new UImageLoadTask(this.a.b, this.a.k);
                        this.a.c.a(this.a.r);
                        this.a.c.execute(new String[]{this.a.k.image_url});
                    }
                    if (this.a.k.msg_type == 1) {
                        UmLog.d(UmengSplashMessageActivity.a, "SPLASH_B");
                        this.a.c = new UImageLoadTask(this.a.b, this.a.k);
                        this.a.c.a(this.a.r);
                        this.a.c.execute(new String[]{this.a.k.image_url, this.a.k.bottom_image_url});
                        return;
                    }
                    return;
                }
            }
            uInAppMessage2 = null;
            if (uInAppMessage != null) {
                InAppMessageManager.getInstance(this.a.b).a(new File(h.d(this.a.b, uInAppMessage2.msg_id)));
                this.a.k = uInAppMessage;
            } else if (uInAppMessage2 != null) {
                this.a.k = uInAppMessage2;
            } else {
                return;
            }
            InAppMessageManager.getInstance(this.a.b).a(this.a.k.msg_id, 0);
            if (InAppMessageManager.getInstance(this.a.b).b(this.a.k)) {
            }
        }

        public void onCardMessage(UInAppMessage uInAppMessage) {
        }
    }

    class a extends b {
        final /* synthetic */ UmengSplashMessageActivity a;

        a(UmengSplashMessageActivity umengSplashMessageActivity, long j, long j2) {
            this.a = umengSplashMessageActivity;
            super(j, j2);
        }

        public void a(long j) {
            if (!this.a.g) {
                this.a.f.setVisibility(0);
                this.a.f.setText(((int) Math.ceil((((double) j) * WeightedLatLng.DEFAULT_INTENSITY) / ((double) UmengSplashMessageActivity.q))) + " " + this.a.k.display_name);
            }
        }

        public void e() {
            if (!this.a.e() || !this.a.g) {
                if (!this.a.g) {
                    InAppMessageManager.getInstance(this.a.b).a(this.a.k.msg_id, this.a.k.msg_type, 1, 0, 0, 0, 0, this.a.k.display_time * 1000);
                }
                this.a.f();
                this.a.finish();
            }
        }
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        if (!onCustomPretreatment()) {
            setRequestedOrientation(1);
            setContentView(c());
            d();
            this.l = new c();
            this.i = new a(this, (long) p, (long) q);
            this.i.b();
        }
    }

    public final void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
    }

    private View c() {
        View frameLayout = new FrameLayout(this.b);
        frameLayout.setLayoutParams(new LayoutParams(-1, -1));
        View linearLayout = new LinearLayout(this.b);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 13.0f);
        this.d = new ImageView(this.b);
        this.d.setLayoutParams(layoutParams);
        this.d.setScaleType(ScaleType.FIT_XY);
        linearLayout.addView(this.d);
        layoutParams = new LinearLayout.LayoutParams(-1, 0, 3.0f);
        this.e = new ImageView(this.b);
        this.e.setLayoutParams(layoutParams);
        this.e.setScaleType(ScaleType.FIT_XY);
        linearLayout.addView(this.e);
        frameLayout.addView(linearLayout);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.gravity = 5;
        layoutParams2.rightMargin = j.a(this.b, 30.0f);
        layoutParams2.topMargin = j.a(this.b, TitleBar.BACKBTN_LEFT_MARGIN);
        this.f = new TextView(this.b);
        this.f.setLayoutParams(layoutParams2);
        int a = j.a(this.b, 6.0f);
        int i = a / 3;
        this.f.setPadding(a, i, a, i);
        this.f.setTextSize(14.0f);
        this.f.setBackgroundColor(Color.parseColor("#80000000"));
        this.f.setTextColor(-1);
        this.f.setVisibility(8);
        frameLayout.addView(this.f);
        return frameLayout;
    }

    private void d() {
        if (InAppMessageManager.a) {
            InAppMessageManager.getInstance(this).a(this.s);
        } else if (System.currentTimeMillis() - InAppMessageManager.getInstance(this.b).b() > ((long) InAppMessageManager.b)) {
            InAppMessageManager.getInstance(this).a(this.s);
        } else {
            this.s.onSplashMessage(null);
        }
    }

    protected final void onStart() {
        super.onStart();
    }

    protected final void onResume() {
        super.onResume();
        if (this.i != null) {
            this.i.d();
        }
        if (this.j != null) {
            this.n = SystemClock.elapsedRealtime();
            this.j.d();
        }
    }

    protected final void onPause() {
        super.onPause();
        if (this.i != null) {
            this.i.c();
        }
        if (this.j != null) {
            this.o += SystemClock.elapsedRealtime() - this.n;
            this.j.c();
        }
    }

    public final void onBackPressed() {
    }

    private synchronized boolean e() {
        boolean z;
        z = this.m;
        this.m = true;
        return z;
    }

    private synchronized void f() {
        if (this.h) {
            this.h = false;
            Intent intent = new Intent();
            intent.setClassName(this.b, InAppMessageManager.getInstance(this).getMainActivityPath());
            intent.setFlags(536870912);
            this.b.startActivity(intent);
        }
    }

    private boolean g() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(InAppMessageManager.getInstance(this.b).g());
        Calendar instance2 = Calendar.getInstance();
        if (instance.get(6) == instance2.get(6) && instance.get(1) == instance2.get(1)) {
            return true;
        }
        return false;
    }

    public boolean onCustomPretreatment() {
        return false;
    }

    private void a(View view) {
        Animation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(500);
        view.startAnimation(alphaAnimation);
    }

    protected final void onDestroy() {
        if (this.i != null) {
            this.i.a();
        }
        if (this.j != null) {
            this.j.a();
        }
        if (this.c != null) {
            this.c.a(null);
        }
        this.m = false;
        super.onDestroy();
    }
}
