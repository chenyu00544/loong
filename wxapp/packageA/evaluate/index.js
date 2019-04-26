var app = getApp();
var order_id = 0;
Page({
  data: {
    order: [],
    stars: [],
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

  //添加评价图片
  addCommentImage:function(e){

  },

  onShareAppMessage: function() {

  }
})