var app = getApp();
var order_id = 0;
Page({
  data: {
    order: [],
    stars: [],
    comment_image: [],
    content: [],
    star_index: [1, 2, 3, 4, 5],
    starKey: [{
      id: 1,
      name: "goods_rank",
      value: 5
    }, {
      id: 2,
      name: "service_rank",
      value: 5
    }, {
      id: 3,
      name: "shipping_rank",
      value: 5
    }],
  },

  onLoad: function(options) {
    order_id = options.ObjectId;
    this.getOrder();
  },

  onShow: function() {

  },

  getOrder: function() {
    var that = this;
    app.vcvbRequest("order/get", {
      order_id: order_id
    }).then((res) => {
      if (res.data.code == 0) {
        that.setData({
          order: res.data.data.order[0]
        });
        that.getComment();
      }
    });
  },

  getComment: function() {
    var that = this;
    app.vcvbRequest("comment/label", {})
      .then((res) => {
        if (res.data.code == 0) {

          for (var i in res.data.data) {
            if (res.data.data[i].selected == undefined) {
              res.data.data[i].selected = 1;
            }
          }

          for (var i in that.data.order.order_goods) {
            var comment = JSON.parse(JSON.stringify(res.data.data));
            var starKey = JSON.parse(JSON.stringify(that.data.starKey));
            that.data.order.order_goods[i].comment = comment;
            that.data.order.order_goods[i].starKey = starKey;
          }
          that.setData({
            order: that.data.order
          });
        }
      });
  },

  //选择评价标签
  selectCommentLabel: function(e) {
    var that = this;
    var id = e.currentTarget.dataset.id || 0;
    var goods_index = e.currentTarget.dataset.goods_index || 0;
    for (var i in that.data.order.order_goods[goods_index].comment) {
      if (that.data.order.order_goods[goods_index].comment[i].id == id) {
        if (that.data.order.order_goods[goods_index].comment[i].selected == 1) {
          that.data.order.order_goods[goods_index].comment[i].selected = 0;
        } else {
          that.data.order.order_goods[goods_index].comment[i].selected = 1;
        }
      }
    }
    that.setData({
      order: that.data.order
    });
  },

  //设置评级星级
  selectStar: function(e) {
    var that = this;
    var id = e.currentTarget.dataset.id || 0;
    var goods_index = e.currentTarget.dataset.goods_index || 0;
    var star_value = e.currentTarget.dataset.star_value || 0;
    for (var i in that.data.order.order_goods[goods_index].starKey) {
      if (that.data.order.order_goods[goods_index].starKey[i].id == id) {
        that.data.order.order_goods[goods_index].starKey[i].value = star_value;
      }
    }
    that.setData({
      order: that.data.order
    });
  },

  //添加图片
  addCommentImage: function(e) {
    var that = this;
    var goods_index = e.currentTarget.dataset.goods_index || 0;
    wx.chooseImage({
      count: 3,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function(res) {
        that.data.comment_image[goods_index] = res.tempFilePaths;
        that.setData({
          comment_image: that.data.comment_image
        });
        app.log(res.tempFilePaths);
      },
    })
  },

  //描述输入
  textareaInput: function(e) {
    var that = this;
    var goods_index = e.currentTarget.dataset.goods_index || 0;
    var content = e.detail.value || "";
    that.data.content[goods_index] = content;
  },

  //保存
  saveComment: function() {
    var that = this;
    var comment = {};
    var label_ids = [];
    var goods_ids = [];
    for (var i in that.data.order.order_goods) {
      var _comment = {}
      var goods_id = that.data.order.order_goods[i].goods_id;
      goods_ids.push(goods_id);

      _comment.ru_id = that.data.order.order_goods[i].ru_id;
      _comment.rec_id = that.data.order.order_goods[i].rec_id;

      if (that.data.content[i] != undefined) {
        _comment.content = that.data.content[i];
      } else {
        _comment.content = "";
      }

      var label_ids = [];
      for (var j in that.data.order.order_goods[i].comment) {
        if (that.data.order.order_goods[i].comment[j].selected == 1) {
          label_ids.push(that.data.order.order_goods[i].comment[j].id);
        }
      }
      _comment.label_ids = label_ids.join(",");

      _comment.starKey = that.data.order.order_goods[i].starKey;

      comment[goods_id] = _comment;
    }

    app.vcvbRequest("comment/add", {
      comment: comment,
      order_id: that.data.order.order_id,
      goods_ids: goods_ids.join(","),
    }).then((res) => {
      if(res.data.code == 0){
        for (var i in res.data.data){
          var order_id = res.data.data[i].order_id;
          var rec_id = res.data.data[i].rec_id;
          var goods_id = res.data.data[i].id_value;
          var ct_id = res.data.data[i].comment_id;
          var bool = that.uploadImage(order_id, rec_id, goods_id, ct_id);
        }
        wx.navigateBack({
          delta: 1
        })
      }
    });
  },

  //上传图片
  uploadImage(order_id, rec_id, goods_id, ct_id) {
    var that =this;
    for (var i in that.data.order.order_goods){
      if (that.data.order.order_goods[i].goods_id == goods_id){
        var images = that.data.comment_image[i];
        for (var j in images){
          wx.uploadFile({
            url: app.apiUrl("wx/comment/up/img"),
            filePath: images[j],
            name: 'file',
            formData: {
              order_id: order_id,
              rec_id: rec_id,
              goods_id: goods_id,
              ct_id: ct_id
            },
            header: {
              'Content-Type': 'application/json',
              'vcvbuy-Authorization': wx.getStorageSync('token')
            },
            success(res) {
              
            }
          });
        }
      }
    }
    return true;
  },

  onShareAppMessage: function() {

  }
})