package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
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
import com.umeng.socialize.handler.UMTencentSSOHandler.ObtainImageUrlListener;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.media.UMediaObject.MediaType;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class QZoneSsoHandler extends UMTencentSSOHandler {
    private static final String TAG = "QZoneSsoHandler";
    private Context context;
    private Activity mActivity;
    private QZoneShareContent mShareContent;
    private IUiListener mUiListener = new QZoneSsoHandler_4();
    private QQPreferences qqPreferences;

    class QZoneSsoHandler_2 implements IUiListener {
        QZoneSsoHandler_2() {
        }

        public void onError(UiError uiError) {
            if (uiError != null) {
                Log.d(QZoneSsoHandler.TAG, "授权失败! ==> errorCode = " + uiError.errorCode + ", errorMsg = " + uiError.errorMessage + ", detail = " + uiError.errorDetail);
            }
            QZoneSsoHandler.this.mAuthListener.onError(SHARE_MEDIA.QQ, 0, new Throwable("授权失败! ==> errorCode = " + uiError.errorCode + ", errorMsg = " + uiError.errorMessage + ", detail = " + uiError.errorDetail));
        }

        public void onCancel() {
            QZoneSsoHandler.this.mAuthListener.onCancel(SHARE_MEDIA.QQ, 0);
        }

        public void onComplete(Object obj) {
            SocializeUtils.safeCloseDialog(QZoneSsoHandler.this.mProgressDialog);
            Bundle parseOauthData = QZoneSsoHandler.this.parseOauthData(obj);
            QZoneSsoHandler.this.qqPreferences.setAuthData(parseOauthData).commit();
            QZoneSsoHandler.this.initOpenidAndToken((JSONObject) obj);
            if (QZoneSsoHandler.this.mAuthListener != null) {
                QZoneSsoHandler.this.mAuthListener.onComplete(SHARE_MEDIA.QQ, 0, QZoneSsoHandler.this.bundleTomap(parseOauthData));
            }
            QZoneSsoHandler.this.uploadAuthData(parseOauthData);
            if (parseOauthData != null && !TextUtils.isEmpty(parseOauthData.getString("ret"))) {
            }
        }
    }

    class QZoneSsoHandler_4 implements IUiListener {
        QZoneSsoHandler_4() {
        }

        public void onError(UiError uiError) {
            QZoneSsoHandler.this.mAuthListener.onError(SHARE_MEDIA.QZONE, 0, null);
        }

        public void onCancel() {
            QZoneSsoHandler.this.mAuthListener.onCancel(SHARE_MEDIA.QZONE, 0);
        }

        public void onComplete(Object obj) {
            QZoneSsoHandler.this.mAuthListener.onComplete(SHARE_MEDIA.QZONE, 0, null);
        }
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        this.qqPreferences = new QQPreferences(context, SHARE_MEDIA.QQ.toString());
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (uMShareListener != null) {
            this.mShareListener = uMShareListener;
        }
        if (isInstall(activity, getConfig())) {
            this.mShareContent = new QZoneShareContent(shareContent);
            shareToQZone(activity, new QZoneShareContent(shareContent));
        }
        return false;
    }

    private void shareToQZone(Activity activity, QZoneShareContent qZoneShareContent) {
        if (activity == null) {
            Log.d("UMError", "QZone share activity is null");
            return;
        }
        Bundle buildParamsQzone = this.mShareContent.buildParamsQzone();
        buildParamsQzone.putString("appName", getAppName());
        int i = buildParamsQzone.getInt("req_type");
        List stringArrayList = buildParamsQzone.getStringArrayList("imageUrl");
        String str = null;
        if (stringArrayList != null && stringArrayList.size() > 0) {
            str = (String) stringArrayList.get(0);
        }
        if (isUploadImageAsync(str, i)) {
            authorize(activity, createAuthListener(buildParamsQzone, new UMImage((Context) activity, str), activity));
        } else {
            defaultQZoneShare(buildParamsQzone, activity);
        }
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        this.mTencent.logout(context);
        if (this.qqPreferences != null) {
            this.qqPreferences.delete();
        }
        uMAuthListener.onComplete(SHARE_MEDIA.QZONE, 1, null);
    }

    private UMAuthListener createAuthListener(final Bundle bundle, final UMImage uMImage, final Activity activity) {
        return new UMAuthListener() {

            class QZoneSsoHandler_1_1 implements ObtainImageUrlListener {
                QZoneSsoHandler_1_1() {
                }

                public void onComplete(String str) {
                    if (TextUtils.isEmpty(str)) {
                        QZoneSsoHandler.this.defaultQZoneShare(bundle, activity);
                        UMediaObject media = QZoneSsoHandler.this.mShareContent.getMedia();
                        int i = bundle.getInt("req_type");
                        if (!QZoneSsoHandler.this.isClientInstalled(activity) && media != null) {
                            if (media.getMediaType() == MediaType.VEDIO || media.getMediaType() == MediaType.MUSIC || i == 1) {
                                Log.e(QZoneSsoHandler.TAG, "QQ空间上传图片失败将导致无客户端分享失败，请设置缩略图为url类型或者较小的本地图片...");
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    bundle.remove("imageUrl");
                    arrayList.add(str);
                    bundle.putStringArrayList("imageUrl", arrayList);
                    QZoneSsoHandler.this.defaultQZoneShare(bundle, activity);
                }
            }

            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (map != null && map.containsKey("uid")) {
                    QZoneSsoHandler.this.getBitmapUrl(uMImage, (String) map.get("uid"), new QZoneSsoHandler_1_1());
                }
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            }

            public void onCancel(SHARE_MEDIA share_media, int i) {
            }
        };
    }

    private boolean isInstall(Context context, Platform platform) {
        if (this.mTencent.isSupportSSOLogin((Activity) context)) {
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

    public boolean isSupportAuth() {
        return true;
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        if (activity == null) {
            Log.d("UMError", "QZone share activity is null");
            return;
        }
        this.mActivity = activity;
        if (isInstall(activity, getConfig())) {
            this.mAuthListener = uMAuthListener;
            loginDeal();
        }
    }

    private void loginDeal() {
        Log.i(TAG, "QQ oauth login...");
        this.mTencent.login(this.mActivity, "all", getAuthlistener(this.mAuthListener));
    }

    private IUiListener getAuthlistener(UMAuthListener uMAuthListener) {
        return new QZoneSsoHandler_2();
    }

    private void uploadAuthData(final Bundle bundle) throws SocializeException {
        new Thread(new Runnable() {
            public void run() {
                PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(QZoneSsoHandler.this.getContext());
                platformTokenUploadReq.addStringParams("to", "qq");
                platformTokenUploadReq.addStringParams("usid", bundle.getString("uid"));
                platformTokenUploadReq.addStringParams("access_token", bundle.getString("access_token"));
                platformTokenUploadReq.addStringParams("refresh_token", bundle.getString("refresh_token"));
                platformTokenUploadReq.addStringParams("expires_in", bundle.getString("expires_in"));
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, QZoneSsoHandler.this.config.appId);
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_KEY, QZoneSsoHandler.this.config.appKey);
                Log.e("upload token resp = " + RestAPI.uploadPlatformToken(platformTokenUploadReq));
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

    public int getRequestCode() {
        return Constants.REQUEST_QZONE_SHARE;
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
                if (uMShareListener != null) {
                    uMShareListener.onResult(SHARE_MEDIA.QZONE);
                }
            }

            public void onError(UiError uiError) {
                if (uMShareListener != null) {
                    uMShareListener.onError(SHARE_MEDIA.QZONE, null);
                }
            }

            public void onCancel() {
                if (uMShareListener != null) {
                    uMShareListener.onCancel(SHARE_MEDIA.QZONE);
                }
            }
        };
    }

    private void defaultQZoneShare(final Bundle bundle, final Activity activity) {
        Log.d(TAG, "invoke Tencent.shareToQzone method...");
        if (bundle != null) {
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    QZoneSsoHandler.this.mTencent.shareToQzone(activity, bundle, QZoneSsoHandler.this.getmShareListener(QZoneSsoHandler.this.mShareListener));
                }
            });
        }
    }
}
