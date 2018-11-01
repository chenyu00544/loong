package com.vcvb.chenyu.shop.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.search.SearchGoodsHItem;
import com.vcvb.chenyu.shop.adapter.item.search.SearchGoodsVItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.dialog.SearchFilterDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.search.FilterBean;
import com.vcvb.chenyu.shop.javaBean.search.FilterList;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;

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
    private int isUpDown = 0;

    private View search;
    private ImageView showType;
    private boolean isShow = true;

    private int type = 0;
    private String keywords;
    private int cateId;
    private int categroyId;

    private List<FilterBean> filterList = new ArrayList<>();

    private SearchFilterDialog filterDialog;

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
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = (ImageView) findViewById(R.id.imageView23);
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
                if (isShow) {
                    isShow = false;
                    Glide.with(context).load(R.drawable.icon_collection_list).into(showType);
                } else {
                    isShow = true;
                    Glide.with(context).load(R.drawable.icon_list).into(showType);
                }
                setData();
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        searchKey = (TextView) findViewById(R.id.textView152);
        comprehensive = (TextView) findViewById(R.id.textView155);
        salesVolume = (TextView) findViewById(R.id.textView156);
        price = (TextView) findViewById(R.id.textView154);
        newProduct = (TextView) findViewById(R.id.textView158);
        filter = (TextView) findViewById(R.id.textView157);
        upDown = (ImageView) findViewById(R.id.imageView77);

        comprehensive.setOnClickListener(searchType);
        salesVolume.setOnClickListener(searchType);
        price.setOnClickListener(searchType);
        newProduct.setOnClickListener(searchType);
        filter.setOnClickListener(searchType);

        mRecyclerView = findViewById(R.id.search_list);
        mLayoutManager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(context, 5));
        mAdapter = new CYCSimpleAdapter();
        mRecyclerView.setAdapter(mAdapter);
        initListener();
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

                        if (goodses.size() != 0) {
                        } else {
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

        filterDialog = new SearchFilterDialog();
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
        filterDialog.setDate(filterList);

        goodses.clear();
        for (int i = 0; i < 10; i++) {
            Goods bean = new Goods();
            bean.setGoods_name("央视网" + i);
            goodses.add(bean);
        }
        setData();
    }

    public void setData() {
        mAdapter.clear();
        if (isShow) {
            mLayoutManager = new GridLayoutManager(context, 2);
        } else {
            mLayoutManager = new GridLayoutManager(context, 1);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter.addAll(getItems(goodses));
    }

    protected List<Item> getItems(List<Goods> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (isShow) {
                cells.add(new SearchGoodsVItem(list.get(i), context));
            } else {
                cells.add(new SearchGoodsHItem(list.get(i), context));
            }
        }
        return cells;
    }

    @Override
    public void initListener() {
        super.initListener();
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                Intent intent = new Intent(SearchInfoActivity.this, GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
        searchKey.setOnClickListener(searchType);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.IsFrom.FROM_SEARCHINFO:
                    keywords = data.getStringExtra("keywords");
                    cateId = data.getIntExtra("cate", 0);
                    if (cateId == 0 && keywords != null && keywords != "") {
                        searchKey.setText(keywords);
                    } else {
                        searchKey.setText(R.string.search);
                    }
                    break;
            }
        }
    }

    public void getSearchData() {

    }

    public void showFilterDialog() {
        filterDialog.show(getSupportFragmentManager(), "Dialog");
    }

    public void getKeyWords() {
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        cateId = intent.getIntExtra("cate", 0);
        categroyId = intent.getIntExtra("categroy", 0);
        if (cateId == 0 && keywords != null && keywords != "") {
            searchKey.setText(keywords);
        } else {
            searchKey.setText(R.string.search);
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
                    type = 1;
                    isUpDown = 0;
                    comprehensive.setTextColor(context.getResources().getColor(R.color.red));
                    break;
                case R.id.textView156:
                    type = 2;
                    isUpDown = 0;
                    salesVolume.setTextColor(context.getResources().getColor(R.color.red));
                    break;
                case R.id.textView154:
                    type = 3;
                    price.setTextColor(context.getResources().getColor(R.color.red));
                    if (isUpDown == 0) {
                        isUpDown = 1;
                    } else if (isUpDown == 1) {
                        isUpDown = 2;
                    } else if (isUpDown == 2) {
                        isUpDown = 1;
                    }
                    break;
                case R.id.textView158:
                    type = 4;
                    isUpDown = 0;
                    newProduct.setTextColor(context.getResources().getColor(R.color.red));
                    break;
                case R.id.textView157:
                    type = 5;
                    isUpDown = 0;
                    showFilterDialog();
                    break;
            }
            if (isUpDown == 0) {
                Glide.with(context).load(R.drawable.icon_up_down_gray).into(upDown);
            } else if (isUpDown == 1) {
                Glide.with(context).load(R.drawable.icon_up).into(upDown);
            } else if (isUpDown == 2) {
                Glide.with(context).load(R.drawable.icon_down).into(upDown);
            }
            if (view.getId() != R.id.textView157) {
                getSearchData();
            }
        }
    };
}
