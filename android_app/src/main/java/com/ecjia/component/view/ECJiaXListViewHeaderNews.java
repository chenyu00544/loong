package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

public class ECJiaXListViewHeaderNews extends LinearLayout {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    public static final int STATE_REFRESHING = 2;
    private final int ROTATE_ANIM_DURATION = 180;
    private ImageView mArrowImageView;
    private LinearLayout mContainer;
    private TextView mHintTextView;
    private ProgressBar mProgressBar;
    private Animation mRotateDownAnim;
    private Animation mRotateUpAnim;
    private int mState = 0;

    public ECJiaXListViewHeaderNews(Context context) {
        super(context);
        initView(context);
    }

    public ECJiaXListViewHeaderNews(Context context, AttributeSet attributeSet) {
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
        this.mHintTextView.setText(R.string.xlistview_footer_hint_normal);
        this.mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);
        this.mRotateUpAnim = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateUpAnim.setDuration(180);
        this.mRotateUpAnim.setFillAfter(true);
        this.mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateDownAnim.setDuration(180);
        this.mRotateDownAnim.setFillAfter(true);
    }

    public void setState(int i) {
        if (i != this.mState) {
            if (i == 2) {
                this.mArrowImageView.clearAnimation();
                this.mArrowImageView.setVisibility(4);
                this.mProgressBar.setVisibility(0);
            } else {
                this.mArrowImageView.setVisibility(0);
                this.mProgressBar.setVisibility(4);
            }
            switch (i) {
                case 0:
                    if (this.mState == 1) {
                        this.mArrowImageView.startAnimation(this.mRotateDownAnim);
                    }
                    if (this.mState == 2) {
                        this.mArrowImageView.clearAnimation();
                    }
                    this.mHintTextView.setText(R.string.xlistview_footer_hint_normal);
                    break;
                case 1:
                    if (this.mState != 1) {
                        this.mArrowImageView.clearAnimation();
                        this.mArrowImageView.startAnimation(this.mRotateUpAnim);
                        this.mHintTextView.setText(R.string.xlistview_footer_hint_ready);
                        break;
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
