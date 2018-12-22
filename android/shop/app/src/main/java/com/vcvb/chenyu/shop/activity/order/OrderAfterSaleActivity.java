package com.vcvb.chenyu.shop.activity.order;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.center.userinfo.UserLogoActivity;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleAddressItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleBriefItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleCauseItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleImgItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleInvoiceNoItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderAfterSaleTotalItem;
import com.vcvb.chenyu.shop.adapter.item.order.OrderIdItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.dialog.OrderReturnCauseDialog;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.javaBean.order.OrderReturnCause;
import com.vcvb.chenyu.shop.javaBean.order.ReturnOrder;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class OrderAfterSaleActivity extends BaseRecyclerViewActivity {

    private OrderDetail orderDetail = new OrderDetail();
    private ReturnOrder returnOrder = new ReturnOrder();
    private List<OrderReturnCause> orderReturnCauses = new ArrayList<>();
    private List<String> imgs = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    private String orderId = "";
    private TextView bottomBt;
    private int img_pos = 0;
    private String return_img;
    private OrderReturnCauseDialog orderReturnCauseDialog;
    private HashMap<String, String> outMp = new HashMap<>();

    private Receiver receiver;

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
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

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

                    if (orderDetail.getShipping_status() == 0 && orderDetail.getPay_status() == 2
                            && orderDetail.getOrder_status() == 1) {
                        bottomBt.setText(R.string.apply_refound);
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

                    JSONArray returnJSONArray = json.getJSONObject("data").getJSONArray
                            ("return_order");
                    for (int i = 0; i < returnJSONArray.length(); i++) {
                        JSONObject object = (JSONObject) returnJSONArray.get(i);
                        returnOrder = JsonUtils.fromJsonObject(object, ReturnOrder.class);
                    }
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
            OrderAfterSaleGoodsItem orderAfterSaleGoodsItem = new OrderAfterSaleGoodsItem
                    (orderDetail.getOrderGoodses().get(i), context);
            cells.add(orderAfterSaleGoodsItem);
        }

        OrderAfterSaleCauseItem orderAfterSaleCauseItem = new OrderAfterSaleCauseItem
                (orderReturnCauses, context);
        orderAfterSaleCauseItem.setOnItemClickListener(causeListener);
        cells.add(orderAfterSaleCauseItem);

        OrderAfterSaleBriefItem orderAfterSaleBriefItem = new OrderAfterSaleBriefItem(null,
                context);
        cells.add(orderAfterSaleBriefItem);

        if (orderDetail.getShipping_status() == 1) {
            OrderAfterSaleImgItem orderAfterSaleImgItem = new OrderAfterSaleImgItem(imgs, context);
            orderAfterSaleImgItem.setOnItemClickListener(imgsListener);
            cells.add(orderAfterSaleImgItem);
        }

        if (returnOrder != null && returnOrder.getReturn_type() > 0) {
            OrderAfterSaleInvoiceNoItem orderAfterSaleInvoiceNoItem = new
                    OrderAfterSaleInvoiceNoItem(returnOrder, context);
            orderAfterSaleInvoiceNoItem.setOnItemClickListener(imgsListener);
            cells.add(orderAfterSaleInvoiceNoItem);
        }


        cells.add(new OrderAfterSaleTotalItem(orderDetail, context));

        return cells;
    }

    //相册权限
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPhotoAlbum() {
        Intent intent = new Intent(OrderAfterSaleActivity.this, UserLogoActivity.class);
        return_img = String.format(Locale.CHINA, "RETURN_IMG_%s.jpg", System.currentTimeMillis());
        intent.putExtra("return_uri", return_img);
        startActivityForResult(intent, ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST);
    }

    public void goToAlbum(int type) {
        img_pos = type;
        OrderAfterSaleActivityPermissionsDispatcher.showPhotoAlbumWithCheck(this);
    }

    //fixme 申请退货， 填写运单
    public void returnGoods() {
        if (returnOrder != null && returnOrder.getAgree_apply() == 0) {
            ToastUtils.showShortToast(context, "等待商家反馈");
        }

        if (outMp.get("cause_id") == null) {
            ToastUtils.showShortToast(context, "请选择退换货原因");
            return;
        }
        if (outMp.get("return_brief") == null) {
            outMp.put("return_brief", "");
        }
        loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();
        mp.put("order_id", orderId);
        mp.put("token", token);
        mp.put("cause_id", outMp.get("cause_id"));
        mp.put("return_brief", outMp.get("return_brief"));
        HttpUtils.getInstance().postImage(ConstantManager.Url.ORDER_RETURN_GOODS, mp, files, new
                HttpUtils.NetCall() {
                    @Override
                    public void success(Call call, final JSONObject json) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismiss();
                                try {
                                    if (json != null) {
                                        if (json.getInt("code") == 0) {
                                            initReturnOrder(json);
                                        } else {
                                            ToastUtils.showShortToast(context, json.getString
                                                    ("msg"));
                                        }
                                    } else {
                                        ToastUtils.showShortToast(context, "网络错误");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                                ToastUtils.showShortToast(context, "网络错误");
                            }
                        });
                    }
                });
    }

    public void initReturnOrder(JSONObject json) {
        JSONArray returnJSONArray = null;
        try {
            returnJSONArray = json.getJSONObject("data").getJSONArray
                    ("return_order");
            for (int i = 0; i < returnJSONArray.length(); i++) {
                JSONObject object = (JSONObject) returnJSONArray.get(i);
                returnOrder = JsonUtils.fromJsonObject(object, ReturnOrder.class);
            }
            bottomBt.setText(R.string.store_feedback);
            bottomBt.setBackgroundResource(R.color.gray);
            bottomBt.setTextColor(context.getResources().getColor(R.color.black));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void registerReceiver() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("briefAction");
        receiver = new Receiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null) {
                    switch (intent.getAction()) {
                        case "briefAction":
                            outMp.put("return_brief", intent.getStringExtra("data"));
                            break;
                    }
                }
            }
        };
        broadcastManager.registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.unregisterReceiver(receiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST:
                    imgs.add(img_pos, String.valueOf(data.getParcelableExtra("uri")));
                    files.add(img_pos, new File(ConstantManager.ImgPath.PATH, return_img));
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    View.OnClickListener bottomListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            returnGoods();
        }
    };
    OrderAfterSaleCauseItem.OnClickListener causeListener = new OrderAfterSaleCauseItem
            .OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            orderReturnCauseDialog.show(getSupportFragmentManager(), "Cause");
        }
    };
    OrderAfterSaleImgItem.OnClickListener imgsListener = new OrderAfterSaleImgItem
            .OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            if (pos == imgs.size() && pos <= 5) {
                goToAlbum(pos);
            } else {
                if (imgs.size() > pos) {
                    imgs.remove(pos);
                }
                if (files.size() > pos) {
                    if (files.get(pos).exists() && files.get(pos).isFile()) {
                        if (files.get(pos).delete()) {
                            ToastUtils.showShortToast(context, "删除成功");
                        } else {
                            ToastUtils.showShortToast(context, "删除失败");
                        }
                    }
                    files.remove(pos);
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    };
    OrderReturnCauseDialog.OnClickListener returnCauseListener = new OrderReturnCauseDialog
            .OnClickListener() {
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
