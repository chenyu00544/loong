package com.vcvb.chenyu.shop.javaBean.search;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FilterBean {

    private Integer isType;

    private String title;

    private Integer attr_id;
    private String cat_id;
    private String attr_name;

    private String subTitle;

    private boolean open = false;

    private List<Filter> list;

    public Integer getIsType() {
        return isType;
    }

    public void setIsType(Integer isType) {
        this.isType = isType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(Integer attr_id) {
        this.attr_id = attr_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<Filter> getList() {
        return list;
    }

    public void setList(List<Filter> list) {
        this.list = list;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setData(JSONObject json) {
        try {
            JSONArray array = json.getJSONArray("values");
            List<Filter> _filters = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                Filter filter = JsonUtils.fromJsonObject(object, Filter.class);
                _filters.add(filter);
            }
            this.setList(_filters);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void setPriceData(JSONObject json) {
        try {
            JSONArray array = json.getJSONArray("price_range");
            List<Filter> _filters = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                Filter filter = JsonUtils.fromJsonObject(object, Filter.class);
                _filters.add(filter);
            }
            this.setList(_filters);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void setAttrData(JSONObject json) {
        try {
            JSONArray array = json.getJSONArray("attr_values");
            List<Filter> _filters = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Filter filter = new Filter();
                filter.setAttr_value((String) array.get(i));
                _filters.add(filter);
            }
            this.setList(_filters);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
