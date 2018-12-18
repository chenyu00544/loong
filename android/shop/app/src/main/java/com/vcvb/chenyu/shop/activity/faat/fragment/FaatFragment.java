package com.vcvb.chenyu.shop.activity.faat.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.adapter.GroupedListAdapter;
import com.vcvb.chenyu.shop.adapter.b.Item;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatBannerItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatNavItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.FaatItemDecoration;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.Banner;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.home.Ads;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class FaatFragment extends BaseRecyclerViewFragment {

    public GroupedListAdapter adapter;
    private GroupedGridLayoutManager groupedGridLayoutManager;
    public List<Faat> faats = new ArrayList<>();

    public int position = 0;
    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;

    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.faat_fragment, container, false);
        if (getActivity() != null) {
            id = getActivity().getIntent().getStringExtra("id");
        }
        getData();
        initView();
        return view;
    }

    public void initView() {
        mRecyclerView = view.findViewById(R.id.rv_list);
        ((DefaultItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.addOnScrollListener(rvScrollListener);
        adapter = new GroupedListAdapter(context);
    }

    @Override
    public void getData() {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("id", id);
        HttpUtils.getInstance().post(ConstantManager.Url.FAAT, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
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
                            loadingDialog.dismiss();
                            Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void bindData(JSONObject json) {
        Faat faat = new Faat();
        Faat _faat = new Faat();
        List<Object> bs = new ArrayList<>();
        try {
            JSONObject data = json.getJSONObject("data");
            JSONObject bannerJSONObject = data.getJSONObject("banner");
            Banner banner = JsonUtils.fromJsonObject(bannerJSONObject, Banner.class);
            banner.setData(bannerJSONObject);
            bs.add(banner);
            faat.setObjs(bs);
            faat.setGroup(0);
            faats.add(faat);

            JSONArray faatJSONArray = data.getJSONArray("faat");
            ArrayList<Object> goodses = new ArrayList<>();
            ArrayList<FaatNav> faatNavs = new ArrayList<>();
            for (int i = 0; i < faatJSONArray.length(); i++) {
                JSONObject object = (JSONObject) faatJSONArray.get(i);
                FaatNav faatNav = JsonUtils.fromJsonObject(object, FaatNav.class);
                if (i == 0) {
                    mRecyclerView.setBackgroundColor(Color.parseColor(faatNav.getColor()));
                    faatNav.setIsSelect(true);
                }
                faatNavs.add(faatNav);

                Banner subbanner = new Banner();
                subbanner.setWidth("750");
                subbanner.setHeight("120");
                subbanner.setType("faat");
                Ads ads = new Ads();
                ads.setAd_code(object.getString("activity_thumb"));
                ArrayList<Ads> ads1 = new ArrayList<>();
                ads1.add(ads);
                subbanner.setNavpos(i);
                subbanner.setAds(ads1);
                goodses.add(subbanner);

                JSONArray goodsJSONArray = object.getJSONArray("goods");
                for (int j = 0; j < goodsJSONArray.length(); j++) {
                    JSONObject gobject = (JSONObject) goodsJSONArray.get(j);
                    Goods goods = JsonUtils.fromJsonObject(gobject, Goods.class);
                    goodses.add(goods);
                }
            }

            _faat.setHeader(faatNavs);
            _faat.setObjs(goodses);
            _faat.setGroup(1);
            faats.add(_faat);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        adapter.setData(getItems(faats));
        groupedGridLayoutManager = new GroupedGridLayoutManager(context, 3, adapter) {
            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                if (faats.get(groupPosition).getItemList().get(childPosition).getItemType() == R
                        .layout.faat_banner_item) {
                    return 3;
                }
                return super.getChildSpanSize(groupPosition, childPosition);
            }
        };
        FaatItemDecoration faatItemDecoration = new FaatItemDecoration(faats, context);
        mRecyclerView.addItemDecoration(faatItemDecoration);
        mRecyclerView.setLayoutManager(groupedGridLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    protected List<Faat> getItems(List<Faat> beans) {

        for (int i = 0; i < beans.size(); i++) {
            if (faats.get(i).getHeader() != null) {
                FaatNavItem faatNavItem = new FaatNavItem(beans.get(i), context);
                faatNavItem.setOnItemClickListener(navListener);
                faats.get(i).setMheader(faatNavItem);
            }
            List<Item> items = new ArrayList<>();
            if (faats.get(i).getObjs() != null) {
                for (int j = 0; j < faats.get(i).getObjs().size(); j++) {
                    if (faats.get(i).getObjs().get(j) instanceof Goods) {
                        FaatGoodsItem faatGoodsItem = new FaatGoodsItem((Goods) faats.get(i)
                                .getObjs().get(j), context);
                        faatGoodsItem.setSubOnItemClickListener(goodsItemListener);
                        items.add(faatGoodsItem);
                    } else {
                        FaatBannerItem faatBannerItem = new FaatBannerItem((Banner) faats.get(i)
                                .getObjs().get(j), context);
                        items.add(faatBannerItem);
                    }
                }
            }
            faats.get(i).setItemList(items);
        }
        return faats;
    }

    public void selectNavs(int pos) {
        int p = 0;
        Banner banner = null;
        for (int i = 1; i < faats.size(); i++) {
            Faat faat = faats.get(i);
            if (faat.getHeader() != null) {
                for (int j = 0; j < ((List<FaatNav>) faats.get(i).getHeader()).size(); j++) {
                    ((List<FaatNav>) faats.get(i).getHeader()).get(j).setIsSelect(false);
                }
                p += 1;
            }
            if (faat.getObjs() != null) {
                if (pos <= p + faat.getObjs().size()) {
                    for (int j = 0; j < faat.getObjs().size(); j++) {
                        if (faat.getObjs().get(j) instanceof Banner) {
                            if (pos >= p) {
                                banner = (Banner) faat.getObjs().get(j);
                            }
                        }
                        p += 1;
                    }
                } else {
                    p += faat.getObjs().size();
                }
            }
            if (faat.getFooter() != null) {
                p += 1;
            }
            if (banner != null) {
                ((List<FaatNav>) faats.get(i).getHeader()).get(banner.getNavpos()).setIsSelect
                        (true);
                break;
            }
        }
        adapter.notifyHeaderChanged(1);
    }

    public void clickSelectNavs(int group, int pos) {
        int p = 0;
        for (int i = 1; i < faats.size(); i++) {
            int n = 0;
            Faat faat = faats.get(i);
            if (faat.getHeader() != null) {
                p += 1;
                for (int j = 0; j < ((List<FaatNav>) faats.get(i).getHeader()).size(); j++) {
                    ((List<FaatNav>) faats.get(i).getHeader()).get(j).setIsSelect(false);
                }
            }
            if (faat.getObjs() != null) {
                if (pos <= p + faat.getObjs().size()) {
                    for (int j = 0; j < faat.getObjs().size(); j++) {
                        p += 1;
                        if (faat.getObjs().get(j) instanceof Banner) {
                            if (n == pos) {
                                smoothMoveToPosition(p);
                            }
                            n += 1;
                        }
                    }
                } else {
                    p += faat.getObjs().size();
                }
            }
        }
        ((List<FaatNav>) faats.get(group).getHeader()).get(pos).setIsSelect(true);
    }

    public void smoothMoveToPosition(int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt
                (mRecyclerView.getChildCount() - 1));

        if (position < firstItem) {
            //跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            //跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.scrollBy(0, top);
                //不平滑移动不走onScrollStateChanged
                selectNavs(position);
            }
        } else {
            //跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }

    public void addCart(Goods goods) {
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();
        String device_id = (String) UserInfoUtils.getInstance(context).getUserInfo().get
                ("device_id");
        mp.put("token", token);
        mp.put("device_id", device_id);
        mp.put("goods_id", goods.getGoods_id() + "");
        HttpUtils.getInstance().post(ConstantManager.Url.ADD_CART, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
                            if (json != null) {
                                try {
                                    if (json.getInt("code") == 0) {
                                        String str = "%s";
                                        ToastUtils.showShortToast(context, "添加成功");
                                    } else {
                                        ToastUtils.showShortToast(context, "已添加");
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
                            loadingDialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    FaatNavItem.OnItemClickListener navListener = new FaatNavItem.OnItemClickListener() {
        @Override
        public void clicked(int group, int pos) {
            clickSelectNavs(group, pos);
        }
    };

    RecyclerView.OnScrollListener rvScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
                mShouldScroll = false;
                smoothMoveToPosition(mToPosition);
            } else if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                selectNavs(position);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            position = groupedGridLayoutManager.findFirstVisibleItemPosition();
        }
    };

    FaatGoodsItem.OnSubItemClickListener goodsItemListener = new FaatGoodsItem
            .OnSubItemClickListener() {

        @Override
        public void clicked(int group, int pos, View v) {
            switch (v.getId()) {
                case R.id.view76:
                    addCart((Goods) faats.get(group).getObjs().get(pos));
                    break;
                default:
                    Goods goods = ((Goods) faats.get(group).getObjs().get(pos));
                    Intent intent = new Intent(context, GoodsDetailActivity.class);
                    intent.putExtra("id", goods.getGoods_id());
                    startActivity(intent);
                    break;
            }
        }
    };
}
