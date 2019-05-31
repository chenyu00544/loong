var app = getApp();
Page({
  data: {
    real: [],
    name: "",
    cardId: "",
    cardfront: "",
    cardback: "",
    cardfront_choose: '',
    cardback_choose: '',
    card_image: [],
  },

  onLoad: function(options) {
    this.getRealInfo();
    this.setData({
      isIphoneX: app.globalData.isIphoneX
    })
  },

  onShow: function() {

  },

  getRealInfo: function() {
    var that = this;
    app.vcvbRequest("user/real", {}).then((res) => {
      that.setData({
        real: res.data.data,
        name: res.data.data.real_name,
        cardId: res.data.data.self_num,
        cardfront: res.data.data.front_of_id_card,
        cardback: res.data.data.reverse_of_id_card,
      })
    });
  },

  //添加图片
  addImage: function(e) {
    var that = this;
    var index = e.currentTarget.dataset.index || 0;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function(res) {
        if (index == 'front') {
          that.data.cardfront = res.tempFilePaths[0];
        } else if (index == 'back') {
          that.data.cardback = res.tempFilePaths[0];
        }
        that.setData({
          cardfront_choose: that.data.cardfront,
          cardback_choose: that.data.cardback,
          cardfront: that.data.cardfront,
          cardback: that.data.cardback
        });
      },
    })
  },

  //上传图片
  uploadImage(url, type) {
    var that = this;
    wx.uploadFile({
      url: app.apiUrl("wx/user/real/up/img"),
      filePath: url,
      name: 'file',
      formData: {
        card_type: type
      },
      header: {
        'Content-Type': 'application/json',
        'vcvbuy-Authorization': wx.getStorageSync('token')
      },
      success(res) {

      }
    });
    return true;
  },

  seveReal: function() {
    var that = this;
    if (that.data.name != that.data.real.real_name || that.data.cardId != that.data.real.self_num || that.data.cardfront_choose != '' || that.data.cardback_choose != '') {
      app.vcvbRequest("user/real/set", {
        card_name: that.data.name,
        card_num: that.data.cardId,
      }).then((res) => {
        if (res.data.code == 0) {
          if (that.data.cardfront_choose != '') {
            that.uploadImage(that.data.cardfront_choose, 'front');
          } 
          if (that.data.cardback_choose != '') {
            that.uploadImage(that.data.cardback_choose, 'back');
          }
          wx.showToast({
            title: '保存成功',
            duration: 2000,
          })
          wx.setStorage({
            key: 'is_real',
            data: res.data.data.review_status
          });
          setTimeout(function(){
            wx.navigateBack({
              delta: 1
            })
          }, 3000);
        }
      })
    } else {
      wx.showToast({
        title: '没有修改项',
        image: '../../images/failure.png',
        duration: 2000,
      });
    }
  },
});