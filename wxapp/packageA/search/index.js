var app = getApp();
var page = 1;
var keyword = "";
var cate_id = 0;
var cate_name = "";
var type = 1;

var goods_type = [];
var catIds = [];
var brandIds = [];
var goodsIds = "";

var search_attr = [];
Page({
  data: {
    price_min: "",
    price_max: "",
    nav_focus: 0,
    showVolumnSort: 0,
    showPriceSort: 0,
    showViewMol: false,
    list: [],
    filter: [],
    count_goods: 0,
    scrollview_h: app.winHeight(),
  },

  onLoad: function(options) {
    var that = this;
    cate_id = (options.cate_id ? options.cate_id : 0);
    keyword = (options.keyword ? options.keyword : '搜索商品') //接收搜索关键词
    that.setData({
      keyword: keyword
    });
    that.getList();
  },

  onShow: function() {
    page = 1;
    goodsIds = "";
  },

  //获取搜索数据
  getList: function() {
    var that = this;
    app.vcvbRequest("search/index", {
      page: page,
      keywords: keyword,
      type: type,
      cate_id: cate_id,
      goods_ids: goodsIds,
      cate_name: cate_name,
      volume: that.data.showVolumnSort,
      price_order: that.data.showPriceSort,
    }).then((res) => {
      that.setData({
        searchLoading: false
      });
      if (res.data.code == 0) {
        var storeList = that.data.list.concat(res.data.data);
        for (var i in res.data.data) {
          goods_type.push(res.data.data[i].goods_type);
          catIds.push(res.data.data[i].cat_id);
          brandIds.push(res.data.data[i].brand_id);
        }
        that.setData({
          list: storeList,
        });
        that.filterBy();
      }
    });
  },

  //获取筛选属性
  filterBy: function() {
    var that = this;
    app.vcvbRequest("search/filter/by", {
      goods_type: goods_type.join(","),
      cat_id: catIds.join(","),
      brand_id: brandIds.join(",")
    }).then((res) => {
      if (res.data.code == 0) {
        for (var i in res.data.data.server.values) {
          res.data.data.server.values[i].selected = false;
        }
        for (var i in res.data.data.price.price_range) {
          res.data.data.price.price_range[i].selected = false;
        }
        for (var i in res.data.data.brand.values) {
          res.data.data.brand.values[i].selected = false;
        }
        for (var i in res.data.data.cate.values) {
          res.data.data.cate.values[i].selected = false;
        }
        for (var i in res.data.data.attr) {
          var selected = [];
          res.data.data.attr[i].selecteds = [];
          for (var j in res.data.data.attr[i].attr_values) {
            selected.push(false);
          }
          res.data.data.attr[i].selecteds = selected;
        }
        that.setData({
          filter: res.data.data,
        });
      }
    });
  },

  //滚动到底部触发事件
  searchScrollLower: function() {
    var that = this;
    that.setData({
      searchLoading: true
    });
    page += 1;
    that.getList();
  },

  //筛选
  toSynthesize: function(e) {
    var that = this;
    that.setData({
      showViewMol: !that.data.showViewMol,
    });
  },

  //综合
  toCateAll: function(e) {
    var that = this;
    type = 1;
    if (e.currentTarget.id == 'list-0') {
      that.setData({
        list: [],
        nav_focus: e.currentTarget.dataset.index,
      });
      page = 1;
      that.getList();
    }
  },

  //销量
  toNum: function(event) {
    var that = this;
    type = 2;
    that.setData({
      list: [],
      showVolumnSort: !that.data.showVolumnSort,
      nav_focus: event.currentTarget.dataset.index,
    });
    page = 1;
    that.getList();
  },

  //价格
  toPrice: function(event) {
    var that = this;
    type = 3;
    that.setData({
      list: [],
      showPriceSort: !that.data.showPriceSort,
      nav_focus: event.currentTarget.dataset.index,
    });
    page = 1;
    that.getList();
  },

  //该方法绑定了页面滚动时的事件
  scroll: function(event) {
    this.setData({
      scrollTop: event.detail.scrollTop,
      viewBox: true
    });
  },

  //回到顶部
  goToTop: function() {
    this.setData({
      scrollTop: 0
    })
  },

  /*商品排列切换*/
  onChangeShowState: function() {
    var that = this;
    that.setData({
      showView: (!that.data.showView),
      arrange: (that.data.arrange ? '' : 'arrange')
    })
  },

  //快捷导航
  commonNav: function() {
    var that = this;
    that.setData({
      nav_select: !that.data.nav_select
    });
  },

  //悬浮导航
  nav: function(e) {
    var that = this;
    var cont = e.currentTarget.dataset.index;
    if (cont == "home") {
      wx.switchTab({
        url: '../../pages/index/index',
      });
    } else if (cont == "fenlei") {
      wx.switchTab({
        url: '../../pages/category/index',
      });
    } else if (cont == "cart") {
      wx.switchTab({
        url: '../../pages/flow/index',
      });
    } else if (cont == "profile") {
      wx.switchTab({
        url: '../../pages/user/index',
      });
    }
  },

  //关闭搜索属性弹窗
  closeModel: function() {
    var that = this;
    that.setData({
      showViewMol: !that.data.showViewMol,
    });
  },

  //确定筛选结果搜索数据
  formSubmit: function(e) {
    var that = this;
    page = 1;
    that.setData({
      list: [],
      count_goods: 0
    });
    that.getList();
    this.closeModel();
  },

  //选择筛选属性，高亮显示
  selectSearchAttr: function(e) {
    var that = this;
    that.data.filter;
    var item = e.currentTarget.dataset.item;
    if (item == "server") {
      var id = e.currentTarget.dataset.id;
      for (var i in that.data.filter.server.values) {
        if (id == i) {
          that.data.filter.server.values[i].selected = !that.data.filter.server.values[i].selected;
        } else {
          that.data.filter.server.values[i].selected = false;
        }
      }
      that.setData({
        filter: that.data.filter
      });
    } else if (item == "price") {
      var id = e.currentTarget.dataset.id;
      var price_min = 0;
      var price_max = 0;
      for (var i in that.data.filter.price.price_range) {
        that.data.filter.price.price_range[i].selected = false;
        if (id == i) {
          that.data.filter.price.price_range[i].selected = true;
          price_min = that.data.filter.price.price_range[i].min;
          price_max = that.data.filter.price.price_range[i].max;
          search_attr["min_price"] = price_min;
          search_attr["max_price"] = price_max;
        }
      }
      that.setData({
        filter: that.data.filter,
        price_min: price_min,
        price_max: price_max
      });
    } else if (item == "brand") {
      var id = e.currentTarget.dataset.id;
      for (var i in that.data.filter.brand.values) {
        if (id == i) {
          that.data.filter.brand.values[i].selected = !that.data.filter.brand.values[i].selected;
        }
      }
      that.setData({
        filter: that.data.filter,
      });
    } else if (item == "cate") {
      var id = e.currentTarget.dataset.id;
      for (var i in that.data.filter.cate.values) {
        if (id == i) {
          that.data.filter.cate.values[i].selected = !that.data.filter.cate.values[i].selected;
        }
      }
      that.setData({
        filter: that.data.filter,
      });
    } else if (item == "attr") {
      var idi = e.currentTarget.dataset.idi;
      var idj = e.currentTarget.dataset.idj;
      for (var i in that.data.filter.attr) {
        if (idi == i) {
          for (var j in that.data.filter.attr[i].attr_values) {
            if (idj == j) {
              that.data.filter.attr[i].selecteds[j] = !that.data.filter.attr[i].selecteds[j];
            }
          }
        }
      }
      that.setData({
        filter: that.data.filter
      });
    }
    that.arrangeSearchAttr();
  },

  //整理搜索属性
  arrangeSearchAttr: function() {
    var that = this;
    var serverIds = [];
    for (var i in that.data.filter.server.values) {
      if (that.data.filter.server.values[i].selected) {
        serverIds.push(that.data.filter.server.values[i].server_id);
      }
    }
    if (serverIds.length > 0) {
      search_attr["server_id"] = serverIds.join(",");
    }

    var brandIds = [];
    for (var i in that.data.filter.brand.values) {
      if (that.data.filter.brand.values[i].selected) {
        brandIds.push(that.data.filter.brand.values[i].id);
      }
    }
    if (brandIds.length > 0) {
      search_attr["brand_id"] = brandIds.join(",");
    }

    var cateIds = [];
    for (var i in that.data.filter.cate.values) {
      if (that.data.filter.cate.values[i].selected) {
        cateIds.push(that.data.filter.cate.values[i].id);
      }
    }
    if (cateIds.length > 0) {
      search_attr["cate_id"] = cateIds.join(",");
    }

    for (var i in that.data.filter.attr) {
      var attrIds = [];
      delete search_attr["attrid_" + that.data.filter.attr[i].attr_id];
      for (var j in that.data.filter.attr[i].selecteds) {
        if (that.data.filter.attr[i].selecteds[j]) {
          attrIds.push(that.data.filter.attr[i].attr_values[j]);
        }
        if (attrIds.length > 0) {
          search_attr["attrid_" + that.data.filter.attr[i].attr_id] = attrIds.join(",");
        }
      }
    }
    that.getCountGoods();
  },

  //获取商品统计数据
  getCountGoods: function() {
    var that = this;
    var data = {};
    data.keywords = keyword;
    for (var i in search_attr) {
      data[i] = search_attr[i];
    }
    app.vcvbRequest("search/filter/to", data).then((res) => {
      if (res.data.code == 0) {
        goodsIds = res.data.data.goods_id.join(",");
        if (goodsIds == ""){
          goodsIds = "0";
        }
        that.setData({
          count_goods: res.data.data.goods_id.length
        })
      }
    })
  },

  //输入框输入数字
  minPrice: function(e) {
    this.setData({
      price_min: e.detail.value
    });
    search_attr["min_price"] = this.data.price_min;
  },

  //输入框输入数字
  maxPrice: function(e) {
    this.setData({
      price_max: e.detail.value
    });
    search_attr["max_price"] = this.data.price_max
  },
});