package anet.channel;

import android.content.Context;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.a;
import anet.channel.entity.d;
import anet.channel.entity.e;
import anet.channel.session.AccsSession;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.IHRStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.i;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: Taobao */
public class b {
    public static final String HR_SERIAL = "serial";
    public static final String HR_SERIAL_CONN = "serialConn";
    public static final String HR_SERIAL_ONLY = "serialOnly";
    private static final int a = ((EventType.AUTH_SUCC.getType() | EventType.AUTH_FAIL.getType()) | EventType.CONNECT_FAIL.getType());

    /* compiled from: Taobao */
    static /* synthetic */ class b_1 {
        static final /* synthetic */ int[] a = new int[EventType.values().length];

        static {
            try {
                a[EventType.AUTH_SUCC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[EventType.AUTH_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[EventType.CONNECT_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static void a() {
        Map hRStrategyMap = StrategyCenter.getInstance().getHRStrategyMap();
        if (hRStrategyMap != null && !hRStrategyMap.isEmpty()) {
            for (Entry entry : hRStrategyMap.entrySet()) {
                IHRStrategy iHRStrategy = (IHRStrategy) entry.getValue();
                String hRStrategy = iHRStrategy.getHRStrategy();
                long currentTimeMillis = System.currentTimeMillis();
                if ((HR_SERIAL.equals(hRStrategy) || HR_SERIAL_ONLY.equals(hRStrategy)) && currentTimeMillis - iHRStrategy.getLastHRTime() > iHRStrategy.getHRInterval() && iHRStrategy.getHrNum() > 0) {
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.HorseRide", "horse ride for this host", null, "host", entry.getKey());
                    }
                    a((String) entry.getKey(), iHRStrategy.getHrNum());
                }
            }
        }
    }

    private static void a(String str, int i) {
        List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(str);
        if (connStrategyListByHost != null && !connStrategyListByHost.isEmpty()) {
            if (connStrategyListByHost.size() > i) {
                Collections.shuffle(connStrategyListByHost);
            }
            int i2 = 0;
            for (IConnStrategy a : connStrategyListByHost) {
                int i3;
                Session a2 = a(a, str);
                if (a2 != null) {
                    b(a2);
                    a2.connect();
                    i3 = i2 + 1;
                } else {
                    i3 = i2;
                }
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }

    private static void b(Session session) {
        session.registerEventcb(a, new c(System.currentTimeMillis()));
    }

    private static void b(e eVar, d dVar) {
        eVar.a = false;
        if (dVar != null) {
            eVar.d = dVar.d;
            eVar.e = dVar.e;
        }
    }

    private static Session a(IConnStrategy iConnStrategy, String str) {
        Session eVar;
        Context context = GlobalAppRuntimeInfo.getContext();
        a aVar = new a(StringUtils.buildKey(iConnStrategy.getConnType().isSSL() ? HttpConstant.HTTPS : HttpConstant.HTTP, str), i.a("HorseRide"), iConnStrategy);
        ConnType connType = iConnStrategy.getConnType();
        if (connType.equals(ConnType.HTTP) || connType.equals(ConnType.HTTPS)) {
            eVar = new anet.channel.session.e(context, aVar);
        } else if (aVar.f()) {
            eVar = new AccsSession(context, aVar);
        } else {
            eVar = new anet.channel.session.i(context, aVar);
        }
        if (eVar != null) {
            eVar.setIsHorseRide(true);
        }
        return eVar;
    }

    private static void c(Session session) {
        for (SessionCenter sessionCenter : SessionCenter.instancesMap.values()) {
            List<Session> a = sessionCenter.sessionPool.a(sessionCenter.getSessionRequest(session.getHost()));
            if (a != null) {
                for (Session sameSession : a) {
                    if (sameSession.sameSession(session)) {
                        return;
                    }
                }
                continue;
            }
            session.close();
        }
    }
}
