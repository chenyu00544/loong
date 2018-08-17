package anet.channel;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import anet.channel.entity.ENV;
import anet.channel.security.ISecurity;
import anet.channel.strategy.a;
import anet.channel.util.Utils;
import anet.channel.util.f;

/* compiled from: Taobao */
public class GlobalAppRuntimeInfo {
    private static final String TAG = "awcn.GlobalAppRuntimeInfo";
    private static Context context;
    private static String currentProcess = "";
    private static volatile String defaultAppKey;
    private static ENV env = ENV.ONLINE;
    public static volatile boolean isBackground = true;
    public static String mConnToken = null;
    private static volatile f proxySetting = null;
    private static volatile ISecurity security = null;
    private static String targetProcess = "";
    private static String ttid;
    private static String userId;
    private static String utdid;

    public static void setContext(Context context) {
        context = context;
        if (context != null) {
            if (TextUtils.isEmpty(currentProcess)) {
                currentProcess = Utils.getProcessName(context, Process.myPid());
            }
            if (TextUtils.isEmpty(targetProcess)) {
                targetProcess = Utils.getMainProcessName(context);
            }
        }
    }

    public static Context getContext() {
        return context;
    }

    public static void setTargetProcess(String str) {
        targetProcess = str;
    }

    public static boolean isTargetProcess() {
        if (TextUtils.isEmpty(targetProcess) || TextUtils.isEmpty(currentProcess)) {
            return true;
        }
        return targetProcess.equalsIgnoreCase(currentProcess);
    }

    public static String getCurrentProcess() {
        return currentProcess;
    }

    public static void setCurrentProcess(String str) {
        currentProcess = str;
    }

    public static void setEnv(ENV env) {
        env = env;
    }

    public static ENV getEnv() {
        return env;
    }

    public static synchronized void setAppKeyAndSecurity(String str, ISecurity iSecurity) {
        synchronized (GlobalAppRuntimeInfo.class) {
            defaultAppKey = str;
            security = iSecurity;
        }
    }

    public static String getAppKey() {
        return defaultAppKey;
    }

    public static ISecurity getSecurity() {
        return security;
    }

    public static void setTtid(String str) {
        ttid = str;
    }

    public static String getTtid() {
        return ttid;
    }

    public static void setUserId(String str) {
        if (userId == null || !userId.equals(str)) {
            userId = str;
            SessionCenter.checkAndStartAccsSession();
            a.b();
        }
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUtdid(String str) {
        if (utdid == null || !utdid.equals(str)) {
            utdid = str;
            SessionCenter.checkAndStartAccsSession();
            a.b();
        }
    }

    public static String getUtdid() {
        return utdid;
    }

    public static void setBackground(boolean z) {
        isBackground = z;
    }

    public static boolean isAppBackground() {
        if (context == null) {
            return true;
        }
        return isBackground;
    }

    public static void setProxySetting(f fVar) {
        proxySetting = fVar;
    }

    public static f getProxySetting() {
        return proxySetting;
    }
}
