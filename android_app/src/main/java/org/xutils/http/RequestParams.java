package org.xutils.http;

import android.text.TextUtils;
import com.taobao.accs.ErrorCode;
import java.io.IOException;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.Executor;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import org.xutils.common.task.Priority;
import org.xutils.common.util.LogUtil;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;
import org.xutils.http.app.HttpRetryHandler;
import org.xutils.http.app.ParamsBuilder;
import org.xutils.http.app.RedirectHandler;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.body.RequestBody;

public class RequestParams extends BaseParams {
    private RequestTracker A;
    private boolean B;
    private HttpRequest a;
    private String b;
    private final String[] c;
    private final String[] d;
    private ParamsBuilder e;
    private String f;
    private String g;
    private SSLSocketFactory h;
    private Proxy i;
    private HostnameVerifier j;
    private boolean k;
    private String l;
    private long m;
    private long n;
    private Executor o;
    private Priority p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;
    private int u;
    private String v;
    private boolean w;
    private int x;
    private HttpRetryHandler y;
    private RedirectHandler z;

    class RequestParams_1 implements a {
        final /* synthetic */ RequestParams a;

        RequestParams_1(RequestParams requestParams) {
            this.a = requestParams;
        }

        public void onParseKV(String str, Object obj) {
            this.a.addParameter(str, obj);
        }
    }

    public /* bridge */ /* synthetic */ void addBodyParameter(String str, Object obj, String str2) {
        super.addBodyParameter(str, obj, str2);
    }

    public /* bridge */ /* synthetic */ void addBodyParameter(String str, Object obj, String str2, String str3) {
        super.addBodyParameter(str, obj, str2, str3);
    }

    public /* bridge */ /* synthetic */ void addHeader(String str, String str2) {
        super.addHeader(str, str2);
    }

    public /* bridge */ /* synthetic */ void addParameter(String str, Object obj) {
        super.addParameter(str, obj);
    }

    public /* bridge */ /* synthetic */ void addQueryStringParameter(String str, String str2) {
        super.addQueryStringParameter(str, str2);
    }

    public /* bridge */ /* synthetic */ void clearParams() {
        super.clearParams();
    }

    public /* bridge */ /* synthetic */ String getBodyContent() {
        return super.getBodyContent();
    }

    public /* bridge */ /* synthetic */ List getBodyParams() {
        return super.getBodyParams();
    }

    public /* bridge */ /* synthetic */ String getCharset() {
        return super.getCharset();
    }

    public /* bridge */ /* synthetic */ List getFileParams() {
        return super.getFileParams();
    }

    public /* bridge */ /* synthetic */ List getHeaders() {
        return super.getHeaders();
    }

    public /* bridge */ /* synthetic */ HttpMethod getMethod() {
        return super.getMethod();
    }

    public /* bridge */ /* synthetic */ List getParams(String str) {
        return super.getParams(str);
    }

    public /* bridge */ /* synthetic */ List getQueryStringParams() {
        return super.getQueryStringParams();
    }

    public /* bridge */ /* synthetic */ RequestBody getRequestBody() throws IOException {
        return super.getRequestBody();
    }

    public /* bridge */ /* synthetic */ String getStringParameter(String str) {
        return super.getStringParameter(str);
    }

    public /* bridge */ /* synthetic */ List getStringParams() {
        return super.getStringParams();
    }

    public /* bridge */ /* synthetic */ boolean isAsJsonContent() {
        return super.isAsJsonContent();
    }

    public /* bridge */ /* synthetic */ boolean isMultipart() {
        return super.isMultipart();
    }

    public /* bridge */ /* synthetic */ void removeParameter(String str) {
        super.removeParameter(str);
    }

    public /* bridge */ /* synthetic */ void setAsJsonContent(boolean z) {
        super.setAsJsonContent(z);
    }

    public /* bridge */ /* synthetic */ void setBodyContent(String str) {
        super.setBodyContent(str);
    }

    public /* bridge */ /* synthetic */ void setCharset(String str) {
        super.setCharset(str);
    }

    public /* bridge */ /* synthetic */ void setHeader(String str, String str2) {
        super.setHeader(str, str2);
    }

    public /* bridge */ /* synthetic */ void setMethod(HttpMethod httpMethod) {
        super.setMethod(httpMethod);
    }

    public /* bridge */ /* synthetic */ void setMultipart(boolean z) {
        super.setMultipart(z);
    }

    public /* bridge */ /* synthetic */ void setRequestBody(RequestBody requestBody) {
        super.setRequestBody(requestBody);
    }

    public /* bridge */ /* synthetic */ String toJSONString() {
        return super.toJSONString();
    }

    public RequestParams() {
        this(null, null, null, null);
    }

    public RequestParams(String str) {
        this(str, null, null, null);
    }

    public RequestParams(String str, ParamsBuilder paramsBuilder, String[] strArr, String[] strArr2) {
        this.k = true;
        this.p = Priority.DEFAULT;
        this.q = 15000;
        this.r = 15000;
        this.s = true;
        this.t = false;
        this.u = 2;
        this.w = false;
        this.x = ErrorCode.APP_NOT_BIND;
        this.B = false;
        if (str != null && paramsBuilder == null) {
            paramsBuilder = new DefaultParamsBuilder();
        }
        this.b = str;
        this.c = strArr;
        this.d = strArr2;
        this.e = paramsBuilder;
    }

    void a() throws Throwable {
        if (!TextUtils.isEmpty(this.f)) {
            return;
        }
        if (TextUtils.isEmpty(this.b) && c() == null) {
            throw new IllegalStateException("uri is empty && @HttpRequest == null");
        }
        b();
        this.f = this.b;
        HttpRequest c = c();
        if (c != null) {
            this.e = (ParamsBuilder) c.builder().newInstance();
            this.f = this.e.buildUri(this, c);
            this.e.buildParams(this);
            this.e.buildSign(this, c.signs());
            if (this.h == null) {
                this.h = this.e.getSSLSocketFactory();
            }
        } else if (this.e != null) {
            this.e.buildParams(this);
            this.e.buildSign(this, this.c);
            if (this.h == null) {
                this.h = this.e.getSSLSocketFactory();
            }
        }
    }

    public String getUri() {
        return TextUtils.isEmpty(this.f) ? this.b : this.f;
    }

    public void setUri(String str) {
        if (TextUtils.isEmpty(this.f)) {
            this.b = str;
        } else {
            this.f = str;
        }
    }

    public String getCacheKey() {
        if (TextUtils.isEmpty(this.g) && this.e != null) {
            HttpRequest c = c();
            if (c != null) {
                this.g = this.e.buildCacheKey(this, c.cacheKeys());
            } else {
                this.g = this.e.buildCacheKey(this, this.d);
            }
        }
        return this.g;
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.h = sSLSocketFactory;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.h;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.j;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.j = hostnameVerifier;
    }

    public boolean isUseCookie() {
        return this.k;
    }

    public void setUseCookie(boolean z) {
        this.k = z;
    }

    public Proxy getProxy() {
        return this.i;
    }

    public void setProxy(Proxy proxy) {
        this.i = proxy;
    }

    public Priority getPriority() {
        return this.p;
    }

    public void setPriority(Priority priority) {
        this.p = priority;
    }

    public int getConnectTimeout() {
        return this.q;
    }

    public void setConnectTimeout(int i) {
        if (i > 0) {
            this.q = i;
        }
    }

    public int getReadTimeout() {
        return this.r;
    }

    public void setReadTimeout(int i) {
        if (i > 0) {
            this.r = i;
        }
    }

    public String getCacheDirName() {
        return this.l;
    }

    public void setCacheDirName(String str) {
        this.l = str;
    }

    public long getCacheSize() {
        return this.m;
    }

    public void setCacheSize(long j) {
        this.m = j;
    }

    public long getCacheMaxAge() {
        return this.n;
    }

    public void setCacheMaxAge(long j) {
        this.n = j;
    }

    public Executor getExecutor() {
        return this.o;
    }

    public void setExecutor(Executor executor) {
        this.o = executor;
    }

    public boolean isAutoResume() {
        return this.s;
    }

    public void setAutoResume(boolean z) {
        this.s = z;
    }

    public boolean isAutoRename() {
        return this.t;
    }

    public void setAutoRename(boolean z) {
        this.t = z;
    }

    public String getSaveFilePath() {
        return this.v;
    }

    public void setSaveFilePath(String str) {
        this.v = str;
    }

    public int getMaxRetryCount() {
        return this.u;
    }

    public void setMaxRetryCount(int i) {
        this.u = i;
    }

    public boolean isCancelFast() {
        return this.w;
    }

    public void setCancelFast(boolean z) {
        this.w = z;
    }

    public int getLoadingUpdateMaxTimeSpan() {
        return this.x;
    }

    public void setLoadingUpdateMaxTimeSpan(int i) {
        this.x = i;
    }

    public HttpRetryHandler getHttpRetryHandler() {
        return this.y;
    }

    public void setHttpRetryHandler(HttpRetryHandler httpRetryHandler) {
        this.y = httpRetryHandler;
    }

    public RedirectHandler getRedirectHandler() {
        return this.z;
    }

    public void setRedirectHandler(RedirectHandler redirectHandler) {
        this.z = redirectHandler;
    }

    public RequestTracker getRequestTracker() {
        return this.A;
    }

    public void setRequestTracker(RequestTracker requestTracker) {
        this.A = requestTracker;
    }

    private void b() {
        a.a(this, getClass(), new RequestParams_1(this));
    }

    private HttpRequest c() {
        if (this.a == null && !this.B) {
            this.B = true;
            Class cls = getClass();
            if (cls != RequestParams.class) {
                this.a = (HttpRequest) cls.getAnnotation(HttpRequest.class);
            }
        }
        return this.a;
    }

    public String toString() {
        try {
            a();
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        String uri = getUri();
        if (TextUtils.isEmpty(uri)) {
            return super.toString();
        }
        return uri + (uri.contains("?") ? "&" : "?") + super.toString();
    }
}
