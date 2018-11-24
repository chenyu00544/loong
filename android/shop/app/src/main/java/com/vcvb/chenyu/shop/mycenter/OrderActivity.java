package com.vcvb.chenyu.shop.mycenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.OrderRecyclerViewAdapter;
import com.vcvb.chenyu.shop.adapter.itemdecoration.OrderItemDecoration;
import com.vcvb.chenyu.shop.javaBean.order.OrderListBean;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import okhttp3.Call;

public class OrderActivity extends BaseActivity {
    private Context context;
    private View line;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private ConstraintSet set = new ConstraintSet();
    private ConstraintLayout cly;

    private RecyclerView mRecyclerView;
    private OrderRecyclerViewAdapter mAdapter;
    private List<OrderListBean> orders;
    private GridLayoutManager mLayoutManager;

    private View noDataView;
    private View haveDataView;

    private int type = 0;

    private RefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        changeStatusBarTextColor(true);
        context = this;
        cly = findViewById(R.id.order_list);
        set.clone(cly);
        setNavBack();
        initView();
        initListener();
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
        titleView.setText(R.string.order_list);
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

    public void initView() {
        orders = new ArrayList<>();
        line = findViewById(R.id.view26);
        tv1 = (TextView) findViewById(R.id.textView74);
        tv2 = (TextView) findViewById(R.id.textView75);
        tv3 = (TextView) findViewById(R.id.textView76);
        tv4 = (TextView) findViewById(R.id.textView77);
        tv5 = (TextView) findViewById(R.id.textView78);

        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        getOrderByList(type);
        setTypeStyle(type);
    }

    public void initListener() {
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrderByList(1);
                setTypeStyle(1);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrderByList(2);
                setTypeStyle(2);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrderByList(3);
                setTypeStyle(3);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrderByList(4);
                setTypeStyle(4);
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrderByList(5);
                setTypeStyle(5);
            }
        });
    }

    public void getOrderByList(int type) {
        this.type = type;
        loadingDialog = new LoadingDialog(this, R.style.TransparentDialog);
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", "");
        mp.put("nav_id", "0");
//        mp.put("order_type", ""+type);
        HttpUtils.getInstance().post(Routes.getInstance().getIndex(), mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                        if (orders.size() != 0) {
                            setHaveDataByView();
                        } else {
                            setNoDateByView();
                        }
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                        setNoDateByView();
                    }
                });
            }
        });

        orders.clear();
        for (int i = 0; i < 5; i++) {
            OrderListBean order = new OrderListBean();
            order.setIsType(2);
            order.setStoreName("store_name" + i);
            orders.add(order);
            for (int j = 0; j < 3; j++) {
                order = new OrderListBean();
                order.setIsType(1);
                order.setGoodsName("goods_name" + j);
                orders.add(order);
            }
            order = new OrderListBean();
            if (type == 1) {
                Random random = new Random();
                int k = random.nextInt(6);
                if (k == 2) {
                    order.setIsType(3);
                    order.setPriceTotal("$19" + i + ".00");
                } else if (k == 0 || k == 1) {
                    order.setIsType(4);
                } else {
                    order.setIsType(k);
                }
            } else if (type == 2) {
                order.setIsType(3);
                order.setPriceTotal("$19" + i + ".00");
            } else if (type == 3 || type == 4) {
                order.setIsType(4);
            } else if (type == 5) {
                order.setIsType(5);
            }
            orders.add(order);
        }
    }

    public void setTypeStyle(int type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(cly);
        }
        set.clear(line.getId());
        set.constrainWidth(line.getId(), 100);
        set.constrainHeight(line.getId(), 4);
        switch (type) {
            case 1:
                set.connect(line.getId(), ConstraintSet.LEFT, tv1.getId(), ConstraintSet.LEFT, 0);
                set.connect(line.getId(), ConstraintSet.RIGHT, tv1.getId(), ConstraintSet.RIGHT, 0);
                set.connect(line.getId(), ConstraintSet.TOP, tv1.getId(), ConstraintSet.BOTTOM, 0);
                break;
            case 2:
                set.connect(line.getId(), ConstraintSet.LEFT, tv2.getId(), ConstraintSet.LEFT, 0);
                set.connect(line.getId(), ConstraintSet.RIGHT, tv2.getId(), ConstraintSet.RIGHT, 0);
                set.connect(line.getId(), ConstraintSet.TOP, tv2.getId(), ConstraintSet.BOTTOM, 0);
                break;
            case 3:
                set.connect(line.getId(), ConstraintSet.LEFT, tv3.getId(), ConstraintSet.LEFT, 0);
                set.connect(line.getId(), ConstraintSet.RIGHT, tv3.getId(), ConstraintSet.RIGHT, 0);
                set.connect(line.getId(), ConstraintSet.TOP, tv3.getId(), ConstraintSet.BOTTOM, 0);
                break;
            case 4:
                set.connect(line.getId(), ConstraintSet.LEFT, tv4.getId(), ConstraintSet.LEFT, 0);
                set.connect(line.getId(), ConstraintSet.RIGHT, tv4.getId(), ConstraintSet.RIGHT, 0);
                set.connect(line.getId(), ConstraintSet.TOP, tv4.getId(), ConstraintSet.BOTTOM, 0);
                break;
            case 5:
                set.connect(line.getId(), ConstraintSet.LEFT, tv5.getId(), ConstraintSet.LEFT);
                set.connect(line.getId(), ConstraintSet.RIGHT, tv5.getId(), ConstraintSet.RIGHT);
                set.connect(line.getId(), ConstraintSet.TOP, tv5.getId(), ConstraintSet.BOTTOM, 0);
                break;
        }
        set.applyTo(cly);
    }

    public void setNoDateByView() {
        cly.removeView(haveDataView);
        noDataView = LayoutInflater.from(this).inflate(R.layout.order_content_no_data,
                null);
        if (findViewById(R.id.no_data) == null) {
            cly.addView(noDataView);
        }
        set.connect(noDataView.getId(), ConstraintSet.TOP, tv1.getId(), ConstraintSet.BOTTOM,
                0);
        set.connect(noDataView.getId(), ConstraintSet.LEFT, cly.getId(), ConstraintSet.LEFT, 0);
        set.connect(noDataView.getId(), ConstraintSet.RIGHT, cly.getId(), ConstraintSet
                .RIGHT, 0);
        set.connect(noDataView.getId(), ConstraintSet.BOTTOM, cly.getId(), ConstraintSet
                .BOTTOM, 0);
        set.applyTo(cly);
    }

    public void setHaveDataByView() {
        cly.removeView(noDataView);
        haveDataView = LayoutInflater.from(this).inflate(R.layout
                .order_content_have_data, null);

        if (findViewById(R.id.have_data) == null) {
            cly.addView(haveDataView);
            set.connect(haveDataView.getId(), ConstraintSet.TOP, tv1.getId(), ConstraintSet
                    .BOTTOM, 4);
            set.connect(haveDataView.getId(), ConstraintSet.LEFT, cly.getId(), ConstraintSet
                    .LEFT, 0);
            set.connect(haveDataView.getId(), ConstraintSet.RIGHT, cly.getId(), ConstraintSet
                    .RIGHT, 0);
            set.connect(haveDataView.getId(), ConstraintSet.BOTTOM, cly.getId(), ConstraintSet
                    .BOTTOM, 0);
            set.applyTo(cly);
            mRecyclerView = haveDataView.findViewById(R.id.order_content);
            mAdapter = new OrderRecyclerViewAdapter(context, orders);
            mLayoutManager = new GridLayoutManager(context, 1);
            OrderItemDecoration spaces = new OrderItemDecoration(context, orders);
            mRecyclerView.addItemDecoration(spaces);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);

            refreshLayout = (RefreshLayout) haveDataView;
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
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
