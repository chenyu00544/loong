@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 秒杀活动列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($seller == 'selfsale') curr @endif fl">
                        <a href="{{url('admin/seckill/selfsale')}}">自营</a>
                    </li>
                    <li class="@if($seller == 'seller') curr @endif fl">
                        <a href="{{url('admin/seckill/seller')}}">店铺</a>
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
                    <li>秒杀活动信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <a href="{{url('admin/seckill/create')}}" class="btn btn-success btn-add btn-sm">添加秒杀活动</a>
                    <a href="{{url('admin/seckilltime')}}" class="btn btn-success btn-add btn-sm">秒杀时间段列表</a>
                    <div class="fr wd250 pad-tb-10">
                        <form action="{{url('admin/seckill/'.$seller)}}" method="get">
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
                            <th width="10%">商家名称</th>
                            <th width="15%">活动标题</th>
                            <th width="10%">活动状态</th>
                            <th width="10%">开始时间</th>
                            <th width="10%">结束时间</th>
                            <th width="10%">上线/下架</th>
                            <th width="10%">审核状态</th>
                            <th width="10%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($seckills->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($seckills as $seckill)
                                <tr class="">
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$seckill->sec_id}}"
                                               class="checkbox check-all-list fl" id="checkbox_{{$seckill->sec_id}}">
                                    </td>
                                    <td>{{$seckill->sec_id}}</td>
                                    <td>
                                        <font class="@if($seckill->ru_id == 0) red @else blue @endif">@if(empty($seckill->seller))
                                                未知商家 @else {{$seckill->seller->shop_name}} @endif</font>
                                    </td>
                                    <td class="wsn">
                                        {{$seckill->acti_title}}
                                    </td>
                                    <td>
                                        <div><font class="red">@if($seckill->end_time<time())
                                                    活动已结束 @else
                                                    活动未结束 @endif</font></div>
                                    </td>
                                    <td>{{date('Y-m-d H:i:s', $seckill->start_time)}}</td>
                                    <td>{{date('Y-m-d H:i:s', $seckill->end_time)}}</td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($seckill->is_putaway == 1) active @endif" data-type="isshow" title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$seckill->sec_id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>@if($seckill->review_status == 3)
                                            <font class="blue">审核已通过</font>
                                        @elseif($seckill->review_status == 1)
                                            <font class="oranges">未审核</font>
                                        @elseif($seckill->review_status == 2)
                                            <font class="red">审核未通过</font>
                                        @endif</td>
                                    <td class="text-center">
                                        <a type="button"
                                           href="{{url('admin/seckillgoods/'.$seckill->sec_id)}}"
                                           class="btn btn-info btn-edit btn-sm">设置商品</a>
                                        <a type="button" href="{{url('admin/seckill/'.$seckill->sec_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$seckill->sec_id}}">删除</a>
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
                        {{$seckills->links()}}
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
                    $.post('{{url("admin/seckill/change")}}',
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

            //开关
            $('.switch').on('click', function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }
                var id = $(this).find('input').val();

                $.post('{{url("admin/seckill/change")}}',
                    {
                        id: id,
                        value: val,
                        type: 'putaway',
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                    }
                );
            });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/seckill/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
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