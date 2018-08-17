package com.alipay.b.f;

import android.content.Context;
import com.alipay.b.h.a;
import com.taobao.accs.common.Constants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONObject;

public final class e {
    public static f a(Context context) {
        if (context == null) {
            return null;
        }
        String a = a.a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (com.alipay.c.a.a.a.a.a(a)) {
            a = a.a("device_feature_file_name", "device_feature_file_key");
        }
        if (com.alipay.c.a.a.a.a.a(a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            f fVar = new f();
            fVar.a(jSONObject.getString("imei"));
            fVar.b(jSONObject.getString(Constants.KEY_IMSI));
            fVar.c(jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_MAC));
            fVar.d(jSONObject.getString("bluetoothmac"));
            fVar.e(jSONObject.getString("gsi"));
            return fVar;
        } catch (Throwable e) {
            com.alipay.b.c.a.a(e);
            return null;
        }
    }
}
