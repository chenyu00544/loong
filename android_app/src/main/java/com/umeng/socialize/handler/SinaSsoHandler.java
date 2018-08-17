package com.umeng.socialize.handler;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.baidu.mapapi.SDKInitializer;
import com.sina.sso.RemoteSSO;
import com.sina.sso.RemoteSSO.Stub;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.PlatformConfig.SinaWeibo;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UMLocation;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.ShareFriendsRequest;
import com.umeng.socialize.net.ShareFriendsResponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.view.UMFriendListener;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SinaSsoHandler extends UMAPIShareHandler {
    private static final int REQUEST_CODE = 5659;
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write";
    private static final String TAG = "SinaSsoHandler";
    private static final String waiturl = "sina2/main?uid";
    private SinaWeibo config = null;
    private AuthInfo mAuthInfo;
    private AuthListener mAuthListener;
    private Context mContext;
    private SsoHandler mSsoHandler;
    private IWeiboShareAPI mWeiboShareAPI;
    private UMShareListener shareListener;
    private SinaPreferences sinaPreferences;

    class AuthListener implements WeiboAuthListener {
        private UMAuthListener mListener = null;

        AuthListener(UMAuthListener uMAuthListener) {
            this.mListener = uMAuthListener;
        }

        public void onComplete(Bundle bundle) {
            SinaSsoHandler.this.sinaPreferences.setAuthData(bundle).commit();
            SinaSsoHandler.this.uploadAuthData(bundle);
            if (this.mListener != null) {
                this.mListener.onComplete(SHARE_MEDIA.SINA, 0, SinaSsoHandler.this.bundleTomap(bundle));
            }
        }

        public void onCancel() {
            if (this.mListener != null) {
                this.mListener.onCancel(SHARE_MEDIA.SINA, 0);
            }
        }

        public void onWeiboException(WeiboException weiboException) {
            if (this.mListener != null) {
                this.mListener.onError(SHARE_MEDIA.SINA, 0, new Throwable(weiboException));
            }
        }
    }

    class AuthListenerWrapper implements UMAuthListener {
        private UMAuthListener mListener = null;

        AuthListenerWrapper(UMAuthListener uMAuthListener) {
            this.mListener = uMAuthListener;
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            if (this.mListener != null) {
                this.mListener.onError(share_media, i, th);
            }
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
            if (this.mListener != null) {
                this.mListener.onCancel(share_media, i);
            }
        }
    }

    static class SinaConnection implements ServiceConnection {
        private static final String AUTH_SERVICE_NAME = "com.sina.weibo.business.RemoteSSOService";
        private static final String REDIRECT_URL = "http://sns.whalecloud.com";
        private static final String WEIBO_SIGNATURE = "30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4";
        public String ActivityName = null;
        public boolean IsConnected = false;
        public boolean IsOK = false;
        public String PackageName = null;
        private WeakReference<Activity> mActivity = null;
        private String mAppId = null;
        private String[] mPermissions = null;

        public SinaConnection(Activity activity, String str) {
            this.mActivity = new WeakReference(activity);
            this.mAppId = str;
        }

        public void setPermissions(String[] strArr) {
            this.mPermissions = strArr;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.IsConnected = false;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.IsConnected = true;
            RemoteSSO asInterface = Stub.asInterface(iBinder);
            try {
                this.PackageName = asInterface.getPackageName();
                this.ActivityName = asInterface.getActivityName();
                this.IsOK = startSingleSignOn((Activity) this.mActivity.get(), 5659);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private boolean startSingleSignOn(Activity activity, int i) {
            boolean z = true;
            Intent intent = new Intent();
            intent.setClassName(this.PackageName, this.ActivityName);
            intent.putExtra("appKey", this.mAppId);
            intent.putExtra(WBConstants.SSO_REDIRECT_URL, REDIRECT_URL);
            if (this.mPermissions != null && this.mPermissions.length > 0) {
                intent.putExtra("scope", TextUtils.join(",", this.mPermissions));
            }
            if (!validateAppSignatureForIntent(activity, intent)) {
                return false;
            }
            try {
                activity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                z = false;
            }
            if (this.IsConnected) {
                this.IsConnected = isServiceAlive(activity);
                if (this.IsConnected) {
                    activity.getApplication().unbindService(this);
                }
            }
            return z;
        }

        private boolean validateAppSignatureForIntent(Context context, Intent intent) {
            PackageManager packageManager = context.getPackageManager();
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            if (resolveActivity == null) {
                return false;
            }
            try {
                for (Signature toCharsString : packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures) {
                    if (WEIBO_SIGNATURE.equals(toCharsString.toCharsString())) {
                        return true;
                    }
                }
                return false;
            } catch (NameNotFoundException e) {
                return false;
            }
        }

        private boolean isServiceAlive(Context context) {
            List runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(100);
            if (runningServices.size() <= 0) {
                return false;
            }
            for (int i = 0; i < runningServices.size(); i++) {
                if (((RunningServiceInfo) runningServices.get(i)).service.getClassName().equals(AUTH_SERVICE_NAME)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        this.mContext = context.getApplicationContext();
        this.config = (SinaWeibo) platform;
        this.sinaPreferences = new SinaPreferences(this.mContext, "sina");
        this.mAuthInfo = new AuthInfo(context, ((SinaWeibo) platform).appKey, Config.REDIRECT_URL, SCOPE);
        if (context instanceof Activity) {
            this.mSsoHandler = new SsoHandler((Activity) context, this.mAuthInfo);
            this.mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context, this.config.appKey);
            this.mWeiboShareAPI.registerApp();
            Log.d("sina", "onCreate");
        }
    }

    public IWeiboShareAPI getmWeiboShareAPI() {
        return this.mWeiboShareAPI;
    }

    public boolean isAuthorized() {
        return this.sinaPreferences.isAuthorized();
    }

    public boolean isInstall(Context context) {
        return isClientInstalled();
    }

    public boolean isAuthorize(Context context) {
        return isAuthorized();
    }

    public SHARE_MEDIA getPlatform() {
        return SHARE_MEDIA.SINA;
    }

    public String getUID() {
        return this.sinaPreferences.getUID();
    }

    public boolean isClientInstalled() {
        return this.mWeiboShareAPI.isWeiboAppInstalled();
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        this.mAuthListener = new AuthListener(uMAuthListener);
        this.mSsoHandler.authorize(this.mAuthListener);
    }

    protected void requestAsync(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener, String str3) {
        if (str3 == null || TextUtils.isEmpty(str) || weiboParameters == null || TextUtils.isEmpty(str2) || requestListener == null) {
            LogUtil.e(TAG, "Argument error!");
            return;
        }
        weiboParameters.put("access_token", str3);
        new AsyncWeiboRunner(this.mContext).requestAsync(str, weiboParameters, str2, requestListener);
    }

    public void getPlatformInfo(final Activity activity, final UMAuthListener uMAuthListener) {
        if (this.sinaPreferences.getUID() != null) {
            WeiboParameters weiboParameters = new WeiboParameters(this.config.appKey);
            weiboParameters.put("uid", this.sinaPreferences.getUID());
            requestAsync("https://api.weibo.com/2/users/show.json", weiboParameters, "GET", new RequestListener() {
                public void onComplete(String str) {
                    Map hashMap = new HashMap();
                    hashMap.put("result", str);
                    uMAuthListener.onComplete(SHARE_MEDIA.SINA, 2, hashMap);
                }

                public void onWeiboException(WeiboException weiboException) {
                    uMAuthListener.onError(SHARE_MEDIA.SINA, 2, new Throwable(weiboException));
                }
            }, this.sinaPreferences.getmAccessToken());
            return;
        }
        authorize(activity, new UMAuthListener() {

            class SinaSsoHandler_2_1 implements Runnable {
                SinaSsoHandler_2_1() {
                }

                public void run() {
                    SinaSsoHandler.this.getPlatformInfo(activity, uMAuthListener);
                }
            }

            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                QueuedWork.runInBack(new SinaSsoHandler_2_1());
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                Log.e("xxxx 授权失败");
            }

            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.e("xxxx 授权取消");
            }
        });
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        this.sinaPreferences.delete();
        uMAuthListener.onComplete(SHARE_MEDIA.SINA, 1, null);
    }

    public boolean share(Activity activity, ShareContent shareContent, final UMShareListener uMShareListener) {
        if (activity == null) {
            Log.d("UMError", "Sina share activity is null");
            return false;
        }
        SinaShareContent sinaShareContent = new SinaShareContent(shareContent);
        sinaShareContent.SetContext(activity);
        BaseRequest sendMultiMessageToWeiboRequest = new SendMultiMessageToWeiboRequest();
        sendMultiMessageToWeiboRequest.transaction = String.valueOf(System.currentTimeMillis());
        sendMultiMessageToWeiboRequest.multiMessage = sinaShareContent.getMessage();
        AuthInfo authInfo = new AuthInfo(activity, this.config.appKey, Config.REDIRECT_URL, SCOPE);
        String str = "";
        if (this.sinaPreferences != null) {
            str = this.sinaPreferences.getmAccessToken();
        }
        this.shareListener = uMShareListener;
        this.mWeiboShareAPI.sendRequest(activity, sendMultiMessageToWeiboRequest, authInfo, str, new WeiboAuthListener() {
            public void onWeiboException(WeiboException weiboException) {
                Log.d("sina_share", "weibo share exception");
                if (uMShareListener != null) {
                    uMShareListener.onError(SHARE_MEDIA.SINA, new Throwable(weiboException));
                }
            }

            public void onComplete(Bundle bundle) {
                SinaSsoHandler.this.uploadAuthData(bundle);
                if (uMShareListener != null) {
                    uMShareListener.onResult(SHARE_MEDIA.SINA);
                }
                SinaSsoHandler.this.sinaPreferences.setAuthData(bundle).commit();
            }

            public void onCancel() {
                Log.d("sina_share", "weibo share cancel");
                if (uMShareListener != null) {
                    uMShareListener.onCancel(SHARE_MEDIA.SINA);
                }
            }
        });
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mSsoHandler != null) {
            this.mSsoHandler.authorizeCallBack(i, i2, intent);
        }
        this.mSsoHandler = null;
    }

    public boolean isSupportAuth() {
        return true;
    }

    public int getRequestCode() {
        return 5659;
    }

    public void setScope(String[] strArr) {
    }

    private boolean bindRemoteSSOService(Activity activity, String str) {
        ServiceConnection sinaConnection = new SinaConnection(activity, str);
        Context applicationContext = activity.getApplicationContext();
        Intent intent = new Intent("com.sina.weibo.remotessoservice");
        List queryIntentServices = activity.getPackageManager().queryIntentServices(intent, 0);
        ComponentName componentName = null;
        if (queryIntentServices != null && queryIntentServices.size() > 0) {
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentServices.get(0);
            componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        }
        intent.setComponent(componentName);
        return applicationContext.bindService(intent, sinaConnection, 1);
    }

    public void authorizeCallBack(int i, int i2, Intent intent) {
        if (i == 5659) {
            SocializeException socializeException;
            if (i2 == -1) {
                String stringExtra = intent.getStringExtra("error");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("error_type");
                }
                if (stringExtra == null) {
                    this.mAuthListener.onComplete(intent.getExtras());
                } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
                    Log.d("Weibo-authorize", "Login canceled by user.");
                    this.mAuthListener.onCancel();
                } else {
                    String stringExtra2 = intent.getStringExtra("error_description");
                    if (stringExtra2 != null) {
                        stringExtra = stringExtra + ":" + stringExtra2;
                    }
                    Log.d("Weibo-authorize", "Login failed: " + stringExtra);
                    socializeException = new SocializeException(i2, stringExtra);
                }
            } else if (i2 != 0) {
            } else {
                if (intent != null) {
                    Log.d("Weibo-authorize", "Login failed: " + intent.getStringExtra("error"));
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(intent.getStringExtra("error"));
                    stringBuilder.append(" : ");
                    stringBuilder.append(intent.getStringExtra("failing_url"));
                    socializeException = new SocializeException(intent.getIntExtra(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, -1), stringBuilder.toString());
                    return;
                }
                Log.d("Weibo-authorize", "Login canceled by user.");
                this.mAuthListener.onCancel();
            }
        }
    }

    public Bundle getEditable(ShareContent shareContent) {
        Bundle bundle = new Bundle();
        bundle.putString(ShareActivity.KEY_PLATFORM, SHARE_MEDIA.SINA.toString());
        bundle.putString("title", "分享到新浪微博");
        bundle.putString(ShareActivity.KEY_TEXT, shareContent.mText);
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMImage)) {
            File asFileImage = ((UMImage) shareContent.mMedia).asFileImage();
            if (asFileImage != null) {
                bundle.putString(ShareActivity.KEY_PIC, asFileImage.getAbsolutePath());
            }
        }
        bundle.putBoolean(ShareActivity.KEY_AT, true);
        bundle.putBoolean("location", true);
        if (shareContent.mFollow == null) {
            bundle.putBoolean(ShareActivity.KEY_FOLLOW, false);
        } else if (this.sinaPreferences.Isfollow()) {
            bundle.putBoolean(ShareActivity.KEY_FOLLOW, false);
        } else {
            bundle.putBoolean(ShareActivity.KEY_FOLLOW, true);
        }
        return bundle;
    }

    protected void saveFollow() {
        super.saveFollow();
        this.sinaPreferences.setIsfollow(true);
    }

    public ShareContent getResult(ShareContent shareContent, Bundle bundle) {
        shareContent.mText = bundle.getString(ShareActivity.KEY_TEXT);
        if (!bundle.getBoolean(ShareActivity.KEY_FOLLOW)) {
            shareContent.mFollow = null;
        }
        if (bundle.getString(ShareActivity.KEY_PIC) == null && (shareContent.mMedia instanceof UMImage)) {
            shareContent.mMedia = null;
        }
        if (bundle.getSerializable("location") != null) {
            shareContent.mLocation = (UMLocation) bundle.getSerializable("location");
        }
        return shareContent;
    }

    public void getfriend(final Activity activity, final UMFriendListener uMFriendListener) {
        if (activity == null) {
            Log.d("UMError", "Sina getFriend activity is null");
            return;
        }
        String uid = this.sinaPreferences.getUID();
        if (uid == null) {
            authorize(activity, new UMAuthListener() {
                public void onComplete(SHARE_MEDIA share_media, int i, final Map<String, String> map) {
                    QueuedWork.runInBack(new Runnable() {
                        public void run() {
                            ShareFriendsResponse queryFriendsList = RestAPI.queryFriendsList(new ShareFriendsRequest(activity, SHARE_MEDIA.SINA, (String) map.get("uid")));
                            if (queryFriendsList == null) {
                                Log.e("follow", "resp = null");
                            } else if (queryFriendsList.isOk()) {
                                Map hashMap = new HashMap();
                                hashMap.put("friend", queryFriendsList.mFriends);
                                hashMap.put("json", queryFriendsList.getJsonData());
                                uMFriendListener.onComplete(SHARE_MEDIA.SINA, 2, hashMap);
                            } else {
                                Log.e("follow", "follow fail e =" + queryFriendsList.mMsg);
                            }
                        }
                    });
                }

                public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                    Log.e("auth fail");
                }

                public void onCancel(SHARE_MEDIA share_media, int i) {
                    Log.e("auth cancle");
                }
            });
            return;
        }
        ShareFriendsResponse queryFriendsList = RestAPI.queryFriendsList(new ShareFriendsRequest(activity, SHARE_MEDIA.SINA, uid));
        if (queryFriendsList == null) {
            uMFriendListener.onError(SHARE_MEDIA.SINA, 2, new Throwable("resp = null"));
        } else if (queryFriendsList.isOk()) {
            Map hashMap = new HashMap();
            hashMap.put("friend", queryFriendsList.mFriends);
            hashMap.put("json", queryFriendsList.getJsonData());
            uMFriendListener.onComplete(SHARE_MEDIA.SINA, 2, hashMap);
        } else {
            uMFriendListener.onError(SHARE_MEDIA.SINA, 2, new Throwable(queryFriendsList.mMsg));
        }
    }

    public void onResponse(BaseResponse baseResponse) {
        switch (baseResponse.errCode) {
            case 0:
                Log.d("sina_share", "weibo share error ok");
                if (isClientInstalled()) {
                    this.shareListener.onResult(SHARE_MEDIA.SINA);
                    return;
                }
                return;
            case 1:
                Log.d("sina_share", "weibo share cancel");
                this.shareListener.onCancel(SHARE_MEDIA.SINA);
                return;
            case 2:
                Log.d("sina_share", "weibo share fail");
                this.shareListener.onError(SHARE_MEDIA.SINA, new Throwable(baseResponse.errMsg));
                return;
            default:
                return;
        }
    }

    private Map<String, String> bundleTomap(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return null;
        }
        Set<String> keySet = bundle.keySet();
        Map<String, String> hashMap = new HashMap();
        for (String str : keySet) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    private void uploadAuthData(final Bundle bundle) throws SocializeException {
        new Thread(new Runnable() {
            public void run() {
                PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(SinaSsoHandler.this.getContext());
                platformTokenUploadReq.addStringParams("to", "sina");
                platformTokenUploadReq.addStringParams("usid", bundle.getString("uid"));
                platformTokenUploadReq.addStringParams("access_token", bundle.getString("access_token"));
                platformTokenUploadReq.addStringParams("refresh_token", bundle.getString("refresh_token"));
                platformTokenUploadReq.addStringParams("expires_in", bundle.getString("expires_in"));
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, SinaSsoHandler.this.config.appKey);
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_KEY, SinaSsoHandler.this.config.appSecret);
                RestAPI.uploadPlatformToken(platformTokenUploadReq);
            }
        }).start();
    }
}
