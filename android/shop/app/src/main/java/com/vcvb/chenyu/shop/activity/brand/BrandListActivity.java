package com.vcvb.chenyu.shop.activity.brand;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
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
    private int changeHeight = 0;
    private int infoInitHeight = 0;
    private int brandInitHeight = 0;
    private int cInitHeight;

    private RelativeLayout brand_info;

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
        upDownIcon = findViewById(R.id.imageView155);
        brand_info = findViewById(R.id.brand_info);
        brandInfo = findViewById(R.id.textView303);
        brandInfo.setText
                ("如果我死了，那些愧对我的人会很开心：终于不用还钱了，哎呀，不然真不知道怎么办那，不敢见他，吓得我朋友圈都不敢发了！不对，我这样的人怕他干嘛？反正也不能把我怎么样。我每天活的多潇洒，灯红酒绿左拥右抱的。怕他干嘛，我是没错的，我怎么能委屈自己那？我要活的开心就不能在乎那么多，人这一辈子就是有舍有得，凭自己本事借的钱为什么要还？——不用还");
        ViewTreeObserver vto = brandInfo.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (changeHeight == 0) {
                    changeHeight = brandInfo.getLineHeight() * (brandInfo.getLineCount() - 3) + 5;
                    System.out.println(changeHeight);
                }
            }
        });

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        cInitHeight = ToolUtils.dip2px(context, 250);
        brandInitHeight = ToolUtils.dip2px(context, 160);
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);
        setupViewPager();

        upDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (upDown.getTag().equals("down")) {
                    upDown.setTag("up");
                    upDownIcon.setImageResource(R.drawable.icon_forward_up);
                    animator(true);
                } else {
                    upDown.setTag("down");
                    upDownIcon.setImageResource(R.drawable.icon_forward_down);
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
        if (b) {
            ValueAnimator anim = ValueAnimator.ofInt(cInitHeight, cInitHeight + changeHeight);
            anim.setDuration(500);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    int currentValue = (Integer) animation.getAnimatedValue();

                    collapsingToolbarLayout.getLayoutParams().height = currentValue;

                    brand_info.getLayoutParams().height = currentValue - cInitHeight +
                            brandInitHeight;

                    collapsingToolbarLayout.requestLayout();

                }
            });
            anim.start();
        } else {
            ValueAnimator anim = ValueAnimator.ofInt(cInitHeight + changeHeight, cInitHeight);
            anim.setDuration(500);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    int currentValue = (Integer) animation.getAnimatedValue();

                    collapsingToolbarLayout.getLayoutParams().height = currentValue;

                    brand_info.getLayoutParams().height = currentValue - cInitHeight +
                            brandInitHeight;

                    collapsingToolbarLayout.requestLayout();
                }
            });
            anim.start();
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
