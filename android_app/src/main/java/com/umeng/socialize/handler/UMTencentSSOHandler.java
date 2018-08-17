package com.umeng.socialize.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.PlatformConfig.QQZone;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.UploadImageRequest;
import com.umeng.socialize.utils.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class UMTencentSSOHandler extends UMSSOHandler {
    private static final String PUBLIC_ACCOUNT = "100424468";
    private static final String TAG = "UMTencentSSOHandler";
    protected static Map<String, String> mImageCache = new HashMap();
    public QQZone config = null;
    private Context context;
    protected UMAuthListener mAuthListener;
    protected String mImageUrl = null;
    protected ProgressDialog mProgressDialog = null;
    protected UMShareListener mShareListener;
    protected Tencent mTencent;

    public interface ObtainImageUrlListener {
        void onComplete(String str);
    }

    protected interface ObtainAppIdListener {
        void onComplete();
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        this.context = context;
        this.config = (QQZone) platform;
        Log.d("appid", "appid qq:" + this.config.appId);
        this.mTencent = Tencent.createInstance(this.config.appId, context);
        android.util.Log.e("xxxx", "tencent=" + this.mTencent.getOpenId());
        if (this.mTencent == null) {
            Log.e(TAG, "Tencent变量初始化失败，请检查你的app id跟AndroidManifest.xml文件中AuthActivity的scheme是否填写正确");
        }
    }

    protected boolean isUploadImageAsync(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        SHARE_MEDIA name = this.config.getName();
        boolean isClientInstalled = isClientInstalled(this.context);
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

    protected Bundle parseOauthData(Object obj) {
        Bundle bundle = new Bundle();
        if (obj != null) {
            Object trim = obj.toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                JSONObject jSONObject;
                try {
                    jSONObject = new JSONObject(trim);
                } catch (JSONException e) {
                    e.printStackTrace();
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    bundle.putString("auth_time", jSONObject.optString("auth_time", ""));
                    bundle.putString("pay_token", jSONObject.optString("pay_token", ""));
                    bundle.putString(Constants.PARAM_PLATFORM_ID, jSONObject.optString(Constants.PARAM_PLATFORM_ID, ""));
                    bundle.putString("ret", String.valueOf(jSONObject.optInt("ret", -1)));
                    bundle.putString("sendinstall", jSONObject.optString("sendinstall", ""));
                    bundle.putString("page_type", jSONObject.optString("page_type", ""));
                    bundle.putString("appid", jSONObject.optString("appid", ""));
                    bundle.putString("openid", jSONObject.optString("openid", ""));
                    bundle.putString("uid", jSONObject.optString("openid", ""));
                    bundle.putString("expires_in", jSONObject.optString("expires_in", ""));
                    bundle.putString("pfkey", jSONObject.optString("pfkey", ""));
                    bundle.putString("access_token", jSONObject.optString("access_token", ""));
                }
            }
        }
        return bundle;
    }

    public boolean isClientInstalled(Context context) {
        return this.mTencent.isSupportSSOLogin((Activity) context);
    }

    protected String getAppName() {
        if (!TextUtils.isEmpty(Config.QQAPPNAME)) {
            return Config.QQAPPNAME;
        }
        String str = "";
        if (this.context == null) {
            return str;
        }
        CharSequence loadLabel = this.context.getApplicationInfo().loadLabel(this.context.getPackageManager());
        if (TextUtils.isEmpty(loadLabel)) {
            return str;
        }
        return loadLabel.toString();
    }

    public void getBitmapUrl(UMediaObject uMediaObject, String str, ObtainImageUrlListener obtainImageUrlListener) {
        System.currentTimeMillis();
        if (uMediaObject instanceof UMImage) {
            uMediaObject = (UMImage) uMediaObject;
        } else {
            uMediaObject = null;
        }
        if (uMediaObject != null) {
            String file = uMediaObject.asFileImage().toString();
            String str2 = (String) mImageCache.get(file);
            if (TextUtils.isEmpty(str2)) {
                Log.i(TAG, "obtain image url form server...");
                CharSequence toGetUrl = new UploadImageRequest(this.context, uMediaObject, str).toGetUrl();
                setImageUrl(file, toGetUrl);
                if (this.context != null && TextUtils.isEmpty(toGetUrl)) {
                    Toast.makeText(this.context, "上传图片失败", 0).show();
                }
                Log.i(TAG, "obtain image url form server..." + this.mImageUrl);
            } else {
                this.mImageUrl = str2;
                Log.i(TAG, "obtain image url form cache..." + this.mImageUrl);
            }
        }
        Log.i(TAG, "doInBackground end...");
        obtainImageUrlListener.onComplete(this.mImageUrl);
    }

    private void setImageUrl(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            mImageCache.put(str, str2);
            this.mImageUrl = str2;
        }
    }

    protected boolean validTencent() {
        return this.mTencent != null && this.mTencent.getAppId().equals(this.config.appId);
    }
}
