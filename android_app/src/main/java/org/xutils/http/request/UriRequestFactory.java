package org.xutils.http.request;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.app.RequestTracker;

public final class UriRequestFactory {
    private static Class<? extends RequestTracker> a;
    private static final HashMap<String, Class<? extends UriRequest>> b = new HashMap();

    private UriRequestFactory() {
    }

    public static UriRequest getUriRequest(RequestParams requestParams, Type type) throws Throwable {
        String substring;
        String uri = requestParams.getUri();
        int indexOf = uri.indexOf(":");
        if (indexOf > 0) {
            substring = uri.substring(0, indexOf);
        } else if (uri.startsWith("/")) {
            substring = "file";
        } else {
            substring = null;
        }
        if (TextUtils.isEmpty(substring)) {
            throw new IllegalArgumentException("The url not be support: " + uri);
        }
        Class cls = (Class) b.get(substring);
        if (cls != null) {
            return (UriRequest) cls.getConstructor(new Class[]{RequestParams.class, Class.class}).newInstance(new Object[]{requestParams, type});
        } else if (substring.startsWith(HttpConstant.HTTP)) {
            return new HttpRequest(requestParams, type);
        } else {
            if (substring.equals("assets")) {
                return new AssetsRequest(requestParams, type);
            }
            if (substring.equals("file")) {
                return new LocalFileRequest(requestParams, type);
            }
            throw new IllegalArgumentException("The url not be support: " + uri);
        }
    }

    public static void registerDefaultTrackerClass(Class<? extends RequestTracker> cls) {
        a = cls;
    }

    public static RequestTracker getDefaultTracker() {
        try {
            return a == null ? null : (RequestTracker) a.newInstance();
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            return null;
        }
    }

    public static void registerRequestClass(String str, Class<? extends UriRequest> cls) {
        b.put(str, cls);
    }
}
