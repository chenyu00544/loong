package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.nocard.views.ah;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.ad;
import org.json.JSONArray;
import org.json.JSONObject;

public final class m extends RelativeLayout {
    private Context a;
    private ImageView b;
    private LinearLayout c;
    private LinearLayout d;
    private Drawable e;
    private Drawable f;

    public m(Context context) {
        super(context);
        this.a = context;
        int a = g.a(context, TitleBar.SHAREBTN_RIGHT_MARGIN);
        setPadding(a, a, a, a);
        setBackgroundColor(-1);
        setOnClickListener(new n(this));
        int a2 = g.a(context, 15.0f);
        this.b = new ImageView(context);
        this.b.setId(this.b.hashCode());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(12, -1);
        addView(this.b, layoutParams);
        this.c = new LinearLayout(context);
        this.c.setOrientation(1);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.rightMargin = a;
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(0, this.b.getId());
        addView(this.c, layoutParams2);
    }

    static /* synthetic */ void a(m mVar) {
        int i = 8;
        if (mVar.d.getVisibility() == 8) {
            i = 0;
        }
        mVar.d.setVisibility(i);
        mVar.b.setBackgroundDrawable(i == 0 ? mVar.e : mVar.f);
    }

    public final void a(Drawable drawable, Drawable drawable2) {
        this.e = drawable;
        this.f = drawable2;
    }

    public final void a(boolean z, JSONArray jSONArray, JSONObject jSONObject) {
        int i = 2;
        this.c.removeAllViews();
        if (this.f != null) {
            this.b.setBackgroundDrawable(this.f);
        }
        int i2 = (jSONArray == null || jSONArray.length() == 0) ? 0 : 1;
        if (z || jSONArray == null) {
            i = i2;
        } else if (jSONArray.length() <= 2) {
            i = jSONArray.length();
        }
        if (jSONArray == null || i == 0) {
            k.c("uppay", "init order detail = null!!!");
            return;
        }
        this.c.addView(ah.a(this.a, jSONArray, 0, i), new LinearLayout.LayoutParams(-1, -2));
        this.d = ah.a(this.a, jSONArray, i, jSONArray.length());
        if (jSONObject != null) {
            View adVar = new ad(this.a, jSONObject, "");
            adVar.g();
            adVar.a(b.m);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = g.a(this.a, 8.0f);
            this.d.addView(adVar, layoutParams);
        }
        this.d.setVisibility(8);
        this.c.addView(this.d, new LinearLayout.LayoutParams(-1, -2));
    }
}
