package com.unionpay;

import android.os.Handler.Callback;
import android.os.Message;
import android.util.Base64;
import com.taobao.accs.common.Constants;
import com.unionpay.utils.UPUtils;
import com.unionpay.utils.b;
import org.json.JSONArray;
import org.json.JSONObject;

final class f implements Callback {
    f() {
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1001:
                a.C = true;
                a.a(a.t, a.I, a.B);
                break;
            case 1002:
                try {
                    if (message.obj != null) {
                        JSONObject jSONObject = new JSONObject((String) message.obj);
                        String a = com.unionpay.utils.f.a(jSONObject, "sign");
                        int i = 0;
                        try {
                            i = Integer.parseInt(a.x);
                        } catch (NumberFormatException e) {
                        }
                        String str = new String(Base64.decode(jSONObject.getString("configs"), 2));
                        if (UPUtils.forConfig(i, a).equals(b.a(UPUtils.a(str + a.u)))) {
                            UPUtils.a(a.t, (String) message.obj, "configs");
                            UPUtils.a(a.t, a.x, Constants.KEY_MODE);
                            UPUtils.a(a.t, a.u, "or");
                            if (!a.C) {
                                a.I = a.b(new JSONArray(str), "sort");
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (!a.C) {
                    a.a(a.t, a.I, a.B);
                    break;
                }
                break;
        }
        return true;
    }
}
