@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 添加秒杀活动</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>秒杀活动相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/seckill')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>活动标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="acti_title" class="form-control input-sm" placeholder="活动标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>活动起止时间：</label>
                            <div class="col-sm-4">
                                <input type="text" style="width: 300px" name="use_start_end_date"
                                       id="use_start_end_date" class="form-control input-sm"
                                       value="{{$now_date}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">上架/下架：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_putaway" value="1" checked> 上架
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_putaway" value="0"> 下架
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">审核：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="review_status" value="1" checked> 未审核
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="review_status" value="3"> 审核通过
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="review_status" value="2"> 审核未通过
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="col-sm-4">
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

            $('#use_start_end_date').daterangepicker(optionDateSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm');
                var t = s + '～' + e + ':59';
                $('#use_start_end_date').val(t);
            });
        });
    </script>
@endsection
@endsection