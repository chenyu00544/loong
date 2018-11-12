package com.vcvb.chenyu.shop;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeListener;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

public class BaseActivity extends AppCompatActivity {

    public String token;
    public String is_real;
    public LinearLayout nav_bar;
    public LinearLayout nav_back;
    public LoadingDialog loadingDialog;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //滑动返回
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)//获取当前页面
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeRelateEnable(true)//是否与下一级activity联动。默认关
                .setSwipeRelateOffset(500)//activity联动时的偏移量。默认500px。
                .setDisallowInterceptTouchEvent(false)//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
//                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕
                .setSwipeEdgePercent(0.1f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setScrimColor(Color.BLACK)//底层阴影颜色
                .setClosePercent(0.8f)//触发关闭Activity百分比
                .addListener(new SwipeListener() {//滑动监听
                    @Override
                    public void onScroll(float percent, int px) {//滑动的百分比与距离

                    }

                    @Override
                    public void onEdgeTouch() {//当开始滑动
                    }

                    @Override
                    public void onScrollToClose() {//当滑动关闭
                    }
                });
        token = (String) UserInfoUtils.getInstance(this).getUserInfo().get("token");
        is_real = (String) UserInfoUtils.getInstance(this).getUserInfo().get("is_real");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    public void setNavBack() {
        nav_bar = findViewById(R.id.nav_header);
        nav_back = findViewById(R.id.nav_back);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SwipeBackHelper.finish(BaseActivity.this);
                }
            });
        }
    }

    public void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View
                        .SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                //恢复状态栏白色字体
            }
        }
    }

    public void initView() {

    }

    public void initRefresh() {

    }

    public void getData(final boolean b) {

    }

    public void initListener() {

    }
}
