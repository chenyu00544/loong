//获取应用实例
var app = getApp();
var QQMapWX = require('../../utils/qqmap-wx-jssdk.min.js')
var qqmap = new QQMapWX({ key: "XSYBZ-P2G34-3K7UB-XPFZS-TBGHT-CXB4U" })

var dialog_ad_url_g = '';

var token
Page({
  data: {
    adshow: false,

    dialog_ad_url: '',
    dialog_ad_image: '',


    iphoneView: true,
    hidden: false,
    

   
  },
  onLoad: function () {
    // app.getOpenIdTap(); //测试
    var gzid = app.getCurrentPageOption().gzid;

    if (gzid != undefined) {
      console.log("写入广告记录");
      ////写入广告记录。  
      app.addGzid(gzid, 0);
    }


    var that = this;

    // console.log("11");

  
    // 获取用户数据
    app.getUserInfo(function (userInfo) {
      that.setData({
        userInfo: userInfo
      })
    })
    // 生命周期函数--监听页面加载
    var token = wx.getStorageSync('token')

    //console.log("miansha::"+token);
  
    //console.log(app.apiUrl('index'));
    wx.request
      ({
        url: app.apiUrl("team/miaosha"), 
        
        method: "post",
        header: { 
          'Content-Type': 'application/json', 
          'X-ECTouch-Authorization': token
        }, 
        success: function (res) 
        {
          var isOk = res.data.data.ok;

          if (isOk == '0')
          {
            //直接跳转。
            wx.navigateTo({
              url: "index"
            });
          }

          var isHasdialogAd = res.data.data.is_dialog_ad;
          var isadshow = false;

          var dialog_ad_url_t = '';
          var dialog_ad_image_t = '';

          if (isHasdialogAd == '1') {
            console.log("存在首页窗口广告");
            isadshow = true;

            dialog_ad_url_g = res.data.data.dialog_ad.ext_url;
            console.log("存在首页窗口广告::" + dialog_ad_url_g);

            dialog_ad_url_t = res.data.data.dialog_ad.ext_url;
            dialog_ad_image_t = res.data.data.dialog_ad.ad_image;

          }
          else {
            // console.log("不存在首页窗口广告"); 
          }


          if (isOk == '1')
          {
            that.setData
              ({
                indexdata: res.data.data,
                adshow: isadshow,
                dialog_ad_url: dialog_ad_url_t,
                dialog_ad_image: dialog_ad_image_t

              })
          }
          
        },
         fail: function (res) 
         {
           wx.navigateTo({
             url: "index"
           });
        }
      })

    //加载中
    this.loadingChange();
   // that.getLocation();
    //that.onChangeShowIphone();
  },

  loadingChange() {
    setTimeout(() => {
      this.setData({
        hidden: true
      })
    }, 1000)
  },

  //点数收集
  formSubmitAD: function (e) {
    this.setData
      ({
        adshow: false
      })

    var formID = e.detail.formId;


    console.log("index 中::" + wx.getStorageSync('openid'));

    app.addFormId(formID);


  }
  ,
  formSubmitAD2: function (e) {
    this.setData
      ({
        adshow: false
      })

    var formID = e.detail.formId;


    console.log("index2222 中::" + wx.getStorageSync('openid'));

    app.addFormId(formID);

    //
    this.dialog_ad_click();

  }, 
  //dialog ad
  dialog_ad_click: function (e) {
    console.log("点击了窗口广告");

    if (dialog_ad_url_g != '') {
      wx.navigateTo
        ({
          url: '../../' + dialog_ad_url_g
        })
    }

  },
  siteDetail: function (e) {
    var that = this
    var index = e.currentTarget.dataset.index;//当前屏中商品的位置。

    var goodsId = that.data.indexdata.goods_list[index].goods_id;   //that.data.index.goods_list[index].goods_id;

   // console.log(goodsId);

    app.getUserInfo(function (userInfo) {
      that.setData({
        userInfo: userInfo
      })
    })
    wx.navigateTo({
      url: "../group/goods?objectId=" + goodsId
    });

  },
  myCatchTouch:function () {
    return;
  }



})

