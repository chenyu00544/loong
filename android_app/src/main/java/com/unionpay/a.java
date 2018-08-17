package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.text.TextUtils;
import android.util.Base64;
import anet.channel.strategy.dispatch.c;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.analytics.pro.x;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.unionpay.utils.UPUtils;
import com.unionpay.utils.b;
import com.unionpay.utils.d;
import com.unionpay.utils.f;
import com.unionpay.utils.g;
import com.unionpay.utils.h;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static String A = "";
    private static int B = 0;
    private static boolean C = false;
    private static com.unionpay.a.a D = null;
    private static Handler E = null;
    private static String F = "application/vnd.android.package-archive";
    private static String G = "http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin";
    private static String H = "[{\"type\":\"app\",\"sort\":100,\"package_info\":[{\"schema\":\"com.unionpay.uppay\",\"version\":\".*\",\"sign\":\"23137B5BE6AEF6682B41E6536F08367E0949A1CC\",\"sort\":101},{\"schema\":\"com.unionpay.tsmservice\",\"version\":\"^[1-9].*|^0[2-9].*|^01\\.[1-9].*|^01\\.0[1-9].*|^01\\.00\\.[1-9].*|^01\\.00\\.0[8-9].*\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":102}],\"need_install\":false,\"install_msg\":\"请先安装银联在线支付服务，安装完成后重新发起付款\",\"url\":\"https://mobile.unionpay.com/getclient?platform=android&type=securepayplugin\",\"download_app\":\"UPPayPluginEx.apk\",\"download_title\":\"银联在线支付服务\",\"download_desp\":\"正在下载银联在线支付服务…\",\"md5\":\"D75BB2802E61738A9A03BF014F927D9A\"},{\"type\":\"jar\",\"sort\":200}]";
    private static JSONArray I;
    private static IntentFilter J = new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
    private static BroadcastReceiver K = new b();
    private static final Callback L = new f();
    private static String a = "SpId";
    private static String b = "paydata";
    private static String c = "SysProvide";
    private static String d = "UseTestMode";
    private static String e = "SecurityChipType";
    private static String f = "uppayuri";
    private static String g = "resultIntentAction";
    private static String h = "reqOriginalId";
    private static String i = "wapurl";
    private static String j = "dlgstyle";
    private static String k = "com.unionpay.uppay";
    private static String l = "com.unionpay.uppay.PayActivity";
    private static String m = "ex_mode";
    private static String n = "server";
    private static String o = "source";
    private static String p = "samsung_out";
    private static String q = "";
    private static boolean r = false;
    private static int s = 10;
    private static Context t;
    private static String u = "";
    private static String v = null;
    private static String w = null;
    private static String x = "";
    private static String y = "";
    private static boolean z = false;

    private static String a(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            String absolutePath = context.getFilesDir().getAbsolutePath();
            if (absolutePath != null) {
                absolutePath = absolutePath + File.separator + str;
                if (new File(absolutePath).exists()) {
                    return absolutePath;
                }
                FileOutputStream openFileOutput = context.openFileOutput(str, 1);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read > 0) {
                        openFileOutput.write(bArr, 0, read);
                    } else {
                        openFileOutput.close();
                        open.close();
                        return absolutePath;
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    static String a(Context context, boolean z) {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            i = Integer.parseInt(x);
        } catch (NumberFormatException e) {
        }
        if (!z) {
            try {
                jSONObject.put("tn", UPUtils.forWap(i, b.a(u)));
            } catch (JSONException e2) {
                e2.printStackTrace();
            } catch (PatternSyntaxException e3) {
                e3.printStackTrace();
            }
        }
        jSONObject.put("imei", d.b(context));
        jSONObject.put("locale", Locale.getDefault().toString().startsWith("zh") ? "zh_CN" : "en_US");
        jSONObject.put("terminal_version", "3.3.7");
        jSONObject.put("terminal_resolution", (context.getResources().getDisplayMetrics().widthPixels + "*" + context.getResources().getDisplayMetrics().heightPixels).trim());
        jSONObject.put("os_name", c.ANDROID);
        jSONObject.put(x.q, VERSION.RELEASE.trim());
        String str = x.v;
        String trim = Build.MODEL.trim();
        if (trim != null) {
            trim.replace(" ", "");
        }
        jSONObject.put(str, trim);
        jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, d.a(context));
        try {
            jSONObject.put("time_zone", TimeZone.getDefault().getDisplayName(false, 0));
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            trim = "network_mode";
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            Object obj = (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) ? "disConnect" : activeNetworkInfo.getType() == 0 ? activeNetworkInfo.getState() == State.CONNECTED ? "mobile:" + activeNetworkInfo.getExtraInfo() : "mobile" : activeNetworkInfo.getType() == 1 ? UtilityImpl.NET_TYPE_WIFI : c.OTHER;
            jSONObject.put(trim, obj);
        } catch (Exception e42) {
            e42.printStackTrace();
        }
        try {
            jSONObject.put(Constants.KEY_IMSI, d.c(context));
        } catch (Exception e422) {
            e422.printStackTrace();
        }
        try {
            jSONObject.put("baseband_version", b(d.a()));
        } catch (Exception e4222) {
            e4222.printStackTrace();
        }
        try {
            jSONObject.put("root", new File("/system/bin/su").exists() ? "1" : "0");
        } catch (Exception e42222) {
            e42222.printStackTrace();
        }
        try {
            trim = "support_map";
            StringBuffer stringBuffer = new StringBuffer("000");
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
            jSONObject.put(trim, stringBuffer.toString());
        } catch (Exception e422222) {
            e422222.printStackTrace();
        }
        jSONObject.put(x.G, b(Locale.getDefault().getCountry()));
        trim = com.umeng.message.common.a.c;
        str = ((Activity) context).getPackageName();
        if (str == null) {
            str = "";
        }
        jSONObject.put(trim, b(str));
        trim = ParamKey.LATITUDE;
        Location d = d.d(context);
        jSONObject.put(trim, b(d != null ? Double.valueOf(d.getLatitude()).toString() : ""));
        trim = ParamKey.LONGITUDE;
        d = d.d(context);
        jSONObject.put(trim, b(d != null ? Double.valueOf(d.getLongitude()).toString() : ""));
        jSONObject.put("tel", b(d.e(context)));
        if (z) {
            try {
                Class.forName("com.unionpay.uppay.PayActivity");
                jSONObject.put("has_sdk", "1");
            } catch (ClassNotFoundException e5) {
                jSONObject.put("has_sdk", "0");
            }
        }
        str = jSONObject.toString();
        g.b("uppay", "init: " + str);
        return str;
    }

    static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        Object obj = 1;
        Object a = a(context, str2);
        if (a == null || TextUtils.isEmpty(a)) {
            obj = null;
        } else {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse("file:" + a), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
        if (obj == null) {
            y = str5;
            A = str2;
            if (!z) {
                try {
                    context.registerReceiver(K, J);
                    DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
                    Request request = new Request(Uri.parse(str));
                    request.setMimeType(F);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str2);
                    request.setTitle(str3);
                    request.setDescription(str4);
                    request.setNotificationVisibility(1);
                    UPUtils.a(context, downloadManager.enqueue(request), "id");
                    z = true;
                } catch (Exception e) {
                    if (e instanceof IllegalArgumentException) {
                        Builder builder = new Builder(context);
                        builder.setTitle(h.a().d);
                        builder.setMessage(h.a().f);
                        builder.setPositiveButton(h.a().b, new i(context));
                        builder.setNegativeButton(h.a().c, new j(context));
                        builder.create().show();
                        return;
                    }
                    context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        }
    }

    static /* synthetic */ void a(Context context, JSONArray jSONArray, int i) {
        Context context2 = context;
        while (jSONArray != null && i < jSONArray.length()) {
            Object a = f.a(jSONArray, i);
            if (a != null) {
                JSONObject jSONObject = (JSONObject) a;
                String a2 = f.a(jSONObject, "type");
                String a3;
                if ("app".equals(a2)) {
                    Builder builder;
                    JSONArray c = f.c(jSONObject, "package_info");
                    CharSequence a4 = f.a(jSONObject, "install_msg");
                    a2 = f.a(jSONObject, "url");
                    boolean b = f.b(jSONObject, "need_install");
                    String a5 = f.a(jSONObject, "download_app");
                    String a6 = f.a(jSONObject, "download_title");
                    String a7 = f.a(jSONObject, "download_desp");
                    String a8 = f.a(jSONObject, "md5");
                    String a9 = f.a(jSONObject, "app_server");
                    JSONArray b2 = b(c, "sort");
                    if (b2 != null && b2.length() > 0) {
                        int length = b2.length();
                        for (int i2 = 0; i2 < length; i2++) {
                            a = f.a(b2, i2);
                            if (a != null) {
                                jSONObject = (JSONObject) a;
                                String a10 = f.a(jSONObject, "schema");
                                String a11 = f.a(jSONObject, "sign");
                                a3 = f.a(jSONObject, "version");
                                if (b.a(context2, a10) && a11.equalsIgnoreCase(b.b(context2, a10)) && b.c(context2, a10).matches(a3)) {
                                    Bundle bundle = new Bundle();
                                    a(u, bundle, x);
                                    bundle.putString(a, v);
                                    bundle.putString(c, w);
                                    bundle.putString(b, u);
                                    bundle.putString(o, q);
                                    bundle.putBoolean(j, r);
                                    bundle.putString(n, a9);
                                    bundle.putString(e, null);
                                    bundle.putInt(h, 0);
                                    Intent intent = new Intent();
                                    intent.putExtras(bundle);
                                    intent.setClassName(a10, l);
                                    if (t instanceof Activity) {
                                        ((Activity) t).startActivityForResult(intent, s);
                                    } else {
                                        intent.addFlags(268435456);
                                        t.startActivity(intent);
                                    }
                                    t = null;
                                    a = 1;
                                    if (a != null) {
                                        return;
                                    }
                                    if (b) {
                                        jSONArray = I;
                                        i = B + 1;
                                        B = i;
                                    } else {
                                        builder = new Builder(context2);
                                        builder.setTitle(h.a().d);
                                        builder.setMessage(a4);
                                        builder.setPositiveButton(h.a().b, new g(context2, a2, a5, a6, a7, a8));
                                        builder.setNegativeButton(h.a().c, new h(context2));
                                        builder.create().show();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    a = null;
                    if (a != null) {
                        if (b) {
                            jSONArray = I;
                            i = B + 1;
                            B = i;
                        } else {
                            builder = new Builder(context2);
                            builder.setTitle(h.a().d);
                            builder.setMessage(a4);
                            builder.setPositiveButton(h.a().b, new g(context2, a2, a5, a6, a7, a8));
                            builder.setNegativeButton(h.a().c, new h(context2));
                            builder.create().show();
                            return;
                        }
                    }
                    return;
                } else if ("wap".equals(a2)) {
                    if (p.equals(q)) {
                        jSONArray = I;
                        i = B + 1;
                        B = i;
                    } else {
                        try {
                            a3 = (String) jSONObject.get("url");
                        } catch (Exception e) {
                            a3 = "";
                        }
                        Bundle bundle2 = new Bundle();
                        a(u, bundle2, x);
                        bundle2.putString(a, v);
                        bundle2.putString(c, w);
                        int i3 = 0;
                        try {
                            i3 = Integer.parseInt(x);
                        } catch (NumberFormatException e2) {
                        }
                        bundle2.putString(b, UPUtils.forWap(i3, b.a(u)));
                        bundle2.putString(i, a3);
                        Intent intent2 = new Intent();
                        intent2.putExtras(bundle2);
                        intent2.setClass(t, UPPayWapActivity.class);
                        ((Activity) t).startActivityForResult(intent2, 100);
                        return;
                    }
                } else if ("jar".equals(a2)) {
                    a(f.a(jSONObject, "app_server"));
                    return;
                } else {
                    return;
                }
            }
            return;
        }
    }

    static void a(String str) {
        Bundle bundle = new Bundle();
        a(u, bundle, x);
        bundle.putString(a, v);
        bundle.putString(c, w);
        bundle.putString(b, u);
        bundle.putString(o, q);
        bundle.putString(n, str);
        bundle.putBoolean(j, r);
        bundle.putInt(h, 2);
        try {
            Class cls = Class.forName("com.unionpay.uppay.PayActivity");
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(t, cls);
            if (t instanceof Activity) {
                ((Activity) t).startActivityForResult(intent, s);
            } else {
                intent.addFlags(268435456);
                t.startActivity(intent);
            }
            t = null;
        } catch (ClassNotFoundException e) {
        }
    }

    private static void a(String str, Bundle bundle, String str2) {
        if (str != null && str.trim().length() > 0) {
            if (str.trim().charAt(0) != '<') {
                bundle.putString(m, str2);
            } else if (str2 == null || !str2.trim().equalsIgnoreCase("00")) {
                bundle.putBoolean(d, true);
            } else {
                bundle.putBoolean(d, false);
            }
        }
    }

    public static boolean a(Context context) {
        int parseInt;
        String str;
        String str2 = H;
        Object a = UPUtils.a(context, "configs");
        Object a2 = UPUtils.a(context, Constants.KEY_MODE);
        Object a3 = UPUtils.a(context, "or");
        if (!(TextUtils.isEmpty(a) || TextUtils.isEmpty(a2) || TextUtils.isEmpty(a3))) {
            try {
                System.loadLibrary("entryexpro");
                JSONObject jSONObject = new JSONObject(a);
                String a4 = f.a(jSONObject, "sign");
                try {
                    parseInt = Integer.parseInt(a2);
                } catch (NumberFormatException e) {
                    parseInt = 0;
                }
                str = new String(Base64.decode(jSONObject.getString("configs"), 2));
                if (!UPUtils.forConfig(parseInt, a4).equals(b.a(UPUtils.a(str + a3)))) {
                    str = str2;
                }
                str2 = str;
            } catch (JSONException e2) {
            }
        }
        try {
            JSONArray jSONArray = new JSONArray(str2);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                a = f.a(jSONArray, i);
                if (a != null) {
                    JSONObject jSONObject2 = (JSONObject) a;
                    if ("app".equals(f.a(jSONObject2, "type"))) {
                        JSONArray b = b(f.c(jSONObject2, "package_info"), "sort");
                        if (b != null && b.length() > 0) {
                            int length2 = b.length();
                            for (parseInt = 0; parseInt < length2; parseInt++) {
                                a = f.a(b, parseInt);
                                if (a != null) {
                                    jSONObject2 = (JSONObject) a;
                                    String a5 = f.a(jSONObject2, "schema");
                                    String a6 = f.a(jSONObject2, "sign");
                                    str = f.a(jSONObject2, "version");
                                    if (b.a(context, a5) && a6.equalsIgnoreCase(b.b(context, a5)) && b.c(context, a5).matches(str)) {
                                        return true;
                                    }
                                }
                            }
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
            t = null;
            return false;
        } catch (JSONException e3) {
            return false;
        }
    }

    private static String b(String str) {
        return str != null ? Pattern.compile("[\":,\\[\\]{}]").matcher(str).replaceAll("").trim() : "";
    }

    private static JSONArray b(JSONArray jSONArray, String str) {
        int i = 0;
        List arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(jSONArray.optJSONObject(i2));
        }
        Collections.sort(arrayList, new k(str));
        JSONArray jSONArray2 = new JSONArray();
        while (i < arrayList.size()) {
            jSONArray2.put((JSONObject) arrayList.get(i));
            i++;
        }
        return jSONArray2;
    }

    static /* synthetic */ void b(Context context) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:com.android.providers.downloads"));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            context.startActivity(new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS"));
        }
    }

    static /* synthetic */ int m() {
        int i = B + 1;
        B = i;
        return i;
    }
}
