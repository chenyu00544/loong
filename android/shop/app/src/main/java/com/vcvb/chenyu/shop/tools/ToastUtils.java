package com.vcvb.chenyu.shop.tools;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
