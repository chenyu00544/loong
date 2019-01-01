package com.vcvb.chenyu.shop.activity.photoview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PhotoViewPagerActivity extends BaseActivity {

    private ViewPager mPager;
    private TextView num;
    private List<String> img_url = new ArrayList<>();
    private int pos = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view_pager_activity);
        context = this;
        changeStatusBarTextColor(false);
        img_url = getIntent().getStringArrayListExtra("imgs");
        setNavBack();
        initView();
        getData(true);
        initListener();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            });
        }
    }

    @Override
    public void initView() {
        super.initView();
        mPager = findViewById(R.id.photo_view);
        num = findViewById(R.id.textView293);
        String str = "%d/%d";
        num.setText(String.format(Locale.CHINA, str, pos, img_url.size()));
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
    }

    @Override
    public void initListener() {
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return img_url.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(context);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(context).load(img_url.get(position)).into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String str = "%d/%d";
                num.setText(String.format(Locale.CHINA, str, position+1, img_url.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
