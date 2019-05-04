var app = getApp();
var page = 1;
Page({
  data: {
    is_loading: false,
    list: [],
  },
  onLoad: function() {
    var that = this;
    page = 1;
    wx.showLoading({
      title: 'loading...',
    })
    that.getBrowseList();
  },

  getBrowseList: function() {
    var that = this
    app.vcvbRequest("collect/browse", {
      page: page
    }).then((res) => {
      wx.hideLoading();
      that.setData({
        list: res.data.data
      })
    });
  },

  loadMore: function() {
    page++;
    var that = this
    that.setData({
      is_loading: true
    });
    app.vcvbRequest("collect/browse", {
      page: page
    }).then((res) => {
      for (var i in res.data.data) {
        if (that.data.list[that.data.list.length - 1].group == res.data.data[i].group) {
          for (var j in res.data.data[i].browse) {
            that.data.list[that.data.list.length - 1].browse.push(res.data.data[i].browse[j]);
          }
        }else{
          if (res.data.data[i].browse != undefined && res.data.data[i].browse.length > 0){
            that.data.list.push(res.data.data[i]);
          }
        }
      }
      that.setData({
        list: that.data.list,
        is_loading: false
      });
    })
  },

  //下拉刷新完后关闭
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
  onReachBottom: function() {
    this.loadMore();
  }
})