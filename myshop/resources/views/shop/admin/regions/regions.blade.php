@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 地区列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>在新增一级地区点击管理进入下一级地区，可进行删除和编辑。</li>
                    <li>地区用于商城定位，请根据商城实际情况谨慎设置。</li>
                    <li>生成地区首字母是方便根据地区首字母搜索相对应的地区</li>
                    <li>地区层级关系必须为中国→省/直辖市→市→县，地区暂只支持到四级地区其后不显示，暂不支持国外</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($typeNav == 'express') curr @endif fl">
                        <a href="{{url('admin/express')}}">快递配置</a>
                    </li>
                    <li class="@if($typeNav == 'transport') curr @endif fl">
                        <a href="{{url('admin/transport')}}">运费管理</a>
                    </li>
                    <li class="@if($typeNav == 'regions') curr @endif fl">
                        <a href="{{url('admin/regions')}}">地区列表</a>
                    </li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    @if(empty($level))<a href="javascript:history.go(-1)"
                                         class="btn btn-success btn-add btn-sm">返回上一级</a>@endif
                    <a href="{{url('admin/regions/create')}}" class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="col-md-3">地区名称</th>
                            <th class="col-md-3">所在层级</th>
                            <th class="col-md-3">所属地区</th>
                            <th class="col-md-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($regions as $region)
                            <tr>
                                <td><input type="text" class="form-control xwd100 region_name"
                                           data-id="{{$region->region_id}}" value="{{$region->region_name}}"></td>
                                <td>{{$region->region_type+1}}级地区</td>
                                <td>{{$region->parent_region}}</td>
                                <td class="text-center">
                                    <a type="button"
                                       href="{{url('admin/regions/nextregions/'.$region->region_id.'/'.$region->region_type)}}"
                                       class="btn btn-info btn-edit btn-sm mar-all-5">管理</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                       data-id="{{$region->region_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
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

            $('.region_name').on('change', function () {
                var id = $(this).data('id');
                var region_name = $(this).val();
                $.post('{{url("admin/regions/changes")}}', {id: id, '_token': '{{csrf_token()}}', region_name:region_name}, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                })
            })

            //删除
            $('.btn-del').on('click', function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/regions/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                setTimeout(function () {
                                    location.href = location.href;
                                }, 1000);
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection