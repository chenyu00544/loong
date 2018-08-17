package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

public final class o extends View {
    private Paint a;
    private int b;

    public o(Context context) {
        this(context, -7829368, 0);
    }

    public o(Context context, int i, int i2) {
        super(context);
        this.a = new Paint();
        this.a.setStyle(Style.STROKE);
        this.a.setStrokeWidth(4.0f);
        this.a.setColor(i);
        this.a.setPathEffect(null);
        this.b = i2;
    }

    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b == 0) {
            float height = (float) (getHeight() >> 1);
            canvas.drawLine(0.0f, height, (float) getWidth(), height, this.a);
        } else if (1 == this.b) {
            float width = (float) (getWidth() >> 1);
            canvas.drawLine(width, 0.0f, width, (float) getHeight(), this.a);
        }
    }
}
