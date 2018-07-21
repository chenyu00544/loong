@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 优惠券列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($seller == 'selfsale') curr @endif fl">
                        <a href="{{url('admin/coupons/selfsale')}}">自营</a>
                    </li>
                    <li class="@if($seller == 'seller') curr @endif fl">
                        <a href="{{url('admin/coupons/seller')}}">店铺</a>
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
                    <a href="{{url('admin/coupons/create')}}" class="btn btn-success btn-add btn-sm">添加优惠券</a>
                    <div class="fr wd250">
                        <form action="{{url('admin/coupons/'.$seller)}}" method="get">
                            {{csrf_field()}}

                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="优惠券名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr lh22" value="查询">
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
                            <th width="20%">优惠活动名称</th>
                            <th width="9%">优惠券类型</th>
                            <th width="8%">使用商品</th>
                            <th width="7%">使用门槛</th>
                            <th width="5%">面值</th>
                            <th width="7%">发行量</th>
                            <th width="12%">有效范围</th>
                            <th width="7%">是否过期</th>
                            <th width="7%">审核状态</th>
                            <th width="8%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($couponses->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($couponses as $coupons)
                                <tr class="">
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$coupons->cou_id}}"
                                               class="checkbox check-all-list fl" id="checkbox_{{$coupons->cou_id}}">
                                    </td>
                                    <td>{{$coupons->cou_id}}</td>
                                    <td>{{$coupons->cou_name}}</td>
                                    <td>
                                        @if($coupons->cou_type == 1)
                                            <font class="red">注册赠券</font>
                                        @elseif($coupons->cou_type == 2)
                                            <font class="blue">购物赠券</font>
                                        @elseif($coupons->cou_type == 3)
                                            <font class="oranges">全场赠券</font>
                                        @elseif($coupons->cou_type == 4)
                                            <font class="green">会员赠券</font>
                                        @endif
                                    </td>
                                    <td>
                                        @if(!$coupons->cou_goods && !$coupons->spec_cat)
                                            <font class="red">全部商品</font>
                                        @elseif($coupons->cou_goods && !$coupons->spec_cat)
                                            <font class="blue">指定商品</font>
                                        @elseif(!$coupons->cou_goods && $coupons->spec_cat)
                                            <font class="oranges">指定分类</font>
                                        @endif
                                    </td>
                                    <td>{{$coupons->cou_man}}</td>
                                    <td>{{$coupons->cou_money}}</td>
                                    <td>{{$coupons->cou_total}}</td>
                                    <td>
                                        {{date('Y-m-d H:i:s', $coupons->cou_start_time)}}<br>
                                        {{date('Y-m-d H:i:s', $coupons->cou_end_time)}}</td>
                                    <td>
                                        @if($coupons->cou_end_time > time())
                                            <font class="green">未过期</font>
                                        @else
                                            <font class="red">已过期</font>
                                        @endif
                                    </td>
                                    <td>
                                        @if($coupons->review_status == 1)
                                            <font class="oranges">未审核</font>
                                        @elseif($coupons->review_status == 2)
                                            <font class="red">审核未通过</font>
                                        @elseif($coupons->review_status == 3)
                                            <font class="blue">审核已通过</font>
                                        @endif
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/coupons/'.$coupons->cou_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$coupons->cou_id}}">删除</a>
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
                        {{$couponses->links()}}
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
                        '{{url("admin/coupons/change")}}',
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
                    '{{url("admin/coupons/change")}}',
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
                        "{{url('admin/coupons/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
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