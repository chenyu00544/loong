package com.ecjia.component.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.ecjia.a.q;
import com.ecmoban.android.missmall.R;

public class ECJiaSelectableRoundedImageView extends ImageView {
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    public static final String TAG = "SelectableRoundedImageView";
    private static final ScaleType[] sScaleTypeArray = new ScaleType[]{ScaleType.MATRIX, ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER, ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE};
    private boolean isOval;
    private ColorStateList mBorderColor;
    private float mBorderWidth;
    private Drawable mDrawable;
    private float mLeftBottomCornerRadius;
    private float mLeftTopCornerRadius;
    private float[] mRadii;
    private int mResource;
    private float mRightBottomCornerRadius;
    private float mRightTopCornerRadius;
    private ScaleType mScaleType;

    static class a extends Drawable {
        private RectF a = new RectF();
        private RectF b = new RectF();
        private final RectF c = new RectF();
        private final int d;
        private final int e;
        private final Paint f;
        private final Paint g;
        private BitmapShader h;
        private float[] i = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        private float[] j = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        private boolean k = false;
        private float l = 0.0f;
        private ColorStateList m = ColorStateList.valueOf(-16777216);
        private ScaleType n = ScaleType.FIT_CENTER;
        private Path o = new Path();
        private Bitmap p;
        private boolean q = false;

        public a(Bitmap bitmap, Resources resources) {
            this.p = bitmap;
            this.h = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
            if (bitmap != null) {
                this.d = bitmap.getScaledWidth(resources.getDisplayMetrics());
                this.e = bitmap.getScaledHeight(resources.getDisplayMetrics());
            } else {
                this.e = -1;
                this.d = -1;
            }
            this.c.set(0.0f, 0.0f, (float) this.d, (float) this.e);
            this.f = new Paint(1);
            this.f.setStyle(Style.FILL);
            this.f.setShader(this.h);
            this.g = new Paint(1);
            this.g.setStyle(Style.STROKE);
            this.g.setColor(this.m.getColorForState(getState(), -16777216));
            this.g.setStrokeWidth(this.l);
        }

        public static a a(Bitmap bitmap, Resources resources) {
            if (bitmap != null) {
                return new a(bitmap, resources);
            }
            return null;
        }

        public static Drawable a(Drawable drawable, Resources resources) {
            if (drawable == null || (drawable instanceof a)) {
                return drawable;
            }
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), a(layerDrawable.getDrawable(i), resources));
                }
                return layerDrawable;
            }
            Bitmap a = a(drawable);
            if (a != null) {
                return new a(a, resources);
            }
            q.a("SelectableRoundedCornerDrawableFailed to create bitmap from drawable!");
            return drawable;
        }

        public static Bitmap a(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            Bitmap createBitmap;
            try {
                createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                createBitmap = null;
            }
            return createBitmap;
        }

        public boolean isStateful() {
            return this.m.isStateful();
        }

        protected boolean onStateChange(int[] iArr) {
            int colorForState = this.m.getColorForState(iArr, 0);
            if (this.g.getColor() == colorForState) {
                return super.onStateChange(iArr);
            }
            this.g.setColor(colorForState);
            return true;
        }

        private void a(Canvas canvas) {
            Rect clipBounds = canvas.getClipBounds();
            Matrix matrix = canvas.getMatrix();
            if (ScaleType.CENTER == this.n) {
                this.a.set(clipBounds);
            } else if (ScaleType.CENTER_CROP == this.n) {
                a(matrix);
                this.a.set(clipBounds);
            } else if (ScaleType.FIT_XY == this.n) {
                matrix = new Matrix();
                matrix.setRectToRect(this.c, new RectF(clipBounds), ScaleToFit.FILL);
                this.h.setLocalMatrix(matrix);
                this.a.set(clipBounds);
            } else if (ScaleType.FIT_START == this.n || ScaleType.FIT_END == this.n || ScaleType.FIT_CENTER == this.n || ScaleType.CENTER_INSIDE == this.n) {
                a(matrix);
                this.a.set(this.c);
            } else if (ScaleType.MATRIX == this.n) {
                a(matrix);
                this.a.set(this.c);
            }
        }

        private void a(Matrix matrix) {
            float[] fArr = new float[9];
            matrix.getValues(fArr);
            for (int i = 0; i < this.i.length; i++) {
                this.i[i] = this.i[i] / fArr[0];
            }
        }

        private void b(Canvas canvas) {
            float[] fArr = new float[9];
            canvas.getMatrix().getValues(fArr);
            float f = fArr[0];
            float f2 = fArr[4];
            float f3 = fArr[2];
            float f4 = fArr[5];
            float width = this.a.width() / ((this.a.width() + this.l) + this.l);
            float height = this.a.height() / ((this.a.height() + this.l) + this.l);
            canvas.scale(width, height);
            if (ScaleType.FIT_START == this.n || ScaleType.FIT_END == this.n || ScaleType.FIT_XY == this.n || ScaleType.FIT_CENTER == this.n || ScaleType.CENTER_INSIDE == this.n || ScaleType.MATRIX == this.n) {
                canvas.translate(this.l, this.l);
            } else if (ScaleType.CENTER == this.n || ScaleType.CENTER_CROP == this.n) {
                canvas.translate((-f3) / (f * width), (-f4) / (f2 * height));
                canvas.translate(-(this.a.left - this.l), -(this.a.top - this.l));
            }
        }

        private void c(Canvas canvas) {
            float[] fArr = new float[9];
            canvas.getMatrix().getValues(fArr);
            this.l = (this.l * this.a.width()) / ((fArr[0] * this.a.width()) - (this.l * 2.0f));
            this.g.setStrokeWidth(this.l);
            this.b.set(this.a);
            this.b.inset((-this.l) / 2.0f, (-this.l) / 2.0f);
        }

        private void a() {
            for (int i = 0; i < this.i.length; i++) {
                if (this.i[i] > 0.0f) {
                    this.j[i] = this.i[i];
                    this.i[i] = this.i[i] - this.l;
                }
            }
        }

        public void draw(Canvas canvas) {
            canvas.save();
            if (!this.q) {
                a(canvas);
                if (this.l > 0.0f) {
                    c(canvas);
                    a();
                }
                this.q = true;
            }
            if (this.k) {
                if (this.l > 0.0f) {
                    b(canvas);
                    this.o.addOval(this.a, Direction.CW);
                    canvas.drawPath(this.o, this.f);
                    this.o.reset();
                    this.o.addOval(this.b, Direction.CW);
                    canvas.drawPath(this.o, this.g);
                } else {
                    this.o.addOval(this.a, Direction.CW);
                    canvas.drawPath(this.o, this.f);
                }
            } else if (this.l > 0.0f) {
                b(canvas);
                this.o.addRoundRect(this.a, this.i, Direction.CW);
                canvas.drawPath(this.o, this.f);
                this.o.reset();
                this.o.addRoundRect(this.b, this.j, Direction.CW);
                canvas.drawPath(this.o, this.g);
            } else {
                this.o.addRoundRect(this.a, this.i, Direction.CW);
                canvas.drawPath(this.o, this.f);
            }
            canvas.restore();
        }

        public void a(float[] fArr) {
            if (fArr != null) {
                if (fArr.length != 8) {
                    throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
                }
                for (int i = 0; i < fArr.length; i++) {
                    this.i[i] = fArr[i];
                }
            }
        }

        public int getOpacity() {
            return (this.p == null || this.p.hasAlpha() || this.f.getAlpha() < 255) ? -3 : -1;
        }

        public void setAlpha(int i) {
            this.f.setAlpha(i);
            invalidateSelf();
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.f.setColorFilter(colorFilter);
            invalidateSelf();
        }

        public void setDither(boolean z) {
            this.f.setDither(z);
            invalidateSelf();
        }

        public void setFilterBitmap(boolean z) {
            this.f.setFilterBitmap(z);
            invalidateSelf();
        }

        public int getIntrinsicWidth() {
            return this.d;
        }

        public int getIntrinsicHeight() {
            return this.e;
        }

        public void a(float f) {
            this.l = f;
            this.g.setStrokeWidth(f);
        }

        public void a(ColorStateList colorStateList) {
            if (colorStateList == null) {
                this.l = 0.0f;
                this.m = ColorStateList.valueOf(0);
                this.g.setColor(0);
                return;
            }
            this.m = colorStateList;
            this.g.setColor(this.m.getColorForState(getState(), -16777216));
        }

        public void a(boolean z) {
            this.k = z;
        }

        public void a(ScaleType scaleType) {
            if (scaleType != null) {
                this.n = scaleType;
            }
        }
    }

    public ECJiaSelectableRoundedImageView(Context context) {
        super(context);
        this.mResource = 0;
        this.mScaleType = ScaleType.FIT_CENTER;
        this.mLeftTopCornerRadius = 0.0f;
        this.mRightTopCornerRadius = 0.0f;
        this.mLeftBottomCornerRadius = 0.0f;
        this.mRightBottomCornerRadius = 0.0f;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = ColorStateList.valueOf(-16777216);
        this.isOval = false;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public ECJiaSelectableRoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaSelectableRoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mResource = 0;
        this.mScaleType = ScaleType.FIT_CENTER;
        this.mLeftTopCornerRadius = 0.0f;
        this.mRightTopCornerRadius = 0.0f;
        this.mLeftBottomCornerRadius = 0.0f;
        this.mRightBottomCornerRadius = 0.0f;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = ColorStateList.valueOf(-16777216);
        this.isOval = false;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ECJiaSelectableRoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(0, -1);
        if (i2 >= 0) {
            setScaleType(sScaleTypeArray[i2]);
        }
        this.mLeftTopCornerRadius = (float) obtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.mRightTopCornerRadius = (float) obtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.mLeftBottomCornerRadius = (float) obtainStyledAttributes.getDimensionPixelSize(3, 0);
        this.mRightBottomCornerRadius = (float) obtainStyledAttributes.getDimensionPixelSize(4, 0);
        if (this.mLeftTopCornerRadius < 0.0f || this.mRightTopCornerRadius < 0.0f || this.mLeftBottomCornerRadius < 0.0f || this.mRightBottomCornerRadius < 0.0f) {
            throw new IllegalArgumentException("radius values cannot be negative.");
        }
        this.mRadii = new float[]{this.mLeftTopCornerRadius, this.mLeftTopCornerRadius, this.mRightTopCornerRadius, this.mRightTopCornerRadius, this.mRightBottomCornerRadius, this.mRightBottomCornerRadius, this.mLeftBottomCornerRadius, this.mLeftBottomCornerRadius};
        this.mBorderWidth = (float) obtainStyledAttributes.getDimensionPixelSize(5, 0);
        if (this.mBorderWidth < 0.0f) {
            throw new IllegalArgumentException("border width cannot be negative.");
        }
        this.mBorderColor = obtainStyledAttributes.getColorStateList(6);
        if (this.mBorderColor == null) {
            this.mBorderColor = ColorStateList.valueOf(-16777216);
        }
        this.isOval = obtainStyledAttributes.getBoolean(7, false);
        obtainStyledAttributes.recycle();
        updateDrawable();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setScaleType(ScaleType scaleType) {
        super.setScaleType(scaleType);
        this.mScaleType = scaleType;
        updateDrawable();
    }

    public void setImageDrawable(Drawable drawable) {
        this.mResource = 0;
        this.mDrawable = a.a(drawable, getResources());
        super.setImageDrawable(this.mDrawable);
        updateDrawable();
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mResource = 0;
        this.mDrawable = a.a(bitmap, getResources());
        super.setImageDrawable(this.mDrawable);
        updateDrawable();
    }

    public void setImageResource(int i) {
        if (this.mResource != i) {
            this.mResource = i;
            this.mDrawable = resolveResource();
            super.setImageDrawable(this.mDrawable);
            updateDrawable();
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    private Drawable resolveResource() {
        Drawable drawable = null;
        Resources resources = getResources();
        if (resources == null) {
            return drawable;
        }
        if (this.mResource != 0) {
            try {
                drawable = resources.getDrawable(this.mResource);
            } catch (Throwable e) {
                q.a("SelectableRoundedImageViewUnable to find resource: " + this.mResource, e);
                this.mResource = 0;
            }
        }
        return a.a(drawable, getResources());
    }

    private void updateDrawable() {
        if (this.mDrawable != null) {
            ((a) this.mDrawable).a(this.mScaleType);
            ((a) this.mDrawable).a(this.mRadii);
            ((a) this.mDrawable).a(this.mBorderWidth);
            ((a) this.mDrawable).a(this.mBorderColor);
            ((a) this.mDrawable).a(this.isOval);
        }
    }

    public float getCornerRadius() {
        return this.mLeftTopCornerRadius;
    }

    public void setCornerRadiiDP(float f, float f2, float f3, float f4) {
        float f5 = getResources().getDisplayMetrics().density;
        float f6 = f * f5;
        float f7 = f2 * f5;
        float f8 = f3 * f5;
        f5 *= f4;
        this.mRadii = new float[]{f6, f6, f7, f7, f5, f5, f8, f8};
        updateDrawable();
    }

    public void setCornerRadius(float f, float f2, float f3, float f4) {
        this.mRadii = new float[]{f, f, f2, f2, f4, f4, f3, f3};
        updateDrawable();
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidthDP(float f) {
        float f2 = getResources().getDisplayMetrics().density * f;
        if (this.mBorderWidth != f2) {
            this.mBorderWidth = f2;
            updateDrawable();
            invalidate();
        }
    }

    public int getBorderColor() {
        return this.mBorderColor.getDefaultColor();
    }

    public void setBorderColor(int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.mBorderColor;
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (!this.mBorderColor.equals(colorStateList)) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(-16777216);
            }
            this.mBorderColor = colorStateList;
            updateDrawable();
            if (this.mBorderWidth > 0.0f) {
                invalidate();
            }
        }
    }

    public boolean isOval() {
        return this.isOval;
    }

    public void setOval(boolean z) {
        this.isOval = z;
        updateDrawable();
        invalidate();
    }
}
