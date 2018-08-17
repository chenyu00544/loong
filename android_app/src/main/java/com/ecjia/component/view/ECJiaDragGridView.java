package com.ecjia.component.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import com.ecjia.a.q;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.Collections;

public class ECJiaDragGridView extends ViewGroup implements OnClickListener, OnLongClickListener, OnTouchListener {
    public static final int animT = 100;
    private String TAG;
    int chilHeight;
    int childWidth;
    protected int colCount;
    int dpi;
    protected int dragged;
    protected boolean enabled;
    protected Handler handler;
    protected float lastDelta;
    protected int lastTarget;
    protected int lastX;
    protected int lastY;
    a mDataExchangelistener;
    c mTagListener;
    protected ArrayList<Integer> newPositions;
    private OnItemClickListener onItemClickListener;
    protected b onRearrangeListener;
    int padding;
    private int screenWidth;
    int scroll;
    protected OnClickListener secondaryOnClickListener;
    protected boolean touching;
    protected Runnable updateTask;

    class ECJiaDragGridView_1 implements Runnable {
        final /* synthetic */ ECJiaDragGridView a;

        ECJiaDragGridView_1(ECJiaDragGridView eCJiaDragGridView) {
            this.a = eCJiaDragGridView;
        }

        public void run() {
            ECJiaDragGridView eCJiaDragGridView;
            if (this.a.dragged != -1) {
                if (this.a.lastY < this.a.padding * 3 && this.a.scroll > 0) {
                    eCJiaDragGridView = this.a;
                    eCJiaDragGridView.scroll -= 20;
                } else if (this.a.lastY > (this.a.getBottom() - this.a.getTop()) - (this.a.padding * 3) && this.a.scroll < this.a.getMaxScroll()) {
                    eCJiaDragGridView = this.a;
                    eCJiaDragGridView.scroll += 20;
                }
            } else if (!(this.a.lastDelta == 0.0f || this.a.touching)) {
                eCJiaDragGridView = this.a;
                eCJiaDragGridView.scroll = (int) (((float) eCJiaDragGridView.scroll) + this.a.lastDelta);
                eCJiaDragGridView = this.a;
                eCJiaDragGridView.lastDelta = (float) (((double) eCJiaDragGridView.lastDelta) * 0.9d);
                if (((double) Math.abs(this.a.lastDelta)) < 0.25d) {
                    this.a.lastDelta = 0.0f;
                }
            }
            this.a.clampScroll();
            this.a.mlayout(this.a.getLeft(), this.a.getTop(), this.a.getRight(), this.a.getBottom());
            this.a.handler.postDelayed(this, 25);
        }
    }

    public interface a {
        void a(int i, int i2);
    }

    public interface b {
        void a(int i, int i2);
    }

    public interface c {
        void a(int i);
    }

    public ECJiaDragGridView(Context context) {
        this(context, null);
    }

    public ECJiaDragGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaDragGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scroll = 0;
        this.lastDelta = 0.0f;
        this.handler = new Handler();
        this.dragged = -1;
        this.lastX = -1;
        this.lastY = -1;
        this.lastTarget = -1;
        this.enabled = true;
        this.touching = false;
        this.newPositions = new ArrayList();
        this.TAG = "DragGridView";
        this.updateTask = new ECJiaDragGridView_1(this);
        setListeners();
        this.handler.removeCallbacks(this.updateTask);
        this.handler.postAtTime(this.updateTask, SystemClock.uptimeMillis() + 500);
        setChildrenDrawingOrderEnabled(true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.dpi = displayMetrics.densityDpi;
        this.screenWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        this.chilHeight = (int) context.getResources().getDimension(R.dimen.dp_48);
        this.padding = 0;
        this.colCount = 1;
        this.childWidth = this.screenWidth - (this.padding * 2);
    }

    protected void setListeners() {
        setOnTouchListener(this);
        super.setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.secondaryOnClickListener = onClickListener;
    }

    public void addView(View view) {
        super.addView(view);
        this.newPositions.add(Integer.valueOf(-1));
    }

    public void removeViewAt(int i) {
        super.removeViewAt(i);
        this.newPositions.remove(i);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getTag().equals("tag") && this.mTagListener != null) {
                this.mTagListener.a(i5);
                q.a("mTagListener:" + i5);
            }
            if (i5 != this.dragged) {
                Point coorFromIndex = getCoorFromIndex(i5);
                childAt.layout(coorFromIndex.x, coorFromIndex.y, coorFromIndex.x + this.childWidth, coorFromIndex.y + this.chilHeight);
            }
        }
    }

    protected void mlayout(int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getTag().equals("tag") && this.mTagListener != null) {
                this.mTagListener.a(i5);
                q.a("mTagListener:" + i5);
            }
            if (i5 != this.dragged) {
                Point coorFromIndex = getCoorFromIndex(i5);
                childAt.layout(coorFromIndex.x, coorFromIndex.y, coorFromIndex.x + this.childWidth, coorFromIndex.y + this.chilHeight);
            }
        }
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.dragged == -1) {
            return i2;
        }
        if (i2 == i - 1) {
            return this.dragged;
        }
        if (i2 >= this.dragged) {
            return i2 + 1;
        }
        return i2;
    }

    public int getIndexFromCoor(int i, int i2) {
        int colOrColFromCoor = getColOrColFromCoor(i);
        int colOrRowFromCoor = getColOrRowFromCoor(this.scroll + i2);
        if (colOrColFromCoor == -1 || colOrRowFromCoor == -1) {
            return -1;
        }
        colOrColFromCoor += colOrRowFromCoor * this.colCount;
        if (colOrColFromCoor < getChildCount()) {
            return colOrColFromCoor;
        }
        return -1;
    }

    protected int getColOrColFromCoor(int i) {
        int i2 = i - this.padding;
        if (i2 < this.padding || i2 > this.padding + this.childWidth) {
            return -1;
        }
        return 0;
    }

    protected int getColOrRowFromCoor(int i) {
        int i2 = i - this.padding;
        int i3 = 0;
        while (i2 > 0) {
            if (i2 < this.chilHeight) {
                return i3;
            }
            i2 -= this.chilHeight + this.padding;
            i3++;
        }
        return -1;
    }

    protected int getTargetFromCoor(int i, int i2) {
        if (getColOrRowFromCoor(this.scroll + i2) == -1) {
            return -1;
        }
        int indexFromCoor = getIndexFromCoor(i, i2 - (this.chilHeight / 4));
        int indexFromCoor2 = getIndexFromCoor(i, (this.chilHeight / 4) + i2);
        if ((indexFromCoor == -1 && indexFromCoor2 == -1) || indexFromCoor == indexFromCoor2) {
            return -1;
        }
        if (indexFromCoor2 <= -1) {
            if (indexFromCoor > -1) {
                indexFromCoor2 = indexFromCoor + 1;
            } else {
                indexFromCoor2 = -1;
            }
        }
        if (this.dragged < indexFromCoor2) {
            return indexFromCoor2 - 1;
        }
        return indexFromCoor2;
    }

    protected Point getCoorFromIndex(int i) {
        return new Point(this.padding, (this.padding + ((this.chilHeight + this.padding) * i)) - this.scroll);
    }

    public int getIndexOf(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) == view) {
                return i;
            }
        }
        return -1;
    }

    public void onClick(View view) {
        if (this.enabled) {
            if (this.secondaryOnClickListener != null) {
                this.secondaryOnClickListener.onClick(view);
            }
            if (this.onItemClickListener != null && getLastIndex() != -1) {
                this.onItemClickListener.onItemClick(null, getChildAt(getLastIndex()), getLastIndex(), (long) (getLastIndex() / this.colCount));
            }
        }
    }

    public boolean onLongClick(View view) {
        if (!this.enabled) {
            return false;
        }
        int lastIndex = getLastIndex();
        if (getChildAt(lastIndex).getTag().equals("tag") || lastIndex == -1) {
            return false;
        }
        this.dragged = lastIndex;
        Log.i(this.TAG, "dragged:" + this.dragged);
        animateDragged();
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.enabled = true;
                this.lastX = (int) motionEvent.getX();
                this.lastY = (int) motionEvent.getY();
                this.touching = true;
                break;
            case 1:
                if (this.dragged != -1) {
                    View childAt = getChildAt(this.dragged);
                    if (this.lastTarget != -1) {
                        reorderChildren();
                    } else {
                        Point coorFromIndex = getCoorFromIndex(this.dragged);
                        childAt.layout(coorFromIndex.x, coorFromIndex.y, coorFromIndex.x + this.childWidth, coorFromIndex.y + this.childWidth);
                    }
                    childAt.clearAnimation();
                    if (childAt instanceof ImageView) {
                        ((ImageView) childAt).setAlpha(255);
                    }
                    this.lastTarget = -1;
                    this.dragged = -1;
                }
                this.touching = false;
                break;
            case 2:
                int y = this.lastY - ((int) motionEvent.getY());
                if (this.dragged != -1) {
                    int x = (int) motionEvent.getX();
                    int y2 = (int) motionEvent.getY();
                    Log.i(this.TAG, "y:" + y2);
                    Log.i(this.TAG, "x:" + x);
                    int i = y2 - (this.chilHeight / 2);
                    getChildAt(this.dragged).layout(getCoorFromIndex(this.dragged).x, i, getCoorFromIndex(this.dragged).x + this.childWidth, this.chilHeight + i);
                    x = getTargetFromCoor(x, y2);
                    if (!(this.lastTarget == x || x == -1)) {
                        Log.i(this.TAG + "交换", "交换");
                        animateGap(x);
                        this.lastTarget = x;
                    }
                } else {
                    this.scroll += y;
                    clampScroll();
                    if (Math.abs(y) > 2) {
                        this.enabled = false;
                    }
                    mlayout(getLeft(), getTop(), getRight(), getBottom());
                }
                this.lastX = (int) motionEvent.getX();
                this.lastY = (int) motionEvent.getY();
                this.lastDelta = (float) y;
                break;
        }
        if (this.dragged != -1) {
            return true;
        }
        return false;
    }

    protected void animateDragged() {
        View childAt = getChildAt(this.dragged);
        int i = getCoorFromIndex(this.dragged).x;
        int i2 = getCoorFromIndex(this.dragged).y;
        childAt.layout(i, i2, this.childWidth + i, this.chilHeight + i2);
        Animation animationSet = new AnimationSet(true);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.7f);
        alphaAnimation.setDuration(100);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        childAt.clearAnimation();
        childAt.startAnimation(animationSet);
    }

    protected void animateGap(int i) {
        int i2 = 0;
        while (i2 < getChildCount()) {
            View childAt = getChildAt(i2);
            if (i2 != this.dragged) {
                int i3;
                int intValue;
                if (this.dragged < i && i2 >= this.dragged + 1 && i2 <= i) {
                    i3 = i2 - 1;
                } else if (i >= this.dragged || i2 < i || i2 >= this.dragged) {
                    i3 = i2;
                } else {
                    i3 = i2 + 1;
                }
                if (((Integer) this.newPositions.get(i2)).intValue() != -1) {
                    intValue = ((Integer) this.newPositions.get(i2)).intValue();
                } else {
                    intValue = i2;
                }
                if (intValue != i3) {
                    Point coorFromIndex = getCoorFromIndex(intValue);
                    Point coorFromIndex2 = getCoorFromIndex(i3);
                    Point point = new Point(coorFromIndex.x - childAt.getLeft(), coorFromIndex.y - childAt.getTop());
                    Point point2 = new Point(coorFromIndex2.x - childAt.getLeft(), coorFromIndex2.y - childAt.getTop());
                    Animation translateAnimation = new TranslateAnimation(0, (float) point.x, 0, (float) point2.x, 0, (float) point.y, 0, (float) point2.y);
                    translateAnimation.setDuration(100);
                    translateAnimation.setFillEnabled(true);
                    translateAnimation.setFillAfter(true);
                    childAt.clearAnimation();
                    childAt.startAnimation(translateAnimation);
                    this.newPositions.set(i2, Integer.valueOf(i3));
                }
            }
            i2++;
        }
    }

    protected void reorderChildren() {
        int i = 0;
        if (this.onRearrangeListener != null) {
            this.onRearrangeListener.a(this.dragged, this.lastTarget);
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            getChildAt(i2).clearAnimation();
            arrayList.add(getChildAt(i2));
        }
        removeAllViews();
        while (this.dragged != this.lastTarget) {
            if (this.lastTarget == arrayList.size()) {
                arrayList.add(arrayList.remove(this.dragged));
                this.dragged = this.lastTarget;
            } else if (this.dragged < this.lastTarget) {
                Collections.swap(arrayList, this.dragged, this.dragged + 1);
                this.dragged++;
            } else if (this.dragged > this.lastTarget) {
                Collections.swap(arrayList, this.dragged, this.dragged - 1);
                this.dragged--;
            }
        }
        while (i < arrayList.size()) {
            this.newPositions.set(i, Integer.valueOf(-1));
            addView((View) arrayList.get(i));
            i++;
        }
        mlayout(getLeft(), getTop(), getRight(), getBottom());
        q.a("dragged:" + this.dragged + "   lastTarget:" + this.lastTarget);
        if (this.mDataExchangelistener != null) {
            this.mDataExchangelistener.a(this.dragged, this.lastTarget);
        }
    }

    public void scrollToTop() {
        this.scroll = 0;
    }

    public void scrollToBottom() {
        this.scroll = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        clampScroll();
    }

    protected void clampScroll() {
        int max = Math.max(getMaxScroll(), 0);
        if (this.scroll < (-200)) {
            this.scroll = -200;
            this.lastDelta = 0.0f;
        } else if (this.scroll > max + 200) {
            this.scroll = max + 200;
            this.lastDelta = 0.0f;
        } else if (this.scroll < 0) {
            if (this.scroll >= (-3)) {
                this.scroll = 0;
            } else if (!this.touching) {
                this.scroll -= this.scroll / 3;
            }
        } else if (this.scroll <= max) {
        } else {
            if (this.scroll <= max + 3) {
                this.scroll = max;
            } else if (!this.touching) {
                this.scroll = ((max - this.scroll) / 3) + this.scroll;
            }
        }
    }

    protected int getMaxScroll() {
        int childCount = getChildCount();
        return (((childCount + 1) * this.padding) + (this.chilHeight * childCount)) - getHeight();
    }

    public int getLastIndex() {
        return getIndexFromCoor(this.lastX, this.lastY);
    }

    public void setOnRearrangeListener(b bVar) {
        this.onRearrangeListener = bVar;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnDataExchangeListener(a aVar) {
        this.mDataExchangelistener = aVar;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(i, i2);
        }
    }

    public void setOnTagPositionListener(c cVar) {
        this.mTagListener = cVar;
    }
}
