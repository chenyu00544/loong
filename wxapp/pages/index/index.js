var app = getApp();

var goods_id = '',
  navPage = 0,
  isloading = true,
  touchDot = 0,
  scrollTop = 0,
  timeout,
  scroll_sub_X = 0;
var token;
Page({
  data: {
    banner: [],
    navigation:[],
    ads: [],
    popup: [],
    imageSize: [],
    adshow: false,
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    current: 0,
    scrollview_h: app.winHeight(),
  },
  onLoad: function(options) {
    var that = this;
    this.getIndexData(navPage, 0);
  },
  onShow() {
    let that = this
    token = wx.getStorageSync('token')
    let cate_data = wx.getStorageSync('cate_data')
    if (!token) {
      // app.redirectTo("../../packageA/login/index");
    }
  },

  //获取页面数据
  getIndexData: function(nav) {
    var nav_id = nav;
    var that = this;
    app.dscRequest("index", {
      nav_id: nav,
    }).then((res) => {
      if (res.data.data != undefined) {
        console.log(res.data.data);
        if (!that.data.ads.length) {
          for (let index in res.data.data.adses) {
            if (res.data.data.adses[index].type == "slide") {
              that.data.banner = res.data.data.adses[index].ads;
            } else if (res.data.data.adses[index].type == "navigation") {
              that.data.navigation = res.data.data.adses[index].ads;
            } else{
              that.data.ads.push(res.data.data.adses[index]);
            }
          }
        }
        app.log(that.data.ads);
        that.setData({
          banner: that.data.banner,
          navigation: that.data.navigation,
          ads: that.data.ads
        });
      }
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
    app.dscRequest("index/loadmore", {
      goods_id: goods_id,
      cate_id: that.data.navList[curNav].goods_cate_id,
      curpage: that.data.navList[curNav].page,
    }).then((res) => {
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

  formSubmit: function(e) {
    var formID = e.detail.formId;

    app.addFormId(formID);

    var targetUrl = e.currentTarget.dataset.url;

    if (targetUrl != '') {
      if (targetUrl == '../../pages/category/index') {
        wx.switchTab({
          url: '' + targetUrl,
        })
      } else {
        wx.navigateTo({
          url: '' + targetUrl
        })
      }
    }
  },
  move: function() {
    return;
  },
  //分享
  onShareAppMessage: function() {
    return {
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/index/index'
    }
  },
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
      })
    }
  },
  //广告跳转
  adsNav:function(e){
    console.log(e);
  }
})