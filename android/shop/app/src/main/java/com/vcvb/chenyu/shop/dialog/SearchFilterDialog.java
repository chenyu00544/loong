package com.vcvb.chenyu.shop.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.search.SearchPriceItem;
import com.vcvb.chenyu.shop.adapter.item.search.SearchServiceItem;
import com.vcvb.chenyu.shop.javaBean.search.FilterBean;

import java.util.ArrayList;
import java.util.List;

public class SearchFilterDialog extends DialogFragment implements View.OnTouchListener,
        GestureDetector.OnGestureListener {

    Window window;
    View view;
    Context context;
    private GestureDetector mGestureDetector;

    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<FilterBean> filters = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_filter_dialog, container);
        mGestureDetector = new GestureDetector(this);
        window = getDialog().getWindow();

        mRecyclerView = view.findViewById(R.id.filter_list);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CYCSimpleAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnTouchListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        window.setGravity(Gravity.RIGHT);
        window.setWindowAnimations(R.style.RightShow);
        window.setLayout((int) (display.getWidth()*0.8), (int) (display.getHeight()*0.9));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mAdapter.addAll(getItems(filters));
    }

    public void setDate(List<FilterBean> list){
        this.filters = list;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    protected List<Item> getItems(List<FilterBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case 1:
                    cells.add(new SearchServiceItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new SearchPriceItem(list.get(i), context));
                    break;
                case 3:
                    cells.add(new SearchPriceItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent1.getX() - motionEvent.getX() > 120 && (motionEvent1.getX() - motionEvent.getX() > motionEvent1.getY() - motionEvent.getY())) {
            dismiss();
        }
        return true;
    }
}
