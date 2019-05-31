@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>会员管理 - 添加编辑会员</div>
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
            <div class="tabs mar-top-20">
                <ul class="fl">
                    @foreach($userNav as $nav)
                        <li class="@if($navType == $nav['navType']) curr @endif fl">
                            <a href="{{url('admin/users/'.$nav['navType'].'/'.$user->user_id)}}">{{$nav['title']}}</a>
                        </li>
                    @endforeach
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/users/'.$user->user_id)}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>会员名称：</label>
                            <div class="col-sm-3">
                                <div class="pad-top-7">{{$user->user_name}}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">可用资金：</label>
                            <div class="col-sm-3">
                                <div class="pad-top-7"><font class="blue fwb">￥{{$user->user_money}}</font>元</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">冻结资金：</label>
                            <div class="col-sm-3">
                                <div class="pad-top-7"><font class="blue fwb">￥{{$user->frozen_money}}</font>元</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">等级积分：</label>
                            <div class="col-sm-4">
                                <div class="pad-top-7 fl">{{$user->rank_points}}</div>
                                <div class="notic fl pad-top-7 mar-left-20">等级积分是一种累计的积分，系统根据该积分来判定用户的会员等级。</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">消费积分：</label>
                            <div class="col-sm-4">
                                <div class="pad-top-7 fl">{{$user->pay_points}}</div>
                                <div class="notic fl pad-top-7 mar-left-20">消费积分是一种站内货币，允许用户在购物时支付一定比例的积分。</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">邮件地址：</label>
                            <div class="col-sm-3">
                                <input type="text" name="email" class="form-control" value="{{$user->email}}"
                                       placeholder="邮件地址">
                                <input type="hidden" name="old_email" class="form-control" value="{{$user->email}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">新密码：</label>
                            <div class="col-sm-3">
                                <input type="text" name="password" class="form-control" value=""
                                       placeholder="新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">确认密码：</label>
                            <div class="col-sm-3">
                                <input type="text" name="c_password" class="form-control" value=""
                                       placeholder="确认密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">出生日期：</label>
                            <div class="col-sm-3">
                                <input type="text" name="birthday" id="birthday" class="form-control"
                                       value="{{$user->birthday}}" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">会员等级：</label>
                            <div class="col-sm-2">
                                <select name="user_rank" class="form-control">
                                    <option value="0">试用账号</option>
                                    @foreach($userRanks as $userRank)
                                        <option value="{{$userRank->rank_id}}"
                                                @if($user->user_rank == $userRank->rank_id) selected @endif>{{$userRank->rank_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">性别：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="sex" value="0" @if($user->sex == 0) checked @endif> 保密
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="sex" value="1" @if($user->sex == 1) checked @endif> 男
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="sex" value="2" @if($user->sex == 2) checked @endif> 女
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">信用额度：</label>
                            <div class="col-sm-3">
                                <input type="text" name="credit_line" class="form-control"
                                       value="{{$user->credit_line}}"
                                       placeholder="信用额度">
                            </div>
                        </div>
                        @foreach($regFields as $regField)
                            <div class="form-group">
                                <label class="col-sm-4 control-label">{{$regField->reg_field_name}}：</label>
                                <div class="col-sm-3">
                                    <input type="text" name="regField{{$regField->id}}" class="form-control"
                                           value="@if($regField->id == 1){{$user->msn}}@elseif($regField->id == 2){{$user->qq}}@elseif($regField->id == 3){{$user->office_phone}}@elseif($regField->id == 4){{$user->home_phone}}@elseif($regField->id == 5){{$user->mobile_phone}}@endif"
                                           placeholder="{{$regField->reg_field_name}}">
                                    <input type="hidden" name="old_regField{{$regField->id}}" class="form-control"
                                           value="@if($regField->id == 1){{$user->msn}}@elseif($regField->id == 2){{$user->qq}}@elseif($regField->id == 3){{$user->office_phone}}@elseif($regField->id == 4){{$user->home_phone}}@elseif($regField->id == 5){{$user->mobile_phone}}@endif"
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
                autoUpdateInput: false,
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
                },
                startDate: '{{$now_date}}',
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