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
import com.vcvb.chenyu.shop.javaBean.SerializableHashMap;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.pay.PaySuccessActivity;
import com.vcvb.chenyu.shop.tools.HttpUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class OrderDetailsActivity extends BaseRecyclerViewActivity {

    private List<OrderDetail> orderDetails = new ArrayList<>();

    private String orderId = "";
    private Integer goodsId;
    private HashMap<String, Object> attr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        changeStatusBarTextColor(true);
        context = this;
        goodsId = getIntent().getIntExtra("goods_id", 0);
        orderId = getIntent().getStringExtra("order");
        Bundle bundle = getIntent().getExtras();
        attr = ((SerializableHashMap) bundle.get("map")).getMap();
        System.out.println(attr);
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
        if (orderId != null && !orderId.equals("")) {
            mp.put("order_id", orderId);
        } else {
            mp.put("goods_id", goodsId + "");
            mp.put("num", attr.get("num") + "");
            List<Integer> attr_ids = (List<Integer>) attr.get("attr_ids");
            List<Integer> goods_attr_ids = (List<Integer>) attr.get("goods_attr_ids");
            if (attr_ids != null && goods_attr_ids != null && attr_ids.size() > 0 &&
                    goods_attr_ids.size() > 0) {
                for (int i = 0; i < attr_ids.size(); i++) {
                    mp.put("attr_" + i, attr_ids.get(i) + "#" + goods_attr_ids.get(i));
                }
            }
        }
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.GET_ORDER, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                System.out.println(json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
                        }
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

        for (int i = 1; i < 9; i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setIsType(i);
            if (i == 1) {
                orderDetail.setAddressId(i);
                orderDetail.setOrderConsignee("asdffa");
                orderDetail.setOrderPhone("18858788888");
                orderDetail.setOrderAddress("asdfjklasdfjkladjkl");
            } else if (i == 2) {
                orderDetail.setOrderId("6546579846516598");
                orderDetail.setOrderDate("2020-19-22 00:00:00");
                orderDetail.setTotalOncePay(18.40);
                orderDetail.setTotalOncePayFormat("$18.40");
            } else if (i > 2 && i < 7) {
                orderDetail.setGoodsName("asdfafasf" + i);
                orderDetail.setGoodsAttr("dfas12123fasf" + i);
                orderDetail.setGoodsPrice(15.5);
                orderDetail.setGoodsPriceFormat("$15.5");
                orderDetail.setGoodsMarket(25.5);
                orderDetail.setGoodsMarketFormat("$25.5");
                orderDetail.setGoodsNum(i);
            } else if (i == 7) {
                orderDetail.setPayType(0);
            } else if (i == 8) {
                orderDetail.setTotalPay(188.40);
                orderDetail.setTotalPayFormat("$188.40");
                orderDetail.setTotalPayAble(180.40);
                orderDetail.setTotalPayAbleFormat("$180.40");
                orderDetail.setShipFree(5.00);
                orderDetail.setShipFreeFormat("$5.00");
                orderDetail.setDiscount(22.00);
                orderDetail.setDiscountFormat("$22.00");
            }
            orderDetails.add(orderDetail);
        }
        mAdapter.addAll(getItems(orderDetails));
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    protected List<Item> getItems(List<OrderDetail> list) {
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
