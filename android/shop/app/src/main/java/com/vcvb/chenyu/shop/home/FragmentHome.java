package com.vcvb.chenyu.shop.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseFragment;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCGridAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds1Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds2Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds3Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds4Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds5Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds6Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds7Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds8Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeAds9Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeGoods_V_Item;
import com.vcvb.chenyu.shop.adapter.item.home.HomeNavsItem;
import com.vcvb.chenyu.shop.adapter.item.home.HomeSlideItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.HomeItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.javaBean.home.Ads;
import com.vcvb.chenyu.shop.javaBean.home.Adses;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.home.HomeBean;
import com.vcvb.chenyu.shop.javaBean.home.Slide;
import com.vcvb.chenyu.shop.msg.MessageActivity;
import com.vcvb.chenyu.shop.search.SearchActivity;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.Routes;
import com.vcvb.chenyu.shop.tools.UrlParse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class FragmentHome extends BaseFragment {

    private JSONObject data;

    private RecyclerView mRecyclerView;
    private CYCGridAdapter mAdapter = new CYCGridAdapter();
    private HomeBean homeBean = new HomeBean();
    private GridLayoutManager mLayoutManager;
    private HomeItemDecoration homeItemDecoration;

    private RefreshLayout refreshLayout;
    private ImageView upwardView;
    private int pos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initRefresh();
        initView();
        getData(true);
        initSearchView();
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            //   do things when fragment is visible
            //    if (ListUtils.isEmpty(mDataList) && !isRefreshing()) {
            //        setRefresh(true);
            //        loadServiceData(false);
            //      }
        } else {
            //        setRefresh(false);
        }
    }

    private void initSearchView() {
        final TextView search = view.findViewById(R.id.search_text);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("isfrom", ConstantManager.IsFrom.FROM_HOME);
                startActivity(intent);
            }
        });

        RelativeLayout other = view.findViewById(R.id.nav_other);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MessageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        upwardView = view.findViewById(R.id.imageView116);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(R.drawable.icon_upward).apply(requestOptions).into(upwardView);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(context, 6);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        homeItemDecoration = new HomeItemDecoration(context);
        mRecyclerView.addItemDecoration(homeItemDecoration);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                pos += dy;
                if (pos > 1500) {
                    upwardView.setVisibility(View.VISIBLE);
                    upwardView.setOnClickListener(listener);
                } else {
                    upwardView.setVisibility(View.GONE);
                    upwardView.setOnClickListener(null);
                }
            }
        });
    }

    private void initRefresh() {
        refreshLayout = view.findViewById(R.id.content_recycler_view);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getData(false);
                refreshLayout.finishRefresh(10000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void getData(final boolean bool) {
        if (bool) {
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", "");
        mp.put("nav_id", "0");
        HttpUtils.getInstance().post(Routes.getInstance().getIndex(), mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if (json != null) {
                    data = json;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bool) {
                                loadingDialog.dismiss();
                            } else if (refreshLayout != null) {
                                refreshLayout.finishRefresh();
                            }
//                            bindData();
                        }
                    });
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bool) {
                            loadingDialog.dismiss();
                        } else if (refreshLayout != null) {
                            refreshLayout.finishRefresh(false);
                        }
                        Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mAdapter.clear();

        String[] appUri = new String[]{"vcvbuy:://pages/goods/index?id=1",
                "vcvbuy:://pages/faat/index?id=1", "vcvbuy:://pages/brand/index?id=1"};
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i < Images.imgUrls.length; i++) {
            Slide banner = new Slide();
            banner.setPic(Images.imgUrls[i]);
            banner.setTitle("xxxooo" + i);
            banner.setAppUri(appUri[i]);
            slides.add(banner);
        }
        homeBean.setSlides(slides);

        ArrayList<Adses> adses_arr = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            Adses adsess = new Adses();
            if (i == 10) {
                adsess.setIsType(8);
            } else {
                adsess.setIsType(i);
            }
            ArrayList<Ads> adses = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Ads ads = new Ads();
                ads.setPic(Images.imageUrls[j]);
                if(i==0){
                    ads.setAppUri("vcvbuy:://pages/categroy/index?id=" + j);
                }else{
                    ads.setAppUri("vcvbuy:://pages/goods/index?id=" + j);
                }
                adses.add(ads);
            }
            adsess.setAds(adses);
            adses_arr.add(adsess);
        }
        homeBean.setAdses(adses_arr);

        ArrayList<Goods> goodses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Goods goods = new Goods();
            goods.setGoodsId("" + i);
            goods.setPic(Images.imageUrls[i]);
            goods.setGoodsName("腾讯宣布推出QQ浏览器微信版，由" + i);
            goods.setGoodsPriceFormat("$188.00");
            goodses.add(goods);
        }
        homeBean.setGoodsList(goodses);

        homeItemDecoration.setData(homeBean);
        mAdapter.addAll(getItems(homeBean));
        final int pos = homeBean.getAdses().size();
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                if (position > pos) {
                    int p = position - pos - 1;
                    Goods goods = homeBean.getGoodsList().get(p);
                    Intent intent = new Intent(context, GoodsDetailActivity.class);
                    intent.putExtra("id", goods.getGoodsId());
                    context.startActivity(intent);
                }
            }
        });
    }

    protected List<Item> getItems(HomeBean bean) {
        List<Item> cells = new ArrayList<>();
        if (bean.getSlides() != null && bean.getSlides().size() > 0) {
            HomeSlideItem homeSlideItem = new HomeSlideItem(bean.getSlides(), context);
            homeSlideItem.setOnItemClickListener(homeSlideItemListener);
            cells.add(homeSlideItem);
        }
        if (bean.getAdses() != null && bean.getAdses().size() > 0) {
            for (int i = 0; i < bean.getAdses().size(); i++) {
                switch (bean.getAdses().get(i).getIsType()) {
                    case 0:
                        //nav
                        HomeNavsItem homeNavsItem = new HomeNavsItem(bean.getAdses().get(i),
                                context);
                        homeNavsItem.setOnItemClickListener(homeNavsItemListener);
                        cells.add(homeNavsItem);
                        break;
                    case 1:
                        //1-2广告
                        HomeAds1Item homeAds1Item = new HomeAds1Item(bean.getAdses().get(i),
                                context);
                        homeAds1Item.setOnItemClickListener(homeAds1ItemListener);
                        cells.add(homeAds1Item);
                        break;
                    case 2:
                        //2*2广告
                        HomeAds2Item homeAds2Item = new HomeAds2Item(bean.getAdses().get(i),
                                context);
                        homeAds2Item.setOnItemClickListener(homeAds2ItemListener);
                        cells.add(homeAds2Item);
                        break;
                    case 3:
                        //2-1广告
                        HomeAds3Item homeAds3Item = new HomeAds3Item(bean.getAdses().get(i),
                                context);
                        homeAds3Item.setOnItemClickListener(homeAds3ItemListener);
                        cells.add(homeAds3Item);
                        break;
                    case 4:
                        //3*3广告
                        HomeAds4Item homeAds4Item = new HomeAds4Item(bean.getAdses().get(i),
                                context);
                        homeAds4Item.setOnItemClickListener(homeAds4ItemListener);
                        cells.add(homeAds4Item);
                        break;
                    case 5:
                        //1*4广告
                        HomeAds5Item homeAds5Item = new HomeAds5Item(bean.getAdses().get(i),
                                context);
                        homeAds5Item.setOnItemClickListener(homeAds5ItemListener);
                        cells.add(homeAds5Item);
                        break;
                    case 6:
                        //1*1广告
                        HomeAds6Item homeAds6Item = new HomeAds6Item(bean.getAdses().get(i),
                                context);
                        homeAds6Item.setOnItemClickListener(homeAds6ItemListener);
                        cells.add(homeAds6Item);
                        break;
                    case 7:
                        //条幅广告
                        HomeAds7Item homeAds7Item = new HomeAds7Item(bean.getAdses().get(i),
                                context);
                        homeAds7Item.setOnItemClickListener(homeAds7ItemListener);
                        cells.add(homeAds7Item);
                        break;
                    case 8:
                        //标题头
                        HomeAds8Item homeAds8Item = new HomeAds8Item(bean.getAdses().get(i),
                                context);
                        homeAds8Item.setOnItemClickListener(homeAds8ItemListener);
                        cells.add(homeAds8Item);
                        break;
                    case 9:
                        //品牌广告
                        HomeAds9Item homeAds9Item = new HomeAds9Item(bean.getAdses().get(i),
                                context);
                        cells.add(homeAds9Item);
                        break;
                }
            }

        }
        if (bean.getGoodsList() != null && bean.getGoodsList().size() > 0) {
            for (int i = 0; i < bean.getGoodsList().size(); i++) {
                cells.add(new HomeGoods_V_Item(bean.getGoodsList().get(i), context));
            }
        }
        return cells;
    }

    public void bindData() {
//        System.out.println(data);
        try {
            if (data != null) {
                homeBean = JsonUtils.fromJsonObject(data.getJSONObject("data"), HomeBean.class);
//                System.out.println(homeData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    HomeSlideItem.OnClickListener homeSlideItemListener = new HomeSlideItem.OnClickListener() {
        @Override
        public void onClicked(int pos) {
            String uri = homeBean.getSlides().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };
    HomeNavsItem.OnClickListener homeNavsItemListener = new HomeNavsItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };

    HomeAds1Item.OnClickListener homeAds1ItemListener = new HomeAds1Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };
    HomeAds2Item.OnClickListener homeAds2ItemListener = new HomeAds2Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };
    HomeAds3Item.OnClickListener homeAds3ItemListener = new HomeAds3Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };
    HomeAds4Item.OnClickListener homeAds4ItemListener = new HomeAds4Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };
    HomeAds5Item.OnClickListener homeAds5ItemListener = new HomeAds5Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };
    HomeAds6Item.OnClickListener homeAds6ItemListener = new HomeAds6Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };
    HomeAds7Item.OnClickListener homeAds7ItemListener = new HomeAds7Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Log.i("tag", homeBean.getAdses().get(0).getAds().get(pos).getAppUri());
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };
    HomeAds8Item.OnClickListener homeAds8ItemListener = new HomeAds8Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            System.out.println(pos);
            String uri = homeBean.getAdses().get(0).getAds().get(pos).getAppUri();
            Class c = UrlParse.getUrlToClass(uri);
            Map<String, String> id = UrlParse.getUrlParams(uri);
            Intent intent = new Intent(context, c);
            intent.putExtra("id", id.get("id"));
            context.startActivity(intent);
        }
    };

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageView116:
                    mRecyclerView.smoothScrollToPosition(0);
                    break;
            }
        }
    };
}
