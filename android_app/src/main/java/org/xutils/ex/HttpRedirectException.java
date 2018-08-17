package org.xutils.ex;

public class HttpRedirectException extends HttpException {
    public HttpRedirectException(int i, String str, String str2) {
        super(i, str);
        setResult(str2);
    }
}
