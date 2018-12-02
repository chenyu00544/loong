package com.vcvb.chenyu.shop.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.home.HomeBean;
import com.vcvb.chenyu.shop.msg.MessageActivity;
import com.vcvb.chenyu.shop.search.SearchActivity;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.UrlParse;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class FragmentHome extends BaseFragment {

    private JSONObject data;
    private JSONObject loadMoreData;
    private JSONObject locationData;

    private RecyclerView mRecyclerView;
    private CYCGridAdapter mAdapter = new CYCGridAdapter();
    private HomeBean homeBean = new HomeBean();
    private GridLayoutManager mLayoutManager;
    private HomeItemDecoration homeItemDecoration;

    private RefreshLayout refreshLayout;
    private ImageView upwardView;
    private int pos;
    private int page = 1;

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
        FragmentHomePermissionsDispatcher.locationWithCheck(this);
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
                loadmore();
                refreshLayout.finishLoadMore(10000/*,false*/);//传入false表示加载失败
            }
        });
    }

    //定位
    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
            .ACCESS_FINE_LOCATION})
    public void location() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context
                .LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
                .PERMISSION_GRANTED) {
            return;
        }
        Location location = null;
        if (locationManager != null) {
            List<String> prodiverlist = locationManager.getProviders(true);
            String provider = "";
            if (prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
                provider = LocationManager.NETWORK_PROVIDER;//网络定位
            } else if (prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
                provider = LocationManager.GPS_PROVIDER;//GPS定位
            } else {
                provider = null;
            }
            location = locationManager.getLastKnownLocation(provider);
        }
        if (location != null) {
            HashMap<String, String> mp = new HashMap<>();
            mp.put("lat", "" + location.getLatitude());
            mp.put("long", "" + location.getLongitude());
            HttpUtils.getInstance().post(ConstantManager.Url.GETGEO, mp, new HttpUtils.NetCall() {
                @Override
                public void success(Call call, JSONObject json) throws IOException {
                    locationData = json;
                    setLocationInfo();
                }

                @Override
                public void failed(Call call, IOException e) {

                }
            });
        }
    }

    public void setLocationInfo() {
        if (locationData != null) {
            Map<String, String> user = new HashMap<>();
            try {
                user.put("region_id", String.valueOf(locationData.getJSONObject("data").getString
                        ("region_id")));
                user.put("region_name", String.valueOf(locationData.getJSONObject("data")
                        .getString("region_name")));
                user.put("formatted_address", String.valueOf(locationData.getJSONObject("data")
                        .getString("formatted_address")));
                UserInfoUtils.getInstance(context).setUserInfo(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void getData(final boolean bool) {
        if (bool) {
            loadingDialog.show();
        }
        page = 1;
        HashMap<String, String> mp = new HashMap<>();
        mp.put("page", page + "");
        HttpUtils.getInstance().post(ConstantManager.Url.HOME, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if (json != null) {
                    data = json;
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (bool) {
                                    loadingDialog.dismiss();
                                } else if (refreshLayout != null) {
                                    refreshLayout.finishRefresh();
                                }
                                bindData();
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

    public void loadmore() {
        page += 1;
        HashMap<String, String> mp = new HashMap<>();
        mp.put("page", page + "");
        HttpUtils.getInstance().post(ConstantManager.Url.HOMELOADMORE, mp, new HttpUtils.NetCall() {
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
                            Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void bindData() {
        if (data != null) {
            try {
                homeBean.setData(data.getJSONObject("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mAdapter.clear();

        homeItemDecoration.setData(homeBean);
        mAdapter.addAll(getItems(homeBean));
        final int pos = homeBean.getAdses().size();
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                if (position >= pos) {
                    int p = position - pos;
                    Goods goods = homeBean.getGoodses().get(p);
                    Intent intent = new Intent(context, GoodsDetailActivity.class);
                    intent.putExtra("id", goods.getGoods_id());
                    context.startActivity(intent);
                }
            }
        });
    }

    public void bindLoadMoreData() {
        if (loadMoreData != null) {
            try {
                HomeBean homeBean = new HomeBean();
                homeBean.setData(loadMoreData.getJSONObject("data"));
                int index = this.homeBean.getAdses().size() + this.homeBean.getGoodses().size();
                mAdapter.addAll(index, getItems(homeBean));
                mAdapter.notifyItemRangeChanged(index, homeBean.getGoodses().size());
                this.homeBean.getGoodses().addAll(homeBean.getGoodses());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<Item> getItems(HomeBean bean) {
        List<Item> cells = new ArrayList<>();
        if (bean.getAdses() != null && bean.getAdses().size() > 0) {
            for (int i = 0; i < bean.getAdses().size(); i++) {
                switch (bean.getAdses().get(i).getType()) {
                    case "slide":
                        //nav
                        HomeSlideItem homeSlideItem = new HomeSlideItem(bean.getAdses().get(i),
                                context);
                        homeSlideItem.setOnItemClickListener(homeSlideItemListener);
                        cells.add(homeSlideItem);
                        break;
                    case "navigation":
                        //nav
                        HomeNavsItem homeNavsItem = new HomeNavsItem(bean.getAdses().get(i),
                                context);
                        homeNavsItem.setOnItemClickListener(homeNavsItemListener);
                        cells.add(homeNavsItem);
                        break;
                    case "ads_1":
                        //1-2广告
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

    public void goToActivityByAdsUri(String type, int pos) {
        for (int i = 0; i < homeBean.getAdses().size(); i++) {
            if (homeBean.getAdses().get(i).getType().equals(type)) {
                String uri = homeBean.getAdses().get(i).getAds().get(pos).getAd_link();
                Class c = UrlParse.getUrlToClass(uri);
                if (c != null) {
                    Map<String, String> id = UrlParse.getUrlParams(uri);
                    Intent intent = new Intent(context, c);
                    intent.putExtra("id", Integer.valueOf(id.get("id")));
                    context.startActivity(intent);
                }
            }
        }
    }

    HomeSlideItem.OnClickListener homeSlideItemListener = new HomeSlideItem.OnClickListener() {
        @Override
        public void onClicked(int pos) {
            goToActivityByAdsUri("slide", pos);
        }
    };
    HomeNavsItem.OnClickListener homeNavsItemListener = new HomeNavsItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("navigation", pos);
        }
    };
    HomeAds1Item.OnClickListener homeAds1ItemListener = new HomeAds1Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_1", pos);
        }
    };
    HomeAds2Item.OnClickListener homeAds2ItemListener = new HomeAds2Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_2", pos);
        }
    };
    HomeAds3Item.OnClickListener homeAds3ItemListener = new HomeAds3Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_3", pos);
        }
    };
    HomeAds4Item.OnClickListener homeAds4ItemListener = new HomeAds4Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_10", pos);
        }
    };
    HomeAds5Item.OnClickListener homeAds5ItemListener = new HomeAds5Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_4", pos);
        }
    };
    HomeAds6Item.OnClickListener homeAds6ItemListener = new HomeAds6Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_5", pos);
        }
    };
    HomeAds7Item.OnClickListener homeAds7ItemListener = new HomeAds7Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_7", pos);
        }
    };
    HomeAds8Item.OnClickListener homeAds8ItemListener = new HomeAds8Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_8", pos);
        }
    };
    HomeAds9Item.OnClickListener homeAds9ItemListener = new HomeAds9Item.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToActivityByAdsUri("ads_9", pos);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //发起定位
                FragmentHomePermissionsDispatcher.locationWithCheck(this);
            } else {
                ToastUtils.showShortToast(context, "您拒绝了定位权限");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
