@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 商品类型</div>
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
                    <a href="{{url('admin/goodstype/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加商品类型　</a>

                    <div class="fr wd250">
                        <form action="{{url('admin/goodstype')}}" method="get">
                            {{csrf_field()}}
                            <input type="text" name="keywords" value="" class="form-control input-sm max-wd-190"
                                   placeholder="名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="col-md-1"><a>编号</a></th>
                            <th class="col-md-3"><a>商品类型名称</a></th>
                            <th class="col-md-1">商家名称</th>
                            <th class="col-md-1">属性分组</th>
                            <th class="col-md-1">类型分类</th>
                            <th class="col-md-1">属性数</th>
                            <th class="col-md-1">状态</th>
                            <th class="col-md-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($goodsTypes as $type)
                            <tr>
                                <td>{{$type->cat_id}}</td>
                                <td>{{$type->cat_name}}</td>
                                <td><font class="red">直营</font></td>
                                <td>{{$type->attr_group}}</td>
                                <td>{{$typeCates[$type->c_id]}}</td>
                                <td>{{$type->enabled}}</td>
                                <td>@if($type->enabled == 1) <img src="{{url('styles/images/yes.png')}}"
                                                                  class="pointer"> @else <img
                                            src="{{url('images/no.png')}}" class="pointer"> @endif</td>
                                <td class="text-center">
                                    <a type="button" href="{{url('admin/attribute/'.$type->cat_id)}}"
                                       class="btn btn-primary btn-examine btn-sm mar-all-5">属性列表</a>
                                    <a type="button" href="{{url('admin/goodstype/'.$type->cat_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                       data-id="{{$type->cat_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$goodsTypes->links()}}
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

            //删除
            $('.btn-del').on('click', function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/goodstype/')}}/" + Id,
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