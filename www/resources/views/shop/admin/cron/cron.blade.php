@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 计划任务</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>计划任务信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/cron/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-2">计划任务名称</th>
                            <th class="col-sm-3">计划任务描述</th>
                            <th class="col-sm-2">上次执行时间</th>
                            <th class="col-sm-2">下次执行时间</th>
                            <th class="col-sm-1">排序</th>
                            <th class="col-sm-1">开启</th>
                            <th class="col-sm-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($crons) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($crons as $cron)
                            <tr>
                                <td>{{$cron->cron_name}}</td>
                                <td class="wsn">{{$cron->cron_desc}}</td>
                                <td>{{date('Y-m-d H:i:s', $cron->thistime)}}</td>
                                <td>{{date('Y-m-d H:i:s', $cron->nextime)}}</td>
                                <td>
                                    <input class="form-control input-sm chang-order" type="text"
                                           data-id="{{$cron->cron_id}}"
                                           name="cron_order"
                                           value="{{$cron->cron_order}}">
                                </td>
                                <td>
                                    <div class="switch-wrap clearfix">
                                        <div class="switch @if($cron->enable) active @endif" data-type="enable"
                                             title="是">
                                            <div class="circle"></div>
                                            <input type="hidden" value="{{$cron->cron_id}}">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <a type="button" class="btn btn-primary btn-edit btn-sm implement"
                                       data-id="{{$cron->cron_id}}">执行</a>
                                    <a type="button" href="{{url('admin/cron/'.$cron->cron_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$cron->cron_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$crons->links()}}
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

            $('.switch').click(function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0
                    $(this).removeClass('active');
                } else {
                    val = 1
                    $(this).addClass('active');
                }

                var tag = $(this).data('type');
                var id = $(this).children('input').val();
                $.post(
                    '{{url("admin/cron/change")}}',
                    {
                        id: id,
                        type: tag,
                        val: val,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                    }
                );
            });

            $('.chang-order').change(function () {
                $.post(
                    '{{url("admin/cron/change")}}',
                    {
                        id: $(this).data('id'),
                        val: $(this).val(),
                        type: 'order',
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                    }
                );
            });

            $('.implement').click(function () {
                var Id = $(this).data('id');
                $.post(
                    '{{url("admin/cron/implement")}}',
                    {
                        id: Id,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                    }
                );
            });

            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/cron/')}}/" + Id,
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