package com.ecjia.component.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.ecmoban.android.missmall.R;

public class ECJiaAutoReturnView extends View {
    private int color;
    private String content;
    private Paint mPaint;
    private int maxLines;
    private int size;

    public ECJiaAutoReturnView(Context context) {
        this(context, null);
    }

    public ECJiaAutoReturnView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint = new Paint();
        this.content = "";
        this.size = 14;
        this.color = ViewCompat.MEASURED_STATE_MASK;
        this.maxLines = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ar_text_view);
        String string = obtainStyledAttributes.getString(0);
        String string2 = obtainStyledAttributes.getString(1);
        String string3 = obtainStyledAttributes.getString(2);
        String string4 = obtainStyledAttributes.getString(3);
        obtainStyledAttributes.recycle();
        if (string != null) {
            this.color = Color.parseColor(string);
        }
        if (string2 != null) {
            this.size = Integer.parseInt(string2);
        }
        if (string3 != null) {
            this.maxLines = Integer.parseInt(string3);
        }
        if (string4 != null) {
            this.content = string4;
        }
        init();
    }

    public ECJiaAutoReturnView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setContent(String str) {
        this.content = str;
        requestLayout();
    }

    private void init() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.color);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setTextSize(getRawSize(1, (float) this.size));
    }

    public void setTextSise(int i) {
        this.size = i;
        init();
    }

    public void setTextColor(int i) {
        this.color = i;
        init();
    }

    public void setMaxLines(int i) {
        this.maxLines = i;
        init();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        float f = fontMetrics.descent - fontMetrics.ascent;
        String[] autoSplit = autoSplit(this.content, this.mPaint, (float) (getWidth() - 5));
        float f2 = f;
        for (String str : autoSplit) {
            if (autoSplit.length == 1) {
                canvas.drawText(str, 0.0f, 5.0f + f2, this.mPaint);
            } else {
                canvas.drawText(str, 0.0f, f2, this.mPaint);
            }
            f2 += fontMetrics.leading + f;
        }
    }

    private String[] autoSplit(String str, Paint paint, float f) {
        int length = str.length();
        float measureText = paint.measureText(str);
        if (measureText <= f) {
            return new String[]{str};
        }
        int ceil = (int) Math.ceil((double) (measureText / f));
        if (ceil > 1 && this.maxLines != 0) {
            ceil = this.maxLines;
        }
        String[] strArr = new String[ceil];
        int i = 1;
        ceil = 0;
        int i2 = 0;
        while (ceil < length) {
            if (paint.measureText(str, ceil, i) > f) {
                String str2 = (String) str.subSequence(ceil, i - 1);
                int i3 = i - 1;
                if (i2 != this.maxLines - 1 || this.maxLines == 0) {
                    strArr[i2] = str2;
                    i2++;
                    ceil = i3;
                } else {
                    if (str2.length() > str2.length() - 1) {
                        strArr[i2] = str2.substring(0, str2.length() - 1) + "...";
                    } else {
                        strArr[i2] = str2;
                    }
                }
            }
            if (i == length) {
                strArr[i2] = (String) str.subSequence(ceil, i);
                break;
            }
            i++;
        }
        return strArr;
    }

    public float getRawSize(int i, float f) {
        Resources system;
        Context context = getContext();
        if (context == null) {
            system = Resources.getSystem();
        } else {
            system = context.getResources();
        }
        return TypedValue.applyDimension(i, f, system.getDisplayMetrics());
    }
}
