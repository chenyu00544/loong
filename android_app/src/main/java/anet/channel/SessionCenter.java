package anet.channel;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.AccsSessionManager.Callback;
import anet.channel.Config.Builder;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ConnType;
import anet.channel.entity.ConnType.TypeLevel;
import anet.channel.entity.ENV;
import anet.channel.session.AccsSession;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.strategy.IStrategyListener;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.a;
import anet.channel.strategy.l;
import anet.channel.strategy.l.b;
import anet.channel.strategy.l.c;
import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;
import anet.channel.util.AppLifecycle.AppLifecycleListener;
import anet.channel.util.HttpConstant;
import anet.channel.util.LruCache;
import anet.channel.util.StringUtils;
import anet.channel.util.Utils;
import anet.channel.util.e;
import anet.channel.util.i;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;

/* compiled from: Taobao */
public class SessionCenter {
    public static final String TAG = "awcn.SessionCenter";
    static Map<Config, SessionCenter> instancesMap = new HashMap();
    private static boolean mInit = false;
    AccsFrameCb accsFrameCb;
    final AccsSessionManager accsSessionManager;
    Config config;
    Context context = GlobalAppRuntimeInfo.getContext();
    final InnerListener innerListener = new InnerListener();
    String seqNum;
    final e sessionPool = new e();
    final LruCache<String, SessionRequest> srCache = new LruCache(32);

    /* compiled from: Taobao */
    private class InnerListener implements INetworkStatusChangeListener, IStrategyListener, AppLifecycleListener {
        boolean foreGroundCheckRunning;

        /* compiled from: Taobao */
        class SessionCenter_InnerListener_1 implements Runnable {
            SessionCenter_InnerListener_1() {
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r5 = this;
                r4 = 0;
                r0 = anet.channel.util.AppLifecycle.a;	 Catch:{ Exception -> 0x0030 }
                r2 = 0;
                r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                if (r0 == 0) goto L_0x0026;
            L_0x0009:
                r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0030 }
                r2 = anet.channel.util.AppLifecycle.a;	 Catch:{ Exception -> 0x0030 }
                r0 = r0 - r2;
                r2 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
                r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                if (r0 <= 0) goto L_0x0026;
            L_0x0017:
                r0 = anet.channel.SessionCenter.InnerListener.this;	 Catch:{ Exception -> 0x0030 }
                r0 = anet.channel.SessionCenter.this;	 Catch:{ Exception -> 0x0030 }
                r0 = r0.accsSessionManager;	 Catch:{ Exception -> 0x0030 }
                r1 = 1;
                r0.forceCloseSession(r1);	 Catch:{ Exception -> 0x0030 }
            L_0x0021:
                r0 = anet.channel.SessionCenter.InnerListener.this;
                r0.foreGroundCheckRunning = r4;
            L_0x0025:
                return;
            L_0x0026:
                r0 = anet.channel.SessionCenter.InnerListener.this;	 Catch:{ Exception -> 0x0030 }
                r0 = anet.channel.SessionCenter.this;	 Catch:{ Exception -> 0x0030 }
                r0 = r0.accsSessionManager;	 Catch:{ Exception -> 0x0030 }
                r0.checkAndStartAccsSession();	 Catch:{ Exception -> 0x0030 }
                goto L_0x0021;
            L_0x0030:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ all -> 0x0039 }
                r0 = anet.channel.SessionCenter.InnerListener.this;
                r0.foreGroundCheckRunning = r4;
                goto L_0x0025;
            L_0x0039:
                r0 = move-exception;
                r1 = anet.channel.SessionCenter.InnerListener.this;
                r1.foreGroundCheckRunning = r4;
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: anet.channel.SessionCenter.InnerListener.1.run():void");
            }
        }

        /* compiled from: Taobao */
        class SessionCenter_InnerListener_2 implements Runnable {
            SessionCenter_InnerListener_2() {
            }

            public void run() {
                ALog.d(SessionCenter.TAG, "horse serial ride start", SessionCenter.this.seqNum, new Object[0]);
                b.a();
            }
        }

        private InnerListener() {
            this.foreGroundCheckRunning = false;
        }

        void registerAll() {
            AppLifecycle.a(this);
            NetworkStatusHelper.a((INetworkStatusChangeListener) this);
            StrategyCenter.getInstance().registerListener(this);
        }

        void unRegisterAll() {
            StrategyCenter.getInstance().unregisterListener(this);
            AppLifecycle.b(this);
            NetworkStatusHelper.b(this);
        }

        public void onNetworkStatusChanged(NetworkStatus networkStatus) {
            ALog.d(SessionCenter.TAG, "onNetworkStatusChanged. reCreateSession", SessionCenter.this.seqNum, "networkStatus", networkStatus);
            List<SessionRequest> a = SessionCenter.this.sessionPool.a();
            if (a.isEmpty()) {
                ALog.i(SessionCenter.TAG, "recreate session failed: infos is empty", SessionCenter.this.seqNum, new Object[0]);
            } else {
                for (SessionRequest sessionRequest : a) {
                    ALog.d(SessionCenter.TAG, "network change, try recreate session", SessionCenter.this.seqNum, new Object[0]);
                    sessionRequest.a(null);
                }
            }
            SessionCenter.this.accsSessionManager.checkAndStartAccsSession();
        }

        public void onStrategyUpdated(c cVar) {
            SessionCenter.this.checkEffectNow(cVar);
            SessionCenter.this.accsSessionManager.checkAndStartAccsSession();
        }

        public void forground() {
            ALog.i(SessionCenter.TAG, "[forground]", SessionCenter.this.seqNum, new Object[0]);
            if (SessionCenter.this.context != null && !this.foreGroundCheckRunning) {
                this.foreGroundCheckRunning = true;
                if (SessionCenter.mInit) {
                    try {
                        anet.channel.c.c.a(new SessionCenter_InnerListener_1());
                        return;
                    } catch (Exception e) {
                        return;
                    }
                }
                ALog.e(SessionCenter.TAG, "forground not inited!", SessionCenter.this.seqNum, new Object[0]);
            }
        }

        public void background() {
            ALog.i(SessionCenter.TAG, "[background]", SessionCenter.this.seqNum, new Object[0]);
            if (SessionCenter.mInit) {
                try {
                    anet.channel.c.c.a(new SessionCenter_InnerListener_2());
                    StrategyCenter.getInstance().saveData();
                    if ("OPPO".equalsIgnoreCase(Build.BRAND)) {
                        SessionCenter.this.accsSessionManager.forceCloseSession(false);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            }
            ALog.e(SessionCenter.TAG, "background not inited!", SessionCenter.this.seqNum, new Object[0]);
        }
    }

    public static synchronized void init(Context context) {
        synchronized (SessionCenter.class) {
            if (context == null) {
                ALog.e(TAG, "paramter context is null!", null, new Object[0]);
                throw new NullPointerException("init failed. paramter context is null");
            }
            GlobalAppRuntimeInfo.setContext(context.getApplicationContext());
            if (!mInit) {
                instancesMap.put(Config.DEFAULT_CONFIG, new SessionCenter(Config.DEFAULT_CONFIG));
                AppLifecycle.a();
                StrategyCenter.getInstance().initialize();
                AppMonitor.getInstance().register();
                mInit = true;
            }
        }
    }

    @Deprecated
    public static synchronized void init(Context context, String str) {
        synchronized (SessionCenter.class) {
            init(context, str, GlobalAppRuntimeInfo.getEnv());
        }
    }

    public static synchronized void init(Context context, String str, ENV env) {
        synchronized (SessionCenter.class) {
            if (context == null) {
                ALog.e(TAG, "paramter context is null!", null, new Object[0]);
                throw new NullPointerException("init failed. paramter context is null");
            }
            Config config = Config.getConfig(str, env);
            if (config == null) {
                config = new Builder().setAppkey(str).setEnv(env).build();
            }
            init(context, config);
        }
    }

    public static synchronized void init(Context context, Config config) {
        synchronized (SessionCenter.class) {
            if (context == null) {
                ALog.e(TAG, "paramter context is null!", null, new Object[0]);
                throw new NullPointerException("init failed. paramter context is null");
            }
            init(context);
            if (!instancesMap.containsKey(config)) {
                instancesMap.put(config, new SessionCenter(config));
            }
        }
    }

    private SessionCenter(Config config) {
        this.config = config;
        this.seqNum = config.getAppkey();
        this.innerListener.registerAll();
        this.accsSessionManager = new AccsSessionManager(this);
        a.a(config.getAccsHost());
        if (TextUtils.isEmpty(GlobalAppRuntimeInfo.getAppKey()) && !config.getAppkey().equals("[default]")) {
            GlobalAppRuntimeInfo.setAppKeyAndSecurity(config.getAppkey(), config.getSecurity());
        }
    }

    private void dispose() {
        ALog.i(TAG, "instance dispose", this.seqNum, new Object[0]);
        this.accsSessionManager.forceCloseSession(false);
        a.b(this.config.getAccsHost());
        this.innerListener.unRegisterAll();
    }

    @Deprecated
    public synchronized void switchEnv(ENV env) {
        switchEnvironment(env);
    }

    public static synchronized void switchEnvironment(ENV env) {
        int i = 0;
        synchronized (SessionCenter.class) {
            try {
                if (GlobalAppRuntimeInfo.getEnv() != env) {
                    ALog.i(TAG, "switch env", null, "old", GlobalAppRuntimeInfo.getEnv(), "new", env);
                    GlobalAppRuntimeInfo.setEnv(env);
                    StrategyCenter.getInstance().switchEnv();
                    SpdyAgent instance = SpdyAgent.getInstance(GlobalAppRuntimeInfo.getContext(), SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
                    if (env != ENV.TEST) {
                        i = 1;
                    }
                    instance.switchAccsServer(i);
                }
                Iterator it = instancesMap.entrySet().iterator();
                while (it.hasNext()) {
                    SessionCenter sessionCenter = (SessionCenter) ((Entry) it.next()).getValue();
                    if (sessionCenter.config.getEnv() != env) {
                        ALog.i(TAG, "remove instance", sessionCenter.seqNum, "ENVIRONMENT", sessionCenter.config.getEnv());
                        sessionCenter.dispose();
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                ALog.e(TAG, "switch env error.", null, th, new Object[0]);
            }
        }
        return;
    }

    public static synchronized SessionCenter getInstance(String str) {
        SessionCenter instance;
        synchronized (SessionCenter.class) {
            Config configByTag = Config.getConfigByTag(str);
            if (configByTag == null) {
                throw new RuntimeException("tag not exist!");
            }
            instance = getInstance(configByTag);
        }
        return instance;
    }

    public static synchronized SessionCenter getInstance(Config config) {
        SessionCenter sessionCenter;
        synchronized (SessionCenter.class) {
            if (config == null) {
                throw new NullPointerException("config is null!");
            }
            if (!mInit) {
                Context appContext = Utils.getAppContext();
                if (appContext != null) {
                    init(appContext);
                }
            }
            sessionCenter = (SessionCenter) instancesMap.get(config);
            if (sessionCenter == null) {
                sessionCenter = new SessionCenter(config);
                instancesMap.put(config, sessionCenter);
            }
        }
        return sessionCenter;
    }

    @Deprecated
    public static synchronized SessionCenter getInstance() {
        SessionCenter sessionCenter;
        synchronized (SessionCenter.class) {
            if (!mInit) {
                Context appContext = Utils.getAppContext();
                if (appContext != null) {
                    init(appContext);
                }
            }
            sessionCenter = null;
            for (Entry entry : instancesMap.entrySet()) {
                sessionCenter = (SessionCenter) entry.getValue();
                if (entry.getKey() != Config.DEFAULT_CONFIG) {
                    break;
                }
            }
        }
        return sessionCenter;
    }

    public Session getThrowsException(String str, long j) throws Exception {
        return getThrowsException(str, null, j);
    }

    public Session getThrowsException(String str, TypeLevel typeLevel, long j) throws Exception {
        return getInternal(e.a(str), typeLevel, j);
    }

    public Session getThrowsException(e eVar, TypeLevel typeLevel, long j) throws Exception {
        return getInternal(eVar, typeLevel, j);
    }

    public Session get(String str, long j) {
        return get(str, null, j);
    }

    public Session get(String str, TypeLevel typeLevel, long j) {
        return get(e.a(str), typeLevel, j);
    }

    public Session get(e eVar, TypeLevel typeLevel, long j) {
        Session session = null;
        try {
            session = getInternal(eVar, typeLevel, j);
        } catch (Throwable e) {
            ALog.e(TAG, "[Get]param url is invaild, return null", session, e, "url", eVar.d());
        } catch (Throwable e2) {
            ALog.e(TAG, "[Get]get session timeout exception return null", session, e2, "url", eVar.d());
        } catch (NoNetworkException e3) {
            ALog.e(TAG, "[Get]get session no network return null", session, "url", eVar.d());
        } catch (NoAvailStrategyException e4) {
            ALog.w(TAG, "[Get]get session no strategy", session, "url", eVar.d());
        } catch (Throwable e22) {
            ALog.e(TAG, "[Get]get session exception return null", session, e22, "url", eVar.d());
        }
        return session;
    }

    public boolean setDataReceiveCb(AccsFrameCb accsFrameCb) {
        ALog.e(TAG, "setDataReceiveCb", this.seqNum, "AccsFrameCb", accsFrameCb);
        this.accsFrameCb = accsFrameCb;
        for (SessionRequest sessionRequest : this.sessionPool.a()) {
            if (a.d(sessionRequest.b())) {
                List<Session> a = this.sessionPool.a(sessionRequest);
                if (!(a == null || a.isEmpty())) {
                    for (Session session : a) {
                        if (session instanceof AccsSession) {
                            ((AccsSession) session).setFrameCb(this.accsFrameCb);
                        }
                    }
                }
            }
        }
        return true;
    }

    public void setAccsSessionCb(Callback callback) {
        this.accsSessionManager.setAccsSessionCb(callback);
    }

    public static void checkAndStartAccsSession() {
        for (SessionCenter sessionCenter : instancesMap.values()) {
            sessionCenter.accsSessionManager.checkAndStartAccsSession();
        }
    }

    public void forceRecreateAccsSession() {
        this.accsSessionManager.forceReCreateSession();
    }

    protected Session getInternal(e eVar, TypeLevel typeLevel, long j) throws Exception {
        Session session = null;
        if (mInit) {
            ALog.d(TAG, "getInternal", this.seqNum, "u", eVar.d(), "TypeClass", typeLevel, "timeout", Long.valueOf(j));
            if (eVar != null) {
                String c;
                ALog.d(TAG, "getInternal", null, "httpUrl", eVar.d(), "TypeClass", typeLevel, "timeout", Long.valueOf(j));
                String cNameByHost = StrategyCenter.getInstance().getCNameByHost(eVar.b());
                if (cNameByHost == null) {
                    eVar.g();
                    c = eVar.c();
                } else {
                    c = StrategyCenter.getInstance().getSchemeByHost(cNameByHost, null);
                    if (c == null) {
                        c = eVar.a();
                    }
                    c = StringUtils.concatString(c, HttpConstant.SCHEME_SPLIT, cNameByHost);
                }
                SessionRequest sessionRequest = getSessionRequest(c);
                session = this.sessionPool.a(sessionRequest, typeLevel);
                if (session != null) {
                    ALog.d(TAG, "get internal hit cache session", this.seqNum, "session", session);
                } else {
                    sessionRequest.a(this.context, typeLevel, i.a(this.seqNum));
                    if (j > 0) {
                        sessionRequest.a(j);
                        session = this.sessionPool.a(sessionRequest, typeLevel);
                        if (session == null) {
                            throw new ConnectException();
                        }
                    }
                }
            }
        }
        ALog.e(TAG, "getInternal not inited!", this.seqNum, new Object[0]);
        return session;
    }

    @Deprecated
    public void enterBackground() {
        AppLifecycle.c();
    }

    @Deprecated
    public void enterForeground() {
        AppLifecycle.b();
    }

    private void checkEffectNow(c cVar) {
        b[] bVarArr = cVar.c;
        for (b bVar : bVarArr) {
            if (bVar.q) {
                ALog.i(TAG, "find effectNow", this.seqNum, "host", bVar.a);
                l.a[] aVarArr = bVar.f;
                String[] strArr = bVar.e;
                for (Session session : this.sessionPool.a(getSessionRequest(StringUtils.buildKey(bVar.c, bVar.a)))) {
                    if (!session.getConnType().isHttpType()) {
                        int i;
                        Object obj;
                        for (Object equals : strArr) {
                            if (session.getIp().equals(equals)) {
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            if (ALog.isPrintLog(2)) {
                                ALog.i(TAG, "ip not match", this.seqNum, "session ip", session.getIp(), "ips", Arrays.toString(strArr));
                            }
                            session.close(true);
                        } else {
                            i = 0;
                            while (i < aVarArr.length) {
                                if (session.getPort() == aVarArr[i].a && session.getConnType().equals(ConnType.valueOf(aVarArr[i]))) {
                                    obj = 1;
                                    break;
                                }
                                i++;
                            }
                            obj = null;
                            if (obj == null) {
                                if (ALog.isPrintLog(2)) {
                                    ALog.i(TAG, "aisle not match", this.seqNum, "port", Integer.valueOf(session.getPort()), "connType", session.getConnType(), "aisle", Arrays.toString(aVarArr));
                                }
                                session.close(true);
                            } else {
                                ALog.i(TAG, "session matches, do nothing", null, new Object[0]);
                            }
                        }
                    }
                }
            }
        }
    }

    protected SessionRequest getSessionRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SessionRequest sessionRequest;
        synchronized (this.srCache) {
            sessionRequest = (SessionRequest) this.srCache.get(str);
            if (sessionRequest == null) {
                sessionRequest = new SessionRequest(str, this);
                this.srCache.put(str, sessionRequest);
            }
        }
        return sessionRequest;
    }
}
