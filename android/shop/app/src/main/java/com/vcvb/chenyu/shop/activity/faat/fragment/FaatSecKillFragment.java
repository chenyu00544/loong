package com.vcvb.chenyu.shop.activity.faat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.GroupedListAdapter;
import com.vcvb.chenyu.shop.adapter.b.Item;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatSecKillGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatSecKillNavItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.SecKill;
import com.vcvb.chenyu.shop.javaBean.faat.SecKillGoods;
import com.vcvb.chenyu.shop.javaBean.faat.SecKillInfo;
import com.vcvb.chenyu.shop.javaBean.faat.SecKillNav;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.TimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class FaatSecKillFragment extends BaseRecyclerViewFragment {

    public GroupedListAdapter adapter;
    private GroupedGridLayoutManager groupedGridLayoutManager;
    public List<SecKill> faats = new ArrayList<>();
    private SecKillInfo secKillInfo;
    private Integer currentTime;

    public int position = 0;
    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.faat_fragment, container, false);
        getData();
        initView();
        return view;
    }

    public void initView() {
        mRecyclerView = view.findViewById(R.id.rv_list);
        ((DefaultItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.addOnScrollListener(rvScrollListener);
        adapter = new GroupedListAdapter(context);
        adapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                     int groupPosition, int childPosition) {
                System.out.println(faats.get(groupPosition).getObjs().get(childPosition));
            }
        });
    }

    @Override
    public void getData() {
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();
        HttpUtils.getInstance().post(ConstantManager.Url.FAAT_SECKILL, mp, new HttpUtils.NetCall() {
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
        try {
            currentTime = json.getInt("time");
            Integer code = json.getInt("code");
            if (code == 0) {
                JSONObject object = json.getJSONObject("data");
                secKillInfo = JsonUtils.fromJsonObject(object, SecKillInfo.class);
                secKillInfo.setCurrent_time(currentTime);
                JSONArray array = object.getJSONArray("goods");
                for (int i = 0; i < array.length(); i++) {
                    SecKill secKill = new SecKill();
                    JSONObject o = (JSONObject) array.get(i);
                    SecKillNav secKillNav = JsonUtils.fromJsonObject(o, SecKillNav.class);
                    secKillNav.setCurrent_time(currentTime);
                    secKill.setHeader(secKillNav);

                    JSONArray garr = o.getJSONArray("kill_goods");
                    ArrayList<Object> _goodses = new ArrayList<>();
                    for (int j = 0; j < garr.length(); j++) {
                        JSONObject go = (JSONObject) garr.get(j);
                        SecKillGoods secKillGoods = JsonUtils.fromJsonObject(go, SecKillGoods
                                .class);
                        secKillGoods.setCurrent_time(currentTime);
                        secKillGoods.setBegin_time(secKillNav.getBegin_time());
                        secKillGoods.setEnd_time(secKillNav.getEnd_time());
                        _goodses.add(secKillGoods);
                    }
                    secKill.setObjs(_goodses);
                    faats.add(secKill);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        adapter.setData(getItems(faats));
        groupedGridLayoutManager = new GroupedGridLayoutManager(context, 1, adapter) {
            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                return super.getChildSpanSize(groupPosition, childPosition);
            }
        };
        mRecyclerView.setLayoutManager(groupedGridLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    protected List<SecKill> getItems(List<SecKill> beans) {

        for (int i = 0; i < beans.size(); i++) {
            if (faats.get(i).getHeader() != null) {
                FaatSecKillNavItem faatSecKillNavItem = new FaatSecKillNavItem((SecKillNav) faats
                        .get(i).getHeader(), context);
                faats.get(i).setMheader(faatSecKillNavItem);
            }
            TimeUtils.startCountdown(new TimeUtils.CallBack() {
                @Override
                public void time() {
                    currentTime += 1;
                }
            });
            List<Item> items = new ArrayList<>();
            if (beans.get(i).getObjs() != null) {
                for (int j = 0; j < beans.get(i).getObjs().size(); j++) {
                    FaatSecKillGoodsItem faatSecKillGoodsItem = new FaatSecKillGoodsItem(
                            (SecKillGoods) faats.get(i).getObjs().get(j), context);
                    items.add(faatSecKillGoodsItem);
                }
            }
            faats.get(i).setItemList(items);
        }
        return faats;
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
            }
        } else {
            //跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }

    RecyclerView.OnScrollListener rvScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
//            if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
//                mShouldScroll = false;
//                smoothMoveToPosition(mToPosition);
//            } else if (RecyclerView.SCROLL_STATE_IDLE == newState) {
//            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            position = groupedGridLayoutManager.findFirstVisibleItemPosition();
        }
    };
}
