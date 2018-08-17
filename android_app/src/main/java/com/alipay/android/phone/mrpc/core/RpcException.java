package com.alipay.android.phone.mrpc.core;

public class RpcException extends RuntimeException {
    private String a;
    private int b;
    private String c;

    public RpcException(Integer num, String str) {
        super(a(num, str));
        this.b = num.intValue();
        this.c = str;
    }

    public RpcException(Integer num, String str, Throwable th) {
        super(a(num, str), th);
        this.b = num.intValue();
        this.c = str;
    }

    public RpcException(Integer num, Throwable th) {
        super(th);
        this.b = num.intValue();
    }

    public RpcException(String str) {
        super(str);
        this.b = 0;
        this.c = str;
    }

    private static String a(Integer num, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RPCException: ");
        if (num != null) {
            stringBuilder.append("[").append(num).append("]");
        }
        stringBuilder.append(" : ");
        if (str != null) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public int getCode() {
        return this.b;
    }

    public String getMsg() {
        return this.c;
    }

    public String getOperationType() {
        return this.a;
    }

    public void setOperationType(String str) {
        this.a = str;
    }
}
