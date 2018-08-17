package com.baidu.mapapi.http;

import android.content.Context;
import android.os.Build.VERSION;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.http.HttpClient.ProtoResultCallback;

public class AsyncHttpClient {
    Context a;
    private int b = m_AppUI.MSG_APP_SAVESCREEN;
    private int c = m_AppUI.MSG_APP_SAVESCREEN;

    static {
        if (VERSION.SDK_INT <= 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    public AsyncHttpClient(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        this.a = context;
    }

    public void get(String str, ProtoResultCallback protoResultCallback) {
        if (str == null) {
            throw new IllegalArgumentException("URI cannot be null");
        }
        new Thread(new a(this, protoResultCallback, str)).start();
    }
}
