package com.vcvb.chenyu.shop.javaBean.cate;

import com.vcvb.chenyu.shop.javaBean.home.Ads;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Categroy {
    private Integer id;
    private String cat_name;
    private Integer sort_order;
    private String cat_alias_name;
    private String touch_icon;
    private boolean is_current = false;
    private List<SubCategroy> subCategroys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public String getCat_alias_name() {
        return cat_alias_name;
    }

    public void setCat_alias_name(String cat_alias_name) {
        this.cat_alias_name = cat_alias_name;
    }

    public String getTouch_icon() {
        return touch_icon;
    }

    public void setTouch_icon(String touch_icon) {
        this.touch_icon = touch_icon;
    }

    public boolean isIs_current() {
        return is_current;
    }

    public void setIs_current(boolean is_current) {
        this.is_current = is_current;
    }

    public List<SubCategroy> getSubCategroys() {
        return subCategroys;
    }

    public void setSubCategroys(List<SubCategroy> subCategroys) {
        this.subCategroys = subCategroys;
    }

    public void setData(JSONObject json) {
        try {
            JSONArray jsonArray = json.getJSONArray("sub_cate");
            List<SubCategroy> _cates = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);

                SubCategroy subCategroy = new SubCategroy();
                JSONObject titleObject = object.getJSONObject("title");
                SubCate title = JsonUtils.fromJsonObject(titleObject, SubCate.class);
                subCategroy.setHeader(title);

                JSONArray subCateJSONArray = object.getJSONArray("sub_cate");
                List<Object> objects = new ArrayList<>();
                for (int j = 0; j < subCateJSONArray.length(); j++){
                    JSONObject subCateObject = (JSONObject) subCateJSONArray.get(i);
                    SubCate subCate = JsonUtils.fromJsonObject(subCateObject, SubCate.class);
                    objects.add(subCate);
                }

                JSONArray adsJSONArray = object.getJSONArray("ads");
                for (int j = 0; j < adsJSONArray.length(); j++){
                    JSONObject adsObject = (JSONObject) adsJSONArray.get(i);
                    Ads ads = JsonUtils.fromJsonObject(adsObject, Ads.class);
                    objects.add(ads);
                }
                subCategroy.setObjs(objects);
                _cates.add(subCategroy);
            }
            this.setSubCategroys(_cates);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
