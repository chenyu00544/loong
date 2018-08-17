package com.umeng.socialize.media;

import com.umeng.socialize.ShareContent;

public class MailShareContent extends SimpleShareContent {
    private String mSubject = "";
    private String mTitle = "";

    public MailShareContent(ShareContent shareContent) {
        super(shareContent);
    }

    public String getSubject() {
        return this.mSubject;
    }

    public void setSubject(String str) {
        this.mSubject = str;
    }
}
