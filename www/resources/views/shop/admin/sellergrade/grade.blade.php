@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商家 - 商家等级列表</div>
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
                    <li>平台区分商家等级有助于更好的管理商城。</li>
                    <li>对于不同等级的商家可提供不同权限的服务。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/sellergrade/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">等级名称</th>
                            <th class="col-sm-1">发布商品数量</th>
                            <th class="col-sm-1">模板数量</th>
                            <th class="col-sm-1">等级介绍</th>
                            <th class="col-sm-1">加入标准</th>
                            <th class="col-sm-1">是否开启</th>
                            <th class="col-sm-1">是否默认</th>
                            <th class="col-sm-1">等级标志</th>
                            <th class="col-sm-4 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($grades) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($grades as $grade)
                            <tr>
                                <th>{{$grade->id}}</th>
                                <td>
                                    <input class="form-control input-sm order wd-120" type="text" data-id="{{$grade->id}}"
                                           name="ord[]"
                                           value="{{$grade->steps_sort}}">
                                </td>
                                <td>
                                    <input class="form-control input-sm order wd-120" type="text" data-id="{{$grade->id}}"
                                           name="ord[]"
                                           value="{{$grade->steps_sort}}">
                                </td>
                                <td>
                                    {{$grade->id}}
                                </td>
                                <td>
                                    {{$grade->id}}
                                </td>
                                <td>
                                    <div class="switch-wrap clearfix">
                                        <div class="switch @if($grade->is_show) active @endif" data-type="is_show"
                                             title="是">
                                            <div class="circle"></div>
                                            <input type="hidden" value="{{$grade->id}}">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    {{$grade->id}}
                                </td>
                                <td>
                                    {{$grade->id}}
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-primary btn-edit btn-sm"
                                       href="{{url('admin/applyprocess/'.$grade->id)}}">分配等级权限</a>
                                    <a href="{{url('admin/sellergrade/'.$grade->id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$grade->id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$grades->links()}}
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
                    '{{url("admin/sellergrade/change")}}',
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

            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/sellergrade/')}}/" + Id,
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