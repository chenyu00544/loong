package com.vcvb.chenyu.shop.tools;

public class Routes {
    private static final byte[] LOCKER = new byte[0];
    private static Routes mInstance;
    private String host = "https://www.missmall.com/mobile/public/api/wx/";
    private String index = host+"index";

    public static Routes getInstance() {
        if (mInstance == null) {
            synchronized (LOCKER) {
                if (mInstance == null) {
                    mInstance = new Routes();
                }
            }
        }
        return mInstance;
    }

    public String getIndex(){
        return this.index;
    }
}
