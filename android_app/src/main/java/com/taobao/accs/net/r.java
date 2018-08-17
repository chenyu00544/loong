package com.taobao.accs.net;

import com.taobao.accs.utl.UtilityImpl;
import org.android.spdy.AccsSSLCallback;

/* compiled from: Taobao */
class r implements AccsSSLCallback {
    final /* synthetic */ o a;

    r(o oVar) {
        this.a = oVar;
    }

    public byte[] getSSLPublicKey(int i, byte[] bArr) {
        return UtilityImpl.staticBinarySafeDecryptNoB64(this.a.d, this.a.b, bArr);
    }
}
