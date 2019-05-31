//获取应用实例
var app = getApp();
var openid;
var size = 30;
var page = 1;
var type = "0";
Page({
    data: {
        current: "0",
        orders: [],
    },

    orderStatus: function (that, id) {
        app.showLoading();
        app.vcvbRequest("team/orders", {
            size: size,
            page: page,
            type: type
        }).then((res) => {
            app.hideLoading();
            that.setData({
                orders: res.data.data,
            })
        });
    },

    onLoad: function (e) {
        var that = this
        that.data.current = e.id || 0;
        this.setData({
            current: that.data.current
        })
        this.orderStatus(this, that.data.current);
    },

    /*订单导航内容切换*/
    bindHeaderTap: function (event) {
        this.setData({
            current: event.target.dataset.index,
        });
        type = event.target.dataset.index;
        this.orderStatus(this, event.target.dataset.index);
    },

    siteDetail: function (e) {
        var that = this;
        //获取点击的id值
        var index = e.currentTarget.dataset.index;
        // console.log(e)
        var orderId = that.data.orders[index].order_id;
        wx.navigateTo({
            url: "../../order/detail?objectId=" + orderId
        });
    },

    /* 拼团进度 */
    teamWait: function (e) {
        var that = this;
        var index = e.currentTarget.dataset.index;
        var team_id = that.data.orders[index].team_id;
        var user_id = that.data.orders[index].user_id;
        wx.navigateTo({
            url: '../../group/wait?objectId=' + team_id + "&user_id=" + user_id,
        });
    },

    //取消订单
    cancel_order: function (e) {
        var that = this;
        var error_msg = '';
        wx.showModal({
            title: '提示',
            content: '确认取消订单？',
            success: function (res) {
                if (res.confirm) {
                    app.vcvbRequest("user/order/cancel", {
                        id: e.currentTarget.dataset.id
                    }).then((res) => {
                        if (res.data.code > 0) {
                            error_msg = '取消失败';

                        } else if (res.data.code == 0) {
                            error_msg = '取消成功';
                            that.orderStatus(that, that.data.current);
                        }
                        wx.showToast({
                            title: error_msg,
                            icon: 'warn',
                            duration: 500
                        })
                    });
                }
            }
        })
    },

    //确认收货
    confirm_order: function (e) {
        var that = this;
        wx.showModal({
            title: '提示',
            content: '确认收到商品？',
            success: function (res) {
                if (res.confirm) {
                    app.vcvbRequest("user/order/confirm", {
                        id: e.currentTarget.dataset.id
                    }).then((res) => {
                        if (res.data.code == 0) {
                            wx.showToast({
                                title: res.data.msg,
                                duration: 2000
                            })
                            that.orderStatus(that, that.data.current);
                            that.redirectTo('../user/inedx')
                        } else {
                            wx.showToast({
                                title: res.data.msg,
                                image: '../../images/failure.png',
                                duration: 2000
                            })
                        }
                    });
                }
            }
        })
    },
    //下拉刷新完后关闭
    onPullDownRefresh: function () {
        wx.stopPullDownRefresh()
    },
})








