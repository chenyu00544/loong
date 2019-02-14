package com.vcvb.chenyu.shop.activity.categoods.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCGridAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.home.CateNavsItem;
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
import com.vcvb.chenyu.shop.adapter.item.home.HomeSlideItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.HomeItemDecoration;
import com.vcvb.chenyu.shop.base.BaseFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.categoods.CategroyGoods;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.vcvb.chenyu.shop.tools.UrlParse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class FragmentCate extends BaseFragment {

    private JSONObject loadMoreData;

    private RecyclerView mRecyclerView;
    private CYCGridAdapter mAdapter = new CYCGridAdapter();
    private CategroyGoods categroyGoods = new CategroyGoods();
    private GridLayoutManager mLayoutManager;
    private HomeItemDecoration homeItemDecoration;

    private ConstraintLayout header;

    private RefreshLayout refreshLayout;
    private int sroll_y = 0;
    private int page = 1;
    private int cateId = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.refresh_recyclerview_list, container, false);
        if (getActivity() != null) {
            cateId = getActivity().getIntent().getIntExtra("cate", 0);
        }
        initRefresh();
        initView();
        getData(true);
        return view;
    }

    private void initView() {
        header = view.findViewById(R.id.nav_header_home);
        mRecyclerView = view.findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 6);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
//        homeItemDecoration = new HomeItemDecoration(context);
//        mRecyclerView.addItemDecoration(homeItemDecoration);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sroll_y += dy;
                if (sroll_y > ToolUtils.dip2px(context, 70) + ToolUtils.getWindowsWidth(context)
                        / 2) {
                    header.setBackgroundColor(context.getResources().getColor(R.color
                            .colorBack_morandi));
                } else {
                    header.setBackgroundColor(context.getResources().getColor(R.color
                            .color_transparent));
                }
            }
        });
    }

    private void initRefresh() {
        refreshLayout = view.findViewById(R.id.refresh);
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
                loadmore();
                refreshLayout.finishLoadMore(10000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void getData(final boolean bool) {
        if (bool) {
            loadingDialog.show();
        }
        page = 1;
        HashMap<String, String> mp = new HashMap<>();
        mp.put("page", page + "");
        mp.put("id", cateId + "");
        HttpUtils.getInstance().post(ConstantManager.Url.CATEGORY_GOODS, mp, new HttpUtils
                .NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (json != null) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (bool) {
                                    loadingDialog.dismiss();
                                } else if (refreshLayout != null) {
                                    refreshLayout.finishRefresh();
                                }
                                bindData(json);
                            }
                        });
                    }
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                if (getActivity() != null) {
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
            }
        });
    }

    public void bindData(JSONObject json) {
        if (json != null) {
            try {
                categroyGoods.setData(json.getJSONObject("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mAdapter.clear();
//        homeItemDecoration.setData(categroyGoods);
        mAdapter.addAll(getItems(categroyGoods));
//        final int pos = categroyGoods.getAdses().size();
//        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
//                .OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
//                if (position >= pos) {
//                    int p = position - pos;
//                    Goods goods = categroyGoods.getGoodses().get(p);
//                    Intent intent = new Intent(context, GoodsDetailActivity.class);
//                    intent.putExtra("id", goods.getGoods_id());
//                    context.startActivity(intent);
//                }
//            }
//        });
    }

    public void loadmore() {
        page += 1;
        HashMap<String, String> mp = new HashMap<>();
        mp.put("page", page + "");
        HttpUtils.getInstance().post(ConstantManager.Url.CATEGORY_GOODS_LOADMORE, mp, new
                HttpUtils.NetCall() {
                    @Override
                    public void success(Call call, JSONObject json) throws IOException {
                        if (json != null) {
                            loadMoreData = json;
                            if (getActivity() != null) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        refreshLayout.finishLoadMore();
                                        bindLoadMoreData();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void failed(Call call, IOException e) {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    refreshLayout.finishLoadMore(false);
                                    Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });
                        }
                    }
                });
    }

    public void bindLoadMoreData() {
        if (loadMoreData != null) {
            try {
                CategroyGoods _categroyGoods = new CategroyGoods();
                _categroyGoods.setData(loadMoreData.getJSONObject("data"));
                int index = this.categroyGoods.getAdses().size() + this.categroyGoods.getGoodses
                        ().size();
                mAdapter.addAll(index, getItems(_categroyGoods));
                mAdapter.notifyItemRangeChanged(index, _categroyGoods.getGoodses().size());
                this.categroyGoods.getGoodses().addAll(_categroyGoods.getGoodses());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<Item> getItems(CategroyGoods bean) {
        List<Item> cells = new ArrayList<>();
        if (bean.getAdses() != null && bean.getAdses().size() > 0) {
            for (int i = 0; i < bean.getAdses().size(); i++) {
                switch (bean.getAdses().get(i).getType()) {
                    case "slide":
                        //nav
                        HomeSlideItem homeSlideItem = new HomeSlideItem(bean.getAdses().get(i),
                                context);

                        homeSlideItem.setOnItemClickListener(homeSlideItemListener);
//                        homeSlideItem.setOnPageChangeListener(onPageChangeListener);
                        cells.add(homeSlideItem);
                        //nav
                        if (bean.getCates() != null && bean.getCates().size() > 0) {
                            CateNavsItem cateNavsItem = new CateNavsItem(bean.getCates(),
                                    context);
                            cateNavsItem.setOnItemClickListener(cateNavsItemListener);
                            cells.add(cateNavsItem);
                        }
                        break;
                    case "ads_1":
                        //1-1-2广告
                        HomeAds1Item homeAds1Item = new HomeAds1Item(bean.getAdses().get(i),
                                context);
                        homeAds1Item.setOnItemClickListener(homeAds1ItemListener);
                        cells.add(homeAds1Item);
                        break;
                    case "ads_2":
                        //2*2广告
                        HomeAds2Item homeAds2Item = new HomeAds2Item(bean.getAdses().get(i),
                                context);
                        homeAds2Item.setOnItemClickListener(homeAds2ItemListener);
                        cells.add(homeAds2Item);
                        break;
                    case "ads_3":
                        //2-1广告
                        HomeAds3Item homeAds3Item = new HomeAds3Item(bean.getAdses().get(i),
                                context);
                        homeAds3Item.setOnItemClickListener(homeAds3ItemListener);
                        cells.add(homeAds3Item);
                        break;
                    case "ads_10":
                        //1*2广告
                        HomeAds4Item homeAds4Item = new HomeAds4Item(bean.getAdses().get(i),
                                context);
                        homeAds4Item.setOnItemClickListener(homeAds4ItemListener);
                        cells.add(homeAds4Item);
                        break;
                    case "ads_4":
                        //3*3广告
                        HomeAds5Item homeAds5Item = new HomeAds5Item(bean.getAdses().get(i),
                                context);
                        homeAds5Item.setOnItemClickListener(homeAds5ItemListener);
                        cells.add(homeAds5Item);
                        break;
                    case "ads_5":
                        //1*4广告
                        HomeAds6Item homeAds6Item = new HomeAds6Item(bean.getAdses().get(i),
                                context);
                        homeAds6Item.setOnItemClickListener(homeAds6ItemListener);
                        cells.add(homeAds6Item);
                        break;
                    case "ads_7":
                        //条幅广告
                        HomeAds7Item homeAds7Item = new HomeAds7Item(bean.getAdses().get(i),
                                context);
                        homeAds7Item.setOnItemClickListener(homeAds7ItemListener);
                        cells.add(homeAds7Item);
                        break;
                    case "ads_8":
                        //标题头
                        HomeAds8Item homeAds8Item = new HomeAds8Item(bean.getAdses().get(i),
                                context);
                        homeAds8Item.setOnItemClickListener(homeAds8ItemListener);
                        cells.add(homeAds8Item);
                        break;
                    case "ads_9":
                        //品牌广告
                        HomeAds9Item homeAds9Item = new HomeAds9Item(bean.getAdses().get(i),
                                context);
                        homeAds9Item.setOnItemClickListener(homeAds9ItemListener);
                        cells.add(homeAds9Item);
                        break;
                }
            }

        }
        if (bean.getGoodses() != null && bean.getGoodses().size() > 0) {
            for (int i = 0; i < bean.getGoodses().size(); i++) {
                cells.add(new HomeGoods_V_Item(bean.getGoodses().get(i), context));
            }
        }
        return cells;
    }

    public void goToActivityByAdsUri(String type, int pos, int group) {
        if (categroyGoods.getAdses().get(group).getType().equals(type)) {
            String uri = categroyGoods.getAdses().get(group).getAds().get(pos).getAd_link();
            Class c = UrlParse.getUrlToClass(uri);
            if (c != null) {
                Map<String, String> id = UrlParse.getUrlParams(uri);
                if (id.get("id") != null) {
                    if (type.equals("navigation")) {
                        Intent intent = new Intent(context, c);
                        intent.putExtra("cate", Integer.valueOf(id.get("id")));
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, c);
                        intent.putExtra("id", Integer.valueOf(id.get("id")));
                        context.startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(context, c);
                    context.startActivity(intent);
                }
            }
        }
    }

    HomeSlideItem.OnClickListener homeSlideItemListener = new HomeSlideItem.OnClickListener() {
        @Override
        public void onClicked(int pos) {
            goToActivityByAdsUri("slide", pos, 0);
        }
    };
//    HomeSlideItem.OnPageChangeListener onPageChangeListener = new HomeSlideItem
//            .OnPageChangeListener() {
//        @Override
//        public void onPageChanged(int pos, Adses adses) {
//            if (adses.getAds().get(pos).getAd_color() != null) {
//                slideBg.setBackgroundColor(Color.parseColor(adses.getAds().get(pos).getAd_color()));
//            }
//        }
//    };
    CateNavsItem.OnClickListener cateNavsItemListener = new CateNavsItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("navigation", pos, group);
        }
    };
    HomeAds1Item.OnClickListener homeAds1ItemListener = new HomeAds1Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_1", pos, group);
        }
    };
    HomeAds2Item.OnClickListener homeAds2ItemListener = new HomeAds2Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_2", pos, group);
        }
    };
    HomeAds3Item.OnClickListener homeAds3ItemListener = new HomeAds3Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_3", pos, group);
        }
    };
    HomeAds4Item.OnClickListener homeAds4ItemListener = new HomeAds4Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_10", pos, group);
        }
    };
    HomeAds5Item.OnClickListener homeAds5ItemListener = new HomeAds5Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_4", pos, group);
        }
    };
    HomeAds6Item.OnClickListener homeAds6ItemListener = new HomeAds6Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_5", pos, group);
        }
    };
    HomeAds7Item.OnClickListener homeAds7ItemListener = new HomeAds7Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_7", pos, group);
        }
    };
    HomeAds8Item.OnClickListener homeAds8ItemListener = new HomeAds8Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_8", pos, group);
        }
    };
    HomeAds9Item.OnClickListener homeAds9ItemListener = new HomeAds9Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos, int group) {
            goToActivityByAdsUri("ads_9", pos, group);
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
