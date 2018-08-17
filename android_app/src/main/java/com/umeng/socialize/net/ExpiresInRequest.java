package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.SocializeUtils;

public class ExpiresInRequest extends SocializeRequest {
    private static final String a = "/share/validate_token/";
    private static final int b = 24;
    private SHARE_MEDIA[] c;

    public ExpiresInRequest(Context context, SHARE_MEDIA[] share_mediaArr) {
        super(context, "", ExpiresInResponse.class, 24, RequestMethod.GET);
        this.c = share_mediaArr;
    }

    public void onPrepareRequest() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.c != null && this.c.length > 0) {
            for (SHARE_MEDIA share_media : this.c) {
                if (share_media != SHARE_MEDIA.GENERIC) {
                    stringBuilder.append(share_media.toString()).append(",");
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_PLATFORM, stringBuilder.toString());
        addStringParams("uid", SocializeConstants.UID);
    }

    protected String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/";
    }
}
