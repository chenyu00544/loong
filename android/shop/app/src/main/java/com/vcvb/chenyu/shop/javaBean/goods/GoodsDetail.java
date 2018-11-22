package com.vcvb.chenyu.shop.javaBean.goods;

import com.vcvb.chenyu.shop.javaBean.address.AddressBean;
import com.vcvb.chenyu.shop.javaBean.store.Shop;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetail {

    private int isScroll = 0;

    private Integer goods_id;
    private Integer is_best;
    private Integer is_new;
    private Integer is_hot;
    private Integer is_promote;
    private Integer is_volume;
    private Integer is_fullcut;
    private Integer is_limit_buy;
    private Integer limit_buy_num;
    private Integer sales_volume;
    private Integer tid;
    private Integer count_cart = 0;
    private Integer comments_number;
    private Integer collect;
    private String cat_id;
    private String goods_name;
    private String goods_sn;
    private String brand_id;
    private String goods_number;
    private Double shop_price;
    private Double market_price;
    private Double promote_price;
    private Long promote_start_date;
    private Long promote_end_date;
    private Long limit_buy_start_date;
    private Long limit_buy_end_date;
    private String goods_video;
    private String goods_brief;
    private String shop_price_format;
    private String market_price_format;
    private String promote_price_format;
    private String original_img;
    private String goods_img;
    private List<AddressBean> addressBeans;
    private List<GoodsDescription> goodsDescriptions;
    private List<List<GoodsAttr>> multiAttrs;
    private List<GoodsAttr> singleAttrs;
    private List<GoodsDesc> goodsDescs;
    private List<GoodsGVP> goodsGVPS;
    private List<GoodsComment> comments;
    private List<GoodsBanner> banners;
    private List<GoodsCommentLabel> commentLabels;
    private List<GoodsFC> goodsFCS;
    private List<GoodsQA> qas;
    private List<GoodsCause> causes;
    private Shop shop;
    private GoodsTransport goodsTransport;
    private GoodsAttr goodsCountryCAttr;
    private GoodsAttr goodsTexAttr;
    private GoodsFaat goodsFaat;
    private GoodsBrand goodsBrand;

    public int getIsScroll() {
        return isScroll;
    }

    public void setIsScroll(int isScroll) {
        this.isScroll = isScroll;
    }

    public List<GoodsBanner> getBanners() {
        return banners;
    }

    public void setBanners(List<GoodsBanner> banners) {
        this.banners = banners;
    }

    public List<GoodsCommentLabel> getCommentLabels() {
        return commentLabels;
    }

    public void setCommentLabels(List<GoodsCommentLabel> commentLabels) {
        this.commentLabels = commentLabels;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public Double getShop_price() {
        return shop_price;
    }

    public void setShop_price(Double shop_price) {
        this.shop_price = shop_price;
    }

    public Double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Double market_price) {
        this.market_price = market_price;
    }

    public Double getPromote_price() {
        return promote_price;
    }

    public void setPromote_price(Double promote_price) {
        this.promote_price = promote_price;
    }

    public Long getPromote_start_date() {
        return promote_start_date;
    }

    public void setPromote_start_date(Long promote_start_date) {
        this.promote_start_date = promote_start_date;
    }

    public Long getPromote_end_date() {
        return promote_end_date;
    }

    public void setPromote_end_date(Long promote_end_date) {
        this.promote_end_date = promote_end_date;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public Integer getIs_best() {
        return is_best;
    }

    public void setIs_best(Integer is_best) {
        this.is_best = is_best;
    }

    public Integer getIs_new() {
        return is_new;
    }

    public void setIs_new(Integer is_new) {
        this.is_new = is_new;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public Integer getIs_promote() {
        return is_promote;
    }

    public void setIs_promote(Integer is_promote) {
        this.is_promote = is_promote;
    }

    public Integer getIs_volume() {
        return is_volume;
    }

    public void setIs_volume(Integer is_volume) {
        this.is_volume = is_volume;
    }

    public Integer getIs_fullcut() {
        return is_fullcut;
    }

    public void setIs_fullcut(Integer is_fullcut) {
        this.is_fullcut = is_fullcut;
    }

    public Integer getIs_limit_buy() {
        return is_limit_buy;
    }

    public void setIs_limit_buy(Integer is_limit_buy) {
        this.is_limit_buy = is_limit_buy;
    }

    public Integer getLimit_buy_num() {
        return limit_buy_num;
    }

    public void setLimit_buy_num(Integer limit_buy_num) {
        this.limit_buy_num = limit_buy_num;
    }

    public Long getLimit_buy_start_date() {
        return limit_buy_start_date;
    }

    public void setLimit_buy_start_date(Long limit_buy_start_date) {
        this.limit_buy_start_date = limit_buy_start_date;
    }

    public Long getLimit_buy_end_date() {
        return limit_buy_end_date;
    }

    public void setLimit_buy_end_date(Long limit_buy_end_date) {
        this.limit_buy_end_date = limit_buy_end_date;
    }

    public Integer getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(Integer sales_volume) {
        this.sales_volume = sales_volume;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public List<GoodsCause> getCauses() {
        return causes;
    }

    public void setCauses(List<GoodsCause> causes) {
        this.causes = causes;
    }

    public Integer getComments_number() {
        return comments_number;
    }

    public void setComments_number(Integer comments_number) {
        this.comments_number = comments_number;
    }

    public String getGoods_video() {
        return goods_video;
    }

    public void setGoods_video(String goods_video) {
        this.goods_video = goods_video;
    }

    public String getGoods_brief() {
        return goods_brief;
    }

    public void setGoods_brief(String goods_brief) {
        this.goods_brief = goods_brief;
    }

    public String getShop_price_format() {
        return shop_price_format;
    }

    public void setShop_price_format(String shop_price_format) {
        this.shop_price_format = shop_price_format;
    }

    public String getMarket_price_format() {
        return market_price_format;
    }

    public void setMarket_price_format(String market_price_format) {
        this.market_price_format = market_price_format;
    }

    public String getPromote_price_format() {
        return promote_price_format;
    }

    public void setPromote_price_format(String promote_price_format) {
        this.promote_price_format = promote_price_format;
    }

    public GoodsBrand getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(GoodsBrand goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public List<AddressBean> getAddressBeans() {
        return addressBeans;
    }

    public void setAddressBeans(List<AddressBean> addressBeans) {
        this.addressBeans = addressBeans;
    }

    public List<GoodsDescription> getGoodsDescriptions() {
        return goodsDescriptions;
    }

    public void setGoodsDescriptions(List<GoodsDescription> goodsDescriptions) {
        this.goodsDescriptions = goodsDescriptions;
    }

    public List<List<GoodsAttr>> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(List<List<GoodsAttr>> multiAttrs) {
        this.multiAttrs = multiAttrs;
    }

    public List<GoodsAttr> getSingleAttrs() {
        return singleAttrs;
    }

    public void setSingleAttrs(List<GoodsAttr> singleAttrs) {
        this.singleAttrs = singleAttrs;
    }

    public List<GoodsDesc> getGoodsDescs() {
        return goodsDescs;
    }

    public void setGoodsDescs(List<GoodsDesc> goodsDescs) {
        this.goodsDescs = goodsDescs;
    }

    public List<GoodsGVP> getGoodsGVPS() {
        return goodsGVPS;
    }

    public void setGoodsGVPS(List<GoodsGVP> goodsGVPS) {
        this.goodsGVPS = goodsGVPS;
    }

    public List<GoodsComment> getComments() {
        return comments;
    }

    public void setComments(List<GoodsComment> comments) {
        this.comments = comments;
    }

    public GoodsFaat getGoodsFaat() {
        return goodsFaat;
    }

    public void setGoodsFaat(GoodsFaat goodsFaat) {
        this.goodsFaat = goodsFaat;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<GoodsFC> getGoodsFCS() {
        return goodsFCS;
    }

    public void setGoodsFCS(List<GoodsFC> goodsFCS) {
        this.goodsFCS = goodsFCS;
    }

    public List<GoodsQA> getQas() {
        return qas;
    }

    public void setQas(List<GoodsQA> qas) {
        this.qas = qas;
    }

    public GoodsTransport getGoodsTransport() {
        return goodsTransport;
    }

    public void setGoodsTransport(GoodsTransport goodsTransport) {
        this.goodsTransport = goodsTransport;
    }

    public GoodsAttr getGoodsCountryCAttr() {
        return goodsCountryCAttr;
    }

    public void setGoodsCountryCAttr(GoodsAttr goodsCountryCAttr) {
        this.goodsCountryCAttr = goodsCountryCAttr;
    }

    public GoodsAttr getGoodsTexAttr() {
        return goodsTexAttr;
    }

    public void setGoodsTexAttr(GoodsAttr goodsTexAttr) {
        this.goodsTexAttr = goodsTexAttr;
    }

    public Integer getCount_cart() {
        return count_cart;
    }

    public void setCount_cart(Integer count_cart) {
        this.count_cart = count_cart;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public void setData(JSONObject Json) throws JSONException {
        this.setBanner(Json);
        this.setCommentLabel(Json);
        this.setGoodsCause(Json);
        this.setBrand(Json);
        this.setAddress(Json);
        this.setGoodsDescription(Json);
        this.setGoodsDescription(Json);
        this.setMultiAttr(Json);
        this.setSingleAttr(Json);
        this.setMobileDescs(Json);
        this.setGVP(Json);
        this.setFullCut(Json);
        this.setComment(Json);
        this.setFaat(Json);
        this.setStore(Json);
        this.setQA(Json);
        this.setTransport(Json);

        this.setGoods_id(Json.getInt("goods_id"));
        this.setCat_id(Json.getString("cat_id"));
        this.setGoods_name(Json.getString("goods_name"));
        this.setGoods_sn(Json.getString("goods_sn"));
        this.setBrand_id(Json.getString("brand_id"));
        this.setGoods_number(Json.getString("goods_number"));
        this.setShop_price(Json.getDouble("shop_price"));
        this.setMarket_price(Json.getDouble("market_price"));
        this.setPromote_price(Json.getDouble("promote_price"));
        this.setPromote_start_date(Json.getLong("promote_start_date"));
        this.setPromote_end_date(Json.getLong("promote_end_date"));
        this.setOriginal_img(Json.getString("original_img"));
        this.setGoods_img(Json.getString("goods_img"));
        this.setIs_best(Json.getInt("is_best"));
        this.setIs_new(Json.getInt("is_new"));
        this.setIs_hot(Json.getInt("is_hot"));
        this.setIs_promote(Json.getInt("is_promote"));
        this.setIs_volume(Json.getInt("is_volume"));
        this.setIs_fullcut(Json.getInt("is_fullcut"));
        this.setIs_limit_buy(Json.getInt("is_limit_buy"));
        this.setLimit_buy_num(Json.getInt("limit_buy_num"));
        this.setLimit_buy_start_date(Json.getLong("limit_buy_start_date"));
        this.setLimit_buy_end_date(Json.getLong("limit_buy_end_date"));
        this.setSales_volume(Json.getInt("sales_volume"));
        this.setTid(Json.getInt("tid"));
        this.setComments_number(Json.getInt("comments_number"));
        this.setGoods_video(Json.getString("goods_video"));
        this.setGoods_brief(Json.getString("goods_brief"));
        this.setShop_price_format(Json.getString("shop_price_format"));
        this.setMarket_price_format(Json.getString("market_price_format"));
        this.setPromote_price_format(Json.getString("promote_price_format"));
        this.setCount_cart(Json.getInt("count_cart"));
        this.setCollect(Json.getInt("collect"));
    }

    private void setBanner(JSONObject Json) {
        try {
            JSONArray bannersJsonArray = Json.getJSONArray("ggallery");
            List<GoodsBanner> banners = new ArrayList<>();
            for (int i = 0; i < bannersJsonArray.length(); i++) {
                JSONObject object = (JSONObject) bannersJsonArray.get(i);
                GoodsBanner banner = JsonUtils.fromJsonObject(object, GoodsBanner.class);
                banners.add(banner);
            }
            this.setBanners(banners);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setCommentLabel(JSONObject Json) {
        try {
            JSONArray commentLabelJsonArray = Json.getJSONArray("comment_label");
            List<GoodsCommentLabel> _commentLabels = new ArrayList<>();
            for (int i = 0; i < commentLabelJsonArray.length(); i++) {
                JSONObject object = (JSONObject) commentLabelJsonArray.get(i);
                GoodsCommentLabel commentLabel = JsonUtils.fromJsonObject(object,
                        GoodsCommentLabel.class);
                _commentLabels.add(commentLabel);
            }
            this.setCommentLabels(_commentLabels);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setGoodsCause(JSONObject Json) {
        try {
            JSONArray causesJsonArray = Json.getJSONArray("goods_cause");
            List<GoodsCause> _causes = new ArrayList<>();
            for (int i = 0; i < causesJsonArray.length(); i++) {
                JSONObject object = (JSONObject) causesJsonArray.get(i);
                GoodsCause cause = JsonUtils.fromJsonObject(object, GoodsCause.class);
                _causes.add(cause);
            }
            this.setCauses(_causes);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setBrand(JSONObject Json) {
        try {
            JSONObject bObject = Json.getJSONObject("brand");
            GoodsBrand goodsBrand = JsonUtils.fromJsonObject(bObject, GoodsBrand.class);
            goodsBrand.setData(Json);
            this.setGoodsBrand(goodsBrand);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setAddress(JSONObject Json) {
        try {
            JSONArray addressJsonArray = Json.getJSONObject("user").getJSONArray("addresses");
            List<AddressBean> _addresses = new ArrayList<>();
            for (int i = 0; i < addressJsonArray.length(); i++) {
                JSONObject object = (JSONObject) addressJsonArray.get(i);
                AddressBean addressBean = JsonUtils.fromJsonObject(object, AddressBean.class);
                if (Json.getJSONObject("user").getInt("address_id") == addressBean.getAddress_id
                        ()) {
                    addressBean.setDef(1);
                }
                _addresses.add(addressBean);
            }
            this.setAddressBeans(_addresses);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setGoodsDescription(JSONObject Json) {
        try {
            JSONArray goodsDescriptionJsonArray = Json.getJSONArray("goods_description");
            List<GoodsDescription> _goodsDescriptions = new ArrayList<>();
            for (int i = 0; i < goodsDescriptionJsonArray.length(); i++) {
                JSONObject object = (JSONObject) goodsDescriptionJsonArray.get(i);
                GoodsDescription goodsDescription = JsonUtils.fromJsonObject(object,
                        GoodsDescription.class);
                _goodsDescriptions.add(goodsDescription);
            }
            this.setGoodsDescriptions(_goodsDescriptions);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setMultiAttr(JSONObject Json) {
        try {
            JSONArray mAttrJsonArray = Json.getJSONArray("multi_attr");
            List<List<GoodsAttr>> _mattrs = new ArrayList<>();
            for (int i = 0; i < mAttrJsonArray.length(); i++) {
                JSONArray mSubAttrJsonArray = (JSONArray) mAttrJsonArray.get(i);
                List<GoodsAttr> _attrs = new ArrayList<>();
                for (int j = 0; j < mSubAttrJsonArray.length(); j++) {
                    JSONObject object = (JSONObject) mSubAttrJsonArray.get(j);
                    GoodsAttr goodsAttr = JsonUtils.fromJsonObject(object, GoodsAttr.class);
                    if (j == 0) {
                        goodsAttr.setIsSelect(true);
                    }
                    _attrs.add(goodsAttr);
                }
                _mattrs.add(_attrs);
            }
            this.setMultiAttrs(_mattrs);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setSingleAttr(JSONObject Json) {
        try {
            JSONArray sAttrJsonArray = Json.getJSONArray("single_attr");
            List<GoodsAttr> _sattr = new ArrayList<>();
            for (int i = 0; i < sAttrJsonArray.length(); i++) {
                JSONObject object = (JSONObject) sAttrJsonArray.get(i);
                String attr_group = (String) object.get("attr_group");
                if (attr_group.equals("2")) {
                    GoodsAttr countryAttr = JsonUtils.fromJsonObject(object, GoodsAttr.class);
                    this.setGoodsCountryCAttr(countryAttr);
                }
                if (attr_group.equals("1")) {
                    GoodsAttr taxAttr = JsonUtils.fromJsonObject(object, GoodsAttr.class);
                    this.setGoodsTexAttr(taxAttr);
                }
                GoodsAttr goodsAttr = JsonUtils.fromJsonObject(object, GoodsAttr.class);
                _sattr.add(goodsAttr);
            }
            this.setSingleAttrs(_sattr);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setMobileDescs(JSONObject Json) {
        try {
            JSONArray goodsInfoJsonArray = Json.getJSONArray("mobile_descs");
            List<GoodsDesc> _goodsInfo = new ArrayList<>();
            for (int i = 0; i < goodsInfoJsonArray.length(); i++) {
                JSONObject object = (JSONObject) goodsInfoJsonArray.get(i);
                GoodsDesc goodsDesc = JsonUtils.fromJsonObject(object, GoodsDesc.class);
                _goodsInfo.add(goodsDesc);
            }
            this.setGoodsDescs(_goodsInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setGVP(JSONObject Json) {
        try {
            JSONArray gvpJsonArray = Json.getJSONArray("gvp");
            List<GoodsGVP> _goodsGvp = new ArrayList<>();
            for (int i = 0; i < gvpJsonArray.length(); i++) {
                JSONObject object = (JSONObject) gvpJsonArray.get(i);
                GoodsGVP goodsGVP = JsonUtils.fromJsonObject(object, GoodsGVP.class);
                _goodsGvp.add(goodsGVP);
            }
            this.setGoodsGVPS(_goodsGvp);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setFullCut(JSONObject Json) {
        try {
            JSONArray fcJsonArray = Json.getJSONArray("fullcut");
            List<GoodsFC> _goodsFCS = new ArrayList<>();
            for (int i = 0; i < fcJsonArray.length(); i++) {
                JSONObject object = (JSONObject) fcJsonArray.get(i);
                GoodsFC goodsFC = JsonUtils.fromJsonObject(object, GoodsFC.class);
                _goodsFCS.add(goodsFC);
            }
            this.setGoodsFCS(_goodsFCS);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setComment(JSONObject Json) {
        try {
            JSONArray commentsJsonArray = Json.getJSONArray("comments");
            List<GoodsComment> _goodsComment = new ArrayList<>();
            for (int i = 0; i < commentsJsonArray.length(); i++) {
                JSONObject object = (JSONObject) commentsJsonArray.get(i);
                GoodsComment goodsComment = JsonUtils.fromJsonObject(object, GoodsComment.class);
                goodsComment.setData(object);
                _goodsComment.add(goodsComment);
            }
            this.setComments(_goodsComment);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setFaat(JSONObject Json) {
        try {
            JSONObject faatJsonObject = Json.getJSONObject("faat");
            GoodsFaat goodsFaat = JsonUtils.fromJsonObject(faatJsonObject, GoodsFaat.class);
            goodsFaat.setData(faatJsonObject);
            this.setGoodsFaat(goodsFaat);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setStore(JSONObject Json) {
        try {
            JSONObject object = Json.getJSONObject("shop");
            Shop shop = JsonUtils.fromJsonObject(object, Shop.class);
            this.setShop(shop);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setQA(JSONObject Json) {
        try {
            JSONArray qaJsonArray = Json.getJSONArray("qa");
            List<GoodsQA> _qas = new ArrayList<>();
            for (int i = 0; i < qaJsonArray.length(); i++) {
                JSONObject object = (JSONObject) qaJsonArray.get(i);
                GoodsQA qa = JsonUtils.fromJsonObject(object, GoodsQA.class);
                _qas.add(qa);
            }
            this.setQas(_qas);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setTransport(JSONObject Json) {
        try {
            JSONObject tpJSONObject = Json.getJSONObject("transport");
            GoodsTransport goodsTransport = JsonUtils.fromJsonObject(tpJSONObject, GoodsTransport
                    .class);
            goodsTransport.setData(tpJSONObject);
            this.setGoodsTransport(goodsTransport);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }
}
