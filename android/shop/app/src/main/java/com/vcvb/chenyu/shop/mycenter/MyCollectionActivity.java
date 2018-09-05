package com.vcvb.chenyu.shop.mycenter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;
import com.vcvb.chenyu.shop.overrideView.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends BaseActivity {
    Context context;
    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<CartListBean> carts = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    private RefreshLayout refreshLayout;

    public LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarTextColor(true);
        setContentView(R.layout.collection_list);
        context = this;
        setNavBack();
        initView();
        initRefresh();
    }

    @Override
    public void setNavBack() {
        super.setNavBack();
        int gravity = Gravity.CENTER;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        TextView titleView = new TextView(this);
        titleView.setLayoutParams(layoutParams);
        titleView.setGravity(gravity);
        titleView.setText(R.string.collection);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(120, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        LinearLayout nav_other = (LinearLayout) findViewById(R.id.nav_other);
        nav_other.setLayoutParams(layoutParams2);
        nav_other.setAlpha(0);

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        LinearLayout title_wrap = (LinearLayout) findViewById(R.id.title_wrap);
        title_wrap.setAlpha(1);
        title_wrap.setLayoutParams(layoutParams3);
        title_wrap.addView(titleView);
    }

    public void initView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.cart_content);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initRefresh(){
        refreshLayout = (RefreshLayout) findViewById(R.id.collection_list);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
    }
}
