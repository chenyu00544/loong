var app = getApp();
var QQMapWX = require('../../utils/qqmap-wx-jssdk.min.js')
var qqmap = new QQMapWX({
  key: "XSYBZ-P2G34-3K7UB-XPFZS-TBGHT-CXB4U"
})
var goods_id = '',
  navPage = 0,
  isloading = true,
  touchDot = 0,
  scrollTop = 0,
  timeout,
  page = 1,
  scroll_sub_X = 0;
Page({
  data: {
    banner: [],
    navigation: [],
    ads: [],
    goodses: [],
    popup: [],
    imageSize: [],
    adshow: false,
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    current: 0,
    scrollview_h: app.winHeight(),
    is_loading: false
  },
  onLoad: function(options) {
    var that = this;
    this.getIndexData(navPage, 0);
  },
  onShow() {
    // app.redirectTo("../../packageA/icon/index");
    // app.redirectTo("../goods/index?objectId=923");
    // app.switchTo("../user/index");
    // app.redirectTo("../../packageA/user/history");
    // app.redirectTo("../../packageA/group/index");
    // app.redirectTo("../../packageA/group/rank");
    app.redirectTo("../../packageA/group/order/index");
    // app.redirectTo("../../packageA/group/goods?objectId=923");
    var that = this
    if (wx.getStorageSync('token')) {
      // app.redirectTo("../../packageA/login/index");
    }
    that.getLocation();
  },

  //获取页面数据
  getIndexData: function(nav) {
    var nav_id = nav;
    var that = this;
    app.vcvbRequest("index", {
      nav_id: nav,
    }).then((res) => {
      if (res.data.data != undefined) {
        if (!that.data.ads.length) {
          for (let index in res.data.data.adses) {
            if (res.data.data.adses[index].type == "slide") {
              that.data.banner = res.data.data.adses[index].ads;
            } else if (res.data.data.adses[index].type == "navigation") {
              that.data.navigation = res.data.data.adses[index].ads;
            } else {
              that.data.ads.push(res.data.data.adses[index]);
            }
          }
        }
        that.data.goodses = res.data.data.goodses;
        that.setData({
          banner: that.data.banner,
          navigation: that.data.navigation,
          ads: that.data.ads,
          goodses: that.data.goodses
        });
      }
    });
  },
  //加载更多
  loadMore: function() {
    var that = this;
    page += 1;
    app.vcvbRequest("index/loadmore", {
      page: page,
    }).then((res) => {
      that.data.goodses = that.data.goodses.concat(res.data.data.goodses);
      that.setData({
        goodses: that.data.goodses,
        is_loading: false,
      });
    });
  },
  //进去详情页
  siteDetail: function(e) {
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
    }, function(res) {});
  },
  //返回顶部
  goTop: function(e) {
    this.setData({
      scrollTop: 0,
      autoplay: true
    })
  },

  //根据图片宽高设置宽高
  imageLoad: function(e) {
    var px2rpx = 2;
    //单位rpx
    var originWidth = e.detail.width * px2rpx,
      originHeight = e.detail.height * px2rpx,
      ratio = originWidth / originHeight;
    var viewWidth = 750,
      viewHeight //设定一个初始宽度
    //当它的宽度大于初始宽度时，实际效果跟mode=widthFix一致
    if (originWidth >= viewWidth) {
      //宽度等于viewWidth,只需要求出高度就能实现自适应
      viewHeight = viewWidth / ratio;
    } else {
      //如果宽度小于初始值，这时就不要缩放了
      viewWidth = originWidth;
      viewHeight = originHeight;
    }
    var imageSize = this.data.imageSize;
    imageSize[e.target.dataset.index] = {
      width: viewWidth,
      height: viewHeight
    }
    this.setData({
      imageSize: imageSize
    })
  },

  //关闭弹窗
  closePopup: function() {
    app.myAdData.isIndexAdShow = true;
    this.setData({
      adshow: false,
    })
  },

  formSubmit: function(e) {
    var formID = e.detail.formId;

    app.addFormId(formID);

    var targetUrl = e.currentTarget.dataset.url;

    if (targetUrl != '') {
      if (targetUrl == '../../pages/category/index') {
        wx.switchTab({
          url: '' + targetUrl,
        })
      } else {
        wx.navigateTo({
          url: '' + targetUrl
        })
      }
    }
  },
  move: function() {
    return;
  },
  //分享
  onShareAppMessage: function() {
    return {
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/index/index'
    }
  },
  //检测纵向滚动
  scroll_Y: function(e) {
    scrollTop = e.detail.scrollTop;
    this.setData({
      indexSearch: e.detail.scrollTop
    });
    if (e.detail.scrollTop > 300) {
      this.setData({
        floorstatus: true,
      })
    } else if (e.detail.scrollTop <= 300) {
      this.setData({
        floorstatus: false,
      })
    }
  },
  //滑到底部
  scrollToLower: function(e) {
    this.setData({
      is_loading: true,
    });
    this.loadMore();

  },
  //广告跳转
  adsNav: function(e) {
    console.log(e);
  },

  //上拉加载
  onReachBottom: function() {
    var that = this;
    page = page + 1;
    wx.showLoading({
      title: '玩命加载中',
    })
  },
  //定位
  chooseLocation() {
    var that = this
    wx.chooseLocation({
      success: (res) => {
        wx.setStorageSync('currentPosition', res)
        var lat = res.latitude;
        var lon = res.longitude;
        qqmap.reverseGeocoder({
          location: {
            latitude: lat,
            longitude: lon
          },
          success: (res) => {
            app.vcvbRequest(("location/specific"), {
              address: res.result.address_component.city,
            })
              .then((res) => {
                that.setData({
                  address: res.address,
                })
              })
            var addess
            if (res.result.address_component.province == res.result.address_component.city) {
              addess = res.result.address_component.city
            } else {
              addess = res.result.address_component.city
            }
            that.setData({
              hasLocation: true,
              address: addess,
            })
          },
          fail: (res) => { },
        });

      }
    })
  },
  //获取当前定位
  getLocation() {
    var that = this
    wx.getLocation({
      success: (res) => {
        //缓存当前位置坐标
        var value = wx.getStorageSync('currentPosition')
        if (value) {
          // that.transformRegion(value)
        } else {
          wx.setStorageSync('currentPosition', res)
          // that.transformRegion(res)
        }
      },
      fail: (res) => {
        wx.showModal({
          title: '温馨提示',
          content: '不允许定位,会对地区商品价格有影响，请确认，去重新允许！',
          success: (res) => {
            if (res.confirm) {
              wx.getSetting({
                success(res) {
                  wx.openSetting({
                    success: (res) => {
                      if (!res.authSetting["scope.userLocation"]) {
                        that.getLocation()
                      } else {
                        wx.getLocation({
                          success: (res) => {
                            //缓存当前位置坐标
                            var value = wx.getStorageSync('currentPosition')
                            if (value) {
                              // that.transformRegion(value)
                            } else {
                              wx.setStorageSync('currentPosition', res)
                              // that.transformRegion(res)
                            }
                          },
                        })
                      }
                    }
                  })
                },
              })
            } else if (res.cancel) {
              that.getLocation()
            }
          },
        })
      },
    })
  },
  transformRegion(res) {
    var that = this
    var lat = res.latitude;
    var lon = res.longitude;
    qqmap.reverseGeocoder({
      location: {
        latitude: lat,
        longitude: lon
      },
      success: (res) => {
        var addess
        if (res.result.address_component.province == res.result.address_component.city) {
          addess = res.result.address_component.city
        } else {
          addess = res.result.address_component.city
        }
        that.setData({
          hasLocation: true,
          address: addess,
        })
      },
      fail: (res) => { },
    });
  },
})