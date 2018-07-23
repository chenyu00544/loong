@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 红包活动</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>红包活动相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/bonus/'.$bonus->bonus_id)}}" method="post"
                          class="form-horizontal" enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>活动名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="type_name" class="form-control input-sm"
                                       value="{{$bonus->type_name}}"
                                       placeholder="活动名称" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>红包金额：</label>
                            <div class="col-sm-4">
                                <input type="text" name="type_money" class="form-control input-sm"
                                       value="{{$bonus->type_money}}"
                                       placeholder="红包可以抵销的金额" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>最小订单金额：</label>
                            <div class="col-sm-4">
                                <input type="text" name="min_goods_amount" class="form-control input-sm"
                                       value="{{$bonus->min_goods_amount}}"
                                       placeholder="只有商品总金额达到这个数的订单才能使用这种红包" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>使用起止日期：</label>
                            <div class="col-sm-4">
                                <input type="text" style="width: 300px" name="use_start_end_date"
                                       id="use_start_end_date" class="form-control input-sm"
                                       value="{{date('Y-m-d H:i:s',$bonus->use_start_date)}}～{{date('Y-m-d H:i:s',$bonus->use_end_date)}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">使用类型：</label>
                            <div class="col-sm-4">
                                <select name="usebonus_type" class="form-control select input-sm wd120">
                                    <option value="0" @if($bonus->usebonus_type==0) selected @endif>自主使用</option>
                                    <option value="1" @if($bonus->usebonus_type==1) selected @endif>全场通用</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">如何发放红包：</label>
                            <div class="col-sm-4">
                                <select name="send_type" class="form-control select input-sm wd120">
                                    <option value="0" @if($bonus->send_type==0) selected @endif>按用户发放</option>
                                    <option value="1" @if($bonus->send_type==1) selected @endif>按商品发放</option>
                                    <option value="2" @if($bonus->send_type==2) selected @endif>按订单金额发放</option>
                                    <option value="3" @if($bonus->send_type==3) selected @endif>线下发放的红包</option>
                                    <option value="4" @if($bonus->send_type==4) selected @endif>自行领取</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group send_date"
                             style="@if($bonus->send_type!=1 && $bonus->send_type!=2) display: none; @endif">
                            <label class="col-sm-4 control-label"><b>*</b>发放起止日期：</label>
                            <div class="col-sm-4">
                                <input type="text" style="width: 300px" name="send_start_end_date"
                                       id="send_start_end_date" class="form-control input-sm"
                                       value="{{date('Y-m-d H:i:s',$bonus->send_start_date)}}～{{date('Y-m-d H:i:s',$bonus->send_end_date)}}">
                            </div>
                        </div>
                        <div class="form-group min_amount" style="@if($bonus->send_type!=2) display: none; @endif">
                            <label class="col-sm-4 control-label"><b>*</b>订单金额达到该数值，发放红包：</label>
                            <div class="col-sm-3">
                                <input type="text" name="min_amount" class="form-control input-sm"
                                       value="{{$bonus->min_amount}}"
                                       placeholder="订单金额达到该数值，发放红包" autocomplete="off">
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
            $('#use_start_end_date').daterangepicker(optionDateSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm');
                var t = s + '～' + e + ':59';
                $('#use_start_end_date').val(t);
            });
            $('#send_start_end_date').daterangepicker(optionDateSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm');
                var t = s + '～' + e + ':59';
                $('#send_start_end_date').val(t);
            });

            $('select[name=send_type]').on('change', function () {
                var send_type = $(this).val();
                $('.send_date').hide();
                $('.min_amount').hide();
                if (send_type == 1) {
                    $('.send_date').show();
                } else if (send_type == 2) {
                    $('.send_date').show();
                    $('.min_amount').show();
                }
            });
        });
    </script>
@endsection
@endsection