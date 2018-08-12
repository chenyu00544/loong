var dialog_ad_url_g = '';
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
var goodsbtns = "";

var bonus_pos = 0;

var gz_id = 0;
var interval;
Page({
  data: {

    adshow: false,

    dialog_ad_url: '',
    dialog_ad_image: '',

    showViewProperty: '',
    goodsType: "加入购物车",

    goodsbtns: "cart",

    hidden: false,
    hiddenOrder: false,
    hiddenAddress: true,
    is_collect: 0,
    currentIndex: 1,
    num: 1,
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
    IsAddress: false,

    //cyc
    cash_type: "",
    showBonus: false,
    bonusInfo: [],
    shopInfo: [],
    redBonus: [],
    faat: [],
    faat_time: '00:00:00',
    cd_time: 0,
    faat_time_t: '开始时间',
    animation: '',
    animationBt: '',
    animationS: '',
    openbonus: false,
    openedbonus: false,
    getuserBool: false,
    goods_Id: 0,
  },
  onLoad: function(options) {
    var goodsId = options.objectId;
    if (options.objectId != undefined && options.objectId != '') {
      wx.setStorageSync('goodsId', goodsId);
    }
    this.setData({
      goods_Id: goodsId,
    })
    var gzid = options.gzid; //getCurrentPageOption().gzid;
    if (gzid == undefined) {
      gzid = '0';
    } else {
      ////写入广告记录。  
      app.addGzid(gzid, goodsId);
      wx.setStorageSync('gzid', gzid);
    }
    gz_id = gzid;

    var gdt_vid = options.gdt_vid;
    if (gdt_vid != undefined && gdt_vid != '') {
      wx.setStorageSync('gdt_vid', gdt_vid);
    }

    if (options.gzid != undefined && options.objectId != undefined) {
      app.adsAddClick();
    }

    let weixinadinfo = options.weixinadinfo;
    let aid = 0
    if (weixinadinfo) {
      let weixinadinfoArr = weixinadinfo.split('.')
      aid = weixinadinfoArr[0]
      wx.setStorageSync('aid', aid);
    }

    var mktag = options.mktag;
    if (mktag == undefined) {
      mktag = '0';
    } else {
      app.addMkTag(mktag);
      wx.setStorageSync('mktag', mktag);
    }

    app.clearState();
    var that = this;
    var cash = options.cash;
    if (cash != undefined) {
      wx.setStorageSync('cash', cash);
      that.setData({
        cash_type: cash
      })
    }

    // 获取用户数据
    if (app.globalData.userInfo) {
      that.setData({
        getuserBool: true
      })
    }
    // 生命周期函数--监听页面加载
    var token = wx.getStorageSync('token')
    // 获取用户数据

    order.id = goodsId
    //调用应用实例的方法获取全局数据
    wx.showNavigationBarLoading();
    wx.request({
      url: app.apiUrl("goods/detail"),
      data: {
        ///额外传递广告来源
        "id": goodsId,
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },

      success: function(res) {
        WxParse.wxParse('goods_desc', 'html', res.data.data.goods_info.goods_desc, that, 5);

        ///////////////////////////////////
        var isHasdialogAd = res.data.data.is_dialog_ad;
        var isadshow = false;

        var dialog_ad_url_t = '';
        var dialog_ad_image_t = '';

        if (isHasdialogAd == '1') {
          console.log("存在首页窗口广告");
          if (app.myAdData.isIndexAdShow) {
            isadshow = false;
          } else {
            isadshow = true;
            app.myAdData.isIndexAdShow = true; //设置为已经显示。
          }

          dialog_ad_url_g = res.data.data.dialog_ad.ext_url;
          console.log("存在首页窗口广告::" + dialog_ad_url_g);

          dialog_ad_url_t = res.data.data.dialog_ad.ext_url;
          dialog_ad_image_t = res.data.data.dialog_ad.ad_image;

        } else {
          // console.log("不存在首页窗口广告"); 
        }
        ////////////////////////////////////////////////////

        that.setData({
          goodsCont: res.data.data,
          properties: res.data.data.goods_properties.pro,
          parameteCont: res.data.data.goods_properties.spe,
          goodsComment: res.data.data.goods_comment.slice(0, 10),
          flowNum: res.data.data.cart_number,
          collect_list: (res.data.data.goods_info.is_collect == 1) ? true : false,
          // couponsData: (res.data.data.coupont[0].pick)
          faat: res.data.data.faat,
          adshow: isadshow,
          dialog_ad_url: dialog_ad_url_t,
          dialog_ad_image: dialog_ad_image_t
        });
        tempOrderPro = []
        tempOrderProStr = []
        //商品有属性则默认选中第一个
        for (var i in res.data.data.goods_properties.pro) {
          that.getProper(res.data.data.goods_properties.pro[i].values[0].id)
        }

        that.getGoodsTotal();

        if (res.data.data.faat.end_time) {
          if (res.data.data.faat.faat_type == 1) {
            that.countTime();
          } else {
            that.cdTime();
            that.setData({
              faat_time: res.data.data.faat.faat_date,
            });
          }
        }

        that.getBonus(goodsId);
        if (that.data.cash_type == 'ondelivery') {
          wx.setNavigationBarTitle({
            title: that.data.goodsCont.shop_name,
          })
        }
        app.netReq("user/trajectory", {
          utype: 9,
          type_name: "进来浏览商品",
          action_name: that.data.goodsCont.goods_info.goods_name
        }, function(res) {});
      }
    })
    //加载中
    this.loadingChange()
  },
  onShow: function() {
    order.num = 1;
    order.pro = [];
    if (this.data.IsAddress == true) {
      this.data.IsAddress = false
    }
    console.log('__show');
  },
  onReady: function() {
    this.animation = wx.createAnimation({
      duration: 400,
      timingFunction: 'ease',
      delay: 100,
      transformOrigin: 'left top 50',
      success: function(res) {
        console.log(res)
      }
    });
    this.animationBt = wx.createAnimation({
      duration: 50,
      timingFunction: 'linear',
      success: function(res) {
        console.log(res)
      }
    })
    this.animationS = wx.createAnimation({
      duration: 300,
      timingFunction: 'ease',
      success: function(res) {
        console.log(res)
      }
    })
  },
  loadingChange() {
    setTimeout(() => {
      wx.hideNavigationBarLoading();
    }, 1000)
  },
  //////////////////////////////////
  //点数收集
  formSubmitAD: function(e) {
    this.setData({
      adshow: false
    })

    var formID = e.detail.formId;


    console.log("index 中::" + wx.getStorageSync('openid'));

    app.addFormId(formID);


  },
  formSubmitAD2: function(e) {
    this.setData({
      adshow: false
    })

    var formID = e.detail.formId;


    app.addFormId(formID);

    //
    this.dialog_ad_click();

  },

  dialog_ad_click: function(e) {
    console.log("点击了窗口广告");

    if (dialog_ad_url_g != '') {
      wx.navigateTo({
        url: dialog_ad_url_g
      })
    }

  },
  ///////////////////////////////////
  /*提交*/
  goodsCheckout: function(e) {
    if (goodsbtns != "cart") {
      wx.showLoading({
        title: '提交订单中',
        mask: true
      });
    }
    var that = this
    var token = wx.getStorageSync('token')
    //获取id
    var goodsbtn = goodsbtns;
    //var goodsbtn = e.currentTarget.id || 'cart';
    var goodsId = that.data.goodsCont.goods_id
    wx.request({
      url: app.apiUrl("cart/add"),
      data: {
        "id": order.id,
        "num": order.num,
        "attr_id": JSON.stringify(tempOrderPro),
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        app.netReq("user/trajectory", {
          utype: 4,
          type_name: "确定购买",
          action_name: that.data.goodsCont.goods_info.goods_name
        }, function(res) {});

        var result = res.data.data;
        if (res.data.code == 0) {
          if (goodsbtn == 'cart') {
            that.setData({
              flowNum: res.data.data.total_number,
              showViewProperty: !that.data.showViewProperty,
              showViewMol: !that.data.showViewMol
            })
          } else {
            wx.request({
              url: app.apiUrl('user/address/list'),
              method: 'POST',
              header: {
                'Content-Type': 'application/json',
                'X-ECTouch-Authorization': token
              },
              success: function(res) {
                wx.hideLoading();
                if (res.data.data != '') {
                  wx.navigateTo({
                    url: "../flow/checkout?buy_type=1"
                  });
                } else {
                  wx.removeStorageSync('pageOptions')
                  wx.navigateTo({
                    url: "../address/index?buy_type=1"
                  });
                }
              }
            })
          }
        } else {
          if (result == "商品已下架") {
            wx.showToast({
              title: '商品已下架',
              image: '../../images/failure.png',
              duration: 2000
            })
          }
        }
      }
    })
  },

  onChangeShowState: function() {
    var that = this;
    that.setData({
      showView: (!that.data.showView)
    })
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
    console.log(e.currentTarget.id)
    this.getProper(e.currentTarget.id)
    this.getGoodsTotal();
  },
  /*属性选择计算*/
  getProper: function(id) {
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
  getGoodsTotal: function() {
    //提交属性  更新价格
    var that = this;
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("goods/property"),
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
      success: function(res) {
        that.setData({
          goods_price: res.data.data.goods_price_formated,
          stock: res.data.data.stock,
          attr_img: res.data.data.attr_img,
          goods_market_price: res.data.data.market_price_formated
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
    return;
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
  //选择属性
  onChangeShowState: function() {
    var that = this;
    that.setData({
      showView: (!that.data.showView)
    })
  },
  //店铺
  storeDetail: function(e) {
    var id = this.data.goodsCont.detail.user_id
    wx.redirectTo({
      url: "../store/index?objectId=" + id
    });
    app.netReq("user/trajectory", {
      utype: 3,
      type_name: "进入店铺"
    }, function(res) {});
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
    console.log(that.data.goodsCont.coupont)
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
  flowCart: function() {
    wx.switchTab({
      url: '../flow/index',
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
  //分享
  onShareAppMessage: function() {
    var cash = wx.getStorageSync('cash', cash);
    var param = '';
    if (cash != undefined) {
      param = "&cash=" + cash;
      that.setData({
        cash_type: cash
      })
    }
    return {
      title: '商品详情',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/goods/index?objectId=' + order.id + param
    }
  },

  /*我要参与 */
  addBargain: function(e) {
    var that = this;
    console.log(e.currentTarget.id);
    goodsbtns = e.currentTarget.id || 'cart';
    var goodsType = "";
    if (goodsbtns == "cart") {
      goodsType = "加入购物车"
    } else {
      goodsType = "确定"
    }
    that.setData({
      showViewProperty: !that.data.showViewProperty,
      showViewMol: !that.data.showViewMol,
      goodsType: goodsType,
      goodsbtns: goodsbtns,
    })

    //微信广告行为转化前端意向购买
    var gdt_vid = wx.getStorageSync('gdt_vid');
    if (gdt_vid) {
      var data = {
        "goods_id": that.data.goods_Id,
        "gzid": wx.getStorageSync('gzid'),
        "gdt_vid": gdt_vid,
        "aid": wx.getStorageSync('aid'),
        "mktag": wx.getStorageSync('mktag'),
        "action_type": "COMPLETE_ORDER"
      }
      app.upWxAdsUserAction(wx.getStorageSync('token'), data);
    }

    app.netReq("user/trajectory", {
      utype: 4,
      type_name: "意向购买",
      action_name: that.data.goodsCont.goods_info.goods_name
    }, function(res) {});
  },
  onChangeShowState: function() {
    var that = this;

    /**
    if (that.data.addCont.bs_id != '0') {
      wx.showToast({
        image: '../../images/failure.png',
        title: "已参与砍价",
        duration: 2000
      })
    }
    **/
    that.setData({
      showViewProperty: !that.data.showViewProperty,
      showViewMol: !that.data.showViewMol
    })
  },

  // toChild: function () {
  //   var that = this
  //   var goodsId = that.data.goodsCont.goods_info.goods_id
  //   wx.navigateTo({
  //     url: "../goods/comment?objectId=" + goodsId
  //   })
  // },


  //cyc获取红包数据
  getBonus: function(pos) {
    bonus_pos = pos;
    var that = this;
    var token = wx.getStorageSync('token');
    wx.request({
      url: app.apiUrl("bonus/info"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        ad_id: 2,
        pos_id: pos,
        ad_type: 'goods'
      },
      success: function(res) {
        if (res.data.data.bn_info > 0) {
          var shopInfo = res.data.data.shop_info;
          if (!shopInfo.logo_thumb) {
            shopInfo.logo_thumb = '../../images/logo.png';
          }

          that.setData({
            showBonus: true,
            bonusInfo: res.data.data.ad_info,
            shopInfo: shopInfo,
          })
        } else {
          that.setData({
            showBonus: false,
          })
        }
      }
    })
  },
  //显示红包动作
  showBonusAction: function() {
    this.animation.top("12%").step();
    this.setData({
      openbonus: true,
    });
    this.setData({
      animation: this.animation.export()
    });
  },
  //打开红包动作
  openBonusAction: function() {
    var that = this;
    var i = 0;
    var time = setInterval(function() {
      if (i % 2 == 0) {
        if (i == 0) {
          that.animationBt.translateX(2.5).step();
          that.setData({
            animationBt: that.animationBt.export()
          });
        } else {
          that.animationBt.translateX(5).step();
          that.setData({
            animationBt: that.animationBt.export()
          });
        }
      } else {
        that.animationBt.translateX(-5).step();
        that.setData({
          animationBt: that.animationBt.export()
        });
      }
      i++;
    }, 50)

    that.receiveBonus(time);

  },
  closeBonusAction: function() {
    this.animation.top("-870rpx").step();
    this.setData({
      openbonus: false,
      openedbonus: false,
      animation: this.animation.export()
    });

  },
  //领取红包
  receiveBonus: function(t) {
    var that = this;
    var token = wx.getStorageSync('token');
    wx.request({
      url: app.apiUrl("bonus/receive"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        ad_id: 2,
        pos_id: bonus_pos,
        open_id: wx.getStorageSync('openid'),
        ad_type: 'goods'
      },
      success: function(res) {
        clearInterval(t);
        that.animationBt.translateX(0).step();
        that.setData({
          animationBt: that.animationBt.export()
        });
        that.setData({
          openbonus: false,
          openedbonus: true,
          showBonus: false,
          redBonus: res.data.data,
        });
      }
    })
  },
  shareBonus: function() {
    this.onShareAppMessage()
  },
  //阻止滑动穿透
  catchTouch: function() {
    return;
  },
  submitFormId: function(e) {
    var formID = e.detail.formId;
    console.log(formID);
    app.addFormId(formID);
  },

  //cyc
  getUser: function(res) {
    if (res.detail.userInfo) {
      this.setData({
        getuserBool: true,
      })
      app.setUserInfo(res);
      this.addBargain({
        currentTarget: {
          id: "cart"
        }
      });
    }
  },
  getUserA: function(res) {
    if (res.detail.userInfo) {
      this.setData({
        getuserBool: true,
      })
      app.setUserInfo(res);
      this.addBargain({
        currentTarget: {
          id: "ondelivery"
        }
      });
    }
  },
  getUserB: function(res) {
    if (res.detail.userInfo) {
      this.setData({
        getuserBool: true,
      })
      app.setUserInfo(res);
      this.addBargain({
        currentTarget: {
          id: "checkout"
        }
      });
    }
  },
  goodsVideo: function(e) {
    var that = this;

    var formID = e.detail.formId;
    app.addFormId(formID);
    
    wx.navigateTo({
      url: '../../packageA/pages/gvideo/index?objectId=' + that.data.goods_Id,
    })
  },
  //倒计时
  cdTime: function() {
    clearInterval(interval);
    var that = this;
    var nowTime = Date.parse(new Date()) / 1000;
    var second = that.data.faat.end_time - nowTime;
    that.setData({
      cd_time: second,
      faat_time_t: '开始时间'
    });
    interval = setInterval(function() {
      second -= 1;
      that.setData({
        cd_time: second,
      });
      if (second < 0) {
        that.countTime();
      }
    }.bind(this), 1000);
  },
  countTime: function() {
    var that = this;
    that.setData({
      faat_time_t: '距结束还有'
    });
    clearInterval(interval);
    interval = setInterval(function() {
      // 秒数 
      var count_time = '';
      var nowTime = Date.parse(new Date()) / 1000;
      var second = that.data.faat.end_time - nowTime;

      // 小时位  
      var hr = Math.floor((second) / 3600);
      var hrStr = hr.toString();
      if (hrStr.length == 1) hrStr = '0' + hrStr;

      // 分钟位  
      var min = Math.floor((second - hr * 3600) / 60);
      var minStr = min.toString();
      if (minStr.length == 1) minStr = '0' + minStr;

      // 秒位  
      var sec = second - hr * 3600 - min * 60;
      var secStr = sec.toString();
      if (secStr.length == 1) secStr = '0' + secStr;
      count_time = hrStr + ':' + minStr + ':' + secStr;
      if (second < 0) {
        clearInterval(interval);
        count_time = hrStr + ':' + minStr + ':' + secStr;
      }
      that.setData({
        faat_time: count_time,
      });
    }.bind(this), 1000);
  },
  //cyc

})