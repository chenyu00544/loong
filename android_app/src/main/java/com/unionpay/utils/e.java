package com.unionpay.utils;

import android.content.Context;

public final class e {
    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
