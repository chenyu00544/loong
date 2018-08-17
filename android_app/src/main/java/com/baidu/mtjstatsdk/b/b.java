package com.baidu.mtjstatsdk.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

public final class b {
    private static final Proxy a = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.172", 80));
    private static final Proxy b = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80));

    public static String a(Context context, String str) {
        c.a("MoUtil.read", str);
        String str2 = "";
        try {
            byte[] b = b(context, str);
            if (b != null) {
                return new String(b, "utf-8");
            }
        } catch (Throwable e) {
            Log.w("statsdk", "MoUtil.read", e);
        }
        return str2;
    }

    public static String a(boolean z, Context context, String str) {
        return z ? b(str) : a(context, str);
    }

    public static HttpURLConnection a(Context context, String str, int i, int i2) {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if (networkInfo2 != null && networkInfo2.isAvailable()) {
            c.a("", "WIFI is available");
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } else if (networkInfo == null || !networkInfo.isAvailable()) {
            c.a("", "getConnection:not wifi and mobile");
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } else {
            String extraInfo = networkInfo.getExtraInfo();
            extraInfo = extraInfo != null ? extraInfo.toLowerCase() : "";
            c.a("current APN", extraInfo);
            httpURLConnection = (extraInfo.startsWith("cmwap") || extraInfo.startsWith("uniwap") || extraInfo.startsWith("3gwap")) ? (HttpURLConnection) url.openConnection(a) : extraInfo.startsWith("ctwap") ? (HttpURLConnection) url.openConnection(b) : (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i2);
        return httpURLConnection;
    }

    public static void a(Context context, String str, String str2, boolean z) {
        boolean z2 = false;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(str, z ? 32768 : 0);
            if (fileOutputStream != null) {
                fileOutputStream.write(str2.getBytes("utf-8"));
            } else {
                String str3 = "statsdk";
                StringBuilder append = new StringBuilder().append("MoUtil.write fout is null:");
                if (fileOutputStream == null) {
                    z2 = true;
                }
                Log.w(str3, append.append(z2).toString());
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable e) {
                    Log.w("statsdk", "MoUtil.write", e);
                }
            }
        } catch (Throwable e2) {
            Log.w("statsdk", "MoUtil.write", e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable e22) {
                    Log.w("statsdk", "MoUtil.write", e22);
                }
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable e3) {
                    Log.w("statsdk", "MoUtil.write", e3);
                }
            }
        }
    }

    public static void a(String str, String str2, boolean z) {
        Throwable e;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            FileOutputStream fileOutputStream = null;
            try {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + str);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
                try {
                    fileOutputStream2.write(str2.getBytes("utf-8"));
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable e2) {
                            Log.w("statsdk", "MoUtil.writeExt", e2);
                        }
                    }
                } catch (FileNotFoundException e3) {
                    e2 = e3;
                    fileOutputStream = fileOutputStream2;
                    try {
                        Log.e("statsdk", "MoUtil.writeExt", e2);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e22) {
                                Log.w("statsdk", "MoUtil.writeExt", e22);
                            }
                        }
                    } catch (Throwable th) {
                        e22 = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e4) {
                                Log.w("statsdk", "MoUtil.writeExt", e4);
                            }
                        }
                        throw e22;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    fileOutputStream = fileOutputStream2;
                    Log.e("statsdk", "MoUtil.writeExt", e22);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e222) {
                            Log.w("statsdk", "MoUtil.writeExt", e222);
                        }
                    }
                } catch (Throwable th2) {
                    e222 = th2;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw e222;
                }
            } catch (FileNotFoundException e6) {
                e222 = e6;
                Log.e("statsdk", "MoUtil.writeExt", e222);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e7) {
                e222 = e7;
                Log.e("statsdk", "MoUtil.writeExt", e222);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        }
    }

    public static void a(boolean z, Context context, String str, String str2, boolean z2) {
        if (z) {
            a(str, str2, z2);
        } else {
            a(context, str, str2, z2);
        }
    }

    public static boolean a(String str) {
        c.a("MoUtil.deleteExt", str);
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + str);
        return file.exists() ? file.delete() : false;
    }

    public static String b(String str) {
        FileInputStream fileInputStream;
        Throwable e;
        c.a("MoUtil.readExt", str);
        String externalStorageState = Environment.getExternalStorageState();
        if (!"mounted".equals(externalStorageState) && !"mounted_ro".equals(externalStorageState)) {
            return "";
        }
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + str);
        String str2 = "";
        if (!file.exists()) {
            return str2;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                externalStorageState = new String(bArr, "utf-8");
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e2) {
                        Log.w("statsdk", "MoUtil.readExt", e2);
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    Log.e("statsdk", "MoUtil.readExt", e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e4) {
                            Log.w("statsdk", "MoUtil.readExt", e4);
                            externalStorageState = str2;
                        }
                    }
                    externalStorageState = str2;
                    return externalStorageState;
                } catch (Throwable th) {
                    e4 = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e22) {
                            Log.w("statsdk", "MoUtil.readExt", e22);
                        }
                    }
                    throw e4;
                }
            } catch (IOException e5) {
                e4 = e5;
                Log.e("statsdk", "MoUtil.readExt", e4);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e42) {
                        Log.w("statsdk", "MoUtil.readExt", e42);
                        externalStorageState = str2;
                    }
                }
                externalStorageState = str2;
                return externalStorageState;
            }
        } catch (FileNotFoundException e6) {
            e42 = e6;
            fileInputStream = null;
            Log.e("statsdk", "MoUtil.readExt", e42);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            externalStorageState = str2;
            return externalStorageState;
        } catch (IOException e7) {
            e42 = e7;
            fileInputStream = null;
            Log.e("statsdk", "MoUtil.readExt", e42);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            externalStorageState = str2;
            return externalStorageState;
        } catch (Throwable th2) {
            e42 = th2;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e42;
        }
        return externalStorageState;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static byte[] b(android.content.Context r6, java.lang.String r7) {
        /*
        r2 = 0;
        r1 = r6.openFileInput(r7);	 Catch:{ FileNotFoundException -> 0x0020, IOException -> 0x0039 }
        if (r1 == 0) goto L_0x007d;
    L_0x0007:
        r0 = r1.available();	 Catch:{ FileNotFoundException -> 0x0071, IOException -> 0x0065, all -> 0x0062 }
        r2 = new byte[r0];	 Catch:{ FileNotFoundException -> 0x0071, IOException -> 0x0065, all -> 0x0062 }
        r1.read(r2);	 Catch:{ FileNotFoundException -> 0x0077, IOException -> 0x006b, all -> 0x0062 }
        r0 = r2;
    L_0x0011:
        if (r1 == 0) goto L_0x0016;
    L_0x0013:
        r1.close();	 Catch:{ IOException -> 0x0017 }
    L_0x0016:
        return r0;
    L_0x0017:
        r1 = move-exception;
        r2 = "statsdk";
        r3 = "MoUtil.readBinary";
        android.util.Log.e(r2, r3, r1);
        goto L_0x0016;
    L_0x0020:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0023:
        r3 = "statsdk";
        r4 = "MoUtil.readBinary";
        android.util.Log.e(r3, r4, r1);	 Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x0016;
    L_0x002c:
        r2.close();	 Catch:{ IOException -> 0x0030 }
        goto L_0x0016;
    L_0x0030:
        r1 = move-exception;
        r2 = "statsdk";
        r3 = "MoUtil.readBinary";
        android.util.Log.e(r2, r3, r1);
        goto L_0x0016;
    L_0x0039:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x003c:
        r3 = "statsdk";
        r4 = "MoUtil.readBinary";
        android.util.Log.e(r3, r4, r1);	 Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x0016;
    L_0x0045:
        r2.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x0016;
    L_0x0049:
        r1 = move-exception;
        r2 = "statsdk";
        r3 = "MoUtil.readBinary";
        android.util.Log.e(r2, r3, r1);
        goto L_0x0016;
    L_0x0052:
        r0 = move-exception;
    L_0x0053:
        if (r2 == 0) goto L_0x0058;
    L_0x0055:
        r2.close();	 Catch:{ IOException -> 0x0059 }
    L_0x0058:
        throw r0;
    L_0x0059:
        r1 = move-exception;
        r2 = "statsdk";
        r3 = "MoUtil.readBinary";
        android.util.Log.e(r2, r3, r1);
        goto L_0x0058;
    L_0x0062:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0053;
    L_0x0065:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x003c;
    L_0x006b:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x003c;
    L_0x0071:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x0023;
    L_0x0077:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x0023;
    L_0x007d:
        r0 = r2;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mtjstatsdk.b.b.b(android.content.Context, java.lang.String):byte[]");
    }

    public static void c(String str) {
        d.c("statsdk", str);
        Log.e("statsdk", "SDK install error:" + str);
    }

    public static boolean c(Context context, String str) {
        boolean exists = context.getFileStreamPath(str).exists();
        c.a("MoUtil.exists", exists + " " + str);
        return exists;
    }

    public static void d(Context context, String str) {
        if (!e(context, str)) {
            c("You need the " + str + " permission. Open AndroidManifest.xml and just before the final </manifest> tag add:  <uses-permission android:name=\"" + str + "\" />");
        }
    }

    public static boolean e(Context context, String str) {
        boolean z = context.checkCallingOrSelfPermission(str) != -1;
        c.a("hasPermission ", z + " | " + str);
        return z;
    }
}
