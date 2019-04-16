var app = getApp()
var token
var goodshistory
Page({
  data: {
    is_first_action: true,
  },
  onShow() {
    var that = this;
    token = wx.getStorageSync('token')
    if (!token) {
      app.redirectTo("../../packageA/login/index");
    }
    that.user();
  },
  user() {
    var that = this
    app.vcvbRequest(("user/index"), {
        page: "1",
      }).then((res) => {
        app.log(res);
        if (!res.data.data.user_id) {
            app.redirectTo("../../packageA/login/index")
        }
        that.setData({
          userInfo: res.data.data,
          hidden: true
        })
      })
  },
  //增值发票
  invoiceNav(e) {
    var that = this
    if (that.data.is_first_action == true) {
      that.setData({
        is_first_action: false,
      })
      app.dscRequest(("user/invoice/detail"))
        .then((res) => {
          if (res.data.data != false) {
            wx.navigateTo({
              url: '../../packageA/invoice/detail'
            })
          } else {
            wx.navigateTo({
              url: '../../packageA/invoice/create'
            })
          }
          that.setData({
            invoices: res.data.data
          })
        })
    }
  },
  //我的微店
  drpRegister() {
    let that = this
    app.vcvbRequest(("drp"))
      .then((res) => {
        if (res.data.error == 0) {
          app.redirectTo("../../packageA/drp/index")
        } else {
          app.redirectTo("../../packageA/drp/register")
        }
      })
  },
  //下拉刷新
  onPullDownRefresh() {
    var that = this
    wx.showNavigationBarLoading() //在标题栏中显示加载
    that.user();
    setTimeout(()=> {
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
    }, 1500);
  },
  onShareAppMessage() {
    return {
      title: '小程序首页',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/user/user'
    }
  }
})