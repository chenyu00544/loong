package com.umeng.socialize.net;

import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareMultiFollowResponse extends SocializeReseponse {
    public Map<String, Integer> mInfoMap;

    public ShareMultiFollowResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        super.parseJsonObject();
        this.mInfoMap = new HashMap();
        Iterator keys = this.mJsonData.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                int optInt;
                JSONObject jSONObject = this.mJsonData.getJSONObject(str);
                if (jSONObject != null) {
                    optInt = jSONObject.optInt(SocializeProtocolConstants.PROTOCOL_KEY_ST, -102);
                } else {
                    optInt = -102;
                }
                this.mInfoMap.put(str, Integer.valueOf(optInt));
            } catch (JSONException e) {
            }
        }
    }
}
