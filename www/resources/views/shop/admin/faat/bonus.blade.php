@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 红包列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($seller == 'selfsale') curr @endif fl">
                        <a href="{{url('admin/bonus/selfsale')}}">自营</a>
                    </li>
                    <li class="@if($seller == 'seller') curr @endif fl">
                        <a href="{{url('admin/bonus/seller')}}">店铺</a>
                    </li>
                </ul>
            </div>
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
                <div class="clearfix mar-bt-20">
                    <a href="{{url('admin/bonus/create')}}" class="btn btn-success btn-add btn-sm">添加红包</a>
                    <div class="fr wd250">
                        <form action="{{url('admin/bonus/'.$seller)}}" method="get">
                            {{csrf_field()}}

                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="活动名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="3%">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th width="5%">编号</th>
                            <th width="17%">类型名称</th>
                            <th width="10%">商家名称</th>
                            <th width="10%">发放类型</th>
                            <th width="12%">红包金额</th>
                            <th width="12%">最小订单金额</th>
                            <th width="8%">发放量</th>
                            <th width="8%">使用量</th>
                            <th width="7%">审核状态</th>
                            <th width="10%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($bonuses->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($bonuses as $bonus)
                                <tr class="">
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$bonus->bonus_id}}"
                                               class="checkbox check-all-list fl" id="checkbox_{{$bonus->bonus_id}}">
                                    </td>
                                    <td>{{$bonus->bonus_id}}</td>
                                    <td>{{$bonus->type_name}}</td>
                                    <td>
                                        @if($bonus->user_id == 0)
                                            <font class="red">自营</font>
                                        @else
                                            <font class="blue">其他</font>
                                        @endif
                                    </td>
                                    <td>
                                        @if($bonus->send_type == 0)
                                            <font class="red">按用户发放</font>
                                        @elseif($bonus->send_type == 1)
                                            <font class="blue">按商品发放</font>
                                        @elseif($bonus->send_type == 2)
                                            <font class="oranges">按订单金额发放</font>
                                        @elseif($bonus->send_type == 3)
                                            <font class="yellow">线下发放的红包</font>
                                        @elseif($bonus->send_type == 4)
                                            <font class="green">自行领取</font>
                                        @endif
                                    </td>
                                    <td>{{$bonus->type_money}}</td>
                                    <td>{{$bonus->min_goods_amount}}</td>
                                    <td>{{$bonus->use_bonus_user_count}}</td>
                                    <td>{{$bonus->use_bonus_count}}</td>
                                    <td>@if($bonus->review_status == 3)
                                            <font class="blue">审核已通过</font>
                                        @elseif($bonus->review_status == 1)
                                            <font class="oranges">未审核</font>
                                        @elseif($bonus->review_status == 2)
                                            <font class="red">审核未通过</font>
                                        @endif</td>
                                    <td class="text-center">
                                        @if($bonus->send_type != 2 && $bonus->send_type != 3)
                                            <a type="button" href="{{url('admin/bonus/send/'.$bonus->bonus_id)}}"
                                               class="btn btn-info btn-edit btn-sm">发放</a>
                                        @endif
                                        <a type="button" href="{{url('admin/bonus/user/'.$bonus->bonus_id)}}"
                                           class="btn btn-info btn-edit btn-sm">查看</a>
                                        <a type="button" href="{{url('admin/bonus/'.$bonus->bonus_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$bonus->bonus_id}}">删除</a>
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
                        {{$bonuses->links()}}
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
                        '{{url("admin/bonus/change")}}',
                        {
                            id: ids,
                            type: 'delete',
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

            $('input[name=sort_order]').change(function () {
                var sort = $(this).val();
                var id = $(this).data('id');
                $.post(
                    '{{url("admin/bonus/change")}}',
                    {
                        id: id,
                        type: 'sort_order',
                        value: sort,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                    }
                );
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
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/bonus/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code === 1) {
                                $(that).parent().parent().remove();
                                if ($('tbody tr').length === 0) {
                                    $('tbody').html('<tr class=""><td class="no-records" colspan="20">没有找到任何记录</td></tr>');
                                }
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection