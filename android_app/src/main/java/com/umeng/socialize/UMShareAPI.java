package com.umeng.socialize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork.UMAsyncTask;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.net.ActionBarRequest;
import com.umeng.socialize.net.ActionBarResponse;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.view.UMFriendListener;

public class UMShareAPI {
    private static UMShareAPI b = null;
    com.umeng.socialize.b.a a;

    static class a extends UMAsyncTask<Void> {
        private Context a;

        protected /* synthetic */ Object doInBackground() {
            return a();
        }

        public a(Context context) {
            this.a = context;
        }

        protected Void a() {
            ActionBarResponse queryShareId = RestAPI.queryShareId(new ActionBarRequest(this.a, c()));
            if (queryShareId != null && queryShareId.isOk()) {
                b();
                Log.i("response: " + queryShareId.mMsg);
                Config.EntityKey = queryShareId.mEntityKey;
                Config.SessionId = queryShareId.mSid;
                Config.UID = queryShareId.mUid;
            }
            Log.i("response has error: " + (queryShareId == null ? "null" : queryShareId.mMsg));
            return null;
        }

        private boolean c() {
            return this.a.getSharedPreferences(SocializeConstants.SOCIAL_PREFERENCE_NAME, 0).getBoolean("newinstall", false);
        }

        public void b() {
            Editor edit = this.a.getSharedPreferences(SocializeConstants.SOCIAL_PREFERENCE_NAME, 0).edit();
            edit.putBoolean("newinstall", true);
            edit.commit();
        }
    }

    private UMShareAPI(Context context) {
        ContextUtil.setContext(context);
        if (VERSION.SDK_INT >= 23) {
            String[] strArr = new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.CALL_PHONE", "android.permission.READ_LOGS", MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, "android.permission.SET_DEBUG_APP", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.GET_ACCOUNTS"};
            Log.d("check", "check permission");
            for (String str : strArr) {
                try {
                    if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() == 0) {
                        Log.d("Umeng", "PERMISSION_GRANTED:" + str);
                    } else {
                        Log.e("Umeng Error", "PERMISSION_MISSING:" + str);
                    }
                } catch (Exception e) {
                    Log.e("Umeng Error", "error" + e.getMessage());
                }
            }
        }
        this.a = new com.umeng.socialize.b.a(context);
        new a(context).execute();
    }

    public static UMShareAPI get(Context context) {
        if (b == null || b.a == null) {
            b = new UMShareAPI(context);
        }
        return b;
    }

    public void doOauthVerify(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (activity != null) {
            new e(this, activity, activity, share_media, uMAuthListener).execute();
        } else {
            Log.d("UMerror", "doOauthVerify activity is null");
        }
    }

    public void deleteOauth(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (activity != null) {
            new f(this, activity, activity, share_media, uMAuthListener).execute();
        } else {
            Log.d("UMerror", "deleteOauth activity is null");
        }
    }

    public void getPlatformInfo(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (activity != null) {
            new g(this, activity, activity, share_media, uMAuthListener).execute();
        } else {
            Log.d("UMerror", "getPlatformInfo activity argument is null");
        }
    }

    public boolean isInstall(Activity activity, SHARE_MEDIA share_media) {
        if (this.a != null) {
            return this.a.a(activity, share_media);
        }
        this.a = new com.umeng.socialize.b.a(activity);
        return this.a.a(activity, share_media);
    }

    public boolean isAuthorize(Activity activity, SHARE_MEDIA share_media) {
        if (this.a != null) {
            return this.a.b(activity, share_media);
        }
        this.a = new com.umeng.socialize.b.a(activity);
        return this.a.b(activity, share_media);
    }

    public void getFriend(Activity activity, SHARE_MEDIA share_media, UMFriendListener uMFriendListener) {
        if (activity != null) {
            new h(this, activity, activity, share_media, uMFriendListener).execute();
        } else {
            Log.d("UMerror", "getFriend activity is null");
        }
    }

    public void doShare(Activity activity, ShareAction shareAction, UMShareListener uMShareListener) {
        if (activity != null) {
            new i(this, activity, activity, shareAction, uMShareListener).execute();
        } else {
            Log.d("UMerror", "Share activity is null");
        }
    }

    public void openShare(ShareAction shareAction, UMShareListener uMShareListener) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.a != null) {
            this.a.a(i, i2, intent);
        } else {
            Log.v("auth fail", "router=null");
        }
    }

    public UMSSOHandler getHandler(SHARE_MEDIA share_media) {
        if (this.a != null) {
            return this.a.a(share_media);
        }
        return null;
    }
}
