package org.xutils.http;

import java.lang.reflect.Type;
import org.xutils.HttpManager;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.Callback.TypedCallback;
import org.xutils.x;
import org.xutils.x.Ext;

public final class HttpManagerImpl implements HttpManager {
    private static final Object a = new Object();
    private static volatile HttpManagerImpl b;

    private class a<T> implements TypedCallback<T> {
        final /* synthetic */ HttpManagerImpl a;
        private final Class<T> b;

        public a(HttpManagerImpl httpManagerImpl, Class<T> cls) {
            this.a = httpManagerImpl;
            this.b = cls;
        }

        public Type getLoadType() {
            return this.b;
        }

        public void onSuccess(T t) {
        }

        public void onError(Throwable th, boolean z) {
        }

        public void onCancelled(CancelledException cancelledException) {
        }

        public void onFinished() {
        }
    }

    private HttpManagerImpl() {
    }

    public static void registerInstance() {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    b = new HttpManagerImpl();
                }
            }
        }
        Ext.setHttpManager(b);
    }

    public <T> Cancelable get(RequestParams requestParams, CommonCallback<T> commonCallback) {
        return request(HttpMethod.GET, requestParams, commonCallback);
    }

    public <T> Cancelable post(RequestParams requestParams, CommonCallback<T> commonCallback) {
        return request(HttpMethod.POST, requestParams, commonCallback);
    }

    public <T> Cancelable request(HttpMethod httpMethod, RequestParams requestParams, CommonCallback<T> commonCallback) {
        requestParams.setMethod(httpMethod);
        Cancelable cancelable = null;
        if (commonCallback instanceof Cancelable) {
            cancelable = (Cancelable) commonCallback;
        }
        return x.task().start(new HttpTask(requestParams, cancelable, commonCallback));
    }

    public <T> T getSync(RequestParams requestParams, Class<T> cls) throws Throwable {
        return requestSync(HttpMethod.GET, requestParams, (Class) cls);
    }

    public <T> T postSync(RequestParams requestParams, Class<T> cls) throws Throwable {
        return requestSync(HttpMethod.POST, requestParams, (Class) cls);
    }

    public <T> T requestSync(HttpMethod httpMethod, RequestParams requestParams, Class<T> cls) throws Throwable {
        return requestSync(httpMethod, requestParams, new a(this, cls));
    }

    public <T> T requestSync(HttpMethod httpMethod, RequestParams requestParams, TypedCallback<T> typedCallback) throws Throwable {
        requestParams.setMethod(httpMethod);
        return x.task().startSync(new HttpTask(requestParams, null, typedCallback));
    }
}
