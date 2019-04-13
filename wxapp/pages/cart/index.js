var app = getApp()
var areaInfo = []; //所有省市区县数据
var provinces = []; //省
var citys = []; //城市
var countys = []; //区县
var t = 0;
var index = [0, 0, 0];
var show = false;
var moveY = 200;
var changeValue, changeProvince, changeCity, changeCounty, changeProvince_id, changeCity_id, changeCounty_id;
let arrId = [];
var cartArr = []
let ruId, actId, arrValueLength, giftindex
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
    getProvinceData(that)
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
    let that = this;
    let inputValue = event.detail.value;
    let rec_id = event.currentTarget.dataset.id;
    that.updateCartNum(rec_id, inputValue);
  },
  //增加
  addCount: function(event) {
    let that = this
    let data = event.currentTarget.dataset
    for (var i in that.data.carts) {
      for (var j in that.data.carts[i].goods) {
        if (that.data.carts[i].goods[j].rec_id == data.id){
          that.updateCartNum(data.id, parseInt(that.data.carts[i].goods[j].goods_number) + 1);
        }
      }
    }
  },
  //减少
  minusCount: function(event) {
    let that = this
    let data = event.currentTarget.dataset
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
    let ids = [event.currentTarget.dataset.id]
    wx.showModal({
      title: '提示',
      content: '您确定要移除当前商品吗?',
      success: function(res) {
        if (res.confirm) {
          app.vcvbRequest(("cart/del"), {
            rec_ids: ids.join(","),
          }).then((res) => {
            that.onShow();
          })
        }
      }
    });
  },
  //合计
  total: function (that) {
    let goods_price_formated = 0;
    let discount_formated = 0;
    let goods_number = 0;

    for (let i in that.data.carts) {
      for (let j in that.data.carts[i].goods) {
        let item = that.data.carts[i].goods[j];
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
  //更新购物车上牌数量
  updateCartNum(cartId, num){
    let that = this;
    if (num > 0){
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

  flowcartBtn: function() {
    wx.switchTab({
      url: '../index/index'
    })
  },
  flowCheckoutBtn: function(e) {
    var that = this
    wx.setStorageSync('flowcheckout', {
      from: "checkout"
    })
    if (that.data.is_first_action == true) {
      that.setData({
        is_first_action: false,
      })
      app.vcvbRequest(("user/address/list"))
        .then((res) => {
          if (res.data.data != '') {
            wx.navigateTo({
              url: "../flow/checkout"
            });
          } else {
            wx.navigateTo({
              url: "../../packageA/address/create"
            });
          }
        })
    }
  },
  //滑动事件
  bindChange: function(e) {
    var val = e.detail.value //[0,0,0,]省市区的下标
    if (index[0] != val[0]) {
      val[1] = 0;
      val[2] = 0;
      getCityArr(val[0], this); //获取地级市数据
      getCountyInfo(val[0], val[1], this); //获取区县数据
    } else { //若省份column未做滑动，地级市做了滑动则定位区县第一位
      if (index[1] != val[1]) {
        val[2] = 0;
        getCountyInfo(val[0], val[1], this); //获取区县数据
      }
    }
    index = val;
    //存储滑动后的数据
    changeValue = [val[0], val[1], val[2]]
    changeProvince = provinces[val[0]].region_name
    changeCity = citys[val[1]].region_name
    changeCounty = countys[val[2]].region_name
    changeProvince_id = provinces[val[0]].region_id
    changeCity_id = citys[val[1]].region_id
    changeCounty_id = countys[val[2]].region_id
  },
  //确定
  checkFloatView(e) {
    var that = this
    moveY = 200;
    show = true;
    t = 0;
    animationEvents(this, moveY, show);
    this.setData({
      value: changeValue,
      province: (changeProvince == undefined ? '' : changeProvince),
      city: (changeCity == undefined ? '' : changeCity),
      county: (changeCounty == undefined ? '' : changeCounty),
      province_id: (changeProvince_id == undefined ? '0' : changeProvince_id),
      city_id: (changeCity_id == undefined ? '0' : changeCity_id),
      county_id: (changeCounty_id == undefined ? '0' : changeCounty_id),
      showViewMol: !that.data.showViewMol,
    })
  },
  //隐藏弹窗浮层
  hiddenFloatView(e) {
    var that = this
    moveY = 200;
    show = true;
    t = 0;
    animationEvents(this, moveY, show);
    that.setData({
      showViewMol: !that.data.showViewMol,
    })
  },
  //移动按钮点击事件
  translate: function(e) {
    var that = this
    if (t == 0) {
      moveY = 0;
      show = false;
      t = 1;
    } else {
      moveY = 200;
      show = true;
      t = 0;
    }
    // this.animation.translate(arr[0], arr[1]).step();
    animationEvents(this, moveY, show);
    //初始化数据
    changeValue = [0, 0, 0]
    changeProvince = '北京'
    changeCity = '北京'
    changeCounty = '东城区'
    changeProvince_id = 2
    changeCity_id = 52
    changeCounty_id = 500
    that.setData({
      showViewMol: !that.data.showViewMol,
    })
  },
  bargainPlayclose: function() {
    var that = this;
    that.setData({
      showViewPlay: !that.data.showViewPlay,
      showViewMol: !that.data.showViewMol,
      isScroll: true
    })
  },
  //编辑
  updataGoods: function() {
    let that = this
    that.setData({
      flowdel: !that.data.flowdel,
      flowNumBox: !that.data.flowNumBox
    })
  },
  //赠品弹框
  cartGifts: function(e) {
    let that = this;
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
    let that = this
    setTimeout(() => {
      var checkGift = []
      arrValueLength = e.detail.value.length
      let checkoutLength = that.data.giftLength
      var shopLists = that.data.shopLists;
      if (arrValueLength <= checkoutLength) {
        for (var i in shopLists) {
          for (var j in shopLists[i].new_list) {
            for (var h in shopLists[i].new_list[j].act_gift_list) {
              checkGift.push(shopLists[i].new_list[j].act_gift_list[h])
            }
          }
        }
        let curId = checkGift[giftindex].id
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
    let that = this
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

//动画事件
function animationEvents(that, moveY, show) {
  that.animation = wx.createAnimation({
    transformOrigin: "50% 50%",
    duration: 400,
    timingFunction: "ease",
    delay: 0
  })
  that.animation.translateY(moveY + 'vh').step()

  that.setData({
    animation: that.animation.export(),
    show: show
  })

}

//获取省份数据
function getProvinceData(that) {
  var s;
  var num = 0;
  for (var i = 0; i < areaInfo.length; i++) {
    s = areaInfo[i];
    if (s.city_id == 0 && s.county_id == 0) {
      provinces[num] = s;
      num++;
    }
  }
  that.setData({
    provinces: provinces
  })
  //初始化调一次
  getCityArr(0, that);
  getCountyInfo(0, 0, that);
}

// 获取地级市数据
function getCityArr(count, that) {
  var c;
  citys = [];
  var num = 0;
  for (var i = 0; i < areaInfo.length; i++) {
    c = areaInfo[i];
    if (c.county_id == "00" && c.province_id == provinces[count].province_id && c.city_id != "00") {
      citys[num] = c;
      num++;
    }
  }
  if (citys.length == 0) {
    citys[0] = {
      name: ''
    };
  }
  that.setData({
    citys: citys,
    value: [count, 0, 0]
  })
}

// 获取区县数据
function getCountyInfo(column0, column1, that) {
  var c;
  countys = [];
  var num = 0;
  for (var i = 0; i < areaInfo.length; i++) {
    c = areaInfo[i];
    if (c.county_id != "00" && c.province_id == provinces[column0].province_id && c.city_id == citys[column1].city_id) {
      countys[num] = c;
      num++;
    }
  }
  if (countys.length == 0) {
    countys[0] = {
      name: ''
    };
  }
  that.setData({
    countys: countys,
    value: [column0, column1, 0]
  })
}