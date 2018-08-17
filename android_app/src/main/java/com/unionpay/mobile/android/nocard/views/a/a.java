package com.unionpay.mobile.android.nocard.views.a;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.unionpay.mobile.android.g.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    public static List<Map<String, Object>> a(Context context, List<c> list, boolean z) {
        if (list == null) {
            return null;
        }
        List<Map<String, Object>> arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            c cVar = (c) list.get(i);
            if (cVar != null) {
                Object b;
                Map hashMap = new HashMap();
                hashMap.put("text1", cVar.e());
                if (cVar.c() == 0) {
                    b = cVar.b();
                } else if (!TextUtils.isEmpty(cVar.b())) {
                    b = cVar.b().substring(0, 4) + " **** **** " + cVar.b().substring(cVar.b().length() - 4);
                }
                hashMap.put("text2", b);
                if (z) {
                    if (cVar.c() == 0) {
                        hashMap.put("editable", Boolean.TRUE);
                        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2, com.unionpay.mobile.android.j.c.a(context).a(1016));
                    } else {
                        hashMap.put("editable", Boolean.FALSE);
                        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2, null);
                    }
                }
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    public static JSONObject a(JSONObject jSONObject, String str) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("href_label", jSONObject.get("label"));
            jSONObject2.put("name", "");
            jSONObject2.put("value", "");
            jSONObject2.put("href_title", str);
            jSONObject2.put("label", com.unionpay.mobile.android.f.c.bD.z);
            jSONObject2.put("required", "0");
            jSONObject2.put("href_url", jSONObject.get("href"));
            jSONObject2.put("error_info", com.unionpay.mobile.android.f.c.bD.aF);
            jSONObject2.put("checked", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject2;
    }
}
