package com.vcvb.chenyu.shop.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsShipItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAddressItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderIdItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderPayTypeItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderTotalItem;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.javaBean.order.OrderGoods;
import com.vcvb.chenyu.shop.javaBean.order.Pay;
import com.vcvb.chenyu.shop.pay.PaySuccessActivity;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;

public class OrderDetailsActivity extends BaseRecyclerViewActivity {

    private List<OrderDetail> orderDetails = new ArrayList<>();
    private List<Pay> pays = new ArrayList<>();
    private List<AddressBean> addresses = new ArrayList<>();

    private TextView payTotalView;

    private String orderId = "";
    private Double pay_total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
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
        title.setText(R.string.order_detail);
        TextView ohter = findViewById(R.id.textView122);
        ohter.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        TextView pay = findViewById(R.id.textView179);
        pay.setOnClickListener(listener);
        mRecyclerView = findViewById(R.id.order_detail);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        payTotalView = findViewById(R.id.textView206);
    }

    @Override
    public void getData(final boolean b) {
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("order_id", orderId);
//        mp.put("order_id", "117,118");
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.GET_ORDER, mp, new HttpUtils.NetCall() {
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
                Integer code = json.getInt("code");
                if (code == 0) {
                    JSONArray orderJSONArray = json.getJSONObject("data").getJSONArray("order");
                    for (int i = 0; i < orderJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) orderJSONArray.get(i);
                        OrderDetail orderDetail = JsonUtils.fromJsonObject(object, OrderDetail
                                .class);
                        orderDetail.setData(object);
                        orderDetails.add(orderDetail);
                    }

                    JSONArray addressJSONArray = json.getJSONObject("data").getJSONArray("address");
                    for (int i = 0; i < addressJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) addressJSONArray.get(i);
                        AddressBean addressBean = JsonUtils.fromJsonObject(object, AddressBean
                                .class);
                        addresses.add(addressBean);
                    }

                    JSONArray payJSONArray = json.getJSONObject("data").getJSONArray("pays");
                    for (int i = 0; i < payJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) payJSONArray.get(i);
                        Pay pay = JsonUtils.fromJsonObject(object, Pay.class);
                        pays.add(pay);
                    }

                    mAdapter.addAll(getItems());
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

    @Override
    public void initListener() {
        super.initListener();
    }

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < addresses.size(); i++) {
            if (addresses.get(i).getDef() == 1) {
                OrderAddressItem orderAddressItem = new OrderAddressItem(addresses.get(i), context);
                orderAddressItem.setOnItemClickListener(addressListener);
                cells.add(orderAddressItem);
            }
        }

        Double goods_amount = 0.0;
        Double shipping_fee = 0.0;
        Double tax = 0.0;
        Double discount = 0.0;
        pay_total = 0.0;
        for (int i = 0; i < orderDetails.size(); i++) {
            OrderIdItem orderIdItem = new OrderIdItem(orderDetails.get(i), context);
            cells.add(orderIdItem);
            List<OrderGoods> orderGoodsList = orderDetails.get(i).getOrderGoodses();
            for (int j = 0; j < orderGoodsList.size(); j++) {
                OrderGoodsItem orderGoodsItem = new OrderGoodsItem(orderGoodsList.get(j), context);
                cells.add(orderGoodsItem);
            }
            goods_amount += Double.valueOf(orderDetails.get(i).getGoods_amount());
            shipping_fee += Double.valueOf(orderDetails.get(i).getShipping_fee());
            tax += Double.valueOf(orderDetails.get(i).getTax());
            discount += Double.valueOf(orderDetails.get(i).getDiscount());
        }
        pay_total += goods_amount+shipping_fee+tax-discount;
        String pay_total_str = "￥%.2f";
        payTotalView.setText(String.format(Locale.CHINA, pay_total_str, pay_total));
        for (int i = 0; i < pays.size(); i++) {
            if (pay_code != null && !pay_code.equals("")) {
                if (pays.get(i).getPay_code().equals(pay_code)) {
                    pays.get(i).setDef(1);
                }
            } else {
                pay_code = pays.get(0).getPay_code();
                pays.get(0).setDef(1);
            }
        }
        OrderPayTypeItem orderPayTypeItem = new OrderPayTypeItem(pays, context);
        orderPayTypeItem.setOnItemClickListener(payListener);
        cells.add(orderPayTypeItem);

        cells.add(new OrderTotalItem(orderDetails, context));
        return cells;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OrderDetailsActivity.this.finish();
        overridePendingTransition(0, 0);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageView23:
                    SwipeBackHelper.finish(OrderDetailsActivity.this);
                    break;
                case R.id.textView179:
                    Intent intent = new Intent(OrderDetailsActivity.this, PaySuccessActivity.class);
                    startActivityForResult(intent, ConstantManager.Pay.PAY_SUCCESS);
                    OrderDetailsActivity.this.finish();
                    break;
            }
        }
    };

    OrderPayTypeItem.OnClickListener payListener = new GoodsShipItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(112);
        }
    };

    OrderAddressItem.OnClickListener addressListener = new OrderAddressItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(12);
        }
    };
}
