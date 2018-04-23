@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商城设置 - 品牌管理</div>
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
                    <a href="{{url('admin/brand/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                    <a href="javascript:;"
                       class="btn btn-danger btn-getfc btn-sm">　生成首字母　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">编号</th>
                            <th class="col-sm-2">品牌中文名称</th>
                            <th class="col-sm-2">品牌英文名称</th>
                            <th class="col-sm-1">首字母</th>
                            <th class="col-sm-1">品牌图片</th>
                            <th class="col-sm-2">品牌描述</th>
                            <th class="col-sm-1">加入推荐</th>
                            <th class="col-sm-1">是否显示</th>
                            <th class="col-sm-1">排序</th>
                            <th class="col-sm-4">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($brands as $brand)
                            <tr>
                                <td>{{$brand->id}}</td>
                                <td>{{$brand->brand_name}}</td>
                                <td>{{$brand->brand_letter}}</td>
                                <td>{{$brand->brand_first_char}}</td>
                                <td>
                                    <span class="show">
                                            <a href="{{url($brand->brand_logo)}}" class="nyroModal">
                                                <i class="glyphicon glyphicon-picture top5" data-tooltipimg="{{url($brand->brand_logo)}}"
                                                   ctype="tooltip" title="tooltip"></i>
                                            </a>
                                        </span>
                                </td>
                                <td>{{$brand->brand_desc}}</td>
                                <td>
                                    <div class="switch switch-small">
                                        <input type="checkbox" name="is_recommend" @if($brand->is_recommend) checked
                                               @endif value="{{$brand->id}}"/>
                                    </div>
                                </td>
                                <td>
                                    <div class="switch switch-small">
                                        <input type="checkbox" name="is_show" @if($brand->is_show) checked
                                               @endif value="{{$brand->id}}"/>
                                    </div>
                                </td>
                                <td><input class="form-control input-sm chang-order" type="text"
                                           data-id="{{$brand->id}}" name="sort_order" value="{{$brand->sort_order}}">
                                </td>
                                <td>
                                    <a type="button" href="{{url('admin/brand/'.$brand->id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$brand->id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$brands->links()}}
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

            $('[name="is_recommend"]').bootstrapSwitch({    //初始化按钮
                onText: "开",
                offText: "关",
                onColor: "success",
                offColor: "danger",
                size: "mini",
                onSwitchChange: function (event, state) {
                    if (state == true) {
                        $.post(
                            '{{url("admin/brand/change")}}',
                            {
                                id: $(this).val(),
                                type: 'is_recommend',
                                is_recommend: '1',
                                _token: '{{csrf_token()}}'
                            },
                            function (data) {

                            }
                        );
                    } else {
                        $.post(
                            '{{url("admin/brand/change")}}',
                            {
                                id: $(this).val(),
                                type: 'is_recommend',
                                is_recommend: '0',
                                _token: '{{csrf_token()}}'
                            },
                            function (data) {

                            }
                        );
                    }
                }
            });

            $('[name="is_show"]').bootstrapSwitch({    //初始化按钮
                onText: "开",
                offText: "关",
                onColor: "success",
                offColor: "danger",
                size: "mini",
                onSwitchChange: function (event, state) {
                    if (state == true) {
                        $.post(
                            '{{url("admin/brand/change")}}',
                            {
                                id: $(this).val(),
                                type: 'opennew',
                                is_show: '1',
                                _token: '{{csrf_token()}}'
                            },
                            function (data) {

                            }
                        );
                    } else {
                        $.post(
                            '{{url("admin/brand/change")}}',
                            {
                                id: $(this).val(),
                                type: 'opennew',
                                is_show: '0',
                                _token: '{{csrf_token()}}'
                            },
                            function (data) {

                            }
                        );
                    }
                }
            });

            $('.chang-order').change(function () {
                $.post(
                    '{{url("admin/brand/change")}}',
                    {
                        id: $(this).data('id'),
                        order: $(this).val(),
                        _token: '{{csrf_token()}}'
                    },
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

            $('.btn-getfc').click(function () {
                $.post(
                    '{{url('admin/brand/firstchar')}}',
                    {
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.open({
                            title: '提示',
                            content: data.msg,
                            icon: data.code,
                            success: function (layero, index) {
                                location.href = location.href;
                            }
                        });
                    }
                );
            });

            $('.btn-del').click(function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/brand/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            if (data.code == 1) {
                                layer.msg(data.msg, {icon: data.code});
                                setTimeout(function () {
                                    location.href = location.href;
                                }, 2000);
                            } else {
                                layer.msg(data.msg, {icon: data.code});
                            }

                        });
                    // layer.msg('的确很重要', {icon: 1});
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection