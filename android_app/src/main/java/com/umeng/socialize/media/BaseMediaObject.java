package com.umeng.socialize.media;

import android.os.Parcel;
import android.text.TextUtils;

public abstract class BaseMediaObject implements UMediaObject {
    protected String a = "";
    protected String b = "";
    protected String c = "";
    protected String d = "";
    protected String e = "";
    protected String f = "";
    protected int g = 0;
    protected String h = "";
    public String mText = null;

    public BaseMediaObject(String str) {
        this.a = str;
    }

    public String getDescription() {
        return this.h;
    }

    public void setDescription(String str) {
        this.h = str;
    }

    public String toUrl() {
        return this.a;
    }

    public void setMediaUrl(String str) {
        this.a = str;
    }

    public boolean isUrlMedia() {
        if (TextUtils.isEmpty(this.a)) {
            return false;
        }
        return true;
    }

    public String getTitle() {
        return this.b;
    }

    public void setTitle(String str) {
        this.b = str;
    }

    public String getThumb() {
        return this.c;
    }

    public void setThumb(String str) {
        this.c = str;
    }

    public void setTargetUrl(String str) {
        this.d = str;
    }

    public String getTargetUrl() {
        return this.d;
    }

    protected BaseMediaObject(Parcel parcel) {
        if (parcel != null) {
            this.a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
        }
    }

    public String toString() {
        return "BaseMediaObject [media_url=" + this.a + ", qzone_title=" + this.b + ", qzone_thumb=" + this.c + "]";
    }
}
