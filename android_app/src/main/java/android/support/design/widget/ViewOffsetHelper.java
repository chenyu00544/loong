package android.support.design.widget;

import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewParent;

class ViewOffsetHelper {
    private int mLayoutLeft;
    private int mLayoutTop;
    private int mOffsetLeft;
    private int mOffsetTop;
    private final View mView;

    public ViewOffsetHelper(View view) {
        this.mView = view;
    }

    public void onViewLayout() {
        this.mLayoutTop = this.mView.getTop();
        this.mLayoutLeft = this.mView.getLeft();
        updateOffsets();
    }

    private void updateOffsets() {
        ViewCompat.offsetTopAndBottom(this.mView, this.mOffsetTop - (this.mView.getTop() - this.mLayoutTop));
        ViewCompat.offsetLeftAndRight(this.mView, this.mOffsetLeft - (this.mView.getLeft() - this.mLayoutLeft));
        if (VERSION.SDK_INT < 23) {
            tickleInvalidationFlag(this.mView);
            ViewParent parent = this.mView.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    private static void tickleInvalidationFlag(View view) {
        float translationY = ViewCompat.getTranslationY(view);
        ViewCompat.setTranslationY(view, 1.0f + translationY);
        ViewCompat.setTranslationY(view, translationY);
    }

    public boolean setTopAndBottomOffset(int i) {
        if (this.mOffsetTop == i) {
            return false;
        }
        this.mOffsetTop = i;
        updateOffsets();
        return true;
    }

    public boolean setLeftAndRightOffset(int i) {
        if (this.mOffsetLeft == i) {
            return false;
        }
        this.mOffsetLeft = i;
        updateOffsets();
        return true;
    }

    public int getTopAndBottomOffset() {
        return this.mOffsetTop;
    }

    public int getLeftAndRightOffset() {
        return this.mOffsetLeft;
    }
}
