package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import org.json.JSONObject;

final class aq implements OnClickListener {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ b b;

    aq(b bVar, JSONObject jSONObject) {
        this.b = bVar;
        this.a = jSONObject;
    }

    public final void onClick(View view) {
        this.b.j();
        this.b.b(this.b.a.aJ, this.a);
    }
}