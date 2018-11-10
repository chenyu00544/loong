package com.vcvb.chenyu.shop.mycenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.collection.CollectionErrorItem;
import com.vcvb.chenyu.shop.adapter.item.collection.CollectionItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.javaBean.collection.CollectionBean;
import com.vcvb.chenyu.shop.popwin.PopWin;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.Routes;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class MyCollectionActivity extends BaseRecyclerViewActivity {
    private List<CollectionBean> collections = new ArrayList<>();

    private RefreshLayout refreshLayout;
    private Integer page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarTextColor(true);
        setContentView(R.layout.collection_list);
        context = this;
        token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
        setNavBack();
        initView();
        initRefresh();
        getData(true);
        initListener();
    }

    @Override
    public void setNavBack() {
        super.setNavBack();
        int gravity = Gravity.CENTER;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView titleView = new TextView(this);
        titleView.setLayoutParams(layoutParams);
        titleView.setGravity(gravity);
        titleView.setText(R.string.collection);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ToolUtils.dip2px
                (context, 60), LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout nav_other = (LinearLayout) findViewById(R.id.nav_other);
        nav_other.setLayoutParams(layoutParams2);
        nav_other.setAlpha(1);
        ImageView iv1 = (ImageView) findViewById(R.id.collection);
        nav_other.removeView(iv1);

        final PopWin popWindow = new PopWin(MyCollectionActivity.this, ToolUtils.dip2px(context,
                156), ToolUtils.dip2px(context, 148));
        final ImageView iv2 = findViewById(R.id.more);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.showAsDropDown(iv2);
            }
        });
        popWindow.setClickListener(new PopWin.OnItemClickListener() {
            @Override
            public void onClicked(View v) {
                System.out.println(v);
            }
        });

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout title_wrap = findViewById(R.id.title_wrap);
        title_wrap.setAlpha(1);
        title_wrap.setLayoutParams(layoutParams3);
        title_wrap.addView(titleView);
    }

    public void initView() {
        mRecyclerView = findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initRefresh() {
        refreshLayout = findViewById(R.id.collection_list);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadmore();
                refreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void getData(final boolean b) {
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("page", page + "");
        HttpUtils.getInstance().post(ConstantManager.Url.COLLECTGOODSES, mp, new HttpUtils.NetCall() {
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

    public void loadmore() {
        HashMap<String, String> mp = new HashMap<>();
        page += 1;
        mp.put("token", token);
        mp.put("page", page + "");
        HttpUtils.getInstance().post(Routes.getInstance().getIndex(), mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bindViewMoreData(json);
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    public void bindViewData(JSONObject json) {
        collections.clear();
        mAdapter.clear();
        if (json != null) {
            try {
                Integer code = json.getInt("code");
                if (code == 0) {
                    JSONArray arr = json.getJSONArray("data");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject object = (JSONObject) arr.get(i);
                        CollectionBean bean = JsonUtils.fromJsonObject(object, CollectionBean
                                .class);
                        collections.add(bean);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        mAdapter.addAll(getItems(collections));
    }

    public void bindViewMoreData(JSONObject json) {
        if (json != null) {

        }
    }

    protected List<Item> getItems(List<CollectionBean> list)
    {
        List<Item> cells = new ArrayList<>();
        if (list == null || list.size() == 0) {
            cells.add(new CollectionErrorItem(null, context));
        }else{
            for (int i = 0; i < list.size(); i++) {
                cells.add(new CollectionItem(list.get(i), context));
            }
        }
        return cells;
    }
    public void initListener() {
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(position);
                clearLong();
                Intent intent = new Intent(MyCollectionActivity.this, GoodsDetailActivity.class);
                startActivity(intent);
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new CYCItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, View itemView, int
                    position) {
                clearLong();
                collections.get(position).setIsLong(true);
                System.out.println(2);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });

        CYCItemClickSupport.BuildTo(mRecyclerView, R.id.textView113).setOnChildClickListener(new CYCItemClickSupport.OnChildItemClickListener() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(1);
                clearLong();
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo1(mRecyclerView, R.id.imageView48).setOnChildClickListener1
                (new CYCItemClickSupport.OnChildItemClickListener1() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                System.out.println(2);
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.textView119).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                System.out.println(3);
                collections.remove(position);
                mAdapter.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    //清理长按显示状态
    public void clearLong() {
        for (int i = 0; i < collections.size(); i++) {
            if (collections.get(i).getIsLong() == true) {
                collections.get(i).setIsLong(false);
            }
        }
    }
}
