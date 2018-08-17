package org.xutils.http;

import com.umeng.message.util.HttpRequest;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT(HttpRequest.METHOD_PUT),
    PATCH("PATCH"),
    HEAD(HttpRequest.METHOD_HEAD),
    MOVE("MOVE"),
    COPY("COPY"),
    DELETE(HttpRequest.METHOD_DELETE),
    OPTIONS(HttpRequest.METHOD_OPTIONS),
    TRACE(HttpRequest.METHOD_TRACE),
    CONNECT("CONNECT");
    
    private final String a;

    private HttpMethod(String str) {
        this.a = str;
    }

    public String toString() {
        return this.a;
    }

    public static boolean permitsRetry(HttpMethod httpMethod) {
        return httpMethod == GET;
    }

    public static boolean permitsCache(HttpMethod httpMethod) {
        return httpMethod == GET || httpMethod == POST;
    }

    public static boolean permitsRequestBody(HttpMethod httpMethod) {
        return httpMethod == POST || httpMethod == PUT || httpMethod == PATCH || httpMethod == DELETE;
    }
}
