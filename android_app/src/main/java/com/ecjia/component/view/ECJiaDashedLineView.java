package com.ecjia.component.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import com.ecmoban.android.missmall.R;

public class ECJiaDashedLineView extends View {
    float[] arrayOfFloat;
    float dashWidth;
    PathEffect effects;
    float gapWidth;
    int lineColor;
    float lineWidth;
    int orientation;

    public ECJiaDashedLineView(Context context) {
        this(context, null);
    }

    public ECJiaDashedLineView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaDashedLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.dashWidth = 0.0f;
        this.gapWidth = 0.0f;
        this.lineWidth = 0.0f;
        this.orientation = 0;
        this.lineColor = ViewCompat.MEASURED_SIZE_MASK;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ECJiaDashedLineView);
        this.dashWidth = obtainStyledAttributes.getDimension(2, 0.0f);
        this.gapWidth = obtainStyledAttributes.getDimension(1, 1.0f);
        this.lineWidth = obtainStyledAttributes.getDimension(3, 1.0f);
        this.orientation = obtainStyledAttributes.getInteger(0, 0);
        this.lineColor = obtainStyledAttributes.getColor(4, 0);
        obtainStyledAttributes.recycle();
        this.arrayOfFloat = new float[]{this.dashWidth, this.gapWidth, this.dashWidth, this.gapWidth};
        this.effects = new DashPathEffect(this.arrayOfFloat, this.dashWidth);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(this.lineColor);
        paint.setStyle(Style.STROKE);
        paint.setStrokeCap(Cap.SQUARE);
        paint.setStrokeWidth(this.lineWidth);
        Path path = new Path();
        if (this.orientation == 0) {
            path.moveTo(0.0f, (float) (getHeight() / 2));
            path.lineTo((float) getMeasuredWidth(), (float) (getMeasuredHeight() / 2));
        } else if (this.orientation == 1) {
            path.moveTo((float) (getMeasuredWidth() / 2), 0.0f);
            path.lineTo((float) (getMeasuredWidth() / 2), (float) getMeasuredHeight());
        }
        paint.setPathEffect(this.effects);
        canvas.drawPath(path, paint);
    }
}
