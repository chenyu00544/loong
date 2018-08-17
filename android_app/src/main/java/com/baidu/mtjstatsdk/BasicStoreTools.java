package com.baidu.mtjstatsdk;

import android.content.Context;

public class BasicStoreTools extends BasicStoreToolsBase {
    public static final String APP_ANALYSIS_EXCEPTION = "exceptionanalysisflag";
    public static final String APP_ANALYSIS_EXCEPTION_TAG = "exceptionanalysistag";
    public static final String APP_SET_CHANNEL = "setchannelwithcodevalue";
    public static final String APP_SET_CHANNEL_APPKEY = "setchannelwithcodevalueandkey";
    public static final String APP_SET_CHANNEL_WITH_CODE = "setchannelwithcode";
    public static final String DEVICE_CUID = "cuid";
    public static final String DEVICE_ID = "device_id";
    public static final String LAST_SEND_TIME = "lastsendtime";
    public static final String ONLY_WIFI = "onlywifi";
    public static final String SEND_LOG_TYPE = "sendLogtype";
    public static final String TIME_INTERVAL = "timeinterval";
    static BasicStoreTools a = new BasicStoreTools();

    private BasicStoreTools() {
    }

    public static BasicStoreTools getInstance() {
        return a;
    }

    public String loadAppChannelWithPreference(Context context) {
        return getString(context, APP_SET_CHANNEL, null);
    }

    public String loadAppChannelWithPreferenceAndAppKey(Context context, String str) {
        return getString(context, "setchannelwithcodevalueandkey|" + str, null);
    }

    public String loadExceptionHeadTag(Context context) {
        return getString(context, APP_ANALYSIS_EXCEPTION_TAG, null);
    }

    public boolean loadExceptionTurn(Context context) {
        return getBoolean(context, APP_ANALYSIS_EXCEPTION, false);
    }

    public String loadGenerateDeviceCUID(Context context) {
        return getString(context, DEVICE_CUID, null);
    }

    public String loadGenerateDeviceId(Context context) {
        return getString(context, "device_id", null);
    }

    public long loadLastSendTime(Context context) {
        return getLong(context, LAST_SEND_TIME, 0);
    }

    public boolean loadOnlyWifiChannel(Context context) {
        return getBoolean(context, ONLY_WIFI, false);
    }

    public int loadSendStrategy(Context context) {
        return getInt(context, SEND_LOG_TYPE, 0);
    }

    public int loadSendStrategyTime(Context context) {
        return getInt(context, TIME_INTERVAL, 1);
    }

    public void setAppChannelWithPreference(Context context, String str) {
        putString(context, APP_SET_CHANNEL, str);
    }

    public void setAppChannelWithPreferenceAndAppKey(Context context, String str, String str2, boolean z) {
        putString(context, "setchannelwithcodevalueandkey|" + str2, str + "||" + str2 + "||" + z);
    }

    public void setExceptionHeadTag(Context context, String str) {
        putString(context, APP_ANALYSIS_EXCEPTION_TAG, str);
    }

    public void setExceptionTurn(Context context, boolean z) {
        putBoolean(context, APP_ANALYSIS_EXCEPTION, z);
    }

    public void setGenerateDeviceCUID(Context context, String str) {
        putString(context, DEVICE_CUID, str);
    }

    public void setGenerateDeviceId(Context context, String str) {
        putString(context, "device_id", str);
    }

    public void setLastSendTime(Context context, long j) {
        putLong(context, LAST_SEND_TIME, j);
    }

    public void setOnlyWifi(Context context, boolean z) {
        putBoolean(context, ONLY_WIFI, z);
    }

    public void setSendStrategy(Context context, int i) {
        putInt(context, SEND_LOG_TYPE, i);
    }

    public void setSendStrategyTime(Context context, int i) {
        putInt(context, TIME_INTERVAL, i);
    }
}
