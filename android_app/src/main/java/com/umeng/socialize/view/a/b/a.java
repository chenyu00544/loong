package com.umeng.socialize.view.a.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: CustomView */
public class a extends View {
    private int a;
    private List<Bitmap> b;
    private RectF c;
    private int d = 0;
    private Handler e = new a(this);

    /* compiled from: CustomView */
    private static class a extends Handler {
        WeakReference<a> a;

        public a(a aVar) {
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            a aVar = (a) this.a.get();
            if (aVar != null) {
                aVar.invalidate();
            }
        }
    }

    public a(Context context, int i, List<Bitmap> list) {
        super(context);
        this.a = i;
        this.c = new RectF(0.0f, 0.0f, (float) i, (float) i);
        this.b = list;
    }

    public void a(int i) {
        this.d = i;
        this.e.sendEmptyMessage(0);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.a, this.a);
    }

    protected void onDraw(Canvas canvas) {
        Log.d("23232", "draw");
        canvas.drawBitmap((Bitmap) this.b.get(this.d), null, this.c, null);
    }
}
