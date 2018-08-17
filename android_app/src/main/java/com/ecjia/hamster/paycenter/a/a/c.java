package com.ecjia.hamster.paycenter.a.a;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/* compiled from: SignUtils */
public class c {
    public static String a(String str, String str2) {
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA", "BC").generatePrivate(new PKCS8EncodedKeySpec(a.a(str2)));
            Signature instance = Signature.getInstance("SHA1WithRSA");
            instance.initSign(generatePrivate);
            instance.update(str.getBytes("UTF-8"));
            return a.a(instance.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
