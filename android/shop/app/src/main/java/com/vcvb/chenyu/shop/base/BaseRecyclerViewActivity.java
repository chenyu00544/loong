package com.vcvb.chenyu.shop.base;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

public class BaseRecyclerViewActivity extends BaseActivity {

    public RecyclerView mRecyclerView;
    public CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    public GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = (String) UserInfoUtils.getInstance(this).getUserInfo().get("token");
    }

    public void setTitle(String str, int id){
        ((TextView)findViewById(id)).setText(str);
    }

    public void setTitle(int str, int id){
        ((TextView)findViewById(id)).setText(str);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imageView23:
                    SwipeBackHelper.finish(BaseRecyclerViewActivity.this);
                    break;
                case R.id.imageView94:

                    break;
            }
        }
    };
}
