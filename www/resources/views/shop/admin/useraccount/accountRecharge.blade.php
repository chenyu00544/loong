@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>会员管理 - 会员列表</div>
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
                    <li class="@if($navType == 1) curr @endif fl">
                        <a href="{{url('admin/uaccount/')}}">提现申请</a>
                    </li>
                    <li class="@if($navType == 0) curr @endif fl">
                        <a href="{{url('admin/uaccount/recharge')}}">充值记录</a>
                    </li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="wd-750 clearfix">
                    <form action="@if($navType==1){{url('admin/uaccount/')}}@else{{url('admin/uaccount/recharge')}}@endif"
                          method="get">
                        {{csrf_field()}}

                        <div class="form-group clearfix">
                            <label class="col-sm-2 control-label text-right lh30">关键词：</label>
                            <div class="col-sm-10 wd-360">
                                <input type="text" name="keywords" value="{{$search['keywords']}}"
                                       class="form-control input-sm max-wd-190" placeholder="会员名称/邮箱/手机号">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="col-sm-2 control-label text-right lh30">充值时间：</label>
                            <div class="col-sm-10 wd-360">
                                <input type="text" name="date" id="date" class="form-control input-sm"
                                       value="{{$search['date']}}" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="col-sm-2 control-label text-right lh30">到款状态：</label>
                            <div class="col-sm-10 wd-240">
                                <select name="is_paid" class="form-control input-sm">
                                    <option value="-1" @if($search['is_paid'] == -1) selected @endif>请选择</option>
                                    <option value="0" @if($search['is_paid'] == 0) selected @endif>未确认</option>
                                    <option value="1" @if($search['is_paid'] == 1) selected @endif>已完成</option>
                                    <option value="2" @if($search['is_paid'] == 2) selected @endif>取消</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10 wd-360">
                                <input type="submit" class="btn btn-primary btn-edit btn-sm lh22"
                                       value="　查询　">
                            </div>
                        </div>
                    </form>
                </div>
                <div>
                    @if($navType == 1)
                        <a href="{{url('admin/uaccount/create')}}"
                           class="btn btn-success btn-add btn-sm">　提现申请　</a>
                    @else
                        <a href="{{url('admin/uaccount/create')}}"
                           class="btn btn-success btn-add btn-sm">　充值申请　</a>
                    @endif
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th width="5%">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th class="text-center" width="5%"><a>编号</a></th>
                            <th width="15%"><a>会员名称</a></th>
                            <th width="10%">操作日期</th>
                            <th width="10%">类型</th>
                            <th width="10%">金额</th>
                            <th width="10%">支付方式</th>
                            <th width="10%">到款状态</th>
                            <th width="10%">操作员</th>
                            <th class="text-center" width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($userAccount) > 0)
                            @foreach($userAccount as $account)
                                <tr>
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$account->id}}"
                                               class="checkbox check-all"
                                               id="checkbox_{{$account->id}}">
                                    </td>
                                    <td class="text-center">
                                        {{$account->id}}
                                    </td>
                                    <td>
                                        {{$account->user_name}}
                                    </td>
                                    <td>{{date('Y-m-d H:i:s',$account->paid_time)}}</td>
                                    <td>
                                        @if($account->process_type ==1) 提现 @else 充值 @endif
                                    </td>
                                    <td>
                                        {{$account->amount}}
                                    </td>
                                    <td>
                                        {{$account->payment}}
                                    </td>
                                    <td>
                                        @if($account->is_paid ==1) 已完成 @else 未确认 @endif
                                    <td>
                                        {{$account->admin_user}}
                                    </td>
                                    <td class="text-center">
                                        @if($account->is_paid ==1)
                                            <a type="button" href="{{url('admin/uaccount/'.$account->id.'/edit')}}"
                                               class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        @else
                                            <a type="button" href="{{url('admin/uaccount/'.$account->id.'/edit')}}"
                                               class="btn btn-info btn-edit btn-sm mar-all-5">审核</a>
                                            <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                               data-id="{{$account->id}}">删除</a>
                                        @endif
                                    </td>
                                </tr>
                            @endforeach
                        @else
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        </tbody>
                    </table>
                    <div class="clearfix bg-color-dray pad-top-4">
                        <div class="fl mar-all-5 checkwrap">
                            <label class="label-tip">
                                <input type="checkbox" name="all_list" value=""
                                       class="checkbox check-all fl ">全选</label>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-info btn-sure btn-sm mar-all-8">完成</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$userAccount->links()}}
                    </div>
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
                timePicker: true,
                timePickerIncrement: 1,
                format: 'YYYY-MM-DD hh:mm:ss',
                timePicker24Hour: true,
                timePickerSeconds: true,
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
            $('#date').daterangepicker(optionSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm:ss');
                var t = s + '～' + e;
                $('#date').val(t);
            });

            //全选
            $('input[name=all_list]').click(function () {
                var flage = $(this).is(':checked')
                $(".check-all").each(function () {
                    $(this).prop("checked", flage);
                })
            });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/uaccount/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                $(that).parent().parent().remove();
                            }
                        }
                    );
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection