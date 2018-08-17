package com.umeng.socialize.media;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.ShareContent;
import java.util.ArrayList;

public class QZoneShareContent extends QQShareContent {
    private UMediaObject uMedia;

    public QZoneShareContent(ShareContent shareContent) {
        super(shareContent);
        this.uMedia = shareContent.mMedia;
    }

    public UMediaObject getMedia() {
        return this.uMedia;
    }

    public Bundle buildParamsQzone() {
        Bundle bundle = new Bundle();
        String text = getText();
        int i = 1;
        UMediaObject uMediaObject = this.uMedia;
        if ((uMediaObject instanceof UMImage) && TextUtils.isEmpty(getText())) {
            i = 5;
            setShareToImage(bundle);
        } else if ((uMediaObject instanceof UMVideo) || (uMediaObject instanceof UMusic)) {
            i = 2;
            setShareToAudio(bundle);
        } else {
            setShareToTextAndImage(bundle);
        }
        bundle.putString("summary", text);
        ArrayList arrayList = new ArrayList();
        CharSequence string = bundle.getString("imageUrl");
        bundle.remove("imageUrl");
        if (!TextUtils.isEmpty(string)) {
            arrayList.add(string);
        }
        bundle.putStringArrayList("imageUrl", arrayList);
        bundle.putInt("req_type", i);
        if (TextUtils.isEmpty(bundle.getString("title"))) {
            bundle.putString("title", "分享到QQ空间");
        }
        if (TextUtils.isEmpty(bundle.getString("targetUrl"))) {
            bundle.putString("targetUrl", "http://wsq.umeng.com/");
        }
        this.mExtraData.clear();
        return bundle;
    }

    private void setShareToTextAndImage(Bundle bundle) {
        setShareToImage(bundle);
    }

    private void setShareToAudio(Bundle bundle) {
        if (getMusic() != null || getVideo() != null) {
            UMediaObject music;
            if (getMusic() != null) {
                parseMusic();
            } else if (getVideo() != null) {
                parseVideo();
            }
            String str = (String) this.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
            if (TextUtils.isEmpty(str)) {
                str = (String) this.mExtraData.get(Constant.IMAGE_PATH_URL);
            }
            if (getMusic() != null) {
                music = getMusic();
            } else {
                music = getVideo();
            }
            bundle.putString("imageUrl", str);
            bundle.putString("targetUrl", getTargeturl());
            bundle.putString("audio_url", music.toUrl());
            bundle.putString("title", getTitle());
        }
    }

    private void setShareToImage(Bundle bundle) {
        parseImage(getImage());
        String str = (String) this.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
        if (TextUtils.isEmpty(str)) {
            str = (String) this.mExtraData.get(Constant.IMAGE_PATH_URL);
        }
        bundle.putString("imageUrl", str);
        if (TextUtils.isEmpty(getTargeturl())) {
            setTargeturl((String) this.mExtraData.get(Constant.IMAGE_TARGETURL));
        }
        if (TextUtils.isEmpty(getTargeturl())) {
            setTargeturl("http://wsq.umeng.com/");
        }
        bundle.putString("targetUrl", getTargeturl());
        bundle.putString("title", getTitle());
    }
}
