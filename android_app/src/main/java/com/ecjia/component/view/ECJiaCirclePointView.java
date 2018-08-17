package com.ecjia.component.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import com.ecjia.a.q;
import com.ecmoban.android.missmall.R;
import com.tencent.open.yyb.TitleBar;

public class ECJiaCirclePointView extends View {
    int circleX;
    int circleY;
    int leftPadding;
    private final Context mContext;
    int mHeight;
    int mWidth;
    Paint paint;
    int pointColor;
    int pointNumber;
    int pointPadding;
    int pointRadius;
    int rightPadding;

    public ECJiaCirclePointView(Context context) {
        this(context, null);
    }

    public ECJiaCirclePointView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaCirclePointView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pointColor = SupportMenu.CATEGORY_MASK;
        this.leftPadding = 10;
        this.rightPadding = 10;
        this.pointRadius = 6;
        this.pointPadding = 4;
        this.pointNumber = 0;
        this.circleX = 0;
        this.circleY = 0;
        this.paint = new Paint();
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ECJiaCirclePointView);
        this.pointColor = obtainStyledAttributes.getColor(0, -2236963);
        this.leftPadding = (int) obtainStyledAttributes.getDimension(1, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.rightPadding = (int) obtainStyledAttributes.getDimension(2, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.pointRadius = (int) obtainStyledAttributes.getDimension(3, 6.0f);
        this.pointPadding = (int) obtainStyledAttributes.getDimension(4, 4.0f);
        obtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        this.paint.setStyle(Style.FILL);
        this.paint.setAntiAlias(true);
        this.paint.setColor(this.pointColor);
    }

    public void setPointColor(int i) {
        this.pointColor = i;
    }

    public void setLeftPadding(int i) {
        this.leftPadding = i;
    }

    public void setPointRadius(int i) {
        this.pointRadius = i;
    }

    public void setPointPadding(int i) {
        this.pointPadding = i;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mWidth = i;
        this.mHeight = i2;
        q.a("CirclePointView w " + i);
        if (this.mWidth != 0) {
            this.pointNumber = ((this.mWidth - this.rightPadding) - this.leftPadding) / ((this.pointRadius * 2) + this.pointPadding);
            int i5 = (((this.mWidth - this.rightPadding) - this.leftPadding) % ((this.pointRadius * 2) + this.pointPadding)) / 2;
            this.circleY = this.mHeight / 2;
            this.circleX = ((i5 + this.leftPadding) - (this.pointPadding / 2)) - this.pointRadius;
            super.onSizeChanged(i, i2, i3, i4);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (canvas != null && this.pointNumber > 0) {
            int i = this.circleX;
            for (int i2 = 0; i2 < this.pointNumber; i2++) {
                i += (this.pointRadius * 2) + this.pointPadding;
                canvas.drawCircle((float) i, (float) this.circleY, (float) this.pointRadius, this.paint);
            }
        }
    }
}
