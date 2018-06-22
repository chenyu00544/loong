@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">会员管理 - 会员列表</div>
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
                    @foreach($usersNav as $nav)
                        <li class="@if($navType == $nav['navType']) curr @endif fl">
                            <a href="{{url('admin/'.$nav['navType'])}}">{{$nav['title']}}</a>
                        </li>
                    @endforeach
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/users/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加会员　</a>

                    <div class="fr wd250">
                        <form action="{{url('admin/users/')}}" method="get">
                            {{csrf_field()}}
                            <input type="text" name="keywords" value=""
                                   class="form-control input-sm max-wd-190" placeholder="名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr lh22" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th width="3%">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th class="text-center" width="5%"><a>编号</a></th>
                            <th width="15%"><a>会员名称</a></th>
                            <th width="10%">昵称</th>
                            <th width="20%">手机/邮箱</th>
                            <th width="8%">注册日期</th>
                            <th width="8%">账户</th>
                            <th width="8%">等级积分</th>
                            <th width="8%">验证</th>
                            <th class="text-center" width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($usersList) > 0)
                            @foreach($usersList as $user)
                                <tr>
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$user->user_id}}"
                                               class="checkbox check-all"
                                               id="checkbox_{{$user->user_id}}">
                                    </td>
                                    <td class="text-center">
                                        {{$user->user_id}}
                                    </td>
                                    <td>
                                        {{$user->user_name}}
                                    </td>
                                    <td>@if($user->nick_name) {{$user->nick_name}} @else 未设置 @endif</td>
                                    <td>
                                        <div class="tDiv">
                                            <div class="tDiv_item clearfix">
                                                <span class="fl">手机：</span>
                                                <div class="value">
                                                    <span>@if($user->mobile_phone) {{$user->mobile_phone}} @else
                                                            未设置 @endif</span>
                                                </div>
                                            </div>
                                            <div class="tDiv_item clearfix">
                                                <span class="fl">邮箱：</span>
                                                <div class="value">
                                                    <span>{{$user->email}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        {{date('Y-m-d', $user->reg_time)}}
                                    </td>
                                    <td>
                                        <div class="tDiv">
                                            <div class="tDiv_item clearfix">
                                                <span class="fl">可用资金：</span>
                                                <div class="value">
                                                    <span>{{$user->user_money}}</span>
                                                </div>
                                            </div>
                                            <div class="tDiv_item clearfix">
                                                <span class="fl">消费积分：</span>
                                                <div class="value">
                                                    <span>{{$user->pay_points}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="tDiv">
                                            <div class="tDiv_item clearfix">
                                                <span class="fl">{{$user->rank_points}}</span>
                                            </div>
                                            <div class="tDiv_item clearfix">
                                                <span class="fl">@if($user->rank_name) {{$user->rank_name}} @else
                                                        试用用户 @endif</span>
                                            </div>
                                        </div>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($user->is_validated) active @endif" data-type="toggle_is_validated" title="是"
                                                 data-uid="{{$user->user_id}}">
                                                <div class="circle"></div>
                                                <input type="hidden" value="0">
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/users/info/'.$user->user_id)}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">查看</a>
                                        <a type="button" href="{{url('admin/users/log/'.$user->user_id)}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">日志</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$user->user_id}}">删除</a>
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
                            <a type="button" class="btn btn-info btn-sure btn-sm mar-all-8">删除</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$usersList->links()}}
                    </div>
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
            $('.nyroModal').nyroModal();

            $("[data-toggle='tooltip']").tooltip();

            //开关
            $('.switch').click(function () {
                var val = 0;
                var uid = $(this).data('uid');
                if ($(this).hasClass('active')) {
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }
                $.post(
                    "{{url('admin/users/changes')}}",
                    {'_token': '{{csrf_token()}}', 'type': 'validated', 'value': val, 'uid': uid},
                    function (data) {
                    }
                );
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
                        "{{url('admin/users/')}}/" + Id,
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