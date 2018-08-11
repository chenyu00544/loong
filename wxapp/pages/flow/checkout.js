var WxParse = require('../../wxParse/wxParse.js');
var app = getApp()
var token

var order = {
  consignee: 1,
  shipping_id: [],
  msg: ''
}
var cart_goods_list;
var order_total;
var shop_name;
var shopLists;
var res_error;
var invoiceType;
var inv_payee;
var tax_id;
var inv_content;
var uc_id;
var inv_payee_name
var shippingList = new Array();
var bs_id;
var flow_type;
var team_id;
var t_id;

var bn_id;

var shop_list;
var rec_id = '';

Page({
  data: {
    index: 0,
    addresss_link: '../address/index?from=flow',
    checkList: [],
    shipping_id: 0,
    shopLists: "",
    resShipingId: [],
    payfee: [],
    addresss: "",
    maskVisual: 'hidden',
    current: 1,
    hiddenOrder: true,
    hiddenAddress: true,
    hiddenUser: true,
    hiddenUnit: true,
    order_total: 0,
    cou_money: 0,
    bonus_money: 0,
    payfee_total: 0,
    has_free_coupons: false,
    cash_type: "",
    showViewBonus: false,
    //cyc

    cash_goodsId: '',
    buy_type: '',
  },

  onLoad: function(options) {
    var that = this
    bs_id = options.objectId;
    flow_type = options.flow_type;
    team_id = options.team_id;
    t_id = options.t_id;
    var goodsId = options.goodsId;
    if (goodsId != undefined) {
      that.data.cash_goodsId = goodsId;
    }

    //cyc
    if (options.rec_id != undefined) {
      rec_id = options.rec_id;
    } else {
      rec_id = '';
    }
    var cash = wx.getStorageSync('cash');
    if (cash != "") {
      that.setData({
        cash_type: cash
      })
    }
    that.setData({
      buy_type: options.buy_type,
    });
    //cyc
  },

  onShow: function(options) {
    var that = this
    token = wx.getStorageSync('token')
    wx.setStorageSync('pageOptions', {
      from: "flow"
    })
    wx.request({
      url: app.apiUrl("flow"),
      method: "post",
      data: {
        flow_type: (flow_type ? flow_type : 0),
        bs_id: (bs_id ? bs_id : 0),
        team_id: (team_id ? team_id : 0),
        t_id: (t_id ? t_id : '0'),
        goods_id: that.data.cash_goodsId,
        buy_type: that.data.buy_type,
        rec_id: rec_id
      },
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        setTimeout(() => {
          if (that.data.addresss == '') {
            wx.navigateTo({
              url: "../address/index"
            });
          }
        }, 1000)
        order.consignee = res.data.data.default_address.address_id
        cart_goods_list = res.data.data.cart_goods_list.list
        var cart_list = res.data.data.cart_goods_list
        for (var i in cart_goods_list) {
          shop_list = cart_goods_list[i].shop_list;
        }
        if (rec_id == '' || rec_id == undefined) {
          for (var i in cart_goods_list) {
            var g_list = cart_goods_list[i].shop_list;
            for (var j in g_list) {
              rec_id += g_list[j].rec_id + ',';
            }
          }
          rec_id = rec_id.substring(0, rec_id.length - 1);
        }
        var attr;
        var temp = '';
        for (var i in res.data.cart_goods_list) {
          attr = res.data.cart_goods_list.list[0][i].goods_attr.split('\n')
          for (var j in attr) {
            if (attr[j] == '') continue;
            temp += attr[j] + ','
          }
          res.data.cart_goods_list.list[0][i].goods_attr = temp.substring(0, temp.length - 1)
        }
        
        that.setData({
          addresss: res.data.data.default_address,
          shopLists: res.data.data.cart_goods_list,
          userInvoice: res.data.data.invoice_content,
          can_invoice: res.data.data.can_invoice,
          vat_invoice: res.data.data.vat_invoice,
          invoice_id: res.data.data.vat_invoice.id,
          coupons_list: res.data.data.coupons_list,
          bonus_list: res.data.data.bonus_list,
          faat: res.data.data.faat,
          // order_total: res.data.data.cart_goods_list.order_total,
          has_free_coupons: res.data.data.has_free_coupons,

          cont_data: res.data.data,
        })

        that.getShopName()
        for (var i in that.data.shippingName) {
          that.shippingChange(new Array(i, that.data.shippingName[i].id));
        }
      }
    })
    //加载中
    this.loadingChange()
  },
  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 2000)
  },

  getShopName: function(e) {
    let shippingName = []
    let shpppingId = []
    for (let item in this.data.shopLists.list) {
      let name = []
      let ship_id = []
      let o = {
        option: [],
        id: 0
      }
      let listItem = this.data.shopLists.list[item]
      if (listItem.shop_info != '') {
        listItem.shop_info.forEach(item => {
          name.push(item.shipping_name)
          ship_id.push(item.shipping_id)
        })
        o.option = name
        shippingName.push(o)
        shpppingId.push(ship_id)
        //快递方式初始化
        order.shipping_id = ship_id[0]
        this.data.resShipingId.push({
          ru_id: listItem.shop_info[0].ru_id,
          shipping_id: listItem.shop_info[0].shipping_id
        })
      }
    }
    //重新赋值
    this.setData({
      shippingName: shippingName,
      shpppingId: shpppingId
    })
  },
  //优惠券
  onChangeShowCoupons: function(e) {
    var that = this;
    that.setData({
      showViewCoupons: (!that.data.showViewCoupons),
    })
  },
  //优惠券
  radioChangeCoupons: function(e) {
    var that = this;
    uc_id = e.detail.value
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl('flow/changecou'),
      data: {
        uc_id: e.detail.value,
        flow_type: (flow_type ? flow_type : 0),
      },
      method: 'POST',
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        showViewCoupons
        var total = that.data.order_total - res.data.data.cou_money - that.data.bonus_money + that.data.payfee_total;
        that.setData({
          cou_money: res.data.data.cou_money,
          cou_id: res.data.data.cou_id,
          total: total > 0 ? total.toFixed(2) : 0,
        });
      }
    })
  },
  //配送方式
  shippingChange: function(e) {
    var that = this
    if (typeof e == 'object' && e.length == 2) {
      var index = e[0],
        rangeIndex = e[1]
    } else {
      var index = e.currentTarget.dataset.index,
        rangeIndex = e.detail.value
    }
    that.data.shippingName[index].id = rangeIndex
    order.shipping_id = this.data.shpppingId[index][rangeIndex]
    var ru_id = this.data.shopLists.list[index].shop_list[0].ru_id,
      temp = 0
    this.data.resShipingId[index].shipping_id = this.data.shopLists.list[index].shop_info[rangeIndex].shipping_id
    this.setData({
      shippingName: this.data.shippingName,
      resShipingId: this.data.resShipingId
    })

    wx.request({
      url: app.apiUrl('flow/shipping'),
      data: {
        address: order.consignee,
        id: this.data.resShipingId[index].shipping_id,
        ru_id: this.data.resShipingId[index].ru_id,
        flow_type: (flow_type ? flow_type : '0'),
        uc_id: (that.data.cou_id ? that.data.cou_id : 0)

      },
      method: 'POST',
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {

        shippingList[that.data.resShipingId[index].ru_id] = {
          shipping_id: that.data.resShipingId[index].shipping_id,
          shipping_fee: parseFloat(res.data.data.fee)
        }

        if (that.data.payfee.hasOwnProperty(index)) {
          res.data.data.fee ? that.data.payfee.splice(index, 1, res.data.data.fee) :
            that.data.payfee.splice(index, 1, res.data.data.massage)
        } else {
          res.data.data.fee ? that.data.payfee.splice(index, 1, res.data.data.fee) :
            that.data.payfee.splice(index, 1, res.data.data.massage)
        }
        if (res.data.data.error == 1) {
          wx.showToast({
            title: res.data.data.massage,
            image: '../../images/failure.png',
            duration: 2000
          })
        }
        that.data.payfee.forEach(item => {
          if (!isNaN(item))
            temp += parseFloat(item)
        })

        that.setData({
          payfee: that.data.payfee,
          payfee_total: temp,
          total: (parseFloat(that.data.order_total) + temp - parseFloat(that.data.cou_money ? that.data.cou_money : 0)).toFixed(2),
          payfee_error: res.data.data.error,
        })
      }
    })
  },



  //提交订单
  submitOrder: function(e) {

    wx.showLoading({
      title: '提交订单中',
      mask: true
    });

    var that = this
    if (that.data.payfee_error == 1) {
      wx.showToast({
        title: "地区不支持配送，无法提交",
        image: '../../images/failure.png',
        duration: 2000
      })
      this.setData({
        hidden: true
      })
      return;
    }
    if (order.consignee == '' || order.consignee == undefined) {
      app.shwomessage('没有收货地址');
      this.setData({
        hidden: true
      })
      return;
    }
    //初始化
    var postdata = {
      inv_payee: '',
      inv_content: ''
    }

    if (invoiceType == 0) {
      if (inv_payee == "单位") {
        var postdata = {
          invoice_type: invoiceType, //发票类型
          inv_payee: inv_payee_name, //个人还是公司名称 ，增值发票时此值为空
          tax_id: tax_id,
          inv_content: inv_content,
        }
      }
      if (inv_payee == "个人") {
        var postdata = {
          invoice_type: invoiceType, //发票类型
          inv_payee: inv_payee, //个人还是公司名称 ，增值发票时此值为空
          inv_content: inv_content, //发票明细
        }
      }
    }
    if (invoiceType == 1) {
      var postdata = {
        invoice_type: invoiceType, //发票类型
        vat_id: that.data.invoice_id,
        inv_payee: "", //个人还是公司名称 ，增值发票时此值为空
        inv_content: "", //发票明细
        tax_id: ""
      }
    }
    if (rec_id == "" || rec_id == undefined) {
      wx.showToast({
        title: '请选择商品',
        icon: 'none',
        duration: 2000,
      })
      this.setData({
        hidden: true
      })
      return;
    }
    that.loadingChange();
    app.netReq("user/trajectory", {
      utype: 5,
      type_name: "提交订单"
    }, function(res) {});
    wx.request({
      url: app.apiUrl('flow/down'),
      method: "post",
      data: {
        consignee: order.consignee,
        shipping: that.data.resShipingId,
        postdata,
        uc_id: (uc_id ? uc_id : "0"),
        bn_id: (bn_id ? bn_id : "0"),
        bs_id: that.data.cont_data.bs_id,
        flow_type: that.data.cont_data.flow_type,
        team_id: that.data.cont_data.team_id,
        t_id: that.data.cont_data.t_id,
        'product_ids': rec_id,
        gzid: wx.getStorageSync('gzid'),
        gdt_vid: wx.getStorageSync('gdt_vid'),
        aid: wx.getStorageSync('aid'),
        mktag: wx.getStorageSync('mktag'),
        action_type: "COMPLETE_ORDER",
      },

      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        wx.hideLoading();
        var oid = res.data.data
        if (res.data.code > 0) {
          wx.showToast({
            title: res.data.data,
            image: '../../images/failure.png',
            duration: 2000
          })
          return
        }
        if (oid != '') {
          wx.request({
            url: app.apiUrl('user/order/detail'),
            method: "post",
            data: {
              id: oid
            },
            header: {
              'Content-Type': 'application/json',
              'X-ECTouch-Authorization': token
            },
            success: function(res) {
              that.setData({
                doneList: res.data.data,
                donePrice: res.data.data,
                order_id: res.data.data.order_id
              });
              var formId_data = e.detail.formId;
              var order_id = that.data.order_id;
              var openid = wx.getStorageSync('openid');
              var token = wx.getStorageSync('token');
              app.payOrder(order_id, openid, token, formId_data);
            }
          })
        }
      }
    })
  },

  cascadePopup: function() {
    var animation = wx.createAnimation({
      duration: 100,
      timingFunction: 'ease-in-out'
    });
    this.animation = animation;
    animation.translateX(-1000).step();
    this.setData({
      animationData: this.animation.export(),
      maskVisual: 'show'
    })
  },

  //点击遮区域关闭弹窗
  cascadeDismiss: function() {
    this.animation.translateX(1000).step();
    this.setData({
      animationData: this.animation.export(),
      maskVisual: 'hidden'
    });
  },

  bindHeaderTap: function(event) {
    this.setData({
      current: event.target.dataset.index,
    });
    page = 1;
    sort_key = event.target.dataset.index

  },

  /**获取内容 */

  //获取发票类型-普通
  toOrder: function(e) {
    invoiceType = e.currentTarget.id
    this.setData({
      hiddenOrder: false,
      hiddenAddress: true,
      invoiceType: e.currentTarget.id
    })
  },

  //获取发票类型-增值
  toAddress: function(e) {
    invoiceType = e.currentTarget.id
    this.setData({
      hiddenOrder: true,
      hiddenAddress: false,
      invoiceType: e.currentTarget.id
    })
  },

  userList: function(e) {
    inv_payee = e.currentTarget.id
    this.setData({
      hiddenUser: false,
      hiddenUnit: true,
      inv_payee: e.currentTarget.id
    })
  },

  unitList: function(e) {
    inv_payee = e.currentTarget.id
    this.setData({
      hiddenUser: true,
      hiddenUnit: false,
      inv_payee: e.currentTarget.id

    })
  },

  unitNameInput: function(e) {
    inv_payee_name = e.detail.value
    this.setData({
      unitName: e.detail.value
    })
  },

  unitNumInput: function(e) {
    tax_id = e.detail.value
    this.setData({
      headingCode: e.detail.value
    })
  },

  radioChange: function(e) {

    inv_content = e.detail.value

  },

  toAddressTs: function(e) {
    wx.showModal({
      title: '提示',
      content: '你还没有增值发票，去添加吗？',
      success: function(res) {
        if (res.confirm) {
          wx.navigateTo({
            url: '../invoice/create'
          })
        } else if (res.cancel) {
          // console.log('用户点击取消')
        }
      }
    })
  },

  //快捷导航
  commonNav: function() {
    var that = this;
    var nav_select
    that.setData({
      nav_select: !that.data.nav_select
    });
  },
  nav: function(e) {
    var that = this;
    var cont = e.currentTarget.dataset.index;
    if (cont == "home") {
      wx.switchTab({
        url: '../index/index',
      });
    } else if (cont == "fenlei") {
      wx.switchTab({
        url: '../category/index',
      });
    } else if (cont == "cart") {
      wx.switchTab({
        url: '../flow/index',
      });
    } else if (cont == "profile") {
      wx.switchTab({
        url: '../user/index',
      });
    }
  },
  //下拉刷新完后关闭
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
  // getmsg: function (e) {
  //   order.msg = e.detail.value
  // },



  //展开关闭红包券
  onChangeShowBonus: function(e) {
    var that = this;
    that.setData({
      showViewBonus: (!that.data.showViewBonus),
    })
  },
  //获取红包信息
  radioChangeBonus: function(e) {
    var that = this;
    bn_id = e.detail.value
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl('bonus/flowget'),
      data: {
        bn_id: e.detail.value,
        flow_type: (flow_type ? flow_type : 0),
      },
      method: 'POST',
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        that.data.shopLists.order_total = (that.data.shopLists.order_total * 100 - res.data.data.type_money * 100 - that.data.cou_money * 100 + that.data.payfee_total * 100)/100;
        that.setData({
          bonus_money: res.data.data.type_money,
          bonus_id: res.data.data.bonus_id,
          shopLists: that.data.shopLists,
        });
      }
    })
  },

  //cyc选中付款方式
  choicePayType: function() {
    var that = this;
    wx.showModal({
      title: '付款方式',
      content: '选择货到付款或在线支付',
      confirmText: '微信支付',
      cancelText: '货到付款',
      success: function(res) {
        if (res.confirm) {
          that.submitOrder()
        } else if (res.cancel) {
          that.goodsOnDelivery()
        }
      }
    })
  },

  //货到付款
  goodsOnDelivery: function() {
    var that = this;
    var token = wx.getStorageSync('token');
    wx.showToast({
      title: '下单中',
      icon: 'loading',
      duration: 2000,
    })

    //选中的商品goodsID
    var product_ids = rec_id;

    if (product_ids == "") {
      wx.showToast({
        title: '请选择商品',
        icon: 'none',
        duration: 2000,
      })
      return;
    }

    wx.request({
      url: app.apiUrl('flow/cashon'),
      data: {
        "consignee": order.consignee,
        "shipping": this.data.resShipingId,
        "uc_id": (uc_id ? uc_id : "0"),
        "bn_id": (bn_id ? bn_id : "0"),
        "bs_id": that.data.cont_data.bs_id ? that.data.cont_data.bs_id : 0,
        "flow_type": that.data.cont_data.flow_type,
        "team_id": that.data.cont_data.team_id,
        "t_id": that.data.cont_data.t_id,
        "gzid": wx.getStorageSync('gzid'),
        "gdt_vid": wx.getStorageSync('gdt_vid'),
        "aid": wx.getStorageSync('aid'),
        "mktag": wx.getStorageSync('mktag'),
        "action_type": "COMPLETE_ORDER",
        "product_ids": product_ids,
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {

        if (res.data.code === 2) {
          that.data.IsAddress = true
          wx.navigateTo({
            url: "../address/index"
          });
        } else if (res.data.code === 0) {
          if (res.data.data.error === 1) {
            wx.showToast({
              title: res.data.data.msg,
              image: '../../images/failure.png',
              duration: 2000,
            })
          } else {
            wx.navigateTo({
              url: "../ordertip/index"
            });
          }
        }
      }
    })
  },

  //cyc
})