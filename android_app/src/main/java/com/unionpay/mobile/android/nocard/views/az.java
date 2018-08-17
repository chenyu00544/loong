package com.unionpay.mobile.android.nocard.views;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tauth.AuthActivity;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

final class az implements OnClickListener {
    final /* synthetic */ o a;

    az(o oVar) {
        this.a = oVar;
    }

    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        Object a = j.a(jSONObject, "errMsg");
        if (a == null || TextUtils.isEmpty(a)) {
            o.a(this.a, j.a(jSONObject, AuthActivity.ACTION_KEY), j.a(jSONObject, "value"));
            return;
        }
        this.a.a((String) a);
    }
}
