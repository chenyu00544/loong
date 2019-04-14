var app = getApp()
var areaInfo = []; //所有省市区县数据
var provinces = []; //省
var citys = []; //城市
var countys = []; //区县
var rec_ids = []; //购物车ID
var show = false;
var moveY = 200;
var arrId = [];
var cartArr = []
var ruId, actId, arrValueLength, giftindex
Page({
  data: {
    startX: 0, //开始坐标
    startY: 0,
    is_first_action: true,
    total: {
      count: 0,
      money: 0,
      goods_price_formated: 0,
      discount_formated: 0,
      goods_number: 0,
    },
    province: provinces,
    city: citys,
    county: countys,
    province_id: provinces,
    city_id: citys,
    county_id: countys,
    flowdel: false,
    flowNumBox: true,
  },
  onLoad: function() {
    var that = this
    areaInfo = wx.getStorageSync('region')
  },
  onShow: function() {
    var that = this
    this.getCartGoods(that);
    that.setData({
      is_first_action: true,
    })
    //初始化动画
    that.animation = wx.createAnimation({
      transformOrigin: "50% 50%",
      duration: 0,
      timingFunction: "ease",
      delay: 0
    })
    that.animation.translateY(200 + 'vh').step();
    that.setData({
      animation: that.animation.export(),
      show: show
    })
  },
  //获取购物车商品
  getCartGoods: function(that) {
    app.vcvbRequest(("cart/index"))
      .then((res) => {
        var cartList = res.data.data.cart;
        if (res.data.data.cart != '' || res.data.data.cart != undefined) {
          that.setData({
            likeGoods: res.data.data.like_goods,
            carts: res.data.data.cart
          });
          that.total(that);
        }
      })
  },
  //直接输入
  inputNumber(event) {
    var that = this;
    var inputValue = event.detail.value;
    var rec_id = event.currentTarget.dataset.id;
    that.updateCartNum(rec_id, inputValue);
  },
  //增加
  addCount: function(event) {
    var that = this
    var data = event.currentTarget.dataset
    for (var i in that.data.carts) {
      for (var j in that.data.carts[i].goods) {
        if (that.data.carts[i].goods[j].rec_id == data.id) {
          that.updateCartNum(data.id, parseInt(that.data.carts[i].goods[j].goods_number) + 1);
        }
      }
    }
  },
  //减少
  minusCount: function(event) {
    var that = this
    var data = event.currentTarget.dataset
    for (var i in that.data.carts) {
      for (var j in that.data.carts[i].goods) {
        if (that.data.carts[i].goods[j].rec_id == data.id) {
          that.updateCartNum(data.id, parseInt(that.data.carts[i].goods[j].goods_number) - 1);
        }
      }
    }
  },
  //删除
  del: function(event) {
    var that = this
    var id = event.currentTarget.dataset.id;
    var ids = [id]
    wx.showModal({
      title: '提示',
      content: '您确定要移除当前商品吗?',
      success: function(res) {
        if (res.confirm) {
          app.vcvbRequest(("cart/del"), {
            rec_ids: ids.join(","),
          }).then((res) => {
            for (var i in that.data.carts) {
              for (var j in that.data.carts[i].goods) {
                if (id == that.data.carts[i].goods[j].rec_id) {
                  that.data.carts[i].goods.splice(j, 1);
                }
              }
              if (that.data.carts[i].goods.length == 0) {
                that.data.carts.splice(i, 1);
              }
              that.setData({
                carts: that.data.carts
              });
            }
            that.total(that);
          })
        }
      }
    });
  },
  //合计
  total: function(that) {
    var goods_price_formated = 0;
    var discount_formated = 0;
    var goods_number = 0;
    rec_ids = [];
    for (var i in that.data.carts) {
      for (var j in that.data.carts[i].goods) {
        var item = that.data.carts[i].goods[j];
        rec_ids.push(item.rec_id);
        goods_price_formated += parseFloat(item.shop_price) * parseInt(item.goods_number);
        if (item.is_promote == 1 && item.promote_start_date < item.current_time && item.promote_end_date > item.current_time) {
          discount_formated += (parseFloat(item.shop_price) - parseFloat(item.promote_price)) * parseInt(item.goods_number);
        }
        goods_number += parseInt(item.goods_number);
      }
    }

    that.data.total.goods_price_formated = goods_price_formated - discount_formated;
    that.data.total.discount_formated = discount_formated;
    that.data.total.goods_number = goods_number;
    that.setData({
      total: that.data.total,
    });
  },
  //更新购物车数量
  updateCartNum(cartId, num) {
    var that = this;
    if (num > 0) {
      app.vcvbRequest(("cart/set"), {
        rec_id: cartId,
        goods_number: num
      }).then((res) => {
        if (res.data.msg == '库存不足') {
          wx.showToast({
            title: '库存不足',
            image: '../../images/failure.png',
            duration: 2000
          })
        };
        that.onShow();
      });
    }
  },
  //购物车还没有商品
  flowcartBtn: function() {
    wx.switchTab({
      url: '../index/index'
    })
  },
  //结算
  flowCheckoutBtn: function(e) {
    var that = this
    if (that.data.is_first_action == true) {
      that.setData({
        is_first_action: false,
      })
      app.vcvbRequest(("user/addresses"))
        .then((res) => {
          if (res.data.data != '') {
            app.vcvbRequest(("order/add"), {
                froms: "wxapp",
                rec_ids: rec_ids.join(",")
              })
              .then((res) => {
                if (res.data.data.order != "") {
                  wx.navigateTo({
                    url: "../order/checkout?ObjectId=" + res.data.data.order.join(",")
                  });
                } else {
                  wx.showToast({
                    title: '下单失败',
                  })
                }
              });
          } else {
            wx.navigateTo({
              url: "../../packageA/address/create"
            });
          }
        })
    }
  },
  //编辑
  updataGoods: function() {
    var that = this
    that.setData({
      flowdel: !that.data.flowdel,
      flowNumBox: !that.data.flowNumBox
    });
  },
  //赠品弹框
  cartGifts: function(e) {
    var that = this;
    if (e.currentTarget.id == 'lookGift') {
      that.setData({
        rideoIcon: 'rideo-icon'
      })
    } else {
      that.setData({
        rideoIcon: ''
      })
    }
    that.setData({
      showViewPlay: !that.data.showViewPlay,
      showViewMol: !that.data.showViewMol
    })
  },
  //赠品选择
  bindCheckbox: function(e) {
    giftindex = e.currentTarget.dataset.index
  },
  checkboxChange: function(e) {
    var that = this
    setTimeout(() => {
      var checkGift = []
      arrValueLength = e.detail.value.length
      var checkoutLength = that.data.giftLength
      var shopLists = that.data.shopLists;
      if (arrValueLength <= checkoutLength) {
        for (var i in shopLists) {
          for (var j in shopLists[i].new_list) {
            for (var h in shopLists[i].new_list[j].act_gift_list) {
              checkGift.push(shopLists[i].new_list[j].act_gift_list[h])
            }
          }
        }
        var curId = checkGift[giftindex].id
        var idHas = cartArr.indexOf(curId); //返回5所在的下标3
        if (idHas != -1) {
          wx.showToast({
            title: '赠品已在购物车',
            image: '../../images/failure.png',
            duration: 2000
          })
        } else {
          arrId = e.detail.value
          checkGift[giftindex].is_checked = !checkGift[giftindex].is_checked;
        }
        ruId = checkGift[giftindex].ru_id
        actId = checkGift[giftindex].act_id
      } else {
        wx.showToast({
          title: '最多只能选' + checkoutLength + '件',
          image: '../../images/failure.png',
          duration: 2000
        })
      }
      that.setData({
        select_gift_value: arrValueLength,
        shopLists: shopLists,
      })
    }, 100)
  },

  //赠品确定领取
  goodsCheckout: function(e) {
    var that = this
    if (e.currentTarget.id == 'checkout') {
      app.vcvbRequest(("cart/addGiftCart"), {
          act_id: actId,
          ru_id: ruId,
          select_gift: arrId
        })
        .then((res) => {
          that.setData({
            showViewPlay: !that.data.showViewPlay,
            showViewMol: !that.data.showViewMol
          });
          that.getCartGoods(that);
        })
    } else {
      that.setData({
        showViewPlay: !that.data.showViewPlay,
        showViewMol: !that.data.showViewMol
      })
    }
  },
})