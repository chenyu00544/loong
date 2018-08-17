package com.ecjia.component.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;
import java.util.Date;

public class ECJiaXListViewHeader extends LinearLayout {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    public static final int STATE_REFRESHING = 2;
    private final int ROTATE_ANIM_DURATION = 180;
    public AnimationDrawable animationDrawable;
    private ImageView mArrowImageView;
    private LinearLayout mContainer;
    private TextView mHintTextView;
    private ProgressBar mProgressBar;
    private int mState = 0;
    public long startRefreshTime;
    private boolean topPullLoadMore = false;

    public void setTopPullLoadMore(boolean z) {
        this.topPullLoadMore = z;
    }

    public ECJiaXListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    public ECJiaXListViewHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        this.mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);
        addView(this.mContainer, layoutParams);
        setGravity(80);
        this.mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
        this.mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
        this.mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);
    }

    public void setState(int i) {
        this.mArrowImageView.setImageResource(R.drawable.animation_xlistview);
        this.animationDrawable = (AnimationDrawable) this.mArrowImageView.getDrawable();
        if (i != this.mState) {
            if (i == 2) {
                this.startRefreshTime = new Date().getTime();
                if (this.animationDrawable.isOneShot()) {
                    this.animationDrawable.setOneShot(false);
                }
                this.animationDrawable.start();
            } else {
                if (this.animationDrawable.isRunning()) {
                    this.animationDrawable.stop();
                }
                this.mArrowImageView.setVisibility(0);
            }
            switch (i) {
                case 0:
                    if (this.mState == 1) {
                    }
                    if (this.mState == 2) {
                    }
                    if (!this.topPullLoadMore) {
                        this.mHintTextView.setText(R.string.xlistview_header_hint_normal);
                        break;
                    } else {
                        this.mHintTextView.setText(R.string.xlistview_header_hint_normal_load);
                        break;
                    }
                case 1:
                    if (this.mState != 1) {
                        this.mArrowImageView.clearAnimation();
                        if (!this.topPullLoadMore) {
                            this.mHintTextView.setText(R.string.xlistview_header_hint_ready);
                            break;
                        } else {
                            this.mHintTextView.setText(R.string.xlistview_header_hint_ready_load);
                            break;
                        }
                    }
                    break;
                case 2:
                    this.mHintTextView.setText(R.string.xlistview_header_hint_loading);
                    break;
            }
            this.mState = i;
        }
    }

    public void setVisiableHeight(int i) {
        if (i < 0) {
            i = 0;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mContainer.getLayoutParams();
        layoutParams.height = i;
        this.mContainer.setLayoutParams(layoutParams);
    }

    public int getVisiableHeight() {
        return this.mContainer.getHeight();
    }
}
