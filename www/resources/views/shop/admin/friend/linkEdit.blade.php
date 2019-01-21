@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>图片库 - 编辑相册</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>该页面展示所有友情链接信息列表。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="{{url('admin/friend/'.$link->link_id)}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>链接名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="link_name" class="form-control" value="{{$link->link_name}}"
                                       placeholder="导航名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>链接地址：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="link_url" class="form-control" value="{{$link->link_url}}"
                                       placeholder="链接地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-1">
                                <input type="text" name="show_order" class="form-control" value="{{$link->show_order}}"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">链接LOGO：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="file" name="link_logo" class="fl">
                                <a href="{{url($link->link_logo)}}" target="_blank" class="nyroModal">
                                    <i class="glyphicon glyphicon-picture top5" data-tooltipimg="{{url($link->link_logo)}}" ectype="tooltip" data-toggle="tooltip" title="tooltip"></i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">或LOGO地址：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="link_logo_url" class="form-control" value="{{$link->link_logo}}"
                                       placeholder="或LOGO地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20" href="javascript:history.go(-1)" >返回</a>
                            </div>
                        </div>
                    </form>
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
        });
    </script>
@endsection
@endsection