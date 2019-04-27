package com.vcvb.chenyu.shop.activity.evaluate;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.donkingliang.groupedadapter.widget.StickyHeaderLayout;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.center.userinfo.UserLogoActivity;
import com.vcvb.chenyu.shop.adapter.GroupedListAdapter;
import com.vcvb.chenyu.shop.adapter.b.Item;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateContentItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateImgItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateLabelItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaluateStarItem;
import com.vcvb.chenyu.shop.base.BaseGroupRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.ConfirmDialog;
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
public class EvaluateDetailActivity extends BaseGroupRecyclerViewActivity {

    private OrderDetail orderDetail = new OrderDetail();
    private List<EvaluateGroup> evaluateGroups = new ArrayList<>();
    private String[] starKey = {"goods_rank", "service_rank", "shipping_rank"};

    private int img_group = 0;
    private int img_pos = 0;

    private String return_img;

    private ConfirmDialog confirmDialog;

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
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        StickyHeaderLayout stickyHeaderLayout = findViewById(R.id.sticky_layout);
        stickyHeaderLayout.setSticky(false);
        confirmDialog = new ConfirmDialog(context);
    }

    @Override
    public void initListener() {
        super.initListener();
        confirmDialog.setOnDialogClickListener(new ConfirmDialog.OnDialogClickListener() {
            @Override
            public void onConfirmClickListener() {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onCancelClickListener() {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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
        evaluateGroups.clear();
        JSONArray jsonArray = json.getJSONArray("data");
        try {
            List<Integer> goods_ids = new ArrayList<>();
            for (int i = 0; i < orderDetail.getOrderGoodses().size(); i++) {
                boolean bool = false;
                for (int n = 0; n < goods_ids.size(); n++) {
                    if (goods_ids.get(n).equals(orderDetail.getOrderGoodses().get(i).getGoods_id
                            ())) {
                        bool = true;
                    }
                }
                if (bool) {
                    continue;
                }
                goods_ids.add(orderDetail.getOrderGoodses().get(i).getGoods_id());

                List<Label> labels = new ArrayList<>();
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject object = (JSONObject) jsonArray.get(j);
                    Label label = JsonUtils.fromJsonObject(object, Label.class);
                    if (i < 3) {
                        label.setIs_select(true);
                    }
                    labels.add(label);
                }

                EvaluateGroup evaluateGroup = new EvaluateGroup();
                List<Object> evaluates = new ArrayList<>();

                OrderGoods orderGoods = orderDetail.getOrderGoodses().get(i);
                evaluateGroup.setHeader(orderGoods);

                Labels _labels = new Labels();
                _labels.setLabels(labels);
                evaluates.add(_labels);

                Stars stars = new Stars();
                List<Star> starList = new ArrayList<>();
                for (String aStarKey : starKey) {
                    Star star = new Star();
                    star.setKey(aStarKey);
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
        if (groupedListAdapter != null) {
            groupedListAdapter.notifyDataRemoved();
        }
        groupedListAdapter.setData(getGroupItems());
        groupedGridLayoutManager = new GroupedGridLayoutManager(context, 1, groupedListAdapter) {
            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                return super.getChildSpanSize(groupPosition, childPosition);
            }
        };
        mRecyclerView.setLayoutManager(groupedGridLayoutManager);
    }

    protected List<EvaluateGroup> getGroupItems() {
        if (evaluateGroups.size() > 0) {
            for (int i = 0; i < evaluateGroups.size(); i++) {
                EvaluateGoodsItem evaluateGoodsItem = new EvaluateGoodsItem(evaluateGroups.get(i)
                        , context);
                evaluateGroups.get(i).setMheader(evaluateGoodsItem);
                List<Item> items = new ArrayList<>();
                for (int j = 0; j < evaluateGroups.get(i).getObjs().size(); j++) {
                    if (evaluateGroups.get(i).getObjs().get(j) instanceof Labels) {
                        EvaluateLabelItem evaluateLabelItem = new EvaluateLabelItem
                                (evaluateGroups.get(i), context);
                        evaluateLabelItem.setSubOnItemClickListener(labelListener);
                        items.add(evaluateLabelItem);
                    } else if (evaluateGroups.get(i).getObjs().get(j) instanceof Stars) {
                        EvaluateStarItem evaluateStarItem = new EvaluateStarItem(evaluateGroups
                                .get(i), context);
                        evaluateStarItem.setSubOnItemClickListener(starListener);
                        items.add(evaluateStarItem);
                    } else if (evaluateGroups.get(i).getObjs().get(j) instanceof Content) {
                        EvaluateContentItem evaluateContentItem = new EvaluateContentItem
                                (evaluateGroups.get(i), context);
                        evaluateContentItem.setOnItemClickListener(contentListener);
                        items.add(evaluateContentItem);
                    } else if (evaluateGroups.get(i).getObjs().get(j) instanceof EvaImage) {
                        EvaluateImgItem evaluateImgItem = new EvaluateImgItem(evaluateGroups.get
                                (i), context);
                        evaluateImgItem.setSubOnItemClickListener(imgsListener);
                        items.add(evaluateImgItem);
                    }
                }
                evaluateGroups.get(i).setItemList(items);
            }
        }
        return evaluateGroups;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST:
                    for (int i = 0; i < evaluateGroups.get(img_group).getObjs().size(); i++) {
                        if (evaluateGroups.get(img_group).getObjs().get(i) instanceof EvaImage) {
                            EvaImage _evaImage = (EvaImage) evaluateGroups.get(img_group).getObjs
                                    ().get(i);
                            _evaImage.getImgs().add(img_pos, String.valueOf(data
                                    .getParcelableExtra("uri")));
                            _evaImage.getFiles().add(img_pos, new File(ConstantManager.ImgPath
                                    .PATH, return_img));
                            groupedListAdapter.notifyChildChanged(img_group, i);
                        }
                    }
                    break;
            }
        }
    }

    //相册权限
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPhotoAlbum() {
        Intent intent = new Intent(EvaluateDetailActivity.this, UserLogoActivity.class);
        return_img = String.format(Locale.CHINA, "RETURN_IMG_%s.jpg", System.currentTimeMillis());
        intent.putExtra("return_uri", return_img);
        startActivityForResult(intent, ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST);
    }

    public void goToAlbum(int group, int pos) {
        img_group = group;
        img_pos = pos;
        EvaluateDetailActivityPermissionsDispatcher.showPhotoAlbumWithCheck(this);
    }

    public void publishEvaluate() {
        HashMap<String, String> mp = new HashMap<>();
        List<File> files = new ArrayList<>();
        List<Integer> goods_ids = new ArrayList<>();
        List<Integer> ru_ids = new ArrayList<>();
        List<Integer> rec_ids = new ArrayList<>();
        mp.put("token", token);
        for (int i = 0; i < evaluateGroups.size(); i++) {
            OrderGoods orderGoods = (OrderGoods) evaluateGroups.get(i).getHeader();
            boolean bool = false;
            for (int n = 0; n < goods_ids.size(); n++) {
                if (goods_ids.get(n).equals(orderDetail.getOrderGoodses().get(i).getGoods_id())) {
                    bool = true;
                }
            }
            if (bool) {
                continue;
            }
            goods_ids.add(orderDetail.getOrderGoodses().get(i).getGoods_id());
            mp.put("ru_id_" + orderDetail.getOrderGoodses().get(i).getGoods_id(), orderDetail
                    .getOrderGoodses().get(i).getRu_id() + "");
            mp.put("rec_id_" + orderDetail.getOrderGoodses().get(i).getGoods_id(), orderDetail
                    .getOrderGoodses().get(i).getRec_id() + "");
            rec_ids.add(orderDetail.getOrderGoodses().get(i).getRec_id());
            for (int j = 0; j < evaluateGroups.get(i).getObjs().size(); j++) {
                if (evaluateGroups.get(i).getObjs().get(j) instanceof Labels) {
                    Labels labels = (Labels) evaluateGroups.get(i).getObjs().get(j);
                    List<Integer> list = new ArrayList<>();
                    for (int k = 0; k < labels.getLabels().size(); k++) {
                        if (labels.getLabels().get(k).isIs_select()) {
                            list.add(labels.getLabels().get(k).getId());
                        }
                    }
                    mp.put("label_ids_" + orderGoods.getGoods_id(), StringUtils.join(list, ","));
                } else if (evaluateGroups.get(i).getObjs().get(j) instanceof Stars) {
                    Stars stars = (Stars) evaluateGroups.get(i).getObjs().get(j);
                    for (int k = 0; k < starKey.length; k++) {
                        mp.put(stars.getStars().get(k).getKey() + "_" + orderGoods.getGoods_id(),
                                String.valueOf(stars.getStars().get(k).getValue()));
                    }
                } else if (evaluateGroups.get(i).getObjs().get(j) instanceof Content) {
                    Content content = (Content) evaluateGroups.get(i).getObjs().get(j);
                    if (content.getContent().equals("")) {
                        ToastUtils.showShortToast(context, context.getResources().getString(R
                                .string.comment_no_empty));
                        return;
                    }
                    mp.put("content_" + orderGoods.getGoods_id(), content.getContent());
                } else if (evaluateGroups.get(i).getObjs().get(j) instanceof EvaImage) {
                    EvaImage evaImage = (EvaImage) evaluateGroups.get(i).getObjs().get(j);
                    files.addAll(evaImage.getFiles());
                    mp.put("img_num_" + orderGoods.getGoods_id(), String.valueOf(evaImage
                            .getFiles().size()));
                }
            }
        }

        mp.put("goods_ids", StringUtils.join(goods_ids, ","));
        mp.put("order_id", orderDetail.getOrder_id_str());

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
                                            confirmDialog.setTitle(context.getResources()
                                                    .getString(R
                                                    .string.thank_you_comment));
                                            confirmDialog.show();
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
                                ToastUtils.showShortToast(context, context.getResources()
                                        .getString(R
                                        .string.net_error));
                            }
                        });
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

    EvaluateLabelItem.OnSubItemClickListener labelListener = new EvaluateLabelItem
            .OnSubItemClickListener() {
        @Override
        public void clicked(int group, int pos, View v) {
            for (int i = 0; i < evaluateGroups.get(group).getObjs().size(); i++) {
                if (evaluateGroups.get(group).getObjs().get(i) instanceof Labels) {
                    Labels _labels = (Labels) evaluateGroups.get(group).getObjs().get(i);
                    if (_labels.getLabels().get(pos).isIs_select()) {
                        _labels.getLabels().get(pos).setIs_select(false);
                    } else {
                        _labels.getLabels().get(pos).setIs_select(true);
                    }
                    groupedListAdapter.notifyChildChanged(group, i);
                }
            }
        }
    };

    EvaluateStarItem.OnSubItemClickListener starListener = new EvaluateStarItem
            .OnSubItemClickListener() {

        @Override
        public void clicked(int group, int pos, View v) {
            for (int i = 0; i < evaluateGroups.get(group).getObjs().size(); i++) {
                if (evaluateGroups.get(group).getObjs().get(i) instanceof Stars) {
                    Stars _stars = (Stars) evaluateGroups.get(group).getObjs().get(i);
                    if (pos >= 1 && pos <= 5) {
                        _stars.getStars().get(0).setValue(pos);
                    } else if (pos >= 6 && pos <= 10) {
                        _stars.getStars().get(1).setValue(pos - 5);
                    } else if (pos >= 11 && pos <= 15) {
                        _stars.getStars().get(2).setValue(pos - 10);
                    }
                    groupedListAdapter.notifyChildChanged(group, i);
                }
            }
        }
    };

    EvaluateContentItem.OnClickListener contentListener = new EvaluateContentItem.OnClickListener
            () {
        @Override
        public void onClicked(View view, int group, String info) {
            for (int i = 0; i < evaluateGroups.get(group).getObjs().size(); i++) {
                if (evaluateGroups.get(group).getObjs().get(i) instanceof Content) {
                    Content _content = (Content) evaluateGroups.get(group).getObjs().get(i);
                    _content.setContent(info);
                }
            }
        }
    };

    EvaluateImgItem.OnSubItemClickListener imgsListener = new EvaluateImgItem
            .OnSubItemClickListener() {
        @Override
        public void clicked(int group, int pos, View v) {
            for (int i = 0; i < evaluateGroups.get(group).getObjs().size(); i++) {
                if (evaluateGroups.get(group).getObjs().get(i) instanceof EvaImage) {
                    EvaImage _evaImage = (EvaImage) evaluateGroups.get(group).getObjs().get(i);
                    if (pos == _evaImage.getImgs().size() && pos <= 5) {
                        goToAlbum(group, pos);
                    } else {
                        if (_evaImage.getImgs().size() > pos) {
                            _evaImage.getImgs().remove(pos);
                        }
                        if (_evaImage.getFiles().size() > pos) {
                            if (_evaImage.getFiles().get(pos).exists() && _evaImage.getFiles()
                                    .get(pos).isFile()) {
                                if (_evaImage.getFiles().get(pos).delete()) {
                                    ToastUtils.showShortToast(context, context.getResources()
                                            .getString(R.string.del_success));
                                } else {
                                    ToastUtils.showShortToast(context, context.getResources()
                                            .getString(R.string.del_failed));
                                }
                            }
                            _evaImage.getFiles().remove(pos);
                        }
                        groupedListAdapter.notifyChildChanged(group, i);
                    }
                }
            }
        }
    };
}
