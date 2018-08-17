package com.baidu.mtjstatsdk;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import anet.channel.strategy.dispatch.c;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.b;
import com.baidu.mtjstatsdk.b.d;
import com.baidu.mtjstatsdk.b.e;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import ru.truba.touchgallery.BuildConfig;

public class HeadObject {
    private String a = "Android";
    private String b = "1";
    private String c;
    private String d = null;
    private String e = null;
    private int f = 0;
    private String g;
    private String h;
    private int i;
    private int j;
    private String k = null;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;

    private static String a(Context context) {
        String e = e.e(context);
        return e != null ? e.replaceAll(":", "") : null;
    }

    private static String a(String str, Context context) {
        if (str == null) {
            return null;
        }
        if (!str.equals("000000000000000")) {
            return str;
        }
        str = a(context);
        d.a("statsdk", "imei=null,mac=" + str);
        return str;
    }

    public synchronized void constructHeader(Context context, String str) {
        if (this.f == 0) {
            if (a.a(this.d)) {
                d.a("statsdk", "constructHeader appkey= " + this.d);
            }
            b.d(context, MsgConstant.PERMISSION_READ_PHONE_STATE);
            b.d(context, MsgConstant.PERMISSION_INTERNET);
            b.d(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE);
            b.d(context, "android.permission.WRITE_SETTINGS");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            this.c = VERSION.SDK;
            this.m = Build.MODEL;
            try {
                this.h = telephonyManager.getDeviceId();
                this.h = a(this.h, context);
            } catch (Throwable e) {
                if (a.a(this.d)) {
                    d.a(e);
                }
            }
            if (this.h == null) {
                this.h = a(context);
            }
            if (this.h == null || this.h.equals("000000000000000")) {
                this.h = BasicStoreTools.getInstance().loadGenerateDeviceId(context);
            }
            if (this.h == null || this.h.equals("000000000000000")) {
                this.h = "hol" + (new Date().getTime() + "").hashCode() + "mes";
                BasicStoreTools.getInstance().setGenerateDeviceId(context, this.h);
                if (a.a(this.d)) {
                    d.a("statsdk", "设备id为空，系统生成id =" + this.h);
                }
            }
            if (this.e == null) {
                this.e = new DataCoreObject().a(context);
            }
            try {
                this.l = telephonyManager.getNetworkOperator();
            } catch (Throwable e2) {
                if (a.a(this.d)) {
                    d.a(e2);
                }
            }
            try {
                this.i = e.a(context);
                this.j = e.b(context);
                if (context.getResources().getConfiguration().orientation == 2) {
                    if (a.a(this.d)) {
                        d.a("statsdk", "Configuration.ORIENTATION_LANDSCAPE");
                    }
                    this.i ^= this.j;
                    this.j = this.i ^ this.j;
                    this.i ^= this.j;
                }
            } catch (Throwable e22) {
                if (a.a(this.d)) {
                    d.a(e22);
                }
            }
            try {
                if (this.k == null || this.k.equals("")) {
                    this.k = BasicStoreTools.getInstance().loadAppChannelWithPreferenceAndAppKey(context, str);
                }
            } catch (Throwable e222) {
                if (a.a(this.d)) {
                    d.a(e222);
                }
            }
            if (str != null) {
                this.d = str;
            }
            if (this.d == null) {
                this.d = e.a(context, "BaiduMobAd_STAT_ID");
            }
            try {
                if (this.g == null || this.g.equals("")) {
                    this.g = "0.1";
                }
            } catch (Throwable e2222) {
                if (a.a(this.d)) {
                    d.a(e2222);
                }
            }
            try {
                String a = e.a(context, "BaiduMobAd_CELL_LOCATION");
                if (a == null || !a.toLowerCase().equals("false")) {
                    this.n = e.c(context);
                    try {
                        a = e.a(context, "BaiduMobAd_GPS_LOCATION");
                        if (a == null && a.toLowerCase().equals("false")) {
                            this.o = "";
                            a = e.a(context, "BaiduMobAd_WIFI_LOCATION");
                            if (a == null) {
                            }
                            this.p = e.f(context);
                            this.q = e.g(context);
                        } else {
                            this.o = e.d(context);
                            try {
                                a = e.a(context, "BaiduMobAd_WIFI_LOCATION");
                                if (a == null && a.toLowerCase().equals("false")) {
                                    this.p = "";
                                    this.q = e.g(context);
                                } else {
                                    this.p = e.f(context);
                                    try {
                                        this.q = e.g(context);
                                    } catch (Throwable e22222) {
                                        if (a.a(this.d)) {
                                            d.a(e22222);
                                        }
                                    }
                                }
                            } catch (Throwable e222222) {
                                if (a.a(this.d)) {
                                    d.a(e222222);
                                }
                            }
                        }
                    } catch (Throwable e2222222) {
                        if (a.a(this.d)) {
                            d.a(e2222222);
                        }
                    }
                } else {
                    this.n = "0_0_0";
                    a = e.a(context, "BaiduMobAd_GPS_LOCATION");
                    if (a == null) {
                    }
                    this.o = e.d(context);
                    a = e.a(context, "BaiduMobAd_WIFI_LOCATION");
                    if (a == null) {
                    }
                    this.p = e.f(context);
                    this.q = e.g(context);
                }
            } catch (Throwable e22222222) {
                if (a.a(this.d)) {
                    d.a(e22222222);
                }
            }
        }
    }

    public String getAppKey() {
        return this.d;
    }

    public String getAppVersionName() {
        return this.g;
    }

    public String getCuid() {
        return this.e;
    }

    public String getappChannel() {
        return this.k;
    }

    public synchronized void installHeader(Context context, JSONObject jSONObject, String str) {
        constructHeader(context, str);
        if (a.a(this.d)) {
            d.a("statsdk", "installHeader appkey= " + this.d);
        }
        try {
            jSONObject.put("o", this.a == null ? "" : this.a);
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_ST, this.b == null ? "1" : this.b);
            jSONObject.put("s", this.c == null ? "" : this.c);
            jSONObject.put("k", this.d == null ? "" : this.d);
            jSONObject.put("i", this.e == null ? "" : this.e);
            jSONObject.put(c.VERSION, BuildConfig.VERSION_NAME);
            jSONObject.put("a", this.f);
            jSONObject.put("n", this.g == null ? "" : this.g);
            jSONObject.put("d", this.h == null ? "" : this.h);
            jSONObject.put("w", this.i);
            jSONObject.put("h", this.j);
            jSONObject.put("c", this.k == null ? "" : this.k);
            jSONObject.put("op", this.l == null ? "" : this.l);
            jSONObject.put("m", this.m == null ? "" : this.m);
            jSONObject.put("cl", this.n);
            jSONObject.put("gl", this.o == null ? "" : this.o);
            jSONObject.put("wl", this.p == null ? "" : this.p);
            jSONObject.put("l", this.q == null ? "" : this.q);
            jSONObject.put(c.TIMESTAMP, System.currentTimeMillis());
            jSONObject.put("sq", 0);
            if (a.a(this.d)) {
                d.a("statsdk", jSONObject.toString());
            }
        } catch (JSONException e) {
            r0 = "header ini error";
            if (a.a(this.d)) {
                String str2;
                d.a("statsdk", str2);
            }
            throw new RuntimeException(str2);
        }
    }

    public void setAppChannel(String str) {
        this.k = str;
    }

    public void setAppKey(String str) {
        this.d = str;
    }

    public void setAppVersionName(String str) {
        this.g = str;
    }

    public void setCuid(String str) {
        this.e = str;
    }
}
