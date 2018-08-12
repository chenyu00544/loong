var app = getApp();
var nav = 0;
var page = 0;
Page({
  data: {
    scrollview_h: app.winHeight()+45,
    curSubNav: 0,
    faat: [],
    scrollX: 0,
    curIndex: 1,
  },

  onLoad: function(options) {
    nav = options.nav;
    page = options.page;
    this.getIndexData(nav, page);
  },

  //获取页面数据
  getIndexData: function(nav, index) {
    var nav_id = nav;
    var that = this;
    app.netReq("index", {
      nav_id: nav,
    }, function(res) {
      if (that.data.faat.length == 0) {
        that.data.faat = res.data.data.faat;
      }
      that.setData({
        faat: that.data.faat,
        curSubNav: index
      });
      that.selectNav(index);
    });
  },

  selectNav: function(index) {
    var page = index;
    if (page == 0){
      page = 1
    }
    this.setData({
      curSubNav: index,
      toSubView: 'nav_sub_' + this.data.faat[page-1].act_id,
    });
  },

  selectSubNav: function(e) {

    let index = parseInt(e.currentTarget.dataset.index);
    this.setData({
      curSubNav: index,
      // toSubView: 'nav_sub_' + this.data.faat[index].act_id,
    });

    if (e.detail.x > app.winWidth() - 100) {
      for (var i = 0; i < this.data.faat.length; i++) {
        if ('nav_sub_' + this.data.faat[i].act_id == e.currentTarget.id) {
          if (i < this.data.faat.length - 1) {
            console.log(this.data.scrollX);
            this.setData({
              scrollX: this.data.scrollX + app.winWidth() / 2
            })
          }
        }
      }

    } else if (e.detail.x < 100) {
      for (var i = 0; i < this.data.faat.length; i++) {
        if ('nav_sub_' + this.data.faat[i].act_id == e.currentTarget.id) {
          if (i < 1) {
            this.data.curIndex = 1;
          } else {
            this.data.curIndex = i;
          }
          break
        }
      }
      this.setData({
        toSubView: 'nav_sub_' + this.data.faat[this.data.curIndex - 1].act_id,
      })
      for (var i = 0; i < this.data.faat.length; i++) {
        if ('nav_sub_' + this.data.faat[i].act_id == e.currentTarget.id) {
          if (i > 1) {
            this.data.scrollX = e.currentTarget.offsetLeft;
          } else {
            this.data.scrollX = 0;
          }
        }
      }
    }
  },
  scroll_X: function(e) {
    this.data.scrollX = e.detail.scrollLeft;
  },

  //快捷导航
  commonNav: function() {
    var that = this;
    var nav_select
    that.setData({
      nav_select: !that.data.nav_select
    });
  },
  nav: function(e) {
    var that = this;
    var cont = e.currentTarget.dataset.index;
    if (cont == "home") {
      wx.switchTab({
        url: '../index/index',
      });
    } else if (cont == "fenlei") {
      wx.switchTab({
        url: '../category/index',
      });
    } else if (cont == "cart") {
      wx.switchTab({
        url: '../flow/index',
      });
    } else if (cont == "profile") {
      wx.switchTab({
        url: '../user/index',
      });
    }
  },
  //进去详情页
  siteDetail: function (e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var goodsId = e.currentTarget.dataset.goodsid;
    var formID = e.detail.formId;
    app.addFormId(formID);

    wx.navigateTo({
      url: "../goods/index?objectId=" + goodsId,
    });
    app.netReq("user/trajectory", {
      utype: 1,
      type_name: "普通商品",
      action_name: e.currentTarget.dataset.goods_name
    }, function (res) { });
  },
})