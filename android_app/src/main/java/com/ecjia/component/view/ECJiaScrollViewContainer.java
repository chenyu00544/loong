package com.ecjia.component.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import java.util.Timer;
import java.util.TimerTask;

public class ECJiaScrollViewContainer extends RelativeLayout {
    public static final int AUTO_DOWN = 1;
    public static final int AUTO_UP = 0;
    public static final int DONE = 2;
    public static final float SPEED = 6.5f;
    private View bottomView;
    private OnTouchListener bottomViewTouchListener = new ECJiaScrollViewContainer_3(this);
    private boolean canPullDown;
    private boolean canPullUp;
    private Handler handler = new ECJiaScrollViewContainer_1(this);
    private boolean isMeasured = false;
    private int mCurrentViewIndex = 0;
    private int mEvents;
    private float mLastY;
    private float mMoveLen;
    b mTextChangeListener;
    private a mTimer;
    private int mViewHeight;
    private int mViewWidth;
    Resources resource;
    public int state = 2;
    private View topView;
    private OnTouchListener topViewTouchListener = new ECJiaScrollViewContainer_2(this);
    private VelocityTracker vt;

    class ECJiaScrollViewContainer_1 extends Handler {
        final /* synthetic */ ECJiaScrollViewContainer a;

        ECJiaScrollViewContainer_1(ECJiaScrollViewContainer eCJiaScrollViewContainer) {
            this.a = eCJiaScrollViewContainer;
        }

        public void handleMessage(Message message) {
            if (this.a.mMoveLen != 0.0f) {
                if (this.a.state == 0) {
                    if (this.a.mTextChangeListener != null) {
                        this.a.mTextChangeListener.a(SC_STATUS.UP);
                    }
                    this.a.mMoveLen = this.a.mMoveLen - ECJiaScrollViewContainer.SPEED;
                    if (this.a.mMoveLen <= ((float) (-this.a.mViewHeight))) {
                        this.a.mMoveLen = (float) (-this.a.mViewHeight);
                        this.a.state = 2;
                        this.a.mCurrentViewIndex = 1;
                    }
                } else if (this.a.state == 1) {
                    if (this.a.mTextChangeListener != null) {
                        this.a.mTextChangeListener.a(SC_STATUS.DOWN);
                    }
                    this.a.mMoveLen = this.a.mMoveLen + ECJiaScrollViewContainer.SPEED;
                    if (this.a.mMoveLen >= 0.0f) {
                        this.a.mMoveLen = 0.0f;
                        this.a.state = 2;
                        this.a.mCurrentViewIndex = 0;
                    }
                } else {
                    this.a.mTimer.a();
                }
            }
            this.a.requestLayout();
        }
    }

    class ECJiaScrollViewContainer_2 implements OnTouchListener {
        final /* synthetic */ ECJiaScrollViewContainer a;

        ECJiaScrollViewContainer_2(ECJiaScrollViewContainer eCJiaScrollViewContainer) {
            this.a = eCJiaScrollViewContainer;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            ScrollView scrollView = (ScrollView) view;
            if (scrollView.getScrollY() == scrollView.getChildAt(0).getMeasuredHeight() - scrollView.getMeasuredHeight() && this.a.mCurrentViewIndex == 0) {
                this.a.canPullUp = true;
            } else {
                this.a.canPullUp = false;
            }
            return false;
        }
    }

    class ECJiaScrollViewContainer_3 implements OnTouchListener {
        final /* synthetic */ ECJiaScrollViewContainer a;

        ECJiaScrollViewContainer_3(ECJiaScrollViewContainer eCJiaScrollViewContainer) {
            this.a = eCJiaScrollViewContainer;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (((ScrollView) view).getScrollY() == 0 && this.a.mCurrentViewIndex == 1) {
                this.a.canPullDown = true;
            } else {
                this.a.canPullDown = false;
            }
            return false;
        }
    }

    public enum SC_STATUS {
        UP,
        DOWN
    }

    class a {
        final /* synthetic */ ECJiaScrollViewContainer a;
        private Handler b;
        private Timer c = new Timer();
        private a d;

        class a extends TimerTask {
            final /* synthetic */ a a;
            private Handler b;

            public a(a aVar, Handler handler) {
                this.a = aVar;
                this.b = handler;
            }

            public void run() {
                this.b.obtainMessage().sendToTarget();
            }
        }

        public a(ECJiaScrollViewContainer eCJiaScrollViewContainer, Handler handler) {
            this.a = eCJiaScrollViewContainer;
            this.b = handler;
        }

        public void a(long j) {
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
            this.d = new a(this, this.b);
            this.c.schedule(this.d, 0, j);
        }

        public void a() {
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
        }
    }

    public interface b {
        void a(SC_STATUS sc_status);
    }

    public ECJiaScrollViewContainer(Context context) {
        super(context);
        init();
    }

    public ECJiaScrollViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ECJiaScrollViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mTimer = new a(this, this.handler);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                if (this.vt == null) {
                    this.vt = VelocityTracker.obtain();
                } else {
                    this.vt.clear();
                }
                this.mLastY = motionEvent.getY();
                this.vt.addMovement(motionEvent);
                this.mEvents = 0;
                break;
            case 1:
                this.mLastY = motionEvent.getY();
                this.vt.addMovement(motionEvent);
                this.vt.computeCurrentVelocity(700);
                float yVelocity = this.vt.getYVelocity();
                if (!(this.mMoveLen == 0.0f || this.mMoveLen == ((float) (-this.mViewHeight)))) {
                    if (Math.abs(yVelocity) < 500.0f) {
                        if (this.mMoveLen <= ((float) ((-this.mViewHeight) / 3))) {
                            this.state = 0;
                        } else if (this.mMoveLen > ((float) ((-this.mViewHeight) / 3))) {
                            this.state = 1;
                        }
                    } else if (yVelocity < 0.0f) {
                        this.state = 0;
                    } else {
                        this.state = 1;
                    }
                    this.mTimer.a(2);
                    try {
                        if (this.vt != null) {
                            this.vt.recycle();
                            this.vt = null;
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                break;
            case 2:
                this.vt.addMovement(motionEvent);
                if (this.canPullUp && this.mCurrentViewIndex == 0 && this.mEvents == 0) {
                    this.mMoveLen += motionEvent.getY() - this.mLastY;
                    if (this.mMoveLen > 0.0f) {
                        this.mMoveLen = 0.0f;
                        this.mCurrentViewIndex = 0;
                    } else if (this.mMoveLen < ((float) (-this.mViewHeight))) {
                        this.mMoveLen = (float) (-this.mViewHeight);
                        this.mCurrentViewIndex = 1;
                    }
                    if (this.mMoveLen < -8.0f) {
                        motionEvent.setAction(3);
                    }
                } else if (this.canPullDown && this.mCurrentViewIndex == 1 && this.mEvents == 0) {
                    this.mMoveLen += motionEvent.getY() - this.mLastY;
                    if (this.mMoveLen < ((float) (-this.mViewHeight))) {
                        this.mMoveLen = (float) (-this.mViewHeight);
                        this.mCurrentViewIndex = 1;
                    } else if (this.mMoveLen > 0.0f) {
                        this.mMoveLen = 0.0f;
                        this.mCurrentViewIndex = 0;
                    }
                    if (this.mMoveLen > ((float) (8 - this.mViewHeight))) {
                        motionEvent.setAction(3);
                    }
                } else {
                    this.mEvents++;
                }
                this.mLastY = motionEvent.getY();
                requestLayout();
                break;
            case 5:
            case 6:
                this.mEvents = -1;
                break;
        }
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setOnTextChangeListener(b bVar) {
        this.mTextChangeListener = bVar;
    }

    public void autoUp() {
        this.mMoveLen = (float) ((-this.mViewHeight) / 2);
        this.mTimer.a();
        this.state = 0;
        this.mCurrentViewIndex = 1;
        this.mTimer.a(2);
        try {
            if (this.vt != null) {
                this.vt.recycle();
                this.vt = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mTextChangeListener != null) {
            this.mTextChangeListener.a(SC_STATUS.UP);
        }
        requestLayout();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.topView.layout(0, (int) this.mMoveLen, this.mViewWidth, this.topView.getMeasuredHeight() + ((int) this.mMoveLen));
        this.bottomView.layout(0, this.topView.getMeasuredHeight() + ((int) this.mMoveLen), this.mViewWidth, (this.topView.getMeasuredHeight() + ((int) this.mMoveLen)) + this.bottomView.getMeasuredHeight());
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mViewHeight = getMeasuredHeight();
        if (!this.isMeasured) {
            this.isMeasured = true;
            this.mViewWidth = getMeasuredWidth();
            this.topView = getChildAt(0);
            this.bottomView = getChildAt(1);
            this.bottomView.setOnTouchListener(this.bottomViewTouchListener);
            this.topView.setOnTouchListener(this.topViewTouchListener);
        }
    }

    public void scrollToPageOne() {
    }
}
