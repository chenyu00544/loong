///全局 是否正在加载中 仅用于前端 如果已经在加载的时候，不要二次加载。释放后才可以继续下拉
//var isLoading = false;
var isHasMoreT = false;

var app = getApp()
// var url = app.apiUrl("goods/list");
var url = app.apiUrl("search/index");
var id = 1;
var page = 1;
var per_page = 10;
var keyword = "";
var sort_key = "1";
var sort_value = "0";
var shop = 1;
// 获取数据的方法，具体怎么获取列表数据大家自行发挥

var GetList = function (that) {
  that.setData({
    hidden: false
  });
  if (sort_value == 1) {
    sort_value = 0
  } else {
    sort_value = 1
  }

  // page = page + 1;  //原始有  但是一开始是2 所以错。加载成功后加一

  var token = wx.getStorageSync('token');

  wx.request({
    url: url,
    data: {
      id: id,
      page: page,
      per_page: per_page,
      shop: shop,
      keyword: keyword,
      sort_key: sort_key,
      sort_value: sort_value,
      warehouse_id: "1",
      area_id: "1"
    },
    method: "post",
    header: {
      'Content-Type': 'application/json',
      'X-ECTouch-Authorization': token
    },
    success: function (res) {
      page = page + 1;

      // console.log("********"+res.data.data.length);

      //console.log("==============" +per_page);

      if (res.data.data.length == per_page) {
        //console.log('--------true-------:');
        isHasMoreT = true;
      }
      else {
        isHasMoreT = false;
        //console.log('--------false-------:');
      }

      that.setData({
        list: res.data.data,
        hidden: true,
        isHasMore: isHasMoreT
      });
    }
  });
}


var GetListMore = function (that) {
  // isHasMore = true;//调试
  //var that = this; 

  if (that.data.isHasMore == false) {
    console.log('--------下拉刷新--不存在更多-----:');
    return;

  }

  if (that.data.isLoading) {
    console.log('--------下拉刷新--JIAZAI ZHONG-----:');
    return;

  }

  console.log('--------下拉刷新--加载中:');

  that.setData({
    isLoading: true
  });

  //isLoading = true;

  if (sort_value == 1) {
    sort_value = 0
  } else {
    sort_value = 1
  }

  // page = page + 1;  //原始有  但是一开始是2 所以错。加载成功后加一

  var token = wx.getStorageSync('token');

  wx.request({
    url: url,
    data: {
      id: id,
      page: page,
      per_page: per_page,
      shop: shop,
      keyword: keyword,
      sort_key: sort_key,
      sort_value: sort_value,
      warehouse_id: "1",
      area_id: "1",
    },
    method: "post",
    header: {
      'Content-Type': 'application/json',
      'X-ECTouch-Authorization': token
    },
    success: function (res) {
      page = page + 1;

      if (res.data.data.length == per_page) {

        isHasMoreT = true;

      }
      else {
        isHasMoreT = false;
      }

      that.data.list = that.data.list.concat(res.data.data);

      that.setData({
        list: that.data.list,
        isLoading: false,
        isHasMore: isHasMoreT
      });

    }
  });
}

Page({
  data: {

    isLoading: false,
    isHasMore: true,

    loadingSize: '20',
    loadingColor: "#444444",
    current: "1",
    hidden: true,
    list: [],
    scrollTop: 0,
    scrollHeight: 0,
    maskVisual: 'hidden',
    currentItem: 0,
    currentPrice: 0,
    showView: true,
    showPrice: true,
    logo_show: false,
    //筛选暂不开放
    brandsCate: [
      {
        data_id: "0",
        id: "0",
        name: "全部",
        checked: true
      },
      {
        data_id: "1",
        id: "1",
        name: "苹果"
      },
      {
        data_id: "2",
        id: "2",
        name: "三星"
      }
    ],
    hotrecent: [
      {
        id: "0",
        name: "0-500",
        checked: true
      },
      {
        id: "1",
        name: "501-1000"
      },
      {
        id: "2",
        name: "1001-10000"
      }
    ]
  },
  onLoad: function (e) {
    id = e.id || ''
    keyword = e.content || ''
    //   这里要非常注意，微信的scroll-view必须要设置高度才能监听滚动事件，所以，需要在页面的onLoad事件中给scroll-view的高度赋值
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          scrollHeight: res.windowHeight
        });
      }
    });
    if (wx.getStorageSync('gzid')) {
      this.setData({
        logo_show: true,
      })
    }
    //加载中
    this.loadingChange()
    page = 1;
    var that = this;
    wx.showNavigationBarLoading();
    GetList(that);
    app.netReq("user/trajectory", { utype: 8, type_name: "进入二级分类" }, function (res) { });
  },
  loadingChange() {
    setTimeout(() => {
      wx.hideNavigationBarLoading();
    }, 2000)
  },
  /*header*/
  bindHeaderTap: function (event) {
    var that = this
    that.setData({
      current: event.target.dataset.index,
    });
    page = 1;
    sort_key = event.target.dataset.index
    GetList(this)
  },


  goodsDetail: function (e) {
    var that = this
    //获取点击的id值
    var index = e.currentTarget.dataset.index;
    var goodsId = that.data.list[index].goods_id;

    wx.navigateTo({
      url: "../goods/index?objectId=" + goodsId
    });
  },
  //下拉刷新完后关闭
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  },

  onShow: function () {
    //   在页面展示之后先获取一次数据
    console.log('onShow:');
  },
  bindDownLoad: function () {

    console.log('--------下拉刷新-------:');

    //   该方法绑定了页面滑动到底部的事件
    var that = this;
    GetListMore(that);
  },
  scroll: function (event) {
    //   该方法绑定了页面滚动时的事件，我这里记录了当前的position.y的值,为了请求数据之后把页面定位到这里来。
    this.setData({
      scrollTop: event.detail.scrollTop,
    });
  },
  goToTop: function () { //回到顶部
    this.setData({
      scrollTop: 0
    })
  },
  topLoad: function (event) {
    //   该方法绑定了页面滑动到顶部的事件，然后做上拉刷新
    page = 1;
    this.setData({
      // list: [],
      scrollTop: 0
    });
    GetList(this)
  },

  /*筛选*/
  onChangeShowState: function () {
    var that = this;
    that.setData({
      showView: (!that.data.showView)
    })
  },
  onChangeShowPrice: function () {
    var that = this;
    that.setData({
      showPrice: (!that.data.showPrice)
    })
  },

  cascadePopup: function () {

    var animation = wx.createAnimation({
      duration: 100,
      timingFunction: 'ease-in-out'
    });
    this.animation = animation;
    animation.translateX(-1000).step();
    this.setData({
      animationData: this.animation.export(),
      maskVisual: 'show'
    })
  },
  //点击遮区域关闭弹窗
  cascadeDismiss: function () {
    this.animation.translateX(1000).step();
    this.setData({
      animationData: this.animation.export(),
      maskVisual: 'hidden'
    });
  },
  /*品牌列表 */
  tagChoose: function (e) {
    var that = this
    var id = e.currentTarget.dataset.id;
    //设置当前样式
    that.setData({
      'currentItem': id
    })
  },

  tagPrice: function (e) {
    var that = this
    var id = e.currentTarget.dataset.id;
    //设置当前样式
    that.setData({
      'currentPrice': id
    })
  },

  radioChange: function (e) {
    var that = this
    that.setData({
      name: e.detail.value
    })
  },

  priceChange: function (e) {
    var that = this
    that.setData({
      pricenName: e.detail.value
    })
  },
  switch2Change: function (e) {
  },
  //快捷导航
  commonNav: function () {
    var that = this;
    var nav_select
    that.setData({
      nav_select: !that.data.nav_select
    });
  },
  nav: function (e) {
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
  onShareAppMessage: function () {
    return {
      title: '商品列表',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/product_list/product_list'
    }
  }

})