package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

final class j implements DownloadListener {
    final /* synthetic */ Activity a;

    j(Activity activity) {
        this.a = activity;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Throwable th) {
        }
    }
}
