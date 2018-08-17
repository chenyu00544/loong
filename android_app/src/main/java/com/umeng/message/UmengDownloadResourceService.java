package com.umeng.message;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.TextUtils;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UMessage;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

public class UmengDownloadResourceService extends Service {
    public static final String TAG = UmengDownloadResourceService.class.getSimpleName();
    private static final String d = ".tmp";
    private static final String e = "RETRY_TIME";
    private static final String f = "OPERATIOIN";
    private static final int g = 1;
    private static final int h = 2;
    private static final long i = 1048576;
    private static final long j = 86400000;
    private static final int k = 300000;
    private static final int l = 3;
    private static Thread m;
    ScheduledThreadPoolExecutor a;
    Context b;
    ArrayList<String> c;

    public class DownloadResourceTask extends AsyncTask<Void, Void, Boolean> {
        UMessage a;
        ArrayList<String> b = new ArrayList();
        int c;
        final /* synthetic */ UmengDownloadResourceService d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Boolean) obj);
        }

        public DownloadResourceTask(UmengDownloadResourceService umengDownloadResourceService, UMessage uMessage, int i) {
            this.d = umengDownloadResourceService;
            this.a = uMessage;
            if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                try {
                    this.b.add(new JSONObject(uMessage.custom).optString("img"));
                } catch (Exception e) {
                }
            }
            if (uMessage.isLargeIconFromInternet()) {
                this.b.add(uMessage.img);
            }
            if (uMessage.isSoundFromInternet()) {
                this.b.add(uMessage.sound);
            }
            this.c = i;
        }

        protected Boolean a(Void... voidArr) {
            Iterator it = this.b.iterator();
            boolean z = true;
            while (it.hasNext()) {
                z = download((String) it.next()) & z;
            }
            return Boolean.valueOf(z);
        }

        protected void a(Boolean bool) {
            super.onPostExecute(bool);
            this.d.c.remove(this.a.msg_id);
            if (bool.booleanValue() || this.c <= 0) {
                MessageSharedPrefs.getInstance(this.d.b).setMessageResourceDownloaded(this.a.msg_id);
                String jSONObject = this.a.getRaw().toString();
                Intent intent = new Intent(this.d.b, UmengDownloadResourceService.class);
                intent.putExtra("body", jSONObject);
                intent.putExtra("id", this.a.message_id);
                intent.putExtra(AgooConstants.MESSAGE_TASK_ID, this.a.task_id);
                intent.putExtra(UmengDownloadResourceService.f, 1);
                intent.putExtra(UmengDownloadResourceService.e, this.c);
                this.d.startService(intent);
            } else if (this.d.c.size() == 0) {
                this.d.stopSelf();
            }
        }

        public boolean download(String str) {
            Closeable fileOutputStream;
            Exception e;
            Throwable th;
            Closeable closeable = null;
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            Closeable openStream;
            try {
                String str2 = str.hashCode() + "";
                String messageResourceFolder = UmengDownloadResourceService.getMessageResourceFolder(this.d.b, this.a);
                File file = new File(messageResourceFolder, str2 + UmengDownloadResourceService.d);
                File file2 = new File(messageResourceFolder, str2);
                if (file2.exists()) {
                    this.d.close(null);
                    this.d.close(null);
                    return true;
                }
                File file3 = new File(messageResourceFolder);
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                openStream = new URL(new URI(str).toASCIIString()).openStream();
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = openStream;
                    try {
                        e.printStackTrace();
                        this.d.close(fileOutputStream);
                        this.d.close(closeable);
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        openStream = fileOutputStream;
                        this.d.close(openStream);
                        this.d.close(closeable);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    this.d.close(openStream);
                    this.d.close(closeable);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[10240];
                    while (true) {
                        int read = openStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            file.renameTo(file2);
                            this.d.close(openStream);
                            this.d.close(fileOutputStream);
                            return true;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    closeable = fileOutputStream;
                    fileOutputStream = openStream;
                    e.printStackTrace();
                    this.d.close(fileOutputStream);
                    this.d.close(closeable);
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    closeable = fileOutputStream;
                    this.d.close(openStream);
                    this.d.close(closeable);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                fileOutputStream = null;
                e.printStackTrace();
                this.d.close(fileOutputStream);
                this.d.close(closeable);
                return false;
            } catch (Throwable th5) {
                th = th5;
                openStream = null;
                this.d.close(openStream);
                this.d.close(closeable);
                throw th;
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        this.a = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
        this.b = this;
        this.c = new ArrayList();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    public int onStartCommand(android.content.Intent r6, int r7, int r8) {
        /*
        r5 = this;
        if (r6 != 0) goto L_0x0007;
    L_0x0002:
        r0 = super.onStartCommand(r6, r7, r8);
    L_0x0006:
        return r0;
    L_0x0007:
        r0 = "OPERATIOIN";
        r1 = 2;
        r0 = r6.getIntExtra(r0, r1);
        r1 = "RETRY_TIME";
        r2 = 3;
        r1 = r6.getIntExtra(r1, r2);
        r2 = "body";
        r2 = r6.getStringExtra(r2);
        r3 = new com.umeng.message.entity.UMessage;	 Catch:{ Exception -> 0x0066 }
        r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0066 }
        r4.<init>(r2);	 Catch:{ Exception -> 0x0066 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0066 }
        r2 = "id";
        r2 = r6.getStringExtra(r2);	 Catch:{ Exception -> 0x0066 }
        r3.message_id = r2;	 Catch:{ Exception -> 0x0066 }
        r2 = "task_id";
        r2 = r6.getStringExtra(r2);	 Catch:{ Exception -> 0x0066 }
        r3.task_id = r2;	 Catch:{ Exception -> 0x0066 }
        r2 = r5.c;	 Catch:{ Exception -> 0x0066 }
        r4 = r3.msg_id;	 Catch:{ Exception -> 0x0066 }
        r2 = r2.contains(r4);	 Catch:{ Exception -> 0x0066 }
        if (r2 == 0) goto L_0x0044;
    L_0x003f:
        r0 = super.onStartCommand(r6, r7, r8);	 Catch:{ Exception -> 0x0066 }
        goto L_0x0006;
    L_0x0044:
        r2 = r5.c;	 Catch:{ Exception -> 0x0066 }
        r4 = r3.msg_id;	 Catch:{ Exception -> 0x0066 }
        r2.add(r4);	 Catch:{ Exception -> 0x0066 }
        switch(r0) {
            case 1: goto L_0x006b;
            case 2: goto L_0x0053;
            default: goto L_0x004e;
        };
    L_0x004e:
        r0 = super.onStartCommand(r6, r7, r8);
        goto L_0x0006;
    L_0x0053:
        r0 = TAG;	 Catch:{ Exception -> 0x0066 }
        r2 = "Start Download Resource";
        com.umeng.message.common.UmLog.i(r0, r2);	 Catch:{ Exception -> 0x0066 }
        r0 = r1 + -1;
        r5.setAlarm(r3, r0);	 Catch:{ Exception -> 0x0066 }
        r5.checkCache();	 Catch:{ Exception -> 0x0066 }
        r5.downloadResource(r3, r0);	 Catch:{ Exception -> 0x0066 }
        goto L_0x004e;
    L_0x0066:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004e;
    L_0x006b:
        r5.deleteAlarm(r3, r1);	 Catch:{ Exception -> 0x0066 }
        r0 = TAG;	 Catch:{ Exception -> 0x0066 }
        r1 = "Show Notification After Downloaded Resource";
        com.umeng.message.common.UmLog.i(r0, r1);	 Catch:{ Exception -> 0x0066 }
        r5.notification(r3);	 Catch:{ Exception -> 0x0066 }
        r0 = r5.c;	 Catch:{ Exception -> 0x0066 }
        r1 = r3.msg_id;	 Catch:{ Exception -> 0x0066 }
        r0.remove(r1);	 Catch:{ Exception -> 0x0066 }
        r0 = r5.c;	 Catch:{ Exception -> 0x0066 }
        r0 = r0.size();	 Catch:{ Exception -> 0x0066 }
        if (r0 != 0) goto L_0x004e;
    L_0x0087:
        r5.stopSelf();	 Catch:{ Exception -> 0x0066 }
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.UmengDownloadResourceService.onStartCommand(android.content.Intent, int, int):int");
    }

    public void notification(UMessage uMessage) {
        UHandler adHandler;
        if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
            adHandler = PushAgent.getInstance(this).getAdHandler();
        } else {
            adHandler = PushAgent.getInstance(this).getMessageHandler();
        }
        if (adHandler == null) {
            return;
        }
        if (TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type)) {
            UmengMessageHandler umengMessageHandler = (UmengMessageHandler) PushAgent.getInstance(this.b).getMessageHandler();
            if (umengMessageHandler != null) {
                umengMessageHandler.dealWithNotificationMessage(this.b, uMessage);
                return;
            }
            return;
        }
        adHandler.handleMessage(this, uMessage);
    }

    @SuppressLint({"NewApi"})
    public void downloadResource(UMessage uMessage, int i) {
        DownloadResourceTask downloadResourceTask = new DownloadResourceTask(this, uMessage, i);
        if (VERSION.SDK_INT >= 11) {
            downloadResourceTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            downloadResourceTask.execute(new Void[0]);
        }
    }

    public void setAlarm(UMessage uMessage, int i) {
        UmLog.i(TAG, "setAlarm");
        ((AlarmManager) getSystemService("alarm")).set(1, System.currentTimeMillis() + 300000, a(uMessage, i));
    }

    public void deleteAlarm(UMessage uMessage, int i) {
        UmLog.i(TAG, "deleteAlarm");
        ((AlarmManager) getSystemService("alarm")).cancel(a(uMessage, i));
    }

    private PendingIntent a(UMessage uMessage, int i) {
        String jSONObject = uMessage.getRaw().toString();
        int hashCode = uMessage.msg_id.hashCode();
        Intent intent = new Intent(this.b, UmengDownloadResourceService.class);
        intent.putExtra("body", jSONObject);
        intent.putExtra("id", uMessage.message_id);
        intent.putExtra(AgooConstants.MESSAGE_TASK_ID, uMessage.task_id);
        intent.putExtra(f, 2);
        intent.putExtra(e, i);
        PendingIntent service = PendingIntent.getService(this.b, hashCode, intent, 134217728);
        UmLog.i(TAG, "PendingIntent: msgId:" + uMessage.msg_id + ",requestCode:" + hashCode + ",retryTime:" + i);
        return service;
    }

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void checkCache() {
        try {
            checkDir(new File(getMessageResourceFolder(this.b, null)), i, 86400000);
        } catch (Throwable th) {
        }
    }

    public static void checkDir(final File file, long j, final long j2) throws IOException {
        if (file.exists() && a(file.getCanonicalFile()) > j) {
            if (m == null) {
                m = new Thread(new Runnable() {
                    public void run() {
                        UmengDownloadResourceService.b(file, j2);
                        UmengDownloadResourceService.m = null;
                    }
                });
            }
            synchronized (m) {
                m.start();
            }
        }
    }

    private static long a(File file) {
        if (file == null || !file.exists() || !file.isDirectory()) {
            return 0;
        }
        Stack stack = new Stack();
        stack.clear();
        stack.push(file);
        long j = 0;
        while (!stack.isEmpty()) {
            long j2 = j;
            for (File file2 : ((File) stack.pop()).listFiles()) {
                if (!file2.isDirectory()) {
                    j2 += file2.length();
                }
            }
            j = j2;
        }
        return j;
    }

    private static void b(File file, long j) {
        if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.isDirectory() && System.currentTimeMillis() - file2.lastModified() > j) {
                    file2.delete();
                }
            }
        }
    }

    public static String getMessageResourceFolder(Context context, UMessage uMessage) {
        String str = context.getCacheDir() + "/umeng_push/";
        if (uMessage == null || uMessage.msg_id == null) {
            return str;
        }
        return str + uMessage.msg_id + "/";
    }
}
