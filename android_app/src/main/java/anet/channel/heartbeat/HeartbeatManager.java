package anet.channel.heartbeat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import anet.channel.Session;

/* compiled from: Taobao */
public class HeartbeatManager {
    private static volatile IHeartbeatFactory heartbeatFactory = new HeartbeatManager_1();

    /* compiled from: Taobao */
    final class HeartbeatManager_1 implements IHeartbeatFactory {
        HeartbeatManager_1() {
        }

        public IHeartbeat createHeartbeat(Session session) {
            if (session == null || session.getConnStrategy() == null || session.getConnStrategy().getHeartbeat() <= 0) {
                return null;
            }
            return new DefaultHeartbeatImpl(session);
        }
    }

    @Deprecated
    /* compiled from: Taobao */
    public static class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
        }
    }

    public static IHeartbeatFactory getHeartbeatFactory() {
        return heartbeatFactory;
    }

    public static void setHeartbeatFactory(IHeartbeatFactory iHeartbeatFactory) {
        heartbeatFactory = iHeartbeatFactory;
    }
}
