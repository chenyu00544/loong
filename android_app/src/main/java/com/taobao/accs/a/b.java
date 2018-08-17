package com.taobao.accs.a;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.a.a.a;
import com.taobao.accs.utl.ALog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: Taobao */
final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    b(Context context, a aVar) {
        this.a = context;
        this.b = aVar;
    }

    public void run() {
        Throwable th;
        Throwable th2;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2;
        try {
            if (a.c == null) {
                Context context = this.a;
                a.a();
            }
            if (a.c != null) {
                a.c.mkdirs();
            }
            ALog.i(a.TAG, "saveElectionResult electionFile", "path", a.d.getPath(), "host", this.b.a, "retry", Integer.valueOf(this.b.b));
            fileOutputStream2 = new FileOutputStream(a.d);
            try {
                Map hashMap = new HashMap();
                hashMap.put(com.umeng.message.common.a.c, TextUtils.isEmpty(this.b.a) ? "" : this.b.a);
                hashMap.put("retry", this.b.b + "");
                if (a.e <= 0 || a.e >= System.currentTimeMillis()) {
                    hashMap.put("lastFlushTime", System.currentTimeMillis() + "");
                } else {
                    hashMap.put("lastFlushTime", a.e + "");
                }
                fileOutputStream2.write(new JSONObject(hashMap).toString().getBytes("UTF-8"));
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Throwable th3) {
                    }
                }
            } catch (IOException e) {
                fileOutputStream = fileOutputStream2;
                try {
                    a.c = new File(this.a.getFilesDir().getPath() + a.d());
                    ALog.i(a.TAG, "path invailable, new path=" + a.c, new Object[0]);
                    a.d = new File(a.c, com.taobao.accs.internal.a.ELECTION_SERVICE_ID);
                    a.a = a.d.getPath();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th4) {
                }
            } catch (Throwable th5) {
                th = th5;
                try {
                    ALog.e(a.TAG, "saveElectionResult is error,e=" + th.toString(), new Object[0]);
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable th6) {
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable th8) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e2) {
            a.c = new File(this.a.getFilesDir().getPath() + a.d());
            ALog.i(a.TAG, "path invailable, new path=" + a.c, new Object[0]);
            a.d = new File(a.c, com.taobao.accs.internal.a.ELECTION_SERVICE_ID);
            a.a = a.d.getPath();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th9) {
            th2 = th9;
            fileOutputStream2 = null;
            th = th2;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }
}
