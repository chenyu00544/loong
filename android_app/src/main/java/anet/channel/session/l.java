package anet.channel.session;

import anet.channel.security.ISecurity;
import anet.channel.util.ALog;
import org.android.spdy.AccsSSLCallback;
import org.android.spdy.SpdyProtocol;

/* compiled from: Taobao */
class l implements AccsSSLCallback {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public byte[] getSSLPublicKey(int i, byte[] bArr) {
        byte[] staticDecrypt;
        Throwable th;
        try {
            staticDecrypt = this.a.iSecurity.staticDecrypt(this.a.mContext, ISecurity.CIPHER_ALGORITHM_AES128, SpdyProtocol.TNET_PUBKEY_SG_KEY, bArr);
            if (staticDecrypt != null) {
                try {
                    if (ALog.isPrintLog(2)) {
                        ALog.i("getSSLPublicKey", null, "decrypt", new String(staticDecrypt));
                    }
                } catch (Throwable th2) {
                    th = th2;
                    ALog.e("awcn.TnetSpdySession", "getSSLPublicKey", null, th, new Object[0]);
                    return staticDecrypt;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            staticDecrypt = null;
            ALog.e("awcn.TnetSpdySession", "getSSLPublicKey", null, th, new Object[0]);
            return staticDecrypt;
        }
        return staticDecrypt;
    }
}
