var app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  onLoad: function (options) {
    var that = this
    // 生命周期函数--监听页面加载
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("share"),
      data: {
         "id": 0,
         "path":"pages/index/index"//生成二维码页面的参数
          },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        that.setData({
          speack_data: res.data.data,
        })
      }
    })
    //加载中
    this.loadingChange();
  },
  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 1000)
  },
  onShareAppMessage: function () {
    return {
      title: '我的代言',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/user/speak'
    }
  },
})