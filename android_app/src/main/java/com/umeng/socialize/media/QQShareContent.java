package com.umeng.socialize.media;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.Log;
import java.util.HashMap;
import java.util.Map;

public class QQShareContent extends SimpleShareContent {
    protected static final String DEFAULT_TARGET_URL = "http://wsq.umeng.com/";
    public Map<String, String> mExtraData = new HashMap();
    public int mShareType = 1;

    public QQShareContent(ShareContent shareContent) {
        super(shareContent);
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMusic)) {
            setMusic((UMusic) shareContent.mMedia);
        }
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMVideo)) {
            setVideo((UMVideo) shareContent.mMedia);
        }
    }

    public Bundle buildParams() {
        Bundle bundle = new Bundle();
        bundle.putString("summary", getText());
        if (getImage() != null && TextUtils.isEmpty(getText())) {
            this.mShareType = 5;
            buildImageParams(bundle);
        } else if (getVideo() == null && getMusic() == null) {
            buildTextImageParams(bundle);
        } else {
            this.mShareType = 2;
            buildAudioParams(bundle);
        }
        bundle.putInt("req_type", this.mShareType);
        if (TextUtils.isEmpty(getTargeturl())) {
            setTargeturl(DEFAULT_TARGET_URL);
        }
        bundle.putString("targetUrl", getTargeturl());
        if (TextUtils.isEmpty(getTitle())) {
            bundle.putString("title", " ");
        } else {
            bundle.putString("title", getTitle());
        }
        if (Config.QQWITHQZONE == 1) {
            bundle.putInt("cflag", 1);
        } else if (Config.QQWITHQZONE == 2) {
            bundle.putInt("cflag", 2);
        }
        if (!TextUtils.isEmpty(Config.appName)) {
            bundle.putString("appName", Config.appName);
        }
        return bundle;
    }

    private void buildImageParams(Bundle bundle) {
        parseImage(getImage());
        String str = (String) this.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
        String str2 = (String) this.mExtraData.get(Constant.IMAGE_PATH_URL);
        if (!TextUtils.isEmpty(str) && BitmapUtils.isFileExist(str)) {
            bundle.putString("imageLocalUrl", str);
        } else if (!TextUtils.isEmpty(str2)) {
            bundle.putString("imageUrl", str2);
        }
    }

    private void buildTextImageParams(Bundle bundle) {
        buildImageParams(bundle);
    }

    private void buildAudioParams(Bundle bundle) {
        UMediaObject uMediaObject;
        if (getMusic() != null) {
            UMusic music = getMusic();
            parseMusic();
            uMediaObject = music;
        } else if (getVideo() != null) {
            UMVideo video = getVideo();
            parseVideo();
            Object obj = video;
        } else {
            uMediaObject = null;
        }
        String str = (String) this.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
        String str2 = (String) this.mExtraData.get(Constant.IMAGE_PATH_URL);
        if (!TextUtils.isEmpty(str) && BitmapUtils.isFileExist(str)) {
            bundle.putString("imageLocalUrl", str);
        } else if (!TextUtils.isEmpty(str2)) {
            bundle.putString("imageUrl", str2);
        }
        bundle.putString("audio_url", uMediaObject.toUrl());
    }

    protected void parseImage(UMImage uMImage) {
        if (uMImage != null) {
            String str = "";
            String str2 = "";
            Log.v("10.12", "image=" + uMImage);
            if (TextUtils.isEmpty(getTargeturl())) {
                if (TextUtils.isEmpty(uMImage.getTargetUrl())) {
                    setTargeturl(uMImage.toUrl());
                } else {
                    setTargeturl(uMImage.getTargetUrl());
                }
            }
            str2 = uMImage.toUrl();
            if (uMImage.asFileImage() != null) {
                str = uMImage.asFileImage().toString();
            }
            if (!BitmapUtils.isFileExist(str)) {
                str = "";
            }
            Log.v("10.12", "image path =" + str);
            this.mExtraData.put(Constant.IMAGE_PATH_LOCAL, str);
            this.mExtraData.put(Constant.IMAGE_PATH_URL, str2);
        }
    }

    protected void parseMusic() {
        UMusic music = getMusic();
        this.mExtraData.put("audio_url", music.toUrl());
        boolean isEmpty = TextUtils.isEmpty(getTargeturl());
        if (TextUtils.isEmpty(music.getThumb())) {
            parseImage(music.getThumbImage());
        } else {
            this.mExtraData.put(Constant.IMAGE_PATH_URL, music.getThumb());
        }
        if (!TextUtils.isEmpty(music.getTitle())) {
            setTitle(music.getTitle());
        }
        if (!isEmpty) {
            return;
        }
        if (TextUtils.isEmpty(music.getTargetUrl())) {
            setTargeturl(music.toUrl());
        } else {
            setTargeturl(music.getTargetUrl());
        }
    }

    protected void parseVideo() {
        UMVideo video = getVideo();
        this.mExtraData.put("audio_url", video.toUrl());
        boolean isEmpty = TextUtils.isEmpty(getTargeturl());
        if (TextUtils.isEmpty(video.getThumb())) {
            parseImage(video.getThumbImage());
        } else {
            this.mExtraData.put(Constant.IMAGE_PATH_URL, video.getThumb());
        }
        if (!TextUtils.isEmpty(video.getTitle())) {
            setTitle(video.getTitle());
        }
        if (!isEmpty) {
            return;
        }
        if (TextUtils.isEmpty(video.getTargetUrl())) {
            setTargeturl(video.toUrl());
        } else {
            setTargeturl(video.getTargetUrl());
        }
    }
}
