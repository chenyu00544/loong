package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.a.q;
import com.ecjia.hamster.activity.ECJiaSeckillActivity;
import com.ecjia.hamster.adapter.ECJiaCirculatoryPagerAdapter;
import com.ecjia.hamster.model.au;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaSeckillView */
public class j extends d<au> {
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private ViewPager g;
    private ArrayList<View> h;
    private ECJiaCirculatoryPagerAdapter i;
    private LinearLayout j;
    private int k = 0;
    private boolean l = true;

    /* compiled from: ECJiaSeckillView */
    class j_1 implements OnClickListener {
        final /* synthetic */ j a;

        j_1(j jVar) {
            this.a = jVar;
        }

        public void onClick(View view) {
            this.a.a.startActivity(new Intent(this.a.a, ECJiaSeckillActivity.class));
            this.a.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    /* compiled from: ECJiaSeckillView */
    class j_2 implements OnPageChangeListener {
        final /* synthetic */ j a;

        j_2(j jVar) {
            this.a = jVar;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            int i2 = (i % 1) % 2;
            this.a.j.getChildAt(this.a.k).setEnabled(false);
            this.a.j.getChildAt(i2).setEnabled(true);
            this.a.k = i2;
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    public j(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.d = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.home_seckill_goods, null);
        this.e = (LinearLayout) this.d.findViewById(R.id.home_groupgoodlist_item);
        this.f = (LinearLayout) this.d.findViewById(R.id.group_getmore);
        this.f.setOnClickListener(new j_1(this));
        this.g = (ViewPager) this.d.findViewById(R.id.banner_groupbuy_viewpager);
        this.h = new ArrayList();
        q.a("groupbuyListView=" + this.h.size());
        this.i = new ECJiaCirculatoryPagerAdapter(this.h);
        this.j = (LinearLayout) this.d.findViewById(R.id.groupbuy_viewGroup);
        this.g.addOnPageChangeListener(new j_2(this));
    }

    public void a(ListView listView) {
    }
}
