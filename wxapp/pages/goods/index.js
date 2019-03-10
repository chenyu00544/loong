var WxParse = require('../../wxParse/wxParse.js');
var app = getApp()
var token
var order = {
  id: "",
  num: 1,
  pro: [],
  prostr: []
}
var goodsbtn = ''
var tempOrderPro = [];
var tempOrderProStr = [];
var coupons_index = '';
var newId = ''; //接口返回值
Page({
  data: {
    showViewProperty: '',
    goodsType: "加入购物车",
    goodsbtns: "cart",

    hidden: false,
    hiddenOrder: false,
    hiddenAddress: true,
    is_collect: 0,
    currentIndex: 1,
    currentTab: 0,
    num: 1,
    goodsComment: [],
    indicatorDots: true,
    autoplay: true,
    interval: 4000,
    duration: 1000,
    showView: true,
    scrollTop: 0,
    floorstatus: false,
  },

  onLoad: function(options) {
    var that = this
    that.setData({
      isIphoneX: app.globalData.isIphoneX
    })

    // 获取用户数据
    var goodsId = options.objectId;
    token = wx.getStorageSync('token');
    order.id = goodsId

    //调用应用实例的方法获取全局数据
    app.vcvbRequest(("goods/detail"), {
      goods_id: goodsId,
      token: token
    }).then((res) => {
      wx.setNavigationBarTitle({
        title: res.data.data.goods_name,
      })
      if (res.data.data != undefined) {
        WxParse.wxParse('goods_desc', 'html', res.data.data.goods_desc, that, 5);
        that.setData({
          goodsDetail: res.data.data,
          hidden: true
        })
        //商品属性
        if (res.data.data.multi_attr != undefined) {
          that.setData({
            properties: res.data.data.multi_attr
          })
          //商品有属性则默认选中第一个
          for (var i in res.data.data.multi_attr) {
            that.getProper(res.data.data.multi_attr[i][0].goods_attr_id);
          }
        }
        that.getGoodsTotal()
      }
    })
  },

  onShow: function() {
    order.num = 1;
    order.pro = [];
    order.prostr = [];
  },

  /*提交*/
  goodsCheckout: function(e) {
    var that = this
    wx.setStorageSync('flowcheckout', {
      from: "checkout"
    })
    //获取id
    var goodsbtn = e.currentTarget.id || 'cart'
    var goodsId = that.data.goodsCont.goods_id

    app.vcvbRequest(("cart/add"), {
        "id": order.id,
        "num": order.num,
        "attr_id": JSON.stringify(tempOrderPro),
      })
      .then((res) => {
        var result = res.data.data;
        if (goodsbtn == 'cart') {
          if (res.data.data == '库存不足') {
            wx.showToast({
              title: '库存不足',
              image: '../../images/failure.png',
              duration: 2000
            })
          } else {
            that.setData({
              flowNum: res.data.data.total_number,
              showViewProperty: !that.data.showViewProperty,
              showViewMol: !that.data.showViewMol
            })
          }
        } else {
          app.vcvbRequest(("user/address/list"))
            .then((res) => {
              if (res.data.data != '') {
                wx.navigateTo({
                  url: "../../pages/flow/checkout"
                });
              } else {
                wx.removeStorageSync('pageOptions')
                wx.navigateTo({
                  url: "../../packageA/address/index"
                });
              }
            })
        }
        if (result == "商品已下架") {
          wx.showToast({
            title: '商品已下架',
            image: '../../images/failure.png',
            duration: 2000
          })
        }
      })
  },
  onChangeShowState: function() {
    var that = this;
    that.setData({
      showView: (!that.data.showView)
    })
  },
  /*增加商品数量*/
  up: function() {
    var num = this.data.num;
    num++;
    if (num >= 99) {
      num = 99
    }
    this.setData({
      num: num
    })
    order.num = num;
    this.getGoodsTotal();
  },
  /*减少商品数量*/
  down: function() {
    var num = this.data.num;
    num--;
    if (num <= 1) {
      num = 1
    }
    this.setData({
      num: num
    })
    order.num = num;
    this.getGoodsTotal();
  },
  /*手动输入商品*/
  import: function(e) {
    var that = this
    var stock = that.data.stock
    var num = Math.floor(e.detail.value);
    if (num <= 1) {
      num = 1
    }
    if (num >= stock) {
      num = stock
    }
    this.setData({
      num: num
    })
    order.num = num;
    this.getGoodsTotal();

  },
  /*单选*/
  modelTap: function(e) {
    this.getProper(e.currentTarget.id)
    this.getGoodsTotal();
  },
  /*属性选择计算*/
  getProper: function(id) {
    var categoryList = this.data.properties;
    for (var i in categoryList) {
      for (var j in categoryList[i]) {
        if (categoryList[i][j].goods_attr_id == id) {
          for (var k in categoryList[i]) {
            categoryList[i][k].checked = false;
          }
          categoryList[i][j].checked = true;
          order.pro[i] = id;
          order.prostr[i] = categoryList[i][j].attr_value;
          if (i == 0) {
            this.setData({
              attr_img: categoryList[i][j].attr_img_flie
            })
          }
        }
      }
    }
    this.setData({
      properties: categoryList,
    });
  },
  getGoodsTotal: function () {
    //提交属性  更新价格
    var that = this;
    app.vcvbRequest(("goods/property"), {
        goods_id: order.id,
        attr_id: order.pro,
        num: order.num,
        warehouse_id: "1",
        model_price: that.data.goodsDetail.model_price,
        area_id: "1"
      })
      .then((res) => {
        that.setData({
          goods_price: res.data.data.product_price_format,
          stock: res.data.data.product_number,
          goods_market_price: res.data.data.product_market_price_format,
        });
      });
  },

  /*收藏*/
  addCollect: function() {
    token = wx.getStorageSync('token')
    if (!token) {
      wx.navigateTo({
        url: "../../packageA/login/index"
      });
      return;
    }
    var that = this;
    app.vcvbRequest(("user/collect/add"), {
      "id": order.id,
    }).then((res) => {
      that.setData({
        collect_list: res.data.data
      })
    })
  },

  //商品相册
  setCurrent: function(e) {
    var that = this;
    if (e.detail.current > 0) {
      that.setData({
        currentTab: e.detail.current
      });
    } else {
      that.setData({
        currentTab: 0
      });
    }
    this.setData({
      currentIndex: e.detail.current + 1
    })
  },
  swichNav: function(e) {
    var that = this;

    that.setData({
      showViewvideo: (!that.data.showViewvideo)
      // currentTab: e.currentTarget.dataset.current
    });
  },
  imgPreview: function() { //图片预览
    const imgs = this.data.goodsCont.goods_img;
    console.log(this.data.goodsCont.goods_img)
    wx.previewImage({
      current: imgs[this.data.currentIndex - 1], // 当前显示图片的http链接
      urls: imgs // 需要预览的图片http链接列表
    })
  },
  /**内容切换 */
  toOrder: function(res) {
    this.setData({
      hiddenOrder: false,
      hiddenAddress: true
    })
  },
  toAddress: function(res) {
    this.setData({
      hiddenOrder: true,
      hiddenAddress: false
    })
  },
  //选择属性
  onChangeShowState: function() {
    var that = this;
    that.setData({
      showView: (!that.data.showView)
    })
  },
  //店铺
  storeDetail: function(e) {
    var id = this.data.goodsCont.detail.user_id
    wx.navigateTo({
      url: "../../packageA/store/index?objectId=" + id
    });
  },
  //优惠券
  onChangeShowCoupons: function() {
    var that = this;
    that.setData({
      showViewCoupons: (!that.data.showViewCoupons)
    })
  },
  //领取优惠券
  printCoupont: function(e) {
    var that = this;
    coupons_index = e.currentTarget.dataset.index;
    var couId = that.data.goodsCont.coupont[coupons_index].cou_id;
    app.vcvbRequest(("goods/coupons"), {
        "cou_id": couId,
      })
      .then((res) => {
        if (res.status_code != 500) {
          if (res.data.data.error == 2) {
            wx.showToast({
              title: "领取成功!",
              duration: 2000
            })
          } else {
            wx.showToast({
              image: '../../images/failure.png',
              title: "已领取!",
              duration: 2000
            })
          }
        } else {
          wx.showToast({
            title: "已领取!",
            duration: 2000
          })
        }
        app.vcvbRequest(("goods/detail"), {
          "id": order.id,
        }).then((res) => {
          that.setData({
            goodsCont: res.data.data,
          })
        })
      })
  },
  flowCart: function() {
    wx.switchTab({
      url: '../../pages/flow/index',
    });
  },

  //返回顶部
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
  bindSharing: function() {
    var that = this
    var goodsId = that.data.goodsCont.goods_info.goods_id
    wx.navigateTo({
      url: "../goods/speak?objectId=" + goodsId
    });
  },
  toChild: function() {
    var that = this
    var goodsId = that.data.goodsCont.goods_info.goods_id
    wx.navigateTo({
      url: "../goods/comment?objectId=" + goodsId
    })
  },
  //促销
  groupPlay: function() {
    var that = this;
    that.setData({
      showViewPlay: !that.data.showViewPlay,
      showViewMol: !that.data.showViewMol,
      isScroll: false
    })
  },
  bargainPlayclose: function() {
    var that = this;
    that.setData({
      showViewPlay: !that.data.showViewPlay,
      showViewMol: !that.data.showViewMol,
      isScroll: true
    })
  },
  //video
  onReady: function(res) {
    this.videoContext = wx.createVideoContext('myVideo')
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
  //分享
  onShareAppMessage: function() {
    var that = this
    var goods_name = that.data.goodsCont.goods_info.goods_name
    return {
      title: goods_name,
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/goods/index?objectId=' + order.id
    }
  },

  /* 弹出属性选择框 */
  addBargain: function(e) {
    token = wx.getStorageSync('token')
    if (!token) {
      wx.navigateTo({
        url: "../../packageA/login/index"
      });
      return;
    }
    var that = this;
    console.log(e.currentTarget.id);
    var goodsbtns = e.currentTarget.id || 'cart';
    var goodsType = "";
    if (goodsbtns == "cart") {
      goodsType = "加入购物车"
    } else {
      goodsType = "确定"
    }
    that.setData({
      showViewProperty: !that.data.showViewProperty,
      showViewMol: !that.data.showViewMol,
      goodsType: goodsType,
      goodsbtns: goodsbtns,
    })
  },
  onChangeShowState: function() {
    var that = this;
    that.setData({
      showViewProperty: !that.data.showViewProperty,
      showViewMol: !that.data.showViewMol
    })
  },
})