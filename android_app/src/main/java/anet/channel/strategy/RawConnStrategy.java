package anet.channel.strategy;

import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.b;
import anet.channel.entity.d;
import anet.channel.entity.e;
import com.taobao.accs.common.Constants;
import java.io.Serializable;
import java.util.Comparator;

/* compiled from: Taobao */
class RawConnStrategy implements Serializable, Comparable<RawConnStrategy> {
    static Comparator<RawConnStrategy> a = new f();
    private int b = 2;
    private long c = 2147483647L;
    public final ConnType connType;
    public final int cto;
    public final int heartbeat;
    public final boolean isAuth;
    public transient boolean isToRemove;
    public final int port;
    public final int retry;
    public final int rto;

    /* compiled from: Taobao */
    static class a {
        a() {
        }

        static RawConnStrategy a(anet.channel.strategy.l.a aVar) {
            ConnType valueOf = ConnType.valueOf(aVar);
            if (valueOf == null) {
                return null;
            }
            return new RawConnStrategy(aVar.a, valueOf, aVar.c, aVar.d, aVar.e, aVar.f, aVar.h);
        }

        static RawConnStrategy a() {
            return new RawConnStrategy(Constants.PORT, ConnType.H2_ACCS_0RTT, 0, 0, 1, 45000, true);
        }

        static RawConnStrategy a(int i, ConnType connType) {
            return new RawConnStrategy(i, connType, 0, 0, 1, 45000, false);
        }
    }

    protected RawConnStrategy(int i, ConnType connType, int i2, int i3, int i4, int i5, boolean z) {
        this.port = i;
        this.connType = connType;
        this.cto = i2;
        this.rto = i3;
        this.retry = i4;
        this.heartbeat = i5;
        this.isAuth = z;
    }

    public void notifyEvent(EventType eventType, d dVar) {
        switch (eventType) {
            case CONNECTED:
                this.b = 1;
                if (dVar instanceof b) {
                    this.c = ((b) dVar).a;
                    return;
                }
                return;
            case CONNECT_FAIL:
            case AUTH_FAIL:
                this.b = 3;
                return;
            case AUTH_SUCC:
                this.b = 0;
                return;
            case HORSE_RIDE:
                if (dVar instanceof e) {
                    e eVar = (e) dVar;
                    if (eVar.a) {
                        this.b = 0;
                        this.c = eVar.b;
                        return;
                    }
                    this.b = 3;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean isAvailable() {
        return this.b != 3;
    }

    public void resetConnStatus() {
        if (this.b == 3) {
            this.b = 2;
        }
    }

    public int compareTo(RawConnStrategy rawConnStrategy) {
        return a.compare(this, rawConnStrategy);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('{').append(this.port).append(' ').append(this.connType).append(' ').append(a()).append('}');
        return stringBuilder.toString();
    }

    private char a() {
        switch (this.b) {
            case 0:
                return 'A';
            case 1:
                return 'C';
            case 2:
                return 'N';
            default:
                return 'U';
        }
    }
}
