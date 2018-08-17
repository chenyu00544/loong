package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.utils.SocializeUtils;

public class UserInfoRequest extends SocializeRequest {
    private static final String a = "/share/userinfo/";
    private static final int b = 12;
    private String c;

    public UserInfoRequest(Context context, String str) {
        super(context, "", UserInfoResponse.class, 12, RequestMethod.GET);
        this.mContext = context;
        this.c = str;
    }

    protected String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/" + this.c + "/";
    }
}
