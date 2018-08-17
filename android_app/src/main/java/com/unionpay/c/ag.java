package com.unionpay.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import anet.channel.strategy.dispatch.c;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class ag {
    private static int a(String str) {
        int i = 0;
        try {
            String str2 = "";
            Matcher matcher = Pattern.compile("([0-9]+)").matcher(str);
            if (matcher.find()) {
                str2 = matcher.toMatchResult().group(0);
            }
            i = Integer.valueOf(str2).intValue();
        } catch (Exception e) {
        }
        return i;
    }

    public static String a() {
        try {
            String trim = Build.MODEL.trim();
            String a = a(Build.MANUFACTURER.trim(), trim);
            if (TextUtils.isEmpty(a)) {
                a = a(Build.BRAND.trim(), trim);
            }
            if (a == null) {
                a = "";
            }
            return ":" + trim;
        } catch (Throwable th) {
            return "";
        }
    }

    private static String a(String str, String str2) {
        try {
            CharSequence toLowerCase = str.toLowerCase();
            return (toLowerCase.startsWith("unknown") || toLowerCase.startsWith("alps") || toLowerCase.startsWith(c.ANDROID) || toLowerCase.startsWith("sprd") || toLowerCase.startsWith("spreadtrum") || toLowerCase.startsWith("rockchip") || toLowerCase.startsWith("wondermedia") || toLowerCase.startsWith("mtk") || toLowerCase.startsWith("mt65") || toLowerCase.startsWith("nvidia") || toLowerCase.startsWith("brcm") || toLowerCase.startsWith("marvell") || str2.toLowerCase().contains(toLowerCase)) ? null : str;
        } catch (Throwable th) {
            return null;
        }
    }

    public static JSONObject a(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("nfc-status", b(context));
            jSONObject.put("appsRegistedHCE", d(context));
            jSONObject.put("ssMode", f(context));
            return jSONObject;
        } catch (Throwable th) {
            return null;
        }
    }

    public static int b() {
        return VERSION.SDK_INT;
    }

    public static int b(Context context) {
        if (context == null) {
            return 0;
        }
        NfcAdapter defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter();
        int i = defaultAdapter != null ? !defaultAdapter.isEnabled() ? 1 : (am.a(19) && context.getPackageManager().hasSystemFeature("android.hardware.nfc.hce")) ? 3 : 2 : 0;
        return i;
    }

    private static String b(String str) {
        String str2 = null;
        try {
            Reader fileReader = new FileReader(str);
            try {
                char[] cArr = new char[1024];
                BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                while (true) {
                    int read = bufferedReader.read(cArr, 0, 1024);
                    if (-1 == read) {
                        break;
                    }
                    str2 = str2 + new String(cArr, 0, read);
                }
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
            }
        } catch (Throwable th) {
        }
        return str2;
    }

    public static String c() {
        return Locale.getDefault().getLanguage();
    }

    public static String c(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                int i = displayMetrics.widthPixels;
                int i2 = displayMetrics.heightPixels;
                return Math.min(i, i2) + "*" + Math.max(i, i2) + "*" + displayMetrics.densityDpi;
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static String d() {
        return Locale.getDefault().getCountry();
    }

    private static JSONArray d(Context context) {
        if (!am.a(19)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (PackageInfo packageInfo : e(context)) {
                ServiceInfo[] serviceInfoArr = packageInfo.services;
                if (serviceInfoArr != null) {
                    for (ServiceInfo serviceInfo : serviceInfoArr) {
                        Bundle bundle = serviceInfo.metaData;
                        if (bundle != null && bundle.containsKey("android.nfc.cardemulation.host_apdu_service")) {
                            jSONArray.put(packageInfo.packageName);
                        }
                    }
                }
            }
            return jSONArray;
        } catch (Throwable th) {
            return null;
        }
    }

    private static List e(Context context) {
        List installedPackages;
        Throwable th;
        Throwable th2;
        PackageManager packageManager = context.getPackageManager();
        try {
            installedPackages = packageManager.getInstalledPackages(4);
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
        return installedPackages;
    }

    public static String[] e() {
        int i;
        BufferedReader bufferedReader;
        String[] strArr = new String[4];
        for (i = 0; i < 4; i++) {
            strArr[i] = "";
        }
        List arrayList = new ArrayList();
        Reader fileReader;
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            bufferedReader = new BufferedReader(fileReader, 1024);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    arrayList.add(readLine);
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        i = 1;
                    } catch (Throwable th) {
                        i = 1;
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
            i = 1;
        } catch (Throwable th2) {
            i = 0;
        }
        String[] strArr2 = new String[]{"Processor\\s*:\\s*(.*)", "CPU\\s*variant\\s*:\\s*0x(.*)", "Hardware\\s*:\\s*(.*)"};
        if (i != 0) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < 3; i2++) {
                Pattern compile = Pattern.compile(strArr2[i2]);
                for (int i3 = 0; i3 < size; i3++) {
                    Matcher matcher = compile.matcher((String) arrayList.get(i3));
                    if (matcher.find()) {
                        strArr[i2] = matcher.toMatchResult().group(1);
                    }
                }
            }
        }
        strArr[3] = b("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
        return strArr;
    }

    private static int f(Context context) {
        try {
            if (am.a(19)) {
                return CardEmulation.getInstance(((NfcManager) context.getSystemService("nfc")).getDefaultAdapter()).getSelectionModeForCategory("payment");
            }
        } catch (Throwable th) {
        }
        return -1;
    }

    public static int[] f() {
        Reader fileReader;
        BufferedReader bufferedReader;
        int i = 0;
        int[] iArr = new int[]{0, 0};
        int[] iArr2 = new int[4];
        for (int i2 = 0; i2 < 4; i2++) {
            iArr2[i2] = 0;
        }
        try {
            fileReader = new FileReader("/proc/meminfo");
            bufferedReader = new BufferedReader(fileReader, 1024);
            while (i < 4) {
                iArr2[i] = a(bufferedReader.readLine());
                i++;
            }
            iArr[0] = iArr2[0];
            iArr[1] = iArr2[3] + (iArr2[1] + iArr2[2]);
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
            }
        } catch (IOException e2) {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e3) {
            }
        } catch (Throwable th) {
        }
        return iArr;
    }

    public static int[] g() {
        try {
            r0 = new int[4];
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            r0[0] = (statFs.getBlockCount() * (statFs.getBlockSize() / 512)) / 2;
            r0[1] = ((statFs.getBlockSize() / 512) * statFs.getAvailableBlocks()) / 2;
            statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            r0[2] = (statFs.getBlockCount() * (statFs.getBlockSize() / 512)) / 2;
            r0[3] = ((statFs.getBlockSize() / 512) * statFs.getAvailableBlocks()) / 2;
            return r0;
        } catch (Throwable th) {
            return null;
        }
    }

    public static int h() {
        int i = 0;
        try {
            Matcher matcher = Pattern.compile("\\s*([0-9]+)").matcher(b("/sys/class/power_supply/battery/full_bat"));
            if (matcher.find()) {
                i = Integer.valueOf(matcher.toMatchResult().group(0)).intValue();
            }
        } catch (Exception e) {
        }
        return i;
    }
}
