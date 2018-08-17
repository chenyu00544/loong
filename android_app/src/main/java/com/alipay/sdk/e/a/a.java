package com.alipay.sdk.e.a;

import com.alipay.sdk.e.d;
import com.tencent.open.utils.SystemUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class a extends d {
    protected final String b() {
        return SystemUtils.QQ_VERSION_NAME_5_0_0;
    }

    protected final JSONObject a() throws JSONException {
        return d.a("sdkConfig", "obtain");
    }
}
