@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 团队列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($nav_type == 'all') curr @endif fl">
                        <a href="{{url('admin/team/info/all')}}">全部团</a>
                    </li>
                    <li class="@if($nav_type == 'teaming') curr @endif fl">
                        <a href="{{url('admin/team/info/teaming')}}">正在拼团</a>
                    </li>
                    <li class="@if($nav_type == 'success') curr @endif fl">
                        <a href="{{url('admin/team/info/success')}}">成功团</a>
                    </li>
                    <li class="@if($nav_type == 'failed') curr @endif fl">
                        <a href="{{url('admin/team/info/failed')}}">失败团</a>
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
                    <li>拼团活动信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix">
                    <div class="fr wd250 pad-tb-10">
                        <form action="{{url('admin/team/info/'.$nav_type)}}" method="get">
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
                            <th width="5%">团号</th>
                            <th width="15%">商品名称</th>
                            <th width="15%">商家名称</th>
                            <th width="15%">开团时间</th>
                            <th width="15%">剩余时间</th>
                            <th width="15%">差几人成团</th>
                            <th width="7%">团状态</th>
                            <th width="10%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($teamLogs->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($teamLogs as $teamLog)
                                <tr class="">
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$teamLog->team_id}}"
                                               class="checkbox check-all-list fl" id="checkbox_{{$teamLog->team_id}}">
                                    </td>
                                    <td>{{$teamLog->team_id}}</td>
                                    <td>{{$teamLog->goods_name}}</td>
                                    <td>{{$teamLog->store->shop_name}}</td>
                                    <td>{{date('Y-m-d H:i:s', $teamLog->start_time)}}</td>
                                    <td>@if($teamLog->cle < 0) 已结束 @else {{$teamLog->time}} @endif</td>
                                    <td>@if($teamLog->surplus <= 0) 已成团 @else 差{{$teamLog->surplus}}人成团 @endif</td>
                                    <td>{{$teamLog->status}}</td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/order/team/'.$teamLog->team_id).'/0'}}"
                                           class="btn btn-info btn-edit btn-sm">查看</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$teamLog->team_id}}">删除</a>
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
                        {{$teamLogs->links()}}
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

                if (ids.length === 0) {
                    return;
                }

                if (ids.length > 0) {
                    $.post(
                        '{{url("admin/team/change")}}',
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

            //sku
            $('.sku').click(function () {
                var goods_id = $(this).data('goodsid');
                layer.open({
                    type: 2,
                    area: ['800px', '500px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '商品SKU',
                    content: ["{{url('admin/goods/sku/')}}" + "/" + goods_id, 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            $('input[name=sort_order]').on('change', function () {
                var id = $(this).data('id');
                var type = 'sort_order';
                var val = $(this).val();
                $.post("{{url('admin/team/change')}}", {
                    _token: token,
                    id: id,
                    type: type,
                    value: val,
                }, function (data) {
                });
            });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/team/')}}/" + Id,
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