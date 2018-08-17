package com.taobao.accs.net;

import anet.channel.strategy.dispatch.DispatchEvent;
import anet.channel.strategy.dispatch.HttpDispatcher.IDispatchEventListener;
import com.taobao.accs.common.a;
import java.util.concurrent.TimeUnit;

/* compiled from: Taobao */
class h implements IDispatchEventListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void onEvent(DispatchEvent dispatchEvent) {
        a.a(new i(this), 2000, TimeUnit.MILLISECONDS);
    }
}
