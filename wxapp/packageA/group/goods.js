var WxParse = require('../../wxParse/wxParse.js');
var app = getApp()
var order = {
  id: "",
  num: 1,
  pro: [],
  prostr: []
};
var goodsbtn = ''
var tempOrderPro = [];
var tempOrderProStr = [];
var coupons_index = '';
var teamId
Page({
  data: {
    hidden: false,
    hiddenOrder: false,
    hiddenAddress: true,
    is_collect: 0,
    isScroll: true,
    currentIndex: 1,
    num: 1,
    groupNum: 1,
    goodsComment: [],
    properties: [],
    indicatorDots: true,
    autoplay: true,
    interval: 4000,
    duration: 1000,
    showView: true,
    scrollTop: 0,
    floorstatus: false,
    parameteCont: [],
  },
  onLoad: function(options) {
    var that = this
    var goodsId = options.objectId;
    order.id = goodsId
    team_id = options.team_id;
    var team_id = (team_id ? team_id : '0')
    //调用应用实例的方法获取全局数据
    app.vcvbRequest("team/goods", {
      goods_id: goodsId,
      team_id: team_id,
    }).then((res) => {
      WxParse.wxParse('goods_desc', 'html', res.data.data.goods_info.goods.goods_desc, that, 5);
      that.setData({
        goodsCont: res.data.data,
      });

      //商品有属性则默认选中第一个
      that.setData({
        properties: res.data.data.goods_info.multi_attr
      })
      for (var i in res.data.data.goods_info.multi_attr) {
        that.getProper(res.data.data.goods_info.multi_attr[i][0].goods_attr_id)
      }
      that.getGoodsTotal();
      that.timeEnd();
    });
  },

  onShow: function() {
    order.num = 1;
    order.groupNum = 1;
    order.pro = [];
  },

  //更多玩法
  groupPlay: function() {
    var that = this;
    that.setData({
      showViewPlay: !that.data.showViewPlay,
      showViewMol: !that.data.showViewMol,
      isScroll: false
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

  /*单独购买 */
  onChangeShowState: function() {
    var that = this;
    that.setData({
      showViewProperty: !that.data.showViewProperty,
      showViewMol: !that.data.showViewMol,
      id: 'buy'
    })
  },

  /*拼团购买 */
  groupProperty: function() {
    var that = this;
    that.setData({
      showViewGroupProperty: !that.data.showViewGroupProperty,
      showViewMol: !that.data.showViewMol,
      id: 'groupcheckout'
    })
  },

  //去参团
  goodsWait: function(e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    teamId = that.data.goodsCont.team_log[index].team_id
    that.setData({
      showViewGroupProperty: !that.data.showViewGroupProperty,
      showViewMol: !that.data.showViewMol,
      id: 'addcheckout'
    })
  },

  /*提交*/
  goodsCheckout: function(e) {
    var that = this;
    //获取id
    var goodsId = that.data.goodsCont.goods_info.goods.goods_id;
    var goodsbtn = e.currentTarget.id || 'buy';
    if (goodsbtn == 'buy') {
      app.vcvbRequest("order/add", {
        "goods_id": order.id,
        "num": order.num,
        "attr_id": order.pro,
        "froms": "wxapp",
      }).then((res) => {
        if (res.data.code == 0) {
          wx.navigateTo({
            url: "../../pages/downorder/checkout?ObjectId=" + res.data.data.order[0],
          });
        }
      });
      that.setData({
        showViewProperty: !that.data.showViewProperty,
        showViewMol: !that.data.showViewMol
      })
    } else {
      if (goodsbtn == 'addcheckout') {
        //参与
        var team_newId = teamId;
      } else {
        //开团
        var team_newId = that.data.goodsCont.goods_info.team_id;
      }
      app.vcvbRequest("team/buy", {
        goods_id: that.data.goodsCont.goods_info.goods.goods_id,
        t_id: that.data.goodsCont.goods_info.id,
        team_id: team_newId,
        num: order.groupNum,
        attr_id: order.pro,
        froms: "wxapp"
      }).then((res) => {
        if (res.data.code == 0) {
          if (res.data.msg == "库存不足") {
            wx.showToast({
              title: "库存不足!",
              duration: 2000
            })
          } else if (res.data.code == 40002) {
            wx.navigateTo({
              url: "../address/create",
            });
          } else if (res.data.code == 50001) {
            wx.navigateTo({
              url: "../real/card",
            });
          } else if (res.data.code == 30003) {
            wx.showToast({
              title: res.data.msg,
              duration: 2000
            })
          } else if (res.data.code == 30004) {
            wx.showToast({
              title: res.data.msg,
              duration: 2000
            })
          } else if (res.data.code == 30005) {
            wx.showToast({
              title: res.data.msg,
              duration: 2000
            })
          } else if (res.data.code == 30006) {
            wx.showToast({
              title: res.data.msg,
              duration: 2000
            })
          }

          wx.navigateTo({
            url: "../../pages/downorder/checkout?ObjectId=" + res.data.data.order[0],
          });
        }
      });
      // wx.request({
      //   url: app.apiUrl("team/teamBuy"),
      //   data: {
      //     goods_id: that.data.goodsCont.goods_info.goods_id,
      //     t_id: that.data.goodsCont.goods_info.id,
      //     team_id: team_newId,
      //     num: order.groupNum,
      //     attr_id: JSON.stringify(tempOrderPro)
      //   },
      //   method: "post",
      //   header: {
      //     'Content-Type': 'application/json',
      //     'X-ECTouch-Authorization': token
      //   },
      //   success: function(res) {
      //     var t_id = res.data.data.t_id
      //     var flow_type = res.data.data.flow_type
      //     var team_id = res.data.data.team_id
      //     var bs_id = 0
      //     wx.request({
      //       url: app.apiUrl('user/address/list'),
      //       method: 'POST',
      //       header: {
      //         'Content-Type': 'application/json',
      //         'X-ECTouch-Authorization': token
      //       },
      //       success: function(res) {
      //         if (res.data.data != '') {
      //           wx.navigateTo({
      //             url: "../flow/checkout?objectId=" + bs_id + "&flow_type=" + flow_type + "&team_id=" + team_id + "&t_id=" + t_id,
      //           });
      //         } else {
      //           wx.removeStorageSync('pageOptions')
      //           wx.navigateTo({
      //             url: "../address/index"
      //           });
      //         }
      //       }
      //     })
      //   }
      // })
      that.setData({
        showViewGroupProperty: !that.data.showViewGroupProperty,
        showViewMol: !that.data.showViewMol
      })
    }
  },

  /*拼团增加商品数量*/
  groupUp: function() {
    var groupNum = this.data.groupNum;
    var max = this.data.goodsCont.goods_info.astrict_num
    groupNum++;
    if (groupNum >= max) {
      groupNum = max
    }
    this.setData({
      groupNum: groupNum
    })
    order.groupNum = groupNum;
    if (groupNum == max) {
      wx.showToast({
        title: "已到限购量",
        image: '../../images/failure.png',
        duration: 2000
      })
    }
  },

  /*手动输入商品*/
  groupImport: function(e) {
    var groupNum = Math.floor(e.detail.value);
    var max = this.data.goodsCont.goods_info.astrict_num
    if (groupNum <= 1) {
      groupNum = 1
    }
    if (groupNum >= max) {
      groupNum = max
    }
    this.setData({
      groupNum: groupNum
    })
    order.groupNum = groupNum;
  },

  /*拼团减少商品数量*/
  groupDown: function() {
    var groupNum = this.data.groupNum;
    groupNum--;
    if (groupNum <= 1) {
      groupNum = 1
    }
    this.setData({
      groupNum: groupNum
    })
    order.groupNum = groupNum;
  },

  /*增加商品数量*/
  up: function() {
    var num = this.data.num;
    num++;
    if (num >= 99) {
      num = 99
    }
    this.setData({
      num: num
    })
    order.num = num;
    this.getGoodsTotal();
  },

  /*减少商品数量*/
  down: function() {
    var num = this.data.num;
    num--;
    if (num <= 1) {
      num = 1
    }
    this.setData({
      num: num
    })
    order.num = num;
    this.getGoodsTotal();
  },

  /*手动输入商品*/
  import: function(e) {
    var num = Math.floor(e.detail.value);
    if (num <= 1) {
      num = 1
    }
    if (num >= 999) {
      num = 999
    }
    this.setData({
      num: num
    })
    order.num = num;
    this.getGoodsTotal();

  },

  /*单选*/
  modelTap: function(e) {
    this.getProper(e.currentTarget.id)
    this.getGoodsTotal();
  },

  /*拼团属性 */
  groupModelTap: function(e) {
    this.getProper(e.currentTarget.id)
    this.teamProperty();
  },

  /*属性选择计算*/
  getProper: function(id) {
    var categoryList = this.data.properties;
    for (var i in categoryList) {
      for (var j in categoryList[i]) {
        if (categoryList[i][j].goods_attr_id == id) {
          for (var k in categoryList[i]) {
            categoryList[i][k].checked = false;
          }
          categoryList[i][j].checked = true;
          order.pro[i] = id;
          order.prostr[i] = categoryList[i][j].attr_value;
          if (i == 0) {
            this.setData({
              attr_img: categoryList[i][j].attr_img_flie
            })
          }
        }
      }
    }
    this.setData({
      properties: categoryList,
    });
  },

  //提交属性  更新价格
  getGoodsTotal: function() {
    var that = this;
    app.vcvbRequest(("goods/property"), {
        goods_id: order.id,
        attr_id: order.pro,
        num: order.num,
        model_price: that.data.goodsCont.goods_info.goods.model_price,
      })
      .then((res) => {
        that.setData({
          goods_price: res.data.data.product_price_format,
          stock: res.data.data.product_number,
          goods_market_price: res.data.data.product_market_price_format,
        });
      });
  },

  teamProperty: function() {
    //提交属性  更新价格
    var that = this;
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("team/teamProperty"),
      data: {
        goods_id: order.id,
        attr_id: tempOrderPro,
        num: order.num,
        warehouse_id: "1",
        area_id: "1"
      },
      method: 'POST',
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        that.setData({
          group_property: res.data.data
        });

      }
    })
  },

  /*收藏*/
  addCollect: function() {
    var that = this;
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("user/collect/add"),
      data: {
        "id": order.id,
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        that.setData({
          collect_list: res.data.data
        })
      }
    })
  },

  //商品相册
  setCurrent: function(e) {
    this.setData({
      currentIndex: e.detail.current + 1
    })
  },

  imgPreview: function() { //图片预览
    const imgs = this.data.goodsCont.goods_img;
    wx.previewImage({
      current: imgs[this.data.currentIndex - 1], // 当前显示图片的http链接
      urls: imgs // 需要预览的图片http链接列表
    })
  },

  /**内容切换 */
  toOrder: function(res) {
    this.setData({
      hiddenOrder: false,
      hiddenAddress: true
    })
  },

  toAddress: function(res) {
    this.setData({
      hiddenOrder: true,
      hiddenAddress: false
    })
  },

  //店铺
  storeDetail: function(e) {
    var id = this.data.goodsCont.detail.user_id
    wx.redirectTo({
      url: "../store/index?objectId=" + id
    });
  },

  //优惠券
  onChangeShowCoupons: function() {
    var that = this;
    that.setData({
      showViewCoupons: (!that.data.showViewCoupons)
    })
  },

  //领取优惠券
  printCoupont: function(e) {
    var that = this;
    var token = wx.getStorageSync('token')
    coupons_index = e.currentTarget.dataset.index;
    var couId = that.data.goodsCont.coupont[coupons_index].cou_id;
    wx.request({
      url: app.apiUrl("goods/coupons"),
      data: {
        "cou_id": couId,
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {

        if (res.status_code != 500) {
          if (res.data.data.error == 2) {
            wx.showToast({
              title: "领取成功!",
              duration: 2000
            })
          } else {
            wx.showToast({
              image: '../../images/failure.png',
              title: "已领取!",
              duration: 2000
            })
          }
        } else {
          wx.showToast({
            title: "已领取!",
            duration: 2000
          })
        }
        that.setData({
          couponsData: res.data.data.error
        })
      }
    })
  },

  //主页
  groupHome: function() {
    wx.navigateTo({
      url: '../group/index',
    });
  },

  //快捷导航
  commonNav: function() {
    var that = this;
    var nav_select
    that.setData({
      nav_select: !that.data.nav_select
    });
  },

  //悬浮导航点击跳转
  nav: function(e) {
    var that = this;
    var cont = e.currentTarget.dataset.index;
    if (cont == "home") {
      wx.switchTab({
        url: '../../pages/index/index',
      });
    } else if (cont == "fenlei") {
      wx.switchTab({
        url: '../../pages/category/index',
      });
    } else if (cont == "cart") {
      wx.switchTab({
        url: '../../pages/cart/index',
      });
    } else if (cont == "profile") {
      wx.switchTab({
        url: '../../pages/user/index',
      });
    }
  },

  //返回顶部
  goTop: function(e) {
    this.setData({
      scrollTop: 0
    })
  },

  scroll: function(e) {
    if (e.detail.scrollTop > 300) {
      this.setData({
        floorstatus: true
      });
    } else {
      this.setData({
        floorstatus: false
      });
    }
  },

  bindSharing: function() {
    var that = this
    var goodsId = that.data.goodsCont.goods_info.goods_id
    wx.navigateTo({
      url: "../goods/speak?objectId=" + goodsId
    });
  },

  timeEnd: function() {
    let that = this;
    //时间数据长度 
    let len = that.data.goodsCont.team_log.length;

    function nowTime() {
      //时间函数
      for (var i = 0; i < len; i++) {
        //获取数据中的时间戳  
        var intDiff = that.data.goodsCont.team_log[i].end_time - Date.parse(new Date()) / 1000;
        var day = 0,
          hour = 0,
          minute = 0,
          second = 0;
        if (intDiff > 0) {
          //转换时间  
          day = Math.floor(intDiff / (60 * 60 * 24));
          hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
          minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
          second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
          if (hour <= 9) hour = '0' + hour;
          if (minute <= 9) minute = '0' + minute;
          if (second <= 9) second = '0' + second;
          var str = day + ':' + hour + ':' + minute + ':' + second
        } else {
          var str = "已结束！";
          clearInterval(timer);
        }
        //在数据中添加difftime参数名，把时间放进去  
        that.data.goodsCont.team_log[i].difftime = str;
      }
      that.setData({
        goodsCont: that.data.goodsCont
      })
    }
    // nowTime();
    var timer = setInterval(nowTime, 1000);
  },

  //分享
  onShareAppMessage: function() {
    return {
      title: '商品详情',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/group/goods?objectId=' + order.id
    }
  },
})