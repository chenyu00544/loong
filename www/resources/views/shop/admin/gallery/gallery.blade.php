@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 图片库管理</div>
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
            <div class="fromlist clearfix">
                <div class="clearfix">
                    @if($rank[1] != 10)
                        <a href="javascript:history.go(-1)"
                           class="btn btn-default btn-add btn-sm fl">　返回　</a>
                    @endif
                    <a href="{{url('admin/gallery/create')}}"
                       class="btn btn-success btn-add btn-sm fl">　添加　</a>
                </div>

                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">级别</th>
                            <th class="col-sm-1">编号</th>
                            <th class="col-sm-2">相册名称</th>
                            <th class="col-sm-1">图片数量</th>
                            <th class="col-sm-1">店铺名称</th>
                            <th class="col-sm-1">封面</th>
                            <th class="col-sm-2">描述</th>
                            <th class="col-sm-1">排序</th>
                            <th class="col-sm-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($gallerys as $gallery)
                            <tr>
                                <td>
                                    <a href="{{url('admin/gallery/'.$gallery->album_id)}}"
                                       class="btn btn-primary btn-sm">下一级</a>
                                </td>
                                <td>{{$gallery->album_id}}</td>
                                <td>{{$gallery->album_name}}</td>
                                <td>{{$countPics[$gallery->album_id]}}</td>
                                <td>@if($gallery->ru_id===0) <em>自营</em> @else <em>其他</em> @endif</td>
                                <td>
                                    <span class="show">
                                        <a href="@if($gallery->album_cover){{url($gallery->album_cover)}}@else{{url('styles/images/no_image.png')}}@endif"
                                           class="nyroModal">
                                            <i class="glyphicon glyphicon-picture top2"
                                               data-tooltipimg="@if($gallery->album_cover){{url($gallery->album_cover)}}@else{{url('styles/images/no_image.png')}}@endif"
                                               ctype="tooltip" title="tooltip"></i>
                                        </a>
                                    </span>
                                </td>
                                <td>{{$gallery->album_desc}}</td>
                                <td>
                                    <input class="form-control input-sm change-order" type="text"
                                           data-id="{{$gallery->id}}" data-order="order"
                                           value="{{$gallery->sort_order}}">
                                </td>
                                <td class="text-center">
                                    <a href="{{url('admin/gallery/galleryview/'.$gallery->album_id)}}"
                                       class="btn btn-info btn-edit btn-sm">查看</a>
                                    <a href="{{url('admin/gallery/'.$gallery->album_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a class="btn btn-danger btn-del btn-sm" data-url="{{$gallery->album_cover}}"
                                       data-id="{{$gallery->album_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$gallerys->links()}}
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

            $('.change-order').change(function () {

                var data = {
                    id: $(this).data('id'),
                    type: $(this).data('order'),
                    val: $(this).val(),
                    _token: '{{csrf_token()}}',
                };

                $.post(
                    '{{url("admin/gallery/changes")}}',
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

            $('.btn-del').click(function () {
                var Id = $(this).data('id');
                var url = $(this).data('url');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/gallery/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}', url: url},
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