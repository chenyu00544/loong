package org.xutils.http.request;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.baidu.location.b.g;
import com.baidu.mapapi.UIMsg.l_ErrorNo;
import com.taobao.accs.ErrorCode;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;
import org.xutils.http.BaseParams.Header;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.body.ProgressBody;
import org.xutils.http.body.RequestBody;
import org.xutils.http.cookie.DbCookieStore;

public class HttpRequest extends UriRequest {
    private static final CookieManager f = new CookieManager(DbCookieStore.INSTANCE, CookiePolicy.ACCEPT_ALL);
    private String a = null;
    private boolean b = false;
    private InputStream c = null;
    private HttpURLConnection d = null;
    private int e = 0;

    HttpRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    protected String buildQueryUrl(RequestParams requestParams) {
        String uri = requestParams.getUri();
        StringBuilder stringBuilder = new StringBuilder(uri);
        if (!uri.contains("?")) {
            stringBuilder.append("?");
        } else if (!uri.endsWith("?")) {
            stringBuilder.append("&");
        }
        List<KeyValue> queryStringParams = requestParams.getQueryStringParams();
        if (queryStringParams != null) {
            for (KeyValue keyValue : queryStringParams) {
                Object obj = keyValue.key;
                uri = keyValue.getValueStr();
                if (!(TextUtils.isEmpty(obj) || uri == null)) {
                    stringBuilder.append(Uri.encode(obj, requestParams.getCharset())).append("=").append(Uri.encode(uri, requestParams.getCharset())).append("&");
                }
            }
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == '&') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == '?') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public String getRequestUri() {
        String str = this.queryUrl;
        if (this.d == null) {
            return str;
        }
        URL url = this.d.getURL();
        if (url != null) {
            return url.toString();
        }
        return str;
    }

    @TargetApi(19)
    public void sendRequest() throws Throwable {
        boolean z;
        this.b = false;
        this.e = 0;
        URL url = new URL(this.queryUrl);
        Proxy proxy = this.params.getProxy();
        if (proxy != null) {
            this.d = (HttpURLConnection) url.openConnection(proxy);
        } else {
            this.d = (HttpURLConnection) url.openConnection();
        }
        if (VERSION.SDK_INT < 19) {
            this.d.setRequestProperty(HttpConstant.CONNECTION, "close");
        }
        this.d.setReadTimeout(this.params.getReadTimeout());
        this.d.setConnectTimeout(this.params.getConnectTimeout());
        HttpURLConnection httpURLConnection = this.d;
        if (this.params.getRedirectHandler() == null) {
            z = true;
        } else {
            z = false;
        }
        httpURLConnection.setInstanceFollowRedirects(z);
        if (this.d instanceof HttpsURLConnection) {
            SSLSocketFactory sslSocketFactory = this.params.getSslSocketFactory();
            if (sslSocketFactory != null) {
                ((HttpsURLConnection) this.d).setSSLSocketFactory(sslSocketFactory);
            }
        }
        if (this.params.isUseCookie()) {
            try {
                List list = (List) f.get(url.toURI(), new HashMap(0)).get("Cookie");
                if (list != null) {
                    this.d.setRequestProperty("Cookie", TextUtils.join(";", list));
                }
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
        }
        List<Header> headers = this.params.getHeaders();
        if (headers != null) {
            for (Header header : headers) {
                String str = header.key;
                String valueStr = header.getValueStr();
                if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(valueStr))) {
                    if (header.setHeader) {
                        this.d.setRequestProperty(str, valueStr);
                    } else {
                        this.d.addRequestProperty(str, valueStr);
                    }
                }
            }
        }
        if (this.requestInterceptListener != null) {
            this.requestInterceptListener.beforeRequest(this);
        }
        HttpMethod method = this.params.getMethod();
        try {
            this.d.setRequestMethod(method.toString());
        } catch (ProtocolException e) {
            Field declaredField = HttpURLConnection.class.getDeclaredField("method");
            declaredField.setAccessible(true);
            declaredField.set(this.d, method.toString());
        }
        if (HttpMethod.permitsRequestBody(method)) {
            RequestBody requestBody = this.params.getRequestBody();
            if (requestBody != null) {
                if (requestBody instanceof ProgressBody) {
                    ((ProgressBody) requestBody).setProgressHandler(this.progressHandler);
                }
                Object contentType = requestBody.getContentType();
                if (!TextUtils.isEmpty(contentType)) {
                    this.d.setRequestProperty(com.umeng.message.util.HttpRequest.HEADER_CONTENT_TYPE, contentType);
                }
                long contentLength = requestBody.getContentLength();
                if (contentLength < 0) {
                    this.d.setChunkedStreamingMode(262144);
                } else if (contentLength < 2147483647L) {
                    this.d.setFixedLengthStreamingMode((int) contentLength);
                } else if (VERSION.SDK_INT >= 19) {
                    this.d.setFixedLengthStreamingMode(contentLength);
                } else {
                    this.d.setChunkedStreamingMode(262144);
                }
                this.d.setRequestProperty("Content-Length", String.valueOf(contentLength));
                this.d.setDoOutput(true);
                requestBody.writeTo(this.d.getOutputStream());
            }
        }
        if (this.params.isUseCookie()) {
            try {
                Map headerFields = this.d.getHeaderFields();
                if (headerFields != null) {
                    f.put(url.toURI(), headerFields);
                }
            } catch (Throwable th2) {
                LogUtil.e(th2.getMessage(), th2);
            }
        }
        this.e = this.d.getResponseCode();
        if (this.requestInterceptListener != null) {
            this.requestInterceptListener.afterRequest(this);
        }
        if (this.e == g.c || this.e == g.aa) {
            throw new HttpException(this.e, getResponseMessage());
        } else if (this.e >= ErrorCode.APP_NOT_BIND) {
            HttpException httpException = new HttpException(this.e, getResponseMessage());
            try {
                httpException.setResult(IOUtil.readStr(getInputStream(), this.params.getCharset()));
            } catch (Throwable th3) {
            }
            LogUtil.e(httpException.toString() + ", url: " + this.queryUrl);
            throw httpException;
        } else {
            this.b = true;
        }
    }

    public boolean isLoading() {
        return this.b;
    }

    public String getCacheKey() {
        if (this.a == null) {
            this.a = this.params.getCacheKey();
            if (TextUtils.isEmpty(this.a)) {
                this.a = this.params.toString();
            }
        }
        return this.a;
    }

    public Object loadResult() throws Throwable {
        this.b = true;
        return super.loadResult();
    }

    public Object loadResultFromCache() throws Throwable {
        this.b = true;
        DiskCacheEntity diskCacheEntity = LruDiskCache.getDiskCache(this.params.getCacheDirName()).setMaxSize(this.params.getCacheSize()).get(getCacheKey());
        if (diskCacheEntity == null) {
            return null;
        }
        if (HttpMethod.permitsCache(this.params.getMethod())) {
            Date lastModify = diskCacheEntity.getLastModify();
            if (lastModify.getTime() > 0) {
                this.params.setHeader("If-Modified-Since", a(lastModify));
            }
            Object etag = diskCacheEntity.getEtag();
            if (!TextUtils.isEmpty(etag)) {
                this.params.setHeader(com.umeng.message.util.HttpRequest.HEADER_IF_NONE_MATCH, etag);
            }
        }
        return this.loader.loadFromCache(diskCacheEntity);
    }

    public void clearCacheHeader() {
        this.params.setHeader("If-Modified-Since", null);
        this.params.setHeader(com.umeng.message.util.HttpRequest.HEADER_IF_NONE_MATCH, null);
    }

    public InputStream getInputStream() throws IOException {
        if (this.d != null && this.c == null) {
            this.c = this.d.getResponseCode() >= 400 ? this.d.getErrorStream() : this.d.getInputStream();
        }
        return this.c;
    }

    public void close() throws IOException {
        if (this.c != null) {
            IOUtil.closeQuietly(this.c);
            this.c = null;
        }
        if (this.d != null) {
            this.d.disconnect();
        }
    }

    public long getContentLength() {
        long j = 0;
        if (this.d != null) {
            try {
                j = (long) this.d.getContentLength();
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
            if (j >= 1) {
                return j;
            }
            try {
                return (long) getInputStream().available();
            } catch (Throwable th2) {
                return j;
            }
        }
        try {
            return (long) getInputStream().available();
        } catch (Throwable th3) {
            return j;
        }
    }

    public int getResponseCode() throws IOException {
        if (this.d != null) {
            return this.e;
        }
        if (getInputStream() != null) {
            return 200;
        }
        return l_ErrorNo.NETWORK_ERROR_404;
    }

    public String getResponseMessage() throws IOException {
        if (this.d != null) {
            return URLDecoder.decode(this.d.getResponseMessage(), this.params.getCharset());
        }
        return null;
    }

    public long getExpiration() {
        long j = -1;
        if (this.d == null) {
            return -1;
        }
        Object headerField = this.d.getHeaderField("Cache-Control");
        if (!TextUtils.isEmpty(headerField)) {
            StringTokenizer stringTokenizer = new StringTokenizer(headerField, ",");
            while (stringTokenizer.hasMoreTokens()) {
                String toLowerCase = stringTokenizer.nextToken().trim().toLowerCase();
                if (toLowerCase.startsWith("max-age")) {
                    int indexOf = toLowerCase.indexOf(61);
                    if (indexOf > 0) {
                        try {
                            long parseLong = Long.parseLong(toLowerCase.substring(indexOf + 1).trim());
                            if (parseLong > 0) {
                                j = System.currentTimeMillis() + (parseLong * 1000);
                            }
                        } catch (Throwable th) {
                            LogUtil.e(th.getMessage(), th);
                        }
                    }
                }
            }
        }
        if (j <= 0) {
            j = this.d.getExpiration();
        }
        if (j <= 0 && this.params.getCacheMaxAge() > 0) {
            j = System.currentTimeMillis() + this.params.getCacheMaxAge();
        }
        if (j <= 0) {
            return Long.MAX_VALUE;
        }
        return j;
    }

    public long getLastModified() {
        return getHeaderFieldDate(com.umeng.message.util.HttpRequest.HEADER_LAST_MODIFIED, System.currentTimeMillis());
    }

    public String getETag() {
        if (this.d == null) {
            return null;
        }
        return this.d.getHeaderField(com.umeng.message.util.HttpRequest.HEADER_ETAG);
    }

    public String getResponseHeader(String str) {
        if (this.d == null) {
            return null;
        }
        return this.d.getHeaderField(str);
    }

    public Map<String, List<String>> getResponseHeaders() {
        if (this.d == null) {
            return null;
        }
        return this.d.getHeaderFields();
    }

    public long getHeaderFieldDate(String str, long j) {
        return this.d == null ? j : this.d.getHeaderFieldDate(str, j);
    }

    private static String a(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM y HH:mm:ss 'GMT'", Locale.US);
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        simpleDateFormat.setTimeZone(timeZone);
        new GregorianCalendar(timeZone).setTimeInMillis(date.getTime());
        return simpleDateFormat.format(date);
    }
}
