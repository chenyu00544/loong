package com.vcvb.chenyu.shop.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.dialog.SearchFilterDialog;
import com.vcvb.chenyu.shop.javaBean.home.Goods;

import java.util.ArrayList;
import java.util.List;

public class SearchInfoActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<Goods> goodses = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

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

    private SearchFilterDialog filterDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_info);
        changeStatusBarTextColor(true);
        context = this;
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        getData(true);
        setNavBack();
        initView();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = (ImageView) findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        search = findViewById(R.id.view43);

        showType = (ImageView) findViewById(R.id.imageView74);
        showType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow) {
                    isShow = false;
                    Glide.with(context).load(R.drawable.icon_list).into(showType);
                } else {
                    isShow = true;
                    Glide.with(context).load(R.drawable.icon_collection_list).into(showType);
                }
            }
        });
    }

    View.OnClickListener searchType = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            comprehensive.setTextColor(context.getResources().getColor(R.color.black));
            salesVolume.setTextColor(context.getResources().getColor(R.color.black));
            price.setTextColor(context.getResources().getColor(R.color.black));
            newProduct.setTextColor(context.getResources().getColor(R.color.black));
            filter.setTextColor(context.getResources().getColor(R.color.black));
            switch (view.getId()) {
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
                    filter.setTextColor(context.getResources().getColor(R.color.red));
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

    @Override
    public void initView() {
        super.initView();
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

        filterDialog = new SearchFilterDialog();

        initListener();
    }

    @Override
    public void getData(boolean b) {
        super.getData(b);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    public void getSearchData() {

    }

    public void showFilterDialog() {
        filterDialog.show(getSupportFragmentManager(), "Dialog");
    }
}
