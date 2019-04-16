var app = getApp();
var areaInfo = []; //所有省市区县数据
var country = 1;  //国家
var provinces = []; //省
var citys = []; //城市
var countys = []; //区县
var index = [0, 0, 0];
var t = 0;
var show = false;
var moveY = 200;
var changeValue, changeProvince, changeCity, changeCounty, changeProvince_id, changeCity_id, changeCounty_id, checkout
Page({
  data: {
    show: show,
    province: provinces,
    city: citys,
    county: countys,
    province_id: provinces,
    city_id: citys,
    county_id: countys,
    value: [0, 0, 0]
  },
  onLoad: function(options) {
    var that = this
    var region = wx.getStorageSync("region");
    if (region != undefined && region.length > 0) {
      //初始化信息
      provinces = region;
      citys = provinces[0].city;
      countys = citys[0].district;
      that.setData({
        provinces: provinces,
        citys: citys,
        countys: countys
      });
    } else {
      app.region();
    }
    //初始化动画
    that.animation = wx.createAnimation({
      transformOrigin: "50% 50%",
      duration: 0,
      timingFunction: "ease",
      delay: 0
    })
    that.animation.translateY(200 + 'vh').step();
    that.setData({
      animation: that.animation.export(),
      show: show
    })
  },
  //地区滑动选择
  bindChange: function(e) {
    var val = e.detail.value //[0,0,0]省市区的下标
    app.log(val);
    if (index[0] != val[0]) {//省份做滑动,定位市/区县第一位
      val[1] = 0;
      val[2] = 0;
      setCity(val[0], this); //获取地级市数据
    } else { //市做了滑动,定位区县第一位
      if (index[1] != val[1]) {
        val[2] = 0;
        setCounty(val[0], val[1], this); //获取区县数据
      }
    }
    index = val;
    //存储滑动后的数据
    changeValue = [val[0], val[1], val[2]];
    changeProvince = provinces[val[0]].region_name;
    changeCity = citys[val[1]].region_name;
    changeCounty = countys[val[2]].region_name;
    changeProvince_id = provinces[val[0]].region_id;
    changeCity_id = citys[val[1]].region_id;
    changeCounty_id = countys[val[2]].region_id;
    this.setData({
      value: index
    });
  },
  //确定
  checkFloatView(e) {
    var that = this
    moveY = 200;
    show = true;
    t = 0;
    animationEvents(this, moveY, show);
    this.setData({
      value: changeValue,
      province: (changeProvince == undefined ? '' : changeProvince),
      city: (changeCity == undefined ? '' : changeCity),
      county: (changeCounty == undefined ? '' : changeCounty),
      province_id: (changeProvince_id == undefined ? '0' : changeProvince_id),
      city_id: (changeCity_id == undefined ? '0' : changeCity_id),
      county_id: (changeCounty_id == undefined ? '0' : changeCounty_id),
      showViewMol: !that.data.showViewMol,
    })
  },
  //隐藏弹窗浮层
  hiddenFloatView(e) {
    var that = this
    moveY = 200;
    show = true;
    t = 0;
    animationEvents(this, moveY, show);
    that.setData({
      showViewMol: !that.data.showViewMol,
    })
  },
  //地区选择
  translate: function(e) {
    var that = this
    if (t == 0) {
      moveY = 0;
      show = false;
      t = 1;
    } else {
      moveY = 200;
      show = true;
      t = 0;
    }
    animationEvents(this, moveY, show);
    //初始化数据
    changeValue = [0, 0, 0]
    changeProvince = provinces[0].region_name
    changeCity = citys[0].region_name
    changeCounty = countys[0].region_name
    changeProvince_id = provinces[0].region_id
    changeCity_id = citys[0].region_id
    changeCounty_id = countys[0].region_id
    that.setData({
      showViewMol: !that.data.showViewMol,
    })
  },
  //信息提交
  saveData: function(e) {
    var that = this
    var data = e.detail.value;
    if (!that.checkAddress(data)){
      return;
    }
    var postdata = {
      consignee: data.consignee,
      country: country,
      province: that.data.province_id,
      city: that.data.city_id,
      district: that.data.county_id,
      phone: data.mobile,
      address: data.address
    }
    app.vcvbRequest(("user/address/add"), postdata)
    .then((res)=>{
      wx.showToast({
        title: '保存成功',
        duration: 2000,
        success: function () {
          wx.navigateBack({
            delta: 1
          })
        }
      })
    });
  },
  //清空表单
  formReset: function() {
    this.setData({
      value: '',
      province: '',
      city: '',
      county: '',
      province_id: '',
      city_id: '',
      county_id: ''
    })
  },
  //下拉刷新完后关闭
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },
  //检查填写的地址是否符合要求
  checkAddress:function(data){
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
    if (!myreg.test(data.mobile)) {
      wx.showToast({
        title: '手机号不符合要求',
        image: '../../images/failure.png',
        duration: 1500
      })
      return false;
    }
    if (data.consignee == '') {
      wx.showToast({
        title: '收件人不能为空',
        image: '../../images/failure.png',
        duration: 2000,
      })
      return false;
    }
    if (data.mobile.length == 0) {
      wx.showToast({
        title: '手机号不能为空',
        image: '../../images/failure.png',
        duration: 2000,
      })
      return false;
    }
    if (data.mobile.length != 11) {
      wx.showToast({
        title: '手机号长度有误',
        image: '../../images/failure.png',
        duration: 1500
      })
      return false;
    }

    if (this.data.province == '' && this.data.city == '' && this.data.county == '') {
      wx.showToast({
        title: '省市区不能空',
        image: '../../images/failure.png',
        duration: 2000,
      })
      return false;
    }
    if (data.address == '') {
      wx.showToast({
        title: '详细地址不能为空',
        image: '../../images/failure.png',
        duration: 2000,
      })
      return false;
    }
    return true;
  },
})
//动画事件
function animationEvents(that, moveY, show) {
  // console.log("moveY:" + moveY + "\nshow:" + show);
  that.animation = wx.createAnimation({
    transformOrigin: "50% 50%",
    duration: 400,
    timingFunction: "ease",
    delay: 0
  })
  that.animation.translateY(moveY + 'vh').step()

  that.setData({
    animation: that.animation.export(),
    show: show
  })

}

// 获取地级市数据
function setCity(pro_num, that) {
  citys = provinces[pro_num].city;
  countys = citys[0].district;
  that.setData({
    citys: citys,
    countys: countys
  });
}

// 获取区县数据
function setCounty(pro_num, city_num, that) {
  countys = provinces[pro_num].city[city_num].district;
  that.setData({
    countys: countys
  });
}