package com.vcvb.chenyu.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import java.util.HashMap;
import java.util.Locale;

public class LaunchActivity extends Activity {
    private static int TOTAL_TIME = 3000;
    private static int ONECE_TIME = 1000;
    public Context context;

    private Button btn;
    private ViewPager mPager;
    private FlowLayout bootDot;
    private TextView inShop;

    private String isShowBootPage = "no";

    private int[] imgs = {R.drawable.boot_page_1, R.drawable.boot_page_2, R.drawable.boot_page_3,
            R.drawable.boot_page_4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        isShowBootPage = (String) UserInfoUtils.getInstance(this).getUserInfo().get
                ("is_show_boot_page");
        context = this;
        getData();
        initView();
        initPager();
    }

    public void initView() {
        mPager = findViewById(R.id.boot_pages);
        bootDot = findViewById(R.id.boot_dot);
        btn = findViewById(R.id.skip_bt);
        inShop = findViewById(R.id.textView296);
    }

    public void getData() {
//        HttpUtils.getInstance().post(ConstantManager.Url.GET_BOOT_PAGE, null, new HttpUtils
//                .NetCall() {
//            @Override
//            public void success(Call call, final JSONObject json) throws IOException {
//                if (json != null) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            bindViewData(json);
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void failed(Call call, IOException e) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        initPager();
//                    }
//                });
//            }
//        });
    }

    public void onClickToMainActivity(View view) {
        countDownTimer.cancel();
        startMain();
    }

    public void initPager() {
        if (isShowBootPage == null || isShowBootPage.equals("no")) {
            btn.setVisibility(View.GONE);
            inShop.setVisibility(View.GONE);
            mPager.setVisibility(View.VISIBLE);
            bootDot.setVisibility(View.VISIBLE);
            initDot(0);
            mPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return imgs.length;
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    ImageView view = new ImageView(context);
                    view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    Glide.with(context).load(imgs[position]).into(view);
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
                public void onPageScrolled(int position, float positionOffset, int
                        positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    initDot(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            mPager.setVisibility(View.GONE);
            bootDot.setVisibility(View.GONE);
            inShop.setVisibility(View.GONE);
            btn.setVisibility(View.VISIBLE);
            countDownTimer.start();
        }
    }

    private void initDot(int pos) {
        if (pos == imgs.length - 1) {
            bootDot.setVisibility(View.GONE);
            inShop.setVisibility(View.VISIBLE);
        }else{
            bootDot.setVisibility(View.VISIBLE);
            inShop.setVisibility(View.GONE);
        }
        bootDot.setChildSpacing(8);
        bootDot.setRowSpacing(8);
        bootDot.setChildSpacingForLastRow(8);
        bootDot.removeAllViews();
        RequestOptions requestOptions = RequestOptions.circleCropTransform().diskCacheStrategy
                (DiskCacheStrategy.ALL).dontAnimate();
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(20, 20);
        for (int i = 0; i < imgs.length; i++) {
            ImageView photoView = new ImageView(context);
            photoView.setId(IdsUtils.generateViewId());
            photoView.setLayoutParams(lp);
            if (i == pos) {
                Glide.with(context).load(R.drawable.icon_circle_wihte).apply(requestOptions).into(photoView);
            } else {
                Glide.with(context).load(R.drawable.icon_circle_border_wihte).apply(requestOptions).into(photoView);
            }
            bootDot.addView(photoView);
        }
    }

    private CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, ONECE_TIME) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            String str = "%s 跳过";
            btn.setText(String.format(Locale.CHINA, str, value));
        }

        @Override
        public void onFinish() {
            btn.setText("0 跳过");
            startMain();
        }
    };

    public void startMain() {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("is_show_boot_page", "ok");
        UserInfoUtils.getInstance(context).setUserInfo(mp);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        LaunchActivity.this.finish();
    }

    public void startMainView(View view) {
        startMain();
    }
}
