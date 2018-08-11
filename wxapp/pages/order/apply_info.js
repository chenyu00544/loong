var app = getApp()
var order_id = 0;
Page({
  data: {
    returnOrder: [],
    back_shipping_name: '',
    back_invoice_no: '',
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

  importShippingName: function(e) {
    this.data.back_shipping_name = e.detail.value;
  },

  importShippingNo: function(e) {
    this.data.back_invoice_no = e.detail.value;
  },

  submitShipping: function() {
    if (this.data.back_shipping_name == '' || this.data.back_invoice_no == '') {
      wx.showToast({
        icon: 'none',
        title: '请填写快递公司和运单',
        duration: 2000,
      });
      return;
    }
    var that = this;
    app.netReq('user/submit/shipno', {
      back_shipping_name: that.data.back_shipping_name,
      back_invoice_no: that.data.back_invoice_no,
      return_id: that.data.returnOrder.rorder.ret_id,
    }, function(res) {
      if(res.data.data.code == 1){
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        });
        setTimeout(function () {
          wx.navigateBack({
            delta: 2
          });
        }, 2000);
      }else{
        wx.showToast({
          title: '失败',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },
})