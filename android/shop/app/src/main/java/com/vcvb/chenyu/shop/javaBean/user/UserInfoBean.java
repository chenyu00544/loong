package com.vcvb.chenyu.shop.javaBean.user;

import com.vcvb.chenyu.shop.R;

public class UserInfoBean {
    private int isType = 1;
    private int title;
    private int subTitle;
    private String imgPath;
    private int imageId = R.drawable.icon_no_pic;

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(int subTitle) {
        this.subTitle = subTitle;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
