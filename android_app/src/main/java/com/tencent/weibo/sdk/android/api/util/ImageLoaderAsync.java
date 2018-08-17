package com.tencent.weibo.sdk.android.api.util;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class ImageLoaderAsync {

    public interface callBackImage {
        void callback(Drawable drawable, String str);
    }

    public Drawable loadImage(final String str, final callBackImage com_tencent_weibo_sdk_android_api_util_ImageLoaderAsync_callBackImage) {
        final Handler imageLoaderAsync_1 = new Handler() {
            public void handleMessage(Message message) {
                com_tencent_weibo_sdk_android_api_util_ImageLoaderAsync_callBackImage.callback((Drawable) message.obj, str);
            }
        };
        new Thread() {
            public void run() {
                imageLoaderAsync_1.sendMessage(imageLoaderAsync_1.obtainMessage(0, Util.loadImageFromUrl(str)));
            }
        }.start();
        return null;
    }
}
