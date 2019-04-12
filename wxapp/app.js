// 配置服务器合法域名(只需写域名即可)
const host = 'https://www.vcvbuy.com/api/wx/';
App({
  onLaunch: function () {
    // 获取用户数据
    this.wxLogin();
  },
  apiUrl(api) { return host + api },
  webUrl(web) { return host + web },
  //异步请求
  vcvbRequest(url, data = {}, method = 'post') {
    let promise = new Promise((resolve, reject) => {
      wx.request({
        url: host + url,
        data: data,
        method: method,
        header: {
          'Content-Type': 'application/json',
          'vcvbuy-Authorization': wx.getStorageSync('token') // Json Web Token
        },
        success: (data) => {
          resolve(data);
        },
        fail: (data) => {
          reject(data);
        }
      })
    })
    return promise
  },
  shwomessage(msg, time = 1000, icon = 'warn') {
    wx.showToast({
      title: msg,
      icon: icon,
      duration: time
    })
  },
  redirectTo(url) {
    wx.navigateTo({
      url: url
    });
  },
  switchTo(url) {
    wx.switchTab({
      url: url,
    });
  },
  /*
  订单支付支付
  */
  payOrder(order_id, openid, token, formId_data) {
    var that = this
    that.vcvbRequest(("payment/pay"), {
      id: order_id,
      open_id: openid,
      code: 'order.pay',
    })
      .then((res) => {
        if (res.data.status_code == 500) {
          wx.showToast({
            title: '支付失败',
            image: '../../images/failure.png',
            duration: 2000
          })
          return
        }
        var wxpayinfo = res.data.data.wxpay
        if (wxpayinfo == '') {
          return
        }
        //发起支付
        wx.requestPayment({
          'timeStamp': wxpayinfo.timestamp,
          'nonceStr': wxpayinfo.nonce_str,
          'package': wxpayinfo.packages,
          'signType': 'MD5',
          'paySign': wxpayinfo.sign,
          'success': function (payres) {
            if (payres.errMsg == 'requestPayment:ok') {
              //成功修改订单状态
              that.vcvbRequest(("payment/notify"), {
                "id": order_id,
                form_id: formId_data
              })
                .then((res) => {
                  if (res.data.data.code == 0) {
                    wx.showToast({
                      title: '支付成功',
                      duration: 2000
                    })
                    if (res.data.data.extension_code == '') {
                      that.redirectTo('../order/index?objectId=' + order_id)
                    }
                    if (res.data.data.extension_code == 'team_buy') {
                      that.redirectTo('../group/wait?objectId=' + res.data.data.team_id + "&user_id=" + res.data.data.user_id)
                    }
                    if (res.data.data.extension_code == 'bargain_buy') {
                      that.redirectTo('../bargain/order/index?objectId=' + order_id)
                    }
                  } else {
                    wx.showToast({
                      title: '付款失败',
                      image: '../../images/failure.png',
                      duration: 2000
                    })
                  }
                })
            }
          },
          'fail': function (payres) {
            wx.showToast({
              title: '支付失败',
              image: '../../images/failure.png',
              duration: 2000
            })
            that.redirectTo('../order/index?objectId=' + order_id)

          }
        })

      })
  },
  //地区接口
  region() {
    var that = this
    var areaInfo = [];
    that.vcvbRequest(("region/list"), {
      id: 1
    })
      .then((res) => {
        var province = res.data.data //数组
        var provinceName = [] //存放循环出数组中的值
        var provinceId = []
        for (var i = 0; i < province.length; i++) {
          provinceName = province[i].region_name;
          provinceId = province[i].region_id
          var provinceList = {
            'province_id': provinceId,
            'city_id': 0,
            'county_id': 0,
            'region_name': provinceName,
            'region_id': provinceId
          }

          areaInfo.push(provinceList)
          //取出所有市的数据
          var city = province[i].region;
          var cityName = [],
            city_id
          for (var j = 0; j < city.length; j++) {
            cityName = city[j].region_name;
            city_id = city[j].region_id
            var cityList = {
              'province_id': provinceId,
              'city_id': j + 1,
              'county_id': 0,
              'region_name': cityName,
              'region_id': city_id
            }
            areaInfo.push(cityList)
            var countyName = [],
              county_id
            var county = city[j].region
            for (var v = 0; v < county.length; v++) {
              countyName = county[v].region_name; //取出所有区
              county_id = county[v].region_id;
              var countyList = {
                'province_id': provinceId,
                'city_id': j + 1,
                'county_id': v + 1,
                'region_name': countyName,
                'region_id': county_id
              }
              areaInfo.push(countyList)
            }
          }
        }
        wx.setStorageSync('region', areaInfo)
      })
  },
  //获取媒体型号
  globalData: {
    isIphoneX: false,
    userInfo: null
  },
  onShow: function () {
    let that = this;
    wx.getSystemInfo({
      success: res => {
        let modelmes = res.model;
        if (modelmes.search('iPhone X') != -1) {
          that.globalData.isIphoneX = true
        }

      }
    })

  },
  netReq: function (url, param, callback) {
    wx.showNavigationBarLoading();
    setTimeout(() => {
      wx.hideNavigationBarLoading();
    }, 3000)
    var that = this
    var token = wx.getStorageSync('token');
    wx.request({
      url: that.apiUrl(url),
      data: param,
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function (res) {
        wx.hideNavigationBarLoading();
        callback(res);
      }
    });
  },
  //窗口宽度
  winWidth: function () {
    return wx.getSystemInfoSync().windowWidth;
  },
  //窗口高度
  winHeight: function () {
    return wx.getSystemInfoSync().windowHeight;
  },
  addFormId: function (fromId) {
    console.log(fromId);
    console.log(wx.getStorageSync('openid'));
    if (fromId == 'the formId is a mock one' || wx.getStorageSync('openid') == '') {
      return;
    }
    var that = this;
    var token = wx.getStorageSync('token')
    this.vcvbRequest("index_ext/addformid", {
      from_id: fromId,
      user_wxid: wx.getStorageSync('openid'),
    }).then((res) => { });
  },
  wxLogin: function () {
    var that = this
    wx.login({
      success: function (result) {
        var code = result.code;
        that.vcvbRequest("login/silent", {
          code: code,
        }).then((res) => {
          wx.setStorage({
            key: 'openid',
            // data: JSON.parse(res.data.split('\n')[1]).openid
            data: res.data.data.openid
          });
          wx.setStorage({
            key: 'token',
            // data: JSON.parse(res.data.split('\n')[1]).token
            data: res.data.data.token
          });
        });
      }
    })
  },
  log:function(e){
    console.log(e);
  },
  showLoading:function(){
    wx.showLoading({
      title: 'Loading...',
    })
    setTimeout(function () {
      wx.hideLoading()
    }, 3000)
  },
  hideLoading: function (title) {
    wx.hideLoading();
  }
})
