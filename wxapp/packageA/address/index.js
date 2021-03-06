var app = getApp()
var WeChatList;
var userName, provinceName, cityName, countyName, telNumber, detailInfo
var fromPage = "";
Page({
  data: {
    addressList: [],
    is_first_action: true,
  },
  onLoad: function(options) {
    fromPage = options.objectId;
    this.setData({
      isIphoneX: app.globalData.isIphoneX
    });
  },
  onShow: function() {
    this.getAddressList();
    this.setData({
      is_first_action: true,
    })
  },
  // 下拉刷新
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
  getAddressList: function() {
    var that = this;
    app.vcvbRequest(("user/addresses"))
      .then((res) => {
        var addressList = res.data.data
        for (var i in res.data.data) {
          WeChatList = {
            userName: addressList[i].consignee,
            provinceName: addressList[i].province_name,
            cityName: addressList[i].city_name,
            countyName: addressList[i].district_name,
            telNumber: addressList[i].mobile,
            detailInfo: addressList[i].address
          }
        }
        if (res.data.code == 0) {
          that.setData({
            addressList: res.data.data
          });
        }
      });
  },
  // 添加收货地址
  createAddress: function() {
    wx.navigateTo({
      url: './create'
    })
  },
  // 编辑收货地址
  editAddress: function(e) {
    var that = this;
    var address_index = e.currentTarget.dataset.address;
    var address_id = that.data.addressList[address_index].address_id;
    wx.navigateTo({
      url: "../address/detail?objectId=" + address_id
    })
  },
  // 删除收货地址
  removeAddress: function(e) {
    var that = this
    var address_id = e.currentTarget.dataset.address;
    wx.showModal({
      title: '提示',
      content: '您确定要移除当前收货地址吗?',
      success: function(res) {
        if (res.confirm) {
          app.vcvbRequest(("user/address/del"), {
            address_id: address_id
          }).then((res) => {
            // var options = wx.getStorageSync('pageOptions')
            // that.onLoad(options);
            that.onShow();
          });
        }
      }
    })
  },
  // 设置默认地址
  setDefault: function(e) {
    var that = this;
    var address_id = e.detail.value;
    app.vcvbRequest(("user/address/setdef"), {
      address_id: address_id
    }).then((res) => {
      wx.showToast({
        title: '设置成功',
        success: function() {
          if (fromPage != "user_center"){
            wx.navigateBack({
              delta: 1
            });
          }else{
            that.onShow();
          }
        }
      })
    });
  },
});
