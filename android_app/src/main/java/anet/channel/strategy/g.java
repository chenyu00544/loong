package anet.channel.strategy;

import java.io.File;

/* compiled from: Taobao */
class g implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ StrategyInfoHolder b;

    g(StrategyInfoHolder strategyInfoHolder, String str) {
        this.b = strategyInfoHolder;
        this.a = str;
    }

    public void run() {
        int i = 0;
        try {
            File[] c = m.c();
            if (c != null) {
                for (int i2 = 0; i2 < c.length && i < 2; i2++) {
                    String name = c[i2].getName();
                    if (!(name.equals(this.a) || name.equals("config"))) {
                        this.b.a(name, null);
                        i++;
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
