package com.vcvb.chenyu.shop.activity.evaluate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.photoview.PhotoViewPagerActivity;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaContentItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaLReplyItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaLabelItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaNoDataItem;
import com.vcvb.chenyu.shop.adapter.item.evaluate.EvaRReplyItem;
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
    private RefreshLayout refreshLayout;

    private List<Label> labels = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_refresh_activity);
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
        mRecyclerView = findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        refreshLayout = findViewById(R.id.list);
    }

    @Override
    public void initListener() {
        super.initListener();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getData(false);
                refreshLayout.finishRefresh(10000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadMoreData();
                refreshLayout.finishLoadMore(10000/*,false*/);//传入false表示加载失败
            }
        });

    }

    @Override
    public void getData(final boolean b) {
        page = 1;
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }

        HashMap<String, String> mp = new HashMap<>();
        mp.put("label_id", label_id + "");
        mp.put("page", page + "");
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
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
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
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        ToastUtils.showShortToast(context, context.getResources().getString(R
                                .string.net_error));
                    }
                });
            }
        });
    }

    public void bindViewData(JSONObject json) {
        mAdapter.clear();
        comments.clear();
        labels.clear();
        try {
            JSONArray commentsArray = json.getJSONObject("data").getJSONArray("comments");
            for (int i = 0; i < commentsArray.length(); i++) {
                JSONObject object = (JSONObject) commentsArray.get(i);
                Comment comment = JsonUtils.fromJsonObject(object, Comment.class);
                comment.setData(object);
                comments.add(comment);

                JSONArray replyArray = object.getJSONArray("reply");
                for (int j = 0; j < replyArray.length(); j++) {
                    JSONObject o = (JSONObject) replyArray.get(j);
                    Comment reply = JsonUtils.fromJsonObject(o, Comment.class);
                    reply.setData(o);
                    comments.add(reply);
                }
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
            mAdapter.addAll(getItems(comments));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void loadMoreData() {
        page += 1;
        HashMap<String, String> mp = new HashMap<>();
        mp.put("label_id", label_id + "");
        mp.put("page", page + "");
        if (goodsDetails != null) {
            mp.put("goods_id", goodsDetails.getGoods_id() + "");
        }
        HttpUtils.getInstance().post(ConstantManager.Url.COMMENT_LIST, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                        if (json != null) {
                            bindMoreViewData(json);
                        }
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                        ToastUtils.showShortToast(context, context.getResources().getString(R
                                .string.net_error));
                    }
                });
            }
        });
    }

    public void bindMoreViewData(JSONObject json) {
        try {
            JSONArray commentsArray = json.getJSONObject("data").getJSONArray("comments");
            int index = comments.size() + 2;
            List<Comment> _comments = new ArrayList<>();
            for (int i = 0; i < commentsArray.length(); i++) {
                JSONObject object = (JSONObject) commentsArray.get(i);
                Comment comment = JsonUtils.fromJsonObject(object, Comment.class);
                comment.setData(object);
                _comments.add(comment);
                comments.add(comment);

                JSONArray replyArray = object.getJSONArray("reply");
                for (int j = 0; j < replyArray.length(); j++) {
                    JSONObject o = (JSONObject) replyArray.get(j);
                    Comment reply = JsonUtils.fromJsonObject(o, Comment.class);
                    reply.setData(o);
                    _comments.add(reply);
                    comments.add(reply);
                }
            }
            mAdapter.addAll(index, getItems(_comments));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected List<Item> getItems(List<Comment> list) {
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
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getParent_id() == 0) {
                            EvaContentItem evaContentItem = new EvaContentItem(list.get(i),
                                    context);
                            evaContentItem.setOnItemClickListener(evaContentListener);
                            cells.add(evaContentItem);
                        } else {
                            if (list.get(i).getComment_type() == 2) {//left
                                EvaLReplyItem evaLReplyItem = new EvaLReplyItem(list.get(i),
                                        context);
                                evaLReplyItem.setOnItemClickListener(evaLReplyListener);
                                cells.add(evaLReplyItem);
                            } else if (list.get(i).getComment_type() == 3) {//right
                                EvaRReplyItem evaRReplyItem = new EvaRReplyItem(list.get(i),
                                        context);
                                evaRReplyItem.setOnItemClickListener(evaRReplyListener);
                                cells.add(evaRReplyItem);
                            }
                        }
                    }
                } else {
                    EvaNoDataItem evaNoDataItem = new EvaNoDataItem(null, context);
                    cells.add(evaNoDataItem);
                }
            } else {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getParent_id() == 0) {
                            EvaContentItem evaContentItem = new EvaContentItem(list.get(i),
                                    context);
                            evaContentItem.setOnItemClickListener(evaContentListener);
                            cells.add(evaContentItem);
                        } else {
                            if (list.get(i).getComment_type() == 2 || list.get(i).getComment_type() == 4) {//left
                                EvaLReplyItem evaLReplyItem = new EvaLReplyItem(list.get(i),
                                        context);
                                evaLReplyItem.setOnItemClickListener(evaLReplyListener);
                                cells.add(evaLReplyItem);
                            } else if (list.get(i).getComment_type() == 3) {//right
                                EvaRReplyItem evaRReplyItem = new EvaRReplyItem(list.get(i),
                                        context);
                                evaRReplyItem.setOnItemClickListener(evaRReplyListener);
                                cells.add(evaRReplyItem);
                            }
                        }
                    }
                }
            }
        } else {
            EvaNoDataItem evaNoDataItem = new EvaNoDataItem(null, context);
            cells.add(evaNoDataItem);
        }
        return cells;
    }

    public void photoView(int p, int pos){
        Comment comment = comments.get(p - 2);
        Intent intent = new Intent(EvaluateListActivity.this, PhotoViewPagerActivity.class);
        ArrayList<String> img_url = new ArrayList<>();
        img_url.add(comment.getCommentImgs().get(pos).getComment_img());
        for (int i = 0; i < comment.getCommentImgs().size(); i++) {
            if (pos != i) {
                img_url.add(comment.getCommentImgs().get(i).getComment_img());
            }
        }
        intent.putStringArrayListExtra("imgs", img_url);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
        public void onClicked(View view, int pos, int p) {
            photoView(p, pos);
        }
    };

    EvaLReplyItem.OnClickListener evaLReplyListener = new EvaLReplyItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int p) {
            photoView(p, pos);
        }
    };

    EvaRReplyItem.OnClickListener evaRReplyListener = new EvaRReplyItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int p) {
            photoView(p, pos);
        }
    };
}
