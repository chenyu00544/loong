package com.vcvb.chenyu.shop.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
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

import com.daimajia.swipe.SwipeLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.dialog.CancelItem;
import com.vcvb.chenyu.shop.adapter.item.dialog.OrderCouponsItem;
import com.vcvb.chenyu.shop.adapter.item.dialog.OrderCouponsNoItem;
import com.vcvb.chenyu.shop.adapter.item.dialog.OrderCouponsNoUseItem;
import com.vcvb.chenyu.shop.javaBean.faat.Coupons;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class OrderCouponsDialog extends DialogFragment {

    Window window;
    View v;
    Context context;
    String tag;
    OnClickListener onClickListener;
    List<Coupons> couponses;
    Integer left_p = 0;
    View line;

    private ConstraintSet set = new ConstraintSet();
    private ConstraintLayout cly;

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView1;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private CYCSimpleAdapter mAdapter1 = new CYCSimpleAdapter();
    private GridLayoutManager mLayoutManager;
    private GridLayoutManager mLayoutManager1;

    @SuppressLint("ValidFragment")
    public OrderCouponsDialog(List<Coupons> coupons) {
        couponses = coupons;
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
        v = inflater.inflate(R.layout.dialog_coupons_faat, container);
        cly = v.findViewById(R.id.coupons_header);
        set.clone(cly);
        window = getDialog().getWindow();
        TextView tv = v.findViewById(R.id.textView230);

        tv.setText(R.string.coupons);
        ImageView close = v.findViewById(R.id.imageView117);
        close.setOnClickListener(listener);

        line = v.findViewById(R.id.view83);

        mRecyclerView = v.findViewById(R.id.coupons_yes_wrap);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.clear();
        mAdapter.addAll(getItems(couponses, true));

        mRecyclerView1 = v.findViewById(R.id.coupons_no_wrap);
        mLayoutManager1 = new GridLayoutManager(context, 1);
        mRecyclerView1.setLayoutManager(mLayoutManager1);
        mRecyclerView1.setAdapter(mAdapter1);
        mAdapter1.clear();
        mAdapter1.addAll(getItems(couponses, false));

        SwipeLayout swipeLayout = v.findViewById(R.id.coupons_wrap);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        swipeLayout.addSwipeListener(swipeListener);
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

    protected List<Item> getItems(List<Coupons> coupons, boolean b) {
        List<Item> cells = new ArrayList<>();
        boolean bool = true;
        for (int i = 0; i < coupons.size(); i++) {
            if (b) {
                if (coupons.get(i).isEnabled()) {
                    if (bool) {
                        bool = false;
                        CancelItem cancelItem = new CancelItem(null, context);
                        cancelItem.setOnItemClickListener(cancellistener);
                        cells.add(cancelItem);
                    }
                    OrderCouponsItem orderCouponsItem = new OrderCouponsItem(coupons.get(i),
                            context);
                    orderCouponsItem.setOnItemClickListener(couponslistener);
                    cells.add(orderCouponsItem);
                }

            } else {
                if (!coupons.get(i).isEnabled()) {
                    OrderCouponsNoItem orderCouponsNoItem = new OrderCouponsNoItem(coupons
                            .get(i), context);
                    cells.add(orderCouponsNoItem);
                }
            }
        }
        if (cells.size() == 0) {
            OrderCouponsNoUseItem orderCouponsNoUseItem = new OrderCouponsNoUseItem(null,
                    context);
            cells.add(orderCouponsNoUseItem);
        }
        return cells;
    }

    private void swipe() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(cly);
        }
        set.clear(line.getId());
        set.constrainWidth(line.getId(), 160);
        set.constrainHeight(line.getId(), 4);
        if (left_p == 0) {
            set.connect(line.getId(), ConstraintSet.LEFT, R.id.textView251, ConstraintSet.LEFT, 0);
            set.connect(line.getId(), ConstraintSet.RIGHT, R.id.textView251, ConstraintSet.RIGHT,
                    0);
            set.connect(line.getId(), ConstraintSet.TOP, R.id.textView251, ConstraintSet.BOTTOM, 8);
        } else if (left_p < 0) {
            set.connect(line.getId(), ConstraintSet.LEFT, R.id.textView252, ConstraintSet.LEFT, 0);
            set.connect(line.getId(), ConstraintSet.RIGHT, R.id.textView252, ConstraintSet.RIGHT,
                    0);
            set.connect(line.getId(), ConstraintSet.TOP, R.id.textView252, ConstraintSet.BOTTOM, 8);
        }
        set.applyTo(cly);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public interface OnClickListener {
        void onClicked(View view, int pos);
    }

    OrderCouponsItem.OnClickListener couponslistener = new OrderCouponsItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, pos);
            }
        }
    };

    CancelItem.OnClickListener cancellistener = new CancelItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, pos);
            }
        }
    };

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    SwipeLayout.SwipeListener swipeListener = new SwipeLayout.SwipeListener() {
        @Override
        public void onStartOpen(SwipeLayout layout) {
            //fixme 滑动开始
        }

        @Override
        public void onOpen(SwipeLayout layout) {
            //fixme 开始滑动结束
            swipe();
        }

        @Override
        public void onStartClose(SwipeLayout layout) {
            //fixme 返回滑动开始
        }

        @Override
        public void onClose(SwipeLayout layout) {
            //fixme 返回滑动结束
            swipe();
        }

        @Override
        public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
            left_p = leftOffset;
        }

        @Override
        public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
        }
    };
}
