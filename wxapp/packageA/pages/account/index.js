var app = getApp();
Page({
  data: {
    funds: [],
  },
  onLoad: function(options) {
    this.getAccountInfo();
  },
  onShow: function() {

  },
  onPullDownRefresh: function() {

  },
  getAccountInfo: function() {
    var that = this;
    app.netReq(
      "user/account", {},
      function(res) {
        that.setData({
          funds: res.data.data
        })
      }
    )
  },
  extractCash: function() {
    
  },
  accountDetail: function() {
    wx.navigateTo({
      url: '../accdetail/index?objectId=account',
    })
  },
  extractCashDetail: function() {
    wx.navigateTo({
      url: '../accdetail/index?objectId=extract',
    })
  }
})