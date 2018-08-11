var app = getApp()
Page({
  data: {
    searchColor: 'rgba(0,0,0,0.4)',
    searchSize: '16',
    searchName: "输入搜索的商品名称",
    hidden: false,
    curNav: 1,
    curIndex: 0,
    cateData: [],
  },
  onLoad: function () 
  {
    var gzid = app.getCurrentPageOption().gzid;
    if (gzid != undefined) {
      console.log("写入广告记录");
      ////写入广告记录。  
      app.addGzid(gzid, 0);
    }
    
    var that = this;

    //调用应用实例的方法获取全局数据
    // 获取缓存数据
    var cateCont = wx.getStorageSync('cate_data');
    if (cateCont)
     {

      console.log("分类缓存");

      that.setData({
         cateData: cateCont,
      })
    } else { 
      wx.request({ 
        url: app.apiUrl("category"),
        method: "post",
        header: {
          'Content-Type': 'application/json'
        },
        success: function (res) 
        {
          console.log("分类网络加载");

          console.log(res);

          wx.setStorageSync('cate_data', res.data.data);
          that.setData({
            cateData: res.data.data,
            curNav: res.data.data[0].id,
          })
        }
      })
    }
  },
  onShow: function () {
  },
  //事件处理函数
  //获取相对应的索引
  selectNav(event) {
    let id = event.target.dataset.id,
      index = parseInt(event.target.dataset.index);
    self = this;
    this.setData({
      curNav: id,
      curIndex: index,
      scrollTop: 0
    });
    app.netReq("user/trajectory", { utype: 7, type_name: "一级分类"}, function(res){});
  },
  // 搜索链接
  bindSearchTap: function () {
    // wx.navigateTo({
    //   url: '../../pages/search/index'
    // })
    wx.switchTab({
      url: '../../pages/search/index',
    })
  },
  //下拉刷新完后关闭
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  },
  //分享
  onShareAppMessage: function () {
    return {
      title: '全部分类',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/categroy/index'
    }
  },
})




