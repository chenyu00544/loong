package com.ecjia.hamster.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class ECJiaADPagerAdapter extends PagerAdapter {
    Context a;
    ArrayList<View> b;

    public ECJiaADPagerAdapter(Context context, ArrayList<View> arrayList) {
        this.a = context;
        this.b = arrayList;
    }

    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.b.get(i);
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
    }

    public int getCount() {
        return this.b.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
