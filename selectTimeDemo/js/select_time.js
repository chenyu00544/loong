$(function () {
    //头部高度
    var head_height = 60;
    //左边宽度
    var left_width = 30;
    //单元格宽高
    var tb_td_width = 15;
    var tb_td_height = 30;
    //时段
    var timeInt = 48;

    var html = '<div>\n' +
        '<div data-index="1" style="">\n' +
        '<div class="wrap-selecttime plug-timer-wrap" style="width: 750px;height: 270px;">' +
        '<table class="plug-timer-grid">\n' +
        '<thead>\n' +
        '<tr class="thead-tr-apm">\n' +
        '<th class="noboder"></th>\n' +
        '<th colspan="24">上午</th>\n' +
        '<th colspan="24">下午</th>\n' +
        '</tr>\n' +
        '<tr class="thead-tr-hour">\n' +
        '<th></th>\n';

    for (var i = 0; i < 24; i++) {
        html += '<th colspan="2">' + i + '</th>\n';
    }
    html += '</tr>\n' +
        '</thead>\n' +
        '<tbody>\n';
    var week_arr = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
    for (var i = 0; i < week_arr.length; i++) {
        html += '<tr>\n';
        for (var j = 0; j <= 48; j++) {
            if (j == 0) {
                html += '<th style="width: 4%;">' + week_arr[i] + '</th>\n';
            } else {
                html += '<td style="width: 2%;" class="selected"></td>';
            }
        }
        html += '</tr>\n';
    }
    html += '</tbody>\n' +
        '</table>\n' +
        '<div class="plug-timer-canvas"></div>\n' +
        '<div class="plug-timer-cover" style=""></div>\n' +
        '</div>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div id="time_seled" class="table_bottom">\n';
    for (var i = 0; i < week_arr.length; i++) {
        html += '<div class="zr">' + week_arr[i] + '（0:00-24:00）</div>\n<div class="zt"></div>\n';
    }
    html += '</div>\n' +
        '<p id="selectTimeMsg" style="display: none;margin:5px 0px;"></p>\n' +
        '<input type="hidden" id="time" name="all_time" valitype="require,timeVali" msgid="selectTimeMsg" caption="每日投放时段"\n' +
        '           switchname="selectTime" valitag="vali" valiresult="true"\n' +
        ' value="111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111">';

    $('#selectTimeContent').html(html);

    $(".plug-timer-canvas").mousedown(function (event) {
        var offset_x = event.offsetX;
        var offset_y = event.offsetY;
        if ((offset_x - left_width) >= 0 && (offset_y - head_height) >= 0) {

            $('.plug-timer-cover').css('width', 0);
            $('.plug-timer-cover').css('height', 0);
            $('.plug-timer-cover').css('display', 'block');

            var cover_x = 0;
            var cover_y = 0;

            $('.plug-timer-cover').css('left', Math.floor(offset_x / tb_td_width) * tb_td_width);
            $('.plug-timer-cover').css('top', Math.floor(offset_y / tb_td_height) * tb_td_height);
            $('.plug-timer-cover').css('width', tb_td_width);
            $('.plug-timer-cover').css('height', tb_td_height);

            $(this).mousemove(function (e) {
                if (offset_x - e.offsetX > 0) {
                    offset_x = Math.ceil(offset_x / tb_td_width) * tb_td_width;
                    cover_x = e.offsetX >= left_width ? e.offsetX : left_width;
                } else if (offset_x - e.offsetX < 0) {
                    offset_x = Math.floor(offset_x / tb_td_width) * tb_td_width;
                    cover_x = offset_x;
                }
                if (offset_y - e.offsetY > 0) {
                    offset_y = Math.ceil(offset_y / tb_td_height) * tb_td_height;
                    cover_y = e.offsetY >= head_height ? e.offsetY : head_height;
                } else if (offset_y - e.offsetY < 0) {
                    offset_y = Math.floor(offset_y / tb_td_height) * tb_td_height;
                    cover_y = offset_y;
                }
                $('.plug-timer-cover').css('left', Math.floor(cover_x / tb_td_width) * tb_td_width);
                $('.plug-timer-cover').css('top', Math.floor(cover_y / tb_td_height) * tb_td_height);
                $('.plug-timer-cover').css('width', Math.ceil(Math.abs((e.offsetX >= left_width ? e.offsetX : left_width) - offset_x) / tb_td_width) * tb_td_width);
                $('.plug-timer-cover').css('height', Math.ceil(Math.abs((e.offsetY >= head_height ? e.offsetY : head_height) - offset_y) / tb_td_height) * tb_td_height);
            });
        }
    });
    $(".plug-timer-canvas").mouseup(function () {
        $(".plug-timer-canvas").unbind('mousemove');
        $('.plug-timer-cover').css('display', 'none');

        var select_width = $('.plug-timer-cover').width();
        var select_height = $('.plug-timer-cover').height();
        var select_y = (parseInt($('.plug-timer-cover').css('top')) - head_height) / tb_td_height;
        var select_x = (parseInt($('.plug-timer-cover').css('left')) - left_width) / tb_td_width + select_y * 48;

        var select_obj = [];
        for (var i = 0; i < select_height / tb_td_height; i++) {
            for (var j = 0; j < select_width / tb_td_width; j++) {
                var x = select_x + j;
                select_obj.push(x);
            }
            select_x = select_x + 48;
        }

        var k = 0;
        var timeStr = '';

        $('tbody>tr>td').each(function (key, val) {
            if (key == select_obj[k]) {
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                } else {
                    $(this).addClass('selected');
                }
                k++;
            }
            if ($(this).hasClass('selected')) {
                timeStr += '1';
            } else {
                timeStr += '0';
            }
        });
        $('#time').val(timeStr);

        showTime();
    });
});

function showTime() {
    var timeStr = $('#time').val();
    var str = '';
    var bool = true;
    var week = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
    for (var i = 0; i < timeStr.length; i++) {
        var row = Math.floor(i / 48);
        var k = i - 48 * row;
        var minute = k * 30 % 60 > 10 ? k * 30 % 60 : k * 30 % 60 + '0';
        if (timeStr[i] == '0') {
            if (!bool) {
                str += Math.floor(k * 30 / 60) + ':' + minute + ', ';
                bool = true;
            }
        } else {
            if (bool) {
                str += Math.floor(k * 30 / 60) + ':' + minute + '-';
                bool = false;
            }
        }
        if (i == 48 * (row + 1) - 1) {
            if (timeStr[i] == '1') {
                str += '24:00';
            } else {
                str = str.substr(0, str.length - 2);
            }
            $('.zr').eq(row).html(week[row] + '（' + str + '）');
            str = '';
            bool = true;
        }
    }

}