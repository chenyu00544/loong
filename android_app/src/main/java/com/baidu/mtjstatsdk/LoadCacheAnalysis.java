package com.baidu.mtjstatsdk;

import android.content.Context;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;
import java.util.HashMap;

public class LoadCacheAnalysis {
    static HashMap<String, i> a = new HashMap();
    private static LoadCacheAnalysis b;
    private static Context c;

    private static void a(Context context, String str) {
        if (context != null) {
            c = context;
        }
        if (a.get(str) == null) {
            a.put(str, getInstance().getLoadCacheObject(context, str));
        }
    }

    private static synchronized void a(String str) {
        synchronized (LoadCacheAnalysis.class) {
            ((i) a.get(str)).b = true;
        }
    }

    private synchronized void b(String str) {
        ((i) a.get(str)).c = true;
    }

    public static void checkLoadCacheFinished(Context context, String str) {
        if (context != null) {
            a(context, str);
            i iVar = (i) a.get(str);
            if (!iVar.c) {
                synchronized (iVar.e) {
                    try {
                        iVar.e.wait();
                    } catch (Throwable e) {
                        if (a.a(str)) {
                            d.a("statsdk", e);
                        }
                    }
                }
            }
        }
    }

    public static void checkStartLoadCache(Context context, String str) {
        if (context != null) {
            a(context, str);
            i iVar = (i) a.get(str);
            if (iVar != null && !iVar.b) {
                try {
                    a(str);
                    iVar.e.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static LoadCacheAnalysis getInstance() {
        if (b == null) {
            b = new LoadCacheAnalysis();
        }
        return b;
    }

    public i getLoadCacheObject(Context context, String str) {
        return new i(this, context, str);
    }

    public void initLoadCacheParams(String str) {
        ((i) a.get(str)).b = false;
        ((i) a.get(str)).c = false;
    }

    public synchronized boolean isFinished(String str) {
        return ((i) a.get(str)).c;
    }

    public synchronized boolean isStart(String str) {
        return a.get(str) == null ? false : ((i) a.get(str)).b;
    }
}
