@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">图片库 - 图片库管理</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>该页面展示图片列表。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix">
                    <a href="JavaScript:;" class="btn btn-success btn-add btn-sm fl">添加图片</a>
                </div>
                <div class="main-info">
                    <ul class="image-item">
                        @foreach($galleryPics as $galleryPic)
                            <li class="image-wrap">
                                <div class="img-container">
                                    <img src="">
                                </div>
                                <div class="checkbox-item">
                                    <input type="checkbox" name="checkboxes[]" value="" class="ui-checkbox">
                                </div>
                                <div class="img-width" style="display: block;">232x330(3.58k)</div>
                                <div class="img-handle" style="display: none;">
                                    <a href="javaScript:;" class=""><i
                                                class="sc-icon icon-move"></i>转移相册</a>
                                    <a href="javaScript:;" class=""><i
                                                class="sc-icon icon-trash"></i>移除</a>
                                </div>
                            </li>
                        @endforeach
                    </ul>
                </div>
                <div class="page_list">
                    {{$galleryPics->links()}}
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
            //上传图片窗口
            $('.btn-add').click(function () {
                layer.open({
                    type: 2,
                    area: ['700px', '250px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '上传图片',
                    content: ["{{url('admin/gallery/uppicview')}}", 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
        });
    </script>
@endsection
@endsection