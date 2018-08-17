package com.umeng.socialize.media;

import android.os.Parcel;
import android.text.TextUtils;
import com.umeng.socialize.media.UMediaObject.MediaType;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;

public class UMusic extends BaseMediaObject {
    private String i = "未知";
    private String j = "未知";
    private UMImage k;
    private String l;
    private String m;
    private String n;
    private String o;
    private int p;

    public int getDuration() {
        return this.p;
    }

    public void setDuration(int i) {
        this.p = i;
    }

    public String getLowBandUrl() {
        return this.o;
    }

    public void setLowBandUrl(String str) {
        this.o = str;
    }

    public UMusic(String str) {
        super(str);
    }

    public String getHighBandDataUrl() {
        return this.m;
    }

    public void setHighBandDataUrl(String str) {
        this.m = str;
    }

    public String getH5Url() {
        return this.n;
    }

    public void setH5Url(String str) {
        this.n = str;
    }

    public MediaType getMediaType() {
        return MediaType.MUSIC;
    }

    protected UMusic(Parcel parcel) {
        super(parcel);
        this.i = parcel.readString();
        this.j = parcel.readString();
    }

    public final Map<String, Object> toUrlExtraParams() {
        Map<String, Object> hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_TITLE, this.i);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_AUTHOR, this.j);
        }
        return hashMap;
    }

    public void setTitle(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.i = str;
        }
    }

    public void setAuthor(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j = str;
        }
    }

    public String getTitle() {
        return this.i;
    }

    public String getAuthor() {
        return this.j;
    }

    public void setThumb(UMImage uMImage) {
        this.k = uMImage;
    }

    public byte[] toByte() {
        if (this.k != null) {
            return this.k.toByte();
        }
        return null;
    }

    public String toString() {
        return "UMusic [title=" + this.i + ", author=" + this.j + "media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=" + this.c + "]";
    }

    public boolean isMultiMedia() {
        return true;
    }

    public UMImage getThumbImage() {
        return this.k;
    }

    public String getLowBandDataUrl() {
        return this.l;
    }

    public void setLowBandDataUrl(String str) {
        this.l = str;
    }
}
