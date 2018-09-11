package com.vcvb.chenyu.shop.mycenter;

import android.content.Context;
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
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.collection.CollectionErrorItem;
import com.vcvb.chenyu.shop.adapter.item.collection.CollectionItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.javaBean.collection.CollectionBean;
import com.vcvb.chenyu.shop.popwin.PopWin;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class MyCollectionActivity extends BaseActivity {
    Context context;
    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<CollectionBean> collections = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    private RefreshLayout refreshLayout;

    public LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarTextColor(true);
        setContentView(R.layout.collection_list);
        context = this;
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
                .LayoutParams.WRAP_CONTENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        TextView titleView = new TextView(this);
        titleView.setLayoutParams(layoutParams);
        titleView.setGravity(gravity);
        titleView.setText(R.string.collection);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ToolUtils.dip2px
                (context, 60), LinearLayout
                .LayoutParams.WRAP_CONTENT);
        LinearLayout nav_other = (LinearLayout) findViewById(R.id.nav_other);
        nav_other.setLayoutParams(layoutParams2);
        nav_other.setAlpha(1);
        ImageView iv1 = (ImageView) findViewById(R.id.collection);
        nav_other.removeView(iv1);

        final PopWin popWindow = new PopWin(MyCollectionActivity.this, ToolUtils.dip2px(context,
                156), ToolUtils.dip2px(context, 148));
        final ImageView iv2 = (ImageView) findViewById(R.id.more);
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
        popWindow.setClickListener1(new PopWin.OnItemClickListener1() {
            @Override
            public void onClicked(View v) {
                System.out.println(v);
            }
        });
        popWindow.setClickListener2(new PopWin.OnItemClickListener2() {
            @Override
            public void onClicked(View v) {
                System.out.println(v);
            }
        });

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        LinearLayout title_wrap = (LinearLayout) findViewById(R.id.title_wrap);
        title_wrap.setAlpha(1);
        title_wrap.setLayoutParams(layoutParams3);
        title_wrap.addView(titleView);
    }

    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initRefresh() {
        refreshLayout = (RefreshLayout) findViewById(R.id.collection_list);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
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

                        if (collections.size() != 0) {
//                            setHaveDataByView();
                        } else {
//                            setNoDateByView();
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
//                        setNoDateByView();
                    }
                });
            }
        });

        collections.clear();
//        CollectionBean bean = new CollectionBean();
//        bean.setIsType(-1);
//        bean.setGoodsName("store_name");
//        collections.add(bean);
        for (int i = 0; i < 10; i++) {
            CollectionBean bean = new CollectionBean();
            bean.setIsType(1);
            bean.setGoodsName("goods_name" + i);
            collections.add(bean);
        }
        mAdapter.addAll(getItems(collections));
    }

    protected List<Item> getItems(List<CollectionBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case -1:
                    cells.add(new CollectionErrorItem(list.get(i), context));
                    break;
                case 1:
                    cells.add(new CollectionItem(list.get(i), context));
                    break;
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
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int
                            position) {
                        clearLong();
                        System.out.println(2);
                        mAdapter.notifyDataSetChanged();
                    }
                });

        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.textView119).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {

                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int
                            position) {
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
