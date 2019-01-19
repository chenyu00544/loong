@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">通知管理 - 通知列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($type == 'app') curr @endif fl">
                        <a href="{{url('admin/notify/app')}}">APP端</a>
                    </li>
                    <li class="@if($type == 'wxapp') curr @endif fl">
                        <a href="{{url('admin/notify/wxapp')}}">微信小程序</a>
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
                        <a href="{{url('admin/notify/create')}}"
                           class="btn btn-success btn-add btn-sm">添加</a>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="13%">ID</th>
                            <th width="12%">标题</th>
                            <th width="10%">描述</th>
                            <th width="12%">图片</th>
                            <th width="12%">是否通知</th>
                            <th width="12%">通知类型</th>
                            <th width="12%">终端</th>
                            <th width="10%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($notifies->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($notifies as $notify)
                                <tr class="">
                                    <td>{{$notify->id}}</td>
                                    <td>{{$notify->title}}</td>
                                    <td>{{$notify->describe}}</td>
                                    <td>
                                        <span class="show">
                                            <a href="{{$notify->img_oss}}" class="nyroModal">
                                                <i class="glyphicon glyphicon-picture top2"
                                                   data-tooltipimg="{{$notify->img_oss}}"
                                                   ctype="tooltip" title="tooltip"></i>
                                            </a>
                                        </span>
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($notify->is_notify) active @endif"
                                                 data-type="enabled" title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$notify->id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>{{$notify->type}}</td>
                                    <td>{{$notify->terminal}}</td>
                                    <td class="text-center">
                                        <a type="button"
                                           href="javascript:;"
                                           class="btn btn-info btn-send btn-sm">发送</a>
                                        <a type="button"
                                           href="{{url('admin/notify/'.$notify->id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$notify->id}}" data-img="{{$notify->img}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
                    <div class="page_list">
                        {{$notifies->links()}}
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

                $.post('{{url("admin/notify/change")}}',
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
                var img = $(this).data('img');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/notify/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}', img:img},
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