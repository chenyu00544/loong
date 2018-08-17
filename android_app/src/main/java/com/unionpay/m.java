package com.unionpay;

import com.taobao.agoo.a.a.b;
import org.json.JSONObject;

final class m implements d {
    final /* synthetic */ UPPayWapActivity a;

    m(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void a(String str, e eVar) {
        String str2;
        Exception exception;
        UPPayWapActivity uPPayWapActivity;
        UPPayWapActivity uPPayWapActivity2;
        UPPayWapActivity uPPayWapActivity3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str3 = "";
            String str4 = "";
            try {
                str2 = (String) jSONObject.get(b.JSON_ERRORCODE);
                try {
                    str3 = (String) jSONObject.get("resultData");
                } catch (Exception e) {
                    Exception exception2 = e;
                    str3 = str2;
                    exception = exception2;
                    if (eVar != null) {
                        uPPayWapActivity = this.a;
                        eVar.a(UPPayWapActivity.b("1", exception.getMessage(), null));
                    }
                    str2 = str3;
                    str3 = str4;
                    uPPayWapActivity2 = this.a;
                    uPPayWapActivity = this.a;
                    UPPayWapActivity.a(uPPayWapActivity2, str2, str3);
                    if (eVar == null) {
                        uPPayWapActivity3 = this.a;
                        eVar.a(UPPayWapActivity.b("0", "success", null));
                    }
                }
            } catch (Exception e2) {
                exception = e2;
                if (eVar != null) {
                    uPPayWapActivity = this.a;
                    eVar.a(UPPayWapActivity.b("1", exception.getMessage(), null));
                }
                str2 = str3;
                str3 = str4;
                uPPayWapActivity2 = this.a;
                uPPayWapActivity = this.a;
                UPPayWapActivity.a(uPPayWapActivity2, str2, str3);
                if (eVar == null) {
                    uPPayWapActivity3 = this.a;
                    eVar.a(UPPayWapActivity.b("0", "success", null));
                }
            }
            uPPayWapActivity2 = this.a;
            uPPayWapActivity = this.a;
            UPPayWapActivity.a(uPPayWapActivity2, str2, str3);
            if (eVar == null) {
                uPPayWapActivity3 = this.a;
                eVar.a(UPPayWapActivity.b("0", "success", null));
            }
        } catch (Exception exception3) {
            if (eVar != null) {
                UPPayWapActivity uPPayWapActivity4 = this.a;
                eVar.a(UPPayWapActivity.b("1", exception3.getMessage(), null));
            }
        }
    }
}
