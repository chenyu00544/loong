package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.utils.SocializeUtils;

public class UploadImageRequest extends SocializeRequest {
    private static final String a = "/api/upload_pic/";
    private static final int b = 23;
    private Context c;
    private String d;
    private UMediaObject e;

    public UploadImageRequest(Context context, UMediaObject uMediaObject, String str) {
        super(context, "", UploadImageResponse.class, 23, RequestMethod.POST);
        this.c = context;
        this.d = str;
        this.e = uMediaObject;
    }

    protected String getPath() {
        return a + SocializeUtils.getAppkey(this.c) + "/";
    }

    public void onPrepareRequest() {
        addStringParams("usid", this.d);
        addMediaParams(this.e);
    }
}
