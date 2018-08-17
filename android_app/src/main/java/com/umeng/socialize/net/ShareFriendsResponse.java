package com.umeng.socialize.net;

import android.text.TextUtils;
import com.umeng.socialize.bean.UMFriend;
import com.umeng.socialize.bean.UMFriend.PinYin;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class ShareFriendsResponse extends SocializeReseponse {
    public List<UMFriend> mFriends;

    public ShareFriendsResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        JSONObject jSONObject = this.mJsonData;
        if (jSONObject == null) {
            Log.e("SocializeReseponse", "data json is null....");
            return;
        }
        this.mFriends = new ArrayList();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                Object obj = keys.next().toString();
                jSONObject = (JSONObject) this.mJsonData.get(obj);
                if (jSONObject.has("name")) {
                    String string = jSONObject.getString("name");
                    if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(string))) {
                        UMFriend uMFriend = new UMFriend();
                        uMFriend.setFid(obj);
                        uMFriend.setName(string);
                        CharSequence optString = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_LINKNAME, "");
                        if (!TextUtils.isEmpty(optString)) {
                            CharSequence charSequence = optString;
                        }
                        uMFriend.setLinkName(string);
                        string = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_PINYIN, "");
                        if (!TextUtils.isEmpty(string)) {
                            PinYin pinYin = new PinYin();
                            pinYin.mInitial = String.valueOf(reverse(string.charAt(0)));
                            pinYin.mTotalPinyin = string;
                            uMFriend.setPinyin(pinYin);
                        }
                        if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_ICON)) {
                            uMFriend.setIcon(jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_ICON));
                        }
                        this.mFriends.add(uMFriend);
                    }
                }
            } catch (Exception e) {
                Log.e("SocializeReseponse", "Parse friend data error", e);
            }
        }
    }

    public static char reverse(char c) {
        if (c < 'a' || c > 'z') {
            return c;
        }
        return (char) (c - 32);
    }
}
