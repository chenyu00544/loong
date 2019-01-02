package com.vcvb.chenyu.shop.activity.brand;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.home.FragmentFind;
import com.vcvb.chenyu.shop.home.FragmentMy;

import java.util.ArrayList;
import java.util.List;

public class BrandListActivity extends BaseRecyclerViewActivity {

    ViewPager mViewPager;
    List<Fragment> mFragments;

    String[] mTitles = new String[]{
            "主页", "微博", "相册"
    };
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_store);
        context = this;
        changeStatusBarTextColor(false);
        setNavBack();
        initView();
        initListener();
        getData(true);
    }

    @Override
    public void setNavBack() {
    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);
        setupViewPager();

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                scroll += dy;
//                if(scroll < 70){
//
//                }
//                System.out.println(scroll);
//            }
//        });
//
//        VerticalOverScrollBounceEffectDecorator decorator = new
//                VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter
//                (mRecyclerView));
//        decorator.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
//            @Override
//            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
//                final View view = decor.getView();
//                System.out.println("-----" + offset);
//                if (offset > 0) {
//                    set.constrainHeight(imageView.getId(), ToolUtils.dip2px(context, 150+offset));
//                    set.constrainWidth(imageView.getId(), (int) (width+offset));
//                    set.connect(cly.getId(), ConstraintSet.LEFT, imageView.getId(),
// ConstraintSet.LEFT, (int) (-offset/2));
//                    // 'view' is currently being over-scrolled from the top.
//                } else if (offset < 0) {
//                    // 'view' is currently being over-scrolled from the bottom.
//                } else {
//                    set.constrainHeight(imageView.getId(), ToolUtils.dip2px(context, 150));
//                    set.constrainWidth(imageView.getId(), width);
//                    // No over-scroll is in-effect.
//                    // This is synonymous with having (state == STATE_IDLE).
//                }
//                set.applyTo(cly);
//            }
//        });
    }


    private void setupViewPager() {

        mFragments = new ArrayList<>();
        mFragments.add(new FragmentMy());
        mFragments.add(new FragmentFind());
        mFragments.add(new FragmentFind());

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void getData(boolean b) {

    }

    @Override
    public void initListener() {
    }
}
