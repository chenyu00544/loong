package kankan.wheel.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* compiled from: WheelScroller */
public class f {
    private a a;
    private Context b;
    private GestureDetector c;
    private Scroller d;
    private int e;
    private float f;
    private boolean g;
    private SimpleOnGestureListener h = new f_1(this);
    private final int i = 0;
    private final int j = 1;
    private Handler k = new f_2(this);

    /* compiled from: WheelScroller */
    public interface a {
        void a();

        void a(int i);

        void b();

        void c();
    }

    /* compiled from: WheelScroller */
    class f_1 extends SimpleOnGestureListener {
        final /* synthetic */ f a;

        f_1(f fVar) {
            this.a = fVar;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.a.e = 0;
            this.a.d.fling(0, this.a.e, 0, (int) (-f2), 0, 0, -2147483647, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            this.a.a(0);
            return true;
        }
    }

    /* compiled from: WheelScroller */
    class f_2 extends Handler {
        final /* synthetic */ f a;

        f_2(f fVar) {
            this.a = fVar;
        }

        public void handleMessage(Message message) {
            this.a.d.computeScrollOffset();
            int currY = this.a.d.getCurrY();
            int a = this.a.e - currY;
            this.a.e = currY;
            if (a != 0) {
                this.a.a.a(a);
            }
            if (Math.abs(currY - this.a.d.getFinalY()) < 1) {
                this.a.d.getFinalY();
                this.a.d.forceFinished(true);
            }
            if (!this.a.d.isFinished()) {
                this.a.k.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                this.a.d();
            } else {
                this.a.b();
            }
        }
    }

    public f(Context context, a aVar) {
        this.c = new GestureDetector(context, this.h);
        this.c.setIsLongpressEnabled(false);
        this.d = new Scroller(context);
        this.a = aVar;
        this.b = context;
    }

    public void a(Interpolator interpolator) {
        this.d.forceFinished(true);
        this.d = new Scroller(this.b, interpolator);
    }

    public void a(int i, int i2) {
        this.d.forceFinished(true);
        this.e = 0;
        this.d.startScroll(0, 0, 0, i, i2 != 0 ? i2 : 400);
        a(0);
        e();
    }

    public void a() {
        this.d.forceFinished(true);
    }

    public boolean a(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f = motionEvent.getY();
                this.d.forceFinished(true);
                c();
                break;
            case 2:
                int y = (int) (motionEvent.getY() - this.f);
                if (y != 0) {
                    e();
                    this.a.a(y);
                    this.f = motionEvent.getY();
                    break;
                }
                break;
        }
        if (!this.c.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            d();
        }
        return true;
    }

    private void a(int i) {
        c();
        this.k.sendEmptyMessage(i);
    }

    private void c() {
        this.k.removeMessages(0);
        this.k.removeMessages(1);
    }

    private void d() {
        this.a.c();
        a(1);
    }

    private void e() {
        if (!this.g) {
            this.g = true;
            this.a.a();
        }
    }

    void b() {
        if (this.g) {
            this.a.b();
            this.g = false;
        }
    }
}
