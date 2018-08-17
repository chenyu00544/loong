package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ScrollView;
import java.lang.ref.WeakReference;

public class UPScrollView extends ScrollView {
    private WeakReference<a> a;
    private int b;
    private OnGlobalLayoutListener c;
    private Handler d;

    public interface a {
        void e(int i);
    }

    public UPScrollView(Context context) {
        this(context, null);
    }

    public UPScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UPScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new v(this);
        this.c = new u(this);
    }

    public final void a(a aVar) {
        this.a = new WeakReference(aVar);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this.c);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this.c);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!(this.a == null || this.a.get() == null)) {
            a aVar = (a) this.a.get();
            int scrollY = getScrollY();
            this.b = scrollY;
            aVar.e(scrollY);
        }
        switch (motionEvent.getAction()) {
            case 1:
                this.d.sendMessageDelayed(this.d.obtainMessage(), 5);
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
