var app = getApp();
var dialog_ad_url_g = '';
var token
var size = '10'
var tc_id = '0'
Page({
  data: {

    adshow: false,

    dialog_ad_url: '',
    dialog_ad_image: '',

    hidden: false,
    indicatorDots: true,
    autoplay: true,
    currentTab: 0, //预设当前项的值
    scrollLeft: 0, //tab标题的滚动条位置
    scrollTop: 0,
    
    //cyc
    user_show: true,
    currentpage: 'home',
    isLoading: false,
    isHasMore: true,
    page: 1,
    //cyc-e   
  },
  onLoad: function (options) 
  {
    var gzid = app.getCurrentPageOption().gzid;

    if (gzid != undefined) {
      console.log("写入广告记录");
      ////写入广告记录。  
      app.addGzid(gzid, 0);
    }
    
    if (options.nav != undefined){
      this.data.currentpage = options.nav;
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

    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("team/virtualOrder"),
      method: "POST", 
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        that.setData({
          index_user:res.data.data
        })
      }
    })
    that.homeCont();//拼团首页广告
    that.groupList();//商品列表

    //加载中
    this.loadingChange();
  },

  //拼团首页
  homeCont: function () {
    var that = this
    //初始化onLoad
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("team"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) 
      {
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
        that.setData
        ({
          index: res.data.data,
          adshow: isadshow,
          dialog_ad_url: dialog_ad_url_t,
          dialog_ad_image: dialog_ad_image_t
        })

        if (that.data.currentpage != 'home') {
          var nav = res.data.data.team_categories[that.data.currentpage];
          var e = { 'current': nav.tc_id, 'index': that.data.currentpage }
          that.navToDir(e);
        }
      }
    })
  },
  // 点击标题切换当前页时改变样式
  swichNav: function (e) {
    var that = this
    var token = wx.getStorageSync('token')
    var cur = e.target.dataset.current;
    var index = e.currentTarget.dataset.index;
    if (index == 'home'){
      that.setData({
        user_show:true,
      })
    }else{
      that.setData({
        user_show: false,
      })
    }
    that.data.currentpage = index;
    that.data.page = 1;
    that.data.isLoading = false;
    that.data.isHasMore = true;
    if (index != 'home') {
      if (cur > 3) {
        that.setData({
          scrollLeft: 300
        })
      } else {
        that.setData({ 
          scrollLeft: 0
        })
      }
      //子频道API
      tc_id = cur
      that.groupList();
      wx.request({
        url: app.apiUrl("team/categoriesIndex"),
        method: "POST",
        data: {
          tc_id: cur 
        },
        header: {
          'Content-Type': 'application/json',
          'X-ECTouch-Authorization': token
        },
        success: function (res) {
          wx.setNavigationBarTitle({
            title: res.data.data.title,
          })
          that.setData({
            index: res.data.data,
          })

        }
      })
    } else {
      //拼购首页
      tc_id = 0
      that.groupList();
      that.homeCont();
      wx.setNavigationBarTitle({
        title: '拼实惠',
      })
    }
    //切换效果
    if (this.data.currentTaB == cur) { return false; }
    else {
      this.setData({
        currentTab: cur
      })
    }
    this.setData({
      scrollTop: 0
    })
  },

  //秒杀
  swichNav2:function(e)
  {
    wx.navigateTo({
      url: 'sell'
    })
  },






  /*返回顶部*/
  goTop: function (e) {
    this.setData({
      scrollTop: 0
    })
  },
  scroll: function (e) {
    // this.setData({
    //   scrollTop: e.detail.scrollTop
    // });
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
  //子频道
  groupChannel: function (e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var tcId = that.data.index.team_categories_child[index].tc_id
    wx.navigateTo({
      url: "../group/channel?objectId=" + tcId
    });

  },
  //商品列表
  groupList: function () {
    var that = this
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("team/teamList"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        page: that.data.page,
        size: size,
        tc_id: tc_id
      },
      success: function (res) {
        that.setData({
          list: res.data.data,
        })
      }
    })
  },
  //获取点击的id值
  siteDetail: function (e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var goodsId = that.data.list[index].goods_id;
    // app.getUserInfo(function (userInfo) {
    //   that.setData({
    //     userInfo: userInfo
    //   })
    // })
    wx.navigateTo({
      url: "../group/goods?objectId=" + goodsId
    });
  },
  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 1000)
  },
  //下拉刷新完后关闭
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
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

  //分享
  onShareAppMessage: function () {
    return {
      title: '越拼越实惠',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/group/index'
    }
  },

  //cyc
  //根据进入页参数切换当前页时改变样式
  navToDir: function (e) {
    var that = this
    var token = wx.getStorageSync('token')
    var cur = e.current;
    var index = e.index;
    if (index == 'home') {
      that.setData({
        user_show: true,
      })
    } else {
      that.setData({
        user_show: false,
      })
    }
    if (index != 'home') {
      if (cur > 3) {
        that.setData({
          scrollLeft: 300
        })
      } else {
        that.setData({
          scrollLeft: 0
        })
      }
      //子频道API
      tc_id = cur
      that.groupList();
      wx.request({
        url: app.apiUrl("team/categoriesIndex"),
        method: "POST",
        data: {
          tc_id: cur
        },
        header: {
          'Content-Type': 'application/json',
          'X-ECTouch-Authorization': token
        },
        success: function (res) {
          wx.setNavigationBarTitle({
            title: res.data.data.title,
          })
          that.setData({
            index: res.data.data,
          })

        }
      })
    } else {
      //拼购首页
      tc_id = 0
      that.groupList();
      that.homeCont();
      wx.setNavigationBarTitle({
        title: '拼购',
      })
    }
    //切换效果
    if (this.data.currentTaB == cur) { return false; }
    else {
      this.setData({
        currentTab: cur
      })
    }
    this.setData({
      scrollTop: 0
    })
  },

  getMoreGoods: function () {
    var that = this
    var page = this.data.page + 1;
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl("team/teamList"),
      method: "POST",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      data: {
        page: page,
        size: size,
        tc_id: tc_id
      },
      success: function (res) {
        var length = res.data.data.length;
        if (length > 0) {
          var list = that.data.list.concat(res.data.data);
          that.setData({
            list: list,
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
  //cyc
})









