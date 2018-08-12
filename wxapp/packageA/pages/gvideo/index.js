var app = getApp()
var goodsId = 0;
Page({
  data: {
    goods_Id: 0,
    goodsComment: [],
    goodsCont: [],
    goods_vedio_url: '',
  },
  onLoad: function(options) {
    var that = this;
    goodsId = options.objectId;
    this.setData({
      goods_Id: options.objectId,
    })
    app.netReq("goods/detail", {
      "id": goodsId
    }, function(res) {
      that.setData({
        goodsCont: res.data.data,
        goodsComment: res.data.data.goods_comment.slice(0, 10),
        goods_vedio_url: res.data.data.goods_info.goods_video,
      })
    })
  },
  onShareAppMessage:function(){
    return {
      title: '商品详情',
      desc: '小程序本身无需下载，无需注册，不占用手机内存，可以跨平台使用，响应迅速，体验接近原生App',
      path: '/pages/goods/index?objectId=' + goodsId
    }
  },
  back_nav:function(e){
    
    var formID = e.detail.formId;
    app.addFormId(formID);

    wx.navigateTo({
      url: '/pages/goods/index?objectId=' + goodsId,
    });
  }
})