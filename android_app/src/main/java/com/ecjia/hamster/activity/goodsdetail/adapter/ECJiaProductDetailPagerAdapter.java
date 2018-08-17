package com.ecjia.hamster.activity.goodsdetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class ECJiaProductDetailPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> a = null;
    private List<String> b = null;

    public ECJiaProductDetailPagerAdapter(FragmentManager fragmentManager, List<Fragment> list, List<String> list2) {
        super(fragmentManager);
        this.a = list;
        this.b = list2;
    }

    public Fragment getItem(int i) {
        return (Fragment) this.a.get(i);
    }

    public int getCount() {
        return this.a.size();
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) this.b.get(i);
    }
}
