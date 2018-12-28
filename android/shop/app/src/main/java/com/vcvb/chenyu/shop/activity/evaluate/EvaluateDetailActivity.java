package com.vcvb.chenyu.shop.activity.evaluate;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.center.userinfo.UserLogoActivity;
import com.vcvb.chenyu.shop.adapter.GroupedListAdapter;
import com.vcvb.chenyu.shop.adapter.b.Item;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateLabelItem;
import com.vcvb.chenyu.shop.base.BaseGroupRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.evaluate.Content;
import com.vcvb.chenyu.shop.javaBean.evaluate.EvaImage;
import com.vcvb.chenyu.shop.javaBean.evaluate.EvaluateGroup;
import com.vcvb.chenyu.shop.javaBean.evaluate.Label;
import com.vcvb.chenyu.shop.javaBean.evaluate.Labels;
import com.vcvb.chenyu.shop.javaBean.evaluate.Star;
import com.vcvb.chenyu.shop.javaBean.evaluate.Stars;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.javaBean.order.OrderGoods;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class EvaluateDetailActivity extends BaseGroupRecyclerViewActivity {

    private OrderDetail orderDetail = new OrderDetail();
    private List<EvaluateGroup> evaluateGroups = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();

    private String[] starKey = {
            "goods_rank", "service_rank", "shipping_rank"
    };

    private int img_pos = 0;
    private String return_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.evaluate_activity);
        changeStatusBarTextColor(true);
        orderDetail = (OrderDetail) getIntent().getSerializableExtra("order");
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
        title.setText(R.string.user_evaluate);
        TextView ohter = findViewById(R.id.textView122);
        ohter.setText(R.string.publish_evaluate);
        ohter.setTextColor(context.getResources().getColor(R.color.colorFont_morandi));
        ohter.setOnClickListener(listener);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.content);
        groupedListAdapter = new GroupedListAdapter(context);
        mRecyclerView.setAdapter(groupedListAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void getData(boolean b) {
        loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.COMMENT_LABEL, mp, new HttpUtils.NetCall
                () {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
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
            for (int i = 0; i < orderDetail.getOrderGoodses().size(); i++) {
                EvaluateGroup evaluateGroup = new EvaluateGroup();
                List<Object> evaluates = new ArrayList<>();

                OrderGoods orderGoods = orderDetail.getOrderGoodses().get(i);
                evaluates.add(orderGoods);

                Labels _labels = new Labels();
                _labels.setLabels(labels);
                evaluates.add(_labels);

                Stars stars = new Stars();
                List<Star> starList = new ArrayList<>();
                for (String aStarKey : starKey) {
                    Star star = new Star();
                    star.setKey(aStarKey);
                    star.setValue(5);
                    starList.add(star);
                }
                stars.setStars(starList);
                evaluates.add(stars);

                Content content = new Content();
                content.setContent("");
                evaluates.add(content);

                EvaImage evaImage = new EvaImage();
                evaluates.add(evaImage);

                evaluateGroup.setObjs(evaluates);
                evaluateGroups.add(evaluateGroup);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        groupedListAdapter.setData(getGroupItems());
        groupedGridLayoutManager = new GroupedGridLayoutManager(context, 1,
                groupedListAdapter) {
            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                return super.getChildSpanSize(groupPosition, childPosition);
            }
        };
        mRecyclerView.setLayoutManager(groupedGridLayoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST:
//                    imgs.add(img_pos, String.valueOf(data.getParcelableExtra("uri")));
//                    files.add(img_pos, new File(ConstantManager.ImgPath.PATH, return_img));
//                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    protected List<EvaluateGroup> getGroupItems() {
        if (evaluateGroups.size() > 0) {
            for (int i = 0; i < evaluateGroups.size(); i++) {
                EvaluateGoodsItem evaluateGoodsItem = new EvaluateGoodsItem(evaluateGroups.get(i),
                        context);
                evaluateGroups.get(i).setHeader(evaluateGoodsItem);
                List<Item> items = new ArrayList<>();
                for (int j = 0; j < evaluateGroups.get(i).getObjs().size(); j++) {
                    if (evaluateGroups.get(i).getObjs().get(j) instanceof Labels) {
                        EvaluateLabelItem evaluateLabelItem = new EvaluateLabelItem(evaluateGroups.get(i),
                                context);
                        items.add(evaluateLabelItem);
                    }
                }
                evaluateGroups.get(i).setItemList(items);
            }
        }
        return evaluateGroups;
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
//        if (content.equals("")) {
//            ToastUtils.showShortToast(context, "评论不能为空");
//            return;
//        }
//        HashMap<String, String> mp = new HashMap<>();
//        mp.put("token", token);
//        mp.put("goods_rank", String.valueOf(star.get("goods_rank")));
//        mp.put("service_rank", String.valueOf(star.get("service_rank")));
//        mp.put("shipping_rank", String.valueOf(star.get("shipping_rank")));
//
//        List<Integer> label_ids = new ArrayList<>();
//        for (int i = 0; i < labels.size(); i++) {
//            if (labels.get(i).isIs_select()) {
//                label_ids.add(labels.get(i).getId());
//            }
//        }
//        mp.put("label_ids", StringUtils.join(label_ids, ","));
//        mp.put("info", content);
//
//        List<Integer> goods_ids = new ArrayList<>();
//        for (int i = 0; i < orderDetail.getOrderGoodses().size(); i++) {
//            goods_ids.add(orderDetail.getOrderGoodses().get(i).getGoods_id());
//        }
//        mp.put("goods_ids", StringUtils.join(goods_ids, ","));
//        mp.put("order_id", orderDetail.getOrder_id_str());
//        mp.put("ru_id", orderDetail.getRu_id());
//        HttpUtils.getInstance().postImage(ConstantManager.Url.COMMENT_ADD, mp, files, new
//                HttpUtils.NetCall() {
//                    @Override
//                    public void success(Call call, final JSONObject json) throws IOException {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (json != null) {
//                                    try {
//                                        if (json.getInt("code") == 0) {
//                                            ToastUtils.showShortToast(context, "谢谢您的评价！");
//                                            Intent intent = new Intent();
//                                            setResult(RESULT_OK, intent);
//                                            finish();
//                                        }
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void failed(Call call, IOException e) {
//
//                    }
//                });
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

//    EvaluateContentItem.OnClickListener contentListener = new EvaluateContentItem.OnClickListener
//            () {
//        @Override
//        public void onClicked(View view, String info) {
//            content = info;
//        }
//    };
//
//    EvaluateImgItem.OnClickListener imgsListener = new EvaluateImgItem.OnClickListener() {
//        @Override
//        public void onClicked(View view, int pos) {
//            if (pos == imgs.size() && pos <= 5) {
//                goToAlbum(pos);
//            } else {
//                if (imgs.size() > pos) {
//                    imgs.remove(pos);
//                }
//                if (files.size() > pos) {
//                    if (files.get(pos).exists() && files.get(pos).isFile()) {
//                        if (files.get(pos).delete()) {
//                            ToastUtils.showShortToast(context, "删除成功");
//                        } else {
//                            ToastUtils.showShortToast(context, "删除失败");
//                        }
//                    }
//                    files.remove(pos);
//                }
//                mAdapter.notifyDataSetChanged();
//            }
//        }
//    };
//
//    EvaluateLabelItem.OnClickListener labelListener = new EvaluateLabelItem.OnClickListener() {
//        @Override
//        public void onClicked(View view, int pos) {
//            if (labels.get(pos).isIs_select()) {
//                labels.get(pos).setIs_select(false);
//            } else {
//                labels.get(pos).setIs_select(true);
//            }
//            mAdapter.notifyDataSetChanged();
//        }
//    };
//
//    EvaluateStarItem.OnClickListener starListener = new EvaluateStarItem.OnClickListener() {
//        @Override
//        public void onClicked(View view, HashMap<String, Integer> mp) {
//            star = mp;
//            mAdapter.notifyDataSetChanged();
//        }
//    };
}
