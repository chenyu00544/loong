package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.n;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaGoodsDescActivity extends a implements a {
    private TextView a;
    private ImageView b;
    private n c;
    private WebView d;

    class ECJiaGoodsDescActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsDescActivity a;

        ECJiaGoodsDescActivity_1(ECJiaGoodsDescActivity eCJiaGoodsDescActivity) {
            this.a = eCJiaGoodsDescActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaGoodsDescActivity_2 extends WebViewClient {
        final /* synthetic */ ECJiaGoodsDescActivity a;

        ECJiaGoodsDescActivity_2(ECJiaGoodsDescActivity eCJiaGoodsDescActivity) {
            this.a = eCJiaGoodsDescActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.helpweb);
        PushAgent.getInstance(this).onAppStart();
        int intExtra = getIntent().getIntExtra("id", 0);
        this.a = (TextView) findViewById(R.id.top_view_text);
        this.a.setText(getBaseContext().getResources().getString(R.string.gooddetail_product));
        this.b = (ImageView) findViewById(R.id.top_view_back);
        this.b.setOnClickListener(new ECJiaGoodsDescActivity_1(this));
        this.d = (WebView) findViewById(R.id.help_web);
        this.c = new n(this);
        this.c.c(intExtra + "");
        this.d.setWebViewClient(new ECJiaGoodsDescActivity_2(this));
        this.d.setInitialScale(25);
        WebSettings settings = this.d.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        this.d.getSettings().setUseWideViewPort(true);
        this.d.getSettings().setLoadWithOverviewMode(true);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("goods/desc") && axVar.b() == 1) {
            this.d.loadDataWithBaseURL(null, this.c.t, "text/html", "utf-8", null);
        }
    }
}
