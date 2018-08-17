package org.xutils.http.request;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.xutils.common.util.LogUtil;
import org.xutils.http.ProgressHandler;
import org.xutils.http.RequestParams;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.loader.Loader;
import org.xutils.http.loader.LoaderFactory;
import org.xutils.x;

public abstract class UriRequest implements Closeable {
    protected ClassLoader callingClassLoader = null;
    protected final Loader<?> loader;
    protected final RequestParams params;
    protected ProgressHandler progressHandler = null;
    protected final String queryUrl;
    protected RequestInterceptListener requestInterceptListener = null;

    class UriRequest_1 implements Runnable {
        final /* synthetic */ UriRequest a;

        UriRequest_1(UriRequest uriRequest) {
            this.a = uriRequest;
        }

        public void run() {
            try {
                this.a.loader.save2Cache(this.a);
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
        }
    }

    public abstract void clearCacheHeader();

    public abstract void close() throws IOException;

    public abstract String getCacheKey();

    public abstract long getContentLength();

    public abstract String getETag();

    public abstract long getExpiration();

    public abstract long getHeaderFieldDate(String str, long j);

    public abstract InputStream getInputStream() throws IOException;

    public abstract long getLastModified();

    public abstract int getResponseCode() throws IOException;

    public abstract String getResponseHeader(String str);

    public abstract Map<String, List<String>> getResponseHeaders();

    public abstract String getResponseMessage() throws IOException;

    public abstract boolean isLoading();

    public abstract Object loadResultFromCache() throws Throwable;

    public abstract void sendRequest() throws Throwable;

    UriRequest(RequestParams requestParams, Type type) throws Throwable {
        this.params = requestParams;
        this.queryUrl = buildQueryUrl(requestParams);
        this.loader = LoaderFactory.getLoader(type, requestParams);
    }

    protected String buildQueryUrl(RequestParams requestParams) {
        return requestParams.getUri();
    }

    public void setProgressHandler(ProgressHandler progressHandler) {
        this.progressHandler = progressHandler;
        this.loader.setProgressHandler(progressHandler);
    }

    public void setCallingClassLoader(ClassLoader classLoader) {
        this.callingClassLoader = classLoader;
    }

    public void setRequestInterceptListener(RequestInterceptListener requestInterceptListener) {
        this.requestInterceptListener = requestInterceptListener;
    }

    public RequestParams getParams() {
        return this.params;
    }

    public String getRequestUri() {
        return this.queryUrl;
    }

    public Object loadResult() throws Throwable {
        return this.loader.load(this);
    }

    public void save2Cache() {
        x.task().run(new UriRequest_1(this));
    }

    public String toString() {
        return getRequestUri();
    }
}
