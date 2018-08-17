package anet.channel.util;

import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Taobao */
public class i {
    private static AtomicInteger a = new AtomicInteger();

    public static String a(String str) {
        if (a.get() == ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) {
            a.set(0);
        }
        if (TextUtils.isEmpty(str)) {
            return StringUtils.concatString("AWCN", String.valueOf(a.incrementAndGet()));
        }
        return StringUtils.concatString(str, ".AWCN", String.valueOf(a.incrementAndGet()));
    }
}
