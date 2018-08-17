package com.taobao.accs;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.SessionCenter;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.AccsClientConfig.Builder;
import com.taobao.accs.AccsClientConfig.ENV;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.j;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
public class ACCSClient {
    private static final String TAG = "ACCSClient";
    public static Map<String, ACCSClient> mACCSClients = new ConcurrentHashMap(2);
    private static Context mContext;
    protected IACCSManager mAccsManager;
    private AccsClientConfig mConfig;

    public ACCSClient(Context context, AccsClientConfig accsClientConfig) {
        this.mConfig = accsClientConfig;
        this.mAccsManager = ACCSManager.getAccsInstance(mContext, accsClientConfig.getAppKey());
    }

    public static synchronized String init(Context context, String str) throws AccsException {
        String init;
        synchronized (ACCSClient.class) {
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    init = init(context, new Builder().setAppKey(str).build());
                }
            }
            throw new AccsException("params error");
        }
        return init;
    }

    public static synchronized String init(Context context, AccsClientConfig accsClientConfig) throws AccsException {
        String tag;
        synchronized (ACCSClient.class) {
            if (context == null || accsClientConfig == null) {
                throw new AccsException("params error");
            }
            mContext = context.getApplicationContext();
            ALog.i(TAG, "init", "config", accsClientConfig.toString());
            tag = accsClientConfig.getTag();
        }
        return tag;
    }

    public static synchronized ACCSClient getAccsClient(String str) throws AccsException {
        ACCSClient aCCSClient;
        synchronized (ACCSClient.class) {
            if (TextUtils.isEmpty(str)) {
                throw new AccsException("tag null");
            }
            AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(str);
            if (configByTag == null) {
                ALog.e(TAG, "configTag not exist, please init first!!", new Object[0]);
                throw new AccsException("tag not exist");
            }
            ALog.i(TAG, "getAccsClient", "configTag", str);
            aCCSClient = (ACCSClient) mACCSClients.get(str);
            if (aCCSClient == null) {
                ALog.i(TAG, "getAccsClient create client", "config", configByTag.toString());
                aCCSClient = new ACCSClient(mContext, configByTag);
                mACCSClients.put(str, aCCSClient);
            } else if (configByTag.equals(aCCSClient.mConfig)) {
                ALog.i(TAG, "getAccsClient exists", new Object[0]);
            } else {
                ALog.i(TAG, "getAccsClient update config", "old config", aCCSClient.mConfig.getTag(), "new config", configByTag.getTag());
                aCCSClient.mConfig = configByTag;
                aCCSClient.mAccsManager = ACCSManager.getAccsInstance(mContext, configByTag.getAppKey());
            }
        }
        return aCCSClient;
    }

    public static synchronized void setEnvironment(Context context, @ENV int i) {
        synchronized (ACCSClient.class) {
            if (i < 0 || i > 2) {
                try {
                    ALog.e(TAG, "env error", "env", Integer.valueOf(i));
                    i = 0;
                } catch (Throwable e) {
                    ALog.e(TAG, "setEnvironment update client", e, new Object[0]);
                } catch (Throwable e2) {
                    try {
                        ALog.e(TAG, "setEnvironment", e2, new Object[0]);
                        j.a(context, i);
                    } catch (Throwable th) {
                        j.a(context, i);
                    }
                }
            }
            int i2 = AccsClientConfig.mEnv;
            AccsClientConfig.mEnv = i;
            if (i2 != i) {
                ALog.i(TAG, "setEnvironment:" + i, new Object[0]);
                j.b(context);
                j.d(context);
                j.c(context);
                if (i == 2) {
                    SessionCenter.switchEnvironment(anet.channel.entity.ENV.TEST);
                } else if (i == 1) {
                    SessionCenter.switchEnvironment(anet.channel.entity.ENV.PREPARE);
                }
                for (Entry key : mACCSClients.entrySet()) {
                    getAccsClient((String) key.getKey());
                }
            }
            j.a(context, i);
        }
        return;
    }

    public void bindApp(String str, IAppReceiver iAppReceiver) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "bindApp mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.bindApp(mContext, this.mConfig.getAppKey(), this.mConfig.getAppSecret(), str, iAppReceiver);
        }
    }

    public void bindUser(String str) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "bindUser mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.bindUser(mContext, str);
        }
    }

    public void bindUser(String str, boolean z) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "bindUser mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.bindUser(mContext, str, z);
        }
    }

    public void unbindUser() {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "unbindUser mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.unbindUser(mContext);
        }
    }

    public void bindService(String str) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "bindService mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.bindService(mContext, str);
        }
    }

    public void unbindService(String str) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "unbindService mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.unbindService(mContext, str);
        }
    }

    public String sendData(AccsRequest accsRequest) {
        if (this.mAccsManager != null) {
            return this.mAccsManager.sendData(mContext, accsRequest);
        }
        ALog.e(TAG, "sendData mAccsManager null", new Object[0]);
        return null;
    }

    public String sendRequest(AccsRequest accsRequest) {
        if (this.mAccsManager != null) {
            return this.mAccsManager.sendRequest(mContext, accsRequest);
        }
        ALog.e(TAG, "sendRequest mAccsManager null", new Object[0]);
        return null;
    }

    public String sendPushResponse(AccsRequest accsRequest, ExtraInfo extraInfo) {
        if (this.mAccsManager != null) {
            return this.mAccsManager.sendPushResponse(mContext, accsRequest, extraInfo);
        }
        ALog.e(TAG, "sendPushResponse mAccsManager null", new Object[0]);
        return null;
    }

    public boolean isNetworkReachable() {
        if (this.mAccsManager != null) {
            return this.mAccsManager.isNetworkReachable(mContext);
        }
        ALog.e(TAG, "isNetworkReachable mAccsManager null", new Object[0]);
        return false;
    }

    public void forceDisableService() {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "forceDisableService mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.forceDisableService(mContext);
        }
    }

    public void forceEnableService() {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "forceEnableService mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.forceEnableService(mContext);
        }
    }

    public void startInAppConnection(String str, IAppReceiver iAppReceiver) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "startInAppConnection mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.startInAppConnection(mContext, this.mConfig.getAppKey(), this.mConfig.getAppSecret(), str, iAppReceiver);
        }
    }

    public void setLoginInfo(ILoginInfo iLoginInfo) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "setLoginInfo mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.setLoginInfo(mContext, iLoginInfo);
        }
    }

    public void clearLoginInfo() {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "clearLoginInfo mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.clearLoginInfo(mContext);
        }
    }

    public boolean cancel(String str) {
        if (this.mAccsManager != null) {
            return this.mAccsManager.cancel(mContext, str);
        }
        ALog.e(TAG, "cancel mAccsManager null", new Object[0]);
        return false;
    }

    public boolean isChannelError(int i) {
        if (this.mAccsManager != null) {
            return this.mAccsManager.isChannelError(i);
        }
        ALog.e(TAG, "isChannelError mAccsManager null", new Object[0]);
        return true;
    }

    public Map<String, Boolean> getChannelState() throws Exception {
        if (this.mAccsManager != null) {
            return this.mAccsManager.getChannelState();
        }
        ALog.e(TAG, "getChannelState mAccsManager null", new Object[0]);
        return null;
    }

    public Map<String, Boolean> forceReConnectChannel() throws Exception {
        if (this.mAccsManager != null) {
            return this.mAccsManager.forceReConnectChannel();
        }
        ALog.e(TAG, "forceReConnectChannel mAccsManager null", new Object[0]);
        return null;
    }

    public String getUserUnit() {
        if (this.mAccsManager != null) {
            return this.mAccsManager.getUserUnit();
        }
        ALog.e(TAG, "getUserUnit mAccsManager null", new Object[0]);
        return null;
    }

    public void registerSerivce(String str, String str2) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "registerSerivce mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.registerSerivce(mContext, str, str2);
        }
    }

    public void unRegisterSerivce(String str) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "unRegisterSerivce mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.unRegisterSerivce(mContext, str);
        }
    }

    public void registerDataListener(String str, AccsAbstractDataListener accsAbstractDataListener) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "registerDataListener mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.registerDataListener(mContext, str, accsAbstractDataListener);
        }
    }

    public void unRegisterDataListener(String str) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "unRegisterDataListener mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.unRegisterDataListener(mContext, str);
        }
    }

    public void sendBusinessAck(String str, String str2, String str3, short s, String str4, Map<ExtHeaderType, String> map) {
        if (this.mAccsManager == null) {
            ALog.e(TAG, "sendBusinessAck mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.sendBusinessAck(str, str2, str3, s, str4, map);
        }
    }
}
