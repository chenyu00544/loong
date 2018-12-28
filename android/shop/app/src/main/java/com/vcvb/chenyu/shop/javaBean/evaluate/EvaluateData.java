package com.vcvb.chenyu.shop.javaBean.evaluate;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class EvaluateData{
    private Integer goods_id;
    private String content;

    private List<String> imgs;
    private List<File> files;
    private List<Label> labels;

    HashMap<String, Integer> star;

    public void EvaluateData() {
        HashMap<String, Integer> mp = new HashMap<>();
        mp.put("goods_rank", 5);
        mp.put("service_rank", 5);
        mp.put("shipping_rank", 5);
        this.setStar(mp);
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public HashMap<String, Integer> getStar() {
        return star;
    }

    public void setStar(HashMap<String, Integer> star) {
        this.star = star;
    }
}
