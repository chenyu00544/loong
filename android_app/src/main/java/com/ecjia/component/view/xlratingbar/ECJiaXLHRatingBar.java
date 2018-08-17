package com.ecjia.component.view.xlratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.ecjia.a.y;
import com.ecmoban.android.missmall.R;

public class ECJiaXLHRatingBar extends LinearLayout {
    private boolean canEdit;
    private int countNum;
    private int countSelected;
    private boolean differentSize;
    private float dividerWidth;
    private b mOnRatingChangeListener;
    private int stateResId;
    private float widthAndHeight;

    private class a implements OnClickListener {
        int a;
        final /* synthetic */ ECJiaXLHRatingBar b;

        public a(ECJiaXLHRatingBar eCJiaXLHRatingBar, int i) {
            this.b = eCJiaXLHRatingBar;
            this.a = i;
        }

        public void onClick(View view) {
            this.b.countSelected = this.a + 1;
            for (int i = 0; i < this.b.countNum; i++) {
                CheckBox checkBox = (CheckBox) this.b.getChildAt(i);
                if (i <= this.a) {
                    checkBox.setChecked(true);
                } else if (i > this.a) {
                    checkBox.setChecked(false);
                }
            }
            if (this.b.mOnRatingChangeListener != null) {
                this.b.mOnRatingChangeListener.a(this.b.countSelected);
            }
        }
    }

    public interface b {
        void a(int i);
    }

    public ECJiaXLHRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XlHRatingBar);
        this.countNum = obtainStyledAttributes.getInt(0, 5);
        this.countSelected = obtainStyledAttributes.getInt(4, 0);
        this.canEdit = obtainStyledAttributes.getBoolean(1, false);
        this.differentSize = obtainStyledAttributes.getBoolean(6, false);
        this.widthAndHeight = obtainStyledAttributes.getDimension(2, (float) y.a(context, 0));
        this.dividerWidth = obtainStyledAttributes.getDimension(3, (float) y.a(context, 0));
        this.stateResId = obtainStyledAttributes.getResourceId(5, -1);
        initView();
    }

    public int getCountNum() {
        return this.countNum;
    }

    public void setCountNum(int i) {
        this.countNum = i;
        initView();
    }

    public int getCountSelected() {
        return this.countSelected;
    }

    public void setCountSelected(int i) {
        if (i <= this.countNum) {
            this.countSelected = i;
            initView();
        }
    }

    private void initView() {
        removeAllViews();
        int i = 0;
        while (i < this.countNum) {
            LayoutParams layoutParams;
            View checkBox = new CheckBox(getContext());
            if (this.widthAndHeight == 0.0f) {
                layoutParams = new LinearLayout.LayoutParams(-2, -2);
            } else {
                layoutParams = new LinearLayout.LayoutParams((int) this.widthAndHeight, (int) this.widthAndHeight);
            }
            if (this.differentSize && this.countNum % 2 != 0) {
                int i2;
                Log.e("xxx", layoutParams.width + "");
                if (i > this.countNum / 2) {
                    i2 = (this.countNum - 1) - i;
                } else {
                    i2 = i;
                }
                layoutParams.width = (int) ((((float) (i2 + 1)) / ((float) ((this.countNum / 2) + 1))) * ((float) layoutParams.width));
                layoutParams.height = layoutParams.width;
            }
            layoutParams.gravity = 16;
            if (i != 0 && i != this.countNum - 1) {
                layoutParams.leftMargin = (int) this.dividerWidth;
                layoutParams.rightMargin = (int) this.dividerWidth;
            } else if (i == 0) {
                layoutParams.rightMargin = (int) this.dividerWidth;
            } else if (i == this.countNum - 1) {
                layoutParams.leftMargin = (int) this.dividerWidth;
            }
            addView(checkBox, layoutParams);
            checkBox.setButtonDrawable(new ColorDrawable(getResources().getColor(17170445)));
            if (this.stateResId == -1) {
                this.stateResId = R.drawable.comment_ratingbar_selector;
            }
            checkBox.setBackgroundResource(this.stateResId);
            if (i + 1 <= this.countSelected) {
                checkBox.setChecked(true);
            }
            checkBox.setEnabled(this.canEdit);
            checkBox.setOnClickListener(new a(this, i));
            i++;
        }
    }

    public b getOnRatingChangeListener() {
        return this.mOnRatingChangeListener;
    }

    public void setOnRatingChangeListener(b bVar) {
        this.mOnRatingChangeListener = bVar;
    }
}
