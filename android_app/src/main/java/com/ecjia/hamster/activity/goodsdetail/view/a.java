package com.ecjia.hamster.activity.goodsdetail.view;

import android.util.Log;

/* compiled from: ECJiaLOG */
public class a {
    public static void a(String str) {
        Log.i("测试输出", b(str));
    }

    protected static String b(String str) {
        StackTraceElement stackTraceElement = new Throwable().fillInStackTrace().getStackTrace()[2];
        return stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(): " + str;
    }
}
