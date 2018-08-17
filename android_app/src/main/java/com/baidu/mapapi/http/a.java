package com.baidu.mapapi.http;

import com.baidu.mapapi.http.HttpClient.ProtoResultCallback;

class a implements Runnable {
    final /* synthetic */ ProtoResultCallback a;
    final /* synthetic */ String b;
    final /* synthetic */ AsyncHttpClient c;

    a(AsyncHttpClient asyncHttpClient, ProtoResultCallback protoResultCallback, String str) {
        this.c = asyncHttpClient;
        this.a = protoResultCallback;
        this.b = str;
    }

    public void run() {
        HttpClient httpClient = new HttpClient(this.c.a, "GET", this.a);
        httpClient.setMaxTimeOut(this.c.b);
        httpClient.setReadTimeOut(this.c.c);
        httpClient.request(this.b);
    }
}
