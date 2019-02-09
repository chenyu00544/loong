@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 拼团频道列表</div>
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
                        <a href="{{url('admin/team/info/all')}}">开团团队</a>
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
                    <li>拼团频道信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix">
                    <a href="{{url('admin/teamcate/create')}}" class="btn btn-success btn-add btn-sm">添加频道</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="10%">频道级别</th>
                            <th width="15%">频道名称</th>
                            <th width="15%">商品数量</th>
                            <th width="15%">是否显示</th>
                            <th width="15%">排序</th>
                            <th width="15%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($teamCates->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($teamCates as $cate)
                                <tr class="">
                                    <td>
                                        <a href="{{url('admin/teamcate/'.$cate->id)}}" class="btn btn-primary btn-sm">下一级</a>
                                    </td>
                                    <td>{{$cate->name}}</td>
                                    <td>0</td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($cate->status == 1) active @endif"
                                                 data-type="isshow" title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$cate->id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>{{$cate->sort_order}}</td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/teamcate/'.$cate->id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$cate->id}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
                    <div class="page_list">
                        {{$teamCates->links()}}
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
                var type = $(this).data('type');
                $.post('{{url("admin/teamcate/change")}}',
                    {
                        id: id,
                        value: val,
                        type: type,
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
                        "{{url('admin/teamcate/')}}/" + Id,
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