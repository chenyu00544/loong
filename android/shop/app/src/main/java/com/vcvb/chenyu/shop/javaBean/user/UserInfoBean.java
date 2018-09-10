package com.vcvb.chenyu.shop.javaBean.user;

import android.net.Uri;

import com.vcvb.chenyu.shop.R;

public class UserInfoBean {
    private int isType = 1;
    private int title;
    private int subTitle;
    private String imgPath;
    private Uri imgUriFront;
    private Uri imgUriBack;
    private int imageId = R.drawable.icon_no_pic;
    private String name;
    private boolean isBind = false;
    private int icon;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsBind() {
        return isBind;
    }

    public void setIsBind(boolean isBind) {
        this.isBind = isBind;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Uri getImgUriFront() {
        return imgUriFront;
    }

    public void setImgUriFront(Uri imgUriFront) {
        this.imgUriFront = imgUriFront;
    }

    public Uri getImgUriBack() {
        return imgUriBack;
    }

    public void setImgUriBack(Uri imgUriBack) {
        this.imgUriBack = imgUriBack;
    }

}
