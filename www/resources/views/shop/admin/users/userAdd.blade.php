@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">会员管理 - 添加编辑会员</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>会员相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/users')}}" method="post" class="form-horizontal">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>会员名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="username" class="form-control" value=""
                                       placeholder="会员名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font> 邮件地址：</label>
                            <div class="col-sm-3">
                                <input type="text" name="email" class="form-control" value=""
                                       placeholder="邮件地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>登录密码：</label>
                            <div class="col-sm-3">
                                <input type="text" name="password" class="form-control" value=""
                                       placeholder="登录密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">会员等级：</label>
                            <div class="col-sm-2">
                                <select name="user_rank" class="form-control">
                                    <option value="0">试用账号</option>
                                    @foreach($userRanks as $userRank)
                                        <option value="{{$userRank->rank_id}}">{{$userRank->rank_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">性别：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="sex" value="0" checked> 保密
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="sex" value="1"> 男
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="sex" value="2"> 女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">出生日期：</label>
                            <div class="col-sm-3">
                                <input type="text" name="birthday" id="birthday" class="form-control" value="{{$now_date}}"
                                       placeholder="出生日期">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">信用额度：</label>
                            <div class="col-sm-3">
                                <input type="text" name="credit_line" class="form-control" value=""
                                       placeholder="信用额度">
                            </div>
                        </div>
                        @foreach($regFields as $regField)
                            <div class="form-group">
                                <label class="col-sm-4 control-label">{{$regField->reg_field_name}}：</label>
                                <div class="col-sm-3">
                                    <input type="text" name="regField{{$regField->id}}" class="form-control" value=""
                                           placeholder="{{$regField->reg_field_name}}">
                                </div>
                            </div>
                        @endforeach
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
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
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