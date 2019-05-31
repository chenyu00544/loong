var app = getApp();
var page = "1"
Page({
  data: {
    indicatorDots: true,
    autoplay: true,
    currentTab: 0, //预设当前项的值
    scrollLeft: 0, //tab标题的滚动条位置
    scrollSubLeft: 0,
    scrollTop: 0,
    subNavIndex:0,
  },

  onLoad: function(options) {
    var that = this
    that.getNav();
  },

  //获取导航
  getNav: function() {
    var that = this;
    app.vcvbRequest("team/nav")
        .then((res) => {
      if (res.data.code == 0) {
        that.setData({
          nav: res.data.data,
          currentTab: res.data.data[0].id
        });
        that.groupList(res.data.data[0].id);
      }
    });
  },

  //商品列表
  groupList: function(navId) {
    var that = this
    app.vcvbRequest("team", {
      page:1,
      id: navId
    }).then((res) => {
      that.setData({
        list: res.data.data
      });
    });
  },

  subNavClick:function(e){
    var index = e.currentTarget.dataset.id || 0;
    this.setData({
      subNavIndex: index,
    });
  },

  // 点击标题切换当前页时改变样式
  swichNav: function(e) {
    var that = this
    var cur = e.target.dataset.current;
    var index = e.currentTarget.dataset.index
    //切换效果
    if (this.data.currentTaB == cur) {
      return false;
    } else {
      that.groupList(cur);
      this.setData({
        currentTab: cur
      });
    }
    this.setData({
      scrollTop: 0
    });
  },
  /*返回顶部*/
  goTop: function(e) {
    this.setData({
      scrollTop: 0
    })
  },
  scroll: function(e) {
    if (e.detail.scrollTop > 300) {
      this.setData({
        floorstatus: true
      });
    } else {
      this.setData({
        floorstatus: false
      });
    }
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
  //子频道
  groupChannel: function(e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var tcId = that.data.index.team_categories_child[index].tc_id
    wx.navigateTo({
      url: "../group/channel?objectId=" + tcId
    });

  },

  //获取点击的id值
  siteDetail: function(e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var goodsId = that.data.list.goodses[that.data.subNavIndex].goods[index].goods_id;
    wx.navigateTo({
      url: "../group/goods?objectId=" + goodsId
    });
  },

  //下拉刷新完后关闭
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },

  //分享
  onShareAppMessage: function() {
    return {
      title: '小程序拼购',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/group/index'
    }
  }
})
