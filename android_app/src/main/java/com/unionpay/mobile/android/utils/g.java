package com.unionpay.mobile.android.utils;

import android.content.Context;

public final class g {
    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
