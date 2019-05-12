var app = getApp();
var orderId = 0;
Page({
  data: {
    order: [],
    address: "",
    total: {},
    bonus_money: 0,
    coupons_money: 0,
    showViewCoupons: false,
    showViewBonus: false,
    bonus_id: 0,
    coupons_id: 0,
  },

  onLoad: function(option) {
    var that = this;
    orderId = option.objectId;
    that.setData({
      isIphoneX: app.globalData.isIphoneX
    })
  },

  onShow: function() {
    this.getCheckOrder();
  },

  //获取订单详细信息
  getCheckOrder: function() {
    var that = this
    app.vcvbRequest("order/get", {
      order_id: orderId
    }).then((res) => {
      if (res.data.code == 0) {
        that.setData({
          order: res.data.data
        })
        that.total();
      }
    });
  },

  //计算订单总金额
  total: function() {
    var price = 0;
    var order_amount = 0;
    var goods_amount = 0;
    var shipping_fee = 0;
    var tax = 0;
    var discount = 0;
    var total = {};

    var order = this.data.order;
    for (var i in order.order) {
      price += parseFloat(order.order[i].total.price);
      order_amount += parseFloat(order.order[i].order_amount);
      goods_amount += parseFloat(order.order[i].goods_amount);
      shipping_fee += parseFloat(order.order[i].shipping_fee);
      tax += parseFloat(order.order[i].tax);
      discount += parseFloat(order.order[i].discount);
    }
    total.goods_amount = goods_amount;
    total.order_amount = order_amount;
    total.shipping_fee = shipping_fee;
    total.tax = tax;
    total.discount = discount;
    total.total = goods_amount + shipping_fee + tax - discount - parseFloat(this.data.bonus_money) - parseFloat(this.data.coupons_money);
    total.bonus = this.data.bonus_money;
    total.coupons = this.data.coupons_money;
    total.price = price;
    this.setData({
      total: total
    })
  },

  //优惠券展开收起
  onChangeShowCoupons: function(e) {
    var that = this
    this.setData({
      showViewCoupons: !that.data.showViewCoupons
    })
  },

  //优惠券选择
  radioChangeCoupons: function(e) {
    var that = this
    var id = e.detail.value;
    var coupons = [];
    for (var i in that.data.order.coupons) {
      if (that.data.order.coupons[i].cou_id == id) {
        coupons = that.data.order.coupons[i]
      }
    }
    that.setData({
      coupons_money: coupons.cou_money,
      coupons_id: coupons.cu_id
    });
    this.total();
  },

  //红包展开收起
  onChangeShowBonus: function(e) {
    var that = this
    this.setData({
      showViewBonus: !that.data.showViewBonus
    })
  },

  //红包选择
  radioChangeBonus: function(e) {
    var that = this
    var id = e.detail.value;
    var bonus = [];
    for (var i in that.data.order.bonus) {
      if (that.data.order.bonus[i].bu_id == id) {
        bonus = that.data.order.bonus[i]
      }
    }
    that.setData({
      bonus_money: bonus.type_money,
      bonus_id: bonus.bu_id,
    });
    this.total();
  },

  // 返回
  back: function() {
    wx.navigateBack({})
  },

  // 确认收货
  shiped: function(e) {
    var that = this;
    var order_id = e.currentTarget.dataset.id || 0;
    wx.showModal({
      title: '提示',
      content: '确认收到商品？',
      success: function (res) {
        if (res.confirm) {
          app.vcvbRequest(("order/confirm/take"), {
            order_id: order_id,
            order_type: "confirm_take_order"
          }).then((res) => {
            if (res.data.code == 0) {
              that.back();
            }
          });
        }
      }
    });
  }
})