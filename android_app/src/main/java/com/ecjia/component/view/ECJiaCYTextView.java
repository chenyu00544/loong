package com.ecjia.component.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.ecmoban.android.missmall.R;
import com.tencent.open.yyb.TitleBar;
import java.util.Vector;

public class ECJiaCYTextView extends TextView {
    public static int m_iTextHeight;
    public static int m_iTextWidth;
    private float LineSpace = 0.0f;
    private Paint mPaint = null;
    private String string = "";

    public ECJiaCYTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ECJiaCYTextView);
        float dimension = obtainStyledAttributes.getDimension(1, TitleBar.BACKBTN_LEFT_MARGIN);
        int color = obtainStyledAttributes.getColor(2, -1442840576);
        float dimension2 = obtainStyledAttributes.getDimension(3, 6.0f);
        int color2 = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        this.LineSpace = dimension2;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(color);
        this.mPaint.setTextSize(dimension);
        switch (color2) {
            case 0:
                this.mPaint.setTypeface(Typeface.DEFAULT);
                return;
            case 1:
                this.mPaint.setTypeface(Typeface.SANS_SERIF);
                return;
            case 2:
                this.mPaint.setTypeface(Typeface.SERIF);
                return;
            case 3:
                this.mPaint.setTypeface(Typeface.MONOSPACE);
                return;
            default:
                this.mPaint.setTypeface(Typeface.DEFAULT);
                return;
        }
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        super.onDraw(canvas);
        if (m_iTextWidth > 0) {
            Vector vector = new Vector();
            FontMetrics fontMetrics = this.mPaint.getFontMetrics();
            int ceil = ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.top))) + ((int) this.LineSpace);
            int ceil2 = (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.top));
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i2 < this.string.length()) {
                char charAt = this.string.charAt(i2);
                float[] fArr = new float[1];
                this.mPaint.getTextWidths(String.valueOf(charAt), fArr);
                if (charAt == '\n') {
                    i3++;
                    vector.addElement(this.string.substring(i4, i2));
                    i4 = i2 + 1;
                    i5 = 0;
                } else {
                    i5 += (int) Math.ceil((double) fArr[0]);
                    if (i5 > m_iTextWidth) {
                        i5 = i3 + 1;
                        vector.addElement(this.string.substring(i4, i2));
                        i4 = i2;
                        i2--;
                        i3 = i5;
                        i5 = 0;
                    } else if (i2 == this.string.length() - 1) {
                        i3++;
                        vector.addElement(this.string.substring(i4, this.string.length()));
                    }
                }
                i2++;
            }
            m_iTextHeight = (i3 * ceil) + 2;
            for (i4 = 0; i4 < i3; i4++) {
                canvas.drawText((String) vector.elementAt(i4), (float) 2, (float) ((ceil * i) + ceil2), this.mPaint);
                i++;
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int measureWidth = measureWidth(i);
        m_iTextWidth = measureWidth;
        setMeasuredDimension(measureWidth, measureHeight(i2));
    }

    private int measureHeight(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        initHeight();
        int i2 = m_iTextHeight;
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return size;
        }
        return i2;
    }

    private void initHeight() {
        m_iTextHeight = 0;
        FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        int ceil = ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.top))) + ((int) this.LineSpace);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < this.string.length()) {
            char charAt = this.string.charAt(i);
            float[] fArr = new float[1];
            this.mPaint.getTextWidths(String.valueOf(charAt), fArr);
            if (charAt == '\n') {
                i3++;
                i2 = i + 1;
                i2 = 0;
            } else {
                i2 += (int) Math.ceil((double) fArr[0]);
                if (i2 > m_iTextWidth) {
                    i3++;
                    i--;
                    i2 = 0;
                } else if (i == this.string.length() - 1) {
                    i3++;
                }
            }
            i++;
        }
        m_iTextHeight = (i3 * ceil) + 2;
    }

    private int measureWidth(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : d_ResultType.SHORT_URL;
    }

    public void SetText(String str) {
        this.string = str;
        requestLayout();
        invalidate();
    }
}
