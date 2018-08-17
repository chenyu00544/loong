package com.ecjia.hamster.paycenter.base;

public interface ECJiaOnPaySucceedListener {

    public enum PaymentType {
        PAYMENT_ZERO,
        PAYMENT_CODE_COD,
        PAYMENT_CODE_BANK,
        PAYMENT_CODE_ONLINE,
        PAYMENT_CODE_BALANCE,
        PAYMENT_UPPAY,
        PAYMENT_ALIPAY,
        PAYMENT_WXPAY,
        PAYMENT_WAPPAY
    }

    void a(PaymentType paymentType, String str);
}
