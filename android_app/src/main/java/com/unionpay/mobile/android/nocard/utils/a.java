package com.unionpay.mobile.android.nocard.utils;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.analytics.pro.x;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.k;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class a {
    private static String a(Bundle bundle) {
        String str = null;
        if (bundle != null) {
            if (bundle.containsKey("paydata")) {
                str = bundle.getString("paydata");
            } else if (bundle.containsKey("tn")) {
                str = bundle.getString("tn");
            }
        }
        return str == null ? "" : str;
    }

    private static String a(String str) {
        byte[] a;
        String str2 = null;
        k.a("uppay", "decodePayInfo +++ \n");
        String decode = str != null ? URLDecoder.decode(str) : null;
        k.b("uppay", "url deocode result:" + decode);
        if (decode != null) {
            try {
                a = com.unionpay.mobile.android.utils.a.a(decode);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (a != null) {
                try {
                    str2 = new String(a, "UTF-8");
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
            k.a("uppay", "order info:" + str2 + "\n");
            k.a("uppay", "decodePayInfo --- \n");
            return str2;
        }
        a = null;
        if (a != null) {
            str2 = new String(a, "UTF-8");
        }
        k.a("uppay", "order info:" + str2 + "\n");
        k.a("uppay", "decodePayInfo --- \n");
        return str2;
    }

    public static boolean a(Intent intent, b bVar) {
        boolean z = false;
        if (intent != null) {
            k.c("uppay", " ===>" + intent.toString());
            String dataString = intent.getDataString();
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("reqOriginalId")) {
                k.c("uppay", " bundle===>" + extras.toString());
                bVar.I.a = extras.getInt("reqOriginalId");
                k.b("uppay", "reqID:" + bVar.I.a);
                bVar.I.e = extras.getBoolean("invokedbyplugin");
            } else if (dataString != null && dataString.length() > 0) {
                bVar.I.a = 1000;
                k.b("uppay", "nativeBrowser data:" + dataString);
            }
            if (extras != null) {
                bVar.aM = extras.getBoolean("dlgstyle", false);
                String string = extras.getString("url_index");
                if (string != null && i.c(string)) {
                    bVar.aO = Integer.parseInt(string);
                }
                Object string2 = extras.getString("server");
                if (!(string2 == null || TextUtils.isEmpty(string2))) {
                    bVar.bk = string2;
                }
                com.unionpay.mobile.android.d.a.M = extras.getInt("navbargb", -10705958);
                com.unionpay.mobile.android.d.a.N = extras.getInt("divlinecolor", -13268007);
            } else {
                bVar.aM = false;
                com.unionpay.mobile.android.d.a.M = -10705958;
                com.unionpay.mobile.android.d.a.N = -13268007;
            }
            switch (bVar.I.a) {
                case 0:
                case 2:
                    bVar.I.c = extras.getString("ex_mode");
                    k.a("uppay", "mServerIdentifier = " + bVar.I.c);
                    bVar.b = a(extras);
                    bVar.g = extras.getString("appID");
                    if (bVar.g == null) {
                        bVar.g = "";
                    }
                    dataString = extras.getString("source");
                    if (dataString != null) {
                        b.bm = dataString.equals("samsung_out");
                    }
                    dataString = extras.getString("frontNotifyByPlugin");
                    boolean z2 = dataString == null || dataString.length() == 0;
                    bVar.U = z2;
                    if (!(bVar.f || bVar.b == null || bVar.b.trim().length() <= 0)) {
                        k.a("uppay", "tn from bundle:" + bVar.b);
                        bVar.c = true;
                        k.a("uppay", "dw.isNewTypeTn=" + bVar.c);
                        z = true;
                    }
                    bVar.r = extras.getString("ResultURL");
                    k.a("uppay", "result URL:" + bVar.r);
                    break;
                case 1:
                    dataString = extras.getString("uppayuri");
                    bVar.I.b = extras.getString("resultIntentAction");
                    k.a("uppay", " result Action=" + bVar.I.b);
                    z = a(dataString, bVar);
                    break;
                case 3:
                    bVar.I.c = extras.getString("ex_mode");
                    bVar.I.g = extras.getString("tencentUID");
                    bVar.I.h = extras.getString("tencentWID");
                    bVar.b = a(extras);
                    if (bVar.b != null && bVar.b.trim().length() > 0 && bVar.I.h != null && bVar.I.h.trim().length() > 0) {
                        z = true;
                        break;
                    }
                case 4:
                    dataString = extras.getString("paydata");
                    k.b("PluginEx", " paydata=" + dataString);
                    z = b(a(dataString), bVar);
                    break;
                case 5:
                    bVar.I.c = extras.getString("ex_mode");
                    k.a("uppay", "mServerIdentifier = " + bVar.I.c);
                    bVar.g = extras.getString("appID");
                    if (bVar.g == null) {
                        bVar.g = "";
                    }
                    bVar.e = extras.getString("amt");
                    bVar.d = extras.getString("aid");
                    bVar.f = true;
                    bVar.c = true;
                    if (!(TextUtils.isEmpty(bVar.e) || TextUtils.isEmpty(bVar.d))) {
                        z = true;
                        break;
                    }
                case 1000:
                    z = a(dataString, bVar);
                    break;
            }
            if (bVar.aM && !b.bm) {
                com.unionpay.mobile.android.d.b.b = -1;
                com.unionpay.mobile.android.d.b.c = -2513882;
                com.unionpay.mobile.android.d.b.d = -6745;
            }
            if (bVar.I.a == 2 || bVar.I.a == 5 || bVar.I.a == 3) {
                bVar.I.d = true;
            }
            bVar.a = bVar.c ? "1.9" : "1.4";
        }
        return z;
    }

    private static boolean a(String str, b bVar) {
        String str2 = null;
        if (str == null) {
            return false;
        }
        String[] split = str.split("\\?");
        if (split.length < 2) {
            k.c("uppay", "uppay protocol params error!");
            return false;
        }
        boolean z;
        String str3 = split[1];
        k.a("uppay", "parseUPPayURIParams() +++ ");
        k.a("uppay", str3);
        str3 = null;
        for (String split2 : str3.split("&")) {
            String[] split3 = split2.split("=");
            if (split3.length >= 2) {
                if (split3[0].equalsIgnoreCase(x.P)) {
                    str3 = split3[1];
                } else if (split3[0].equalsIgnoreCase("paydata")) {
                    str2 = split3[1];
                }
            }
        }
        if (str3 == null || !str3.equalsIgnoreCase("token") || str2 == null) {
            z = false;
        } else {
            k.a("uppay", "paydata=" + str2);
            z = b(a(str2), bVar);
        }
        k.a("uppay", "parseUPPayURIParams() ---");
        return z;
    }

    private static boolean b(String str, b bVar) {
        if (str == null || str.length() == 0) {
            return false;
        }
        bVar.I.c = "00";
        for (String trim : str.split(",")) {
            String[] split = trim.trim().split("=");
            if (split.length >= 2) {
                if (split[0].trim().equalsIgnoreCase("tn")) {
                    bVar.b = split[1].trim();
                } else if (split[0].trim().equalsIgnoreCase("usetestmode")) {
                    if (split[1].trim().equalsIgnoreCase("true")) {
                        bVar.I.c = "01";
                    } else if (split[1].trim().equalsIgnoreCase("test")) {
                        bVar.I.c = "02";
                    } else if (split[1].trim().equalsIgnoreCase("inner")) {
                        bVar.I.c = "98";
                    }
                } else if (split[0].trim().equalsIgnoreCase("ResultURL")) {
                    try {
                        bVar.r = URLDecoder.decode(split[1].trim(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bVar.b != null && bVar.b.length() > 0;
    }
}
