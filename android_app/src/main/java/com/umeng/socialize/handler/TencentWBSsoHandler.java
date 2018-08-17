package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.PlatformConfig.TencentWeibo;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UMLocation;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.TencentWBSharepreference;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.PlatformTokenUploadResponse;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.ShareDeleteOauthRequest;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.utils.Dummy;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.OauthHelper;
import com.umeng.socialize.view.OauthDialog;
import java.io.File;
import java.util.Map;

public class TencentWBSsoHandler extends UMAPIShareHandler {
    private static final String waiturl = "tenc2/main?uid";
    private TencentWeibo config;
    private Context context;
    private TencentWBSharepreference tencentWBSharepreference;

    class AuthListenerWrapper implements UMAuthListener {
        private UMAuthListener mListener = null;

        AuthListenerWrapper(UMAuthListener uMAuthListener) {
            this.mListener = uMAuthListener;
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Log.e("xxxx data = " + map);
            TencentWBSsoHandler.this.tencentWBSharepreference.setAuthData(map).commit();
            if (this.mListener != null) {
                this.mListener.onComplete(share_media, i, map);
            }
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

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        this.config = (TencentWeibo) platform;
        this.context = context.getApplicationContext();
        this.tencentWBSharepreference = new TencentWBSharepreference(context, SHARE_MEDIA.TENCENT.toString());
    }

    public boolean isAuthorized() {
        return this.tencentWBSharepreference.isAuthorized();
    }

    public boolean isAuthorize(Context context) {
        return isAuthorized();
    }

    public String getUID() {
        return this.tencentWBSharepreference.getUID();
    }

    public SHARE_MEDIA getPlatform() {
        return SHARE_MEDIA.TENCENT;
    }

    public void authorizeCallBack(int i, int i2, Intent intent) {
    }

    public void authorize(final Activity activity, UMAuthListener uMAuthListener) {
        final UMAuthListener uMAuthListener2 = (UMAuthListener) Dummy.get(UMAuthListener.class, uMAuthListener);
        if (isAuthorize(activity)) {
            uMAuthListener2.onComplete(SHARE_MEDIA.TENCENT, 0, this.tencentWBSharepreference.getAuthData());
            return;
        }
        QueuedWork.runInMain(new Runnable() {
            public void run() {
                OauthDialog oauthDialog = new OauthDialog(activity, SHARE_MEDIA.TENCENT, new AuthListenerWrapper(uMAuthListener2));
                oauthDialog.setWaitUrl(TencentWBSsoHandler.waiturl);
                oauthDialog.show();
            }
        });
    }

    private void uploadAuthData(final Map<String, String> map) throws SocializeException {
        new Thread(new Runnable() {
            public void run() {
                PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(TencentWBSsoHandler.this.getContext());
                platformTokenUploadReq.addStringParams("to", "tencent");
                platformTokenUploadReq.addStringParams("usid", (String) map.get("uid"));
                platformTokenUploadReq.addStringParams("access_token", (String) map.get("access_key"));
                platformTokenUploadReq.addStringParams("refresh_token", (String) map.get("refresh_token"));
                platformTokenUploadReq.addStringParams("expires_in", (String) map.get("expires_in"));
                PlatformTokenUploadResponse uploadPlatformToken = RestAPI.uploadPlatformToken(platformTokenUploadReq);
                if (uploadPlatformToken == null) {
                    Log.e("fail to upload tencent token");
                } else if (!uploadPlatformToken.isOk()) {
                    Log.e("fail to upload tencent token = " + uploadPlatformToken.mMsg);
                }
            }
        }).start();
    }

    public int getRequestCode() {
        return HandlerRequestCode.TENCENT_WB_REQUEST_CODE;
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        int i;
        SocializeReseponse execute = new SocializeClient().execute(new ShareDeleteOauthRequest(context, getConfig().getName(), Config.UID));
        this.tencentWBSharepreference.delete();
        if (execute != null) {
            i = execute.mStCode;
        } else {
            i = -102;
        }
        if (i == 200) {
            OauthHelper.remove(context, getConfig().getName());
            OauthHelper.removeTokenExpiresIn(context, getConfig().getName());
        } else {
            uMAuthListener.onError(getConfig().getName(), 1, new Throwable("delete fail"));
        }
        if (uMAuthListener != null) {
            uMAuthListener.onComplete(getConfig().getName(), 1, null);
        }
    }

    public boolean isSupportAuth() {
        return true;
    }

    public Bundle getEditable(ShareContent shareContent) {
        Bundle bundle = new Bundle();
        bundle.putString(ShareActivity.KEY_PLATFORM, SHARE_MEDIA.TENCENT.toString());
        bundle.putString("title", "分享到腾讯微博");
        bundle.putString(ShareActivity.KEY_TEXT, shareContent.mText);
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMImage)) {
            File asFileImage = ((UMImage) shareContent.mMedia).asFileImage();
            if (asFileImage != null) {
                bundle.putString(ShareActivity.KEY_PIC, asFileImage.getAbsolutePath());
            }
        } else if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMusic)) {
            bundle.putString(ShareActivity.KEY_PIC, WeiXinShareContent.TYPE_MUSIC);
        } else if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMVideo)) {
            bundle.putString(ShareActivity.KEY_PIC, WeiXinShareContent.TYPE_VIDEO);
        }
        bundle.putBoolean(ShareActivity.KEY_AT, true);
        bundle.putBoolean("location", true);
        Log.e("xxxx sendbundle=" + bundle);
        return bundle;
    }

    public ShareContent getResult(ShareContent shareContent, Bundle bundle) {
        shareContent.mText = bundle.getString(ShareActivity.KEY_TEXT);
        if (bundle.getString(ShareActivity.KEY_PIC) == null) {
            shareContent.mMedia = null;
        }
        if (bundle.getSerializable("location") != null) {
            shareContent.mLocation = (UMLocation) bundle.getSerializable("location");
        }
        return shareContent;
    }
}
