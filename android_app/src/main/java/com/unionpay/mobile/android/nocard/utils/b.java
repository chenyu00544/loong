package com.unionpay.mobile.android.nocard.utils;

import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class b {
    public static void a(JSONObject jSONObject, com.unionpay.mobile.android.g.b bVar) {
        bVar.as = j.a(jSONObject, "red_packet_url");
    }

    public static void b(JSONObject jSONObject, com.unionpay.mobile.android.g.b bVar) {
        bVar.aR = j.a(jSONObject, "pay_msg");
        bVar.bj = j.a(jSONObject, "reserved");
        bVar.aS = j.a(jSONObject, "promotion_msg");
        bVar.aT = j.a(jSONObject, "instalment_msg");
        bVar.aW = j.a(jSONObject, "temporary_pay_flag");
        if ("0".equalsIgnoreCase(bVar.aW)) {
            bVar.aX = j.a(jSONObject, "temporary_pay_info");
        }
        if ("0".equalsIgnoreCase(j.a(jSONObject, "luck_draw_flag"))) {
            bVar.aV = true;
        }
    }
}
