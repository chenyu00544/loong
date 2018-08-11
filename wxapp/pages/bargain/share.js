var WxParse = require('../../wxParse/wxParse.js');
var app = getApp()
var order = {
  id: "",
  num: 1,
  pro: [],
  prostr: []
}
var goodsbtn = ''
var tempOrderPro = [];
var tempOrderProStr = [];
var coupons_index = '';
var goodsId
var bs_id
Page({
  data: {
    hidden: false,
    hiddenOrder: false,
    hiddenAddress: true,
    hiddenFriends: false,
    hiddenRank: true,
    is_collect: 0,
    currentIndex: 1,
    num: 1,
    goodsComment: [],
    properties: [],
    showView: true,
    scrollTop: 0,
    floorstatus: false,
    parameteCont: [],
    countDownDay: 0,
    countDownHour: 0,
    countDownMinute: 0,
    countDownSecond: 0,
  },
  onLoad: function (options) {
    var that = this
    // 获取用户数据
    app.getUserInfo(function (userInfo) {
      that.setData({
        userInfo: userInfo
      })
    })
    // 生命周期函数--监听页面加载
    var token = wx.getStorageSync('token')
    // 获取用户数据
    goodsId = options.objectId;
    order.id = goodsId
    bs_id = (options.bs_id ? options.bs_id : '0')

    //调用应用实例的方法获取全局数据
    var that = this
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("bargain/goodsDetail"),
      data: {
        "id": order.id,
        bs_id: bs_id
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      
      success: function (res) {
        WxParse.wxParse('goods_desc', 'html', res.data.data.goods_info.goods_desc, that, 5);
        that.setData({
          goodsCont: res.data.data,
          addCont: res.data.data.goods_info,
          bargainInfo: res.data.data.bargain_info,
          bargain_list: res.data.data.bargain_list.slice(0, 2),
          bargain_ranking: res.data.data.bargain_ranking.slice(0, 2),
          properties: res.data.data.goods_properties.pro,
        })
        that.timer();
      }

    })
    wx.request({
      url: app.apiUrl("bargain/goBargain"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        id: order.id,
        bs_id: bs_id,
      },
      success: function (res) {
        if (res.data.data.error == 2) {
          that.setData({
            addCont: res.data.data,
            bargainInfo: res.data.data,
            showViewGo: !that.data.showViewGo,
            showViewMol: !that.data.showViewMol
          })
        } else {
          // wx.showToast({
          //   image: '../../images/failure.png',
          //   title: res.data.data.message,
          //   duration: 2000
          // })
        }
     
      }
        
    })


    //加载中
    this.loadingChange()

  },
  onShow: function () {
    order.num = 1;
    order.pro = [];
  },
  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 1000)
  },
  /*我要参与 */
  addBargain: function () {
    var that = this
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("bargain/addBargain"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        "id": order.id,
        "attr_id": JSON.stringify(tempOrderPro),
      },
      success: function (res) {
        if (res.data.data.error == 2) {
          wx.showToast({
            title: '参与成功',
            duration: 2000
          })
        }
        that.setData({
          addCont: res.data.data,
        })
        wx.request({
          url: app.apiUrl("bargain/goodsDetail"),
          data: {
            "id": order.id,
            bs_id: res.data.data.bs_id
          },
          method: "post",
          header: {
            'Content-Type': 'application/json',
            'X-ECTouch-Authorization': token
          },

          success: function (res) {
            that.setData({
              goodsCont: res.data.data,
              addCont: res.data.data.goods_info,
              bargain_list: res.data.data.bargain_list.slice(0, 2),
              bargain_ranking: res.data.data.bargain_ranking.slice(0, 2),
            })
          }
        })
      }
    })
  },
  /*去砍价 */
  goBargain: function () {
    var that = this
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("bargain/goBargain"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        id: order.id,
        bs_id: that.data.addCont.bs_id,
      },
      success: function (res) {
        if (res.data.data.error == 2) {
          that.setData({
            addCont: res.data.data,
            bargainInfo: res.data.data,
            showViewGo: !that.data.showViewGo,
            showViewMol: !that.data.showViewMol
          })
        } else {
          wx.showToast({
            image: '../../images/failure.png',
            title: res.data.data.message,
            duration: 2000
          })
        }
        wx.request({
          url: app.apiUrl("bargain/goodsDetail"),
          data: {
            "id": order.id,
            bs_id: res.data.data.bs_id
          },
          method: "post",
          header: {
            'Content-Type': 'application/json',
            'X-ECTouch-Authorization': token
          },
          success: function (res) {
            that.setData({
              goodsCont: res.data.data,
              addCont: res.data.data.goods_info,
              bargainInfo: res.data.data.bargain_info,
              bargain_list: res.data.data.bargain_list.slice(0, 2),
              bargain_ranking: res.data.data.bargain_ranking.slice(0, 2),
            })
          }
        })
      }
    })

  },

  goBargainClose: function () {
    var that = this;
    that.setData({
      showViewGo: !that.data.showViewGo,
      showViewMol: !that.data.showViewMol
    })
  },
  bargainFriends: function () {
    var that = this;
    that.setData({
      showViewCom: !that.data.showViewCom,
      showViewMol: !that.data.showViewMol
    })
  },

  //更多玩法
  bargainPlay: function () {
    var that = this;
    that.setData({
      showViewPlay: !that.data.showViewPlay,
      showViewMol: !that.data.showViewMol
    })
  },
  //亲友帮
  bargainFriendsMore: function () {
    var that = this;
    that.setData({
      showViewFriendsMore: !that.data.showViewFriendsMore,
      showViewMol: !that.data.showViewMol
    })
  },
  //排行榜
  bargainRanksMore: function () {
    var that = this;
    that.setData({
      showViewRankMore: !that.data.showViewRankMore,
      showViewMol: !that.data.showViewMol
    })
  },
  /*提交*/
  bargainCheckout: function (e) {
    var that = this
    var token = wx.getStorageSync('token')
    //获取id
    var goodsbtn = e.currentTarget.id || 'cart'
    var goodsId = that.data.goodsCont.goods_id

    wx.request({
      url: app.apiUrl("bargain/Bargainbuy"),
      data: {
        id: order.id,
        bs_id: that.data.goodsCont.goods_info.bs_id,
        num: 1,
        goods_id: that.data.goodsCont.goods_info.goods_id
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        var bs_id = res.data.data.bs_id
        var flow_type = res.data.data.flow_type
        wx.request({
          url: app.apiUrl('user/address/list'),
          method: 'POST',
          header: {
            'Content-Type': 'application/json',
            'X-ECTouch-Authorization': token
          },
          success: function (res) {
            if (res.data.data != '') {
              wx.navigateTo({
                url: "../flow/checkout?objectId=" + bs_id + "&flow_type=" + flow_type,
              });
            } else {
              wx.removeStorageSync('pageOptions')
              wx.navigateTo({
                url: "../address/index"
              });

            }
          }
        })

      }
    })

  },
  onChangeShowState: function () {
    var that = this;
    if (that.data.addCont.bs_id == '0') {
      that.setData({
        showView: (!that.data.showView)
      })
    } 
  },
  /*增加商品数量*/
  up: function () {
    var num = this.data.num;
    // num++;
    // if (num >= 99) {
    //   num = 99
    // }
    this.setData({
      num: num
    })
    order.num = num;
    this.getGoodsTotal();
  },
  /*减少商品数量*/
  down: function () {
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
  import: function (e) {
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
  modelTap: function (e) {
    var that = this
    //在砍价参与后其他按钮不可点击
    // if (that.data.goodsCont.goods_info.add_bargain==0){
    this.getProper(e.currentTarget.id)
    this.getGoodsTotal();
    // }
  },
  /*属性选择计算*/
  getProper: function (id) {
    tempOrderPro = []
    tempOrderProStr = []
    var categoryList = this.data.properties;
    for (var index in categoryList) {
      for (var i in categoryList[index].values) {
        categoryList[index].values[i].checked = false;
        if (categoryList[index].values[i].id == id) {
          order.pro[categoryList[index].name] = id
          order.prostr[categoryList[index].name] = categoryList[index].values[i].label
        }
      }
    }

    //处理页面
    for (var index in categoryList) {
      if (order.pro[categoryList[index].name] != undefined && order.pro[categoryList[index].name] != '') {
        for (var i in categoryList[index].values) {
          if (categoryList[index].values[i].id == order.pro[categoryList[index].name]) {
            categoryList[index].values[i].checked = true;
          }
        }
      }
    }
    for (var l in order.pro) {
      tempOrderPro.push(order.pro[l]);
    }
    for (var n in order.prostr) {
      tempOrderProStr.push(order.prostr[n]);
    }

    this.setData({
      properties: categoryList,
      selectedPro: tempOrderProStr.join(',')
    });
  },
  getGoodsTotal: function () {
    //提交属性  更新价格
    var that = this;
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("bargain/property"),
      data: {
        id: order.id,
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
      success: function (res) {
        that.setData({
          goods_price: res.data.data.goods_price_formated,
          property_price: res.data.data,
          stock: res.data.data.stock,
          attr_img: res.data.data.attr_img,
          goods_market_price: res.data.data.market_price_formated
        });

      }
    })
  },

  /*收藏*/
  addCollect: function () {
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
      success: function (res) {
        that.setData({
          collect_list: res.data.data
        })
      }
    })
  },

  //商品相册
  setCurrent: function (e) {
    this.setData({
      currentIndex: e.detail.current + 1
    })
  },
  imgPreview: function () { //图片预览
    const imgs = this.data.goodsCont.goods_img;
    wx.previewImage({
      current: imgs[this.data.currentIndex - 1], // 当前显示图片的http链接
      urls: imgs // 需要预览的图片http链接列表
    })
  },
  /**排行榜 */
  toFriends: function (res) {
    this.setData({
      hiddenFriends: false,
      hiddenRank: true,
    })
  },
  toRank: function (res) {
    this.setData({
      hiddenFriends: true,
      hiddenRank: false,
    })
  },
  /**内容切换 */
  toOrder: function (res) {
    this.setData({
      hiddenOrder: false,
      hiddenAddress: true
    })
  },
  toAddress: function (res) {
    this.setData({
      hiddenOrder: true,
      hiddenAddress: false
    })
  },

  //店铺
  storeDetail: function (e) {
    var id = this.data.goodsCont.detail.user_id
    wx.redirectTo({
      url: "../store/index?objectId=" + id
    });
  },

  
  flowCart: function () {
    wx.switchTab({
      url: '../flow/index',
    });
  },
  //快捷导航
  commonNav: function () {
    var that = this;
    var nav_select
    that.setData({
      nav_select: !that.data.nav_select
    });
  },
  bargainHome: function () {
    wx.navigateTo({
      url: "../bargain/index"
    });
  },
  //返回顶部
  goTop: function (e) {
    this.setData({
      scrollTop: 0
    })
  },
  scroll: function (e) {
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
  bindSharing: function () {
    var that = this
    var goodsId = that.data.goodsCont.goods_info.goods_id
    wx.navigateTo({
      url: "../goods/speak?objectId=" + goodsId
    });
  },
  // 页面渲染完成后 调用  
  timer: function () {
    var that = this
    var endTime = that.data.goodsCont.goods_info.end_time
    var totalSecond = endTime - Date.parse(new Date()) / 1000;

    var interval = setInterval(function () {
      // 秒数  
      var second = totalSecond;

      // 天数位  
      var day = Math.floor(second / 3600 / 24);
      var dayStr = day.toString();
      if (dayStr.length == 1) dayStr = '0' + dayStr;

      // 小时位  
      var hr = Math.floor((second - day * 3600 * 24) / 3600);
      var hrStr = hr.toString();
      if (hrStr.length == 1) hrStr = '0' + hrStr;

      // 分钟位  
      var min = Math.floor((second - day * 3600 * 24 - hr * 3600) / 60);
      var minStr = min.toString();
      if (minStr.length == 1) minStr = '0' + minStr;

      // 秒位  
      var sec = second - day * 3600 * 24 - hr * 3600 - min * 60;
      var secStr = sec.toString();
      if (secStr.length == 1) secStr = '0' + secStr;

      this.setData({
        countDownDay: dayStr,
        countDownHour: hrStr,
        countDownMinute: minStr,
        countDownSecond: secStr,
      });
      totalSecond--;
      if (totalSecond < 0) {
        clearInterval(interval);
        wx.showToast({
          title: '活动已结束',
        });
        this.setData({
          countDownDay: '00',
          countDownHour: '00',
          countDownMinute: '00',
          countDownSecond: '00',
        });
      }
    }.bind(this), 1000);
  },
  //获取点击的id值
  siteDetail: function (e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var goodsId = that.data.goodsCont.bargain_hot[index].id;
    app.getUserInfo(function (userInfo) {
      that.setData({
        userInfo: userInfo
      })
    })
    wx.navigateTo({
      url: "../bargain/goods?objectId=" + goodsId
    });
  },
  nav: function (e) {
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
  //分享
  onShareAppMessage: function () {
    var that = this
    var bs_id = this.data.addCont.bs_id
    var user_name = that.data.bargainInfo.user_name
    return {
      title: user_name + '邀请您帮忙砍价[拼团详情]',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/bargain/share?objectId=' + goodsId + "&bs_id=" + bs_id,
    }
  },


})








