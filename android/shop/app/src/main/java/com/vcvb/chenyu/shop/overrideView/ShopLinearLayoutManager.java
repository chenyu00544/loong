package com.vcvb.chenyu.shop.overrideView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class ShopLinearLayoutManager extends LinearLayoutManager {
    private Context contxt;
    public ShopLinearLayoutManager(Context context){
        super(context);
        this.contxt = context;
    }
}
