package com.taobao.agoo;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.IACCSManager;
import com.taobao.accs.IAgooAppReceiver;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.agoo.a.a.c;

/* compiled from: Taobao */
final class b extends IAgooAppReceiver {
    final /* synthetic */ Context a;
    final /* synthetic */ IRegister b;
    final /* synthetic */ IACCSManager c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;

    b(Context context, IRegister iRegister, IACCSManager iACCSManager, String str, String str2) {
        this.a = context;
        this.b = iRegister;
        this.c = iACCSManager;
        this.d = str;
        this.e = str2;
    }

    public void onBindApp(int i, String str) {
        try {
            ALog.i("TaobaoRegister", "onBindApp", Constants.KEY_ERROR_CODE, Integer.valueOf(i));
            if (i == 200) {
                if (TaobaoRegister.mRequestListener == null) {
                    TaobaoRegister.mRequestListener = new com.taobao.agoo.a.b(this.a);
                }
                TaobaoRegister.mRequestListener;
                if (com.taobao.agoo.a.b.b.b(this.a.getPackageName())) {
                    ALog.i("TaobaoRegister", "agoo aready Registered return ", new Object[0]);
                    if (this.b != null) {
                        this.b.onSuccess(org.android.agoo.common.b.f(this.a));
                        return;
                    }
                    return;
                }
                this.c.registerDataListener(this.a, TaobaoConstants.SERVICE_ID_DEVICECMD, TaobaoRegister.mRequestListener);
                byte[] a = c.a(this.a, this.d, this.e);
                if (a != null) {
                    CharSequence sendRequest = this.c.sendRequest(this.a, new AccsRequest(null, TaobaoConstants.SERVICE_ID_DEVICECMD, a, null));
                    if (TextUtils.isEmpty(sendRequest)) {
                        if (this.b != null) {
                            this.b.onFailure(TaobaoConstants.REGISTER_ERROR, "accs channel disabled!");
                        }
                    } else if (this.b != null) {
                        TaobaoRegister.mRequestListener.a.put(sendRequest, this.b);
                    }
                } else if (this.b != null) {
                    this.b.onFailure(TaobaoConstants.REGISTER_ERROR, "req data null");
                }
            } else if (this.b != null) {
                this.b.onFailure(String.valueOf(i), "accs bindapp error!");
            }
        } catch (Throwable th) {
            ALog.e("TaobaoRegister", "register onBindApp", th, new Object[0]);
        }
    }

    public String getAppkey() {
        return this.d;
    }
}
