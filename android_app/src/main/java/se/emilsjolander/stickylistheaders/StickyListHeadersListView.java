package se.emilsjolander.stickylistheaders;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class StickyListHeadersListView extends FrameLayout {
    private a mAdapter;
    private boolean mAreHeadersSticky;
    private boolean mClippingToPadding;
    private a mDataSetObserver;
    private Drawable mDivider;
    private int mDividerHeight;
    private float mDownY;
    private View mHeader;
    private Long mHeaderId;
    private Integer mHeaderOffset;
    private boolean mHeaderOwnsTouch;
    private Integer mHeaderPosition;
    private boolean mIsDrawingListUnderStickyHeader;
    private i mList;
    private c mOnHeaderClickListener;
    private OnScrollListener mOnScrollListenerDelegate;
    private d mOnStickyHeaderChangedListener;
    private e mOnStickyHeaderOffsetChangedListener;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mStickyHeaderTopOffset;
    private float mTouchSlop;

    class StickyListHeadersListView_1 implements OnClickListener {
        final /* synthetic */ StickyListHeadersListView a;

        StickyListHeadersListView_1(StickyListHeadersListView stickyListHeadersListView) {
            this.a = stickyListHeadersListView;
        }

        public void onClick(View view) {
            this.a.mOnHeaderClickListener.a(this.a, this.a.mHeader, this.a.mHeaderPosition.intValue(), this.a.mHeaderId.longValue(), true);
        }
    }

    class StickyListHeadersListView_2 implements OnClickListener {
        final /* synthetic */ StickyListHeadersListView a;

        StickyListHeadersListView_2(StickyListHeadersListView stickyListHeadersListView) {
            this.a = stickyListHeadersListView;
        }

        public void onClick(View view) {
            this.a.mOnHeaderClickListener.a(this.a, this.a.mHeader, this.a.mHeaderPosition.intValue(), this.a.mHeaderId.longValue(), true);
        }
    }

    private class a extends DataSetObserver {
        final /* synthetic */ StickyListHeadersListView a;

        private a(StickyListHeadersListView stickyListHeadersListView) {
            this.a = stickyListHeadersListView;
        }

        public void onChanged() {
            this.a.clearHeader();
        }

        public void onInvalidated() {
            this.a.clearHeader();
        }
    }

    private class b implements a {
        final /* synthetic */ StickyListHeadersListView a;

        private b(StickyListHeadersListView stickyListHeadersListView) {
            this.a = stickyListHeadersListView;
        }

        public void a(View view, int i, long j) {
            this.a.mOnHeaderClickListener.a(this.a, view, i, j, false);
        }
    }

    public interface c {
        void a(StickyListHeadersListView stickyListHeadersListView, View view, int i, long j, boolean z);
    }

    public interface d {
        void a(StickyListHeadersListView stickyListHeadersListView, View view, int i, long j);
    }

    public interface e {
        void a(StickyListHeadersListView stickyListHeadersListView, View view, int i);
    }

    private class f implements OnScrollListener {
        final /* synthetic */ StickyListHeadersListView a;

        private f(StickyListHeadersListView stickyListHeadersListView) {
            this.a = stickyListHeadersListView;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.a.mOnScrollListenerDelegate != null) {
                this.a.mOnScrollListenerDelegate.onScroll(absListView, i, i2, i3);
            }
            this.a.updateOrClearHeader(this.a.mList.a());
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (this.a.mOnScrollListenerDelegate != null) {
                this.a.mOnScrollListenerDelegate.onScrollStateChanged(absListView, i);
            }
        }
    }

    private class g implements a {
        final /* synthetic */ StickyListHeadersListView a;

        private g(StickyListHeadersListView stickyListHeadersListView) {
            this.a = stickyListHeadersListView;
        }

        public void a(Canvas canvas) {
            if (VERSION.SDK_INT < 8) {
                this.a.updateOrClearHeader(this.a.mList.a());
            }
            if (this.a.mHeader == null) {
                return;
            }
            if (this.a.mClippingToPadding) {
                canvas.save();
                canvas.clipRect(0, this.a.mPaddingTop, this.a.getRight(), this.a.getBottom());
                this.a.drawChild(canvas, this.a.mHeader, 0);
                canvas.restore();
                return;
            }
            this.a.drawChild(canvas, this.a.mHeader, 0);
        }
    }

    public StickyListHeadersListView(Context context) {
        this(context, null);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, se.emilsjolander.stickylistheaders.f.a.stickyListHeadersListViewStyle);
    }

    @TargetApi(11)
    public StickyListHeadersListView(Context context, AttributeSet attributeSet, int i) {
        boolean z = true;
        super(context, attributeSet, i);
        this.mAreHeadersSticky = true;
        this.mClippingToPadding = true;
        this.mIsDrawingListUnderStickyHeader = true;
        this.mStickyHeaderTopOffset = 0;
        this.mPaddingLeft = 0;
        this.mPaddingTop = 0;
        this.mPaddingRight = 0;
        this.mPaddingBottom = 0;
        this.mTouchSlop = (float) ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mList = new i(context);
        this.mDivider = this.mList.getDivider();
        this.mDividerHeight = this.mList.getDividerHeight();
        this.mList.setDivider(null);
        this.mList.setDividerHeight(0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView, i, 0);
            try {
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_padding, 0);
                this.mPaddingLeft = obtainStyledAttributes.getDimensionPixelSize(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_paddingLeft, dimensionPixelSize);
                this.mPaddingTop = obtainStyledAttributes.getDimensionPixelSize(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_paddingTop, dimensionPixelSize);
                this.mPaddingRight = obtainStyledAttributes.getDimensionPixelSize(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_paddingRight, dimensionPixelSize);
                this.mPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_paddingBottom, dimensionPixelSize);
                setPadding(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
                this.mClippingToPadding = obtainStyledAttributes.getBoolean(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_clipToPadding, true);
                super.setClipToPadding(true);
                this.mList.setClipToPadding(this.mClippingToPadding);
                int i2 = obtainStyledAttributes.getInt(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_scrollbars, 512);
                this.mList.setVerticalScrollBarEnabled((i2 & 512) != 0);
                i iVar = this.mList;
                if ((i2 & 256) == 0) {
                    z = false;
                }
                iVar.setHorizontalScrollBarEnabled(z);
                if (VERSION.SDK_INT >= 9) {
                    this.mList.setOverScrollMode(obtainStyledAttributes.getInt(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_overScrollMode, 0));
                }
                this.mList.setFadingEdgeLength(obtainStyledAttributes.getDimensionPixelSize(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_fadingEdgeLength, this.mList.getVerticalFadingEdgeLength()));
                int i3 = obtainStyledAttributes.getInt(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_requiresFadingEdge, 0);
                if (i3 == 4096) {
                    this.mList.setVerticalFadingEdgeEnabled(false);
                    this.mList.setHorizontalFadingEdgeEnabled(true);
                } else if (i3 == 8192) {
                    this.mList.setVerticalFadingEdgeEnabled(true);
                    this.mList.setHorizontalFadingEdgeEnabled(false);
                } else {
                    this.mList.setVerticalFadingEdgeEnabled(false);
                    this.mList.setHorizontalFadingEdgeEnabled(false);
                }
                this.mList.setCacheColorHint(obtainStyledAttributes.getColor(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_cacheColorHint, this.mList.getCacheColorHint()));
                if (VERSION.SDK_INT >= 11) {
                    this.mList.setChoiceMode(obtainStyledAttributes.getInt(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_choiceMode, this.mList.getChoiceMode()));
                }
                this.mList.setDrawSelectorOnTop(obtainStyledAttributes.getBoolean(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_drawSelectorOnTop, false));
                this.mList.setFastScrollEnabled(obtainStyledAttributes.getBoolean(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_fastScrollEnabled, this.mList.isFastScrollEnabled()));
                if (VERSION.SDK_INT >= 11) {
                    this.mList.setFastScrollAlwaysVisible(obtainStyledAttributes.getBoolean(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_fastScrollAlwaysVisible, this.mList.isFastScrollAlwaysVisible()));
                }
                this.mList.setScrollBarStyle(obtainStyledAttributes.getInt(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_scrollbarStyle, 0));
                if (obtainStyledAttributes.hasValue(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_listSelector)) {
                    this.mList.setSelector(obtainStyledAttributes.getDrawable(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_listSelector));
                }
                this.mList.setScrollingCacheEnabled(obtainStyledAttributes.getBoolean(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_scrollingCache, this.mList.isScrollingCacheEnabled()));
                if (obtainStyledAttributes.hasValue(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_divider)) {
                    this.mDivider = obtainStyledAttributes.getDrawable(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_divider);
                }
                this.mList.setStackFromBottom(obtainStyledAttributes.getBoolean(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_stackFromBottom, false));
                this.mDividerHeight = obtainStyledAttributes.getDimensionPixelSize(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_dividerHeight, this.mDividerHeight);
                this.mList.setTranscriptMode(obtainStyledAttributes.getInt(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_android_transcriptMode, 0));
                this.mAreHeadersSticky = obtainStyledAttributes.getBoolean(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_hasStickyHeaders, true);
                this.mIsDrawingListUnderStickyHeader = obtainStyledAttributes.getBoolean(se.emilsjolander.stickylistheaders.f.b.StickyListHeadersListView_isDrawingListUnderStickyHeader, true);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.mList.a(new g());
        this.mList.setOnScrollListener(new f());
        addView(this.mList);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureHeader(this.mHeader);
    }

    private void ensureHeaderHasCorrectLayoutParams(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        } else if (layoutParams.height == -1 || layoutParams.width == -2) {
            layoutParams.height = -2;
            layoutParams.width = -1;
            view.setLayoutParams(layoutParams);
        }
    }

    private void measureHeader(View view) {
        if (view != null) {
            measureChild(view, MeasureSpec.makeMeasureSpec((getMeasuredWidth() - this.mPaddingLeft) - this.mPaddingRight, 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mList.layout(0, 0, this.mList.getMeasuredWidth(), getHeight());
        if (this.mHeader != null) {
            int i5 = ((MarginLayoutParams) this.mHeader.getLayoutParams()).topMargin;
            this.mHeader.layout(this.mPaddingLeft, i5, this.mHeader.getMeasuredWidth() + this.mPaddingLeft, this.mHeader.getMeasuredHeight() + i5);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.mList.getVisibility() == 0 || this.mList.getAnimation() != null) {
            drawChild(canvas, this.mList, 0);
        }
    }

    private void clearHeader() {
        if (this.mHeader != null) {
            removeView(this.mHeader);
            this.mHeader = null;
            this.mHeaderId = null;
            this.mHeaderPosition = null;
            this.mHeaderOffset = null;
            this.mList.a(0);
            updateHeaderVisibilities();
        }
    }

    private void updateOrClearHeader(int i) {
        int i2 = 0;
        int count = this.mAdapter == null ? 0 : this.mAdapter.getCount();
        if (count != 0 && this.mAreHeadersSticky) {
            int headerViewsCount = i - this.mList.getHeaderViewsCount();
            if (this.mList.getChildCount() > 0 && this.mList.getChildAt(0).getBottom() < stickyHeaderTop()) {
                headerViewsCount++;
            }
            int i3 = this.mList.getChildCount() != 0 ? 1 : 0;
            int i4;
            if (i3 == 0 || this.mList.getFirstVisiblePosition() != 0 || this.mList.getChildAt(0).getTop() < stickyHeaderTop()) {
                i4 = 0;
            } else {
                i4 = 1;
            }
            if (headerViewsCount > count - 1 || headerViewsCount < 0) {
                i2 = 1;
            }
            if (i3 != 0 && r1 == 0 && r3 == 0) {
                updateHeader(headerViewsCount);
            } else {
                clearHeader();
            }
        }
    }

    private void updateHeader(int i) {
        int min;
        if (this.mHeaderPosition == null || this.mHeaderPosition.intValue() != i) {
            this.mHeaderPosition = Integer.valueOf(i);
            long a = this.mAdapter.a(i);
            if (this.mHeaderId == null || this.mHeaderId.longValue() != a) {
                this.mHeaderId = Long.valueOf(a);
                View a2 = this.mAdapter.a(this.mHeaderPosition.intValue(), this.mHeader, this);
                if (this.mHeader != a2) {
                    if (a2 == null) {
                        throw new NullPointerException("header may not be null");
                    }
                    swapHeader(a2);
                }
                ensureHeaderHasCorrectLayoutParams(this.mHeader);
                measureHeader(this.mHeader);
                if (this.mOnStickyHeaderChangedListener != null) {
                    this.mOnStickyHeaderChangedListener.a(this, this.mHeader, i, this.mHeaderId.longValue());
                }
                this.mHeaderOffset = null;
            }
        }
        int stickyHeaderTop = stickyHeaderTop();
        for (int i2 = 0; i2 < this.mList.getChildCount(); i2++) {
            View childAt = this.mList.getChildAt(i2);
            Object obj = ((childAt instanceof WrapperView) && ((WrapperView) childAt).hasHeader()) ? 1 : null;
            boolean a3 = this.mList.a(childAt);
            if (childAt.getTop() >= stickyHeaderTop() && (obj != null || a3)) {
                min = Math.min(childAt.getTop() - this.mHeader.getMeasuredHeight(), stickyHeaderTop);
                break;
            }
        }
        min = stickyHeaderTop;
        setHeaderOffet(min);
        if (!this.mIsDrawingListUnderStickyHeader) {
            this.mList.a(this.mHeader.getMeasuredHeight() + this.mHeaderOffset.intValue());
        }
        updateHeaderVisibilities();
    }

    private void swapHeader(View view) {
        if (this.mHeader != null) {
            removeView(this.mHeader);
        }
        this.mHeader = view;
        addView(this.mHeader);
        if (this.mOnHeaderClickListener != null) {
            this.mHeader.setOnClickListener(new StickyListHeadersListView_1(this));
        }
        this.mHeader.setClickable(true);
    }

    private void updateHeaderVisibilities() {
        int stickyHeaderTop = stickyHeaderTop();
        int childCount = this.mList.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mList.getChildAt(i);
            if (childAt instanceof WrapperView) {
                WrapperView wrapperView = (WrapperView) childAt;
                if (wrapperView.hasHeader()) {
                    View view = wrapperView.mHeader;
                    if (wrapperView.getTop() < stickyHeaderTop) {
                        if (view.getVisibility() != 4) {
                            view.setVisibility(4);
                        }
                    } else if (view.getVisibility() != 0) {
                        view.setVisibility(0);
                    }
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void setHeaderOffet(int i) {
        if (this.mHeaderOffset == null || this.mHeaderOffset.intValue() != i) {
            this.mHeaderOffset = Integer.valueOf(i);
            if (VERSION.SDK_INT >= 11) {
                this.mHeader.setTranslationY((float) this.mHeaderOffset.intValue());
            } else {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mHeader.getLayoutParams();
                marginLayoutParams.topMargin = this.mHeaderOffset.intValue();
                this.mHeader.setLayoutParams(marginLayoutParams);
            }
            if (this.mOnStickyHeaderOffsetChangedListener != null) {
                this.mOnStickyHeaderOffsetChangedListener.a(this, this.mHeader, -this.mHeaderOffset.intValue());
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 0) {
            this.mDownY = motionEvent.getY();
            boolean z = this.mHeader != null && this.mDownY <= ((float) (this.mHeader.getHeight() + this.mHeaderOffset.intValue()));
            this.mHeaderOwnsTouch = z;
        }
        if (!this.mHeaderOwnsTouch) {
            return this.mList.dispatchTouchEvent(motionEvent);
        }
        if (this.mHeader != null && Math.abs(this.mDownY - motionEvent.getY()) <= this.mTouchSlop) {
            return this.mHeader.dispatchTouchEvent(motionEvent);
        }
        if (this.mHeader != null) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            this.mHeader.dispatchTouchEvent(obtain);
            obtain.recycle();
        }
        MotionEvent obtain2 = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX(), this.mDownY, motionEvent.getMetaState());
        obtain2.setAction(0);
        z = this.mList.dispatchTouchEvent(obtain2);
        obtain2.recycle();
        this.mHeaderOwnsTouch = false;
        return z;
    }

    private boolean isStartOfSection(int i) {
        return i == 0 || this.mAdapter.a(i) != this.mAdapter.a(i - 1);
    }

    public int getHeaderOverlap(int i) {
        if (isStartOfSection(Math.max(0, i - getHeaderViewsCount()))) {
            return 0;
        }
        View a = this.mAdapter.a(i, null, this.mList);
        if (a == null) {
            throw new NullPointerException("header may not be null");
        }
        ensureHeaderHasCorrectLayoutParams(a);
        measureHeader(a);
        return a.getMeasuredHeight();
    }

    private int stickyHeaderTop() {
        return (this.mClippingToPadding ? this.mPaddingTop : 0) + this.mStickyHeaderTopOffset;
    }

    public void setAreHeadersSticky(boolean z) {
        this.mAreHeadersSticky = z;
        if (z) {
            updateOrClearHeader(this.mList.a());
        } else {
            clearHeader();
        }
        this.mList.invalidate();
    }

    public boolean areHeadersSticky() {
        return this.mAreHeadersSticky;
    }

    @Deprecated
    public boolean getAreHeadersSticky() {
        return areHeadersSticky();
    }

    public void setStickyHeaderTopOffset(int i) {
        this.mStickyHeaderTopOffset = i;
        updateOrClearHeader(this.mList.a());
    }

    public int getStickyHeaderTopOffset() {
        return this.mStickyHeaderTopOffset;
    }

    public void setDrawingListUnderStickyHeader(boolean z) {
        this.mIsDrawingListUnderStickyHeader = z;
        this.mList.a(0);
    }

    public boolean isDrawingListUnderStickyHeader() {
        return this.mIsDrawingListUnderStickyHeader;
    }

    public void setOnHeaderClickListener(c cVar) {
        this.mOnHeaderClickListener = cVar;
        if (this.mAdapter == null) {
            return;
        }
        if (this.mOnHeaderClickListener != null) {
            this.mAdapter.a(new b());
            if (this.mHeader != null) {
                this.mHeader.setOnClickListener(new StickyListHeadersListView_2(this));
                return;
            }
            return;
        }
        this.mAdapter.a(null);
    }

    public void setOnStickyHeaderOffsetChangedListener(e eVar) {
        this.mOnStickyHeaderOffsetChangedListener = eVar;
    }

    public void setOnStickyHeaderChangedListener(d dVar) {
        this.mOnStickyHeaderChangedListener = dVar;
    }

    public View getListChildAt(int i) {
        return this.mList.getChildAt(i);
    }

    public int getListChildCount() {
        return this.mList.getChildCount();
    }

    public ListView getWrappedList() {
        return this.mList;
    }

    private boolean requireSdkVersion(int i) {
        if (VERSION.SDK_INT >= i) {
            return true;
        }
        Log.e("StickyListHeaders", "Api lvl must be at least " + i + " to call this method");
        return false;
    }

    public void setAdapter(h hVar) {
        if (hVar == null) {
            if (this.mAdapter instanceof g) {
                ((g) this.mAdapter).b = null;
            }
            if (this.mAdapter != null) {
                this.mAdapter.a = null;
            }
            this.mList.setAdapter(null);
            clearHeader();
            return;
        }
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        }
        if (hVar instanceof SectionIndexer) {
            this.mAdapter = new g(getContext(), hVar);
        } else {
            this.mAdapter = new a(getContext(), hVar);
        }
        this.mDataSetObserver = new a();
        this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
        if (this.mOnHeaderClickListener != null) {
            this.mAdapter.a(new b());
        } else {
            this.mAdapter.a(null);
        }
        this.mAdapter.a(this.mDivider, this.mDividerHeight);
        this.mList.setAdapter(this.mAdapter);
        clearHeader();
    }

    public h getAdapter() {
        return this.mAdapter == null ? null : this.mAdapter.a;
    }

    public void setDivider(Drawable drawable) {
        this.mDivider = drawable;
        if (this.mAdapter != null) {
            this.mAdapter.a(this.mDivider, this.mDividerHeight);
        }
    }

    public void setDividerHeight(int i) {
        this.mDividerHeight = i;
        if (this.mAdapter != null) {
            this.mAdapter.a(this.mDivider, this.mDividerHeight);
        }
    }

    public Drawable getDivider() {
        return this.mDivider;
    }

    public int getDividerHeight() {
        return this.mDividerHeight;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListenerDelegate = onScrollListener;
    }

    public void setOnTouchListener(final OnTouchListener onTouchListener) {
        if (onTouchListener != null) {
            this.mList.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ StickyListHeadersListView b;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return onTouchListener.onTouch(this.b, motionEvent);
                }
            });
        } else {
            this.mList.setOnTouchListener(null);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mList.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mList.setOnItemLongClickListener(onItemLongClickListener);
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        this.mList.addHeaderView(view, obj, z);
    }

    public void addHeaderView(View view) {
        this.mList.addHeaderView(view);
    }

    public void removeHeaderView(View view) {
        this.mList.removeHeaderView(view);
    }

    public int getHeaderViewsCount() {
        return this.mList.getHeaderViewsCount();
    }

    public void addFooterView(View view, Object obj, boolean z) {
        this.mList.addFooterView(view, obj, z);
    }

    public void addFooterView(View view) {
        this.mList.addFooterView(view);
    }

    public void removeFooterView(View view) {
        this.mList.removeFooterView(view);
    }

    public int getFooterViewsCount() {
        return this.mList.getFooterViewsCount();
    }

    public void setEmptyView(View view) {
        this.mList.setEmptyView(view);
    }

    public View getEmptyView() {
        return this.mList.getEmptyView();
    }

    public boolean isVerticalScrollBarEnabled() {
        return this.mList.isVerticalScrollBarEnabled();
    }

    public boolean isHorizontalScrollBarEnabled() {
        return this.mList.isHorizontalScrollBarEnabled();
    }

    public void setVerticalScrollBarEnabled(boolean z) {
        this.mList.setVerticalScrollBarEnabled(z);
    }

    public void setHorizontalScrollBarEnabled(boolean z) {
        this.mList.setHorizontalScrollBarEnabled(z);
    }

    @TargetApi(9)
    public int getOverScrollMode() {
        if (requireSdkVersion(9)) {
            return this.mList.getOverScrollMode();
        }
        return 0;
    }

    @TargetApi(9)
    public void setOverScrollMode(int i) {
        if (requireSdkVersion(9) && this.mList != null) {
            this.mList.setOverScrollMode(i);
        }
    }

    @TargetApi(8)
    public void smoothScrollBy(int i, int i2) {
        if (requireSdkVersion(8)) {
            this.mList.smoothScrollBy(i, i2);
        }
    }

    @TargetApi(11)
    public void smoothScrollByOffset(int i) {
        if (requireSdkVersion(11)) {
            this.mList.smoothScrollByOffset(i);
        }
    }

    @SuppressLint({"NewApi"})
    @TargetApi(8)
    public void smoothScrollToPosition(int i) {
        int i2 = 0;
        if (!requireSdkVersion(8)) {
            return;
        }
        if (VERSION.SDK_INT < 11) {
            this.mList.smoothScrollToPosition(i);
            return;
        }
        int headerOverlap = this.mAdapter == null ? 0 : getHeaderOverlap(i);
        if (!this.mClippingToPadding) {
            i2 = this.mPaddingTop;
        }
        this.mList.smoothScrollToPositionFromTop(i, headerOverlap - i2);
    }

    @TargetApi(8)
    public void smoothScrollToPosition(int i, int i2) {
        if (requireSdkVersion(8)) {
            this.mList.smoothScrollToPosition(i, i2);
        }
    }

    @TargetApi(11)
    public void smoothScrollToPositionFromTop(int i, int i2) {
        int i3 = 0;
        if (requireSdkVersion(11)) {
            int headerOverlap = (this.mAdapter == null ? 0 : getHeaderOverlap(i)) + i2;
            if (!this.mClippingToPadding) {
                i3 = this.mPaddingTop;
            }
            this.mList.smoothScrollToPositionFromTop(i, headerOverlap - i3);
        }
    }

    @TargetApi(11)
    public void smoothScrollToPositionFromTop(int i, int i2, int i3) {
        int i4 = 0;
        if (requireSdkVersion(11)) {
            int headerOverlap = (this.mAdapter == null ? 0 : getHeaderOverlap(i)) + i2;
            if (!this.mClippingToPadding) {
                i4 = this.mPaddingTop;
            }
            this.mList.smoothScrollToPositionFromTop(i, headerOverlap - i4, i3);
        }
    }

    public void setSelection(int i) {
        setSelectionFromTop(i, 0);
    }

    public void setSelectionAfterHeaderView() {
        this.mList.setSelectionAfterHeaderView();
    }

    public void setSelectionFromTop(int i, int i2) {
        int i3 = 0;
        int headerOverlap = (this.mAdapter == null ? 0 : getHeaderOverlap(i)) + i2;
        if (!this.mClippingToPadding) {
            i3 = this.mPaddingTop;
        }
        this.mList.setSelectionFromTop(i, headerOverlap - i3);
    }

    public void setSelector(Drawable drawable) {
        this.mList.setSelector(drawable);
    }

    public void setSelector(int i) {
        this.mList.setSelector(i);
    }

    public int getFirstVisiblePosition() {
        return this.mList.getFirstVisiblePosition();
    }

    public int getLastVisiblePosition() {
        return this.mList.getLastVisiblePosition();
    }

    @TargetApi(11)
    public void setChoiceMode(int i) {
        this.mList.setChoiceMode(i);
    }

    @TargetApi(11)
    public void setItemChecked(int i, boolean z) {
        this.mList.setItemChecked(i, z);
    }

    @TargetApi(11)
    public int getCheckedItemCount() {
        if (requireSdkVersion(11)) {
            return this.mList.getCheckedItemCount();
        }
        return 0;
    }

    @TargetApi(8)
    public long[] getCheckedItemIds() {
        if (requireSdkVersion(8)) {
            return this.mList.getCheckedItemIds();
        }
        return null;
    }

    @TargetApi(11)
    public int getCheckedItemPosition() {
        return this.mList.getCheckedItemPosition();
    }

    @TargetApi(11)
    public SparseBooleanArray getCheckedItemPositions() {
        return this.mList.getCheckedItemPositions();
    }

    public int getCount() {
        return this.mList.getCount();
    }

    public Object getItemAtPosition(int i) {
        return this.mList.getItemAtPosition(i);
    }

    public long getItemIdAtPosition(int i) {
        return this.mList.getItemIdAtPosition(i);
    }

    public void setOnCreateContextMenuListener(OnCreateContextMenuListener onCreateContextMenuListener) {
        this.mList.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }

    public boolean showContextMenu() {
        return this.mList.showContextMenu();
    }

    public void invalidateViews() {
        this.mList.invalidateViews();
    }

    public void setClipToPadding(boolean z) {
        if (this.mList != null) {
            this.mList.setClipToPadding(z);
        }
        this.mClippingToPadding = z;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
        if (this.mList != null) {
            this.mList.setPadding(i, i2, i3, i4);
        }
        super.setPadding(0, 0, 0, 0);
        requestLayout();
    }

    protected void recomputePadding() {
        setPadding(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public void setFastScrollEnabled(boolean z) {
        this.mList.setFastScrollEnabled(z);
    }

    @TargetApi(11)
    public void setFastScrollAlwaysVisible(boolean z) {
        if (requireSdkVersion(11)) {
            this.mList.setFastScrollAlwaysVisible(z);
        }
    }

    @TargetApi(11)
    public boolean isFastScrollAlwaysVisible() {
        if (VERSION.SDK_INT < 11) {
            return false;
        }
        return this.mList.isFastScrollAlwaysVisible();
    }

    public void setScrollBarStyle(int i) {
        this.mList.setScrollBarStyle(i);
    }

    public int getScrollBarStyle() {
        return this.mList.getScrollBarStyle();
    }

    public int getPositionForView(View view) {
        return this.mList.getPositionForView(view);
    }

    @TargetApi(11)
    public void setMultiChoiceModeListener(MultiChoiceModeListener multiChoiceModeListener) {
        if (requireSdkVersion(11)) {
            this.mList.setMultiChoiceModeListener(multiChoiceModeListener);
        }
    }

    public Parcelable onSaveInstanceState() {
        if (super.onSaveInstanceState() == BaseSavedState.EMPTY_STATE) {
            return this.mList.onSaveInstanceState();
        }
        throw new IllegalStateException("Handling non empty state of parent class is not implemented");
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(BaseSavedState.EMPTY_STATE);
        this.mList.onRestoreInstanceState(parcelable);
    }

    @TargetApi(14)
    public boolean canScrollVertically(int i) {
        return this.mList.canScrollVertically(i);
    }

    public void setTranscriptMode(int i) {
        this.mList.setTranscriptMode(i);
    }

    public void setBlockLayoutChildren(boolean z) {
        this.mList.a(z);
    }

    public void setStackFromBottom(boolean z) {
        this.mList.setStackFromBottom(z);
    }

    public boolean isStackFromBottom() {
        return this.mList.isStackFromBottom();
    }
}
