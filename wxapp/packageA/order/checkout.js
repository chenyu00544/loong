var app = getApp();
var orderId = 0;
Page({
  data: {
    order: [],
  },
  onLoad: function(option) {
    var that = this;
    orderId = option.order_id;
    that.getCheckOrder();
  },
  onShow: function() {


  },
  getCheckOrder: function() {
    var that = this
    app.vcvbRequst("order/get", {
      "order_id": orderId
    }).then((res) => {
      if (res.data.data.code == 0) {
        that.setData({
          order: res.data.data,
        })
      }
    });
  },
})