package anet.channel.b;

import anet.channel.util.HttpConstant;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
public class d extends a {
    private ConcurrentHashMap<String, String> c = new ConcurrentHashMap();

    protected Object a(int i, Object... objArr) {
        if (i == 3) {
            return this.c.put((String) objArr[0], HttpConstant.HTTP);
        }
        if (i != 2) {
            return a;
        }
        String str = (String) this.c.get(objArr[0]);
        if (str != null) {
            return str;
        }
        this.c.putIfAbsent((String) objArr[0], HttpConstant.HTTPS);
        return HttpConstant.HTTPS;
    }
}
