package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.pro.x;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.PlatformConfig.Weixin;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Dummy;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.weixin.net.WXAuthUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UMWXHandler extends UMSSOHandler {
    private static final int REFRESH_TOKEN_EXPIRES = 604800;
    private static final int REQUEST_CODE = 10086;
    private static final int RESP_TYPE_AUTH = 1;
    private static final int RESP_TYPE_SHARE = 2;
    private static final String TAG = "UMWXHandler";
    private static String sScope = "snsapi_userinfo,snsapi_friend,snsapi_message";
    private Weixin config;
    private boolean isToCircle = false;
    private UMAuthListener mAuthListener;
    private IWXAPIEventHandler mEventHandler = new UMWXHandler_5();
    private WeiXinShareContent mShareContent;
    private SHARE_MEDIA mTarget = SHARE_MEDIA.WEIXIN;
    private IWXAPI mWXApi;
    private Context mcontext;
    private UMShareListener umShareListener;
    private WeixinPreferences weixinPreferences;

    class UMWXHandler_5 implements IWXAPIEventHandler {
        UMWXHandler_5() {
        }

        public void onResp(BaseResp baseResp) {
            switch (baseResp.getType()) {
                case 1:
                    UMWXHandler.this.onAuthCallback((Resp) baseResp);
                    return;
                case 2:
                    UMWXHandler.this.onShareCallback((SendMessageToWX.Resp) baseResp);
                    return;
                default:
                    return;
            }
        }

        public void onReq(BaseReq baseReq) {
        }
    }

    public void setScope(String... strArr) {
        sScope = TextUtils.join(",", strArr);
    }

    public void onCreate(Context context, Platform platform) {
        this.weixinPreferences = new WeixinPreferences(context.getApplicationContext(), "weixin");
        this.config = (Weixin) platform;
        this.mWXApi = WXAPIFactory.createWXAPI(context.getApplicationContext(), this.config.appId);
        this.mWXApi.registerApp(this.config.appId);
        if (!isClientInstalled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("请安装");
            stringBuilder.append(this.mTarget);
            stringBuilder.append("客户端");
            if (Config.IsToastTip) {
                Toast.makeText(context, stringBuilder.toString(), 0).show();
            }
        }
        this.mcontext = context;
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        this.mAuthListener = uMAuthListener;
        this.mTarget = this.config.getName();
        if (this.weixinPreferences.isAuthValid()) {
            if (this.weixinPreferences.isAccessTokenAvailable()) {
                loadOauthData("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + this.config.appId + "&grant_type=refresh_token&refresh_token=" + this.weixinPreferences.getRefreshToken());
            }
            this.mAuthListener.onComplete(SHARE_MEDIA.WEIXIN, 0, getAuthWithRefreshToken(this.weixinPreferences.getRefreshToken()));
            return;
        }
        BaseReq req = new Req();
        req.scope = sScope;
        req.state = UInAppMessage.NONE;
        this.mWXApi.sendReq(req);
    }

    public boolean isAuthorize(Context context) {
        return this.weixinPreferences.isAuth();
    }

    private void loadOauthData(String str) {
        this.weixinPreferences.setBundle(parseAuthData(WXAuthUtils.request(str)));
        this.mAuthListener.onComplete(this.config.getName(), 0, null);
    }

    private Bundle parseAuthData(String str) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            return bundle;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            String str2 = "";
            while (keys.hasNext()) {
                str2 = (String) keys.next();
                bundle.putString(str2, jSONObject.optString(str2));
            }
            bundle.putString("uid", bundle.getString("openid"));
            bundle.putLong("refresh_token_expires", 604800);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bundle;
    }

    private Map<String, String> getAuthWithRefreshToken(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?");
        stringBuilder.append("appid=").append(this.config.appId);
        stringBuilder.append("&grant_type=refresh_token");
        stringBuilder.append("&refresh_token=").append(str);
        String request = WXAuthUtils.request(stringBuilder.toString());
        Map<String, String> map = null;
        try {
            map = SocializeUtils.jsonToMap(request);
        } catch (Exception e) {
        }
        return map;
    }

    private void getAuthWithCode(String str, final UMAuthListener uMAuthListener) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        stringBuilder.append("appid=").append(this.config.appId);
        stringBuilder.append("&secret=").append(this.config.appSecret);
        stringBuilder.append("&code=").append(str);
        stringBuilder.append("&grant_type=authorization_code");
        new Thread(new Runnable() {
            public void run() {
                String request = WXAuthUtils.request(stringBuilder.toString());
                try {
                    Map jsonToMap = SocializeUtils.jsonToMap(request);
                    if (jsonToMap == null || jsonToMap.size() == 0) {
                        jsonToMap = UMWXHandler.this.weixinPreferences.getmap();
                    }
                    UMWXHandler.this.weixinPreferences.setBundle(UMWXHandler.this.parseAuthData(request));
                    QueuedWork.runInMain(new Runnable() {
                        public void run() {
                            UMWXHandler.this.uploadAuthData(jsonToMap);
                            uMAuthListener.onComplete(SHARE_MEDIA.WEIXIN, 0, jsonToMap);
                        }
                    });
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public boolean isInstall(Context context) {
        return isClientInstalled();
    }

    protected void onAuthCallback(Resp resp) {
        UMAuthListener uMAuthListener = (UMAuthListener) Dummy.get(UMAuthListener.class, this.mAuthListener);
        if (resp.errCode == 0) {
            getAuthWithCode(resp.code, uMAuthListener);
        } else if (resp.errCode != -2) {
            Throwable socializeException = new SocializeException(TextUtils.concat(new CharSequence[]{"weixin auth error (", String.valueOf(resp.errCode), "):", resp.errStr}).toString());
            if (uMAuthListener != null) {
                uMAuthListener.onError(SHARE_MEDIA.WEIXIN, 0, socializeException);
            }
        } else if (uMAuthListener != null) {
            uMAuthListener.onCancel(SHARE_MEDIA.WEIXIN, 0);
        }
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        if (isInstall(context)) {
            this.weixinPreferences.delete();
            uMAuthListener.onComplete(SHARE_MEDIA.WEIXIN, 1, null);
        }
    }

    public boolean isSupportAuth() {
        return true;
    }

    public int getRequestCode() {
        if (this.isToCircle) {
            return HandlerRequestCode.WX_CIRCLE_REQUEST_CODE;
        }
        return 10086;
    }

    private void refreshAccessToken() {
        if (this.weixinPreferences == null) {
            return;
        }
        if (this.weixinPreferences.isAuthValid()) {
            Log.d("refresh", "requesting access token with refresh");
            this.weixinPreferences.setBundle(parseAuthData(WXAuthUtils.request("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + this.config.appId + "&grant_type=refresh_token&refresh_token=" + this.weixinPreferences.getRefreshToken())));
            return;
        }
        Log.e("refresh", "weixin refresh token is expired");
    }

    public void getPlatformInfo(Activity activity, final UMAuthListener uMAuthListener) {
        Object uid = this.weixinPreferences.getUID();
        Object accessToken = this.weixinPreferences.getAccessToken();
        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(accessToken)) {
            Log.e(TAG, "please check had authed...");
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    uMAuthListener.onComplete(SHARE_MEDIA.WEIXIN, 2, null);
                }
            });
            return;
        }
        if (!this.weixinPreferences.isAccessTokenAvailable()) {
            Log.d("refresh", "getting auth with refresh token");
            refreshAccessToken();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
        stringBuilder.append(accessToken).append("&openid=").append(uid);
        stringBuilder.append("&lang=zh_CN");
        final Map parseUserInfo = parseUserInfo(WXAuthUtils.request(stringBuilder.toString()));
        QueuedWork.runInMain(new Runnable() {
            public void run() {
                uMAuthListener.onComplete(SHARE_MEDIA.WEIXIN, 2, parseUserInfo);
            }
        });
    }

    private void uploadAuthData(final Map<String, String> map) throws SocializeException {
        new Thread(new Runnable() {
            public void run() {
                if (UMWXHandler.this.mcontext != null) {
                    PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(UMWXHandler.this.mcontext);
                    platformTokenUploadReq.addStringParams("to", "wxsession");
                    platformTokenUploadReq.addStringParams("usid", (String) map.get("uid"));
                    platformTokenUploadReq.addStringParams("access_token", (String) map.get("access_token"));
                    platformTokenUploadReq.addStringParams("refresh_token", (String) map.get("refresh_token"));
                    platformTokenUploadReq.addStringParams("expires_in", (String) map.get("expires_in"));
                    platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, UMWXHandler.this.config.appId);
                    platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_KEY, UMWXHandler.this.config.appSecret);
                    Log.e("upload token resp = " + RestAPI.uploadPlatformToken(platformTokenUploadReq));
                }
            }
        }).start();
    }

    private Map<String, String> parseUserInfo(String str) {
        Map<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("errcode")) {
                Log.e(TAG, str + "");
                hashMap.put("errcode", jSONObject.getString("errcode"));
                return hashMap;
            }
            hashMap.put("openid", jSONObject.opt("openid").toString());
            hashMap.put("nickname", jSONObject.opt("nickname").toString());
            hashMap.put(x.F, jSONObject.opt(x.F).toString());
            hashMap.put("city", jSONObject.opt("city").toString());
            hashMap.put("province", jSONObject.opt("province").toString());
            hashMap.put(x.G, jSONObject.opt(x.G).toString());
            hashMap.put("headimgurl", jSONObject.opt("headimgurl").toString());
            hashMap.put(GameAppOperation.GAME_UNION_ID, jSONObject.opt(GameAppOperation.GAME_UNION_ID).toString());
            hashMap.put("sex", jSONObject.opt("sex").toString());
            JSONArray jSONArray = jSONObject.getJSONArray("privilege");
            int length = jSONArray.length();
            if (length <= 0) {
                return hashMap;
            }
            Object obj = new String[length];
            for (int i = 0; i < length; i++) {
                obj[i] = jSONArray.get(i).toString();
            }
            hashMap.put("privilege", obj.toString());
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (activity == null) {
            Log.d("UMError", "Weixin share activity is null");
            return false;
        }
        this.mTarget = this.config.getName();
        if (isClientInstalled()) {
            this.mShareContent = new WeiXinShareContent(shareContent);
            if (this.mShareContent != null) {
                this.mShareContent.parseMediaType();
                String str = this.mShareContent.mShareType;
                WeiXinShareContent weiXinShareContent = this.mShareContent;
                if (str == WeiXinShareContent.TYPE_EMOJI && this.isToCircle) {
                    Toast.makeText(activity, "微信朋友圈不支持表情分享...", 0).show();
                    return false;
                }
            }
            this.umShareListener = uMShareListener;
            return shareTo(new WeiXinShareContent(shareContent));
        } else if (!Config.IsToastTip) {
            return false;
        } else {
            Toast.makeText(activity, "你还没有安装微信", 0).show();
            return false;
        }
    }

    public boolean isClientInstalled() {
        return this.mWXApi.isWXAppInstalled();
    }

    public boolean shareTo(WeiXinShareContent weiXinShareContent) {
        BaseReq req = new SendMessageToWX.Req();
        req.transaction = buildTransaction(this.mShareContent.mShareType);
        req.message = weiXinShareContent.getWxMediaMessage();
        switch (this.mTarget) {
            case WEIXIN:
                req.scene = 0;
                break;
            case WEIXIN_CIRCLE:
                req.scene = 1;
                break;
            case WEIXIN_FAVORITE:
                req.scene = 2;
                break;
            default:
                req.scene = 2;
                break;
        }
        if (req.message == null) {
            Log.e("wx,message = null");
            return false;
        } else if (req.message.mediaObject != null) {
            return this.mWXApi.sendReq(req);
        } else {
            Log.e("wx,mediaobject = null");
            return false;
        }
    }

    protected void onShareCallback(SendMessageToWX.Resp resp) {
        switch (resp.errCode) {
            case -3:
            case -1:
                if (this.umShareListener != null) {
                    this.umShareListener.onError(this.mTarget, new SocializeException(resp.errCode, resp.errStr));
                    return;
                }
                return;
            case -2:
                if (this.umShareListener != null) {
                    this.umShareListener.onCancel(this.mTarget);
                    return;
                }
                return;
            case 0:
                if (this.umShareListener != null) {
                    Map hashMap = new HashMap();
                    hashMap.put("uid", resp.openId);
                    uploadAuthData(hashMap);
                    this.umShareListener.onResult(this.mTarget);
                    return;
                }
                return;
            default:
                Log.d(TAG, "微信发送 -- 未知错误.");
                return;
        }
    }

    public IWXAPIEventHandler getWXEventHandler() {
        return this.mEventHandler;
    }

    public IWXAPI getWXApi() {
        return this.mWXApi;
    }

    private String buildTransaction(String str) {
        if (str == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        return str + System.currentTimeMillis();
    }
}
