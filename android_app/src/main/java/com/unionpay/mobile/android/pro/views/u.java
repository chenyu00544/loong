package com.unionpay.mobile.android.pro.views;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tauth.AuthActivity;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

final class u implements OnClickListener {
    final /* synthetic */ k a;

    u(k kVar) {
        this.a = kVar;
    }

    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a = j.a(jSONObject, "errMsg");
        if (a == null || TextUtils.isEmpty(a)) {
            k.a(this.a, j.a(jSONObject, AuthActivity.ACTION_KEY), j.a(jSONObject, "value") + ",\"carrier_tp\":\"7\"");
            return;
        }
        this.a.a(a);
    }
}
