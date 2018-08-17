package com.umeng.socialize.utils;

import android.content.Context;
import android.os.Build;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.AesHelper;

public class URLBuilder {
    private String idmd5 = null;
    private String imei = null;
    private String mAppkey = null;
    private String mEntityKey = null;
    private String mHost = null;
    private String mOpId = null;
    private String mPath = null;
    private String mPlatfrom = null;
    private String mReqType = "0";
    private String mSessionId = null;
    private String mUID = null;
    private String mac = null;
    private String model = null;
    private String network = null;
    private String os = null;
    private String protoversion = null;
    private String sdkversion = null;
    private String ts = null;

    public URLBuilder(Context context) {
        this.imei = DeviceConfig.getDeviceId(context);
        if (this.imei != null) {
            this.idmd5 = AesHelper.md5(this.imei);
        }
        this.mac = DeviceConfig.getMac(context);
        this.network = DeviceConfig.getNetworkAccessMode(context)[0];
        this.model = Build.MODEL;
        this.sdkversion = "5.2.0";
        this.os = "Android";
        this.ts = String.valueOf(System.currentTimeMillis());
        this.protoversion = SocializeConstants.PROTOCOL_VERSON;
    }

    public URLBuilder setHost(String str) {
        this.mHost = str;
        return this;
    }

    public URLBuilder setPath(String str) {
        this.mPath = str;
        return this;
    }

    public URLBuilder setAppkey(String str) {
        this.mAppkey = str;
        return this;
    }

    public URLBuilder setEntityKey(String str) {
        this.mEntityKey = str;
        return this;
    }

    public URLBuilder withMedia(SHARE_MEDIA share_media) {
        this.mPlatfrom = share_media.toString();
        return this;
    }

    public URLBuilder withOpId(String str) {
        this.mOpId = str;
        return this;
    }

    public URLBuilder withSessionId(String str) {
        this.mSessionId = str;
        return this;
    }

    public URLBuilder withUID(String str) {
        this.mUID = str;
        return this;
    }

    public URLBuilder withQuery(String str, String str2) {
        return this;
    }

    public String to() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mHost);
        stringBuilder.append(this.mPath);
        stringBuilder.append(this.mAppkey);
        stringBuilder.append("/");
        stringBuilder.append(this.mEntityKey);
        stringBuilder.append("/?");
        stringBuilder.append(buildParams());
        return stringBuilder.toString();
    }

    public String toEncript() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mHost);
        stringBuilder.append(this.mPath);
        stringBuilder.append(this.mAppkey);
        stringBuilder.append("/");
        stringBuilder.append(this.mEntityKey);
        stringBuilder.append("/?");
        String buildParams = buildParams();
        Log.i("base url: " + stringBuilder.toString());
        Log.i("params: " + buildParams);
        AesHelper.setPassword(this.mAppkey);
        try {
            String encryptNoPadding = AesHelper.encryptNoPadding(buildParams, "UTF-8");
            stringBuilder.append("ud_get=");
            stringBuilder.append(encryptNoPadding);
        } catch (Exception e) {
            Log.w("fail to encrypt query string");
            stringBuilder.append(buildParams);
        }
        return stringBuilder.toString();
    }

    private String buildParams() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("via=").append(this.mPlatfrom.toLowerCase());
        stringBuilder.append("&opid=").append(this.mOpId);
        stringBuilder.append("&ak=").append(this.mAppkey);
        stringBuilder.append("&pcv=").append(this.protoversion);
        stringBuilder.append("&tp=").append(this.mReqType);
        if (this.imei != null) {
            stringBuilder.append("&imei=").append(this.imei);
        }
        if (this.idmd5 != null) {
            stringBuilder.append("&md5imei=").append(this.idmd5);
        }
        if (this.mac != null) {
            stringBuilder.append("&mac=").append(this.mac);
        }
        if (this.network != null) {
            stringBuilder.append("&en=").append(this.network);
        }
        if (this.model != null) {
            stringBuilder.append("&de=").append(this.model);
        }
        if (this.sdkversion != null) {
            stringBuilder.append("&sdkv=").append(this.sdkversion);
        }
        if (this.os != null) {
            stringBuilder.append("&os=").append(this.os);
        }
        if (this.ts != null) {
            stringBuilder.append("&dt=").append(this.ts);
        }
        if (this.mUID != null) {
            stringBuilder.append("&uid=").append(this.mUID);
        }
        if (this.mEntityKey != null) {
            stringBuilder.append("&ek=").append(this.mEntityKey);
        }
        if (this.mSessionId != null) {
            stringBuilder.append("&sid=").append(this.mSessionId);
        }
        return stringBuilder.toString();
    }
}
