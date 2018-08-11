var app = getApp()
var token
Page({
  data: {
    checked: [],
    startX: 0, //开始坐标
    startY: 0,
    toView: 'blue',
    zujiIcon: '../../res/images/icon-zuji.png',
    zijiName: '推荐商品',
    selectedMenuId: 1,
    total: {
      count: 0,
      money: 0
    },
    // flowMoney: {
    //   checkoutUrl: '../flow/checkout',
    // }
  },

  getCartGoods: function(that) {
    var token = wx.getStorageSync('token')
    //调用应用实例的方法获取全局数据
    wx.request({
      url: app.apiUrl("cart"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        var attr;
        var temp = '';
        for (var i in res.data.cart_list) {
          attr = res.data.data.cart_list[i].goods_attr.split('\n')
          temp = ''
          for (var j in attr) {
            if (attr[j] == '') continue;
            temp += attr[j] + ','
          }
          res.data.cart_list[i].goods_attr = temp.substring(0, temp.length - 1)
        }
        if (res.data.data.cart_list == '' || res.data.data.cart_list == undefined) {
          that.setData({
            shopLists: '',
            indexGoods: res.data.data.best_goods,
          })
        } else {
          Object.keys(res.data.data.cart_list).forEach(function (k) {
            that.data.checked[k] = [];
          })
          that.setData({
            checked: that.data.checked,
            shopLists: res.data.data.cart_list,
            indexGoods: res.data.data.best_goods,
          })
        }
      }
    })
  },
  onLoad: function() {
    token = wx.getStorageSync('token')
    var that = this
    wx.showNavigationBarLoading();
    this.getCartGoods(that);

    //加载中
    this.loadingChange()
  },
  onShow: function() {
    var that = this
    this.getCartGoods(that);
    this.loadingChange();
  },
  loadingChange() {
    // this.setData({
    //   hidden: false
    // })
    setTimeout(() => {
      wx.hideNavigationBarLoading();
    }, 1000)
  },

  addCount: function(event) {
    var that = this
    let data = event.currentTarget.dataset
    let total = that.data.total
    let shopLists = that.data.shopLists

    var arr = [];
    var shopList, v;
    for (var i in shopLists) {
      arr.push(shopLists[i]);
    }
    for (v in arr) {
      shopList = arr[v].find(function(u) {
        return u.rec_id == data.id
      })
      if (shopList != undefined) {
        break;
      }
    }
    shopList.goods_number = parseInt(shopList.goods_number) + 1
    total.count += 1
    shopLists.total_price += parseInt(shopList.goods_price)

    //改变购物车商品数量
    wx.request({
      url: app.apiUrl('cart/update'),
      data: {
        id: data.id,
        amount: shopList.goods_number
      },
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      method: 'POST',
      success: function() {
        that.onLoad()
      }
    })

  },
  minusCount: function(event) {
    var that = this

    let data = event.currentTarget.dataset
    let total = this.data.total
    let shopLists = this.data.shopLists

    var arr = [];
    var shopList, v;
    for (var i in shopLists) {
      arr.push(shopLists[i]);
    }
    for (v in arr) {
      shopList = arr[v].find(function(u) {
        return u.rec_id == data.id
      })
      if (shopList != undefined) {
        break;
      }
    }
    shopLists.total_price -= parseInt(shopList.goods_price)
    if (parseInt(shopLists.total_price) < 0) {
      shopLists.total_price += parseInt(shopLists.goods_price)
      return
    }
    shopList.goods_number = parseInt(shopList.goods_number) - 1
    if (parseInt(shopList.goods_number) < 1) {
      shopList.goods_number = parseInt(shopList.goods_number) + 1
      shopLists.total_price += parseInt(shopList.goods_price)
      return
    }


    //改变购物车商品数量
    wx.request({
      url: app.apiUrl('cart/update'),
      data: {
        id: data.id,
        amount: shopList.goods_number
      },
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      method: 'POST',
      success: function() {
        that.onLoad()
      }
    })

  },
  del: function(event) {
    var that = this
    let data = event.currentTarget.dataset

    wx.showModal({
      title: '提示',
      content: '您确定要移除当前商品吗?',
      success: function(res) {
        if (res.confirm) {
          wx.request({
            url: app.apiUrl('cart/delete'),
            data: {
              id: data.id
            },
            method: 'POST',
            header: {
              'Content-Type': 'application/json',
              'X-ECTouch-Authorization': token
            },
            success: function(res) {
              that.onShow()
            }
          })
        }
      }
    })
  },
  flowcartBtn: function() {
    wx.switchTab({
      url: '../category/index'
    })
  },

  flowCheckoutBtn: function(e) {
    var that = this
    var rec_id = '';
    Object.keys(that.data.shopLists).forEach(function(k) {
      for (var i = 0; i < that.data.checked[k].length; i++) {
        if (that.data.checked[k][i] == true) {
          rec_id += that.data.shopLists[k][i].rec_id + ',';
        }
      }
    });
    rec_id = rec_id.substring(0, rec_id.length-1)
    if (that.data.total.count == 0) {
      wx.showToast({
        title: '请选择商品',
        icon: 'none',
      });
      return;
    }
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl('user/address/list'),
      method: 'POST',
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        if (res.data.data != '') {
          that.setData({
            total: {
              count: 0,
              money: 0
            }
          });
          wx.navigateTo({
            url: "../flow/checkout?rec_id=" + rec_id
          });
        } else {
          wx.navigateTo({
            url: "../address/index"
          });

        }

      }
    })
  },
  //下拉刷新完后关闭
  onPullDownRefresh: function() {
    var that = this
    this.getCartGoods(that);
    wx.stopPullDownRefresh()
    that.onLoad()
  },

  selectGoods: function(e) {
    if (!this.data.checked[e.currentTarget.dataset.index][e.currentTarget.dataset.sindex]) {
      this.data.checked[e.currentTarget.dataset.index][e.currentTarget.dataset.sindex] = true;
      this.data.total.money = parseFloat(parseInt(parseFloat(this.data.shopLists[e.currentTarget.dataset.index][e.currentTarget.dataset.sindex].goods_price) * 100 + parseFloat(this.data.total.money) * 100) / 100).toFixed(2)
      this.data.total.count += 1
    } else {
      this.data.checked[e.currentTarget.dataset.index][e.currentTarget.dataset.sindex] = false;
      this.data.total.money = parseFloat(parseInt(parseFloat(this.data.total.money) * 100 - parseFloat(this.data.shopLists[e.currentTarget.dataset.index][e.currentTarget.dataset.sindex].goods_price) * 100) / 100).toFixed(2)
      this.data.total.count -= 1
    }
    this.setData({
      total: this.data.total,
      checked: this.data.checked
    });
  }
})