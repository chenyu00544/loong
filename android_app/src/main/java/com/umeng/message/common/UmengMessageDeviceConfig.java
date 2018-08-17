package com.umeng.message.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.ta.utdid2.device.UTDevice;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.proguard.h;
import com.umeng.message.util.a;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONException;
import org.json.JSONObject;

public class UmengMessageDeviceConfig {
    public static final int DEFAULT_TIMEZONE = 8;
    protected static final String a = "Unknown";
    private static final String b = UmengMessageDeviceConfig.class.getSimpleName();
    private static final String c = "2G/3G";
    private static final String d = "Wi-Fi";
    private static boolean e = false;

    public static boolean isAppInstalled(String str, Context context) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isChinese(Context context) {
        return context.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
    }

    public static boolean isScreenPortrait(Context context) {
        if (context.getResources().getConfiguration().orientation == 1) {
            return true;
        }
        return false;
    }

    public static String getAppVersionCode(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            return a;
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return a;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public static String getAppLabel(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "");
    }

    public static String[] getGPU(GL10 gl10) {
        try {
            String[] strArr = new String[2];
            String glGetString = gl10.glGetString(7936);
            String glGetString2 = gl10.glGetString(7937);
            strArr[0] = glGetString;
            strArr[1] = glGetString2;
            return strArr;
        } catch (Exception e) {
            UmLog.e(b, "Could not read gpu infor:", e);
            return new String[0];
        }
    }

    public static String getCPU() {
        Exception exception;
        String str;
        Exception exception2;
        String str2 = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str2 = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (Exception e) {
                    try {
                        UmLog.e(b, "Could not read from file /proc/cpuinfo", e);
                    } catch (Exception e2) {
                        exception = e2;
                        str = str2;
                        exception2 = exception;
                        UmLog.e(b, "Could not open file /proc/cpuinfo", exception2);
                        str2 = str;
                        if (str2 != null) {
                            return str2.substring(str2.indexOf(58) + 1).trim();
                        }
                        return str2;
                    }
                }
            }
        } catch (Exception e22) {
            exception = e22;
            str = str2;
            exception2 = exception;
            UmLog.e(b, "Could not open file /proc/cpuinfo", exception2);
            str2 = str;
            if (str2 != null) {
                return str2;
            }
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        if (str2 != null) {
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        return str2;
    }

    public static String getDeviceId(Context context) {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            UmLog.w(b, "No IMEI.");
        }
        String str = "";
        try {
            if (checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                deviceId = telephonyManager.getDeviceId();
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                UmLog.w(b, "No IMEI.");
                deviceId = getMac(context);
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                UmLog.w(b, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
                deviceId = Secure.getString(context.getContentResolver(), "android_id");
                UmLog.i(b, "getDeviceId: Secure.ANDROID_ID: " + deviceId);
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                UmLog.w(b, "Failed to take Secure.ANDROID_ID as IMEI. Try to use Serial_number instead.");
                deviceId = getSerial_number();
                UmLog.i(b, "getDeviceId: Serial_number: " + deviceId);
                return deviceId;
            }
        } catch (Exception e) {
            UmLog.w(b, "No IMEI.", e);
        }
        deviceId = str;
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        UmLog.w(b, "No IMEI.");
        deviceId = getMac(context);
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        UmLog.w(b, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        deviceId = Secure.getString(context.getContentResolver(), "android_id");
        UmLog.i(b, "getDeviceId: Secure.ANDROID_ID: " + deviceId);
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        UmLog.w(b, "Failed to take Secure.ANDROID_ID as IMEI. Try to use Serial_number instead.");
        deviceId = getSerial_number();
        UmLog.i(b, "getDeviceId: Serial_number: " + deviceId);
        return deviceId;
    }

    public static String getDIN(Context context) {
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            UmLog.w(b, "No IMEI.");
        }
        try {
            if (checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                Object deviceId = telephonyManager.getDeviceId();
                if (!TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
            }
        } catch (Exception e) {
            UmLog.w(b, "No IMEI.", e);
        }
        return "";
    }

    public static String getAndroidId(Context context) {
        String string = System.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            return "";
        }
        return string;
    }

    public static String getSerial_number() {
        String str = Build.SERIAL;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getDeviceIdUmengMD5(Context context) {
        return h.b(getDeviceId(context));
    }

    public static String getDeviceIdMD5(Context context) {
        return h.a(getDeviceId(context));
    }

    public static String getNetworkOperatorName(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return a;
            }
            return telephonyManager.getNetworkOperatorName();
        } catch (Exception e) {
            e.printStackTrace();
            return a;
        }
    }

    public static String getDisplayResolution(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(i);
        } catch (Exception e) {
            e.printStackTrace();
            return a;
        }
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = new String[]{a, a};
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || networkInfo.getState() != State.CONNECTED) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
            strArr[0] = "Wi-Fi";
            return strArr;
        } catch (Exception e) {
        }
    }

    public static boolean isWiFiAvailable(Context context) {
        return "Wi-Fi".equals(getNetworkAccessMode(context)[0]);
    }

    public static Location getLocation(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (checkPermission(context, "android.permission.ACCESS_FINE_LOCATION")) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null) {
                    UmLog.d(b, "get location from gps:" + lastKnownLocation.getLatitude() + "," + lastKnownLocation.getLongitude());
                    return lastKnownLocation;
                }
            }
            if (checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                Location lastKnownLocation2 = locationManager.getLastKnownLocation("network");
                if (lastKnownLocation2 != null) {
                    UmLog.d(b, "get location from network:" + lastKnownLocation2.getLatitude() + "," + lastKnownLocation2.getLongitude());
                    return lastKnownLocation2;
                }
            }
            UmLog.d(b, "Could not get location from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?");
            return null;
        } catch (Exception e) {
            UmLog.e(b, e.getMessage());
            return null;
        }
    }

    public static boolean isOnline(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isSdCardWrittenable() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static int getTimeZone(Context context) {
        try {
            Calendar instance = Calendar.getInstance(a(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Exception e) {
            UmLog.i(b, "error in getTimeZone", e);
        }
        return 8;
    }

    public static String[] getLocaleInfo(Context context) {
        String[] strArr = new String[2];
        try {
            Locale a = a(context);
            if (a != null) {
                strArr[0] = a.getCountry();
                strArr[1] = a.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = a;
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = a;
            }
        } catch (Exception e) {
            UmLog.e(b, "error in getLocaleInfo", e);
        }
        return strArr;
    }

    private static Locale a(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Exception e) {
            UmLog.e(b, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public static String getAppkey(Context context) {
        return getMetaData(context, "UMENG_APPKEY");
    }

    public static String getMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString(str);
                if (string != null) {
                    return string.trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        UmLog.e(b, String.format("Could not read meta-data %s from AndroidManifest.xml.", new Object[]{str}));
        return null;
    }

    public static String getMac(Context context) {
        if (VERSION.SDK_INT <= 22) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (checkPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                    return wifiManager.getConnectionInfo().getMacAddress();
                }
                UmLog.w(b, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            } catch (Exception e) {
                UmLog.w(b, "Could not get mac address." + e.toString());
            }
        } else {
            try {
                for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
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
                        return stringBuilder.toString();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static String getUmid(final Context context) {
        Object umid = MessageSharedPrefs.getInstance(context).getUmid();
        if (!TextUtils.isEmpty(umid)) {
            return umid;
        }
        if (!e) {
            e = true;
            new Thread(new Runnable() {
                public void run() {
                    String str = "";
                    try {
                        FileInputStream openFileInput = context.openFileInput("exid.dat");
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[1024];
                        while (openFileInput.read(bArr) != -1) {
                            byteArrayOutputStream.write(bArr, 0, bArr.length);
                        }
                        String str2 = new String(byteArrayOutputStream.toByteArray());
                        openFileInput.close();
                        byteArrayOutputStream.close();
                        Object optString = new JSONObject(str2).optString("umid", "");
                        if (!TextUtils.isEmpty(optString)) {
                            MessageSharedPrefs.getInstance(context).setUmid(optString);
                            UTrack.getInstance(context).updateHeader();
                        }
                    } catch (IOException e) {
                        UmLog.i(UmengMessageDeviceConfig.b, "no umid");
                    } catch (JSONException e2) {
                        UmLog.i(UmengMessageDeviceConfig.b, "no umid");
                    }
                }
            }).start();
        }
        return "";
    }

    public static String getResolution(Context context) {
        try {
            int a;
            int a2;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                a = a(displayMetrics, "noncompatWidthPixels");
                a2 = a(displayMetrics, "noncompatHeightPixels");
            } else {
                a2 = -1;
                a = -1;
            }
            if (a == -1 || r0 == -1) {
                a = displayMetrics.widthPixels;
                a2 = displayMetrics.heightPixels;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a);
            stringBuffer.append("*");
            stringBuffer.append(a2);
            return stringBuffer.toString();
        } catch (Exception e) {
            UmLog.e(b, "read resolution fail", e);
            return a;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            if (declaredField != null) {
                declaredField.setAccessible(true);
                return declaredField.getInt(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getOperator(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e) {
            UmLog.i(b, "read carrier fail", e);
            return a;
        }
    }

    public static String getTimeString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
    }

    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    public static Date toTime(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getIntervalSeconds(Date date, Date date2) {
        if (!date.after(date2)) {
            Date date3 = date2;
            date2 = date;
            date = date3;
        }
        return (int) ((date.getTime() - date2.getTime()) / 1000);
    }

    public static String getChannel(Context context) {
        String str = a;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (obj2 != null) {
                        return obj2;
                    }
                    UmLog.i(b, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                }
            }
        } catch (Exception e) {
            UmLog.i(b, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e.printStackTrace();
        }
        return str;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getApplicationLable(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static boolean isDebug(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUtdid(Context context) {
        try {
            return UTDevice.getUtdid(context);
        } catch (Throwable th) {
            UmLog.e(b, "fail to get utdid. " + th.getMessage());
            return "";
        }
    }

    public static boolean isServiceWork(Context context, String str, String str2) {
        List runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            String str3 = ((RunningServiceInfo) runningServices.get(i)).service.getClassName().toString();
            String str4 = ((RunningServiceInfo) runningServices.get(i)).service.getPackageName().toString();
            if (str3.equals(str) && str4.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIntentExist(Context context, String str, String str2) {
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 0);
        if (queryIntentActivities.isEmpty()) {
            return false;
        }
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            if (((ResolveInfo) queryIntentActivities.get(i)).activityInfo.packageName.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getDataData(String str) {
        boolean exists = new File("/data/app/" + str + "-1.apk").exists();
        if (!exists) {
            exists = new File("/data/app/" + str + "-2.apk").exists();
        }
        if (!exists) {
            exists = new File("/data/app/" + str + ".apk").exists();
        }
        if (!exists) {
            exists = new File("/data/app/" + str + WeiboAuthException.DEFAULT_AUTH_ERROR_CODE).exists();
        }
        if (exists) {
            return exists;
        }
        return new File("/data/app/" + str + "-2").exists();
    }

    public static boolean isMIUI() {
        String str = "ro.miui.ui.version.code";
        str = "ro.miui.ui.version.name";
        str = "ro.miui.internal.storage";
        try {
            a g = a.g();
            if (g.a("ro.miui.ui.version.code", null) == null && g.a("ro.miui.ui.version.name", null) == null && g.a("ro.miui.internal.storage", null) == null) {
                return false;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isMiui8() {
        try {
            String a = a.g().a("ro.miui.ui.version.name");
            if (a == null || !a.contains("8")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            if (e == null) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }
}
