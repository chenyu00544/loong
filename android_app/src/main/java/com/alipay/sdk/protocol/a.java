package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.umeng.message.entity.UInAppMessage;

public enum a {
    None(UInAppMessage.NONE),
    WapPay("js://wappay"),
    Update("js://update");
    
    private String d;

    private a(String str) {
        this.d = str;
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return None;
        }
        a aVar = None;
        for (a aVar2 : values()) {
            if (str.startsWith(aVar2.d)) {
                return aVar2;
            }
        }
        return aVar;
    }
}
