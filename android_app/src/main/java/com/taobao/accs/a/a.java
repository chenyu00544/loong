package com.taobao.accs.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Environment;
import android.text.TextUtils;
import com.taobao.accs.client.AccsConfig.ACCS_GROUP;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.client.GlobalConfig;
import com.taobao.accs.common.Constants;
import com.taobao.accs.internal.ServiceImpl;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.c;
import com.taobao.accs.utl.h;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Taobao */
public class a {
    public static final String ACTION_ACCS_CUSTOM_ELECTION = ("com.taobao.accs.intent.action." + GlobalConfig.mGroup + "ELECTION");
    public static final String ACTION_ACCS_ELECTION = "com.taobao.accs.intent.action.ELECTION";
    public static final String TAG = "ElectionServiceUtil";
    public static String a = null;
    public static boolean b = false;
    private static File c = null;
    private static File d = null;
    private static long e = 0;
    private static final Random f = new Random();

    /* compiled from: Taobao */
    public static class a {
        public String a = "";
        public int b = 0;
    }

    public static void a() {
        try {
            c = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + d());
            ALog.i(TAG, "path=" + c, new Object[0]);
            d = new File(c, com.taobao.accs.internal.a.ELECTION_SERVICE_ID);
            a = d.getPath();
        } catch (Throwable th) {
            ALog.e(TAG, TAG, th, new Object[0]);
        }
    }

    public static final void a(Context context, a aVar) {
        if (aVar != null) {
            GlobalClientInfo.getInstance(context).setElectionReslt(aVar);
            com.taobao.accs.common.a.a(new b(context, aVar));
        }
    }

    public static final a a(Context context) {
        if (!b()) {
            return new a();
        }
        a electionResult = GlobalClientInfo.getInstance(context).getElectionResult();
        if (electionResult == null) {
            return b(context);
        }
        ALog.i(TAG, "getElectionResult from mem", "host", electionResult.a, "retry", Integer.valueOf(electionResult.b));
        return electionResult;
    }

    public static final boolean b() {
        if (!b) {
            return h.c();
        }
        ALog.e(TAG, "try Election Fail, disable election!!", new Object[0]);
        return false;
    }

    public static final a b(Context context) {
        Throwable th;
        a aVar = new a();
        FileInputStream fileInputStream = null;
        try {
            if (c == null) {
                a();
            }
            File file = new File(a);
            if (file.exists()) {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[fileInputStream2.available()];
                    fileInputStream2.read(bArr);
                    JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
                    if (jSONObject != null) {
                        String string = jSONObject.getString(com.umeng.message.common.a.c);
                        if (UtilityImpl.packageExist(context, string)) {
                            aVar.a = string;
                            e = jSONObject.getLong("lastFlushTime");
                        }
                        if (System.currentTimeMillis() < e + 86400000) {
                            aVar.b = jSONObject.getInt("retry");
                            fileInputStream = fileInputStream2;
                        } else {
                            e = 0;
                        }
                    }
                    fileInputStream = fileInputStream2;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            ALog.i(TAG, "getElectionResult", "host", aVar.a, "retry", Integer.valueOf(aVar.b));
            GlobalClientInfo.getInstance(context).setElectionReslt(aVar);
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable th3) {
            th = th3;
            ALog.e(TAG, "readFile is error", th, new Object[0]);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return aVar;
        }
        return aVar;
    }

    public static final void a(Context context, byte[] bArr) {
        if (bArr == null || c == null) {
            ALog.e(TAG, "saveBlackList null", "data", bArr, "path", c);
            return;
        }
        ALog.i(TAG, "saveBlackList", "path", c + "/accs_blacklist");
        GlobalClientInfo.getInstance(context).setElectionBlackList(a(bArr));
        com.taobao.accs.common.a.a(new c(context, bArr, r0));
    }

    public static final void c(Context context) {
        ALog.i(TAG, "clearBlackList", new Object[0]);
        GlobalClientInfo.getInstance(context).setElectionBlackList(null);
        if (c == null) {
            ALog.e(TAG, "clearBlackList path null", new Object[0]);
            return;
        }
        try {
            File file = new File(c + "accs_blacklist");
            if (file.exists()) {
                file.delete();
            }
        } catch (Throwable th) {
            ALog.e(TAG, "clearBlackList", th, new Object[0]);
        }
    }

    public static final Map<String, Set<Integer>> d(Context context) {
        Map<String, Set<Integer>> electionBlackList = GlobalClientInfo.getInstance(context).getElectionBlackList();
        if (electionBlackList != null) {
            ALog.i(TAG, "getBlackList from mem", electionBlackList.toString());
            return electionBlackList;
        } else if (c == null) {
            ALog.e(TAG, "getBlackList path null", new Object[0]);
            return null;
        } else {
            try {
                byte[] a = c.a(new File(c + "accs_blacklist"));
                if (a != null) {
                    electionBlackList = a(a);
                }
            } catch (Throwable th) {
                ALog.e(TAG, "getBlackList", th, new Object[0]);
            }
            GlobalClientInfo.getInstance(context).setElectionBlackList(electionBlackList);
            return electionBlackList;
        }
    }

    private static Map<String, Set<Integer>> a(byte[] bArr) {
        Throwable th;
        Map<String, Set<Integer>> map = null;
        if (bArr != null) {
            try {
                JSONArray jSONArray = new JSONObject(new String(bArr)).getJSONArray(com.taobao.accs.internal.a.ELECTION_KEY_BLACKLIST);
                if (jSONArray != null && jSONArray.length() > 0) {
                    Map<String, Set<Integer>> hashMap = new HashMap();
                    int i = 0;
                    while (i < jSONArray.length()) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            JSONArray jSONArray2 = jSONObject.getJSONArray(com.taobao.accs.internal.a.ELECTION_KEY_SDKVS);
                            String string = jSONObject.getString(Constants.KEY_ELECTION_PKG);
                            if (jSONArray2 != null && jSONArray2.length() > 0) {
                                Set hashSet = new HashSet();
                                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                    hashSet.add(Integer.valueOf(jSONArray2.getInt(i)));
                                }
                                hashMap.put(string, hashSet);
                            }
                            i++;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            map = hashMap;
                            th = th3;
                        }
                    }
                    map = hashMap;
                }
                String str = TAG;
                String str2 = "praseBlackList";
                Object[] objArr = new Object[2];
                objArr[0] = com.taobao.accs.internal.a.ELECTION_KEY_BLACKLIST;
                objArr[1] = map == null ? "null" : map.toString();
                ALog.i(str, str2, objArr);
            } catch (Throwable th4) {
                th = th4;
                ALog.e(TAG, "praseBlackList", th, new Object[0]);
                return map;
            }
        }
        return map;
    }

    public static final boolean a(Context context, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean z;
        if (i != 1) {
            try {
                ALog.w(TAG, "checkApp election version not match", com.umeng.message.common.a.c, str, "require", Integer.valueOf(i), "self ver", Integer.valueOf(1));
                return false;
            } catch (Throwable th) {
                ALog.e(TAG, "checkApp error", th, new Object[0]);
                z = false;
            }
        } else if (ServiceImpl.a(str)) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                ALog.w(TAG, "checkApp applicaton info null", com.umeng.message.common.a.c, str);
                return false;
            } else if (applicationInfo.enabled) {
                Map d = d(context);
                if (d != null) {
                    ALog.i(TAG, "checkApp", "blackList", d.toString());
                    Set set = (Set) d.get(str);
                    if (set == null || !(set.contains(Integer.valueOf(Constants.SDK_VERSION_CODE)) || set.contains(Integer.valueOf(-1)))) {
                        set = (Set) d.get("*");
                        if (set != null && (set.contains(Integer.valueOf(Constants.SDK_VERSION_CODE)) || set.contains(Integer.valueOf(-1)))) {
                            ALog.w(TAG, "checkApp in blacklist *", com.umeng.message.common.a.c, str, "sdkv", Integer.valueOf(Constants.SDK_VERSION_CODE));
                            return false;
                        }
                    }
                    ALog.w(TAG, "checkApp in blacklist", com.umeng.message.common.a.c, str, "sdkv", Integer.valueOf(Constants.SDK_VERSION_CODE));
                    return false;
                }
                z = true;
                return z;
            } else {
                ALog.i(TAG, "checkApp is disabled", com.umeng.message.common.a.c, str);
                return false;
            }
        } else {
            ALog.w(TAG, "checkApp is unbinded", com.umeng.message.common.a.c, str);
            return false;
        }
    }

    public static String a(Context context, Map<String, Integer> map) {
        if (map == null || map.size() <= 0) {
            ALog.e(TAG, "localElection failed, packMap null", new Object[0]);
            return null;
        }
        List arrayList = new ArrayList();
        long j = -1;
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            long intValue = (long) ((Integer) entry.getValue()).intValue();
            if (intValue > j) {
                arrayList.clear();
                j = intValue;
            }
            if (intValue == j) {
                arrayList.add(str);
            }
        }
        String str2 = (String) arrayList.get(f.nextInt(10000) % arrayList.size());
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        ALog.i(TAG, "localElection localResult null, user curr app", Constants.KEY_ELECTION_PKG, context.getPackageName());
        return context.getPackageName();
    }

    public static final String e(Context context) {
        Throwable th;
        String str;
        try {
            ResolveInfo resolveService = context.getPackageManager().resolveService(new Intent(c()), 0);
            if (resolveService == null) {
                ALog.e(TAG, "getResolveService resolveInfo null", new Object[0]);
                return null;
            }
            ServiceInfo serviceInfo = resolveService.serviceInfo;
            if (serviceInfo == null || !serviceInfo.isEnabled()) {
                ALog.e(TAG, "getResolveService serviceinfo null or disabled", new Object[0]);
                return null;
            }
            str = serviceInfo.packageName;
            if (TextUtils.isEmpty(str)) {
                ALog.e(TAG, "getResolveService clientPack null", new Object[0]);
                return null;
            }
            try {
                ALog.i(TAG, "getResolveService", com.umeng.message.common.a.c, str);
                return str;
            } catch (Throwable th2) {
                th = th2;
                ALog.e(TAG, "getResolveService error", th, new Object[0]);
                return str;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str = null;
            th = th4;
            ALog.e(TAG, "getResolveService error", th, new Object[0]);
            return str;
        }
    }

    public static String c() {
        if (GlobalConfig.mGroup == ACCS_GROUP.TAOBAO) {
            return "com.taobao.accs.intent.action.ELECTION";
        }
        return ACTION_ACCS_CUSTOM_ELECTION;
    }

    public static String d() {
        if (GlobalConfig.mGroup == ACCS_GROUP.TAOBAO) {
            return "/accs/accs_main/1";
        }
        return "/accs/" + GlobalConfig.mGroup + "/" + 1;
    }
}
