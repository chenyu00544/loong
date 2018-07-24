@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 红包列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>促销管理信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="3%">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th width="5%">编号</th>
                            <th width="17%">红包类型</th>
                            <th width="10%">订单号</th>
                            <th width="10%">使用会员</th>
                            <th width="15%">使用时间</th>
                            <th width="30%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($bonusUser->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($bonusUser as $bu)
                                <tr class="">
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$bu->bu_id}}"
                                               class="checkbox check-all-list fl" id="checkbox_{{$bu->bu_id}}">
                                    </td>
                                    <td>{{$bu->bu_id}}</td>
                                    <td>{{$bu->Bonus->type_name}}</td>
                                    <td>
                                        @if(empty($bu->order_id))
                                            N/A
                                        @else
                                            {{$bu->order_id}}
                                        @endif
                                    </td>
                                    <td>
                                        @if(empty($bu->User->user_name))
                                            N/A
                                        @else
                                            {{$bu->User->user_name}}
                                        @endif
                                    </td>
                                    <td>
                                        @if(empty($bu->used_time))
                                            未使用
                                        @else
                                            {{date('Y-m-d H:i:s', $bu->used_time)}}
                                        @endif
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$bu->bu_id}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
                    <div class="clearfix bg-color-dray pad-top-4 bt-batch">
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-delete btn-sm mar-all-8"
                               disabled="disabled" data-type="delete">删除</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$bonusUser->links()}}
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
            //批量修改
            $('.bt-batch').on('click', 'a', function () {
                var ids = [];
                $(".check-all-list").each(function () {
                    if ($(this).is(':checked')) {
                        ids.push($(this).val());
                    }
                });

                if (ids.length == 0) {
                    return;
                }

                if (ids.length > 0) {
                    $.post(
                        '{{url("admin/bonus/delbu")}}',
                        {
                            ids: ids,
                            _token: '{{csrf_token()}}'
                        },
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        }
                    );
                }
            });

            //全选
            $('input[name=all_list]').click(function () {
                var flage = $(this).is(':checked');
                $(".check-all-list").each(function () {
                    $(this).prop("checked", flage);
                    if (flage) {
                        $('.bt-batch').find('a').removeAttr('disabled');
                        $(this).parent().parent().parent().parent().addClass('current');
                        $(this).parent().parent().parent().parent().parent().addClass('current');
                    } else {
                        $('.bt-batch').find('a').attr('disabled', true);
                        $(this).parent().parent().parent().parent().removeClass('current');
                        $(this).parent().parent().parent().parent().parent().removeClass('current');
                    }
                });

            });

            $(".check-all-list").on('click', function () {
                if ($(this).is(':checked')) {
                    $('.bt-batch').find('a').removeAttr('disabled');
                    $(this).parent().parent().parent().parent().addClass('current');
                    $(this).parent().parent().parent().parent().parent().addClass('current');
                } else {
                    var bool = true;
                    $(".check-all-list").each(function () {
                        if ($(this).is(':checked')) {
                            bool = false;
                        }
                    });
                    if (bool) {
                        $('.bt-batch').find('a').attr('disabled', true);
                    }
                    $(this).parent().parent().parent().parent().removeClass('current');
                    $(this).parent().parent().parent().parent().parent().removeClass('current');
                }
            });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = [$(this).data('id')];
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/bonus/delbu')}}",
                        {'_token': '{{csrf_token()}}', ids:Id},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                $(that).parent().parent().remove();
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection