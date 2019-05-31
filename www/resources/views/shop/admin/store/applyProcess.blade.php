@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>商家 - 申请流程信息列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>流程内容信息管理。</li>
                    <li>可编辑流程内容和添加流程内容。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/applyprocess/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">编号</th>
                            <th class="col-sm-2">内容标题</th>
                            <th class="col-sm-2">所属流程</th>
                            <th class="col-sm-1">显示样式</th>
                            <th class="col-sm-3">特殊说明</th>
                            <th class="col-sm-1">显示位置</th>
                            <th class="col-sm-2 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($applyProcesses) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($applyProcesses as $pro)
                            <tr>
                                <th>{{$pro->tid}}</th>
                                <td>
                                    {{$pro->fields_titles}}
                                </td>
                                <td>
                                    {{$pro->msp->process_title}}
                                </td>
                                <td>
                                    @if($pro->steps_style == 0)
                                        基本信息
                                    @elseif($pro->steps_style == 1)
                                        店铺类型
                                    @elseif($pro->steps_style == 2)
                                        类目信息
                                    @elseif($pro->steps_style == 3)
                                        品牌信息
                                    @elseif($pro->steps_style == 4)
                                        店铺信息
                                    @endif
                                </td>
                                <td class="wsn">
                                    {!! $pro->fields_special !!}
                                </td>
                                <td>
                                    @if($pro->special_type == 0)
                                        无
                                    @elseif($pro->special_type == 1)
                                        上面
                                    @elseif($pro->special_type == 2)
                                        下面
                                    @endif
                                </td>
                                <td class="text-center">
                                    <a href="{{url('admin/applyprocess/'.$pro->tid.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$pro->tid}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$applyProcesses->links()}}
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
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/applyprocess/')}}/" + Id,
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