@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品设置 - 添加类型</div>
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
                    <form name="brand" action="{{url('admin/goodstype')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠活动名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="act_name" class="form-control input-sm" value=""
                                       placeholder="优惠活动名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠起止时间：</label>
                            <div class="col-sm-4">
                                <input type="text" style="width: 300px" name="start_end_date"
                                       id="start_end_date" class="form-control input-sm"
                                       value="{{$now_date}} 00:00:00～{{$now_date}} 23:59:59">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>享受优惠的会员等级：</label>
                            <div class="col-sm-4">
                                <input type="checkbox" name="user_rank[]" id="is_best" value="0">
                                <label class="ui-label mar-left-5" for="is_best">精品</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>上传图片：</label>
                            <div class="col-sm-4">
                                <input type="file" name="activity_thumb">
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="hidden" name="c_id" value="0">
                            <label class="col-sm-4 control-label">使用类型：</label>
                            <div class="col-sm-4 pre-cate">
                                <div class="cate-option fl">
                                    <select name="userFav_type" class="form-control select input-sm">
                                        <option value="0">自主使用</option>
                                        <option value="1">全场通用</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">属性分组：</label>
                            <div class="col-sm-4 n-wd400">
                                <textarea name="attr_group" class="form-control ww" row="5"
                                          placeholder="每行一个商品属性组。排序也将按照自然顺序排序。"
                                          style="min-height:100px;"></textarea>
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
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script type="text/javascript"
            src="{{url('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.js')}}"></script>
    <script>
        $(function () {
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
            $('#start_end_date').daterangepicker(optionSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm:ss');
                var t = s + '～' + e;
                $('#start_end_date').val(t);
            });
        });

    </script>
@endsection
@endsection