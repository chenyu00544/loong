package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.media.Constant;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class UMQQSsoHandler extends UMTencentSSOHandler {
    private static final String TAG = "UMQQSsoHandler";
    private boolean GOTO_SHARE_ACTIVITY = false;
    private Activity mActivity;
    private Bundle mParams;
    private IUiListener mShareListener;
    private QQShareContent msharecontent;
    private QQPreferences qqPreferences;

    class UMQQSsoHandler_2 implements IUiListener {
        UMQQSsoHandler_2() {
        }

        public void onError(UiError uiError) {
            if (uiError != null) {
                Log.d(UMQQSsoHandler.TAG, "授权失败! ==> errorCode = " + uiError.errorCode + ", errorMsg = " + uiError.errorMessage + ", detail = " + uiError.errorDetail);
            }
            UMQQSsoHandler.this.mAuthListener.onError(SHARE_MEDIA.QQ, 0, new Throwable("授权失败! ==> errorCode = " + uiError.errorCode + ", errorMsg = " + uiError.errorMessage + ", detail = " + uiError.errorDetail));
        }

        public void onCancel() {
            if (UMQQSsoHandler.this.mAuthListener != null) {
                UMQQSsoHandler.this.mAuthListener.onCancel(SHARE_MEDIA.QQ, 0);
            }
        }

        public void onComplete(Object obj) {
            SocializeUtils.safeCloseDialog(UMQQSsoHandler.this.mProgressDialog);
            Bundle parseOauthData = UMQQSsoHandler.this.parseOauthData(obj);
            if (UMQQSsoHandler.this.qqPreferences == null && UMQQSsoHandler.this.mActivity != null) {
                UMQQSsoHandler.this.qqPreferences = new QQPreferences(UMQQSsoHandler.this.mActivity, SHARE_MEDIA.QQ.toString());
            }
            if (UMQQSsoHandler.this.qqPreferences != null) {
                UMQQSsoHandler.this.qqPreferences.setAuthData(parseOauthData).commit();
            }
            UMQQSsoHandler.this.initOpenidAndToken((JSONObject) obj);
            if (UMQQSsoHandler.this.mAuthListener != null) {
                UMQQSsoHandler.this.mAuthListener.onComplete(SHARE_MEDIA.QQ, 0, UMQQSsoHandler.this.bundleTomap(parseOauthData));
            }
            UMQQSsoHandler.this.uploadAuthData(parseOauthData);
            if (parseOauthData != null && Integer.valueOf(parseOauthData.getString("ret")).intValue() != 0) {
            }
        }
    }

    class UMQQSsoHandler_5 implements Runnable {
        UMQQSsoHandler_5() {
        }

        public void run() {
            UMQQSsoHandler.this.mTencent.shareToQQ(UMQQSsoHandler.this.mActivity, UMQQSsoHandler.this.mParams, UMQQSsoHandler.this.mShareListener);
        }
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        if (context != null) {
            this.qqPreferences = new QQPreferences(context, SHARE_MEDIA.QQ.toString());
        }
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (activity == null) {
            Log.d("UMError", "qq share activity is null");
        } else {
            Log.e("xxxxxxxxx");
            this.mParams = null;
            this.mActivity = activity;
            this.mShareListener = getSharelistener(uMShareListener);
            if (this.mShareListener == null) {
                Log.d("listen", "listener is null");
            }
            this.msharecontent = new QQShareContent(shareContent);
            shareToQQ(activity);
        }
        return false;
    }

    private IUiListener getSharelistener(final UMShareListener uMShareListener) {
        return new IUiListener() {
            public void onError(UiError uiError) {
                if (uiError != null) {
                    Log.e("xxxx qqerror" + uiError.errorDetail + uiError.errorMessage + uiError.errorCode);
                }
                uMShareListener.onError(SHARE_MEDIA.QQ, null);
            }

            public void onCancel() {
                Log.e("xxxx qqcancle");
                uMShareListener.onCancel(SHARE_MEDIA.QQ);
            }

            public void onComplete(Object obj) {
                Log.e("xxxx qqcomplete");
                uMShareListener.onResult(SHARE_MEDIA.QQ);
            }
        };
    }

    public boolean isAuthorize(Context context) {
        if (context == null || this.qqPreferences == null) {
            return false;
        }
        return this.qqPreferences.isAuthValid();
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        if (activity == null) {
            Log.d("UMError", "qq authorize activity is null");
            return;
        }
        this.mActivity = activity;
        this.mAuthListener = uMAuthListener;
        loginDeal();
    }

    private IUiListener getAuthlistener(UMAuthListener uMAuthListener) {
        return new UMQQSsoHandler_2();
    }

    public boolean isInstall(Context context) {
        if (this.mTencent.isSupportSSOLogin((Activity) context)) {
            return true;
        }
        return false;
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        this.mTencent.logout(context);
        if (this.qqPreferences != null) {
            this.qqPreferences.delete();
        }
        uMAuthListener.onComplete(SHARE_MEDIA.QQ, 1, null);
    }

    private boolean isInstall(Context context, Platform platform) {
        if (this.mTencent.isSupportSSOLogin((Activity) context)) {
            return true;
        }
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("请安装");
        stringBuilder.append(ResContainer.getString(context, platform.getName().toSnsPlatform().mShowWord));
        stringBuilder.append("客户端");
        Log.v(stringBuilder.toString());
        if (Config.IsToastTip) {
            Toast.makeText(context, stringBuilder, 1).show();
        }
        return false;
    }

    public boolean isSupportAuth() {
        return true;
    }

    private void loginDeal() {
        Log.i(TAG, "QQ oauth login...");
        if (isInstall(this.mActivity)) {
            Log.d("qq", "installed qq");
            this.mTencent.login(this.mActivity, "all", getAuthlistener(this.mAuthListener));
            return;
        }
        Log.d("qq", "didn't install qq");
        this.mTencent.loginServerSide(this.mActivity, "all", getAuthlistener(this.mAuthListener));
    }

    public void initOpenidAndToken(JSONObject jSONObject) {
        try {
            Object string = jSONObject.getString("access_token");
            Object string2 = jSONObject.getString("expires_in");
            Object string3 = jSONObject.getString("openid");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                this.mTencent.setAccessToken(string, string2);
                this.mTencent.setOpenId(string3);
            }
        } catch (Exception e) {
        }
    }

    private void uploadAuthData(final Bundle bundle) throws SocializeException {
        new Thread(new Runnable() {
            public void run() {
                if (UMQQSsoHandler.this.getContext() != null && bundle != null && UMQQSsoHandler.this.config != null) {
                    PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(UMQQSsoHandler.this.getContext());
                    platformTokenUploadReq.addStringParams("to", "qq");
                    platformTokenUploadReq.addStringParams("usid", bundle.getString("uid"));
                    platformTokenUploadReq.addStringParams("access_token", bundle.getString("access_token"));
                    platformTokenUploadReq.addStringParams("refresh_token", bundle.getString("refresh_token"));
                    platformTokenUploadReq.addStringParams("expires_in", bundle.getString("expires_in"));
                    platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, UMQQSsoHandler.this.config.appId);
                    platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_KEY, UMQQSsoHandler.this.config.appKey);
                    Log.e("upload token resp = " + RestAPI.uploadPlatformToken(platformTokenUploadReq));
                }
            }
        }).start();
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

    public void canOpenShareActivity(boolean z) {
        this.GOTO_SHARE_ACTIVITY = z;
    }

    private void gotoShare() {
    }

    public void shareToQQ(Context context) {
        if (validTencent()) {
            String str = (String) this.msharecontent.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
            if (isLoadImageAsync(context)) {
                loadImage(context, (String) this.msharecontent.mExtraData.get(Constant.IMAGE_PATH_URL));
                return;
            } else if (isUploadImageAsync(str, this.msharecontent.mShareType, context)) {
                UMImage uMImage = new UMImage(context, new File(str));
                Log.w(TAG, "未安装QQ客户端的情况下，QQ不支持音频，图文是为本地图片的分享。此时将上传本地图片到相册，请确保在QQ互联申请了upload_pic权限.");
                authorize(this.mActivity, createUploadAuthListener(uMImage));
                return;
            } else {
                Log.d("shareQQ", "share to qq");
                defaultShareToQQ(this.msharecontent);
                return;
            }
        }
        Log.d(TAG, "QQ平台还没有授权");
        authorize(this.mActivity, null);
    }

    private UMAuthListener createUploadAuthListener(final UMImage uMImage) {
        return new UMAuthListener() {
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (map != null && map.containsKey("uid") && !TextUtils.isEmpty(uMImage.asUrlImage())) {
                    UMQQSsoHandler.this.mParams.putString("imageUrl", uMImage.asUrlImage());
                    UMQQSsoHandler.this.mParams.remove("imageLocalUrl");
                    UMQQSsoHandler.this.defaultShareToQQ(UMQQSsoHandler.this.msharecontent);
                }
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            }

            public void onCancel(SHARE_MEDIA share_media, int i) {
            }
        };
    }

    protected boolean isUploadImageAsync(String str, int i, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        SHARE_MEDIA name = this.config.getName();
        boolean isClientInstalled = isClientInstalled(context);
        boolean z = (str.startsWith("http://") || str.startsWith("https://")) ? false : true;
        if (isClientInstalled || !z) {
            return false;
        }
        if (name == SHARE_MEDIA.QQ && (i == 2 || i == 1)) {
            return true;
        }
        if (name != SHARE_MEDIA.QZONE) {
            return false;
        }
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    private boolean isLoadImageAsync(Context context) {
        String str = (String) this.msharecontent.mExtraData.get(Constant.IMAGE_PATH_URL);
        String str2 = (String) this.msharecontent.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
        if (this.msharecontent.mShareType == 5 && isClientInstalled(context) && !TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return true;
        }
        return false;
    }

    private void loadImage(Context context, String str) {
        BitmapUtils.getBitmapFromFile(str);
        SocializeUtils.safeCloseDialog(this.mProgressDialog);
        this.mParams.putString("imageLocalUrl", BitmapUtils.getFileName(str));
        this.mParams.remove("imageUrl");
        defaultShareToQQ(this.msharecontent);
    }

    private void defaultShareToQQ(QQShareContent qQShareContent) {
        this.mParams = this.msharecontent.buildParams();
        this.mParams.putString("appName", getAppName());
        if (this.mParams != null) {
            QueuedWork.runInMain(new UMQQSsoHandler_5());
        }
    }

    public int getRequestCode() {
        return Constants.REQUEST_QQ_SHARE;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == Constants.REQUEST_QQ_SHARE) {
            Tencent.onActivityResultData(i, i2, intent, this.mShareListener);
        }
        if (i == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(i, i2, intent, getAuthlistener(this.mAuthListener));
        }
    }

    public void getPlatformInfo(Activity activity, final UMAuthListener uMAuthListener) {
        if (isAuthorize(activity)) {
            try {
                Object obj = this.qqPreferences.getmAccessToken();
                QQPreferences qQPreferences = this.qqPreferences;
                Object expiresIn = QQPreferences.getExpiresIn();
                Object obj2 = this.qqPreferences.getmUID();
                if (!(activity == null || this.qqPreferences == null)) {
                    obj = this.qqPreferences.getmAccessToken();
                    qQPreferences = this.qqPreferences;
                    expiresIn = QQPreferences.getExpiresIn();
                    obj2 = this.qqPreferences.getmUID();
                }
                if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(expiresIn) || TextUtils.isEmpty(obj2))) {
                    this.mTencent.setAccessToken(obj, expiresIn);
                    this.mTencent.setOpenId(obj2);
                }
            } catch (Exception e) {
            }
        }
        if (activity == null) {
            Log.d("UMError", "QQ getPlatFormInfo activity is null");
        } else {
            new UserInfo(activity, this.mTencent.getQQToken()).getUserInfo(new IUiListener() {
                public void onCancel() {
                }

                public void onComplete(Object obj) {
                    try {
                        JSONObject jSONObject = new JSONObject(obj.toString());
                        Map hashMap = new HashMap();
                        hashMap.put("screen_name", jSONObject.optString("nickname"));
                        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_GENDER, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_GENDER));
                        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_ICON, jSONObject.optString("figureurl_qq_2"));
                        hashMap.put("is_yellow_year_vip", jSONObject.optString("is_yellow_year_vip"));
                        hashMap.put("yellow_vip_level", jSONObject.optString("yellow_vip_level"));
                        hashMap.put("msg", jSONObject.optString("msg"));
                        hashMap.put("city", jSONObject.optString("city"));
                        hashMap.put("vip", jSONObject.optString("vip"));
                        hashMap.put("level", jSONObject.optString("level"));
                        hashMap.put("province", jSONObject.optString("province"));
                        hashMap.put("is_yellow_vip", jSONObject.optString("is_yellow_vip"));
                        if (UMQQSsoHandler.this.qqPreferences != null) {
                            hashMap.put("openid", UMQQSsoHandler.this.qqPreferences.getuid());
                        }
                        uMAuthListener.onComplete(SHARE_MEDIA.QQ, 2, hashMap);
                    } catch (JSONException e) {
                        uMAuthListener.onComplete(SHARE_MEDIA.QQ, 2, null);
                    }
                }

                public void onError(UiError uiError) {
                    uMAuthListener.onError(SHARE_MEDIA.QQ, 2, new Throwable(uiError.toString()));
                }
            });
        }
    }
}
