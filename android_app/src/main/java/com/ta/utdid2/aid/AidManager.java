package com.ta.utdid2.aid;

import android.content.Context;
import android.util.Log;
import com.ta.utdid2.android.utils.NetworkUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.android.utils.TimeUtils;
import com.ut.device.AidCallback;

public class AidManager {
    private static final int NUM_DAY_OUT_OF_DATE = 1;
    private static final String TAG = AidManager.class.getName();
    private static AidManager sAidManager = null;
    private Context mContext;

    public static synchronized AidManager getInstance(Context context) {
        AidManager aidManager;
        synchronized (AidManager.class) {
            if (sAidManager == null) {
                sAidManager = new AidManager(context);
            }
            aidManager = sAidManager;
        }
        return aidManager;
    }

    private AidManager(Context context) {
        this.mContext = context;
    }

    public void requestAid(String str, String str2, String str3, AidCallback aidCallback) {
        boolean z = false;
        if (aidCallback == null) {
            Log.e(TAG, "callback is null!");
        } else if (this.mContext == null || StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
            boolean z2;
            String str4 = TAG;
            StringBuilder append = new StringBuilder("mContext:").append(this.mContext).append("; callback:").append(aidCallback).append("; has appName:");
            if (StringUtils.isEmpty(str)) {
                z2 = false;
            } else {
                z2 = true;
            }
            StringBuilder append2 = append.append(z2).append("; has token:");
            if (!StringUtils.isEmpty(str2)) {
                z = true;
            }
            Log.e(str4, append2.append(z).toString());
            aidCallback.onAidEventChanged(1002, "");
        } else {
            String aidValueFromSP = AidStorageController.getAidValueFromSP(this.mContext, str, str2);
            if (!StringUtils.isEmpty(aidValueFromSP) && TimeUtils.isUpToDate(AidStorageController.getAidGenTimeFromSP(this.mContext, str, str2), 1)) {
                aidCallback.onAidEventChanged(1001, aidValueFromSP);
            } else if (NetworkUtils.isConnected(this.mContext)) {
                AidRequester.getInstance(this.mContext).postRestAsync(str, str2, str3, aidValueFromSP, aidCallback);
            } else {
                aidCallback.onAidEventChanged(1003, aidValueFromSP);
            }
        }
    }

    public String getValue(String str, String str2, String str3) {
        boolean z = false;
        if (this.mContext == null || StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
            String str4 = TAG;
            StringBuilder append = new StringBuilder("mContext:").append(this.mContext).append("; has appName:").append(!StringUtils.isEmpty(str)).append("; has token:");
            if (!StringUtils.isEmpty(str2)) {
                z = true;
            }
            Log.e(str4, append.append(z).toString());
            return "";
        }
        String aidValueFromSP = AidStorageController.getAidValueFromSP(this.mContext, str, str2);
        if ((StringUtils.isEmpty(aidValueFromSP) || !TimeUtils.isUpToDate(AidStorageController.getAidGenTimeFromSP(this.mContext, str, str2), 1)) && NetworkUtils.isConnected(this.mContext)) {
            return genAidValue(str, str2, str3);
        }
        return aidValueFromSP;
    }

    private synchronized String genAidValue(String str, String str2, String str3) {
        String str4;
        if (this.mContext == null) {
            Log.e(TAG, "no context!");
            str4 = "";
        } else {
            str4 = "";
            if (NetworkUtils.isConnected(this.mContext)) {
                str4 = AidRequester.getInstance(this.mContext).postRest(str, str2, str3, AidStorageController.getAidValueFromSP(this.mContext, str, str2));
            }
            AidStorageController.setAidValueToSP(this.mContext, str, str4, str2);
        }
        return str4;
    }
}
