package com.vcvb.chenyu.shop.activity.faat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatNoDataItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatTeamGoodsItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.TeamGoods;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class FaatTeamFragment extends BaseRecyclerViewFragment {

    public List<TeamGoods> goodses = new ArrayList<>();
    private Integer currentTime;

    public int position = 0;
    public int nav_id = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.recycler_list, container, false);
        getData();
        initView();
        return view;
    }

    public void initView() {
        mRecyclerView = view.findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 6);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(context, 5));
        mRecyclerView.setAdapter(mAdapter);
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("id", goodses.get(position).getGoods_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getData() {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("id", nav_id + "");
        HttpUtils.getInstance().post(ConstantManager.Url.FAAT_TEAM, mp, new HttpUtils.NetCall
                () {
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
        try {
            currentTime = json.getInt("time");
            Integer code = json.getInt("code");
            if (code == 0) {
                goodses.clear();
                mAdapter.clear();
                JSONArray array = json.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = (JSONObject) array.get(i);
                    JSONArray goods = object.getJSONArray("goods");
                    for (int j = 0; j < goods.length(); j++) {
                        JSONObject obj = (JSONObject) goods.get(i);
                        TeamGoods teamGoods = JsonUtils.fromJsonObject(obj, TeamGoods.class);
                        goodses.add(teamGoods);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        mAdapter.setData(getItems());
    }

    protected List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        if (goodses != null && goodses.size() > 0) {
            for (int i = 0; i < goodses.size(); i++) {
                FaatTeamGoodsItem faatTeamGoodsItem = new FaatTeamGoodsItem(goodses.get(i), context);
                items.add(faatTeamGoodsItem);
            }
        } else {
            mLayoutManager = new GridLayoutManager(context, 1);
            items.add(new FaatNoDataItem(null, context));
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        return items;
    }
}
