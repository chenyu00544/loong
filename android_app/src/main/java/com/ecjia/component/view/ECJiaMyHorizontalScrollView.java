package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.ecjia.a.q;

public class ECJiaMyHorizontalScrollView extends HorizontalScrollView {
    private View actionview;
    private a mListener = null;

    public interface a {
        void a();

        void a(boolean z);

        boolean b();
    }

    public ECJiaMyHorizontalScrollView(Context context) {
        super(context);
    }

    public ECJiaMyHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaMyHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public View getAction() {
        return this.actionview;
    }

    public void setAction(View view) {
        this.actionview = view;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        q.b("event.getAction()==" + motionEvent.getAction());
        if (this.mListener == null || !this.mListener.b()) {
            switch (motionEvent.getAction()) {
                case 0:
                case 3:
                    smoothScrollTo(0, 0);
                    break;
                case 1:
                    break;
                default:
                    return super.onTouchEvent(motionEvent);
            }
            int scrollX = getScrollX();
            int width = this.actionview.getWidth();
            if (scrollX < width / 2) {
                smoothScrollTo(0, 0);
                this.mListener.a(false);
                return true;
            }
            smoothScrollTo(width, 0);
            if (this.mListener == null) {
                return true;
            }
            this.mListener.a(true);
            return true;
        }
        this.mListener.a(false);
        if (getScrollX() > 0) {
            smoothScrollTo(0, 0);
            return true;
        }
        this.mListener.a();
        return true;
    }

    public void onStatusChangeListener(a aVar) {
        this.mListener = aVar;
    }
}
