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
import com.vcvb.chenyu.shop.adapter.CYCGridAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatBannerItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatHeaderItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatNavItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatSubNavItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.FaatItemDecoration;
import com.vcvb.chenyu.shop.javaBean.faat.Banner;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;
import com.vcvb.chenyu.shop.javaBean.faat.Header;
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

    public RecyclerView navView;
    public CYCGridAdapter adapter = new CYCGridAdapter();
    public Faat faat;

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
        getData();
        navView = view.findViewById(R.id.nav_bak);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        navView.setLayoutManager(layoutManager);
        navView.setAdapter(adapter);
        CYCItemClickSupport.addTo(navView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                scrollToPos(position);
            }
        });

        mRecyclerView = view.findViewById(R.id.faat_list);
        mLayoutManager = new GridLayoutManager(context, 6);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(listener);

    }

    public void scrollToPos(int position) {
        for (int i = 0; i < faat.getFaatNavs().size(); i++) {
            faat.getFaatNavs().get(i).setIsSelect(false);
        }
        faat.getFaatNavs().get(position).setIsSelect(true);
        adapter.notifyDataSetChanged();

        int p = 0;
        for (int i = 0; i < faat.getGoodses().size(); i++) {
            if (faat.getGoodses().get(i) instanceof Header) {
                p = ((Header) faat.getGoodses().get(i)).getPos();
                if(position == p){
                    mRecyclerView.smoothScrollToPosition(i+2);
                }
            }
        }
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
        faat = new Faat();
        Banner banner = new Banner();
        banner.setBackGroundPic("http://scimg.jb51.net/allimg/161202/102-161202094551Z8.jpg");
        faat.setBanner(banner);

        ArrayList<Object> goodses = new ArrayList<>();
        ArrayList<FaatNav> faatNavs = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            FaatNav faatNav = new FaatNav();
            faatNav.setTitle("火爆一行" + i);
            if(i==0){
                faatNav.setIsSelect(true);
            }
            faatNavs.add(faatNav);

            Header header = new Header();
            header.setBackGroundPic("http://58pic.ooopic.com/58pic/19/50/38/56e00c6189f99.jpg");
            header.setPos(i);
            goodses.add(header);
            for (int j = 0; j < 20; j++) {
                Goods goods = new Goods();
                goods.setGoodsName("上传到我图网， 素材大小为7.73 MB上传到我图网， 素材大小为7.73 MB" + j);
                goods.setGoodsPriceFormat("$100.00");
                goods.setPic("http://dimage.yissimg" +
                        ".com/item/2014/0630/15/f1f4970f7eac4584becc4614aa187c3c.jpg");
                goodses.add(goods);
            }
        }
        faat.setFaatNavs(faatNavs);
        faat.setGoodses(goodses);
        mRecyclerView.removeItemDecoration(faatItemDecoration);
        faatItemDecoration = new FaatItemDecoration(faat, context);
        mRecyclerView.addItemDecoration(faatItemDecoration);
        mAdapter.addAll(getItems(faat));
        adapter.addAll(getNavItems(faat));
    }

    protected List<Item> getItems(Faat bean) {
        List<Item> cells = new ArrayList<>();
        if (bean.getFaatNavs() != null) {
            FaatNavItem faatNavItem = new FaatNavItem(bean, context);
            faatNavItem.setOnItemClickListener(navClickListener);
            cells.add(faatNavItem);
        }
        if (bean.getBanner() != null) {
            cells.add(new FaatBannerItem(bean, context));
        }
        if (bean.getGoodses() != null) {
            for (int i = 0; i < bean.getGoodses().size(); i++) {
                if (bean.getGoodses().get(i) instanceof Goods) {
                    FaatGoodsItem faatGoodsItem = new FaatGoodsItem((Goods) bean.getGoodses().get
                            (i), context);
                    cells.add(faatGoodsItem);
                } else {
                    FaatHeaderItem faatHeaderItem = new FaatHeaderItem((Header) bean.getGoodses()
                            .get(i), context);
                    cells.add(faatHeaderItem);
                }
            }
        }
        return cells;
    }

    protected List<Item> getNavItems(Faat bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < bean.getFaatNavs().size(); i++) {
            cells.add(new FaatSubNavItem(bean.getFaatNavs().get(i), context, faat.getFaatNavs()
                    .size()));
        }
        return cells;
    }


    FaatNavItem.OnClickListener navClickListener = new FaatNavItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            scrollToPos(pos);
        }
    };

    RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == 0) {
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int position = mLayoutManager.findFirstVisibleItemPosition();
            if(faat.getGoodses().get(position) instanceof Header){
                for (int i = 0; i < faat.getFaatNavs().size(); i++) {
                    faat.getFaatNavs().get(i).setIsSelect(false);
                }
                int npos= ((Header) faat.getGoodses().get(position)).getPos();
                faat.getFaatNavs().get(npos).setIsSelect(true);
                adapter.notifyDataSetChanged();
                navView.smoothScrollToPosition(npos);
            }
            if (position > 0) {
                //做显示布局操作
//                navView.setVisibility(View.VISIBLE);
            } else {
                //做隐藏布局操作
//                navView.setVisibility(View.GONE);
            }
        }
    };

}
