package com.vcvb.chenyu.shop.activity.brand;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.home.FragmentFind;
import com.vcvb.chenyu.shop.home.FragmentMy;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class BrandListActivity extends BaseRecyclerViewActivity {

    ViewPager mViewPager;
    List<Fragment> mFragments;

    String[] mTitles = new String[]{"主页", "微博", "相册"};
    private TabLayout mTabLayout;

    private TextView upDown;
    private ImageView upDownIcon;
    private TextView brandInfo;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private int changeHeight = 100;
    private int brandInitHeight = 0;
    private int cInitHeight;

    //    private ConstraintLayout brand_info;
    private RelativeLayout brand_info;
    private ConstraintSet set = new ConstraintSet();

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
        upDown = findViewById(R.id.textView304);
//        upDownIcon = findViewById(R.id.imageView155);
        brand_info = findViewById(R.id.brand_info);
//        set.clone(brand_info);
//        brandInfo = findViewById(R.id.textView303);
//        brandInfo.setText("这一看不就是跟Material " +
//                "Design工具栏折叠效果类似。我们捋一下效果是怎样的，滑动的时候实现搜索栏渐变以及高度改变的工具栏折叠效果这一看不就是跟Material " +
//                "Design工具栏折叠效果类似。我们捋一下效果是怎样的，滑动的时候实现搜索栏渐变以及高度改变的工具栏折叠效果这一看不就是跟Material " +
//                "Design工具栏折叠效果类似。我们捋一下效果是怎样的，滑动的时候实现搜索栏渐变以及高度改变的工具栏折叠效果这一看不就是跟Material " +
//                "Design工具栏折叠效果类似。我们捋一下效果是怎样的，滑动的时候实现搜索栏渐变以及高度改变的工具栏折叠效果");
//        ViewTreeObserver vto = brandInfo.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (initHeight == 0) {
//                    initHeight = brandInfo.getMeasuredHeight();
//                    brandInfoHeight = brandInfo.getLineHeight() * 3 + 5;
//                    set.constrainHeight(brandInfo.getId(), brandInfoHeight);
//                    set.applyTo(brand_info);
//                    changeHeight = initHeight - brandInfoHeight;
//                }
//            }
//        });

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        cInitHeight = ToolUtils.dip2px(context, 250);
        brandInitHeight = ToolUtils.dip2px(context, 150);
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);
        setupViewPager();

        upDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (upDown.getTag().equals("down")) {
                    upDown.setTag("up");
//                    upDownIcon.setImageResource(R.drawable.icon_forward_up);
                    animator(true);
                } else {
                    upDown.setTag("down");
//                    upDownIcon.setImageResource(R.drawable.icon_forward_down);
                    animator(false);
                }
            }
        });

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

    private void animator(boolean b) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            TransitionManager.beginDelayedTransition(brand_info);
//        }
        if (b) {
//            set.constrainHeight(brandInfo.getId(), initHeight);
//            set.applyTo(brand_info);
            ValueAnimator anim = ValueAnimator.ofInt(cInitHeight, cInitHeight + changeHeight);
            anim.setDuration(500);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    int currentValue = (Integer) animation.getAnimatedValue();

                    collapsingToolbarLayout.getLayoutParams().height = currentValue;

                    brand_info.getLayoutParams().height = currentValue-cInitHeight+brandInitHeight;

                    collapsingToolbarLayout.requestLayout();

                }
            });
            anim.start();

//            ValueAnimator anim2 = ValueAnimator.ofInt(brandHeaderHeight, brandHeaderHeight +
//                    changeHeight);
//            anim2.setDuration(500);
//            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//
//                    int currentValue = (Integer) animation.getAnimatedValue();
//
//                    brand_info.getLayoutParams().height = currentValue;
//
//                    brand_info.requestLayout();
//
//                }
//            });
//            anim2.start();
        } else {
//            set.constrainHeight(brandInfo.getId(), brandInfoHeight);
//            set.applyTo(brand_info);
            ValueAnimator anim = ValueAnimator.ofInt(cInitHeight + changeHeight, cInitHeight);
            anim.setDuration(500);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    int currentValue = (Integer) animation.getAnimatedValue();

                    collapsingToolbarLayout.getLayoutParams().height = currentValue;

                    brand_info.getLayoutParams().height = currentValue-cInitHeight+brandInitHeight;

                    collapsingToolbarLayout.requestLayout();
                }
            });
            anim.start();

//            ValueAnimator anim2 = ValueAnimator.ofInt(brandHeaderHeight + changeHeight,
//                    brandHeaderHeight);
//            anim2.setDuration(500);
//            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//
//                    int currentValue = (Integer) animation.getAnimatedValue();
//
//                    brand_info.getLayoutParams().height = currentValue;
//
//                    brand_info.requestLayout();
//
//                }
//            });
//            anim2.start();
        }
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
