package com.umeng.socialize.media;

import com.umeng.socialize.media.UMediaObject.MediaType;
import java.util.Map;

public class UMWebPage extends BaseMediaObject {
    private String i = "";
    private UMImage j = null;

    public UMWebPage(String str) {
        super(str);
        this.d = str;
    }

    public void setTargetUrl(String str) {
        super.setTargetUrl(str);
        this.a = str;
    }

    public String getDescription() {
        return this.i;
    }

    public void setDescription(String str) {
        this.i = str;
    }

    public void setThumb(UMImage uMImage) {
        this.j = uMImage;
    }

    public UMImage getThumbImage() {
        return this.j;
    }

    public MediaType getMediaType() {
        return MediaType.WEBPAGE;
    }

    public Map<String, Object> toUrlExtraParams() {
        return null;
    }

    public byte[] toByte() {
        return null;
    }

    public boolean isMultiMedia() {
        return true;
    }

    public String toString() {
        return "UMWebPage [mDescription=" + this.i + ", mMediaTitle=" + this.b + ", mMediaThumb=" + this.c + ", mMediaTargetUrl=" + this.d + ", mLength=" + this.g + "]";
    }
}
