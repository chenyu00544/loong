package com.taobao.accs;

import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
public class AccsClientConfig {
    public static final String[] DEFAULT_CENTER_HOSTS = new String[]{"acs.m.taobao.com", "acs.wapa.taobao.com", "acs.waptest.taobao.com"};
    private static String[] DEFAULT_CHANNEL_HOSTS = new String[]{Constants.SERVICE_HOST, "acs.wapa.taobao.com", "acs.waptest.taobao.com"};
    public static final int SECURITY_OFF = 2;
    public static final int SECURITY_OPEN = 1;
    public static final int SECURITY_TAOBAO = 0;
    private static Map<String, AccsClientConfig> mDebugConfigs = new ConcurrentHashMap(1);
    @ENV
    public static int mEnv = 0;
    private static Map<String, AccsClientConfig> mPreviewConfigs = new ConcurrentHashMap(1);
    private static Map<String, AccsClientConfig> mReleaseConfigs = new ConcurrentHashMap(1);
    private String mAppKey;
    private String mAppSecret;
    private String mAuthCode;
    private boolean mAutoUnit;
    private String mChannelHost;
    private int mChannelPubKey;
    private int mConfigEnv;
    private String mInappHost;
    private int mInappPubKey;
    private boolean mKeepalive;
    private int mSecurity;
    private String mTag;

    /* compiled from: Taobao */
    public static class Builder {
        private String mAppKey;
        private String mAppSecret;
        private String mAuthCode;
        private boolean mAutoUnit = true;
        private String mChannelHost;
        private int mChannelPubKey = -1;
        private int mConfigEnv = -1;
        private String mInappHost;
        private int mInappPubKey = -1;
        private boolean mKeepalive = true;
        private String mTag;

        public Builder setAppKey(String str) {
            this.mAppKey = str;
            return this;
        }

        public Builder setAppSecret(String str) {
            this.mAppSecret = str;
            return this;
        }

        public Builder setInappHost(String str) {
            this.mInappHost = str;
            return this;
        }

        public Builder setChannelHost(String str) {
            this.mChannelHost = str;
            return this;
        }

        public Builder setAutoCode(String str) {
            this.mAuthCode = str;
            return this;
        }

        public Builder setInappPubKey(int i) {
            this.mInappPubKey = i;
            return this;
        }

        public Builder setChannelPubKey(int i) {
            this.mChannelPubKey = i;
            return this;
        }

        public Builder setKeepAlive(boolean z) {
            this.mKeepalive = z;
            return this;
        }

        public Builder setAutoUnit(boolean z) {
            this.mAutoUnit = z;
            return this;
        }

        public Builder setConfigEnv(@ENV int i) {
            this.mConfigEnv = i;
            return this;
        }

        public Builder setTag(String str) {
            this.mTag = str;
            return this;
        }

        public AccsClientConfig build() throws AccsException {
            if (TextUtils.isEmpty(this.mAppKey)) {
                throw new AccsException("appkey null");
            }
            Map access$1300;
            AccsClientConfig accsClientConfig = new AccsClientConfig();
            accsClientConfig.mAppKey = this.mAppKey;
            accsClientConfig.mAppSecret = this.mAppSecret;
            accsClientConfig.mAuthCode = this.mAuthCode;
            accsClientConfig.mKeepalive = this.mKeepalive;
            accsClientConfig.mAutoUnit = this.mAutoUnit;
            accsClientConfig.mInappPubKey = this.mInappPubKey;
            accsClientConfig.mChannelPubKey = this.mChannelPubKey;
            accsClientConfig.mInappHost = this.mInappHost;
            accsClientConfig.mChannelHost = this.mChannelHost;
            accsClientConfig.mTag = this.mTag;
            accsClientConfig.mConfigEnv = this.mConfigEnv;
            if (accsClientConfig.mConfigEnv < 0) {
                accsClientConfig.mConfigEnv = AccsClientConfig.mEnv;
            }
            if (TextUtils.isEmpty(accsClientConfig.mAppSecret)) {
                accsClientConfig.mSecurity = 0;
            } else {
                accsClientConfig.mSecurity = 2;
            }
            if (TextUtils.isEmpty(accsClientConfig.mInappHost)) {
                accsClientConfig.mInappHost = AccsClientConfig.DEFAULT_CENTER_HOSTS[AccsClientConfig.mEnv];
            }
            if (TextUtils.isEmpty(accsClientConfig.mChannelHost)) {
                accsClientConfig.mChannelHost = AccsClientConfig.DEFAULT_CHANNEL_HOSTS[AccsClientConfig.mEnv];
            }
            if (TextUtils.isEmpty(accsClientConfig.mTag)) {
                accsClientConfig.mTag = accsClientConfig.mAppKey;
            }
            switch (accsClientConfig.mConfigEnv) {
                case 1:
                    access$1300 = AccsClientConfig.mPreviewConfigs;
                    break;
                case 2:
                    access$1300 = AccsClientConfig.mDebugConfigs;
                    break;
                default:
                    access$1300 = AccsClientConfig.mReleaseConfigs;
                    break;
            }
            access$1300.put(accsClientConfig.getTag(), accsClientConfig);
            ALog.i("AccsClientConfig", "build", "config", accsClientConfig.toString());
            return accsClientConfig;
        }
    }

    @Retention(RetentionPolicy.CLASS)
    /* compiled from: Taobao */
    public @interface ENV {
    }

    @Retention(RetentionPolicy.CLASS)
    /* compiled from: Taobao */
    public @interface SECURITY_TYPE {
    }

    protected AccsClientConfig() {
    }

    public static AccsClientConfig getConfig(String str) {
        Map map;
        switch (mEnv) {
            case 1:
                map = mPreviewConfigs;
                break;
            case 2:
                map = mDebugConfigs;
                break;
            default:
                map = mReleaseConfigs;
                break;
        }
        for (AccsClientConfig accsClientConfig : r0.values()) {
            if (accsClientConfig.mAppKey.equals(str) && accsClientConfig.mConfigEnv == mEnv) {
                return accsClientConfig;
            }
        }
        ALog.e("AccsClientConfig", "getConfig null!", new Object[0]);
        return null;
    }

    public static AccsClientConfig getConfigByTag(String str) {
        Map map;
        switch (mEnv) {
            case 1:
                map = mPreviewConfigs;
                break;
            case 2:
                map = mDebugConfigs;
                break;
            default:
                map = mReleaseConfigs;
                break;
        }
        AccsClientConfig accsClientConfig = (AccsClientConfig) map.get(str);
        if (accsClientConfig == null) {
            ALog.e("AccsClientConfig", "getConfig null!", new Object[0]);
        }
        return accsClientConfig;
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public String getAppSecret() {
        return this.mAppSecret;
    }

    public String getInappHost() {
        return this.mInappHost;
    }

    public String getChannelHost() {
        return this.mChannelHost;
    }

    public int getSecurity() {
        return this.mSecurity;
    }

    public String getAuthCode() {
        return this.mAuthCode;
    }

    public int getInappPubKey() {
        return this.mInappPubKey;
    }

    public int getChannelPubKey() {
        return this.mChannelPubKey;
    }

    public boolean isKeepalive() {
        return this.mKeepalive;
    }

    public boolean isAutoUnit() {
        return this.mAutoUnit;
    }

    public String getTag() {
        return this.mTag;
    }

    public int getConfigEnv() {
        return this.mConfigEnv;
    }

    public String toString() {
        return "AccsClientConfig{" + "mAppKey=" + this.mAppKey + ", mAppSecret=" + this.mAppSecret + ", mInappHost=" + this.mInappHost + ", mChannelHost=" + this.mChannelHost + ", mSecurity=" + this.mSecurity + ", mAuthCode=" + this.mAuthCode + ", mInappPubKey=" + this.mInappPubKey + ", mChannelPubKey=" + this.mChannelPubKey + ", mKeepalive=" + this.mKeepalive + ", mAutoUnit=" + this.mAutoUnit + ", mConfigEnv=" + this.mConfigEnv + ", mTag=" + this.mTag;
    }
}
