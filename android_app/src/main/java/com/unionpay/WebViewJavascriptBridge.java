package com.unionpay;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.unionpay.utils.g;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

public class WebViewJavascriptBridge implements Serializable {
    WebView a;
    Activity b;
    d c;
    Map d = new HashMap();
    Map e = new HashMap();
    long f = 0;

    public WebViewJavascriptBridge(Activity activity, WebView webView, d dVar) {
        this.b = activity;
        this.a = webView;
        this.c = dVar;
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.addJavascriptInterface(this, "_WebViewJavascriptBridge");
        this.a.setWebViewClient(new c());
        this.a.setWebChromeClient(new ac());
    }

    private String a(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r").replace("\f", "\\f");
    }

    private void a(String str, e eVar, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("data", str);
        if (eVar != null) {
            StringBuilder stringBuilder = new StringBuilder("java_cb_");
            long j = this.f + 1;
            this.f = j;
            String stringBuilder2 = stringBuilder.append(j).toString();
            this.e.put(stringBuilder2, eVar);
            hashMap.put("callbackId", stringBuilder2);
        }
        if (str2 != null) {
            hashMap.put("handlerName", str2);
        }
        a(hashMap);
    }

    private void a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("responseId", str);
        hashMap.put("responseData", str2);
        a(hashMap);
    }

    private void a(Map map) {
        g.a("test", "sending:" + new JSONObject(map).toString());
        this.b.runOnUiThread(new aa(this, String.format("javascript:WebViewJavascriptBridge._handleMessageFromJava('%s');", new Object[]{a(r0)})));
    }

    public static String convertStreamToString(InputStream inputStream) {
        String next;
        IOException e;
        String str = "";
        try {
            Scanner useDelimiter = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            next = useDelimiter.hasNext() ? useDelimiter.next() : str;
            try {
                inputStream.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return next;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            next = str;
            e = iOException;
            e.printStackTrace();
            return next;
        }
        return next;
    }

    @JavascriptInterface
    public void _handleMessageFromJs(String str, String str2, String str3, String str4, String str5) {
        if (str2 != null) {
            ((e) this.e.get(str2)).a(str3);
            this.e.remove(str2);
            return;
        }
        e abVar = str4 != null ? new ab(this, str4) : null;
        d dVar;
        if (str5 != null) {
            dVar = (d) this.d.get(str5);
            if (dVar == null) {
                g.b("test", "WVJB Warning: No handler for " + str5);
                return;
            }
        }
        dVar = this.c;
        try {
            this.b.runOnUiThread(new z(this, dVar, str, abVar));
        } catch (Exception e) {
            g.b("test", "WebViewJavascriptBridge: WARNING: java handler threw. " + e.getMessage());
        }
    }

    public void callHandler(String str) {
        callHandler(str, null, null);
    }

    public void callHandler(String str, String str2) {
        callHandler(str, str2, null);
    }

    public void callHandler(String str, String str2, e eVar) {
        a(str2, eVar, str);
    }

    public void registerHandler(String str, d dVar) {
        this.d.put(str, dVar);
    }

    public void send(String str) {
        send(str, null);
    }

    public void send(String str, e eVar) {
        a(str, eVar, null);
    }
}
