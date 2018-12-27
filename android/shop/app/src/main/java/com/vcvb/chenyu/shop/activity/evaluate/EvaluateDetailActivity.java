package com.vcvb.chenyu.shop.activity.evaluate;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.center.userinfo.UserLogoActivity;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateContentItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateImgItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateLabelItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateStarItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.evaluate.Label;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;

import org.apache.commons.lang3.StringUtils;
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
public class EvaluateDetailActivity extends BaseRecyclerViewActivity {

    private OrderDetail orderDetail = new OrderDetail();
    private List<String> imgs = new ArrayList<>();
    private List<File> files = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();

    HashMap<String, Integer> star = new HashMap<>();
    private String content = "";

    private int img_pos = 0;
    private String return_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.recycler_activity);
        changeStatusBarTextColor(true);
        orderDetail = (OrderDetail) getIntent().getSerializableExtra("order");
        setNavBack();
        initView();
        initListener();
        getData(false);
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        TextView title = findViewById(R.id.textView123);
        title.setText(R.string.user_evaluate);
        TextView ohter = findViewById(R.id.textView122);
        ohter.setText(R.string.publish_evaluate);
        ohter.setTextColor(context.getResources().getColor(R.color.colorFont_morandi));
        ohter.setOnClickListener(listener);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.list);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        star.put("goods_rank", 5);
        star.put("service_rank", 5);
        star.put("shipping_rank", 5);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void getData(boolean b) {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.COMMENT_LABEL, mp, new HttpUtils.NetCall
                () {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json != null) {
                            try {
                                if (json.getInt("code") == 0) {
                                    bindViewData(json);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    public void bindViewData(JSONObject json) throws JSONException {
        JSONArray jsonArray = json.getJSONArray("data");
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Label label = JsonUtils.fromJsonObject(object, Label.class);
                if (i < 3) {
                    label.setIs_select(true);
                }
                labels.add(label);
            }
            mAdapter.addAll(getItems());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
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

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < orderDetail.getOrderGoodses().size(); i++) {
            EvaluateGoodsItem evaluateGoodsItem = new EvaluateGoodsItem(orderDetail, context);
            cells.add(evaluateGoodsItem);

            if (orderDetail.getComment_status() == 0) {
                if (labels.size() > 0) {
                    EvaluateLabelItem evaluateLabelItem = new EvaluateLabelItem(labels, context);
                    evaluateLabelItem.setOnItemClickListener(labelListener);
                    cells.add(evaluateLabelItem);
                }

                EvaluateStarItem evaluateStarItem = new EvaluateStarItem(star, context);
                evaluateStarItem.setOnItemClickListener(starListener);
                cells.add(evaluateStarItem);
            }

            EvaluateContentItem evaluateContentItem = new EvaluateContentItem(orderDetail, context);
            evaluateContentItem.setOnItemClickListener(contentListener);
            cells.add(evaluateContentItem);

            EvaluateImgItem evaluateImgItem = new EvaluateImgItem(imgs, context);
            evaluateImgItem.setOnItemClickListener(imgsListener);
            cells.add(evaluateImgItem);
        }
        return cells;
    }

    //相册权限
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPhotoAlbum() {
        Intent intent = new Intent(EvaluateDetailActivity.this, UserLogoActivity.class);
        return_img = String.format(Locale.CHINA, "RETURN_IMG_%s.jpg", System.currentTimeMillis());
        intent.putExtra("return_uri", return_img);
        startActivityForResult(intent, ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST);
    }

    public void goToAlbum(int type) {
        img_pos = type;
        EvaluateDetailActivityPermissionsDispatcher.showPhotoAlbumWithCheck(this);
    }

    public void publishEvaluate() {
        if (content.equals("")) {
            ToastUtils.showShortToast(context, "评论不能为空");
            return;
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("goods_rank", String.valueOf(star.get("goods_rank")));
        mp.put("service_rank", String.valueOf(star.get("service_rank")));
        mp.put("shipping_rank", String.valueOf(star.get("shipping_rank")));

        List<Integer> label_ids = new ArrayList<>();
        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).isIs_select()) {
                label_ids.add(labels.get(i).getId());
            }
        }
        mp.put("label_ids", StringUtils.join(label_ids, ","));
        mp.put("info", content);

        List<Integer> goods_ids = new ArrayList<>();
        for (int i = 0; i < orderDetail.getOrderGoodses().size(); i++) {
            goods_ids.add(orderDetail.getOrderGoodses().get(i).getGoods_id());
        }
        mp.put("goods_ids", StringUtils.join(goods_ids, ","));
        mp.put("order_id", orderDetail.getOrder_id_str());
        mp.put("ru_id", orderDetail.getRu_id());
        HttpUtils.getInstance().postImage(ConstantManager.Url.COMMENT_ADD, mp, files, new
                HttpUtils.NetCall() {
                    @Override
                    public void success(Call call, final JSONObject json) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (json != null) {
                                    try {
                                        if (json.getInt("code") == 0) {
                                            ToastUtils.showShortToast(context, "谢谢您的评价！");
                                            Intent intent = new Intent();
                                            setResult(RESULT_OK, intent);
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void failed(Call call, IOException e) {

                    }
                });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textView122:
                    //发表评价
                    publishEvaluate();
                    break;
                case R.id.imageView23:
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    SwipeBackHelper.finish(EvaluateDetailActivity.this);
                    break;
            }
        }
    };

    EvaluateContentItem.OnClickListener contentListener = new EvaluateContentItem.OnClickListener
            () {
        @Override
        public void onClicked(View view, String info) {
            content = info;
        }
    };

    EvaluateImgItem.OnClickListener imgsListener = new EvaluateImgItem.OnClickListener() {
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

    EvaluateLabelItem.OnClickListener labelListener = new EvaluateLabelItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            if (labels.get(pos).isIs_select()) {
                labels.get(pos).setIs_select(false);
            } else {
                labels.get(pos).setIs_select(true);
            }
            mAdapter.notifyDataSetChanged();
        }
    };

    EvaluateStarItem.OnClickListener starListener = new EvaluateStarItem.OnClickListener() {
        @Override
        public void onClicked(View view, HashMap<String, Integer> mp) {
            star = mp;
            mAdapter.notifyDataSetChanged();
        }
    };
}
