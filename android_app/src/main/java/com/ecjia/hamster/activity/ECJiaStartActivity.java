package com.ecjia.hamster.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.ecjia.a.h;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.c;
import com.ecjia.component.a.j;
import com.ecjia.component.a.l;
import com.ecjia.component.a.s;
import com.ecjia.hamster.adapter.ECJiaADPagerAdapter;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.b;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ECJiaStartActivity extends Activity implements OnPageChangeListener, a {
    private Context a;
    private SharedPreferences b;
    private j c;
    private l d;
    private com.ecjia.component.a.a e;
    private s f;
    private ImageView g;
    private ViewPager h;
    private AlphaAnimation i;
    private AlphaAnimation j;
    private ArrayList<View> k = new ArrayList();
    private int l = 0;

    class ECJiaStartActivity_1 implements AnimationListener {
        final /* synthetic */ ECJiaStartActivity a;

        ECJiaStartActivity_1(ECJiaStartActivity eCJiaStartActivity) {
            this.a = eCJiaStartActivity;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.a.b();
        }
    }

    class ECJiaStartActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaStartActivity a;

        ECJiaStartActivity_3(ECJiaStartActivity eCJiaStartActivity) {
            this.a = eCJiaStartActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaMainActivity.class));
            this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            this.a.finish();
        }
    }

    class ECJiaStartActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaStartActivity a;

        ECJiaStartActivity_4(ECJiaStartActivity eCJiaStartActivity) {
            this.a = eCJiaStartActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaMainActivity.class));
            this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            this.a.finish();
        }
    }

    class ECJiaStartActivity_5 implements AnimationListener {
        final /* synthetic */ ECJiaStartActivity a;

        ECJiaStartActivity_5(ECJiaStartActivity eCJiaStartActivity) {
            this.a = eCJiaStartActivity;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.a.startActivity(new Intent(this.a, ECJiaMainActivity.class));
            this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            this.a.finish();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.splash);
        h.b(this);
        this.b = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.c = new j(this);
        this.c.a((a) this);
        this.c.b();
        this.f = new s(this);
        this.f.a((a) this);
        this.f.c();
        this.d = new l(this);
        this.d.a((a) this);
        this.d.a();
        this.g = (ImageView) findViewById(R.id.start_img);
        this.h = (ViewPager) findViewById(R.id.start_viewpager);
        this.a = this;
        this.i = new AlphaAnimation(0.3f, 1.0f);
        this.i.setDuration(2000);
        this.i.setAnimationListener(new ECJiaStartActivity_1(this));
        this.g.setAnimation(this.i);
    }

    public boolean a() {
        ParseException e;
        View inflate;
        ImageView imageView;
        this.e = new com.ecjia.component.a.a(this);
        this.e.b();
        if (this.e.b.size() <= 0) {
            return false;
        }
        for (int i = 0; i < this.e.b.size(); i++) {
            long time;
            long j;
            Button button;
            Button button2;
            Bitmap decodeFile;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(new Date());
            long time2;
            try {
                time2 = simpleDateFormat.parse(((b) this.e.b.get(i)).b()).getTime();
                try {
                    time = simpleDateFormat.parse(((b) this.e.b.get(i)).c()).getTime();
                } catch (ParseException e2) {
                    e = e2;
                    time = 0;
                    e.printStackTrace();
                    j = 0;
                    inflate = LayoutInflater.from(this).inflate(R.layout.start_a, null);
                    inflate.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaStartActivity b;

                        public void onClick(View view) {
                            this.b.startActivity(new Intent(this.b, ECJiaMainActivity.class));
                            com.ecjia.a.b.a.a().a(this.b, ((b) this.b.e.b.get(i)).a());
                            this.b.finish();
                            ((Activity) this.b.a).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        }
                    });
                    button = (Button) inflate.findViewById(R.id.go_ecjia);
                    button.setOnClickListener(new ECJiaStartActivity_3(this));
                    imageView = (ImageView) inflate.findViewById(R.id.starta);
                    imageView.setScaleType(ScaleType.FIT_XY);
                    button2 = (Button) inflate.findViewById(R.id.welcome_intent1);
                    button2.setOnClickListener(new ECJiaStartActivity_4(this));
                    button.setVisibility(8);
                    button2.setVisibility(0);
                    decodeFile = BitmapFactory.decodeFile("/" + ((b) this.e.b.get(i)).d());
                    if (decodeFile != null) {
                        q.a("加载本地广告图失败");
                    } else {
                        q.a("加载本地广告图成功");
                        imageView.setImageBitmap(decodeFile);
                        this.k.add(inflate);
                    }
                }
                try {
                    j = simpleDateFormat.parse(format).getTime();
                } catch (ParseException e3) {
                    e = e3;
                    e.printStackTrace();
                    j = 0;
                    inflate = LayoutInflater.from(this).inflate(R.layout.start_a, null);
                    inflate.setOnClickListener(/* anonymous class already generated */);
                    button = (Button) inflate.findViewById(R.id.go_ecjia);
                    button.setOnClickListener(new ECJiaStartActivity_3(this));
                    imageView = (ImageView) inflate.findViewById(R.id.starta);
                    imageView.setScaleType(ScaleType.FIT_XY);
                    button2 = (Button) inflate.findViewById(R.id.welcome_intent1);
                    button2.setOnClickListener(new ECJiaStartActivity_4(this));
                    button.setVisibility(8);
                    button2.setVisibility(0);
                    decodeFile = BitmapFactory.decodeFile("/" + ((b) this.e.b.get(i)).d());
                    if (decodeFile != null) {
                        q.a("加载本地广告图成功");
                        imageView.setImageBitmap(decodeFile);
                        this.k.add(inflate);
                    } else {
                        q.a("加载本地广告图失败");
                    }
                }
            } catch (ParseException e4) {
                e = e4;
                time = 0;
                time2 = 0;
                e.printStackTrace();
                j = 0;
                inflate = LayoutInflater.from(this).inflate(R.layout.start_a, null);
                inflate.setOnClickListener(/* anonymous class already generated */);
                button = (Button) inflate.findViewById(R.id.go_ecjia);
                button.setOnClickListener(new ECJiaStartActivity_3(this));
                imageView = (ImageView) inflate.findViewById(R.id.starta);
                imageView.setScaleType(ScaleType.FIT_XY);
                button2 = (Button) inflate.findViewById(R.id.welcome_intent1);
                button2.setOnClickListener(new ECJiaStartActivity_4(this));
                button.setVisibility(8);
                button2.setVisibility(0);
                decodeFile = BitmapFactory.decodeFile("/" + ((b) this.e.b.get(i)).d());
                if (decodeFile != null) {
                    q.a("加载本地广告图成功");
                    imageView.setImageBitmap(decodeFile);
                    this.k.add(inflate);
                } else {
                    q.a("加载本地广告图失败");
                }
            }
            if (j > time || j < r10) {
                break;
            }
            inflate = LayoutInflater.from(this).inflate(R.layout.start_a, null);
            inflate.setOnClickListener(/* anonymous class already generated */);
            button = (Button) inflate.findViewById(R.id.go_ecjia);
            button.setOnClickListener(new ECJiaStartActivity_3(this));
            imageView = (ImageView) inflate.findViewById(R.id.starta);
            imageView.setScaleType(ScaleType.FIT_XY);
            button2 = (Button) inflate.findViewById(R.id.welcome_intent1);
            button2.setOnClickListener(new ECJiaStartActivity_4(this));
            button.setVisibility(8);
            button2.setVisibility(0);
            decodeFile = BitmapFactory.decodeFile("/" + ((b) this.e.b.get(i)).d());
            if (decodeFile != null) {
                q.a("加载本地广告图失败");
            } else {
                q.a("加载本地广告图成功");
                imageView.setImageBitmap(decodeFile);
                this.k.add(inflate);
            }
        }
        if (this.k.size() > 0) {
            return true;
        }
        return false;
    }

    private void b() {
        if (a()) {
            c();
            this.g.setVisibility(8);
            return;
        }
        startActivity(new Intent(this, ECJiaGalleryImageActivity.class));
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    private void c() {
        this.j = new AlphaAnimation(1.0f, 1.0f);
        this.j.setDuration(3000);
        this.j.setAnimationListener(new ECJiaStartActivity_5(this));
        if (this.k.size() > 0) {
            this.h = (ViewPager) findViewById(R.id.start_viewpager);
            this.h.setAdapter(new ECJiaADPagerAdapter(this, this.k));
            this.h.setOnPageChangeListener(this);
            this.h.setAnimation(this.j);
        }
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i == this.h.getAdapter().getCount() - 1) {
            this.j.setAnimationListener(null);
            this.h.clearAnimation();
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("shop/token")) {
            if (axVar.b() == 1) {
                this.f.b();
            }
        } else if (str.equals("shop/config") && this.b.getBoolean("isFirstRun", true)) {
            c cVar = new c(this);
            cVar.a((a) this);
            cVar.a("2");
        }
    }
}
