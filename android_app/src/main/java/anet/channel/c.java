package anet.channel;

import anet.channel.b.b_1;
import anet.channel.entity.EventCb;
import anet.channel.entity.EventType;
import anet.channel.entity.b;
import anet.channel.entity.d;
import anet.channel.entity.e;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;

/* compiled from: Taobao */
final class c implements EventCb {
    final /* synthetic */ long a;

    c(long j) {
        this.a = j;
    }

    public void onEvent(Session session, EventType eventType, d dVar) {
        if (session != null && eventType != null) {
            e eVar = new e(EventType.HORSE_RIDE);
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.HorseRide", "horse ride evnet callback now !!!! ", session.mSeq, "ip", session.getIp(), "port", Integer.valueOf(session.getPort()), "conntype", session.getConnType(), "EventType", eventType, "Event", dVar);
            }
            switch (b_1.a[eventType.ordinal()]) {
                case 1:
                    eVar.a = true;
                    if (dVar instanceof b) {
                        eVar.b = ((b) dVar).a;
                    } else {
                        eVar.b = System.currentTimeMillis() - this.a;
                    }
                    b.c(session);
                    break;
                case 2:
                    b.b(eVar, dVar);
                    break;
                case 3:
                    b.b(eVar, dVar);
                    break;
                default:
                    return;
            }
            StrategyCenter.getInstance().notifyConnEvent(session.getRealHost(), session.getConnStrategy(), EventType.HORSE_RIDE, eVar);
        }
    }
}
