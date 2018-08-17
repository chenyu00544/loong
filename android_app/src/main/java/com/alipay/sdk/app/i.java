package com.alipay.sdk.app;

import com.baidu.mapapi.UIMsg.m_AppUI;

public enum i {
    SUCCEEDED(9000, "处理成功"),
    FAILED(m_AppUI.MSG_APP_SAVESCREEN, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(4001, "参数错误"),
    DOUBLE_REQUEST(5000, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");
    
    public int h;
    public String i;

    private i(int i, String str) {
        this.h = i;
        this.i = str;
    }

    public static i a(int i) {
        switch (i) {
            case 4001:
                return PARAMS_ERROR;
            case 5000:
                return DOUBLE_REQUEST;
            case 6001:
                return CANCELED;
            case 6002:
                return NETWORK_ERROR;
            case 8000:
                return PAY_WAITTING;
            case 9000:
                return SUCCEEDED;
            default:
                return FAILED;
        }
    }
}
