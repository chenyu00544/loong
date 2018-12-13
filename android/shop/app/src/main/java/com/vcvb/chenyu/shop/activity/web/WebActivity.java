package com.vcvb.chenyu.shop.activity.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;

public class WebActivity extends BaseRecyclerViewActivity {

    private WebView webView;
    private String url;
    private ProgressBar progressBar;
    private TextView titleV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        context = this;
        changeStatusBarTextColor(true);
        url = getIntent().getStringExtra("url");
        setNavBack();
        initView();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        titleV = findViewById(R.id.textView123);
        titleV.setText(R.string.app_name);
        TextView add = findViewById(R.id.textView122);
        add.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        progressBar = findViewById(R.id.progressBar);
        webView = findViewById(R.id.web);
        webView.loadUrl(url);
        WebSettings seting = webView.getSettings();
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
//        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleV.setText(title);
            }
        });
    }
}
