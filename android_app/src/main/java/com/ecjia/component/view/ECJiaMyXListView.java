package com.ecjia.component.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;
import java.text.SimpleDateFormat;
import java.util.Date;

@TargetApi(9)
public class ECJiaMyXListView extends ListView implements OnScrollListener {
    private static final float OFFSET_RADIO = 1.8f;
    private static final int PULL_LOAD_MORE_DELTA = 50;
    private static final int SCROLLBACK_FOOTER = 1;
    private static final int SCROLLBACK_HEADER = 0;
    private static final int SCROLL_DURATION = 400;
    private int id;
    private boolean mEnablePullLoad;
    private boolean mEnablePullRefresh = true;
    private ECJiaXListViewFooter mFooterView;
    private TextView mHeaderTimeView;
    public ECJiaXListViewHeader mHeaderView;
    private RelativeLayout mHeaderViewContent;
    private int mHeaderViewHeight;
    private boolean mIsFooterReady = false;
    private float mLastY = -1.0f;
    private a mListViewListener;
    private boolean mPullLoading;
    public boolean mPullRefreshing = false;
    private int mScrollBack;
    private OnScrollListener mScrollListener;
    private Scroller mScroller;
    private int mTotalItemCount;
    private View titleView;
    private float xDistance;
    private float xLast;
    private float yDistance;
    private float yLast;

    class ECJiaMyXListView_1 implements OnGlobalLayoutListener {
        final /* synthetic */ ECJiaMyXListView a;

        ECJiaMyXListView_1(ECJiaMyXListView eCJiaMyXListView) {
            this.a = eCJiaMyXListView;
        }

        public void onGlobalLayout() {
            this.a.mHeaderViewHeight = this.a.mHeaderViewContent.getHeight();
            this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }

    class ECJiaMyXListView_2 implements OnClickListener {
        final /* synthetic */ ECJiaMyXListView a;

        ECJiaMyXListView_2(ECJiaMyXListView eCJiaMyXListView) {
            this.a = eCJiaMyXListView;
        }

        public void onClick(View view) {
            this.a.startLoadMore();
        }
    }

    class ECJiaMyXListView_3 implements OnClickListener {
        final /* synthetic */ ECJiaMyXListView a;

        ECJiaMyXListView_3(ECJiaMyXListView eCJiaMyXListView) {
            this.a = eCJiaMyXListView;
        }

        public void onClick(View view) {
            this.a.startLoadMore();
        }
    }

    class ECJiaMyXListView_4 extends Handler {
        final /* synthetic */ ECJiaMyXListView a;

        ECJiaMyXListView_4(ECJiaMyXListView eCJiaMyXListView) {
            this.a = eCJiaMyXListView;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.a.stopLoadMore();
        }
    }

    class ECJiaMyXListView_5 extends Handler {
        final /* synthetic */ ECJiaMyXListView a;

        ECJiaMyXListView_5(ECJiaMyXListView eCJiaMyXListView) {
            this.a = eCJiaMyXListView;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.a.stopRefresh();
        }
    }

    public interface a {
        void a(int i);

        void b(int i);
    }

    public interface b extends OnScrollListener {
        void a(View view);
    }

    public ECJiaMyXListView(Context context) {
        super(context);
        setOverScrollMode(2);
        initWithContext(context);
    }

    public ECJiaMyXListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOverScrollMode(2);
        initWithContext(context);
    }

    public ECJiaMyXListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOverScrollMode(2);
        initWithContext(context);
    }

    public void setTitleView(View view) {
        this.titleView = view;
        this.titleView.getBackground().setAlpha(0);
    }

    public View getTitleView() {
        return this.titleView;
    }

    public void RemoveTitleView(View view) {
        this.titleView = view;
    }

    private void initWithContext(Context context) {
        this.mScroller = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        this.mHeaderView = new ECJiaXListViewHeader(context);
        this.mHeaderViewContent = (RelativeLayout) this.mHeaderView.findViewById(R.id.xlistview_header_content);
        this.mHeaderTimeView = (TextView) this.mHeaderView.findViewById(R.id.xlistview_header_time);
        addHeaderView(this.mHeaderView);
        this.mFooterView = new ECJiaXListViewFooter(context);
        this.mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ECJiaMyXListView_1(this));
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!this.mIsFooterReady) {
            this.mIsFooterReady = true;
            addFooterView(this.mFooterView);
        }
        super.setAdapter(listAdapter);
    }

    public void setPullRefreshEnable(boolean z) {
        this.mEnablePullRefresh = z;
        if (this.mEnablePullRefresh) {
            this.mHeaderViewContent.setVisibility(0);
        } else {
            this.mHeaderViewContent.setVisibility(4);
        }
    }

    public void setPullLoadEnable(boolean z) {
        this.mEnablePullLoad = z;
        if (this.mEnablePullLoad) {
            this.mPullLoading = false;
            this.mFooterView.show();
            this.mFooterView.setState(0);
            this.mFooterView.setOnClickListener(new ECJiaMyXListView_2(this));
            return;
        }
        this.mFooterView.hide();
        this.mFooterView.setOnClickListener(null);
    }

    public void setPullLoadEnable(boolean z, boolean z2) {
        this.mEnablePullLoad = z;
        if (this.mEnablePullLoad) {
            this.mPullLoading = false;
            if (z2) {
                this.mFooterView.hide();
            } else {
                this.mFooterView.show();
            }
            this.mFooterView.setState(0);
            this.mFooterView.setOnClickListener(new ECJiaMyXListView_3(this));
            return;
        }
        this.mFooterView.hide();
        this.mFooterView.setOnClickListener(null);
    }

    public void showFooter() {
        this.mFooterView.show();
    }

    public void stopRefresh() {
        if (this.mPullRefreshing) {
            this.mPullRefreshing = false;
            resetHeaderHeight();
        }
        if (this.titleView != null) {
            this.titleView.setVisibility(0);
        }
    }

    public void stopLoadMore() {
        if (this.mPullLoading) {
            this.mPullLoading = false;
            this.mFooterView.setState(0);
        }
        this.mFooterView.setEnabled(true);
    }

    public void setRefreshTime() {
        this.mHeaderTimeView.setText(date());
    }

    public String date() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private void invokeOnScrolling() {
        if (this.mScrollListener instanceof b) {
            ((b) this.mScrollListener).a(this);
        }
    }

    private void updateHeaderHeight(float f) {
        this.mHeaderView.setVisiableHeight(((int) f) + this.mHeaderView.getVisiableHeight());
        if (this.mEnablePullRefresh && !this.mPullRefreshing) {
            if (this.mHeaderView.getVisiableHeight() > this.mHeaderViewHeight) {
                this.mHeaderView.setState(1);
            } else {
                this.mHeaderView.setState(0);
            }
            if (this.titleView != null) {
                this.titleView.setVisibility(8);
            }
        }
        setSelection(0);
    }

    private void resetHeaderHeight() {
        int visiableHeight = this.mHeaderView.getVisiableHeight();
        if (visiableHeight != 0) {
            if (!this.mPullRefreshing || visiableHeight > this.mHeaderViewHeight) {
                int i;
                if (!this.mPullRefreshing || visiableHeight <= this.mHeaderViewHeight) {
                    i = 0;
                } else {
                    i = this.mHeaderViewHeight;
                }
                this.mScrollBack = 0;
                this.mScroller.startScroll(0, visiableHeight, 0, i - visiableHeight, SCROLL_DURATION);
                invalidate();
            }
        }
    }

    private void updateFooterHeight(float f) {
        int bottomMargin = this.mFooterView.getBottomMargin() + ((int) f);
        if (this.mEnablePullLoad && !this.mPullLoading) {
            if (bottomMargin > 50) {
                this.mFooterView.setState(1);
            } else {
                this.mFooterView.setState(0);
            }
        }
        this.mFooterView.setBottomMargin(bottomMargin);
    }

    private void resetFooterHeight() {
        int bottomMargin = this.mFooterView.getBottomMargin();
        if (bottomMargin > 0) {
            this.mScrollBack = 1;
            this.mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
            invalidate();
        }
    }

    public void startLoadMore() {
        this.mPullLoading = true;
        this.mFooterView.setState(2);
        if (this.mListViewListener != null) {
            this.mListViewListener.b(this.id);
        }
        this.mFooterView.setEnabled(false);
        new ECJiaMyXListView_4(this).sendEmptyMessageDelayed(0, 4000);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mLastY == -1.0f) {
            this.mLastY = motionEvent.getRawY();
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.mLastY = motionEvent.getRawY();
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.mLastY;
                this.mLastY = motionEvent.getRawY();
                if (getFirstVisiblePosition() == 0 && (this.mHeaderView.getVisiableHeight() > 0 || rawY > 0.0f)) {
                    updateHeaderHeight(rawY / OFFSET_RADIO);
                    invokeOnScrolling();
                } else if (getLastVisiblePosition() == this.mTotalItemCount - 1 && ((this.mFooterView.getBottomMargin() > 0 || rawY <= 0.0f) && this.mEnablePullLoad)) {
                    updateFooterHeight((-rawY) / OFFSET_RADIO);
                }
                if (this.mHeaderView.getVisiableHeight() == 0 && rawY < 0.0f && this.titleView != null) {
                    this.titleView.setVisibility(0);
                    break;
                }
                break;
            default:
                this.mLastY = -1.0f;
                if (getFirstVisiblePosition() == 0) {
                    if (this.mEnablePullRefresh && this.mHeaderView.getVisiableHeight() > this.mHeaderViewHeight) {
                        this.mPullRefreshing = true;
                        this.mHeaderView.setState(2);
                        if (this.mListViewListener != null) {
                            this.mListViewListener.a(this.id);
                            new ECJiaMyXListView_5(this).sendEmptyMessageDelayed(0, 4000);
                        }
                        if (this.titleView != null) {
                            this.titleView.setVisibility(8);
                        }
                    }
                    if (!(this.titleView == null || this.mHeaderView.getVisiableHeight() != 0 || this.mPullRefreshing)) {
                        this.titleView.setVisibility(0);
                    }
                    resetHeaderHeight();
                } else if (getLastVisiblePosition() == this.mTotalItemCount - 1) {
                    if (this.mEnablePullLoad && this.mFooterView.getBottomMargin() > 50 && this.mFooterView.isEnabled()) {
                        startLoadMore();
                    }
                    resetFooterHeight();
                }
                if (this.titleView != null) {
                    if (this.mHeaderView.getVisiableHeight() <= 0) {
                        this.titleView.setVisibility(0);
                        break;
                    }
                    this.titleView.setVisibility(8);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.mScrollBack == 0) {
                this.mHeaderView.setVisiableHeight(this.mScroller.getCurrY());
            } else {
                this.mFooterView.setBottomMargin(this.mScroller.getCurrY());
            }
            postInvalidate();
            invokeOnScrolling();
        }
        super.computeScroll();
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.mTotalItemCount = i3;
        if (this.mScrollListener != null) {
            this.mScrollListener.onScroll(absListView, i, i2, i3);
        }
    }

    public void setXListViewListener(a aVar, int i) {
        this.mListViewListener = aVar;
        this.id = i;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.yDistance = 0.0f;
                this.xDistance = 0.0f;
                this.xLast = motionEvent.getX();
                this.yLast = motionEvent.getY();
                break;
            case 2:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.xDistance += Math.abs(x - this.xLast);
                this.yDistance += Math.abs(y - this.yLast);
                this.xLast = x;
                this.yLast = y;
                if (this.xDistance > this.yDistance) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
