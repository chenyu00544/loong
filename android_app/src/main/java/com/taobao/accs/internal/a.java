package com.taobao.accs.internal;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.IBaseService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.Constants.Operate;
import com.taobao.accs.net.o;
import com.taobao.accs.ut.monitor.ElectionRateMonitor;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.j;
import com.tencent.tauth.AuthActivity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.android.agoo.common.AgooConstants;

/* compiled from: Taobao */
public abstract class a implements IBaseService {
    public static final String ELECTION_KEY_BLACKLIST = "blacklist";
    public static final String ELECTION_KEY_HOST = "host";
    public static final String ELECTION_KEY_SDKVS = "sdkvs";
    public static final String ELECTION_KEY_VERSION = "elversion";
    public static final String ELECTION_SERVICE_ID = "accs_election";
    public static final int ELE_ERROR_EXCEPTION = -901;
    public static final int ELE_ERROR_SERVER = -900;
    public static final int ELE_LIST_NULL = -902;
    protected static ConcurrentHashMap<String, com.taobao.accs.net.a> a = new ConcurrentHashMap(2);
    private static int g = 0;
    private static boolean h = false;
    private Context b;
    private boolean c = false;
    private boolean d = true;
    private Map<String, Integer> e = null;
    private ScheduledThreadPoolExecutor f;
    private Service i = null;
    private ElectionRateMonitor j;
    private ElectionRateMonitor k;
    private ScheduledFuture<?> l;
    private ScheduledFuture<?> m;
    private AccsAbstractDataListener n = new b(this);
    private com.taobao.accs.a.a.a o;
    private boolean p = false;
    private boolean q = false;
    private ScheduledFuture<?> r;

    public abstract int a(Intent intent);

    public abstract void a();

    public a(Service service) {
        this.i = service;
        this.b = service.getApplicationContext();
        this.e = new HashMap();
        this.f = com.taobao.accs.common.a.a();
        AppMonitor.getInstance().register(ElectionRateMonitor.class);
    }

    public void onCreate() {
        ALog.i("ElectionServiceImpl", "onCreate,", "sdkv", Integer.valueOf(Constants.SDK_VERSION_CODE));
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 2;
        }
        CharSequence schemeSpecificPart;
        String stringExtra;
        CharSequence action = intent.getAction();
        CharSequence packageName = this.b.getPackageName();
        ALog.i("ElectionServiceImpl", "onStartCommand begin", AuthActivity.ACTION_KEY, action);
        if (com.taobao.accs.a.a.b()) {
            try {
                if (TextUtils.equals(action, "android.intent.action.PACKAGE_REMOVED")) {
                    schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                    boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
                    com.taobao.accs.a.a.a a = com.taobao.accs.a.a.a(this.b);
                    CharSequence charSequence = a.a;
                    ALog.w("ElectionServiceImpl", "onstartcommand PACKAGE_REMOVED", Constants.KEY_ELECTION_PKG, schemeSpecificPart, "host", charSequence, "replaced", Boolean.valueOf(booleanExtra));
                    if (TextUtils.isEmpty(charSequence) || !TextUtils.equals(charSequence, schemeSpecificPart)) {
                        ALog.i("ElectionServiceImpl", "onstartcommand PACKAGE_REMOVED no need election", new Object[0]);
                    } else if (this.b.getPackageName().equals(com.taobao.accs.a.a.e(this.b))) {
                        a.b = 0;
                        com.taobao.accs.a.a.a(this.b, a);
                        a(this.b, "host removed");
                    } else {
                        ALog.i("ElectionServiceImpl", "onstartcommand PACKAGE_REMOVED no need election", new Object[0]);
                    }
                } else if (TextUtils.equals(action, com.taobao.accs.a.a.c())) {
                    ALog.i("ElectionServiceImpl", "operate is receive", "operate", (Operate) intent.getSerializableExtra("operate"));
                    Intent intent2;
                    switch ((Operate) intent.getSerializableExtra("operate")) {
                        case TRY_ELECTION:
                            d();
                            break;
                        case START_ELECTION:
                            a(this.b, intent.getStringExtra("reason"));
                            break;
                        case ASK_VERSION:
                            String stringExtra2 = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
                            int intExtra = intent.getIntExtra(ELECTION_KEY_VERSION, 0);
                            intent2 = new Intent(com.taobao.accs.a.a.c());
                            intent2.putExtra("operate", Operate.REPORT_VERSION);
                            intent2.putExtra(Constants.KEY_PACKAGE_NAME, this.b.getPackageName());
                            intent2.setPackage(stringExtra2);
                            intent2.setClassName(stringExtra2, com.taobao.accs.utl.a.channelService);
                            int i3 = Constants.SDK_VERSION_CODE;
                            if (com.taobao.accs.a.a.a(this.b, this.b.getPackageName(), intExtra)) {
                                intent2.putExtra(Constants.KEY_SDK_VERSION, Constants.SDK_VERSION_CODE);
                            } else {
                                i3 = 0;
                            }
                            this.b.startService(intent2);
                            ALog.i("ElectionServiceImpl", AgooConstants.MESSAGE_REPORT, "sdkv", Integer.valueOf(i3), "from pkg", this.b.getPackageName(), "to pkg", stringExtra2);
                            break;
                        case REPORT_VERSION:
                            if (!this.c) {
                                ALog.e("ElectionServiceImpl", "not electioning, but receive report", new Object[0]);
                                break;
                            }
                            stringExtra = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
                            int intExtra2 = intent.getIntExtra(Constants.KEY_SDK_VERSION, 0);
                            g--;
                            if (intExtra2 != 0) {
                                this.e.put(stringExtra, Integer.valueOf(intExtra2));
                            }
                            ALog.i("ElectionServiceImpl", "collect info", "sdkv", Integer.valueOf(intExtra2), "election pkg", stringExtra, "electionPackCount", Integer.valueOf(g));
                            if (g == 0) {
                                e();
                                break;
                            }
                            break;
                        case RESULT_ELECTION:
                            this.d = true;
                            if (this.m != null) {
                                this.m.cancel(true);
                                this.m = null;
                            }
                            schemeSpecificPart = intent.getStringExtra("sudoPack");
                            ALog.i("ElectionServiceImpl", "election result", "host", schemeSpecificPart, "curr pkg", packageName);
                            b.a("accs", BaseMonitor.COUNT_ELECTION_PKG_TIMES, schemeSpecificPart, 0.0d);
                            com.taobao.accs.a.a.b(this.b);
                            if (!TextUtils.isEmpty(schemeSpecificPart)) {
                                if (!TextUtils.equals(schemeSpecificPart, packageName)) {
                                    a(true);
                                    break;
                                }
                                a();
                                break;
                            }
                            break;
                        case PING_ELECTION:
                            schemeSpecificPart = com.taobao.accs.a.a.a(this.b).a;
                            CharSequence stringExtra3 = intent.getStringExtra("pingPack");
                            if (!(TextUtils.isEmpty(schemeSpecificPart) || TextUtils.isEmpty(stringExtra3) || !TextUtils.equals(schemeSpecificPart, packageName))) {
                                ALog.i("ElectionServiceImpl", "host receive ping, and report ping", "to pkg", stringExtra3, "host", schemeSpecificPart);
                                intent2 = new Intent(com.taobao.accs.a.a.c());
                                intent2.setPackage(stringExtra3);
                                intent2.setClassName(stringExtra3, com.taobao.accs.utl.a.channelService);
                                intent2.putExtra("operate", Operate.PING_ELECTION);
                                intent2.putExtra("isPing", true);
                                intent2.putExtra("pingPack", stringExtra3);
                                intent2.putExtra(Constants.KEY_SDK_VERSION, Constants.SDK_VERSION_CODE);
                                this.b.startService(intent2);
                                a();
                            }
                            if (TextUtils.equals(stringExtra3, packageName)) {
                                ALog.i("ElectionServiceImpl", "receive host's ping back", "host", schemeSpecificPart);
                                h = intent.getBooleanExtra("isPing", false);
                                break;
                            }
                            break;
                    }
                    return 2;
                }
            } catch (Throwable th) {
                ALog.e("ElectionServiceImpl", "onStartCommand", th, new Object[0]);
            }
        } else if (TextUtils.equals(action, com.taobao.accs.a.a.c())) {
            ALog.e("ElectionServiceImpl", "election disabled", new Object[0]);
            return 2;
        }
        if (TextUtils.equals(action, Constants.ACTION_START_SERVICE)) {
            Object stringExtra4 = intent.getStringExtra(Constants.KEY_PACKAGE_NAME);
            stringExtra2 = intent.getStringExtra("appKey");
            String stringExtra5 = intent.getStringExtra(Constants.KEY_TTID);
            String stringExtra6 = intent.getStringExtra("app_sercet");
            int intExtra3 = intent.getIntExtra(Constants.KEY_MODE, 0);
            ALog.i("ElectionServiceImpl", "try to saveAppKey", "appkey", stringExtra2, "appSecret", stringExtra6, Constants.KEY_TTID, stringExtra5, Constants.KEY_ELECTION_PKG, stringExtra4);
            if (!(TextUtils.isEmpty(stringExtra4) || TextUtils.isEmpty(stringExtra2) || !stringExtra4.equals(this.b.getPackageName()))) {
                j.a(this.b, intExtra3);
                com.taobao.accs.net.a a2 = a(this.b, stringExtra2, false, -1);
                if (a2 != null) {
                    a2.a = stringExtra5;
                }
                UtilityImpl.saveAppKey(this.b, stringExtra2, stringExtra6);
            }
            stringExtra = intent.getStringExtra(org.android.agoo.common.b.PROPERTY_APP_KEY);
            ALog.i("ElectionServiceImpl", "save agoo appKey", "appkey", stringExtra);
            if (!TextUtils.isEmpty(stringExtra)) {
                org.android.agoo.common.b.a(this.b, stringExtra);
            }
            if (com.taobao.accs.a.a.b()) {
                return 2;
            }
        }
        schemeSpecificPart = com.taobao.accs.a.a.a(this.b).a;
        if (TextUtils.isEmpty(schemeSpecificPart) || TextUtils.equals(schemeSpecificPart, this.b.getPackageName()) || !com.taobao.accs.a.a.b()) {
            ALog.i("ElectionServiceImpl", "deliver to channelservice", "host pkg", schemeSpecificPart);
            return a(intent);
        }
        if (!(this.c || TextUtils.equals(action, "android.intent.action.PACKAGE_REMOVED"))) {
            ALog.i("ElectionServiceImpl", "not electioning and not host, stop", new Object[0]);
            a(true);
        }
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean onUnbind(Intent intent) {
        return false;
    }

    public void onDestroy() {
        ALog.e("ElectionServiceImpl", "Service onDestroy", new Object[0]);
        this.b = null;
        this.i = null;
    }

    private String c() {
        CharSequence charSequence;
        Object obj;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Object th4;
        ElectionRateMonitor electionRateMonitor;
        ElectionRateMonitor electionRateMonitor2;
        CharSequence charSequence2 = null;
        int i = 0;
        int i2;
        try {
            if (this.e == null || this.e.size() <= 0) {
                try {
                    i2 = ELE_LIST_NULL;
                    String str = "apps is null";
                    charSequence = null;
                } catch (Throwable th5) {
                    th2 = th5;
                    i2 = ELE_LIST_NULL;
                    obj = null;
                    if (this.k != null) {
                        if (TextUtils.isEmpty(obj)) {
                            this.k.errorCode = i2;
                            this.k.errorMsg = obj;
                        }
                        electionRateMonitor2 = this.k;
                        if (TextUtils.isEmpty(charSequence2)) {
                            i = 1;
                        }
                        electionRateMonitor2.ret = i;
                        AppMonitor.getInstance().commitStat(this.k);
                    }
                    throw th2;
                }
            }
            charSequence = com.taobao.accs.a.a.a(this.b, this.e);
            obj = null;
            i2 = 0;
            try {
                ALog.i("ElectionServiceImpl", "localElection", "host", charSequence);
                if (this.k != null) {
                    if (!TextUtils.isEmpty(obj)) {
                        this.k.errorCode = i2;
                        this.k.errorMsg = obj;
                    }
                    electionRateMonitor = this.k;
                    if (!TextUtils.isEmpty(charSequence)) {
                        i = 1;
                    }
                    electionRateMonitor.ret = i;
                    AppMonitor.getInstance().commitStat(this.k);
                    return charSequence;
                }
            } catch (Throwable th6) {
                th3 = th6;
                ALog.e("ElectionServiceImpl", "localElection error", th3, new Object[0]);
                i2 = ELE_ERROR_EXCEPTION;
                th4 = th3.toString();
                if (this.k != null) {
                    if (TextUtils.isEmpty(th4)) {
                        this.k.errorCode = ELE_ERROR_EXCEPTION;
                        this.k.errorMsg = th4;
                    }
                    electionRateMonitor = this.k;
                    if (TextUtils.isEmpty(charSequence)) {
                        i = 1;
                    }
                    electionRateMonitor.ret = i;
                    AppMonitor.getInstance().commitStat(this.k);
                    return charSequence;
                }
                return charSequence;
            }
        } catch (Throwable th7) {
            th2 = th7;
            obj = null;
            i2 = 0;
            if (this.k != null) {
                if (TextUtils.isEmpty(obj)) {
                    this.k.errorCode = i2;
                    this.k.errorMsg = obj;
                }
                electionRateMonitor2 = this.k;
                if (TextUtils.isEmpty(charSequence2)) {
                    i = 1;
                }
                electionRateMonitor2.ret = i;
                AppMonitor.getInstance().commitStat(this.k);
            }
            throw th2;
        }
        return charSequence;
    }

    private void a(Map<String, Integer> map) {
        ALog.i("ElectionServiceImpl", "serverElection start", Constants.KEY_ELECTION_PACKS, map.toString());
        this.j = new ElectionRateMonitor();
        try {
            this.j.type = "server";
            if (this.k != null) {
                this.j.reason = this.k.reason;
            }
            GlobalClientInfo.getInstance(this.b).registerListener(ELECTION_SERVICE_ID, this.n);
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_COMMAND);
            intent.putExtra(Constants.KEY_PACKAGE_NAME, this.b.getPackageName());
            intent.putExtra("command", 105);
            intent.putExtra(Constants.KEY_ELECTION_PACKS, (HashMap) map);
            a(intent);
        } catch (Throwable th) {
            this.j.errorCode = ELE_ERROR_EXCEPTION;
            this.j.errorMsg = th.toString();
        }
    }

    protected static com.taobao.accs.net.a a(Context context, String str, boolean z, int i) {
        com.taobao.accs.net.a aVar;
        Throwable th;
        Throwable th2;
        try {
            if (TextUtils.isEmpty(str)) {
                ALog.i("ElectionServiceImpl", "getConnection appkey null or env invalid", "command", Integer.valueOf(i), "conns:", Integer.valueOf(a.size()));
                if (a.size() > 0) {
                    return (com.taobao.accs.net.a) a.elements().nextElement();
                }
                return null;
            }
            ALog.i("ElectionServiceImpl", "getConnection", "appkey", str);
            int a = j.a(context);
            String str2 = "|" + a;
            aVar = (com.taobao.accs.net.a) a.get(str2);
            if (aVar != null) {
                return aVar;
            }
            try {
                AccsClientConfig.mEnv = a;
                com.taobao.accs.net.a oVar = new o(context, 0, str);
                if (z) {
                    try {
                        oVar.a();
                    } catch (Throwable th3) {
                        th = th3;
                        aVar = oVar;
                        th2 = th;
                        ALog.e("ElectionServiceImpl", "getConnection", th2, new Object[0]);
                        return aVar;
                    }
                }
                if (a.size() < 10) {
                    a.put(str2, oVar);
                    return oVar;
                }
                ALog.e("ElectionServiceImpl", "to many conns!!!", new Object[0]);
                return oVar;
            } catch (Throwable th4) {
                th2 = th4;
                ALog.e("ElectionServiceImpl", "getConnection", th2, new Object[0]);
                return aVar;
            }
        } catch (Throwable th32) {
            th = th32;
            aVar = null;
            th2 = th;
            ALog.e("ElectionServiceImpl", "getConnection", th2, new Object[0]);
            return aVar;
        }
    }

    private void d() {
        String str;
        try {
            if (UtilityImpl.isFirstStart(this.b)) {
                b(this.b, "first start");
                UtilityImpl.setSdkStart(this.b);
                return;
            }
            str = com.taobao.accs.a.a.a(this.b).a;
            Object packageName = this.b.getPackageName();
            ALog.i("ElectionServiceImpl", "tryElection begin", "isFirstStart", Boolean.valueOf(UtilityImpl.isFirstStart(this.b)), "currentPack", packageName, "currentElectionPack", str);
            if (TextUtils.isEmpty(str)) {
                ALog.i("ElectionServiceImpl", "host is empty, try selectAppToElection", new Object[0]);
                b(this.b, "host null");
            } else if (TextUtils.equals(str, packageName)) {
                ALog.i("ElectionServiceImpl", "curr is host, no need election", new Object[0]);
                a();
            } else {
                Intent intent = new Intent(com.taobao.accs.a.a.c());
                intent.setPackage(str);
                intent.putExtra("operate", Operate.PING_ELECTION);
                intent.setClassName(str, com.taobao.accs.utl.a.channelService);
                intent.putExtra("pingPack", packageName);
                this.b.startService(intent);
                ALog.i("ElectionServiceImpl", "tryElection send PING_ELECTION", "to pkg", str);
                this.f.schedule(new c(this, str), 5, TimeUnit.SECONDS);
            }
        } catch (Throwable th) {
            ALog.e("ElectionServiceImpl", "tryElection error", th, new Object[0]);
        }
    }

    private void b(Context context, String str) {
        String e = com.taobao.accs.a.a.e(context);
        ALog.i("ElectionServiceImpl", "selectAppToElection", Constants.KEY_ELECTION_PKG, e);
        com.taobao.accs.a.a.b = false;
        this.d = false;
        Intent intent = new Intent(com.taobao.accs.a.a.c());
        if (TextUtils.isEmpty(e)) {
            intent.putExtra("operate", Operate.START_ELECTION);
            intent.putExtra("reason", str);
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), com.taobao.accs.utl.a.channelService);
        } else {
            intent.putExtra("operate", Operate.START_ELECTION);
            intent.putExtra("reason", str);
            intent.setPackage(e);
            intent.setClassName(e, com.taobao.accs.utl.a.channelService);
        }
        if (this.m != null) {
            this.m.cancel(true);
            this.m = null;
        }
        this.m = this.f.schedule(new d(this, context), 30, TimeUnit.SECONDS);
        context.startService(intent);
    }

    public void a(Context context, String str) {
        try {
            if (this.c) {
                ALog.w("ElectionServiceImpl", "isElectioning return", new Object[0]);
                return;
            }
            this.o = com.taobao.accs.a.a.a(context);
            if (this.o.b > 20) {
                ALog.w("ElectionServiceImpl", "startElection too many times, return", "times", Integer.valueOf(this.o.b));
                b.a("accs", BaseMonitor.COUNT_ELECTION_OVER_MAX, str + UtilityImpl.getDeviceId(context), 0.0d);
                return;
            }
            this.k = new ElectionRateMonitor();
            this.k.type = AgooConstants.MESSAGE_LOCAL;
            this.k.reason = str;
            b.a("accs", BaseMonitor.COUNT_ELECTION_START_TIMES, str, 0.0d);
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(com.taobao.accs.a.a.c()), 32);
            this.c = true;
            if (queryIntentServices == null || queryIntentServices.size() < 2) {
                String str2 = "ElectionServiceImpl";
                String str3 = "startElection apps < 2";
                Object[] objArr = new Object[2];
                objArr[0] = "services";
                objArr[1] = queryIntentServices == null ? "null" : queryIntentServices.toString();
                ALog.i(str2, str3, objArr);
                e();
                return;
            }
            g = 0;
            ALog.i("ElectionServiceImpl", "startElection begin", "locallist", queryIntentServices.toString(), "size", Integer.valueOf(queryIntentServices.size()));
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (!(resolveInfo == null || resolveInfo.serviceInfo == null)) {
                    String str4 = resolveInfo.serviceInfo.packageName;
                    if (TextUtils.isEmpty(str4)) {
                        ALog.i("ElectionServiceImpl", "startElection unvailable app", Constants.KEY_ELECTION_PKG, str4);
                    } else {
                        Intent intent = new Intent(com.taobao.accs.a.a.c());
                        intent.putExtra("operate", Operate.ASK_VERSION);
                        intent.setPackage(str4);
                        intent.putExtra(Constants.KEY_PACKAGE_NAME, context.getPackageName());
                        intent.putExtra(ELECTION_KEY_VERSION, 1);
                        intent.setClassName(str4, com.taobao.accs.utl.a.channelService);
                        ALog.i("ElectionServiceImpl", "startElection askversion", "receive pkg", str4);
                        context.startService(intent);
                        g++;
                    }
                }
            }
            this.p = false;
            this.l = this.f.schedule(new e(this), 3, TimeUnit.SECONDS);
        } catch (Throwable th) {
            ALog.e("ElectionServiceImpl", "startElection error", th, new Object[0]);
            this.c = false;
            if (this.k != null) {
                this.k.errorCode = ELE_ERROR_EXCEPTION;
                this.k.errorMsg = th.toString();
            }
        }
    }

    private void e() {
        try {
            if (this.l != null) {
                this.l.cancel(true);
                this.l = null;
            }
            if (this.p) {
                ALog.i("ElectionServiceImpl", "reportcompleted, return", new Object[0]);
                return;
            }
            this.p = true;
            ALog.i("ElectionServiceImpl", "onReportComplete", new Object[0]);
            if (this.e == null) {
                this.e = new HashMap();
            }
            this.e.put(this.b.getPackageName(), Integer.valueOf(Constants.SDK_VERSION_CODE));
            if (this.e.size() == 1) {
                String str = ((String[]) this.e.keySet().toArray(new String[0]))[0];
                if (this.k != null) {
                    this.k.ret = TextUtils.isEmpty(str) ? 0 : 1;
                    AppMonitor.getInstance().commitStat(this.k);
                }
                a(str);
            } else {
                a(this.e);
                this.q = false;
                this.r = this.f.schedule(new f(this), 20, TimeUnit.SECONDS);
            }
            this.c = false;
        } catch (Throwable th) {
            if (this.k != null) {
                this.k.errorCode = ELE_ERROR_EXCEPTION;
                this.k.errorMsg = th.toString();
            }
            ALog.e("ElectionServiceImpl", "onReportComplete", th, new Object[0]);
        } finally {
            this.c = false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte[] r11, int r12) {
        /*
        r10 = this;
        r1 = 0;
        r2 = 1;
        r0 = r10.q;
        if (r0 == 0) goto L_0x0010;
    L_0x0006:
        r0 = "ElectionServiceImpl";
        r2 = "server election handled, return";
        r1 = new java.lang.Object[r1];
        com.taobao.accs.utl.ALog.i(r0, r2, r1);
    L_0x000f:
        return;
    L_0x0010:
        r10.q = r2;
        r3 = 0;
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r12 != r0) goto L_0x0019;
    L_0x0017:
        if (r11 != 0) goto L_0x0061;
    L_0x0019:
        r0 = "ElectionServiceImpl";
        r4 = "handleServerElectionResult fail, start local election";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r6 = 0;
        r7 = "error";
        r5[r6] = r7;	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r6 = 1;
        r7 = java.lang.Integer.valueOf(r12);	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        com.taobao.accs.utl.ALog.e(r0, r4, r5);	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r0 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
        if (r12 == r0) goto L_0x0124;
    L_0x0033:
        r4 = r2;
    L_0x0034:
        r0 = r10.j;	 Catch:{ Throwable -> 0x00a8, all -> 0x0106 }
        if (r0 == 0) goto L_0x0121;
    L_0x0038:
        r0 = r10.j;	 Catch:{ Throwable -> 0x00a8, all -> 0x0106 }
        r0.errorCode = r12;	 Catch:{ Throwable -> 0x00a8, all -> 0x0106 }
        r0 = r10.j;	 Catch:{ Throwable -> 0x00a8, all -> 0x0106 }
        r5 = "server status error";
        r0.errorMsg = r5;	 Catch:{ Throwable -> 0x00a8, all -> 0x0106 }
        r0 = r3;
    L_0x0043:
        if (r4 == 0) goto L_0x0049;
    L_0x0045:
        r0 = r10.c();
    L_0x0049:
        r10.a(r0);
        r0 = r10.j;
        if (r0 == 0) goto L_0x000f;
    L_0x0050:
        r3 = r10.j;
        if (r4 == 0) goto L_0x00a6;
    L_0x0054:
        r0 = r1;
    L_0x0055:
        r3.ret = r0;
        r0 = anet.channel.appmonitor.AppMonitor.getInstance();
        r1 = r10.j;
        r0.commitStat(r1);
        goto L_0x000f;
    L_0x0061:
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r0.<init>(r11);	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r4 = "ElectionServiceImpl";
        r5 = "handleServerElectionResult";
        r6 = 2;
        r6 = new java.lang.Object[r6];	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r7 = 0;
        r8 = "json";
        r6[r7] = r8;	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r7 = 1;
        r6[r7] = r0;	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        com.taobao.accs.utl.ALog.i(r4, r5, r6);	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r5 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r5.<init>(r0);	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r0 = "host";
        r3 = r5.getString(r0);	 Catch:{ Throwable -> 0x00a8, all -> 0x00e2 }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x0119, all -> 0x010b }
        if (r0 == 0) goto L_0x011e;
    L_0x0089:
        r4 = r2;
    L_0x008a:
        r0 = "blacklist";
        r0 = r5.getJSONArray(r0);	 Catch:{ Throwable -> 0x0119, all -> 0x0111 }
        if (r0 == 0) goto L_0x009f;
    L_0x0092:
        r0 = r0.length();	 Catch:{ Throwable -> 0x0119, all -> 0x0111 }
        if (r0 <= 0) goto L_0x009f;
    L_0x0098:
        r0 = r10.b;	 Catch:{ Throwable -> 0x0119, all -> 0x0111 }
        com.taobao.accs.a.a.a(r0, r11);	 Catch:{ Throwable -> 0x0119, all -> 0x0111 }
        r0 = r3;
        goto L_0x0043;
    L_0x009f:
        r0 = r10.b;	 Catch:{ Throwable -> 0x0119, all -> 0x0111 }
        com.taobao.accs.a.a.c(r0);	 Catch:{ Throwable -> 0x0119, all -> 0x0111 }
        r0 = r3;
        goto L_0x0043;
    L_0x00a6:
        r0 = r2;
        goto L_0x0055;
    L_0x00a8:
        r0 = move-exception;
        r9 = r0;
        r0 = r3;
        r3 = r9;
    L_0x00ac:
        r4 = "ElectionServiceImpl";
        r5 = "handleServerElectionResult";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x0116 }
        com.taobao.accs.utl.ALog.e(r4, r5, r3, r6);	 Catch:{ all -> 0x0116 }
        r4 = r10.j;	 Catch:{ all -> 0x0116 }
        if (r4 == 0) goto L_0x00c8;
    L_0x00ba:
        r4 = r10.j;	 Catch:{ all -> 0x0116 }
        r5 = -901; // 0xfffffffffffffc7b float:NaN double:NaN;
        r4.errorCode = r5;	 Catch:{ all -> 0x0116 }
        r4 = r10.j;	 Catch:{ all -> 0x0116 }
        r3 = r3.toString();	 Catch:{ all -> 0x0116 }
        r4.errorMsg = r3;	 Catch:{ all -> 0x0116 }
    L_0x00c8:
        r0 = r10.c();
        r10.a(r0);
        r0 = r10.j;
        if (r0 == 0) goto L_0x000f;
    L_0x00d3:
        r0 = r10.j;
        r0.ret = r1;
        r0 = anet.channel.appmonitor.AppMonitor.getInstance();
        r1 = r10.j;
        r0.commitStat(r1);
        goto L_0x000f;
    L_0x00e2:
        r0 = move-exception;
        r4 = r1;
        r9 = r0;
        r0 = r3;
        r3 = r9;
    L_0x00e7:
        if (r4 == 0) goto L_0x00ed;
    L_0x00e9:
        r0 = r10.c();
    L_0x00ed:
        r10.a(r0);
        r0 = r10.j;
        if (r0 == 0) goto L_0x0103;
    L_0x00f4:
        r0 = r10.j;
        if (r4 == 0) goto L_0x0104;
    L_0x00f8:
        r0.ret = r1;
        r0 = anet.channel.appmonitor.AppMonitor.getInstance();
        r1 = r10.j;
        r0.commitStat(r1);
    L_0x0103:
        throw r3;
    L_0x0104:
        r1 = r2;
        goto L_0x00f8;
    L_0x0106:
        r0 = move-exception;
        r9 = r0;
        r0 = r3;
        r3 = r9;
        goto L_0x00e7;
    L_0x010b:
        r0 = move-exception;
        r4 = r1;
        r9 = r0;
        r0 = r3;
        r3 = r9;
        goto L_0x00e7;
    L_0x0111:
        r0 = move-exception;
        r9 = r0;
        r0 = r3;
        r3 = r9;
        goto L_0x00e7;
    L_0x0116:
        r3 = move-exception;
        r4 = r2;
        goto L_0x00e7;
    L_0x0119:
        r0 = move-exception;
        r9 = r0;
        r0 = r3;
        r3 = r9;
        goto L_0x00ac;
    L_0x011e:
        r4 = r1;
        goto L_0x008a;
    L_0x0121:
        r0 = r3;
        goto L_0x0043;
    L_0x0124:
        r4 = r1;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.internal.a.a(byte[], int):void");
    }

    private void a(String str) {
        try {
            if (this.o == null) {
                this.o = com.taobao.accs.a.a.a(this.b);
            }
            this.o.a = str;
            com.taobao.accs.a.a.a aVar = this.o;
            aVar.b++;
            com.taobao.accs.a.a.a(this.b, this.o);
            ALog.i("ElectionServiceImpl", "handleResult notify result", "host", str, "packMap", this.e);
            for (String str2 : this.e.keySet()) {
                if (!TextUtils.isEmpty(str2)) {
                    Intent intent = new Intent(com.taobao.accs.a.a.c());
                    intent.setPackage(str2);
                    intent.putExtra("operate", Operate.RESULT_ELECTION);
                    intent.putExtra("sudoPack", str);
                    intent.setClassName(str2, com.taobao.accs.utl.a.channelService);
                    this.b.startService(intent);
                }
            }
        } catch (Throwable th) {
            ALog.e("ElectionServiceImpl", "handleResult", th, new Object[0]);
        }
    }

    public void a(boolean z) {
        ALog.e("ElectionServiceImpl", "shouldStopSelf, kill:" + z, new Object[0]);
        if (this.i != null) {
            this.i.stopSelf();
        }
        if (z) {
            Process.killProcess(Process.myPid());
        }
    }
}
