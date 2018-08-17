package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class WrapperView extends ViewGroup {
    Drawable mDivider;
    int mDividerHeight;
    View mHeader;
    View mItem;
    int mItemTop;

    WrapperView(Context context) {
        super(context);
    }

    public boolean hasHeader() {
        return this.mHeader != null;
    }

    public View getItem() {
        return this.mItem;
    }

    public View getHeader() {
        return this.mHeader;
    }

    void update(View view, View view2, Drawable drawable, int i) {
        if (view == null) {
            throw new NullPointerException("List view item must not be null.");
        }
        if (this.mItem != view) {
            removeView(this.mItem);
            this.mItem = view;
            Object parent = view.getParent();
            if (!(parent == null || parent == this || !(parent instanceof ViewGroup))) {
                ((ViewGroup) parent).removeView(view);
            }
            addView(view);
        }
        if (this.mHeader != view2) {
            if (this.mHeader != null) {
                removeView(this.mHeader);
            }
            this.mHeader = view2;
            if (view2 != null) {
                addView(view2);
            }
        }
        if (this.mDivider != drawable) {
            this.mDivider = drawable;
            this.mDividerHeight = i;
            invalidate();
        }
    }

    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        int size = MeasureSpec.getSize(i);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        if (this.mHeader != null) {
            LayoutParams layoutParams = this.mHeader.getLayoutParams();
            if (layoutParams == null || layoutParams.height <= 0) {
                this.mHeader.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            } else {
                this.mHeader.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
            }
            measuredHeight = this.mHeader.getMeasuredHeight() + 0;
        } else if (this.mDivider == null || this.mItem.getVisibility() == 8) {
            measuredHeight = 0;
        } else {
            measuredHeight = this.mDividerHeight + 0;
        }
        LayoutParams layoutParams2 = this.mItem.getLayoutParams();
        if (this.mItem.getVisibility() == 8) {
            this.mItem.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, 1073741824));
        } else if (layoutParams2 == null || layoutParams2.height < 0) {
            this.mItem.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            measuredHeight += this.mItem.getMeasuredHeight();
        } else {
            this.mItem.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824));
            measuredHeight += this.mItem.getMeasuredHeight();
        }
        setMeasuredDimension(size, measuredHeight);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int height = getHeight();
        if (this.mHeader != null) {
            int measuredHeight = this.mHeader.getMeasuredHeight();
            this.mHeader.layout(0, 0, width, measuredHeight);
            this.mItemTop = measuredHeight;
            this.mItem.layout(0, measuredHeight, width, height);
        } else if (this.mDivider != null) {
            this.mDivider.setBounds(0, 0, width, this.mDividerHeight);
            this.mItemTop = this.mDividerHeight;
            this.mItem.layout(0, this.mDividerHeight, width, height);
        } else {
            this.mItemTop = 0;
            this.mItem.layout(0, 0, width, height);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mHeader == null && this.mDivider != null && this.mItem.getVisibility() != 8) {
            if (VERSION.SDK_INT < 11) {
                canvas.clipRect(0, 0, getWidth(), this.mDividerHeight);
            }
            this.mDivider.draw(canvas);
        }
    }
}
