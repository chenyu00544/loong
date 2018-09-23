package com.vcvb.chenyu.shop.faat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vcvb.chenyu.shop.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatBannerItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatHeaderItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatNavItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatSubNavItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.FaatItemDecoration;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class CosmeticsFragment extends BaseRecyclerViewFragment {

    FaatItemDecoration faatItemDecoration;

    RecyclerView navView;
    public CYCSimpleAdapter adapter = new CYCSimpleAdapter();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.faat_fragment, container, false);
        initView();
        return view;
    }

    public void initView() {
        navView = view.findViewById(R.id.nav_bak);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        navView.setLayoutManager(layoutManager);
        navView.setAdapter(adapter);

        mRecyclerView = view.findViewById(R.id.faat_list);
        mLayoutManager = new GridLayoutManager(context, 6);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        getData();
    }

    @Override
    public void getData() {
        super.getData();
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", "");
        mp.put("nav_id", "0");
        HttpUtils.getInstance().post(Routes.getInstance().getIndex(), mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if (json != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
                            bindData();
                        }
                    });
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                        Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void bindData() {
        final ArrayList<Faat> faats = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Faat faat = new Faat();
            faat.setTitle("asdfa" + i);
            if (i == 0) {
                faat.setIsType(2);
                faat.setBackGroundPic("http://scimg.jb51.net/allimg/161202/102-161202094551Z8.jpg");
            } else if (i == 1) {
                faat.setIsType(4);
                ArrayList<FaatNav> faatNavs = new ArrayList<>();
                for (int j = 0; j < 15; j++) {
                    FaatNav faatNav = new FaatNav();
                    faatNav.setTitle("sdfa" + j);
                    faatNavs.add(faatNav);
                }
                faat.setFaatNavs(faatNavs);
                adapter.addAll(getNavItems(faatNavs));
            } else if (i == 34) {
                faat.setIsType(3);
                faat.setBackGroundPic("http://58pic.ooopic.com/58pic/19/50/38/56e00c6189f99.jpg");
            } else {
                faat.setIsType(1);
                Goods goods = new Goods();
                goods.setGoodsName("上传到我图网， 素材大小为7.73 MB上传到我图网， 素材大小为7.73 MB" + i);
                goods.setGoodsPriceFormat("$100.00");
                goods.setPic("http://dimage.yissimg" +
                        ".com/item/2014/0630/15/f1f4970f7eac4584becc4614aa187c3c.jpg");
                faat.setGoods(goods);
            }
            faats.add(faat);
        }
        faatItemDecoration = new FaatItemDecoration(faats, context);
        mRecyclerView.addItemDecoration(faatItemDecoration);
        mRecyclerView.addOnScrollListener(listener);
        mAdapter.addAll(getItems(faats));
    }

    protected List<Item> getItems(List<Faat> bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {
            switch (bean.get(i).getIsType()) {
                case 1:
                    cells.add(new FaatGoodsItem(bean.get(i), context));
                    break;
                case 2:
                    cells.add(new FaatBannerItem(bean.get(i), context));
                    break;
                case 3:
                    cells.add(new FaatHeaderItem(bean.get(i), context));
                    break;
                case 4:
                    cells.add(new FaatNavItem(bean.get(i), context));
                    break;

            }
        }
        return cells;
    }

    protected List<Item> getNavItems(List<FaatNav> bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {
            cells.add(new FaatSubNavItem(bean.get(i), context));
        }
        return cells;
    }

    RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener(){
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int position = mLayoutManager.findFirstVisibleItemPosition();
            if (position > 0) {
                //做显示布局操作
                navView.setVisibility(View.VISIBLE);
            } else {
                //做隐藏布局操作
                navView.setVisibility(View.GONE);

            }
        }
    };

}
