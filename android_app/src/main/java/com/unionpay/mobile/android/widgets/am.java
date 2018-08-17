package com.unionpay.mobile.android.widgets;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tauth.AuthActivity;
import com.unionpay.mobile.android.f.c;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

final class am implements OnClickListener {
    final /* synthetic */ k a;

    am(k kVar) {
        this.a = kVar;
    }

    public final void onClick(View view) {
        Iterator it;
        if (this.a.r) {
            JSONObject jSONObject = new JSONObject();
            try {
                if (TextUtils.isEmpty(this.a.a())) {
                    jSONObject.put("errMsg", String.format(c.bD.aC, new Object[]{c.bD.C}));
                    view.setTag(jSONObject);
                    it = this.a.o.iterator();
                    while (it.hasNext()) {
                        ((OnClickListener) it.next()).onClick(view);
                    }
                }
                if (this.a.a().matches("[A-Za-z0-9]{8,32}")) {
                    this.a.a(true);
                    jSONObject.put("value", this.a.h());
                    jSONObject.put(AuthActivity.ACTION_KEY, this.a.t);
                } else {
                    jSONObject.put("errMsg", String.format(c.bD.aD, new Object[]{c.bD.C}));
                }
                view.setTag(jSONObject);
                it = this.a.o.iterator();
                while (it.hasNext()) {
                    ((OnClickListener) it.next()).onClick(view);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            this.a.b.e();
            this.a.a(false);
            it = this.a.p.iterator();
            while (it.hasNext()) {
                ((OnClickListener) it.next()).onClick(view);
            }
        }
    }
}
