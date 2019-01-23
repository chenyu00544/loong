package com.vcvb.chenyu.shop.activity.faat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.Item;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatSecKillGoodsItem;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatSecKillNavItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.GroupBuy;
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

public class FaatGroupBuyFragment extends BaseRecyclerViewFragment {

    public List<GroupBuy> faats = new ArrayList<>();
    private Integer currentTime;

    public int position = 0;

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
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();
        HttpUtils.getInstance().post(ConstantManager.Url.FAAT_GROUPBUY, mp, new HttpUtils.NetCall() {
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

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        mAdapter.setData(getItems(faats));
    }

    protected List<SecKill> getItems(List<SecKill> beans) {

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
