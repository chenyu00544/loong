package ru.truba.touchgallery.TouchView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.tencent.open.yyb.TitleBar;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"NewApi"})
public class TouchImageView extends ImageView {
    static final int CLICK = 10;
    static final long DOUBLE_PRESS_INTERVAL = 600;
    static final int DRAG = 1;
    static final float FRICTION = 0.9f;
    static final int NONE = 0;
    private static final String TAG = "ImgTouch";
    static final int ZOOM = 2;
    boolean allowInert = false;
    float bmHeight;
    float bmWidth;
    float bottom;
    float height;
    PointF last = new PointF();
    PointF lastDelta = new PointF(0.0f, 0.0f);
    long lastDragTime = 0;
    long lastPressTime = 0;
    float[] m;
    private Timer mClickTimer;
    private Context mContext;
    private OnClickListener mOnClickListener;
    private Object mScaleDetector;
    private Handler mTimerHandler = null;
    Matrix matrix = new Matrix();
    float matrixX;
    float matrixY;
    float maxScale = 3.0f;
    PointF mid = new PointF();
    float minScale = 1.0f;
    int mode = 0;
    float oldDist = 1.0f;
    public boolean onBottomSide = false;
    public boolean onLeftSide = false;
    public boolean onRightSide = false;
    public boolean onTopSide = false;
    float origHeight;
    float origWidth;
    float redundantXSpace;
    float redundantYSpace;
    float right;
    float saveScale = 1.0f;
    Matrix savedMatrix = new Matrix();
    PointF start = new PointF();
    float velocity = 0.0f;
    float width;
    private boolean zoomToOriginalSize = false;

    class TouchImageView_1 implements OnClickListener {
        TouchImageView_1() {
        }

        public void onClick(View view) {
            ((Activity) TouchImageView.this.mContext).finish();
            Log.d(TouchImageView.TAG, "click============");
        }
    }

    class TouchImageView_2 implements OnTouchListener {
        TouchImageView_2() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            WrapMotionEvent wrap = WrapMotionEvent.wrap(motionEvent);
            if (TouchImageView.this.mScaleDetector != null) {
                ((ScaleGestureDetector) TouchImageView.this.mScaleDetector).onTouchEvent(motionEvent);
            }
            TouchImageView.this.fillMatrixXY();
            PointF pointF = new PointF(wrap.getX(), wrap.getY());
            float f;
            switch (wrap.getAction() & 255) {
                case 0:
                    TouchImageView.this.allowInert = false;
                    TouchImageView.this.savedMatrix.set(TouchImageView.this.matrix);
                    TouchImageView.this.last.set(wrap.getX(), wrap.getY());
                    TouchImageView.this.start.set(TouchImageView.this.last);
                    TouchImageView.this.mode = 1;
                    Log.d(TouchImageView.TAG, "1============");
                    break;
                case 1:
                    TouchImageView.this.allowInert = true;
                    TouchImageView.this.mode = 0;
                    int abs = (int) Math.abs(wrap.getX() - TouchImageView.this.start.x);
                    int abs2 = (int) Math.abs(wrap.getY() - TouchImageView.this.start.y);
                    if (abs < 10 && abs2 < 10) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - TouchImageView.this.lastPressTime <= TouchImageView.DOUBLE_PRESS_INTERVAL) {
                            if (TouchImageView.this.mClickTimer != null) {
                                TouchImageView.this.mClickTimer.cancel();
                            }
                            if (TouchImageView.this.saveScale == 1.0f) {
                                f = TouchImageView.this.maxScale / TouchImageView.this.saveScale;
                                TouchImageView.this.matrix.postScale(f, f, TouchImageView.this.start.x, TouchImageView.this.start.y);
                                TouchImageView.this.saveScale = TouchImageView.this.maxScale;
                            } else {
                                TouchImageView.this.matrix.postScale(TouchImageView.this.minScale / TouchImageView.this.saveScale, TouchImageView.this.minScale / TouchImageView.this.saveScale, TouchImageView.this.width / 2.0f, TouchImageView.this.height / 2.0f);
                                TouchImageView.this.saveScale = TouchImageView.this.minScale;
                            }
                            TouchImageView.this.calcPadding();
                            TouchImageView.this.checkAndSetTranslate(0.0f, 0.0f);
                            TouchImageView.this.lastPressTime = 0;
                        } else {
                            TouchImageView.this.lastPressTime = currentTimeMillis;
                            TouchImageView.this.mClickTimer = new Timer();
                            TouchImageView.this.mClickTimer.schedule(new Task(), 300);
                        }
                        if (TouchImageView.this.saveScale == TouchImageView.this.minScale) {
                            TouchImageView.this.scaleMatrixToBounds();
                        }
                    }
                    Log.d(TouchImageView.TAG, "3============");
                    break;
                case 2:
                    TouchImageView.this.allowInert = false;
                    float f2;
                    if (TouchImageView.this.mode == 1) {
                        float f3 = pointF.x - TouchImageView.this.last.x;
                        f2 = pointF.y - TouchImageView.this.last.y;
                        long currentTimeMillis2 = System.currentTimeMillis();
                        TouchImageView.this.velocity = (((float) TouchImageView.this.distanceBetween(pointF, TouchImageView.this.last)) / ((float) (currentTimeMillis2 - TouchImageView.this.lastDragTime))) * TouchImageView.FRICTION;
                        TouchImageView.this.lastDragTime = currentTimeMillis2;
                        TouchImageView.this.checkAndSetTranslate(f3, f2);
                        TouchImageView.this.lastDelta.set(f3, f2);
                        TouchImageView.this.last.set(pointF.x, pointF.y);
                    } else if (TouchImageView.this.mScaleDetector == null && TouchImageView.this.mode == 2) {
                        f2 = TouchImageView.this.spacing(wrap);
                        if (motionEvent.getPointerCount() >= 2 && TitleBar.SHAREBTN_RIGHT_MARGIN <= Math.abs(TouchImageView.this.oldDist - f2) && Math.abs(TouchImageView.this.oldDist - f2) <= 50.0f) {
                            f = f2 / TouchImageView.this.oldDist;
                            TouchImageView.this.oldDist = f2;
                            f2 = TouchImageView.this.saveScale;
                            TouchImageView touchImageView = TouchImageView.this;
                            touchImageView.saveScale *= f;
                            if (TouchImageView.this.saveScale > TouchImageView.this.maxScale) {
                                TouchImageView.this.saveScale = TouchImageView.this.maxScale;
                                f = TouchImageView.this.maxScale / f2;
                            } else if (TouchImageView.this.saveScale < TouchImageView.this.minScale) {
                                TouchImageView.this.saveScale = TouchImageView.this.minScale;
                                f = TouchImageView.this.minScale / f2;
                            }
                            TouchImageView.this.calcPadding();
                            if (TouchImageView.this.origWidth * TouchImageView.this.saveScale <= TouchImageView.this.width || TouchImageView.this.origHeight * TouchImageView.this.saveScale <= TouchImageView.this.height) {
                                TouchImageView.this.matrix.postScale(f, f, TouchImageView.this.width / 2.0f, TouchImageView.this.height / 2.0f);
                                if (f < 1.0f) {
                                    TouchImageView.this.fillMatrixXY();
                                    if (f < 1.0f) {
                                        TouchImageView.this.scaleMatrixToBounds();
                                    }
                                }
                            } else {
                                PointF access$1200 = TouchImageView.this.midPointF(wrap);
                                TouchImageView.this.matrix.postScale(f, f, access$1200.x, access$1200.y);
                                TouchImageView.this.fillMatrixXY();
                                if (f < 1.0f) {
                                    if (TouchImageView.this.matrixX < (-TouchImageView.this.right)) {
                                        TouchImageView.this.matrix.postTranslate(-(TouchImageView.this.matrixX + TouchImageView.this.right), 0.0f);
                                    } else if (TouchImageView.this.matrixX > 0.0f) {
                                        TouchImageView.this.matrix.postTranslate(-TouchImageView.this.matrixX, 0.0f);
                                    }
                                    if (TouchImageView.this.matrixY < (-TouchImageView.this.bottom)) {
                                        TouchImageView.this.matrix.postTranslate(0.0f, -(TouchImageView.this.matrixY + TouchImageView.this.bottom));
                                    } else if (TouchImageView.this.matrixY > 0.0f) {
                                        TouchImageView.this.matrix.postTranslate(0.0f, -TouchImageView.this.matrixY);
                                    }
                                }
                            }
                            TouchImageView.this.checkSiding();
                        }
                    }
                    Log.d(TouchImageView.TAG, "5============");
                    break;
                case 5:
                    TouchImageView.this.oldDist = TouchImageView.this.spacing(wrap);
                    if (TouchImageView.this.oldDist > TitleBar.SHAREBTN_RIGHT_MARGIN) {
                        TouchImageView.this.savedMatrix.set(TouchImageView.this.matrix);
                        TouchImageView.this.midPoint(TouchImageView.this.mid, wrap);
                        TouchImageView.this.mode = 2;
                        Log.d(TouchImageView.TAG, "2============");
                        break;
                    }
                    break;
                case 6:
                    TouchImageView.this.mode = 0;
                    TouchImageView.this.velocity = 0.0f;
                    TouchImageView.this.savedMatrix.set(TouchImageView.this.matrix);
                    TouchImageView.this.oldDist = TouchImageView.this.spacing(wrap);
                    Log.d(TouchImageView.TAG, "4============");
                    break;
            }
            TouchImageView.this.setImageMatrix(TouchImageView.this.matrix);
            TouchImageView.this.invalidate();
            return false;
        }
    }

    private class ScaleListener extends SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            TouchImageView.this.mode = 2;
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float min = (float) Math.min((double) Math.max(0.95f, scaleGestureDetector.getScaleFactor()), 1.05d);
            float f = TouchImageView.this.saveScale;
            TouchImageView touchImageView = TouchImageView.this;
            touchImageView.saveScale *= min;
            if (TouchImageView.this.saveScale > TouchImageView.this.maxScale) {
                TouchImageView.this.saveScale = TouchImageView.this.maxScale;
                min = TouchImageView.this.maxScale / f;
            } else if (TouchImageView.this.saveScale < TouchImageView.this.minScale) {
                TouchImageView.this.saveScale = TouchImageView.this.minScale;
                min = TouchImageView.this.minScale / f;
            }
            TouchImageView.this.right = ((TouchImageView.this.width * TouchImageView.this.saveScale) - TouchImageView.this.width) - ((TouchImageView.this.redundantXSpace * 2.0f) * TouchImageView.this.saveScale);
            TouchImageView.this.bottom = ((TouchImageView.this.height * TouchImageView.this.saveScale) - TouchImageView.this.height) - ((TouchImageView.this.redundantYSpace * 2.0f) * TouchImageView.this.saveScale);
            float f2;
            if (TouchImageView.this.origWidth * TouchImageView.this.saveScale <= TouchImageView.this.width || TouchImageView.this.origHeight * TouchImageView.this.saveScale <= TouchImageView.this.height) {
                TouchImageView.this.matrix.postScale(min, min, TouchImageView.this.width / 2.0f, TouchImageView.this.height / 2.0f);
                if (min < 1.0f) {
                    TouchImageView.this.matrix.getValues(TouchImageView.this.m);
                    f = TouchImageView.this.m[2];
                    f2 = TouchImageView.this.m[5];
                    if (min < 1.0f) {
                        if (((float) Math.round(TouchImageView.this.origWidth * TouchImageView.this.saveScale)) < TouchImageView.this.width) {
                            if (f2 < (-TouchImageView.this.bottom)) {
                                TouchImageView.this.matrix.postTranslate(0.0f, -(TouchImageView.this.bottom + f2));
                            } else if (f2 > 0.0f) {
                                TouchImageView.this.matrix.postTranslate(0.0f, -f2);
                            }
                        } else if (f < (-TouchImageView.this.right)) {
                            TouchImageView.this.matrix.postTranslate(-(f + TouchImageView.this.right), 0.0f);
                        } else if (f > 0.0f) {
                            TouchImageView.this.matrix.postTranslate(-f, 0.0f);
                        }
                    }
                }
            } else {
                TouchImageView.this.matrix.postScale(min, min, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                TouchImageView.this.matrix.getValues(TouchImageView.this.m);
                f = TouchImageView.this.m[2];
                f2 = TouchImageView.this.m[5];
                if (min < 1.0f) {
                    if (f < (-TouchImageView.this.right)) {
                        TouchImageView.this.matrix.postTranslate(-(f + TouchImageView.this.right), 0.0f);
                    } else if (f > 0.0f) {
                        TouchImageView.this.matrix.postTranslate(-f, 0.0f);
                    }
                    if (f2 < (-TouchImageView.this.bottom)) {
                        TouchImageView.this.matrix.postTranslate(0.0f, -(TouchImageView.this.bottom + f2));
                    } else if (f2 > 0.0f) {
                        TouchImageView.this.matrix.postTranslate(0.0f, -f2);
                    }
                }
            }
            return true;
        }
    }

    private class Task extends TimerTask {
        private Task() {
        }

        public void run() {
            TouchImageView.this.mTimerHandler.sendEmptyMessage(0);
        }
    }

    static class TimeHandler extends Handler {
        private final WeakReference<TouchImageView> mService;

        TimeHandler(TouchImageView touchImageView) {
            this.mService = new WeakReference(touchImageView);
        }

        public void handleMessage(Message message) {
            ((TouchImageView) this.mService.get()).performClick();
            if (((TouchImageView) this.mService.get()).mOnClickListener != null) {
                ((TouchImageView) this.mService.get()).mOnClickListener.onClick((View) this.mService.get());
            }
        }
    }

    public boolean isZoomToOriginalSize() {
        return this.zoomToOriginalSize;
    }

    public void setZoomToOriginalSize(boolean z) {
        this.zoomToOriginalSize = z;
    }

    public TouchImageView(Context context) {
        super(context);
        super.setClickable(true);
        this.mContext = context;
        init();
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setClickable(true);
        this.mContext = context;
        init();
    }

    protected void init() {
        this.mTimerHandler = new TimeHandler(this);
        this.matrix.setTranslate(1.0f, 1.0f);
        this.m = new float[9];
        setImageMatrix(this.matrix);
        setScaleType(ScaleType.MATRIX);
        if (VERSION.SDK_INT >= 8) {
            this.mScaleDetector = new ScaleGestureDetector(this.mContext, new ScaleListener());
        }
        setOnClickListener(new TouchImageView_1());
        setOnTouchListener(new TouchImageView_2());
    }

    public void resetScale() {
        fillMatrixXY();
        this.matrix.postScale(this.minScale / this.saveScale, this.minScale / this.saveScale, this.width / 2.0f, this.height / 2.0f);
        this.saveScale = this.minScale;
        calcPadding();
        checkAndSetTranslate(0.0f, 0.0f);
        scaleMatrixToBounds();
        setImageMatrix(this.matrix);
        invalidate();
    }

    public boolean pagerCanScroll() {
        if (this.mode == 0 && this.saveScale == this.minScale) {
            return true;
        }
        return false;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.allowInert) {
            float f = this.lastDelta.x * this.velocity;
            float f2 = this.lastDelta.y * this.velocity;
            if (f <= this.width && f2 <= this.height) {
                this.velocity *= FRICTION;
                if (((double) Math.abs(f)) >= 0.1d || ((double) Math.abs(f2)) >= 0.1d) {
                    checkAndSetTranslate(f, f2);
                    setImageMatrix(this.matrix);
                }
            }
        }
    }

    private void checkAndSetTranslate(float f, float f2) {
        float round = (float) Math.round(this.origWidth * this.saveScale);
        float round2 = (float) Math.round(this.origHeight * this.saveScale);
        fillMatrixXY();
        if (round < this.width) {
            if (this.matrixY + f2 > 0.0f) {
                f2 = -this.matrixY;
                f = 0.0f;
            } else if (this.matrixY + f2 < (-this.bottom)) {
                f2 = -(this.matrixY + this.bottom);
                f = 0.0f;
            } else {
                f = 0.0f;
            }
        } else if (round2 >= this.height) {
            if (this.matrixX + f > 0.0f) {
                f = -this.matrixX;
            } else if (this.matrixX + f < (-this.right)) {
                f = -(this.matrixX + this.right);
            }
            if (this.matrixY + f2 > 0.0f) {
                f2 = -this.matrixY;
            } else if (this.matrixY + f2 < (-this.bottom)) {
                f2 = -(this.matrixY + this.bottom);
            }
        } else if (this.matrixX + f > 0.0f) {
            f = -this.matrixX;
            f2 = 0.0f;
        } else if (this.matrixX + f < (-this.right)) {
            f = -(this.matrixX + this.right);
            f2 = 0.0f;
        } else {
            f2 = 0.0f;
        }
        this.matrix.postTranslate(f, f2);
        checkSiding();
    }

    private void checkSiding() {
        fillMatrixXY();
        float round = (float) Math.round(this.origWidth * this.saveScale);
        float round2 = (float) Math.round(this.origHeight * this.saveScale);
        this.onBottomSide = false;
        this.onTopSide = false;
        this.onRightSide = false;
        this.onLeftSide = false;
        if ((-this.matrixX) < TitleBar.SHAREBTN_RIGHT_MARGIN) {
            this.onLeftSide = true;
        }
        if ((round >= this.width && (this.matrixX + round) - this.width < TitleBar.SHAREBTN_RIGHT_MARGIN) || (round <= this.width && round + (-this.matrixX) <= this.width)) {
            this.onRightSide = true;
        }
        if ((-this.matrixY) < TitleBar.SHAREBTN_RIGHT_MARGIN) {
            this.onTopSide = true;
        }
        if (Math.abs(((-this.matrixY) + this.height) - round2) < TitleBar.SHAREBTN_RIGHT_MARGIN) {
            this.onBottomSide = true;
        }
    }

    private void calcPadding() {
        this.right = ((this.width * this.saveScale) - this.width) - ((this.redundantXSpace * 2.0f) * this.saveScale);
        this.bottom = ((this.height * this.saveScale) - this.height) - ((this.redundantYSpace * 2.0f) * this.saveScale);
    }

    private void fillMatrixXY() {
        this.matrix.getValues(this.m);
        this.matrixX = this.m[2];
        this.matrixY = this.m[5];
    }

    private void scaleMatrixToBounds() {
        if (Math.abs(this.matrixX + (this.right / 2.0f)) > 0.5f) {
            this.matrix.postTranslate(-(this.matrixX + (this.right / 2.0f)), 0.0f);
        }
        if (Math.abs(this.matrixY + (this.bottom / 2.0f)) > 0.5f) {
            this.matrix.postTranslate(0.0f, -(this.matrixY + (this.bottom / 2.0f)));
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.bmWidth = (float) bitmap.getWidth();
        this.bmHeight = (float) bitmap.getHeight();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.width = (float) MeasureSpec.getSize(i);
        this.height = (float) MeasureSpec.getSize(i2);
        float min = Math.min(this.width / this.bmWidth, this.height / this.bmHeight);
        this.matrix.setScale(min, min);
        setImageMatrix(this.matrix);
        this.saveScale = 1.0f;
        this.redundantYSpace = this.height - (this.bmHeight * min);
        this.redundantXSpace = this.width - (min * this.bmWidth);
        this.redundantYSpace /= 2.0f;
        this.redundantXSpace /= 2.0f;
        this.matrix.postTranslate(this.redundantXSpace, this.redundantYSpace);
        this.origWidth = this.width - (this.redundantXSpace * 2.0f);
        this.origHeight = this.height - (this.redundantYSpace * 2.0f);
        calcPadding();
        setImageMatrix(this.matrix);
    }

    private double distanceBetween(PointF pointF, PointF pointF2) {
        return Math.sqrt(Math.pow((double) (pointF.x - pointF2.x), 2.0d) + Math.pow((double) (pointF.y - pointF2.y), 2.0d));
    }

    private float spacing(WrapMotionEvent wrapMotionEvent) {
        float x = wrapMotionEvent.getX(0) - wrapMotionEvent.getX(1);
        float y = wrapMotionEvent.getY(0) - wrapMotionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private void midPoint(PointF pointF, WrapMotionEvent wrapMotionEvent) {
        pointF.set((wrapMotionEvent.getX(0) + wrapMotionEvent.getX(1)) / 2.0f, (wrapMotionEvent.getY(0) + wrapMotionEvent.getY(1)) / 2.0f);
    }

    private PointF midPointF(WrapMotionEvent wrapMotionEvent) {
        return new PointF((wrapMotionEvent.getX(0) + wrapMotionEvent.getX(1)) / 2.0f, (wrapMotionEvent.getY(0) + wrapMotionEvent.getY(1)) / 2.0f);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
}
