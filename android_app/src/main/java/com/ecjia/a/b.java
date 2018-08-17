package com.ecjia.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

@SuppressLint({"ParcelCreator"})
/* compiled from: ECJiaBaseIntent */
public class b extends Intent {
    public b(Context context, Class<?> cls) {
        super(context, cls);
        putExtra("activity_name", context.getClass().getName());
    }
}
