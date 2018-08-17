package com.jeremyfeinstein.slidingmenu.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;

public class CustomViewBehind extends ViewGroup {
    private static final int MARGIN_THRESHOLD = 48;
    private static final String TAG = "CustomViewBehind";
    private boolean mChildrenEnabled;
    private View mContent;
    private float mFadeDegree;
    private boolean mFadeEnabled;
    private final Paint mFadePaint;
    private int mMarginThreshold;
    private int mMode;
    private float mScrollScale;
    private View mSecondaryContent;
    private Drawable mSecondaryShadowDrawable;
    private View mSelectedView;
    private Bitmap mSelectorDrawable;
    private boolean mSelectorEnabled;
    private Drawable mShadowDrawable;
    private int mShadowWidth;
    private int mTouchMode;
    private CanvasTransformer mTransformer;
    private CustomViewAbove mViewAbove;
    private int mWidthOffset;

    public CustomViewBehind(Context context) {
        this(context, null);
    }

    public CustomViewBehind(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTouchMode = 0;
        this.mFadePaint = new Paint();
        this.mSelectorEnabled = true;
        this.mMarginThreshold = (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics());
    }

    public void setCustomViewAbove(CustomViewAbove customViewAbove) {
        this.mViewAbove = customViewAbove;
    }

    public void setCanvasTransformer(CanvasTransformer canvasTransformer) {
        this.mTransformer = canvasTransformer;
    }

    public void setWidthOffset(int i) {
        this.mWidthOffset = i;
        requestLayout();
    }

    public void setMarginThreshold(int i) {
        this.mMarginThreshold = i;
    }

    public int getMarginThreshold() {
        return this.mMarginThreshold;
    }

    public int getBehindWidth() {
        return this.mContent.getWidth();
    }

    public void setContent(View view) {
        if (this.mContent != null) {
            removeView(this.mContent);
        }
        this.mContent = view;
        addView(this.mContent);
    }

    public View getContent() {
        return this.mContent;
    }

    public void setSecondaryContent(View view) {
        if (this.mSecondaryContent != null) {
            removeView(this.mSecondaryContent);
        }
        this.mSecondaryContent = view;
        addView(this.mSecondaryContent);
    }

    public View getSecondaryContent() {
        return this.mSecondaryContent;
    }

    public void setChildrenEnabled(boolean z) {
        this.mChildrenEnabled = z;
    }

    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
        if (this.mTransformer != null) {
            invalidate();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.mChildrenEnabled;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.mChildrenEnabled;
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.mTransformer != null) {
            canvas.save();
            this.mTransformer.transformCanvas(canvas, this.mViewAbove.getPercentOpen());
            super.dispatchDraw(canvas);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        this.mContent.layout(0, 0, i5 - this.mWidthOffset, i6);
        if (this.mSecondaryContent != null) {
            this.mSecondaryContent.layout(0, 0, i5 - this.mWidthOffset, i6);
        }
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(0, i);
        int defaultSize2 = getDefaultSize(0, i2);
        setMeasuredDimension(defaultSize, defaultSize2);
        defaultSize = getChildMeasureSpec(i, 0, defaultSize - this.mWidthOffset);
        defaultSize2 = getChildMeasureSpec(i2, 0, defaultSize2);
        this.mContent.measure(defaultSize, defaultSize2);
        if (this.mSecondaryContent != null) {
            this.mSecondaryContent.measure(defaultSize, defaultSize2);
        }
    }

    public void setMode(int i) {
        if (i == 0 || i == 1) {
            if (this.mContent != null) {
                this.mContent.setVisibility(0);
            }
            if (this.mSecondaryContent != null) {
                this.mSecondaryContent.setVisibility(4);
            }
        }
        this.mMode = i;
    }

    public int getMode() {
        return this.mMode;
    }

    public void setScrollScale(float f) {
        this.mScrollScale = f;
    }

    public float getScrollScale() {
        return this.mScrollScale;
    }

    public void setShadowDrawable(Drawable drawable) {
        this.mShadowDrawable = drawable;
        invalidate();
    }

    public void setSecondaryShadowDrawable(Drawable drawable) {
        this.mSecondaryShadowDrawable = drawable;
        invalidate();
    }

    public void setShadowWidth(int i) {
        this.mShadowWidth = i;
        invalidate();
    }

    public void setFadeEnabled(boolean z) {
        this.mFadeEnabled = z;
    }

    public void setFadeDegree(float f) {
        if (f > 1.0f || f < 0.0f) {
            throw new IllegalStateException("The BehindFadeDegree must be between 0.0f and 1.0f");
        }
        this.mFadeDegree = f;
    }

    public int getMenuPage(int i) {
        if (i > 1) {
            i = 2;
        } else if (i < 1) {
            i = 0;
        }
        if (this.mMode == 0 && i > 1) {
            return 0;
        }
        if (this.mMode != 1 || i >= 1) {
            return i;
        }
        return 2;
    }

    public void scrollBehindTo(View view, int i, int i2) {
        int i3 = 0;
        if (this.mMode == 0) {
            if (i >= view.getLeft()) {
                i3 = 4;
            }
            scrollTo((int) (((float) (getBehindWidth() + i)) * this.mScrollScale), i2);
        } else if (this.mMode == 1) {
            if (i <= view.getLeft()) {
                i3 = 4;
            }
            scrollTo((int) (((float) (getBehindWidth() - getWidth())) + (((float) (i - getBehindWidth())) * this.mScrollScale)), i2);
        } else if (this.mMode == 2) {
            int i4;
            View view2 = this.mContent;
            if (i >= view.getLeft()) {
                i4 = 4;
            } else {
                i4 = 0;
            }
            view2.setVisibility(i4);
            view2 = this.mSecondaryContent;
            if (i <= view.getLeft()) {
                i4 = 4;
            } else {
                i4 = 0;
            }
            view2.setVisibility(i4);
            if (i == 0) {
                i3 = 4;
            }
            if (i <= view.getLeft()) {
                scrollTo((int) (((float) (getBehindWidth() + i)) * this.mScrollScale), i2);
            } else {
                scrollTo((int) (((float) (getBehindWidth() - getWidth())) + (((float) (i - getBehindWidth())) * this.mScrollScale)), i2);
            }
        }
        if (i3 == 4) {
            Log.v(TAG, "behind INVISIBLE");
        }
        setVisibility(i3);
    }

    public int getMenuLeft(View view, int i) {
        if (this.mMode != 0) {
            if (this.mMode != 1) {
                if (this.mMode == 2) {
                    switch (i) {
                        case 0:
                            return view.getLeft() - getBehindWidth();
                        case 2:
                            return view.getLeft() + getBehindWidth();
                        default:
                            break;
                    }
                }
            }
            switch (i) {
                case 0:
                    return view.getLeft();
                case 2:
                    return view.getLeft() + getBehindWidth();
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return view.getLeft() - getBehindWidth();
            case 2:
                return view.getLeft();
        }
        return view.getLeft();
    }

    public int getAbsLeftBound(View view) {
        if (this.mMode == 0 || this.mMode == 2) {
            return view.getLeft() - getBehindWidth();
        }
        if (this.mMode == 1) {
            return view.getLeft();
        }
        return 0;
    }

    public int getAbsRightBound(View view) {
        if (this.mMode == 0) {
            return view.getLeft();
        }
        if (this.mMode == 1 || this.mMode == 2) {
            return view.getLeft() + getBehindWidth();
        }
        return 0;
    }

    public boolean marginTouchAllowed(View view, int i) {
        int left = view.getLeft();
        int right = view.getRight();
        if (this.mMode == 0) {
            if (i < left || i > left + this.mMarginThreshold) {
                return false;
            }
            return true;
        } else if (this.mMode == 1) {
            if (i > right || i < right - this.mMarginThreshold) {
                return false;
            }
            return true;
        } else if (this.mMode != 2) {
            return false;
        } else {
            if (i >= left && i <= left + this.mMarginThreshold) {
                return true;
            }
            if (i > right || i < right - this.mMarginThreshold) {
                return false;
            }
            return true;
        }
    }

    public void setTouchMode(int i) {
        this.mTouchMode = i;
    }

    public boolean menuOpenTouchAllowed(View view, int i, float f) {
        switch (this.mTouchMode) {
            case 0:
                return menuTouchInQuickReturn(view, i, f);
            case 1:
                return true;
            default:
                return false;
        }
    }

    public boolean menuTouchInQuickReturn(View view, int i, float f) {
        if (this.mMode == 0 || (this.mMode == 2 && i == 0)) {
            if (f >= ((float) view.getLeft())) {
                return true;
            }
            return false;
        } else if (this.mMode != 1 && (this.mMode != 2 || i != 2)) {
            return false;
        } else {
            if (f > ((float) view.getRight())) {
                return false;
            }
            return true;
        }
    }

    public boolean menuClosedSlideAllowed(float f) {
        if (this.mMode == 0) {
            if (f > 0.0f) {
                return true;
            }
            return false;
        } else if (this.mMode == 1) {
            if (f >= 0.0f) {
                return false;
            }
            return true;
        } else if (this.mMode != 2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean menuOpenSlideAllowed(float f) {
        if (this.mMode == 0) {
            if (f < 0.0f) {
                return true;
            }
            return false;
        } else if (this.mMode == 1) {
            if (f <= 0.0f) {
                return false;
            }
            return true;
        } else if (this.mMode != 2) {
            return false;
        } else {
            return true;
        }
    }

    public void drawShadow(View view, Canvas canvas) {
        if (this.mShadowDrawable != null && this.mShadowWidth > 0) {
            int left;
            if (this.mMode == 0) {
                left = view.getLeft() - this.mShadowWidth;
            } else if (this.mMode == 1) {
                left = view.getRight();
            } else if (this.mMode == 2) {
                if (this.mSecondaryShadowDrawable != null) {
                    left = view.getRight();
                    this.mSecondaryShadowDrawable.setBounds(left, 0, this.mShadowWidth + left, getHeight());
                    this.mSecondaryShadowDrawable.draw(canvas);
                }
                left = view.getLeft() - this.mShadowWidth;
            } else {
                left = 0;
            }
            this.mShadowDrawable.setBounds(left, 0, this.mShadowWidth + left, getHeight());
            this.mShadowDrawable.draw(canvas);
        }
    }

    public void drawFade(View view, Canvas canvas, float f) {
        int i = 0;
        if (this.mFadeEnabled) {
            int left;
            this.mFadePaint.setColor(Color.argb((int) ((this.mFadeDegree * 255.0f) * Math.abs(1.0f - f)), 0, 0, 0));
            if (this.mMode == 0) {
                left = view.getLeft() - getBehindWidth();
                i = view.getLeft();
            } else if (this.mMode == 1) {
                left = view.getRight();
                i = view.getRight() + getBehindWidth();
            } else if (this.mMode == 2) {
                Canvas canvas2 = canvas;
                canvas2.drawRect((float) (view.getLeft() - getBehindWidth()), 0.0f, (float) view.getLeft(), (float) getHeight(), this.mFadePaint);
                left = view.getRight();
                i = view.getRight() + getBehindWidth();
            } else {
                left = 0;
            }
            canvas.drawRect((float) left, 0.0f, (float) i, (float) getHeight(), this.mFadePaint);
        }
    }

    public void drawSelector(View view, Canvas canvas, float f) {
        if (this.mSelectorEnabled && this.mSelectorDrawable != null && this.mSelectedView != null && ((String) this.mSelectedView.getTag(R.id.selected_view)).equals("CustomViewBehindSelectedView")) {
            canvas.save();
            int width = (int) (((float) this.mSelectorDrawable.getWidth()) * f);
            int left;
            if (this.mMode == 0) {
                left = view.getLeft();
                width = left - width;
                canvas.clipRect(width, 0, left, getHeight());
                canvas.drawBitmap(this.mSelectorDrawable, (float) width, (float) getSelectorTop(), null);
            } else if (this.mMode == 1) {
                left = view.getRight();
                width += left;
                canvas.clipRect(left, 0, width, getHeight());
                canvas.drawBitmap(this.mSelectorDrawable, (float) (width - this.mSelectorDrawable.getWidth()), (float) getSelectorTop(), null);
            }
            canvas.restore();
        }
    }

    public void setSelectorEnabled(boolean z) {
        this.mSelectorEnabled = z;
    }

    public void setSelectedView(View view) {
        if (this.mSelectedView != null) {
            this.mSelectedView.setTag(R.id.selected_view, null);
            this.mSelectedView = null;
        }
        if (view != null && view.getParent() != null) {
            this.mSelectedView = view;
            this.mSelectedView.setTag(R.id.selected_view, "CustomViewBehindSelectedView");
            invalidate();
        }
    }

    private int getSelectorTop() {
        return this.mSelectedView.getTop() + ((this.mSelectedView.getHeight() - this.mSelectorDrawable.getHeight()) / 2);
    }

    public void setSelectorBitmap(Bitmap bitmap) {
        this.mSelectorDrawable = bitmap;
        refreshDrawableState();
    }
}
