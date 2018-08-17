package com.taobao.accs.utl;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import anet.channel.util.HMacUtil;
import anet.channel.util.HttpConstant;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.dynamicdatastore.IDynamicDataStoreComponent;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.client.a;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog.Level;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.android.spdy.SpdyProtocol;

/* compiled from: Taobao */
public class UtilityImpl {
    public static final String NET_TYPE_2G = "2g";
    public static final String NET_TYPE_3G = "3g";
    public static final String NET_TYPE_4G = "4g";
    public static final String NET_TYPE_UNKNOWN = "unknown";
    public static final String NET_TYPE_WIFI = "wifi";
    private static final String SSL_TIKET_KEY = "accs_ssl_ticket_key";
    private static final String SSL_TIKET_KEY2 = "accs_ssl_key2_";
    private static final String TAG = "UtilityImpl";
    public static final int TNET_FILE_NUM = 5;
    public static final int TNET_FILE_SIZE = 5242880;
    private static final byte[] mLock = new byte[0];

    public static String getProxyHost(Context context) {
        Object string = context.getSharedPreferences(Constants.SP_FILE_NAME, 4).getString(Constants.KEY_PROXY_HOST, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String proxyIp = getProxyIp();
        if (TextUtils.isEmpty(proxyIp)) {
            return null;
        }
        return proxyIp;
    }

    public static int getProxyPort(Context context) {
        int i = context.getSharedPreferences(Constants.SP_FILE_NAME, 4).getInt(Constants.KEY_PROXY_PORT, -1);
        if (i > 0) {
            return i;
        }
        if (getProxyHost(context) == null) {
            return -1;
        }
        try {
            return getProxyPort();
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean appVersionChanged(Context context) {
        boolean z = false;
        synchronized (mLock) {
            int i;
            int i2 = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt(Constants.KEY_APP_VERSION_CODE, -1);
            PackageInfo packageInfo = GlobalClientInfo.getInstance(context).getPackageInfo();
            if (packageInfo != null) {
                i = packageInfo.versionCode;
            } else {
                i = 0;
            }
            if (i2 != i) {
                saveAppVersionCode(context);
                z = true;
            }
        }
        return z;
    }

    private static void saveAppVersionCode(Context context) {
        try {
            Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            edit.putInt(Constants.KEY_APP_VERSION_CODE, GlobalClientInfo.getInstance(context).getPackageInfo().versionCode);
            edit.apply();
        } catch (Throwable th) {
        }
    }

    public static void focusDisableService(Context context) {
        synchronized (mLock) {
            Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            edit.putBoolean(Constants.KEY_FOUCE_DISABLE, true);
            edit.commit();
            disableService(context);
        }
    }

    public static void focusEnableService(Context context) {
        synchronized (mLock) {
            Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            edit.putBoolean(Constants.KEY_FOUCE_DISABLE, false);
            edit.commit();
            enableService(context);
        }
    }

    public static boolean getFocusDisableStatus(Context context) {
        Throwable th;
        Object obj;
        Throwable th2;
        Object obj2 = null;
        if (context == null) {
            return obj2;
        }
        try {
            synchronized (mLock) {
                try {
                    obj2 = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getBoolean(Constants.KEY_FOUCE_DISABLE, false);
                    try {
                        return obj2;
                    } catch (Throwable th3) {
                        th = th3;
                        obj = obj2;
                        th2 = th;
                        try {
                            throw th2;
                        } catch (Exception e) {
                            return obj;
                        }
                    }
                } catch (Throwable th32) {
                    th = th32;
                    obj = obj2;
                    th2 = th;
                    throw th2;
                }
            }
        } catch (Exception e2) {
            return obj2;
        }
    }

    public static boolean getServiceEnabled(Context context) {
        try {
            if (context.getPackageManager().getServiceInfo(new ComponentName(context, a.channelService), 128).enabled) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            e.printStackTrace();
            ALog.e(TAG, getStackMsg(e), new Object[0]);
            return false;
        }
    }

    public static boolean getAgooServiceEnabled(Context context) {
        ComponentName componentName = new ComponentName(context, a.a(context.getPackageName()));
        PackageManager packageManager = context.getPackageManager();
        try {
            if (componentName.getPackageName().equals("!")) {
                ALog.e(TAG, "getAgooServiceEnabled,exception,comptName.getPackageName()=" + componentName.getPackageName(), new Object[0]);
                return false;
            } else if (packageManager.getServiceInfo(componentName, 128).enabled) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void enableService(Context context) {
        ComponentName componentName = new ComponentName(context, a.channelService);
        ALog.d(TAG, "enableService,comptName=" + componentName.toString(), new Object[0]);
        try {
            context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
        } catch (Exception e) {
        }
    }

    public static void disableService(Context context) {
        ComponentName componentName = new ComponentName(context, a.channelService);
        PackageManager packageManager = context.getPackageManager();
        try {
            ALog.d(TAG, "disableService,comptName=" + componentName.toString(), new Object[0]);
            if (packageManager.getServiceInfo(componentName, 128).enabled) {
                packageManager.setComponentEnabledSetting(componentName, 2, 1);
                killService(context);
            }
        } catch (NameNotFoundException e) {
        }
    }

    public static void killService(Context context) {
        try {
            int myUid = Process.myUid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (runningAppProcessInfo.uid == myUid) {
                        if (!TextUtils.isEmpty(a.e) && a.e.equals(runningAppProcessInfo.processName)) {
                            ALog.e(TAG, "kill1 " + runningAppProcessInfo.processName, new Object[0]);
                            Process.killProcess(runningAppProcessInfo.pid);
                            return;
                        } else if (runningAppProcessInfo.processName.endsWith(":channel")) {
                            ALog.e(TAG, "kill " + runningAppProcessInfo.processName, new Object[0]);
                            Process.killProcess(runningAppProcessInfo.pid);
                            return;
                        }
                    }
                }
                ALog.e(TAG, "kill nothing", new Object[0]);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "killService", th, new Object[0]);
        }
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "unknown";
        }
        if (activeNetworkInfo.getType() == 1) {
            return NET_TYPE_WIFI;
        }
        Object subtypeName = activeNetworkInfo.getSubtypeName();
        String str = "";
        if (TextUtils.isEmpty(subtypeName)) {
            return str;
        }
        return subtypeName.replaceAll(" ", "");
    }

    public static String getNetworkTypeExt(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() == 1) {
                return NET_TYPE_WIFI;
            }
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return NET_TYPE_2G;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return NET_TYPE_3G;
                case 13:
                    return NET_TYPE_4G;
                default:
                    String subtypeName = activeNetworkInfo.getSubtypeName();
                    if (TextUtils.isEmpty(subtypeName)) {
                        return "unknown";
                    }
                    if (subtypeName.equalsIgnoreCase("td-scdma") || subtypeName.equalsIgnoreCase("td_scdma") || subtypeName.equalsIgnoreCase("tds_hsdpa")) {
                        return NET_TYPE_3G;
                    }
                    return "unknown";
            }
            ALog.e(TAG, "getNetworkTypeExt", e, new Object[0]);
            return null;
        } catch (Throwable e) {
            ALog.e(TAG, "getNetworkTypeExt", e, new Object[0]);
            return null;
        }
    }

    public static String getImsi(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Throwable th) {
            return null;
        }
    }

    public static long String2Time(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US).parse(str).getTime();
        } catch (Throwable th) {
            return 0;
        }
    }

    public static String formatTime(long j) {
        String str = "";
        try {
            str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US).format(new Date(j));
        } catch (Throwable th) {
            ALog.e(TAG, "formatTime", th, new Object[0]);
        }
        return str;
    }

    public static String formatDay(long j) {
        String str = "";
        try {
            str = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Long.valueOf(j));
        } catch (Throwable th) {
            ALog.e(TAG, "formatDay", th, new Object[0]);
        }
        return str;
    }

    public static boolean isForeground(Context context) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Object packageName = ((RunningTaskInfo) GlobalClientInfo.getInstance(context).getActivityManager().getRunningTasks(1).get(0)).topActivity.getPackageName();
            if (!TextUtils.isEmpty(packageName) && packageName.equals(context.getPackageName())) {
                return true;
            }
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(TAG, "isForeground time " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            }
            return false;
        } catch (Throwable th) {
            ALog.e(TAG, "isForeground error ", th, new Object[0]);
        }
    }

    private static boolean isSecurityOff(String str) {
        AccsClientConfig config = AccsClientConfig.getConfig(str);
        if ((config == null ? 0 : config.getSecurity()) == 2) {
            return true;
        }
        return false;
    }

    public static String getAppsign(Context context, String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str)) {
            ALog.e(TAG, "getAppsign appkey null", new Object[0]);
            return null;
        }
        try {
            if (!isSecurityOff(str)) {
                SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
                if (instance != null) {
                    String authCode;
                    ALog.d(TAG, "SecurityGuardManager not null!", new Object[0]);
                    ISecureSignatureComponent secureSignatureComp = instance.getSecureSignatureComp();
                    SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
                    securityGuardParamContext.appKey = str;
                    securityGuardParamContext.paramMap.put("INPUT", str3 + str);
                    securityGuardParamContext.requestType = 3;
                    AccsClientConfig config = AccsClientConfig.getConfig(str);
                    if (config != null) {
                        authCode = config.getAuthCode();
                    } else {
                        authCode = null;
                    }
                    return secureSignatureComp.signRequest(securityGuardParamContext, authCode);
                }
                ALog.d(TAG, "SecurityGuardManager is null", new Object[0]);
                return null;
            } else if (!TextUtils.isEmpty(str2)) {
                return HMacUtil.hmacSha1Hex(str2.getBytes(), (str + str3).getBytes());
            } else {
                ALog.e(TAG, "getAppsign secret null", new Object[0]);
                return null;
            }
        } catch (Throwable th) {
            ALog.e(TAG, "getAppsign", th, new Object[0]);
            return null;
        }
    }

    public static byte[] staticBinarySafeDecryptNoB64(Context context, String str, byte[] bArr) {
        byte[] bArr2 = null;
        if (!isSecurityOff(str)) {
            if (context == null || bArr == null) {
                ALog.e(TAG, "staticBinarySafeDecryptNoB64 input null!", new Object[0]);
            } else {
                try {
                    AccsClientConfig config = AccsClientConfig.getConfig(str);
                    ALog.i(TAG, "staticBinarySafeDecryptNoB64", "appkey", str, "authcode", config != null ? config.getAuthCode() : null);
                    SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
                    if (instance != null) {
                        IStaticDataEncryptComponent staticDataEncryptComp = instance.getStaticDataEncryptComp();
                        if (staticDataEncryptComp != null) {
                            bArr2 = staticDataEncryptComp.staticBinarySafeDecryptNoB64(16, SpdyProtocol.TNET_PUBKEY_SG_KEY, bArr, r1);
                        }
                    }
                    if (bArr2 == null) {
                        ALog.e(TAG, "staticBinarySafeDecryptNoB64 null", new Object[0]);
                    }
                } catch (Throwable th) {
                    ALog.e(TAG, "staticBinarySafeDecryptNoB64", th, new Object[0]);
                }
            }
        }
        return bArr2;
    }

    public static int SecurityGuardPutSslTicket2(Context context, String str, String str2, byte[] bArr) {
        int i = -1;
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || context == null || bArr == null)) {
            try {
                if (!isSecurityOff(str)) {
                    SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
                    if (instance != null) {
                        IDynamicDataStoreComponent dynamicDataStoreComp = instance.getDynamicDataStoreComp();
                        if (dynamicDataStoreComp != null) {
                            i = dynamicDataStoreComp.putByteArray(SSL_TIKET_KEY2 + str2, bArr);
                        }
                    }
                }
            } catch (Throwable th) {
                ALog.e(TAG, "SecurityGuardPutSslTicket2", th, new Object[0]);
            }
        }
        return i;
    }

    public static byte[] SecurityGuardGetSslTicket2(Context context, String str, String str2) {
        byte[] bArr = null;
        if (context == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            ALog.i(TAG, "get sslticket input null", new Object[0]);
        } else {
            try {
                if (!isSecurityOff(str)) {
                    SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
                    if (instance != null) {
                        IDynamicDataStoreComponent dynamicDataStoreComp = instance.getDynamicDataStoreComp();
                        if (dynamicDataStoreComp != null) {
                            bArr = dynamicDataStoreComp.getByteArray(SSL_TIKET_KEY2 + str2);
                        }
                    }
                }
            } catch (Throwable th) {
                ALog.e(TAG, "SecurityGuardGetSslTicket2", th, new Object[0]);
            }
        }
        return bArr;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = GlobalClientInfo.getInstance(context).getConnectivityManager().getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isConnected();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static String getDeviceId(Context context) {
        return a.b(context);
    }

    public static boolean isServiceRunning(Context context, String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(30)) {
            if (runningServiceInfo.service.getPackageName().equals(str) && runningServiceInfo.service.getClassName().equals(a.channelService)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFirstStart(Context context) {
        boolean z = false;
        synchronized (mLock) {
            if (context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt("version", -1) != Constants.SDK_VERSION_CODE) {
                z = true;
            }
        }
        return z;
    }

    public static void setSdkStart(Context context) {
        try {
            synchronized (mLock) {
                Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                edit.putInt("version", Constants.SDK_VERSION_CODE);
                edit.apply();
            }
            ALog.i(TAG, "setSdkStart", new Object[0]);
        } catch (Throwable th) {
            ALog.e(TAG, "setSdkStart", th, new Object[0]);
        }
    }

    public static boolean packageExist(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            ALog.e(TAG, "package not exist", Constants.KEY_ELECTION_PKG, str);
            return false;
        }
    }

    public static boolean utdidChanged(Context context) {
        boolean z;
        Throwable th;
        boolean z2;
        Throwable th2;
        try {
            synchronized (mLock) {
                try {
                    String deviceId = getDeviceId(context);
                    z = !context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getString("utdid", deviceId).equals(deviceId);
                    try {
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        z2 = z;
                        th2 = th4;
                        try {
                            throw th2;
                        } catch (Throwable th22) {
                            th4 = th22;
                            z = z2;
                            th3 = th4;
                            ALog.e(TAG, "saveUtdid", th3, new Object[0]);
                            return z;
                        }
                    }
                } catch (Throwable th5) {
                    th22 = th5;
                    z2 = false;
                    throw th22;
                }
            }
        } catch (Throwable th222) {
            th3 = th222;
            z = false;
            ALog.e(TAG, "saveUtdid", th3, new Object[0]);
            return z;
        }
    }

    public static void saveUtdid(Context context) {
        try {
            synchronized (mLock) {
                Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                edit.putString("utdid", getDeviceId(context));
                edit.apply();
            }
        } catch (Throwable th) {
            ALog.e(TAG, "saveUtdid", th, new Object[0]);
        }
    }

    public static boolean channelServiceExist(Context context, String str) {
        boolean z = false;
        try {
            z = context.getPackageManager().getServiceInfo(new ComponentName(str, a.channelService), 0).isEnabled();
        } catch (Exception e) {
        }
        return z;
    }

    public static void setServiceTime(Context context, String str, long j) {
        try {
            Editor edit = context.getSharedPreferences(Constants.SP_CHANNEL_FILE_NAME, 0).edit();
            edit.putLong(str, j);
            edit.commit();
            ALog.d(TAG, "setServiceTime:" + j, new Object[0]);
        } catch (Throwable th) {
            ALog.e(TAG, "setServiceTime:", th, new Object[0]);
        }
    }

    public static long getServiceAliveTime(Context context) {
        long j;
        Throwable th;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_CHANNEL_FILE_NAME, 0);
            j = sharedPreferences.getLong(Constants.SP_KEY_SERVICE_START, 0);
            long j2 = sharedPreferences.getLong(Constants.SP_KEY_SERVICE_END, 0);
            if (j > 0) {
                j = j2 - j;
            } else {
                j = 0;
            }
            try {
                Editor edit = sharedPreferences.edit();
                edit.putLong(Constants.SP_KEY_SERVICE_START, 0);
                edit.putLong(Constants.SP_KEY_SERVICE_END, 0);
                edit.commit();
            } catch (Throwable th2) {
                th = th2;
                ALog.e(TAG, "getServiceAliveTime:", th, new Object[0]);
                return j;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            j = 0;
            th = th4;
            ALog.e(TAG, "getServiceAliveTime:", th, new Object[0]);
            return j;
        }
        return j;
    }

    public static boolean isAccsStatisticsOff(Context context) {
        return true;
    }

    public static int praseInt(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static String getAppVersion(Context context) {
        String str = "";
        try {
            return GlobalClientInfo.getInstance(context).getPackageInfo().versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getProcessName(Context context, int i) {
        return a.a(context, i);
    }

    public static boolean isMainProcess(Context context) {
        return a.a(context);
    }

    public static boolean isChannelProcess(Context context) {
        return false;
    }

    public static int getByteLen(String str) {
        int i = 0;
        if (str != null) {
            try {
                i = str.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static String getStackMsg(Throwable th) {
        return a.a(th);
    }

    public static File getExternalFilesDir(Context context) {
        return context.getExternalFilesDir(null);
    }

    public static File getCacheFilesDir(Context context) {
        return context.getExternalCacheDir();
    }

    public static void storeCookie(Context context, String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                GlobalClientInfo.c = str;
                Editor edit = context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, 0).edit();
                edit.putString(Constants.SP_KEY_COOKIE_SEC, str);
                edit.apply();
            }
        } catch (Throwable e) {
            ALog.e(TAG, "storeCookie fail", e, new Object[0]);
        }
    }

    public static String restoreCookie(Context context) {
        String str = null;
        try {
            str = context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, 4).getString(Constants.SP_KEY_COOKIE_SEC, null);
        } catch (Throwable e) {
            ALog.e(TAG, "reStoreCookie fail", e, new Object[0]);
        }
        return str;
    }

    public static void clearCookie(Context context) {
        try {
            GlobalClientInfo.c = null;
            Editor edit = context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, 0).edit();
            edit.clear();
            edit.apply();
        } catch (Throwable th) {
            ALog.e(TAG, "clearCookie fail", th, new Object[0]);
        }
    }

    public static long getUsableSpace() {
        return a.a();
    }

    public static String convertHost(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (str.startsWith("//")) {
                return "https:" + str;
            }
            int indexOf = str.indexOf(HttpConstant.SCHEME_SPLIT);
            if (indexOf == -1) {
                return "https://" + str;
            }
            Object substring = str.substring(indexOf + 3);
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            return "https://" + substring;
        } catch (Throwable th) {
            ALog.e(TAG, "convertHost", th, new Object[0]);
            return null;
        }
    }

    public static String getProxyIp() {
        if (VERSION.SDK_INT < 11) {
            return Proxy.getDefaultHost();
        }
        return System.getProperty("http.proxyHost");
    }

    public static int getProxyPort() {
        if (VERSION.SDK_INT < 11) {
            return Proxy.getDefaultPort();
        }
        try {
            return Integer.parseInt(System.getProperty("http.proxyPort"));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getProxy() {
        String str = getProxyIp() + ":" + getProxyPort();
        if (ALog.isPrintLog(Level.D)) {
            ALog.d(TAG, "getProxy:" + str, new Object[0]);
        }
        return str;
    }

    public static String isNotificationEnabled(Context context) {
        return a.d(context);
    }

    public static String getTnetLogFilePath(Context context, String str) {
        String str2 = null;
        try {
            Object externalFilesDir = context.getExternalFilesDir("tnetlogs");
            if (!(externalFilesDir != null && externalFilesDir.exists() && externalFilesDir.canWrite())) {
                externalFilesDir = context.getDir("logs", 0);
            }
            ALog.d(TAG, "getTnetLogFilePath :" + externalFilesDir, new Object[0]);
            str2 = externalFilesDir + "/" + str.toLowerCase();
        } catch (Throwable th) {
            ALog.e(TAG, "getTnetLogFilePath", th, new Object[0]);
        }
        return str2;
    }

    public static String int2String(int i) {
        String str = null;
        try {
            str = String.valueOf(i);
        } catch (Throwable e) {
            ALog.e(TAG, "int2String", e, new Object[0]);
        }
        return str;
    }

    public static int String2Int(String str) {
        int i = 0;
        try {
            i = Integer.valueOf(str).intValue();
        } catch (Throwable e) {
            ALog.e(TAG, "String2Int", e, new Object[i]);
        }
        return i;
    }

    public static String getDefaultAppkey(Context context) {
        String[] appkey = getAppkey(context);
        return (appkey == null || appkey.length <= 0) ? null : appkey[0];
    }

    public static String[] getAppkey(Context context) {
        String[] appkey;
        synchronized (mLock) {
            appkey = ACCSManager.getAppkey(context);
        }
        return appkey;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveAppKey(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
        r1 = mLock;	 Catch:{ Throwable -> 0x003d }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x003d }
        r0 = "ACCS_SDK";
        r2 = 0;
        r0 = r4.getSharedPreferences(r0, r2);	 Catch:{ all -> 0x003a }
        r2 = "appkey";
        r3 = "";
        r2 = r0.getString(r2, r3);	 Catch:{ all -> 0x003a }
        r3 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x003a }
        if (r3 != 0) goto L_0x0024;
    L_0x0018:
        r3 = r2.equals(r5);	 Catch:{ all -> 0x003a }
        if (r3 != 0) goto L_0x0024;
    L_0x001e:
        r3 = r2.contains(r5);	 Catch:{ all -> 0x003a }
        if (r3 == 0) goto L_0x0026;
    L_0x0024:
        monitor-exit(r1);	 Catch:{ all -> 0x003a }
    L_0x0025:
        return;
    L_0x0026:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x003a }
        if (r3 == 0) goto L_0x0042;
    L_0x002c:
        r0 = r0.edit();	 Catch:{ all -> 0x003a }
        r2 = "appkey";
        r0.putString(r2, r5);	 Catch:{ all -> 0x003a }
        r0.commit();	 Catch:{ all -> 0x003a }
        monitor-exit(r1);	 Catch:{ all -> 0x003a }
        goto L_0x0025;
    L_0x003a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ Throwable -> 0x003d }
        throw r0;	 Catch:{ Throwable -> 0x003d }
    L_0x003d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0025;
    L_0x0042:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003a }
        r3.<init>(r2);	 Catch:{ all -> 0x003a }
        r2 = "|";
        r2 = r3.append(r2);	 Catch:{ all -> 0x003a }
        r2 = r2.append(r5);	 Catch:{ all -> 0x003a }
        r5 = r2.toString();	 Catch:{ all -> 0x003a }
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.utl.UtilityImpl.saveAppKey(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static void clearSharePreferences(Context context) {
        try {
            synchronized (mLock) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_FILE_NAME, 0);
                Object string = sharedPreferences.getString("appkey", null);
                Object string2 = sharedPreferences.getString("app_sercet", null);
                Object string3 = sharedPreferences.getString(Constants.KEY_PROXY_HOST, null);
                int i = sharedPreferences.getInt(Constants.KEY_PROXY_PORT, -1);
                int i2 = sharedPreferences.getInt("version", -1);
                int i3 = sharedPreferences.getInt(Constants.SP_KEY_DEBUG_MODE, 0);
                Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                edit.clear();
                if (!TextUtils.isEmpty(string)) {
                    edit.putString("appkey", string);
                }
                if (!TextUtils.isEmpty(string2)) {
                    edit.putString("app_sercet", string2);
                }
                if (!TextUtils.isEmpty(string3)) {
                    edit.putString(Constants.KEY_PROXY_HOST, string3);
                }
                if (i > 0) {
                    edit.putInt(Constants.KEY_PROXY_PORT, i);
                }
                if (i2 > 0) {
                    edit.putInt("version", i2);
                }
                if (i3 == 2 || i3 == 1) {
                    edit.putInt(Constants.SP_KEY_DEBUG_MODE, i3);
                }
                edit.commit();
            }
        } catch (Throwable th) {
            ALog.e(TAG, "clearSharePreferences", th, new Object[0]);
        }
    }
}
