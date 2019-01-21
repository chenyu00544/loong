@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>系统设置 - 添加评论标签</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>评论标签设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/commentlabel')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>评价标签名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="label_name" class="form-control" value=""
                                       placeholder="评价标签名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>英文别名：</label>
                            <div class="col-sm-3">
                                <input type="text" name="alias_en" class="form-control" value=""
                                       placeholder="英文别名">
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
        });
    </script>
@endsection
@endsection