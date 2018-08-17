package com.unionpay.mobile.android.utils;

import android.content.res.ColorStateList;
import android.graphics.LinearGradient;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import java.util.Arrays;

public final class h {
    private static final int[] a = new int[]{16842910};
    private static final int[] b = new int[]{16842908};
    private static final int[] c = new int[]{-16842910};
    private static final int[] d = new int[]{16842910, 16842919};
    private static final int[] e = new int[]{16842910, 16842908};
    private static final int[] f = new int[]{16842910, 16842912};

    public static ColorStateList a(int i, int i2) {
        return a(i, i2, i2, i);
    }

    public static ColorStateList a(int i, int i2, int i3, int i4) {
        int[] iArr = new int[]{i2, i3, i, i3, i4, i4};
        int[][] iArr2 = new int[6][];
        iArr2[0] = new int[]{16842919, 16842910};
        iArr2[1] = new int[]{16842910, 16842908};
        iArr2[2] = new int[]{16842910};
        iArr2[3] = new int[]{16842908};
        iArr2[4] = new int[]{16842909};
        iArr2[5] = new int[0];
        return new ColorStateList(iArr2, iArr);
    }

    public static Drawable a(int i, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(a(i, 18.0f), null, null));
        shapeDrawable.getPaint().setShader(new LinearGradient(f, f2, f3, f4, iArr, fArr, TileMode.CLAMP));
        return shapeDrawable;
    }

    public static Drawable a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(d, drawable2);
        stateListDrawable.addState(e, drawable2);
        if (drawable4 != null) {
            stateListDrawable.addState(f, drawable4);
        }
        stateListDrawable.addState(a, drawable);
        stateListDrawable.addState(b, drawable2);
        if (drawable3 != null) {
            stateListDrawable.addState(c, drawable3);
        }
        return stateListDrawable;
    }

    public static ShapeDrawable a(int i, int i2, float f) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(a(i2, f), null, null));
        shapeDrawable.getPaint().setStrokeWidth(1.0f);
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setStyle(Style.FILL_AND_STROKE);
        return shapeDrawable;
    }

    private static float[] a(int i, float f) {
        float[] fArr = new float[8];
        Arrays.fill(fArr, 0.0f);
        if (i != 0) {
            if ((i & 1) != 0) {
                fArr[1] = f;
                fArr[0] = f;
            }
            if ((i & 2) != 0) {
                fArr[3] = f;
                fArr[2] = f;
            }
            if ((i & 4) != 0) {
                fArr[5] = f;
                fArr[4] = f;
            }
            if ((i & 8) != 0) {
                fArr[7] = f;
                fArr[6] = f;
            }
        }
        return fArr;
    }
}
