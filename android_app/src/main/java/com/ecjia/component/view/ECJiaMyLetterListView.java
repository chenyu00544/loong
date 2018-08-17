package com.ecjia.component.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.ecmoban.android.missmall.R;

public class ECJiaMyLetterListView extends View {
    String[] b;
    int choose;
    a onTouchingLetterChangedListener;
    Paint paint;
    boolean showBkg;

    public interface a {
        void a(String str);
    }

    public ECJiaMyLetterListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        this.choose = -1;
        this.paint = new Paint();
        this.showBkg = false;
    }

    public ECJiaMyLetterListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        this.choose = -1;
        this.paint = new Paint();
        this.showBkg = false;
    }

    public ECJiaMyLetterListView(Context context) {
        super(context);
        this.b = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        this.choose = -1;
        this.paint = new Paint();
        this.showBkg = false;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.showBkg) {
            canvas.drawColor(Color.parseColor("#10000000"));
        }
        int height = getHeight();
        int width = getWidth();
        int length = height / this.b.length;
        for (height = 0; height < this.b.length; height++) {
            this.paint.setColor(Color.parseColor("#515151"));
            this.paint.setTypeface(Typeface.DEFAULT);
            this.paint.setTextSize(getResources().getDimension(R.dimen.choosecity_a_z));
            this.paint.setAntiAlias(true);
            if (height == this.choose) {
                this.paint.setColor(Color.parseColor("#3399ff"));
                this.paint.setFakeBoldText(true);
            }
            canvas.drawText(this.b[height], ((float) (width / 2)) - (this.paint.measureText(this.b[height]) / 2.0f), (float) ((length * height) + length), this.paint);
            this.paint.reset();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.choose;
        a aVar = this.onTouchingLetterChangedListener;
        int height = (int) ((y / ((float) getHeight())) * ((float) this.b.length));
        switch (action) {
            case 0:
                this.showBkg = true;
                if (i != height && aVar != null && height >= 0 && height <= this.b.length) {
                    aVar.a(this.b[height]);
                    this.choose = height;
                    invalidate();
                    break;
                }
            case 1:
                this.showBkg = false;
                this.choose = -1;
                invalidate();
                break;
            case 2:
                if (i != height && aVar != null && height >= 0 && height <= this.b.length) {
                    aVar.a(this.b[height]);
                    this.choose = height;
                    invalidate();
                    break;
                }
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void setOnTouchingLetterChangedListener(a aVar) {
        this.onTouchingLetterChangedListener = aVar;
    }
}
