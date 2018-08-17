package com.unionpay;

import org.json.JSONObject;

final class y implements d {
    final /* synthetic */ UPPayWapActivity a;

    y(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void a(String str, e eVar) {
        String str2;
        Exception exception;
        Exception exception2;
        UPPayWapActivity uPPayWapActivity;
        String str3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str4 = "";
            String str5 = "";
            String str6 = "";
            String str7 = "";
            try {
                String str8 = (String) jSONObject.get("url");
                try {
                    str2 = (String) jSONObject.get("app");
                } catch (Exception e) {
                    exception = e;
                    str2 = str4;
                    str4 = str5;
                    str5 = str6;
                    str6 = str8;
                    exception2 = exception;
                    if (eVar != null) {
                        uPPayWapActivity = this.a;
                        eVar.a(UPPayWapActivity.b("1", exception2.getMessage(), null));
                    }
                    str3 = str5;
                    str5 = str4;
                    str4 = str2;
                    str2 = str6;
                    str6 = str3;
                    a.a(this.a, str2, str4, str5, str6, str7);
                }
                try {
                    str4 = (String) jSONObject.get("title");
                } catch (Exception e2) {
                    exception = e2;
                    str4 = str5;
                    str5 = str6;
                    str6 = str8;
                    exception2 = exception;
                    if (eVar != null) {
                        uPPayWapActivity = this.a;
                        eVar.a(UPPayWapActivity.b("1", exception2.getMessage(), null));
                    }
                    str3 = str5;
                    str5 = str4;
                    str4 = str2;
                    str2 = str6;
                    str6 = str3;
                    a.a(this.a, str2, str4, str5, str6, str7);
                }
                try {
                    str5 = (String) jSONObject.get("desp");
                    try {
                        str7 = (String) jSONObject.get("md5");
                        str6 = str5;
                        str5 = str4;
                        str4 = str2;
                        str2 = str8;
                    } catch (Exception e3) {
                        exception = e3;
                        str6 = str8;
                        exception2 = exception;
                        if (eVar != null) {
                            uPPayWapActivity = this.a;
                            eVar.a(UPPayWapActivity.b("1", exception2.getMessage(), null));
                        }
                        str3 = str5;
                        str5 = str4;
                        str4 = str2;
                        str2 = str6;
                        str6 = str3;
                        a.a(this.a, str2, str4, str5, str6, str7);
                    }
                } catch (Exception e4) {
                    exception = e4;
                    str5 = str6;
                    str6 = str8;
                    exception2 = exception;
                    if (eVar != null) {
                        uPPayWapActivity = this.a;
                        eVar.a(UPPayWapActivity.b("1", exception2.getMessage(), null));
                    }
                    str3 = str5;
                    str5 = str4;
                    str4 = str2;
                    str2 = str6;
                    str6 = str3;
                    a.a(this.a, str2, str4, str5, str6, str7);
                }
            } catch (Exception e5) {
                exception2 = e5;
                str3 = str6;
                str6 = "";
                str2 = str4;
                str4 = str5;
                str5 = str3;
                if (eVar != null) {
                    uPPayWapActivity = this.a;
                    eVar.a(UPPayWapActivity.b("1", exception2.getMessage(), null));
                }
                str3 = str5;
                str5 = str4;
                str4 = str2;
                str2 = str6;
                str6 = str3;
                a.a(this.a, str2, str4, str5, str6, str7);
            }
            a.a(this.a, str2, str4, str5, str6, str7);
        } catch (Exception exception22) {
            if (eVar != null) {
                UPPayWapActivity uPPayWapActivity2 = this.a;
                eVar.a(UPPayWapActivity.b("1", exception22.getMessage(), null));
            }
        }
    }
}
