package com.alipay.sdk.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.alipay.sdk.app.h;
import com.taobao.accs.utl.BaseMonitor;
import com.umeng.socialize.common.SocializeConstants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public final class i {

    public static class a {
        public Signature[] a;
        public int b;

        public final boolean a() {
            if (this.a == null || this.a.length <= 0) {
                return false;
            }
            int i = 0;
            while (i < this.a.length) {
                String a = i.a(this.a[i].toByteArray());
                if (a == null || TextUtils.equals(a, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")) {
                    i++;
                } else {
                    com.alipay.sdk.app.a.a.a("biz", "PublicKeyUnmatch", a);
                    return true;
                }
            }
            return false;
        }
    }

    public static String a(byte[] bArr) {
        try {
            String obj = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString();
            if (obj.indexOf("modulus") != -1) {
                return obj.substring(obj.indexOf("modulus") + 8, obj.lastIndexOf(",")).trim();
            }
        } catch (Throwable e) {
            com.alipay.sdk.app.a.a.a(BaseMonitor.ALARM_POINT_AUTH, "GetPublicKeyFromSignEx", e);
        }
        return null;
    }

    public static a a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 192);
            if (!a(packageInfo)) {
                try {
                    packageInfo = b(context, str);
                } catch (Throwable th) {
                    com.alipay.sdk.app.a.a.a(BaseMonitor.ALARM_POINT_AUTH, "GetInstalledPackagesEx", th);
                }
            }
        } catch (Throwable th2) {
            com.alipay.sdk.app.a.a.a(BaseMonitor.ALARM_POINT_AUTH, "GetInstalledPackagesEx", th2);
            packageInfo = null;
        }
        if (!a(packageInfo) || packageInfo == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = packageInfo.signatures;
        aVar.b = packageInfo.versionCode;
        return aVar;
    }

    private static boolean a(PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = str + "info == null";
        } else if (packageInfo.signatures == null) {
            str = str + "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = str + "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            com.alipay.sdk.app.a.a.a(BaseMonitor.ALARM_POINT_AUTH, "NotIncludeSignatures", str);
        }
        return z;
    }

    private static PackageInfo b(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(192)) {
            if (packageInfo.packageName.equals(str)) {
                return packageInfo;
            }
        }
        return null;
    }

    public static boolean a(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo("com.alipay.android.app", 128) == null) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
            if (packageInfo != null && packageInfo.versionCode > 73) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a("biz", "CheckClientExistEx", th);
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
            if (packageInfo != null && packageInfo.versionCode < 99) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String d(Context context) {
        String a = a();
        String b = b();
        String e = e(context);
        return " (" + a + ";" + b + ";" + e + ";;" + f(context) + ")(sdk android)";
    }

    public static String a() {
        return "Android " + VERSION.RELEASE;
    }

    public static WebView a(Activity activity, String str, String str2) {
        Method method;
        if (!TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(activity.getApplicationContext()).sync();
            CookieManager.getInstance().setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
        View linearLayout = new LinearLayout(activity);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        activity.setContentView(linearLayout, layoutParams);
        View webView = new WebView(activity);
        layoutParams.weight = 1.0f;
        webView.setVisibility(0);
        linearLayout.addView(webView, layoutParams);
        WebSettings settings = webView.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + d(activity));
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(TextSize.NORMAL);
        webView.setVerticalScrollbarOverlay(true);
        webView.setDownloadListener(new j(activity));
        if (VERSION.SDK_INT >= 7) {
            try {
                method = webView.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                if (method != null) {
                    method.invoke(webView.getSettings(), new Object[]{Boolean.valueOf(true)});
                }
            } catch (Exception e) {
            }
        }
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable th) {
        }
        if (VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(1);
        }
        webView.loadUrl(str);
        return webView;
    }

    public static String b() {
        String d = d();
        int indexOf = d.indexOf(SocializeConstants.OP_DIVIDER_MINUS);
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        indexOf = d.indexOf("\n");
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        return "Linux " + d;
    }

    private static String d() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            CharSequence readLine = bufferedReader.readLine();
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (!matcher.matches()) {
                return "Unavailable";
            }
            if (matcher.groupCount() < 4) {
                return "Unavailable";
            }
            return new StringBuilder(matcher.group(1)).append("\n").append(matcher.group(2)).append(" ").append(matcher.group(3)).append("\n").append(matcher.group(4)).toString();
        } catch (IOException e) {
            return "Unavailable";
        } catch (Throwable th) {
            bufferedReader.close();
        }
    }

    public static String e(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String f(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(displayMetrics.widthPixels);
        stringBuilder.append("*");
        stringBuilder.append(displayMetrics.heightPixels);
        return stringBuilder.toString();
    }

    public static String c() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 65.0d))));
                    break;
                case 1:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 97.0d))));
                    break;
                case 2:
                    stringBuilder.append(String.valueOf(new Random().nextInt(10)));
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    public static boolean a(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    public static String g(Context context) {
        String str = "";
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals("com.eg.android.AlipayGphone")) {
                    str = str + "#M";
                } else {
                    String str2;
                    if (runningAppProcessInfo.processName.startsWith("com.eg.android.AlipayGphone:")) {
                        str2 = str + "#" + runningAppProcessInfo.processName.replace("com.eg.android.AlipayGphone:", "");
                    } else {
                        str2 = str;
                    }
                    str = str2;
                }
            }
        } catch (Throwable th) {
            str = "";
        }
        if (str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() == 0) {
            return "N";
        }
        return str;
    }

    public static String h(Context context) {
        try {
            List installedPackages = context.getPackageManager().getInstalledPackages(0);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < installedPackages.size(); i++) {
                Object obj;
                PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                int i2 = packageInfo.applicationInfo.flags;
                if ((i2 & 1) == 0 && (i2 & 128) == 0) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    if (packageInfo.packageName.equals("com.eg.android.AlipayGphone")) {
                        stringBuilder.append(packageInfo.packageName).append(packageInfo.versionCode).append(SocializeConstants.OP_DIVIDER_MINUS);
                    } else if (!(packageInfo.packageName.contains("theme") || packageInfo.packageName.startsWith("com.google.") || packageInfo.packageName.startsWith("com.android."))) {
                        stringBuilder.append(packageInfo.packageName).append(SocializeConstants.OP_DIVIDER_MINUS);
                    }
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a("biz", "GetInstalledAppEx", th);
            return "";
        }
    }

    public static boolean a(WebView webView, String str, Activity activity) {
        if (!TextUtils.isEmpty(str)) {
            if (str.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase()) || str.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
                try {
                    a a = a(activity, "com.eg.android.AlipayGphone");
                    if (!(a == null || a.a())) {
                        if (str.startsWith("intent://platformapi/startapp")) {
                            str = str.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
                        }
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                } catch (Throwable th) {
                }
            } else if (TextUtils.equals(str, "sdklite://h5quit") || TextUtils.equals(str, "http://m.alipay.com/?action=h5quit")) {
                h.a = h.a();
                activity.finish();
            } else if (str.startsWith("sdklite://h5quit?result=")) {
                com.alipay.sdk.app.i a2;
                try {
                    String substring = str.substring(str.indexOf("sdklite://h5quit?result=") + 24);
                    int parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf("&end_code=") + 10));
                    if (parseInt == com.alipay.sdk.app.i.SUCCEEDED.h || parseInt == com.alipay.sdk.app.i.PAY_WAITTING.h) {
                        String decode = URLDecoder.decode(str);
                        decode = decode.substring(decode.indexOf("sdklite://h5quit?result=") + 24, decode.lastIndexOf("&end_code="));
                        a2 = com.alipay.sdk.app.i.a(parseInt);
                        h.a = h.a(a2.h, a2.i, decode);
                        activity.runOnUiThread(new k(activity));
                    } else {
                        a2 = com.alipay.sdk.app.i.a(com.alipay.sdk.app.i.FAILED.h);
                        h.a = h.a(a2.h, a2.i, "");
                        activity.runOnUiThread(new k(activity));
                    }
                } catch (Exception e) {
                    a2 = com.alipay.sdk.app.i.a(com.alipay.sdk.app.i.PARAMS_ERROR.h);
                    h.a = h.a(a2.h, a2.i, "");
                }
            } else {
                webView.loadUrl(str);
            }
        }
        return true;
    }
}
