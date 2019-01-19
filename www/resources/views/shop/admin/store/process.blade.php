@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">店铺管理 - 店铺设置</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($nav == 'store') curr @endif fl">
                        <a href="{{url('admin/store')}}">店铺设置</a>
                    </li>
                    <li class="@if($nav == 'process') curr @endif fl">
                        <a href="{{url('admin/msp')}}">入驻流程</a>
                    </li>
                    <li class="@if($nav == 'privilege') curr @endif fl">
                        <a href="{{url('admin/store/privilege')}}">入驻初始化权限</a>
                    </li>
                    <li class="@if($nav == 'grade') curr @endif fl">
                        <a href="{{url('admin/sellergrade')}}">店铺等级</a>
                    </li>
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>商家入驻申请流程步骤信息管理。</li>
                    <li>平台按实际业务需要设定流程步骤。</li>
                    <li>如不清楚流程设定请谨慎删除通用流程。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/msp/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">编号</th>
                            <th class="col-sm-3">流程信息标题</th>
                            <th class="col-sm-2">所属流程</th>
                            <th class="col-sm-1">排序</th>
                            <th class="col-sm-1">是否显示</th>
                            <th class="col-sm-2 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($process) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($process as $pro)
                            <tr>
                                <th>{{$pro->id}}</th>
                                <td>
                                    {{$pro->process_title}}
                                </td>
                                <td>
                                    @if($pro->process_steps == 1)
                                        入驻须知
                                    @elseif($pro->process_steps == 2)
                                        公司信息认证
                                    @elseif($pro->process_steps == 3)
                                        店铺信息认证
                                    @endif
                                </td>
                                <td>
                                    <input class="form-control input-sm order wd-120" type="text" data-id="{{$pro->id}}"
                                           name="ord[]"
                                           value="{{$pro->steps_sort}}">
                                </td>
                                <td>
                                    <div class="switch-wrap clearfix">
                                        <div class="switch @if($pro->is_show) active @endif" data-type="is_show"
                                             title="是">
                                            <div class="circle"></div>
                                            <input type="hidden" value="{{$pro->id}}">
                                        </div>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-primary btn-edit btn-sm"
                                       href="{{url('admin/applyprocess/'.$pro->id)}}">查看</a>
                                    <a href="{{url('admin/msp/'.$pro->id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    {{--<a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$pro->id}}">删除</a>--}}
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$process->links()}}
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
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }

                var tag = $(this).data('type');
                var id = $(this).children('input').val();
                $.post(
                    '{{url("admin/msp/change")}}',
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

            $('.order').change(function () {
                $.post(
                    '{{url("admin/msp/change")}}',
                    {
                        id: $(this).data('id'),
                        val: $(this).val(),
                        type: 'order',
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
                        "{{url('admin/msp/')}}/" + Id,
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