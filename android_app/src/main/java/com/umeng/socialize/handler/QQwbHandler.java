package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.connect.common.Constants;
import com.tencent.open.t.Weibo;
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
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.PlatformTokenUploadResponse;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class QQwbHandler extends UMTencentSSOHandler {
    private static final int REQUEST_ADD_PIC_T = 1001;
    private Activity mActivity;
    private Weibo mWeibo = null;
    private QQPreferences qqPreferences;

    class QQwbHandler_2 implements IUiListener {
        QQwbHandler_2() {
        }

        public void onError(UiError uiError) {
            if (uiError != null) {
                Log.d("授权失败! ==> errorCode = " + uiError.errorCode + ", errorMsg = " + uiError.errorMessage + ", detail = " + uiError.errorDetail);
            }
        }

        public void onCancel() {
        }

        public void onComplete(Object obj) {
            SocializeUtils.safeCloseDialog(QQwbHandler.this.mProgressDialog);
            Bundle parseOauthData = QQwbHandler.this.parseOauthData(obj);
            QQwbHandler.this.qqPreferences = new QQPreferences(QQwbHandler.this.mActivity, SHARE_MEDIA.QQ.toString());
            QQwbHandler.this.qqPreferences.setAuthData(parseOauthData).commit();
            QQwbHandler.this.uploadAuthData(parseOauthData);
            QQwbHandler.this.initOpenidAndToken((JSONObject) obj);
            if (QQwbHandler.this.mAuthListener != null) {
                QQwbHandler.this.mAuthListener.onComplete(SHARE_MEDIA.QQ, 0, QQwbHandler.this.bundleTomap(parseOauthData));
            }
            if (parseOauthData != null && !TextUtils.isEmpty(parseOauthData.getString("ret"))) {
            }
        }
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        if (activity == null) {
            Log.d("UMError", "QQwb authorize activity is null");
            return;
        }
        this.mActivity = activity;
        if (isInstall(activity, getConfig())) {
            this.mAuthListener = uMAuthListener;
            loginDeal();
        }
    }

    private boolean isInstall(Context context, Platform platform) {
        if (DeviceConfig.isAppInstalled("com.tencent.mobileqq", context)) {
            return true;
        }
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("请安装");
        stringBuilder.append("qq");
        stringBuilder.append("客户端");
        Log.v(stringBuilder.toString());
        if (Config.IsToastTip) {
            Toast.makeText(context, stringBuilder, 1).show();
        }
        return false;
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (activity == null) {
            Log.d("UMError", "QQwb share activity is null");
            return false;
        }
        this.qqPreferences = new QQPreferences(activity, SHARE_MEDIA.QQ.toString());
        if (this.qqPreferences.getmUID() == null) {
            Log.e("QQ没有授权");
            if (uMShareListener == null) {
                return false;
            }
            uMShareListener.onError(SHARE_MEDIA.TENCENT, new Throwable("QQ is not  auth"));
            return false;
        }
        Object obj = this.qqPreferences.getmAccessToken();
        QQPreferences qQPreferences = this.qqPreferences;
        Object expiresIn = QQPreferences.getExpiresIn();
        Object obj2 = this.qqPreferences.getmUID();
        if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(expiresIn) || TextUtils.isEmpty(obj2))) {
            this.mTencent.setAccessToken(obj, expiresIn);
            this.mTencent.setOpenId(obj2);
        }
        this.mWeibo = new Weibo(activity, this.mTencent.getQQToken());
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMImage)) {
            String path;
            if (((UMImage) shareContent.mMedia).asFileImage() != null) {
                path = ((UMImage) shareContent.mMedia).asFileImage().getPath();
            } else {
                path = null;
            }
            Log.e("xxxxx", "filePath=" + path);
            if (path == null) {
                this.mWeibo.sendText(shareContent.mText, getSharelistener(uMShareListener));
            } else {
                this.mWeibo.sendPicText(shareContent.mText, path, getSharelistener(uMShareListener));
            }
        } else if (!TextUtils.isEmpty(shareContent.mText)) {
            this.mWeibo.sendText(shareContent.mText, getSharelistener(uMShareListener));
        }
        return true;
    }

    public boolean QQisReady() {
        return this.mTencent.isSessionValid() && this.mTencent.getQQToken().getOpenId() != null;
    }

    private IUiListener getSharelistener(final UMShareListener uMShareListener) {
        return new IUiListener() {
            public void onError(UiError uiError) {
                Log.e("xxxx qqerror");
                uMShareListener.onError(SHARE_MEDIA.TENCENT, null);
            }

            public void onCancel() {
                Log.e("xxxx qqcancle");
                uMShareListener.onCancel(SHARE_MEDIA.TENCENT);
            }

            public void onComplete(Object obj) {
                Log.e("xxxx qqcomplete=" + obj);
                uMShareListener.onResult(SHARE_MEDIA.TENCENT);
            }
        };
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

    private void loginDeal() {
        this.mTencent.login(this.mActivity, "all", getAuthlistener(this.mAuthListener));
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        this.mTencent.logout(context);
        if (this.qqPreferences != null) {
            this.qqPreferences.delete();
        }
        uMAuthListener.onComplete(SHARE_MEDIA.TENCENT, 1, null);
    }

    private IUiListener getAuthlistener(UMAuthListener uMAuthListener) {
        return new QQwbHandler_2();
    }

    public boolean isSupportAuth() {
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == Constants.REQUEST_QZONE_SHARE) {
            Tencent.onActivityResultData(i, i2, intent, getmShareListener(this.mShareListener));
        }
        if (i == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(i, i2, intent, getAuthlistener(this.mAuthListener));
        }
    }

    public IUiListener getmShareListener(final UMShareListener uMShareListener) {
        return new IUiListener() {
            public void onComplete(Object obj) {
                uMShareListener.onResult(SHARE_MEDIA.QZONE);
            }

            public void onError(UiError uiError) {
                uMShareListener.onError(SHARE_MEDIA.QZONE, null);
            }

            public void onCancel() {
                uMShareListener.onCancel(SHARE_MEDIA.QZONE);
            }
        };
    }

    public void initOpenidAndToken(JSONObject jSONObject) {
        try {
            Object string = jSONObject.getString("access_token");
            Object string2 = jSONObject.getString("expires_in");
            Object string3 = jSONObject.getString("openid");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                this.mTencent.setAccessToken(string, string2);
                this.mTencent.setOpenId(string3);
                Log.e("xxxx write mTencent.getQQToken().getOpenId()=");
            }
        } catch (Exception e) {
        }
    }

    private void uploadAuthData(final Bundle bundle) throws SocializeException {
        new Thread(new Runnable() {
            public void run() {
                PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(QQwbHandler.this.getContext());
                platformTokenUploadReq.addStringParams("to", "qq");
                platformTokenUploadReq.addStringParams("usid", bundle.getString("uid"));
                platformTokenUploadReq.addStringParams("access_token", bundle.getString("access_token"));
                platformTokenUploadReq.addStringParams("refresh_token", bundle.getString("refresh_token"));
                platformTokenUploadReq.addStringParams("expires_in", bundle.getString("expires_in"));
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, QQwbHandler.this.config.appId);
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_KEY, QQwbHandler.this.config.appKey);
                PlatformTokenUploadResponse uploadPlatformToken = RestAPI.uploadPlatformToken(platformTokenUploadReq);
                if (uploadPlatformToken == null) {
                    Log.e("fail to upload sina token");
                } else if (!uploadPlatformToken.isOk()) {
                    Log.e("fail to upload sina token = " + uploadPlatformToken.mMsg);
                }
            }
        }).start();
    }
}
