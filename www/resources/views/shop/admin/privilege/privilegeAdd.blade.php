@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">权限管理 - 添加编辑管理员</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>可从管理平台手动添加一名新管理员，并填写相关信息。</li>
                    <li>新增管理员后可从管理员列表中找到该条数据，并再次进行编辑操作。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/privilege')}}" method="post" class="form-horizontal">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>用户名：</label>
                            <div class="col-sm-3">
                                <input type="text" name="user_name" class="form-control" value=""
                                       placeholder="用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>Email地址：</label>
                            <div class="col-sm-3">
                                <input type="text" name="email" class="form-control" value=""
                                       placeholder="邮件地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>新密码：</label>
                            <div class="col-sm-3">
                                <input type="password" name="new_password" class="form-control" value=""
                                       placeholder="新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>确认密码：</label>
                            <div class="col-sm-3">
                                <input type="password" name="confirm_password" class="form-control" value=""
                                       placeholder="确认密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            var optionSet = {
                singleDatePicker: true,
                showDropdowns: true,
                timePicker: false,
                format: 'YYYY-MM-DD',
                timePicker24Hour: false,
                timePickerSeconds: false,
                locale: {
                    "separator": " -222 ",
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
            $('#birthday').daterangepicker(optionSet, function (start, end) {
                var s = start.format('YYYY-MM-DD');
                var t = s;
                $('#birthday').val(t);
            });
        });
    </script>
@endsection
@endsection