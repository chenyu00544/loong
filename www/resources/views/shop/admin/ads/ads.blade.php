@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">广告管理 - 广告列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($type == 'pc') curr @endif fl">
                        <a href="{{url('admin/ad/pc')}}">PC端</a>
                    </li>
                    <li class="@if($type == 'web') curr @endif fl">
                        <a href="{{url('admin/ad/web')}}">WEB端</a>
                    </li>
                    <li class="@if($type == 'app') curr @endif fl">
                        <a href="{{url('admin/ad/app')}}">APP端</a>
                    </li>
                    <li class="@if($type == 'wxapp') curr @endif fl">
                        <a href="{{url('admin/ad/wxapp')}}">微信小程序</a>
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
                    <li>商城相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <div class="fl">
                        <a href="{{url('admin/ad/add/'.$type)}}"
                           class="btn btn-success btn-add btn-sm">添加广告</a>
                    </div>
                    <div class="fr wd250 pad-top-7">
                        <form action="{{url('admin/ad/'.$type)}}" method="get">
                            {{csrf_field()}}

                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="广告名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr lh22" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="5%">编号</th>
                            <th width="14%">广告名称</th>
                            <th width="15%">广告位置</th>
                            <th width="10%">媒介类型</th>
                            <th width="10%">开始日期</th>
                            <th width="10%">结束日期</th>
                            <th width="10%">点击次数</th>
                            <th width="10%">是否开启</th>
                            <th width="14%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($ads->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($ads as $ad)
                                <tr class="">
                                    <td>{{$ad->ad_id}}</td>
                                    <td>{{$ad->ad_name}}</td>
                                    <td>{{$ad->position_name}}</td>
                                    <td>
                                        <span class="show">
                                            <a href="{{url($ad->ad_code)}}" class="nyroModal">
                                                <i class="glyphicon glyphicon-picture top2"
                                                   data-tooltipimg="{{url($ad->ad_code)}}"
                                                   ctype="tooltip" title="tooltip"></i>
                                            </a>
                                        </span>
                                    </td>
                                    <td>{{date('Y-m-d H:i:s', $ad->start_time)}}</td>
                                    <td>@if($ad->end_time < time()) <font class="red">已结束</font> @else{{date('Y-m-d H:i:s', $ad->end_time)}}@endif</td>
                                    <td>{{$ad->click_count}}</td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($ad->enabled) active @endif"
                                                 data-type="enabled" title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$ad->ad_id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-center">
                                        <a type="button"
                                           href="{{url('admin/ad/edit/'.$ad->ad_id.'/'.$type)}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;" data-id="{{$ad->ad_id}}"
                                           class="btn btn-danger btn-del btn-sm">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
                    <div class="page_list">
                        {{$ads->links()}}
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

            $('.switch').click(function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }

                var id = $(this).children('input').val();

                $.post(
                    '{{url("admin/ad/change")}}',
                    {
                        id: id,
                        val: val,
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
                        "{{url('admin/ad/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                $(that).parent().parent().remove();
                            }, 1000);
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection