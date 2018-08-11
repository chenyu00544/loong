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
var dbsubmitBool = true;

var _gzid = '';
var _aid = '';
var _gdt_vid = '';
var _mktag = '';
var _goodsId = '';
var px2rpx = 2,
  windowWidth = 375;
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
    resShipingId: [],
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

    toView: '',

    region: ['请选择', '请选择', '请选择'],
    // 收货人信息
    consignee: '',
    mobile: '',
    address: '',
    addressData: [],
    is_bottom: false,
    imageSize: [],
    bt_name: '',
  },
  onLoad: function(options) {

    if (wx.getStorageSync('token') == "") {
      app.wxLogin();
    }

    var goodsId = options.objectId;
    if (options.objectId != undefined && options.objectId != '') {
      wx.setStorageSync('goodsId', goodsId);
      _goodsId = goodsId;
    }
    this.setData({
      goods_Id: goodsId,
    })
    var gzid = options.gzid;
    if (gzid == undefined) {
      gzid = '';
    } else {
      ////写入广告记录。  
      app.addGzid(gzid, goodsId);
      wx.setStorageSync('gzid', gzid);
      _gzid = gzid;
    }
    gz_id = gzid;
    var gdt_vid = options.gdt_vid;
    if (gdt_vid != undefined && gdt_vid != '') {
      wx.setStorageSync('gdt_vid', gdt_vid);
      _gdt_vid = gdt_vid;
    }

    if (options.gzid != undefined && options.objectId != undefined) {
      if (options.share === 'share') {
        wx.setStorageSync('gdt_vid', 'share');
      }
      app.adsAddClick();
    }

    let weixinadinfo = options.weixinadinfo;
    let aid = 0
    if (weixinadinfo) {
      let weixinadinfoArr = weixinadinfo.split('.')
      aid = weixinadinfoArr[0]
      wx.setStorageSync('aid', aid);
      _aid = aid;
    }

    var mktag = options.mktag;
    if (mktag == undefined) {
      mktag = '0';
    } else {
      app.addMkTag(mktag);
      wx.setStorageSync('mktag', mktag);
      _mktag = mktag;
    }

    // 获取用户数据
    if (app.globalData.userInfo) {
      this.setData({
        getuserBool: true
      })
    }
    var token = wx.getStorageSync('token');
    var that = this;

    var url = app.apiUrl("team/virtualOrder");
    if (gzid != undefined && gzid != '') {
      url = app.apiUrl("flow/cashorder");
    }
    wx.request({
      url: url,
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        goods_id: wx.getStorageSync('goodsId'),
      },
      success: function(res) {
        that.setData({
          index_user: res.data.data
        })
      }
    });

    order.id = goodsId
    //调用应用实例的方法获取全局数据
    wx.showNavigationBarLoading()
    wx.request({
      url: app.apiUrl("goods/detail"),
      data: {
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
          if (app.myAdData.isIndexAdShow) {
            isadshow = false;
          } else {
            isadshow = true;
            app.myAdData.isIndexAdShow = true; //设置为已经显示。
          }

          dialog_ad_url_g = res.data.data.dialog_ad.ext_url;

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
          goodsComment: res.data.data.goods_comment.slice(0, 3),
          flowNum: res.data.data.cart_number,
          collect_list: (res.data.data.goods_info.is_collect == 1) ? true : false,
          bt_name: res.data.data.goods_ads ? res.data.data.goods_ads.bt_name ? res.data.data.goods_ads.bt_name : '验货付款，立即抢购' : '验货付款，立即抢购',
          adshow: isadshow,
          dialog_ad_url: dialog_ad_url_t,
          dialog_ad_image: dialog_ad_image_t

        })
        tempOrderPro = []
        tempOrderProStr = []
        //商品有属性则默认选中第一个
        for (var i in res.data.data.goods_properties.pro) {
          that.getProper(res.data.data.goods_properties.pro[i].values[0].id)
        }
        that.getGoodsTotal();
        wx.setNavigationBarTitle({
          title: that.data.goodsCont.shop_name,
        });
        that.data.resShipingId.push({
          ru_id: res.data.data.detail.sellershopinfo.ru_id ? res.data.data.detail.sellershopinfo.ru_id : 0,
          shipping_id: res.data.data.goods_info.shipping_name.shipping_id,
          shipping_name: res.data.data.goods_info.shipping_name.shipping_title
        });
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
  },
  loadingChange() {
    setTimeout(() => {
      wx.hideNavigationBarLoading()
    }, 1000)
  },
  //////////////////////////////////
  //点数收集
  formSubmitAD: function(e) {
    this.setData({
      adshow: false
    })
    var formID = e.detail.formId;
    app.addFormId(formID);
  },
  formSubmitAD2: function(e) {
    this.setData({
      adshow: false
    })
    var formID = e.detail.formId;

    app.addFormId(formID);
    this.dialog_ad_click();
  },

  dialog_ad_click: function(e) {
    if (dialog_ad_url_g != '') {
      wx.navigateTo({
        url: dialog_ad_url_g
      })
    }
  },
  ///////////////////////////////////
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
      scrollTop: 0,
      is_bottom: false
    })
  },
  // 使页面滚动到底部
  goBottom: function(e) {
    this.setData({
      toView: 'submit-order',
      is_bottom: true
    });
    
    var gdt_vid = wx.getStorageSync('gdt_vid');
    if (gdt_vid) {
      var data = {
        "goods_id": wx.getStorageSync('goodsId'),
        "gzid": wx.getStorageSync('gzid'),
        "gdt_vid": gdt_vid,
        "aid": wx.getStorageSync('aid'),
        "mktag": wx.getStorageSync('mktag'),
        "action_type": "COMPLETE_ORDER"
      }
      app.upWxAdsUserAction(wx.getStorageSync('token'), data);
    }

    var formID = e.detail.formId;
    app.addFormId(formID);
  },
  scroll: function(e) {
    if (e.detail.scrollHeight > 0) {
      if (e.detail.scrollHeight - e.detail.scrollTop >= 1300) {
        this.setData({
          is_bottom: false
        });
      } else if (e.detail.scrollHeight - e.detail.scrollTop < 1300) {
        this.setData({
          is_bottom: true
        });
      }
    }

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
    var gzid = _gzid != '' ? _gzid : wx.getStorageSync('gzid');
    var gdt_vid = _gdt_vid != '' ? _gdt_vid : wx.getStorageSync('gdt_vid');
    var aid = _aid != '' ? _aid : wx.getStorageSync('aid');
    var mktag = _mktag != '' ? _mktag : wx.getStorageSync('mktag');
    var param = '';
    if (gzid != '') {
      param += '&gzid=' + gzid;
    }
    if (gdt_vid != '') {
      param += '&gdt_vid=' + gdt_vid;
    }
    if (aid != '') {
      param += '&aid=' + aid;
    }
    if (mktag != '') {
      param += '&mktag=' + mktag;
    }

    return {
      title: '商品详情',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/cashgoods/index?objectId=' + order.id + param
    }
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

  calling: function() {
    wx.makePhoneCall({
      phoneNumber: '4006006632',
      success: function() {},
    })
  },

  consigneeInput: function(e) {
    this.setData({
      consignee: e.detail.value
    })
  },
  mobileInput: function(e) {
    this.setData({
      mobile: e.detail.value
    })
  },
  addressInput: function(e) {
    this.setData({
      address: e.detail.value
    })
  },
  //货到付款
  goodsOnDelivery: function() {
    var that = this;
    var token = wx.getStorageSync('token');
    wx.showToast({
      title: '下单中',
      icon: 'loading',
      duration: 5000,
    });

    if (this.data.consignee.replace(/\s+/g, '') === '' || this.data.mobile.replace(/\s+/g, '') === '' || this.data.address.replace(/\s+/g, '') === '') {
      wx.showToast({
        title: '请认真填写地址',
        icon: 'none',
        duration: 3000,
      });
      return;
    }

    if (that.data.addressData['province_id'] == undefined) {
      wx.showToast({
        title: '请认真填写省市县',
        icon: 'none',
        duration: 3000,
      });
      return;
    }
    if (that.data.addressData['city_id'] == undefined) {
      wx.showToast({
        title: '请认真填写省市县',
        icon: 'none',
        duration: 3000,
      });
      return;
    }
    if (that.data.addressData['district_id'] == undefined) {
      wx.showToast({
        title: '请认真填写省市县',
        icon: 'none',
        duration: 3000,
      });
      return;
    }

    var postdata = {
      consignee: that.data.consignee,
      province: that.data.addressData['province_id'],
      city: that.data.addressData['city_id'],
      district: that.data.addressData['district_id'],
      address: that.data.address,
      mobile: that.data.mobile,
    }
    wx.request({
      url: app.apiUrl('flow/cashondel'),
      data: {
        "consignee": that.data.consignee,
        "shipping": that.data.resShipingId,
        "address": postdata,
        "uc_id": "0",
        "bn_id": "0",
        "bs_id": "0",
        "flow_type": "0",
        "team_id": "0",
        "t_id": "0",
        "id": order.id,
        "num": 1,
        "attr_id": JSON.stringify(tempOrderPro),
        "gzid": _gzid != '' ? _gzid : wx.getStorageSync('gzid'),
        "gdt_vid": _gdt_vid != '' ? _gdt_vid : wx.getStorageSync('gdt_vid'),
        "aid": _aid != '' ? _aid : wx.getStorageSync('aid'),
        "mktag": _mktag != '' ? _mktag : wx.getStorageSync('mktag'),
        "action_type": "COMPLETE_ORDER",
      },

      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        if (res.data.code === 1) {
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

  pickerTypeChange: function(e) {
    var token = wx.getStorageSync('token');
    var that = this;
    var area = e.detail.value;
    wx.request({
      url: app.apiUrl("region/mapping"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        province: area[0],
        city: area[1],
        area: area[2],
      },
      success: function(res) {
        that.data.addressData['province_id'] = res.data.data.province_id;
        that.data.addressData['city_id'] = res.data.data.city_id;
        that.data.addressData['district_id'] = res.data.data.area_id;
      }
    });
    that.setData({
      region: area,
      addressData: that.data.addressData,
    });
  },
  imageLoad: function(e) {
    //单位rpx
    var originWidth = e.detail.width * px2rpx,
      originHeight = e.detail.height * px2rpx,
      ratio = originWidth / originHeight;
    var viewWidth = 750,
      viewHeight //设定一个初始宽度
    //当它的宽度大于初始宽度时，实际效果跟mode=widthFix一致
    if (originWidth >= viewWidth) {
      //宽度等于viewWidth,只需要求出高度就能实现自适应
      viewHeight = viewWidth / ratio;
    } else {
      //如果宽度小于初始值，这时就不要缩放了
      viewWidth = originWidth;
      viewHeight = originHeight;
    }
    var imageSize = this.data.imageSize;
    imageSize[e.target.dataset.index] = {
      width: viewWidth,
      height: viewHeight
    }
    this.setData({
      imageSize: imageSize
    })
  },

  goOnLinePay:function(){
    wx.navigateTo({
      url: '../goods/index?objectId=' + _goodsId,
    })
  },
  //cyc
})