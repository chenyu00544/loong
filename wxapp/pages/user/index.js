var app = getApp()
var token
Page({
  data: {
    userInfo: {},
    user: [],
    phone: "18888888888",
    userService: [{
        name: "我的砍价",
        icon: "daojianfu",
        link: "../../pages/bargain/order/index",
        color: "#FF0C4B"
      },
      {
        name: "我的拼团",
        icon: "shehuituanti",
        link: "../../pages/group/order/index",
        color: "#FFB80C"
      },
      {
        name: "增值发票",
        icon: "fapiaoguanli",
        link: "../invoice/create",
        color: "#FD4100"
      },
      {
        name: "收货地址",
        icon: "dizhi",
        link: "../address/index",
        color: "#7ACF00"
      },
    ],
  },
  bindProfile: function() {
    wx.navigateTo({
      url: '../profile/profile'
    })
  },
  bindMoney: function() {
    wx.navigateTo({
      url: '../account/account'
    })
  },
  bindOrder: function() {
    wx.navigateTo({
      url: "../user_order/order"
    })
  },

  onLoad: function(option) {},

  onShow: function() {
    var that = this
    // 获取用户数据
    this.setData({
      userInfo: app.globalData.userInfo,
    })
    var token = wx.getStorageSync('token');
    wx.showNavigationBarLoading();
    wx.request({
      url: app.apiUrl("user"),
      data: {
        per_page: "10",
        page: "1"
      },
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      method: "POST",
      success: function (res) {
        if (!app.globalData.userInfo) {
          app.globalData.userInfo = { avatarUrl: res.data.data.userInfo.user_picture, nickName: res.data.data.userInfo.nick_name};
        }
        that.setData({
          recommend: res.data.data.best_goods,
          orderNum: res.data.data.order,
          user: res.data.data,
          phone: res.data.data.userInfo.mobile_phone,
          userInfo: app.globalData.userInfo,
        })
      }
    })
    //加载中
    this.loadingChange()
  },

  loadingChange() {
    setTimeout(() => {
      wx.hideNavigationBarLoading();
    }, 500)
  },

  //获取点击的id值
  siteDetail: function(e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    // console.log(index)
    var goodsId = that.data.recommend[index].goods_id;
    //  console.log(goodsId)
    wx.navigateTo({
      url: "../goods/index?objectId=" + goodsId
    });
    app.netReq("user/trajectory", {
      utype: 1,
      type_name: "用户中心普通商品",
      action_name: that.data.recommend[index].goods_name
    }, function(res) {});
  },
  invoiceNav: function(e) {
    var that = this
    token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("user/invoice/detail"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        if (res.data.data != false) {
          wx.navigateTo({
            url: '../invoice/detail'
          })
        } else {
          wx.navigateTo({
            url: '../invoice/create'
          })
        }
        that.setData({
          invoices: res.data.data
        })
      }
    })
  },
  userAddress: function(e) {
    // wx.setStorageSync('userAddress', { from: "address" })
    wx.navigateTo({
      url: '../address/index'
    })
  },
  onShareAppMessage: function() {
    return {
      title: '小程序首页',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/user/user'
    }
  },

  //cyc
  //下拉刷新完后关闭
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
  getUserInfo: function(res) {
    var userinfo = res.detail.userInfo;
    app.setUserInfo(res);
    this.setData({
      userInfo: res.detail.userInfo
    })
  },
  getPhoneNumber: function(e) {
    var that = this;
    token = wx.getStorageSync('token');
    wx.login({
      success: function(result) {
        var code = result.code;
        wx.request({
          url: app.apiUrl("user/authphone"),
          method: "POST",
          header: {
            'Content-Type': 'application/json',
            'X-ECTouch-Authorization': token
          },
          data: {
            code: code,
            iv: e.detail.iv,
            encryptedData: e.detail.encryptedData
          },
          success: function(res) {
            if (res.data.code == 1) {
              that.setData({
                phone: res.data.data.phoneNumber
              })
            } else {
              that.setData({
                phone: "18888888888"
              });
            }
          }
        })
      }
    })
  },
  //cyc
})