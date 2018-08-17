package com.ecjia.hamster.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

public class ECJiaCirculatoryPagerAdapter extends PagerAdapter {
    public ArrayList<View> a;
    View b;

    public ECJiaCirculatoryPagerAdapter(ArrayList<View> arrayList) {
        this.a = arrayList;
    }

    public int getCount() {
        if (this.a.size() == 0) {
            return 0;
        }
        if (this.a.size() != 1) {
            return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return 1;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.a.size() == 0) {
            return null;
        }
        if (this.a.size() == 1) {
            this.b = (View) this.a.get(i);
            viewGroup.addView(this.b);
            return this.b;
        }
        int size = i % this.a.size();
        if (size < 0) {
            size += this.a.size();
        }
        this.b = (View) this.a.get(size);
        ViewParent parent = this.b.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.b);
        }
        viewGroup.addView(this.b);
        return this.b;
    }

    public int getItemPosition(Object obj) {
        return -2;
    }
}
