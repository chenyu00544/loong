package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.utils.e;
import com.unionpay.utils.h;
import org.json.JSONException;
import org.json.JSONObject;

public class UPPayWapActivity extends Activity {
    LinearLayout a;
    private WebView b;
    private WebViewJavascriptBridge c;
    private AlertDialog d;

    static /* synthetic */ void a(UPPayWapActivity uPPayWapActivity, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("pay_result", str);
        intent.putExtra("result_data", str2);
        uPPayWapActivity.setResult(-1, intent);
        uPPayWapActivity.finish();
    }

    static /* synthetic */ void a(UPPayWapActivity uPPayWapActivity, boolean z) {
        uPPayWapActivity.a.setVisibility(z ? 0 : 8);
    }

    private static String b(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject.put("code", str);
            }
            if (str2 != null) {
                jSONObject.put("msg", str2);
            }
            if (str3 != null) {
                jSONObject.put("value", str3);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void onCreate(Bundle bundle) {
        OnClickListener pVar;
        CharSequence stringExtra;
        super.onCreate(bundle);
        String stringExtra2 = getIntent().getStringExtra("waptype");
        String str = "";
        String str2 = "";
        String str3;
        Object obj;
        if (stringExtra2 == null || !stringExtra2.equals("new_page")) {
            stringExtra2 = getIntent().getStringExtra("wapurl");
            str2 = getIntent().getStringExtra("paydata");
            stringExtra2 = str2 != null ? stringExtra2 + "?s=" + str2 : str;
            str2 = h.a().e;
            pVar = new p(this);
            str3 = str2;
            str2 = stringExtra2;
            obj = str3;
        } else {
            String stringExtra3 = getIntent().getStringExtra("wapurl");
            stringExtra = getIntent().getStringExtra("waptitle");
            if (stringExtra3 != null) {
                str = stringExtra3;
            }
            if (stringExtra == null) {
                obj = str2;
            }
            str3 = str;
            pVar = new l(this);
            str2 = str3;
        }
        getWindow().requestFeature(1);
        View linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams);
        int a = e.a(this, TitleBar.SHAREBTN_RIGHT_MARGIN);
        int a2 = e.a(this, 52.0f);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, a2));
        relativeLayout.setBackgroundColor(-10705958);
        this.a = new LinearLayout(this);
        this.a.setPadding(a, a, a, a);
        this.a.setGravity(16);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.leftMargin = a;
        this.a.setOnClickListener(pVar);
        relativeLayout.addView(this.a, layoutParams2);
        int a3 = e.a(this, TitleBar.BACKBTN_LEFT_MARGIN);
        a = e.a(this, 11.0f);
        View imageView = new ImageView(this);
        imageView.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeStream(getClass().getResourceAsStream("res/nav_back.png"))));
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a, a3);
        layoutParams3.addRule(15, -1);
        this.a.addView(imageView, layoutParams3);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(e.a(this, 320.0f), a2);
        layoutParams4.addRule(13, -1);
        View textView = new TextView(this);
        textView.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
        textView.setTextColor(-1);
        textView.setText(stringExtra);
        textView.setGravity(17);
        textView.setSingleLine(true);
        textView.setEllipsize(TruncateAt.END);
        relativeLayout.addView(textView, layoutParams4);
        linearLayout.addView(relativeLayout);
        this.b = new WebView(this);
        this.b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        linearLayout.addView(this.b);
        setContentView(linearLayout);
        this.c = new WebViewJavascriptBridge(this, this.b, null);
        this.b.loadUrl(str2);
        this.c.registerHandler("getDeviceInfo", new s(this));
        this.c.registerHandler("saveData", new t(this));
        this.c.registerHandler("getData", new u(this));
        this.c.registerHandler("removeData", new v(this));
        this.c.registerHandler("setPageBackEnable", new w(this));
        this.c.registerHandler("payBySDK", new x(this));
        this.c.registerHandler("downloadApp", new y(this));
        this.c.registerHandler("payResult", new m(this));
        this.c.registerHandler("closePage", new n(this));
        this.c.registerHandler("openNewPage", new o(this));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        onPause();
        return true;
    }
}
