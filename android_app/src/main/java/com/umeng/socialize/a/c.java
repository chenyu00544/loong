package com.umeng.socialize.a;

import android.content.Context;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.utils.Log;

/* compiled from: SocialAnalytics */
public class c {
    private static SocializeClient a = new SocializeClient();

    public static void a(Context context, String str, String str2, UMediaObject uMediaObject) {
        new Thread(new d(context, str, str2, uMediaObject)).start();
    }

    public static void b(Context context, String str, String str2, UMediaObject uMediaObject) {
        SocializeRequest aVar = new a(context, str, str2);
        aVar.c("shake");
        aVar.a(uMediaObject);
        b bVar = (b) a.execute(aVar);
        if (bVar == null || !bVar.isOk()) {
            Log.d("fail to send log");
        } else {
            Log.d("send log succeed");
        }
    }
}
