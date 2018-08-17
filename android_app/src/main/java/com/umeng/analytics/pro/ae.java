package com.umeng.analytics.pro;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.umeng.message.MsgConstant;

/* compiled from: ImeiTracker */
public class ae extends y {
    private static final String a = "imei";
    private Context b;

    public ae(Context context) {
        super("imei");
        this.b = context;
    }

    public String f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        if (telephonyManager == null) {
        }
        try {
            if (bt.a(this.b, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                return telephonyManager.getDeviceId();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
