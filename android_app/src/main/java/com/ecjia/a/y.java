package com.ecjia.a;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* compiled from: ECJiaScreenUtils */
public class y {
    public static int a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int b(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int c(Context context) {
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return a(context, 24);
        }
    }

    public static int d(Context context) {
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("navigation_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return a(context, 48);
        }
    }

    public static int a(Context context, int i) {
        return (int) ((((float) i) * e(context)) + 0.5f);
    }

    public static float e(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.density;
        } catch (Exception e) {
            return 160.0f;
        }
    }
}
