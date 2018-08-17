package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import com.ecjia.a.b.a;
import com.ecjia.component.view.ECJiaSelectableRoundedImageView;
import com.ecjia.hamster.activity.ECJiaTopicListActivity;
import com.ecjia.hamster.model.ah;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: ECJiaThemeStreets */
public class k extends d<ah> {
    int d;
    int e;
    private int f;
    private LinearLayout g;
    private LinearLayout h;
    private LinearLayout i;
    private List<ECJiaSelectableRoundedImageView> j = new ArrayList();
    private LinearLayout k;
    private LinearLayout l;
    private ECJiaSelectableRoundedImageView m;
    private ECJiaSelectableRoundedImageView n;
    private ECJiaSelectableRoundedImageView o;
    private ECJiaSelectableRoundedImageView p;
    private ECJiaSelectableRoundedImageView q;
    private ECJiaSelectableRoundedImageView r;
    private ECJiaSelectableRoundedImageView s;
    private ImageView t;

    /* compiled from: ECJiaThemeStreets */
    class k_1 implements OnClickListener {
        final /* synthetic */ k a;

        k_1(k kVar) {
            this.a = kVar;
        }

        public void onClick(View view) {
            this.a.a.startActivity(new Intent(this.a.a, ECJiaTopicListActivity.class));
            this.a.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public k(Activity activity) {
        super(activity);
    }

    protected void a() {
        this.d = this.a.getResources().getDimensionPixelOffset(R.dimen.dp_10);
        this.e = (this.b - (this.d * 2)) / 2;
        this.f = (int) this.a.getResources().getDimension(R.dimen.dp_5);
        this.g = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.home_theme_view, null);
        this.h = (LinearLayout) this.g.findViewById(R.id.home_theme_big_item);
        this.h.setVisibility(8);
        this.t = (ImageView) this.g.findViewById(R.id.home_theme_icon);
        this.i = (LinearLayout) this.g.findViewById(R.id.home_more_theme);
        this.i.setOnClickListener(new k_1(this));
        this.k = (LinearLayout) this.g.findViewById(R.id.home_theme_above);
        this.l = (LinearLayout) this.g.findViewById(R.id.home_theme_below);
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        if (this.a.getResources().getConfiguration().locale.equals(Locale.CHINA)) {
            a((int) R.drawable.theme_icon_chinese);
        } else {
            a((int) R.drawable.theme_icon_english);
        }
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.g);
    }

    public void a(ArrayList<ah> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.h.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            return;
        }
        this.c = arrayList;
        this.h.setVisibility(0);
        this.k.removeAllViews();
        this.l.removeAllViews();
        this.j.clear();
        if (arrayList.size() > 0 && arrayList.size() <= 2) {
            c();
        } else if (arrayList.size() > 2 && arrayList.size() <= 5) {
            c();
            e();
        } else if (arrayList.size() > 5) {
            d();
            f();
        }
        a((List) arrayList);
    }

    private void a(final List<ah> list) {
        for (int i = 0; i < Math.min(this.j.size(), list.size()); i++) {
            ImageLoader.getInstance().displayImage(((ah) list.get(i)).a(), (ImageView) this.j.get(i));
            ((ECJiaSelectableRoundedImageView) this.j.get(i)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ k c;

                public void onClick(View view) {
                    a.a().a(this.c.a, ((ah) list.get(i)).b());
                }
            });
        }
    }

    private void a(int i) {
        this.t.setImageResource(i);
    }

    private void c() {
        this.k.setVisibility(0);
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new LayoutParams(this.e * 2, this.e));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, this.e);
        layoutParams.weight = 1.0f;
        layoutParams.gravity = 17;
        this.m = new ECJiaSelectableRoundedImageView(this.a);
        this.n = new ECJiaSelectableRoundedImageView(this.a);
        this.m.setLayoutParams(layoutParams);
        this.n.setLayoutParams(layoutParams);
        this.m.setCornerRadius((float) this.f, 0.0f, (float) this.f, 0.0f);
        this.n.setCornerRadius(0.0f, (float) this.f, 0.0f, (float) this.f);
        layoutParams = new LayoutParams(1, this.e);
        View view = new View(this.a);
        view.setBackgroundColor(Color.parseColor("#DDDDDD"));
        view.setLayoutParams(layoutParams);
        linearLayout.addView(this.m);
        linearLayout.addView(view);
        linearLayout.addView(this.n);
        this.k.addView(linearLayout);
        this.j.add(this.m);
        this.j.add(this.n);
    }

    private void d() {
        this.k.setVisibility(0);
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(0);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(this.e * 2, this.e);
        linearLayout.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, this.e);
        layoutParams2.weight = 1.0f;
        layoutParams2.gravity = 17;
        this.m = new ECJiaSelectableRoundedImageView(this.a);
        this.n = new ECJiaSelectableRoundedImageView(this.a);
        this.m.setLayoutParams(layoutParams2);
        this.n.setLayoutParams(layoutParams2);
        this.m.setCornerRadius((float) this.f, 0.0f, 0.0f, 0.0f);
        this.n.setCornerRadius(0.0f, (float) this.f, 0.0f, 0.0f);
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(1, this.e);
        View view = new View(this.a);
        view.setBackgroundColor(Color.parseColor("#DDDDDD"));
        view.setLayoutParams(layoutParams3);
        linearLayout.addView(this.m);
        linearLayout.addView(view);
        linearLayout.addView(this.n);
        this.k.addView(linearLayout);
        linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(layoutParams);
        this.o = new ECJiaSelectableRoundedImageView(this.a);
        this.p = new ECJiaSelectableRoundedImageView(this.a);
        this.o.setLayoutParams(layoutParams2);
        this.p.setLayoutParams(layoutParams2);
        this.o.setCornerRadius(0.0f, 0.0f, (float) this.f, 0.0f);
        this.p.setCornerRadius(0.0f, 0.0f, 0.0f, (float) this.f);
        View view2 = new View(this.a);
        view2.setBackgroundColor(Color.parseColor("#DDDDDD"));
        view2.setLayoutParams(layoutParams3);
        linearLayout.addView(this.o);
        linearLayout.addView(view2);
        linearLayout.addView(this.p);
        view2 = new View(this.a);
        view2.setBackgroundColor(Color.parseColor("#DDDDDD"));
        view2.setLayoutParams(new LayoutParams(this.b, 1));
        this.k.addView(view2);
        this.k.addView(linearLayout);
        this.j.add(this.m);
        this.j.add(this.n);
        this.j.add(this.o);
        this.j.add(this.p);
    }

    private void e() {
        this.l.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = new LayoutParams((this.b - (this.d * 4)) / 3, (this.b - (this.d * 4)) / 3);
        layoutParams.setMargins(this.f, 0, this.f, 0);
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setLayoutParams(layoutParams);
        View linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setLayoutParams(layoutParams);
        View linearLayout3 = new LinearLayout(this.a);
        linearLayout3.setLayoutParams(layoutParams);
        linearLayout.setPadding(1, 1, 1, 1);
        linearLayout2.setPadding(1, 1, 1, 1);
        linearLayout3.setPadding(1, 1, 1, 1);
        linearLayout.setBackgroundResource(R.drawable.selecter_raditem_press);
        linearLayout2.setBackgroundResource(R.drawable.selecter_raditem_press);
        linearLayout3.setBackgroundResource(R.drawable.selecter_raditem_press);
        this.o = new ECJiaSelectableRoundedImageView(this.a);
        this.p = new ECJiaSelectableRoundedImageView(this.a);
        this.q = new ECJiaSelectableRoundedImageView(this.a);
        this.o.setCornerRadius((float) this.f, (float) this.f, (float) this.f, (float) this.f);
        this.p.setCornerRadius((float) this.f, (float) this.f, (float) this.f, (float) this.f);
        this.q.setCornerRadius((float) this.f, (float) this.f, (float) this.f, (float) this.f);
        layoutParams = new LayoutParams(-1, -1);
        this.o.setLayoutParams(layoutParams);
        this.p.setLayoutParams(layoutParams);
        this.q.setLayoutParams(layoutParams);
        linearLayout.addView(this.o);
        linearLayout2.addView(this.p);
        linearLayout3.addView(this.q);
        this.l.addView(linearLayout);
        this.l.addView(linearLayout2);
        this.l.addView(linearLayout3);
        this.j.add(this.o);
        this.j.add(this.p);
        this.j.add(this.q);
    }

    private void f() {
        this.l.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = new LayoutParams((this.b - (this.d * 4)) / 3, (this.b - (this.d * 4)) / 3);
        layoutParams.setMargins(this.f, 0, this.f, 0);
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setLayoutParams(layoutParams);
        View linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setLayoutParams(layoutParams);
        View linearLayout3 = new LinearLayout(this.a);
        linearLayout3.setLayoutParams(layoutParams);
        linearLayout.setPadding(1, 1, 1, 1);
        linearLayout2.setPadding(1, 1, 1, 1);
        linearLayout3.setPadding(1, 1, 1, 1);
        linearLayout.setBackgroundResource(R.drawable.selecter_raditem_press);
        linearLayout2.setBackgroundResource(R.drawable.selecter_raditem_press);
        linearLayout3.setBackgroundResource(R.drawable.selecter_raditem_press);
        this.q = new ECJiaSelectableRoundedImageView(this.a);
        this.r = new ECJiaSelectableRoundedImageView(this.a);
        this.s = new ECJiaSelectableRoundedImageView(this.a);
        this.q.setCornerRadius((float) this.f, (float) this.f, (float) this.f, (float) this.f);
        this.r.setCornerRadius((float) this.f, (float) this.f, (float) this.f, (float) this.f);
        this.s.setCornerRadius((float) this.f, (float) this.f, (float) this.f, (float) this.f);
        layoutParams = new LayoutParams(-1, -1);
        this.q.setLayoutParams(layoutParams);
        this.r.setLayoutParams(layoutParams);
        this.s.setLayoutParams(layoutParams);
        linearLayout.addView(this.q);
        linearLayout2.addView(this.r);
        linearLayout3.addView(this.s);
        this.l.addView(linearLayout);
        this.l.addView(linearLayout2);
        this.l.addView(linearLayout3);
        this.j.add(this.q);
        this.j.add(this.r);
        this.j.add(this.s);
        if (this.c.size() < 7) {
            linearLayout3.setVisibility(4);
        } else {
            linearLayout3.setVisibility(0);
        }
    }
}
