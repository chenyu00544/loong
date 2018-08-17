package com.ecjia.component.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.Scroller;
import java.util.LinkedList;
import java.util.Queue;

public class ECJiaHorizontalListView extends AdapterView<ListAdapter> {
    protected ListAdapter mAdapter;
    public boolean mAlwaysOverrideTouch = true;
    protected int mCurrentX;
    private boolean mDataChanged = false;
    private DataSetObserver mDataObserver = new ECJiaHorizontalListView_1(this);
    private int mDisplayOffset = 0;
    private GestureDetector mGesture;
    private int mLeftViewIndex = -1;
    private int mMaxX = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    protected int mNextX;
    private OnClickListener mOnClicked;
    private OnGestureListener mOnGesture = new ECJiaHorizontalListView_3(this);
    private OnItemClickListener mOnItemClicked;
    private OnItemLongClickListener mOnItemLongClicked;
    private OnItemSelectedListener mOnItemSelected;
    private Queue<View> mRemovedViewQueue = new LinkedList();
    private int mRightViewIndex = 0;
    protected Scroller mScroller;

    class ECJiaHorizontalListView_1 extends DataSetObserver {
        final /* synthetic */ ECJiaHorizontalListView a;

        ECJiaHorizontalListView_1(ECJiaHorizontalListView eCJiaHorizontalListView) {
            this.a = eCJiaHorizontalListView;
        }

        public void onChanged() {
            synchronized (this.a) {
                this.a.mDataChanged = true;
            }
            this.a.invalidate();
            this.a.requestLayout();
        }

        public void onInvalidated() {
            this.a.reset();
            this.a.invalidate();
            this.a.requestLayout();
        }
    }

    class ECJiaHorizontalListView_2 implements Runnable {
        final /* synthetic */ ECJiaHorizontalListView a;

        ECJiaHorizontalListView_2(ECJiaHorizontalListView eCJiaHorizontalListView) {
            this.a = eCJiaHorizontalListView;
        }

        public void run() {
            this.a.requestLayout();
        }
    }

    class ECJiaHorizontalListView_3 extends SimpleOnGestureListener {
        final /* synthetic */ ECJiaHorizontalListView a;

        ECJiaHorizontalListView_3(ECJiaHorizontalListView eCJiaHorizontalListView) {
            this.a = eCJiaHorizontalListView;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return this.a.onDown(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return this.a.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            synchronized (this.a) {
                ECJiaHorizontalListView eCJiaHorizontalListView = this.a;
                eCJiaHorizontalListView.mNextX += (int) f;
            }
            this.a.requestLayout();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.a.mOnClicked != null) {
                this.a.mOnClicked.onClick(this.a);
            }
            for (int i = 0; i < this.a.getChildCount(); i++) {
                View childAt = this.a.getChildAt(i);
                if (a(motionEvent, childAt)) {
                    if (this.a.mOnItemClicked != null) {
                        this.a.mOnItemClicked.onItemClick(this.a, childAt, (this.a.mLeftViewIndex + 1) + i, this.a.mAdapter.getItemId((this.a.mLeftViewIndex + 1) + i));
                    }
                    if (this.a.mOnItemSelected != null) {
                        this.a.mOnItemSelected.onItemSelected(this.a, childAt, (this.a.mLeftViewIndex + 1) + i, this.a.mAdapter.getItemId((this.a.mLeftViewIndex + 1) + i));
                    }
                    return true;
                }
            }
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            int childCount = this.a.getChildCount();
            int i = 0;
            while (i < childCount) {
                View childAt = this.a.getChildAt(i);
                if (!a(motionEvent, childAt)) {
                    i++;
                } else if (this.a.mOnItemLongClicked != null) {
                    this.a.mOnItemLongClicked.onItemLongClick(this.a, childAt, (this.a.mLeftViewIndex + 1) + i, this.a.mAdapter.getItemId(i + (this.a.mLeftViewIndex + 1)));
                    return;
                } else {
                    return;
                }
            }
        }

        private boolean a(MotionEvent motionEvent, View view) {
            Rect rect = new Rect();
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int width = view.getWidth() + i;
            int i2 = iArr[1];
            rect.set(i, i2, width, view.getHeight() + i2);
            return rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
    }

    public ECJiaHorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    private synchronized void initView() {
        this.mLeftViewIndex = -1;
        this.mRightViewIndex = 0;
        this.mDisplayOffset = 0;
        this.mCurrentX = 0;
        this.mNextX = 0;
        this.mMaxX = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mScroller = new Scroller(getContext());
        this.mGesture = new GestureDetector(getContext(), this.mOnGesture);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelected = onItemSelectedListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClicked = onClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClicked = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClicked = onItemLongClickListener;
    }

    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    public View getSelectedView() {
        return null;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataObserver);
        }
        this.mAdapter = listAdapter;
        this.mAdapter.registerDataSetObserver(this.mDataObserver);
        reset();
    }

    private synchronized void reset() {
        initView();
        removeAllViewsInLayout();
        requestLayout();
    }

    public void setSelection(int i) {
    }

    private void addAndMeasureChild(View view, int i) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -1);
        }
        addViewInLayout(view, i, layoutParams, true);
        view.measure(MeasureSpec.makeMeasureSpec(getWidth(), Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(getHeight(), Integer.MIN_VALUE));
    }

    protected synchronized void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mAdapter != null) {
            int i5;
            if (this.mDataChanged) {
                i5 = this.mCurrentX;
                initView();
                removeAllViewsInLayout();
                this.mNextX = i5;
                this.mDataChanged = false;
            }
            if (this.mScroller.computeScrollOffset()) {
                this.mNextX = this.mScroller.getCurrX();
            }
            if (this.mNextX <= 0) {
                this.mNextX = 0;
                this.mScroller.forceFinished(true);
            }
            if (this.mNextX >= this.mMaxX) {
                this.mNextX = this.mMaxX;
                this.mScroller.forceFinished(true);
            }
            i5 = this.mCurrentX - this.mNextX;
            removeNonVisibleItems(i5);
            fillList(i5);
            positionItems(i5);
            this.mCurrentX = this.mNextX;
            if (!this.mScroller.isFinished()) {
                post(new ECJiaHorizontalListView_2(this));
            }
        }
    }

    private void fillList(int i) {
        int right;
        int i2 = 0;
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt != null) {
            right = childAt.getRight();
        } else {
            right = 0;
        }
        fillListRight(right, i);
        childAt = getChildAt(0);
        if (childAt != null) {
            i2 = childAt.getLeft();
        }
        fillListLeft(i2, i);
    }

    private void fillListRight(int i, int i2) {
        while (i + i2 < getWidth() && this.mRightViewIndex < this.mAdapter.getCount()) {
            View view = this.mAdapter.getView(this.mRightViewIndex, (View) this.mRemovedViewQueue.poll(), this);
            addAndMeasureChild(view, -1);
            i += view.getMeasuredWidth();
            if (this.mRightViewIndex == this.mAdapter.getCount() - 1) {
                this.mMaxX = (this.mCurrentX + i) - getWidth();
            }
            if (this.mMaxX < 0) {
                this.mMaxX = 0;
            }
            this.mRightViewIndex++;
        }
    }

    private void fillListLeft(int i, int i2) {
        while (i + i2 > 0 && this.mLeftViewIndex >= 0) {
            View view = this.mAdapter.getView(this.mLeftViewIndex, (View) this.mRemovedViewQueue.poll(), this);
            addAndMeasureChild(view, 0);
            i -= view.getMeasuredWidth();
            this.mLeftViewIndex--;
            this.mDisplayOffset -= view.getMeasuredWidth();
        }
    }

    private void removeNonVisibleItems(int i) {
        View childAt = getChildAt(0);
        while (childAt != null && childAt.getRight() + i <= 0) {
            this.mDisplayOffset += childAt.getMeasuredWidth();
            this.mRemovedViewQueue.offer(childAt);
            removeViewInLayout(childAt);
            this.mLeftViewIndex++;
            childAt = getChildAt(0);
        }
        childAt = getChildAt(getChildCount() - 1);
        while (childAt != null && childAt.getLeft() + i >= getWidth()) {
            this.mRemovedViewQueue.offer(childAt);
            removeViewInLayout(childAt);
            this.mRightViewIndex--;
            childAt = getChildAt(getChildCount() - 1);
        }
    }

    private void positionItems(int i) {
        if (getChildCount() > 0) {
            this.mDisplayOffset += i;
            int i2 = this.mDisplayOffset;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                int measuredWidth = childAt.getMeasuredWidth();
                childAt.layout(i2, 0, i2 + measuredWidth, childAt.getMeasuredHeight());
                i2 += childAt.getPaddingRight() + measuredWidth;
            }
        }
    }

    public synchronized void scrollTo(int i) {
        this.mScroller.startScroll(this.mNextX, 0, i - this.mNextX, 0);
        requestLayout();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent) | this.mGesture.onTouchEvent(motionEvent);
    }

    protected boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        synchronized (this) {
            this.mScroller.fling(this.mNextX, 0, (int) (-f), 0, 0, this.mMaxX, 0, 0);
        }
        requestLayout();
        return true;
    }

    protected boolean onDown(MotionEvent motionEvent) {
        this.mScroller.forceFinished(true);
        return true;
    }
}
