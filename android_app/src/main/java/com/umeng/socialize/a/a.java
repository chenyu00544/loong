package com.umeng.socialize.a;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;

/* compiled from: AnalyticsReqeust */
public class a extends SocializeRequest {
    private static final String a = "/share/multi_add/";
    private static final int b = 9;
    private String c;
    private String d;
    private String e;
    private String f;
    private UMediaObject g;

    public a(Context context, String str, String str2) {
        super(context, "", b.class, 9, RequestMethod.POST);
        this.mContext = context;
        this.c = str;
        this.f = str2;
        setReqType(1);
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.f = str;
    }

    public void a(UMediaObject uMediaObject) {
        this.g = uMediaObject;
    }

    public void onPrepareRequest() {
        super.onPrepareRequest();
        String str = "{\"%s\":\"%s\"}";
        Object[] objArr = new Object[2];
        objArr[0] = this.c;
        objArr[1] = this.d == null ? "" : this.d;
        String format = String.format(str, objArr);
        str = SocializeUtils.getAppkey(this.mContext);
        addStringParams("to", format);
        addStringParams("sns", format);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_AK, str);
        addStringParams("type", this.e);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_TEXT, this.f);
        Log.d("para", "parameter" + format + " " + SocializeUtils.getAppkey(this.mContext) + " " + this.e + " " + this.f);
        addMediaParams(this.g);
        if (!TextUtils.isEmpty(Config.getAdapterSDK())) {
            addStringParams(Config.getAdapterSDK(), Config.getAdapterSDKVersion());
        }
    }

    protected String getPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a);
        stringBuilder.append(SocializeUtils.getAppkey(this.mContext));
        stringBuilder.append("/").append(Config.EntityKey).append("/");
        return stringBuilder.toString();
    }
}
