@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 类型分类</div>
        <div class="content">
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
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($typeNav == 'goodsType') curr @endif fl">
                        <a href="{{url('admin/goodstype')}}">商品类型</a>
                    </li>
                    <li class="@if($typeNav == 'typeCate') curr @endif fl">
                        <a href="{{url('admin/typecate')}}">类型分类</a>
                    </li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/typecate/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加类型分类　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="col-sm-2">级别({{$rank[0]}})</th>
                            <th class="col-md-2">商家名称</th>
                            <th class="col-md-2">分类名称</th>
                            <th class="col-md-2">类型数</th>
                            <th class="col-md-1">排序</th>
                            <th class="col-md-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($typeCates as $typeCate)
                            <tr>
                                <td>
                                    <a href="{{url('admin/typecate/'.$typeCate->cate_id)}}"
                                       class="btn btn-primary btn-sm">下一级</a>
                                </td>
                                <td><font class="red">直营</font></td>
                                <td>{{$typeCate->cat_name}}</td>
                                <td>123</td>
                                <td>
                                    <input class="form-control input-sm chang-order" type="text"
                                           data-id="{{$typeCate->cate_id}}"
                                           data-cate="order" value="{{$typeCate->sort_order}}">
                                </td>
                                <td class="text-center">
                                    <a type="button" href="{{url('admin/typecate/'.$typeCate->cate_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                       data-id="{{$typeCate->cate_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
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

            $('.chang-order').on('change', function () {

                var data = {
                    id: $(this).data('id'),
                    type: $(this).data('cate'),
                    val: $(this).val(),
                    _token: '{{csrf_token()}}',
                };

                $.post(
                    '{{url("admin/typecate/change")}}',
                    data,
                    function (data) {
                        layer.open({
                            title: '提示',
                            content: data.msg,
                            icon: data.code,
                            success: function (layero, index) {
                            }
                        });
                    }
                );
            });

            //删除
            $('.btn-del').click(function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/typecate/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            if (data.code == 1) {
                                layer.msg(data.msg, {icon: data.code});
                                setTimeout(function () {
                                    location.href = location.href;
                                }, 1000);
                            } else {
                                layer.msg(data.msg, {icon: data.code});
                            }

                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection