$(function () {
    //顶部导航切换
    $('.admin-menu li').click(function () {
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
    });

    //左边导航切换
    $('.item .title').click(function () {
        $(this).parent().parent().children('.item').removeClass('current');
        $(this).parent().addClass('current');
        $(this).parent().parent().children('.item').children('.sub-item').hide();
        $(this).parent().children('.sub-item').show();
        $(this).parent().children('.sub-item').css('top', 50 - $(this).offset().top);
        $(this).parent().children('.sub-item').find('li').removeClass('curr');
        $(this).parent().children('.sub-item').find('li').first().addClass('curr')
    });

    //左边子导航切换
    $('.sub-item li').click(function () {
        $(this).parent().find('li').removeClass('curr');
        $(this).addClass('curr');
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

    $('.pagination li').on('click', function () {
        var page = 1;
        if (!$(this).find('span').length) {
            $('.pagination li').removeClass('active');
            $(this).addClass('active');
            page = $(this).find('a').html();
        } else if ($(this).find('span').html() == '«') {
            if($(this).next().hasClass('active')){
                return false;
            }
            $('.pagination li').each(function (k, v) {
                if ($(v).hasClass('active')) {
                    $(v).removeClass('active');
                    if($(v).prev().hasClass('disabled')){
                        $(v).prev().prev().addClass('active');
                        page = $(v).prev().prev().find('a').html();
                    }else{
                        $(v).prev().addClass('active');
                        page = $(v).prev().find('a').html();
                    }

                    return false;
                }
            });
        } else if ($(this).find('span').html() == '»') {
            if($(this).prev().hasClass('active')){
                return false;
            }
            $('.pagination li').each(function (k, v) {
                if ($(v).hasClass('active')) {
                    $(v).removeClass('active');
                    if($(v).next().hasClass('disabled')){
                        $(v).next().next().addClass('active');
                        page = $(v).next().next().find('a').html();
                    }else{
                        $(v).next().addClass('active');
                        page = $(v).next().find('a').html();
                    }
                    return false;
                }
            });
        }
        console.log(page);
    });

});

function region() {
    $('.shop_country')
}

//建立一個可存取到file的url
function getImageURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    }
    else if (window.URL != undefined) {
        // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    }
    else if (window.webkitURL != undefined) {
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
                html = '<div class="gallery-option fl mar-left-20"><select class="form-control select" data-parent="1"><option value="0">请选择</option>';
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

