package com.umeng.socialize;

import android.app.Dialog;
import com.baidu.mapapi.UIMsg.m_AppUI;

public class Config {
    public static String Descriptor = "com.umeng.share";
    public static String EntityKey = null;
    public static String EntityName = null;
    public static boolean IsToastTip = true;
    public static int LinkedInProfileScope = 0;
    public static int LinkedInShareCode = 0;
    public static boolean OpenEditor = true;
    public static String QQAPPNAME = "";
    public static int QQWITHQZONE = 0;
    public static String REDIRECT_URL = "http://sns.whalecloud.com";
    public static String SessionId = null;
    public static boolean ShareLocation = true;
    public static String UID = null;
    public static int UseCocos = 0;
    public static boolean WBBYQQ = true;
    private static String a = "";
    public static String appName = null;
    private static String b = "";
    public static int connectionTimeOut = m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT;
    public static Dialog dialog = null;
    public static boolean dialogSwitch = true;
    public static float imageSize = 3072.0f;
    public static boolean isIntentShareFB = false;
    public static boolean isLoadImgByCompress = true;
    public static boolean isloadUrl = false;
    public static final boolean mEncrypt = true;
    public static int readSocketTimeOut = m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT;
    public static boolean showShareBoardOnTop = false;

    public static String getAdapterSDKVersion() {
        return b;
    }

    public static String getAdapterSDK() {
        return a;
    }

    public static void setAdapterSDKInfo(String str, String str2) {
        a = str;
        b = str2;
    }
}
