package com.alipay.sdk.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.widget.TextView;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.b.c;
import com.alipay.sdk.d.a;
import com.alipay.sdk.f.b;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.i;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.tauth.AuthActivity;
import com.umeng.socialize.common.SocializeConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class d {
    private static a c;
    protected boolean a = true;
    protected boolean b = true;

    public abstract JSONObject a() throws JSONException;

    public List<Header> a(boolean z, String str) {
        List<Header> arrayList = new ArrayList();
        arrayList.add(new BasicHeader("msp-gzip", String.valueOf(z)));
        arrayList.add(new BasicHeader("Operation-Type", "alipay.msp.cashier.dispatch.bytes"));
        arrayList.add(new BasicHeader("content-type", "application/octet-stream"));
        arrayList.add(new BasicHeader("Version", SocializeConstants.PROTOCOL_VERSON));
        arrayList.add(new BasicHeader("AppId", "TAOBAO"));
        arrayList.add(new BasicHeader("Msp-Param", a.a(str)));
        arrayList.add(new BasicHeader("des-mode", "CBC"));
        return arrayList;
    }

    public String b() {
        return "4.9.0";
    }

    public String c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put("device", Build.MODEL);
        hashMap.put("namespace", "com.alipay.mobilecashier");
        hashMap.put("api_name", "com.alipay.mcpay");
        hashMap.put("api_version", b());
        return a(hashMap, new HashMap());
    }

    public static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put("method", str2);
        jSONObject.put(AuthActivity.ACTION_KEY, jSONObject2);
        return jSONObject;
    }

    public String a(String str, JSONObject jSONObject) {
        b a = b.a();
        com.alipay.sdk.g.b a2 = com.alipay.sdk.g.b.a();
        JSONObject a3 = com.alipay.sdk.util.b.a(new JSONObject(), jSONObject);
        try {
            String e;
            String a4;
            Object c;
            a3.put("tid", a2.a);
            String str2 = "user_agent";
            c a5 = c.a();
            Context context = b.a().a;
            com.alipay.sdk.util.a a6 = com.alipay.sdk.util.a.a(context);
            if (TextUtils.isEmpty(a5.a)) {
                String a7 = i.a();
                String b = i.b();
                e = i.e(context);
                a4 = h.a(context);
                a5.a = "Msp/15.2.2" + " (" + a7 + ";" + b + ";" + e + ";" + a4.substring(0, a4.indexOf(HttpConstant.SCHEME_SPLIT)) + ";" + i.f(context) + ";" + Float.toString(new TextView(context).getTextSize());
            }
            e = com.alipay.sdk.util.a.b(context).p;
            a4 = "-1;-1";
            String str3 = "1";
            String a8 = a6.a();
            String b2 = a6.b();
            Context context2 = b.a().a;
            SharedPreferences sharedPreferences = context2.getSharedPreferences("virtualImeiAndImsi", 0);
            CharSequence string = sharedPreferences.getString("virtual_imsi", null);
            if (TextUtils.isEmpty(string)) {
                if (TextUtils.isEmpty(com.alipay.sdk.g.b.a().a)) {
                    c = b.a().c();
                    string = TextUtils.isEmpty(c) ? c.b() : c.substring(3, 18);
                } else {
                    string = com.alipay.sdk.util.a.a(context2).a();
                }
                sharedPreferences.edit().putString("virtual_imsi", string).commit();
            }
            CharSequence charSequence = string;
            context2 = b.a().a;
            SharedPreferences sharedPreferences2 = context2.getSharedPreferences("virtualImeiAndImsi", 0);
            string = sharedPreferences2.getString("virtual_imei", null);
            if (TextUtils.isEmpty(string)) {
                string = TextUtils.isEmpty(com.alipay.sdk.g.b.a().a) ? c.b() : com.alipay.sdk.util.a.a(context2).b();
                sharedPreferences2.edit().putString("virtual_imei", string).commit();
            }
            CharSequence charSequence2 = string;
            if (a2 != null) {
                a5.c = a2.b;
            }
            String replace = Build.MANUFACTURER.replace(";", " ");
            String replace2 = Build.MODEL.replace(";", " ");
            boolean b3 = b.b();
            String str4 = a6.a;
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            String ssid = connectionInfo != null ? connectionInfo.getSSID() : WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            String bssid = connectionInfo != null ? connectionInfo.getBSSID() : "00";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a5.a).append(";").append(e).append(";").append(a4).append(";").append(str3).append(";").append(a8).append(";").append(b2).append(";").append(a5.c).append(";").append(replace).append(";").append(replace2).append(";").append(b3).append(";").append(str4).append(";-1;-1;").append(a5.b).append(";").append(charSequence).append(";").append(charSequence2).append(";").append(ssid).append(";").append(bssid);
            if (a2 != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("tid", a2.a);
                hashMap.put("utdid", b.a().c());
                c = a5.b(context, hashMap);
                if (!TextUtils.isEmpty(c)) {
                    stringBuilder.append(";").append(c);
                }
            }
            stringBuilder.append(")");
            a3.put(str2, stringBuilder.toString());
            a3.put("has_alipay", i.b(a.a));
            a3.put("has_msp_app", i.a(a.a));
            a3.put("external_info", str);
            a3.put("app_key", "2014052600006128");
            a3.put("utdid", a.c());
            a3.put("new_client_key", a2.b);
        } catch (Throwable th) {
        }
        return a3.toString();
    }

    public static String a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Entry entry : hashMap.entrySet()) {
            jSONObject2.put((String) entry.getKey(), entry.getValue());
        }
        JSONObject jSONObject3 = new JSONObject();
        for (Entry entry2 : hashMap2.entrySet()) {
            jSONObject3.put((String) entry2.getKey(), entry2.getValue());
        }
        jSONObject2.put("params", jSONObject3);
        jSONObject.put("data", jSONObject2);
        return jSONObject.toString();
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
            if (!jSONObject.has("params")) {
                return false;
            }
            Object optString = jSONObject.getJSONObject("params").optString("public_key", null);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            b.a();
            c.a().a(optString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public b a(Context context, String str) throws Throwable {
        return a(context, str, h.a(context), true);
    }

    public final b a(Context context, String str, String str2, boolean z) throws Throwable {
        String str3 = null;
        e eVar = new e(this.b);
        c a = eVar.a(new b(c(), a(str, a())), this.a);
        if (c == null) {
            c = new a(context, str2);
        } else if (!TextUtils.equals(str2, c.a)) {
            c.a = str2;
        }
        HttpResponse a2 = c.a(a.b, a(a.a, str));
        String str4 = "msp-gzip";
        if (a2 != null) {
            Header[] allHeaders = a2.getAllHeaders();
            if (allHeaders != null && allHeaders.length > 0) {
                for (Header header : allHeaders) {
                    if (header != null) {
                        String name = header.getName();
                        if (name != null && name.equalsIgnoreCase(str4)) {
                            str3 = header.getValue();
                            break;
                        }
                    }
                }
            }
        }
        b a3 = eVar.a(new c(Boolean.valueOf(str3).booleanValue(), a(a2)));
        if (a3 != null && a(a3.a) && z) {
            return a(context, str, str2, false);
        }
        return a3;
    }

    private static byte[] a(HttpResponse httpResponse) throws IllegalStateException, IOException {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bArr = new byte[1024];
        InputStream content;
        try {
            content = httpResponse.getEntity().getContent();
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = content.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr, 0, read);
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream = byteArrayOutputStream2;
                    }
                }
                bArr = byteArrayOutputStream2.toByteArray();
                if (content != null) {
                    try {
                        content.close();
                    } catch (Exception e) {
                    }
                }
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception e2) {
                }
                return bArr;
            } catch (Throwable th3) {
                th = th3;
                if (content != null) {
                    try {
                        content.close();
                    } catch (Exception e3) {
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            content = null;
            if (content != null) {
                content.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }
}
