package anet.channel.strategy;

import android.content.Context;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.util.ALog;
import anet.channel.util.h;
import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: Taobao */
class m {
    private static File a = null;
    private static FileFilter b = new n();
    private static Comparator<File> c = new o();

    m() {
    }

    public static void a() {
        try {
            Context context = GlobalAppRuntimeInfo.getContext();
            if (context != null) {
                a = new File(context.getExternalFilesDir(null), "awcn_strategy");
                if (!a(a)) {
                    a = new File(context.getFilesDir(), "awcn_strategy");
                    if (!a(a)) {
                        ALog.e("awcn.StrategySerializeHelper", "create directory failed!!!", null, "dir", a.getAbsolutePath());
                    }
                }
                if (!GlobalAppRuntimeInfo.isTargetProcess()) {
                    String currentProcess = GlobalAppRuntimeInfo.getCurrentProcess();
                    a = new File(a, currentProcess.substring(currentProcess.indexOf(58) + 1));
                    if (!a(a)) {
                        ALog.e("awcn.StrategySerializeHelper", "create directory failed!!!", null, "dir", a.getAbsolutePath());
                    }
                }
                ALog.i("awcn.StrategySerializeHelper", "StrateyFolder", null, "path", a.getAbsolutePath());
                d();
            }
        } catch (Throwable th) {
            ALog.e("awcn.StrategySerializeHelper", "StrategySerializeHelper initialize failed!!!", null, th, new Object[0]);
        }
    }

    private static boolean a(File file) {
        if (file == null || file.exists()) {
            return true;
        }
        return file.mkdir();
    }

    public static File a(String str) {
        a(a);
        return new File(a, str);
    }

    static synchronized void b() {
        synchronized (m.class) {
            ALog.i("awcn.StrategySerializeHelper", "clear start.", null, new Object[0]);
            if (a != null) {
                File[] listFiles = a.listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (file.isFile()) {
                            file.delete();
                        }
                    }
                    ALog.i("awcn.StrategySerializeHelper", "clear end.", null, new Object[0]);
                }
            }
        }
    }

    static synchronized File[] c() {
        File[] fileArr;
        synchronized (m.class) {
            if (a == null) {
                fileArr = null;
            } else {
                fileArr = a.listFiles(b);
                if (fileArr != null) {
                    Arrays.sort(fileArr, c);
                }
            }
        }
        return fileArr;
    }

    static synchronized void d() {
        int i = 0;
        synchronized (m.class) {
            File[] c = c();
            if (c != null) {
                for (File file : c) {
                    if (System.currentTimeMillis() - file.lastModified() >= 604800000) {
                        file.delete();
                    } else if (!file.getName().equalsIgnoreCase("config")) {
                        int i2 = i + 1;
                        if (((long) i) > 10) {
                            file.delete();
                        }
                        i = i2;
                    }
                }
            }
        }
    }

    static synchronized void a(Serializable serializable, String str) {
        synchronized (m.class) {
            h.a(serializable, a(str));
        }
    }

    static synchronized <T> T b(String str) {
        T a;
        synchronized (m.class) {
            a = h.a(a(str));
        }
        return a;
    }
}
