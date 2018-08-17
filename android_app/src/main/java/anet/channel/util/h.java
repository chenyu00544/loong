package anet.channel.util;

import android.content.Context;
import anet.channel.GlobalAppRuntimeInfo;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/* compiled from: Taobao */
public class h {
    private static File a = null;

    public static File a(String str) {
        if (a == null) {
            Context context = GlobalAppRuntimeInfo.getContext();
            if (context != null) {
                a = context.getExternalCacheDir();
                if (a == null) {
                    a = context.getCacheDir();
                }
            }
        }
        return new File(a, str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(java.io.Serializable r13, java.io.File r14) {
        /*
        r0 = 1;
        r4 = 0;
        r2 = 0;
        r5 = anet.channel.util.h.class;
        monitor-enter(r5);
        if (r13 == 0) goto L_0x000a;
    L_0x0008:
        if (r14 != 0) goto L_0x0017;
    L_0x000a:
        r0 = "awcn.SerializeHelper";
        r1 = "persist fail. Invalid parameter";
        r2 = 0;
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0085 }
        anet.channel.util.ALog.e(r0, r1, r2, r3);	 Catch:{ all -> 0x0085 }
    L_0x0015:
        monitor-exit(r5);
        return;
    L_0x0017:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0085 }
        r1 = java.util.UUID.randomUUID();	 Catch:{ Exception -> 0x0088, all -> 0x00ad }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0088, all -> 0x00ad }
        r3 = "-";
        r8 = "";
        r1 = r1.replace(r3, r8);	 Catch:{ Exception -> 0x0088, all -> 0x00ad }
        r3 = a(r1);	 Catch:{ Exception -> 0x0088, all -> 0x00ad }
        r3.createNewFile();	 Catch:{ Exception -> 0x00da, all -> 0x00ad }
        r1 = 1;
        r3.setReadable(r1);	 Catch:{ Exception -> 0x00da, all -> 0x00ad }
        r1 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00da, all -> 0x00ad }
        r1.<init>(r3);	 Catch:{ Exception -> 0x00da, all -> 0x00ad }
        r2 = new java.io.ObjectOutputStream;	 Catch:{ Exception -> 0x00de }
        r8 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x00de }
        r8.<init>(r1);	 Catch:{ Exception -> 0x00de }
        r2.<init>(r8);	 Catch:{ Exception -> 0x00de }
        r2.writeObject(r13);	 Catch:{ Exception -> 0x00de }
        r2.flush();	 Catch:{ Exception -> 0x00de }
        r2.close();	 Catch:{ Exception -> 0x00de }
        if (r1 == 0) goto L_0x0053;
    L_0x0050:
        r1.close();	 Catch:{ IOException -> 0x00d3 }
    L_0x0053:
        if (r0 == 0) goto L_0x0015;
    L_0x0055:
        r0 = r3.renameTo(r14);	 Catch:{ all -> 0x0085 }
        if (r0 == 0) goto L_0x00b5;
    L_0x005b:
        r0 = "awcn.SerializeHelper";
        r1 = "persist end.";
        r2 = 0;
        r3 = 4;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0085 }
        r4 = 0;
        r8 = "file";
        r3[r4] = r8;	 Catch:{ all -> 0x0085 }
        r4 = 1;
        r8 = r14.getAbsoluteFile();	 Catch:{ all -> 0x0085 }
        r3[r4] = r8;	 Catch:{ all -> 0x0085 }
        r4 = 2;
        r8 = "cost";
        r3[r4] = r8;	 Catch:{ all -> 0x0085 }
        r4 = 3;
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0085 }
        r6 = r8 - r6;
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0085 }
        r3[r4] = r6;	 Catch:{ all -> 0x0085 }
        anet.channel.util.ALog.i(r0, r1, r2, r3);	 Catch:{ all -> 0x0085 }
        goto L_0x0015;
    L_0x0085:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0088:
        r0 = move-exception;
        r1 = r2;
    L_0x008a:
        r3 = "awcn.SerializeHelper";
        r8 = "persist fail. ";
        r9 = 0;
        r10 = 2;
        r10 = new java.lang.Object[r10];	 Catch:{ all -> 0x00d8 }
        r11 = 0;
        r12 = "file";
        r10[r11] = r12;	 Catch:{ all -> 0x00d8 }
        r11 = 1;
        r12 = r14.getName();	 Catch:{ all -> 0x00d8 }
        r10[r11] = r12;	 Catch:{ all -> 0x00d8 }
        anet.channel.util.ALog.e(r3, r8, r9, r0, r10);	 Catch:{ all -> 0x00d8 }
        if (r1 == 0) goto L_0x00e1;
    L_0x00a3:
        r1.close();	 Catch:{ IOException -> 0x00a9 }
        r0 = r4;
        r3 = r2;
        goto L_0x0053;
    L_0x00a9:
        r0 = move-exception;
        r0 = r4;
        r3 = r2;
        goto L_0x0053;
    L_0x00ad:
        r0 = move-exception;
        r1 = r2;
    L_0x00af:
        if (r1 == 0) goto L_0x00b4;
    L_0x00b1:
        r1.close();	 Catch:{ IOException -> 0x00d6 }
    L_0x00b4:
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x00b5:
        r0 = "awcn.SerializeHelper";
        r1 = "rename failed.";
        r2 = 0;
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0085 }
        anet.channel.util.ALog.e(r0, r1, r2, r3);	 Catch:{ all -> 0x0085 }
        r0 = anet.channel.appmonitor.AppMonitor.getInstance();	 Catch:{ all -> 0x0085 }
        r1 = new anet.channel.statist.ExceptionStatistic;	 Catch:{ all -> 0x0085 }
        r2 = -106; // 0xffffffffffffff96 float:NaN double:NaN;
        r3 = 0;
        r4 = "rt";
        r1.<init>(r2, r3, r4);	 Catch:{ all -> 0x0085 }
        r0.commitStat(r1);	 Catch:{ all -> 0x0085 }
        goto L_0x0015;
    L_0x00d3:
        r1 = move-exception;
        goto L_0x0053;
    L_0x00d6:
        r1 = move-exception;
        goto L_0x00b4;
    L_0x00d8:
        r0 = move-exception;
        goto L_0x00af;
    L_0x00da:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x008a;
    L_0x00de:
        r0 = move-exception;
        r2 = r3;
        goto L_0x008a;
    L_0x00e1:
        r0 = r4;
        r3 = r2;
        goto L_0x0053;
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.h.a(java.io.Serializable, java.io.File):void");
    }

    public static synchronized <T> T a(File file) {
        Throwable th;
        Throwable th2;
        T t = null;
        synchronized (h.class) {
            FileInputStream fileInputStream = null;
            FileInputStream fileInputStream2;
            try {
                if (file.exists()) {
                    fileInputStream2 = new FileInputStream(file);
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(fileInputStream2));
                        t = objectInputStream.readObject();
                        objectInputStream.close();
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            if (ALog.isPrintLog(3)) {
                                ALog.e("awcn.SerializeHelper", "restore file fail.", null, th, new Object[0]);
                            }
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            return t;
                        } catch (Throwable th4) {
                            th2 = th4;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                            throw th2;
                        }
                    }
                } else {
                    if (ALog.isPrintLog(3)) {
                        ALog.w("awcn.SerializeHelper", "file not exist.", null, "file", file.getName());
                    }
                    if (null != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                }
            } catch (Throwable th5) {
                fileInputStream2 = null;
                th2 = th5;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                throw th2;
            }
        }
        return t;
    }
}
