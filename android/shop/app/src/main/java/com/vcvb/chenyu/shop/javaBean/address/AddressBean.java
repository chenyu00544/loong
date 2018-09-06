package com.vcvb.chenyu.shop.javaBean.address;

public class AddressBean {
    private int isType = 1;
    private String userName;
    private String phoneMun;
    private String addressInfo;
    private boolean def = false;
    private int addressId;

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneMun() {
        return phoneMun;
    }

    public void setPhoneMun(String phoneMun) {
        this.phoneMun = phoneMun;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public boolean getDef() {
        return def;
    }

    public void setDef(boolean def) {
        this.def = def;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

}
