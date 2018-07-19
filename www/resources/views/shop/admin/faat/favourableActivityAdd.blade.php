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
                            <div class="col-sm-8">
                                <div class="checkbox">
                                    @foreach($userRanks as $userRank)
                                        <label class="mar-right-10">
                                            <input type="checkbox" name="rank[]"
                                                   value="{{$userRank->rank_id}}">{{$userRank->rank_name}}
                                        </label>
                                    @endforeach
                                </div>
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
                            <label class="col-sm-4 control-label"><b>*</b>使用类型：</label>
                            <div class="col-sm-4">
                                <select name="userFav_type" class="form-control select input-sm wd120">
                                    <option value="0">自主使用</option>
                                    <option value="1">全场通用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="c_id" value="0">
                            <label class="col-sm-4 control-label"><b>*</b>优惠范围：</label>
                            <div class="col-sm-4">
                                <select name="act_range" class="form-control select input-sm wd120">
                                    <option value="0">全部商品</option>
                                    <option value="1">以下分类</option>
                                    <option value="2">以下品牌</option>
                                    <option value="3">以下商品</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group rang-ext-val">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-8">
                                <div class="checkbox bg-eee pad-bt-10">
                                    <label class="mar-all-10 db">
                                        <input type="checkbox" name="act_range_ext[]"
                                               value="12312" checked>搜索并加入优惠范围
                                    </label>
                                    <label class="mar-all-10 db">
                                        <input type="checkbox" name="act_range_ext[]"
                                               value="12312" checked>搜索并加入优惠范围
                                    </label>
                                    <label class="mar-all-10 db">
                                        <input type="checkbox" name="act_range_ext[]"
                                               value="12312" checked>搜索并加入优惠范围
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group rang-ext" style="display: none;">
                            <input type="hidden" name="c_id" value="0">
                            <label class="col-sm-4 control-label"><b>*</b>搜索并加入优惠范围：</label>
                            <div class="col-sm-6">
                                <input type="text" class="keyword-1 form-control wd-120 input-sm fl" placeholder="关键字">
                                <a href="javascript:;"
                                   class="btn btn-info input-sm btn-search-1 fl mar-left-10">搜索</a>
                                <div class="cate-option fl">
                                    <select name="result_val_1" class="form-control select input-sm wd250">
                                        <option value="0">请选择</option>
                                    </select>
                                </div>
                                <a href="javascript:;" class="btn btn-info input-sm btn-add-1 fl mar-left-10">添加</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>金额下限：</label>
                            <div class="col-sm-3">
                                <input type="text" name="min_amount" class="form-control input-sm" value=""
                                       placeholder="金额下限">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>金额上限：</label>
                            <div class="col-sm-3">
                                <input type="text" name="max_amount" class="form-control input-sm" value=""
                                       placeholder="金额上限">
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="c_id" value="0">
                            <label class="col-sm-4 control-label"><b>*</b>优惠方式：</label>
                            <div class="col-sm-4">
                                <select name="act_type" class="form-control select input-sm wd120 fl">
                                    <option value="0">享受赠品（特惠品）</option>
                                    <option value="1">享受现金减免</option>
                                    <option value="2">享受价格折扣</option>
                                </select>
                                <input type="text" name="act_type_ext"
                                       class="form-control input-sm wd-80 fl mar-left-10" value=""
                                       placeholder="数值">
                            </div>
                        </div>
                        <div class="form-group act-type-ext" style="display: none;">
                            <input type="hidden" name="c_id" value="0">
                            <label class="col-sm-4 control-label"><b>*</b>搜索并加入赠品（特惠品）：</label>
                            <div class="col-sm-6">
                                <input type="text" class="keyword_2 form-control wd-120 input-sm fl" placeholder="关键字">
                                <a href="javascript:;"
                                   class="btn btn-info input-sm btn-search-2 fl mar-left-10">搜索</a>
                                <div class="cate-option fl">
                                    <select name="result_val_2" class="form-control select input-sm wd250">
                                        <option value="0">请选择</option>
                                    </select>
                                </div>
                                <a href="javascript:;" class="btn btn-info input-sm btn-add-2 fl mar-left-10">添加</a>
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