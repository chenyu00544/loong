package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.List;

public class ECJiaFlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";
    private List<List<View>> mAllViews = new ArrayList();
    boolean mFlowMode = true;
    private List<Integer> mLineHeight = new ArrayList();
    private List<Integer> mLineWidth = new ArrayList();

    public ECJiaFlowLayout(Context context) {
        super(context);
    }

    public ECJiaFlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new MarginLayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -1);
    }

    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        Log.e(TAG, size + "," + size2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            measureChild(childAt, i, i2);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = (childAt.getMeasuredWidth() + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin;
            measuredHeight = marginLayoutParams.bottomMargin + (childAt.getMeasuredHeight() + marginLayoutParams.topMargin);
            int i8;
            if (this.mFlowMode) {
                if (i7 + measuredWidth > size) {
                    i6 = i5 + i4;
                    i5 = measuredWidth;
                    measuredWidth = Math.max(i7, measuredWidth);
                } else {
                    measuredWidth += i7;
                    measuredHeight = Math.max(i4, measuredHeight);
                    i8 = measuredWidth;
                    measuredWidth = i6;
                    i6 = i5;
                    i5 = i8;
                }
                if (i3 == childCount - 1) {
                    measuredWidth = Math.max(measuredWidth, i5);
                    i6 += measuredHeight;
                }
            } else {
                if (i4 + measuredHeight > size2) {
                    i8 = measuredWidth;
                    measuredWidth = i6 + i7;
                    i6 = Math.max(i4, measuredHeight);
                    i5 = i8;
                } else {
                    measuredHeight += i4;
                    i8 = Math.max(i7, measuredWidth);
                    measuredWidth = i6;
                    i6 = i5;
                    i5 = i8;
                }
                if (i3 == childCount - 1) {
                    i6 = Math.max(i6, measuredHeight);
                    measuredWidth += i5;
                }
            }
            i3++;
            i4 = measuredHeight;
            i7 = i5;
            i5 = i6;
            i6 = measuredWidth;
        }
        if (mode == 1073741824) {
            i6 = size;
        }
        if (mode2 == 1073741824) {
            measuredHeight = size2;
        } else {
            measuredHeight = i5;
        }
        setMeasuredDimension(i6, measuredHeight);
    }

    private void setFlowMode(boolean z) {
        this.mFlowMode = z;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth;
        this.mAllViews.clear();
        if (this.mFlowMode) {
            this.mLineHeight.clear();
        } else {
            this.mLineWidth.clear();
        }
        int width = getWidth();
        getHeight();
        Object arrayList = new ArrayList();
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i7 < childCount) {
            View childAt = getChildAt(i7);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (((marginLayoutParams.leftMargin + measuredWidth) + marginLayoutParams.rightMargin) + i6 > width) {
                this.mLineHeight.add(Integer.valueOf(i5));
                this.mAllViews.add(arrayList);
                i6 = 0;
                arrayList = new ArrayList();
            }
            measuredWidth = ((measuredWidth + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin) + i6;
            i6 = Math.max(i5, marginLayoutParams.bottomMargin + (marginLayoutParams.topMargin + measuredHeight));
            arrayList.add(childAt);
            i7++;
            i5 = i6;
            i6 = measuredWidth;
        }
        this.mLineHeight.add(Integer.valueOf(i5));
        this.mAllViews.add(arrayList);
        childCount = this.mAllViews.size();
        measuredWidth = 0;
        width = 0;
        int i8 = 0;
        while (measuredWidth < childCount) {
            List list = (List) this.mAllViews.get(measuredWidth);
            int intValue = ((Integer) this.mLineHeight.get(measuredWidth)).intValue();
            i7 = 0;
            i5 = i8;
            while (i7 < list.size()) {
                View view = (View) list.get(i7);
                if (view.getVisibility() == 8) {
                    i8 = i5;
                } else {
                    MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) view.getLayoutParams();
                    measuredHeight = marginLayoutParams2.leftMargin + i5;
                    int i9 = marginLayoutParams2.topMargin + width;
                    view.layout(measuredHeight, i9, view.getMeasuredWidth() + measuredHeight, view.getMeasuredHeight() + i9);
                    i8 = i5 + ((view.getMeasuredWidth() + marginLayoutParams2.leftMargin) + marginLayoutParams2.rightMargin);
                }
                i7++;
                i5 = i8;
            }
            measuredWidth++;
            width += intValue;
            i8 = 0;
        }
    }
}
