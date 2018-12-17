@extends('shop.layouts.index')
@section('css')
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.css')}}">
@endsection
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">品牌设置 - 添加品牌</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>商店相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/brand/'.$brand->id)}}" method="post"
                          class="form-horizontal" enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>品牌中文名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="brand_name" class="form-control" value="{{$brand->brand_name}}"
                                       placeholder="品牌中文名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">品牌英文名称：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="brand_letter" class="form-control"
                                       value="{{$brand->brand_letter}}"
                                       placeholder="品牌英文名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">品牌首字母：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="brand_first_char" class="form-control"
                                       value="{{$brand->brand_first_char}}"
                                       placeholder="品牌首字母">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">品牌网址：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="site_url" class="form-control" value="{{$brand->site_url}}"
                                       placeholder="品牌网址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">主题颜色：</label>
                            <div class="col-sm-3">
                                <input id="color-picker" type="text" name="color"
                                       class="form-control max-wd-100 input-sm" value="{{$brand->color}}"
                                       style="background: {{$brand->color}};color: #ffffff;" autocomplete="off">
                                <div class="form-prompt"></div>
                                <div class="notic fl mar-left-10"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>品牌LOGO：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="file" name="brand_logo" class="fl">
                                <input type="hidden" name="brand_logo_path" value="{{$brand->brand_logo}}">
                                <span class="img-show fl">
                                    <a href="{{url($brand->brand_logo_oss)}}" target="_blank" class="nyroModal">
                                        <i class="glyphicon glyphicon-picture top5"
                                           data-tooltipimg="{{url($brand->brand_logo_oss)}}"
                                           ectype="tooltip" data-toggle="tooltip" title="tooltip"></i>
                                    </a>
                                </span>
                                <a href="" class="btn btn-danger fr btn-sm">　删除　</a>
                            </div>
                            <div class="notic col-sm-3">请上传图片，做为品牌的LOGO！标准尺寸200*88</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">品牌专区大图：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="file" name="index_img" class="fl">
                                <input type="hidden" name="index_img_path" value="{{$brand->index_img}}">
                                <span class="img-show fl">
                                    <a href="{{url($brand->index_img_oss)}}" target="_blank" class="nyroModal">
                                        <i class="glyphicon glyphicon-picture top5"
                                           data-tooltipimg="{{url($brand->index_img_oss)}}"
                                           ectype="tooltip" data-toggle="tooltip" title="tooltip"></i>
                                    </a>
                                </span>
                                <a href="" class="btn btn-danger fr btn-sm">　删除　</a>
                            </div>
                            <div class="notic col-sm-3">标准尺寸278*285</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>App品牌的背景：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="file" name="brand_bg_app" class="fl">
                                <input type="hidden" name="brand_bg_app_path" value="{{$brand->brand_bg_app}}">
                                <span class="img-show fl">
                                    <a href="{{url($brand->brand_bg_app_oss)}}" target="_blank" class="nyroModal">
                                        <i class="glyphicon glyphicon-picture top5" data-tooltipimg="{{url($brand->brand_bg_app_oss)}}"
                                           ectype="tooltip" data-toggle="tooltip" title="tooltip"></i>
                                    </a>
                                </span>
                                <a href="" class="btn btn-danger fr btn-sm">　删除　</a>
                            </div>
                            <div class="notic col-sm-3">请上传图片，做为App品牌的背景！标准尺寸750*300</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">品牌背景图：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="file" name="brand_bg" class="fl">
                                <input type="hidden" name="brand_bg_path" value="{{$brand->brand_bg}}">
                                <span class="img-show fl">
                                    <a href="{{url($brand->brand_bg_oss)}}" target="_blank"
                                       class="nyroModal">
                                        <i class="glyphicon glyphicon-picture top5"
                                           data-tooltipimg="{{url($brand->brand_bg_oss)}}"
                                           ectype="tooltip" data-toggle="tooltip" title="tooltip"></i>
                                    </a>
                                </span>
                                <a href="" class="btn btn-danger fr btn-sm">　删除　</a>
                            </div>
                            <div class="notic col-sm-3">品牌详情页头部分类背景，建议尺寸：1920*200</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">品牌描述：</label>
                            <div class="col-sm-4 n-wd400">
                                <textarea name="brand_desc" class="form-control ww" row="5" placeholder="品牌描述"
                                          style="min-height:100px;">{{$brand->brand_desc}}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-4">
                                <input type="text" name="sort_order" class="form-control" value="{{$brand->sort_order}}"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_show" value="1"
                                           @if($brand->is_show == 1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_show" value="0"
                                           @if($brand->is_show == 0) checked @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">加入推荐：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_recommend" value="1"
                                           @if($brand->is_recommend == 1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_recommend" value="0"
                                           @if($brand->is_recommend == 0) checked @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20" href="javascript:history.go(-1)">返回</a>
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
    <script type="text/javascript"
            src="{{url('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.js')}}"></script>
    <script>
        $(function () {
            $('.nyroModal').nyroModal();

            //选择颜色
            $('#color-picker').colorpicker();
            $('#color-picker').on('change', function () {
                $(this).css('background', $(this).val());
                $(this).css('color', '#fff');
            });
        });
    </script>
@endsection
@endsection