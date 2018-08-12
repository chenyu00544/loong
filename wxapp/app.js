var OPEN_ID = '' //储存获取到openid
// var SESSION_KEY = ''//储存获取到

App({
  onLaunch: function() {
    // 获取用户数据
    // this.getUserInfo();//默认注释掉，现在打开 点击收集用到openid
    this.wxLogin();
    this.clearState();
    this.checkToken();
  },

  onHide: function() {},

  //cyc-start
  wxLogin: function() {
    var that = this
    wx.login({
      success: function(result) {
        var code = result.code;
        that.SilentLogin(code)
      }
    })
  },

  SilentLogin: function(code) {
    if (code) {
      var that = this
      wx.request({
        url: that.apiUrl('user/login/silent'),
        method: 'POST',
        data: {
          code: code
        },
        success: function(res) {
          wx.setStorage({
            key: 'token',
            data: res.data.token,
          })
          wx.setStorage({
            key: 'openid',
            data: res.data.openid,
          })
          setTimeout(function() {
            that.checkToken();
          }, 2000);
        }
      })
    }
  },

  //检查token正确性
  checkToken: function() {
    if (wx.getStorageSync('token') == '' && wx.getStorageSync('token') == undefined) {
      this.wxLogin();
    } else {
      return;
    }
  },

  setUserInfo: function(Info) {
    var userInfo = Info.detail
    var that = this
    if (that.globalData.userInfo.nickName == '') {
      that.globalData.userInfo = userInfo.userInfo;
      wx.login({
        success: function(result) {
          var code = result.code;
          userInfo.userInfo.code;
          that.doLogin(code, userInfo);
        }
      })
    }
  },

  //cyc-end

  getUserInfo: function(cb) {
    var that = this
    if (that.globalData.userInfo) {
      typeof cb == "function" && cb(that.globalData.userInfo)
    } else {
      //调用登录接口
      wx.login({
        success: function(result) {
          var code = result.code;
          wx.getUserInfo({
            withCredentials: true,
            lang: "zh_CN",
            success: function(res) {
              res.userInfo.code = code;
              that.doLogin(code, res);
              that.globalData.userInfo = res.userInfo
              typeof cb == "function" && cb(that.globalData.userInfo)
            },
            fail: function() {
              wx.showModal({
                title: '温馨提示',
                content: '拒绝授权，将会影响购物流程和用户登录,请确定重新授权！',
                success: function(res) {
                  if (res.confirm) {
                    wx.getSetting({
                      success(res) {
                        if (!res.authSetting["scope.userInfo"]) {
                          wx.authorize({
                            scope: 'scope.userInfo',
                            fail() {
                              wx.openSetting({
                                success: function(res) {
                                  if (res.authSetting["scope.userInfo"]) {
                                    wx.getUserInfo({
                                      withCredentials: true,
                                      success: function(res) {
                                        that.doLogin(code, res);
                                        that.globalData.userInfo = res.userInfo
                                        typeof cb == "function" && cb(that.globalData.userInfo)
                                      },
                                    })
                                  } else {
                                    that.getUserInfo();
                                  }
                                }
                              })
                            }
                          })
                        }
                      }
                    })
                  } else if (res.cancel) {
                    that.getUserInfo();
                  }
                },
              })
            }
          })
        }
      })
    }
  },
  // 小程序用户登录
  doLogin: function(code, userinfo) {

    var that = this
    if (code) {
      // 发起网络请求
      wx.request({
        url: that.apiUrl('user/login'),
        method: 'POST',
        data: {
          userinfo: userinfo,
          code: code
        },
        success: function(res) {
          if (!wx.getStorageSync('token') && wx.getStorageSync('openid')) {
            wx.setStorage({
              key: 'token',
              data: JSON.parse(res.data.split('\n')[1]).token
            })
            wx.setStorage({
              key: 'openid',
              data: JSON.parse(res.data.split('\n')[1]).openid
            })
          }
        }
      })
    } else {
      console.log('获取用户登录态失败！' + res.errMsg)
    }
  },

  globalData: {
    userInfo: null,
  },

  myAdData: {
    isIndexAdShow: false,
    isGoodsInfoAdShow: false
  },
  // 设置服务端API
  apiUrl: function(api) {
    return 'https://www.missmall.com/mobile/public/api/wx/' + api;
    return 'https://www.miss.com/mobile/public/api/wx/' + api;
  },
  apiUrlMy: function(api) {
    return 'https://www.missmall.com/mobile/index.php?' + api;
  },

  getCurrentPageOption() {
    var pages = getCurrentPages() //获取加载的页面
    var currentPage = pages[pages.length - 1] //获取当前页面的对象
    // var url = currentPage.route    //当前页面url
    var option = currentPage.options;
    return option
  },

  addFormId: function (fromId) {
    if (fromId == 'the formId is a mock one' || wx.getStorageSync('openid') == '') {
      return;
    }
    var that = this;
    var token = wx.getStorageSync('token')
    console.log(fromId);
    console.log(wx.getStorageSync('openid'));
    wx.request({
      url: that.apiUrl('user/addlog'),
      data: {
        from_id: fromId,
        user_wxid: wx.getStorageSync('openid'),
      },

      method: 'POST',
      header: {
        'Content-Type': 'application/json',
        //"Content-Type": "application/x-www-form-urlencoded"  POST用这个
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        /* 
        //res.data
        wx.showModal({
          title: '提示',
          content: '链接成功',
          success: function (res) {
            if (res.confirm) {
              console.log('用户点击确定')
            }
          } 
        })
        console.log(res);
        */
      },
      fail: function(err) {
        /* 
        // fail   
        wx.showModal({
          title: '提示',
          content: "错误了",
          success: function (res) {
            if (res.confirm) {
              console.log('用户点击确定')
            }
          }
        });
        console.log("push err")
        console.log(err);
        */
      }
    })
  },

  addGzid: function(gzid, goodsId) {
    var that = this;
    var token = wx.getStorageSync('token')

    wx.request({
      url: that.apiUrl('user/addgzid'), //app.apiUrlMy('m=site&c=index&a=test'),  //app.apiUrl('user/logina'),//
      data: {
        gzid: gzid,
        goods_id: goodsId,
        user_wxid: wx.getStorageSync('openid')
      },

      method: 'POST',
      header: {
        'Content-Type': 'application/json',
        //"Content-Type": "application/x-www-form-urlencoded"  POST用这个
        'X-ECTouch-Authorization': token
      },
      success: function(res) {
        /* 
        //res.data
        wx.showModal({
          title: '提示',
          content: '链接成功',
          success: function (res) {
            if (res.confirm) {
              console.log('用户点击确定')
            }
          } 
        })
        console.log(res);
        */
      },
      fail: function(err) {
        /* 
        // fail   
        wx.showModal({
          title: '提示',
          content: "错误了",
          success: function (res) {
            if (res.confirm) {
              console.log('用户点击确定')
            }
          }
        });
        console.log("push err")
        console.log(err);
        */
      }
    })
  },

  shwomessage: function(msg, time = 1000, icon = 'warn') {
    wx.showToast({
      title: msg,
      icon: icon,
      duration: time
    })
  },
  redirectTo: function(url) {
    wx.navigateTo({
      url: url
    });
  },
  payOrder: function(order_id, openid, token, formId_data) {
    console.log(wx.getStorageSync('gzid'));
    console.log(wx.getStorageSync('gdt_vid'));
    var that = this
    wx.request({
      url: that.apiUrl('payment/pay'),
      data: {
        id: order_id,
        open_id: openid,
        code: 'order.pay',
        gzid: wx.getStorageSync('gzid'),
        gdt_vid: wx.getStorageSync('gdt_vid'),
        aid: wx.getStorageSync('aid'),
        mktag: wx.getStorageSync('mktag')
      },
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      method: "POST",
      success: function(res) {
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
          'success': function(payres) {
            if (payres.errMsg == 'requestPayment:ok') {
              //成功修改订单状态
              wx.request({
                url: that.apiUrl('payment/notify'),
                data: {
                  "id": order_id,
                  form_id: formId_data,
                  gzid: wx.getStorageSync('gzid'),
                  gdt_vid: wx.getStorageSync('gdt_vid'),
                  aid: wx.getStorageSync('aid'),
                  mktag: wx.getStorageSync('mktag'),
                  action_type: "COMPLETE_ORDER",
                },
                method: "post",
                header: {
                  'Content-Type': 'application/json',
                  'X-ECTouch-Authorization': token
                },
                success: function(res) {
                  if (res.data.data.code == 0) {
                    wx.showToast({
                      title: '支付成功',
                      duration: 2000
                    })
                    if (res.data.data.extension_code == 'team_buy') {
                      that.redirectTo('../group/wait?objectId=' + res.data.data.team_id + "&user_id=" + res.data.data.user_id)
                    } else if (res.data.data.extension_code == 'bargain_buy') {
                      that.redirectTo('../bargain/order/index?objectId=' + order_id)
                    } else {
                      that.redirectTo('../order/index?objectId=' + order_id)
                    }
                  } else {
                    wx.showToast({
                      title: '付款失败',
                      image: '../../images/failure.png',
                      duration: 2000
                    })
                  }
                }
              })
            }
          },
          'fail': function(payres) {
            wx.showToast({
              title: '支付失败',
              image: '../../images/failure.png',
              duration: 2000
            })
            that.redirectTo('../order/index?objectId=' + order_id);
            app.netReq("user/trajectory", {
              utype: 10,
              type_name: "取消微信支付"
            }, function(res) {});
          }
        })

      }
    })
  },

  //清除状态cyc
  clearState: function() {
    wx.setStorageSync('cash', '');
  },

  clearAds: function() {
    wx.setStorageSync('gdt_vid', '');
    wx.setStorageSync('gzid', '');
  },

  hideTabbar: function() {
    wx.hideTabBar({
      aniamtion: false
    })
  },

  //微信广告提交转化数据
  upWxAdsUserAction: function(token, data) {
    var that = this
    wx.request({
      url: that.apiUrl('ads/reports/add'),
      data: data,
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {

      }
    })
  },

  addMkTag: function(mk_id) {
    var that = this
    var token = wx.getStorageSync('token');
    var openid = wx.getStorageSync('openid');
    wx.request({
      url: that.apiUrl('user/addmktag'),
      data: {
        mk_id: mk_id,
        openid: openid
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {

      }
    })
  },
  adsAddClick: function() {
    var that = this
    var token = wx.getStorageSync('token');
    wx.request({
      url: that.apiUrl('user/addclick'),
      data: {
        gzid: wx.getStorageSync('gzid'),
        gdt_vid: wx.getStorageSync('gdt_vid'),
        goods_id: wx.getStorageSync('goodsId'),
      },
      method: "post",
      header: {
        'Content-Type': 'application/json',
        'X-ECTouch-Authorization': token
      },
      success: function(res) {

      }
    })
  },
  netReq: function(url, param, callback) {
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
      success: function(res) {
        wx.hideNavigationBarLoading();
        callback(res);
      }
    });
  },
  //窗口宽度
  winWidth: function() {
    return wx.getSystemInfoSync().windowWidth;
  },
  //窗口高度
  winHeight: function() {
    return wx.getSystemInfoSync().windowHeight;
  },
  //cyc
})