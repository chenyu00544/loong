var app = getApp()
var order_id = 0,
  rec_id = 0,
  cause_desc = "";
Page({
  data: {
    returnOrder: [],
    return_cause: [],
    causeId: 0,
    attrId: [],
    causeindex: 0,
    upload_img: '../../images/upload_image.png',
    upload_imgs: [],
    num: 1,
  },

  onLoad: function(options) {
    var that = this;
    order_id = options.objectId;
    rec_id = options.rec_id;
    app.netReq('user/applyreturn', {
      id: order_id,
      rec_id: rec_id
    }, function(res) {
      for (var i in res.data.data.return_cause) {
        that.data.return_cause.push(res.data.data.return_cause[i].cause_name);
      }
      for (var i in res.data.data.attr) {
        var attr = [];
        for (var j in res.data.data.attr[i]) {
          if(j==0){
            attr[j] = true;
          }else{
            attr[j] = false;
          }
        }
        that.data.attrId[i] = attr;
      }
      that.setData({
        returnOrder: res.data.data,
        return_cause: that.data.return_cause,
        attrId: that.data.attrId,
      })
    })
  },

  selectCause: function(e) {
    this.setData({
      causeId: e.target.dataset.index,
    })
  },
  bindPickerChange: function(e) {
    this.setData({
      causeindex: e.detail.value
    })
  },
  uploadImg: function() {
    var that = this;
    wx.chooseImage({
      count: 3,
      sizeType: ["original", "compressed"],
      sourceType: ['album', 'camera'],
      success: function(res) {
        that.setData({
          upload_imgs: res.tempFilePaths,
        });
      },
    })
  },
  submitData: function() {
    var that = this;
    var cause = that.data.returnOrder.cause[that.data.causeId];
    var return_cause = that.data.returnOrder.return_cause[that.data.causeindex];
    if (!return_cause) {
      wx.showToast({
        icon: 'none',
        title: '请选择原因',
      });
      return;
    }
    if (cause_desc == "") {
      wx.showToast({
        icon: 'none',
        title: '请填写问题描述',
      });
      return;
    }
    var attr_id = '';
    var attr_value = '';
    for (var i in that.data.attrId) {
      for (var j in that.data.attrId[i]) {
        if (that.data.attrId[i][j] == true){
          attr_id += that.data.returnOrder.attr[i][j].goods_attr_id+',';
          attr_value += that.data.returnOrder.attr[i][j].attr_value + ',';
        }
      }
    }
    if (that.data.upload_imgs.length > 0) {
      var token = wx.getStorageSync('token');
      for (var i in that.data.upload_imgs) {
        wx.uploadFile({
          url: app.apiUrl('user/applyimg'),
          filePath: that.data.upload_imgs[i],
          name: 'file',
          formData: {
            'id': order_id,
            causeid: cause.val,
            causename: cause.name,
            return_causeid: return_cause.cause_id,
            return_causename: return_cause.cause_name,
            cause_desc: cause_desc,
            rec_id: rec_id,
            num: that.data.num,
            attr_id: attr_id,
            attr_value: attr_value,
          },
          method: 'POST',
          header: {
            "Content-Type": "multipart/form-data",
            'X-ECTouch-Authorization': token,
          },
          success: function(res) {
            var data = res.data
            wx.showToast({
              title: '已上传' + (parseInt(i) + 1) + '张',
            });
            if (i == that.data.upload_imgs.length-1){
              wx.navigateBack({
                delta: 2
              })
            }
          }
        });
      }
    } else {
      app.netReq('user/applyimg', {
        'id': order_id,
        causeid: cause.val,
        causename: cause.name,
        return_causeid: return_cause.cause_id,
        return_causename: return_cause.cause_name,
        cause_desc: cause_desc,
        rec_id: rec_id,
        num: that.data.num,
        attr_id: attr_id,
        attr_value: attr_value,
      }, function(res) {
        wx.navigateBack({
          delta: 2
        })
      })
    }
  },
  bindTextAreaBlur: function(e) {
    cause_desc = e.detail.value;
  },
  /*增加商品数量*/
  up: function() {
    var num = this.data.num;
    num++;
    if (num >= 99) {
      num = 99
    }
    if (num > this.data.returnOrder.goods_num_total) {
      num = this.data.returnOrder.goods_num_total;
    }
    this.setData({
      num: num
    })
  },
  /*减少商品数量*/
  down: function() {
    var num = this.data.num;
    num--;
    if (num <= 1) {
      num = 1
    }
    this.setData({
      num: num
    })
  },
  /*手动输入商品*/
  import: function(e) {
    var num = Math.floor(e.detail.value);
    if (num <= 1) {
      num = 1
    }
    if (num >= 999) {
      num = 999
    }
    if (num > this.data.returnOrder.goods_num_total) {
      num = this.data.returnOrder.goods_num_total;
    }
    this.setData({
      num: num
    })
  },
  selectAttr:function(e){
    var attr = e.currentTarget.dataset.attr;
    var index = e.currentTarget.dataset.index;
    for (var i in this.data.attrId) {
      if (attr == i){
        for (var j in this.data.attrId[i]) {
          this.data.attrId[i][j] = false;
        }
      }
    }
    console.log(this.data.attrId);
    this.data.attrId[attr][index] = true
    this.setData({
      attrId: this.data.attrId,
    });
  },
})