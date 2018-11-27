package com.vcvb.chenyu.shop.order;

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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.order.OrderListItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderNoDataItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.OrderItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.ConfirmDialog;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class OrderListActivity extends BaseActivity {
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
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();

    private List<OrderDetail> orders = new ArrayList<>();
    private GridLayoutManager mLayoutManager;
    private OrderItemDecoration spaces;

    private int type = 0;
    private int page = 1;
    private int position = 0;

    private RefreshLayout refreshLayout;
    private ConfirmDialog confirmDialog;

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
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView titleView = new TextView(this);
        titleView.setLayoutParams(layoutParams);
        titleView.setGravity(gravity);
        titleView.setText(R.string.order_list);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(120, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        LinearLayout nav_other = findViewById(R.id.nav_other);
        nav_other.setLayoutParams(layoutParams2);
        nav_other.setAlpha(0);

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout title_wrap = findViewById(R.id.title_wrap);
        title_wrap.setAlpha(1);
        title_wrap.setLayoutParams(layoutParams3);
        title_wrap.addView(titleView);
    }

    public void initView() {
        line = findViewById(R.id.view26);
        tv1 = findViewById(R.id.textView74);
        tv2 = findViewById(R.id.textView75);
        tv3 = findViewById(R.id.textView76);
        tv4 = findViewById(R.id.textView77);
        tv5 = findViewById(R.id.textView78);

        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        getData(type, true);
        setTypeStyle(type);

        mRecyclerView = findViewById(R.id.order_content);
        mLayoutManager = new GridLayoutManager(context, 1);
        spaces = new OrderItemDecoration(context);
        mRecyclerView.addItemDecoration(spaces);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        refreshLayout = findViewById(R.id.order_wrap);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getData(type, false);
                refreshLayout.finishRefresh(10000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadmore();
                refreshLayout.finishLoadMore(10000/*,false*/);//传入false表示加载失败
            }
        });

        confirmDialog = new ConfirmDialog(context);
        confirmDialog.setTitle(R.string.is_delete);
    }

    public void initListener() {
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(1, true);
                setTypeStyle(1);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(2, true);
                setTypeStyle(2);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(3, true);
                setTypeStyle(3);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(4, true);
                setTypeStyle(4);
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(5, true);
                setTypeStyle(5);
            }
        });

        confirmDialog.setOnDialogClickListener(new ConfirmDialog.OnDialogClickListener() {
            @Override
            public void onConfirmClickListener() {
                cancelOrder();
                confirmDialog.dismiss();
            }

            @Override
            public void onCancelClickListener() {
                confirmDialog.dismiss();
            }
        });
    }

    public void getData(int type, final boolean bool) {
        page = 1;
        this.type = type;
        if (bool) {
            loadingDialog = new LoadingDialog(this, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("page", page + "");
        mp.put("token", token);
        mp.put("order_type", "" + type);
        HttpUtils.getInstance().post(ConstantManager.Url.ORDERS, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bool) {
                            loadingDialog.dismiss();
                        }
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        bindViewData(json);
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bool) {
                            loadingDialog.dismiss();
                        }
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
            }
        });
    }

    public void bindViewData(JSONObject json) {
        if (json != null) {
            mAdapter.clear();
            orders.clear();
            try {
                Integer code = json.getInt("code");
                if (code == 0) {
                    JSONArray orderJSONArray = json.getJSONArray("data");
                    for (int i = 0; i < orderJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) orderJSONArray.get(i);
                        OrderDetail orderDetail = JsonUtils.fromJsonObject(object, OrderDetail
                                .class);
                        orderDetail.setData(object);
                        orders.add(orderDetail);
                    }
                    mAdapter.addAll(getItems(orders));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadmore() {
        page += 1;
        HashMap<String, String> mp = new HashMap<>();
        mp.put("page", page + "");
        mp.put("token", token);
        mp.put("order_type", "" + type);
        HttpUtils.getInstance().post(ConstantManager.Url.ORDERS, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bindViewMoreData(json);
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                    }
                });
            }
        });
    }

    public void bindViewMoreData(JSONObject json) {
        if (json != null) {
            try {
                Integer code = json.getInt("code");
                if (code == 0) {
                    List<OrderDetail> _orders = new ArrayList<>();
                    JSONArray orderJSONArray = json.getJSONArray("data");
                    for (int i = 0; i < orderJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) orderJSONArray.get(i);
                        OrderDetail orderDetail = JsonUtils.fromJsonObject(object, OrderDetail
                                .class);
                        orderDetail.setData(object);
                        _orders.add(orderDetail);
                    }
                    mAdapter.addAll(_orders.size(), getItems(_orders));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<Item> getItems(List<OrderDetail> orderList) {
        List<Item> cells = new ArrayList<>();
        if (orders.size() > 0) {
            for (int i = 0; i < orderList.size(); i++) {
                OrderListItem orderListItem = new OrderListItem(orderList.get(i), context);
                orderListItem.setOnItemClickListener(orderListListener);
                cells.add(orderListItem);
            }
        } else {
            cells.add(new OrderNoDataItem(null, context));
        }
        return cells;
    }

    //订单类型样式
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

    //取消订单
    public void cancelOrder() {
        final OrderDetail orderDetail = orders.get(position);
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("order_status", "2");
        mp.put("order_id", orderDetail.getOrder_id_str());
        HttpUtils.getInstance().post(ConstantManager.Url.CANCEL_ORDER, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        orders.remove(position);
                        mAdapter.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    // fixme 再次购买

    public void buyAgain(){
        final OrderDetail orderDetail = orders.get(position);
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("froms", "android");
        mp.put("order_id", orderDetail.getOrder_id_str());
        HttpUtils.getInstance().post(ConstantManager.Url.ADD_ORDER, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(json);
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    OrderListItem.OnClickListener orderListListener = new OrderListItem
            .OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            position = pos;
            switch (view.getId()) {
                case R.id.now_pay://立即支付
                    System.out.println("立即支付");
                    break;
                case R.id.cancel_order://取消购买
                    confirmDialog.show();
                    break;
                case R.id.buy_again://再次购买
                    buyAgain();
                    break;
                case R.id.after_sale://售后
                    System.out.println("售后");
                    break;
                case R.id.look_express://查看物流
                    System.out.println("查看物流");
                    break;
                case R.id.take_goods://确认收货
                    System.out.println("确认收货");
                    break;
                case R.id.evaluate://评价
                    System.out.println("评价");
                    break;
                default:

                    break;
            }
        }
    };
}