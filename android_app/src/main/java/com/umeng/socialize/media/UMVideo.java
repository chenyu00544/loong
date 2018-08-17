package com.umeng.socialize.media;

import com.umeng.socialize.media.UMediaObject.MediaType;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;

public class UMVideo extends BaseMediaObject {
    private UMImage i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;

    public int getDuration() {
        return this.n;
    }

    public void setDuration(int i) {
        this.n = i;
    }

    public UMVideo(String str) {
        super(str);
    }

    public String getLowBandUrl() {
        return this.j;
    }

    public String getLowBandDataUrl() {
        return this.k;
    }

    public void setLowBandDataUrl(String str) {
        this.k = str;
    }

    public String getHighBandDataUrl() {
        return this.l;
    }

    public void setHighBandDataUrl(String str) {
        this.l = str;
    }

    public String getH5Url() {
        return this.m;
    }

    public void setH5Url(String str) {
        this.m = str;
    }

    public void setLowBandUrl(String str) {
        this.j = str;
    }

    public MediaType getMediaType() {
        return MediaType.VEDIO;
    }

    public final Map<String, Object> toUrlExtraParams() {
        Map<String, Object> hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
        }
        return hashMap;
    }

    public void setThumb(UMImage uMImage) {
        this.i = uMImage;
    }

    public byte[] toByte() {
        if (this.i != null) {
            return this.i.toByte();
        }
        return null;
    }

    public String toString() {
        return "UMVedio [media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=" + this.c + "media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=" + this.c + "]";
    }

    public boolean isMultiMedia() {
        return true;
    }

    public UMImage getThumbImage() {
        return this.i;
    }
}
