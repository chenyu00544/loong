package com.vcvb.chenyu.shop.javaBean.address;

public class AddressBean {
    private int isType = 1;
    private String userName;
    private String phoneMun;
    private String addressInfo;
    private boolean def = false;
    private int addressId;
    private String province;
    private int provinceId;
    private String city;
    private int cityId;
    private String county;
    private int countyId;

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }
}
