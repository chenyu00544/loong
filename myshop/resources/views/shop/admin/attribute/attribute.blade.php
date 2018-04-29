@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 商品属性</div>
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
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/attribute/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加属性　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th style="width: 40px">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th class="col-md-1"><a>编号</a></th>
                            <th class="col-md-1"><a>属性名称</a></th>
                            <th class="col-md-1">商品类型</th>
                            <th class="col-md-1">属性类型</th>
                            <th class="col-md-1">录入方式</th>
                            <th class="col-md-3">可选值列表</th>
                            <th class="col-md-1">排序</th>
                            <th class="col-md-2 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($attributes as $attribute)
                            <tr>
                                <td>
                                    <input type="checkbox" name="checkboxes" value="{{$attribute->attr_id}}"
                                           class="checkbox check-all"
                                           id="checkbox_{{$attribute->attr_id}}">
                                </td>
                                <td>{{$attribute->attr_id}}</td>
                                <td>{{$attribute->attr_name}}</td>
                                <td>{{$goodsType[$attribute->cat_id]}}</td>
                                <td>@if($attribute->attr_type == 0)唯一属性@elseif($attribute->attr_type == 1)单选属性@else复选属性@endif</td>
                                <td>@if($attribute->attr_input_type == 0)手工录入@else列表选择@endif</td>
                                <td>{{$attribute->attr_values}}</td>
                                <td><input class="form-control input-sm chang-order" type="text"
                                           data-id="{{$attribute->attr_id}}" name="sort_order"
                                           value="{{$attribute->sort_order}}"></td>
                                <td class="text-center">
                                    <a type="button" href="{{url('admin/attribute/'.$attribute->attr_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                       data-id="{{$attribute->attr_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="clearfix bg-color-dray pad-top-4">
                        <div class="fl">
                            <a type="button" class="btn btn-info btn-sure btn-sm mar-all-8">删除</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$attributes->links()}}
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

            //全选
            $('[name="all_list"]').click(function () {
                var flage = $(this).is(':checked')
                $(".check-all").each(function () {
                    $(this).prop("checked", flage);
                })
            })

            //删除
            $('.btn-del').on('click', function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/attribute/')}}/" + Id,
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