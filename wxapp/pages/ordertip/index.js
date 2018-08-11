var app = getApp()
Page({
  data: {
    order_id: "",
    orders: [],
    address: [],
  },
  onLoad: function (options) {
    this.getOrderInfo();
  },

  getOrderInfo: function () {
    var that = this
    var token = wx.getStorageSync('token');
    wx.request({
      url: app.apiUrl('user/order/la'),
      method: 'post',
      data: {
        size: 1,
        page: 1,
        status: 0,
        type: "0"
      },
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        console.log(res);
        that.setData({
          order_id: res.data.data.list[0].order_sn,
          orders: res.data.data.list,
          address: res.data.data.address,
        })
      }
    })
  },
  getUser: function (res) {
    if (res.detail.userInfo) {
      app.setUserInfo(res);
      wx.switchTab({
        url: '../user/index'
      })
    }
  },
})