//获取应用实例
var app = getApp()
var token
var openid;
var size = 100;
var page = 1;
var id = 0;
Page({
  data: {
    current: "0",
    orders: [],
    hidden: true,
    bottomloading: false,
    viewBox: false
  },
  //事件处理函数

  /*订单导航内容切换*/
  bindHeaderTap: function(event) {
    this.setData({
      orders: [],
      current: event.target.dataset.index,
      scrollTop: 0,
      viewBox: false
    });
    page = 1;
    wx.showLoading({
      title: 'loading...',
    });
    this.orderStatus(this, event.target.dataset.index);
  },

  orderStatus: function(that, id) {
    var that = this;
    app.vcvbRequest(("order/index"), {
      page: page,
      order_type: id,
    }).then((res) => {
      wx.hideLoading();
      var lists = [];
      if (page != 1) {
        lists = that.data.orders
      }
      for (var i = 0; i < res.data.data.length; i++) {
        lists.push(res.data.data[i]);
      }
      that.setData({
        orders: lists,
        bottomloading: ''
      });
      page++;
      that.setData({
        hidden: true,
      });
    });
  },

  onLoad: function(e) {
    var that = this;
    that.data.current = e.id || 1;
    that.setData({
      current: that.data.current,
      scrollHeight: wx.getSystemInfoSync().windowHeight - 40,
    });
    page = 1;
  },

  onShow: function() {
    var that = this;
    page = 1;
    that.orderStatus(that, that.data.current);
  },

  //加载更多
  loadMore: function() {
    var that = this;
    that.setData({
      bottomloading: 'active'
    });;
    that.orderStatus(that, that.data.current);
  },

  //订单详情
  orderDetail: function(e) {
    var that = this
    //获取点击的id值
    var index = e.currentTarget.dataset.index;
    var orderId = that.data.orders[index].order_id;
    wx.navigateTo({
      url: "../../pages/order/detail?objectId=" + orderId
    });
  },

  //取消订单
  cancelOrder: function(e) {
    var that = this;
    var order_id = e.currentTarget.dataset.id || 0;
    wx.showModal({
      title: '提示',
      content: '确认取消订单？',
      success: function(res) {
        if (res.confirm) {
          app.vcvbRequest(("order/cancel"), {
            order_id: order_id,
            order_type: "cancel_order"
          }).then((res) => {
            for (var i in that.data.orders) {
              if (order_id == that.data.orders[i].order_id) {
                that.data.orders.splice(i, 1);
              }
              that.setData({
                orders: that.data.orders
              })
            }
          });
        }
      }
    })

  },

  //支付
  payOrder: function(e) {
    var order_id = e.currentTarget.dataset.id || 0;
    wx.navigateTo({
      url: "../../pages/downorder/checkout?ObjectId=" + order_id,
    });
  },

  //查看物流
  checkLogisticsOrder: function(e) {
    var order_id = e.currentTarget.dataset.id || 0;
    wx.navigateTo({
      url: "../logistics/index?ObjectId=" + order_id,
    });
  },

  //确认收货
  confirmOrder: function(e) {
    var that = this;
    var order_id = e.currentTarget.dataset.id || 0;
    wx.showModal({
      title: '提示',
      content: '确认收到商品？',
      success: function(res) {
        if (res.confirm) {
          app.vcvbRequest(("order/confirm/take"), {
            order_id: order_id,
            order_type: "confirm_take_order"
          }).then((res) => {
            if (res.data.code == 0) {
              for (var i in that.data.orders) {
                if (order_id == that.data.orders[i].order_id) {
                  that.data.orders[i].shipping_status = 2;
                }
                that.setData({
                  orders: that.data.orders
                })
              }
            }
          });
        }
      }
    })
  },

  //再次购买
  againBuy: function(e) {
    var that = this;
    var order_id = e.currentTarget.dataset.id || 0;
    app.vcvbRequest(("order/add"), {
      order_id: order_id,
      froms: "wxapp"
    }).then((res) => {
      if (res.data.code == 0) {
        wx.navigateTo({
          url: "../../pages/downorder/checkout?ObjectId=" + res.data.data.order.join(","),
        });
      }
    });
  },

  //评价
  evaluate: function(e) {
    var order_id = e.currentTarget.dataset.id || 0;
    wx.navigateTo({
      url: "../evaluate/index?ObjectId=" + order_id,
    });
  },

  //再次评价
  againEvaluate: function(e) {

  },

  navMore: function() {
    var that = this;
    that.setData({
      showViewNav: (!that.data.showViewNav)
    })
  },
  bargainNav: function(e) {
    wx.navigateTo({
      url: "../bargain/order/index"
    });
  },
  groupNav: function(e) {
    wx.navigateTo({
      url: "../group/order/index"
    });
  },
  //滚动触发事件
  scroll: function(event) {
    //   该方法绑定了页面滚动时的事件，我这里记录了当前的position.y的值,为了请求数据之后把页面定位到这里来。
    this.setData({
      scrollTop: event.detail.scrollTop,
      viewBox: true,
    });
  },

  //下拉刷新完后关闭
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
})