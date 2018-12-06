package com.vcvb.chenyu.shop.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.search.SearchErrorItem;
import com.vcvb.chenyu.shop.adapter.item.search.SearchGoodsHItem;
import com.vcvb.chenyu.shop.adapter.item.search.SearchGoodsVItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.dialog.SearchFilterDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.search.FilterBean;
import com.vcvb.chenyu.shop.javaBean.search.FilterList;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class SearchInfoActivity extends BaseRecyclerViewActivity {

    private List<Goods> goodses = new ArrayList<>();

    private TextView searchKey;
    private TextView comprehensive;
    private TextView salesVolume;
    private TextView price;
    private TextView newProduct;
    private TextView filter;
    private ImageView upDown;
    private ImageView volUpDown;

    private View search;
    private ImageView showType;

    private RefreshLayout refreshLayout;

    private String keywords;
    private int cateId;
    private String cateName;
    private HashMap<String, String> filterMp = new HashMap<>();
    private Integer page = 1;

    private List<FilterBean> filterList = new ArrayList<>();

    private SearchFilterDialog filterDialog;

    public RecyclerView filterRecyclerView;
    public CYCSimpleAdapter filterAdapter = new CYCSimpleAdapter();
    public GridLayoutManager filterLayoutManager;
    public DrawerLayout drawerLayout;
    private ConstraintLayout rightDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_info);
        changeStatusBarTextColor(true);
        context = this;
        setNavBack();
        initView();
        getKeyWords();
        getData(true);
        initRefresh();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SwipeBackHelper.finish(SearchInfoActivity.this);
                }
            });
        }
        search = findViewById(R.id.view43);

        showType = findViewById(R.id.imageView74);
        showType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.clear();
                HashMap<String, String> mp = new HashMap<>();
                if (is_show.equals("two_c")) {
                    mp.put("is_show", "one_c");
                    UserInfoUtils.getInstance(context).setUserInfo(mp);
                    is_show = "one_c";
                    mLayoutManager = new GridLayoutManager(context, 1);
                    Glide.with(context).load(R.drawable.icon_collection_list).into(showType);
                } else {
                    mp.put("is_show", "two_c");
                    UserInfoUtils.getInstance(context).setUserInfo(mp);
                    is_show = "two_c";
                    mLayoutManager = new GridLayoutManager(context, 2);
                    Glide.with(context).load(R.drawable.icon_list).into(showType);
                }
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter.addAll(getItems(goodses));
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        searchKey = findViewById(R.id.textView152);
        comprehensive = findViewById(R.id.textView155);
        salesVolume = findViewById(R.id.textView156);
        price = findViewById(R.id.textView154);
        newProduct = findViewById(R.id.textView158);
        filter = findViewById(R.id.textView157);
        upDown = findViewById(R.id.imageView77);
        volUpDown = findViewById(R.id.imageView131);

        comprehensive.setOnClickListener(searchType);
        salesVolume.setOnClickListener(searchType);
        price.setOnClickListener(searchType);
        newProduct.setOnClickListener(searchType);
        filter.setOnClickListener(searchType);

        mRecyclerView = findViewById(R.id.search_list);
        if (is_show.equals("two_c")) {
            mLayoutManager = new GridLayoutManager(context, 2);
        } else {
            mLayoutManager = new GridLayoutManager(context, 1);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(context, 5));
        mRecyclerView.setAdapter(mAdapter);

        filterRecyclerView = findViewById(R.id.filter_list);
        filterLayoutManager = new GridLayoutManager(context, 1);
        filterRecyclerView.setLayoutManager(filterLayoutManager);
        filterRecyclerView.setAdapter(filterAdapter);

        drawerLayout = findViewById(R.id.drawer_layout);
        rightDrawerLayout = findViewById(R.id.filter_wrap);
        initListener();
    }

    @Override
    public void initListener() {
        super.initListener();
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                Intent intent = new Intent(SearchInfoActivity.this, GoodsDetailActivity.class);
                intent.putExtra("id", goodses.get(position).getGoods_id());
                startActivity(intent);
            }
        });
        searchKey.setOnClickListener(searchType);
    }

    public void initRefresh() {
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadmore();
                refreshLayout.finishLoadMore(10000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void getKeyWords() {
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        cateName = intent.getStringExtra("cate_name");
        cateId = intent.getIntExtra("cate", 0);
        filterMp.put("keywords", keywords);
        filterMp.put("cate_name", cateName);
        filterMp.put("cate_id", cateId + "");
        filterMp.put("type", "1");
        if (cateId == 0 && keywords != null && !keywords.equals("")) {
            searchKey.setText(keywords);
        } else {
            searchKey.setText(R.string.search);
        }
    }

    @Override
    public void getData(final boolean b) {
        page = 1;
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        filterMp.put("page", page + "");
        HttpUtils.getInstance().post(ConstantManager.Url.SEARCH, filterMp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (json != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (json.getInt("code") == 0) {
                                    if (b) {
                                        loadingDialog.dismiss();
                                    }
                                    bindViewData(json);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
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
        goodses.clear();
        mAdapter.clear();
        try {
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Goods bean = JsonUtils.fromJsonObject(object, Goods.class);
                goodses.add(bean);
            }
            mAdapter.addAll(getItems(goodses));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            FilterBean bean = new FilterBean();
            bean.setIsType(2);
            bean.setTitle("每行" + i);
            List<FilterList> list = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                FilterList bean2 = new FilterList();
                bean2.setTitle("同学的实" + j);
                list.add(bean2);
            }
            bean.setList(list);
            filterList.add(bean);
        }
        filterDialog = new SearchFilterDialog();
        filterDialog.setDate(filterList);
    }

    public void loadmore() {
        page += 1;
        filterMp.put("page", page + "");
        HttpUtils.getInstance().post(ConstantManager.Url.SEARCH, filterMp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (json != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.finishLoadMore();
                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (json.getInt("code") == 0) {
                                    bindViewLoadMoreData(json);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                });
            }
        });
    }

    public void bindViewLoadMoreData(JSONObject json) {
        int index = goodses.size();
        try {
            List<Goods> _goodses = new ArrayList<>();
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Goods bean = JsonUtils.fromJsonObject(object, Goods.class);
                _goodses.add(bean);
            }
            mAdapter.addAll(index, getItems(_goodses));
            mAdapter.notifyItemRangeChanged(index, _goodses.size());
            goodses.addAll(_goodses);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected List<Item> getItems(List<Goods> list) {
        List<Item> cells = new ArrayList<>();
        if (goodses.size() > 0) {
            if(page == 1){
                if (is_show.equals("two_c")) {
                    mLayoutManager = new GridLayoutManager(context, 2);
                } else {
                    mLayoutManager = new GridLayoutManager(context, 1);
                }
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
            for (int i = 0; i < list.size(); i++) {
                if (is_show.equals("two_c")) {
                    cells.add(new SearchGoodsVItem(list.get(i), context));
                } else {
                    cells.add(new SearchGoodsHItem(list.get(i), context));
                }
            }
        }else{
            if(page == 1){
                mLayoutManager = new GridLayoutManager(context, 1);
                cells.add(new SearchErrorItem(null, context));
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
        }

        return cells;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.IsFrom.FROM_SEARCHINFO:
                    keywords = data.getStringExtra("keywords");
                    cateId = data.getIntExtra("cate", 0);
                    cateName = data.getStringExtra("cate_name");
                    filterMp.put("keywords", keywords);
                    filterMp.put("cate_name", cateName);
                    filterMp.put("cate_id", cateId + "");
                    filterMp.put("type", "1");
                    if (cateId == 0 && keywords != null && keywords != "") {
                        searchKey.setText(keywords);
                    } else {
                        searchKey.setText(R.string.search);
                    }
                    getData(false);
                    break;
            }
        }
    }

    //右边菜单开关事件
    public void openRightLayout() {
        if (drawerLayout.isDrawerOpen(rightDrawerLayout)) {
            drawerLayout.closeDrawer(rightDrawerLayout);
        } else {
            drawerLayout.openDrawer(rightDrawerLayout);
        }
    }

    View.OnClickListener searchType = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() != R.id.textView157) {
                comprehensive.setTextColor(context.getResources().getColor(R.color.black));
                salesVolume.setTextColor(context.getResources().getColor(R.color.black));
                price.setTextColor(context.getResources().getColor(R.color.black));
                newProduct.setTextColor(context.getResources().getColor(R.color.black));
            }
            switch (view.getId()) {
                case R.id.textView152:
                    Intent intent = new Intent(SearchInfoActivity.this, SearchActivity.class);
                    intent.putExtra("isfrom", ConstantManager.IsFrom.FROM_SEARCHINFO);
                    startActivityForResult(intent, ConstantManager.IsFrom.FROM_SEARCHINFO);
                    overridePendingTransition(0, 0);
                    break;
                case R.id.textView155:
                    filterMp.put("type", "1");
                    filterMp.put("price_order", "0");
                    filterMp.put("volume", "0");
                    comprehensive.setTextColor(context.getResources().getColor(R.color.red));
                    break;
                case R.id.textView156:
                    filterMp.put("type", "2");
                    filterMp.put("price_order", "0");
                    if (filterMp.get("volume") == null || filterMp.get("volume").equals("0")) {
                        filterMp.put("volume", "1");
                    } else if (filterMp.get("volume").equals("1")) {
                        filterMp.put("volume", "2");
                    } else if (filterMp.get("volume").equals("2")) {
                        filterMp.put("volume", "1");
                    }
                    salesVolume.setTextColor(context.getResources().getColor(R.color.red));
                    break;
                case R.id.textView154:
                    filterMp.put("type", "3");
                    filterMp.put("volume", "0");
                    price.setTextColor(context.getResources().getColor(R.color.red));
                    if (filterMp.get("price_order") == null || filterMp.get("price_order").equals
                            ("0")) {
                        filterMp.put("price_order", "1");
                    } else if (filterMp.get("price_order").equals("1")) {
                        filterMp.put("price_order", "2");
                    } else if (filterMp.get("price_order").equals("2")) {
                        filterMp.put("price_order", "1");
                    }
                    break;
                case R.id.textView158:
                    filterMp.put("type", "4");
                    filterMp.put("price_order", "0");
                    filterMp.put("volume", "0");
                    newProduct.setTextColor(context.getResources().getColor(R.color.red));
                    break;
                case R.id.textView157:
                    openRightLayout();
                    break;
            }
            if (filterMp.get("volume") == null || filterMp.get("volume").equals("0")) {
                Glide.with(context).load(R.drawable.icon_up_down_gray).into(volUpDown);
            } else if (filterMp.get("volume").equals("1")) {
                Glide.with(context).load(R.drawable.icon_up).into(volUpDown);
            } else if (filterMp.get("volume").equals("2")) {
                Glide.with(context).load(R.drawable.icon_down).into(volUpDown);
            }

            if (filterMp.get("price_order") == null || filterMp.get("price_order").equals("0")) {
                Glide.with(context).load(R.drawable.icon_up_down_gray).into(upDown);
            } else if (filterMp.get("price_order").equals("1")) {
                Glide.with(context).load(R.drawable.icon_up).into(upDown);
            } else if (filterMp.get("price_order").equals("2")) {
                Glide.with(context).load(R.drawable.icon_down).into(upDown);
            }
            if (view.getId() != R.id.textView157 && view.getId() != R.id.textView152) {
                getData(false);
            }
        }
    };
}
