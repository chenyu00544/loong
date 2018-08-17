package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.j;
import com.ecmoban.android.missmall.R;

public class ECJiaGetPasswordOldActivity extends a {
    private TextView a;
    private ImageView b;
    private WebView c;

    class ECJiaGetPasswordOldActivity_1 extends WebViewClient {
        final /* synthetic */ ECJiaGetPasswordOldActivity a;

        ECJiaGetPasswordOldActivity_1(ECJiaGetPasswordOldActivity eCJiaGetPasswordOldActivity) {
            this.a = eCJiaGetPasswordOldActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }

    class ECJiaGetPasswordOldActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaGetPasswordOldActivity a;

        ECJiaGetPasswordOldActivity_2(ECJiaGetPasswordOldActivity eCJiaGetPasswordOldActivity) {
            this.a = eCJiaGetPasswordOldActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_get_password2);
        b();
    }

    private void b() {
        this.a = (TextView) findViewById(R.id.top_view_text);
        this.b = (ImageView) findViewById(R.id.top_view_back);
        this.c = (WebView) findViewById(R.id.getpwd_webView);
        this.c.getSettings().setJavaScriptEnabled(true);
        this.c.setWebViewClient(new ECJiaGetPasswordOldActivity_1(this));
        WebSettings settings = this.c.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        this.a.setText(getResources().getString(R.string.getpassword));
        this.b.setOnClickListener(new ECJiaGetPasswordOldActivity_2(this));
        if (!"".equals(j.a().a.c())) {
            this.c.loadUrl(j.a().a.c());
        }
    }
}
