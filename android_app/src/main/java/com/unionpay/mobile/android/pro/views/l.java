package com.unionpay.mobile.android.pro.views;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.e.a;
import org.json.JSONObject;

final class l implements Callback {
    final /* synthetic */ a a;

    l(a aVar) {
        this.a = aVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case m_AppUI.MSG_APP_VERSION /*2004*/:
                this.a.H.removeMessages(m_AppUI.MSG_APP_VERSION_COMMEND);
                Bundle bundle = (Bundle) message.obj;
                if (bundle != null) {
                    if (!bundle.getBoolean("success")) {
                        this.a.a(this.a.a.ap, false);
                        break;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(a.a(bundle.getString("result"), this.a.C.f()));
                        a aVar = this.a;
                        if (a.d(jSONObject)) {
                            a.a(this.a, this.a.x.a().b, a.f(this.a));
                            break;
                        }
                        this.a.a(this.a.a.ap, false);
                        return false;
                    } catch (Exception e) {
                        this.a.a(this.a.a.ap, false);
                        e.printStackTrace();
                        return false;
                    }
                }
                break;
            case m_AppUI.MSG_APP_VERSION_COMMEND /*2006*/:
                this.a.a(this.a.a.ap, false);
                break;
        }
        return true;
    }
}
