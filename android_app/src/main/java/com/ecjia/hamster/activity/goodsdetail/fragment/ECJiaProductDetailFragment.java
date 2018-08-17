package com.ecjia.hamster.activity.goodsdetail.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.n;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.hamster.adapter.o;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;

public class ECJiaProductDetailFragment extends ECJiaGoodsDetailBaseFragment implements a {
    n d;
    boolean e = true;
    private WebView f;
    private FrameLayout g;
    private FrameLayout h;
    private ListView i;
    private ECJiaErrorView j;
    private String k;
    private o l;
    private RelativeLayout m;
    private RelativeLayout n;
    private TextView o;
    private TextView p;

    class ECJiaProductDetailFragment_1 implements OnClickListener {
        final /* synthetic */ ECJiaProductDetailFragment a;

        ECJiaProductDetailFragment_1(ECJiaProductDetailFragment eCJiaProductDetailFragment) {
            this.a = eCJiaProductDetailFragment;
        }

        public void onClick(View view) {
            this.a.a("one");
        }
    }

    class ECJiaProductDetailFragment_2 implements OnClickListener {
        final /* synthetic */ ECJiaProductDetailFragment a;

        ECJiaProductDetailFragment_2(ECJiaProductDetailFragment eCJiaProductDetailFragment) {
            this.a = eCJiaProductDetailFragment;
        }

        public void onClick(View view) {
            this.a.a("two");
        }
    }

    class ECJiaProductDetailFragment_3 extends WebViewClient {
        final /* synthetic */ ECJiaProductDetailFragment a;

        ECJiaProductDetailFragment_3(ECJiaProductDetailFragment eCJiaProductDetailFragment) {
            this.a = eCJiaProductDetailFragment;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_detail_layout, null);
        d(inflate);
        this.k = getActivity().getIntent().getStringExtra("goods_id");
        return inflate;
    }

    public void onResume() {
        super.onResume();
        if (this.e) {
            b();
        }
        q.a("ECJiaProductDetailFragment onResume");
    }

    private void d(View view) {
        b(view);
        c(view);
        a(view);
    }

    private void b() {
        if (this.d == null) {
            this.d = new n(getActivity());
            this.d.a((a) this);
        }
        this.d.c(this.k);
    }

    void a(View view) {
        this.m = (RelativeLayout) view.findViewById(R.id.tabOne_item);
        this.n = (RelativeLayout) view.findViewById(R.id.tabTwo_item);
        this.o = (TextView) view.findViewById(R.id.tabone_text);
        this.p = (TextView) view.findViewById(R.id.tabtwo_text);
        this.m.setOnClickListener(new ECJiaProductDetailFragment_1(this));
        this.n.setOnClickListener(new ECJiaProductDetailFragment_2(this));
        a("one");
    }

    public void b(View view) {
        this.g = (FrameLayout) view.findViewById(R.id.webview_item);
        this.f = (WebView) view.findViewById(R.id.my_web);
        this.f.setWebViewClient(new ECJiaProductDetailFragment_3(this));
        this.f.setInitialScale(25);
        WebSettings settings = this.f.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
    }

    public void c(View view) {
        this.h = (FrameLayout) view.findViewById(R.id.property_item);
        this.i = (ListView) view.findViewById(R.id.property_list);
        this.j = (ECJiaErrorView) view.findViewById(R.id.no_features);
        this.j.setVisibility(0);
        this.i.setVisibility(8);
    }

    private void a(String str) {
        ColorStateList colorStateList = getResources().getColorStateList(R.color.filter_text_color);
        if ("one".equals(str)) {
            this.o.setTextColor(SupportMenu.CATEGORY_MASK);
            this.p.setTextColor(colorStateList);
            this.g.setVisibility(0);
            this.h.setVisibility(8);
        } else if ("two".equals(str)) {
            this.o.setTextColor(colorStateList);
            this.p.setTextColor(SupportMenu.CATEGORY_MASK);
            this.g.setVisibility(8);
            this.h.setVisibility(0);
            if (com.ecjia.hamster.adapter.n.a().a == null || com.ecjia.hamster.adapter.n.a().a.q().size() <= 0) {
                this.j.setVisibility(0);
                this.i.setVisibility(8);
                return;
            }
            this.i.setVisibility(0);
            this.j.setVisibility(8);
            this.l = new o(getActivity(), com.ecjia.hamster.adapter.n.a().a.q());
            this.i.setAdapter(this.l);
        }
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case 248307114:
                if (str.equals("goods/desc")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() != 1) {
                    return;
                }
                if (TextUtils.isEmpty(this.d.t)) {
                    this.e = true;
                    return;
                }
                this.f.loadDataWithBaseURL(null, this.d.t, "text/html", "utf-8", null);
                this.e = false;
                return;
            default:
                return;
        }
    }
}
