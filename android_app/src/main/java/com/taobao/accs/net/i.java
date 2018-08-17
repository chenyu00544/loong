package com.taobao.accs.net;

import anet.channel.strategy.StrategyCenter;

/* compiled from: Taobao */
class i implements Runnable {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public void run() {
        StrategyCenter.getInstance().saveData();
    }
}
