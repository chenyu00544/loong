package com.ecjia.hamster.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import java.util.List;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;

public class ECJiaFullScreenViewPagerActivity extends a {
    UrlPagerAdapter a;
    List<String> b = new ArrayList();
    ViewGroup c;
    TextView d;
    TextView[] e;
    int k;
    private ImageView l;
    private GalleryViewPager m;

    class ECJiaFullScreenViewPagerActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaFullScreenViewPagerActivity a;

        ECJiaFullScreenViewPagerActivity_1(ECJiaFullScreenViewPagerActivity eCJiaFullScreenViewPagerActivity) {
            this.a = eCJiaFullScreenViewPagerActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaFullScreenViewPagerActivity_2 implements OnPageChangeListener {
        final /* synthetic */ ECJiaFullScreenViewPagerActivity a;

        ECJiaFullScreenViewPagerActivity_2(ECJiaFullScreenViewPagerActivity eCJiaFullScreenViewPagerActivity) {
            this.a = eCJiaFullScreenViewPagerActivity;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            for (int i2 = 0; i2 < this.a.e.length; i2++) {
                this.a.e[i].setBackgroundResource(R.drawable.view_selectde);
                if (i != i2) {
                    this.a.e[i2].setBackgroundResource(R.drawable.view_unselected);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.full_screen_view_pager);
        PushAgent.getInstance(this).onAppStart();
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra("position", 0);
        this.b = intent.getStringArrayListExtra("pictures");
        this.k = intent.getIntExtra("size", 0);
        getWindow().setLayout(-1, -1);
        this.m = (GalleryViewPager) findViewById(R.id.fullscreen_viewpager);
        this.c = (ViewGroup) findViewById(R.id.full_viewGroup);
        ((LinearLayout) findViewById(R.id.full_layout)).setOnClickListener(new ECJiaFullScreenViewPagerActivity_1(this));
        this.m.setOnPageChangeListener(new ECJiaFullScreenViewPagerActivity_2(this));
        b();
        this.m.setCurrentItem(intExtra % this.k);
        this.l = (ImageView) findViewById(R.id.item_grid_share);
        this.l.setVisibility(8);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.my_scale_action, R.anim.my_scale_finish);
    }

    public void b() {
        this.a = new UrlPagerAdapter(this, this.b);
        this.m.setAdapter(this.a);
        c();
    }

    public void c() {
        Resources resources = getResources();
        this.c.removeAllViews();
        this.e = new TextView[this.k];
        for (int i = 0; i < this.k; i++) {
            this.d = new TextView(this);
            LayoutParams layoutParams = new LinearLayout.LayoutParams((int) resources.getDimension(R.dimen.default_pointwidth), (int) resources.getDimension(R.dimen.default_pointwidth));
            layoutParams.setMargins((int) resources.getDimension(R.dimen.default_pointdistance), 0, (int) resources.getDimension(R.dimen.default_pointdistance), 0);
            this.d.setLayoutParams(layoutParams);
            this.e[i] = this.d;
            if (i == 0) {
                this.e[i].setBackgroundResource(R.drawable.view_selectde);
            } else {
                this.e[i].setBackgroundResource(R.drawable.view_unselected);
            }
            this.c.addView(this.e[i]);
        }
        this.c.invalidate();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
            overridePendingTransition(R.anim.my_scale_action, R.anim.my_scale_finish);
        }
        return true;
    }
}
