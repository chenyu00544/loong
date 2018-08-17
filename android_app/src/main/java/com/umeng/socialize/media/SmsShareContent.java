package com.umeng.socialize.media;

import com.umeng.socialize.ShareContent;

public class SmsShareContent {
    private UMImage mImage;
    private String mText;

    public SmsShareContent(ShareContent shareContent) {
        this.mText = shareContent.mText;
        if (shareContent.mMedia instanceof UMImage) {
            this.mImage = (UMImage) shareContent.mMedia;
        }
    }

    public void setText(String str) {
        this.mText = str;
    }

    public void setImage(UMImage uMImage) {
        this.mImage = uMImage;
    }

    public String getText() {
        return this.mText;
    }

    public UMImage getImage() {
        return this.mImage;
    }
}
