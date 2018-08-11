var app = getApp()
var token
Page({
  data: {
    defaultSize: 'default',
    primarySize: 'default',
    warnSize: 'default',
    disabled: false,
    plain: false,
    loading: false,
    order_id:"",
    done: {
      name: '付款金额',
      price: '7520.00',
    },
    doneList: [
      {
        order_sn: '订单号',

      },
      {
        order_sn: '配送方式',
      }
    ],
    flowMoney: {
      checkoutUrl: '../user/index',
    },
    cash_type:'',
  },

  onLoad: function (e) {
    token = wx.getStorageSync('token')
    var that = this
    
    var cash = wx.getStorageSync('cash', cash);
    if (cash != undefined) {
      that.setData({
        cash_type: cash
      })
    }

    //加载中
    this.loadingChange()

    //获取订单信息
    var order_id = e.id
    wx.request({
      url: app.apiUrl('user/order/detail'),
      method: "post",
      data: {
        id: order_id
      },
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        console.log(res.data.data)
        that.setData({
          doneList: res.data.data,
          donePrice:res.data.data,
          order_id:res.data.data.order_id
        })
      }
    })

  },
  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 2000)
  },
  //下拉刷新完后关闭
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  },
  primary: function (e) {
    var that = this
    var formId_data = e.detail.formId
    var order_id = that.data.order_id
    var openid = wx.getStorageSync('openid')
    app.payOrder(order_id, openid, token, formId_data)
    app.netReq("user/trajectory", { utype: 10, type_name: "微信支付" }, function (res) { });
  },

})