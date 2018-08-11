var app = getApp()
var order = {
  id: "",
}
Page({
  data: {
    code_img: ""
  },
  /**
   * 页面的初始数据
   */
  onLoad: function (options) {
    var that = this
    // 获取用户数据
    var goodsId = options.objectId;
    order.id = goodsId

    // 生命周期函数--监听页面加载
    var token = wx.getStorageSync('token')
    // 获取用户数据
    var goodsId = options.objectId;
    order.id = goodsId
    //调用应用实例的方法获取全局数据
    wx.request({
      url: app.apiUrl("goods/share"),
      data: {
        id: goodsId,
        "path":'pages/goods/index?objectId=' + order.id//当前分享二维码页
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        that.setData({
          goods: res.data.data,
        })
      }
    })
  },
  onShow: function () {
    var that = this
    // 获取用户数据
    app.getUserInfo(function (userInfo) {
      that.setData({
        userInfo: userInfo
      })
    })
  },
  onShareAppMessage: function () {
    return {
      title: '商品详情',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/goods/speak?objectId=' + order.id
    }
  },
})