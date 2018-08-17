package com.taobao.agoo.a.a;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.d.a;

/* compiled from: Taobao */
public class c extends b {
    public static final String JSON_CMD_REGISTER = "register";
    public String a;
    public String b;
    public String c;
    public String d = String.valueOf(Constants.SDK_VERSION_CODE);
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;

    public byte[] a() {
        byte[] bArr = null;
        try {
            ALog.i("RegisterDO", "buildData", "data", new a().a(b.JSON_CMD, this.e).a("appKey", this.a).a("utdid", this.b).a("appVersion", this.c).a(Constants.KEY_SDK_VERSION, this.d).a(Constants.KEY_TTID, this.f).a(Constants.KEY_PACKAGE_NAME, this.g).a("c0", this.h).a("c1", this.i).a("c2", this.j).a("c3", this.k).a("c4", this.l).a("c5", this.m).a("c6", this.n).a().toString());
            bArr = r1.getBytes("utf-8");
        } catch (Throwable th) {
            ALog.e("RegisterDO", "buildData", th, new Object[0]);
        }
        return bArr;
    }

    public static byte[] a(Context context, String str, String str2) {
        c cVar;
        byte[] a;
        Throwable th;
        c cVar2 = null;
        try {
            Object deviceId = UtilityImpl.getDeviceId(context);
            String packageName = context.getPackageName();
            Object obj = GlobalClientInfo.getInstance(context).getPackageInfo().versionName;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(deviceId) || TextUtils.isEmpty(obj)) {
                ALog.e("RegisterDO", "buildRegister param null", "appKey", str, "utdid", deviceId, "appVersion", obj);
                if (cVar2 == null) {
                    return cVar2;
                }
                cVar2.a();
                return cVar2;
            }
            cVar = new c();
            try {
                String subscriberId;
                cVar.e = JSON_CMD_REGISTER;
                cVar.a = str;
                cVar.b = deviceId;
                cVar.c = obj;
                cVar.f = str2;
                cVar.g = packageName;
                cVar.h = Build.BRAND;
                cVar.i = Build.MODEL;
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    packageName = telephonyManager.getDeviceId();
                } else {
                    Object obj2 = cVar2;
                }
                cVar.j = packageName;
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                } else {
                    deviceId = cVar2;
                }
                cVar.k = subscriberId;
                if (cVar != null) {
                    a = cVar.a();
                    return a;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    ALog.e("RegisterDO", "buildRegister", th, new Object[0]);
                    if (cVar != null) {
                        a = cVar.a();
                        return a;
                    }
                    a = cVar2;
                    return a;
                } catch (Throwable th3) {
                    th = th3;
                    cVar2 = cVar;
                    if (cVar2 != null) {
                        cVar2.a();
                    }
                    throw th;
                }
            }
            a = cVar2;
            return a;
        } catch (Throwable th4) {
            th = th4;
            if (cVar2 != null) {
                cVar2.a();
            }
            throw th;
        }
    }
}
