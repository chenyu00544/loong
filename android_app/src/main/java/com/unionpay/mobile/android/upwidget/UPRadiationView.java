package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.utils.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UPRadiationView extends View {
    private List<a> a;
    private int b;
    private int c;
    private Context d;
    private Handler e;

    private class a {
        Paint a;
        int b;
        float c;
        int d;
        final /* synthetic */ UPRadiationView e;

        private a(UPRadiationView uPRadiationView) {
            this.e = uPRadiationView;
        }
    }

    public UPRadiationView(Context context) {
        this(context, null);
    }

    public UPRadiationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context;
        this.a = Collections.synchronizedList(new ArrayList());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
        int i2 = displayMetrics2.heightPixels;
        if (i <= 0 || i2 <= 0) {
            throw new RuntimeException("size illegal");
        }
        this.b = i / 2;
        this.c = (i2 / 2) - b.n;
        this.e = new t(this);
        this.e.sendEmptyMessage(0);
    }

    private static Paint a(int i, float f) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(f);
        paint.setStyle(Style.FILL);
        paint.setAlpha(i);
        paint.setColor(-1);
        return paint;
    }

    static /* synthetic */ void a(UPRadiationView uPRadiationView) {
        if (uPRadiationView.a == null) {
            return;
        }
        if (uPRadiationView.a.size() == 0) {
            a aVar = new a();
            aVar.b = 0;
            aVar.d = 255;
            aVar.c = (float) (aVar.b / 4);
            aVar.a = a(aVar.d, aVar.c);
            uPRadiationView.a.add(aVar);
            return;
        }
        for (int i = 0; i < uPRadiationView.a.size(); i++) {
            aVar = (a) uPRadiationView.a.get(i);
            if (aVar.d == 0) {
                uPRadiationView.a.remove(i);
                aVar.a = null;
            } else {
                aVar.b += 10;
                aVar.d -= 4;
                if (aVar.d < 0) {
                    aVar.d = 0;
                }
                aVar.c = (float) (aVar.b / 4);
                aVar.a.setAlpha(aVar.d);
                aVar.a.setStrokeWidth(aVar.c);
                if (aVar.b == g.a(uPRadiationView.d, 60.0f)) {
                    aVar = new a();
                    aVar.b = 0;
                    aVar.d = 255;
                    aVar.c = (float) (aVar.b / 4);
                    aVar.a = a(aVar.d, aVar.c);
                    uPRadiationView.a.add(aVar);
                }
            }
        }
    }

    public final void a() {
        this.d = null;
        this.e.removeCallbacksAndMessages(null);
        this.e = null;
        if (this.a != null) {
            this.a.clear();
        }
        this.a = null;
    }

    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < this.a.size(); i++) {
            a aVar = (a) this.a.get(i);
            canvas.drawCircle((float) this.b, (float) this.c, (float) aVar.b, aVar.a);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }
}
