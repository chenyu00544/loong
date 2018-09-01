package com.vcvb.chenyu.shop.home;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.HomeRecyclerViewAdapter;
import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.javaBean.home.HomeData;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.Routes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class FragmentHome extends BaseFragment {

    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    int pos = 0;
    private JSONObject data;

    private RefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initRefresh();
        getData();
        initSearchView();
        initRecyclerView();
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
        RelativeLayout nav_back = view.findViewById(R.id.nav_back);
        final EditText editText = view.findViewById(R.id.search_text);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                editText.setCursorVisible(true);
                return false;
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        editText.setCursorVisible(false);
                        editText.clearFocus();
                        break;
                }
                return false;
            }
        });

        final LinearLayout linearLayout = view.findViewById(R.id.search_wrap);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                linearLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = linearLayout.getRootView()
                        .getHeight();
                int heightDifference = screenHeight - (r.bottom);
                if (heightDifference > 200) {
                    //软键盘显示
                } else {
                    //软键盘隐藏
                    editText.setCursorVisible(false);
                    editText.clearFocus();
                }
            }
        });
    }

    private void initRecyclerView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        recyclerView = view.findViewById(R.id.recyclerView);
        //设置RecyclerView管理器
        recyclerView.setLayoutManager(new GridLayoutManager(context, 6));
//        recyclerView.addItemDecoration(new SpacesItemDecoration(3));
        List list = new ArrayList();
        HashMap bhm = new HashMap();
        ArrayList<HashMap> imgUrls = new ArrayList<>();
        for (int i = 0; i < Images.imgUrls.length; i++) {
            HashMap bannerhm = new HashMap();
            bannerhm.put("url", Images.imgUrls[i]);
            bannerhm.put("title", "xxxooo" + i);
            bannerhm.put("path", "vcvbuy:://pages/goods/index?id=" + i);
            imgUrls.add(bannerhm);
        }
        bhm.put("banner", imgUrls);
        list.add(bhm);
        String[] ads = new String[]{
                "ads_25", "ads_1_2", "ads_11", "ads_2_1", "ads_12", "ads_11", "ads_11_title", "ads_14", "ads_11", "ads_22",
                "ads_11", "ads_33",
        };

        for (int i = 0; i < ads.length; i++) {
            HashMap ahm = new HashMap();
            HashMap adshm = new HashMap();
            ahm.put(ads[i], adshm);
            list.add(ahm);
        }

        for (int i = 0; i < 10; i++) {
            HashMap ghm = new HashMap();
            ghm.put("goods", new HashMap());
            list.add(ghm);
        }

        //初始化适配器
        adapter = new HomeRecyclerViewAdapter(list, width, context);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
        final View v = view;

        //滑动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                pos = getDistance();
                if (pos >= 255) {
                    v.findViewById(R.id.nav_header_home).setBackgroundColor(0xFFFF4081);
                    v.findViewById(R.id.search_wrap).setBackgroundResource(R.drawable.search_shape_light);
                } else {
                    pos = pos > 0 ? pos : 0;
                    v.findViewById(R.id.search_wrap).setBackgroundResource(R.drawable.search_shape);
                    String h = Integer.toHexString(pos);
                    String c = "";
                    if (pos < 16) {
                        c = "0" + h;
                    } else {
                        c = h;
                    }
                    v.findViewById(R.id.nav_header_home).setBackgroundColor(Color.parseColor
                            ("#" + c + "FF4081"));
                }
            }
        });
    }

    private int getDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        View firstVisibItem = recyclerView.getChildAt(0);
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        int itemCount = layoutManager.getItemCount();
        int recycleViewHeight = recyclerView.getHeight();
        int itemHeight = firstVisibItem.getHeight();
        int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem);
        return (firstItemPosition + 1) * itemHeight - firstItemBottom;
    }

    private void initRefresh() {
        refreshLayout = view.findViewById(R.id.content_recycler_view);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getDataByTip(false, refreshLayout);
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

    @Override
    public void getData() {
        super.getData();
        getDataByTip(true, null);
    }

    public void getDataByTip(final boolean is_first, final RefreshLayout refreshLayout) {
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
                            if (is_first) {
                                loadingDialog.dismiss();
                            } else if (refreshLayout != null) {
                                refreshLayout.finishRefresh();
                            }
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
                        if (is_first) {
                            loadingDialog.dismiss();
                        } else if (refreshLayout != null) {
                            refreshLayout.finishRefresh(false);
                        }
                        Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void bindData() {
//        System.out.println(data);
        HomeData homeData;

        try {
            if (data != null) {
                homeData = JsonUtils.fromJsonObject(data.getJSONObject("data"), HomeData.class);
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
}
