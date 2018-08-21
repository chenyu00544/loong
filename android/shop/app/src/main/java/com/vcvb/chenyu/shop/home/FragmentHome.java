package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.image.GlideImageLoader;
import com.vcvb.chenyu.shop.image.Images;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    View view;
    Context context;

    private Banner banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        initSearchView();
        initBanner();
        initAdsList();

        return view;
    }

    private void initSearchView() {
        RelativeLayout nav_back = view.findViewById(R.id.nav_back);
        final EditText editText = view.findViewById(R.id.search_text);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                editText.setCursorVisible(true);
                return false;
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        editText.setCursorVisible(false);
                        break;
                }
                return false;
            }
        });

        final LinearLayout linearLayout = view.findViewById(R.id.search_wrap);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                linearLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = linearLayout.getRootView()
                        .getHeight();
                int heightDifference = screenHeight - (r.bottom);
                if (heightDifference > 200) {
                    //软键盘显示
                } else {
                    //软键盘隐藏
                    editText.setCursorVisible(false);
                }
            }
        });
    }

    private void initBanner() {
        banner = view.findViewById(R.id.banner);
        ArrayList<String> imageUrls = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0; i < Images.imgUrls.length; i++) {
            imageUrls.add(Images.imgUrls[i]);
        }
        titleList.add("好好学习");
        titleList.add("天天向上");
        titleList.add("热爱劳动");
        //设置内置样式，内含六种特效
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置轮播的动画效果，内含多种特效
        banner.setBannerAnimation(Transformer.Accordion);
        //设置轮播图的标题集合
        banner.setBannerTitles(titleList);
        //设置轮播间隔时间
        banner.setDelayTime(5000);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageUrls);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.i("tag", "你点了第" + position + "张轮播图");
            }
        });
        banner.start();
    }

    private void initAdsList() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        RelativeLayout ads = view.findViewById(R.id.ads_list);
        ImageButton imageButton = new ImageButton(context);
        imageButton.set
        ads.addView(imageButton);
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }
}
