package com.vcvb.chenyu.shop.adapter.spacesitem;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DefaultItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int spaces;
    public DefaultItemDecoration(Context context, int spaces) {
        super();
        mContext = context;
        this.spaces = spaces;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.set(spaces, spaces, spaces, spaces);
    }
}
