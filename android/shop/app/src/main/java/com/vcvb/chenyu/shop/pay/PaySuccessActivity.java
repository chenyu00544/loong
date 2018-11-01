package com.vcvb.chenyu.shop.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.MainActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCGridAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsItem;
import com.vcvb.chenyu.shop.adapter.item.pay.PayGoodsHeaderItem;
import com.vcvb.chenyu.shop.adapter.item.pay.PaySuccessItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.PayItemDecoration;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.mycenter.OrderActivity;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class PaySuccessActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private CYCGridAdapter mAdapter = new CYCGridAdapter();
    private OrderDetail orderDetail = new OrderDetail();
    private GridLayoutManager mLayoutManager;

    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.pay_success);
        changeStatusBarTextColor(true);
        setNavBack();
        initView();
        getData(true);
        initListener();
        orderId = getIntent().getStringExtra("order_id");
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        TextView title = findViewById(R.id.textView123);
        title.setText(R.string.pay_success);
        TextView ohter = findViewById(R.id.textView122);
        ohter.setText(R.string.over);
        ohter.setOnClickListener(listener);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.pay_list);
        mAdapter = new CYCGridAdapter();
        mRecyclerView.addItemDecoration(new PayItemDecoration(context));
        mLayoutManager = new GridLayoutManager(context, 6);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getData(final boolean b) {
        super.getData(b);
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
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

        orderDetail.setOrderConsignee("dsfaf");
        orderDetail.setOrderPhone("18858786888");
        orderDetail.setOrderAddress("jklasdfjkl;asdfuiopjkasdfasdf");
        orderDetail.setTotalPayAbleFormat("$999.0");
        List<Goods> goodses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Goods goods = new Goods();
            goods.setGoods_name("jkasdf"+i);
            goodses.add(goods);
        }
        orderDetail.setList(goodses);
        mAdapter.addAll(getItems(orderDetail));
    }

    protected List<Item> getItems(OrderDetail orderDetail) {
        List<Item> cells = new ArrayList<>();
        PaySuccessItem paySuccessItem = new PaySuccessItem(orderDetail, context);
        paySuccessItem.setOnItemClickListener(paySuccessItemListener);
        cells.add(paySuccessItem);
        cells.add(new PayGoodsHeaderItem(orderDetail, context));
        for (int i = 0; i < orderDetail.getList().size(); i++){
            cells.add(new GoodsItem(orderDetail.getList().get(i), context));
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
                    SwipeBackHelper.finish(PaySuccessActivity.this);
                    break;
            }
        }
    };

    PaySuccessItem.OnClickListener paySuccessItemListener = new PaySuccessItem.OnClickListener() {
        @Override
        public void onClicked(View view, String str) {
            switch (str) {
                case "HOME":
                    Intent intent = new Intent(PaySuccessActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case "ORDER":
                    Intent intent_o = new Intent(PaySuccessActivity.this, OrderActivity.class);
                    startActivity(intent_o);
                    break;
            }
        }
    };
}
