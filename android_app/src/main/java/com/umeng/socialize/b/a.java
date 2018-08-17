package com.umeng.socialize.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import com.tencent.connect.common.Constants;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.a.c;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.UrlRequest;
import com.umeng.socialize.net.UrlResponse;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.view.UMFriendListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: SocialRouter */
public final class a {
    private int a = 0;
    private final Map<SHARE_MEDIA, UMSSOHandler> b = new HashMap();
    private final List<Pair<SHARE_MEDIA, String>> c = new ArrayList();
    private a d;
    private Context e;

    /* compiled from: SocialRouter */
    static class a {
        private Map<SHARE_MEDIA, UMSSOHandler> a;

        public a(Map<SHARE_MEDIA, UMSSOHandler> map) {
            this.a = map;
        }

        public boolean a(Context context, SHARE_MEDIA share_media) {
            if (!a(context)) {
                return false;
            }
            if (!a(share_media)) {
                return false;
            }
            if (((UMSSOHandler) this.a.get(share_media)).isSupportAuth()) {
                return true;
            }
            Log.w(share_media.toString() + "平台不支持授权,无法完成操作");
            return false;
        }

        public boolean a(Activity activity, ShareAction shareAction) {
            if (!a((Context) activity)) {
                return false;
            }
            SHARE_MEDIA platform = shareAction.getPlatform();
            if (platform == null || !a(platform)) {
                return false;
            }
            return true;
        }

        private boolean a(Context context) {
            if (context != null) {
                return true;
            }
            Log.e("Context is null");
            return false;
        }

        private boolean a(SHARE_MEDIA share_media) {
            Platform platform = (Platform) PlatformConfig.configs.get(share_media);
            if (platform != null && !platform.isConfigured()) {
                Log.e(share_media + ": 没有配置相关的Appkey、Secret");
                return false;
            } else if (((UMSSOHandler) this.a.get(share_media)) != null) {
                return true;
            } else {
                Log.e("没有配置 " + share_media + " 的jar包");
                return false;
            }
        }
    }

    public a(Context context) {
        List list = this.c;
        String str = "com.umeng.socialize.handler.";
        list.add(new Pair(SHARE_MEDIA.LAIWANG, "com.umeng.socialize.handler.UMLWHandler"));
        list.add(new Pair(SHARE_MEDIA.LAIWANG_DYNAMIC, "com.umeng.socialize.handler.UMLWHandler"));
        list.add(new Pair(SHARE_MEDIA.SINA, "com.umeng.socialize.handler.SinaSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.PINTEREST, "com.umeng.socialize.handler.UMPinterestHandler"));
        list.add(new Pair(SHARE_MEDIA.QZONE, "com.umeng.socialize.handler.QZoneSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.QQ, "com.umeng.socialize.handler.UMQQSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.RENREN, "com.umeng.socialize.handler.RenrenSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.TENCENT, "com.umeng.socialize.handler.QQwbHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN, "com.umeng.socialize.handler.UMWXHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN_CIRCLE, "com.umeng.socialize.handler.UMWXHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN_FAVORITE, "com.umeng.socialize.handler.UMWXHandler"));
        list.add(new Pair(SHARE_MEDIA.YIXIN, "com.umeng.socialize.handler.UMYXHandler"));
        list.add(new Pair(SHARE_MEDIA.YIXIN_CIRCLE, "com.umeng.socialize.handler.UMYXHandler"));
        list.add(new Pair(SHARE_MEDIA.EMAIL, "com.umeng.socialize.handler.EmailHandler"));
        list.add(new Pair(SHARE_MEDIA.EVERNOTE, "com.umeng.socialize.handler.UMEvernoteHandler"));
        list.add(new Pair(SHARE_MEDIA.FACEBOOK, "com.umeng.socialize.handler.UMFacebookHandler"));
        list.add(new Pair(SHARE_MEDIA.FLICKR, "com.umeng.socialize.handler.UMFlickrHandler"));
        list.add(new Pair(SHARE_MEDIA.FOURSQUARE, "com.umeng.socialize.handler.UMFourSquareHandler"));
        list.add(new Pair(SHARE_MEDIA.GOOGLEPLUS, "com.umeng.socialize.handler.UMGooglePlusHandler"));
        list.add(new Pair(SHARE_MEDIA.INSTAGRAM, "com.umeng.socialize.handler.UMInstagramHandler"));
        list.add(new Pair(SHARE_MEDIA.KAKAO, "com.umeng.socialize.handler.UMKakaoHandler"));
        list.add(new Pair(SHARE_MEDIA.LINE, "com.umeng.socialize.handler.UMLineHandler"));
        list.add(new Pair(SHARE_MEDIA.LINKEDIN, "com.umeng.socialize.handler.UMLinkedInHandler"));
        list.add(new Pair(SHARE_MEDIA.POCKET, "com.umeng.socialize.handler.UMPocketHandler"));
        list.add(new Pair(SHARE_MEDIA.WHATSAPP, "com.umeng.socialize.handler.UMWhatsAppHandler"));
        list.add(new Pair(SHARE_MEDIA.YNOTE, "com.umeng.socialize.handler.UMYNoteHandler"));
        list.add(new Pair(SHARE_MEDIA.SMS, "com.umeng.socialize.handler.SmsHandler"));
        list.add(new Pair(SHARE_MEDIA.DOUBAN, "com.umeng.socialize.handler.DoubanHandler"));
        list.add(new Pair(SHARE_MEDIA.TUMBLR, "com.umeng.socialize.handler.UMTumblrHandler"));
        list.add(new Pair(SHARE_MEDIA.TWITTER, "com.umeng.socialize.handler.TwitterHandler"));
        list.add(new Pair(SHARE_MEDIA.ALIPAY, "com.umeng.socialize.handler.AlipayHandler"));
        this.d = new a(this.b);
        this.e = null;
        this.e = context;
        a();
    }

    private void a() {
        for (Pair pair : this.c) {
            Object obj;
            if (pair.first == SHARE_MEDIA.WEIXIN_CIRCLE || pair.first == SHARE_MEDIA.WEIXIN_FAVORITE) {
                obj = (UMSSOHandler) this.b.get(SHARE_MEDIA.WEIXIN);
            } else if (pair.first == SHARE_MEDIA.YIXIN_CIRCLE) {
                r1 = (UMSSOHandler) this.b.get(SHARE_MEDIA.YIXIN);
            } else if (pair.first == SHARE_MEDIA.LAIWANG_DYNAMIC) {
                r1 = (UMSSOHandler) this.b.get(SHARE_MEDIA.LAIWANG);
            } else if (pair.first != SHARE_MEDIA.TENCENT) {
                obj = a((String) pair.second);
            } else if (Config.WBBYQQ) {
                obj = a((String) pair.second);
            } else {
                obj = a("com.umeng.socialize.handler.TencentWBSsoHandler");
            }
            this.b.put(pair.first, obj);
        }
    }

    private UMSSOHandler a(String str) {
        try {
            return (UMSSOHandler) Class.forName(str).newInstance();
        } catch (Exception e) {
            Log.v("xxxx", "ignore=" + e);
            return null;
        }
    }

    public UMSSOHandler a(SHARE_MEDIA share_media) {
        UMSSOHandler uMSSOHandler = (UMSSOHandler) this.b.get(share_media);
        if (uMSSOHandler != null) {
            uMSSOHandler.onCreate(this.e, PlatformConfig.getPlatform(share_media));
        }
        return uMSSOHandler;
    }

    public void a(int i, int i2, Intent intent) {
        UMSSOHandler a = a(i);
        if (a != null) {
            a.onActivityResult(i, i2, intent);
        }
    }

    private UMSSOHandler a(int i) {
        int i2 = Constants.REQUEST_QQ_SHARE;
        if (!(i == Constants.REQUEST_QQ_SHARE || i == Constants.REQUEST_LOGIN)) {
            i2 = i;
        }
        if (i == HandlerRequestCode.FACEBOOK_REQUEST_SHARE_CODE || i == HandlerRequestCode.FACEBOOK_REQUEST_AUTH_CODE) {
            i2 = 64206;
        }
        int i3;
        if (i == HandlerRequestCode.SINA_AUTH_REQUEST_CODE || i == 765) {
            i3 = 5659;
        } else {
            i3 = i2;
        }
        for (UMSSOHandler uMSSOHandler : this.b.values()) {
            if (uMSSOHandler != null && r1 == uMSSOHandler.getRequestCode()) {
                return uMSSOHandler;
            }
        }
        return null;
    }

    public void a(SHARE_MEDIA share_media, UMSSOHandler uMSSOHandler) {
        if (share_media == null || uMSSOHandler == null) {
            Log.e("SHARE_MEDIA or UMSSOHandler is null");
        } else {
            this.b.put(share_media, uMSSOHandler);
        }
    }

    public void a(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (this.d.a((Context) activity, share_media)) {
            if (uMAuthListener == null) {
                uMAuthListener = new b(this);
            }
            ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
            ((UMSSOHandler) this.b.get(share_media)).deleteAuth(activity, uMAuthListener);
        }
    }

    public void b(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (this.d.a((Context) activity, share_media)) {
            if (uMAuthListener == null) {
                uMAuthListener = new c(this);
            }
            ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
            ((UMSSOHandler) this.b.get(share_media)).getPlatformInfo(activity, uMAuthListener);
        }
    }

    public void a(Activity activity, SHARE_MEDIA share_media, UMFriendListener uMFriendListener) {
        if (this.d.a((Context) activity, share_media)) {
            if (uMFriendListener == null) {
                uMFriendListener = new d(this);
            }
            ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
            ((UMSSOHandler) this.b.get(share_media)).getfriend(activity, uMFriendListener);
        }
    }

    public boolean a(Activity activity, SHARE_MEDIA share_media) {
        if (!this.d.a((Context) activity, share_media)) {
            return false;
        }
        ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return ((UMSSOHandler) this.b.get(share_media)).isInstall(activity);
    }

    public boolean b(Activity activity, SHARE_MEDIA share_media) {
        if (!this.d.a((Context) activity, share_media)) {
            return false;
        }
        ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return ((UMSSOHandler) this.b.get(share_media)).isAuthorize(activity);
    }

    public void c(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (this.d.a((Context) activity, share_media)) {
            UMSSOHandler uMSSOHandler = (UMSSOHandler) this.b.get(share_media);
            uMSSOHandler.onCreate(activity, PlatformConfig.getPlatform(share_media));
            uMSSOHandler.authorize(activity, uMAuthListener);
        }
    }

    public void a(Activity activity, ShareAction shareAction, UMShareListener uMShareListener) {
        if (this.d.a(activity, shareAction)) {
            if (uMShareListener == null) {
                uMShareListener = new e(this);
            }
            SHARE_MEDIA platform = shareAction.getPlatform();
            UMSSOHandler uMSSOHandler = (UMSSOHandler) this.b.get(platform);
            uMSSOHandler.setCaller(shareAction.getFrom());
            uMSSOHandler.onCreate(activity, PlatformConfig.getPlatform(platform));
            if (!(platform.toString().equals("TENCENT") || platform.toString().equals("RENREN") || platform.toString().equals("DOUBAN"))) {
                if (platform.toString().equals("WEIXIN")) {
                    c.a(activity, "wxsession", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                    c.a(activity, "wxtimeline", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else if (platform.toString().equals("WEIXIN_FAVORITE")) {
                    c.a(activity, "wxfavorite", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else {
                    c.a(activity, platform.toString().toLowerCase(), shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                }
            }
            if (platform.toString().equals("TENCENT") && Config.WBBYQQ) {
                c.a(activity, platform.toString().toLowerCase(), shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
            }
            if (Config.isloadUrl) {
                a(activity, shareAction);
            }
            uMSSOHandler.share(activity, shareAction.getShareContent(), uMShareListener);
        }
    }

    private void a(Activity activity, ShareAction shareAction) {
        Object obj = shareAction.getShareContent().mTargetUrl;
        if (!TextUtils.isEmpty(obj)) {
            String str;
            if (shareAction.getPlatform().toString().equals("WEIXIN")) {
                str = "wxsession";
            } else if (shareAction.getPlatform().toString().equals("")) {
                str = "wxtimeline";
            } else {
                str = shareAction.getPlatform().toString().toLowerCase();
            }
            UrlResponse uploadUrl = RestAPI.uploadUrl(new UrlRequest(activity, str, obj));
            Log.e("xxxxxx resp" + uploadUrl);
            if (uploadUrl == null || uploadUrl.mStCode != 200) {
                Log.e("upload url fail ");
            } else {
                shareAction.withTargetUrl(uploadUrl.result);
            }
        }
    }
}
