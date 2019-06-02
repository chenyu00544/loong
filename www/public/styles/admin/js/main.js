    $(function () {
        //顶部导航切换切换
        $('.admin-menu li').click(function () {
            var src = $(this).find('a').attr('href');
            $('#main').attr('src', src);
            var nav_param = $(this).data('param');
            $.cookie('nav', nav_param);
            $.cookie('subnav', 'sub_' + nav_param);
            $.cookie('subnav_index', 0);
            $.cookie('ssubnav_index', 0);

            $(this).parent().find('li').removeClass('active');
            $(this).addClass('active');
            $('.sub-nav').hide();
            var sub_nav = $('.sub_' + $(this).data('param'));

            sub_nav.show();
            sub_nav.find('.item').removeClass('current');
            sub_nav.find('.item').first().addClass('current');
            sub_nav.find('.item').find('.sub-item').hide();
            sub_nav.find('.item').first().children('.sub-item').show();
            sub_nav.find('.item').children('.sub-item').find('li').removeClass('curr');
            sub_nav.find('.item').children('.sub-item').find('li').first().addClass('curr');
            $('#main').attr('src', $(this).find('a').attr('href'));
        });

        //左边导航切换
        $('.item .title').click(function () {
            $.cookie('subnav', $(this).parent().parent().data('param'));
            $.cookie('subnav_index', $(this).parent().data('param'));
            $.cookie('ssubnav_index', 0);
            $(this).parent().parent().children('.item').removeClass('current');
            $(this).parent().addClass('current');
            $(this).parent().parent().children('.item').children('.sub-item').hide();
            $(this).parent().children('.sub-item').show();
            $(this).parent().children('.sub-item').css('top', 50 - $(this).offset().top);
            $(this).parent().children('.sub-item').find('li').removeClass('curr');
            $(this).parent().children('.sub-item').find('li').first().addClass('curr');
            $('#main').attr('src', $(this).find('a').attr('href'));
        });

        //左边子导航切换
        $('.sub-item li').click(function () {
            $(this).parent().find('li').removeClass('curr');
            $(this).addClass('curr');
            $.cookie('ssubnav_index', $(this).data('param'));
            $('#main').attr('src', $(this).find('a').attr('href'));
        });

        $('#notice').parent().mouseenter(function () {
            $('#msg-container').show();
        });
        $('#notice').parent().mouseleave(function () {
            $('#msg-container').hide();
        });

        $('#admin-manager-btn').mouseenter(function () {
            $(this).find('i').removeClass('arrow');
            $(this).find('i').addClass('arrow-close');
            $('.manager-menu').show();
        });
        $('#admin-manager-btn').mouseleave(function () {
            $(this).find('i').removeClass('arrow-close');
            $(this).find('i').addClass('arrow');
            $('.manager-menu').hide();
        });

        $('.tabs li').click(function () {
            $(this).parent().find('li').removeClass('curr');
            $(this).addClass('curr');
            var i = 0;
            $(this).parent().find('li').each(function (k, v) {
                if ($(v).hasClass('curr')) {
                    i = k;
                }
            })
            $('.switch-info').hide();
            $('.switch-info').each(function (k, v) {
                if (k == i) {
                    $(v).show();
                }
            })
        });

        region();

        $('.pagination').on('click', 'li', function () {
            var page = 1;
            if (!$(this).find('span').length) {
                if (!$(this).hasClass('disabled')) {
                    $('.pagination li').removeClass('active');
                    $(this).addClass('active');
                    page = $(this).find('a').html();
                }
            } else if ($(this).find('span').html() == '«') {
                if ($(this).next().hasClass('active')) {
                    return false;
                }
                $('.pagination li').each(function (k, v) {
                    if ($(v).hasClass('active')) {
                        $(v).removeClass('active');
                        if ($(v).prev().hasClass('disabled')) {
                            $(v).prev().prev().addClass('active');
                            page = $(v).prev().prev().find('a').html();
                        } else {
                            $(v).prev().addClass('active');
                            page = $(v).prev().find('a').html();
                        }

                        return false;
                    }
                });
            } else if ($(this).find('span').html() == '»') {
                if ($(this).prev().hasClass('active')) {
                    return false;
                }
                $('.pagination li').each(function (k, v) {
                    if ($(v).hasClass('active')) {
                        $(v).removeClass('active');
                        if ($(v).next().hasClass('disabled')) {
                            $(v).next().next().addClass('active');
                            page = $(v).next().next().find('a').html();
                        } else {
                            $(v).next().addClass('active');
                            page = $(v).next().find('a').html();
                        }
                        return false;
                    }
                });
            }
            if (!$(this).hasClass('disabled')) {
                log(page);
            }
        });

        //修正多个时间控件daterangepicker无法收藏
        $('input[type=text]').click(function () {
            $('input[type=text]').removeClass('active');
            $('.daterangepicker').hide();
        });

        $("option").click(function () {
            $("select").removeAttr("size");
            $("select").blur();
            $(this).attr("selected", "");
        });
        $("select").blur(function () {
            $("select").removeAttr("size");
            $(this).attr("selected", "");
            $(this).css('height', '');
        });
        $("select").focus(function () {
            $(this).attr("size", "10");
            $(this).css('height', 'auto');
        });
    });

    function log(obj) {
        console.log(obj);
    }

    function initNav() {
        var nav = $.cookie('nav');
        var subnav = $.cookie('subnav');
        var subnav_index = $.cookie('subnav_index');
        var ssubnav_index = $.cookie('ssubnav_index');
        var href = $.cookie('href');
        // log(href);
        $('.admin-menu li').removeClass('active');
        $('.sub-nav').hide();
        $('.' + subnav).show();
        $('.admin-menu li').each(function () {
            if ($(this).data('param') == nav) {
                $(this).addClass('active');
            }
        });
        $('.' + subnav).find('.item').removeClass('current');
        $('.sub-item').hide();
        $('.' + subnav).find('.item').each(function (k, v) {
            if (subnav_index == k) {
                $(this).addClass('current');
                $(this).children('.sub-item').show();
                $(this).children('.sub-item').css('top', 50 - $(this).offset().top);
                $(this).children('.sub-item').find('li').removeClass('curr');
                $(this).children('.sub-item').find('li').each(function () {
                    if ($(this).data('param') == ssubnav_index) {
                        $(this).addClass('curr');
                        if (href != undefined && href != '') {
                            $('#main').attr('src', href);
                        } else {
                            $.cookie('href', $(this).find('a').attr('href'));
                            $('#main').attr('src', $(this).find('a').attr('href'));
                        }
                    }
                });
            }
        });


    }

    function region() {
        $('.shop_country')
    }

    //建立一個可预览到file的url
    function getImageURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) { // basic
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) {
            // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) {
            // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

    function setNextAblum(that, token, url) {
        var id = $(that).val();
        var parent = $(that).data('parent');
        $('input[name=parent_album_id]').val(id);
        if (id > 0) {
            var html = '';
            $.post(url + id, {'_token': token}, function (data) {
                if (data.length > 0) {
                    html = '<div class="gallery-option fl mar-left-20">' +
                        '<select class="form-control select" data-parent="1">' +
                        '<option value="0">请选择</option>';
                    $.each(data, function (k, v) {
                        html += '<option value="' + v.album_id + '">' + v.album_name + '</option>';
                    })
                    html += '</select></div>';
                    $(that).parent().nextAll().remove();
                    $('.p-gallery').append(html);
                } else {
                    $(that).parent().nextAll().remove();
                }
            })
        } else {
            $(that).parent().nextAll().remove();
        }
    }

    function setNextGoodsTypeCate(that, token, url) {
        var id = $(that).val();
        var parent = $(that).data('parent');
        $('input[name=attr_parent_id]').val(id);
        if (id > 0) {
            var html = '';
            $.post(url + id, {'_token': token}, function (data) {
                if (data.code == 1 && data.data.length > 0) {
                    html = '<select class="form-control max-wd-110 fl mar-right-20 select">' +
                        '<option value="0">请选择</option>';
                    $.each(data.data, function (k, v) {
                        html += '<option value="' + v.cate_id + '">' + v.cat_name + '</option>';
                    })
                    html += '</select>';
                    $(that).nextAll().remove();
                    $('.goods_type_cat').append(html);
                } else {
                    $(that).nextAll().remove();
                }
            })
        } else {
            $(that).nextAll().remove();
        }
    }

    function getGoodsTypes(that, token, url) {
        var id = $(that).val();
        var html = '<select class="form-control max-wd-350 fl">' +
            '<option value="0">请选择</option>' +
            '</select>';
        if (id > 0) {
            $.post(url + id, {'_token': token}, function (data) {
                if (data.length > 0) {
                    html = '<option value="0">请选择</option>';
                    $.each(data, function (k, v) {
                        html += '<option value="' + v.cat_id + '">' + v.cat_name + '</option>';
                    });
                    $('.goods_type select').html(html);
                } else {
                    $('.goods_type select').html(html);
                }
            });
        } else {
            $('.goods_type select').html(html);
        }
    }

    function setPages(data) {
        var html = '<li class="prev" data-num="' + data.prev + '">' +
            '<a href="#" aria-label="Previous">' +
            '<span aria-hidden="true">«</span>' +
            '</a>' +
            '</li>';
        $.each(data.page, function (k, v) {
            if (data.currentPage == v) {
                html += '<li class="active num-page" data-num="' + v + '"><a href="#">' + v + '</a></li>'
            } else if (v == '...') {
                html += '<li class="disabled"><a href="javascript:;">' + v + '</a></li>'
            } else {
                html += '<li class="num-page" data-num="' + v + '"><a href="#">' + v + '</a></li>'
            }
        });
        html += '<li class="next" data-num="' + data.next + '">' +
            '<a href="#" aria-label="Next">' +
            '<span aria-hidden="true">»</span>' +
            '</a>' +
            '</li>';
        $('.pagination').html(html);
    }

    function copyTextToClipboard(text) {
        var textArea = document.createElement("textarea");
        textArea.style.position = 'fixed';
        textArea.style.top = 0;
        textArea.style.left = 0;
        textArea.style.width = '2em';
        textArea.style.height = '2em';
        textArea.style.padding = 0;
        textArea.style.border = 'none';
        textArea.style.outline = 'none';
        textArea.style.boxShadow = 'none';
        textArea.style.background = 'transparent';
        textArea.value = text;
        document.body.appendChild(textArea);

        textArea.select();
        try {
            var msg = document.execCommand('copy') ? '成功' : '失败';
            layer.msg('复制内容 ' + msg);
        } catch (err) {
            log('浏览器不支持此复制方法');
            layer.msg('浏览器不支持此复制方法');
        }
        document.body.removeChild(textArea);
    }

    var optionDateSet = {
        timePicker: true,
        timePickerIncrement: 1,
        timePicker24Hour: true, //新版本
        timePicker12Hour: false, //老版本
        timePickerSeconds: true,
        // showDropdowns:true,
        format: "YYYY-MM-DD hh:mm:ss",
        time: {
            enabled: true
        },
        locale: {
            "separator": "～",
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "起始",
            "toLabel": "结束",
            "customRangeLabel": "自定义",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        }
    };

    var optionDateRangDay = {
        timePicker: true,
        timePickerIncrement: 1,
        timePicker24Hour: true, //新版本
        timePicker12Hour: false, //老版本
        timePickerSeconds: true,
        // showDropdowns:true,
        format: "YYYY-MM-DD",
        time: {
            enabled: true
        },
        locale: {
            "separator": "～",
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "起始",
            "toLabel": "结束",
            "customRangeLabel": "自定义",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        }
    };

    var optionDateSingle = {
        singleDatePicker: true,
        showDropdowns: true,
        format: "YYYY-MM",
        time: {
            enabled: true
        },
        locale: {
            "separator": "～",
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "起始",
            "toLabel": "结束",
            "customRangeLabel": "自定义",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        }
    };

    var optionDateDay = {
        singleDatePicker: true,
        showDropdowns: true,
        format: "YYYY-MM-DD",
        time: {
            enabled: true
        },
        locale: {
            "separator": "～",
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "起始",
            "toLabel": "结束",
            "customRangeLabel": "自定义",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1,
        }
    };