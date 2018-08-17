package anet.channel;

import android.text.TextUtils;
import anet.channel.AccsSessionManager.Callback;
import anet.channel.entity.ENV;
import anet.channel.heartbeat.HeartbeatManager;
import anet.channel.heartbeat.IHeartbeatFactory;
import anet.channel.security.ISecurity;
import anet.channel.security.c;
import anet.channel.strategy.a;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public final class Config {
    public static final Config DEFAULT_CONFIG = new Builder().setTag("[default]").setAppkey("[default]").setEnv(ENV.ONLINE).setAccsSessionAutoRecreate(false).setAccsSessionCallback(AccsSessionManager.DISABLE_AUTO_CONNTION).setHeartbeatFactory(null).build();
    private static final String TAG = "awcn.Config";
    private static Map<String, Config> configMap = new HashMap();
    private String accsHost;
    private int accsPublicKey = -1;
    private boolean accsSessionAutoCreate = true;
    private Callback accsSessionCb = null;
    private String appkey;
    private ENV env = ENV.ONLINE;
    private IHeartbeatFactory heartbeatFactory;
    private ISecurity iSecurity;
    private String tag;
    private boolean unitEnable = true;

    /* compiled from: Taobao */
    public static class Builder {
        private String accsHost;
        private int accsPublicKey = -1;
        private boolean accsSessionAutoCreate = true;
        private Callback accsSessionCb;
        private String appSecret;
        private String appkey;
        private String authCode;
        private ENV env = ENV.ONLINE;
        private IHeartbeatFactory heartbeatFactory = HeartbeatManager.getHeartbeatFactory();
        private String tag;
        private boolean unitEnable = true;

        public Builder setTag(String str) {
            this.tag = str;
            return this;
        }

        public Builder setAppkey(String str) {
            this.appkey = str;
            return this;
        }

        public Builder setEnv(ENV env) {
            this.env = env;
            return this;
        }

        public Builder setAuthCode(String str) {
            this.authCode = str;
            return this;
        }

        public Builder setAppSecret(String str) {
            this.appSecret = str;
            return this;
        }

        public Builder setAccsHost(String str) {
            this.accsHost = str;
            HttpDispatcher.getInstance().addHosts(Arrays.asList(new String[]{str}));
            return this;
        }

        public Builder setAccsPublicKey(int i) {
            this.accsPublicKey = i;
            return this;
        }

        public Builder setAccsSessionCallback(Callback callback) {
            if (callback == null) {
                this.accsSessionCb = AccsSessionManager.DISABLE_AUTO_CONNTION;
            } else {
                this.accsSessionCb = callback;
            }
            return this;
        }

        public Builder setUnitEnable(boolean z) {
            this.unitEnable = z;
            return this;
        }

        public Builder setAccsSessionAutoRecreate(boolean z) {
            this.accsSessionAutoCreate = z;
            return this;
        }

        public Builder setHeartbeatFactory(IHeartbeatFactory iHeartbeatFactory) {
            this.heartbeatFactory = iHeartbeatFactory;
            return this;
        }

        public Config build() {
            if (TextUtils.isEmpty(this.appkey)) {
                throw new RuntimeException("appkey can not be null or empty!");
            }
            for (Config config : Config.configMap.values()) {
                if (config.env == this.env && config.appkey.equals(this.appkey)) {
                    ALog.w(Config.TAG, "duplicated config exist!", null, "appkey", this.appkey, "env", this.env);
                    if (!TextUtils.isEmpty(this.tag)) {
                        synchronized (Config.configMap) {
                            Config.configMap.put(this.tag, config);
                        }
                    }
                    return config;
                }
            }
            Config config2 = new Config();
            config2.appkey = this.appkey;
            config2.env = this.env;
            config2.accsSessionCb = this.accsSessionCb;
            config2.accsPublicKey = this.accsPublicKey;
            config2.unitEnable = this.unitEnable;
            config2.accsSessionAutoCreate = this.accsSessionAutoCreate;
            config2.heartbeatFactory = this.heartbeatFactory;
            if (TextUtils.isEmpty(this.tag)) {
                config2.tag = StringUtils.concatString(this.appkey, "$", this.env.toString());
            } else {
                config2.tag = this.tag;
            }
            if (TextUtils.isEmpty(this.appSecret)) {
                config2.iSecurity = c.a().createSecurity(this.authCode);
            } else {
                config2.iSecurity = c.a().createNonSecurity(this.appSecret);
            }
            if (TextUtils.isEmpty(this.accsHost)) {
                config2.accsHost = a.a(this.env);
            } else {
                config2.accsHost = this.accsHost;
            }
            synchronized (Config.configMap) {
                Config.configMap.put(config2.tag, config2);
            }
            return config2;
        }
    }

    protected Config() {
    }

    public static Config getConfigByTag(String str) {
        Config config;
        synchronized (configMap) {
            config = (Config) configMap.get(str);
        }
        return config;
    }

    public static Config getConfig(String str, ENV env) {
        synchronized (configMap) {
            for (Config config : configMap.values()) {
                if (config.env == env && config.appkey.equals(str)) {
                    return config;
                }
            }
            return null;
        }
    }

    public String getTag() {
        return this.tag;
    }

    public String getAppkey() {
        return this.appkey;
    }

    public ENV getEnv() {
        return this.env;
    }

    public ISecurity getSecurity() {
        return this.iSecurity;
    }

    public String getAccsHost() {
        return this.accsHost;
    }

    public int getAccsPublicKey() {
        return this.accsPublicKey;
    }

    public Callback getAccsSessionCb() {
        return this.accsSessionCb;
    }

    public boolean isUnitEnable() {
        return this.unitEnable;
    }

    public boolean isAccsSessionAutoCreate() {
        return this.accsSessionAutoCreate;
    }

    public IHeartbeatFactory getHeartbeatFactory() {
        return this.heartbeatFactory;
    }

    public String toString() {
        return this.tag;
    }
}
