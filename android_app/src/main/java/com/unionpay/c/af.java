package com.unionpay.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class af {
    static TelephonyManager a;
    static String b;
    private static final Pattern c = Pattern.compile("^([0-9A-F]{2}:){5}([0-9A-F]{2})$");
    private static final Pattern d = Pattern.compile("[0-3][0-9a-f]{24,32}");
    private static final Pattern e = Pattern.compile("[0-3][0-9a-f]{32}");
    private static String f = null;

    public static final String a() {
        try {
            if (am.a(9)) {
                return Build.SERIAL;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    private static String a(File file) {
        try {
            if (file.exists() && file.canRead()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[128];
                int read = fileInputStream.read(bArr);
                fileInputStream.close();
                return new String(bArr, 0, read);
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static void a(Context context) {
        a = (TelephonyManager) context.getSystemService("phone");
    }

    private static void a(Context context, String str) {
        File[] listFiles = new File("/").listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file : listFiles) {
                if (file.isDirectory() && !"/sdcard".equals(file.getAbsolutePath())) {
                    if (file.canWrite() && !new File(file, ".tcookieid" + j(context)).exists()) {
                        a(new File(file, ".tcookieid"), str);
                    }
                    if (file.listFiles() != null) {
                        for (File file2 : file.listFiles()) {
                            if (file2.isDirectory() && file2.canWrite() && !new File(file2, ".tcookieid" + j(context)).exists()) {
                                a(new File(file2, ".tcookieid"), str);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void a(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (am.a(9)) {
                file.getClass().getMethod("setReadable", new Class[]{Boolean.TYPE, Boolean.TYPE}).invoke(file, new Object[]{Boolean.valueOf(true), Boolean.valueOf(false)});
                return;
            }
            Runtime.getRuntime().exec("chmod 444 " + file.getAbsolutePath());
        } catch (Throwable th) {
        }
    }

    public static synchronized String b(Context context) {
        int i = 0;
        synchronized (af.class) {
            if (b == null) {
                Object obj;
                Object obj2;
                String b = aj.b(context, "tdid", "pref.deviceid.key", null);
                String string = am.b(b) ? PreferenceManager.getDefaultSharedPreferences(context).getString("pref.deviceid.key", null) : b;
                File[] listFiles = new File("/").listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    obj = null;
                } else {
                    b = null;
                    loop2:
                    for (File file : listFiles) {
                        if (file.isDirectory() && !"/sdcard".equals(file.getAbsolutePath())) {
                            if (file.canWrite()) {
                                b = a(new File(file, ".tcookieid"));
                                if (!am.b(b)) {
                                    break;
                                }
                            }
                            if (file.listFiles() != null) {
                                for (File file2 : file.listFiles()) {
                                    if (file2.isDirectory()) {
                                        b = a(new File(file2, ".tcookieid"));
                                        if (!am.b(b)) {
                                            break loop2;
                                        }
                                    }
                                }
                                continue;
                            } else {
                                continue;
                            }
                        }
                    }
                    String str = b;
                }
                boolean b2 = b();
                if (!am.a(23) || context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                    String str2;
                    if ("mounted".equals(Environment.getExternalStorageState())) {
                        b = a(new File(Environment.getExternalStorageDirectory(), b2 ? ".tcookieid" : ".tcookieid" + j(context)));
                        if (am.b(b)) {
                            b = a(new File(Environment.getExternalStorageDirectory(), ".tid" + j(context)));
                        }
                        str2 = b;
                    } else {
                        str2 = "";
                    }
                } else {
                    obj2 = null;
                }
                String[] strArr = new String[]{string, obj, obj2};
                for (String str3 : strArr) {
                    if (!am.b(str3) && e.matcher(str3).matches()) {
                        break;
                    }
                }
                String str32 = null;
                if (am.b(str32) && !am.b(string) && Math.random() < 0.99d) {
                    int length = strArr.length;
                    while (i < length) {
                        b = strArr[i];
                        if (!am.b(b) && d.matcher(b).matches()) {
                            break;
                        }
                        i++;
                    }
                }
                b = str32;
                if (am.b(b)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(d(context)).append('-').append(g(context)).append('-').append(c(context));
                    str32 = "3" + am.c(stringBuilder.toString());
                } else {
                    str32 = b;
                }
                if (!str32.equals(string)) {
                    try {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("tdid", 0);
                        if (sharedPreferences != null) {
                            Editor edit = sharedPreferences.edit();
                            edit.putString("pref.deviceid.key", str32);
                            edit.commit();
                        }
                    } catch (Throwable th) {
                    }
                }
                if (!str32.equals(obj2)) {
                    a(new File(Environment.getExternalStorageDirectory(), b2 ? ".tcookieid" : ".tcookieid" + j(context)), str32);
                }
                if (!str32.equals(obj)) {
                    a(context, str32);
                }
                b = str32;
            }
        }
        return b;
    }

    private static boolean b() {
        boolean booleanValue;
        try {
            if (am.a(9)) {
                booleanValue = ((Boolean) Environment.class.getMethod("isExternalStorageRemovable", new Class[0]).invoke(null, new Object[0])).booleanValue();
                return booleanValue;
            }
        } catch (Throwable th) {
        }
        booleanValue = true;
        if (booleanValue) {
        }
    }

    public static String c(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            return null;
        }
    }

    public static String d(Context context) {
        try {
            if (am.a(23) && context.checkSelfPermission(MsgConstant.PERMISSION_READ_PHONE_STATE) != 0) {
                return null;
            }
            if (am.b(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                String string;
                if (a == null) {
                    a(context);
                }
                JSONArray r = ah.r(context);
                if (r != null && r.length() == 2) {
                    try {
                        string = r.getJSONObject(1).getString("imei");
                    } catch (Exception e) {
                    }
                    return string != null ? a.getDeviceId() : string;
                }
                string = null;
                if (string != null) {
                }
            }
            return null;
        } catch (Throwable th) {
        }
    }

    public static String e(Context context) {
        String str = null;
        try {
            if ((!am.a(23) || context.checkSelfPermission(MsgConstant.PERMISSION_READ_PHONE_STATE) == 0) && am.b(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                if (a == null) {
                    a(context);
                }
                str = a.getSimSerialNumber();
            }
        } catch (Throwable th) {
        }
        return str;
    }

    public static String f(Context context) {
        String str = null;
        try {
            if ((!am.a(23) || context.checkSelfPermission(MsgConstant.PERMISSION_READ_PHONE_STATE) == 0) && am.b(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                if (a == null) {
                    a(context);
                }
                str = a.getSubscriberId();
            }
        } catch (Throwable th) {
        }
        return str;
    }

    public static String g(Context context) {
        String str = null;
        if (!am.a) {
            return null;
        }
        String str2 = "02:00:00:00:00:00";
        String str3;
        try {
            if (am.a(23)) {
                try {
                    List<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
                    if (list == null || list.size() <= 0) {
                        return str2;
                    }
                    for (NetworkInterface networkInterface : list) {
                        if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                            byte[] hardwareAddress = networkInterface.getHardwareAddress();
                            if (hardwareAddress == null) {
                                return "";
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            int length = hardwareAddress.length;
                            for (int i = 0; i < length; i++) {
                                stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                            }
                            if (stringBuilder.length() > 0) {
                                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                            }
                            str = stringBuilder.toString().toUpperCase().trim();
                        }
                    }
                    try {
                        return am.b(str) ? str2 : str;
                    } catch (Throwable th) {
                        str3 = str;
                    }
                } catch (Throwable th2) {
                }
            } else {
                if (am.b(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                    if (wifiManager.isWifiEnabled()) {
                        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                        if (connectionInfo != null) {
                            str3 = connectionInfo.getMacAddress();
                            if (str3 != null) {
                                try {
                                    str3 = str3.toUpperCase().trim();
                                    if ("00:00:00:00:00:00".equals(str3) || !c.matcher(str3).matches()) {
                                        str3 = null;
                                    }
                                } catch (Throwable th3) {
                                }
                            }
                            return str3;
                        }
                    }
                }
                str3 = null;
                return str3;
            }
        } catch (Throwable th4) {
            str3 = null;
        }
    }

    public static final String h(Context context) {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context}), new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static final String i(Context context) {
        String g = g(context);
        if (!TextUtils.isEmpty(g)) {
            g = String.valueOf(Long.parseLong(g.replaceAll(":", ""), 16));
        }
        String c = c(context);
        String d = d(context);
        String f = f(context);
        String e = e(context);
        String b = b(context);
        String h = h(context);
        return new StringBuilder(Constants.TARGET_SERVICE_PRE).append(g).append("|").append(c).append("|").append(d).append("|").append(f).append("|").append(e).append("|").append(b).append("|").append(h).append("|").append(a()).toString();
    }

    private static String j(Context context) {
        if (f == null) {
            try {
                Sensor[] sensorArr = new Sensor[64];
                for (Sensor sensor : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
                    if (sensor.getType() < sensorArr.length && sensor.getType() >= 0) {
                        sensorArr[sensor.getType()] = sensor;
                    }
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < sensorArr.length; i++) {
                    if (sensorArr[i] != null) {
                        stringBuffer.append(i).append('.').append(sensorArr[i].getVendor()).append('-').append(sensorArr[i].getName()).append('-').append(sensorArr[i].getVersion()).append('\n');
                    }
                }
                f = String.valueOf(stringBuffer.toString().hashCode());
            } catch (Throwable th) {
            }
        }
        return f;
    }
}
