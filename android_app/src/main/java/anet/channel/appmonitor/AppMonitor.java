package anet.channel.appmonitor;

import anet.channel.statist.AlarmObject;
import anet.channel.statist.CountObject;
import anet.channel.statist.StatObject;

/* compiled from: Taobao */
public class AppMonitor {
    private static volatile IAppMonitor appMonitor = new DefaultAppMonitor();

    /* compiled from: Taobao */
    final class AppMonitor_1 implements IAppMonitor {
        final /* synthetic */ IAppMonitor val$appMonitor;

        AppMonitor_1(IAppMonitor iAppMonitor) {
            this.val$appMonitor = iAppMonitor;
        }

        public void register() {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.register();
            }
        }

        public void register(Class<?> cls) {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.register(cls);
            }
        }

        public void commitStat(StatObject statObject) {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.commitStat(statObject);
            }
        }

        public void commitAlarm(AlarmObject alarmObject) {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.commitAlarm(alarmObject);
            }
        }

        public void commitCount(CountObject countObject) {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.commitCount(countObject);
            }
        }
    }

    public static IAppMonitor getInstance() {
        return appMonitor;
    }

    public static void setInstance(IAppMonitor iAppMonitor) {
        appMonitor = new AppMonitor_1(iAppMonitor);
    }
}
