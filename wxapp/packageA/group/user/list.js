var app = getApp()
var size = 30;
var page = 1;
var team_id
Page({
  data: {
    hiddenNo: false,
    hiddenHas: true,
    hiddenEnd: true,
  },
  onLoad: function (options) {
    var that = this
    team_id = options.objectId;
    that.userList();
  },
  userList: function () {
    var that = this
    app.vcvbRequest("team/user",{
      size: size,
      page: page,
      team_id: team_id   
    }).then((res)=>{
      that.setData({
        listsData: res.data.data
      });
    });
  },
})