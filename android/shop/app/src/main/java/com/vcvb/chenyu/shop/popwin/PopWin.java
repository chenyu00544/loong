package com.vcvb.chenyu.shop.popwin;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.vcvb.chenyu.shop.R;

public class PopWin extends PopupWindow {
    private Context context;
    private View view;

    private OnItemClickListener mOnItemClickListener;
    private OnItemClickListener1 mOnItemClickListener1;
    private OnItemClickListener2 mOnItemClickListener2;

    public PopWin(Context context) {
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public PopWin(Context context, int width, int height) {
        super(context);
        this.context = context;
        setWidth(width);
        setHeight(height);
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.PopupAnimation);
        view = LayoutInflater.from(context).inflate(R.layout.menu, null);
        setContentView(view);
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

    public interface OnItemClickListener {
        void onClicked(View v);
    }

    public PopWin setClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        view.findViewById(R.id.view33).setOnClickListener(mOnClickListener);
        return this;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClicked(v);
            }
        }
    };

    public interface OnItemClickListener1 {
        void onClicked(View v);
    }

    public PopWin setClickListener1(OnItemClickListener1 listener) {
        mOnItemClickListener1 = listener;
        view.findViewById(R.id.view35).setOnClickListener(mOnClickListener1);
        return this;
    }

    private View.OnClickListener mOnClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener1 != null) {
                mOnItemClickListener1.onClicked(v);
            }
        }
    };

    public interface OnItemClickListener2 {
        void onClicked(View v);
    }

    public PopWin setClickListener2(OnItemClickListener2 listener) {
        mOnItemClickListener2 = listener;
        view.findViewById(R.id.view36).setOnClickListener(mOnClickListener2);
        return this;
    }

    private View.OnClickListener mOnClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener2 != null) {
                mOnItemClickListener2.onClicked(v);
            }
        }
    };
}
