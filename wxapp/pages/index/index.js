var app = getApp();
var goods_id = '',
  navPage = 0,
  isloading = true,
  touchDot = 0,
  scrollTop = 0,
  timeout;

Page({
  data: {
    navList: [],
    banner: [],
    ads: [],
    insert: [],
    goodses: [],
    faat: [],
    popup: [],
    imageSize: [],
    adshow: false,
    //导航参数start
    curNav: 0,
    curIndex: 0,
    curSubIndex: 0,
    toView: 'nav_0',
    scrollX: 0,
    scroll_sub_X: 0,
    navIsHide: false,
    floorstatus: false,
    //导航参数end
    //子导航start
    curSubNav: 0,
    toSubView: 'nav_sub_0',
    //子导航end
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    current: 0,
    scrollview_h: app.winHeight(),
  },
  onLoad: function(options) {
    var that = this;

    //外部广告参数start
    var mktag = options.mktag;
    if (mktag == undefined) {
      mktag = '0';
    } else {
      app.addMkTag(mktag);
      wx.setStorageSync('mktag', mktag);
    }
    if (options.nav != undefined && options.nav != '') {
      navPage = options.nav;
      wx.setStorageSync('nav', navPage);
    }
    if (options.objectId != undefined && options.objectId != '') {
      goods_id = options.objectId;
      wx.setStorageSync('goodsId', goods_id);
    }
    var gdt_vid = options.gdt_vid;
    if (gdt_vid != undefined && gdt_vid != '') {
      wx.setStorageSync('gdt_vid', gdt_vid);
    }
    var aid = options.aid;
    if (aid != undefined && aid != '') {
      wx.setStorageSync('aid', aid);
    }
    var cash = options.cash;
    if (cash != undefined && cash != '') {
      wx.setStorageSync('cash', cash);
    }
    var gzid = options.gzid;
    if (gzid != undefined && gzid != '') {
      wx.setStorageSync('gzid', gzid);
    }
    if (options.gzid != undefined && options.objectId != undefined) {
      app.adsAddClick();
    }
    //外部广告参数end
    this.getIndexData(navPage, 0);
  },

  //导航点击动作start
  selectNav(e) {
    let index = parseInt(e.target.dataset.index);
    this.setData({
      curNav: index,
    });
    this.getIndexData(e.target.dataset.navid, index);
    if (e.detail.x > app.winWidth() - 100) {
      for (var i = 0; i < this.data.navList.length; i++) {
        if ('nav_' + this.data.navList[i].id == e.target.id) {
          if (i < this.data.navList.length - 1) {
            this.setData({
              scrollX: this.data.scrollX + e.currentTarget.offsetLeft
            })
          }
        }
      }
    } else if (e.detail.x < 100) {
      for (var i = 0; i < this.data.navList.length; i++) {
        if ('nav_' + this.data.navList[i].id == e.target.id) {
          if (i < 1) {
            this.data.curIndex = 1;
          } else {
            this.data.curIndex = i;
          }
          break
        }
      }
      this.setData({
        toView: 'nav_' + this.data.navList[this.data.curIndex - 1].id,
      })
      for (var i = 0; i < this.data.navList.length; i++) {
        if (this.data.navList[i].id == e.target.id) {
          if (i > 1) {
            this.data.scrollX = e.currentTarget.offsetLeft;
          } else {
            this.data.scrollX = 0;
          }
        }
      }
    }
  },
  scroll_X: function(e) {
    this.data.scrollX = e.detail.scrollLeft;
  },
  //导航点击动作end

  //点击子导航start
  selectSubNav(e) {
    let index = parseInt(e.currentTarget.dataset.index);
    this.setData({
      curSubNav: index,
      // toSubView: 'nav_sub_' + this.data.faat[this.data.curNav][index].act_id,
    });
    if (e.detail.x > app.winWidth() - 100) {
      for (var i = 0; i < this.data.faat[this.data.curNav].length; i++) {
        if ('nav_sub_' + this.data.faat[this.data.curNav][i].act_id == e.currentTarget.id) {
          if (i < this.data.faat[this.data.curNav].length - 1) {
            this.setData({
              scroll_sub_X: this.data.scroll_sub_X + app.winWidth() / 2
            })
          }
        }
      }
    } else if (e.detail.x < 100) {
      for (var i = 0; i < this.data.faat[this.data.curNav].length; i++) {
        if ('nav_sub_' + this.data.faat[this.data.curNav][i].act_id == e.currentTarget.id) {
          if (i < 1) {
            this.data.curSubIndex = 1;
          } else {
            this.data.curSubIndex = i;
          }
          break
        }
      }
      this.setData({
        toSubView: 'nav_sub_' + this.data.faat[this.data.curNav][this.data.curSubIndex - 1].act_id,
      })
      for (var i = 0; i < this.data.faat[this.data.curNav].length; i++) {
        if ('nav_sub_' + this.data.faat[this.data.curNav][i].act_id == e.currentTarget.id) {
          if (i > 1) {
            this.data.scroll_sub_X = e.currentTarget.offsetLeft;
          } else {
            this.data.scroll_sub_X = 0;
          }
        }
      }
    }
  },
  //点击子导航end

  //检测纵向滚动
  scroll_Y: function(e) {
    scrollTop = e.detail.scrollTop;
    if (e.detail.scrollTop > 300) {
      this.setData({
        floorstatus: true,
      })
    } else if (e.detail.scrollTop <= 300) {
      this.setData({
        floorstatus: false,
        navIsHide: false,
      })
    }
    if (e.detail.scrollTop > e.detail.scrollHeight - app.winHeight() * 2) {
      if (isloading) {
        isloading = false;
        this.loadMore();
      }
    }
  },

  //触摸移动
  touchStart: function(e) {
    touchDot = e.touches[0].pageY;
  },
  touchMove: function(e) {
    var touchMove = e.touches[0].pageY;
    if (scrollTop > 300) {
      // 向上滑动    
      if (touchMove - touchDot <= -20) {
        if (this.data.navIsHide == false) {
          this.setData({
            navIsHide: true,
            autoplay: false,
          })
        }
      }
      // 向下滑动  
      if (touchMove - touchDot >= 20) {
        if (this.data.navIsHide == true) {
          this.setData({
            navIsHide: false,
            autoplay: true,
          })
        }
      }
    }
  },
  touchEnd: function(e) {

  },

  //获取页面数据
  getIndexData: function(nav, index) {
    var nav_id = nav;
    var that = this;
    app.netReq("index", {
      goods_id: goods_id,
      nav_id: nav,
    }, function(res) {
      if (that.data.navList.length == 0) {
        that.data.navList = res.data.data.nav_list;
      }
      for (var i in that.data.navList) {
        if (that.data.navList[i].id == nav) {
          index = i;
        }
      }
      if (!that.data.banner[index]) {
        that.data.banner[index] = res.data.data.ads.slide;
      }
      if (!that.data.insert[index]) {
        that.data.insert[index] = res.data.data.ads.insert;
      }
      if (!that.data.ads[index]) {
        that.data.ads[index] = res.data.data.ads.ads;
      }
      if (!that.data.goodses[index]) {
        that.data.goodses[index] = res.data.data.goods_list;
      }
      if (!that.data.faat[index]) {
        that.data.faat[index] = res.data.data.faat;
      }
      that.setData({
        current: 0,
        navList: res.data.data.nav_list,
        banner: that.data.banner,
        ads: that.data.ads,
        insert: that.data.insert,
        goodses: that.data.goodses,
        faat: that.data.faat,
        curNav: index,
      });
      if (nav_id == 0) {
        nav_id = res.data.data.nav_list[0].id;
      }
      clearInterval(timeout);
      timeout = setTimeout(function() {
        app.netReq('index/popup', {
          nav_id: nav_id
        }, function(res) {
          if (res.data.data.popup.ad_id) {
            if (app.myAdData.isIndexAdShow) {
              that.data.adshow = false;
            } else {
              that.data.adshow = true;
              app.myAdData.isIndexAdShow = true;
            }
          } else {
            that.data.adshow = false;
          }
          that.setData({
            popup: res.data.data.popup,
            adshow: that.data.adshow,
          });
        });
      }, 5000);
    });
  },
  //加载更多
  loadMore: function() {
    var that = this;
    var curNav = that.data.curNav;
    if (!that.data.navList[curNav].page) {
      that.data.navList[curNav].page = 2;
    } else {
      that.data.navList[curNav].page += 1;
    }
    if (that.data.navList[curNav].goods_cate_id == 0) {
      isloading = true;
      return;
    }
    app.netReq("index/loadmore", {
      goods_id: goods_id,
      cate_id: that.data.navList[curNav].goods_cate_id,
      curpage: that.data.navList[curNav].page,
    }, function(res) {
      isloading = true;
      that.data.goodses[curNav] = that.data.goodses[curNav].concat(res.data.data.goods_list);
      that.setData({
        goodses: that.data.goodses,
      });
    });
  },

  //进去详情页
  siteDetail: function(e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var goodsId = e.currentTarget.dataset.goodsid;
    var formID = e.detail.formId;
    app.addFormId(formID);
    
    wx.navigateTo({
      url: "../goods/index?objectId=" + goodsId,
      // url: "../cashgoods/index?objectId=" + goodsId// +"&mktag=12&gzid=GZ47_715-T2&gdt_vid=wx06h5cq2ttv4r4i",
      // url: "../../packageA/pages/bonus/index",
      // url: "faat?nav=48&page=0",
    });
    app.netReq("user/trajectory", {
      utype: 1,
      type_name: "普通商品",
      action_name: e.currentTarget.dataset.goods_name
    }, function(res) {});
  },

  //返回顶部
  goTop: function(e) {
    this.setData({
      scrollTop: 0,
      autoplay: true
    })
  },

  //根据图片宽高设置宽高
  imageLoad: function(e) {
    var px2rpx = 2;
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

  //关闭弹窗
  closePopup: function() {
    app.myAdData.isIndexAdShow = true;
    this.setData({
      adshow: false,
    })
  },

  formSubmitAD: function(e) {
    this.setData({
      adshow: false
    })
    var formID = e.detail.formId;
    app.addFormId(formID);
    app.netReq("user/trajectory", {
      utype: 17,
      type_name: "关闭弹窗",
      action_name: ""
    }, function(res) {});
  },
  formSubmitAD2: function(e) {
    this.setData({
      adshow: false
    });
    var formID = e.detail.formId;
    app.addFormId(formID);
    this.dialog_ad_click();
    app.netReq("user/trajectory", {
      utype: 18,
      type_name: "点击广告弹窗",
      action_name: ""
    }, function(res) {});
    if (this.data.popup.ext_url != '') {
      wx.navigateTo({
        url: this.data.popup.ext_url
      })
    }
  },

  onPullDownRefresh: function() {
    wx.stopPullDownRefresh();
  },

  formSubmitAD3: function (e) {
    this.setData({
      adshow: false
    })

    var formID = e.detail.formId;

    app.addFormId(formID);

    var targetUrl = e.currentTarget.dataset.url;

    if (targetUrl != '') {
      if (targetUrl == '../../pages/category/index'){
        wx.switchTab({
          url: '' + targetUrl,
        })
      }else{
        wx.navigateTo({
          url: '' + targetUrl
        })
      }
      
      app.netReq("user/trajectory", {
        utype: 1,
        type_name: "广告商品",
        action_name: targetUrl
      }, function (res) { });
    }
  },
})