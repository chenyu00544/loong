package com.vcvb.chenyu.shop.adapter.item.faat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;

public class FaatNavItem extends BaseItem<Faat> {
    public static final int TYPE = R.layout.faat_nav_item;
    public static final int POSTYPE = ConstantManager.Item.HEADER;

    private static HorizontalScrollView scrollView;
    private static LinearLayout linearLayout;

    private OnItemListener onItemListener;

    public FaatNavItem(Faat bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public int getPosType() {
        return POSTYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        scrollView = holder.get(R.id.navs_wrap);
        linearLayout = holder.get(R.id.navs_list);
        if (linearLayout.getChildCount() < mData.getHeader().size()) {
            for (int i = linearLayout.getChildCount(); i < mData.getHeader().size(); i++) {
                FaatNav faatNav = (FaatNav) mData.getHeader().get(i);
                View v = LayoutInflater.from(context).inflate(R.layout.faat_nav_sub_item, null);
                TextView tv = v.findViewById(R.id.textView25);
                tv.setText(faatNav.getTitle());
                linearLayout.addView(v);
            }
        }
        scrollView.setOnTouchListener(listener);
        scrollView.scrollTo(mData.getScrollX(), 0);
    }

    public interface OnItemListener{
        void srcolled(int pos_dx);
    }

    public void setOnItemListener(OnItemListener listener) {
        onItemListener = listener;
    }

    HorizontalScrollView.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(onItemListener != null){
                    onItemListener.srcolled(scrollView.getScrollX());
                }
            }else if(event.getAction() == MotionEvent.ACTION_MOVE){
                System.out.println(scrollView.getScrollX());
            }
            return false;
        }
    };
}
