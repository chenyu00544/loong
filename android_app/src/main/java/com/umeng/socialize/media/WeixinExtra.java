package com.umeng.socialize.media;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.weixin.net.WXAuthUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class WeixinExtra {
    public static void isAccessTokenValid(String str, String str2, final UMAuthListener uMAuthListener) {
        final String str3 = "https://api.weixin.qq.com/sns/auth?access_token=" + str + "&openid=" + str2;
        new Thread(new Runnable() {
            public void run() {
                try {
                    final JSONObject jSONObject = new JSONObject(WXAuthUtils.request(str3));
                    if (jSONObject != null) {
                        QueuedWork.runInMain(new Runnable() {
                            public void run() {
                                Map hashMap = new HashMap();
                                if (jSONObject.optInt("errcode", -1) == 0) {
                                    hashMap.put("result", jSONObject.toString());
                                    uMAuthListener.onComplete(SHARE_MEDIA.WEIXIN, 2, hashMap);
                                    return;
                                }
                                uMAuthListener.onError(SHARE_MEDIA.WEIXIN, 2, new Throwable(jSONObject.toString()));
                            }
                        });
                    }
                } catch (JSONException e) {
                }
            }
        }).start();
    }
}
