package cn.itguy.zxingportrait.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import cn.itguy.zxingportrait.a;
import cn.itguy.zxingportrait.a.b;
import cn.itguy.zxingportrait.a.d;
import cn.itguy.zxingportrait.camera.c;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.google.zxing.h;
import java.util.ArrayList;
import java.util.List;

public final class ViewfinderView extends View {
    private static final long ANIMATION_DELAY = 80;
    private static final int CURRENT_POINT_OPACITY = 160;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int[] SCANNER_ALPHA = new int[]{0, 64, 128, 192, 255, 192, 128, 64};
    private c cameraManager;
    private int i = 0;
    private Drawable lineDrawable;
    private Context mContext;
    private GradientDrawable mDrawable;
    private Rect mRect;
    private final int maskColor;
    private final Paint paint;
    private List<h> possibleResultPoints;
    private Bitmap resultBitmap;
    private final int resultColor;
    private int scannerAlpha;
    private int size = 100;
    private TextPaint textPaint;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.paint = new Paint(1);
        this.textPaint = new TextPaint();
        Resources resources = getResources();
        this.maskColor = resources.getColor(b.viewfinder_mask);
        this.resultColor = resources.getColor(b.result_view);
        this.mRect = new Rect();
        int color = getResources().getColor(b.lightgreen);
        int color2 = getResources().getColor(b.green);
        int color3 = getResources().getColor(b.lightgreen);
        this.lineDrawable = getResources().getDrawable(d.zx_code_line);
        this.mDrawable = new GradientDrawable(Orientation.LEFT_RIGHT, new int[]{color, color, color2, color3, color3});
        this.scannerAlpha = 0;
        this.possibleResultPoints = new ArrayList(5);
    }

    public void setCameraManager(c cVar) {
        this.cameraManager = cVar;
    }

    void drawText(Rect rect, Canvas canvas) {
        this.textPaint.setColor(Color.parseColor("#ffffff"));
        this.textPaint.setTypeface(Typeface.DEFAULT);
        this.textPaint.setTextSize(this.mContext.getResources().getDimension(a.c.dp_14));
        this.textPaint.setStrokeWidth(0.0f);
        String string = this.mContext.getResources().getString(a.h.scanning_notify_two);
        this.textPaint.getTextBounds(string, 0, string.length(), new Rect());
        canvas.drawText(string, (float) (((rect.left + rect.right) - ((int) this.textPaint.measureText(string))) / 2), (float) (rect.bottom + 10), this.textPaint);
    }

    public void onDraw(Canvas canvas) {
        if (this.cameraManager != null) {
            Rect e = this.cameraManager.e();
            if (e != null) {
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                if (width > 800) {
                    this.size = 100;
                } else if (width > d_ResultType.SHORT_URL && width <= 800) {
                    this.size = 70;
                } else if (width <= d_ResultType.SHORT_URL) {
                    this.size = 40;
                }
                canvas.drawRect(0.0f, 0.0f, (float) width, (float) (e.top + this.size), this.paint);
                canvas.drawRect(0.0f, (float) (e.top + this.size), (float) (e.left + this.size), (float) (e.bottom - this.size), this.paint);
                canvas.drawRect((float) (e.right - this.size), (float) (e.top + this.size), (float) width, (float) (e.bottom - this.size), this.paint);
                canvas.drawRect(0.0f, (float) (e.bottom - this.size), (float) width, (float) height, this.paint);
                drawText(e, canvas);
                if (this.resultBitmap != null) {
                    this.paint.setAlpha(160);
                    canvas.drawBitmap(this.resultBitmap, null, e, this.paint);
                    return;
                }
                this.paint.setColor(getResources().getColor(b.green));
                canvas.drawRect((float) (e.left + this.size), (float) (e.top + this.size), (float) ((e.left + 15) + this.size), (float) ((e.top + 5) + this.size), this.paint);
                canvas.drawRect((float) (e.left + this.size), (float) (e.top + this.size), (float) ((e.left + 5) + this.size), (float) ((e.top + 15) + this.size), this.paint);
                canvas.drawRect((float) ((e.right - 15) - this.size), (float) (e.top + this.size), (float) (e.right - this.size), (float) ((e.top + 5) + this.size), this.paint);
                canvas.drawRect((float) ((e.right - 5) - this.size), (float) (e.top + this.size), (float) (e.right - this.size), (float) ((e.top + 15) + this.size), this.paint);
                canvas.drawRect((float) (e.left + this.size), (float) ((e.bottom - 5) - this.size), (float) ((e.left + 15) + this.size), (float) (e.bottom - this.size), this.paint);
                canvas.drawRect((float) (e.left + this.size), (float) ((e.bottom - 15) - this.size), (float) ((e.left + 5) + this.size), (float) (e.bottom - this.size), this.paint);
                canvas.drawRect((float) ((e.right - 15) - this.size), (float) ((e.bottom - 5) - this.size), (float) (e.right - this.size), (float) (e.bottom - this.size), this.paint);
                canvas.drawRect((float) ((e.right - 5) - this.size), (float) ((e.bottom - 15) - this.size), (float) (e.right - this.size), (float) (e.bottom - this.size), this.paint);
                this.paint.setColor(getResources().getColor(b.green));
                this.paint.setAlpha(SCANNER_ALPHA[this.scannerAlpha]);
                this.scannerAlpha = (this.scannerAlpha + 1) % SCANNER_ALPHA.length;
                int i = this.i + 5;
                this.i = i;
                if (i < (e.bottom - this.size) - (e.top + this.size)) {
                    this.mRect.set((e.left - 6) + this.size, ((e.top + this.i) - 6) + this.size, (e.right + 6) - this.size, ((e.top + 6) + this.i) + this.size);
                    this.lineDrawable.setBounds(this.mRect);
                    this.lineDrawable.draw(canvas);
                    invalidate();
                } else {
                    this.i = 0;
                }
                postInvalidateDelayed(ANIMATION_DELAY, e.left, e.top, e.right, e.bottom);
            }
        }
    }

    public void drawViewfinder() {
        Bitmap bitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        invalidate();
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.resultBitmap = bitmap;
        invalidate();
    }

    public void addPossibleResultPoint(h hVar) {
        List list = this.possibleResultPoints;
        synchronized (list) {
            list.add(hVar);
            int size = list.size();
            if (size > 20) {
                list.subList(0, size - 10).clear();
            }
        }
    }

    public void recycleLineDrawable() {
        if (this.mDrawable != null) {
            this.mDrawable.setCallback(null);
        }
        if (this.lineDrawable != null) {
            this.lineDrawable.setCallback(null);
        }
    }
}
