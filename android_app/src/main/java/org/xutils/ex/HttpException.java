package org.xutils.ex;

import android.text.TextUtils;

public class HttpException extends BaseException {
    private int a;
    private String b;
    private String c;
    private String d;

    public HttpException(int i, String str) {
        super(str);
        this.a = i;
    }

    public void setCode(int i) {
        this.a = i;
    }

    public void setMessage(String str) {
        this.c = str;
    }

    public int getCode() {
        return this.a;
    }

    public String getErrorCode() {
        return this.b == null ? String.valueOf(this.a) : this.b;
    }

    public void setErrorCode(String str) {
        this.b = str;
    }

    public String getMessage() {
        if (TextUtils.isEmpty(this.c)) {
            return super.getMessage();
        }
        return this.c;
    }

    public String getResult() {
        return this.d;
    }

    public void setResult(String str) {
        this.d = str;
    }

    public String toString() {
        return "errorCode: " + getErrorCode() + ", msg: " + getMessage() + ", result: " + this.d;
    }
}
