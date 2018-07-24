@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 发放红包</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>发放红包相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="bonus" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>类型金额：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control input-sm"
                                       value="{{$bonus->type_name}}[￥{{$bonus->type_money}}]" disabled>
                                <input type="hidden" name="bonus_type_id" value="{{$bonus->bonus_id}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>红包数量：</label>
                            <div class="col-sm-4">
                                <input type="text" name="bonus_sum" class="form-control input-sm" value=""
                                       placeholder="红包数量" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <a type="button" class="btn btn-danger clearfix bu-add"
                                   href="javascript:;">　确定　</a>
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

            $('.bu-add').on('click', function () {
                var bonus_type_id = $('input[name=bonus_type_id]').val();
                var bonus_sum = $('input[name=bonus_sum]').val();
                layer.load();
                $.post("{{url('admin/bonus/adduser')}}", {'_token': "{{csrf_token()}}", bonus_type_id: bonus_type_id, bonus_sum:bonus_sum}, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                    layer.closeAll('loading');
                });
            });
        });
    </script>
@endsection
@endsection