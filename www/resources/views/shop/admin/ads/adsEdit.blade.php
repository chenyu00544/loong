@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">广告管理 - 添加广告</div>
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
                    <form name="conf" action="{{url('admin/ad/'.$adInfo->ad_id)}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <input type="hidden" name="ad_type" value="0">

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>广告名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="ad_name" class="form-control" value="{{$adInfo->ad_name}}"
                                       placeholder="广告名称" autocomplete="off" data-name="{{$adInfo->ad_name}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">广告名称序号：</label>
                            <div class="col-sm-3">
                                <div class="quantity-form">
                                    <a href="javascript:;" class="num_reduce btn btn-info fl wd-40 btn-sm">-</a>
                                    <input type="text" value="1" class="form-control wd-80 fl input-sm num_id" readonly>
                                    <a href="javascript:;" class="num_add btn btn-info fl wd-40 btn-sm">+</a>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>广告位置：</label>
                            <div class="col-sm-4">
                                <input type="hidden" name="ad_terminal" value="{{$type}}">
                                <select name="position_id" id="" class="form-control">
                                    @foreach($adsposes as $adspos)
                                        <option value="{{$adspos->position_id}}"
                                                @if($adspos->position_id == $adInfo->position_id) selected @endif >{{$adspos->position_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>起止日期：</label>
                            <div class="col-sm-4">
                                <input type="text" style="width: 300px" name="start_end_time"
                                       id="start_end_time" class="form-control input-sm"
                                       value="{{date('Y-m-d H:i:s', $adInfo->start_time).'～'.date('Y-m-d H:i:s', $adInfo->end_time)}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>上传广告图片：</label>
                            <div class="col-sm-4">
                                <input type="file" name="ad_img" value="" class="fl">
                                <span class="img-show fl">
                                    <a href="{{url($adInfo->ad_code)}}" target="_blank" class="nyroModal">
                                        <i class="glyphicon glyphicon-picture top5"
                                           data-tooltipimg="{{url($adInfo->ad_code)}}" ectype="tooltip"
                                           data-toggle="tooltip" title="tooltip"></i>
                                    </a>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">或图片网址：</label>
                            <div class="col-sm-4">
                                <input type="text" name="img_url" class="form-control" value="{{$adInfo->img_url}}"
                                       placeholder="或图片网址" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">广告链接：</label>
                            <div class="col-sm-4">
                                <input type="text" name="ad_link" class="form-control" value="{{$adInfo->ad_link}}"
                                       placeholder="广告链接" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">广告大标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="b_title" class="form-control" value="{{$adInfo->b_title}}"
                                       placeholder="广告大标题" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">广告小标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="s_title" class="form-control" value="{{$adInfo->s_title}}"
                                       placeholder="广告小标题" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">背景颜色：</label>
                            <div class="col-sm-4">
                                <input type="text" name="link_color" class="form-control"
                                       value="{{$adInfo->link_color}}"
                                       placeholder="背景颜色" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否开启：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="enabled" value="1"
                                           @if($adInfo->enabled ==1) checked @endif> 开启
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="enabled" value="0"
                                           @if($adInfo->enabled ==0) checked @endif> 关闭
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">广告联系人：</label>
                            <div class="col-sm-4">
                                <input type="text" name="link_man" class="form-control" value="{{$adInfo->link_man}}"
                                       placeholder="广告联系人" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">联系人Email：</label>
                            <div class="col-sm-4">
                                <input type="text" name="link_email" class="form-control"
                                       value="{{$adInfo->link_email}}"
                                       placeholder="联系人Email" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">联系电话：</label>
                            <div class="col-sm-4">
                                <input type="text" name="link_phone" class="form-control"
                                       value="{{$adInfo->link_phone}}"
                                       placeholder="联系电话" autocomplete="off">
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
    <script type="text/javascript"
            src="{{url('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.js')}}"></script>
    <script>
        $(function () {
            $('.nyroModal').nyroModal();
            var optionSet = {
                timePicker: true,
                timePickerIncrement: 1,
                format: 'YYYY-MM-DD hh:mm:ss',
                timePicker24Hour: true,
                timePickerSeconds: true,
                locale: {
                    "separator": " -222 ",
                    "applyLabel": "确定",
                    "cancelLabel": "取消",
                    "fromLabel": "起始",
                    "toLabel": "结束",
                    "customRangeLabel": "自定义",
                    "weekLabel": "W",
                    "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
                    "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                    "firstDay": 1
                }
            };
            $('#start_end_time').daterangepicker(optionSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm:ss');
                var t = s + '～' + e;
                $('#start_end_time').val(t);
            });

            $('input[name=ad_name]').change(function () {
                var ad_name = $('input[name=ad_name]').val();
                $('input[name=ad_name]').data('name', ad_name);
            });

            $('.num_reduce').on('click', function () {
                var ad_name = $('input[name=ad_name]').data('name');
                var id = parseInt($('.num_id').val());
                if (id == 1) {
                    return;
                } else {
                    id -= 1;
                }
                $('.num_id').val(id);
                $('input[name=ad_name]').val(ad_name + '_' + id);
            });
            $('.num_add').on('click', function () {
                var ad_name = $('input[name=ad_name]').data('name');
                var id = parseInt($('.num_id').val());
                id += 1;
                $('.num_id').val(id);
                $('input[name=ad_name]').val(ad_name + '_' + id);
            });
        });
    </script>
@endsection
@endsection