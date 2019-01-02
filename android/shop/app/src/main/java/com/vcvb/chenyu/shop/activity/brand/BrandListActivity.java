package com.vcvb.chenyu.shop.activity.brand;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.test.TestBaseItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.IOverScrollUpdateListener;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;

public class BrandListActivity extends BaseRecyclerViewActivity {

    private int scroll = 0;
    private ConstraintSet set = new ConstraintSet();
    private ConstraintLayout cly;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_store);
        context = this;
        changeStatusBarTextColor(false);
        setNavBack();
        initView();
        initListener();
        getData(true);
    }

    @Override
    public void setNavBack() {
    }

    @Override
    public void initView() {
        cly = findViewById(R.id.brand_wrap);
        set.clone(cly);
        imageView = findViewById(R.id.imageView155);
        mRecyclerView = findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addAll(getItems());

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scroll += dy;
                System.out.println(scroll);
            }
        });

        VerticalOverScrollBounceEffectDecorator decorator = new
                VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter
                (mRecyclerView));
        decorator.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
            @Override
            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
                final View view = decor.getView();
                System.out.println("-----" + offset);
                if (offset > 0) {
                    set.constrainHeight(imageView.getId(), ToolUtils.dip2px(context, 200+offset));
                    // 'view' is currently being over-scrolled from the top.
                } else if (offset < 0) {
                    // 'view' is currently being over-scrolled from the bottom.
                } else {
                    set.constrainHeight(imageView.getId(), ToolUtils.dip2px(context, 200));
                    // No over-scroll is in-effect.
                    // This is synonymous with having (state == STATE_IDLE).
                }
                set.applyTo(cly);
            }
        });
    }

    @Override
    public void getData(boolean b) {

    }

    @Override
    public void initListener() {
    }

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestBaseItem collectionItem = new TestBaseItem(null, context);
            cells.add(collectionItem);
        }
        return cells;
    }
}
