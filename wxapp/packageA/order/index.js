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
      var lists = that.data.orders
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
    var that = this
    that.data.current = e.id || 1;
    that.setData({
      current: that.data.current,
      scrollHeight: wx.getSystemInfoSync().windowHeight - 40,
    });
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
      url: "../order/detail?objectId=" + orderId
    });
  },

  //取消订单
  cancelOrder: function(e) {
    var that = this
    var error_msg = '';
    wx.showModal({
      title: '提示',
      content: '确认取消订单？',
      success: function(res) {
        if (res.confirm) {
          app.vcvbRequest(("order/cancel"), {
            page: page,
            order_type: id,
          }).then((res) => {
            
          });
          wx.request({
            url: app.apiUrl('order/cancel'),
            data: {
              id: e.currentTarget.dataset.id
            },
            header: {
              'Content-Type': 'application/json',
              'X-ECTouch-Authorization': token
            },
            method: "POST",
            success: function(res) {
              if (res.data.code > 0) {
                error_msg = '取消失败'

              } else if (res.data.code == 0) {
                error_msg = '取消成功'

                that.orderStatus(that, that.data.current);
              }
              wx.showToast({
                title: error_msg,
                icon: 'warn',
                duration: 500
              })
            }
          })
        }
      }
    })

  },

  //支付
  payOrder: function(e) {
    var formId_data = e.detail.formId
    var that = this
    var order_id = e.currentTarget.dataset.id
    var openid = wx.getStorageSync('openid')
    app.payOrder(order_id, openid, token, formId_data)
    that.orderStatus(that, that.data.current);

  },

  //查看物流
  afterSaleOrder:function(e){
    
  },

  //确认收货
  confirmOrder: function(e) {
    var that = this
    wx.showModal({
      title: '提示',
      content: '确认收到商品？',
      success: function(res) {
        if (res.confirm) {
          app.vcvbRequest(("order/confirm/take"), {
            page: page,
            order_type: id,
          }).then((res) => {

          });
          wx.request({
            url: app.apiUrl('user/order/set '),
            data: {
              id: e.currentTarget.dataset.id
            },
            header: {
              'Content-Type': 'application/json',
              'X-ECTouch-Authorization': token
            },
            method: "POST",
            success: function(res) {
              if (res.data.code == 0) {
                wx.showToast({
                  title: res.data.msg,
                  duration: 2000
                })
                that.orderStatus(that, that.data.current);
                wx.redirectTo({
                  url: '../user/index'
                })
              } else {
                wx.showToast({
                  title: res.data.msg,
                  image: '../../images/failure.png',
                  duration: 2000
                })
              }
            }
          })
        }
      }
    })
  },

  //再次购买
  againBuy: function(e) {
    var that = this
  },

  //评价
  evaluate: function(e) {

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