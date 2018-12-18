package com.vcvb.chenyu.shop.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleAddressItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleCauseItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleTotalItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderIdItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.dialog.OrderReturnCauseDialog;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.javaBean.order.OrderReturnCause;
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

public class OrderAfterSaleActivity extends BaseRecyclerViewActivity {

    private OrderDetail orderDetail = new OrderDetail();
    private List<OrderReturnCause> orderReturnCauses = new ArrayList<>();
    private String orderId = "";
    private TextView bottomBt;

    private OrderReturnCauseDialog orderReturnCauseDialog;
    private HashMap<String, String> outMp = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_after_sale);
        changeStatusBarTextColor(true);
        context = this;
        orderId = getIntent().getStringExtra("order_id");
        setNavBack();
        initView();
        getData(true);
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        TextView title = findViewById(R.id.textView123);
        title.setText(R.string.sale_after);
        TextView ohter = findViewById(R.id.textView122);
        ohter.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.order_detail);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        bottomBt = findViewById(R.id.textView179);
        bottomBt.setOnClickListener(bottomListener);
    }

    @Override
    public void getData(final boolean b) {
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("order_id", orderId);
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.ORDER_AFTER_SALE, mp, new HttpUtils
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
        orderReturnCauses.clear();
        if (json != null) {
            try {
                Integer code = json.getInt("code");
                if (code == 0) {
                    JSONArray orderJSONArray = json.getJSONObject("data").getJSONArray("order");
                    for (int i = 0; i < orderJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) orderJSONArray.get(i);
                        orderDetail = JsonUtils.fromJsonObject(object, OrderDetail.class);
                        orderDetail.setData(object);
                    }

                    JSONArray causeJSONArray = json.getJSONObject("data").getJSONArray("causes");
                    for (int i = 0; i < causeJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) causeJSONArray.get(i);
                        OrderReturnCause orderReturnCause = JsonUtils.fromJsonObject(object,
                                OrderReturnCause.class);
                        orderReturnCauses.add(orderReturnCause);
                    }
                    orderReturnCauseDialog = new OrderReturnCauseDialog(orderReturnCauses);
                    orderReturnCauseDialog.setOnItemClickListener(returnCauseListener);
                }
                mAdapter.addAll(getItems());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();

        cells.add(new OrderAfterSaleAddressItem(orderDetail, context));
        OrderIdItem orderIdItem = new OrderIdItem(orderDetail, context);
        cells.add(orderIdItem);

        for (int i = 0; i < orderDetail.getOrderGoodses().size(); i++) {
            cells.add(new OrderAfterSaleGoodsItem(orderDetail.getOrderGoodses().get(i), context));
        }

        OrderAfterSaleCauseItem orderAfterSaleCauseItem = new OrderAfterSaleCauseItem
                (orderReturnCauses, context);
        orderAfterSaleCauseItem.setOnItemClickListener(causeListener);
        cells.add(orderAfterSaleCauseItem);

        cells.add(new OrderAfterSaleTotalItem(orderDetail, context));

        return cells;
    }

    public void returnGoods() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OrderAfterSaleActivity.this.finish();
        overridePendingTransition(0, 0);
    }


    View.OnClickListener bottomListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            returnGoods();
        }
    };

    OrderAfterSaleCauseItem.OnClickListener causeListener = new OrderAfterSaleCauseItem.OnClickListener(){
        @Override
        public void onClicked(View view, int pos) {
            orderReturnCauseDialog.show(getSupportFragmentManager(), "Cause");
        }
    };
    OrderReturnCauseDialog.OnClickListener returnCauseListener = new OrderReturnCauseDialog.OnClickListener(){
        @Override
        public void onClicked(View view, int pos) {
            for (int i = 0; i < orderReturnCauses.size(); i++) {
                orderReturnCauses.get(i).setIs_select(false);
                if (i == pos) {
                    orderReturnCauses.get(i).setIs_select(true);
                    outMp.put("cause_id", orderReturnCauses.get(i).getCause_id() + "");
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    };
}
