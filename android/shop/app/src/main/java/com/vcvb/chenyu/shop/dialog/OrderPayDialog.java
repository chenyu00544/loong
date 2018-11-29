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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.dialog.GoodsAddressItem;
import com.vcvb.chenyu.shop.adapter.item.dialog.GoodsNoAddressItem;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;
import com.vcvb.chenyu.shop.javaBean.order.Pay;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class OrderPayDialog extends DialogFragment {

    Window window;
    View v;
    Context context;
    String tag;
    OnClickListener onClickListener;
    List<Pay> pays;

    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private GridLayoutManager mLayoutManager;

    @SuppressLint("ValidFragment")
    public OrderPayDialog(List<Pay> pay) {
        pays = pay;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dialog_goods_faat, container);
        window = getDialog().getWindow();
        TextView tv = v.findViewById(R.id.textView230);
        tv.setText(R.string.pay_type);
        mRecyclerView = v.findViewById(R.id.faat_wrap);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.clear();
        mAdapter.addAll(getItems(pays));
        ImageView close = v.findViewById(R.id.imageView117);
        close.setOnClickListener(listener);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager windowManager = window.getWindowManager();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        Display display = windowManager.getDefaultDisplay();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.BottomShow);
        layoutParams.width = display.getWidth();
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            this.tag = tag;
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected List<Item> getItems(List<Pay> pay) {
        List<Item> cells = new ArrayList<>();
        if (pay != null) {
            for (int i = 0; i < pay.size(); i++) {
                GoodsAddressItem goodsAddressItem = new GoodsAddressItem(pay.get(i), context);
                goodsAddressItem.setOnItemClickListener(addresslistener);
                cells.add(goodsAddressItem);
            }
        } else {
            GoodsNoAddressItem goodsNoAddressItem = new GoodsNoAddressItem(null, context);
            goodsNoAddressItem.setOnItemClickListener(noAddresslistener);
            cells.add(goodsNoAddressItem);
        }
        return cells;
    }

    public interface OnClickListener {
        void onClicked(View view, int pos);
    }

    GoodsNoAddressItem.OnClickListener noAddresslistener = new GoodsNoAddressItem.OnClickListener
            () {
        @Override
        public void onClicked(View view, int pos) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, pos);
            }
        }
    };

    GoodsAddressItem.OnClickListener addresslistener = new GoodsAddressItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, pos);
            }
        }
    };

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}
