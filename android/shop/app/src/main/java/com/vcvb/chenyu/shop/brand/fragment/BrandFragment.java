package com.vcvb.chenyu.shop.brand.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.vcvb.chenyu.shop.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.GroupedListAdapter;
import com.vcvb.chenyu.shop.adapter.b.Item;
import com.vcvb.chenyu.shop.adapter.item.brand.BrandBannerItem;
import com.vcvb.chenyu.shop.adapter.item.brand.BrandGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.brand.BrandNavItem;
import com.vcvb.chenyu.shop.javaBean.brand.Brand;
import com.vcvb.chenyu.shop.javaBean.faat.Banner;
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

public class BrandFragment extends BaseRecyclerViewFragment {

    public GroupedListAdapter adapter;
    private GroupedGridLayoutManager groupedGridLayoutManager;
    public List<Brand> brands = new ArrayList<>();
    public int position = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.brand_fragment, container, false);
        initView();
        return view;
    }

    public void initView() {
        getData();
        mRecyclerView = view.findViewById(R.id.rv_list);
        mRecyclerView.addOnScrollListener(rvScrollListener);
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
        Brand brand = new Brand();
        Banner banner = new Banner();
        banner.setWidth(750);
        banner.setHeight(440);
        banner.setBackGroundPic("http://scimg.jb51.net/allimg/161202/102-161202094551Z8.jpg");
        List<Object> bs = new ArrayList<>();
        bs.add(banner);
        brand.setObjs(bs);
        brand.setGroup(0);
        brands.add(brand);

        Brand brand1 = new Brand();
        ArrayList<Object> goodses = new ArrayList<>();
        ArrayList<FaatNav> faatNavs = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            FaatNav faatNav = new FaatNav();
            if (i == 0) {
                faatNav.setIsSelect(true);
            }
            faatNav.setTitle("火爆一行" + i);
            faatNavs.add(faatNav);

            Banner subbanner = new Banner();
            subbanner.setBackGroundPic("http://58pic.ooopic.com/58pic/19/50/38/56e00c6189f99.jpg");
            subbanner.setNavPos(i);
            subbanner.setWidth(750);
            subbanner.setHeight(185);
            goodses.add(subbanner);
            for (int j = 0; j < 20; j++) {
                Goods goods = new Goods();
                goods.setGoodsName("上传到我图网， 素材大小为7.73 MB上传到我图网， 素材大小为7.73 MB" + j);
                goods.setGoodsPriceFormat("$1000.00");
                goods.setPic("http://dimage.yissimg" +
                        ".com/item/2014/0630/15/f1f4970f7eac4584becc4614aa187c3c.jpg");
                goodses.add(goods);
            }
        }
        brand1.setHeader(faatNavs);
        brand1.setObjs(goodses);
        brand1.setGroup(1);
        brands.add(brand1);

        adapter = new GroupedListAdapter(context, getItems(brands));
        groupedGridLayoutManager = new GroupedGridLayoutManager(context, 3, adapter) {
            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                if (brands.get(groupPosition).getItemList().get(childPosition).getItemType() == R
                        .layout.faat_banner_item) {
                    return 3;
                }
                return super.getChildSpanSize(groupPosition, childPosition);
            }
        };
        mRecyclerView.setLayoutManager(groupedGridLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    protected List<Brand> getItems(List<Brand> beans) {

        for (int i = 0; i < beans.size(); i++) {
            if (brands.get(i).getHeader() != null) {
                BrandNavItem brandNavItem = new BrandNavItem(beans.get(i), context);
                brandNavItem.setOnItemClickListener(navListener);
                brands.get(i).setMheader(brandNavItem);
            }
            List<Item> items = new ArrayList<>();
            if (brands.get(i).getObjs() != null) {
                for (int j = 0; j < brands.get(i).getObjs().size(); j++) {
                    if (brands.get(i).getObjs().get(j) instanceof Goods) {
                        BrandGoodsItem brandGoodsItem = new BrandGoodsItem((Goods) brands.get(i)
                                .getObjs().get(j), context);
                        items.add(brandGoodsItem);
                    } else {
                        BrandBannerItem brandBannerItem = new BrandBannerItem((Banner) brands.get
                                (i).getObjs().get(j), context);
                        items.add(brandBannerItem);
                    }
                }
            }
            brands.get(i).setItemList(items);
        }
        return brands;
    }

    public void selectNavs(int pos) {
        int p = 0;
        Banner banner = null;
        for (int i = 1; i < brands.size(); i++) {
            Brand brand = brands.get(i);
            if (brand.getHeader() != null) {
                for (int j = 0; j < ((List<FaatNav>) brands.get(i).getHeader()).size(); j++) {
                    ((List<FaatNav>) brands.get(i).getHeader()).get(j).setIsSelect(false);
                }
                p += 1;
            }
            if (brand.getObjs() != null) {
                if (pos <= p + brand.getObjs().size()) {
                    for (int j = 0; j < brand.getObjs().size(); j++) {
                        if (brand.getObjs().get(j) instanceof Banner) {
                            if (pos >= p) {
                                banner = (Banner) brand.getObjs().get(j);
                            }
                        }
                        p += 1;
                    }
                } else {
                    p += brand.getObjs().size();
                }
            }
            if (brand.getFooter() != null) {
                p += 1;
            }
            if (banner != null) {
                ((List<FaatNav>) brands.get(i).getHeader()).get(banner.getNavPos()).setIsSelect
                        (true);
                break;
            }
        }
        adapter.notifyHeaderChanged(1);
    }

    public void clickSelectNavs(int group, int pos) {
        int p = 0;
        for (int i = 1; i < brands.size(); i++) {
            int n = 0;
            Brand brand = brands.get(i);
            if (brand.getHeader() != null) {
                p +=1;
                for (int j = 0; j < ((List<FaatNav>) brands.get(i).getHeader()).size(); j++) {
                    ((List<FaatNav>) brands.get(i).getHeader()).get(j).setIsSelect(false);
                }
            }
            if (brand.getObjs() != null) {
                if (pos <= p + brand.getObjs().size()) {
                    for (int j = 0; j < brand.getObjs().size(); j++) {
                        p += 1;
                        if (brand.getObjs().get(j) instanceof Banner) {
                            if(n == pos){
                                mRecyclerView.smoothScrollToPosition(p);
                            }
                            n += 1;
                        }
                    }
                } else {
                    p += brand.getObjs().size();
                }
            }
        }
        ((List<FaatNav>) brands.get(group).getHeader()).get(pos).setIsSelect(true);
    }

    BrandNavItem.OnItemClickListener navListener = new BrandNavItem.OnItemClickListener(){
        @Override
        public void clicked(int group, int pos) {
            clickSelectNavs(group,pos);
        }
    };

    RecyclerView.OnScrollListener rvScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == 0) {
                selectNavs(position);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            position = groupedGridLayoutManager.findFirstVisibleItemPosition();
        }
    };
}
