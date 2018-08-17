package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.bw;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;
import com.umeng.analytics.social.d;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

public class MobclickAgent {
    private static final String a = "input map is null";

    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1),
        E_UM_ANALYTICS_OEM(224),
        E_UM_GAME_OEM(225);
        
        private int a;

        private EScenarioType(int i) {
            this.a = i;
        }

        public int toValue() {
            return this.a;
        }
    }

    public static class UMAnalyticsConfig {
        public String mAppkey;
        public String mChannelId;
        public Context mContext;
        public boolean mIsCrashEnable;
        public EScenarioType mType;

        private UMAnalyticsConfig() {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = EScenarioType.E_UM_NORMAL;
            this.mContext = null;
        }

        public UMAnalyticsConfig(Context context, String str, String str2) {
            this(context, str, str2, null, true);
        }

        public UMAnalyticsConfig(Context context, String str, String str2, EScenarioType eScenarioType) {
            this(context, str, str2, eScenarioType, true);
        }

        public UMAnalyticsConfig(Context context, String str, String str2, EScenarioType eScenarioType, boolean z) {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = EScenarioType.E_UM_NORMAL;
            this.mContext = null;
            this.mContext = context;
            this.mAppkey = str;
            this.mChannelId = str2;
            this.mIsCrashEnable = z;
            if (eScenarioType != null) {
                this.mType = eScenarioType;
                return;
            }
            switch (AnalyticsConfig.getVerticalType(context)) {
                case 0:
                    this.mType = EScenarioType.E_UM_NORMAL;
                    return;
                case 1:
                    this.mType = EScenarioType.E_UM_GAME;
                    return;
                case 224:
                    this.mType = EScenarioType.E_UM_ANALYTICS_OEM;
                    return;
                case 225:
                    this.mType = EScenarioType.E_UM_GAME_OEM;
                    return;
                default:
                    return;
            }
        }
    }

    public static void startWithConfigure(UMAnalyticsConfig uMAnalyticsConfig) {
        if (uMAnalyticsConfig != null) {
            b.a().a(uMAnalyticsConfig);
        }
    }

    public static void setLocation(double d, double d2) {
        b.a().a(d, d2);
    }

    public static void setLatencyWindow(long j) {
        b.a().a(j);
    }

    public static void enableEncrypt(boolean z) {
        b.a().e(z);
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        b.a().a(z);
    }

    public static void setSecret(Context context, String str) {
        b.a().b(context, str);
    }

    public static void setScenarioType(Context context, EScenarioType eScenarioType) {
        b.a().a(context, eScenarioType);
    }

    public static void setSessionContinueMillis(long j) {
        b.a().b(j);
    }

    public static b getAgent() {
        return b.a();
    }

    public static void setCheckDevice(boolean z) {
        b.a().c(z);
    }

    public static void setOpenGLContext(GL10 gl10) {
        b.a().a(gl10);
    }

    public static void openActivityDurationTrack(boolean z) {
        b.a().b(z);
    }

    public static void onPageStart(String str) {
        if (TextUtils.isEmpty(str)) {
            bw.e("pageName is null or empty");
        } else {
            b.a().a(str);
        }
    }

    public static void onPageEnd(String str) {
        if (TextUtils.isEmpty(str)) {
            bw.e("pageName is null or empty");
        } else {
            b.a().b(str);
        }
    }

    public static void setDebugMode(boolean z) {
        b.a().d(z);
    }

    public static void onPause(Context context) {
        b.a().b(context);
    }

    public static void onResume(Context context) {
        if (context == null) {
            bw.e("unexpected null context in onResume");
        } else {
            b.a().a(context);
        }
    }

    public static void reportError(Context context, String str) {
        b.a().a(context, str);
    }

    public static void reportError(Context context, Throwable th) {
        b.a().a(context, th);
    }

    public static void onEvent(Context context, List<String> list, int i, String str) {
    }

    public static void onEvent(Context context, String str) {
        b.a().a(context, str, null, -1, 1);
    }

    public static void onEvent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            bw.c("label is null or empty");
        } else {
            b.a().a(context, str, str2, -1, 1);
        }
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        if (map == null) {
            bw.e(a);
            return;
        }
        b.a().a(context, str, new HashMap(map), -1);
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) {
        Map hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("__ct__", Integer.valueOf(i));
        b.a().a(context, str, hashMap, -1);
    }

    public static void onSocialEvent(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bw.e("context is null in onShareEvent");
            return;
        }
        d.d = "3";
        UMSocialService.share(context, str, uMPlatformDataArr);
    }

    public static void onSocialEvent(Context context, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bw.e("context is null in onShareEvent");
            return;
        }
        d.d = "3";
        UMSocialService.share(context, uMPlatformDataArr);
    }

    public static void onKillProcess(Context context) {
        b.a().d(context);
    }

    public static void onProfileSignIn(String str) {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            bw.d("uid is null");
        } else if (str2.length() > 64) {
            bw.d("uid is Illegal(length bigger then  legitimate length).");
        } else if (TextUtils.isEmpty(str)) {
            b.a().a("_adhoc", str2);
        } else if (str.length() > 32) {
            bw.d("provider is Illegal(length bigger then  legitimate length).");
        } else {
            b.a().a(str, str2);
        }
    }

    public static void onProfileSignOff() {
        b.a().c();
    }
}
