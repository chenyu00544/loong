var app = getApp();
Page({
  data: {
    header_img:'https://www.missmall.com/data/css/bonus.jpg',
    bonus:[],
    bt_name: '立即领取',
  },

  onLoad: function (options) {
    var that = this;
    app.netReq('bonus/list', {}, function(data){
      that.setData({
        bonus: data.data.data,
      })
    })
  },

  onShareAppMessage: function () {
  
  },

  receiveBonus:function(e){
    var that = this;
    var bonus_id = e.currentTarget.dataset.type_id;
    var index = e.currentTarget.dataset.index;
    app.netReq('bonus/list', { bonus_id: bonus_id}, function (data) {
      if(data.data.data.bonus_id){
        that.data.bonus[index].rece = 1;
        that.setData({
          bonus: that.data.bonus
        })
      }
    })
  }
});