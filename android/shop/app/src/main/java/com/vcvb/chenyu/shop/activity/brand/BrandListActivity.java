package com.vcvb.chenyu.shop.activity.brand;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.brand.fragment.BrandAllFragment;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.home.FragmentFind;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BrandListActivity extends BaseRecyclerViewActivity {

    ViewPager mViewPager;
    List<Fragment> mFragments;

    String[] mTitles = new String[]{"全部", "上新", "促销"};
    int[] mIcon = new int[]{R.drawable.icon_tab_all, R.drawable.icon_tab_new, R.drawable
            .icon_tab_sale};
    int[] mIconActive = new int[]{R.drawable.icon_tab_all_active, R.drawable.icon_tab_new_active,
            R.drawable
            .icon_tab_sale_active};
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
        mFragments.add(new BrandAllFragment());
        mFragments.add(new FragmentFind());
        mFragments.add(new FragmentFind());

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < mTitles.length; i++) {
            if(i == 0){
                mTabLayout.getTabAt(i).setCustomView(tabIcon(mTitles[i], mIconActive[i], i));
            }else {
                mTabLayout.getTabAt(i).setCustomView(tabIcon(mTitles[i], mIcon[i], i));
            }
        }

        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) mTabLayout.getChildAt(0);

//                    int dp10 = dip2px(mTabLayout.getContext(), 10);
                    int dp10 = 20;
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView
                                .getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void getData(boolean b) {

    }

    @Override
    public void initListener() {
    }

    private View tabIcon(String name, int iconID, int i) {
        View newtab = LayoutInflater.from(context).inflate(R.layout.brand_tab_item, null);
        TextView tv = newtab.findViewById(R.id.tabtext);
        tv.setText(name);
        ImageView im = newtab.findViewById(R.id.tabicon);
        im.setImageResource(iconID);
        if (i == 0) {
            tv.setTextColor(context.getResources().getColor(R.color.colorBack_morandi));
        }
        return newtab;
    }

    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        assert view != null;
        ImageView img_title = view.findViewById(R.id.tabicon);
        TextView txt_title = view.findViewById(R.id.tabtext);
        txt_title.setTextColor(context.getResources().getColor(R.color.colorBack_morandi));
        for (int i = 0; i < mTitles.length; i++) {
            if (txt_title.getText().toString().equals(mTitles[i])) {
                img_title.setImageResource(mIconActive[i]);
            }
        }
    }

    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        assert view != null;
        ImageView img_title = view.findViewById(R.id.tabicon);
        TextView txt_title = view.findViewById(R.id.tabtext);
        txt_title.setTextColor(context.getResources().getColor(R.color.black_29));
        for (int i = 0; i < mTitles.length; i++) {
            if (txt_title.getText().toString().equals(mTitles[i])) {
                img_title.setImageResource(mIcon[i]);
            }
        }
    }
}
