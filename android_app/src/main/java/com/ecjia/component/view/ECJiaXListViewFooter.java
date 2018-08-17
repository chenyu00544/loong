package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

public class ECJiaXListViewFooter extends LinearLayout {
    public static final int STATE_LOADING = 2;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    private View mContentView;
    private Context mContext;
    private TextView mHintView;
    private View mProgressBar;

    public ECJiaXListViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public ECJiaXListViewFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public void setState(int i) {
        this.mHintView.setVisibility(4);
        this.mProgressBar.setVisibility(4);
        this.mHintView.setVisibility(4);
        if (i == 1) {
            this.mHintView.setVisibility(0);
            this.mHintView.setText(R.string.xlistview_footer_hint_ready);
        } else if (i == 2) {
            this.mProgressBar.setVisibility(0);
        } else {
            this.mHintView.setVisibility(0);
            this.mHintView.setText(R.string.xlistview_footer_hint_normal);
        }
    }

    public void setBottomMargin(int i) {
        if (i >= 0) {
            LayoutParams layoutParams = (LayoutParams) this.mContentView.getLayoutParams();
            layoutParams.bottomMargin = i;
            this.mContentView.setLayoutParams(layoutParams);
        }
    }

    public int getBottomMargin() {
        return ((LayoutParams) this.mContentView.getLayoutParams()).bottomMargin;
    }

    public void normal() {
        this.mHintView.setVisibility(0);
        this.mProgressBar.setVisibility(8);
    }

    public void loading() {
        this.mHintView.setVisibility(8);
        this.mProgressBar.setVisibility(0);
    }

    public void hide() {
        LayoutParams layoutParams = (LayoutParams) this.mContentView.getLayoutParams();
        layoutParams.height = 0;
        this.mContentView.setLayoutParams(layoutParams);
    }

    public void show() {
        LayoutParams layoutParams = (LayoutParams) this.mContentView.getLayoutParams();
        layoutParams.height = -2;
        this.mContentView.setLayoutParams(layoutParams);
    }

    private void initView(Context context) {
        this.mContext = context;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.xlistview_footer, null);
        addView(linearLayout);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.mContentView = linearLayout.findViewById(R.id.xlistview_footer_content);
        this.mProgressBar = linearLayout.findViewById(R.id.xlistview_footer_progressbar);
        this.mHintView = (TextView) linearLayout.findViewById(R.id.xlistview_footer_hint_textview);
    }
}
