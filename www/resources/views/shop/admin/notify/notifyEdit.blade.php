@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">通知管理 - 编辑通知</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>位置相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/notify/'.$notify->id)}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="title" class="form-control" value="{{$notify->title}}"
                                       placeholder="标题" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>终端类型：</label>
                            <div class="col-sm-2">
                                <select name="terminal" id="" class="form-control">
                                    <option value="app" @if($notify->terminal == "app") selected @endif>APP端</option>
                                    <option value="wxapp" @if($notify->terminal == "wxapp") selected @endif>小程序端
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">通知类型：</label>
                            <div class="col-sm-3">
                                <select name="type" id="" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="1" @if($notify->type == 1) selected @endif>文章</option>
                                    <option value="2" @if($notify->type == 2) selected @endif>事件</option>
                                    <option value="3" @if($notify->type == 3) selected @endif>促销</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>描述：</label>
                            <div class="col-sm-4">
                                <input type="text" name="describe" class="form-control" value="{{$notify->describe}}"
                                       placeholder="描述" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">内容：</label>
                            <div class="col-sm-4">
                                <textarea name="content" id="" cols="15" rows="5" class="form-control"
                                          placeholder="内容">{{$notify->content}}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">图片：</label>
                            <div class="col-sm-4">
                                <input type="file" name="img" class="fl">
                                <input type="hidden" name="img_old" value="{{$notify->img}}" class="fl">
                                <span class="img-show fl">
                                    <a href="{{url($notify->img_oss)}}" target="_blank" class="nyroModal">
                                        <i class="glyphicon glyphicon-picture top5"
                                           data-tooltipimg="{{$notify->img_oss}}" ectype="tooltip"
                                           data-toggle="tooltip" title="tooltip"></i>
                                    </a>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">手机端跳转链接：</label>
                            <div class="col-sm-4">
                                <input type="text" name="url" class="form-control" value="{{$notify->url}}"
                                       placeholder="手机端跳转链接" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否通知：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_notify" value="0"
                                           @if($notify->is_notify == 0) checked @endif> 否
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_notify" value="1"
                                           @if($notify->is_notify == 1) checked @endif> 是
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
            $('.nyroModal').nyroModal();
        });
    </script>
@endsection
@endsection