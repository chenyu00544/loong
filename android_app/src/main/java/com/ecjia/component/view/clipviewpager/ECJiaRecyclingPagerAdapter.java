package com.ecjia.component.view.clipviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class ECJiaRecyclingPagerAdapter extends PagerAdapter {
    private final a a;

    public abstract View a(int i, View view, ViewGroup viewGroup);

    public ECJiaRecyclingPagerAdapter() {
        this(new a());
    }

    ECJiaRecyclingPagerAdapter(a aVar) {
        this.a = aVar;
        aVar.a(a());
    }

    public void notifyDataSetChanged() {
        this.a.a();
        super.notifyDataSetChanged();
    }

    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        int a = a(i);
        View view = null;
        if (a != -1) {
            view = this.a.a(i, a);
        }
        view = a(i, view, viewGroup);
        viewGroup.addView(view);
        return view;
    }

    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = (View) obj;
        viewGroup.removeView(view);
        int a = a(i);
        if (a != -1) {
            this.a.a(view, i, a);
        }
    }

    public final boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int a() {
        return 1;
    }

    public int a(int i) {
        return 0;
    }
}
