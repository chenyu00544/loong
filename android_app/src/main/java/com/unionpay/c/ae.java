package com.unionpay.c;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import anet.channel.util.HttpConstant;
import com.baidu.mapapi.UIMsg.m_AppUI;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

class ae {
    private static volatile ae f = null;
    private static ak g = null;
    private static Handler n;
    private static HandlerThread o;
    private final String a = "140.207.168.45";
    private final String b = "140.207.168.45";
    private final String c = HttpConstant.HTTP;
    private final String d = "http://140.207.168.45/g/d";
    private volatile boolean e = false;
    private r h = new a(this, "140.207.168.45", "140.207.168.45", "http://140.207.168.45/g/d");
    private final int i = m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT;
    private int j = m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT;
    private boolean k = false;
    private long l = (SystemClock.elapsedRealtime() - ((long) this.j));
    private Random m = new Random();
    private FileLock p = null;
    private final String q = "";

    final class a extends r {
        final /* synthetic */ ae e;

        a(ae aeVar, String str, String str2, String str3) {
            this.e = aeVar;
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = 1;
        }

        final byte[] a(HashMap hashMap) {
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                OutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                aq aqVar = new aq(gZIPOutputStream);
                if (!hashMap.containsKey("entity") || hashMap.get("entity") == null || !(hashMap.get("entity") instanceof f)) {
                    return null;
                }
                aqVar.a((f) hashMap.get("entity"));
                gZIPOutputStream.finish();
                gZIPOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    static {
        n = null;
        HandlerThread handlerThread = new HandlerThread("prepareSubmitHandlerThread");
        o = handlerThread;
        handlerThread.start();
        n = new ar(o.getLooper());
        try {
            i.a().a(a());
        } catch (Throwable th) {
        }
    }

    private ae() {
    }

    static ae a() {
        if (f == null) {
            synchronized (ae.class) {
                if (f == null) {
                    f = new ae();
                }
            }
        }
        return f;
    }

    private synchronized void b() {
        if (!this.e) {
            try {
                if (d.e != null) {
                    this.p = d.e.tryLock();
                }
                if (this.p == null) {
                    String str = "Aborted submitting, file cannot be accessed due to lock.";
                    if (a.a) {
                        Log.d("UPLog", str);
                    }
                } else if (ah.a(d.c)) {
                    f a = as.a(ad.d());
                    ab abVar = new ab();
                    abVar.c = this.h.a;
                    abVar.b = this.h.b;
                    abVar.a = this.h.c;
                    abVar.d = "Analytics";
                    abVar.e = "";
                    abVar.g = a;
                    if (a == null) {
                        y.a("No new data found!");
                        abVar.f = null;
                    } else {
                        y.a("New data found, Submitting...");
                        HashMap hashMap = new HashMap();
                        hashMap.put("entity", a);
                        abVar.f = this.h.a(hashMap);
                        abVar.h = true;
                    }
                    Message obtain = Message.obtain();
                    obtain.obj = abVar;
                    obtain.what = 103;
                    w.a().sendMessage(obtain);
                    this.e = true;
                }
            } catch (Throwable th) {
                c();
            }
        }
    }

    private void c() {
        if (this.p != null) {
            try {
                this.p.release();
            } catch (Throwable th) {
            }
        }
    }
}
