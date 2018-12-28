package com.vcvb.chenyu.shop.javaBean.evaluate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EvaImage {

    private List<String> imgs = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
