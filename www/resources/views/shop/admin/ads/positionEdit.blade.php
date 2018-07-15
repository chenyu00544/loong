@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">广告管理 - 编辑广告位置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>广告相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/adspos/'.$adspos->position_id)}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>广告位名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="position_name" class="form-control"
                                       value="{{$adspos->position_name}}"
                                       placeholder="广告位名称" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>广告位终端类型：</label>
                            <div class="col-sm-2">
                                <select name="ad_terminal" id="" class="form-control">
                                    <option value="pc" @if($adspos->ad_terminal=='pc') selected @endif>PC端</option>
                                    <option value="web" @if($adspos->ad_terminal=='web') selected @endif>WEB端</option>
                                    <option value="app" @if($adspos->ad_terminal=='app') selected @endif>APP端</option>
                                    <option value="wxapp" @if($adspos->ad_terminal=='wxapp') selected @endif>小程序端
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>广告位宽度：</label>
                            <div class="col-sm-4">
                                <input type="text" name="ad_width" class="form-control" value="{{$adspos->ad_width}}"
                                       placeholder="像素" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>广告位高度：</label>
                            <div class="col-sm-4">
                                <input type="text" name="ad_height" class="form-control"
                                       value="{{$adspos->ad_height}}"
                                       placeholder="像素" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">广告位结构：</label>
                            <div class="col-sm-4">
                                <input type="text" name="position_model" class="form-control"
                                       value="{{$adspos->position_model}}"
                                       placeholder="广告位结构" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">广告位描述：</label>
                            <div class="col-sm-4">
                                <input type="text" name="position_desc" class="form-control"
                                       value="{{$adspos->position_desc}}"
                                       placeholder="广告位描述" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">广告位模板：</label>
                            <div class="col-sm-4">
                                <textarea name="position_style" id="" cols="30" rows="5" placeholder="广告位模板"
                                          class="form-control">{{$adspos->position_style}}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否公共：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_public" value="0"
                                           @if($adspos->is_public == 0) checked @endif> 否
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_public" value="1"
                                           @if($adspos->is_public == 1) checked @endif> 是
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
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
        });
    </script>
@endsection
@endsection