package com.umeng.socialize.media;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.tencent.mm.sdk.modelmsg.WXEmojiObject;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class WeiXinShareContent {
    public static final String TYPE_EMOJI = "emoji";
    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_MUSIC = "music";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_TEXT_IMAGE = "text_image";
    public static final String TYPE_VIDEO = "video";
    private final String DEFAULT_TITLE = "分享到微信";
    private final int DESCRIPTION_LIMIT = 1024;
    private final int IMAGE_LIMIT = 102400;
    private final int SHOW_COMPRESS_TOAST = 1;
    private final int SHOW_TITLE_TOAST = 2;
    private final int THUMB_LIMIT = 32768;
    private final int THUMB_SIZE = 150;
    private final int TITLE_LIMIT = 512;
    private ShareContent mShareContent;
    public String mShareType;
    private String mTargetUrl;
    private String mText;
    private String mTitle;
    private WXMediaMessage mWxMediaMessage = null;
    private UMediaObject uMediaObject;

    public WeiXinShareContent(ShareContent shareContent) {
        this.mShareContent = shareContent;
        this.mTitle = shareContent.mTitle;
        this.mText = shareContent.mText;
        this.uMediaObject = shareContent.mMedia;
        this.mTargetUrl = shareContent.mTargetUrl;
    }

    public void parseMediaType() {
        if (!TextUtils.isEmpty(this.mText) && this.uMediaObject == null) {
            this.mShareType = TYPE_TEXT;
        } else if (this.uMediaObject != null && (this.uMediaObject instanceof UMEmoji)) {
            this.mShareType = TYPE_EMOJI;
        } else if (TextUtils.isEmpty(this.mText) && this.uMediaObject != null && (this.uMediaObject instanceof UMImage)) {
            this.mShareType = "image";
        } else if (this.uMediaObject != null && (this.uMediaObject instanceof UMusic)) {
            this.mShareType = TYPE_MUSIC;
        } else if (this.uMediaObject != null && (this.uMediaObject instanceof UMVideo)) {
            this.mShareType = TYPE_VIDEO;
        } else if (!TextUtils.isEmpty(this.mText) && this.uMediaObject != null && (this.uMediaObject instanceof UMImage)) {
            this.mShareType = TYPE_TEXT_IMAGE;
        }
    }

    public WXMediaMessage getWxMediaMessage() {
        WXMediaMessage wXMediaMessage = null;
        if (this.mShareContent.mMedia == null) {
            if (!TextUtils.isEmpty(this.mShareContent.mText)) {
                Log.i("--->", "text share..");
                wXMediaMessage = buildTextParams();
            }
        } else if (this.mShareContent.mMedia instanceof UMEmoji) {
            wXMediaMessage = buildEmojiParams();
        } else if (TextUtils.isEmpty(this.mShareContent.mText) && (this.mShareContent.mMedia instanceof UMImage)) {
            Log.d("weixin", "picture share");
            wXMediaMessage = buildImageParams();
        } else if (this.mShareContent.mMedia instanceof UMusic) {
            wXMediaMessage = buildMusicParams();
        } else if (this.mShareContent.mMedia instanceof UMVideo) {
            wXMediaMessage = buildVideoParams();
        } else if (!TextUtils.isEmpty(this.mShareContent.mText) && (this.mShareContent.mMedia instanceof UMImage)) {
            Log.d("图文分享..");
            wXMediaMessage = buildTextImageParams();
        }
        if (wXMediaMessage != null) {
            byte[] bArr = wXMediaMessage.thumbData;
            if (bArr != null && bArr.length > 32768) {
                wXMediaMessage.thumbData = compressBitmap(bArr, 32768);
                Log.d("压缩之后缩略图大小 : " + (wXMediaMessage.thumbData.length / 1024) + " KB.");
            }
            if (TextUtils.isEmpty(wXMediaMessage.title) || wXMediaMessage.title.getBytes().length < 512) {
                this.mTitle = "分享到微信";
            } else {
                wXMediaMessage.title = new String(wXMediaMessage.title.getBytes(), 0, 512);
            }
            if (!TextUtils.isEmpty(wXMediaMessage.description) && wXMediaMessage.description.getBytes().length >= 1024) {
                wXMediaMessage.description = new String(wXMediaMessage.description.getBytes(), 0, 1024);
            }
        }
        return wXMediaMessage;
    }

    private byte[] compressBitmap(byte[] bArr, int i) {
        int i2 = 0;
        if (bArr != null && bArr.length >= i) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            int i3 = 1;
            while (i2 == 0 && i3 <= 10) {
                int pow = (int) (Math.pow(0.8d, (double) i3) * 100.0d);
                Log.d("quality = " + pow);
                decodeByteArray.compress(CompressFormat.JPEG, pow, byteArrayOutputStream);
                Log.d("WeiXin Thumb Size = " + (byteArrayOutputStream.toByteArray().length / 1024) + " KB");
                if (byteArrayOutputStream == null || byteArrayOutputStream.size() >= i) {
                    byteArrayOutputStream.reset();
                    i3++;
                } else {
                    i2 = 1;
                }
            }
            if (byteArrayOutputStream != null) {
                bArr = byteArrayOutputStream.toByteArray();
                if (!decodeByteArray.isRecycled()) {
                    decodeByteArray.recycle();
                }
                if (bArr != null && bArr.length <= 0) {
                    Log.e("### 您的原始图片太大,导致缩略图压缩过后还大于32KB,请将分享到微信的图片进行适当缩小.");
                }
            }
        }
        return bArr;
    }

    private WXMediaMessage buildEmojiParams() {
        UMEmoji uMEmoji = (UMEmoji) this.mShareContent.mMedia;
        UMImage uMImage = uMEmoji.mSrcImage;
        String file = uMImage.asFileImage().toString();
        IMediaObject wXEmojiObject = new WXEmojiObject();
        if (uMEmoji.mSrcImage.isUrlMedia()) {
            file = BitmapUtils.getFileName(uMImage.toUrl());
            if (!new File(file).exists()) {
                BitmapUtils.loadImage(uMImage.toUrl(), 150, 150);
            }
        }
        wXEmojiObject.emojiPath = file;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXEmojiObject;
        if (uMEmoji.getThumbImage() != null) {
            wXMediaMessage.thumbData = uMEmoji.mThumb.toByte();
        } else if (TextUtils.isEmpty(uMEmoji.getThumb())) {
            wXMediaMessage.thumbData = uMEmoji.mSrcImage.toByte();
        } else {
            Bitmap loadImage = BitmapUtils.loadImage(uMEmoji.getThumb(), 150, 150);
            wXMediaMessage.thumbData = BitmapUtils.bitmap2Bytes(loadImage);
            loadImage.recycle();
        }
        wXMediaMessage.title = this.mTitle;
        wXMediaMessage.description = this.mShareContent.mText;
        return wXMediaMessage;
    }

    private WXMediaMessage buildMusicParams() {
        UMusic uMusic = (UMusic) this.mShareContent.mMedia;
        Object wXMusicObject = new WXMusicObject();
        if (!TextUtils.isEmpty(uMusic.getTargetUrl())) {
            wXMusicObject.musicUrl = uMusic.getTargetUrl();
        } else if (TextUtils.isEmpty(this.mShareContent.mTargetUrl)) {
            wXMusicObject.musicUrl = "http://wsq.umeng.com";
        } else {
            wXMusicObject.musicUrl = this.mShareContent.mTargetUrl;
        }
        wXMusicObject.musicDataUrl = uMusic.toUrl();
        if (!TextUtils.isEmpty(uMusic.getLowBandDataUrl())) {
            wXMusicObject.musicLowBandDataUrl = uMusic.getLowBandDataUrl();
        }
        if (!TextUtils.isEmpty(uMusic.getLowBandUrl())) {
            wXMusicObject.musicLowBandUrl = uMusic.getLowBandUrl();
        }
        WXMediaMessage buildMediaMessage = buildMediaMessage();
        buildMediaMessage.mediaObject = wXMusicObject;
        if (!TextUtils.isEmpty(uMusic.getTitle())) {
            buildMediaMessage.title = uMusic.getTitle();
        } else if (TextUtils.isEmpty(this.mShareContent.mTitle)) {
            buildMediaMessage.title = "分享音频";
        } else {
            buildMediaMessage.title = this.mShareContent.mTitle;
        }
        buildMediaMessage.description = this.mShareContent.mText;
        buildMediaMessage.mediaObject = wXMusicObject;
        if (!(uMusic.getThumb() == null || ("".equals(uMusic.getThumb()) && uMusic.getThumb() == null))) {
            byte[] asBinImage = uMusic.getThumbImage() != null ? uMusic.getThumbImage().asBinImage() : !TextUtils.isEmpty(uMusic.getThumb()) ? new UMImage(ContextUtil.getContext(), uMusic.getThumb()).asBinImage() : null;
            if (asBinImage != null) {
                Log.d("share with thumb");
                buildMediaMessage.thumbData = asBinImage;
            }
        }
        return buildMediaMessage;
    }

    private WXMediaMessage buildTextParams() {
        IMediaObject wXTextObject = new WXTextObject();
        wXTextObject.text = this.mShareContent.mText;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = this.mShareContent.mText;
        wXMediaMessage.title = this.mTitle;
        return wXMediaMessage;
    }

    private WXMediaMessage buildImageParams() {
        UMImage uMImage = (UMImage) this.mShareContent.mMedia;
        IMediaObject wXImageObject = new WXImageObject();
        WXMediaMessage buildMediaMessage = buildMediaMessage();
        if (uMImage.isUrlMedia()) {
            wXImageObject.imageUrl = uMImage.asUrlImage();
            wXImageObject.imageData = uMImage.asBinImage();
            buildMediaMessage.mediaObject = wXImageObject;
            return buildMediaMessage;
        }
        wXImageObject.imageData = uMImage.asBinImage();
        if (wXImageObject.imageData.length > 102400) {
            wXImageObject.imageData = BitmapUtils.compressBitmap(wXImageObject.imageData, 102400);
        }
        buildMediaMessage.mediaObject = wXImageObject;
        return buildMediaMessage;
    }

    private WXMediaMessage buildVideoParams() {
        UMVideo uMVideo = (UMVideo) this.mShareContent.mMedia;
        IMediaObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = uMVideo.toUrl();
        if (!TextUtils.isEmpty(uMVideo.getLowBandUrl())) {
            wXVideoObject.videoLowBandUrl = uMVideo.getLowBandUrl();
        }
        WXMediaMessage buildMediaMessage = buildMediaMessage();
        buildMediaMessage.mediaObject = wXVideoObject;
        if (TextUtils.isEmpty(this.mShareContent.mTitle)) {
            buildMediaMessage.title = "分享视频";
        } else {
            buildMediaMessage.title = this.mShareContent.mTitle;
        }
        buildMediaMessage.description = this.mShareContent.mText;
        byte[] asBinImage = !TextUtils.isEmpty(uMVideo.getThumb()) ? new UMImage(ContextUtil.getContext(), uMVideo.getThumb()).asBinImage() : uMVideo.getThumbImage() != null ? uMVideo.getThumbImage().asBinImage() : null;
        if (asBinImage != null && asBinImage.length > 0) {
            buildMediaMessage.thumbData = asBinImage;
        }
        return buildMediaMessage;
    }

    private WXMediaMessage buildTextImageParams() {
        UMImage uMImage = (UMImage) this.mShareContent.mMedia;
        if (TextUtils.isEmpty(this.mTargetUrl)) {
            this.mTargetUrl = "http://wsq.umeng.com";
        }
        IMediaObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = this.mTargetUrl;
        WXMediaMessage buildMediaMessage = buildMediaMessage();
        buildMediaMessage.title = this.mTitle;
        buildMediaMessage.description = this.mShareContent.mText;
        buildMediaMessage.mediaObject = wXWebpageObject;
        return buildMediaMessage;
    }

    private WXMediaMessage buildMediaMessage() {
        Object obj;
        Object obj2 = null;
        UMImage uMImage;
        String str;
        if (this.mShareContent.mMedia instanceof UMImage) {
            String file;
            uMImage = (UMImage) this.mShareContent.mMedia;
            if (uMImage.asFileImage() != null) {
                file = uMImage.asFileImage().toString();
                Log.d("localPath", file);
                obj2 = file;
                file = null;
            } else {
                file = uMImage.asUrlImage();
            }
            str = file;
            obj = obj2;
            obj2 = str;
        } else {
            String str2;
            if (this.mShareContent.mMedia instanceof UMVideo) {
                uMImage = ((UMVideo) this.mShareContent.mMedia).getThumbImage();
                if (uMImage != null) {
                    if (uMImage == null || uMImage.asFileImage() == null) {
                        str = uMImage.asUrlImage();
                        obj = null;
                        str2 = str;
                    } else {
                        obj = uMImage.asFileImage().toString();
                    }
                }
            } else if (this.mShareContent.mMedia instanceof UMusic) {
                uMImage = ((UMusic) this.mShareContent.mMedia).getThumbImage();
                if (uMImage != null) {
                    if (uMImage == null || uMImage.asFileImage() == null) {
                        str = uMImage.asUrlImage();
                        obj = null;
                        str2 = str;
                    } else {
                        obj = uMImage.asFileImage().toString();
                    }
                }
            }
            obj = null;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        if (!TextUtils.isEmpty(obj2)) {
            wXMediaMessage.thumbData = BitmapUtils.bitmap2Bytes(BitmapUtils.loadImage(obj2, 150, 150));
        } else if (!TextUtils.isEmpty(obj)) {
            Bitmap thumbFromCache = getThumbFromCache(obj);
            Log.d("localBitmap", thumbFromCache + "");
            wXMediaMessage.setThumbImage(thumbFromCache);
            if (!(thumbFromCache == null || thumbFromCache.isRecycled())) {
                thumbFromCache.recycle();
            }
        }
        return wXMediaMessage;
    }

    private Bitmap getThumbFromCache(String str) {
        Log.d("imagePath", str);
        if (!BitmapUtils.isFileExist(str)) {
            return null;
        }
        Log.d("imagePath", "iamge exist:" + str);
        if (BitmapUtils.isNeedScale(str, 32768)) {
            Bitmap bitmapFromFile = BitmapUtils.getBitmapFromFile(str, 150, 150);
            Log.d("imagePath", "bitmap exist resize:" + bitmapFromFile);
            return bitmapFromFile;
        }
        bitmapFromFile = BitmapUtils.getBitmapFromFile(str);
        Log.d("imagePath", "bitmap exist:" + bitmapFromFile);
        return bitmapFromFile;
    }
}
