package com.vcvb.chenyu.shop.activity.evaluate;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaContentItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaLabelItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaNoDataItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.evaluate.Comment;
import com.vcvb.chenyu.shop.javaBean.evaluate.Label;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
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

import okhttp3.Call;

public class EvaluateListActivity extends BaseRecyclerViewActivity {

    private int label_id = 0;
    private GoodsDetail goodsDetails;

    private List<Label> labels = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private int page = 1;

    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        label_id = getIntent().getIntExtra("label_id", 0);
        goodsDetails = (GoodsDetail) getIntent().getSerializableExtra("goods");
        context = this;
        changeStatusBarTextColor(true);
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
        title.setText(R.string.evaluate);
        TextView ohter = findViewById(R.id.textView122);
        ohter.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.list);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        photoView = findViewById(R.id.photo_view);
        photoView.enable();
        photoView.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void getData(final boolean b) {
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }

        HashMap<String, String> mp = new HashMap<>();
        mp.put("label_id", label_id + "");
        if (goodsDetails != null) {
            mp.put("goods_id", goodsDetails.getGoods_id() + "");
        }
        HttpUtils.getInstance().post(ConstantManager.Url.COMMENT_LIST, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
                        }
                        if (json != null) {
                            bindViewData(json);
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
                        ToastUtils.showShortToast(context, context.getResources().getString(R
                                .string.net_error));
                    }
                });
            }
        });
    }

    public void bindViewData(JSONObject json) {
        try {
            JSONArray commentsArray = json.getJSONObject("data").getJSONArray("comments");
            for (int i = 0; i < commentsArray.length(); i++) {
                JSONObject object = (JSONObject) commentsArray.get(i);
                Comment comment = JsonUtils.fromJsonObject(object, Comment.class);
                comment.setData(object);
                comments.add(comment);
            }
            JSONArray labelArray = json.getJSONObject("data").getJSONArray("comment_labels");
            for (int i = 0; i < labelArray.length(); i++) {
                JSONObject object = (JSONObject) labelArray.get(i);
                Label label = JsonUtils.fromJsonObject(object, Label.class);
                if (label_id == label.getId()) {
                    label.setIs_select(true);
                }
                labels.add(label);
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

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();
        if (goodsDetails != null) {
            if (page == 1) {
                EvaGoodsItem evaGoodsItem = new EvaGoodsItem(goodsDetails, context);
                cells.add(evaGoodsItem);

                if (labels.size() > 0) {
                    EvaLabelItem evaLabelItem = new EvaLabelItem(labels, context);
                    evaLabelItem.setOnItemClickListener(evaLabelListener);
                    cells.add(evaLabelItem);
                }

                if (comments.size() > 0) {
                    for (int i = 0; i < comments.size(); i++) {
                        EvaContentItem evaContentItem = new EvaContentItem(comments.get(i),
                                context);
                        evaContentItem.setOnItemClickListener(evaContentListener);
                        cells.add(evaContentItem);
                    }
                } else {
                    EvaNoDataItem evaNoDataItem = new EvaNoDataItem(null, context);
                    cells.add(evaNoDataItem);
                }
            } else {

            }
        } else {
            EvaNoDataItem evaNoDataItem = new EvaNoDataItem(null, context);
            cells.add(evaNoDataItem);
        }
        return cells;
    }

    EvaLabelItem.OnClickListener evaLabelListener = new EvaLabelItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            for (int i = 0; i < labels.size(); i++) {
                labels.get(i).setIs_select(false);
            }
            labels.get(pos).setIs_select(true);
            mAdapter.notifyDataSetChanged();
        }
    };

    EvaContentItem.OnClickListener evaContentListener = new EvaContentItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, Info info) {
            photoView.animaFrom(info);
            photoView.setVisibility(View.VISIBLE);
        }
    };

}
