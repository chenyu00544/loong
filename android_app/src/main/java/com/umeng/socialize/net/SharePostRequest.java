package com.umeng.socialize.net;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.common.ImageFormat;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest.FilePair;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.util.Map;

public class SharePostRequest extends SocializeRequest {
    private static final String a = "/share/add/";
    private static final int b = 9;
    private String c;
    private String d;
    private ShareContent e;

    public SharePostRequest(Context context, String str, String str2, ShareContent shareContent) {
        super(context, "", SocializeReseponse.class, 9, RequestMethod.POST);
        this.mContext = context;
        this.c = str;
        this.d = str2;
        this.e = shareContent;
        Log.e("xxxx content=" + shareContent.mMedia);
    }

    public void onPrepareRequest() {
        addStringParams("to", this.c);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_TEXT, this.e.mText);
        addStringParams("usid", this.d);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_AK, SocializeUtils.getAppkey(this.mContext));
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY, Config.EntityKey);
        if (this.e.mLocation != null) {
            addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_LOCATION, this.e.mLocation.toString());
        }
        addMediaParams(this.e.mMedia);
    }

    protected String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/" + Config.EntityKey + "/";
    }

    public Map<String, FilePair> getFilePair() {
        if (this.e == null || this.e.mMedia == null || this.e.mMedia.isUrlMedia()) {
            return super.getFilePair();
        }
        Map<String, FilePair> filePair = super.getFilePair();
        if (this.e.mMedia instanceof UMImage) {
            UMImage uMImage = (UMImage) this.e.mMedia;
            uMImage.asFileImage().getPath();
            Object asBinImage = uMImage.asBinImage();
            String checkFormat = ImageFormat.checkFormat(asBinImage);
            if (TextUtils.isEmpty(checkFormat)) {
                checkFormat = "png";
            }
            String str = System.currentTimeMillis() + "";
            Log.e("xxxx filedata=" + asBinImage);
            filePair.put(SocializeProtocolConstants.PROTOCOL_KEY_IMAGE, new FilePair(str + "." + checkFormat, asBinImage));
        }
        return filePair;
    }
}
