var app = getApp();
var order_id = 0;
Page({
  data: {
    order:[],
    logistics:[]
  },
  onLoad: function (options) {
    var that = this;
    order_id = options.ObjectId;
    that.getOrder();
  },

  onShow: function () {
    
  },
  
  getOrder:function(){
    var that = this;
    wx.showLoading({
      title: 'Loading...',
    })
    app.vcvbRequest(("order/get"), {
      order_id: order_id,
    }).then((res) => {
      wx.hideLoading();
      if (res.data.code == 0){
        that.setData({
          order: res.data.data.order[0]
        });
        that.getLogistics(res.data.data.order[0].shipping_name, res.data.data.order[0].invoice_no);
      }
    });
  },

  getLogistics: function (ship_name, ship_no){
    var that = this;
    if (ship_no != ""){
      app.vcvbRequest(("order/logistics/get"), {
        ship_name: ship_name,
        ship_no: ship_no,
      }).then((res) => {
        if (res.data.code == 0) {
          that.setData({
            logistics: res.data.data
          });
        }
      });
    }
  }
})