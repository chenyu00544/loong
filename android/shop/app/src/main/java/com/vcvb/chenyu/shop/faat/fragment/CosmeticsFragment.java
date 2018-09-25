package com.vcvb.chenyu.shop.faat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vcvb.chenyu.shop.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.GroupedListAdapter;
import com.vcvb.chenyu.shop.adapter.b.Item;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatBannerItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatHeaderItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatNavItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.FaatItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
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

    public CYCSimpleAdapter adapter = new CYCSimpleAdapter();
    ArrayList<Faat> faats;
//    int pos_dx = 0;

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
//        navView = view.findViewById(R.id.nav_bak);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        navView.setLayoutManager(layoutManager);
//        navView.setAdapter(adapter);
////        navView.setVisibility(View.GONE);
//        navView.addOnScrollListener(listener2);

        mRecyclerView = view.findViewById(R.id.faat_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//        mLayoutManager = new GridLayoutManager(context, 6);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addOnScrollListener(listener);
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
        faats = new ArrayList<>();
        boolean bool = true;
        for (int i = 0; i < 2; i++) {
            Faat faat = new Faat();
            if (i == 0) {
                faat.setIsType(ConstantManager.Item.BANNER);
                ArrayList<Object> banners = new ArrayList<>();
                Banner banner = new Banner();
                banner.setBackGroundPic("http://scimg.jb51.net/allimg/161202/102-161202094551Z8.jpg");
                banners.add(banner);
                faat.setObjs(banners);
            } else {
                faat.setIsType(ConstantManager.Item.ITEMS);
                if (bool) {
                    bool = false;
                    ArrayList<Object> faatNavs = new ArrayList<>();
                    for (int j = 0; j < 15; j++) {
                        FaatNav faatNav = new FaatNav();
                        faatNav.setTitle("sdfa" + j);
                        faatNavs.add(faatNav);
                    }
                    faat.setHeader(faatNavs);
                }
                ArrayList<Object> goodses = new ArrayList<>();
                for (int j = 0; j < 20; j++) {
                    Goods goods = new Goods();
                    goods.setGoodsName("上传到我图网， 素材大小为7.73 MB上传到我图网， 素材大小为7.73 MB" + i);
                    goods.setGoodsPriceFormat("$100.00");
                    goods.setPic("http://dimage.yissimg.com/item/2014/0630/15/f1f4970f7eac4584becc4614aa187c3c.jpg");
                    goodses.add(goods);
                }
                Header header = new Header();
                header.setBackGroundPic("http://58pic.ooopic.com/58pic/19/50/38/56e00c6189f99.jpg");
                goodses.add(header);
                for (int j = 0; j < 20; j++) {
                    Goods goods = new Goods();
                    goods.setGoodsName("上传到我图网， 素材大小为7.73 MB上传到我图网， 素材大小为7.73 MB" + i);
                    goods.setGoodsPriceFormat("$100.00");
                    goods.setPic("http://dimage.yissimg" + "" + "" + "" + "" + "" + "" +
                            ".com/item/2014/0630/15/f1f4970f7eac4584becc4614aa187c3c.jpg");
                    goodses.add(goods);
                }
                faat.setObjs(goodses);
            }
            faats.add(faat);
        }
        GroupedListAdapter adapter = new GroupedListAdapter(context, getItems(faats));
        mRecyclerView.setAdapter(adapter);
//        mAdapter.addAll(getItems(faats));
    }

    protected List<Faat> getItems(List<Faat> bean) {
        for (int i = 0; i < bean.size(); i++) {
            switch (bean.get(i).getIsType()) {
                case ConstantManager.Item.ITEMS:
                    List<Item> cs = new ArrayList<>();
                    for (int j = 0; j < bean.get(i).getObjs().size(); j++) {
                        if(bean.get(i).getObjs().get(j).getClass() == Goods.class){
                            cs.add(new FaatGoodsItem(bean.get(i), context));
                        }else{
                            cs.add(new FaatHeaderItem(bean.get(i), context));
                        }
                    }
                    bean.get(i).setmObjs(cs);
                    if (bean.get(i).getHeader() != null) {
                        FaatNavItem faatNavItem = new FaatNavItem(bean.get(i), context);
                        faatNavItem.setItemScrollListener(scrollListener);
                        bean.get(i).setmHeader(faatNavItem);
                    }
                    break;
                case ConstantManager.Item.BANNER:
                    List<Item> cs1 = new ArrayList<>();
                    cs1.add(new FaatBannerItem(bean.get(i), context));
                    bean.get(i).setmObjs(cs1);
                    break;
            }
        }
        return bean;
    }

//    protected List<Item> getNavItems(List<FaatNav> bean) {
//        List<Item> cells = new ArrayList<>();
//        for (int i = 0; i < bean.size(); i++) {
//            cells.add(new FaatSubNavItem(bean.get(i), context));
//        }
//        return cells;
//    }

    FaatNavItem.ScrollListener scrollListener = new FaatNavItem.ScrollListener() {
        @Override
        public void Scrolled(RecyclerView recyclerView, int dx, int dy) {
        }
    };

    RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
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
//                navView.setVisibility(View.VISIBLE);
            } else {
                //做隐藏布局操作
//                navView.setVisibility(View.GONE);
            }
        }
    };

}
