package com.unionpay.c;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrength;
import android.telephony.NeighboringCellInfo;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.location.h.c;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.analytics.pro.x;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ah {
    static TelephonyManager a;
    static boolean b = false;
    static long c = -300000;
    private static final String[] d = new String[]{"UNKNOWN", "GPRS", "EDGE", "UMTS", "CDMA", "EVDO_0", "EVDO_A", "1xRTT", "HSDPA", "HSUPA", "HSPA", "IDEN", "EVDO_B", "LTE", "EHRPD", "HSPAP"};
    private static final String[] e = new String[]{"NONE", "GSM", "CDMA", "SIP"};

    private static Boolean a(String str) {
        try {
            char charAt = str.length() > 0 ? str.charAt(0) : '0';
            Boolean valueOf = Boolean.valueOf(true);
            for (int i = 0; i < str.length(); i++) {
                if (charAt != str.charAt(i)) {
                    return Boolean.valueOf(false);
                }
            }
            return valueOf;
        } catch (Throwable th) {
            return Boolean.valueOf(false);
        }
    }

    private static String a(int i) {
        return (i < 0 || i >= d.length) ? String.valueOf(i) : d[i];
    }

    private static JSONArray a(BitSet bitSet) {
        int cardinality = bitSet.cardinality();
        if (bitSet != null && cardinality > 0) {
            JSONArray jSONArray = new JSONArray();
            cardinality = bitSet.nextSetBit(0);
            while (cardinality >= 0) {
                jSONArray.put(cardinality);
                cardinality = bitSet.nextSetBit(cardinality + 1);
            }
        }
        return null;
    }

    public static JSONObject a(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lat", i);
            jSONObject.put("lng", i2);
            jSONObject.put("unit", "qd");
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    private static JSONObject a(TelephonyManager telephonyManager, SubscriptionManager subscriptionManager, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (am.a(22)) {
                SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = subscriptionManager.getActiveSubscriptionInfoForSimSlotIndex(i);
                if (activeSubscriptionInfoForSimSlotIndex != null) {
                    jSONObject.put("simSerialNumber", activeSubscriptionInfoForSimSlotIndex.getIccId() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getIccId());
                    jSONObject.put("simOperator", activeSubscriptionInfoForSimSlotIndex.getMcc() + "0" + activeSubscriptionInfoForSimSlotIndex.getMnc());
                    jSONObject.put("simOperatorName", activeSubscriptionInfoForSimSlotIndex.getCarrierName() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getCarrierName());
                    jSONObject.put("simCountryIso", activeSubscriptionInfoForSimSlotIndex.getCountryIso() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getCountryIso());
                    int subscriptionId = activeSubscriptionInfoForSimSlotIndex.getSubscriptionId();
                    Method method = telephonyManager.getClass().getMethod("getSubscriberId", new Class[]{Integer.TYPE});
                    method.setAccessible(true);
                    Object invoke = method.invoke(telephonyManager, new Object[]{Integer.valueOf(subscriptionId)});
                    String str = "subscriberId";
                    if (invoke == null) {
                        invoke = "";
                    }
                    jSONObject.put(str, invoke);
                }
            }
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    private static JSONObject a(TelephonyManager telephonyManager, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("imei", str.trim());
            jSONObject.put("subscriberId", telephonyManager.getSubscriberId() == null ? "" : telephonyManager.getSubscriberId());
            jSONObject.put("simSerialNumber", telephonyManager.getSimSerialNumber() == null ? "" : telephonyManager.getSimSerialNumber());
            jSONObject.put("dataState", telephonyManager.getDataState());
            jSONObject.put("networkType", a(telephonyManager.getNetworkType()));
            jSONObject.put("networkOperator", telephonyManager.getNetworkOperator());
            jSONObject.put("phoneType", b(telephonyManager.getPhoneType()));
            jSONObject.put("simOperator", telephonyManager.getSimOperator() == null ? "" : telephonyManager.getSimOperator());
            jSONObject.put("simOperatorName", telephonyManager.getSimOperatorName() == null ? "" : telephonyManager.getSimOperatorName());
            jSONObject.put("simCountryIso", telephonyManager.getSimCountryIso() == null ? "" : telephonyManager.getSimCountryIso());
            return jSONObject;
        } catch (Throwable th) {
            return null;
        }
    }

    private static JSONObject a(Class cls, Object obj, Integer num, String str, String str2) {
        Method method;
        Object obj2;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("imei", str);
        try {
            method = cls.getMethod("getSubscriberId" + str2, new Class[]{Integer.TYPE});
            String str3 = "subscriberId";
            if (method.invoke(obj, new Object[]{num}) == null) {
                obj2 = "";
            } else {
                obj2 = ((String) method.invoke(obj, new Object[]{num})).trim();
            }
            jSONObject.put(str3, obj2);
        } catch (Throwable th) {
        }
        try {
            method = cls.getMethod("getSimSerialNumber" + str2, new Class[]{Integer.TYPE});
            str3 = "simSerialNumber";
            if (method.invoke(obj, new Object[]{num}) == null) {
                obj2 = "";
            } else {
                obj2 = ((String) method.invoke(obj, new Object[]{num})).trim();
            }
            jSONObject.put(str3, obj2);
        } catch (Throwable th2) {
        }
        try {
            jSONObject.put("dataState", (Integer) cls.getMethod("getDataState" + str2, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{num}));
        } catch (Throwable th3) {
        }
        try {
            jSONObject.put("networkType", a(((Integer) cls.getMethod("getNetworkType" + str2, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{num})).intValue()));
        } catch (Throwable th4) {
        }
        try {
            jSONObject.put("networkOperator", (String) cls.getMethod("getNetworkOperator" + str2, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{num}));
        } catch (Throwable th5) {
        }
        try {
            jSONObject.put("phoneType", b(((Integer) cls.getMethod("getPhoneType" + str2, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{num})).intValue()));
        } catch (Throwable th6) {
        }
        try {
            method = cls.getMethod("getSimOperator" + str2, new Class[]{Integer.TYPE});
            str3 = "simOperator";
            if (method.invoke(obj, new Object[]{num}) == null) {
                obj2 = "";
            } else {
                obj2 = ((String) method.invoke(obj, new Object[]{num})).trim();
            }
            jSONObject.put(str3, obj2);
        } catch (Throwable th7) {
        }
        try {
            method = cls.getMethod("getSimOperatorName" + str2, new Class[]{Integer.TYPE});
            str3 = "simOperatorName";
            if (method.invoke(obj, new Object[]{num}) == null) {
                obj2 = "";
            } else {
                obj2 = ((String) method.invoke(obj, new Object[]{num})).trim();
            }
            jSONObject.put(str3, obj2);
        } catch (Throwable th8) {
        }
        return jSONObject;
    }

    public static boolean a() {
        try {
            return am.a(11) ? !TextUtils.isEmpty(System.getProperty("http.proxyHost")) : !TextUtils.isEmpty(Proxy.getDefaultHost());
        } catch (Throwable th) {
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r5) {
        /*
        r1 = 0;
        r0 = "android.permission.ACCESS_NETWORK_STATE";
        r0 = com.unionpay.c.am.b(r5, r0);	 Catch:{ Throwable -> 0x0084 }
        if (r0 == 0) goto L_0x0031;
    L_0x0009:
        r0 = "connectivity";
        r0 = r5.getSystemService(r0);	 Catch:{ Throwable -> 0x0084 }
        r0 = (android.net.ConnectivityManager) r0;	 Catch:{ Throwable -> 0x0084 }
        r2 = r0.getActiveNetworkInfo();	 Catch:{ Throwable -> 0x0084 }
        if (r2 == 0) goto L_0x001c;
    L_0x0017:
        r0 = r2.isConnected();	 Catch:{ Throwable -> 0x0084 }
    L_0x001b:
        return r0;
    L_0x001c:
        r2 = 0;
        r0 = r0.getNetworkInfo(r2);	 Catch:{ Throwable -> 0x0084 }
        if (r0 == 0) goto L_0x002f;
    L_0x0023:
        r0 = r0.getState();	 Catch:{ Throwable -> 0x0084 }
        r2 = android.net.NetworkInfo.State.UNKNOWN;	 Catch:{ Throwable -> 0x0084 }
        r0 = r0.equals(r2);	 Catch:{ Throwable -> 0x0084 }
        if (r0 != 0) goto L_0x0031;
    L_0x002f:
        r0 = r1;
        goto L_0x001b;
    L_0x0031:
        r0 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Throwable -> 0x0084 }
        r2 = c;	 Catch:{ Throwable -> 0x0084 }
        r0 = r0 - r2;
        r2 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0060;
    L_0x003f:
        r0 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Throwable -> 0x0084 }
        c = r0;	 Catch:{ Throwable -> 0x0084 }
        r0 = 0;
        r1 = a();	 Catch:{ Throwable -> 0x006e, all -> 0x007a }
        if (r1 == 0) goto L_0x0063;
    L_0x004c:
        r1 = new java.net.Socket;	 Catch:{ Throwable -> 0x006e, all -> 0x007a }
        r2 = android.net.Proxy.getDefaultHost();	 Catch:{ Throwable -> 0x006e, all -> 0x007a }
        r3 = android.net.Proxy.getDefaultPort();	 Catch:{ Throwable -> 0x006e, all -> 0x007a }
        r1.<init>(r2, r3);	 Catch:{ Throwable -> 0x006e, all -> 0x007a }
        r0 = r1;
    L_0x005a:
        r1 = 1;
        b = r1;	 Catch:{ Throwable -> 0x006e }
        r0.close();	 Catch:{ Throwable -> 0x0086 }
    L_0x0060:
        r0 = b;
        goto L_0x001b;
    L_0x0063:
        r1 = new java.net.Socket;	 Catch:{ Throwable -> 0x006e, all -> 0x007a }
        r2 = "140.207.168.45";
        r3 = 80;
        r1.<init>(r2, r3);	 Catch:{ Throwable -> 0x006e, all -> 0x007a }
        r0 = r1;
        goto L_0x005a;
    L_0x006e:
        r1 = move-exception;
        r1 = 0;
        b = r1;	 Catch:{ all -> 0x008a }
        if (r0 == 0) goto L_0x0060;
    L_0x0074:
        r0.close();	 Catch:{ Throwable -> 0x0078 }
        goto L_0x0060;
    L_0x0078:
        r0 = move-exception;
        goto L_0x0060;
    L_0x007a:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x007e:
        if (r1 == 0) goto L_0x0083;
    L_0x0080:
        r1.close();	 Catch:{ Throwable -> 0x0088 }
    L_0x0083:
        throw r0;	 Catch:{ Throwable -> 0x0084 }
    L_0x0084:
        r0 = move-exception;
        goto L_0x0060;
    L_0x0086:
        r0 = move-exception;
        goto L_0x0060;
    L_0x0088:
        r1 = move-exception;
        goto L_0x0083;
    L_0x008a:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.c.ah.a(android.content.Context):boolean");
    }

    private static Boolean b(String str) {
        try {
            Integer valueOf = Integer.valueOf(str.length());
            if (valueOf.intValue() > 10 && valueOf.intValue() < 20 && !a(str.trim()).booleanValue()) {
                return Boolean.valueOf(true);
            }
        } catch (Throwable th) {
        }
        return Boolean.valueOf(false);
    }

    private static String b(int i) {
        return (i < 0 || i >= e.length) ? String.valueOf(i) : e[i];
    }

    public static boolean b(Context context) {
        try {
            return ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).isWifiEnabled();
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            if (a == null) {
                s(context);
            }
            return a.getSimState() == 5;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean d(Context context) {
        try {
            if (am.b(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                return activeNetworkInfo != null && 1 == activeNetworkInfo.getType() && activeNetworkInfo.isConnected();
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public static boolean e(Context context) {
        try {
            if (a == null) {
                s(context);
            }
            return a.getDataState() == 2;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String f(Context context) {
        return !a(context) ? "OFFLINE" : d(context) ? c.do : g(context);
    }

    public static String g(Context context) {
        String str = d[0];
        try {
            if (a == null) {
                s(context);
            }
            str = a(a.getNetworkType());
        } catch (Throwable th) {
        }
        return str;
    }

    public static String h(Context context) {
        try {
            if (a == null) {
                s(context);
            }
            return a.getNetworkOperator();
        } catch (Throwable th) {
            return null;
        }
    }

    public static String i(Context context) {
        try {
            if (a == null) {
                s(context);
            }
            return a.getSimOperator();
        } catch (Throwable th) {
            return null;
        }
    }

    public static String j(Context context) {
        try {
            if (a == null) {
                s(context);
            }
            return a.getNetworkCountryIso();
        } catch (Throwable th) {
            return null;
        }
    }

    public static String k(Context context) {
        try {
            if (a == null) {
                s(context);
            }
            return a.getNetworkOperatorName();
        } catch (Throwable th) {
            return null;
        }
    }

    public static JSONArray l(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", UtilityImpl.NET_TYPE_WIFI);
            jSONObject.put("available", b(context));
            jSONObject.put("connected", d(context));
            jSONObject.put("current", p(context));
            jSONObject.put("scannable", q(context));
            jSONObject.put("configured", o(context));
            jSONArray.put(jSONObject);
        } catch (Throwable th) {
        }
        try {
            jSONObject = new JSONObject();
            jSONObject.put("type", "cellular");
            jSONObject.put("available", c(context));
            jSONObject.put("connected", e(context));
            jSONObject.put("current", m(context));
            jSONObject.put("scannable", n(context));
            jSONArray.put(jSONObject);
        } catch (Throwable th2) {
        }
        return jSONArray.length() > 0 ? jSONArray : null;
    }

    public static JSONArray m(Context context) {
        try {
            if (!am.a) {
                return null;
            }
            if (am.a(23) && context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
                return null;
            }
            if (am.b(context, "android.permission.ACCESS_COARSE_LOCATION") || am.b(context, "android.permission.ACCESS_FINE_LOCATION")) {
                if (a == null) {
                    s(context);
                }
                JSONObject jSONObject = new JSONObject();
                if (am.c || am.d) {
                    CellLocation cellLocation = a.getCellLocation();
                    if (cellLocation instanceof GsmCellLocation) {
                        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                        if (gsmCellLocation != null) {
                            jSONObject.put("systemId", gsmCellLocation.getLac());
                            jSONObject.put("networkId", gsmCellLocation.getCid());
                            if (am.a(9)) {
                                jSONObject.put("basestationId", gsmCellLocation.getPsc());
                            }
                        }
                    } else if (cellLocation instanceof CdmaCellLocation) {
                        CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                        if (cdmaCellLocation != null) {
                            jSONObject.put("systemId", cdmaCellLocation.getSystemId());
                            jSONObject.put("networkId", cdmaCellLocation.getNetworkId());
                            jSONObject.put("basestationId", cdmaCellLocation.getBaseStationId());
                            jSONObject.put("location", a(cdmaCellLocation.getBaseStationLatitude(), cdmaCellLocation.getBaseStationLongitude()));
                        }
                    }
                }
                jSONObject.put("type", g(context));
                jSONObject.put("mcc", h(context));
                jSONObject.put("operator", k(context));
                jSONObject.put(x.G, j(context));
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                return jSONArray;
            }
            return null;
        } catch (Throwable th) {
        }
    }

    public static JSONArray n(Context context) {
        if (!am.a) {
            return null;
        }
        if (am.a(23) && context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return null;
        }
        if (am.b(context, "android.permission.ACCESS_COARSE_LOCATION") || am.b(context, "android.permission.ACCESS_FINE_LOCATION")) {
            try {
                if (a == null) {
                    s(context);
                }
                JSONArray jSONArray = new JSONArray();
                if (am.a(17)) {
                    List<CellInfo> allCellInfo = a.getAllCellInfo();
                    if (allCellInfo != null) {
                        for (CellInfo cellInfo : allCellInfo) {
                            try {
                                int lac;
                                int cid;
                                int mcc;
                                int mnc;
                                int i;
                                Object obj;
                                CellSignalStrength cellSignalStrength;
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("registered", cellInfo.isRegistered());
                                jSONObject.put("ts", cellInfo.getTimeStamp());
                                if (cellInfo instanceof CellInfoGsm) {
                                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                                    CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
                                    lac = cellIdentity.getLac();
                                    cid = cellIdentity.getCid();
                                    mcc = cellIdentity.getMcc();
                                    mnc = cellIdentity.getMnc();
                                    i = lac;
                                    lac = cid;
                                    cid = -1;
                                    CellSignalStrength cellSignalStrength2 = cellInfoGsm.getCellSignalStrength();
                                    obj = "GSM";
                                    cellSignalStrength = cellSignalStrength2;
                                } else if (cellInfo instanceof CellInfoCdma) {
                                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                                    CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
                                    cid = cellIdentity2.getSystemId();
                                    mcc = cellIdentity2.getNetworkId();
                                    mnc = cellIdentity2.getBasestationId();
                                    CellSignalStrength cellSignalStrength3 = cellInfoCdma.getCellSignalStrength();
                                    jSONObject.put("cdmaDbm", cellSignalStrength3.getCdmaDbm());
                                    jSONObject.put("cdmaDbm", cellSignalStrength3.getCdmaDbm());
                                    jSONObject.put("cdmaEcio", cellSignalStrength3.getCdmaEcio());
                                    jSONObject.put("evdoDbm", cellSignalStrength3.getEvdoDbm());
                                    jSONObject.put("evdoEcio", cellSignalStrength3.getEvdoEcio());
                                    jSONObject.put("evdoSnr", cellSignalStrength3.getEvdoSnr());
                                    jSONObject.put("location", a(cellIdentity2.getLatitude(), cellIdentity2.getLongitude()));
                                    lac = mcc;
                                    i = cid;
                                    mcc = -1;
                                    cid = mnc;
                                    mnc = -1;
                                    cellSignalStrength = cellSignalStrength3;
                                    r0 = "CDMA";
                                } else if (cellInfo instanceof CellInfoWcdma) {
                                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                                    CellIdentityWcdma cellIdentity3 = cellInfoWcdma.getCellIdentity();
                                    i = cellIdentity3.getLac();
                                    lac = cellIdentity3.getCid();
                                    cid = cellIdentity3.getPsc();
                                    mcc = cellIdentity3.getMcc();
                                    mnc = cellIdentity3.getMnc();
                                    cellSignalStrength = cellInfoWcdma.getCellSignalStrength();
                                    r0 = "WCDMA";
                                } else if (cellInfo instanceof CellInfoLte) {
                                    String str = "LTE";
                                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                                    CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
                                    i = cellIdentity4.getTac();
                                    lac = cellIdentity4.getPci();
                                    cid = cellIdentity4.getCi();
                                    mcc = cellIdentity4.getMcc();
                                    mnc = cellIdentity4.getMnc();
                                    String str2 = str;
                                    cellSignalStrength = cellInfoLte.getCellSignalStrength();
                                    r0 = str2;
                                } else {
                                    obj = null;
                                    cellSignalStrength = null;
                                    mnc = -1;
                                    mcc = -1;
                                    cid = -1;
                                    lac = -1;
                                    i = -1;
                                }
                                if (i != -1) {
                                    jSONObject.put("systemId", i);
                                }
                                if (lac != -1) {
                                    jSONObject.put("networkId", lac);
                                }
                                if (cid != -1) {
                                    jSONObject.put("basestationId", cid);
                                }
                                if (mcc != -1) {
                                    jSONObject.put("mcc", mcc);
                                }
                                if (mnc != -1) {
                                    jSONObject.put("mnc", mnc);
                                }
                                if (cellSignalStrength != null) {
                                    jSONObject.put("asuLevel", cellSignalStrength.getAsuLevel());
                                    jSONObject.put("dbm", cellSignalStrength.getDbm());
                                }
                                jSONObject.put("type", obj);
                                jSONArray.put(jSONObject);
                            } catch (Throwable th) {
                            }
                        }
                    }
                } else if (am.a(5) && (am.c || am.d)) {
                    List<NeighboringCellInfo> neighboringCellInfo = a.getNeighboringCellInfo();
                    if (neighboringCellInfo != null) {
                        for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("systemId", neighboringCellInfo2.getLac());
                                jSONObject2.put("netId", neighboringCellInfo2.getCid());
                                jSONObject2.put("basestationId", neighboringCellInfo2.getPsc());
                                jSONObject2.put("asuLevel", neighboringCellInfo2.getRssi());
                                jSONObject2.put("type", a(neighboringCellInfo2.getNetworkType()));
                                jSONArray.put(jSONObject2);
                            } catch (Throwable th2) {
                            }
                        }
                    }
                }
                return jSONArray.length() > 0 ? jSONArray : null;
            } catch (Throwable th3) {
            }
        }
        return null;
    }

    public static JSONArray o(Context context) {
        try {
            if (!am.a) {
                return null;
            }
            if (am.b(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                List<WifiConfiguration> configuredNetworks = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConfiguredNetworks();
                if (configuredNetworks != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("networkId", wifiConfiguration.networkId);
                            jSONObject.put("priority", wifiConfiguration.priority);
                            jSONObject.put("name", wifiConfiguration.SSID);
                            jSONObject.put("id", wifiConfiguration.BSSID);
                            jSONObject.put("allowedKeyManagement", a(wifiConfiguration.allowedKeyManagement));
                            jSONObject.put("allowedAuthAlgorithms", a(wifiConfiguration.allowedAuthAlgorithms));
                            jSONObject.put("allowedGroupCiphers", a(wifiConfiguration.allowedGroupCiphers));
                            jSONObject.put("allowedPairwiseCiphers", a(wifiConfiguration.allowedPairwiseCiphers));
                            jSONArray.put(jSONObject);
                        } catch (Throwable th) {
                        }
                    }
                    return jSONArray.length() > 0 ? jSONArray : null;
                }
            }
            return null;
        } catch (Throwable th2) {
        }
    }

    public static JSONArray p(Context context) {
        try {
            if (!am.a) {
                return null;
            }
            if (am.b(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (wifiManager.isWifiEnabled()) {
                    WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                    if (!(connectionInfo == null || connectionInfo.getBSSID() == null)) {
                        String bssid = connectionInfo.getBSSID();
                        JSONArray jSONArray = new JSONArray();
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("name", connectionInfo.getSSID());
                            jSONObject.put("id", bssid);
                            jSONObject.put("level", connectionInfo.getRssi());
                            jSONObject.put("hidden", connectionInfo.getHiddenSSID());
                            jSONObject.put("ip", connectionInfo.getIpAddress());
                            jSONObject.put("speed", connectionInfo.getLinkSpeed());
                            jSONObject.put("networkId", connectionInfo.getNetworkId());
                            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, connectionInfo.getMacAddress());
                            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
                            if (dhcpInfo != null) {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("dns1", dhcpInfo.dns1);
                                jSONObject2.put("dns2", dhcpInfo.dns2);
                                jSONObject2.put("gw", dhcpInfo.gateway);
                                jSONObject2.put("ip", dhcpInfo.ipAddress);
                                jSONObject2.put("mask", dhcpInfo.netmask);
                                jSONObject2.put("server", dhcpInfo.serverAddress);
                                jSONObject2.put("leaseDuration", dhcpInfo.leaseDuration);
                                jSONObject.put("dhcp", jSONObject2);
                            }
                        } catch (Throwable th) {
                        }
                        jSONArray.put(jSONObject);
                        return jSONArray;
                    }
                }
            }
            return null;
        } catch (Throwable th2) {
        }
    }

    public static JSONArray q(Context context) {
        if (!am.a || (!am.c && !am.d)) {
            return null;
        }
        try {
            if (am.b(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                if (wifiManager.isWifiEnabled() || (VERSION.SDK_INT >= 18 && wifiManager.isScanAlwaysAvailable())) {
                    if (am.b(context, "android.permission.CHANGE_WIFI_STATE")) {
                        try {
                            Object obj = new Object();
                            context.registerReceiver(new p(obj, context), new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                            wifiManager.startScan();
                            synchronized (obj) {
                                obj.wait(2000);
                            }
                        } catch (Throwable th) {
                        }
                    }
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    if (scanResults != null) {
                        JSONArray jSONArray = new JSONArray();
                        for (ScanResult scanResult : scanResults) {
                            if (scanResult.level >= -85) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("id", scanResult.BSSID);
                                    jSONObject.put("name", scanResult.SSID);
                                    jSONObject.put("level", scanResult.level);
                                    jSONObject.put("freq", scanResult.frequency);
                                    if (am.a(17)) {
                                        jSONObject.put("ts", scanResult.timestamp);
                                        jSONObject.put("scanTs", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) + (scanResult.timestamp / 1000));
                                    }
                                    jSONArray.put(jSONObject);
                                } catch (Throwable th2) {
                                }
                            }
                        }
                        return jSONArray;
                    }
                }
            }
        } catch (Throwable th3) {
        }
        return null;
    }

    public static JSONArray r(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            List arrayList = new ArrayList();
            JSONObject a;
            String str;
            if (am.a(22)) {
                SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
                try {
                    JSONObject a2 = a(telephonyManager, subscriptionManager, 0);
                    a2.put("imei", telephonyManager.getDeviceId());
                    jSONArray.put(a2);
                } catch (Throwable th) {
                }
                try {
                    a = a(telephonyManager, subscriptionManager, 1);
                    str = "imei";
                    Object deviceId = (am.a(23) && telephonyManager.getPhoneCount() == 2) ? telephonyManager.getDeviceId(1) : null;
                    a.put(str, deviceId);
                    if (a.length() > 0) {
                        jSONArray.put(a);
                    }
                } catch (Throwable th2) {
                }
            } else {
                JSONObject a3;
                str = telephonyManager.getDeviceId();
                if (b(str.trim()).booleanValue()) {
                    arrayList.add(str.trim());
                    a3 = a(telephonyManager, str);
                    if (a3 != null) {
                        jSONArray.put(a3);
                    }
                }
                try {
                    telephonyManager = (TelephonyManager) context.getSystemService("phone1");
                    str = telephonyManager.getDeviceId();
                    if (!(str == null || !b(str).booleanValue() || arrayList.contains(str))) {
                        arrayList.add(str);
                        a3 = a(telephonyManager, str);
                        if (a3 != null) {
                            jSONArray.put(a3);
                        }
                    }
                } catch (Throwable th3) {
                }
                try {
                    telephonyManager = (TelephonyManager) context.getSystemService("phone2");
                    str = telephonyManager.getDeviceId();
                    if (!(str == null || !b(str).booleanValue() || arrayList.contains(str))) {
                        arrayList.add(str);
                        a3 = a(telephonyManager, str);
                        if (a3 != null) {
                            jSONArray.put(a3);
                        }
                    }
                } catch (Throwable th4) {
                }
                JSONArray w = w(context);
                JSONArray v = v(context);
                if (v == null) {
                    v = w;
                }
                w = u(context);
                if (w == null) {
                    w = v;
                }
                v = t(context);
                if (v != null) {
                    w = v;
                }
                if (w != null && w.length() > 0) {
                    for (int i = 0; i < w.length(); i++) {
                        a = w.getJSONObject(i);
                        String string = a.getString("imei");
                        if (!arrayList.contains(string)) {
                            arrayList.add(string);
                            jSONArray.put(a);
                        }
                    }
                }
            }
        } catch (Throwable th5) {
        }
        return jSONArray;
    }

    private static void s(Context context) {
        try {
            a = (TelephonyManager) context.getSystemService("phone");
        } catch (Throwable th) {
        }
    }

    private static JSONArray t(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Class cls = Class.forName("com.android.internal.telephony.Phone");
            Field field = cls.getField("GEMINI_SIM_1");
            field.setAccessible(true);
            Integer num = (Integer) field.get(null);
            Field field2 = cls.getField("GEMINI_SIM_2");
            field2.setAccessible(true);
            Integer num2 = (Integer) field2.get(null);
            Integer num3 = num;
        } catch (Throwable th) {
            return null;
        }
        Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", new Class[]{Integer.TYPE});
        if (telephonyManager == null || declaredMethod == null) {
            return null;
        }
        String trim = ((String) declaredMethod.invoke(telephonyManager, new Object[]{num3})).trim();
        String trim2 = ((String) declaredMethod.invoke(telephonyManager, new Object[]{num2})).trim();
        if (b(trim).booleanValue()) {
            jSONArray.put(a(TelephonyManager.class, telephonyManager, num3, trim, "Gemini"));
        }
        if (b(trim2).booleanValue()) {
            jSONArray.put(a(TelephonyManager.class, telephonyManager, num2, trim2, "Gemini"));
        }
        return jSONArray;
    }

    private static JSONArray u(Context context) {
        JSONArray jSONArray;
        TelephonyManager telephonyManager;
        Integer num;
        Integer num2;
        try {
            jSONArray = new JSONArray();
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Class cls = Class.forName("com.android.internal.telephony.Phone");
            Field field = cls.getField("GEMINI_SIM_1");
            field.setAccessible(true);
            num = (Integer) field.get(null);
            Field field2 = cls.getField("GEMINI_SIM_2");
            field2.setAccessible(true);
            num2 = (Integer) field2.get(null);
        } catch (Throwable th) {
            return null;
        }
        Method method = TelephonyManager.class.getMethod("getDefault", new Class[]{Integer.TYPE});
        TelephonyManager telephonyManager2 = (TelephonyManager) method.invoke(telephonyManager, new Object[]{num});
        telephonyManager = (TelephonyManager) method.invoke(telephonyManager, new Object[]{num2});
        String trim = telephonyManager2.getDeviceId().trim();
        String trim2 = telephonyManager.getDeviceId().trim();
        if (b(trim).booleanValue()) {
            JSONObject a = a(telephonyManager2, trim);
            if (a != null) {
                jSONArray.put(a);
            }
        }
        if (b(trim2).booleanValue()) {
            JSONObject a2 = a(telephonyManager, trim2);
            if (a2 != null) {
                jSONArray.put(a2);
            }
        }
        return jSONArray;
    }

    private static JSONArray v(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            Class cls = Class.forName("com.android.internal.telephony.PhoneFactory");
            String str = (String) cls.getMethod("getServiceName", new Class[]{String.class, Integer.TYPE}).invoke(cls, new Object[]{"phone", Integer.valueOf(1)});
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String trim = telephonyManager.getDeviceId().trim();
            TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService(str);
            String trim2 = telephonyManager2.getDeviceId().trim();
            if (b(trim).booleanValue()) {
                JSONObject a = a(telephonyManager, trim);
                if (a != null) {
                    jSONArray.put(a);
                }
            }
            if (b(trim2).booleanValue()) {
                JSONObject a2 = a(telephonyManager2, trim2);
                if (a2 != null) {
                    jSONArray.put(a2);
                }
            }
            return jSONArray;
        } catch (Throwable th) {
            return null;
        }
    }

    private static JSONArray w(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            Class cls = Class.forName("android.telephony.MSimTelephonyManager");
            Object systemService = context.getSystemService("phone_msim");
            Integer valueOf = Integer.valueOf(0);
            Integer valueOf2 = Integer.valueOf(1);
            Method method = cls.getMethod("getDeviceId", new Class[]{Integer.TYPE});
            String trim = ((String) method.invoke(systemService, new Object[]{valueOf})).trim();
            String trim2 = ((String) method.invoke(systemService, new Object[]{valueOf2})).trim();
            if (b(trim).booleanValue()) {
                jSONArray.put(a(cls, systemService, valueOf, trim, ""));
            }
            if (b(trim2).booleanValue()) {
                jSONArray.put(a(cls, systemService, valueOf2, trim2, ""));
            }
            return jSONArray;
        } catch (Throwable th) {
            return null;
        }
    }
}
