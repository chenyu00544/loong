package com.taobao.accs.internal;

import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;

/* compiled from: Taobao */
class b extends AccsAbstractDataListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void onData(String str, String str2, String str3, byte[] bArr, ExtraInfo extraInfo) {
    }

    public void onBind(String str, int i, ExtraInfo extraInfo) {
    }

    public void onUnbind(String str, int i, ExtraInfo extraInfo) {
    }

    public void onSendData(String str, String str2, int i, ExtraInfo extraInfo) {
    }

    public void onResponse(String str, String str2, int i, byte[] bArr, ExtraInfo extraInfo) {
        this.a.a(bArr, i);
        if (this.a.r != null) {
            this.a.r.cancel(true);
        }
    }
}
