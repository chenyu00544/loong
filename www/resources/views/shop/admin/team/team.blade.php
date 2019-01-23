@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 拼团活动列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($nav_type == 'team') curr @endif fl">
                        <a href="{{url('admin/team')}}">拼团商品</a>
                    </li>
                    <li class="@if($nav_type == 'cate') curr @endif fl">
                        <a href="{{url('admin/teamcate')}}">活动频道</a>
                    </li>
                    <li class="@if($nav_type == 'teaminfo') curr @endif fl">
                        <a href="{{url('admin/team/teaminfo')}}">开团团队</a>
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
                <div class="clearfix mar-bt-20">
                    <a href="{{url('admin/team/create')}}" class="btn btn-success btn-add btn-sm">添加拼团商品</a>
                    <div class="fr wd250 pad-tb-10">
                        <form action="{{url('admin/team/'.$nav_type)}}" method="get">
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
                            <th width="15%">商品名称</th>
                            <th width="7%">商家名称</th>
                            <th width="12%">原价/拼团/货号</th>
                            <th width="12%">添加排行(按钮)</th>
                            <th width="9%">SKU/库存</th>
                            <th width="8%">几人团</th>
                            <th width="8%">购买人次</th>
                            <th width="6%">排序</th>
                            <th width="8%">审核状态</th>
                            <th width="10%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($teams->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($teams as $team)
                                <tr class="">
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$team->id}}"
                                               class="checkbox check-all-list fl" id="checkbox_{{$team->id}}">
                                    </td>
                                    <td>{{$team->id}}</td>
                                    <td>
                                        <div class="tDiv goods-info">
                                            <div class="img fl pad-all-5">
                                                <a href="{{$team->goods->original_img}}" target="_blank" title="">
                                                    <img src="{{$team->goods->original_img}}"
                                                         width="68" height="68">
                                                </a>
                                            </div>
                                            <div class="desc fl pad-all-5">
                                                <div class="name">
                                                    <span class="max-wd-190 line-hg-20" title="" data-toggle="tooltip"
                                                          data-placement="bottom"
                                                          data-original-title="{{$team->goods->goods_name}}">{{$team->goods->goods_name}}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <font class="@if(empty($team->goods)) red @else blue @endif">@if(empty($team->goods))
                                                未知商家 @else {{$team->goods->shop_name}} @endif</font></td>
                                    <td>
                                        <div class="notic">拼团：¥{{$team->team_price}}</div>
                                        <div class="notic">原价：¥{{$team->goods->shop_price}}</div>
                                        <div class="notic">货号：{{$team->goods->goods_sn}}</div>
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix"><span class="fl">精品　</span>
                                            <div class="switch @if($team->goods->is_best == 1) active @endif"
                                                 data-type="is_best" title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$team->goods_id}}">
                                            </div>
                                        </div>
                                        <div class="switch-wrap clearfix"><span class="fl">新品　</span>
                                            <div class="switch @if($team->goods->is_new == 1) active @endif"
                                                 data-type="is_new" title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$team->goods_id}}">
                                            </div>
                                        </div>
                                        <div class="switch-wrap clearfix"><span class="fl">热销　</span>
                                            <div class="switch @if($team->goods->is_hot == 1) active @endif"
                                                 data-type="is_hot" title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$team->goods_id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        @if($team->products->count() == 0)
                                            {{$team->goods->goods_number}}
                                        @else
                                            <a href="javascript:;" class="sku" data-goodsid="{{$team->goods_id}}" data-userid="{{$team->goods->user_id}}"><i
                                                        class="glyphicon glyphicon-edit fs-18"></i></a>
                                        @endif
                                    </td>
                                    <td>{{$team->team_num}}人团</td>
                                    <td>{{$team->limit_num}}人次</td>
                                    <td>
                                        <input class="form-control input-sm chang-order max-wd-100" name="sort_order" type="text" data-id="{{$team->goods_id}}" value="{{$team->sort_order}}" autocomplete="off">
                                    </td>
                                    <td>@if($team->is_audit == 2)
                                            <font class="blue">审核已通过</font>
                                        @elseif($team->is_audit == 0)
                                            <font class="oranges">未审核</font>
                                        @elseif($team->is_audit == 1)
                                            <font class="red">审核未通过</font>
                                        @endif</td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/team/'.$team->id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$team->id}}">删除</a>
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
                        {{$teams->links()}}
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
                    area: ['800px', '470px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '审核',
                    content: ["{{url('admin/goods/sku/')}}" + "/" + goods_id, 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
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