package com.unionpay.mobile.android.nocard.utils;

import android.text.TextUtils;
import com.tencent.tauth.AuthActivity;
import com.umeng.socialize.media.WeiXinShareContent;
import com.unionpay.mobile.android.g.a;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.utils.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class f {
    public static int a(b bVar, JSONObject jSONObject) {
        bVar.bt = j.a(jSONObject, "promotion_change_info");
        bVar.z = j.d(jSONObject, "rules");
        bVar.ad = j.d(jSONObject, "available_area_codes");
        bVar.D = j.d(jSONObject, "entrust_rules");
        bVar.E = j.a(jSONObject, "pre_cmd");
        bVar.A = j.a(jSONObject, "title");
        bVar.C = j.c(jSONObject, "rules_button");
        bVar.al = j.c(jSONObject, "service_checkbox");
        bVar.am = j.c(jSONObject, "bind_card_checkbox");
        Object a = j.a(jSONObject, "timeout_msg");
        if (!(a == null || TextUtils.isEmpty(a))) {
            bVar.ak = a;
        }
        bVar.p = new HashMap();
        JSONObject c = j.c(jSONObject, "f55");
        a = j.a(c, "order_amount");
        HashMap hashMap = bVar.p;
        String str = "trans_amt";
        if (a == null || a.length() <= 0) {
            a = "000000000000";
        }
        hashMap.put(str, a);
        a = j.a(c, "order_currency");
        hashMap = bVar.p;
        str = "trans currcy code";
        if (a == null || a.length() <= 0) {
            a = "0156";
        }
        hashMap.put(str, a);
        a = j.a(c, "trans_type");
        hashMap = bVar.p;
        str = "trans_type";
        if (a == null || a.length() <= 0) {
            a = "00";
        }
        hashMap.put(str, a);
        a = j.a(c, "mer_name");
        HashMap hashMap2 = bVar.p;
        String str2 = "mer_name";
        if (a == null || a.length() <= 0) {
            a = "";
        }
        hashMap2.put(str2, a);
        bVar.aq = j.a(jSONObject, "pan");
        bVar.bf = j.a(jSONObject, "encrypt_key");
        bVar.bg = j.a(jSONObject, "auth_id");
        String a2 = j.a(jSONObject, "fail_continue");
        if (a2 != null && a2.equalsIgnoreCase("0")) {
            bVar.F = true;
        }
        return ((bVar.z == null || bVar.z.length() <= 0) && (bVar.D == null || bVar.D.length() <= 0)) ? 2 : 0;
    }

    public static int a(b bVar, JSONObject jSONObject, boolean z) {
        if (!z) {
            bVar.G = jSONObject;
        }
        return a(bVar, jSONObject);
    }

    public static e a(JSONObject jSONObject) {
        e fVar = new com.unionpay.mobile.android.g.f();
        fVar.a("promotion", j.c(jSONObject, "promotion"));
        fVar.a("instalment", j.c(jSONObject, "instalment"));
        fVar.a("promotion_instalment_msgbox", j.c(jSONObject, "promotion_instalment_msgbox"));
        return fVar;
    }

    public static boolean a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return false;
        }
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String a = j.a(jSONObject, "type");
                String a2 = j.a(jSONObject, "readonly");
                if ("pan".equalsIgnoreCase(a)) {
                    return "true".equalsIgnoreCase(a2);
                } else {
                    i++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int b(b bVar, JSONObject jSONObject) {
        int i = jSONObject == null ? 2 : 0;
        if (bVar.ab == null) {
            bVar.ab = new ArrayList();
        }
        bVar.ab.clear();
        List e = j.e(jSONObject, "user_cards");
        if (e != null && e.size() > 0) {
            int i2 = 0;
            while (e != null && i2 < e.size()) {
                bVar.ab.add(new a(j.a((JSONArray) e.get(i2), 0), j.a((JSONArray) e.get(i2), 1), j.a((JSONArray) e.get(i2), 2), (byte) 0));
                i2++;
            }
        }
        bVar.ad = j.d(jSONObject, "available_area_codes");
        bVar.bt = j.a(jSONObject, "promotion_change_info");
        bVar.ac = j.d(jSONObject, "user_info");
        bVar.z = j.d(jSONObject, "rules");
        bVar.Z = j.c(jSONObject, "service_url");
        bVar.aa = j.c(jSONObject, "bind_url");
        bVar.ae = j.a(jSONObject, "empty_info");
        bVar.aY = j.a(jSONObject, "add_card_tip");
        bVar.aq = j.a(jSONObject, "pan");
        return i;
    }

    public static boolean c(b bVar, JSONObject jSONObject) {
        bVar.aF = null;
        bVar.aF = j.c(jSONObject, "cardExpireMsgBox");
        if (bVar.aF == null) {
            bVar.aF = j.c(jSONObject, "openByCounterMsgBox");
        }
        if (bVar.aF == null) {
            bVar.aF = j.c(jSONObject, "restrictPayMsgBox");
        }
        if (bVar.aF == null) {
            return false;
        }
        bVar.aG = j.a(bVar.aF, "title");
        bVar.aH = j.a(bVar.aF, WeiXinShareContent.TYPE_TEXT);
        JSONObject c = j.c(bVar.aF, "func");
        JSONObject c2 = j.c(bVar.aF, "cancel");
        bVar.aK = j.a(c, "label");
        bVar.aL = j.a(c, AuthActivity.ACTION_KEY);
        bVar.aI = j.a(c2, "label");
        bVar.aJ = j.a(c2, AuthActivity.ACTION_KEY);
        return true;
    }
}
