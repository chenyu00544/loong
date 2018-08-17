package com.unionpay.mobile.android.pboctransaction.d;

import android.text.TextUtils;
import com.unionpay.mobile.android.pboctransaction.f;
import java.security.PrivateKey;
import javax.crypto.Cipher;

public final class a {
    public static String a(PrivateKey privateKey, String str) {
        String str2 = "";
        if (!(TextUtils.isEmpty(str) || privateKey == null)) {
            try {
                Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                Object obj = new byte[245];
                System.arraycopy(str.getBytes(), 0, obj, 0, str.getBytes().length);
                instance.init(1, privateKey);
                str2 = f.a(instance.doFinal(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str2;
    }
}
