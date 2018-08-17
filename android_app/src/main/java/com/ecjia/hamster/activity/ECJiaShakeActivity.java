package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.a.w;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.nineoldandroids.a.b;
import com.nineoldandroids.a.c;
import com.nineoldandroids.a.i;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.message.PushAgent;
import com.umeng.socialize.common.SocializeConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ECJiaShakeActivity extends a implements SensorEventListener, OnClickListener, com.ecjia.component.a.a.a {
    SensorManager a = null;
    Vibrator b = null;
    LayoutParams c;
    String d;
    String e;
    String k;
    boolean l = true;
    HashMap<Integer, View> m = new HashMap();
    float n;
    float o;
    float p;
    a q = new a(this);
    Handler r = new ECJiaShakeActivity_7(this);
    private ImageView s;
    private ImageView t;
    private w u;
    private RelativeLayout v;
    private SoundPool w = new SoundPool(3, 3, 0);
    private Map<Integer, Integer> x = new HashMap();
    private AudioManager y = null;

    class ECJiaShakeActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaShakeActivity a;

        ECJiaShakeActivity_1(ECJiaShakeActivity eCJiaShakeActivity) {
            this.a = eCJiaShakeActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaShakeActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaShakeActivity a;

        ECJiaShakeActivity_2(ECJiaShakeActivity eCJiaShakeActivity) {
            this.a = eCJiaShakeActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaShakeHistoryActivity.class));
        }
    }

    class ECJiaShakeActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaShakeActivity a;

        ECJiaShakeActivity_3(ECJiaShakeActivity eCJiaShakeActivity) {
            this.a = eCJiaShakeActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a, ECJiaGoodsDetailActivity.class);
            intent.putExtra("goods_id", this.a.u.b.e().a());
            this.a.startActivity(intent);
        }
    }

    class ECJiaShakeActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaShakeActivity a;

        ECJiaShakeActivity_4(ECJiaShakeActivity eCJiaShakeActivity) {
            this.a = eCJiaShakeActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaMyPurseActivity.class));
        }
    }

    class ECJiaShakeActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaShakeActivity a;

        ECJiaShakeActivity_5(ECJiaShakeActivity eCJiaShakeActivity) {
            this.a = eCJiaShakeActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaMyPurseActivity.class));
        }
    }

    class ECJiaShakeActivity_6 extends b {
        final /* synthetic */ ECJiaShakeActivity a;

        ECJiaShakeActivity_6(ECJiaShakeActivity eCJiaShakeActivity) {
            this.a = eCJiaShakeActivity;
        }

        public void b(com.nineoldandroids.a.a aVar) {
            super.b(aVar);
            this.a.v.setVisibility(0);
        }

        public void a(com.nineoldandroids.a.a aVar) {
            super.a(aVar);
        }
    }

    class ECJiaShakeActivity_7 extends Handler {
        final /* synthetic */ ECJiaShakeActivity a;

        ECJiaShakeActivity_7(ECJiaShakeActivity eCJiaShakeActivity) {
            this.a = eCJiaShakeActivity;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 9:
                    this.a.l = true;
                    return;
                case 10:
                    if (!this.a.l) {
                        Message message2 = new Message();
                        message2.what = 9;
                        sendMessageDelayed(message2, 1500);
                    }
                    this.a.b.vibrate(500);
                    this.a.f();
                    return;
                default:
                    return;
            }
        }
    }

    class a implements Runnable {
        final /* synthetic */ ECJiaShakeActivity a;

        a(ECJiaShakeActivity eCJiaShakeActivity) {
            this.a = eCJiaShakeActivity;
        }

        public void run() {
            this.a.u.a();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_shake);
        this.c = new LayoutParams(-1, -1);
        b();
        this.d = getResources().getString(R.string.shake_reach);
        this.e = getResources().getString(R.string.shake_use);
        this.k = getResources().getString(R.string.shake_use);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.shake_topview);
        this.i.setBackgroundColor(Color.parseColor("#00000000"));
        if (getResources().getConfiguration().locale.equals(Locale.CHINA)) {
            this.i.setTitleImage((int) R.drawable.shake_title);
        } else {
            this.i.setTitleImage((int) R.drawable.shake_title_english);
        }
        this.i.setLeftBackImageBackground(R.drawable.icon_topview_shake_back);
        this.i.setLeftOnClickListener(new ECJiaShakeActivity_1(this));
        this.i.setRightType(12);
        this.i.setRightImageBackground(R.drawable.icon_topview_shake_record);
        this.i.setRightOnClickListener(new ECJiaShakeActivity_2(this));
        this.i.setLeftBackImageSize(R.dimen.dp_32);
        this.i.setRightImageSize(R.dimen.dp_32);
    }

    void b() {
        a();
        this.s = (ImageView) findViewById(R.id.shake_bg);
        this.t = (ImageView) findViewById(R.id.shake_hand);
        LayoutParams layoutParams = (LayoutParams) this.t.getLayoutParams();
        layoutParams.height = d() / 2;
        layoutParams.width = d() / 2;
        this.t.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.s.getLayoutParams();
        layoutParams.height = (d() * 3) / 4;
        layoutParams.width = (d() * 3) / 4;
        this.s.setLayoutParams(layoutParams);
        ((AnimationDrawable) this.s.getDrawable()).start();
        this.a = (SensorManager) getSystemService("sensor");
        this.b = (Vibrator) getSystemService("vibrator");
        this.x.put(Integer.valueOf(0), Integer.valueOf(this.w.load(this, R.raw.shake_sound_male, 1)));
        this.x.put(Integer.valueOf(1), Integer.valueOf(this.w.load(this, R.raw.shake_match, 1)));
        this.x.put(Integer.valueOf(2), Integer.valueOf(this.w.load(this, R.raw.shake_nomatch, 1)));
        this.u = new w(this);
        this.u.a((com.ecjia.component.a.a.a) this);
        this.v = (RelativeLayout) findViewById(R.id.bonus_view);
        this.v.setGravity(13);
        c();
    }

    public void onClick(View view) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        this.a.unregisterListener(this);
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.a.registerListener(this, this.a.getDefaultSensor(1), 3);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        if ((Math.abs(f) > ((float) 8) && Math.abs(f2) > ((float) 8)) || ((Math.abs(f2) > ((float) 8) && Math.abs(f3) > ((float) 8)) || (Math.abs(f) > ((float) 8) && Math.abs(f3) > ((float) 8)))) {
            q.a("x轴方向的重力加速度" + f + "；y轴方向的重力加速度" + f2 + "；z轴方向的重力加速度" + f3);
            if (this.l) {
                this.r.postDelayed(this.q, 1500);
                this.l = false;
                e();
                Message message = new Message();
                message.what = 10;
                this.r.sendMessageDelayed(message, 500);
            }
        }
    }

    void c() {
        this.m.put(Integer.valueOf(1), LayoutInflater.from(this).inflate(R.layout.layout_shake_goods, null));
        this.m.put(Integer.valueOf(2), LayoutInflater.from(this).inflate(R.layout.layout_shake_bonus, null));
        this.m.put(Integer.valueOf(3), LayoutInflater.from(this).inflate(R.layout.layout_shake_integral, null));
        this.m.put(Integer.valueOf(4), LayoutInflater.from(this).inflate(R.layout.layout_shake_nothing, null));
    }

    void a(int i) {
        this.v.removeAllViews();
        this.v.setVisibility(4);
        View view = (View) this.m.get(Integer.valueOf(i));
        view.setLayoutParams(this.c);
        if (i == 1) {
            ImageLoader.getInstance().displayImage(this.u.b.e().d().getThumb(), (ImageView) view.findViewById(R.id.goods_image));
            ((TextView) view.findViewById(R.id.goods_name)).setText(this.u.b.e().b());
            ((TextView) view.findViewById(R.id.goods_price)).setText(this.u.b.e().c());
            view.setOnClickListener(new ECJiaShakeActivity_3(this));
        } else if (i == 2) {
            ((TextView) view.findViewById(R.id.bonus_amount)).setText(this.u.b.d().b());
            ((TextView) view.findViewById(R.id.bonus_name)).setText(this.u.b.d().a());
            ((TextView) view.findViewById(R.id.request_amount)).setText(this.d + this.u.b.d().c() + this.e);
            ((TextView) view.findViewById(R.id.start_end_date)).setText(this.u.b.d().d().replaceAll(SocializeConstants.OP_DIVIDER_MINUS, ".") + SocializeConstants.OP_DIVIDER_MINUS + this.u.b.d().e().replaceAll(SocializeConstants.OP_DIVIDER_MINUS, "."));
            view.setOnClickListener(new ECJiaShakeActivity_4(this));
        } else if (i == 3) {
            ((TextView) view.findViewById(R.id.integral)).setText(this.u.b.f());
            view.setOnClickListener(new ECJiaShakeActivity_5(this));
        } else if (i == 4) {
        }
        this.v.addView(view);
        i a = i.a(this.v, "translationY", (float) ((int) (((double) (-d())) * 0.5d)), 0.0f);
        c cVar = new c();
        i a2 = i.a(this.v, "alpha", 0.0f, 1.0f);
        a.b(800);
        a.a(new ECJiaShakeActivity_6(this));
        cVar.a(a, a2);
        cVar.a();
    }

    void e() {
        i a = i.a(this.t, "rotation", 0.0f, 30.0f);
        i a2 = i.a(this.t, "rotation", 30.0f, -27.0f);
        i a3 = i.a(this.t, "rotation", -27.0f, 24.0f);
        i a4 = i.a(this.t, "rotation", 24.0f, -21.0f);
        i a5 = i.a(this.t, "rotation", -21.0f, 18.0f);
        i a6 = i.a(this.t, "rotation", 18.0f, -15.0f);
        i a7 = i.a(this.t, "rotation", -15.0f, 12.0f);
        i a8 = i.a(this.t, "rotation", 12.0f, -9.0f);
        i a9 = i.a(this.t, "rotation", -9.0f, 6.0f);
        i a10 = i.a(this.t, "rotation", 6.0f, -3.0f);
        i a11 = i.a(this.t, "rotation", -3.0f, 0.0f);
        a.b(100);
        a2.b(100);
        a3.b(100);
        a4.b(100);
        a5.b(100);
        a6.b(100);
        a7.b(100);
        a8.b(100);
        a9.b(100);
        a10.b(100);
        a11.b(100);
        c cVar = new c();
        cVar.b(a, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
        cVar.a();
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("mobile/shake")) {
            return;
        }
        if (axVar.b() == 1) {
            String str3 = this.u.a;
            int i = -1;
            switch (str3.hashCode()) {
                case 93921311:
                    if (str3.equals("bonus")) {
                        i = 1;
                        break;
                    }
                    break;
                case 98539350:
                    if (str3.equals("goods")) {
                        i = 0;
                        break;
                    }
                    break;
                case 570086828:
                    if (str3.equals("integral")) {
                        i = 2;
                        break;
                    }
                    break;
                case 2129323981:
                    if (str3.equals("nothing")) {
                        i = 3;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    a(1);
                    break;
                case 1:
                    a(2);
                    break;
                case 2:
                    a(3);
                    break;
                case 3:
                    a(4);
                    break;
            }
            this.w.play(((Integer) this.x.get(Integer.valueOf(1))).intValue(), this.p, this.p, 1, 0, 1.0f);
            return;
        }
        if (this.u.a == null) {
            new k((Context) this, axVar.d()).a();
        } else if (this.u.a.equals("nothing")) {
            a(4);
        }
        this.w.play(((Integer) this.x.get(Integer.valueOf(2))).intValue(), this.p, this.p, 1, 0, 1.0f);
    }

    void f() {
        this.y = (AudioManager) getSystemService("audio");
        this.n = (float) this.y.getStreamMaxVolume(3);
        this.o = (float) this.y.getStreamVolume(3);
        this.p = this.o / this.n;
        this.w.play(((Integer) this.x.get(Integer.valueOf(0))).intValue(), this.p, this.p, 1, 0, 1.0f);
    }
}
