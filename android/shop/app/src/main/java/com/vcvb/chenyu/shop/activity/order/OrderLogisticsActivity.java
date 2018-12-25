package com.vcvb.chenyu.shop.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.MainActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.order.OrderLogisticsAddressItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderLogisticsItem;
import com.vcvb.chenyu.shop.adapter.item.pay.PaySuccessItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.order.Logistics;
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

public class OrderLogisticsActivity extends BaseRecyclerViewActivity {

    private OrderDetail orderDetail = new OrderDetail();
    private List<Logistics> logisticses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.pay_success);
        changeStatusBarTextColor(true);
        orderDetail = (OrderDetail) getIntent().getSerializableExtra("order");
        setNavBack();
        initView();
        getData(true);
        initListener();
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        TextView title = findViewById(R.id.textView123);
        title.setText(R.string.logistics_info);
        TextView ohter = findViewById(R.id.textView122);
        ohter.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.pay_list);
//        mRecyclerView.addItemDecoration(new PayItemDecoration(context));
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getData(final boolean b) {
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("ship_name", orderDetail.getShipping_name());
        mp.put("ship_no", orderDetail.getInvoice_no());
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.ORDER_LOGISTICS, mp, new HttpUtils
                .NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
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
                        if (b) {
                            loadingDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    public void bindViewData(JSONObject json) {
        if (json != null) {
            try {
                if (json.getInt("code") == 0) {
                    JSONArray array = json.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = (JSONObject) array.get(i);
                        Logistics logistics = JsonUtils.fromJsonObject(object, Logistics.class);
                        if (i == 0) {
                            logistics.setIs_last(true);
                        }
                        logisticses.add(logistics);
                    }
                    mAdapter.addAll(getItems(logisticses));
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

    protected List<Item> getItems(List<Logistics> logistics) {
        List<Item> cells = new ArrayList<>();
        OrderLogisticsAddressItem orderLogisticsAddressItem = new OrderLogisticsAddressItem
                (orderDetail, context);
        cells.add(orderLogisticsAddressItem);
        for (int i = 0; i < logistics.size(); i++) {
            cells.add(new OrderLogisticsItem(logistics.get(i), context));
        }
        return cells;
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textView122:
                case R.id.imageView23:
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    SwipeBackHelper.finish(OrderLogisticsActivity.this);
                    break;
            }
        }
    };

    PaySuccessItem.OnClickListener paySuccessItemListener = new PaySuccessItem.OnClickListener() {
        @Override
        public void onClicked(View view, String str) {
            switch (str) {
                case "HOME":
                    Intent intent = new Intent(OrderLogisticsActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case "ORDER":
                    Intent intent_o = new Intent(OrderLogisticsActivity.this, OrderListActivity
                            .class);
                    startActivity(intent_o);
                    break;
            }
        }
    };
}
