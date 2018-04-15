@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品设置 - 商品分类列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>商品相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist">
                <div class="clearfix">
                    <a href="{{url('admin/comcate/create')}}"
                       class="btn btn-success btn-add btn-sm fl">　添加　</a>
                    @if($rank[1] != 10)
                        <a href="javascript:history.go(-1)"
                           class="btn btn-default btn-add btn-sm">　返回　</a>
                    @endif
                </div>

                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">级别({{$rank[0]}})</th>
                            <th class="col-sm-2">分类名称</th>
                            <th class="col-sm-1">利润(%)</th>
                            <th class="col-sm-1">商品数量</th>
                            <th class="col-sm-1">数量单位</th>
                            <th class="col-sm-1">导航栏</th>
                            <th class="col-sm-1">是否显示</th>
                            <th class="col-sm-1">价格分级</th>
                            <th class="col-sm-1">排序</th>
                            <th class="col-sm-3">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($cates as $cate)
                            <tr>
                                <td><a href="{{url('admin/comcate/'.$cate->id)}}" class="btn btn-primary btn-sm">下一级</a>
                                </td>
                                <td>{{$cate->cat_name}}</td>
                                <td><input class="form-control input-sm chang-cate" type="text"
                                           data-cate="commission_rate" data-id="{{$cate->id}}"
                                           value="{{$cate->commission_rate}}"></td>
                                <td>0</td>
                                <td><input class="form-control input-sm chang-cate" type="text"
                                           data-cate="measure_unit" data-id="{{$cate->id}}"
                                           value="{{$cate->measure_unit}}"></td>
                                <td>
                                    <div class="switch switch-small">
                                        <input type="checkbox" name="show_in_nav" @if($cate->show_in_nav) checked
                                               @endif value="{{$cate->id}}"/>
                                    </div>
                                </td>
                                <td>
                                    <div class="switch switch-small">
                                        <input type="checkbox" name="is_show" @if($cate->is_show) checked
                                               @endif value="{{$cate->id}}"/>
                                    </div>
                                </td>
                                <td><input class="form-control input-sm chang-cate" type="text" data-id="{{$cate->id}}"
                                           data-cate="grade" value="{{$cate->grade}}"></td>
                                <td><input class="form-control input-sm chang-cate" type="text" data-id="{{$cate->id}}"
                                           data-cate="sort_order" value="{{$cate->sort_order}}"></td>
                                <td>
                                    <a href="{{url('admin/comcate/add/cate/'.$cate->id)}}"
                                       class="btn btn-info btn-warning btn-sm">添加子类</a>
                                    <a href="{{url('admin/comcate/'.$cate->id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a class="btn btn-danger btn-del btn-sm" data-id="{{$cate->id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center"><a href="#">copy</a></div>
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            $('[name="show_in_nav"]').bootstrapSwitch({    //初始化按钮
                onText: "开",
                offText: "关",
                onColor: "success",
                offColor: "danger",
                size: "mini",
                onSwitchChange: function (event, state) {
                    var val = 0;
                    if (state == true) {
                        val = 1;
                    } else {
                        val = 0;
                    }
                    $.post(
                        '{{url("admin/comcate/chang")}}',
                        {
                            id: $(this).val(),
                            shownav: val,
                            _token: '{{csrf_token()}}'
                        },
                        function (data) {

                        }
                    );

                }
            });

            $('[name="is_show"]').bootstrapSwitch({    //初始化按钮
                onText: "开",
                offText: "关",
                onColor: "success",
                offColor: "danger",
                size: "mini",
                onSwitchChange: function (event, state) {
                    var val = 0;
                    if (state == true) {
                        val = 1;
                    } else {
                        val = 0;
                    }
                    $.post(
                        '{{url("admin/comcate/chang")}}',
                        {
                            id: $(this).val(),
                            isshow: val,
                            _token: '{{csrf_token()}}'
                        },
                        function (data) {

                        }
                    );
                }
            });

            $('.chang-cate').change(function () {

                var cate = $(this).data('cate');
                var data = {
                    id: $(this).data('id'),
                    _token: '{{csrf_token()}}',
                };
                var type = {};
                switch (cate) {
                    case 'sort_order':
                        type = {order: $(this).val()};
                        break;
                    case 'grade':
                        type = {grade: $(this).val()};
                        break;
                    case 'measure_unit':
                        type = {measure_unit: $(this).val()};
                        break;
                    case 'commission_rate':
                        type = {commission_rate: $(this).val()};
                        break;
                    default:
                        break;
                }
                var obj = Object.assign(data, type);
                $.post(
                    '{{url("admin/comcate/chang")}}',
                    obj,
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

            $('.btn-del').click(function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/comcate/')}}/" + Id,
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
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection