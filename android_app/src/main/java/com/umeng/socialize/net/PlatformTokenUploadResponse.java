package com.umeng.socialize.net;

import android.text.TextUtils;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.base.SocializeReseponse;
import org.json.JSONException;
import org.json.JSONObject;

public class PlatformTokenUploadResponse extends SocializeReseponse {
    public String mExpiresIn;
    public String mTencentUid;

    public PlatformTokenUploadResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        super.parseJsonObject();
        a();
        b();
    }

    private void a() {
        if (this.mJsonData != null) {
            try {
                JSONObject jSONObject = this.mJsonData.getJSONObject("tencent");
                if (jSONObject != null) {
                    Object optString = jSONObject.optString(SocializeConstants.TENCENT_UID);
                    if (!TextUtils.isEmpty(optString)) {
                        this.mTencentUid = optString;
                    }
                }
            } catch (JSONException e) {
            }
        }
    }

    private void b() {
    }
}
