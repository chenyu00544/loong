package anet.channel.request;

import android.text.TextUtils;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.StringUtils;
import anet.channel.util.e;
import com.umeng.message.util.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public class Request {
    public static final String DEFAULT_CHARSET = "UTF-8";
    private String bizId;
    private BodyEntry body;
    private String charset;
    private int connectTimeout;
    private Map<String, String> headers;
    private e httpUrl;
    private boolean isHostnameVerifyEnable;
    private boolean isRedirectEnable;
    private String method;
    private Map<String, String> params;
    private int readTimeout;
    private int redirectTimes;
    public final RequestStatistic rs;
    private String seq;
    private URL url;

    /* compiled from: Taobao */
    public static class Builder {
        private String bizId;
        private BodyEntry body;
        private String charset;
        private int connectTimeout = 0;
        private Map<String, String> headers = new HashMap();
        private e httpUrl;
        private boolean isHostnameVerifyEnable = true;
        private boolean isRedirectEnable = true;
        private String method = "GET";
        private Map<String, String> params;
        private int readTimeout = 0;
        private int redirectTimes = 0;
        private RequestStatistic rs = null;
        private String seq;

        public Builder setUrl(e eVar) {
            this.httpUrl = eVar;
            return this;
        }

        public Builder setUrl(String str) {
            this.httpUrl = e.a(str);
            if (this.httpUrl != null) {
                return this;
            }
            throw new IllegalArgumentException("url is invalid! url = " + str);
        }

        public Builder setMethod(String str) {
            if ("POST".equalsIgnoreCase(str)) {
                this.method = "POST";
            } else {
                this.method = "GET";
            }
            return this;
        }

        public Builder setHeaders(Map<String, String> map) {
            this.headers.clear();
            if (map != null) {
                this.headers.putAll(map);
            }
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.headers.put(str, str2);
            return this;
        }

        public Builder setParams(Map<String, String> map) {
            this.params = map;
            return this;
        }

        public Builder addParam(String str, String str2) {
            if (this.params == null) {
                this.params = new HashMap();
            }
            this.params.put(str, str2);
            return this;
        }

        public Builder setCharset(String str) {
            this.charset = str;
            return this;
        }

        public Builder setBody(BodyEntry bodyEntry) {
            this.body = bodyEntry;
            return this;
        }

        public Builder setRedirectEnable(boolean z) {
            this.isRedirectEnable = z;
            return this;
        }

        public Builder setRedirectTimes(int i) {
            this.redirectTimes = i;
            return this;
        }

        public Builder setHostnameVerifyEnable(boolean z) {
            this.isHostnameVerifyEnable = z;
            return this;
        }

        public Builder setBizId(String str) {
            this.bizId = str;
            return this;
        }

        public Builder setSeq(String str) {
            this.seq = str;
            return this;
        }

        public Builder setReadTimeout(int i) {
            this.readTimeout = i;
            return this;
        }

        public Builder setConnectTimeout(int i) {
            this.connectTimeout = i;
            return this;
        }

        public Builder setRequestStatistic(RequestStatistic requestStatistic) {
            this.rs = requestStatistic;
            return this;
        }

        public Request build() {
            return new Request();
        }
    }

    /* compiled from: Taobao */
    public static final class Method {
        public static final String GET = "GET";
        public static final String POST = "POST";
    }

    private Request(Builder builder) {
        this.method = "GET";
        this.isRedirectEnable = true;
        this.isHostnameVerifyEnable = true;
        this.redirectTimes = 0;
        this.connectTimeout = 10000;
        this.readTimeout = 10000;
        this.method = builder.method;
        this.headers = builder.headers;
        this.params = builder.params;
        this.body = builder.body;
        this.charset = builder.charset;
        this.isRedirectEnable = builder.isRedirectEnable;
        this.redirectTimes = builder.redirectTimes;
        this.isHostnameVerifyEnable = builder.isHostnameVerifyEnable;
        this.bizId = builder.bizId;
        this.seq = builder.seq;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.httpUrl = builder.httpUrl;
        this.httpUrl.g();
        this.rs = builder.rs != null ? builder.rs : new RequestStatistic(getHost(), this.bizId);
        formatUrl();
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.method = this.method;
        builder.headers = this.headers;
        builder.params = this.params;
        builder.body = this.body;
        builder.charset = this.charset;
        builder.isRedirectEnable = this.isRedirectEnable;
        builder.redirectTimes = this.redirectTimes;
        builder.isHostnameVerifyEnable = this.isHostnameVerifyEnable;
        builder.httpUrl = this.httpUrl;
        builder.bizId = this.bizId;
        builder.seq = this.seq;
        builder.connectTimeout = this.connectTimeout;
        builder.readTimeout = this.readTimeout;
        builder.rs = this.rs;
        return builder;
    }

    public e getHttpUrl() {
        return this.httpUrl;
    }

    public String getUrlString() {
        return this.httpUrl.d();
    }

    public URL getUrl() {
        if (this.url == null) {
            this.url = this.httpUrl.e();
        }
        return this.url;
    }

    public void setDnsOptimize(String str, int i) {
        this.httpUrl.a(str, i);
        this.rs.setIPAndPort(str, i);
    }

    public int getRedirectTimes() {
        return this.redirectTimes;
    }

    public String getHost() {
        return this.httpUrl.b();
    }

    public String getMethod() {
        return this.method;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    public String getContentEncoding() {
        return this.charset != null ? this.charset : "UTF-8";
    }

    public boolean isRedirectEnable() {
        return this.isRedirectEnable;
    }

    public boolean isHostnameVerifyEnable() {
        return this.isHostnameVerifyEnable;
    }

    public int postBody(OutputStream outputStream) throws IOException {
        if (this.body != null) {
            return this.body.writeTo(outputStream);
        }
        return 0;
    }

    public byte[] getBody() {
        if (this.body == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(128);
        try {
            postBody(byteArrayOutputStream);
        } catch (IOException e) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public String getBizId() {
        return this.bizId;
    }

    public String getSeq() {
        return this.seq;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    private void formatUrl() {
        String encodeQueryParams = StringUtils.encodeQueryParams(this.params, getContentEncoding());
        if (!TextUtils.isEmpty(encodeQueryParams)) {
            if (this.method == "GET" || (this.method == "POST" && this.body != null)) {
                String d = this.httpUrl.d();
                StringBuilder stringBuilder = new StringBuilder(this.httpUrl.d());
                if (stringBuilder.indexOf("?") == -1) {
                    stringBuilder.append('?');
                } else if (d.charAt(d.length() - 1) != '&') {
                    stringBuilder.append('&');
                }
                stringBuilder.append(encodeQueryParams);
                e a = e.a(stringBuilder.toString());
                if (a != null) {
                    this.httpUrl = a;
                    return;
                }
                return;
            }
            try {
                this.body = new ByteArrayEntry(encodeQueryParams.getBytes(getContentEncoding()));
                this.headers.put(HttpRequest.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + getContentEncoding());
            } catch (UnsupportedEncodingException e) {
            }
        }
    }
}
