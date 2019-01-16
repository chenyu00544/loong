@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 团购活动列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($seller == 'selfsale') curr @endif fl">
                        <a href="{{url('admin/groupbuy/selfsale')}}">自营</a>
                    </li>
                    <li class="@if($seller == 'seller') curr @endif fl">
                        <a href="{{url('admin/groupbuy/seller')}}">店铺</a>
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
                    <li>团购活动信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <a href="{{url('admin/groupbuy/create')}}" class="btn btn-success btn-add btn-sm">添加团购活动</a>
                    <div class="fr wd250 pad-tb-10">
                        <form action="{{url('admin/groupbuy/'.$seller)}}" method="get">
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
                            <th width="17%">商品名称</th>
                            <th width="10%">商家名称</th>
                            <th width="12%">结束时间</th>
                            <th width="6%">保证金</th>
                            <th width="6%">限购</th>
                            <th width="8%">订购商品</th>
                            <th width="6%">订单</th>
                            <th width="8%">当前价格</th>
                            <th width="7%">审核状态</th>
                            <th width="10%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($groups->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($groups as $group)
                                <tr class="">
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$group->act_id}}"
                                               class="checkbox check-all-list fl" id="checkbox_{{$group->act_id}}">
                                    </td>
                                    <td>{{$group->act_id}}</td>
                                    <td class="wsn">{{$group->act_name}}</td>
                                    <td>@if($group->user_id == 0) <font
                                                class="red">{{$group->seller->shop_name}}</font> @else <font
                                                class="blue">{{$group->seller->shop_name}}</font> @endif</td>
                                    <td>{{date('Y-m-d H:i:s', $group->end_time)}}</td>
                                    <td>{{$group->deposit}}</td>
                                    <td>{{$group->restrict_amount}}</td>
                                    <td>0</td>
                                    <td>0</td>
                                    <td>{{$group->price_ladder[0]['price']}}</td>
                                    <td>@if($group->review_status == 3)
                                            <font class="blue">审核已通过</font>
                                        @elseif($group->review_status == 1)
                                            <font class="oranges">未审核</font>
                                        @elseif($group->review_status == 2)
                                            <font class="red">审核未通过</font>
                                        @endif</td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/groupbuy/see/'.$group->act_id)}}"
                                           class="btn btn-info btn-edit btn-sm">查看</a>
                                        <a type="button" href="{{url('admin/groupbuy/'.$group->act_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$group->act_id}}">删除</a>
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
                        {{$groups->links()}}
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
                        '{{url("admin/groupbuy/change")}}',
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
                        "{{url('admin/groupbuy/')}}/" + Id,
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