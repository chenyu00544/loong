// pages/index/test.js
Page({ 

  formSubmit: function (e) 
  {   
    this.setData
      ({
        adshow: false
      })

    var formID = e.detail.formId;

    console.log('formID：' + formID); 
 
   //ok  真机有值  模拟器是提示the formId is a mock one
   
  /*      
    
ed68e88ca4ae341f23fbd97d13924843

    wx.showToast({
      title: formID,
      icon: 'success',
      duration: 2000
    })
*/
    /**/
    wx.showModal({
    title: '提示',
    content: formID,
    success: function(res) {
      if (res.confirm) {
      console.log('用户点击确定')
      }
    }
    })
     

    //$("#test_id").html("122222223333");

    console.log('form发生了submit事件，携带数据为：', e.detail.value)
  },  
  formReset: function () { 
    console.log('form发生了reset事件')
  },
     
  /**
   * 页面的初始数据 
   */
  data: { 
    adshow:true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options)
   {
  
   // formSubmit();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  close_ad:function(){
    this.setData
    ({
        adshow: false
    })
  }
})