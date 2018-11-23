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
import com.vcvb.chenyu.shop.adapter.item.order.OrderAddressItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderIdItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderPayTypeItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderTotalItem;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
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

import okhttp3.Call;

public class OrderDetailsActivity extends BaseRecyclerViewActivity {

    private List<OrderDetail> orderDetails = new ArrayList<>();
    private List<Pay> pays = new ArrayList<>();
    private List<AddressBean> addresses = new ArrayList<>();

    private String orderId = "";

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
                        Pay pay = JsonUtils.fromJsonObject(object, Pay
                                .class);
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
//        for (int i = 1; i < 9; i++) {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setIsType(i);
//            if (i == 1) {
//                orderDetail.setAddressId(i);
//                orderDetail.setOrderConsignee("asdffa");
//                orderDetail.setOrderPhone("18858788888");
//                orderDetail.setOrderAddress("asdfjklasdfjkladjkl");
//            } else if (i == 2) {
//                orderDetail.setOrderId("6546579846516598");
//                orderDetail.setOrderDate("2020-19-22 00:00:00");
//                orderDetail.setTotalOncePay(18.40);
//                orderDetail.setTotalOncePayFormat("$18.40");
//            } else if (i > 2 && i < 7) {
//                orderDetail.setGoodsName("asdfafasf" + i);
//                orderDetail.setGoodsAttr("dfas12123fasf" + i);
//                orderDetail.setGoodsPrice(15.5);
//                orderDetail.setGoodsPriceFormat("$15.5");
//                orderDetail.setGoodsMarket(25.5);
//                orderDetail.setGoodsMarketFormat("$25.5");
//                orderDetail.setGoodsNum(i);
//            } else if (i == 7) {
//                orderDetail.setPayType(0);
//            } else if (i == 8) {
//                orderDetail.setTotalPay(188.40);
//                orderDetail.setTotalPayFormat("$188.40");
//                orderDetail.setTotalPayAble(180.40);
//                orderDetail.setTotalPayAbleFormat("$180.40");
//                orderDetail.setShipFree(5.00);
//                orderDetail.setShipFreeFormat("$5.00");
//                orderDetail.setDiscount(22.00);
//                orderDetail.setDiscountFormat("$22.00");
//            }
//            orderDetails.add(orderDetail);
//        }

    }

    @Override
    public void initListener() {
        super.initListener();
    }

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case 1:
                    cells.add(new OrderAddressItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new OrderIdItem(list.get(i), context));
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    cells.add(new OrderGoodsItem(list.get(i), context));
                    break;
                case 7:
                    cells.add(new OrderPayTypeItem(list.get(i), context));
                    break;
                case 8:
                    cells.add(new OrderTotalItem(list.get(i), context));
                    break;
            }
        }
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
}
