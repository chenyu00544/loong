package anet.channel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.c.c;
import anet.channel.entity.ConnType;
import anet.channel.entity.ConnType.TypeLevel;
import anet.channel.entity.EventType;
import anet.channel.session.AccsSession;
import anet.channel.session.e;
import anet.channel.session.k;
import anet.channel.statist.AlarmObject;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.i;
import com.taobao.accs.common.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: Taobao */
class SessionRequest {
    String a;
    SessionCenter b;
    e c;
    volatile boolean d = false;
    volatile Session e;
    volatile boolean f = false;
    private String g;
    private volatile Future h;
    private Object i = new Object();

    /* compiled from: Taobao */
    static /* synthetic */ class SessionRequest_1 {
        static final /* synthetic */ int[] a = new int[EventType.values().length];

        static {
            try {
                a[EventType.AUTH_SUCC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[EventType.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[EventType.CONNECT_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* compiled from: Taobao */
    private interface IConnCb {
        void onDisConnect(Session session, long j, EventType eventType);

        void onFailed(Session session, long j, EventType eventType, int i);

        void onSuccess(Session session, long j);
    }

    /* compiled from: Taobao */
    class a implements IConnCb {
        boolean a = false;
        final /* synthetic */ SessionRequest b;
        private Context c;
        private List<anet.channel.entity.a> d;
        private anet.channel.entity.a e;

        a(SessionRequest sessionRequest, Context context, List<anet.channel.entity.a> list, anet.channel.entity.a aVar) {
            this.b = sessionRequest;
            this.c = context;
            this.d = list;
            this.e = aVar;
        }

        public void onFailed(Session session, long j, EventType eventType, int i) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SessionRequest", "Connect failed", this.e.i(), "session", session, "host", this.b.a(), "isHandleFinish", Boolean.valueOf(this.a));
            }
            if (this.b.f) {
                this.b.f = false;
            } else if (!this.a) {
                this.a = true;
                this.b.c.b(this.b, session);
                if (!session.tryNextWhenFail) {
                    return;
                }
                if (!NetworkStatusHelper.f()) {
                    this.b.c();
                } else if (this.d.size() > 0) {
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.SessionRequest", "use next connInfo to create session", this.e.i(), "host", this.b.a());
                    }
                    anet.channel.entity.a aVar = (anet.channel.entity.a) this.d.remove(0);
                    this.b.a(this.c, aVar, new a(this.b, this.c, this.d, aVar), aVar.i());
                } else {
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.SessionRequest", "connInfo has used up,finish", this.e.i(), "host", this.b.a());
                    }
                    this.b.c();
                    if (EventType.CONNECT_FAIL.equals(eventType) && i != -2613 && i != -2601) {
                        AlarmObject alarmObject = new AlarmObject();
                        alarmObject.module = "networkPrefer";
                        alarmObject.modulePoint = "policy";
                        alarmObject.arg = this.b.a;
                        alarmObject.errorCode = String.valueOf(i);
                        alarmObject.isSuccess = false;
                        AppMonitor.getInstance().commitAlarm(alarmObject);
                    }
                }
            }
        }

        public void onSuccess(Session session, long j) {
            ALog.d("awcn.SessionRequest", "Connect Success", this.e.i(), "session", session, "host", this.b.a());
            try {
                if (this.b.f) {
                    this.b.f = false;
                    session.close(false);
                    return;
                }
                this.b.c.a(this.b, session);
                if (session != null && (session instanceof AccsSession)) {
                    ((AccsSession) session).setFrameCb(this.b.b.accsFrameCb);
                    ALog.d("awcn.SessionRequest", "set Framecb success", null, "session", session);
                }
                AlarmObject alarmObject = new AlarmObject();
                alarmObject.module = "networkPrefer";
                alarmObject.modulePoint = "policy";
                alarmObject.arg = this.b.a;
                alarmObject.isSuccess = true;
                AppMonitor.getInstance().commitAlarm(alarmObject);
                this.b.c();
            } catch (Throwable e) {
                ALog.e("awcn.SessionRequest", "[onSuccess]:", this.e.i(), e, new Object[0]);
            } finally {
                this.b.c();
            }
        }

        public void onDisConnect(Session session, long j, EventType eventType) {
            boolean isAppBackground = GlobalAppRuntimeInfo.isAppBackground();
            ALog.d("awcn.SessionRequest", "Connect Disconnect", this.e.i(), "session", session, "host", this.b.a(), "appIsBg", Boolean.valueOf(isAppBackground), "isHandleFinish", Boolean.valueOf(this.a));
            this.b.c.b(this.b, session);
            if (!this.a) {
                this.a = true;
                if (!session.autoReCreate) {
                    return;
                }
                if (isAppBackground) {
                    ALog.e("awcn.SessionRequest", "[onDisConnect]app background, don't Recreate", this.e.i(), "session", session);
                } else if (NetworkStatusHelper.f()) {
                    try {
                        ALog.d("awcn.SessionRequest", "session disconnected, try to recreate session", this.e.i(), new Object[0]);
                        c.a(new h(this, session), (long) ((Math.random() * 10.0d) * 1000.0d), TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                    }
                } else {
                    ALog.e("awcn.SessionRequest", "[onDisConnect]no network, don't Recreate", this.e.i(), "session", session);
                }
            }
        }
    }

    /* compiled from: Taobao */
    private class b implements Runnable {
        String a = null;
        final /* synthetic */ SessionRequest b;

        b(SessionRequest sessionRequest, String str) {
            this.b = sessionRequest;
            this.a = str;
        }

        public void run() {
            if (this.b.d) {
                ALog.e("awcn.SessionRequest", "Connecting timeout!!! reset status!", this.a, new Object[0]);
                if (this.b.e != null) {
                    this.b.e.tryNextWhenFail = false;
                    this.b.e.close();
                }
                this.b.a(false);
            }
        }
    }

    SessionRequest(String str, SessionCenter sessionCenter) {
        this.a = str;
        this.g = this.a.substring(this.a.indexOf(HttpConstant.SCHEME_SPLIT) + 3);
        this.b = sessionCenter;
        this.c = sessionCenter.sessionPool;
    }

    protected String a() {
        return this.a;
    }

    protected String b() {
        return this.g;
    }

    void a(boolean z) {
        this.d = z;
        if (!z) {
            if (this.h != null) {
                this.h.cancel(true);
                this.h = null;
            }
            this.e = null;
        }
    }

    protected synchronized void a(Context context, TypeLevel typeLevel, String str) throws NoNetworkException, NoAvailStrategyException {
        if (this.c.a(this, typeLevel) != null) {
            ALog.d("awcn.SessionRequest", "Available Session exist!!!", str, new Object[0]);
        } else {
            if (TextUtils.isEmpty(str)) {
                str = i.a(null);
            }
            ALog.d("awcn.SessionRequest", "SessionRequest start", str, "host", this.a, "type", typeLevel);
            if (this.d) {
                ALog.d("awcn.SessionRequest", "session is connecting, return", str, "host", a());
            } else {
                a(true);
                this.h = c.a(new b(this, str), 45, TimeUnit.SECONDS);
                if (NetworkStatusHelper.f()) {
                    List a = a(typeLevel, str);
                    if (a.isEmpty()) {
                        ALog.i("awcn.SessionRequest", "no strategy, can't create session", str, "host", this.a, "type", typeLevel);
                        c();
                        throw new NoAvailStrategyException(this);
                    }
                    if (typeLevel == TypeLevel.HTTP) {
                        ListIterator listIterator = a.listIterator();
                        while (listIterator.hasNext()) {
                            IConnStrategy iConnStrategy = (IConnStrategy) listIterator.next();
                            if (e.a(this.a, iConnStrategy.getIp(), iConnStrategy.getPort())) {
                                listIterator.remove();
                            }
                        }
                        if (a.isEmpty()) {
                            ALog.i("awcn.SessionRequest", "all http strategies are removed.", null, new Object[0]);
                            c();
                        }
                    }
                    a = a(a, str);
                    try {
                        anet.channel.entity.a aVar = (anet.channel.entity.a) a.remove(0);
                        a(context, aVar, new a(this, context, a, aVar), aVar.i());
                    } catch (Throwable th) {
                        c();
                    }
                } else {
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.SessionRequest", "network is not available, can't create session", str, "NetworkStatusHelper.isConnected()", Boolean.valueOf(NetworkStatusHelper.f()));
                    }
                    c();
                    throw new NoNetworkException(this);
                }
            }
        }
    }

    private List<IConnStrategy> a(TypeLevel typeLevel, String str) {
        List<IConnStrategy> list = Collections.EMPTY_LIST;
        try {
            String[] parseURL = StringUtils.parseURL(a());
            if (parseURL == null) {
                return Collections.EMPTY_LIST;
            }
            list = StrategyCenter.getInstance().getConnStrategyListByHost(parseURL[1]);
            if (!list.isEmpty()) {
                boolean equalsIgnoreCase = HttpConstant.HTTPS.equalsIgnoreCase(parseURL[0]);
                ListIterator listIterator = list.listIterator();
                while (listIterator.hasNext()) {
                    IConnStrategy iConnStrategy = (IConnStrategy) listIterator.next();
                    if (!(iConnStrategy.getConnType().isSSL() == equalsIgnoreCase && (typeLevel == null || iConnStrategy.getConnType().getTypeLevel() == typeLevel))) {
                        listIterator.remove();
                    }
                }
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SessionRequest", "[getAvailStrategy]", str, "strategies", list);
            }
            return list;
        } catch (Throwable th) {
            Throwable th2 = th;
            List<IConnStrategy> list2 = list;
            ALog.e("awcn.SessionRequest", "", str, th2, new Object[0]);
            return list2;
        }
    }

    private List<anet.channel.entity.a> a(List<IConnStrategy> list, String str) {
        if (list.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<anet.channel.entity.a> arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            IConnStrategy iConnStrategy = (IConnStrategy) list.get(i2);
            int retryTimes = iConnStrategy.getRetryTimes();
            int i3 = 0;
            while (i3 <= retryTimes) {
                int i4 = i + 1;
                anet.channel.entity.a aVar = new anet.channel.entity.a(a(), str + "_" + i4, iConnStrategy);
                aVar.b = i3;
                aVar.c = retryTimes;
                arrayList.add(aVar);
                i3++;
                i = i4;
            }
        }
        return arrayList;
    }

    private void a(Context context, anet.channel.entity.a aVar, IConnCb iConnCb, String str) {
        ConnType c = aVar.c();
        if (context == null || c.isHttpType()) {
            this.e = new e(context, aVar);
        } else {
            if (aVar.f()) {
                this.e = new AccsSession(context, aVar);
                ((AccsSession) this.e).setFrameCb(this.b.accsFrameCb);
            } else {
                this.e = new anet.channel.session.i(context, aVar);
            }
            ((k) this.e).setConfig(this.b.config);
        }
        ALog.i("awcn.SessionRequest", "create connection...", str, HttpConstant.HOST, a(), "Type", aVar.c(), "IP", aVar.a(), "Port", Integer.valueOf(aVar.b()), "heartbeat", Integer.valueOf(aVar.h()), "session", this.e);
        a(this.e, iConnCb, System.currentTimeMillis());
        this.e.connect();
    }

    private void a(Session session, IConnCb iConnCb, long j) {
        if (iConnCb != null) {
            session.registerEventcb(EventType.ALL.getType(), new f(this, iConnCb, j));
            session.registerEventcb(((EventType.CONNECTED.getType() | EventType.CONNECT_FAIL.getType()) | EventType.AUTH_SUCC.getType()) | EventType.AUTH_FAIL.getType(), new g(this, session));
        }
    }

    protected void b(boolean z) {
        ALog.d("awcn.SessionRequest", "closeSessions", null, "host", this.a, "autoCreate", Boolean.valueOf(z));
        if (!(z || this.e == null)) {
            this.e.tryNextWhenFail = false;
            this.e.close(false);
        }
        List<Session> a = this.c.a(this);
        if (a != null) {
            for (Session session : a) {
                if (session != null) {
                    session.close(z);
                }
            }
        }
    }

    protected void a(String str) {
        ALog.d("awcn.SessionRequest", "reCreateSession", str, "host", this.a);
        b(true);
    }

    protected void a(long j) throws InterruptedException, TimeoutException {
        ALog.d("awcn.SessionRequest", "[await]", null, "timeoutMs", Long.valueOf(j));
        if (j > 0) {
            synchronized (this.i) {
                long currentTimeMillis = System.currentTimeMillis() + j;
                while (this.d) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 >= currentTimeMillis) {
                        break;
                    }
                    this.i.wait(currentTimeMillis - currentTimeMillis2);
                }
                if (this.d) {
                    throw new TimeoutException();
                }
            }
        }
    }

    protected void c() {
        a(false);
        synchronized (this.i) {
            this.i.notifyAll();
        }
    }

    protected void a(Session session, int i, String str) {
        Context context = GlobalAppRuntimeInfo.getContext();
        if (context != null) {
            String realHost = session.getRealHost();
            if (!TextUtils.isEmpty(realHost) && realHost.endsWith(this.b.config.getAccsHost())) {
                try {
                    Intent intent = new Intent(Constants.ACTION_RECEIVE);
                    intent.setPackage(context.getPackageName());
                    intent.setClassName(context, com.taobao.accs.utl.a.msgService);
                    intent.putExtra("command", 103);
                    intent.putExtra("host", session.getHost());
                    intent.putExtra(Constants.KEY_CENTER_HOST, realHost.equals(this.b.config.getAccsHost()));
                    boolean isAvailable = session.isAvailable();
                    if (!isAvailable) {
                        intent.putExtra(Constants.KEY_ERROR_CODE, i);
                        intent.putExtra(Constants.KEY_ERROR_DETAIL, str);
                    }
                    intent.putExtra(Constants.KEY_CONNECT_AVAILABLE, isAvailable);
                    intent.putExtra(Constants.KEY_TYPE_INAPP, true);
                    context.startService(intent);
                } catch (Throwable th) {
                    ALog.e("awcn.SessionRequest", "sendConnectInfoBroadCastToAccs", null, th, new Object[0]);
                }
            }
        }
    }
}
