package com.unionpay.mobile.android.pro.views;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tauth.AuthActivity;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

final class b implements OnClickListener {
    final /* synthetic */ y a;

    b(y yVar) {
        this.a = yVar;
    }

    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a = j.a(jSONObject, "errMsg");
        if (a == null || TextUtils.isEmpty(a)) {
            y.a(this.a, j.a(jSONObject, AuthActivity.ACTION_KEY), j.a(jSONObject, "value") + ",\"carrier_tp\":" + (this.a.a.br ? "10" : "2"));
            return;
        }
        this.a.a(a);
    }
}
