package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
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

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.HomeRecyclerViewAdapter;
import com.vcvb.chenyu.shop.image.Images;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentHome extends Fragment {
    View view;
    Context context;
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();

        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

        initSearchView();
        initRecyclerView();
        return view;
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
                }
            }
        });
    }

    private void initRecyclerView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        recyclerView = view.findViewById(R.id.recyclerView);
        //设置RecyclerView管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager
                .VERTICAL, false));
        //初始化适配器
        List list = new ArrayList();
        HashMap bhm = new HashMap();
        ArrayList<HashMap> imgUrls = new ArrayList<>();
        for (int i = 0; i < Images.imgUrls.length; i++) {
            HashMap bannerhm = new HashMap();
            bannerhm.put("url", Images.imgUrls[i]);
            bannerhm.put("title", "xxxooo" + i);
            bannerhm.put("path", "pages/xxx/xxx");
            imgUrls.add(bannerhm);
        }
        bhm.put("banner", imgUrls);
        list.add(bhm);
        String[] ads = new String[]{
                "ads_25", "ads_1_2", "ads_11", "ads_2_1", "ads_11", "ads_14", "ads_11", "ads_22",
                "ads_11", "ads_33",
        };

        for (int i = 0; i < ads.length; i++) {
            HashMap ahm = new HashMap();
            ahm.put(ads[i], new HashMap());
            list.add(ahm);
        }

        for (int i = 0; i < 10; i++) {
            HashMap ghm = new HashMap();
            ghm.put("name", "sdfadfasdf");
            list.add(ghm);
        }
        adapter = new HomeRecyclerViewAdapter(list, width);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
        final View v = view;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener(new RecyclerView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    int pos = getDistance();
                    if (pos >= 255) {
                        v.findViewById(R.id.nav_header_home).setBackgroundColor(0xFFFF4081);
                        v.findViewById(R.id.search_wrap).setBackgroundResource(R.drawable.search_shape_light);
                    } else {
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
        } else {
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    Log.i("xx", "newState: " + newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int pos = getDistance();
                    if (pos >= 255) {
                        v.findViewById(R.id.nav_header_home).setBackgroundColor(0xFFFF4081);
                        v.findViewById(R.id.search_wrap).setBackgroundResource(R.drawable.search_shape_light);
                    } else {
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
}
