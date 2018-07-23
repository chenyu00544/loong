@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 添加注册项设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>注册项相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/regfields')}}" method="post" class="form-horizontal">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>注册项名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="reg_field_name" class="form-control" value=""
                                       placeholder="注册项名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序权值：</label>
                            <div class="col-sm-3">
                                <input type="text" name="dis_order" class="form-control" value="0"
                                       placeholder="排序权值">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="display" value="1" checked> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="display" value="0"> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否必填：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_need" value="1" checked> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_need" value="0"> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
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