package com.unionpay;

import com.unionpay.utils.UPUtils;
import java.util.Iterator;
import org.json.JSONObject;

final class t implements d {
    final /* synthetic */ UPPayWapActivity a;

    t(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void a(String str, e eVar) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                UPUtils.a(this.a, jSONObject.getString(str2), str2);
            }
            if (eVar != null) {
                UPPayWapActivity uPPayWapActivity = this.a;
                eVar.a(UPPayWapActivity.b("0", "success", null));
            }
        } catch (Exception e) {
            if (eVar != null) {
                UPPayWapActivity uPPayWapActivity2 = this.a;
                eVar.a(UPPayWapActivity.b("1", e.getMessage(), null));
            }
        }
    }
}
