package com.vcvb.chenyu.shop;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.vcvb.chenyu.shop.dialog.LoadingDialog;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class BaseActivity extends SwipeBackActivity {
    private SwipeBackLayout swipeBackLayout;
    public LinearLayout nav_bar;
    public LinearLayout nav_back;
    public LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //滑动返回
        setSwipeBackEnable(true);
        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    public void setNavBack() {
        nav_bar = (LinearLayout) findViewById(R.id.nav_header);
        nav_back = (LinearLayout) findViewById(R.id.nav_back);
        if(nav_back != null){
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    public void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View
                        .SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                //恢复状态栏白色字体
            }
        }
    }

    public void initView(){

    }

    public void initRefresh(){

    }
    public void getData(final boolean b){

    }
    public void initListener(){

    }
}
