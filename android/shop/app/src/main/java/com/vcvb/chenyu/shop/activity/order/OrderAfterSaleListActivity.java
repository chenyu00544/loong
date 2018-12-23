package com.vcvb.chenyu.shop.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.order.OrderListItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderNoDataItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.OrderItemDecoration;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
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

public class OrderAfterSaleListActivity extends BaseRecyclerViewActivity {
    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();

    private List<OrderDetail> orders = new ArrayList<>();
    private GridLayoutManager mLayoutManager;
    private OrderItemDecoration spaces;

    private int page = 1;
    private int position = 0;

    private RefreshLayout refreshLayout;
    private ConfirmDialog confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_after_sale_list);
        changeStatusBarTextColor(true);
        context = this;
        setNavBack();
        initView();
        initListener();
        getData(true);
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        TextView title = findViewById(R.id.textView123);
        title.setText(R.string.sale_after);
        ImageView ohter = findViewById(R.id.imageView94);
        ohter.setAlpha(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initView() {

        mRecyclerView = findViewById(R.id.order_content);
        mLayoutManager = new GridLayoutManager(context, 1);
        spaces = new OrderItemDecoration(context);
        mRecyclerView.addItemDecoration(spaces);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        refreshLayout = findViewById(R.id.order_wrap);

        confirmDialog = new ConfirmDialog(context);
        confirmDialog.setTitle(context.getResources().getString(R.string.is_delete));
    }

    public void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getData(false);
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

        confirmDialog.setOnDialogClickListener(new ConfirmDialog.OnDialogClickListener() {
            @Override
            public void onConfirmClickListener() {
            }

            @Override
            public void onCancelClickListener() {
            }
        });
    }

    public void getData(final boolean bool) {
        page = 1;
        if (bool) {
            loadingDialog = new LoadingDialog(this, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("page", page + "");
        mp.put("token", token);
        mp.put("order_type", "6");
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
        mp.put("order_type", "6");
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
                    int index = orders.size();
                    List<OrderDetail> _orders = new ArrayList<>();
                    JSONArray orderJSONArray = json.getJSONArray("data");
                    for (int i = 0; i < orderJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) orderJSONArray.get(i);
                        OrderDetail orderDetail = JsonUtils.fromJsonObject(object, OrderDetail
                                .class);
                        orderDetail.setData(object);
                        _orders.add(orderDetail);
                        orders.add(orderDetail);
                    }
                    mAdapter.addAll(index, getItems(_orders));
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
            if (page == 1) {
                OrderNoDataItem orderNoDataItem = new OrderNoDataItem(null, context);
                orderNoDataItem.setOnItemClickListener(orderNoDataListener);
                cells.add(orderNoDataItem);
            }
        }
        return cells;
    }

    // fixme 售后
    public void  afterSale(){
        OrderDetail orderDetail = orders.get(position);
        Intent intent = new Intent(OrderAfterSaleListActivity.this, OrderAfterSaleActivity.class);
        intent.putExtra("order_id", orderDetail.getOrder_id_str());
        startActivityForResult(intent, ConstantManager.ResultStatus.ORDER_RESULT);
    }

    OrderNoDataItem.OnClickListener orderNoDataListener = new OrderNoDataItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            switch (view.getId()) {
                case R.id.textView82:
                    Intent intentM = new Intent();
                    intentM.putExtra("code", ConstantManager.ResultStatus.ORDER_RESULT);
                    OrderAfterSaleListActivity.this.setResult(RESULT_OK, intentM);
                    finish();
                    break;
            }
        }
    };

    OrderListItem.OnClickListener orderListListener = new OrderListItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            position = pos;
            switch (view.getId()) {
                case R.id.sale_after_return://售后
                    afterSale();
                    break;
                default:
                    break;
            }
        }
    };
}
