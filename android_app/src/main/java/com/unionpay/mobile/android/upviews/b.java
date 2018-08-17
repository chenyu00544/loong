package com.unionpay.mobile.android.upviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.ArrayList;
import java.util.Timer;

public final class b extends WebView implements Callback {
    private WebSettings a;
    private Handler b;
    private a c;
    private Timer d;
    private boolean e;
    private ArrayList<String> f;

    public interface a {
        void u();

        void v();
    }

    public interface b extends a {
        void c(String str);
    }

    private class c extends WebChromeClient {
        final /* synthetic */ b a;

        private c(b bVar) {
            this.a = bVar;
        }

        public final void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                this.a.b.sendEmptyMessage(1);
            }
        }
    }

    private class d extends WebViewClient {
        final /* synthetic */ b a;

        private d(b bVar) {
            this.a = bVar;
        }

        public final void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.d.cancel();
            this.a.d.purge();
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.d = new Timer();
            this.a.d.schedule(new c(this), StatisticConfig.MIN_UPLOAD_INTERVAL);
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            this.a.a();
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String str2;
            if (this.a.f == null || this.a.f.size() == 0) {
                str2 = null;
            } else {
                if (str != null && str.length() > 0) {
                    int i = 0;
                    while (this.a.f != null && i < this.a.f.size()) {
                        if (str.startsWith((String) this.a.f.get(i))) {
                            str2 = (String) this.a.f.get(i);
                            break;
                        }
                        i++;
                    }
                }
                str2 = null;
            }
            if (str2 != null) {
                Message obtainMessage = this.a.b.obtainMessage(4);
                obtainMessage.obj = str;
                this.a.b.sendMessage(obtainMessage);
            } else {
                webView.loadUrl(str);
            }
            return true;
        }
    }

    public b(Context context, a aVar) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = new Timer();
        this.e = false;
        this.f = null;
        this.b = new Handler(this);
        this.c = aVar;
        setScrollBarStyle(33554432);
        this.a = getSettings();
        this.a.setJavaScriptEnabled(true);
        setWebChromeClient(new c());
        setWebViewClient(new d());
    }

    private final void a() {
        loadData(String.format("<div align=\"center\">%s</div>", new Object[]{"&#x7F51;&#x9875;&#x52A0;&#x8F7D;&#x5931;&#x8D25;&#xFF0C;&#x8BF7;&#x91CD;&#x8BD5;"}), "text/html", "utf-8");
    }

    public final void a(String str) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        if (str != null && str.length() > 0) {
            this.f.add(str);
        }
    }

    public final void b(String str) {
        Message obtainMessage = this.b.obtainMessage(0);
        obtainMessage.obj = str;
        this.b.sendMessage(obtainMessage);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                if (this.c != null) {
                    this.c.u();
                }
                String str = "";
                if (message.obj != null) {
                    str = (String) message.obj;
                }
                Log.e("uppay", "url = " + str);
                loadUrl(str);
                break;
            case 1:
            case 2:
                break;
            case 3:
                a();
                break;
            case 4:
                if (this.c != null && (this.c instanceof b)) {
                    ((b) this.c).c((String) message.obj);
                    break;
                }
        }
        if (message.what == 1) {
            this.e = true;
        }
        if (this.c != null) {
            this.c.v();
        }
        return true;
    }
}
