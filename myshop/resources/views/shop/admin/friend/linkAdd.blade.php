@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 友情链接</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>该页面展示所有友情链接信息列表。</li>
                    <li>可点击链接进入相应网页，也可进行编辑或删除友情链接。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="{{url('admin/friend')}}" method="post" class="form-horizontal" enctype="multipart/form-data" >
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>链接名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="link_name" class="form-control" value=""
                                       placeholder="链接名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>链接地址：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="link_url" class="form-control" value=""
                                       placeholder="链接地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-1">
                                <input type="text" name="show_order" class="form-control" value=""
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">链接LOGO：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="file" name="link_logo" class="fl">
                            </div>
                            <input type="hidden" name="link_logo_bak" value="">
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">或LOGO地址：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="link_logo_url" class="form-control" value=""
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
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
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