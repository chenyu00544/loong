var app = getApp()
var order_id = 0;
Page({

  data: {
    returnOrder: [],
  },

  onLoad: function(options) {
    var that = this;
    order_id = options.objectId;
    app.netReq('user/applyreturn', {
      id: order_id
    }, function(res) {
      that.setData({
        returnOrder: res.data.data,
      })
    })
  },

  applyReturn:function(e){
    var rec_id = e.currentTarget.dataset.rec_id;
    wx.navigateTo({
      url: "apply_return?objectId=" + order_id + "&rec_id=" + rec_id,
    });
  },

  checkReturn:function(e){
    var rec_id = e.currentTarget.dataset.rec_id;
    wx.navigateTo({
      url: "apply_info?objectId=" + order_id + "&rec_id=" + rec_id,
    });
  }
})