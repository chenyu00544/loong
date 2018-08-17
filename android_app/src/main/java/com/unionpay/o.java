package com.unionpay;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONObject;

final class o implements d {
    final /* synthetic */ UPPayWapActivity a;

    o(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void a(String str, e eVar) {
        String str2;
        Exception exception;
        UPPayWapActivity uPPayWapActivity;
        Bundle bundle;
        Intent intent;
        UPPayWapActivity uPPayWapActivity2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str3 = "";
            String str4 = "";
            try {
                str2 = (String) jSONObject.get("url");
                try {
                    str3 = (String) jSONObject.get("title");
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
                    bundle = new Bundle();
                    bundle.putString("waptype", "new_page");
                    bundle.putString("wapurl", str2);
                    bundle.putString("waptitle", str3);
                    intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(this.a, UPPayWapActivity.class);
                    this.a.startActivity(intent);
                    if (eVar == null) {
                        uPPayWapActivity2 = this.a;
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
                bundle = new Bundle();
                bundle.putString("waptype", "new_page");
                bundle.putString("wapurl", str2);
                bundle.putString("waptitle", str3);
                intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(this.a, UPPayWapActivity.class);
                this.a.startActivity(intent);
                if (eVar == null) {
                    uPPayWapActivity2 = this.a;
                    eVar.a(UPPayWapActivity.b("0", "success", null));
                }
            }
            bundle = new Bundle();
            bundle.putString("waptype", "new_page");
            bundle.putString("wapurl", str2);
            bundle.putString("waptitle", str3);
            intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(this.a, UPPayWapActivity.class);
            this.a.startActivity(intent);
            if (eVar == null) {
                uPPayWapActivity2 = this.a;
                eVar.a(UPPayWapActivity.b("0", "success", null));
            }
        } catch (Exception exception3) {
            if (eVar != null) {
                UPPayWapActivity uPPayWapActivity3 = this.a;
                eVar.a(UPPayWapActivity.b("1", exception3.getMessage(), null));
            }
        }
    }
}
