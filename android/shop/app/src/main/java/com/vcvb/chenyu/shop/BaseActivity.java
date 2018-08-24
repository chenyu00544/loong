package com.vcvb.chenyu.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class BaseActivity extends SwipeBackActivity {
    private SwipeBackLayout swipeBackLayout;
    public LinearLayout nav_bar;
    public LinearLayout nav_back;

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
}
