package anet.channel.strategy.dispatch;

import anet.channel.util.ALog;

/* compiled from: Taobao */
public class a {
    public static volatile double a = 0.0d;
    public static volatile double b = 0.0d;
    private static volatile int c = 0;
    private static volatile long d = 0;

    public static void a(int i, int i2) {
        ALog.i("awcn.AmdcRuntimeInfo", "set amdc limit", null, "level", Integer.valueOf(i), "time", Integer.valueOf(i2));
        if (c != i) {
            c = i;
            d = System.currentTimeMillis() + (((long) i2) * 1000);
        }
    }

    public static int a() {
        if (c > 0 && System.currentTimeMillis() - d > 0) {
            d = 0;
            c = 0;
        }
        return c;
    }
}
