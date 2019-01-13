package com.vcvb.chenyu.shop.activity.brand.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.brand.BrandGoodsVItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.brand.BrandGoods;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class BrandSaleFragment extends BaseRecyclerViewFragment {

    private String id;
    private BrandGoods brandGoods;

    public CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();

    private String type_w = "normal";
    private String up_down = "down";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.brand_normal_fragment, container, false);
        if (getActivity() != null) {
            id = getActivity().getIntent().getStringExtra("id");
        }
        getData();
        initView();
        return view;
    }

    public void initView() {
        mRecyclerView = view.findViewById(R.id.rv_list);
        mLayoutManager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(context, 5));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("brand_id", id);
        mp.put("type", "is_sale");
        mp.put("orderby_column", type_w);
        mp.put("orderby_desc", up_down);
        HttpUtils.getInstance().post(ConstantManager.Url.BRAND, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (json != null) {
                                try {
                                    if (json.getInt("code") == 0) {
                                        bindData(json);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void bindData(JSONObject json) {
        mAdapter.clear();
        if (brandGoods != null && brandGoods.getGoodses() != null) {
            brandGoods.getGoodses().clear();
        }
        try {
            JSONObject object = json.getJSONObject("data");
            brandGoods = JsonUtils.fromJsonObject(object, BrandGoods.class);
            brandGoods.setData(object);
            mAdapter.addAll(getItems());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        if (brandGoods.getGoodses() != null && brandGoods.getGoodses().size() > 0) {
            for (int i = 0; i < brandGoods.getGoodses().size(); i++) {
                BrandGoodsVItem brandGoodsVItem = new BrandGoodsVItem(brandGoods.getGoodses().get
                        (i), context);
                items.add(brandGoodsVItem);
            }
        }
        return items;
    }
}
