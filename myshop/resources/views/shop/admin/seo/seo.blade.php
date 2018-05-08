@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - SEO设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>插入的变量必需包括花括号“{}”，当应用范围不支持该变量时，该变量将不会在前台显示(变量后边的分隔符也不会显示)，留空为系统默认设置，SEO自定义支持手写。以下是可用SEO变量:</li>
                    <li>站点名称 {sitename}，（应用范围：全站）</li>
                    <li>店铺名称 {shopname}，（应用范围：店铺页）</li>
                    <li>名称 {name}，（应用范围：团购名称、商品名称、品牌名称、文章标题、分类名称）</li>
                    <li>关键词 {key}，（应用范围：商品关键词、文章关键词、店铺关键词）</li>
                    <li>简单描述 {description. }，（应用范围：商品描述、文章网页描述、店铺关键词）</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($seoNav == 'home') curr @endif fl">
                        <a href="{{url('admin/seo')}}">首页</a>
                    </li>
                    <li class="@if($seoNav == 'brand') curr @endif fl">
                        <a href="{{url('admin/seo/brand')}}">品牌</a>
                    </li>
                    <li class="@if($seoNav == 'goods') curr @endif fl">
                        <a href="{{url('admin/seo/goods')}}">商品</a>
                    </li>
                    <li class="@if($seoNav == 'goodsCate') curr @endif fl">
                        <a href="{{url('admin/seo/goodscate')}}">商品分类</a>
                    </li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="{{url('admin/seo')}}" class="form-horizontal" method="post" enctype="multipart/form-data">
                        {{csrf_field()}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label">title：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="title" class="form-control shop_name" value="{{$seo->title}}"
                                       placeholder="title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">keywords：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="keywords" class="form-control shop_name" value="{{$seo->keywords}}"
                                       placeholder="keywords">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">description：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="description" class="form-control shop_name" value="{{$seo->description}}"
                                       placeholder="description">
                            </div>
                        </div>
                        <div class="item">
                            <div class="label">&nbsp;</div>
                            <div class="">
                                <input type="hidden" name="type" value="{{$seoNav}}">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                            </div>
                        </div>

                    </form>
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

            //添加快递
            $('.btn-install').on('click', function () {
                var code = $(this).data('code');
                $.post(
                    "{{url('admin/express/install')}}/" + code,
                    {'_token': '{{csrf_token()}}'},
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                        if (data.code == 1) {
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        }
                    }
                );
            });

            //编辑快递
            $('.btn-edit').on('click', function () {
                var id = $(this).data('id');
                layer.open({
                    type: 2,
                    area: ['500px', '380px'],
                    fixed: true, //固定
                    maxmin: true,
                    title: '编辑',
                    content: ["{{url('admin/express')}}/" + id + "/edit", 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            //删除
            $('.btn-del').on('click', function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/express/')}}/" + Id,
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