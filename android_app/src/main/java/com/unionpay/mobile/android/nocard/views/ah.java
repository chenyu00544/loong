package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.location.Location;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build.VERSION;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.accs.common.Constants;
import com.tencent.tauth.AuthActivity;
import com.umeng.analytics.pro.x;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.c;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.ad;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ah {
    public static LinearLayout a(Context context, JSONArray jSONArray, int i, int i2) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(1);
        new LayoutParams(-1, -2).topMargin = a.d;
        JSONObject jSONObject = null;
        while (i < i2 && i < jSONArray.length()) {
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            linearLayout.addView(new ad(context, a.I, jSONObject, ""));
            i++;
        }
        return linearLayout;
    }

    public static String a(Context context, String str, String str2, String str3, String str4, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            String str6;
            jSONObject.put("tn", str);
            jSONObject.put("user", f.d(context));
            jSONObject.put("locale", a(f.a()));
            jSONObject.put("terminal_version", f.a(context));
            jSONObject.put("terminal_resolution", f.d());
            jSONObject.put("os_name", str2);
            jSONObject.put(x.q, VERSION.RELEASE.trim());
            jSONObject.put(x.v, a(f.c()));
            jSONObject.put("terminal_type", str3);
            jSONObject.put("appId", str4);
            jSONObject.put("uid", PreferenceUtils.a(context));
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, a(f.c(context)));
            try {
                jSONObject.put("time_zone", a(f.f()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                jSONObject.put("network_mode", f.f(context));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                jSONObject.put(Constants.KEY_IMSI, a(f.e(context)));
            } catch (Exception e22) {
                e22.printStackTrace();
            }
            try {
                jSONObject.put("baseband_version", a(f.e()));
            } catch (Exception e222) {
                e222.printStackTrace();
            }
            try {
                str6 = "support_map";
                StringBuffer stringBuffer = new StringBuffer("000");
                if (!"000".equals(str5)) {
                    stringBuffer.setCharAt(2, '1');
                }
                if (VERSION.SDK_INT >= 10) {
                    NfcAdapter defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter();
                    if (defaultAdapter != null) {
                        if (defaultAdapter.isEnabled()) {
                            stringBuffer.setCharAt(0, '1');
                        } else {
                            stringBuffer.setCharAt(0, '2');
                        }
                        if (VERSION.SDK_INT >= 19 && context.getPackageManager().hasSystemFeature("android.hardware.nfc.hce")) {
                            stringBuffer.setCharAt(1, '1');
                        }
                    }
                }
                jSONObject.put(str6, stringBuffer.toString());
            } catch (Exception e2222) {
                e2222.printStackTrace();
            }
            try {
                jSONObject.put("se_map", str5);
            } catch (Exception e22222) {
                e22222.printStackTrace();
            }
            jSONObject.put("root", f.b());
            jSONObject.put(x.G, a(Locale.getDefault().getCountry()));
            jSONObject.put(com.umeng.message.common.a.c, a(f.b(context)));
            str6 = ParamKey.LATITUDE;
            Location g = f.g(context);
            jSONObject.put(str6, a(g != null ? Double.valueOf(g.getLatitude()).toString() : ""));
            str6 = ParamKey.LONGITUDE;
            g = f.g(context);
            jSONObject.put(str6, a(g != null ? Double.valueOf(g.getLongitude()).toString() : ""));
            jSONObject.put("tel", a(f.h(context)));
            jSONObject.put("packageId", c.b(context));
        } catch (JSONException e3) {
            e3.printStackTrace();
        } catch (PatternSyntaxException e4) {
            e4.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        jSONObject2 = jSONObject2.substring(1, jSONObject2.length() - 1);
        k.a("uppay", "init: " + jSONObject2);
        return jSONObject2;
    }

    private static String a(String str) throws PatternSyntaxException {
        return str != null ? Pattern.compile("[\":,\\[\\]{}]").matcher(str).replaceAll("").trim() : "";
    }

    public static String a(String str, String str2, String str3, String str4) {
        return String.format("\"first_pay_flag\":\"%s\",\"card\":\"%s\",\"pay_type\":\"%s\",\"pay_mode\":\"%s\"", new Object[]{str, str2, str3, str4});
    }

    public static String a(JSONObject jSONObject) {
        k.a("uppay", "action:" + j.a(jSONObject, AuthActivity.ACTION_KEY));
        return j.a(jSONObject, AuthActivity.ACTION_KEY);
    }

    public static String b(Context context, String str, String str2, String str3, String str4, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("amount", str);
            jSONObject.put("aid", str5);
            jSONObject.put("user", f.d(context));
            jSONObject.put("locale", a(f.a()));
            jSONObject.put("terminal_version", f.a(context));
            jSONObject.put("terminal_resolution", f.d());
            jSONObject.put("os_name", str2);
            jSONObject.put(x.q, VERSION.RELEASE.trim());
            jSONObject.put(x.v, a(f.c()));
            jSONObject.put("terminal_type", str3);
            jSONObject.put("appId", str4);
            jSONObject.put("uid", PreferenceUtils.a(context));
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, a(f.c(context)));
            try {
                jSONObject.put("time_zone", a(f.f()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                jSONObject.put("network_mode", f.f(context));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                jSONObject.put(Constants.KEY_IMSI, a(f.e(context)));
            } catch (Exception e22) {
                e22.printStackTrace();
            }
            try {
                jSONObject.put("baseband_version", a(f.e()));
            } catch (Exception e222) {
                e222.printStackTrace();
            }
            jSONObject.put("root", f.b());
            jSONObject.put(x.G, a(Locale.getDefault().getCountry()));
            jSONObject.put(com.umeng.message.common.a.c, a(f.b(context)));
        } catch (JSONException e3) {
            e3.printStackTrace();
        } catch (PatternSyntaxException e4) {
            e4.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        jSONObject2 = jSONObject2.substring(1, jSONObject2.length() - 1);
        k.c("uppay", "init: " + jSONObject2);
        return jSONObject2;
    }

    public static String b(String str, String str2, String str3, String str4) {
        return String.format("\"first_pay_flag\":\"%s\",%s,\"pay_type\":\"%s\",\"pay_mode\":\"%s\"", new Object[]{str, str2, str3, str4});
    }

    public static String c(String str, String str2, String str3, String str4) {
        String str5 = "\"pay_type\":\"%s\",\"pay_mode\":\"%s\",%s";
        String str6 = "\"pay_type\":\"%s\",\"pay_mode\":\"%s\",%s,%s";
        if (str3 == null || str3.length() <= 0) {
            return String.format(str5, new Object[]{str, str2, str4});
        }
        return String.format(str6, new Object[]{str, str2, str3, str4});
    }
}
