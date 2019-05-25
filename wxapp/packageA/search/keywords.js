var app = getApp();
var keywords = [];
var search_content = '';
Page({
  data: {
    history_key: [],
    hot_key: [],
    hot_cate: []
  },

  onLoad: function() {
    keywords = [];
  },

  onShow: function() {
    this.getHistorySearch();
    this.getHotKeyWords();
  },

  getHistorySearch: function() {
    var key_words = [];
    var recent = wx.getStorageSync('historyKeyword');
    var history_arr = recent.split(',');
    var history_search = [];
    for (var i = 0; i < history_arr.length; i++) {
      if (history_arr[i] != '') {
        var history = history_arr[i].split('_');
        keywords[history[0]] = history[1];
        if (i <= 10) {
          key_words.push(history[0]);
        }
      }
    }
    this.setData({
      history_key: key_words
    });
  },

  getHotKeyWords: function() {
    var that = this;
    app.vcvbRequest("search/keywords").then((res) => {
      if (res.data.code == 0) {
        that.setData({
          hot_key: res.data.data.keyword,
          hot_cate: res.data.data.cate,
        });
      }
    });
  },

  onPullDownRefresh: function() {
    wx.stopPullDownRefresh()
  },

  getSearchContent: function(e) {
    if (e.detail.value != "") {
      search_content = e.detail.value
    }
  },

  search: function() {
    if (keywords[search_content] > 0) {
      keywords[search_content] = parseInt(keywords[search_content]) + 1;
    } else {
      keywords[search_content] = 1;
    }
    this.sortKeywords();
    app.redirectTo('../search/index?keyword=' + search_content);
  },

  goSearch: function(e) {
    if (e.currentTarget.dataset.text != undefined && e.currentTarget.dataset.text != "") {
      app.log(e.currentTarget.dataset.text);
      var search_key = e.currentTarget.dataset.text;
      if (keywords[search_key] > 0) {
        keywords[search_key] = parseInt(keywords[search_key]) + 1;
      } else {
        keywords[search_key] = 1;
      }
      this.sortKeywords();
      app.redirectTo('../search/index?keyword=' + search_key);
    }

    if (e.currentTarget.dataset.cate_id != undefined && e.currentTarget.dataset.cate_id > 0) {
      var cate_id = e.currentTarget.dataset.cate_id;
      app.log(e.currentTarget.dataset.cate_id);
      app.redirectTo('../search/index?cate_id=' + cate_id);
    }

  },

  clearSearch: function() {
    wx.setStorageSync("historyKeyword", "");
    keywords = [];
    this.getHistorySearch()
  },

  sortKeywords: function() {
    var key_arr = [];
    var key_sort = [];
    for (var i in keywords) {
      key_sort.push(parseInt(keywords[i]));
    }
    key_sort.sort(function(a, b) {
      return b - a;
    });

    for (var i in key_sort) {
      for (var j in keywords) {
        if (key_sort[i] == parseInt(keywords[j])) {
          if (j != "") {
            key_arr.push(j + "_" + keywords[j]);
          }
        }
      }
    }
    var temp = app.remove_duplication(key_arr);
    wx.setStorageSync("historyKeyword", temp.join(","));
  },
})