package com.umeng.socialize;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.shareboard.a;
import com.umeng.socialize.utils.ShareBoardlistener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareAction {
    private ShareContent a = new ShareContent();
    private String b = null;
    private SHARE_MEDIA c = null;
    private UMShareListener d = null;
    private ShareBoardlistener e = null;
    private Activity f;
    private List<SHARE_MEDIA> g = null;
    private List<SnsPlatform> h = new ArrayList();
    private List<ShareContent> i = new ArrayList();
    private List<UMShareListener> j = new ArrayList();
    private int k = 80;
    private View l = null;
    private ShareBoardlistener m = new b(this);
    private ShareBoardlistener n = new c(this);

    public ShareAction(Activity activity) {
        if (activity != null) {
            this.f = (Activity) new WeakReference(activity).get();
        }
    }

    public void openBoard() {
    }

    public ShareContent getShareContent() {
        return this.a;
    }

    public String getFrom() {
        return this.b;
    }

    public SHARE_MEDIA getPlatform() {
        return this.c;
    }

    public ShareAction setPlatform(SHARE_MEDIA share_media) {
        this.c = share_media;
        return this;
    }

    public ShareAction setCallback(UMShareListener uMShareListener) {
        this.d = uMShareListener;
        return this;
    }

    public ShareAction setShareboardclickCallback(ShareBoardlistener shareBoardlistener) {
        this.e = shareBoardlistener;
        return this;
    }

    public ShareAction setShareContent(ShareContent shareContent) {
        this.a = shareContent;
        return this;
    }

    public ShareAction setDisplayList(SHARE_MEDIA... share_mediaArr) {
        this.g = Arrays.asList(share_mediaArr);
        this.h.clear();
        for (SHARE_MEDIA toSnsPlatform : this.g) {
            this.h.add(toSnsPlatform.toSnsPlatform());
        }
        return this;
    }

    public ShareAction setListenerList(UMShareListener... uMShareListenerArr) {
        this.j = Arrays.asList(uMShareListenerArr);
        return this;
    }

    public ShareAction setContentList(ShareContent... shareContentArr) {
        if (shareContentArr == null || Arrays.asList(shareContentArr).size() == 0) {
            ShareContent shareContent = new ShareContent();
            shareContent.mText = "友盟分享";
            this.i.add(shareContent);
        } else {
            this.i = Arrays.asList(shareContentArr);
        }
        return this;
    }

    public ShareAction addButton(String str, String str2, String str3, String str4) {
        this.h.add(SHARE_MEDIA.createSnsPlatform(str, str2, str3, str4, 0));
        return this;
    }

    public ShareAction withText(String str) {
        this.a.mText = str;
        return this;
    }

    public ShareAction withTitle(String str) {
        this.a.mTitle = str;
        return this;
    }

    public ShareAction withTargetUrl(String str) {
        this.a.mTargetUrl = str;
        return this;
    }

    public ShareAction withMedia(UMImage uMImage) {
        this.a.mMedia = uMImage;
        return this;
    }

    public ShareAction withMedia(UMEmoji uMEmoji) {
        this.a.mMedia = uMEmoji;
        return this;
    }

    public ShareAction withFollow(String str) {
        this.a.mFollow = str;
        return this;
    }

    public ShareAction withExtra(UMImage uMImage) {
        this.a.mExtra = uMImage;
        return this;
    }

    public ShareAction withMedia(UMusic uMusic) {
        this.a.mMedia = uMusic;
        return this;
    }

    public ShareAction withMedia(UMVideo uMVideo) {
        this.a.mMedia = uMVideo;
        return this;
    }

    public ShareAction withShareBoardDirection(View view, int i) {
        this.k = i;
        this.l = view;
        return this;
    }

    public void share() {
        UMShareAPI.get(this.f).doShare(this.f, this, this.d);
    }

    public void open() {
        a aVar;
        if (this.h.size() != 0) {
            Map hashMap = new HashMap();
            hashMap.put("listener", this.d);
            hashMap.put("content", this.a);
            aVar = new a(this.f, this.h);
            if (this.e == null) {
                aVar.a(this.n);
            } else {
                aVar.a(this.e);
            }
            aVar.setFocusable(true);
            aVar.setBackgroundDrawable(new BitmapDrawable());
            if (this.l == null) {
                this.l = this.f.getWindow().getDecorView();
            }
            aVar.showAtLocation(this.l, this.k, 0, 0);
            return;
        }
        this.h.add(SHARE_MEDIA.WEIXIN.toSnsPlatform());
        this.h.add(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform());
        this.h.add(SHARE_MEDIA.SINA.toSnsPlatform());
        this.h.add(SHARE_MEDIA.QQ.toSnsPlatform());
        hashMap = new HashMap();
        hashMap.put("listener", this.d);
        hashMap.put("content", this.a);
        aVar = new a(this.f, this.h);
        if (this.i.size() == 0) {
            if (this.e == null) {
                aVar.a(this.m);
            } else {
                aVar.a(this.e);
            }
        } else if (this.e == null) {
            aVar.a(this.n);
        } else {
            aVar.a(this.e);
        }
        aVar.setFocusable(true);
        aVar.setBackgroundDrawable(new BitmapDrawable());
        if (this.l == null) {
            this.l = this.f.getWindow().getDecorView();
        }
        aVar.showAtLocation(this.l, 80, 0, 0);
    }

    public static Rect locateView(View view) {
        int[] iArr = new int[2];
        if (view == null) {
            return null;
        }
        try {
            view.getLocationOnScreen(iArr);
            Rect rect = new Rect();
            rect.left = iArr[0];
            rect.top = iArr[1];
            rect.right = rect.left + view.getWidth();
            rect.bottom = rect.top + view.getHeight();
            return rect;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
