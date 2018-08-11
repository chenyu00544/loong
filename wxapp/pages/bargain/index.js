var app = getApp();

var dialog_ad_url_g = '';

var token
var interval = '';

Page({
  data: {

    adshow: false,

    dialog_ad_url: '',
    dialog_ad_image: '',

    iphoneView: true,
    hidden: false,
    indicatorDots: true,
    autoplay: true,
    interval: 4000,
    duration: 1000,
    hasLocation: false,

    count_time: [],
    activity_over: [],

    index: [],
    isLoading: false,
    isHasMore: true,
    page: 1,
  },
  onLoad: function (options) {

    var gzid = app.getCurrentPageOption().gzid;

    if (gzid != undefined) {
      console.log("写入广告记录");
      ////写入广告记录。  
      app.addGzid(gzid, 0);
    }

    var that = this;
    // app.getUserInfo(function (userInfo) {
    //   that.setData({
    //     userInfo: userInfo
    //   })
    // })

    var mktag = options.mktag;
    if (mktag == undefined) {
      mktag = '0';
    } else {
      app.addMkTag(mktag);
      wx.setStorageSync('mktag', mktag);
    }

    //初始化onLoad
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("bargain"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        console.log(res)
        that.setData({
          banner: res.data.data.banner,
        })
      }
    })
    wx.request({
      url: app.apiUrl("bargain/list"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        page: "1",
        per_page: 10
      },
      success: function (res) {
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
          index: res.data.data.bargain_list,
          adshow: isadshow,
          dialog_ad_url: dialog_ad_url_t,
          dialog_ad_image: dialog_ad_image_t
        });
        that.countTime();
      }
    })

    //加载中
    this.loadingChange();
    that.onChangeShowIphone();
  },

  scroll: function (e) {
    if (e.detail.scrollTop > 500) {
      this.setData({
        floorstatus: true
      });
    } else {
      this.setData({
        floorstatus: false
      });
    }
  },

  //获取点击的id值
  siteDetail: function (e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var goodsId = that.data.index[index].id;
    wx.navigateTo({
      url: "../bargain/goods?objectId=" + goodsId
    });
  },
  onChangeShowIphone: function () {
    var that = this;
    that.setData({
      iphoneView: (!that.data.iphoneView)
    })
  },
  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 1000)
  },

  //////////////////////////////////
  //点数收集
  formSubmitAD: function (e) {
    this.setData({
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

  //下拉刷新完后关闭
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
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
  //分享
  onShareAppMessage: function () {
    return {
      title: '米时砍价,越砍越实惠',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/bargain/index'
    }
  },
  myCatchTouch: function () {
    return;
  },
  //cyc

  countTime: function () {
    clearInterval(interval);
    var that = this;
    var endTimeArr = that.data.index;
    var nowTime = Date.parse(new Date()) / 1000;
    var totalSecond = [];
    var activity_over = [];
    for (var i = 0; i < endTimeArr.length; i++) {
      var endTime = parseInt(endTimeArr[i].end_time);
      totalSecond.push(endTime - Date.parse(new Date()) / 1000);
      activity_over.push(false);
    }
    interval = setInterval(function () {
      // 秒数 
      var count_time = [];
      for (var j = 0; j < totalSecond.length; j++) {
        var second = totalSecond[j];

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
        count_time[j] = { 'hour': hrStr, 'min': minStr, 'sec': secStr }
        totalSecond[j] = totalSecond[j] - 1;
        if (totalSecond[j] < 0) {
          clearInterval(interval);
          count_time[j] = { 'hour': "00", 'min': "00", 'sec': "00" }
          activity_over[j] = true;
        }
      }
      that.setData({
        count_time: count_time,
        activity_over: activity_over
      });
    }.bind(this), 1000);
  },

  getMoreGoods: function () {
    var that = this
    var page = this.data.page + 1;
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("bargain/list"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        page: page,
        per_page: 10
      },
      success: function (res) {
        var length = res.data.data.bargain_list.length;
        if (length > 0) {
          var list = that.data.index.concat(res.data.data.bargain_list);
          that.setData({
            index: list,
            page: page,
            isLoading: false,
          })
          if (length < 10) {
            that.setData({
              isHasMore: false,
            })
          }
        } else {
          that.setData({
            isLoading: false,
            isHasMore: false,
          })
        }
        that.countTime();
      }
    })
  },

  onReachBottom: function () {
    this.loadMore()
  },

  loadMore: function () {
    if (!this.data.isLoading) {
      if (this.data.isHasMore) {
        this.setData({
          isLoading: true,
        })
        this.getMoreGoods();
      }
    }
  },

  //end-cyc
})