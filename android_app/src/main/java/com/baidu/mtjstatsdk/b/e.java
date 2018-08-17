package com.baidu.mtjstatsdk.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baidu.location.h.c;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import com.umeng.message.util.HttpRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.zip.GZIPOutputStream;

public final class e {
    public static int a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = h(context);
        } catch (Throwable e) {
            d.a("createAdReqURL", e);
        }
        return displayMetrics.widthPixels;
    }

    public static String a(Context context, String str) {
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Object obj = null;
                if (applicationInfo.metaData != null) {
                    obj = applicationInfo.metaData.get(str);
                }
                if (obj == null) {
                    d.a("StatSDK", "null,can't find information for key:" + str);
                    if (str == "BaiduMobAd_STAT_ID") {
                        d.c("不能在manifest.xml中找到APP Key||can't find app key in manifest.xml.");
                    }
                } else {
                    str2 = obj.toString();
                    if (str2.trim().equals("") && str == "BaiduMobAd_STAT_ID") {
                        d.c("APP Key值为空||The value of APP Key is empty.");
                    }
                }
            }
        } catch (NameNotFoundException e) {
            if (str == "BaiduMobAd_STAT_ID") {
                d.c("不能在manifest.xml中找到APP Key||can't find app key in manifest.xml.");
            }
        }
        return str2;
    }

    public static String a(Context context, String str, String str2, int i, int i2) {
        BufferedReader bufferedReader;
        IOException e;
        BufferedWriter bufferedWriter = null;
        HttpURLConnection a = b.a(context, str, i, i2);
        a.setDoOutput(true);
        a.setInstanceFollowRedirects(false);
        a.setUseCaches(false);
        a.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "gzip");
        a.connect();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(a.getOutputStream())));
            try {
                bufferedWriter2.write(str2);
                bufferedWriter2.close();
                bufferedReader = new BufferedReader(new InputStreamReader(a.getInputStream()));
            } catch (IOException e2) {
                e = e2;
                bufferedWriter = bufferedWriter2;
                Object obj = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                a.disconnect();
                throw e;
            }
            try {
                int contentLength = a.getContentLength();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                }
                bufferedReader.close();
                a.disconnect();
                if (a.getResponseCode() == 200 && contentLength == 0) {
                    return stringBuilder.toString();
                }
                throw new IOException("http code =" + a.getResponseCode() + "& contentResponse=" + stringBuilder);
            } catch (IOException e3) {
                e = e3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                a.disconnect();
                throw e;
            }
        } catch (IOException e4) {
            e = e4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            a.disconnect();
            throw e;
        }
    }

    public static int b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = h(context);
        } catch (Throwable e) {
            d.a("createAdReqURL", e);
        }
        return displayMetrics.heightPixels;
    }

    public static String c(Context context) {
        String format = String.format("%s_%s_%s", new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)});
        try {
            if (b.e(context, "android.permission.ACCESS_FINE_LOCATION") || b.e(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
                d.a("getLocation cell:", cellLocation + "");
                if (cellLocation == null) {
                    return format;
                }
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    r3 = new Object[3];
                    r3[0] = String.format("%d", new Object[]{Integer.valueOf(gsmCellLocation.getCid())});
                    r3[1] = String.format("%d", new Object[]{Integer.valueOf(gsmCellLocation.getLac())});
                    r3[2] = Integer.valueOf(0);
                    return String.format("%s_%s_%s", r3);
                }
                String[] split = cellLocation.toString().replace("[", "").replace("]", "").split(",");
                return String.format("%s_%s_%s", new Object[]{split[0], split[3], split[4]});
            }
        } catch (Throwable e) {
            d.a("getLocation", e);
        }
        return format;
    }

    public static String d(Context context) {
        String str = "";
        try {
            if (b.e(context, "android.permission.ACCESS_FINE_LOCATION")) {
                Location lastKnownLocation = ((LocationManager) context.getSystemService("location")).getLastKnownLocation("gps");
                d.a("statsdk", "location: " + lastKnownLocation);
                if (lastKnownLocation != null) {
                    return String.format("%s_%s_%s", new Object[]{Long.valueOf(lastKnownLocation.getTime()), Double.valueOf(lastKnownLocation.getLongitude()), Double.valueOf(lastKnownLocation.getLatitude())});
                }
            }
        } catch (Throwable e) {
            d.a("statsdk", e);
        }
        return str;
    }

    public static String e(Context context) {
        String str = "";
        try {
            if (b.e(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                return ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo().getMacAddress();
            }
            d.c("You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add:android.permission.ACCESS_WIFI_STATE");
            return str;
        } catch (Throwable e) {
            d.a("statsdk", e);
        }
    }

    public static String f(Context context) {
        int i = 0;
        String str = "";
        try {
            if (b.e(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (wifiManager.isWifiEnabled()) {
                    int i2 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                    int i3 = -1;
                    while (i < wifiManager.getScanResults().size()) {
                        int abs = Math.abs(((ScanResult) wifiManager.getScanResults().get(i)).level);
                        if (i2 > abs) {
                            i3 = abs;
                            abs = i;
                        } else {
                            abs = i3;
                            i3 = i2;
                        }
                        i++;
                        i2 = i3;
                        i3 = abs;
                    }
                    if (i3 >= 0) {
                        ScanResult scanResult = (ScanResult) wifiManager.getScanResults().get(i3);
                        return String.format("%s_%s", new Object[]{scanResult.BSSID.replace(":", "").toLowerCase(), Integer.valueOf(Math.abs(scanResult.level))});
                    }
                }
            }
        } catch (Throwable e) {
            d.a("getWifiLocation", e);
        }
        return str;
    }

    public static String g(Context context) {
        String str = "";
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        str = activeNetworkInfo.getTypeName();
        return (str.equals(c.do) || activeNetworkInfo.getSubtypeName() == null) ? str : activeNetworkInfo.getSubtypeName();
    }

    public static DisplayMetrics h(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
