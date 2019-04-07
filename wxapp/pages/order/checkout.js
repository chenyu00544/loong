var app = getApp();
var orderId = 0;
Page({
  data: {
    order: [],
    address: "",
  },
  onLoad: function(option) {
    var that = this;
    orderId = option.ObjectId;
  },
  onShow: function() {
    this.getCheckOrder();
  },
  getCheckOrder: function() {
    var that = this
    app.vcvbRequest("order/get", {
      "order_id": orderId
    }).then((res) => {
      if (res.data.code == 0) {
        var address = [];
        if (res.data.data.address != undefined) {
          address = res.data.data.address[0];
        }

        for (var i in res.data.data.address) {
          if (res.data.data.address[i].def = 1) {
            address = res.data.data.address[i];
          }
        }
        that.setData({
          order: res.data.data,
          address: address,
        })
      }
    });
  },
})