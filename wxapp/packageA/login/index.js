var app = getApp();
var token;
var userinfo;
var code;
var viewName;
var uid
Page({
  data: {
    loginView: true,
    hidden: true,
  },
  onLoad(options) {
    uid = options.objectId || '0';
  },
  onShow: function() {
    var that = this
    token = wx.getStorageSync('token')
  },

  //获取用户信息及登陆
  getUserInfo: function(e) {
    var that = this
    userinfo = e.detail
    if (userinfo.errMsg == 'getUserInfo:fail auth deny') {
      that.setData({
        loginView: false,
      })
    } else {
      that.setData({
        hidden: false,
      })
      setTimeout(() => {
        wx.navigateBack({
          delta: 1
        })
      }, 3000)
      //调用登录接口
      wx.login({
        success: function(result) {
          code = result.code;
          that.doLogin();
        },
      })

    }
  },

  //获取手机信息
  getPhoneNumber:function(e){

  },
  // 小程序用户登录
  doLogin: function() {
    var that = this
    userinfo.userInfo.uid = uid;
    if (code) {
      app.vcvbRequest(("login/index"), {
          userinfo: userinfo,
          code: code
        })
        .then((res) => {
          wx.setStorage({
            key: 'token',
            // data: JSON.parse(res.data.split('\n')[1]).token
            data: res.data.token
          })
          wx.setStorage({
            key: 'openid',
            // data: JSON.parse(res.data.split('\n')[1]).openid
            data: res.data.openid
          })
        })
    } else {
      console.log('获取用户登录态失败！' + res.errMsg)
    }
  },
  //关闭授权弹框
  userInfoBtn: function() {
    var that = this
    that.setData({
      loginView: 'hide'
    })
  },
})