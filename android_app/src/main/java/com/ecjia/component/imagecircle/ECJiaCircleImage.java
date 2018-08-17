package com.ecjia.component.imagecircle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.ecmoban.android.missmall.R;

public class ECJiaCircleImage extends ImageView {
    public ECJiaCircleImage(Context context) {
        super(context);
    }

    public ECJiaCircleImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaCircleImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public Bitmap createMask() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setColor(Color.parseColor("#FFFFFFFF"));
        canvas.drawOval(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), paint);
        return createBitmap;
    }

    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null && getWidth() != 0 && getHeight() != 0) {
            Bitmap bitmap;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
            }
            if (bitmap != null) {
                try {
                    bitmap = bitmap.copy(Config.ARGB_8888, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    bitmap = null;
                }
            } else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_no_avarta_icon);
            }
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_no_avarta_icon);
            }
            canvas.drawBitmap(getCroppedBitmap(bitmap, getWidth()), 0.0f, 0.0f, null);
        }
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap, int i) {
        if (!(bitmap.getWidth() == i && bitmap.getHeight() == i)) {
            bitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#BAB399"));
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) (bitmap.getWidth() / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }
}
