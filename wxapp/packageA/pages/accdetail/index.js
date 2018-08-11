var app = getApp();
var page = 1;
var url = '';
Page({
  data: {
    accounts: [],
    loadmore: true,
    is_more: true,
  },
  onLoad: function(options) {
    if (options.objectId == 'account') {
      url = 'user/account/detail'
    } else {
      url = 'user/account/log'
    }
  },
  onShow: function() {
    page = 0;
    this.getAccountInfo();
  },
  onReachBottom: function() {
    var that = this;
    that.setData({
      loadmore: true,
    });
    page += 1;
    app.netReq(
      url, {
        page: page,
        size: 10
      },
      function(res) {
        var is_m = true;
        if (!res.data.data) {
          is_m = false;
        }
        that.setData({
          accounts: that.data.accounts.concat(res.data.data),
          loadmore: false,
          is_more: is_m
        })
      }
    )
  },
  getAccountInfo: function() {
    var that = this;
    app.netReq(
      url, {
        page: page,
        size: 10
      },
      function(res) {
        that.setData({
          accounts: res.data.data,
          loadmore: false,
        })
      }
    )
  },
})