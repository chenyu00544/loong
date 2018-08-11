var WxParse = require('../../wxParse/wxParse.js');
var app = getApp();
var dialog_ad_url_g = '';
var order = {
  id: "",
  num: 1,
  groupNum: 1,
  pro: [],
  prostr: []
}
var goodsbtn = ''
var tempOrderPro = [];
var tempOrderProStr = [];
var coupons_index = '';
var teamId
Page({
  data: {

      adshow: false,

    dialog_ad_url: '',
    dialog_ad_image: '',


    hidden: false,
    hiddenOrder: false,
    hiddenAddress: true,
    is_collect: 0,
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
    has_free_coupons: false,

    getuserBool: false,
  },
  onLoad: function (options) 
  {
    var gzid = app.getCurrentPageOption().gzid;

    if (gzid != undefined) {
      console.log("写入广告记录");
      ////写入广告记录。  
      app.addGzid(gzid, 0);
    }
    
    var that = this;
    // 获取用户数据
    if (app.globalData.userInfo) {
      that.setData({
        getuserBool: true
      })
    }

    var mktag = options.mktag;
    if (mktag == undefined) {
      mktag = '0';
    } else {
      app.addMkTag(mktag);
      wx.setStorageSync('mktag', mktag);
    }

    // 生命周期函数--监听页面加载
    var token = wx.getStorageSync('token')
    // 获取用户数据
    var goodsId = options.objectId;
    order.id = goodsId
    team_id = options.team_id;
    var team_id = (team_id ? team_id : '0')
    //调用应用实例的方法获取全局数据
    wx.request({
      url: app.apiUrl("team/goodsDetail"),
      data: {
        goods_id: goodsId,
        team_id: team_id,
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },

      success: function (res) 
      {
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
          }
          else {
            isadshow = true;
            app.myAdData.isIndexAdShow = true;//设置为已经显示。
          }

          dialog_ad_url_g = res.data.data.dialog_ad.ext_url;
          console.log("存在首页窗口广告::" + dialog_ad_url_g);

          dialog_ad_url_t = res.data.data.dialog_ad.ext_url;
          dialog_ad_image_t = res.data.data.dialog_ad.ad_image;

        }
        else {
          // console.log("不存在首页窗口广告"); 
        }
    ////////////////////////////////////////////////////




        that.setData({
          goodsCont: res.data.data,
          properties: res.data.data.goods_properties.pro,
          parameteCont: res.data.data.goods_properties.spe,
          // goodsComment: res.data.data.goods_comment.slice(0, 3),
          flowNum: res.data.data.cart_number,
          // collect_list: (res.data.data.goods_info.is_collect == 1) ? true : false,
          // couponsData: (res.data.data.coupont[0].pick)
          //res.data.data.has_free_coupons,
          has_free_coupons: res.data.data.has_free_coupons,

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
        // if()
        that.getGoodsTotal();
        that.teamProperty();
        that.timeEnd();

        if (that.data.has_free_coupons)
      {
          console.log("存在免单券");
      }
      else
      {
          console.log("不存在免单券");
      }


      }
    })

    wx.request({
      url: app.apiUrl("team/virtualOrder"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        that.setData({
          index_user: res.data.data
        })
      }
    })

    //加载中
    this.loadingChange()
  },


  //////////////////////////////////
  //点数收集
  formSubmitAD: function (e) {
    this.setData
      ({
        adshow: false
      })

    var formID = e.detail.formId;


    console.log("index 中::" + wx.getStorageSync('openid'));

    app.addFormId(formID);


  },
  formSubmitAD2: function (e) {
    this.setData
      ({
        adshow: false
      })

    var formID = e.detail.formId;


    console.log("index2222 中::" + wx.getStorageSync('openid'));

    app.addFormId(formID);

    //
    this.dialog_ad_click();

  },
  dialog_ad_click: function (e) {
    console.log("点击了窗口广告");

    if (dialog_ad_url_g != '') {
      wx.navigateTo
        ({
          url: dialog_ad_url_g
        })
    }

  },
  ///////////////////////////////////


  
  onShow: function () {
    order.num = 1;
    order.groupNum = 1;
    order.pro = [];

  },
  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 1000)
  },
  //更多玩法
  groupPlay: function () {
    var that = this;
    that.setData({
      showViewPlay: !that.data.showViewPlay,
      showViewMol: !that.data.showViewMol
    })
  },

  /*单独购买 */
  onChangeShowState: function () {
    var that = this;
    that.setData({
      showViewProperty: !that.data.showViewProperty,
      showViewMol: !that.data.showViewMol
    })
  },
  /*拼团购买 */
  groupProperty: function () {
    var that = this;
    that.setData({
      showViewGroupProperty: !that.data.showViewGroupProperty,
      showViewMol: !that.data.showViewMol,
      id: 'groupcheckout'
    })
  },
  //去参团
  goodsWait: function (e) {
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
  goodsCheckout: function (e) {
    var that = this
    var token = wx.getStorageSync('token')
    //获取id
    var goodsId = that.data.goodsCont.goods_id
    var goodsbtn = e.currentTarget.id || 'cart'
    if (goodsbtn == 'cart' || goodsbtn == 'checkout') {
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
        success: function (res) {
          var result = res.data.data;
          if (res.data.code == 0) {
            if (goodsbtn == 'cart') {

              that.setData({
                flowNum: res.data.data.total_number
              })
            }
            if (goodsbtn == 'checkout') {
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
                      url: "../flow/checkout"
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
      that.setData({
        showViewProperty: !that.data.showViewProperty,
        showViewMol: !that.data.showViewMol
      })
    } else {
      if (goodsbtn == 'addcheckout') {
         var team_newId = teamId
      } else {
        var team_newId = that.data.goodsCont.goods_info.team_id
      }
      wx.request({
        url: app.apiUrl("team/teamBuy"),
        data: {
          goods_id: that.data.goodsCont.goods_info.goods_id,
          t_id: that.data.goodsCont.goods_info.id,
          team_id: team_newId,
          num: order.groupNum,
          attr_id: JSON.stringify(tempOrderPro)
        },
        method: "post",
        header: {
          'Content-Type': 'application/json',
          'X-ECTouch-Authorization': token
        },
        success: function (res) {
          var t_id = res.data.data.t_id
          var flow_type = res.data.data.flow_type
          var team_id = res.data.data.team_id
          var bs_id = 0;
          if (res.data.data == '您已参团或开团'){
            wx.showToast({
              title: '您已参团或开团',
              icon:'none'
            });
            return;
          }
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
                  url: "../flow/checkout?objectId=" + bs_id + "&flow_type=" + flow_type + "&team_id=" + team_id + "&t_id=" + t_id,
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
      that.setData({
        showViewGroupProperty: !that.data.showViewGroupProperty,
        showViewMol: !that.data.showViewMol
      })
    }


  },

  /*拼团增加商品数量*/
  groupUp: function () {
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
    this.teamProperty();
  },
  /*手动输入商品*/
  groupImport: function (e) {
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
    this.teamProperty();

  },
  groupDown: function () {
    var groupNum = this.data.groupNum;
    groupNum--;
    if (groupNum <= 1) {
      groupNum = 1
    }
    this.setData({
      groupNum: groupNum
    })
    order.groupNum = groupNum;
    this.teamProperty();
  },

  /*增加商品数量*/
  up: function () {
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
    this.getProper(e.currentTarget.id)
    this.getGoodsTotal();
  },
  /*拼团属性 */
  groupModelTap: function (e) {
    this.getProper(e.currentTarget.id)
    this.teamProperty();
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
      url: app.apiUrl("goods/property"),
      data: {
        id: order.id,
        attr_id: tempOrderPro,
        num: order.num,
        groupNum: order.num,
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
          property: res.data.data
        });

      }
    })
  },
  teamProperty: function () {
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
      success: function (res) {
        that.setData({
          group_property: res.data.data
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
  //优惠券
  onChangeShowCoupons: function () {
    var that = this;
    that.setData({
      showViewCoupons: (!that.data.showViewCoupons)
    })
  },
  //领取优惠券
  printCoupont: function (e) {
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
      success: function (res) {

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
  groupHome: function () {
    wx.navigateTo({
      url: '../group/index',
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

  timeEnd: function () {
    let that = this;
    let len = that.data.goodsCont.team_log.length;//时间数据长度 
    function nowTime() {//时间函数  
      // console.log(a)  
      for (var i = 0; i < len; i++) {
        var intDiff = that.data.goodsCont.team_log[i].end_time - Date.parse(new Date()) / 1000;//获取数据中的时间戳  

        // console.log(intDiff)  
        var day = 0, hour = 0, minute = 0, second = 0;
        if (intDiff > 0) {//转换时间  
          day = Math.floor(intDiff / (60 * 60 * 24));
          hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
          minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
          second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
          if (hour <= 9) hour = '0' + hour;
          if (minute <= 9) minute = '0' + minute;
          if (second <= 9) second = '0' + second;
          that.data.goodsCont.team_log[i].end_time--;
          var str = hour + ':' + minute + ':' + second
          // console.log(str)      
        } else {
          var str = "已结束！";
          clearInterval(timer);
        }
        // console.log(str);  
        that.data.goodsCont.team_log[i].difftime = str;//在数据中添加difftime参数名，把时间放进去  
      }
      that.setData({
        goodsCont: that.data.goodsCont
      })
      // console.log(that)  
    }

    nowTime();
    var timer = setInterval(nowTime, 1000);
  },
  //分享
  onShareAppMessage: function () {
    return {
      title: '',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/group/goods?objectId=' + order.id
    }
  },

  //cyc
  getUser: function (res) {
    if (res.detail.userInfo) {
      this.onChangeShowState();
      this.setData({
        getuserBool: true,
      })
      app.setUserInfo(res)
    }
  },
  getUserB: function (res) {
    if (res.detail.userInfo) {
      this.groupProperty();
      this.setData({
        getuserBool: true,
      })
      app.setUserInfo(res)
    }
  },
  //cyc
})








