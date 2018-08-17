package com.taobao.accs.b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.j;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Taobao */
public class a implements Callback {
    public static final int ACT_START = 0;
    public static final int ACT_STOP = -1;
    public static final String AGOO_PID = "agoo.pid";
    public static final String EX_FILE_NAME = "DaemonServer";
    public static final String PROCESS_NAME = "runServer";
    public static String a = "startservice -n {packname}/com.taobao.accs.ChannelService";
    private static final String c = a.class.getName();
    private static int g = 7200;
    private static int h = 2500;
    private static final ReentrantLock i = new ReentrantLock();
    private static a j = null;
    public boolean b = false;
    private Context d = null;
    private String e;
    private int f = 1800;
    private String k = "100001";
    private String l = "tb_accs_eudemon_1.1.3";
    private String m = "";
    private String n = "21646297";
    private int o = 0;
    private String p = "100.69.165.28";
    private String q = "http://100.69.165.28/agoo/report";
    private int r = 80;
    private boolean s = true;
    private Handler t = null;
    private HandlerThread u = null;

    public a(Context context, int i, boolean z) {
        b();
        a = "startservice -n {packname}/com.taobao.accs.ChannelService";
        this.d = context;
        this.f = i;
        this.b = z;
        this.e = a(new Build(), "CPU_ABI");
        this.m = "/data/data/" + context.getPackageName();
        this.o = Constants.SDK_VERSION_CODE;
        this.n = UtilityImpl.getDefaultAppkey(this.d);
        if (j.a(context) == 0) {
            this.p = "agoodm.m.taobao.com";
            this.r = 80;
            this.q = "http://agoodm.m.taobao.com/agoo/report";
            this.k = "1009527";
        } else if (j.a(context) == 1) {
            this.p = "110.75.98.154";
            this.r = 80;
            this.q = "http://agoodm.wapa.taobao.com/agoo/report";
            this.k = "1009527";
        } else {
            this.p = "100.69.168.33";
            this.r = 80;
            this.q = "http://100.69.168.33/agoo/report";
            this.f = 60;
            this.k = "9527";
        }
    }

    private void b() {
        Log.d(c, "start handler init");
        this.u = new HandlerThread("soManager-threads");
        this.u.setPriority(5);
        this.u.start();
        this.t = new Handler(this.u.getLooper(), this);
    }

    private String c() {
        if (this.e.startsWith("arm")) {
            return "armeabi/";
        }
        return this.e + "/";
    }

    private static String a(Build build, String str) {
        try {
            return Build.class.getField(str).get(build).toString();
        } catch (Throwable th) {
            return "Unknown";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.taobao.accs.b.a a(android.content.Context r4, int r5, boolean r6) {
        /*
        r0 = i;	 Catch:{ Exception -> 0x0018 }
        r0.lock();	 Catch:{ Exception -> 0x0018 }
        r0 = j;	 Catch:{ Exception -> 0x0018 }
        if (r0 != 0) goto L_0x0010;
    L_0x0009:
        r0 = new com.taobao.accs.b.a;	 Catch:{ Exception -> 0x0018 }
        r0.<init>(r4, r5, r6);	 Catch:{ Exception -> 0x0018 }
        j = r0;	 Catch:{ Exception -> 0x0018 }
    L_0x0010:
        r0 = i;
        r0.unlock();
    L_0x0015:
        r0 = j;
        return r0;
    L_0x0018:
        r0 = move-exception;
        r1 = c;	 Catch:{ all -> 0x0029 }
        r2 = "getInstance";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0029 }
        com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);	 Catch:{ all -> 0x0029 }
        r0 = i;
        r0.unlock();
        goto L_0x0015;
    L_0x0029:
        r0 = move-exception;
        r1 = i;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.b.a.a(android.content.Context, int, boolean):com.taobao.accs.b.a");
    }

    private String d() throws IOException {
        InputStream inputStream = null;
        File file = new File(this.d.getFilesDir(), EX_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
        ALog.w(c, "open assets from = " + c() + EX_FILE_NAME, new Object[0]);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            if (this.b) {
                inputStream = this.d.getAssets().open(c() + EX_FILE_NAME);
                byte[] bArr = new byte[100];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } else {
                a(fileOutputStream, file);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e) {
                    ALog.e(c, "error in close input file", e, new Object[0]);
                }
            }
            try {
                fileOutputStream.close();
            } catch (Throwable e2) {
                ALog.e(c, "error in close io", e2, new Object[0]);
            }
        } catch (Throwable e22) {
            ALog.e(c, "error in copy daemon files", e22, new Object[0]);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e222) {
                    ALog.e(c, "error in close input file", e222, new Object[0]);
                }
            }
            try {
                fileOutputStream.close();
            } catch (Throwable e2222) {
                ALog.e(c, "error in close io", e2222, new Object[0]);
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e3) {
                    ALog.e(c, "error in close input file", e3, new Object[0]);
                }
            }
            try {
                fileOutputStream.close();
            } catch (Throwable e32) {
                ALog.e(c, "error in close io", e32, new Object[0]);
            }
        }
        return file.getCanonicalPath();
    }

    private void a(FileOutputStream fileOutputStream, File file) throws IOException {
        Throwable e;
        String a = b.a(this.e);
        ALog.d(c, ">>>soDataSize:datasize:" + a.length(), new Object[0]);
        byte[] decode = Base64.decode(a.getBytes(), 0);
        ALog.d(c, ">>>soDataSize:" + decode.length, new Object[0]);
        if (decode.length > 0 && fileOutputStream != null) {
            StatFs statFs = new StatFs(file.getCanonicalPath());
            if (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()) < ((long) decode.length)) {
                Log.e(c, "Disk is not enough for writing this file");
                return;
            }
            ByteArrayInputStream byteArrayInputStream;
            try {
                byteArrayInputStream = new ByteArrayInputStream(decode);
                try {
                    decode = new byte[100];
                    while (true) {
                        int read = byteArrayInputStream.read(decode, 0, 100);
                        if (read < 0) {
                            break;
                        }
                        fileOutputStream.write(decode, 0, read);
                    }
                    fileOutputStream.getFD().sync();
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        ALog.e(c, "error in write files", e, new Object[0]);
                        fileOutputStream.getFD().sync();
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        e = th;
                        fileOutputStream.getFD().sync();
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw e;
                    }
                }
            } catch (IOException e5) {
                e = e5;
                byteArrayInputStream = null;
                ALog.e(c, "error in write files", e, new Object[0]);
                fileOutputStream.getFD().sync();
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            } catch (Throwable th2) {
                e = th2;
                byteArrayInputStream = null;
                fileOutputStream.getFD().sync();
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                throw e;
            }
        }
    }

    private void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        a("", "chmod 500 " + str, stringBuilder);
        a("", str + " " + e(), stringBuilder);
        ALog.w(c, str + " " + e(), new Object[0]);
    }

    private String e() {
        StringBuilder stringBuilder = new StringBuilder();
        String str = "/data/data/" + this.d.getPackageName();
        stringBuilder.append("-s \"" + str + "/lib/" + "\" ");
        stringBuilder.append("-n \"runServer\" ");
        stringBuilder.append("-p \"" + g() + "\" ");
        stringBuilder.append("-f \"" + str + "\" ");
        stringBuilder.append("-t \"" + this.f + "\" ");
        stringBuilder.append("-c \"agoo.pid\" ");
        if (this.m != null) {
            stringBuilder.append("-P " + this.m + " ");
        }
        if (this.k != null) {
            stringBuilder.append("-K " + this.k + " ");
        }
        if (this.l != null) {
            stringBuilder.append("-U " + this.l + " ");
        }
        if (this.q != null) {
            stringBuilder.append("-L " + this.q + " ");
        }
        stringBuilder.append("-D " + f() + " ");
        if (this.p != null) {
            stringBuilder.append("-I " + this.p + " ");
        }
        if (this.r > 0) {
            stringBuilder.append("-O " + this.r + " ");
        }
        str = UtilityImpl.getProxyHost(this.d);
        int proxyPort = UtilityImpl.getProxyPort(this.d);
        if (str != null && proxyPort > 0) {
            stringBuilder.append("-X " + str + " ");
            stringBuilder.append("-Y " + proxyPort + " ");
        }
        if (this.s) {
            stringBuilder.append("-T ");
        }
        stringBuilder.append("-Z ");
        return stringBuilder.toString();
    }

    private String f() {
        String deviceId = UtilityImpl.getDeviceId(this.d);
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "null";
        }
        deviceId = "{\"package\":\"" + this.d.getPackageName() + "\",\"appKey\":\"" + this.n + "\",\"utdid\":\"" + deviceId + "\",\"sdkVersion\":\"" + this.o + "\"}";
        try {
            deviceId = URLEncoder.encode(deviceId, "UTF-8");
        } catch (Throwable th) {
            ALog.e(c, "getReportData failed for url encode, data:" + deviceId, new Object[0]);
        }
        return deviceId;
    }

    private String g() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a.replaceAll("\\{packname\\}", this.d.getApplicationContext().getPackageName()));
        if (VERSION.SDK_INT > 15) {
            stringBuilder.append(" --user 0");
        }
        return stringBuilder.toString();
    }

    private void a(String str, int i, int i2, String str2, String str3, int i3) {
        String str4 = "AndroidVer=" + VERSION.RELEASE + "&Model=" + Build.MODEL + "&AndroidSdk=" + VERSION.SDK_INT + "&AccsVer=" + Constants.SDK_VERSION_CODE + "&Appkey=" + UtilityImpl.getDefaultAppkey(this.d) + "&PullCount=" + str2 + "&Pid=" + str + "&StartTime=" + i + "&EndTime=" + i2 + "&ExitCode=" + str3 + "&AliveTime=" + i3;
        Log.d(c, "EUDEMON_ENDSTAT doReportDaemonStat:" + str4);
        UTMini.getInstance().commitEvent(66001, "EUDEMON_ENDSTAT", str4);
    }

    private void h() {
        InputStreamReader inputStreamReader;
        Throwable e;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        String str = "/data/data/" + this.d.getPackageName() + "/" + "eudemon";
        File file = new File(str);
        if (file.exists()) {
            FileOutputStream fileOutputStream2 = null;
            InputStreamReader inputStreamReader2 = null;
            BufferedReader bufferedReader2 = null;
            FileInputStream fileInputStream2;
            try {
                fileInputStream2 = new FileInputStream(file);
                try {
                    inputStreamReader = new InputStreamReader(fileInputStream2);
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                    try {
                        Log.e(c, "report daemon stat exp:", e);
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e3) {
                                Log.e(c, "error in close buffreader stream", e3);
                            }
                        }
                        if (inputStreamReader2 != null) {
                            try {
                                inputStreamReader2.close();
                            } catch (Throwable e32) {
                                Log.e(c, "error in close reader stream", e32);
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e322) {
                                Log.e(c, "error in close input file", e322);
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e3222) {
                                Log.e(c, "error in close input file", e3222);
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        e3222 = th;
                        bufferedReader = bufferedReader2;
                        inputStreamReader = inputStreamReader2;
                        fileOutputStream2 = fileOutputStream;
                        fileInputStream2 = fileInputStream;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable th2) {
                                Log.e(c, "error in close buffreader stream", th2);
                            }
                        }
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (Throwable th22) {
                                Log.e(c, "error in close reader stream", th22);
                            }
                        }
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Throwable th222) {
                                Log.e(c, "error in close input file", th222);
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Throwable th2222) {
                                Log.e(c, "error in close input file", th2222);
                            }
                        }
                        throw e3222;
                    }
                } catch (Throwable th3) {
                    e3222 = th3;
                    bufferedReader = null;
                    inputStreamReader = null;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw e3222;
                }
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        String str2 = "";
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            String[] split = readLine.split("\\|");
                            if (split.length == 5) {
                                String trim = split[0].trim();
                                int intValue = Integer.valueOf(split[1].trim()).intValue();
                                int intValue2 = Integer.valueOf(split[2].trim()).intValue();
                                int i = intValue2 - intValue;
                                String trim2 = split[3].trim();
                                String trim3 = split[4].trim();
                                if (trim3.equals("0")) {
                                    File file2 = new File("/proc", trim);
                                    Log.e(c, "pidfile:" + file2);
                                    if (file2.exists()) {
                                        str2 = str2 + readLine + "\n";
                                    } else {
                                        i += this.f / 2;
                                    }
                                }
                                a(trim, intValue, intValue2, trim2, trim3, i);
                            }
                        }
                        FileOutputStream fileOutputStream3 = new FileOutputStream(new File(str));
                        try {
                            fileOutputStream3.write(str2.getBytes());
                            bufferedReader.close();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable e32222) {
                                    Log.e(c, "error in close buffreader stream", e32222);
                                }
                            }
                            if (inputStreamReader != null) {
                                try {
                                    inputStreamReader.close();
                                } catch (Throwable e322222) {
                                    Log.e(c, "error in close reader stream", e322222);
                                }
                            }
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable e3222222) {
                                    Log.e(c, "error in close input file", e3222222);
                                }
                            }
                            if (fileOutputStream3 != null) {
                                try {
                                    fileOutputStream3.close();
                                } catch (Throwable e32222222) {
                                    Log.e(c, "error in close input file", e32222222);
                                }
                            }
                        } catch (Exception e4) {
                            e32222222 = e4;
                            inputStreamReader2 = inputStreamReader;
                            fileOutputStream = fileOutputStream3;
                            fileInputStream = fileInputStream2;
                            bufferedReader2 = bufferedReader;
                            Log.e(c, "report daemon stat exp:", e32222222);
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            if (inputStreamReader2 != null) {
                                inputStreamReader2.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th4) {
                            e32222222 = th4;
                            fileOutputStream2 = fileOutputStream3;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            throw e32222222;
                        }
                    } catch (Exception e5) {
                        e32222222 = e5;
                        bufferedReader2 = bufferedReader;
                        inputStreamReader2 = inputStreamReader;
                        fileOutputStream = null;
                        fileInputStream = fileInputStream2;
                        Log.e(c, "report daemon stat exp:", e32222222);
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th5) {
                        e32222222 = th5;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw e32222222;
                    }
                } catch (Exception e6) {
                    e32222222 = e6;
                    inputStreamReader2 = inputStreamReader;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                    Log.e(c, "report daemon stat exp:", e32222222);
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (inputStreamReader2 != null) {
                        inputStreamReader2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th6) {
                    e32222222 = th6;
                    bufferedReader = null;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw e32222222;
                }
            } catch (Exception e7) {
                e32222222 = e7;
                fileInputStream = null;
                fileOutputStream = null;
                Log.e(c, "report daemon stat exp:", e32222222);
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th7) {
                e32222222 = th7;
                bufferedReader = null;
                inputStreamReader = null;
                fileInputStream2 = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw e32222222;
            }
        }
    }

    public void a() {
        Log.d(c, "start SoManager");
        Message obtain = Message.obtain();
        obtain.what = 0;
        this.t.sendMessage(obtain);
    }

    private void i() {
        ALog.d(c, "api level is:" + VERSION.SDK_INT, new Object[0]);
        b(this.d);
        if (VERSION.SDK_INT < 20) {
            try {
                String d = d();
                h();
                a(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UTMini.getInstance().commitEvent(66001, "EUDEMON_START", "");
    }

    private void j() {
        File file = new File("/data/data/" + this.d.getPackageName(), "daemonserver.pid");
        if (file.exists()) {
            file.delete();
        }
    }

    public static final PendingIntent a(Context context) {
        Intent intent = new Intent();
        intent.setAction(context.getApplicationContext().getPackageName() + ".intent.action.COCKROACH");
        intent.putExtra("cockroach", "cockroach-PPreotect");
        intent.putExtra("pack", context.getApplicationContext().getPackageName());
        return PendingIntent.getService(context, 0, intent, 134217728);
    }

    public static void b(Context context) {
        int i = Calendar.getInstance().get(11);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager != null) {
            PendingIntent a = a(context);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (i > 23 || i < 6) {
                ALog.w(c, "time is night, do not wakeup cpu", new Object[0]);
                b(alarmManager, a, elapsedRealtime);
                return;
            }
            ALog.w(c, "time is daytime, wakeup cpu for keeping connecntion", new Object[0]);
            a(alarmManager, a, elapsedRealtime);
        }
    }

    private static void a(AlarmManager alarmManager, PendingIntent pendingIntent, long j) {
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
            alarmManager.setRepeating(2, ((long) (h * 1000)) + j, (long) (h * 1000), pendingIntent);
        }
    }

    private static void b(AlarmManager alarmManager, PendingIntent pendingIntent, long j) {
        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(3, ((long) (g * 1000)) + j, (long) (g * 1000), pendingIntent);
    }

    public static void c(Context context) {
        Throwable th;
        try {
            File file = new File(context.getFilesDir(), AGOO_PID);
            ALog.d(c, "pid path:" + file.getAbsolutePath(), new Object[0]);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileWriter fileWriter;
            try {
                int myPid = Process.myPid();
                fileWriter = new FileWriter(file);
                try {
                    fileWriter.write(String.valueOf(myPid).toCharArray());
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (Throwable th2) {
                            ALog.e(c, "error", th2, new Object[0]);
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    try {
                        ALog.e(c, "save pid error", th2, new Object[0]);
                        if (fileWriter != null) {
                            try {
                                fileWriter.close();
                            } catch (Throwable th22) {
                                ALog.e(c, "error", th22, new Object[0]);
                            }
                        }
                    } catch (Throwable th4) {
                        th22 = th4;
                        if (fileWriter != null) {
                            try {
                                fileWriter.close();
                            } catch (Throwable th5) {
                                ALog.e(c, "error", th5, new Object[0]);
                            }
                        }
                        throw th22;
                    }
                }
            } catch (Throwable th6) {
                th22 = th6;
                fileWriter = null;
                if (fileWriter != null) {
                    fileWriter.close();
                }
                throw th22;
            }
        } catch (Throwable th222) {
            ALog.e(c, "error in create file", th222, new Object[0]);
        }
    }

    public boolean handleMessage(Message message) {
        try {
            if (message.what == 0) {
                i();
            } else if (message.what == -1) {
                j();
            }
        } catch (Throwable th) {
            ALog.e(c, "handleMessage error", th, new Object[0]);
        }
        return true;
    }

    public static boolean a(String str, String str2, StringBuilder stringBuilder) {
        Log.w("TAG.", str2);
        try {
            Process exec = Runtime.getRuntime().exec("sh");
            DataInputStream dataInputStream = new DataInputStream(exec.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
            if (!(str == null || "".equals(str.trim()))) {
                dataOutputStream.writeBytes("cd " + str + "\n");
            }
            dataOutputStream.writeBytes(str2 + " &\n");
            dataOutputStream.writeBytes("exit \n");
            dataOutputStream.flush();
            exec.waitFor();
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            String str3 = new String(bArr);
            if (str3.length() != 0) {
                stringBuilder.append(str3);
            }
            return true;
        } catch (Exception e) {
            stringBuilder.append("Exception:" + e.getMessage());
            return false;
        }
    }
}
