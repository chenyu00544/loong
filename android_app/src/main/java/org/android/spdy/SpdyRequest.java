package org.android.spdy;

import anet.channel.util.HttpConstant;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class SpdyRequest {
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    private int connectionTimeoutMs = 0;
    private String domain;
    private Map<String, String> extHead;
    private String host;
    private String method;
    private int port;
    private RequestPriority priority;
    private String proxyIp = "0.0.0.0";
    private int proxyPort = 0;
    private int requestTimeoutMs = 0;
    private int retryTimes = 0;
    private URL url;

    public SpdyRequest(URL url, String str, int i, String str2, int i2, String str3, RequestPriority requestPriority, int i3, int i4, int i5) {
        this.url = url;
        this.domain = "";
        this.host = str;
        this.port = i;
        if (!(str2 == null || i2 == 0)) {
            this.proxyIp = str2;
            this.proxyPort = i2;
        }
        this.method = str3;
        this.extHead = new HashMap(5);
        this.priority = requestPriority;
        if (requestPriority == null) {
            this.priority = RequestPriority.DEFAULT_PRIORITY;
        }
        this.requestTimeoutMs = i3;
        this.connectionTimeoutMs = i4;
        this.retryTimes = i5;
    }

    public SpdyRequest(URL url, String str, int i, String str2, RequestPriority requestPriority) {
        this.url = url;
        this.domain = "";
        this.host = str;
        this.port = i;
        this.method = str2;
        this.extHead = new HashMap(5);
        this.priority = requestPriority;
        if (requestPriority == null) {
            this.priority = RequestPriority.DEFAULT_PRIORITY;
        }
    }

    public SpdyRequest(URL url, String str, RequestPriority requestPriority) {
        this.url = url;
        this.domain = "";
        this.host = url.getHost();
        this.port = url.getPort();
        if (this.port < 0) {
            this.port = url.getDefaultPort();
        }
        this.method = str;
        this.extHead = new HashMap(5);
        this.priority = requestPriority;
        if (requestPriority == null) {
            this.priority = RequestPriority.DEFAULT_PRIORITY;
        }
    }

    public SpdyRequest(URL url, String str, RequestPriority requestPriority, int i, int i2) {
        this.url = url;
        this.domain = "";
        this.host = url.getHost();
        this.port = url.getPort();
        if (this.port < 0) {
            this.port = url.getDefaultPort();
        }
        this.method = str;
        this.extHead = new HashMap(5);
        this.priority = requestPriority;
        if (requestPriority == null) {
            this.priority = RequestPriority.DEFAULT_PRIORITY;
        }
        this.requestTimeoutMs = i;
        this.connectionTimeoutMs = i2;
    }

    public SpdyRequest(URL url, String str) {
        this.url = url;
        this.domain = "";
        this.host = url.getHost();
        this.port = url.getPort();
        if (this.port < 0) {
            this.port = url.getDefaultPort();
        }
        this.method = str;
        this.extHead = new HashMap(5);
        this.priority = RequestPriority.DEFAULT_PRIORITY;
    }

    public SpdyRequest(URL url, String str, String str2, int i, String str3, int i2, String str4, RequestPriority requestPriority, int i3, int i4, int i5) {
        this.url = url;
        this.domain = str;
        this.host = str2;
        this.port = i;
        if (!(str3 == null || i2 == 0)) {
            this.proxyIp = str3;
            this.proxyPort = i2;
        }
        this.method = str4;
        this.extHead = new HashMap(5);
        this.priority = requestPriority;
        if (requestPriority == null) {
            this.priority = RequestPriority.DEFAULT_PRIORITY;
        }
        this.requestTimeoutMs = i3;
        this.connectionTimeoutMs = i4;
        this.retryTimes = i5;
    }

    public SpdyRequest(URL url, String str, String str2, int i, String str3, RequestPriority requestPriority) {
        this.url = url;
        this.domain = str;
        this.host = str2;
        this.port = i;
        this.method = str3;
        this.extHead = new HashMap(5);
        this.priority = requestPriority;
        if (requestPriority == null) {
            this.priority = RequestPriority.DEFAULT_PRIORITY;
        }
    }

    public SpdyRequest(URL url, String str, String str2, RequestPriority requestPriority) {
        this.url = url;
        this.domain = str;
        this.host = url.getHost();
        this.port = url.getPort();
        if (this.port < 0) {
            this.port = url.getDefaultPort();
        }
        this.method = str2;
        this.extHead = new HashMap(5);
        this.priority = requestPriority;
        if (requestPriority == null) {
            this.priority = RequestPriority.DEFAULT_PRIORITY;
        }
    }

    public SpdyRequest(URL url, String str, String str2, RequestPriority requestPriority, int i, int i2) {
        this.url = url;
        this.domain = str;
        this.host = url.getHost();
        this.port = url.getPort();
        if (this.port < 0) {
            this.port = url.getDefaultPort();
        }
        this.method = str2;
        this.extHead = new HashMap(5);
        this.priority = requestPriority;
        if (requestPriority == null) {
            this.priority = RequestPriority.DEFAULT_PRIORITY;
        }
        this.requestTimeoutMs = i;
        this.connectionTimeoutMs = i2;
    }

    public SpdyRequest(URL url, String str, String str2) {
        this.url = url;
        this.domain = str;
        this.host = url.getHost();
        this.port = url.getPort();
        if (this.port < 0) {
            this.port = url.getDefaultPort();
        }
        this.method = str2;
        this.extHead = new HashMap(5);
        this.priority = RequestPriority.DEFAULT_PRIORITY;
    }

    public void addHeader(String str, String str2) {
        this.extHead.put(str, str2);
    }

    public void addHeaders(Map<String, String> map) {
        this.extHead.putAll(map);
    }

    URL getUrl() {
        return this.url;
    }

    String getMethod() {
        return this.method;
    }

    int getPriority() {
        return this.priority.getPriorityInt();
    }

    private String getPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.url.getPath());
        if (this.url.getQuery() != null) {
            stringBuilder.append("?").append(this.url.getQuery());
        }
        if (this.url.getRef() != null) {
            stringBuilder.append("#").append(this.url.getRef());
        }
        return stringBuilder.toString();
    }

    Map<String, String> getHeaders() {
        Map<String, String> hashMap = new HashMap(5);
        hashMap.put(":path", getPath());
        hashMap.put(":method", this.method);
        hashMap.put(":version", "HTTP/1.1");
        hashMap.put(":host", this.url.getAuthority());
        hashMap.put(":scheme", this.url.getProtocol());
        if (this.extHead != null && this.extHead.size() > 0) {
            hashMap.putAll(this.extHead);
        }
        return hashMap;
    }

    String getUrlPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.url.getProtocol()).append(HttpConstant.SCHEME_SPLIT).append(this.url.getAuthority()).append(getPath());
        return stringBuilder.toString();
    }

    String getHost() {
        return this.host;
    }

    int getPort() {
        return this.port < 0 ? 80 : this.port;
    }

    String getProxyIp() {
        return this.proxyIp;
    }

    int getProxyPort() {
        return this.proxyPort;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    String getDomain() {
        return this.domain;
    }

    public String getAuthority() {
        return this.host + ":" + Integer.toString(this.port) + "/" + this.proxyIp + ":" + this.proxyPort;
    }

    public int getRequestTimeoutMs() {
        return this.requestTimeoutMs;
    }

    public int getConnectionTimeoutMs() {
        return this.connectionTimeoutMs;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }
}
