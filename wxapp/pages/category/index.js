var app = getApp()
var token
Page({
  data: {
    searchColor: 'rgba(0,0,0,0.4)',
    searchSize: '16',
    searchName: "搜索商品",
    hidden: false,
    curNav: 1,
    curIndex: 0,
    cateData: [],
  },
  onShow: function() {
    var that = this
    token = wx.getStorageSync('token');
    if (!token) {
      wx.clearStorageSync('cate_data');
      app.redirectTo("../../packageA/login/index");
    } else {
      var cateCont = wx.getStorageSync('cate_data');
      if (typeof cateCont == 'object') {
        that.setData({
          cateData: cateCont,
          curNav: cateCont[0].id
        })
      } else {
        app.vcvbRequest(("category/index"))
          .then((res) => {
            // wx.setStorageSync('cate_data', res.data.data);
            that.setData({
              cateData: res.data.data,
              curNav: res.data.data[0].id,
            })
          });
      }
    }
  },
  onLoad: function() {},
  //事件处理函数
  //获取相对应的索引
  selectNav(event) {
    let id = event.target.dataset.id,
      index = parseInt(event.target.dataset.index);
    this.setData({
      curNav: id,
      curIndex: index,
      scrollTop: 0
    })
  },
  //分享
  onShareAppMessage: function() {
    return {
      title: '全部分类',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/categroy/index'
    }
  }
})