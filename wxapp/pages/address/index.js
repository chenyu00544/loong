var app = getApp()
var buy_type = 0;
Page({
  data: {
    addressList: [],
    addressCount:0
  },
  onLoad: function (options) {
    this.getAddressList();
    buy_type = options.buy_type;
  },
  onShow: function () {
    this.getAddressList();
  },
  // 加载中
  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 1000)
  },
  // 下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  },
  getAddressList: function () {
    var that = this
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl('user/address/list'),
      method: 'POST',
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        if (res.data.code == 0) {
          that.setData({
            addressList: res.data.data,
            addressCount: res.data.data.length
          });
        }
      }
    })
    this.loadingChange();
  },
  // 添加收货地址
  createAddress: function () {
    wx.navigateTo({
      url: './create'
    });
    app.netReq("user/trajectory", { utype: 11, type_name: "添加地址" }, function (res) { });
  },
  //获取微信收货地址
  chooseAddress: function () {
    var that = this
    wx.chooseAddress({
      success: function (res) {
        var consignee = res.userName;
        var province = res.provinceName;
        var city = res.cityName;
        var area = res.countyName;
        var detailInfo = res.detailInfo;
        var mobile = res.telNumber;
        // var mobile = 18858788888;
        wx.request({
          url: app.apiUrl('region/mapping'),
          data: { province: province, city: city, area: area },
          method: 'POST',
          success: function (res) {
            var postdata = {
              consignee: consignee,
              province: res.data.data.province_id,
              city: res.data.data.city_id,
              district: res.data.data.area_id,
              address: detailInfo,
              mobile: mobile,
            }
            var token = wx.getStorageSync('token');

            wx.request({
              url: app.apiUrl('user/address/add'),
              method: 'post',
              header: {
                'X-ECTouch-Authorization': token
              },
              data: postdata,
              success: function (res) {
                console.log(res);
                wx.request({
                  url: app.apiUrl('user/address/choice'),
                  method: 'POST',
                  header: {
                    'X-ECTouch-Authorization': token
                  },
                  data: {
                    id: res.data.data
                  },
                  success: function () {
                    wx.showToast({
                      title: '设置成功',
                      success: function () {
                        var options = wx.getStorageSync('pageOptions')
                        that.onLoad(options);
                        setTimeout(function(){
                          if (options.from == 'flow') {
                            wx.navigateBack({
                              delta: 1
                            })
                          } else {
                            wx.reLaunch({
                              url: '../flow/checkout?buy_type=' + buy_type
                            })
                          }
                        },2000)
                      }
                    })
                  }
                })
              }
            })
          }
        })
      },
      fail: function (res) {
        if (res.errMsg === 'chooseAddress:fail auth deny') {
          wx.openSetting({})
        }
      }
    });
    app.netReq("user/trajectory", { utype: 14, type_name: "获取微信地址" }, function (res) { });
  },
  // 编辑收货地址
  editAddress: function (e) {
    var that = this
    var address_index = e.currentTarget.dataset.address
    var address_id = that.data.addressList[address_index].id
    wx.navigateTo({
      url: "../address/detail?objectId=" + address_id
    });
    app.netReq("user/trajectory", { utype: 12, type_name: "编辑地址" }, function (res) { });
  },
  // 删除收货地址
  removeAddress: function (e) {
    var that = this
    var token = wx.getStorageSync('token')
    var address_id = e.currentTarget.dataset.address
    wx.showModal({
      title: '提示',
      content: '您确定要移除当前收货地址吗?',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: app.apiUrl('user/address/delete'),
            method: 'POST',
            header: {
              'X-ECTouch-Authorization': token
            },
            data: {
              id: address_id
            },
            success: function () {
              var options = wx.getStorageSync('pageOptions')
              that.onLoad(options);
            }
          })
        }
      }
    })
  },
  // 设置默认地址
  setDefault: function (e) {
    var that = this
    var address_id = e.detail.value
    var token = wx.getStorageSync('token')
    wx.request({
      url: app.apiUrl('user/address/choice'),
      method: 'POST',
      header: {
        'X-ECTouch-Authorization': token
      },
      data: {
        id: address_id
      },
      success: function () {
        wx.showToast({
          title: '设置成功',
          success: function () {
            var options = wx.getStorageSync('pageOptions')
            // var options_user = wx.getStorageSync('address')
            that.onLoad(options);
            if (options.from == 'flow') {
              wx.navigateBack({
                delta: 1
              })
            } else {
              wx.reLaunch({
                url: '../flow/checkout'
              })
            }
          }
        })
      }
    })
  },
})